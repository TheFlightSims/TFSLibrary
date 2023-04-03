/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColormapOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ 
/*     */ final class LookupOpImage extends ColormapOpImage {
/*     */   protected LookupTableJAI table;
/*     */   
/*     */   public LookupOpImage(RenderedImage source, Map config, ImageLayout layout, LookupTableJAI table) {
/* 115 */     super(source, layout, config, true);
/* 117 */     this.table = table;
/* 119 */     SampleModel sm = source.getSampleModel();
/* 121 */     if (this.sampleModel.getTransferType() != table.getDataType() || this.sampleModel.getNumBands() != table.getDestNumBands(sm.getNumBands())) {
/* 129 */       this.sampleModel = table.getDestSampleModel(sm, this.tileWidth, this.tileHeight);
/* 130 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/* 133 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/* 139 */     permitInPlaceOperation();
/* 142 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/* 149 */     for (int b = 0; b < 3; b++) {
/* 150 */       byte[] map = colormap[b];
/* 151 */       int mapSize = map.length;
/* 153 */       int band = (this.table.getNumBands() < 3) ? 0 : b;
/* 155 */       for (int i = 0; i < mapSize; i++) {
/* 156 */         int result = this.table.lookup(band, map[i] & 0xFF);
/* 157 */         map[i] = ImageUtil.clampByte(result);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 173 */     this.table.lookup(sources[0], dest, destRect);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\LookupOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */