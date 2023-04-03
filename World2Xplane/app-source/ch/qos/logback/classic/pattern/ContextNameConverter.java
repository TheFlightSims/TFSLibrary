/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class ContextNameConverter extends ClassicConverter {
/*    */   public String convert(ILoggingEvent event) {
/* 29 */     return event.getLoggerContextVO().getName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\ContextNameConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */