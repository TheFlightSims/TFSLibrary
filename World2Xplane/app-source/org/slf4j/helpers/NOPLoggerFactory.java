/*    */ package org.slf4j.helpers;
/*    */ 
/*    */ import org.slf4j.ILoggerFactory;
/*    */ import org.slf4j.Logger;
/*    */ 
/*    */ public class NOPLoggerFactory implements ILoggerFactory {
/*    */   public Logger getLogger(String name) {
/* 55 */     return NOPLogger.NOP_LOGGER;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\helpers\NOPLoggerFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */