/*    */ package ch.qos.logback.classic.util;
/*    */ 
/*    */ import ch.qos.logback.classic.Level;
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ 
/*    */ public class LevelToSyslogSeverity {
/*    */   public static int convert(ILoggingEvent event) {
/* 29 */     Level level = event.getLevel();
/* 31 */     switch (level.levelInt) {
/*    */       case 40000:
/* 33 */         return 3;
/*    */       case 30000:
/* 35 */         return 4;
/*    */       case 20000:
/* 37 */         return 6;
/*    */       case 5000:
/*    */       case 10000:
/* 40 */         return 7;
/*    */     } 
/* 42 */     throw new IllegalArgumentException("Level " + level + " is not a valid level for a printing method");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\LevelToSyslogSeverity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */