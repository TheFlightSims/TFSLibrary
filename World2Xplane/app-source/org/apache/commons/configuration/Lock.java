/*    */ package org.apache.commons.configuration;
/*    */ 
/*    */ public class Lock {
/* 37 */   private static String counterLock = "Lock";
/*    */   
/*    */   private static int counter;
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private final int instanceId;
/*    */   
/*    */   public Lock(String name) {
/* 55 */     this.name = name;
/* 56 */     synchronized (counterLock) {
/* 58 */       this.instanceId = ++counter;
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getName() {
/* 69 */     return this.name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return "Lock: " + this.name + " id = " + this.instanceId + ": " + super.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\Lock.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */