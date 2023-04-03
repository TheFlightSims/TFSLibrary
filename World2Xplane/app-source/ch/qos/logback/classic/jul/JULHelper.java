/*    */ package ch.qos.logback.classic.jul;
/*    */ 
/*    */ import ch.qos.logback.classic.Level;
/*    */ import ch.qos.logback.classic.Logger;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class JULHelper {
/*    */   public static final boolean isRegularNonRootLogger(Logger julLogger) {
/* 23 */     if (julLogger == null)
/* 24 */       return false; 
/* 25 */     return !julLogger.getName().equals("");
/*    */   }
/*    */   
/*    */   public static final boolean isRoot(Logger julLogger) {
/* 29 */     if (julLogger == null)
/* 30 */       return false; 
/* 31 */     return julLogger.getName().equals("");
/*    */   }
/*    */   
/*    */   public static Level asJULLevel(Level lbLevel) {
/* 35 */     switch (lbLevel.levelInt) {
/*    */       case -2147483648:
/* 37 */         return Level.ALL;
/*    */       case 5000:
/* 39 */         return Level.FINEST;
/*    */       case 10000:
/* 41 */         return Level.FINE;
/*    */       case 20000:
/* 43 */         return Level.INFO;
/*    */       case 30000:
/* 45 */         return Level.WARNING;
/*    */       case 40000:
/* 47 */         return Level.SEVERE;
/*    */       case 2147483647:
/* 49 */         return Level.OFF;
/*    */     } 
/* 51 */     throw new IllegalArgumentException("Unexpected level [" + lbLevel + "]");
/*    */   }
/*    */   
/*    */   public static String asJULLoggerName(String loggerName) {
/* 56 */     if ("ROOT".equals(loggerName))
/* 57 */       return ""; 
/* 59 */     return loggerName;
/*    */   }
/*    */   
/*    */   public static Logger asJULLogger(String loggerName) {
/* 63 */     String julLoggerName = asJULLoggerName(loggerName);
/* 64 */     return Logger.getLogger(julLoggerName);
/*    */   }
/*    */   
/*    */   public static Logger asJULLogger(Logger logger) {
/* 68 */     return asJULLogger(logger.getName());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\jul\JULHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */