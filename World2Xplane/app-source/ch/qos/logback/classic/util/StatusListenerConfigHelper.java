/*    */ package ch.qos.logback.classic.util;
/*    */ 
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.core.Context;
/*    */ import ch.qos.logback.core.spi.ContextAware;
/*    */ import ch.qos.logback.core.spi.LifeCycle;
/*    */ import ch.qos.logback.core.status.OnConsoleStatusListener;
/*    */ import ch.qos.logback.core.status.StatusListener;
/*    */ import ch.qos.logback.core.util.OptionHelper;
/*    */ 
/*    */ public class StatusListenerConfigHelper {
/*    */   static void installIfAsked(LoggerContext loggerContext) {
/* 26 */     String slClass = OptionHelper.getSystemProperty("logback.statusListenerClass");
/* 28 */     if (!OptionHelper.isEmpty(slClass))
/* 29 */       addStatusListener(loggerContext, slClass); 
/*    */   }
/*    */   
/*    */   private static void addStatusListener(LoggerContext loggerContext, String listenerClass) {
/* 35 */     StatusListener listener = null;
/* 36 */     if ("SYSOUT".equalsIgnoreCase(listenerClass)) {
/* 37 */       OnConsoleStatusListener onConsoleStatusListener = new OnConsoleStatusListener();
/*    */     } else {
/* 39 */       listener = createListenerPerClassName(loggerContext, listenerClass);
/*    */     } 
/* 41 */     initListener(loggerContext, listener);
/*    */   }
/*    */   
/*    */   private static void initListener(LoggerContext loggerContext, StatusListener listener) {
/* 45 */     if (listener != null) {
/* 46 */       if (listener instanceof ContextAware)
/* 47 */         ((ContextAware)listener).setContext((Context)loggerContext); 
/* 48 */       if (listener instanceof LifeCycle)
/* 49 */         ((LifeCycle)listener).start(); 
/* 50 */       loggerContext.getStatusManager().add(listener);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static StatusListener createListenerPerClassName(LoggerContext loggerContext, String listenerClass) {
/*    */     try {
/* 56 */       return (StatusListener)OptionHelper.instantiateByClassName(listenerClass, StatusListener.class, (Context)loggerContext);
/* 58 */     } catch (Exception e) {
/* 60 */       e.printStackTrace();
/* 61 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\StatusListenerConfigHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */