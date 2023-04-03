/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ 
/*     */ public class Incrementor {
/*     */   private int maximalCount;
/*     */   
/*  40 */   private int count = 0;
/*     */   
/*     */   private final MaxCountExceededCallback maxCountCallback;
/*     */   
/*     */   public Incrementor() {
/*  52 */     this(0);
/*     */   }
/*     */   
/*     */   public Incrementor(int max) {
/*  61 */     this(max, new MaxCountExceededCallback() {
/*     */           public void trigger(int max) {
/*  65 */             throw new MaxCountExceededException(Integer.valueOf(max));
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static interface MaxCountExceededCallback {
/*     */     void trigger(int param1Int);
/*     */   }
/*     */   
/*     */   public Incrementor(int max, MaxCountExceededCallback cb) {
/*  79 */     this.maximalCount = max;
/*  80 */     this.maxCountCallback = cb;
/*     */   }
/*     */   
/*     */   public void setMaximalCount(int max) {
/*  91 */     this.maximalCount = max;
/*     */   }
/*     */   
/*     */   public int getMaximalCount() {
/* 100 */     return this.maximalCount;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 109 */     return this.count;
/*     */   }
/*     */   
/*     */   public boolean canIncrement() {
/* 120 */     return (this.count < this.maximalCount);
/*     */   }
/*     */   
/*     */   public void incrementCount(int value) {
/* 131 */     for (int i = 0; i < value; i++)
/* 132 */       incrementCount(); 
/*     */   }
/*     */   
/*     */   public void incrementCount() {
/* 150 */     if (++this.count > this.maximalCount)
/* 151 */       this.maxCountCallback.trigger(this.maximalCount); 
/*     */   }
/*     */   
/*     */   public void resetCount() {
/* 159 */     this.count = 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\Incrementor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */