/*     */ package org.geotools.util.logging;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.log4j.Priority;
/*     */ 
/*     */ final class Log4JLogger extends LoggerAdapter {
/*     */   final Logger logger;
/*     */   
/*     */   public Log4JLogger(String name, Logger logger) {
/*  48 */     super(name);
/*  49 */     this.logger = logger;
/*     */   }
/*     */   
/*     */   private static Level toLog4JLevel(Level level) {
/*  57 */     int n = level.intValue();
/*  58 */     switch (n / 100) {
/*     */       default:
/*  62 */         switch (n) {
/*     */           default:
/*  63 */             if (n >= 0)
/*  63 */               return Level.FATAL; 
/*     */           case -2147483648:
/*  64 */             return Level.ALL;
/*     */           case 2147483647:
/*     */             break;
/*     */         } 
/*  65 */         return Level.OFF;
/*     */       case 10:
/*  68 */         return Level.ERROR;
/*     */       case 9:
/*  69 */         return Level.WARN;
/*     */       case 7:
/*     */       case 8:
/*  71 */         return Level.INFO;
/*     */       case 5:
/*     */       case 6:
/*  73 */         return Level.DEBUG;
/*     */       case 4:
/*  74 */         return Level.TRACE;
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */         break;
/*     */     } 
/*  78 */     return Level.ALL;
/*     */   }
/*     */   
/*     */   private static Level toJavaLevel(Level level) {
/*  86 */     int n = level.toInt();
/*  87 */     if (n == Integer.MAX_VALUE)
/*  87 */       return Level.OFF; 
/*  88 */     if (n >= 40000)
/*  88 */       return Level.SEVERE; 
/*  89 */     if (n >= 30000)
/*  89 */       return Level.WARNING; 
/*  90 */     if (n >= 20000)
/*  90 */       return Level.CONFIG; 
/*  91 */     if (n >= 10000)
/*  91 */       return Level.FINE; 
/*  92 */     if (n >= 5000)
/*  92 */       return Level.FINER; 
/*  93 */     return Level.ALL;
/*     */   }
/*     */   
/*     */   public void setLevel(Level level) {
/* 100 */     this.logger.setLevel(toLog4JLevel(level));
/*     */   }
/*     */   
/*     */   public Level getLevel() {
/* 107 */     return toJavaLevel(this.logger.getEffectiveLevel());
/*     */   }
/*     */   
/*     */   public boolean isLoggable(Level level) {
/* 114 */     return this.logger.isEnabledFor((Priority)toLog4JLevel(level));
/*     */   }
/*     */   
/*     */   public void log(Level level, String message) {
/* 122 */     this.logger.log((Priority)toLog4JLevel(level), message);
/*     */   }
/*     */   
/*     */   public void log(Level level, String message, Throwable thrown) {
/* 130 */     this.logger.log((Priority)toLog4JLevel(level), message, thrown);
/*     */   }
/*     */   
/*     */   public void severe(String message) {
/* 133 */     this.logger.error(message);
/*     */   }
/*     */   
/*     */   public void warning(String message) {
/* 134 */     this.logger.warn(message);
/*     */   }
/*     */   
/*     */   public void info(String message) {
/* 135 */     this.logger.info(message);
/*     */   }
/*     */   
/*     */   public void config(String message) {
/* 136 */     this.logger.info(message);
/*     */   }
/*     */   
/*     */   public void fine(String message) {
/* 137 */     this.logger.debug(message);
/*     */   }
/*     */   
/*     */   public void finer(String message) {
/* 138 */     this.logger.debug(message);
/*     */   }
/*     */   
/*     */   public void finest(String message) {
/* 139 */     this.logger.trace(message);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\Log4JLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */