/*     */ package org.geotools.util.logging;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ final class CommonsLogger extends LoggerAdapter {
/*     */   final Log logger;
/*     */   
/*     */   public CommonsLogger(String name, Log logger) {
/*  49 */     super(name);
/*  50 */     this.logger = logger;
/*     */   }
/*     */   
/*     */   public void setLevel(Level level) {}
/*     */   
/*     */   public Level getLevel() {
/*  63 */     if (this.logger.isTraceEnabled())
/*  63 */       return Level.FINEST; 
/*  64 */     if (this.logger.isDebugEnabled())
/*  64 */       return Level.FINE; 
/*  65 */     if (this.logger.isInfoEnabled())
/*  65 */       return Level.CONFIG; 
/*  66 */     if (this.logger.isWarnEnabled())
/*  66 */       return Level.WARNING; 
/*  67 */     if (this.logger.isErrorEnabled())
/*  67 */       return Level.SEVERE; 
/*  68 */     if (this.logger.isFatalEnabled())
/*  68 */       return Level.SEVERE; 
/*  69 */     return Level.OFF;
/*     */   }
/*     */   
/*     */   public boolean isLoggable(Level level) {
/*  77 */     int n = level.intValue();
/*  78 */     switch (n / 100) {
/*     */       default:
/*  80 */         switch (n) {
/*     */           case -2147483648:
/*  81 */             return true;
/*     */           case 2147483647:
/*  82 */             return false;
/*     */         } 
/*  83 */         return (n >= 0 && this.logger.isFatalEnabled());
/*     */       case 10:
/*  86 */         return this.logger.isErrorEnabled();
/*     */       case 9:
/*  87 */         return this.logger.isWarnEnabled();
/*     */       case 7:
/*     */       case 8:
/*  89 */         return this.logger.isInfoEnabled();
/*     */       case 5:
/*     */       case 6:
/*  91 */         return this.logger.isDebugEnabled();
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */         break;
/*     */     } 
/*  96 */     return this.logger.isTraceEnabled();
/*     */   }
/*     */   
/*     */   public void log(Level level, String message, Throwable thrown) {
/* 106 */     int n = level.intValue();
/* 107 */     switch (n / 100) {
/*     */       default:
/* 111 */         if (n != Integer.MAX_VALUE || n >= 0)
/* 112 */           this.logger.fatal(message, thrown); 
/*     */         return;
/*     */       case 10:
/* 116 */         this.logger.error(message, thrown);
/*     */         return;
/*     */       case 9:
/* 117 */         this.logger.warn(message, thrown);
/*     */         return;
/*     */       case 7:
/*     */       case 8:
/* 119 */         this.logger.info(message, thrown);
/*     */         return;
/*     */       case 5:
/*     */       case 6:
/* 121 */         this.logger.debug(message, thrown);
/*     */         return;
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */         break;
/*     */     } 
/* 126 */     this.logger.trace(message, thrown);
/*     */   }
/*     */   
/*     */   public void severe(String message) {
/* 130 */     this.logger.error(message);
/*     */   }
/*     */   
/*     */   public void warning(String message) {
/* 131 */     this.logger.warn(message);
/*     */   }
/*     */   
/*     */   public void info(String message) {
/* 132 */     this.logger.info(message);
/*     */   }
/*     */   
/*     */   public void config(String message) {
/* 133 */     this.logger.info(message);
/*     */   }
/*     */   
/*     */   public void fine(String message) {
/* 134 */     this.logger.debug(message);
/*     */   }
/*     */   
/*     */   public void finer(String message) {
/* 135 */     this.logger.debug(message);
/*     */   }
/*     */   
/*     */   public void finest(String message) {
/* 136 */     this.logger.trace(message);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\CommonsLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */