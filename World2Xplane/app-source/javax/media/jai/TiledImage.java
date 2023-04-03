/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.image.BandedSampleModel;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.TileObserver;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.WritableRenderedImage;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public class TiledImage extends PlanarImage implements WritableRenderedImage, PropertyChangeListener {
/*      */   protected int tilesX;
/*      */   
/*      */   protected int tilesY;
/*      */   
/*      */   protected int minTileX;
/*      */   
/*      */   protected int minTileY;
/*      */   
/*      */   protected WritableRaster[][] tiles;
/*      */   
/*      */   protected int[][] writers;
/*      */   
/*  120 */   protected Vector tileObservers = null;
/*      */   
/*      */   private boolean areBuffersShared = false;
/*      */   
/*  126 */   private TiledImage parent = null;
/*      */   
/*  129 */   private SampleModel ancestorSampleModel = null;
/*      */   
/*  132 */   private int[] bandList = null;
/*      */   
/*  135 */   private int[] numWritableTiles = null;
/*      */   
/*  138 */   private ROI srcROI = null;
/*      */   
/*  142 */   private Rectangle overlapBounds = null;
/*      */   
/*      */   private static SampleModel coerceSampleModel(SampleModel sampleModel, int sampleModelWidth, int sampleModelHeight) {
/*  154 */     return (sampleModel.getWidth() == sampleModelWidth && sampleModel.getHeight() == sampleModelHeight) ? sampleModel : sampleModel.createCompatibleSampleModel(sampleModelWidth, sampleModelHeight);
/*      */   }
/*      */   
/*      */   private void initTileGrid(TiledImage parent) {
/*  170 */     if (parent != null) {
/*  171 */       this.minTileX = parent.minTileX;
/*  172 */       this.minTileY = parent.minTileY;
/*      */     } else {
/*  174 */       this.minTileX = getMinTileX();
/*  175 */       this.minTileY = getMinTileY();
/*      */     } 
/*  178 */     int maxTileX = getMaxTileX();
/*  179 */     int maxTileY = getMaxTileY();
/*  181 */     this.tilesX = maxTileX - this.minTileX + 1;
/*  182 */     this.tilesY = maxTileY - this.minTileY + 1;
/*      */   }
/*      */   
/*      */   public TiledImage(int minX, int minY, int width, int height, int tileGridXOffset, int tileGridYOffset, SampleModel tileSampleModel, ColorModel colorModel) {
/*  212 */     this((TiledImage)null, minX, minY, width, height, tileGridXOffset, tileGridYOffset, tileSampleModel, colorModel);
/*      */   }
/*      */   
/*      */   private TiledImage(TiledImage parent, int minX, int minY, int width, int height, int tileGridXOffset, int tileGridYOffset, SampleModel sampleModel, ColorModel colorModel) {
/*  227 */     super(new ImageLayout(minX, minY, width, height, tileGridXOffset, tileGridYOffset, sampleModel.getWidth(), sampleModel.getHeight(), sampleModel, colorModel), null, null);
/*  231 */     initTileGrid(parent);
/*  233 */     if (parent == null) {
/*  234 */       this.tiles = new WritableRaster[this.tilesX][this.tilesY];
/*  235 */       this.writers = new int[this.tilesX][this.tilesY];
/*  236 */       this.tileObservers = new Vector();
/*  237 */       this.numWritableTiles = new int[1];
/*  238 */       this.numWritableTiles[0] = 0;
/*  239 */       this.ancestorSampleModel = sampleModel;
/*      */     } else {
/*  241 */       this.parent = parent;
/*  242 */       this.tiles = parent.tiles;
/*  243 */       this.writers = parent.writers;
/*  244 */       this.tileObservers = parent.tileObservers;
/*  245 */       this.numWritableTiles = parent.numWritableTiles;
/*  246 */       this.ancestorSampleModel = parent.ancestorSampleModel;
/*      */     } 
/*  249 */     this.tileFactory = (TileFactory)JAI.getDefaultInstance().getRenderingHint(JAI.KEY_TILE_FACTORY);
/*      */   }
/*      */   
/*      */   public TiledImage(Point origin, SampleModel sampleModel, int tileWidth, int tileHeight) {
/*  277 */     this(origin.x, origin.y, sampleModel.getWidth(), sampleModel.getHeight(), origin.x, origin.y, coerceSampleModel(sampleModel, tileWidth, tileHeight), PlanarImage.createColorModel(sampleModel));
/*      */   }
/*      */   
/*      */   public TiledImage(SampleModel sampleModel, int tileWidth, int tileHeight) {
/*  302 */     this(0, 0, sampleModel.getWidth(), sampleModel.getHeight(), 0, 0, coerceSampleModel(sampleModel, tileWidth, tileHeight), PlanarImage.createColorModel(sampleModel));
/*      */   }
/*      */   
/*      */   public TiledImage(RenderedImage source, int tileWidth, int tileHeight) {
/*  323 */     this(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight(), source.getTileGridXOffset(), source.getTileGridYOffset(), coerceSampleModel(source.getSampleModel(), tileWidth, tileHeight), source.getColorModel());
/*  330 */     set(source);
/*      */   }
/*      */   
/*      */   public TiledImage(RenderedImage source, boolean areBuffersShared) {
/*  353 */     this(source, source.getTileWidth(), source.getTileHeight());
/*  354 */     this.areBuffersShared = areBuffersShared;
/*      */   }
/*      */   
/*      */   public static TiledImage createInterleaved(int minX, int minY, int width, int height, int numBands, int dataType, int tileWidth, int tileHeight, int[] bandOffsets) {
/*  388 */     SampleModel sm = RasterFactory.createPixelInterleavedSampleModel(dataType, tileWidth, tileHeight, numBands, numBands * tileWidth, bandOffsets);
/*  394 */     return new TiledImage(minX, minY, width, height, minX, minY, sm, PlanarImage.createColorModel(sm));
/*      */   }
/*      */   
/*      */   public static TiledImage createBanded(int minX, int minY, int width, int height, int dataType, int tileWidth, int tileHeight, int[] bankIndices, int[] bandOffsets) {
/*  433 */     SampleModel sm = new BandedSampleModel(dataType, tileWidth, tileHeight, tileWidth, bankIndices, bandOffsets);
/*  439 */     return new TiledImage(minX, minY, width, height, minX, minY, sm, PlanarImage.createColorModel(sm));
/*      */   }
/*      */   
/*      */   private void overlayPixels(WritableRaster tile, RenderedImage im, Rectangle rect) {
/*  455 */     WritableRaster child = tile.createWritableChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, this.bandList);
/*  461 */     im.copyData(child);
/*      */   }
/*      */   
/*      */   private void overlayPixels(WritableRaster tile, RenderedImage im, Area a) {
/*  475 */     ROIShape rs = new ROIShape(a);
/*  476 */     Rectangle bounds = rs.getBounds();
/*  477 */     LinkedList rectList = rs.getAsRectangleList(bounds.x, bounds.y, bounds.width, bounds.height);
/*  480 */     int numRects = rectList.size();
/*  481 */     for (int i = 0; i < numRects; i++) {
/*  482 */       Rectangle rect = rectList.get(i);
/*  483 */       WritableRaster child = tile.createWritableChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, this.bandList);
/*  488 */       im.copyData(child);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void overlayPixels(WritableRaster tile, RenderedImage im, Rectangle rect, int[][] bitmask) {
/*  500 */     Raster r = im.getData(rect);
/*  504 */     if (this.bandList != null)
/*  505 */       tile = tile.createWritableChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, this.bandList); 
/*  512 */     Object data = r.getDataElements(rect.x, rect.y, null);
/*  517 */     int leftover = rect.width % 32;
/*  518 */     int bitWidth = (rect.width + 31) / 32 - ((leftover > 0) ? 1 : 0);
/*  519 */     int y = rect.y;
/*  521 */     for (int j = 0; j < rect.height; j++, y++) {
/*  522 */       int[] rowMask = bitmask[j];
/*  523 */       int x = rect.x;
/*      */       int i;
/*  525 */       for (i = 0; i < bitWidth; i++) {
/*  526 */         int mask32 = rowMask[i];
/*  527 */         int bit = Integer.MIN_VALUE;
/*  529 */         for (int b = 0; b < 32; b++, x++) {
/*  530 */           if ((mask32 & bit) != 0) {
/*  531 */             r.getDataElements(x, y, data);
/*  532 */             tile.setDataElements(x, y, data);
/*      */           } 
/*  534 */           bit >>>= 1;
/*      */         } 
/*      */       } 
/*  538 */       if (leftover > 0) {
/*  539 */         int mask32 = rowMask[i];
/*  540 */         int bit = Integer.MIN_VALUE;
/*  542 */         for (int b = 0; b < leftover; b++, x++) {
/*  543 */           if ((mask32 & bit) != 0) {
/*  544 */             r.getDataElements(x, y, data);
/*  545 */             tile.setDataElements(x, y, data);
/*      */           } 
/*  547 */           bit >>>= 1;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void set(RenderedImage im) {
/*  572 */     if (im == null)
/*  573 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  577 */     if (getNumSources() > 0 && im == getSourceImage(0))
/*      */       return; 
/*  581 */     Rectangle imRect = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight());
/*  585 */     if ((imRect = imRect.intersection(getBounds())).isEmpty())
/*      */       return; 
/*  590 */     this.areBuffersShared = false;
/*  593 */     int txMin = XToTileX(imRect.x);
/*  594 */     int tyMin = YToTileY(imRect.y);
/*  595 */     int txMax = XToTileX(imRect.x + imRect.width - 1);
/*  596 */     int tyMax = YToTileY(imRect.y + imRect.height - 1);
/*  599 */     for (int j = tyMin; j <= tyMax; j++) {
/*  600 */       for (int i = txMin; i <= txMax; i++) {
/*      */         WritableRaster t;
/*  602 */         if ((t = this.tiles[i - this.minTileX][j - this.minTileY]) != null && !isTileLocked(i, j)) {
/*  604 */           Rectangle tileRect = getTileRect(i, j);
/*  605 */           tileRect = tileRect.intersection(imRect);
/*  606 */           if (!tileRect.isEmpty())
/*  607 */             overlayPixels(t, im, tileRect); 
/*      */         } 
/*      */       } 
/*      */     } 
/*  614 */     PlanarImage src = PlanarImage.wrapRenderedImage(im);
/*  615 */     if (getNumSources() == 0) {
/*  616 */       addSource(src);
/*      */     } else {
/*  618 */       setSource(src, 0);
/*      */     } 
/*  620 */     this.srcROI = null;
/*  621 */     this.overlapBounds = imRect;
/*  624 */     this.properties.addProperties(src);
/*      */   }
/*      */   
/*      */   public void set(RenderedImage im, ROI roi) {
/*  649 */     if (im == null)
/*  650 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  654 */     if (getNumSources() > 0 && im == getSourceImage(0))
/*      */       return; 
/*  658 */     Rectangle imRect = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight());
/*  663 */     Rectangle overlap = imRect.intersection(roi.getBounds());
/*  664 */     if (overlap.isEmpty() || (overlap = overlap.intersection(getBounds())).isEmpty())
/*      */       return; 
/*  670 */     this.areBuffersShared = false;
/*  673 */     int txMin = XToTileX(overlap.x);
/*  674 */     int tyMin = YToTileY(overlap.y);
/*  675 */     int txMax = XToTileX(overlap.x + overlap.width - 1);
/*  676 */     int tyMax = YToTileY(overlap.y + overlap.height - 1);
/*  678 */     Shape roiShape = roi.getAsShape();
/*  679 */     Area roiArea = null;
/*  680 */     if (roiShape != null)
/*  681 */       roiArea = new Area(roiShape); 
/*  685 */     for (int j = tyMin; j <= tyMax; j++) {
/*  686 */       for (int i = txMin; i <= txMax; i++) {
/*      */         WritableRaster t;
/*  688 */         if ((t = this.tiles[i - this.minTileX][j - this.minTileY]) != null && !isTileLocked(i, j)) {
/*  690 */           Rectangle rect = getTileRect(i, j).intersection(overlap);
/*  691 */           if (!rect.isEmpty())
/*  692 */             if (roiShape != null) {
/*  693 */               Area a = new Area(rect);
/*  694 */               a.intersect(roiArea);
/*  696 */               if (!a.isEmpty())
/*  697 */                 overlayPixels(t, im, a); 
/*      */             } else {
/*  700 */               int[][] bitmask = roi.getAsBitmask(rect.x, rect.y, rect.width, rect.height, (int[][])null);
/*  706 */               if (bitmask != null && bitmask.length > 0)
/*  707 */                 overlayPixels(t, im, rect, bitmask); 
/*      */             }  
/*      */         } 
/*      */       } 
/*      */     } 
/*  716 */     PlanarImage src = PlanarImage.wrapRenderedImage(im);
/*  717 */     if (getNumSources() == 0) {
/*  718 */       addSource(src);
/*      */     } else {
/*  720 */       setSource(src, 0);
/*      */     } 
/*  722 */     this.srcROI = roi;
/*  723 */     this.overlapBounds = overlap;
/*  726 */     this.properties.addProperties(src);
/*      */   }
/*      */   
/*      */   public Graphics getGraphics() {
/*  738 */     return createGraphics();
/*      */   }
/*      */   
/*      */   public Graphics2D createGraphics() {
/*  748 */     int dataType = this.sampleModel.getDataType();
/*  749 */     if (dataType != 0 && dataType != 2 && dataType != 1 && dataType != 3)
/*  753 */       throw new UnsupportedOperationException(JaiI18N.getString("TiledImage0")); 
/*  755 */     return new TiledImageGraphics(this);
/*      */   }
/*      */   
/*      */   public TiledImage getSubImage(int x, int y, int w, int h, int[] bandSelect, ColorModel cm) {
/*  793 */     Rectangle subImageBounds = new Rectangle(x, y, w, h);
/*  794 */     if (subImageBounds.isEmpty())
/*  795 */       return null; 
/*  797 */     Rectangle overlap = subImageBounds.intersection(getBounds());
/*  798 */     if (overlap.isEmpty())
/*  799 */       return null; 
/*  803 */     SampleModel sm = (bandSelect != null) ? getSampleModel().createSubsetSampleModel(bandSelect) : getSampleModel();
/*  808 */     if (cm == null && (bandSelect == null || bandSelect.length == getSampleModel().getNumBands()))
/*  811 */       cm = getColorModel(); 
/*  815 */     TiledImage subImage = new TiledImage(this, overlap.x, overlap.y, overlap.width, overlap.height, getTileGridXOffset(), getTileGridYOffset(), sm, cm);
/*  825 */     int[] subBandList = null;
/*  826 */     if (bandSelect != null) {
/*  827 */       if (this.bandList != null) {
/*  830 */         subBandList = new int[bandSelect.length];
/*  831 */         for (int band = 0; band < bandSelect.length; band++)
/*  832 */           subBandList[band] = this.bandList[bandSelect[band]]; 
/*      */       } else {
/*  836 */         subBandList = bandSelect;
/*      */       } 
/*      */     } else {
/*  840 */       subBandList = this.bandList;
/*      */     } 
/*  844 */     subImage.bandList = subBandList;
/*  846 */     return subImage;
/*      */   }
/*      */   
/*      */   public TiledImage getSubImage(int x, int y, int w, int h, int[] bandSelect) {
/*  878 */     SampleModel sm = (bandSelect != null) ? getSampleModel().createSubsetSampleModel(bandSelect) : getSampleModel();
/*  881 */     return getSubImage(x, y, w, h, bandSelect, createColorModel(sm));
/*      */   }
/*      */   
/*      */   public TiledImage getSubImage(int x, int y, int w, int h) {
/*  899 */     return getSubImage(x, y, w, h, (int[])null, (ColorModel)null);
/*      */   }
/*      */   
/*      */   public TiledImage getSubImage(int[] bandSelect, ColorModel cm) {
/*  921 */     if (bandSelect == null)
/*  922 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  924 */     return getSubImage(getMinX(), getMinY(), getWidth(), getHeight(), bandSelect, cm);
/*      */   }
/*      */   
/*      */   public TiledImage getSubImage(int[] bandSelect) {
/*  943 */     if (bandSelect == null)
/*  944 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  946 */     return getSubImage(getMinX(), getMinY(), getWidth(), getHeight(), bandSelect);
/*      */   }
/*      */   
/*      */   private void createTile(int tileX, int tileY) {
/*  958 */     PlanarImage src = (getNumSources() > 0) ? getSourceImage(0) : null;
/*  962 */     if (src == null && this.parent != null) {
/*  963 */       this.parent.createTile(tileX, tileY);
/*      */       return;
/*      */     } 
/*  967 */     synchronized (this.tiles) {
/*  969 */       if (this.tiles[tileX - this.minTileX][tileY - this.minTileY] == null) {
/*  971 */         if (this.areBuffersShared) {
/*  972 */           Raster srcTile = src.getTile(tileX, tileY);
/*  973 */           if (srcTile instanceof WritableRaster) {
/*  974 */             this.tiles[tileX - this.minTileX][tileY - this.minTileY] = (WritableRaster)srcTile;
/*      */           } else {
/*  977 */             Point location = new Point(srcTile.getMinX(), srcTile.getMinY());
/*  979 */             this.tiles[tileX - this.minTileX][tileY - this.minTileY] = Raster.createWritableRaster(this.sampleModel, srcTile.getDataBuffer(), location);
/*      */           } 
/*      */           return;
/*      */         } 
/*  988 */         this.tiles[tileX - this.minTileX][tileY - this.minTileY] = createWritableRaster(this.ancestorSampleModel, new Point(tileXToX(tileX), tileYToY(tileY)));
/*  992 */         WritableRaster tile = this.tiles[tileX - this.minTileX][tileY - this.minTileY];
/*  995 */         if (src != null) {
/*  997 */           Rectangle tileRect = getTileRect(tileX, tileY);
/* 1000 */           Rectangle rect = this.overlapBounds.intersection(tileRect);
/* 1003 */           if (rect.isEmpty())
/*      */             return; 
/* 1008 */           if (this.srcROI != null) {
/* 1010 */             Shape roiShape = this.srcROI.getAsShape();
/* 1012 */             if (roiShape != null) {
/* 1014 */               Area a = new Area(rect);
/* 1015 */               a.intersect(new Area(roiShape));
/* 1017 */               if (!a.isEmpty())
/* 1019 */                 overlayPixels(tile, src, a); 
/*      */             } else {
/* 1022 */               int[][] bitmask = this.srcROI.getAsBitmask(rect.x, rect.y, rect.width, rect.height, (int[][])null);
/* 1027 */               overlayPixels(tile, src, rect, bitmask);
/*      */             } 
/* 1033 */           } else if (!rect.isEmpty()) {
/* 1034 */             if (this.bandList == null && rect.equals(tileRect)) {
/* 1038 */               if (tileRect.equals(tile.getBounds())) {
/* 1039 */                 src.copyData(tile);
/*      */               } else {
/* 1041 */                 src.copyData(tile.createWritableChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, (int[])null));
/*      */               } 
/*      */             } else {
/* 1049 */               overlayPixels(tile, src, rect);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1067 */     if (tileX < this.minTileX || tileY < this.minTileY || tileX > getMaxTileX() || tileY > getMaxTileY())
/* 1069 */       return null; 
/* 1072 */     createTile(tileX, tileY);
/* 1075 */     if (this.bandList == null)
/* 1076 */       return this.tiles[tileX - this.minTileX][tileY - this.minTileY]; 
/* 1080 */     Raster r = this.tiles[tileX - this.minTileX][tileY - this.minTileY];
/* 1082 */     return r.createChild(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight(), r.getMinX(), r.getMinY(), this.bandList);
/*      */   }
/*      */   
/*      */   public WritableRaster getWritableTile(int tileX, int tileY) {
/* 1099 */     if (tileX < this.minTileX || tileY < this.minTileY || tileX > getMaxTileX() || tileY > getMaxTileY())
/* 1101 */       return null; 
/* 1104 */     if (isTileLocked(tileX, tileY))
/* 1105 */       return null; 
/* 1108 */     createTile(tileX, tileY);
/* 1109 */     this.writers[tileX - this.minTileX][tileY - this.minTileY] = this.writers[tileX - this.minTileX][tileY - this.minTileY] + 1;
/* 1111 */     if (this.writers[tileX - this.minTileX][tileY - this.minTileY] == 1) {
/* 1112 */       this.numWritableTiles[0] = this.numWritableTiles[0] + 1;
/* 1114 */       Enumeration e = this.tileObservers.elements();
/* 1115 */       while (e.hasMoreElements()) {
/* 1116 */         TileObserver t = e.nextElement();
/* 1117 */         t.tileUpdate(this, tileX, tileY, true);
/*      */       } 
/*      */     } 
/* 1122 */     if (this.bandList == null)
/* 1123 */       return this.tiles[tileX - this.minTileX][tileY - this.minTileY]; 
/* 1127 */     WritableRaster wr = this.tiles[tileX - this.minTileX][tileY - this.minTileY];
/* 1129 */     return wr.createWritableChild(wr.getMinX(), wr.getMinY(), wr.getWidth(), wr.getHeight(), wr.getMinX(), wr.getMinY(), this.bandList);
/*      */   }
/*      */   
/*      */   public void releaseWritableTile(int tileX, int tileY) {
/* 1144 */     if (isTileLocked(tileX, tileY))
/*      */       return; 
/* 1148 */     this.writers[tileX - this.minTileX][tileY - this.minTileY] = this.writers[tileX - this.minTileX][tileY - this.minTileY] - 1;
/* 1150 */     if (this.writers[tileX - this.minTileX][tileY - this.minTileY] < 0)
/* 1151 */       throw new RuntimeException(JaiI18N.getString("TiledImage1")); 
/* 1154 */     if (this.writers[tileX - this.minTileX][tileY - this.minTileY] == 0) {
/* 1155 */       this.numWritableTiles[0] = this.numWritableTiles[0] - 1;
/* 1157 */       Enumeration e = this.tileObservers.elements();
/* 1158 */       while (e.hasMoreElements()) {
/* 1159 */         TileObserver t = e.nextElement();
/* 1160 */         t.tileUpdate(this, tileX, tileY, false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean lockTile(int tileX, int tileY) {
/* 1176 */     if (tileX < this.minTileX || tileY < this.minTileY || tileX > getMaxTileX() || tileY > getMaxTileY())
/* 1178 */       return false; 
/* 1182 */     if (isTileWritable(tileX, tileY))
/* 1183 */       return false; 
/* 1187 */     createTile(tileX, tileY);
/* 1190 */     this.writers[tileX - this.minTileX][tileY - this.minTileY] = -1;
/* 1192 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean isTileLocked(int tileX, int tileY) {
/* 1203 */     return (this.writers[tileX - this.minTileX][tileY - this.minTileY] < 0);
/*      */   }
/*      */   
/*      */   public void setData(Raster r) {
/* 1218 */     Rectangle rBounds = r.getBounds();
/* 1219 */     if ((rBounds = rBounds.intersection(getBounds())).isEmpty())
/*      */       return; 
/* 1224 */     int txMin = XToTileX(rBounds.x);
/* 1225 */     int tyMin = YToTileY(rBounds.y);
/* 1226 */     int txMax = XToTileX(rBounds.x + rBounds.width - 1);
/* 1227 */     int tyMax = YToTileY(rBounds.y + rBounds.height - 1);
/* 1229 */     for (int ty = tyMin; ty <= tyMax; ty++) {
/* 1230 */       for (int tx = txMin; tx <= txMax; tx++) {
/* 1231 */         WritableRaster wr = getWritableTile(tx, ty);
/* 1232 */         if (wr != null) {
/* 1238 */           Rectangle tileRect = getTileRect(tx, ty);
/* 1239 */           if (tileRect.contains(rBounds)) {
/* 1240 */             JDKWorkarounds.setRect(wr, r, 0, 0);
/*      */           } else {
/* 1242 */             Rectangle xsect = rBounds.intersection(tileRect);
/* 1243 */             Raster rChild = r.createChild(xsect.x, xsect.y, xsect.width, xsect.height, xsect.x, xsect.y, null);
/* 1247 */             WritableRaster wChild = wr.createWritableChild(xsect.x, xsect.y, xsect.width, xsect.height, xsect.x, xsect.y, (int[])null);
/* 1251 */             JDKWorkarounds.setRect(wChild, rChild, 0, 0);
/*      */           } 
/* 1253 */           releaseWritableTile(tx, ty);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setData(Raster r, ROI roi) {
/* 1273 */     Rectangle rBounds = r.getBounds();
/* 1274 */     if ((rBounds = rBounds.intersection(getBounds())).isEmpty() || (rBounds = rBounds.intersection(roi.getBounds())).isEmpty())
/*      */       return; 
/* 1280 */     LinkedList rectList = roi.getAsRectangleList(rBounds.x, rBounds.y, rBounds.width, rBounds.height);
/* 1285 */     int txMin = XToTileX(rBounds.x);
/* 1286 */     int tyMin = YToTileY(rBounds.y);
/* 1287 */     int txMax = XToTileX(rBounds.x + rBounds.width - 1);
/* 1288 */     int tyMax = YToTileY(rBounds.y + rBounds.height - 1);
/* 1290 */     int numRects = rectList.size();
/* 1292 */     for (int ty = tyMin; ty <= tyMax; ty++) {
/* 1293 */       for (int tx = txMin; tx <= txMax; tx++) {
/* 1294 */         WritableRaster wr = getWritableTile(tx, ty);
/* 1295 */         if (wr != null) {
/* 1296 */           Rectangle tileRect = getTileRect(tx, ty);
/* 1297 */           for (int i = 0; i < numRects; i++) {
/* 1298 */             Rectangle rect = rectList.get(i);
/* 1299 */             rect = rect.intersection(tileRect);
/* 1302 */             if (!rect.isEmpty()) {
/* 1303 */               Raster rChild = r.createChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, null);
/* 1307 */               WritableRaster wChild = wr.createWritableChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, (int[])null);
/* 1311 */               JDKWorkarounds.setRect(wChild, rChild, 0, 0);
/*      */             } 
/*      */           } 
/* 1314 */           releaseWritableTile(tx, ty);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addTileObserver(TileObserver observer) {
/* 1339 */     this.tileObservers.addElement(observer);
/*      */   }
/*      */   
/*      */   public void removeTileObserver(TileObserver observer) {
/* 1352 */     this.tileObservers.removeElement(observer);
/*      */   }
/*      */   
/*      */   public Point[] getWritableTileIndices() {
/* 1363 */     Point[] indices = null;
/* 1365 */     if (hasTileWriters()) {
/* 1366 */       Vector v = new Vector();
/* 1367 */       int count = 0;
/* 1369 */       for (int j = 0; j < this.tilesY; j++) {
/* 1370 */         for (int i = 0; i < this.tilesX; i++) {
/* 1371 */           if (this.writers[i][j] > 0) {
/* 1372 */             v.addElement(new Point(i + this.minTileX, j + this.minTileY));
/* 1373 */             count++;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1378 */       indices = new Point[count];
/* 1379 */       for (int k = 0; k < count; k++)
/* 1380 */         indices[k] = v.elementAt(k); 
/*      */     } 
/* 1384 */     return indices;
/*      */   }
/*      */   
/*      */   public boolean hasTileWriters() {
/* 1395 */     return (this.numWritableTiles[0] > 0);
/*      */   }
/*      */   
/*      */   public boolean isTileWritable(int tileX, int tileY) {
/* 1405 */     return (this.writers[tileX - this.minTileX][tileY - this.minTileY] > 0);
/*      */   }
/*      */   
/*      */   public void clearTiles() {
/* 1418 */     if (hasTileWriters())
/* 1419 */       throw new IllegalStateException(JaiI18N.getString("TiledImage2")); 
/* 1421 */     this.tiles = (WritableRaster[][])null;
/*      */   }
/*      */   
/*      */   public void setSample(int x, int y, int b, int s) {
/* 1453 */     int tileX = XToTileX(x);
/* 1454 */     int tileY = YToTileY(y);
/* 1455 */     WritableRaster t = getWritableTile(tileX, tileY);
/* 1456 */     if (t != null)
/* 1457 */       t.setSample(x, y, b, s); 
/* 1459 */     releaseWritableTile(tileX, tileY);
/*      */   }
/*      */   
/*      */   public int getSample(int x, int y, int b) {
/* 1470 */     int tileX = XToTileX(x);
/* 1471 */     int tileY = YToTileY(y);
/* 1472 */     Raster t = getTile(tileX, tileY);
/* 1473 */     return t.getSample(x, y, b);
/*      */   }
/*      */   
/*      */   public void setSample(int x, int y, int b, float s) {
/* 1485 */     int tileX = XToTileX(x);
/* 1486 */     int tileY = YToTileY(y);
/* 1487 */     WritableRaster t = getWritableTile(tileX, tileY);
/* 1488 */     if (t != null)
/* 1489 */       t.setSample(x, y, b, s); 
/* 1491 */     releaseWritableTile(tileX, tileY);
/*      */   }
/*      */   
/*      */   public float getSampleFloat(int x, int y, int b) {
/* 1502 */     int tileX = XToTileX(x);
/* 1503 */     int tileY = YToTileY(y);
/* 1504 */     Raster t = getTile(tileX, tileY);
/* 1505 */     return t.getSampleFloat(x, y, b);
/*      */   }
/*      */   
/*      */   public void setSample(int x, int y, int b, double s) {
/* 1517 */     int tileX = XToTileX(x);
/* 1518 */     int tileY = YToTileY(y);
/* 1519 */     WritableRaster t = getWritableTile(tileX, tileY);
/* 1520 */     if (t != null)
/* 1521 */       t.setSample(x, y, b, s); 
/* 1523 */     releaseWritableTile(tileX, tileY);
/*      */   }
/*      */   
/*      */   public double getSampleDouble(int x, int y, int b) {
/* 1534 */     int tileX = XToTileX(x);
/* 1535 */     int tileY = YToTileY(y);
/* 1536 */     Raster t = getTile(tileX, tileY);
/* 1537 */     return t.getSampleDouble(x, y, b);
/*      */   }
/*      */   
/*      */   public synchronized void propertyChange(PropertyChangeEvent evt) {
/* 1564 */     PlanarImage src = (getNumSources() > 0) ? getSourceImage(0) : null;
/* 1566 */     if (evt.getSource() == src && (evt instanceof RenderingChangeEvent || (evt instanceof PropertyChangeEventJAI && evt.getPropertyName().equalsIgnoreCase("InvalidRegion")))) {
/* 1572 */       Shape invalidRegion = (evt instanceof RenderingChangeEvent) ? ((RenderingChangeEvent)evt).getInvalidRegion() : (Shape)evt.getNewValue();
/* 1578 */       Rectangle invalidBounds = invalidRegion.getBounds();
/* 1579 */       if (invalidBounds.isEmpty())
/*      */         return; 
/* 1584 */       Area invalidArea = new Area(invalidRegion);
/* 1585 */       if (this.srcROI != null) {
/* 1586 */         Shape roiShape = this.srcROI.getAsShape();
/* 1587 */         if (roiShape != null) {
/* 1588 */           invalidArea.intersect(new Area(roiShape));
/*      */         } else {
/* 1590 */           LinkedList rectList = this.srcROI.getAsRectangleList(invalidBounds.x, invalidBounds.y, invalidBounds.width, invalidBounds.height);
/* 1595 */           Iterator it = rectList.iterator();
/* 1596 */           while (it.hasNext() && !invalidArea.isEmpty())
/* 1597 */             invalidArea.intersect(new Area(it.next())); 
/*      */         } 
/*      */       } 
/* 1603 */       if (invalidArea.isEmpty())
/*      */         return; 
/* 1608 */       Point[] tileIndices = getTileIndices(invalidArea.getBounds());
/* 1609 */       int numIndices = tileIndices.length;
/* 1612 */       for (int i = 0; i < numIndices; i++) {
/* 1613 */         int tx = (tileIndices[i]).x;
/* 1614 */         int ty = (tileIndices[i]).y;
/* 1615 */         Raster tile = this.tiles[tx][ty];
/* 1616 */         if (tile != null && invalidArea.intersects(tile.getBounds()))
/* 1618 */           this.tiles[tx][ty] = null; 
/*      */       } 
/* 1622 */       if (this.eventManager.hasListeners("InvalidRegion")) {
/* 1624 */         Shape oldInvalidRegion = new Rectangle();
/* 1628 */         if (this.srcROI != null) {
/* 1629 */           Area oldInvalidArea = new Area(getBounds());
/* 1630 */           Shape roiShape = this.srcROI.getAsShape();
/* 1631 */           if (roiShape != null) {
/* 1632 */             oldInvalidArea.subtract(new Area(roiShape));
/*      */           } else {
/* 1634 */             Rectangle oldInvalidBounds = oldInvalidArea.getBounds();
/* 1636 */             LinkedList rectList = this.srcROI.getAsRectangleList(oldInvalidBounds.x, oldInvalidBounds.y, oldInvalidBounds.width, oldInvalidBounds.height);
/* 1641 */             Iterator it = rectList.iterator();
/* 1642 */             while (it.hasNext() && !oldInvalidArea.isEmpty())
/* 1643 */               oldInvalidArea.subtract(new Area(it.next())); 
/*      */           } 
/* 1646 */           oldInvalidRegion = oldInvalidArea;
/*      */         } 
/* 1650 */         PropertyChangeEventJAI irEvt = new PropertyChangeEventJAI(this, "InvalidRegion", oldInvalidRegion, invalidRegion);
/* 1656 */         this.eventManager.firePropertyChange(irEvt);
/* 1659 */         Vector sinks = getSinks();
/* 1660 */         if (sinks != null) {
/* 1661 */           int numSinks = sinks.size();
/* 1662 */           for (int j = 0; j < numSinks; j++) {
/* 1663 */             Object sink = sinks.get(j);
/* 1664 */             if (sink instanceof PropertyChangeListener)
/* 1665 */               ((PropertyChangeListener)sink).propertyChange(irEvt); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TiledImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */