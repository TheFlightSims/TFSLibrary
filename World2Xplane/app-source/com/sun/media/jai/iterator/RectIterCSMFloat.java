/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import com.sun.media.jai.util.DataBufferUtils;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.RenderedImage;
/*     */ 
/*     */ public class RectIterCSMFloat extends RectIterCSM {
/*     */   float[][] bankData;
/*     */   
/*     */   float[] bank;
/*     */   
/*     */   public RectIterCSMFloat(RenderedImage im, Rectangle bounds) {
/*  25 */     super(im, bounds);
/*  27 */     this.bankData = new float[this.numBands + 1][];
/*  28 */     dataBufferChanged();
/*     */   }
/*     */   
/*     */   protected final void dataBufferChanged() {
/*  32 */     if (this.bankData == null)
/*     */       return; 
/*  36 */     float[][] bd = DataBufferUtils.getBankDataFloat(this.dataBuffer);
/*  37 */     for (int i = 0; i < this.numBands; i++)
/*  38 */       this.bankData[i] = bd[this.bankIndices[i]]; 
/*  40 */     this.bank = this.bankData[this.b];
/*  42 */     adjustBandOffsets();
/*     */   }
/*     */   
/*     */   public void startBands() {
/*  46 */     super.startBands();
/*  47 */     this.bank = this.bankData[0];
/*     */   }
/*     */   
/*     */   public void nextBand() {
/*  51 */     super.nextBand();
/*  52 */     this.bank = this.bankData[this.b];
/*     */   }
/*     */   
/*     */   public final int getSample() {
/*  56 */     return (int)this.bank[this.offset + this.bandOffset];
/*     */   }
/*     */   
/*     */   public final int getSample(int b) {
/*  60 */     return (int)this.bankData[b][this.offset + this.bandOffsets[b]];
/*     */   }
/*     */   
/*     */   public final float getSampleFloat() {
/*  64 */     return this.bank[this.offset + this.bandOffset];
/*     */   }
/*     */   
/*     */   public final float getSampleFloat(int b) {
/*  68 */     return this.bankData[b][this.offset + this.bandOffsets[b]];
/*     */   }
/*     */   
/*     */   public final double getSampleDouble() {
/*  72 */     return this.bank[this.offset + this.bandOffset];
/*     */   }
/*     */   
/*     */   public final double getSampleDouble(int b) {
/*  76 */     return this.bankData[b][this.offset + this.bandOffsets[b]];
/*     */   }
/*     */   
/*     */   public int[] getPixel(int[] iArray) {
/*  80 */     if (iArray == null)
/*  81 */       iArray = new int[this.numBands]; 
/*  83 */     for (int b = 0; b < this.numBands; b++)
/*  84 */       iArray[b] = (int)this.bankData[b][this.offset + this.bandOffsets[b]]; 
/*  86 */     return iArray;
/*     */   }
/*     */   
/*     */   public float[] getPixel(float[] fArray) {
/*  90 */     if (fArray == null)
/*  91 */       fArray = new float[this.numBands]; 
/*  93 */     for (int b = 0; b < this.numBands; b++)
/*  94 */       fArray[b] = this.bankData[b][this.offset + this.bandOffsets[b]]; 
/*  96 */     return fArray;
/*     */   }
/*     */   
/*     */   public double[] getPixel(double[] dArray) {
/* 100 */     if (dArray == null)
/* 101 */       dArray = new double[this.numBands]; 
/* 103 */     for (int b = 0; b < this.numBands; b++)
/* 104 */       dArray[b] = this.bankData[b][this.offset + this.bandOffsets[b]]; 
/* 106 */     return dArray;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RectIterCSMFloat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */