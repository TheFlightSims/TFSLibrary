/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.RenderedImage;
/*    */ 
/*    */ public class RandomIterCSMDouble extends RandomIterCSM {
/*    */   public RandomIterCSMDouble(RenderedImage im, Rectangle bounds) {
/* 23 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   public final int getSample(int x, int y, int b) {
/* 27 */     return (int)getSampleDouble(x, y, b);
/*    */   }
/*    */   
/*    */   public final float getSampleFloat(int x, int y, int b) {
/* 31 */     return (float)getSampleDouble(x, y, b);
/*    */   }
/*    */   
/*    */   public final double getSampleDouble(int x, int y, int b) {
/* 35 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RandomIterCSMDouble.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */