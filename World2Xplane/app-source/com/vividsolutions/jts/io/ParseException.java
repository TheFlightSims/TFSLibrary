/*    */ package com.vividsolutions.jts.io;
/*    */ 
/*    */ public class ParseException extends Exception {
/*    */   public ParseException(String message) {
/* 50 */     super(message);
/*    */   }
/*    */   
/*    */   public ParseException(Exception e) {
/* 60 */     this(e.toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\ParseException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */