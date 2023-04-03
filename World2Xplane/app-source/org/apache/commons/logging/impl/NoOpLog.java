/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class NoOpLog implements Log, Serializable {
/*     */   public NoOpLog() {}
/*     */   
/*     */   public NoOpLog(String name) {}
/*     */   
/*     */   public void trace(Object message) {}
/*     */   
/*     */   public void trace(Object message, Throwable t) {}
/*     */   
/*     */   public void debug(Object message) {}
/*     */   
/*     */   public void debug(Object message, Throwable t) {}
/*     */   
/*     */   public void info(Object message) {}
/*     */   
/*     */   public void info(Object message, Throwable t) {}
/*     */   
/*     */   public void warn(Object message) {}
/*     */   
/*     */   public void warn(Object message, Throwable t) {}
/*     */   
/*     */   public void error(Object message) {}
/*     */   
/*     */   public void error(Object message, Throwable t) {}
/*     */   
/*     */   public void fatal(Object message) {}
/*     */   
/*     */   public void fatal(Object message, Throwable t) {}
/*     */   
/*     */   public final boolean isDebugEnabled() {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean isErrorEnabled() {
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean isFatalEnabled() {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean isInfoEnabled() {
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean isTraceEnabled() {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean isWarnEnabled() {
/* 105 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\logging\impl\NoOpLog.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */