/*    */ package ch.qos.logback.core.util;
/*    */ 
/*    */ public class FixedDelay implements DelayStrategy {
/*    */   private final int subsequentDelay;
/*    */   
/*    */   private int nextDelay;
/*    */   
/*    */   public FixedDelay(int initialDelay, int subsequentDelay) {
/* 35 */     String s = new String();
/* 36 */     this.nextDelay = initialDelay;
/* 37 */     this.subsequentDelay = subsequentDelay;
/*    */   }
/*    */   
/*    */   public FixedDelay(int delay) {
/* 47 */     this(delay, delay);
/*    */   }
/*    */   
/*    */   public int nextDelay() {
/* 54 */     int delay = this.nextDelay;
/* 55 */     this.nextDelay = this.subsequentDelay;
/* 56 */     return delay;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\FixedDelay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */