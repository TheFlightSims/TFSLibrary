/*    */ package ch.qos.logback.classic.pattern;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class LevelConverter extends ClassicConverter {
/*    */   public String convert(ILoggingEvent le) {
/* 26 */     return le.getLevel().toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\pattern\LevelConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */