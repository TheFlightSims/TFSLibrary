/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.WritableRenderedImage;
/*      */ import java.lang.reflect.Method;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public abstract class PointOpImage extends OpImage {
/*      */   private boolean isDisposed = false;
/*      */   
/*      */   private boolean areFieldsInitialized = false;
/*      */   
/*      */   private boolean checkInPlaceOperation = false;
/*      */   
/*      */   private boolean isInPlaceEnabled = false;
/*      */   
/*      */   private WritableRenderedImage source0AsWritableRenderedImage;
/*      */   
/*      */   private OpImage source0AsOpImage;
/*      */   
/*      */   private boolean source0IsWritableRenderedImage;
/*      */   
/*      */   private boolean sameBounds;
/*      */   
/*      */   private boolean sameTileGrid;
/*      */   
/*      */   private static ImageLayout layoutHelper(ImageLayout layout, Vector sources, Map config) {
/*   84 */     int numSources = sources.size();
/*   86 */     if (numSources < 1)
/*   87 */       throw new IllegalArgumentException(JaiI18N.getString("Generic5")); 
/*   90 */     RenderedImage source0 = sources.get(0);
/*   91 */     Rectangle isect = new Rectangle(source0.getMinX(), source0.getMinY(), source0.getWidth(), source0.getHeight());
/*   95 */     Rectangle rect = new Rectangle();
/*   96 */     for (int i = 1; i < numSources; i++) {
/*   97 */       RenderedImage s = sources.get(i);
/*   98 */       rect.setBounds(s.getMinX(), s.getMinY(), s.getWidth(), s.getHeight());
/*  100 */       isect = isect.intersection(rect);
/*      */     } 
/*  103 */     if (isect.isEmpty())
/*  104 */       throw new IllegalArgumentException(JaiI18N.getString("PointOpImage0")); 
/*  108 */     if (layout == null) {
/*  109 */       layout = new ImageLayout(isect.x, isect.y, isect.width, isect.height);
/*      */     } else {
/*  112 */       layout = (ImageLayout)layout.clone();
/*  113 */       if (!layout.isValid(1))
/*  114 */         layout.setMinX(isect.x); 
/*  116 */       if (!layout.isValid(2))
/*  117 */         layout.setMinY(isect.y); 
/*  119 */       if (!layout.isValid(4))
/*  120 */         layout.setWidth(isect.width); 
/*  122 */       if (!layout.isValid(8))
/*  123 */         layout.setHeight(isect.height); 
/*  126 */       Rectangle r = new Rectangle(layout.getMinX(null), layout.getMinY(null), layout.getWidth(null), layout.getHeight(null));
/*  130 */       if (r.isEmpty())
/*  131 */         throw new IllegalArgumentException(JaiI18N.getString("PointOpImage1")); 
/*  135 */       if (!isect.contains(r))
/*  136 */         throw new IllegalArgumentException(JaiI18N.getString("PointOpImage2")); 
/*      */     } 
/*  144 */     if (numSources > 1 && !layout.isValid(256)) {
/*  147 */       SampleModel sm = source0.getSampleModel();
/*  148 */       ColorModel cm = source0.getColorModel();
/*  149 */       int dtype0 = getAppropriateDataType(sm);
/*  150 */       int bands0 = getBandCount(sm, cm);
/*  151 */       int dtype = dtype0;
/*  152 */       int bands = bands0;
/*  154 */       for (int j = 1; j < numSources; j++) {
/*  155 */         RenderedImage source = sources.get(j);
/*  156 */         sm = source.getSampleModel();
/*  157 */         cm = source.getColorModel();
/*  158 */         int sourceBands = getBandCount(sm, cm);
/*  160 */         dtype = mergeTypes(dtype, getPixelType(sm));
/*  161 */         bands = Math.min(bands, sourceBands);
/*      */       } 
/*  165 */       if (dtype == -1 && bands > 1)
/*  166 */         dtype = 0; 
/*  171 */       SampleModel sm0 = source0.getSampleModel();
/*  172 */       if (dtype != sm0.getDataType() || bands != sm0.getNumBands()) {
/*      */         SampleModel sampleModel;
/*  173 */         int tw = layout.getTileWidth(source0);
/*  174 */         int th = layout.getTileHeight(source0);
/*  176 */         if (dtype == -1) {
/*  177 */           sampleModel = new MultiPixelPackedSampleModel(0, tw, th, 1);
/*      */         } else {
/*  181 */           sampleModel = RasterFactory.createPixelInterleavedSampleModel(dtype, tw, th, bands);
/*      */         } 
/*  187 */         layout.setSampleModel(sampleModel);
/*  190 */         if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sampleModel, cm)) {
/*  192 */           cm = ImageUtil.getCompatibleColorModel(sampleModel, config);
/*  193 */           layout.setColorModel(cm);
/*      */         } 
/*      */       } 
/*      */     } 
/*  198 */     return layout;
/*      */   }
/*      */   
/*      */   private static int getPixelType(SampleModel sampleModel) {
/*  205 */     return ImageUtil.isBinary(sampleModel) ? -1 : sampleModel.getDataType();
/*      */   }
/*      */   
/*      */   private static int getBandCount(SampleModel sampleModel, ColorModel colorModel) {
/*  214 */     if (ImageUtil.isBinary(sampleModel))
/*  215 */       return 1; 
/*  216 */     if (colorModel instanceof java.awt.image.IndexColorModel)
/*  217 */       return colorModel.getNumComponents(); 
/*  219 */     return sampleModel.getNumBands();
/*      */   }
/*      */   
/*      */   private static int getAppropriateDataType(SampleModel sampleModel) {
/*  227 */     int dataType = sampleModel.getDataType();
/*  228 */     int retVal = dataType;
/*  230 */     if (ImageUtil.isBinary(sampleModel)) {
/*  231 */       retVal = -1;
/*  232 */     } else if (dataType == 1 || dataType == 3) {
/*  234 */       boolean canUseBytes = true;
/*  235 */       boolean canUseShorts = true;
/*  237 */       int[] ss = sampleModel.getSampleSize();
/*  238 */       for (int i = 0; i < ss.length; i++) {
/*  239 */         if (ss[i] > 16) {
/*  240 */           canUseBytes = false;
/*  241 */           canUseShorts = false;
/*      */           break;
/*      */         } 
/*  244 */         if (ss[i] > 8)
/*  245 */           canUseBytes = false; 
/*      */       } 
/*  249 */       if (canUseBytes) {
/*  250 */         retVal = 0;
/*  251 */       } else if (canUseShorts) {
/*  252 */         retVal = 1;
/*      */       } 
/*      */     } 
/*  256 */     return retVal;
/*      */   }
/*      */   
/*      */   private static int mergeTypes(int type0, int type1) {
/*  269 */     if (type0 == type1)
/*  270 */       return type0; 
/*  274 */     int type = type1;
/*  278 */     switch (type0) {
/*      */       case 2:
/*  284 */         if (type1 == 0) {
/*  285 */           type = 2;
/*      */           break;
/*      */         } 
/*  286 */         if (type1 == 1)
/*  287 */           type = 3; 
/*      */         break;
/*      */       case 1:
/*  291 */         if (type1 == 0) {
/*  292 */           type = 1;
/*      */           break;
/*      */         } 
/*  293 */         if (type1 == 2)
/*  294 */           type = 3; 
/*      */         break;
/*      */       case 3:
/*  298 */         if (type1 == 0 || type1 == 2 || type1 == 1)
/*  301 */           type = 3; 
/*      */         break;
/*      */       case 4:
/*  305 */         if (type1 != 5)
/*  306 */           type = 4; 
/*      */         break;
/*      */       case 5:
/*  310 */         type = 5;
/*      */         break;
/*      */     } 
/*  314 */     return type;
/*      */   }
/*      */   
/*      */   public PointOpImage(Vector sources, ImageLayout layout, Map configuration, boolean cobbleSources) {
/*  369 */     super(checkSourceVector(sources, true), layoutHelper(layout, sources, configuration), configuration, cobbleSources);
/*      */   }
/*      */   
/*      */   public PointOpImage(RenderedImage source, ImageLayout layout, Map configuration, boolean cobbleSources) {
/*  399 */     this(vectorize(source), layout, configuration, cobbleSources);
/*      */   }
/*      */   
/*      */   public PointOpImage(RenderedImage source0, RenderedImage source1, ImageLayout layout, Map configuration, boolean cobbleSources) {
/*  429 */     this(vectorize(source0, source1), layout, configuration, cobbleSources);
/*      */   }
/*      */   
/*      */   public PointOpImage(RenderedImage source0, RenderedImage source1, RenderedImage source2, ImageLayout layout, Map configuration, boolean cobbleSources) {
/*  462 */     this(vectorize(source0, source1, source2), layout, configuration, cobbleSources);
/*      */   }
/*      */   
/*      */   private synchronized void initializeFields() {
/*  472 */     if (this.areFieldsInitialized)
/*      */       return; 
/*  475 */     PlanarImage source0 = getSource(0);
/*  477 */     if (this.checkInPlaceOperation) {
/*  494 */       Vector source0Sinks = source0.getSinks();
/*  495 */       this.isInPlaceEnabled = (source0 != null && getTileGridXOffset() == source0.getTileGridXOffset() && getTileGridYOffset() == source0.getTileGridYOffset() && getBounds().equals(source0.getBounds()) && source0 instanceof OpImage && hasCompatibleSampleModel(source0) && (source0Sinks == null || source0Sinks.size() <= 1));
/*  509 */       if (this.isInPlaceEnabled && !((OpImage)source0).computesUniqueTiles())
/*  511 */         this.isInPlaceEnabled = false; 
/*  516 */       if (this.isInPlaceEnabled)
/*      */         try {
/*  518 */           Method getTileMethod = source0.getClass().getMethod("getTile", new Class[] { int.class, int.class });
/*  522 */           Class opImageClass = Class.forName("javax.media.jai.OpImage");
/*  524 */           Class declaringClass = getTileMethod.getDeclaringClass();
/*  527 */           if (!declaringClass.equals(opImageClass))
/*  528 */             this.isInPlaceEnabled = false; 
/*  530 */         } catch (ClassNotFoundException e) {
/*  531 */           this.isInPlaceEnabled = false;
/*  532 */         } catch (NoSuchMethodException e) {
/*  533 */           this.isInPlaceEnabled = false;
/*      */         }  
/*  538 */       if (this.isInPlaceEnabled) {
/*  540 */         this.source0IsWritableRenderedImage = source0 instanceof WritableRenderedImage;
/*  544 */         if (this.source0IsWritableRenderedImage) {
/*  545 */           this.source0AsWritableRenderedImage = (WritableRenderedImage)source0;
/*      */         } else {
/*  548 */           this.source0AsOpImage = (OpImage)source0;
/*      */         } 
/*      */       } 
/*  553 */       this.checkInPlaceOperation = false;
/*      */     } 
/*  557 */     int numSources = getNumSources();
/*  560 */     this.sameBounds = true;
/*  561 */     this.sameTileGrid = true;
/*  564 */     for (int i = 0; i < numSources && (this.sameBounds || this.sameTileGrid); i++) {
/*  565 */       PlanarImage source = getSource(i);
/*  568 */       if (this.sameBounds)
/*  569 */         this.sameBounds = (this.sameBounds && this.minX == source.minX && this.minY == source.minY && this.width == source.width && this.height == source.height); 
/*  575 */       if (this.sameTileGrid)
/*  576 */         this.sameTileGrid = (this.sameTileGrid && this.tileGridXOffset == source.tileGridXOffset && this.tileGridYOffset == source.tileGridYOffset && this.tileWidth == source.tileWidth && this.tileHeight == source.tileHeight); 
/*      */     } 
/*  585 */     this.areFieldsInitialized = true;
/*      */   }
/*      */   
/*      */   private boolean hasCompatibleSampleModel(PlanarImage src) {
/*  598 */     SampleModel srcSM = src.getSampleModel();
/*  599 */     int numBands = this.sampleModel.getNumBands();
/*  601 */     boolean isCompatible = (srcSM.getTransferType() == this.sampleModel.getTransferType() && srcSM.getWidth() == this.sampleModel.getWidth() && srcSM.getHeight() == this.sampleModel.getHeight() && srcSM.getNumBands() == numBands && srcSM.getClass().equals(this.sampleModel.getClass()));
/*  608 */     if (isCompatible)
/*  609 */       if (this.sampleModel instanceof ComponentSampleModel) {
/*  610 */         ComponentSampleModel smSrc = (ComponentSampleModel)srcSM;
/*  611 */         ComponentSampleModel smDst = (ComponentSampleModel)this.sampleModel;
/*  612 */         isCompatible = (isCompatible && smSrc.getPixelStride() == smDst.getPixelStride() && smSrc.getScanlineStride() == smDst.getScanlineStride());
/*  615 */         int[] biSrc = smSrc.getBankIndices();
/*  616 */         int[] biDst = smDst.getBankIndices();
/*  617 */         int[] boSrc = smSrc.getBandOffsets();
/*  618 */         int[] boDst = smDst.getBandOffsets();
/*  619 */         for (int b = 0; b < numBands && isCompatible; b++)
/*  620 */           isCompatible = (isCompatible && biSrc[b] == biDst[b] && boSrc[b] == boDst[b]); 
/*  624 */       } else if (this.sampleModel instanceof SinglePixelPackedSampleModel) {
/*  626 */         SinglePixelPackedSampleModel smSrc = (SinglePixelPackedSampleModel)srcSM;
/*  628 */         SinglePixelPackedSampleModel smDst = (SinglePixelPackedSampleModel)this.sampleModel;
/*  630 */         isCompatible = (isCompatible && smSrc.getScanlineStride() == smDst.getScanlineStride());
/*  632 */         int[] bmSrc = smSrc.getBitMasks();
/*  633 */         int[] bmDst = smDst.getBitMasks();
/*  634 */         for (int b = 0; b < numBands && isCompatible; b++)
/*  635 */           isCompatible = (isCompatible && bmSrc[b] == bmDst[b]); 
/*  638 */       } else if (this.sampleModel instanceof MultiPixelPackedSampleModel) {
/*  639 */         MultiPixelPackedSampleModel smSrc = (MultiPixelPackedSampleModel)srcSM;
/*  641 */         MultiPixelPackedSampleModel smDst = (MultiPixelPackedSampleModel)this.sampleModel;
/*  643 */         isCompatible = (isCompatible && smSrc.getPixelBitStride() == smDst.getPixelBitStride() && smSrc.getScanlineStride() == smDst.getScanlineStride() && smSrc.getDataBitOffset() == smDst.getDataBitOffset());
/*      */       } else {
/*  648 */         isCompatible = false;
/*      */       }  
/*  652 */     return isCompatible;
/*      */   }
/*      */   
/*      */   protected void permitInPlaceOperation() {
/*  669 */     Object inPlaceProperty = null;
/*      */     try {
/*  671 */       inPlaceProperty = AccessController.doPrivileged(new PrivilegedAction(this) {
/*      */             private final PointOpImage this$0;
/*      */             
/*      */             public Object run() {
/*  674 */               String name = "javax.media.jai.PointOpImage.InPlace";
/*  675 */               return System.getProperty(name);
/*      */             }
/*      */           });
/*  678 */     } catch (SecurityException se) {}
/*  683 */     this.checkInPlaceOperation = (inPlaceProperty == null || !(inPlaceProperty instanceof String) || !((String)inPlaceProperty).equalsIgnoreCase("false"));
/*      */   }
/*      */   
/*      */   protected boolean isColormapOperation() {
/*  707 */     return false;
/*      */   }
/*      */   
/*      */   public Raster computeTile(int tileX, int tileY) {
/*  727 */     if (!this.cobbleSources)
/*  728 */       return super.computeTile(tileX, tileY); 
/*  732 */     initializeFields();
/*  735 */     WritableRaster dest = null;
/*  736 */     if (this.isInPlaceEnabled)
/*  737 */       if (this.source0IsWritableRenderedImage) {
/*  739 */         dest = this.source0AsWritableRenderedImage.getWritableTile(tileX, tileY);
/*      */       } else {
/*  744 */         Raster raster = this.source0AsOpImage.getTileFromCache(tileX, tileY);
/*  747 */         if (raster == null)
/*      */           try {
/*  750 */             raster = this.source0AsOpImage.computeTile(tileX, tileY);
/*  751 */             if (raster instanceof WritableRaster)
/*  752 */               dest = (WritableRaster)raster; 
/*  754 */           } catch (Exception e) {} 
/*      */       }  
/*  766 */     boolean recyclingSource0Tile = (dest != null);
/*  768 */     if (!recyclingSource0Tile) {
/*  770 */       Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/*  771 */       dest = createWritableRaster(this.sampleModel, org);
/*      */     } 
/*  776 */     if (isColormapOperation()) {
/*  777 */       if (!recyclingSource0Tile) {
/*  778 */         PlanarImage src = getSource(0);
/*  779 */         Raster srcTile = null;
/*  780 */         Rectangle srcRect = null;
/*  781 */         Rectangle dstRect = dest.getBounds();
/*  785 */         if (this.sameTileGrid) {
/*  788 */           srcTile = getSource(0).getTile(tileX, tileY);
/*  790 */         } else if (dstRect.intersects(src.getBounds())) {
/*  794 */           srcTile = src.getData(dstRect);
/*      */         } else {
/*  799 */           return dest;
/*      */         } 
/*  802 */         srcRect = srcTile.getBounds();
/*  806 */         if (!dstRect.contains(srcRect)) {
/*  807 */           srcRect = dstRect.intersection(srcRect);
/*  808 */           srcTile = srcTile.createChild(srcTile.getMinX(), srcTile.getMinY(), srcRect.width, srcRect.height, srcRect.x, srcRect.y, null);
/*      */         } 
/*  818 */         JDKWorkarounds.setRect(dest, srcTile, 0, 0);
/*      */       } 
/*  820 */       return dest;
/*      */     } 
/*  824 */     int destMinX = dest.getMinX();
/*  825 */     int destMinY = dest.getMinY();
/*  826 */     int destMaxX = destMinX + dest.getWidth();
/*  827 */     int destMaxY = destMinY + dest.getHeight();
/*  830 */     Rectangle bounds = getBounds();
/*  831 */     if (destMinX < bounds.x)
/*  832 */       destMinX = bounds.x; 
/*  834 */     int boundsMaxX = bounds.x + bounds.width;
/*  835 */     if (destMaxX > boundsMaxX)
/*  836 */       destMaxX = boundsMaxX; 
/*  838 */     if (destMinY < bounds.y)
/*  839 */       destMinY = bounds.y; 
/*  841 */     int boundsMaxY = bounds.y + bounds.height;
/*  842 */     if (destMaxY > boundsMaxY)
/*  843 */       destMaxY = boundsMaxY; 
/*  847 */     int numSrcs = getNumSources();
/*  851 */     if (recyclingSource0Tile && numSrcs == 1) {
/*  853 */       Raster[] sources = { dest };
/*  854 */       Rectangle destRect = new Rectangle(destMinX, destMinY, destMaxX - destMinX, destMaxY - destMinY);
/*  857 */       computeRect(sources, dest, destRect);
/*  858 */     } else if (recyclingSource0Tile && this.sameBounds && this.sameTileGrid) {
/*  860 */       Raster[] sources = new Raster[numSrcs];
/*  861 */       sources[0] = dest;
/*  862 */       for (int i = 1; i < numSrcs; i++)
/*  863 */         sources[i] = getSource(i).getTile(tileX, tileY); 
/*  865 */       Rectangle destRect = new Rectangle(destMinX, destMinY, destMaxX - destMinX, destMaxY - destMinY);
/*  868 */       computeRect(sources, dest, destRect);
/*      */     } else {
/*  871 */       if (!this.sameBounds)
/*  873 */         for (int i = recyclingSource0Tile ? 1 : 0; i < numSrcs; i++) {
/*  874 */           bounds = getSource(i).getBounds();
/*  875 */           if (destMinX < bounds.x)
/*  876 */             destMinX = bounds.x; 
/*  878 */           boundsMaxX = bounds.x + bounds.width;
/*  879 */           if (destMaxX > boundsMaxX)
/*  880 */             destMaxX = boundsMaxX; 
/*  882 */           if (destMinY < bounds.y)
/*  883 */             destMinY = bounds.y; 
/*  885 */           boundsMaxY = bounds.y + bounds.height;
/*  886 */           if (destMaxY > boundsMaxY)
/*  887 */             destMaxY = boundsMaxY; 
/*  890 */           if (destMinX >= destMaxX || destMinY >= destMaxY)
/*  891 */             return dest; 
/*      */         }  
/*  897 */       Rectangle destRect = new Rectangle(destMinX, destMinY, destMaxX - destMinX, destMaxY - destMinY);
/*  902 */       Raster[] sources = new Raster[numSrcs];
/*  904 */       if (this.sameTileGrid) {
/*  907 */         if (recyclingSource0Tile)
/*  908 */           sources[0] = dest; 
/*  910 */         for (int i = recyclingSource0Tile ? 1 : 0; i < numSrcs; i++)
/*  911 */           sources[i] = getSource(i).getTile(tileX, tileY); 
/*  914 */         computeRect(sources, dest, destRect);
/*      */       } else {
/*  920 */         IntegerSequence xSplits = new IntegerSequence(destMinX, destMaxX);
/*  922 */         xSplits.insert(destMinX);
/*  923 */         xSplits.insert(destMaxX);
/*  925 */         IntegerSequence ySplits = new IntegerSequence(destMinY, destMaxY);
/*  927 */         ySplits.insert(destMinY);
/*  928 */         ySplits.insert(destMaxY);
/*  930 */         for (int i = recyclingSource0Tile ? 1 : 0; i < numSrcs; i++) {
/*  931 */           PlanarImage s = getSource(i);
/*  932 */           s.getSplits(xSplits, ySplits, destRect);
/*      */         } 
/*  940 */         Rectangle subRect = new Rectangle();
/*  942 */         ySplits.startEnumeration();
/*      */         int y1;
/*  943 */         for (y1 = ySplits.nextElement(); ySplits.hasMoreElements(); y1 = y2) {
/*  944 */           int y2 = ySplits.nextElement();
/*  945 */           int h = y2 - y1;
/*  947 */           xSplits.startEnumeration();
/*  948 */           int x1 = xSplits.nextElement();
/*  949 */           for (; xSplits.hasMoreElements(); x1 = x2) {
/*  950 */             int x2 = xSplits.nextElement();
/*  951 */             int w = x2 - x1;
/*  954 */             if (recyclingSource0Tile)
/*  955 */               sources[0] = dest; 
/*  957 */             int j = recyclingSource0Tile ? 1 : 0;
/*  958 */             for (; j < numSrcs; j++) {
/*  959 */               PlanarImage s = getSource(j);
/*  960 */               int tx = s.XToTileX(x1);
/*  961 */               int ty = s.YToTileY(y1);
/*  962 */               sources[j] = s.getTile(tx, ty);
/*      */             } 
/*  965 */             subRect.x = x1;
/*  966 */             subRect.y = y1;
/*  967 */             subRect.width = w;
/*  968 */             subRect.height = h;
/*  969 */             computeRect(sources, dest, subRect);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  975 */     if (recyclingSource0Tile && this.source0IsWritableRenderedImage)
/*  976 */       this.source0AsWritableRenderedImage.releaseWritableTile(tileX, tileY); 
/*  979 */     return dest;
/*      */   }
/*      */   
/*      */   public final Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/*  999 */     if (sourceRect == null)
/* 1000 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1003 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 1004 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 1006 */     return new Rectangle(sourceRect);
/*      */   }
/*      */   
/*      */   public final Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 1027 */     if (destRect == null)
/* 1028 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1031 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 1032 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 1034 */     return new Rectangle(destRect);
/*      */   }
/*      */   
/*      */   public synchronized void dispose() {
/* 1048 */     if (this.isDisposed)
/*      */       return; 
/* 1052 */     this.isDisposed = true;
/* 1054 */     if (this.cache != null && this.isInPlaceEnabled && this.tileRecycler != null)
/* 1055 */       this.cache.removeTiles(this); 
/* 1058 */     super.dispose();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PointOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */