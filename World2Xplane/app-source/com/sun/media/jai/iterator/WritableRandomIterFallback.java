/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRenderedImage;
/*     */ import javax.media.jai.iterator.WritableRandomIter;
/*     */ 
/*     */ public final class WritableRandomIterFallback extends RandomIterFallback implements WritableRandomIter {
/*     */   WritableRenderedImage wim;
/*     */   
/*     */   public WritableRandomIterFallback(WritableRenderedImage im, Rectangle bounds) {
/*  28 */     super(im, bounds);
/*  29 */     this.wim = im;
/*     */   }
/*     */   
/*     */   private void makeCurrentWritable(int xLocal, int yLocal) {
/*  33 */     int xIDNew = this.xTiles[xLocal];
/*  34 */     int yIDNew = this.yTiles[yLocal];
/*  36 */     if (xIDNew != this.xID || yIDNew != this.yID || this.dataBuffer == null) {
/*  37 */       if (this.dataBuffer != null)
/*  38 */         this.wim.releaseWritableTile(this.xID, this.yID); 
/*  40 */       this.xID = xIDNew;
/*  41 */       this.yID = yIDNew;
/*  42 */       Raster tile = this.wim.getWritableTile(this.xID, this.yID);
/*  44 */       this.dataBuffer = tile.getDataBuffer();
/*  45 */       this.sampleModelTranslateX = tile.getSampleModelTranslateX();
/*  46 */       this.sampleModelTranslateY = tile.getSampleModelTranslateY();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSample(int x, int y, int b, int s) {
/*  51 */     makeCurrentWritable(x - this.boundsX, y - this.boundsY);
/*  52 */     this.sampleModel.setSample(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, b, s, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void setSample(int x, int y, int b, float s) {
/*  59 */     makeCurrentWritable(x - this.boundsX, y - this.boundsY);
/*  60 */     this.sampleModel.setSample(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, b, s, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void setSample(int x, int y, int b, double s) {
/*  67 */     makeCurrentWritable(x - this.boundsX, y - this.boundsY);
/*  68 */     this.sampleModel.setSample(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, b, s, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void setPixel(int x, int y, int[] iArray) {
/*  75 */     makeCurrentWritable(x - this.boundsX, y - this.boundsY);
/*  76 */     this.sampleModel.setPixel(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, iArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void setPixel(int x, int y, float[] fArray) {
/*  83 */     makeCurrentWritable(x - this.boundsX, y - this.boundsY);
/*  84 */     this.sampleModel.setPixel(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, fArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void setPixel(int x, int y, double[] dArray) {
/*  91 */     makeCurrentWritable(x - this.boundsX, y - this.boundsY);
/*  92 */     this.sampleModel.setPixel(x - this.sampleModelTranslateX, y - this.sampleModelTranslateY, dArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public void done() {
/*  99 */     if (this.dataBuffer != null)
/* 100 */       this.wim.releaseWritableTile(this.xID, this.yID); 
/* 102 */     this.dataBuffer = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WritableRandomIterFallback.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */