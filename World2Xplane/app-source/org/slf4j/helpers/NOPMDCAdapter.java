/*    */ package org.slf4j.helpers;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.slf4j.spi.MDCAdapter;
/*    */ 
/*    */ public class NOPMDCAdapter implements MDCAdapter {
/*    */   public void clear() {}
/*    */   
/*    */   public String get(String key) {
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public void put(String key, String val) {}
/*    */   
/*    */   public void remove(String key) {}
/*    */   
/*    */   public Map getCopyOfContextMap() {
/* 32 */     return null;
/*    */   }
/*    */   
/*    */   public void setContextMap(Map contextMap) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\helpers\NOPMDCAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */