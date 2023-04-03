/*      */ package akka.event;
/*      */ 
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.immutable.Map;
/*      */ 
/*      */ public final class NoLogging$ implements LoggingAdapter {
/*      */   public static final NoLogging$ MODULE$;
/*      */   
/*      */   public Map<String, Object> mdc() {
/* 1147 */     return LoggingAdapter$class.mdc(this);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String message) {
/* 1147 */     LoggingAdapter$class.error(this, cause, message);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1) {
/* 1147 */     LoggingAdapter$class.error(this, cause, template, arg1);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1, Object arg2) {
/* 1147 */     LoggingAdapter$class.error(this, cause, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1, Object arg2, Object arg3) {
/* 1147 */     LoggingAdapter$class.error(this, cause, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void error(Throwable cause, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1147 */     LoggingAdapter$class.error(this, cause, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void error(String message) {
/* 1147 */     LoggingAdapter$class.error(this, message);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1) {
/* 1147 */     LoggingAdapter$class.error(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1, Object arg2) {
/* 1147 */     LoggingAdapter$class.error(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1, Object arg2, Object arg3) {
/* 1147 */     LoggingAdapter$class.error(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void error(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1147 */     LoggingAdapter$class.error(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void warning(String message) {
/* 1147 */     LoggingAdapter$class.warning(this, message);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1) {
/* 1147 */     LoggingAdapter$class.warning(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1, Object arg2) {
/* 1147 */     LoggingAdapter$class.warning(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1, Object arg2, Object arg3) {
/* 1147 */     LoggingAdapter$class.warning(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void warning(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1147 */     LoggingAdapter$class.warning(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void info(String message) {
/* 1147 */     LoggingAdapter$class.info(this, message);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1) {
/* 1147 */     LoggingAdapter$class.info(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1, Object arg2) {
/* 1147 */     LoggingAdapter$class.info(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1, Object arg2, Object arg3) {
/* 1147 */     LoggingAdapter$class.info(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void info(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1147 */     LoggingAdapter$class.info(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void debug(String message) {
/* 1147 */     LoggingAdapter$class.debug(this, message);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1) {
/* 1147 */     LoggingAdapter$class.debug(this, template, arg1);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1, Object arg2) {
/* 1147 */     LoggingAdapter$class.debug(this, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1, Object arg2, Object arg3) {
/* 1147 */     LoggingAdapter$class.debug(this, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void debug(String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1147 */     LoggingAdapter$class.debug(this, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public void log(int level, String message) {
/* 1147 */     LoggingAdapter$class.log(this, level, message);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1) {
/* 1147 */     LoggingAdapter$class.log(this, level, template, arg1);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1, Object arg2) {
/* 1147 */     LoggingAdapter$class.log(this, level, template, arg1, arg2);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1, Object arg2, Object arg3) {
/* 1147 */     LoggingAdapter$class.log(this, level, template, arg1, arg2, arg3);
/*      */   }
/*      */   
/*      */   public void log(int level, String template, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 1147 */     LoggingAdapter$class.log(this, level, template, arg1, arg2, arg3, arg4);
/*      */   }
/*      */   
/*      */   public final boolean isEnabled(int level) {
/* 1147 */     return LoggingAdapter$class.isEnabled(this, level);
/*      */   }
/*      */   
/*      */   public final void notifyLog(int level, String message) {
/* 1147 */     LoggingAdapter$class.notifyLog(this, level, message);
/*      */   }
/*      */   
/*      */   public String format(String t, Seq arg) {
/* 1147 */     return LoggingAdapter$class.format(this, t, arg);
/*      */   }
/*      */   
/*      */   private NoLogging$() {
/* 1147 */     MODULE$ = this;
/* 1147 */     LoggingAdapter$class.$init$(this);
/*      */   }
/*      */   
/*      */   public NoLogging$ getInstance() {
/* 1153 */     return this;
/*      */   }
/*      */   
/*      */   public final boolean isErrorEnabled() {
/* 1155 */     return false;
/*      */   }
/*      */   
/*      */   public final boolean isWarningEnabled() {
/* 1156 */     return false;
/*      */   }
/*      */   
/*      */   public final boolean isInfoEnabled() {
/* 1157 */     return false;
/*      */   }
/*      */   
/*      */   public final boolean isDebugEnabled() {
/* 1158 */     return false;
/*      */   }
/*      */   
/*      */   public final void notifyError(String message) {}
/*      */   
/*      */   public final void notifyError(Throwable cause, String message) {}
/*      */   
/*      */   public final void notifyWarning(String message) {}
/*      */   
/*      */   public final void notifyInfo(String message) {}
/*      */   
/*      */   public final void notifyDebug(String message) {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\NoLogging$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */