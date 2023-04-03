/*    */ package com.vividsolutions.jts.noding;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.util.Assert;
/*    */ 
/*    */ public class SegmentPointComparator {
/*    */   public static int compare(int octant, Coordinate p0, Coordinate p1) {
/* 63 */     if (p0.equals2D(p1))
/* 63 */       return 0; 
/* 65 */     int xSign = relativeSign(p0.x, p1.x);
/* 66 */     int ySign = relativeSign(p0.y, p1.y);
/* 68 */     switch (octant) {
/*    */       case 0:
/* 69 */         return compareValue(xSign, ySign);
/*    */       case 1:
/* 70 */         return compareValue(ySign, xSign);
/*    */       case 2:
/* 71 */         return compareValue(ySign, -xSign);
/*    */       case 3:
/* 72 */         return compareValue(-xSign, ySign);
/*    */       case 4:
/* 73 */         return compareValue(-xSign, -ySign);
/*    */       case 5:
/* 74 */         return compareValue(-ySign, -xSign);
/*    */       case 6:
/* 75 */         return compareValue(-ySign, xSign);
/*    */       case 7:
/* 76 */         return compareValue(xSign, -ySign);
/*    */     } 
/* 78 */     Assert.shouldNeverReachHere("invalid octant value");
/* 79 */     return 0;
/*    */   }
/*    */   
/*    */   public static int relativeSign(double x0, double x1) {
/* 84 */     if (x0 < x1)
/* 84 */       return -1; 
/* 85 */     if (x0 > x1)
/* 85 */       return 1; 
/* 86 */     return 0;
/*    */   }
/*    */   
/*    */   private static int compareValue(int compareSign0, int compareSign1) {
/* 91 */     if (compareSign0 < 0)
/* 91 */       return -1; 
/* 92 */     if (compareSign0 > 0)
/* 92 */       return 1; 
/* 93 */     if (compareSign1 < 0)
/* 93 */       return -1; 
/* 94 */     if (compareSign1 > 0)
/* 94 */       return 1; 
/* 95 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentPointComparator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */