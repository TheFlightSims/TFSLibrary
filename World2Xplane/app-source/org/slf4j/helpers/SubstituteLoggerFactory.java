/*    */ package org.slf4j.helpers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.slf4j.ILoggerFactory;
/*    */ import org.slf4j.Logger;
/*    */ 
/*    */ public class SubstituteLoggerFactory implements ILoggerFactory {
/* 56 */   final List loggerNameList = new ArrayList();
/*    */   
/*    */   public Logger getLogger(String name) {
/* 59 */     synchronized (this.loggerNameList) {
/* 60 */       this.loggerNameList.add(name);
/*    */     } 
/* 62 */     return NOPLogger.NOP_LOGGER;
/*    */   }
/*    */   
/*    */   public List getLoggerNameList() {
/* 66 */     List copy = new ArrayList();
/* 67 */     synchronized (this.loggerNameList) {
/* 68 */       copy.addAll(this.loggerNameList);
/*    */     } 
/* 70 */     return copy;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\helpers\SubstituteLoggerFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */