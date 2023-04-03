/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.rmi.RMIImage;
/*     */ import com.sun.media.jai.rmi.RMIImageImpl;
/*     */ import com.sun.media.jai.rmi.RasterProxy;
/*     */ import com.sun.media.jai.rmi.RenderContextProxy;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.net.InetAddress;
/*     */ import java.rmi.Naming;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.remote.SerializableRenderedImage;
/*     */ 
/*     */ public class RemoteImage extends PlanarImage {
/*     */   static final int DEFAULT_TIMEOUT = 1000;
/*     */   
/*     */   static final int DEFAULT_NUM_RETRIES = 5;
/*     */   
/*     */   static final int VAR_MIN_X = 0;
/*     */   
/*     */   static final int VAR_MIN_Y = 1;
/*     */   
/*     */   static final int VAR_WIDTH = 2;
/*     */   
/*     */   static final int VAR_HEIGHT = 3;
/*     */   
/*     */   static final int VAR_TILE_WIDTH = 4;
/*     */   
/*     */   static final int VAR_TILE_HEIGHT = 5;
/*     */   
/*     */   static final int VAR_TILE_GRID_X_OFFSET = 6;
/*     */   
/*     */   static final int VAR_TILE_GRID_Y_OFFSET = 7;
/*     */   
/*     */   static final int VAR_SAMPLE_MODEL = 8;
/*     */   
/*     */   static final int VAR_COLOR_MODEL = 9;
/*     */   
/*     */   static final int VAR_SOURCES = 10;
/*     */   
/*     */   static final int NUM_VARS = 11;
/*     */   
/*  90 */   private static final Class NULL_PROPERTY_CLASS = RMIImageImpl.NULL_PROPERTY.getClass();
/*     */   
/*     */   protected RMIImage remoteImage;
/*     */   
/*  97 */   private Long id = null;
/*     */   
/* 100 */   protected boolean[] fieldValid = new boolean[11];
/*     */   
/* 103 */   protected String[] propertyNames = null;
/*     */   
/* 106 */   protected int timeout = 1000;
/*     */   
/* 109 */   protected int numRetries = 5;
/*     */   
/* 112 */   private Rectangle imageBounds = null;
/*     */   
/*     */   private static Vector vectorize(RenderedImage image) {
/* 115 */     Vector v = new Vector(1);
/* 116 */     v.add(image);
/* 117 */     return v;
/*     */   }
/*     */   
/*     */   public RemoteImage(String serverName, RenderedImage source) {
/* 149 */     super(null, null, null);
/* 151 */     if (serverName == null)
/* 152 */       serverName = getLocalHostAddress(); 
/* 157 */     int index = serverName.indexOf("::");
/* 158 */     boolean remoteChainingHack = (index != -1);
/* 160 */     if (!remoteChainingHack && source == null)
/* 162 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage1")); 
/* 166 */     if (remoteChainingHack) {
/* 169 */       this.id = Long.valueOf(serverName.substring(index + 2));
/* 170 */       serverName = serverName.substring(0, index);
/*     */     } 
/* 174 */     getRMIImage(serverName);
/* 176 */     if (!remoteChainingHack)
/* 178 */       getRMIID(); 
/* 182 */     setRMIProperties(serverName);
/* 184 */     if (source != null)
/*     */       try {
/* 186 */         if (source instanceof java.io.Serializable) {
/* 187 */           this.remoteImage.setSource(this.id, source);
/*     */         } else {
/* 189 */           this.remoteImage.setSource(this.id, (RenderedImage)new SerializableRenderedImage(source));
/*     */         } 
/* 193 */       } catch (RemoteException e) {
/* 194 */         throw new RuntimeException(e.getMessage());
/*     */       }  
/*     */   }
/*     */   
/*     */   public RemoteImage(String serverName, RenderedOp source) {
/* 228 */     super(null, null, null);
/* 230 */     if (serverName == null)
/* 231 */       serverName = getLocalHostAddress(); 
/* 233 */     if (source == null)
/* 234 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage1")); 
/* 239 */     getRMIImage(serverName);
/* 242 */     getRMIID();
/* 245 */     setRMIProperties(serverName);
/*     */     try {
/* 248 */       this.remoteImage.setSource(this.id, source);
/* 249 */     } catch (RemoteException e) {
/* 250 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public RemoteImage(String serverName, RenderableOp source, RenderContext renderContext) {
/* 283 */     super(null, null, null);
/* 285 */     if (serverName == null)
/* 286 */       serverName = getLocalHostAddress(); 
/* 288 */     if (source == null)
/* 289 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage1")); 
/* 293 */     if (renderContext == null)
/* 294 */       renderContext = new RenderContext(new AffineTransform()); 
/* 298 */     getRMIImage(serverName);
/* 301 */     getRMIID();
/* 304 */     setRMIProperties(serverName);
/* 307 */     RenderContextProxy rcp = new RenderContextProxy(renderContext);
/*     */     try {
/* 310 */       this.remoteImage.setSource(this.id, source, rcp);
/* 311 */     } catch (RemoteException e) {
/* 312 */       e.printStackTrace();
/* 313 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getRMIImage(String serverName) {
/* 335 */     if (serverName == null)
/* 336 */       serverName = getLocalHostAddress(); 
/* 339 */     String serviceName = new String("rmi://" + serverName + "/" + "RemoteImageServer");
/* 343 */     this.remoteImage = null;
/*     */     try {
/* 345 */       this.remoteImage = (RMIImage)Naming.lookup(serviceName);
/* 346 */     } catch (Exception e) {
/* 347 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getLocalHostAddress() {
/*     */     String serverName;
/*     */     try {
/* 359 */       serverName = InetAddress.getLocalHost().getHostAddress();
/* 360 */     } catch (Exception e) {
/* 361 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/* 363 */     return serverName;
/*     */   }
/*     */   
/*     */   private void getRMIID() {
/*     */     try {
/* 371 */       this.id = this.remoteImage.getRemoteID();
/* 372 */     } catch (Exception e) {
/* 373 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRMIProperties(String serverName) {
/* 384 */     setProperty(getClass().getName() + ".serverName", serverName);
/* 385 */     setProperty(getClass().getName() + ".id", this.id);
/*     */   }
/*     */   
/*     */   protected void finalize() {
/*     */     try {
/* 393 */       this.remoteImage.dispose(this.id);
/* 394 */     } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public void setTimeout(int timeout) {
/* 406 */     if (timeout > 0)
/* 407 */       this.timeout = timeout; 
/*     */   }
/*     */   
/*     */   public int getTimeout() {
/* 415 */     return this.timeout;
/*     */   }
/*     */   
/*     */   public void setNumRetries(int numRetries) {
/* 425 */     if (numRetries > 0)
/* 426 */       this.numRetries = numRetries; 
/*     */   }
/*     */   
/*     */   public int getNumRetries() {
/* 434 */     return this.numRetries;
/*     */   }
/*     */   
/*     */   protected void requestField(int fieldIndex, int retries, int timeout) {
/* 451 */     if (retries < 0)
/* 452 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage3")); 
/* 453 */     if (timeout < 0)
/* 454 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage4")); 
/* 457 */     int count = 0;
/* 459 */     if (this.fieldValid[fieldIndex])
/*     */       return; 
/* 462 */     while (count++ < retries) {
/*     */       try {
/*     */         Vector localSources;
/*     */         int numSources, i;
/* 464 */         switch (fieldIndex) {
/*     */           case 0:
/* 466 */             this.minX = this.remoteImage.getMinX(this.id);
/*     */             break;
/*     */           case 1:
/* 469 */             this.minY = this.remoteImage.getMinY(this.id);
/*     */             break;
/*     */           case 2:
/* 472 */             this.width = this.remoteImage.getWidth(this.id);
/*     */             break;
/*     */           case 3:
/* 475 */             this.height = this.remoteImage.getHeight(this.id);
/*     */             break;
/*     */           case 4:
/* 478 */             this.tileWidth = this.remoteImage.getTileWidth(this.id);
/*     */             break;
/*     */           case 5:
/* 481 */             this.tileHeight = this.remoteImage.getTileHeight(this.id);
/*     */             break;
/*     */           case 6:
/* 484 */             this.tileGridXOffset = this.remoteImage.getTileGridXOffset(this.id);
/*     */             break;
/*     */           case 7:
/* 487 */             this.tileGridYOffset = this.remoteImage.getTileGridYOffset(this.id);
/*     */             break;
/*     */           case 8:
/* 490 */             this.sampleModel = this.remoteImage.getSampleModel(this.id).getSampleModel();
/*     */             break;
/*     */           case 9:
/* 494 */             this.colorModel = this.remoteImage.getColorModel(this.id).getColorModel();
/*     */             break;
/*     */           case 10:
/* 498 */             localSources = this.remoteImage.getSources(this.id);
/* 499 */             numSources = localSources.size();
/* 500 */             for (i = 0; i < numSources; i++) {
/* 501 */               RenderedImage src = localSources.get(i);
/* 503 */               addSource(PlanarImage.wrapRenderedImage(src));
/*     */             } 
/*     */             break;
/*     */         } 
/* 509 */         this.fieldValid[fieldIndex] = true;
/*     */         return;
/* 511 */       } catch (RemoteException e) {
/* 512 */         System.err.println(JaiI18N.getString("RemoteImage0"));
/*     */         try {
/* 514 */           Thread.sleep(timeout);
/* 515 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void requestField(int fieldIndex) {
/* 530 */     requestField(fieldIndex, this.numRetries, this.timeout);
/*     */   }
/*     */   
/*     */   public int getMinX() {
/* 537 */     requestField(0);
/* 538 */     return this.minX;
/*     */   }
/*     */   
/*     */   public int getMaxX() {
/* 546 */     requestField(0);
/* 547 */     requestField(2);
/* 548 */     return this.minX + this.width;
/*     */   }
/*     */   
/*     */   public int getMinY() {
/* 553 */     requestField(1);
/* 554 */     return this.minY;
/*     */   }
/*     */   
/*     */   public int getMaxY() {
/* 562 */     requestField(1);
/* 563 */     requestField(3);
/* 564 */     return this.minY + this.height;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 569 */     requestField(2);
/* 570 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 575 */     requestField(3);
/* 576 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 581 */     requestField(4);
/* 582 */     return this.tileWidth;
/*     */   }
/*     */   
/*     */   public int getTileHeight() {
/* 587 */     requestField(5);
/* 588 */     return this.tileHeight;
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 593 */     requestField(6);
/* 594 */     return this.tileGridXOffset;
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 599 */     requestField(7);
/* 600 */     return this.tileGridYOffset;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 605 */     requestField(8);
/* 606 */     return this.sampleModel;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 611 */     requestField(9);
/* 612 */     return this.colorModel;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/* 621 */     requestField(10);
/* 622 */     return super.getSources();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 639 */     Object property = super.getProperty(name);
/* 641 */     if (property == null || property == Image.UndefinedProperty) {
/* 643 */       int count = 0;
/* 644 */       while (count++ < this.numRetries) {
/*     */         try {
/* 646 */           property = this.remoteImage.getProperty(this.id, name);
/* 647 */           if (NULL_PROPERTY_CLASS.isInstance(property))
/* 648 */             property = Image.UndefinedProperty; 
/*     */           break;
/* 651 */         } catch (RemoteException e) {
/*     */           try {
/* 653 */             Thread.sleep(this.timeout);
/* 654 */           } catch (InterruptedException f) {}
/*     */         } 
/*     */       } 
/* 659 */       if (property == null)
/* 660 */         property = Image.UndefinedProperty; 
/* 663 */       if (property != Image.UndefinedProperty)
/* 664 */         setProperty(name, property); 
/*     */     } 
/* 668 */     return property;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 674 */     String[] localPropertyNames = super.getPropertyNames();
/* 677 */     Vector names = new Vector();
/* 678 */     if (localPropertyNames != null)
/* 679 */       for (int i = 0; i < localPropertyNames.length; i++)
/* 680 */         names.add(localPropertyNames[i]);  
/* 685 */     int count = 0;
/* 686 */     String[] remotePropertyNames = null;
/* 687 */     while (count++ < this.numRetries) {
/*     */       try {
/* 689 */         remotePropertyNames = this.remoteImage.getPropertyNames(this.id);
/*     */         break;
/* 691 */       } catch (RemoteException e) {
/*     */         try {
/* 693 */           Thread.sleep(this.timeout);
/* 694 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/* 700 */     if (remotePropertyNames != null)
/* 701 */       for (int i = 0; i < remotePropertyNames.length; i++) {
/* 702 */         if (!names.contains(remotePropertyNames[i]))
/* 703 */           names.add(remotePropertyNames[i]); 
/*     */       }  
/* 709 */     this.propertyNames = (names.size() == 0) ? null : names.<String>toArray(new String[names.size()]);
/* 712 */     return this.propertyNames;
/*     */   }
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 724 */     int count = 0;
/* 726 */     while (count++ < this.numRetries) {
/*     */       try {
/* 728 */         RasterProxy rp = this.remoteImage.getTile(this.id, x, y);
/* 729 */         return rp.getRaster();
/* 730 */       } catch (RemoteException e) {
/*     */         try {
/* 732 */           Thread.sleep(this.timeout);
/* 733 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/* 737 */     return null;
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 744 */     int count = 0;
/* 746 */     while (count++ < this.numRetries) {
/*     */       try {
/* 748 */         RasterProxy rp = this.remoteImage.getData(this.id);
/* 749 */         return rp.getRaster();
/* 750 */       } catch (RemoteException e) {
/*     */         try {
/* 752 */           Thread.sleep(this.timeout);
/* 753 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/* 757 */     return null;
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 775 */     if (this.imageBounds == null)
/* 776 */       this.imageBounds = getBounds(); 
/* 778 */     if (rect == null) {
/* 779 */       rect = this.imageBounds;
/* 780 */     } else if (!rect.intersects(this.imageBounds)) {
/* 781 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteImage2"));
/*     */     } 
/* 784 */     int count = 0;
/* 786 */     while (count++ < this.numRetries) {
/*     */       try {
/* 788 */         RasterProxy rp = this.remoteImage.getData(this.id, rect);
/* 789 */         return rp.getRaster();
/* 790 */       } catch (RemoteException e) {
/*     */         try {
/* 792 */           Thread.sleep(this.timeout);
/* 793 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/* 797 */     return null;
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster raster) {
/* 812 */     int count = 0;
/* 814 */     Rectangle bounds = (raster == null) ? new Rectangle(getMinX(), getMinY(), getWidth(), getHeight()) : raster.getBounds();
/* 819 */     while (count++ < this.numRetries) {
/*     */       try {
/* 821 */         RasterProxy rp = this.remoteImage.copyData(this.id, bounds);
/*     */         try {
/* 823 */           if (raster == null) {
/* 824 */             raster = (WritableRaster)rp.getRaster();
/*     */             break;
/*     */           } 
/* 826 */           raster.setDataElements(bounds.x, bounds.y, rp.getRaster());
/* 830 */         } catch (ArrayIndexOutOfBoundsException e) {
/* 831 */           raster = null;
/*     */         } 
/*     */         break;
/* 834 */       } catch (RemoteException e) {
/*     */         try {
/* 836 */           Thread.sleep(this.timeout);
/* 837 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/* 842 */     return raster;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RemoteImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */