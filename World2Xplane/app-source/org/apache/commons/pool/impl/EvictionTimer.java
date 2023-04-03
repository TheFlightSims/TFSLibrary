/*    */ package org.apache.commons.pool.impl;
/*    */ 
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ class EvictionTimer {
/*    */   private static Timer _timer;
/*    */   
/*    */   private static int _usageCount;
/*    */   
/*    */   static synchronized void schedule(TimerTask task, long delay, long period) {
/* 60 */     if (null == _timer)
/* 61 */       _timer = new Timer(true); 
/* 63 */     _usageCount++;
/* 64 */     _timer.schedule(task, delay, period);
/*    */   }
/*    */   
/*    */   static synchronized void cancel(TimerTask task) {
/* 72 */     task.cancel();
/* 73 */     _usageCount--;
/* 74 */     if (_usageCount == 0) {
/* 75 */       _timer.cancel();
/* 76 */       _timer = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\EvictionTimer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */