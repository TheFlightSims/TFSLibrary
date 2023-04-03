/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.rmi.Naming;
/*     */ import java.rmi.RMISecurityManager;
/*     */ import java.rmi.RemoteException;
/*     */ import java.rmi.server.UnicastRemoteObject;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.PropertySource;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.remote.RemoteImagingException;
/*     */ import javax.media.jai.remote.SerializableRenderedImage;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class RMIImageImpl implements RMIImage {
/*  88 */   public static final Object NULL_PROPERTY = new NullPropertyTag();
/*     */   
/*  91 */   private static long idCounter = 0L;
/*     */   
/*  97 */   private static Hashtable sources = null;
/*     */   
/* 103 */   private static Hashtable propertySources = null;
/*     */   
/*     */   private static synchronized void addSource(Long id, RenderedImage source, PropertySource ps) {
/* 116 */     if (sources == null) {
/* 117 */       sources = new Hashtable();
/* 118 */       propertySources = new Hashtable();
/*     */     } 
/* 122 */     sources.put(id, source);
/* 123 */     propertySources.put(id, ps);
/*     */   }
/*     */   
/*     */   private static PlanarImage getSource(Long id) throws RemoteException {
/* 133 */     Object obj = null;
/* 134 */     if (sources == null || (obj = sources.get(id)) == null)
/* 136 */       throw new RemoteException(JaiI18N.getString("RMIImageImpl2")); 
/* 139 */     return (PlanarImage)obj;
/*     */   }
/*     */   
/*     */   private static PropertySource getPropertySource(Long id) throws RemoteException {
/* 150 */     Object obj = null;
/* 151 */     if (propertySources == null || (obj = propertySources.get(id)) == null)
/* 153 */       throw new RemoteException(JaiI18N.getString("RMIImageImpl2")); 
/* 156 */     return (PropertySource)obj;
/*     */   }
/*     */   
/*     */   public RMIImageImpl() throws RemoteException {
/*     */     try {
/* 166 */       UnicastRemoteObject.exportObject(this);
/* 167 */     } catch (RemoteException e) {
/* 168 */       ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 170 */       String message = JaiI18N.getString("RMIImageImpl0");
/* 171 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Long getRemoteID() throws RemoteException {
/* 189 */     return new Long(++idCounter);
/*     */   }
/*     */   
/*     */   public void setSource(Long id, RenderedImage source) throws RemoteException {
/* 207 */     PlanarImage pi = PlanarImage.wrapRenderedImage(source);
/* 208 */     addSource(id, (RenderedImage)pi, (PropertySource)pi);
/*     */   }
/*     */   
/*     */   public void setSource(Long id, RenderedOp source) throws RemoteException {
/* 223 */     addSource(id, (RenderedImage)source.getRendering(), (PropertySource)source);
/*     */   }
/*     */   
/*     */   public void setSource(Long id, RenderableOp source, RenderContextProxy renderContextProxy) throws RemoteException {
/* 235 */     RenderContext renderContext = renderContextProxy.getRenderContext();
/* 237 */     RenderedImage r = source.createRendering(renderContext);
/* 238 */     PlanarImage pi = PlanarImage.wrapRenderedImage(r);
/* 239 */     addSource(id, (RenderedImage)pi, (PropertySource)pi);
/*     */   }
/*     */   
/*     */   public void dispose(Long id) throws RemoteException {
/* 247 */     if (sources != null) {
/* 248 */       sources.remove(id);
/* 249 */       propertySources.remove(id);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getProperty(Long id, String name) throws RemoteException {
/* 256 */     PropertySource ps = getPropertySource(id);
/* 257 */     Object property = ps.getProperty(name);
/* 258 */     if (property == null || property.equals(Image.UndefinedProperty))
/* 260 */       property = NULL_PROPERTY; 
/* 262 */     return property;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(Long id) throws RemoteException {
/* 271 */     PropertySource ps = getPropertySource(id);
/* 272 */     return ps.getPropertyNames();
/*     */   }
/*     */   
/*     */   public int getMinX(Long id) throws RemoteException {
/* 277 */     return getSource(id).getMinX();
/*     */   }
/*     */   
/*     */   public int getMaxX(Long id) throws RemoteException {
/* 282 */     return getSource(id).getMaxX();
/*     */   }
/*     */   
/*     */   public int getMinY(Long id) throws RemoteException {
/* 287 */     return getSource(id).getMinY();
/*     */   }
/*     */   
/*     */   public int getMaxY(Long id) throws RemoteException {
/* 292 */     return getSource(id).getMaxY();
/*     */   }
/*     */   
/*     */   public int getWidth(Long id) throws RemoteException {
/* 297 */     return getSource(id).getWidth();
/*     */   }
/*     */   
/*     */   public int getHeight(Long id) throws RemoteException {
/* 302 */     return getSource(id).getHeight();
/*     */   }
/*     */   
/*     */   public int getTileWidth(Long id) throws RemoteException {
/* 307 */     return getSource(id).getTileWidth();
/*     */   }
/*     */   
/*     */   public int getTileHeight(Long id) throws RemoteException {
/* 312 */     return getSource(id).getTileHeight();
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset(Long id) throws RemoteException {
/* 319 */     return getSource(id).getTileGridXOffset();
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset(Long id) throws RemoteException {
/* 326 */     return getSource(id).getTileGridYOffset();
/*     */   }
/*     */   
/*     */   public int getMinTileX(Long id) throws RemoteException {
/* 331 */     return getSource(id).getMinTileX();
/*     */   }
/*     */   
/*     */   public int getNumXTiles(Long id) throws RemoteException {
/* 339 */     return getSource(id).getNumXTiles();
/*     */   }
/*     */   
/*     */   public int getMinTileY(Long id) throws RemoteException {
/* 344 */     return getSource(id).getMinTileY();
/*     */   }
/*     */   
/*     */   public int getNumYTiles(Long id) throws RemoteException {
/* 352 */     return getSource(id).getNumYTiles();
/*     */   }
/*     */   
/*     */   public int getMaxTileX(Long id) throws RemoteException {
/* 357 */     return getSource(id).getMaxTileX();
/*     */   }
/*     */   
/*     */   public int getMaxTileY(Long id) throws RemoteException {
/* 362 */     return getSource(id).getMaxTileY();
/*     */   }
/*     */   
/*     */   public SampleModelProxy getSampleModel(Long id) throws RemoteException {
/* 367 */     return new SampleModelProxy(getSource(id).getSampleModel());
/*     */   }
/*     */   
/*     */   public ColorModelProxy getColorModel(Long id) throws RemoteException {
/* 372 */     return new ColorModelProxy(getSource(id).getColorModel());
/*     */   }
/*     */   
/*     */   public Vector getSources(Long id) throws RemoteException {
/* 382 */     Vector sourceVector = getSource(id).getSources();
/* 383 */     int size = sourceVector.size();
/* 384 */     boolean isCloned = false;
/* 385 */     for (int i = 0; i < size; i++) {
/* 386 */       RenderedImage img = sourceVector.get(i);
/* 387 */       if (!(img instanceof java.io.Serializable)) {
/* 388 */         if (!isCloned)
/* 389 */           sourceVector = (Vector)sourceVector.clone(); 
/* 391 */         sourceVector.set(i, new SerializableRenderedImage(img, false));
/*     */       } 
/*     */     } 
/* 394 */     return sourceVector;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds(Long id) throws RemoteException {
/* 399 */     return getSource(id).getBounds();
/*     */   }
/*     */   
/*     */   public RasterProxy getTile(Long id, int tileX, int tileY) throws RemoteException {
/* 414 */     return new RasterProxy(getSource(id).getTile(tileX, tileY));
/*     */   }
/*     */   
/*     */   public RasterProxy getData(Long id) throws RemoteException {
/* 423 */     return new RasterProxy(getSource(id).getData());
/*     */   }
/*     */   
/*     */   public RasterProxy getData(Long id, Rectangle bounds) throws RemoteException {
/* 437 */     RasterProxy rp = null;
/* 438 */     if (bounds == null) {
/* 439 */       rp = getData(id);
/*     */     } else {
/* 441 */       bounds = bounds.intersection(getBounds(id));
/* 442 */       rp = new RasterProxy(getSource(id).getData(bounds));
/*     */     } 
/* 444 */     return rp;
/*     */   }
/*     */   
/*     */   public RasterProxy copyData(Long id, Rectangle bounds) throws RemoteException {
/* 453 */     return getData(id, bounds);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 476 */     if (System.getSecurityManager() == null)
/* 477 */       System.setSecurityManager(new RMISecurityManager()); 
/* 481 */     String host = null;
/* 482 */     int port = 1099;
/* 483 */     for (int i = 0; i < args.length; i++) {
/* 484 */       if (args[i].equalsIgnoreCase("-host")) {
/* 485 */         host = args[++i];
/* 486 */       } else if (args[i].equalsIgnoreCase("-port")) {
/* 487 */         port = Integer.parseInt(args[++i]);
/*     */       } 
/*     */     } 
/* 492 */     if (host == null)
/*     */       try {
/* 494 */         host = InetAddress.getLocalHost().getHostAddress();
/* 495 */       } catch (UnknownHostException e) {
/* 496 */         System.err.println(JaiI18N.getString("RMIImageImpl1") + e.getMessage());
/* 498 */         e.printStackTrace();
/*     */       }  
/* 502 */     System.out.println(JaiI18N.getString("RMIImageImpl3") + " " + host + ":" + port);
/*     */     try {
/* 506 */       RMIImageImpl im = new RMIImageImpl();
/* 507 */       String serverName = new String("rmi://" + host + ":" + port + "/" + "RemoteImageServer");
/* 511 */       System.out.println(JaiI18N.getString("RMIImageImpl4") + " \"" + serverName + "\".");
/* 513 */       Naming.rebind(serverName, im);
/* 514 */       System.out.println(JaiI18N.getString("RMIImageImpl5"));
/* 515 */     } catch (Exception e) {
/* 516 */       System.err.println(JaiI18N.getString("RMIImageImpl0") + e.getMessage());
/* 518 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RMIImageImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */