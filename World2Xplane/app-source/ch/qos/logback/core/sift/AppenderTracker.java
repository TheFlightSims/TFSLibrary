/*    */ package ch.qos.logback.core.sift;
/*    */ 
/*    */ import ch.qos.logback.core.Appender;
/*    */ import ch.qos.logback.core.Context;
/*    */ import ch.qos.logback.core.helpers.NOPAppender;
/*    */ import ch.qos.logback.core.joran.spi.JoranException;
/*    */ import ch.qos.logback.core.spi.AbstractComponentTracker;
/*    */ import ch.qos.logback.core.spi.ContextAwareImpl;
/*    */ 
/*    */ public class AppenderTracker<E> extends AbstractComponentTracker<Appender<E>> {
/* 34 */   int nopaWarningCount = 0;
/*    */   
/*    */   final Context context;
/*    */   
/*    */   final AppenderFactory<E> appenderFactory;
/*    */   
/*    */   final ContextAwareImpl contextAware;
/*    */   
/*    */   public AppenderTracker(Context context, AppenderFactory<E> appenderFactory) {
/* 42 */     this.context = context;
/* 43 */     this.appenderFactory = appenderFactory;
/* 44 */     this.contextAware = new ContextAwareImpl(context, this);
/*    */   }
/*    */   
/*    */   protected void processPriorToRemoval(Appender<E> component) {
/* 50 */     component.stop();
/*    */   }
/*    */   
/*    */   protected Appender<E> buildComponent(String key) {
/*    */     NOPAppender<E> nOPAppender;
/* 55 */     Appender<E> appender = null;
/*    */     try {
/* 57 */       appender = this.appenderFactory.buildAppender(this.context, key);
/* 58 */     } catch (JoranException je) {
/* 59 */       this.contextAware.addError("Error while building appender with discriminating value [" + key + "]");
/*    */     } 
/* 61 */     if (appender == null)
/* 62 */       nOPAppender = buildNOPAppender(key); 
/* 65 */     return (Appender<E>)nOPAppender;
/*    */   }
/*    */   
/*    */   private NOPAppender<E> buildNOPAppender(String key) {
/* 69 */     if (this.nopaWarningCount < 4) {
/* 70 */       this.nopaWarningCount++;
/* 71 */       this.contextAware.addError("Building NOPAppender for discriminating value [" + key + "]");
/*    */     } 
/* 73 */     NOPAppender<E> nopa = new NOPAppender();
/* 74 */     nopa.setContext(this.context);
/* 75 */     nopa.start();
/* 76 */     return nopa;
/*    */   }
/*    */   
/*    */   protected boolean isComponentStale(Appender<E> appender) {
/* 81 */     return !appender.isStarted();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\sift\AppenderTracker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */