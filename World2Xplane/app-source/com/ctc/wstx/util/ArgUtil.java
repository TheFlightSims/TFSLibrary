/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ public final class ArgUtil {
/*    */   public static boolean convertToBoolean(String prop, Object value) {
/* 14 */     if (value == null)
/* 15 */       return false; 
/* 17 */     if (value instanceof Boolean)
/* 18 */       return ((Boolean)value).booleanValue(); 
/* 20 */     if (value instanceof String) {
/* 21 */       String str = (String)value;
/* 22 */       if (str.equalsIgnoreCase("false"))
/* 23 */         return false; 
/* 25 */       if (str.equalsIgnoreCase("true"))
/* 26 */         return true; 
/* 28 */       throw new IllegalArgumentException("Invalid String value for property '" + prop + "': expected Boolean value.");
/*    */     } 
/* 30 */     throw new IllegalArgumentException("Invalid value type (" + value.getClass() + ") for property '" + prop + "': expected Boolean value.");
/*    */   }
/*    */   
/*    */   public static int convertToInt(String prop, Object value, int minValue) {
/*    */     int i;
/* 37 */     if (value == null) {
/* 38 */       i = 0;
/* 39 */     } else if (value instanceof Number) {
/* 40 */       i = ((Number)value).intValue();
/* 41 */     } else if (value instanceof String) {
/*    */       try {
/* 43 */         i = Integer.parseInt((String)value);
/* 44 */       } catch (NumberFormatException nex) {
/* 45 */         throw new IllegalArgumentException("Invalid String value for property '" + prop + "': expected a number (Integer).");
/*    */       } 
/*    */     } else {
/* 48 */       throw new IllegalArgumentException("Invalid value type (" + value.getClass() + ") for property '" + prop + "': expected Integer value.");
/*    */     } 
/* 51 */     if (i < minValue)
/* 52 */       throw new IllegalArgumentException("Invalid numeric value (" + i + ") for property '" + prop + "': minimum is " + minValue + "."); 
/* 56 */     return i;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\ArgUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */