/*     */ package scala;
/*     */ 
/*     */ import scala.collection.Iterable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Option$ implements Serializable {
/*     */   public static final Option$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  11 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Option$() {
/*  11 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> option2Iterable(Option xo) {
/*  17 */     return (Iterable<A>)xo.toList();
/*     */   }
/*     */   
/*     */   public <A> Option<A> apply(Object x) {
/*  25 */     return (x == null) ? None$.MODULE$ : new Some<A>((A)x);
/*     */   }
/*     */   
/*     */   public <A> Option<A> empty() {
/*  30 */     return None$.MODULE$;
/*     */   }
/*     */   
/*     */   public class Option$$anonfun$orNull$1 extends AbstractFunction0<A1> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Predef.$less$colon$less ev$1;
/*     */     
/*     */     public final A1 apply() {
/* 131 */       return (A1)this.ev$1.apply(null);
/*     */     }
/*     */     
/*     */     public Option$$anonfun$orNull$1(Option $outer, Predef.$less$colon$less ev$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Option$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */