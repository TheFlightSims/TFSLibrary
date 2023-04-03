/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.WritableRenderedImage;
/*    */ import javax.media.jai.iterator.WritableRandomIter;
/*    */ 
/*    */ public final class WritableRandomIterCSMShort extends RandomIterCSMShort implements WritableRandomIter {
/*    */   public WritableRandomIterCSMShort(WritableRenderedImage im, Rectangle bounds) {
/* 25 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   public void setSample(int x, int y, int b, int val) {}
/*    */   
/*    */   public void setSample(int x, int y, int b, float val) {}
/*    */   
/*    */   public void setSample(int x, int y, int b, double val) {}
/*    */   
/*    */   public void setPixel(int x, int y, int[] iArray) {}
/*    */   
/*    */   public void setPixel(int x, int y, float[] fArray) {}
/*    */   
/*    */   public void setPixel(int x, int y, double[] dArray) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\WritableRandomIterCSMShort.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */