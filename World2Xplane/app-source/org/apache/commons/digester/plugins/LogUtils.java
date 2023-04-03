/*    */ package org.apache.commons.digester.plugins;
/*    */ 
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.impl.NoOpLog;
/*    */ 
/*    */ class LogUtils {
/*    */   static Log getLogger(Digester digester) {
/* 67 */     if (digester == null)
/* 68 */       return (Log)new NoOpLog(); 
/* 71 */     return digester.getLogger();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\LogUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */