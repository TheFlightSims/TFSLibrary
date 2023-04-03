/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.WritableRenderedImage;
/*    */ import javax.media.jai.iterator.WritableRookIter;
/*    */ 
/*    */ public class WritableRookIterFallback extends RookIterFallback implements WritableRookIter {
/*    */   public WritableRookIterFallback(WritableRenderedImage im, Rectangle bounds) {
/* 27 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   public void setSample(int s) {
/* 31 */     this.sampleModel.setSample(this.localX, this.localY, this.b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(int b, int s) {
/* 35 */     this.sampleModel.setSample(this.localX, this.localY, b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(float s) {
/* 39 */     this.sampleModel.setSample(this.localX, this.localY, this.b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(int b, float s) {
/* 43 */     this.sampleModel.setSample(this.localX, this.localY, b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(double s) {
/* 47 */     this.sampleModel.setSample(this.localX, this.localY, this.b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(int b, double s) {
/* 51 */     this.sampleModel.setSample(this.localX, this.localY, b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setPixel(int[] iArray) {
/* 55 */     this.sampleModel.setPixel(this.localX, this.localY, iArray, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setPixel(float[] fArray) {
/* 59 */     this.sampleModel.setPixel(this.localX, this.localY, fArray, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setPixel(double[] dArray) {
/* 63 */     this.sampleModel.setPixel(this.localX, this.localY, dArray, this.dataBuffer);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WritableRookIterFallback.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */