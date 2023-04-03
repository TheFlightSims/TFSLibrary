/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ final class BandSelectOpImage extends PointOpImage {
/*     */   private boolean areDataCopied;
/*     */   
/*     */   private int[] bandIndices;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, int[] bandIndices) {
/*  52 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  56 */     SampleModel sourceSM = source.getSampleModel();
/*  57 */     int numBands = bandIndices.length;
/*  63 */     SampleModel sm = null;
/*  64 */     if (sourceSM instanceof java.awt.image.SinglePixelPackedSampleModel && numBands < 3) {
/*  65 */       (new int[1])[0] = 0;
/*  65 */       (new int[2])[0] = 0;
/*  65 */       (new int[2])[1] = 1;
/*  65 */       sm = new PixelInterleavedSampleModel(0, sourceSM.getWidth(), sourceSM.getHeight(), numBands, sourceSM.getWidth() * numBands, (numBands == 1) ? new int[1] : new int[2]);
/*     */     } else {
/*  71 */       sm = sourceSM.createSubsetSampleModel(bandIndices);
/*     */     } 
/*  73 */     il.setSampleModel(sm);
/*  76 */     ColorModel cm = il.getColorModel(null);
/*  77 */     if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/*  80 */       il.unsetValid(512); 
/*  84 */     il.setTileGridXOffset(source.getTileGridXOffset());
/*  85 */     il.setTileGridYOffset(source.getTileGridYOffset());
/*  86 */     il.setTileWidth(source.getTileWidth());
/*  87 */     il.setTileHeight(source.getTileHeight());
/*  89 */     return il;
/*     */   }
/*     */   
/*     */   public BandSelectOpImage(RenderedImage source, Map config, ImageLayout layout, int[] bandIndices) {
/* 105 */     super(vectorize(source), layoutHelper(layout, source, bandIndices), config, true);
/* 109 */     this.areDataCopied = (source.getSampleModel() instanceof java.awt.image.SinglePixelPackedSampleModel && bandIndices.length < 3);
/* 112 */     this.bandIndices = (int[])bandIndices.clone();
/*     */   }
/*     */   
/*     */   public boolean computesUniqueTiles() {
/* 116 */     return this.areDataCopied;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 120 */     Raster tile = getSourceImage(0).getTile(tileX, tileY);
/* 122 */     if (this.areDataCopied) {
/* 125 */       tile = tile.createChild(tile.getMinX(), tile.getMinY(), tile.getWidth(), tile.getHeight(), tile.getMinX(), tile.getMinY(), this.bandIndices);
/* 129 */       WritableRaster raster = createTile(tileX, tileY);
/* 130 */       raster.setRect(tile);
/* 132 */       return raster;
/*     */     } 
/* 135 */     return tile.createChild(tile.getMinX(), tile.getMinY(), tile.getWidth(), tile.getHeight(), tile.getMinX(), tile.getMinY(), this.bandIndices);
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 144 */     return computeTile(tileX, tileY);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BandSelectOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */