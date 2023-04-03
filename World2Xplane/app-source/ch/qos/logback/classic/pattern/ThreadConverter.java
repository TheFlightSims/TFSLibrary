/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class ThreadConverter extends ClassicConverter {
/*    */   public String convert(ILoggingEvent event) {
/* 26 */     return event.getThreadName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\ThreadConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */