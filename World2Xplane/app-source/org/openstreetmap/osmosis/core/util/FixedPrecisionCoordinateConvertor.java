/*    */ package org.openstreetmap.osmosis.core.util;
/*    */ 
/*    */ public final class FixedPrecisionCoordinateConvertor {
/*    */   private static final int PRECISION = 7;
/*    */   
/* 13 */   private static final int MULTIPLICATION_FACTOR = calculateMultiplicationFactor();
/*    */   
/*    */   private static int calculateMultiplicationFactor() {
/* 33 */     int result = 1;
/* 35 */     for (int i = 0; i < 7; i++)
/* 36 */       result *= 10; 
/* 39 */     return result;
/*    */   }
/*    */   
/*    */   public static int convertToFixed(double coordinate) {
/* 53 */     int result = (int)Math.round(coordinate * MULTIPLICATION_FACTOR);
/* 55 */     return result;
/*    */   }
/*    */   
/*    */   public static double convertToDouble(int coordinate) {
/* 69 */     double result = coordinate / MULTIPLICATION_FACTOR;
/* 71 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\FixedPrecisionCoordinateConvertor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */