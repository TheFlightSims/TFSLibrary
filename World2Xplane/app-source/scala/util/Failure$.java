/*     */ package scala.util;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Failure$ implements Serializable {
/*     */   public static final Failure$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 167 */     return "Failure";
/*     */   }
/*     */   
/*     */   public <T> Failure<T> apply(Throwable exception) {
/* 167 */     return new Failure<T>(exception);
/*     */   }
/*     */   
/*     */   public <T> Option<Throwable> unapply(Failure x$0) {
/* 167 */     return (x$0 == null) ? (Option<Throwable>)scala.None$.MODULE$ : (Option<Throwable>)new Some(x$0.exception());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 167 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Failure$() {
/* 167 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class Failure$$anonfun$recover$1 extends AbstractFunction0<U> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction rescueException$1;
/*     */     
/*     */     public final U apply() {
/* 185 */       return (U)this.rescueException$1.apply(this.$outer.exception());
/*     */     }
/*     */     
/*     */     public Failure$$anonfun$recover$1(Failure $outer, PartialFunction rescueException$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Failure$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */