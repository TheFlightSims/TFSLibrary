/*     */ package scala.math;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import scala.Function1;
/*     */ 
/*     */ public abstract class LowPriorityOrderingImplicits$class {
/*     */   public static void $init$(LowPriorityOrderingImplicits $this) {}
/*     */   
/*     */   public static Ordering ordered(LowPriorityOrderingImplicits $this, Function1 evidence$1) {
/* 149 */     return new LowPriorityOrderingImplicits$$anon$6($this, evidence$1);
/*     */   }
/*     */   
/*     */   public static Ordering comparatorToOrdering(LowPriorityOrderingImplicits $this, Comparator cmp) {
/* 152 */     return new LowPriorityOrderingImplicits$$anon$7($this, cmp);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\LowPriorityOrderingImplicits$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */