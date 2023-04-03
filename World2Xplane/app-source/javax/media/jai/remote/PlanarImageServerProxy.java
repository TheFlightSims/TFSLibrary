/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.TileCache;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public abstract class PlanarImageServerProxy extends PlanarImage implements RemoteRenderedImage {
/*     */   protected int retryInterval;
/*     */   
/*     */   protected int numRetries;
/*     */   
/*     */   protected transient TileCache cache;
/*     */   
/*     */   protected Object tileCacheMetric;
/*     */   
/*     */   protected transient OperationRegistry registry;
/*     */   
/*     */   protected String serverName;
/*     */   
/*     */   protected String protocolName;
/*     */   
/*     */   protected String operationName;
/*     */   
/*     */   protected ParameterBlock paramBlock;
/*     */   
/*     */   protected RenderingHints hints;
/*     */   
/* 126 */   private ImageLayout layout = null;
/*     */   
/*     */   protected NegotiableCapabilitySet preferences;
/*     */   
/*     */   protected NegotiableCapabilitySet negotiated;
/*     */   
/*     */   NegotiableCapabilitySet serverCapabilities;
/*     */   
/*     */   NegotiableCapabilitySet clientCapabilities;
/*     */   
/*     */   private static void checkLayout(ImageLayout layout) {
/* 151 */     if (layout == null)
/* 152 */       throw new IllegalArgumentException("layout is null."); 
/* 155 */     if (layout.getValidMask() != 1023)
/* 156 */       throw new Error(JaiI18N.getString("PlanarImageServerProxy3")); 
/*     */   }
/*     */   
/*     */   public PlanarImageServerProxy(String serverName, String protocolName, String operationName, ParameterBlock paramBlock, RenderingHints hints) {
/* 192 */     super(null, null, null);
/* 194 */     if (operationName == null)
/* 195 */       throw new IllegalArgumentException(JaiI18N.getString("PlanarImageServerProxy1")); 
/* 199 */     this.serverName = serverName;
/* 200 */     this.protocolName = protocolName;
/* 201 */     this.operationName = operationName;
/* 202 */     this.paramBlock = paramBlock;
/* 203 */     this.hints = hints;
/* 205 */     if (hints == null) {
/* 207 */       this.registry = JAI.getDefaultInstance().getOperationRegistry();
/* 208 */       this.cache = JAI.getDefaultInstance().getTileCache();
/* 209 */       this.retryInterval = 1000;
/* 210 */       this.numRetries = 5;
/* 214 */       setNegotiationPreferences((NegotiableCapabilitySet)null);
/*     */     } else {
/* 217 */       this.registry = (OperationRegistry)hints.get(JAI.KEY_OPERATION_REGISTRY);
/* 219 */       if (this.registry == null)
/* 220 */         this.registry = JAI.getDefaultInstance().getOperationRegistry(); 
/* 223 */       this.cache = (TileCache)hints.get(JAI.KEY_TILE_CACHE);
/* 224 */       if (this.cache == null)
/* 225 */         this.cache = JAI.getDefaultInstance().getTileCache(); 
/* 228 */       Integer integer = (Integer)hints.get(JAI.KEY_RETRY_INTERVAL);
/* 229 */       if (integer == null) {
/* 230 */         this.retryInterval = 1000;
/*     */       } else {
/* 232 */         this.retryInterval = integer.intValue();
/*     */       } 
/* 235 */       integer = (Integer)hints.get(JAI.KEY_NUM_RETRIES);
/* 236 */       if (integer == null) {
/* 237 */         this.numRetries = 5;
/*     */       } else {
/* 239 */         this.numRetries = integer.intValue();
/*     */       } 
/* 242 */       this.tileCacheMetric = hints.get(JAI.KEY_TILE_CACHE_METRIC);
/* 245 */       setNegotiationPreferences((NegotiableCapabilitySet)hints.get(JAI.KEY_NEGOTIATION_PREFERENCES));
/*     */     } 
/* 249 */     if (paramBlock != null)
/* 250 */       setSources(paramBlock.getSources()); 
/*     */   }
/*     */   
/*     */   public String getServerName() {
/* 258 */     return this.serverName;
/*     */   }
/*     */   
/*     */   public String getProtocolName() {
/* 266 */     return this.protocolName;
/*     */   }
/*     */   
/*     */   public String getOperationName() {
/* 273 */     return this.operationName;
/*     */   }
/*     */   
/*     */   public ParameterBlock getParameterBlock() {
/* 282 */     return this.paramBlock;
/*     */   }
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 290 */     return this.hints;
/*     */   }
/*     */   
/*     */   public TileCache getTileCache() {
/* 299 */     return this.cache;
/*     */   }
/*     */   
/*     */   public void setTileCache(TileCache cache) {
/* 314 */     if (this.cache != null)
/* 315 */       this.cache.removeTiles(this); 
/* 317 */     this.cache = cache;
/*     */   }
/*     */   
/*     */   public Object getTileCacheMetric() {
/* 324 */     return this.tileCacheMetric;
/*     */   }
/*     */   
/*     */   public abstract ImageLayout getImageLayout() throws RemoteImagingException;
/*     */   
/*     */   public abstract Object getRemoteProperty(String paramString) throws RemoteImagingException;
/*     */   
/*     */   public abstract String[] getRemotePropertyNames() throws RemoteImagingException;
/*     */   
/*     */   public abstract Rectangle mapSourceRect(Rectangle paramRectangle, int paramInt) throws RemoteImagingException;
/*     */   
/*     */   public abstract Rectangle mapDestRect(Rectangle paramRectangle, int paramInt) throws RemoteImagingException;
/*     */   
/*     */   public abstract Raster computeTile(int paramInt1, int paramInt2) throws RemoteImagingException;
/*     */   
/*     */   public int getRetryInterval() {
/* 437 */     return this.retryInterval;
/*     */   }
/*     */   
/*     */   public void setRetryInterval(int retryInterval) {
/* 448 */     if (retryInterval < 0)
/* 449 */       throw new IllegalArgumentException(JaiI18N.getString("Generic3")); 
/* 451 */     this.retryInterval = retryInterval;
/*     */   }
/*     */   
/*     */   public int getNumRetries() {
/* 458 */     return this.numRetries;
/*     */   }
/*     */   
/*     */   public void setNumRetries(int numRetries) {
/* 469 */     if (numRetries < 0)
/* 470 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/* 472 */     this.numRetries = numRetries;
/*     */   }
/*     */   
/*     */   public int getMinX() {
/* 480 */     requestLayout();
/* 481 */     return this.minX;
/*     */   }
/*     */   
/*     */   public int getMaxX() {
/* 490 */     requestLayout();
/* 491 */     return this.minX + this.width;
/*     */   }
/*     */   
/*     */   public int getMinY() {
/* 499 */     requestLayout();
/* 500 */     return this.minY;
/*     */   }
/*     */   
/*     */   public int getMaxY() {
/* 509 */     requestLayout();
/* 510 */     return this.minY + this.height;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 518 */     requestLayout();
/* 519 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 527 */     requestLayout();
/* 528 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 536 */     requestLayout();
/* 537 */     return this.tileWidth;
/*     */   }
/*     */   
/*     */   public int getTileHeight() {
/* 545 */     requestLayout();
/* 546 */     return this.tileHeight;
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 554 */     requestLayout();
/* 555 */     return this.tileGridXOffset;
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 563 */     requestLayout();
/* 564 */     return this.tileGridYOffset;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 572 */     requestLayout();
/* 573 */     return this.sampleModel;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 581 */     requestLayout();
/* 582 */     return this.colorModel;
/*     */   }
/*     */   
/*     */   private ImageLayout requestLayout() {
/*     */     RemoteImagingException remoteImagingException;
/* 595 */     if (this.layout != null)
/* 596 */       return this.layout; 
/* 598 */     Exception rieSave = null;
/* 599 */     int count = 0;
/* 600 */     while (count++ < this.numRetries) {
/*     */       try {
/* 602 */         this.layout = getImageLayout();
/* 604 */         checkLayout(this.layout);
/* 607 */         this.minX = this.layout.getMinX(null);
/* 608 */         this.minY = this.layout.getMinY(null);
/* 609 */         this.width = this.layout.getWidth(null);
/* 610 */         this.height = this.layout.getHeight(null);
/* 611 */         this.tileWidth = this.layout.getTileWidth(null);
/* 612 */         this.tileHeight = this.layout.getTileHeight(null);
/* 613 */         this.tileGridXOffset = this.layout.getTileGridXOffset(null);
/* 614 */         this.tileGridYOffset = this.layout.getTileGridYOffset(null);
/* 615 */         this.sampleModel = this.layout.getSampleModel(null);
/* 616 */         this.colorModel = this.layout.getColorModel(null);
/*     */         break;
/* 618 */       } catch (RemoteImagingException e) {
/* 619 */         System.err.println(JaiI18N.getString("PlanarImageServerProxy0"));
/* 621 */         remoteImagingException = e;
/*     */         try {
/* 624 */           Thread.sleep(this.retryInterval);
/* 625 */         } catch (InterruptedException f) {}
/*     */       } 
/*     */     } 
/* 630 */     if (this.layout == null)
/* 631 */       sendExceptionToListener((Exception)remoteImagingException); 
/* 634 */     return this.layout;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 657 */     Object property = super.getProperty(name);
/* 659 */     if (property == null || property == Image.UndefinedProperty) {
/*     */       RemoteImagingException remoteImagingException;
/* 661 */       Exception rieSave = null;
/* 662 */       int count = 0;
/* 663 */       while (count++ < this.numRetries) {
/*     */         try {
/* 665 */           property = getRemoteProperty(name);
/* 666 */           if (property != Image.UndefinedProperty)
/* 667 */             setProperty(name, property); 
/* 669 */           return property;
/* 670 */         } catch (RemoteImagingException rie) {
/* 671 */           System.err.println(JaiI18N.getString("PlanarImageServerProxy0"));
/* 673 */           remoteImagingException = rie;
/*     */           try {
/* 675 */             Thread.sleep(this.retryInterval);
/* 676 */           } catch (InterruptedException ie) {}
/*     */         } 
/*     */       } 
/* 681 */       sendExceptionToListener((Exception)remoteImagingException);
/* 682 */       return property;
/*     */     } 
/* 684 */     return property;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*     */     RemoteImagingException remoteImagingException;
/* 707 */     String[] localPropertyNames = super.getPropertyNames();
/* 709 */     Vector names = new Vector();
/* 711 */     if (localPropertyNames != null)
/* 712 */       for (int i = 0; i < localPropertyNames.length; i++)
/* 713 */         names.add(localPropertyNames[i]);  
/* 717 */     int count = 0;
/* 718 */     String[] remotePropertyNames = null;
/* 719 */     Exception rieSave = null;
/* 721 */     while (count++ < this.numRetries) {
/*     */       try {
/* 723 */         remotePropertyNames = getRemotePropertyNames();
/*     */         break;
/* 725 */       } catch (RemoteImagingException rie) {
/* 726 */         System.err.println(JaiI18N.getString("PlanarImageServerProxy0"));
/* 728 */         remoteImagingException = rie;
/*     */         try {
/* 730 */           Thread.sleep(this.retryInterval);
/* 731 */         } catch (InterruptedException ie) {}
/*     */       } 
/*     */     } 
/* 736 */     if (count > this.numRetries)
/* 737 */       sendExceptionToListener((Exception)remoteImagingException); 
/* 741 */     if (remotePropertyNames != null)
/* 742 */       for (int i = 0; i < remotePropertyNames.length; i++) {
/* 743 */         if (!names.contains(remotePropertyNames[i]))
/* 744 */           names.add(remotePropertyNames[i]); 
/*     */       }  
/* 750 */     String[] propertyNames = (names.size() == 0) ? null : names.<String>toArray(new String[names.size()]);
/* 753 */     return propertyNames;
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 771 */     Raster tile = null;
/* 774 */     if (tileX >= getMinTileX() && tileX <= getMaxTileX() && tileY >= getMinTileY() && tileY <= getMaxTileY()) {
/* 778 */       tile = (this.cache != null) ? this.cache.getTile(this, tileX, tileY) : null;
/* 780 */       if (tile == null) {
/*     */         RemoteImagingException remoteImagingException;
/* 782 */         int count = 0;
/* 783 */         Exception rieSave = null;
/* 784 */         while (count++ < this.numRetries) {
/*     */           try {
/* 786 */             tile = computeTile(tileX, tileY);
/*     */             break;
/* 788 */           } catch (RemoteImagingException rie) {
/* 789 */             System.err.println(JaiI18N.getString("PlanarImageServerProxy0"));
/* 791 */             remoteImagingException = rie;
/*     */             try {
/* 793 */               Thread.sleep(this.retryInterval);
/* 794 */             } catch (InterruptedException ie) {}
/*     */           } 
/*     */         } 
/* 800 */         if (count > this.numRetries)
/* 801 */           sendExceptionToListener((Exception)remoteImagingException); 
/* 805 */         if (this.cache != null)
/* 806 */           this.cache.add(this, tileX, tileY, tile, this.tileCacheMetric); 
/*     */       } 
/*     */     } 
/* 811 */     return tile;
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 818 */     if (this.cache != null)
/* 819 */       this.cache.removeTiles(this); 
/* 821 */     super.finalize();
/*     */   }
/*     */   
/*     */   public NegotiableCapabilitySet getNegotiationPreferences() {
/* 833 */     return this.preferences;
/*     */   }
/*     */   
/*     */   public void setNegotiationPreferences(NegotiableCapabilitySet preferences) {
/* 860 */     this.preferences = preferences;
/* 864 */     this.negotiated = null;
/* 866 */     getNegotiatedValues();
/*     */   }
/*     */   
/*     */   public synchronized NegotiableCapabilitySet getNegotiatedValues() throws RemoteImagingException {
/* 879 */     if (this.negotiated == null) {
/* 883 */       getCapabilities();
/* 886 */       this.negotiated = RemoteJAI.negotiate(this.preferences, this.serverCapabilities, this.clientCapabilities);
/* 891 */       setServerNegotiatedValues(this.negotiated);
/*     */     } 
/* 894 */     return this.negotiated;
/*     */   }
/*     */   
/*     */   public NegotiableCapability getNegotiatedValue(String category) throws RemoteImagingException {
/* 913 */     if (this.negotiated == null) {
/* 915 */       getCapabilities();
/* 918 */       return RemoteJAI.negotiate(this.preferences, this.serverCapabilities, this.clientCapabilities, category);
/*     */     } 
/* 926 */     return this.negotiated.getNegotiatedValue(category);
/*     */   }
/*     */   
/*     */   private void getCapabilities() {
/* 933 */     String mode = "remoteRendered";
/* 934 */     if (this.serverCapabilities == null) {
/*     */       RemoteImagingException remoteImagingException;
/* 936 */       RemoteDescriptor desc = (RemoteDescriptor)this.registry.getDescriptor(mode, this.protocolName);
/* 939 */       int count = 0;
/* 940 */       Exception rieSave = null;
/* 941 */       while (count++ < this.numRetries) {
/*     */         try {
/* 943 */           this.serverCapabilities = desc.getServerCapabilities(this.serverName);
/*     */           break;
/* 946 */         } catch (RemoteImagingException rie) {
/* 947 */           System.err.println(JaiI18N.getString("PlanarImageServerProxy0"));
/* 949 */           remoteImagingException = rie;
/*     */           try {
/* 951 */             Thread.sleep(this.retryInterval);
/* 952 */           } catch (InterruptedException ie) {}
/*     */         } 
/*     */       } 
/* 957 */       if (count > this.numRetries)
/* 958 */         sendExceptionToListener((Exception)remoteImagingException); 
/*     */     } 
/* 962 */     if (this.clientCapabilities == null) {
/* 963 */       RemoteRIF rrif = (RemoteRIF)this.registry.getFactory(mode, this.protocolName);
/* 966 */       this.clientCapabilities = rrif.getClientCapabilities();
/*     */     } 
/*     */   }
/*     */   
/*     */   void sendExceptionToListener(Exception e) {
/* 971 */     ImagingListener listener = null;
/* 972 */     if (this.hints != null) {
/* 973 */       listener = (ImagingListener)this.hints.get(JAI.KEY_IMAGING_LISTENER);
/*     */     } else {
/* 975 */       listener = JAI.getDefaultInstance().getImagingListener();
/*     */     } 
/* 976 */     String message = JaiI18N.getString("PlanarImageServerProxy2");
/* 977 */     listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), this, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\PlanarImageServerProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */