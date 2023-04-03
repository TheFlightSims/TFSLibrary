/*      */ package javax.media.jai.remote;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Image;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
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
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.OperationRegistry;
/*      */ import javax.media.jai.ParameterListDescriptor;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.RasterAccessor;
/*      */ import javax.media.jai.RasterFormatTag;
/*      */ import javax.media.jai.RemoteImage;
/*      */ import javax.media.jai.TileCache;
/*      */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*      */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*      */ import javax.media.jai.tilecodec.TileDecoder;
/*      */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*      */ import javax.media.jai.tilecodec.TileEncoder;
/*      */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public final class SerializableRenderedImage implements RenderedImage, Serializable {
/*      */   private static final int SERVER_TIMEOUT = 60000;
/*      */   
/*      */   private static final String CLOSE_MESSAGE = "CLOSE";
/*      */   
/*      */   private static final String CLOSE_ACK = "CLOSE_ACK";
/*      */   
/*      */   private Object UID;
/*      */   
/*      */   private transient boolean isServer;
/*      */   
/*      */   private boolean isSourceRemote;
/*      */   
/*      */   private transient RenderedImage source;
/*      */   
/*      */   private int minX;
/*      */   
/*      */   private int minY;
/*      */   
/*      */   private int width;
/*      */   
/*      */   private int height;
/*      */   
/*      */   private int minTileX;
/*      */   
/*      */   private int minTileY;
/*      */   
/*      */   private int numXTiles;
/*      */   
/*      */   private int numYTiles;
/*      */   
/*      */   private int tileWidth;
/*      */   
/*      */   private int tileHeight;
/*      */   
/*      */   private int tileGridXOffset;
/*      */   
/*      */   private int tileGridYOffset;
/*      */   
/*  214 */   private transient SampleModel sampleModel = null;
/*      */   
/*  217 */   private transient ColorModel colorModel = null;
/*      */   
/*  220 */   private transient Vector sources = null;
/*      */   
/*  223 */   private transient Hashtable properties = null;
/*      */   
/*      */   private boolean useDeepCopy;
/*      */   
/*      */   private Rectangle imageBounds;
/*      */   
/*      */   private transient Raster imageRaster;
/*      */   
/*      */   private InetAddress host;
/*      */   
/*      */   private int port;
/*      */   
/*      */   private transient boolean serverOpen = false;
/*      */   
/*  244 */   private transient ServerSocket serverSocket = null;
/*      */   
/*      */   private transient Thread serverThread;
/*      */   
/*      */   private String formatName;
/*      */   
/*      */   private transient OperationRegistry registry;
/*      */   
/*      */   private static transient Hashtable remoteReferenceCount;
/*      */   
/*      */   private boolean useTileCodec = false;
/*      */   
/*  278 */   private transient TileDecoderFactory tileDecoderFactory = null;
/*      */   
/*  281 */   private transient TileEncoderFactory tileEncoderFactory = null;
/*      */   
/*  284 */   private TileCodecParameterList encodingParam = null;
/*      */   
/*  285 */   private TileCodecParameterList decodingParam = null;
/*      */   
/*      */   private static synchronized void incrementRemoteReferenceCount(Object o) {
/*  297 */     if (remoteReferenceCount == null) {
/*  298 */       remoteReferenceCount = new Hashtable();
/*  299 */       remoteReferenceCount.put(o, new Integer(1));
/*      */     } else {
/*  301 */       Integer count = (Integer)remoteReferenceCount.get(o);
/*  302 */       if (count == null) {
/*  303 */         remoteReferenceCount.put(o, new Integer(1));
/*      */       } else {
/*  305 */         remoteReferenceCount.put(o, new Integer(count.intValue() + 1));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static synchronized void decrementRemoteReferenceCount(Object o) {
/*  321 */     if (remoteReferenceCount != null) {
/*  322 */       Integer count = (Integer)remoteReferenceCount.get(o);
/*  323 */       if (count != null)
/*  324 */         if (count.intValue() == 1) {
/*  325 */           remoteReferenceCount.remove(o);
/*      */         } else {
/*  327 */           remoteReferenceCount.put(o, new Integer(count.intValue() - 1));
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public SerializableRenderedImage(RenderedImage source, boolean useDeepCopy, OperationRegistry registry, String formatName, TileCodecParameterList encodingParam, TileCodecParameterList decodingParam) throws NotSerializableException {
/*  390 */     this(source, useDeepCopy, false);
/*  394 */     if (formatName == null)
/*      */       return; 
/*  397 */     this.formatName = formatName;
/*  400 */     if (registry == null)
/*  401 */       registry = JAI.getDefaultInstance().getOperationRegistry(); 
/*  402 */     this.registry = registry;
/*  405 */     if (encodingParam == null) {
/*  406 */       TileCodecDescriptor tcd = getTileCodecDescriptor("tileEncoder", formatName);
/*  408 */       encodingParam = tcd.getDefaultParameters("tileEncoder");
/*  409 */     } else if (!formatName.equals(encodingParam.getFormatName())) {
/*  410 */       throw new IllegalArgumentException(JaiI18N.getString("UseTileCodec0"));
/*      */     } 
/*  414 */     if (decodingParam == null) {
/*  415 */       TileCodecDescriptor tcd = getTileCodecDescriptor("tileDecoder", formatName);
/*  417 */       decodingParam = tcd.getDefaultParameters("tileDecoder");
/*  418 */     } else if (!formatName.equals(decodingParam.getFormatName())) {
/*  419 */       throw new IllegalArgumentException(JaiI18N.getString("UseTileCodec1"));
/*      */     } 
/*  422 */     this.tileEncoderFactory = (TileEncoderFactory)registry.getFactory("tileEncoder", formatName);
/*  424 */     this.tileDecoderFactory = (TileDecoderFactory)registry.getFactory("tileDecoder", formatName);
/*  426 */     if (this.tileEncoderFactory == null || this.tileDecoderFactory == null)
/*  427 */       throw new RuntimeException(JaiI18N.getString("UseTileCodec2")); 
/*  429 */     this.encodingParam = encodingParam;
/*  430 */     this.decodingParam = decodingParam;
/*  431 */     this.useTileCodec = true;
/*      */   }
/*      */   
/*      */   public SerializableRenderedImage(RenderedImage source, boolean useDeepCopy) {
/*  454 */     this(source, useDeepCopy, true);
/*      */   }
/*      */   
/*      */   public SerializableRenderedImage(RenderedImage source) {
/*  473 */     this(source, false, true);
/*      */   }
/*      */   
/*      */   private SerializableRenderedImage(RenderedImage source, boolean useDeepCopy, boolean checkDataBuffer) {
/*  492 */     this.UID = ImageUtil.generateID(this);
/*  494 */     if (source == null)
/*  495 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableRenderedImage0")); 
/*  498 */     SampleModel sm = source.getSampleModel();
/*  499 */     if (sm != null && SerializerFactory.getSerializer(sm.getClass()) == null)
/*  501 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableRenderedImage2")); 
/*  504 */     ColorModel cm = source.getColorModel();
/*  505 */     if (cm != null && SerializerFactory.getSerializer(cm.getClass()) == null)
/*  507 */       throw new IllegalArgumentException(JaiI18N.getString("SerializableRenderedImage3")); 
/*  510 */     if (checkDataBuffer) {
/*  511 */       Raster ras = source.getTile(source.getMinTileX(), source.getMinTileY());
/*  512 */       if (ras != null) {
/*  513 */         DataBuffer db = ras.getDataBuffer();
/*  514 */         if (db != null && SerializerFactory.getSerializer(db.getClass()) == null)
/*  516 */           throw new IllegalArgumentException(JaiI18N.getString("SerializableRenderedImage4")); 
/*      */       } 
/*      */     } 
/*  521 */     this.isServer = true;
/*  524 */     this.useDeepCopy = useDeepCopy;
/*  527 */     this.source = source;
/*  530 */     this.isSourceRemote = source instanceof RemoteImage;
/*  533 */     this.minX = source.getMinX();
/*  534 */     this.minY = source.getMinY();
/*  535 */     this.width = source.getWidth();
/*  536 */     this.height = source.getHeight();
/*  537 */     this.minTileX = source.getMinTileX();
/*  538 */     this.minTileY = source.getMinTileY();
/*  539 */     this.numXTiles = source.getNumXTiles();
/*  540 */     this.numYTiles = source.getNumYTiles();
/*  541 */     this.tileWidth = source.getTileWidth();
/*  542 */     this.tileHeight = source.getTileHeight();
/*  543 */     this.tileGridXOffset = source.getTileGridXOffset();
/*  544 */     this.tileGridYOffset = source.getTileGridYOffset();
/*  545 */     this.sampleModel = source.getSampleModel();
/*  546 */     this.colorModel = source.getColorModel();
/*  547 */     this.sources = new Vector();
/*  548 */     this.sources.add(source);
/*  549 */     this.properties = new Hashtable();
/*  552 */     String[] propertyNames = source.getPropertyNames();
/*  553 */     if (propertyNames != null)
/*  554 */       for (int i = 0; i < propertyNames.length; i++)
/*  555 */         this.properties.put(propertyNames[i], source.getProperty(propertyNames[i]));  
/*  561 */     this.imageBounds = new Rectangle(this.minX, this.minY, this.width, this.height);
/*      */     try {
/*  565 */       this.host = InetAddress.getLocalHost();
/*  566 */     } catch (UnknownHostException e) {
/*  567 */       throw new RuntimeException(e.getMessage());
/*      */     } 
/*  571 */     this.serverOpen = false;
/*      */   }
/*      */   
/*      */   private class TileServer implements Runnable {
/*      */     private final SerializableRenderedImage this$0;
/*      */     
/*      */     private TileServer(SerializableRenderedImage this$0) {
/*  577 */       SerializableRenderedImage.this = SerializableRenderedImage.this;
/*      */     }
/*      */     
/*      */     public void run() {
/*  594 */       while (SerializableRenderedImage.this.serverOpen) {
/*  596 */         Socket socket = null;
/*      */         try {
/*  598 */           socket = SerializableRenderedImage.this.serverSocket.accept();
/*  599 */           socket.setSoLinger(true, 1);
/*  600 */         } catch (InterruptedIOException e) {
/*      */           continue;
/*  604 */         } catch (SocketException e) {
/*  605 */           SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage5"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage5"), e));
/*  608 */         } catch (IOException e) {
/*  609 */           SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage6"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage6"), e));
/*      */         } 
/*  615 */         InputStream in = null;
/*  616 */         OutputStream out = null;
/*  617 */         ObjectInputStream objectIn = null;
/*  618 */         ObjectOutputStream objectOut = null;
/*      */         try {
/*  620 */           in = socket.getInputStream();
/*  621 */           out = socket.getOutputStream();
/*  622 */           objectIn = new ObjectInputStream(in);
/*  623 */           objectOut = new ObjectOutputStream(out);
/*  624 */         } catch (IOException e) {
/*  625 */           SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage7"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage7"), e));
/*      */         } 
/*  631 */         Object obj = null;
/*      */         try {
/*  633 */           obj = objectIn.readObject();
/*  634 */         } catch (IOException e) {
/*  635 */           SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage8"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage8"), e));
/*  638 */         } catch (ClassNotFoundException e) {
/*  639 */           SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage9"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage9"), e));
/*      */         } 
/*  644 */         if (obj instanceof String && ((String)obj).equals("CLOSE")) {
/*      */           try {
/*  648 */             objectOut.writeObject("CLOSE_ACK");
/*  649 */           } catch (IOException e) {
/*  650 */             SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage17"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage17"), e));
/*      */           } 
/*  658 */           SerializableRenderedImage.decrementRemoteReferenceCount(this);
/*  659 */         } else if (obj instanceof Rectangle) {
/*  662 */           Raster raster = SerializableRenderedImage.this.source.getData((Rectangle)obj);
/*  666 */           if (SerializableRenderedImage.this.useTileCodec) {
/*  667 */             byte[] buf = SerializableRenderedImage.this.encodeRasterToByteArray(raster);
/*      */             try {
/*  669 */               objectOut.writeObject(buf);
/*  670 */             } catch (IOException e) {
/*  671 */               SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage10"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage10"), e));
/*      */             } 
/*      */           } else {
/*      */             try {
/*  678 */               objectOut.writeObject(SerializerFactory.getState(raster, null));
/*  679 */             } catch (IOException e) {
/*  680 */               SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage10"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage10"), e));
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         try {
/*  702 */           objectOut.flush();
/*  703 */           socket.shutdownOutput();
/*  704 */           socket.shutdownInput();
/*  705 */           objectOut.close();
/*  706 */           objectIn.close();
/*  707 */           out.close();
/*  708 */           in.close();
/*  709 */           socket.close();
/*  710 */         } catch (IOException e) {
/*  711 */           SerializableRenderedImage.this.sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage10"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage10"), e));
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public WritableRaster copyData(WritableRaster dest) {
/*      */     Rectangle region;
/*  722 */     if (this.isServer || this.isSourceRemote)
/*  723 */       return this.source.copyData(dest); 
/*  727 */     if (dest == null) {
/*  728 */       region = this.imageBounds;
/*  729 */       SampleModel destSM = getSampleModel().createCompatibleSampleModel(region.width, region.height);
/*  732 */       dest = Raster.createWritableRaster(destSM, new Point(region.x, region.y));
/*      */     } else {
/*  736 */       region = dest.getBounds().intersection(this.imageBounds);
/*      */     } 
/*  739 */     if (!region.isEmpty()) {
/*  740 */       int startTileX = PlanarImage.XToTileX(region.x, this.tileGridXOffset, this.tileWidth);
/*  743 */       int startTileY = PlanarImage.YToTileY(region.y, this.tileGridYOffset, this.tileHeight);
/*  746 */       int endTileX = PlanarImage.XToTileX(region.x + region.width - 1, this.tileGridXOffset, this.tileWidth);
/*  749 */       int endTileY = PlanarImage.YToTileY(region.y + region.height - 1, this.tileGridYOffset, this.tileHeight);
/*  753 */       SampleModel[] sampleModels = { getSampleModel() };
/*  754 */       int tagID = RasterAccessor.findCompatibleTag(sampleModels, dest.getSampleModel());
/*  758 */       RasterFormatTag srcTag = new RasterFormatTag(getSampleModel(), tagID);
/*  760 */       RasterFormatTag dstTag = new RasterFormatTag(dest.getSampleModel(), tagID);
/*  763 */       for (int ty = startTileY; ty <= endTileY; ty++) {
/*  764 */         for (int tx = startTileX; tx <= endTileX; tx++) {
/*  765 */           Raster tile = getTile(tx, ty);
/*  766 */           Rectangle subRegion = region.intersection(tile.getBounds());
/*  769 */           RasterAccessor s = new RasterAccessor(tile, subRegion, srcTag, getColorModel());
/*  772 */           RasterAccessor d = new RasterAccessor(dest, subRegion, dstTag, null);
/*  775 */           ImageUtil.copyRaster(s, d);
/*      */         } 
/*      */       } 
/*      */     } 
/*  780 */     return dest;
/*      */   }
/*      */   
/*      */   public ColorModel getColorModel() {
/*  784 */     return this.colorModel;
/*      */   }
/*      */   
/*      */   public Raster getData() {
/*  788 */     if (this.isServer || this.isSourceRemote)
/*  789 */       return this.source.getData(); 
/*  792 */     return getData(this.imageBounds);
/*      */   }
/*      */   
/*      */   public Raster getData(Rectangle rect) {
/*  796 */     Raster raster = null;
/*  801 */     if (this.isServer || this.isSourceRemote) {
/*  802 */       raster = this.source.getData(rect);
/*  803 */     } else if (this.useDeepCopy) {
/*  804 */       raster = this.imageRaster.createChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, null);
/*      */     } else {
/*  813 */       Socket socket = connectToServer();
/*  817 */       OutputStream out = null;
/*  818 */       ObjectOutputStream objectOut = null;
/*  819 */       InputStream in = null;
/*  820 */       ObjectInputStream objectIn = null;
/*      */       try {
/*  822 */         out = socket.getOutputStream();
/*  823 */         objectOut = new ObjectOutputStream(out);
/*  824 */         in = socket.getInputStream();
/*  825 */         objectIn = new ObjectInputStream(in);
/*  826 */       } catch (IOException e) {
/*  827 */         sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage7"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage7"), e));
/*      */       } 
/*      */       try {
/*  834 */         objectOut.writeObject(rect);
/*  835 */       } catch (IOException e) {
/*  836 */         sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage10"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage10"), e));
/*      */       } 
/*  842 */       Object object = null;
/*      */       try {
/*  844 */         object = objectIn.readObject();
/*  845 */       } catch (IOException e) {
/*  846 */         sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage8"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage8"), e));
/*  849 */       } catch (ClassNotFoundException e) {
/*  850 */         sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage9"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage9"), e));
/*      */       } 
/*  854 */       if (this.useTileCodec) {
/*  855 */         byte[] buf = (byte[])object;
/*  856 */         raster = decodeRasterFromByteArray(buf);
/*      */       } else {
/*  859 */         if (!(object instanceof SerializableState))
/*  860 */           raster = null; 
/*  862 */         SerializableState ss = (SerializableState)object;
/*  863 */         Class c = ss.getObjectClass();
/*  864 */         if (Raster.class.isAssignableFrom(c)) {
/*  865 */           raster = (Raster)ss.getObject();
/*      */         } else {
/*  867 */           raster = null;
/*      */         } 
/*      */       } 
/*      */       try {
/*  872 */         objectOut.flush();
/*  873 */         socket.shutdownOutput();
/*  874 */         socket.shutdownInput();
/*  875 */         objectOut.close();
/*  876 */         out.close();
/*  877 */         objectIn.close();
/*  878 */         in.close();
/*  879 */         socket.close();
/*  880 */       } catch (IOException e) {
/*  881 */         String message = JaiI18N.getString("SerializableRenderedImage11");
/*  883 */         sendExceptionToListener(message, (Exception)new ImagingException(message, e));
/*      */       } 
/*  890 */       if (this.imageBounds.equals(rect)) {
/*  892 */         closeClient();
/*  894 */         this.imageRaster = raster;
/*  895 */         this.useDeepCopy = true;
/*      */       } 
/*      */     } 
/*  899 */     return raster;
/*      */   }
/*      */   
/*      */   public int getHeight() {
/*  903 */     return this.height;
/*      */   }
/*      */   
/*      */   public int getMinTileX() {
/*  907 */     return this.minTileX;
/*      */   }
/*      */   
/*      */   public int getMinTileY() {
/*  911 */     return this.minTileY;
/*      */   }
/*      */   
/*      */   public int getMinX() {
/*  915 */     return this.minX;
/*      */   }
/*      */   
/*      */   public int getMinY() {
/*  919 */     return this.minY;
/*      */   }
/*      */   
/*      */   public int getNumXTiles() {
/*  923 */     return this.numXTiles;
/*      */   }
/*      */   
/*      */   public int getNumYTiles() {
/*  927 */     return this.numYTiles;
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/*  934 */     Object property = this.properties.get(name);
/*  935 */     return (property == null) ? Image.UndefinedProperty : property;
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/*  939 */     String[] names = null;
/*  940 */     if (!this.properties.isEmpty()) {
/*  941 */       names = new String[this.properties.size()];
/*  942 */       Enumeration keys = this.properties.keys();
/*  943 */       int index = 0;
/*  944 */       while (keys.hasMoreElements())
/*  948 */         names[index++] = keys.nextElement(); 
/*      */     } 
/*  951 */     return names;
/*      */   }
/*      */   
/*      */   public SampleModel getSampleModel() {
/*  955 */     return this.sampleModel;
/*      */   }
/*      */   
/*      */   public Vector getSources() {
/*  965 */     return this.sources;
/*      */   }
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/*  969 */     if (this.isServer || this.isSourceRemote)
/*  970 */       return this.source.getTile(tileX, tileY); 
/*  973 */     TileCache cache = JAI.getDefaultInstance().getTileCache();
/*  974 */     if (cache != null) {
/*  975 */       Raster raster = cache.getTile(this, tileX, tileY);
/*  976 */       if (raster != null)
/*  977 */         return raster; 
/*      */     } 
/*  981 */     Rectangle imageBounds = new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*  983 */     Rectangle destRect = imageBounds.intersection(new Rectangle(tileXToX(tileX), tileYToY(tileY), getTileWidth(), getTileHeight()));
/*  989 */     Raster tile = getData(destRect);
/*  991 */     if (cache != null)
/*  992 */       cache.add(this, tileX, tileY, tile); 
/*  995 */     return tile;
/*      */   }
/*      */   
/*      */   public Object getImageID() {
/* 1006 */     return this.UID;
/*      */   }
/*      */   
/*      */   private int tileXToX(int tx) {
/* 1022 */     return PlanarImage.tileXToX(tx, getTileGridXOffset(), getTileWidth());
/*      */   }
/*      */   
/*      */   private int tileYToY(int ty) {
/* 1038 */     return PlanarImage.tileYToY(ty, getTileGridYOffset(), getTileHeight());
/*      */   }
/*      */   
/*      */   public int getTileGridXOffset() {
/* 1042 */     return this.tileGridXOffset;
/*      */   }
/*      */   
/*      */   public int getTileGridYOffset() {
/* 1046 */     return this.tileGridYOffset;
/*      */   }
/*      */   
/*      */   public int getTileHeight() {
/* 1050 */     return this.tileHeight;
/*      */   }
/*      */   
/*      */   public int getTileWidth() {
/* 1054 */     return this.tileWidth;
/*      */   }
/*      */   
/*      */   public int getWidth() {
/* 1058 */     return this.width;
/*      */   }
/*      */   
/*      */   private synchronized void openServer() throws IOException, SocketException {
/* 1075 */     if (!this.serverOpen) {
/* 1077 */       this.serverSocket = new ServerSocket(0);
/* 1080 */       this.serverSocket.setSoTimeout(60000);
/* 1083 */       this.port = this.serverSocket.getLocalPort();
/* 1086 */       this.serverOpen = true;
/* 1089 */       this.serverThread = new Thread(new TileServer());
/* 1090 */       this.serverThread.setDaemon(true);
/* 1091 */       this.serverThread.start();
/* 1094 */       incrementRemoteReferenceCount(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void closeClient() {
/* 1105 */     Socket socket = connectToServer();
/* 1109 */     OutputStream out = null;
/* 1110 */     ObjectOutputStream objectOut = null;
/* 1111 */     ObjectInputStream objectIn = null;
/*      */     try {
/* 1113 */       out = socket.getOutputStream();
/* 1114 */       objectOut = new ObjectOutputStream(out);
/* 1115 */       objectIn = new ObjectInputStream(socket.getInputStream());
/* 1116 */     } catch (IOException e) {
/* 1117 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage7"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage7"), e));
/*      */     } 
/*      */     try {
/* 1124 */       objectOut.writeObject("CLOSE");
/* 1125 */     } catch (IOException e) {
/* 1126 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage13"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage13"), e));
/*      */     } 
/*      */     try {
/* 1132 */       objectIn.readObject();
/* 1133 */     } catch (IOException e) {
/* 1134 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage8"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage8"), e));
/* 1138 */     } catch (ClassNotFoundException cnfe) {
/* 1139 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage9"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage9"), cnfe));
/*      */     } 
/*      */     try {
/* 1147 */       objectOut.flush();
/* 1148 */       socket.shutdownOutput();
/* 1149 */       objectOut.close();
/* 1150 */       out.close();
/* 1151 */       objectIn.close();
/* 1152 */       socket.close();
/* 1153 */     } catch (IOException e) {
/* 1154 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage11"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage11"), e));
/*      */     } 
/*      */   }
/*      */   
/*      */   private Socket connectToServer() {
/* 1167 */     Socket socket = null;
/*      */     try {
/* 1169 */       socket = new Socket(this.host, this.port);
/* 1170 */       socket.setSoLinger(true, 1);
/* 1171 */     } catch (IOException e) {
/* 1172 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage14"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage14"), e));
/*      */     } 
/* 1177 */     return socket;
/*      */   }
/*      */   
/*      */   private byte[] encodeRasterToByteArray(Raster raster) {
/* 1185 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 1186 */     TileEncoder encoder = this.tileEncoderFactory.createEncoder(bos, this.encodingParam, raster.getSampleModel());
/*      */     try {
/* 1191 */       encoder.encode(raster);
/* 1192 */       return bos.toByteArray();
/* 1193 */     } catch (IOException e) {
/* 1194 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage15"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage15"), e));
/* 1198 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private Raster decodeRasterFromByteArray(byte[] buf) {
/* 1205 */     ByteArrayInputStream bis = new ByteArrayInputStream(buf);
/* 1211 */     if (this.tileDecoderFactory == null) {
/* 1214 */       if (this.registry == null)
/* 1215 */         this.registry = JAI.getDefaultInstance().getOperationRegistry(); 
/* 1216 */       this.tileDecoderFactory = (TileDecoderFactory)this.registry.getFactory("tileDecoder", this.formatName);
/* 1219 */       TileCodecParameterList temp = this.decodingParam;
/* 1221 */       if (temp != null) {
/* 1222 */         TileCodecDescriptor tcd = getTileCodecDescriptor("tileDecoder", this.formatName);
/* 1224 */         ParameterListDescriptor pld = tcd.getParameterListDescriptor("tileDecoder");
/* 1226 */         this.decodingParam = new TileCodecParameterList(this.formatName, new String[] { "tileDecoder" }, pld);
/* 1230 */         String[] names = pld.getParamNames();
/* 1232 */         if (names != null)
/* 1233 */           for (int i = 0; i < names.length; i++)
/* 1234 */             this.decodingParam.setParameter(names[i], temp.getObjectParameter(names[i]));  
/*      */       } else {
/* 1238 */         this.decodingParam = getTileCodecDescriptor("tileDecoder", this.formatName).getDefaultParameters("tileDecoder");
/*      */       } 
/*      */     } 
/* 1242 */     TileDecoder decoder = this.tileDecoderFactory.createDecoder(bis, this.decodingParam);
/*      */     try {
/* 1245 */       return decoder.decode();
/* 1246 */     } catch (IOException e) {
/* 1247 */       sendExceptionToListener(JaiI18N.getString("SerializableRenderedImage16"), (Exception)new ImagingException(JaiI18N.getString("SerializableRenderedImage16"), e));
/* 1251 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 1259 */     dispose();
/* 1262 */     super.finalize();
/*      */   }
/*      */   
/*      */   public void dispose() {
/* 1293 */     if (this.isServer) {
/* 1294 */       if (this.serverOpen) {
/* 1296 */         this.serverOpen = false;
/*      */         try {
/* 1300 */           this.serverThread.join(120000L);
/* 1301 */         } catch (Exception e) {}
/*      */         try {
/* 1307 */           this.serverSocket.close();
/* 1308 */         } catch (Exception e) {}
/*      */       } 
/*      */     } else {
/* 1314 */       closeClient();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1325 */     if (!this.useDeepCopy)
/*      */       try {
/* 1328 */         openServer();
/* 1329 */       } catch (Exception e1) {
/* 1330 */         if (e1 instanceof SocketException && 
/* 1331 */           this.serverSocket != null)
/*      */           try {
/* 1333 */             this.serverSocket.close();
/* 1334 */           } catch (IOException e2) {} 
/* 1341 */         this.serverOpen = false;
/* 1342 */         this.useDeepCopy = true;
/*      */       }  
/* 1347 */     out.defaultWriteObject();
/* 1350 */     if (this.isSourceRemote) {
/* 1351 */       String remoteClass = this.source.getClass().getName();
/* 1352 */       out.writeObject(this.source.getProperty(remoteClass + ".serverName"));
/* 1353 */       out.writeObject(this.source.getProperty(remoteClass + ".id"));
/*      */     } 
/* 1357 */     Hashtable propertyTable = this.properties;
/* 1358 */     boolean propertiesCloned = false;
/* 1359 */     Enumeration keys = propertyTable.keys();
/* 1360 */     while (keys.hasMoreElements()) {
/* 1361 */       Object key = keys.nextElement();
/* 1362 */       if (!(this.properties.get(key) instanceof Serializable)) {
/* 1363 */         if (!propertiesCloned) {
/* 1364 */           propertyTable = (Hashtable)this.properties.clone();
/* 1365 */           propertiesCloned = true;
/*      */         } 
/* 1367 */         propertyTable.remove(key);
/*      */       } 
/*      */     } 
/* 1372 */     out.writeObject(SerializerFactory.getState(this.sampleModel, null));
/* 1373 */     out.writeObject(SerializerFactory.getState(this.colorModel, null));
/* 1374 */     out.writeObject(propertyTable);
/* 1377 */     if (this.useDeepCopy)
/* 1378 */       if (this.useTileCodec) {
/* 1379 */         out.writeObject(encodeRasterToByteArray(this.source.getData()));
/*      */       } else {
/* 1381 */         out.writeObject(SerializerFactory.getState(this.source.getData(), null));
/*      */       }  
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1395 */     this.isServer = false;
/* 1396 */     this.source = null;
/* 1397 */     this.serverOpen = false;
/* 1398 */     this.serverSocket = null;
/* 1399 */     this.serverThread = null;
/* 1400 */     this.colorModel = null;
/* 1403 */     in.defaultReadObject();
/* 1405 */     if (this.isSourceRemote) {
/* 1407 */       String serverName = (String)in.readObject();
/* 1408 */       Long id = (Long)in.readObject();
/* 1411 */       this.source = (RenderedImage)new RemoteImage(serverName + "::" + id.longValue(), (RenderedImage)null);
/*      */     } 
/* 1416 */     SerializableState smState = (SerializableState)in.readObject();
/* 1417 */     this.sampleModel = (SampleModel)smState.getObject();
/* 1418 */     SerializableState cmState = (SerializableState)in.readObject();
/* 1419 */     this.colorModel = (ColorModel)cmState.getObject();
/* 1420 */     this.properties = (Hashtable)in.readObject();
/* 1423 */     if (this.useDeepCopy)
/* 1424 */       if (this.useTileCodec) {
/* 1425 */         this.imageRaster = decodeRasterFromByteArray((byte[])in.readObject());
/*      */       } else {
/* 1428 */         SerializableState rasState = (SerializableState)in.readObject();
/* 1430 */         this.imageRaster = (Raster)rasState.getObject();
/*      */       }  
/*      */   }
/*      */   
/*      */   private TileCodecDescriptor getTileCodecDescriptor(String registryMode, String formatName) {
/* 1436 */     if (this.registry == null)
/* 1437 */       return (TileCodecDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor(registryMode, formatName); 
/* 1440 */     return (TileCodecDescriptor)this.registry.getDescriptor(registryMode, formatName);
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 1444 */     ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 1445 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */   
/*      */   SerializableRenderedImage() {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\SerializableRenderedImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */