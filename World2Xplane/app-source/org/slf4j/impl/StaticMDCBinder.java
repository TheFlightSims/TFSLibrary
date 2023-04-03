/*    */ package org.slf4j.impl;
/*    */ 
/*    */ import ch.qos.logback.classic.util.LogbackMDCAdapter;
/*    */ import org.slf4j.spi.MDCAdapter;
/*    */ 
/*    */ public class StaticMDCBinder {
/* 32 */   public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();
/*    */   
/*    */   public MDCAdapter getMDCA() {
/* 42 */     return (MDCAdapter)new LogbackMDCAdapter();
/*    */   }
/*    */   
/*    */   public String getMDCAdapterClassStr() {
/* 46 */     return LogbackMDCAdapter.class.getName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\impl\StaticMDCBinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */