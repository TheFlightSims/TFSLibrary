/*     */ package scala.math;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Some;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class Ordering$class {
/*     */   public static void $init$(Ordering $this) {}
/*     */   
/*     */   public static Some tryCompare(Ordering<Object> $this, Object x, Object y) {
/*  75 */     return new Some(BoxesRunTime.boxToInteger($this.compare(x, y)));
/*     */   }
/*     */   
/*     */   public static boolean lteq(Ordering $this, Object x, Object y) {
/*  88 */     return ($this.compare((T)x, (T)y) <= 0);
/*     */   }
/*     */   
/*     */   public static boolean gteq(Ordering $this, Object x, Object y) {
/*  91 */     return ($this.compare((T)x, (T)y) >= 0);
/*     */   }
/*     */   
/*     */   public static boolean lt(Ordering $this, Object x, Object y) {
/*  94 */     return ($this.compare((T)x, (T)y) < 0);
/*     */   }
/*     */   
/*     */   public static boolean gt(Ordering $this, Object x, Object y) {
/*  97 */     return ($this.compare((T)x, (T)y) > 0);
/*     */   }
/*     */   
/*     */   public static boolean equiv(Ordering<Object> $this, Object x, Object y) {
/* 100 */     return ($this.compare(x, y) == 0);
/*     */   }
/*     */   
/*     */   public static Object max(Ordering<Object> $this, Object x, Object y) {
/* 103 */     return $this.gteq(x, y) ? x : y;
/*     */   }
/*     */   
/*     */   public static Object min(Ordering<Object> $this, Object x, Object y) {
/* 106 */     return $this.lteq(x, y) ? x : y;
/*     */   }
/*     */   
/*     */   public static Ordering reverse(Ordering<T> $this) {
/* 109 */     return new Ordering$$anon$4($this);
/*     */   }
/*     */   
/*     */   public static Ordering on(Ordering $this, Function1 f) {
/* 121 */     return new Ordering$$anon$5($this, (Ordering<T>)f);
/*     */   }
/*     */   
/*     */   public static Ordering.Ops mkOrderingOps(Ordering<T> $this, Object lhs) {
/* 139 */     return new Ordering.Ops($this, (T)lhs);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Ordering$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */