/*    */ package org.openstreetmap.osmosis.core;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ public final class LogLevels {
/*    */   public static final int DEFAULT_LEVEL_INDEX = 3;
/*    */   
/* 29 */   public static final Level[] LOG_LEVELS = new Level[] { Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO, Level.FINE, Level.FINER, Level.FINEST };
/*    */   
/*    */   public static Level getLogLevel(int logLevelIndex) {
/* 50 */     if (logLevelIndex < 0)
/* 51 */       logLevelIndex = 0; 
/* 53 */     if (logLevelIndex >= LOG_LEVELS.length)
/* 54 */       logLevelIndex = LOG_LEVELS.length - 1; 
/* 57 */     return LOG_LEVELS[logLevelIndex];
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\LogLevels.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */