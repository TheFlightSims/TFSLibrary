/*     */ package ch.qos.logback.classic.net;
/*     */ 
/*     */ import ch.qos.logback.classic.PatternLayout;
/*     */ import ch.qos.logback.classic.pattern.SyslogStartConverter;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.classic.spi.IThrowableProxy;
/*     */ import ch.qos.logback.classic.spi.StackTraceElementProxy;
/*     */ import ch.qos.logback.classic.util.LevelToSyslogSeverity;
/*     */ import ch.qos.logback.core.Layout;
/*     */ import ch.qos.logback.core.net.SyslogAppenderBase;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ public class SyslogAppender extends SyslogAppenderBase<ILoggingEvent> {
/*     */   public static final String DEFAULT_SUFFIX_PATTERN = "[%thread] %logger %msg";
/*     */   
/*     */   public static final String DEFAULT_STACKTRACE_PATTERN = "\t";
/*     */   
/*  41 */   PatternLayout stackTraceLayout = new PatternLayout();
/*     */   
/*  42 */   String stackTracePattern = "\t";
/*     */   
/*     */   boolean throwableExcluded = false;
/*     */   
/*     */   public void start() {
/*  48 */     super.start();
/*  49 */     setupStackTraceLayout();
/*     */   }
/*     */   
/*     */   String getPrefixPattern() {
/*  53 */     return "%syslogStart{" + getFacility() + "}%nopex{}";
/*     */   }
/*     */   
/*     */   public int getSeverityForEvent(Object eventObject) {
/*  64 */     ILoggingEvent event = (ILoggingEvent)eventObject;
/*  65 */     return LevelToSyslogSeverity.convert(event);
/*     */   }
/*     */   
/*     */   protected void postProcess(Object eventObject, OutputStream sw) {
/*  70 */     if (this.throwableExcluded)
/*     */       return; 
/*  73 */     ILoggingEvent event = (ILoggingEvent)eventObject;
/*  74 */     IThrowableProxy tp = event.getThrowableProxy();
/*  76 */     if (tp == null)
/*     */       return; 
/*  79 */     String stackTracePrefix = this.stackTraceLayout.doLayout(event);
/*  80 */     boolean isRootException = true;
/*  81 */     while (tp != null) {
/*  82 */       StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
/*     */       try {
/*  84 */         handleThrowableFirstLine(sw, tp, stackTracePrefix, isRootException);
/*  85 */         isRootException = false;
/*  86 */         for (StackTraceElementProxy step : stepArray) {
/*  87 */           StringBuilder sb = new StringBuilder();
/*  88 */           sb.append(stackTracePrefix).append(step);
/*  89 */           sw.write(sb.toString().getBytes());
/*  90 */           sw.flush();
/*     */         } 
/*  92 */       } catch (IOException e) {
/*     */         break;
/*     */       } 
/*  95 */       tp = tp.getCause();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void handleThrowableFirstLine(OutputStream sw, IThrowableProxy tp, String stackTracePrefix, boolean isRootException) throws IOException {
/* 101 */     StringBuilder sb = (new StringBuilder()).append(stackTracePrefix);
/* 103 */     if (!isRootException)
/* 104 */       sb.append("Caused by: "); 
/* 106 */     sb.append(tp.getClassName()).append(": ").append(tp.getMessage());
/* 107 */     sw.write(sb.toString().getBytes());
/* 108 */     sw.flush();
/*     */   }
/*     */   
/*     */   boolean stackTraceHeaderLine(StringBuilder sb, boolean topException) {
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public Layout<ILoggingEvent> buildLayout() {
/* 117 */     PatternLayout layout = new PatternLayout();
/* 118 */     layout.getInstanceConverterMap().put("syslogStart", SyslogStartConverter.class.getName());
/* 120 */     if (this.suffixPattern == null)
/* 121 */       this.suffixPattern = "[%thread] %logger %msg"; 
/* 123 */     layout.setPattern(getPrefixPattern() + this.suffixPattern);
/* 124 */     layout.setContext(getContext());
/* 125 */     layout.start();
/* 126 */     return (Layout<ILoggingEvent>)layout;
/*     */   }
/*     */   
/*     */   private void setupStackTraceLayout() {
/* 130 */     this.stackTraceLayout.getInstanceConverterMap().put("syslogStart", SyslogStartConverter.class.getName());
/* 133 */     this.stackTraceLayout.setPattern(getPrefixPattern() + this.stackTracePattern);
/* 134 */     this.stackTraceLayout.setContext(getContext());
/* 135 */     this.stackTraceLayout.start();
/*     */   }
/*     */   
/*     */   public boolean isThrowableExcluded() {
/* 139 */     return this.throwableExcluded;
/*     */   }
/*     */   
/*     */   public void setThrowableExcluded(boolean throwableExcluded) {
/* 150 */     this.throwableExcluded = throwableExcluded;
/*     */   }
/*     */   
/*     */   public String getStackTracePattern() {
/* 160 */     return this.stackTracePattern;
/*     */   }
/*     */   
/*     */   public void setStackTracePattern(String stackTracePattern) {
/* 173 */     this.stackTracePattern = stackTracePattern;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\SyslogAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */