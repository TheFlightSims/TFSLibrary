/*    */ package org.openstreetmap.osmosis.core.pipeline.common;
/*    */ 
/*    */ public class TaskRunner extends Thread {
/*    */   private boolean successful;
/*    */   
/*    */   private Throwable exception;
/*    */   
/*    */   public TaskRunner(Runnable task, String name) {
/* 32 */     super(task, name);
/* 34 */     this.successful = true;
/* 37 */     setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
/*    */           public void uncaughtException(Thread t, Throwable e) {
/* 41 */             TaskRunner.this.successful = false;
/* 42 */             TaskRunner.this.exception = e;
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public boolean isSuccessful() {
/* 56 */     return this.successful;
/*    */   }
/*    */   
/*    */   public Throwable getException() {
/* 66 */     return this.exception;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\TaskRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */