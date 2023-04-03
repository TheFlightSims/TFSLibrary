/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class LoggerConverter extends NamedConverter {
/*    */   protected String getFullyQualifiedName(ILoggingEvent event) {
/* 21 */     return event.getLoggerName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\LoggerConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */