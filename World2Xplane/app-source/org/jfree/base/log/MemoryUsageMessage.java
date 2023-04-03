/*    */ package org.jfree.base.log;
/*    */ 
/*    */ public class MemoryUsageMessage {
/*    */   private final String message;
/*    */   
/*    */   public MemoryUsageMessage(String message) {
/* 61 */     this.message = message;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 70 */     return this.message + "Free: " + Runtime.getRuntime().freeMemory() + "; " + "Total: " + Runtime.getRuntime().totalMemory();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\log\MemoryUsageMessage.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */