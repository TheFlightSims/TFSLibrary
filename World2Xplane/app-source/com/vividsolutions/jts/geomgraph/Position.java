/*    */ package com.vividsolutions.jts.geomgraph;
/*    */ 
/*    */ public class Position {
/*    */   public static final int ON = 0;
/*    */   
/*    */   public static final int LEFT = 1;
/*    */   
/*    */   public static final int RIGHT = 2;
/*    */   
/*    */   public static final int opposite(int position) {
/* 57 */     if (position == 1)
/* 57 */       return 2; 
/* 58 */     if (position == 2)
/* 58 */       return 1; 
/* 59 */     return position;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\Position.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */