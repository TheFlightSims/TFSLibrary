/*     */ package com.google.common.util.concurrent;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Ticker;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.annotation.concurrent.ThreadSafe;
/*     */ 
/*     */ @ThreadSafe
/*     */ @Beta
/*     */ public abstract class RateLimiter {
/*     */   private final SleepingTicker ticker;
/*     */   
/*     */   private final long offsetNanos;
/*     */   
/*     */   double storedPermits;
/*     */   
/*     */   double maxPermits;
/*     */   
/*     */   volatile double stableIntervalMicros;
/*     */   
/*     */   public static RateLimiter create(double permitsPerSecond) {
/* 242 */     return create(SleepingTicker.SYSTEM_TICKER, permitsPerSecond);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static RateLimiter create(SleepingTicker ticker, double permitsPerSecond) {
/* 247 */     RateLimiter rateLimiter = new Bursty(ticker, 1.0D);
/* 248 */     rateLimiter.setRate(permitsPerSecond);
/* 249 */     return rateLimiter;
/*     */   }
/*     */   
/*     */   public static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit) {
/* 275 */     return create(SleepingTicker.SYSTEM_TICKER, permitsPerSecond, warmupPeriod, unit);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static RateLimiter create(SleepingTicker ticker, double permitsPerSecond, long warmupPeriod, TimeUnit unit) {
/* 281 */     RateLimiter rateLimiter = new WarmingUp(ticker, warmupPeriod, unit);
/* 282 */     rateLimiter.setRate(permitsPerSecond);
/* 283 */     return rateLimiter;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static RateLimiter createWithCapacity(SleepingTicker ticker, double permitsPerSecond, long maxBurstBuildup, TimeUnit unit) {
/* 289 */     double maxBurstSeconds = unit.toNanos(maxBurstBuildup) / 1.0E9D;
/* 290 */     Bursty rateLimiter = new Bursty(ticker, maxBurstSeconds);
/* 291 */     rateLimiter.setRate(permitsPerSecond);
/* 292 */     return rateLimiter;
/*     */   }
/*     */   
/* 323 */   private final Object mutex = new Object();
/*     */   
/* 329 */   private long nextFreeTicketMicros = 0L;
/*     */   
/*     */   private RateLimiter(SleepingTicker ticker) {
/* 332 */     this.ticker = ticker;
/* 333 */     this.offsetNanos = ticker.read();
/*     */   }
/*     */   
/*     */   public final void setRate(double permitsPerSecond) {
/* 355 */     Preconditions.checkArgument((permitsPerSecond > 0.0D && !Double.isNaN(permitsPerSecond)), "rate must be positive");
/* 357 */     synchronized (this.mutex) {
/* 358 */       resync(readSafeMicros());
/* 359 */       double stableIntervalMicros = TimeUnit.SECONDS.toMicros(1L) / permitsPerSecond;
/* 360 */       this.stableIntervalMicros = stableIntervalMicros;
/* 361 */       doSetRate(permitsPerSecond, stableIntervalMicros);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final double getRate() {
/* 375 */     return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
/*     */   }
/*     */   
/*     */   public double acquire() {
/* 388 */     return acquire(1);
/*     */   }
/*     */   
/*     */   public double acquire(int permits) {
/* 400 */     long microsToWait = reserve(permits);
/* 401 */     this.ticker.sleepMicrosUninterruptibly(microsToWait);
/* 402 */     return 1.0D * microsToWait / TimeUnit.SECONDS.toMicros(1L);
/*     */   }
/*     */   
/*     */   long reserve() {
/* 414 */     return reserve(1);
/*     */   }
/*     */   
/*     */   long reserve(int permits) {
/* 424 */     checkPermits(permits);
/* 425 */     synchronized (this.mutex) {
/* 426 */       return reserveNextTicket(permits, readSafeMicros());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean tryAcquire(long timeout, TimeUnit unit) {
/* 443 */     return tryAcquire(1, timeout, unit);
/*     */   }
/*     */   
/*     */   public boolean tryAcquire(int permits) {
/* 457 */     return tryAcquire(permits, 0L, TimeUnit.MICROSECONDS);
/*     */   }
/*     */   
/*     */   public boolean tryAcquire() {
/* 471 */     return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
/*     */   }
/*     */   
/*     */   public boolean tryAcquire(int permits, long timeout, TimeUnit unit) {
/* 486 */     long microsToWait, timeoutMicros = unit.toMicros(timeout);
/* 487 */     checkPermits(permits);
/* 489 */     synchronized (this.mutex) {
/* 490 */       long nowMicros = readSafeMicros();
/* 491 */       if (this.nextFreeTicketMicros > nowMicros + timeoutMicros)
/* 492 */         return false; 
/* 494 */       microsToWait = reserveNextTicket(permits, nowMicros);
/*     */     } 
/* 497 */     this.ticker.sleepMicrosUninterruptibly(microsToWait);
/* 498 */     return true;
/*     */   }
/*     */   
/*     */   private static void checkPermits(int permits) {
/* 502 */     Preconditions.checkArgument((permits > 0), "Requested permits must be positive");
/*     */   }
/*     */   
/*     */   private long reserveNextTicket(double requiredPermits, long nowMicros) {
/* 511 */     resync(nowMicros);
/* 512 */     long microsToNextFreeTicket = Math.max(0L, this.nextFreeTicketMicros - nowMicros);
/* 513 */     double storedPermitsToSpend = Math.min(requiredPermits, this.storedPermits);
/* 514 */     double freshPermits = requiredPermits - storedPermitsToSpend;
/* 516 */     long waitMicros = storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend) + (long)(freshPermits * this.stableIntervalMicros);
/* 519 */     this.nextFreeTicketMicros += waitMicros;
/* 520 */     this.storedPermits -= storedPermitsToSpend;
/* 521 */     return microsToNextFreeTicket;
/*     */   }
/*     */   
/*     */   private void resync(long nowMicros) {
/* 536 */     if (nowMicros > this.nextFreeTicketMicros) {
/* 537 */       this.storedPermits = Math.min(this.maxPermits, this.storedPermits + (nowMicros - this.nextFreeTicketMicros) / this.stableIntervalMicros);
/* 539 */       this.nextFreeTicketMicros = nowMicros;
/*     */     } 
/*     */   }
/*     */   
/*     */   private long readSafeMicros() {
/* 544 */     return TimeUnit.NANOSECONDS.toMicros(this.ticker.read() - this.offsetNanos);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 549 */     return String.format("RateLimiter[stableRate=%3.1fqps]", new Object[] { Double.valueOf(1000000.0D / this.stableIntervalMicros) });
/*     */   }
/*     */   
/*     */   abstract void doSetRate(double paramDouble1, double paramDouble2);
/*     */   
/*     */   abstract long storedPermitsToWaitTime(double paramDouble1, double paramDouble2);
/*     */   
/*     */   private static class WarmingUp extends RateLimiter {
/*     */     final long warmupPeriodMicros;
/*     */     
/*     */     private double slope;
/*     */     
/*     */     private double halfPermits;
/*     */     
/*     */     WarmingUp(RateLimiter.SleepingTicker ticker, long warmupPeriod, TimeUnit timeUnit) {
/* 638 */       super(ticker);
/* 639 */       this.warmupPeriodMicros = timeUnit.toMicros(warmupPeriod);
/*     */     }
/*     */     
/*     */     void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
/* 644 */       double oldMaxPermits = this.maxPermits;
/* 645 */       this.maxPermits = this.warmupPeriodMicros / stableIntervalMicros;
/* 646 */       this.halfPermits = this.maxPermits / 2.0D;
/* 648 */       double coldIntervalMicros = stableIntervalMicros * 3.0D;
/* 649 */       this.slope = (coldIntervalMicros - stableIntervalMicros) / this.halfPermits;
/* 650 */       if (oldMaxPermits == Double.POSITIVE_INFINITY) {
/* 652 */         this.storedPermits = 0.0D;
/*     */       } else {
/* 654 */         this.storedPermits = (oldMaxPermits == 0.0D) ? this.maxPermits : (this.storedPermits * this.maxPermits / oldMaxPermits);
/*     */       } 
/*     */     }
/*     */     
/*     */     long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
/* 662 */       double availablePermitsAboveHalf = storedPermits - this.halfPermits;
/* 663 */       long micros = 0L;
/* 665 */       if (availablePermitsAboveHalf > 0.0D) {
/* 666 */         double permitsAboveHalfToTake = Math.min(availablePermitsAboveHalf, permitsToTake);
/* 667 */         micros = (long)(permitsAboveHalfToTake * (permitsToTime(availablePermitsAboveHalf) + permitsToTime(availablePermitsAboveHalf - permitsAboveHalfToTake)) / 2.0D);
/* 669 */         permitsToTake -= permitsAboveHalfToTake;
/*     */       } 
/* 672 */       micros = (long)(micros + this.stableIntervalMicros * permitsToTake);
/* 673 */       return micros;
/*     */     }
/*     */     
/*     */     private double permitsToTime(double permits) {
/* 677 */       return this.stableIntervalMicros + permits * this.slope;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Bursty extends RateLimiter {
/*     */     final double maxBurstSeconds;
/*     */     
/*     */     Bursty(RateLimiter.SleepingTicker ticker, double maxBurstSeconds) {
/* 692 */       super(ticker);
/* 693 */       this.maxBurstSeconds = maxBurstSeconds;
/*     */     }
/*     */     
/*     */     void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
/* 698 */       double oldMaxPermits = this.maxPermits;
/* 699 */       this.maxPermits = this.maxBurstSeconds * permitsPerSecond;
/* 700 */       this.storedPermits = (oldMaxPermits == 0.0D) ? 0.0D : (this.storedPermits * this.maxPermits / oldMaxPermits);
/*     */     }
/*     */     
/*     */     long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
/* 707 */       return 0L;
/*     */     }
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static abstract class SleepingTicker extends Ticker {
/* 715 */     static final SleepingTicker SYSTEM_TICKER = new SleepingTicker() {
/*     */         public long read() {
/* 718 */           return systemTicker().read();
/*     */         }
/*     */         
/*     */         public void sleepMicrosUninterruptibly(long micros) {
/* 723 */           if (micros > 0L)
/* 724 */             Uninterruptibles.sleepUninterruptibly(micros, TimeUnit.MICROSECONDS); 
/*     */         }
/*     */       };
/*     */     
/*     */     abstract void sleepMicrosUninterruptibly(long param1Long);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\commo\\util\concurrent\RateLimiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */