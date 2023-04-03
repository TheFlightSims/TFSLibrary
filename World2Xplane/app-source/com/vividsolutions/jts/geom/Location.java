/*    */ package com.vividsolutions.jts.geom;
/*    */ 
/*    */ public class Location {
/*    */   public static final int INTERIOR = 0;
/*    */   
/*    */   public static final int BOUNDARY = 1;
/*    */   
/*    */   public static final int EXTERIOR = 2;
/*    */   
/*    */   public static final int NONE = -1;
/*    */   
/*    */   public static char toLocationSymbol(int locationValue) {
/* 78 */     switch (locationValue) {
/*    */       case 2:
/* 80 */         return 'e';
/*    */       case 1:
/* 82 */         return 'b';
/*    */       case 0:
/* 84 */         return 'i';
/*    */       case -1:
/* 86 */         return '-';
/*    */     } 
/* 88 */     throw new IllegalArgumentException("Unknown location value: " + locationValue);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Location.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */