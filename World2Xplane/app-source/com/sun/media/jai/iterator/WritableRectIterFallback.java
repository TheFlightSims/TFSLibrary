/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.WritableRenderedImage;
/*    */ import javax.media.jai.iterator.WritableRectIter;
/*    */ 
/*    */ public class WritableRectIterFallback extends RectIterFallback implements WritableRectIter {
/*    */   protected WritableRenderedImage wim;
/*    */   
/*    */   public WritableRectIterFallback(WritableRenderedImage im, Rectangle bounds) {
/* 29 */     super(im, bounds);
/* 30 */     this.wim = im;
/*    */   }
/*    */   
/*    */   public void setSample(int s) {
/* 34 */     this.sampleModel.setSample(this.localX, this.localY, this.b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(int b, int s) {
/* 38 */     this.sampleModel.setSample(this.localX, this.localY, b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(float s) {
/* 42 */     this.sampleModel.setSample(this.localX, this.localY, this.b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(int b, float s) {
/* 46 */     this.sampleModel.setSample(this.localX, this.localY, b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(double s) {
/* 50 */     this.sampleModel.setSample(this.localX, this.localY, this.b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setSample(int b, double s) {
/* 54 */     this.sampleModel.setSample(this.localX, this.localY, b, s, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setPixel(int[] iArray) {
/* 58 */     this.sampleModel.setPixel(this.localX, this.localY, iArray, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setPixel(float[] fArray) {
/* 62 */     this.sampleModel.setPixel(this.localX, this.localY, fArray, this.dataBuffer);
/*    */   }
/*    */   
/*    */   public void setPixel(double[] dArray) {
/* 66 */     this.sampleModel.setPixel(this.localX, this.localY, dArray, this.dataBuffer);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WritableRectIterFallback.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */