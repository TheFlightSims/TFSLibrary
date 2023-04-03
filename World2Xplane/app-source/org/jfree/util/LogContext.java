/*     */ package org.jfree.util;
/*     */ 
/*     */ public class LogContext {
/*     */   private String contextPrefix;
/*     */   
/*     */   public LogContext(String contextPrefix) {
/*  61 */     this.contextPrefix = contextPrefix;
/*     */   }
/*     */   
/*     */   public boolean isDebugEnabled() {
/*  71 */     return Log.isDebugEnabled();
/*     */   }
/*     */   
/*     */   public boolean isInfoEnabled() {
/*  81 */     return Log.isInfoEnabled();
/*     */   }
/*     */   
/*     */   public boolean isWarningEnabled() {
/*  91 */     return Log.isWarningEnabled();
/*     */   }
/*     */   
/*     */   public boolean isErrorEnabled() {
/* 101 */     return Log.isErrorEnabled();
/*     */   }
/*     */   
/*     */   public void debug(Object message) {
/* 111 */     log(3, message);
/*     */   }
/*     */   
/*     */   public void debug(Object message, Exception e) {
/* 121 */     log(3, message, e);
/*     */   }
/*     */   
/*     */   public void info(Object message) {
/* 130 */     log(2, message);
/*     */   }
/*     */   
/*     */   public void info(Object message, Exception e) {
/* 140 */     log(2, message, e);
/*     */   }
/*     */   
/*     */   public void warn(Object message) {
/* 149 */     log(1, message);
/*     */   }
/*     */   
/*     */   public void warn(Object message, Exception e) {
/* 159 */     log(1, message, e);
/*     */   }
/*     */   
/*     */   public void error(Object message) {
/* 168 */     log(0, message);
/*     */   }
/*     */   
/*     */   public void error(Object message, Exception e) {
/* 178 */     log(0, message, e);
/*     */   }
/*     */   
/*     */   public void log(int level, Object message) {
/* 190 */     if (this.contextPrefix != null) {
/* 191 */       Log.getInstance().doLog(level, new Log.SimpleMessage(this.contextPrefix, ":", message));
/*     */     } else {
/* 194 */       Log.getInstance().doLog(level, message);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void log(int level, Object message, Exception e) {
/* 210 */     if (this.contextPrefix != null) {
/* 211 */       Log.getInstance().doLog(level, new Log.SimpleMessage(this.contextPrefix, ":", message), e);
/*     */     } else {
/* 216 */       Log.getInstance().doLog(level, message, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 228 */     if (this == o)
/* 229 */       return true; 
/* 231 */     if (!(o instanceof LogContext))
/* 232 */       return false; 
/* 235 */     LogContext logContext = (LogContext)o;
/* 237 */     if (this.contextPrefix != null) {
/* 239 */       if (!this.contextPrefix.equals(logContext.contextPrefix))
/* 240 */         return false; 
/* 244 */     } else if (logContext.contextPrefix != null) {
/* 245 */       return false;
/*     */     } 
/* 249 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 258 */     return (this.contextPrefix != null) ? this.contextPrefix.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\LogContext.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */