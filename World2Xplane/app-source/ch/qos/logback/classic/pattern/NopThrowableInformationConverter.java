/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class NopThrowableInformationConverter extends ThrowableHandlingConverter {
/*    */   public String convert(ILoggingEvent event) {
/* 40 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\NopThrowableInformationConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */