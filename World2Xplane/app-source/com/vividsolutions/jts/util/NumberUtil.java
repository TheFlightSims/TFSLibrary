/*   */ package com.vividsolutions.jts.util;
/*   */ 
/*   */ public class NumberUtil {
/*   */   public static boolean equalsWithTolerance(double x1, double x2, double tolerance) {
/* 8 */     return (Math.abs(x1 - x2) <= tolerance);
/*   */   }
/*   */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\NumberUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */