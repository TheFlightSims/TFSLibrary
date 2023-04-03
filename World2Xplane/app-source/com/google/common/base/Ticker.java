/*    */ package com.google.common.base;
/*    */ 
/*    */ import com.google.common.annotations.Beta;
/*    */ import com.google.common.annotations.GwtCompatible;
/*    */ 
/*    */ @Beta
/*    */ @GwtCompatible
/*    */ public abstract class Ticker {
/*    */   public abstract long read();
/*    */   
/*    */   public static Ticker systemTicker() {
/* 54 */     return SYSTEM_TICKER;
/*    */   }
/*    */   
/* 57 */   private static final Ticker SYSTEM_TICKER = new Ticker() {
/*    */       public long read() {
/* 60 */         return Platform.systemNanoTime();
/*    */       }
/*    */     };
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\Ticker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */