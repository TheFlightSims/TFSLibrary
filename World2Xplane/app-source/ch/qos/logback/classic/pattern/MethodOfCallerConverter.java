/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class MethodOfCallerConverter extends ClassicConverter {
/*    */   public String convert(ILoggingEvent le) {
/* 22 */     StackTraceElement[] cda = le.getCallerData();
/* 23 */     if (cda != null && cda.length > 0)
/* 24 */       return cda[0].getMethodName(); 
/* 26 */     return "?";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\MethodOfCallerConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */