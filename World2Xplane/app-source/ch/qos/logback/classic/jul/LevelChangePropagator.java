/*     */ package ch.qos.logback.classic.jul;
/*     */ 
/*     */ import ch.qos.logback.classic.Level;
/*     */ import ch.qos.logback.classic.Logger;
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.spi.LoggerContextListener;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import ch.qos.logback.core.spi.LifeCycle;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogManager;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class LevelChangePropagator extends ContextAwareBase implements LoggerContextListener, LifeCycle {
/*  35 */   private Set julLoggerSet = new HashSet();
/*     */   
/*     */   boolean isStarted = false;
/*     */   
/*     */   boolean resetJUL = false;
/*     */   
/*     */   public void setResetJUL(boolean resetJUL) {
/*  40 */     this.resetJUL = resetJUL;
/*     */   }
/*     */   
/*     */   public boolean isResetResistant() {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public void onStart(LoggerContext context) {}
/*     */   
/*     */   public void onReset(LoggerContext context) {}
/*     */   
/*     */   public void onStop(LoggerContext context) {}
/*     */   
/*     */   public void onLevelChange(Logger logger, Level level) {
/*  57 */     propagate(logger, level);
/*     */   }
/*     */   
/*     */   private void propagate(Logger logger, Level level) {
/*  61 */     addInfo("Propagating " + level + " level on " + logger + " onto the JUL framework");
/*  62 */     Logger julLogger = JULHelper.asJULLogger(logger);
/*  65 */     this.julLoggerSet.add(julLogger);
/*  66 */     Level julLevel = JULHelper.asJULLevel(level);
/*  67 */     julLogger.setLevel(julLevel);
/*     */   }
/*     */   
/*     */   public void resetJULLevels() {
/*  71 */     LogManager lm = LogManager.getLogManager();
/*  73 */     Enumeration<String> e = lm.getLoggerNames();
/*  74 */     while (e.hasMoreElements()) {
/*  75 */       String loggerName = e.nextElement();
/*  76 */       Logger julLogger = lm.getLogger(loggerName);
/*  77 */       if (JULHelper.isRegularNonRootLogger(julLogger) && julLogger.getLevel() != null) {
/*  78 */         addInfo("Setting level of jul logger [" + loggerName + "] to null");
/*  79 */         julLogger.setLevel(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void propagateExistingLoggerLevels() {
/*  85 */     LoggerContext loggerContext = (LoggerContext)this.context;
/*  86 */     List<Logger> loggerList = loggerContext.getLoggerList();
/*  87 */     for (Logger l : loggerList) {
/*  88 */       if (l.getLevel() != null)
/*  89 */         propagate(l, l.getLevel()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void start() {
/*  95 */     if (this.resetJUL)
/*  96 */       resetJULLevels(); 
/*  98 */     propagateExistingLoggerLevels();
/* 100 */     this.isStarted = true;
/*     */   }
/*     */   
/*     */   public void stop() {
/* 104 */     this.isStarted = false;
/*     */   }
/*     */   
/*     */   public boolean isStarted() {
/* 108 */     return this.isStarted;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\jul\LevelChangePropagator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */