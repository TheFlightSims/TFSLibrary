/*     */ package org.apache.commons.lang.time;
/*     */ 
/*     */ public class StopWatch {
/*     */   private static final int STATE_UNSTARTED = 0;
/*     */   
/*     */   private static final int STATE_RUNNING = 1;
/*     */   
/*     */   private static final int STATE_STOPPED = 2;
/*     */   
/*     */   private static final int STATE_SUSPENDED = 3;
/*     */   
/*     */   private static final int STATE_UNSPLIT = 10;
/*     */   
/*     */   private static final int STATE_SPLIT = 11;
/*     */   
/*  78 */   private int runningState = 0;
/*     */   
/*  83 */   private int splitState = 10;
/*     */   
/*  88 */   private long startTime = -1L;
/*     */   
/*  93 */   private long stopTime = -1L;
/*     */   
/*     */   public void start() {
/* 117 */     if (this.runningState == 2)
/* 118 */       throw new IllegalStateException("Stopwatch must be reset before being restarted. "); 
/* 120 */     if (this.runningState != 0)
/* 121 */       throw new IllegalStateException("Stopwatch already started. "); 
/* 123 */     this.stopTime = -1L;
/* 124 */     this.startTime = System.currentTimeMillis();
/* 125 */     this.runningState = 1;
/*     */   }
/*     */   
/*     */   public void stop() {
/* 141 */     if (this.runningState != 1 && this.runningState != 3)
/* 142 */       throw new IllegalStateException("Stopwatch is not running. "); 
/* 144 */     if (this.runningState == 1)
/* 145 */       this.stopTime = System.currentTimeMillis(); 
/* 147 */     this.runningState = 2;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 160 */     this.runningState = 0;
/* 161 */     this.splitState = 10;
/* 162 */     this.startTime = -1L;
/* 163 */     this.stopTime = -1L;
/*     */   }
/*     */   
/*     */   public void split() {
/* 180 */     if (this.runningState != 1)
/* 181 */       throw new IllegalStateException("Stopwatch is not running. "); 
/* 183 */     this.stopTime = System.currentTimeMillis();
/* 184 */     this.splitState = 11;
/*     */   }
/*     */   
/*     */   public void unsplit() {
/* 201 */     if (this.splitState != 11)
/* 202 */       throw new IllegalStateException("Stopwatch has not been split. "); 
/* 204 */     this.stopTime = -1L;
/* 205 */     this.splitState = 10;
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 222 */     if (this.runningState != 1)
/* 223 */       throw new IllegalStateException("Stopwatch must be running to suspend. "); 
/* 225 */     this.stopTime = System.currentTimeMillis();
/* 226 */     this.runningState = 3;
/*     */   }
/*     */   
/*     */   public void resume() {
/* 243 */     if (this.runningState != 3)
/* 244 */       throw new IllegalStateException("Stopwatch must be suspended to resume. "); 
/* 246 */     this.startTime += System.currentTimeMillis() - this.stopTime;
/* 247 */     this.stopTime = -1L;
/* 248 */     this.runningState = 1;
/*     */   }
/*     */   
/*     */   public long getTime() {
/* 264 */     if (this.runningState == 2 || this.runningState == 3)
/* 265 */       return this.stopTime - this.startTime; 
/* 266 */     if (this.runningState == 0)
/* 267 */       return 0L; 
/* 268 */     if (this.runningState == 1)
/* 269 */       return System.currentTimeMillis() - this.startTime; 
/* 271 */     throw new RuntimeException("Illegal running state has occured. ");
/*     */   }
/*     */   
/*     */   public long getSplitTime() {
/* 290 */     if (this.splitState != 11)
/* 291 */       throw new IllegalStateException("Stopwatch must be split to get the split time. "); 
/* 293 */     return this.stopTime - this.startTime;
/*     */   }
/*     */   
/*     */   public long getStartTime() {
/* 305 */     if (this.runningState == 0)
/* 306 */       throw new IllegalStateException("Stopwatch has not been started"); 
/* 308 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 323 */     return DurationFormatUtils.formatDurationHMS(getTime());
/*     */   }
/*     */   
/*     */   public String toSplitString() {
/* 339 */     return DurationFormatUtils.formatDurationHMS(getSplitTime());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\time\StopWatch.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */