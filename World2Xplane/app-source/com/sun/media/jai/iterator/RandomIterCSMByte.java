/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.DataBufferByte;
/*    */ import java.awt.image.RenderedImage;
/*    */ 
/*    */ public class RandomIterCSMByte extends RandomIterCSM {
/*    */   byte[][] bankData;
/*    */   
/*    */   public RandomIterCSMByte(RenderedImage im, Rectangle bounds) {
/* 26 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   protected final void dataBufferChanged() {
/* 30 */     this.bankData = ((DataBufferByte)this.dataBuffer).getBankData();
/*    */   }
/*    */   
/*    */   public final int getSample(int x, int y, int b) {
/* 34 */     makeCurrent(x - this.boundsX, y - this.boundsY);
/* 35 */     return this.bankData[b][(x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride + this.bandOffsets[b]] & 0xFF;
/*    */   }
/*    */   
/*    */   public final float getSampleFloat(int x, int y, int b) {
/* 41 */     makeCurrent(x - this.boundsX, y - this.boundsX);
/* 42 */     return (this.bankData[b][(x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride + this.bandOffsets[b]] & 0xFF);
/*    */   }
/*    */   
/*    */   public final double getSampleDouble(int x, int y, int b) {
/* 48 */     makeCurrent(x - this.boundsX, y - this.boundsX);
/* 49 */     return (this.bankData[b][(x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride + this.bandOffsets[b]] & 0xFF);
/*    */   }
/*    */   
/*    */   public int[] getPixel(int x, int y, int[] iArray) {
/* 55 */     if (iArray == null)
/* 56 */       iArray = new int[this.numBands]; 
/* 59 */     int offset = (x - this.sampleModelTranslateX) * this.pixelStride + (y - this.sampleModelTranslateY) * this.scanlineStride;
/* 61 */     for (int b = 0; b < this.numBands; b++)
/* 62 */       iArray[b] = this.bankData[b][offset + this.bandOffsets[b]] & 0xFF; 
/* 64 */     return iArray;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RandomIterCSMByte.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */