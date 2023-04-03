/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ final class BursaWolfInfo {
/*    */   final String operation;
/*    */   
/*    */   final int method;
/*    */   
/*    */   final String target;
/*    */   
/*    */   BursaWolfInfo(String operation, int method, String target) {
/* 34 */     this.operation = operation;
/* 35 */     this.method = method;
/* 36 */     this.target = target;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return this.operation;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\BursaWolfInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */