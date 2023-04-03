/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.RenderedImage;
/*    */ import javax.media.jai.iterator.WritableRectIter;
/*    */ 
/*    */ public class WritableRectIterCSMByte extends RectIterCSMByte implements WritableRectIter {
/*    */   public WritableRectIterCSMByte(RenderedImage im, Rectangle bounds) {
/* 24 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   public void setSample(int s) {
/* 28 */     this.bank[this.offset + this.bandOffset] = (byte)s;
/*    */   }
/*    */   
/*    */   public void setSample(int b, int s) {
/* 32 */     this.bankData[b][this.offset + this.bandOffsets[b]] = (byte)s;
/*    */   }
/*    */   
/*    */   public void setSample(float s) {
/* 36 */     this.bank[this.offset + this.bandOffset] = (byte)(int)s;
/*    */   }
/*    */   
/*    */   public void setSample(int b, float s) {
/* 40 */     this.bankData[b][this.offset + this.bandOffsets[b]] = (byte)(int)s;
/*    */   }
/*    */   
/*    */   public void setSample(double s) {
/* 44 */     this.bank[this.offset + this.bandOffset] = (byte)(int)s;
/*    */   }
/*    */   
/*    */   public void setSample(int b, double s) {
/* 48 */     this.bankData[b][this.offset + this.bandOffsets[b]] = (byte)(int)s;
/*    */   }
/*    */   
/*    */   public void setPixel(int[] iArray) {
/* 52 */     for (int b = 0; b < this.numBands; b++)
/* 53 */       this.bankData[b][this.offset + this.bandOffsets[b]] = (byte)iArray[b]; 
/*    */   }
/*    */   
/*    */   public void setPixel(float[] fArray) {
/* 58 */     for (int b = 0; b < this.numBands; b++)
/* 59 */       this.bankData[b][this.offset + this.bandOffsets[b]] = (byte)(int)fArray[b]; 
/*    */   }
/*    */   
/*    */   public void setPixel(double[] dArray) {
/* 64 */     for (int b = 0; b < this.numBands; b++)
/* 65 */       this.bankData[b][this.offset + this.bandOffsets[b]] = (byte)(int)dArray[b]; 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WritableRectIterCSMByte.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */