/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.RenderedImage;
/*    */ import javax.media.jai.iterator.WritableRectIter;
/*    */ 
/*    */ public class WritableRectIterCSMFloat extends RectIterCSMFloat implements WritableRectIter {
/*    */   public WritableRectIterCSMFloat(RenderedImage im, Rectangle bounds) {
/* 23 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   public void setSample(int s) {
/* 27 */     this.bank[this.offset + this.bandOffset] = s;
/*    */   }
/*    */   
/*    */   public void setSample(int b, int s) {
/* 31 */     this.bankData[b][this.offset + this.bandOffsets[b]] = s;
/*    */   }
/*    */   
/*    */   public void setSample(float s) {
/* 35 */     this.bank[this.offset + this.bandOffset] = s;
/*    */   }
/*    */   
/*    */   public void setSample(int b, float s) {
/* 39 */     this.bankData[b][this.offset + this.bandOffsets[b]] = s;
/*    */   }
/*    */   
/*    */   public void setSample(double s) {
/* 43 */     this.bank[this.offset + this.bandOffset] = (float)s;
/*    */   }
/*    */   
/*    */   public void setSample(int b, double s) {
/* 47 */     this.bankData[b][this.offset + this.bandOffsets[b]] = (float)s;
/*    */   }
/*    */   
/*    */   public void setPixel(int[] iArray) {
/* 51 */     for (int b = 0; b < this.numBands; b++)
/* 52 */       this.bankData[b][this.offset + this.bandOffsets[b]] = iArray[b]; 
/*    */   }
/*    */   
/*    */   public void setPixel(float[] fArray) {
/* 57 */     for (int b = 0; b < this.numBands; b++)
/* 58 */       this.bankData[b][this.offset + this.bandOffsets[b]] = fArray[b]; 
/*    */   }
/*    */   
/*    */   public void setPixel(double[] dArray) {
/* 63 */     for (int b = 0; b < this.numBands; b++)
/* 64 */       this.bankData[b][this.offset + this.bandOffsets[b]] = (float)dArray[b]; 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WritableRectIterCSMFloat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */