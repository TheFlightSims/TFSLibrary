/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ 
/*     */ public abstract class RandomIterCSM extends RandomIterFallback {
/*     */   protected ComponentSampleModel sampleModel;
/*     */   
/*     */   protected int pixelStride;
/*     */   
/*     */   protected int scanlineStride;
/*     */   
/*     */   protected int[] bandOffsets;
/*     */   
/*     */   protected int numBands;
/*     */   
/*     */   public RandomIterCSM(RenderedImage im, Rectangle bounds) {
/*  34 */     super(im, bounds);
/*  35 */     this.sampleModel = (ComponentSampleModel)im.getSampleModel();
/*  36 */     this.numBands = this.sampleModel.getNumBands();
/*  37 */     this.pixelStride = this.sampleModel.getPixelStride();
/*  38 */     this.scanlineStride = this.sampleModel.getScanlineStride();
/*     */   }
/*     */   
/*     */   protected void dataBufferChanged() {}
/*     */   
/*     */   protected final void makeCurrent(int xLocal, int yLocal) {
/*  51 */     int xIDNew = this.xTiles[xLocal];
/*  52 */     int yIDNew = this.yTiles[yLocal];
/*  54 */     if (xIDNew != this.xID || yIDNew != this.yID || this.dataBuffer == null) {
/*  55 */       this.xID = xIDNew;
/*  56 */       this.yID = yIDNew;
/*  57 */       Raster tile = this.im.getTile(this.xID, this.yID);
/*  59 */       this.dataBuffer = tile.getDataBuffer();
/*  60 */       dataBufferChanged();
/*  62 */       this.bandOffsets = this.dataBuffer.getOffsets();
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getSampleFloat(int x, int y, int b) {
/*  67 */     return getSample(x, y, b);
/*     */   }
/*     */   
/*     */   public double getSampleDouble(int x, int y, int b) {
/*  71 */     return getSample(x, y, b);
/*     */   }
/*     */   
/*     */   public int[] getPixel(int x, int y, int[] iArray) {
/*  75 */     if (iArray == null)
/*  76 */       iArray = new int[this.numBands]; 
/*  78 */     for (int b = 0; b < this.numBands; b++)
/*  79 */       iArray[b] = getSample(x, y, b); 
/*  81 */     return iArray;
/*     */   }
/*     */   
/*     */   public float[] getPixel(int x, int y, float[] fArray) {
/*  85 */     if (fArray == null)
/*  86 */       fArray = new float[this.numBands]; 
/*  88 */     for (int b = 0; b < this.numBands; b++)
/*  89 */       fArray[b] = getSampleFloat(x, y, b); 
/*  91 */     return fArray;
/*     */   }
/*     */   
/*     */   public double[] getPixel(int x, int y, double[] dArray) {
/*  95 */     if (dArray == null)
/*  96 */       dArray = new double[this.numBands]; 
/*  98 */     for (int b = 0; b < this.numBands; b++)
/*  99 */       dArray[b] = getSampleDouble(x, y, b); 
/* 101 */     return dArray;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RandomIterCSM.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */