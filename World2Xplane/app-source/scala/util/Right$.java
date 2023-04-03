/*     */ package scala.util;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ 
/*     */ public final class Right$ implements Serializable {
/*     */   public static final Right$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 200 */     return "Right";
/*     */   }
/*     */   
/*     */   public <A, B> Right<A, B> apply(Object b) {
/* 200 */     return new Right<A, B>((B)b);
/*     */   }
/*     */   
/*     */   public <A, B> Option<B> unapply(Right x$0) {
/* 200 */     return (x$0 == null) ? (Option<B>)scala.None$.MODULE$ : (Option<B>)new Some(x$0.b());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 200 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Right$() {
/* 200 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Right$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */