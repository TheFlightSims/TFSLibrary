/*     */ package ch.qos.logback.classic.spi;
/*     */ 
/*     */ import ch.qos.logback.classic.Level;
/*     */ import ch.qos.logback.classic.Logger;
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.util.LogbackMDCAdapter;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.slf4j.MDC;
/*     */ import org.slf4j.Marker;
/*     */ import org.slf4j.helpers.FormattingTuple;
/*     */ import org.slf4j.helpers.MessageFormatter;
/*     */ import org.slf4j.spi.MDCAdapter;
/*     */ 
/*     */ public class LoggingEvent implements ILoggingEvent {
/*     */   transient String fqnOfLoggerClass;
/*     */   
/*     */   private String threadName;
/*     */   
/*     */   private String loggerName;
/*     */   
/*     */   private LoggerContext loggerContext;
/*     */   
/*     */   private LoggerContextVO loggerContextVO;
/*     */   
/*     */   private transient Level level;
/*     */   
/*     */   private String message;
/*     */   
/*     */   private transient String formattedMessage;
/*     */   
/*     */   private transient Object[] argumentArray;
/*     */   
/*     */   private ThrowableProxy throwableProxy;
/*     */   
/*     */   private StackTraceElement[] callerDataArray;
/*     */   
/*     */   private Marker marker;
/*     */   
/*     */   private Map<String, String> mdcPropertyMap;
/*     */   
/*  94 */   private static final Map<String, String> CACHED_NULL_MAP = new HashMap<String, String>();
/*     */   
/*     */   private long timeStamp;
/*     */   
/*     */   public LoggingEvent() {}
/*     */   
/*     */   public LoggingEvent(String fqcn, Logger logger, Level level, String message, Throwable throwable, Object[] argArray) {
/* 107 */     this.fqnOfLoggerClass = fqcn;
/* 108 */     this.loggerName = logger.getName();
/* 109 */     this.loggerContext = logger.getLoggerContext();
/* 110 */     this.loggerContextVO = this.loggerContext.getLoggerContextRemoteView();
/* 111 */     this.level = level;
/* 113 */     this.message = message;
/* 115 */     FormattingTuple ft = MessageFormatter.arrayFormat(message, argArray);
/* 116 */     this.formattedMessage = ft.getMessage();
/* 118 */     if (throwable == null) {
/* 119 */       this.argumentArray = ft.getArgArray();
/* 120 */       throwable = ft.getThrowable();
/*     */     } else {
/* 122 */       this.argumentArray = argArray;
/*     */     } 
/* 125 */     if (throwable != null) {
/* 126 */       this.throwableProxy = new ThrowableProxy(throwable);
/* 127 */       LoggerContext lc = logger.getLoggerContext();
/* 128 */       if (lc.isPackagingDataEnabled())
/* 129 */         this.throwableProxy.calculatePackagingData(); 
/*     */     } 
/* 133 */     this.timeStamp = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void setArgumentArray(Object[] argArray) {
/* 137 */     if (this.argumentArray != null)
/* 138 */       throw new IllegalStateException("argArray has been already set"); 
/* 140 */     this.argumentArray = argArray;
/*     */   }
/*     */   
/*     */   public Object[] getArgumentArray() {
/* 144 */     return this.argumentArray;
/*     */   }
/*     */   
/*     */   public Level getLevel() {
/* 148 */     return this.level;
/*     */   }
/*     */   
/*     */   public String getLoggerName() {
/* 152 */     return this.loggerName;
/*     */   }
/*     */   
/*     */   public void setLoggerName(String loggerName) {
/* 156 */     this.loggerName = loggerName;
/*     */   }
/*     */   
/*     */   public String getThreadName() {
/* 160 */     if (this.threadName == null)
/* 161 */       this.threadName = Thread.currentThread().getName(); 
/* 163 */     return this.threadName;
/*     */   }
/*     */   
/*     */   public void setThreadName(String threadName) throws IllegalStateException {
/* 171 */     if (this.threadName != null)
/* 172 */       throw new IllegalStateException("threadName has been already set"); 
/* 174 */     this.threadName = threadName;
/*     */   }
/*     */   
/*     */   public IThrowableProxy getThrowableProxy() {
/* 182 */     return this.throwableProxy;
/*     */   }
/*     */   
/*     */   public void setThrowableProxy(ThrowableProxy tp) {
/* 189 */     if (this.throwableProxy != null)
/* 190 */       throw new IllegalStateException("ThrowableProxy has been already set."); 
/* 192 */     this.throwableProxy = tp;
/*     */   }
/*     */   
/*     */   public void prepareForDeferredProcessing() {
/* 205 */     getFormattedMessage();
/* 206 */     getThreadName();
/* 208 */     getMDCPropertyMap();
/*     */   }
/*     */   
/*     */   public LoggerContextVO getLoggerContextVO() {
/* 212 */     return this.loggerContextVO;
/*     */   }
/*     */   
/*     */   public void setLoggerContextRemoteView(LoggerContextVO loggerContextVO) {
/* 216 */     this.loggerContextVO = loggerContextVO;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 220 */     return this.message;
/*     */   }
/*     */   
/*     */   public void setMessage(String message) {
/* 224 */     if (this.message != null)
/* 225 */       throw new IllegalStateException("The message for this event has been set already."); 
/* 228 */     this.message = message;
/*     */   }
/*     */   
/*     */   public long getTimeStamp() {
/* 232 */     return this.timeStamp;
/*     */   }
/*     */   
/*     */   public void setTimeStamp(long timeStamp) {
/* 236 */     this.timeStamp = timeStamp;
/*     */   }
/*     */   
/*     */   public void setLevel(Level level) {
/* 240 */     if (this.level != null)
/* 241 */       throw new IllegalStateException("The level has been already set for this event."); 
/* 244 */     this.level = level;
/*     */   }
/*     */   
/*     */   public StackTraceElement[] getCallerData() {
/* 258 */     if (this.callerDataArray == null)
/* 259 */       this.callerDataArray = CallerData.extract(new Throwable(), this.fqnOfLoggerClass, this.loggerContext.getMaxCallerDataDepth(), this.loggerContext.getFrameworkPackages()); 
/* 262 */     return this.callerDataArray;
/*     */   }
/*     */   
/*     */   public boolean hasCallerData() {
/* 266 */     return (this.callerDataArray != null);
/*     */   }
/*     */   
/*     */   public void setCallerData(StackTraceElement[] callerDataArray) {
/* 270 */     this.callerDataArray = callerDataArray;
/*     */   }
/*     */   
/*     */   public Marker getMarker() {
/* 274 */     return this.marker;
/*     */   }
/*     */   
/*     */   public void setMarker(Marker marker) {
/* 278 */     if (this.marker != null)
/* 279 */       throw new IllegalStateException("The marker has been already set for this event."); 
/* 282 */     this.marker = marker;
/*     */   }
/*     */   
/*     */   public long getContextBirthTime() {
/* 286 */     return this.loggerContextVO.getBirthTime();
/*     */   }
/*     */   
/*     */   public String getFormattedMessage() {
/* 292 */     if (this.formattedMessage != null)
/* 293 */       return this.formattedMessage; 
/* 295 */     if (this.argumentArray != null) {
/* 296 */       this.formattedMessage = MessageFormatter.arrayFormat(this.message, this.argumentArray).getMessage();
/*     */     } else {
/* 299 */       this.formattedMessage = this.message;
/*     */     } 
/* 302 */     return this.formattedMessage;
/*     */   }
/*     */   
/*     */   public Map<String, String> getMDCPropertyMap() {
/* 307 */     if (this.mdcPropertyMap == null) {
/* 308 */       MDCAdapter mdc = MDC.getMDCAdapter();
/* 309 */       if (mdc instanceof LogbackMDCAdapter) {
/* 310 */         this.mdcPropertyMap = ((LogbackMDCAdapter)mdc).getPropertyMap();
/*     */       } else {
/* 312 */         this.mdcPropertyMap = mdc.getCopyOfContextMap();
/*     */       } 
/*     */     } 
/* 315 */     if (this.mdcPropertyMap == null)
/* 316 */       this.mdcPropertyMap = CACHED_NULL_MAP; 
/* 318 */     return this.mdcPropertyMap;
/*     */   }
/*     */   
/*     */   public void setMDCPropertyMap(Map<String, String> map) {
/* 328 */     if (this.mdcPropertyMap != null)
/* 329 */       throw new IllegalStateException("The MDCPropertyMap has been already set for this event."); 
/* 332 */     this.mdcPropertyMap = map;
/*     */   }
/*     */   
/*     */   public Map<String, String> getMdc() {
/* 342 */     return getMDCPropertyMap();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 347 */     StringBuilder sb = new StringBuilder();
/* 348 */     sb.append('[');
/* 349 */     sb.append(this.level).append("] ");
/* 350 */     sb.append(getFormattedMessage());
/* 351 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 361 */     throw new UnsupportedOperationException(getClass() + " does not support serialization. " + "Use LoggerEventVO instance instead. See also LoggerEventVO.build method.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\spi\LoggingEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */