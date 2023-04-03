/*     */ package scala.util;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ 
/*     */ public final class Left$ implements Serializable {
/*     */   public static final Left$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 189 */     return "Left";
/*     */   }
/*     */   
/*     */   public <A, B> Left<A, B> apply(Object a) {
/* 189 */     return new Left<A, B>((A)a);
/*     */   }
/*     */   
/*     */   public <A, B> Option<A> unapply(Left x$0) {
/* 189 */     return (x$0 == null) ? (Option<A>)scala.None$.MODULE$ : (Option<A>)new Some(x$0.a());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 189 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Left$() {
/* 189 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Left$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */