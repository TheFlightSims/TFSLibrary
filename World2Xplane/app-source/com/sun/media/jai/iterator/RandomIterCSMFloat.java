/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import com.sun.media.jai.util.DataBufferUtils;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.RenderedImage;
/*    */ 
/*    */ public class RandomIterCSMFloat extends RandomIterCSM {
/*    */   float[][] bankData;
/*    */   
/*    */   public RandomIterCSMFloat(RenderedImage im, Rectangle bounds) {
/* 25 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   protected final void dataBufferChanged() {
/* 29 */     this.bankData = DataBufferUtils.getBankDataFloat(this.dataBuffer);
/*    */   }
/*    */   
/*    */   public final int getSample(int x, int y, int b) {
/* 33 */     makeCurrent(x - this.boundsX, y - this.boundsX);
/* 34 */     return (int)this.bankData[b][(x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride + this.bandOffsets[b]];
/*    */   }
/*    */   
/*    */   public final float getSampleFloat(int x, int y, int b) {
/* 40 */     makeCurrent(x - this.boundsX, y - this.boundsX);
/* 41 */     return this.bankData[b][(x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride + this.bandOffsets[b]];
/*    */   }
/*    */   
/*    */   public final double getSampleDouble(int x, int y, int b) {
/* 47 */     makeCurrent(x - this.boundsX, y - this.boundsX);
/* 48 */     return this.bankData[b][(x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride + this.bandOffsets[b]];
/*    */   }
/*    */   
/*    */   public float[] getPixel(int x, int y, float[] fArray) {
/* 54 */     if (fArray == null)
/* 55 */       fArray = new float[this.numBands]; 
/* 58 */     int offset = (x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride;
/* 60 */     for (int b = 0; b < this.numBands; b++)
/* 61 */       fArray[b] = this.bankData[b][offset + this.bandOffsets[b]]; 
/* 63 */     return fArray;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RandomIterCSMFloat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */