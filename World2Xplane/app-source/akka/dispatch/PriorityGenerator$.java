/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class PriorityGenerator$ {
/*     */   public static final PriorityGenerator$ MODULE$;
/*     */   
/*     */   private PriorityGenerator$() {
/* 139 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public PriorityGenerator apply(Function1 priorityFunction) {
/* 143 */     return new PriorityGenerator$$anon$2(priorityFunction);
/*     */   }
/*     */   
/*     */   public static class PriorityGenerator$$anon$2 extends PriorityGenerator {
/*     */     private final Function1 priorityFunction$1;
/*     */     
/*     */     public PriorityGenerator$$anon$2(Function1 priorityFunction$1) {}
/*     */     
/*     */     public int gen(Object message) {
/* 144 */       return BoxesRunTime.unboxToInt(this.priorityFunction$1.apply(message));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\PriorityGenerator$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */