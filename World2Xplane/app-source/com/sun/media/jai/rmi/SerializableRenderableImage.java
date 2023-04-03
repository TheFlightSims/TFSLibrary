/*      */ package com.sun.media.jai.rmi;
/*      */ 
/*      */ import java.awt.Image;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InterruptedIOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.net.InetAddress;
/*      */ import java.net.ServerSocket;
/*      */ import java.net.Socket;
/*      */ import java.net.SocketException;
/*      */ import java.net.UnknownHostException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.OperationRegistry;
/*      */ import javax.media.jai.remote.SerializableRenderedImage;
/*      */ import javax.media.jai.remote.SerializableState;
/*      */ import javax.media.jai.remote.SerializerFactory;
/*      */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*      */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*      */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ 
/*      */ public final class SerializableRenderableImage implements RenderableImage, Serializable {
/*      */   private static final int SERVER_TIMEOUT = 60000;
/*      */   
/*      */   private static final String CLOSE_MESSAGE = "CLOSE";
/*      */   
/*      */   private transient boolean isServer;
/*      */   
/*      */   private transient RenderableImage source;
/*      */   
/*      */   private float minX;
/*      */   
/*      */   private float minY;
/*      */   
/*      */   private float width;
/*      */   
/*      */   private float height;
/*      */   
/*  136 */   private transient Vector sources = null;
/*      */   
/*  139 */   private transient Hashtable properties = null;
/*      */   
/*      */   private boolean isDynamic;
/*      */   
/*      */   private InetAddress host;
/*      */   
/*      */   private int port;
/*      */   
/*      */   private transient boolean serverOpen = false;
/*      */   
/*  154 */   private transient ServerSocket serverSocket = null;
/*      */   
/*      */   private transient Thread serverThread;
/*      */   
/*      */   private static transient Hashtable remoteReferenceCount;
/*      */   
/*      */   private boolean useTileCodec = false;
/*      */   
/*  185 */   private OperationRegistry registry = null;
/*      */   
/*  188 */   private String formatName = null;
/*      */   
/*  191 */   private TileCodecParameterList encodingParam = null;
/*      */   
/*  192 */   private TileCodecParameterList decodingParam = null;
/*      */   
/*      */   private static synchronized void incrementRemoteReferenceCount(Object o) {
/*  204 */     if (remoteReferenceCount == null) {
/*  205 */       remoteReferenceCount = new Hashtable();
/*  206 */       remoteReferenceCount.put(o, new Integer(1));
/*      */     } else {
/*  208 */       Integer count = (Integer)remoteReferenceCount.get(o);
/*  209 */       if (count == null) {
/*  210 */         remoteReferenceCount.put(o, new Integer(1));
/*      */       } else {
/*  212 */         remoteReferenceCount.put(o, new Integer(count.intValue() + 1));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static synchronized void decrementRemoteReferenceCount(Object o) {
/*  228 */     if (remoteReferenceCount != null) {
/*  229 */       Integer count = (Integer)remoteReferenceCount.get(o);
/*  230 */       if (count != null)
/*  231 */         if (count.intValue() == 1) {
/*  232 */           remoteReferenceCount.remove(o);
/*      */         } else {
/*  234 */           remoteReferenceCount.put(o, new Integer(count.intValue() - 1));
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public SerializableRenderableImage(RenderableImage source, OperationRegistry registry, String formatName, TileCodecParameterList encodingParam, TileCodecParameterList decodingParam) {
/*  288 */     this(source);
/*  290 */     this.registry = registry;
/*  291 */     this.formatName = formatName;
/*  292 */     this.encodingParam = encodingParam;
/*  293 */     this.decodingParam = decodingParam;
/*  295 */     if (formatName == null)
/*  296 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableRenderableImage2")); 
/*  300 */     if (!formatName.equals(encodingParam.getFormatName()))
/*  301 */       throw new IllegalArgumentException(JaiI18N.getString("UseTileCodec0")); 
/*  305 */     if (!formatName.equals(decodingParam.getFormatName()))
/*  306 */       throw new IllegalArgumentException(JaiI18N.getString("UseTileCodec1")); 
/*  310 */     TileEncoderFactory tileEncoderFactory = (TileEncoderFactory)registry.getFactory("tileEncoder", formatName);
/*  312 */     TileDecoderFactory tileDecoderFactory = (TileDecoderFactory)registry.getFactory("tileDecoder", formatName);
/*  314 */     if (tileEncoderFactory == null || tileDecoderFactory == null)
/*  315 */       throw new RuntimeException(JaiI18N.getString("UseTileCodec2")); 
/*  317 */     this.useTileCodec = true;
/*      */   }
/*      */   
/*      */   public SerializableRenderableImage(RenderableImage source) {
/*  333 */     if (source == null)
/*  334 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableRenderableImage1")); 
/*  338 */     this.isServer = true;
/*  341 */     this.source = source;
/*  344 */     this.minX = source.getMinX();
/*  345 */     this.minY = source.getMinY();
/*  346 */     this.width = source.getWidth();
/*  347 */     this.height = source.getHeight();
/*  348 */     this.isDynamic = source.isDynamic();
/*  350 */     this.sources = new Vector();
/*  351 */     this.sources.add(source);
/*  353 */     this.properties = new Hashtable();
/*  354 */     String[] propertyNames = source.getPropertyNames();
/*  356 */     if (propertyNames != null)
/*  357 */       for (int i = 0; i < propertyNames.length; i++) {
/*  358 */         String propertyName = propertyNames[i];
/*  359 */         this.properties.put(new CaselessStringKey(propertyName), source.getProperty(propertyName));
/*      */       }  
/*      */     try {
/*  366 */       this.host = InetAddress.getLocalHost();
/*  367 */     } catch (UnknownHostException e) {
/*  368 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  372 */     this.serverOpen = false;
/*      */   }
/*      */   
/*      */   private class RenderingServer implements Runnable {
/*      */     private final SerializableRenderableImage this$0;
/*      */     
/*      */     private RenderingServer(SerializableRenderableImage this$0) {
/*  378 */       SerializableRenderableImage.this = SerializableRenderableImage.this;
/*      */     }
/*      */     
/*      */     public void run() {
/*  396 */       while (SerializableRenderableImage.this.serverOpen) {
/*  398 */         Socket socket = null;
/*      */         try {
/*  400 */           socket = SerializableRenderableImage.this.serverSocket.accept();
/*  401 */           socket.setSoLinger(true, 1);
/*  402 */         } catch (InterruptedIOException e) {
/*      */           continue;
/*  406 */         } catch (Exception e) {
/*  407 */           throw new RuntimeException(e.getMessage());
/*      */         } 
/*  412 */         InputStream in = null;
/*  413 */         OutputStream out = null;
/*  414 */         ObjectInputStream objectIn = null;
/*  415 */         ObjectOutputStream objectOut = null;
/*      */         try {
/*  417 */           in = socket.getInputStream();
/*  418 */           out = socket.getOutputStream();
/*  419 */           objectIn = new ObjectInputStream(in);
/*  420 */           objectOut = new ObjectOutputStream(out);
/*  421 */         } catch (Exception e) {
/*  422 */           throw new RuntimeException(e.getMessage());
/*      */         } 
/*  426 */         Object obj = null;
/*      */         try {
/*  428 */           obj = objectIn.readObject();
/*  429 */         } catch (Exception e) {
/*  430 */           throw new RuntimeException(e.getMessage());
/*      */         } 
/*  433 */         RenderedImage ri = null;
/*  436 */         if (obj instanceof String) {
/*  437 */           String str = (String)obj;
/*  439 */           if (str.equals("CLOSE")) {
/*  441 */             SerializableRenderableImage.decrementRemoteReferenceCount(this);
/*      */           } else {
/*      */             SerializableRenderedImage sri;
/*  444 */             if (str.equals("createDefaultRendering")) {
/*  446 */               ri = SerializableRenderableImage.this.source.createDefaultRendering();
/*  448 */             } else if (str.equals("createRendering")) {
/*  451 */               obj = null;
/*      */               try {
/*  453 */                 obj = objectIn.readObject();
/*  454 */               } catch (Exception e) {
/*  455 */                 throw new RuntimeException(e.getMessage());
/*      */               } 
/*  458 */               SerializableState ss = (SerializableState)obj;
/*  459 */               RenderContext rc = (RenderContext)ss.getObject();
/*  461 */               ri = SerializableRenderableImage.this.source.createRendering(rc);
/*  463 */             } else if (str.equals("createScaledRendering")) {
/*  466 */               obj = null;
/*      */               try {
/*  468 */                 obj = objectIn.readObject();
/*  469 */               } catch (Exception e) {
/*  470 */                 throw new RuntimeException(e.getMessage());
/*      */               } 
/*  473 */               int w = ((Integer)obj).intValue();
/*      */               try {
/*  476 */                 obj = objectIn.readObject();
/*  477 */               } catch (Exception e) {
/*  478 */                 throw new RuntimeException(e.getMessage());
/*      */               } 
/*  481 */               int h = ((Integer)obj).intValue();
/*      */               try {
/*  484 */                 obj = objectIn.readObject();
/*  485 */               } catch (Exception e) {
/*  486 */                 throw new RuntimeException(e.getMessage());
/*      */               } 
/*  489 */               SerializableState ss = (SerializableState)obj;
/*  490 */               RenderingHints rh = (RenderingHints)ss.getObject();
/*  492 */               ri = SerializableRenderableImage.this.source.createScaledRendering(w, h, rh);
/*      */             } 
/*  495 */             if (SerializableRenderableImage.this.useTileCodec) {
/*      */               try {
/*  497 */                 sri = new SerializableRenderedImage(ri, true, SerializableRenderableImage.this.registry, SerializableRenderableImage.this.formatName, SerializableRenderableImage.this.encodingParam, SerializableRenderableImage.this.decodingParam);
/*  503 */               } catch (NotSerializableException nse) {
/*  504 */                 throw new RuntimeException(nse.getMessage());
/*      */               } 
/*      */             } else {
/*  507 */               sri = new SerializableRenderedImage(ri, true);
/*      */             } 
/*      */             try {
/*  511 */               objectOut.writeObject(sri);
/*  512 */             } catch (Exception e) {
/*  513 */               throw new RuntimeException(e.getMessage());
/*      */             } 
/*      */           } 
/*      */         } else {
/*  517 */           throw new RuntimeException(JaiI18N.getString("SerializableRenderableImage0"));
/*      */         } 
/*      */         try {
/*  536 */           objectOut.close();
/*  537 */           objectIn.close();
/*  538 */           out.close();
/*  539 */           in.close();
/*  540 */           socket.close();
/*  541 */         } catch (Exception e) {
/*  542 */           throw new RuntimeException(e.getMessage());
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public RenderedImage createDefaultRendering() {
/*      */     RenderedImage ri;
/*  557 */     if (this.isServer)
/*  558 */       return this.source.createDefaultRendering(); 
/*  562 */     Socket socket = connectToServer();
/*  566 */     OutputStream out = null;
/*  567 */     ObjectOutputStream objectOut = null;
/*  568 */     InputStream in = null;
/*  569 */     ObjectInputStream objectIn = null;
/*      */     try {
/*  571 */       out = socket.getOutputStream();
/*  572 */       objectOut = new ObjectOutputStream(out);
/*  573 */       in = socket.getInputStream();
/*  574 */       objectIn = new ObjectInputStream(in);
/*  575 */     } catch (Exception e) {
/*  576 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*      */     try {
/*  581 */       objectOut.writeObject("createDefaultRendering");
/*  582 */     } catch (Exception e) {
/*  583 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  587 */     Object object = null;
/*      */     try {
/*  589 */       object = objectIn.readObject();
/*  590 */     } catch (Exception e) {
/*  591 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  595 */     if (object instanceof SerializableRenderedImage) {
/*  596 */       ri = (RenderedImage)object;
/*      */     } else {
/*  598 */       ri = null;
/*      */     } 
/*      */     try {
/*  603 */       out.close();
/*  604 */       objectOut.close();
/*  605 */       in.close();
/*  606 */       objectIn.close();
/*  607 */       socket.close();
/*  608 */     } catch (Exception e) {
/*  609 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  612 */     return ri;
/*      */   }
/*      */   
/*      */   public RenderedImage createRendering(RenderContext renderContext) {
/*  618 */     if (this.isServer)
/*  619 */       return this.source.createRendering(renderContext); 
/*  623 */     Socket socket = connectToServer();
/*  627 */     OutputStream out = null;
/*  628 */     ObjectOutputStream objectOut = null;
/*  629 */     InputStream in = null;
/*  630 */     ObjectInputStream objectIn = null;
/*      */     try {
/*  632 */       out = socket.getOutputStream();
/*  633 */       objectOut = new ObjectOutputStream(out);
/*  634 */       in = socket.getInputStream();
/*  635 */       objectIn = new ObjectInputStream(in);
/*  636 */     } catch (Exception e) {
/*  637 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*      */     try {
/*  643 */       objectOut.writeObject("createRendering");
/*  644 */       objectOut.writeObject(SerializerFactory.getState(renderContext, null));
/*  646 */     } catch (Exception e) {
/*  647 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  651 */     Object object = null;
/*      */     try {
/*  653 */       object = objectIn.readObject();
/*  654 */     } catch (Exception e) {
/*  655 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  658 */     RenderedImage ri = (RenderedImage)object;
/*      */     try {
/*  662 */       out.close();
/*  663 */       objectOut.close();
/*  664 */       in.close();
/*  665 */       objectIn.close();
/*  666 */       socket.close();
/*  667 */     } catch (Exception e) {
/*  668 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  671 */     return ri;
/*      */   }
/*      */   
/*      */   public RenderedImage createScaledRendering(int w, int h, RenderingHints hints) {
/*  678 */     if (this.isServer)
/*  679 */       return this.source.createScaledRendering(w, h, hints); 
/*  683 */     Socket socket = connectToServer();
/*  687 */     OutputStream out = null;
/*  688 */     ObjectOutputStream objectOut = null;
/*  689 */     InputStream in = null;
/*  690 */     ObjectInputStream objectIn = null;
/*      */     try {
/*  692 */       out = socket.getOutputStream();
/*  693 */       objectOut = new ObjectOutputStream(out);
/*  694 */       in = socket.getInputStream();
/*  695 */       objectIn = new ObjectInputStream(in);
/*  696 */     } catch (Exception e) {
/*  697 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*      */     try {
/*  703 */       objectOut.writeObject("createScaledRendering");
/*  704 */       objectOut.writeObject(new Integer(w));
/*  705 */       objectOut.writeObject(new Integer(h));
/*  706 */       objectOut.writeObject(SerializerFactory.getState(hints, null));
/*  707 */     } catch (Exception e) {
/*  708 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  712 */     Object object = null;
/*      */     try {
/*  714 */       object = objectIn.readObject();
/*  715 */     } catch (Exception e) {
/*  716 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  719 */     RenderedImage ri = (RenderedImage)object;
/*      */     try {
/*  723 */       out.close();
/*  724 */       objectOut.close();
/*  725 */       in.close();
/*  726 */       objectIn.close();
/*  727 */       socket.close();
/*  728 */     } catch (Exception e) {
/*  729 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  732 */     return ri;
/*      */   }
/*      */   
/*      */   public float getHeight() {
/*  736 */     return this.height;
/*      */   }
/*      */   
/*      */   public float getMinX() {
/*  740 */     return this.minX;
/*      */   }
/*      */   
/*      */   public float getMinY() {
/*  744 */     return this.minY;
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/*  750 */     Object property = this.properties.get(new CaselessStringKey(name));
/*  751 */     return (property == null) ? Image.UndefinedProperty : property;
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/*  755 */     String[] names = null;
/*  756 */     if (!this.properties.isEmpty()) {
/*  757 */       names = new String[this.properties.size()];
/*  758 */       Enumeration keys = this.properties.keys();
/*  759 */       int index = 0;
/*  761 */       while (keys.hasMoreElements()) {
/*  762 */         CaselessStringKey key = keys.nextElement();
/*  763 */         names[index++] = key.getName();
/*      */       } 
/*      */     } 
/*  766 */     return names;
/*      */   }
/*      */   
/*      */   public Vector getSources() {
/*  776 */     return this.sources;
/*      */   }
/*      */   
/*      */   public boolean isDynamic() {
/*  783 */     return this.isDynamic;
/*      */   }
/*      */   
/*      */   public float getWidth() {
/*  787 */     return this.width;
/*      */   }
/*      */   
/*      */   private synchronized void openServer() throws IOException, SocketException {
/*  804 */     if (!this.serverOpen) {
/*  806 */       this.serverSocket = new ServerSocket(0);
/*  809 */       this.serverSocket.setSoTimeout(60000);
/*  812 */       this.port = this.serverSocket.getLocalPort();
/*  815 */       this.serverOpen = true;
/*  818 */       this.serverThread = new Thread(new RenderingServer());
/*  819 */       this.serverThread.start();
/*  822 */       incrementRemoteReferenceCount(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void closeClient() {
/*  832 */     Socket socket = connectToServer();
/*  836 */     OutputStream out = null;
/*  837 */     ObjectOutputStream objectOut = null;
/*      */     try {
/*  839 */       out = socket.getOutputStream();
/*  840 */       objectOut = new ObjectOutputStream(out);
/*  841 */     } catch (Exception e) {
/*  842 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*      */     try {
/*  847 */       objectOut.writeObject("CLOSE");
/*  848 */     } catch (Exception e) {
/*  849 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*      */     try {
/*  854 */       out.close();
/*  855 */       objectOut.close();
/*  856 */       socket.close();
/*  857 */     } catch (Exception e) {
/*  858 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   private Socket connectToServer() {
/*  868 */     Socket socket = null;
/*      */     try {
/*  870 */       socket = new Socket(this.host, this.port);
/*  871 */       socket.setSoLinger(true, 1);
/*  872 */     } catch (Exception e) {
/*  873 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  876 */     return socket;
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/*  884 */     dispose();
/*  887 */     super.finalize();
/*      */   }
/*      */   
/*      */   public void dispose() {
/*  918 */     if (this.isServer) {
/*  919 */       if (this.serverOpen) {
/*  921 */         this.serverOpen = false;
/*      */         try {
/*  925 */           this.serverThread.join(120000L);
/*  926 */         } catch (Exception e) {}
/*      */         try {
/*  932 */           this.serverSocket.close();
/*  933 */         } catch (Exception e) {}
/*      */       } 
/*      */     } else {
/*  939 */       closeClient();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*      */     try {
/*  953 */       openServer();
/*  954 */     } catch (Exception e1) {
/*  955 */       if (e1 instanceof SocketException && 
/*  956 */         this.serverSocket != null)
/*      */         try {
/*  958 */           this.serverSocket.close();
/*  959 */         } catch (IOException e2) {} 
/*  966 */       this.serverOpen = false;
/*      */     } 
/*  970 */     out.defaultWriteObject();
/*  973 */     Hashtable propertyTable = this.properties;
/*  974 */     boolean propertiesCloned = false;
/*  975 */     Enumeration keys = propertyTable.keys();
/*  976 */     while (keys.hasMoreElements()) {
/*  977 */       Object key = keys.nextElement();
/*  978 */       if (!(this.properties.get(key) instanceof Serializable)) {
/*  979 */         if (!propertiesCloned) {
/*  980 */           propertyTable = (Hashtable)this.properties.clone();
/*  981 */           propertiesCloned = true;
/*      */         } 
/*  983 */         propertyTable.remove(key);
/*      */       } 
/*      */     } 
/*  988 */     out.writeObject(propertyTable);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  999 */     this.isServer = false;
/* 1000 */     this.source = null;
/* 1001 */     this.serverOpen = false;
/* 1002 */     this.serverSocket = null;
/* 1003 */     this.serverThread = null;
/* 1006 */     in.defaultReadObject();
/* 1009 */     this.properties = (Hashtable)in.readObject();
/*      */   }
/*      */   
/*      */   SerializableRenderableImage() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\SerializableRenderableImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */