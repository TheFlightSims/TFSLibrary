/*    */ package ch.qos.logback.classic.net;
/*    */ 
/*    */ import ch.qos.logback.core.spi.ContextAwareBase;
/*    */ import ch.qos.logback.core.spi.LifeCycle;
/*    */ 
/*    */ public abstract class ReceiverBase extends ContextAwareBase implements LifeCycle {
/*    */   private boolean started;
/*    */   
/*    */   public final void start() {
/* 34 */     if (isStarted())
/*    */       return; 
/* 35 */     if (getContext() == null)
/* 36 */       throw new IllegalStateException("context not set"); 
/* 38 */     if (shouldStart()) {
/* 39 */       getContext().getExecutorService().execute(getRunnableTask());
/* 40 */       this.started = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void stop() {
/* 48 */     if (!isStarted())
/*    */       return; 
/*    */     try {
/* 50 */       onStop();
/* 52 */     } catch (RuntimeException ex) {
/* 53 */       addError("on stop: " + ex, ex);
/*    */     } 
/* 55 */     this.started = false;
/*    */   }
/*    */   
/*    */   public final boolean isStarted() {
/* 62 */     return this.started;
/*    */   }
/*    */   
/*    */   protected abstract boolean shouldStart();
/*    */   
/*    */   protected abstract void onStop();
/*    */   
/*    */   protected abstract Runnable getRunnableTask();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\ReceiverBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */