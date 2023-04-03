/*     */ package scala.math;
/*     */ 
/*     */ public abstract class Numeric$class {
/*     */   public static void $init$(Numeric $this) {}
/*     */   
/*     */   public static Object zero(Numeric $this) {
/* 200 */     return $this.fromInt(0);
/*     */   }
/*     */   
/*     */   public static Object one(Numeric $this) {
/* 201 */     return $this.fromInt(1);
/*     */   }
/*     */   
/*     */   public static Object abs(Numeric<Object> $this, Object x) {
/* 203 */     return $this.lt(x, $this.zero()) ? $this.negate(x) : x;
/*     */   }
/*     */   
/*     */   public static int signum(Numeric<Object> $this, Object x) {
/* 205 */     return $this.lt(x, $this.zero()) ? -1 : (
/* 206 */       $this.gt(x, $this.zero()) ? 1 : 
/* 207 */       0);
/*     */   }
/*     */   
/*     */   public static Numeric.Ops mkNumericOps(Numeric<T> $this, Object lhs) {
/* 221 */     return new Numeric.Ops($this, (T)lhs);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Numeric$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */