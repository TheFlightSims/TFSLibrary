/*     */ package com.google.common.cache;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Preconditions;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @Beta
/*     */ @GwtCompatible
/*     */ public final class CacheStats {
/*     */   private final long hitCount;
/*     */   
/*     */   private final long missCount;
/*     */   
/*     */   private final long loadSuccessCount;
/*     */   
/*     */   private final long loadExceptionCount;
/*     */   
/*     */   private final long totalLoadTime;
/*     */   
/*     */   private final long evictionCount;
/*     */   
/*     */   public CacheStats(long hitCount, long missCount, long loadSuccessCount, long loadExceptionCount, long totalLoadTime, long evictionCount) {
/*  79 */     Preconditions.checkArgument((hitCount >= 0L));
/*  80 */     Preconditions.checkArgument((missCount >= 0L));
/*  81 */     Preconditions.checkArgument((loadSuccessCount >= 0L));
/*  82 */     Preconditions.checkArgument((loadExceptionCount >= 0L));
/*  83 */     Preconditions.checkArgument((totalLoadTime >= 0L));
/*  84 */     Preconditions.checkArgument((evictionCount >= 0L));
/*  86 */     this.hitCount = hitCount;
/*  87 */     this.missCount = missCount;
/*  88 */     this.loadSuccessCount = loadSuccessCount;
/*  89 */     this.loadExceptionCount = loadExceptionCount;
/*  90 */     this.totalLoadTime = totalLoadTime;
/*  91 */     this.evictionCount = evictionCount;
/*     */   }
/*     */   
/*     */   public long requestCount() {
/*  99 */     return this.hitCount + this.missCount;
/*     */   }
/*     */   
/*     */   public long hitCount() {
/* 106 */     return this.hitCount;
/*     */   }
/*     */   
/*     */   public double hitRate() {
/* 115 */     long requestCount = requestCount();
/* 116 */     return (requestCount == 0L) ? 1.0D : (this.hitCount / requestCount);
/*     */   }
/*     */   
/*     */   public long missCount() {
/* 126 */     return this.missCount;
/*     */   }
/*     */   
/*     */   public double missRate() {
/* 139 */     long requestCount = requestCount();
/* 140 */     return (requestCount == 0L) ? 0.0D : (this.missCount / requestCount);
/*     */   }
/*     */   
/*     */   public long loadCount() {
/* 149 */     return this.loadSuccessCount + this.loadExceptionCount;
/*     */   }
/*     */   
/*     */   public long loadSuccessCount() {
/* 160 */     return this.loadSuccessCount;
/*     */   }
/*     */   
/*     */   public long loadExceptionCount() {
/* 171 */     return this.loadExceptionCount;
/*     */   }
/*     */   
/*     */   public double loadExceptionRate() {
/* 180 */     long totalLoadCount = this.loadSuccessCount + this.loadExceptionCount;
/* 181 */     return (totalLoadCount == 0L) ? 0.0D : (this.loadExceptionCount / totalLoadCount);
/*     */   }
/*     */   
/*     */   public long totalLoadTime() {
/* 192 */     return this.totalLoadTime;
/*     */   }
/*     */   
/*     */   public double averageLoadPenalty() {
/* 200 */     long totalLoadCount = this.loadSuccessCount + this.loadExceptionCount;
/* 201 */     return (totalLoadCount == 0L) ? 0.0D : (this.totalLoadTime / totalLoadCount);
/*     */   }
/*     */   
/*     */   public long evictionCount() {
/* 211 */     return this.evictionCount;
/*     */   }
/*     */   
/*     */   public CacheStats minus(CacheStats other) {
/* 220 */     return new CacheStats(Math.max(0L, this.hitCount - other.hitCount), Math.max(0L, this.missCount - other.missCount), Math.max(0L, this.loadSuccessCount - other.loadSuccessCount), Math.max(0L, this.loadExceptionCount - other.loadExceptionCount), Math.max(0L, this.totalLoadTime - other.totalLoadTime), Math.max(0L, this.evictionCount - other.evictionCount));
/*     */   }
/*     */   
/*     */   public CacheStats plus(CacheStats other) {
/* 236 */     return new CacheStats(this.hitCount + other.hitCount, this.missCount + other.missCount, this.loadSuccessCount + other.loadSuccessCount, this.loadExceptionCount + other.loadExceptionCount, this.totalLoadTime + other.totalLoadTime, this.evictionCount + other.evictionCount);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 247 */     return Objects.hashCode(new Object[] { Long.valueOf(this.hitCount), Long.valueOf(this.missCount), Long.valueOf(this.loadSuccessCount), Long.valueOf(this.loadExceptionCount), Long.valueOf(this.totalLoadTime), Long.valueOf(this.evictionCount) });
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object object) {
/* 253 */     if (object instanceof CacheStats) {
/* 254 */       CacheStats other = (CacheStats)object;
/* 255 */       return (this.hitCount == other.hitCount && this.missCount == other.missCount && this.loadSuccessCount == other.loadSuccessCount && this.loadExceptionCount == other.loadExceptionCount && this.totalLoadTime == other.totalLoadTime && this.evictionCount == other.evictionCount);
/*     */     } 
/* 262 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 267 */     return Objects.toStringHelper(this).add("hitCount", this.hitCount).add("missCount", this.missCount).add("loadSuccessCount", this.loadSuccessCount).add("loadExceptionCount", this.loadExceptionCount).add("totalLoadTime", this.totalLoadTime).add("evictionCount", this.evictionCount).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\cache\CacheStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */