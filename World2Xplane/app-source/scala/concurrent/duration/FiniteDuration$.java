/*     */ package scala.concurrent.duration;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class FiniteDuration$ implements Serializable {
/*     */   public static final FiniteDuration$ MODULE$;
/*     */   
/*     */   private final long max_ns;
/*     */   
/*     */   private final long max_µs;
/*     */   
/*     */   private final long max_ms;
/*     */   
/*     */   private final long max_s;
/*     */   
/*     */   private final long max_min;
/*     */   
/*     */   private final long max_h;
/*     */   
/*     */   private final long max_d;
/*     */   
/*     */   private Object readResolve() {
/* 524 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private FiniteDuration$() {
/* 524 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public FiniteDuration apply(long length, TimeUnit unit) {
/* 530 */     return new FiniteDuration(length, unit);
/*     */   }
/*     */   
/*     */   public FiniteDuration apply(long length, String unit) {
/* 531 */     return new FiniteDuration(length, (TimeUnit)Duration$.MODULE$.timeUnit().apply(unit));
/*     */   }
/*     */   
/*     */   private final long max_ns() {
/* 534 */     return Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private final long max_µs() {
/* 535 */     return 9223372036854775L;
/*     */   }
/*     */   
/*     */   private final long max_ms() {
/* 536 */     return 9223372036854L;
/*     */   }
/*     */   
/*     */   private final long max_s() {
/* 537 */     return 9223372036L;
/*     */   }
/*     */   
/*     */   private final long max_min() {
/* 538 */     return 153722867L;
/*     */   }
/*     */   
/*     */   private final long max_h() {
/* 539 */     return 2562047L;
/*     */   }
/*     */   
/*     */   private final long max_d() {
/* 540 */     return 106751L;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\FiniteDuration$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */