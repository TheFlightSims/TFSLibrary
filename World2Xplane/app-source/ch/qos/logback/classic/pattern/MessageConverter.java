/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class MessageConverter extends ClassicConverter {
/*    */   public String convert(ILoggingEvent event) {
/* 26 */     return event.getFormattedMessage();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\MessageConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */