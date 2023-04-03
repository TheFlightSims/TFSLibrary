/*    */ package com.vividsolutions.jts.index.quadtree;
/*    */ 
/*    */ public class IntervalSize {
/*    */   public static final int MIN_BINARY_EXPONENT = -50;
/*    */   
/*    */   public static boolean isZeroWidth(double min, double max) {
/* 65 */     double width = max - min;
/* 66 */     if (width == 0.0D)
/* 66 */       return true; 
/* 68 */     double maxAbs = Math.max(Math.abs(min), Math.abs(max));
/* 69 */     double scaledInterval = width / maxAbs;
/* 70 */     int level = DoubleBits.exponent(scaledInterval);
/* 71 */     return (level <= -50);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\IntervalSize.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */