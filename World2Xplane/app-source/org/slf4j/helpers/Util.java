/*    */ package org.slf4j.helpers;
/*    */ 
/*    */ public class Util {
/*    */   public static final void report(String msg, Throwable t) {
/* 37 */     System.err.println(msg);
/* 38 */     System.err.println("Reported exception:");
/* 39 */     t.printStackTrace();
/*    */   }
/*    */   
/*    */   public static final void report(String msg) {
/* 43 */     System.err.println("SLF4J: " + msg);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\helpers\Util.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */