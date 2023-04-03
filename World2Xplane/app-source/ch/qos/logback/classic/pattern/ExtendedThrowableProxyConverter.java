/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.classic.spi.StackTraceElementProxy;
/*    */ import ch.qos.logback.classic.spi.ThrowableProxyUtil;
/*    */ 
/*    */ public class ExtendedThrowableProxyConverter extends ThrowableProxyConverter {
/*    */   protected void extraData(StringBuilder builder, StackTraceElementProxy step) {
/* 24 */     if (step != null)
/* 25 */       ThrowableProxyUtil.subjoinPackagingData(builder, step); 
/*    */   }
/*    */   
/*    */   protected void prepareLoggingEvent(ILoggingEvent event) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\ExtendedThrowableProxyConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */