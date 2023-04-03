/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.core.CoreConstants;
/*    */ 
/*    */ public class LineSeparatorConverter extends ClassicConverter {
/*    */   public String convert(ILoggingEvent event) {
/* 22 */     return CoreConstants.LINE_SEPARATOR;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\LineSeparatorConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */