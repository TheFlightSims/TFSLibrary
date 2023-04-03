/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Success$ implements Serializable {
/*     */   public static final Success$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 194 */     return "Success";
/*     */   }
/*     */   
/*     */   public <T> Success<T> apply(Object value) {
/* 194 */     return new Success<T>((T)value);
/*     */   }
/*     */   
/*     */   public <T> Option<T> unapply(Success x$0) {
/* 194 */     return (x$0 == null) ? (Option<T>)scala.None$.MODULE$ : (Option<T>)new Some(x$0.value());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 194 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Success$() {
/* 194 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class Success$$anonfun$map$1 extends AbstractFunction0<U> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final U apply() {
/* 206 */       return (U)this.f$1.apply(this.$outer.value());
/*     */     }
/*     */     
/*     */     public Success$$anonfun$map$1(Success $outer, Function1 f$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Success$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */