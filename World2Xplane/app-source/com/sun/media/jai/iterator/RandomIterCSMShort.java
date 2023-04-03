/*    */ package com.sun.media.jai.iterator;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.RenderedImage;
/*    */ 
/*    */ public class RandomIterCSMShort extends RandomIterCSM {
/*    */   public RandomIterCSMShort(RenderedImage im, Rectangle bounds) {
/* 23 */     super(im, bounds);
/*    */   }
/*    */   
/*    */   public final int getSample(int x, int y, int b) {
/* 27 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RandomIterCSMShort.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */