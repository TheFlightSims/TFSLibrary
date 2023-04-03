/*     */ package com.vividsolutions.jts.util;
/*     */ 
/*     */ public class Stopwatch {
/*     */   private long startTimestamp;
/*     */   
/*  45 */   private long totalTime = 0L;
/*     */   
/*     */   private boolean isRunning = false;
/*     */   
/*     */   public Stopwatch() {
/*  50 */     start();
/*     */   }
/*     */   
/*     */   public void start() {
/*  55 */     if (this.isRunning)
/*     */       return; 
/*  56 */     this.startTimestamp = System.currentTimeMillis();
/*  57 */     this.isRunning = true;
/*     */   }
/*     */   
/*     */   public long stop() {
/*  62 */     if (this.isRunning) {
/*  63 */       updateTotalTime();
/*  64 */       this.isRunning = false;
/*     */     } 
/*  66 */     return this.totalTime;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  71 */     this.totalTime = 0L;
/*  72 */     this.startTimestamp = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public long split() {
/*  77 */     if (this.isRunning)
/*  78 */       updateTotalTime(); 
/*  79 */     return this.totalTime;
/*     */   }
/*     */   
/*     */   private void updateTotalTime() {
/*  84 */     long endTimestamp = System.currentTimeMillis();
/*  85 */     long elapsedTime = endTimestamp - this.startTimestamp;
/*  86 */     this.startTimestamp = endTimestamp;
/*  87 */     this.totalTime += elapsedTime;
/*     */   }
/*     */   
/*     */   public long getTime() {
/*  92 */     updateTotalTime();
/*  93 */     return this.totalTime;
/*     */   }
/*     */   
/*     */   public String getTimeString() {
/*  98 */     long totalTime = getTime();
/*  99 */     return getTimeString(totalTime);
/*     */   }
/*     */   
/*     */   public static String getTimeString(long timeMillis) {
/* 103 */     String totalTimeStr = (timeMillis < 10000L) ? (timeMillis + " ms") : ((timeMillis / 1000.0D) + " s");
/* 106 */     return totalTimeStr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\Stopwatch.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */