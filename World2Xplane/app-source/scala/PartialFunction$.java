/*     */ package scala;
/*     */ 
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ public final class PartialFunction$ {
/*     */   public static final PartialFunction$ MODULE$;
/*     */   
/*     */   public final PartialFunction<Object, Object> scala$PartialFunction$$fallback_pf;
/*     */   
/*     */   public final Function1<Object, Object> scala$PartialFunction$$constFalse;
/*     */   
/*     */   private final PartialFunction<Object, Nothing$> empty_pf;
/*     */   
/*     */   public class PartialFunction$$anonfun$runWith$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 action$1;
/*     */     
/*     */     public PartialFunction$$anonfun$runWith$1(PartialFunction $outer, Function1 action$1) {}
/*     */     
/*     */     public final boolean apply(Object x) {
/* 136 */       Object z = this.$outer.applyOrElse(x, PartialFunction$.MODULE$.scala$PartialFunction$$checkFallback());
/* 137 */       this.action$1.apply(z);
/* 137 */       return !PartialFunction$.MODULE$.scala$PartialFunction$$fallbackOccurred(z);
/*     */     }
/*     */   }
/*     */   
/*     */   private PartialFunction$() {
/* 156 */     MODULE$ = this;
/* 210 */     this.scala$PartialFunction$$fallback_pf = (PartialFunction<Object, Object>)new PartialFunction.$anonfun$1();
/* 244 */     this.scala$PartialFunction$$constFalse = (Function1<Object, Object>)new PartialFunction.$anonfun$2();
/* 246 */     this.empty_pf = new PartialFunction.$anon$1();
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractPartialFunction<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */       return (B1)PartialFunction$.MODULE$.scala$PartialFunction$$fallback_pf;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public <B> PartialFunction<Object, B> scala$PartialFunction$$checkFallback() {
/*     */     return (PartialFunction)this.scala$PartialFunction$$fallback_pf;
/*     */   }
/*     */   
/*     */   public <B> boolean scala$PartialFunction$$fallbackOccurred(Object x) {
/*     */     return (this.scala$PartialFunction$$fallback_pf == x);
/*     */   }
/*     */   
/*     */   public <A, B> PartialFunction<A, B> unlifted(Function1<?, Option<?>> f) {
/*     */     PartialFunction.Unlifted<Object, Object> unlifted;
/*     */     if (f instanceof PartialFunction.Lifted) {
/*     */       PartialFunction.Lifted lifted = (PartialFunction.Lifted)f;
/*     */       PartialFunction partialFunction = lifted.pf();
/*     */     } else {
/*     */       unlifted = new PartialFunction.Unlifted<Object, Object>(f);
/*     */     } 
/*     */     return (PartialFunction)unlifted;
/*     */   }
/*     */   
/*     */   public <A, B> PartialFunction<A, B> apply(Function1 f) {
/*     */     return (PartialFunction<A, B>)new PartialFunction$$anonfun$apply$1(f);
/*     */   }
/*     */   
/*     */   public static class PartialFunction$$anonfun$apply$1 extends AbstractPartialFunction<A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final <A1 extends A, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       return this.f$1.apply(x2);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       return true;
/*     */     }
/*     */     
/*     */     public PartialFunction$$anonfun$apply$1(Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Object x$1) {
/*     */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$1 implements PartialFunction<Object, Nothing$> {
/*     */     private final Function1<Object, None$> lift;
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 246 */       return (B1)PartialFunction$class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 246 */       return Function1$class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 246 */       return Function1$class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 246 */       return Function1$class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 246 */       return Function1$class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 246 */       return Function1$class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 246 */       Function1$class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 246 */       return Function1$class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 246 */       return Function1$class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 246 */       return Function1$class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 246 */       return Function1$class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 246 */       return Function1$class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 246 */       Function1$class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 246 */       return Function1$class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 246 */       return Function1$class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 246 */       return Function1$class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 246 */       return Function1$class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 246 */       return Function1$class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 246 */       Function1$class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 246 */       return Function1$class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 246 */       return Function1$class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 246 */       return Function1$class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 246 */       return Function1$class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 246 */       return Function1$class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 246 */       Function1$class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Nothing$> compose(Function1 g) {
/* 246 */       return Function1$class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 246 */       return Function1$class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 246 */       return Function1$class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 246 */       return Function1$class.toString(this);
/*     */     }
/*     */     
/*     */     public $anon$1() {
/* 246 */       Function1$class.$init$(this);
/* 246 */       PartialFunction$class.$init$(this);
/* 251 */       this.lift = (Function1<Object, None$>)new $anonfun$3(this);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Object x) {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public Nothing$ apply(Object x) {
/*     */       throw new MatchError(x);
/*     */     }
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
/*     */       return that;
/*     */     }
/*     */     
/*     */     public <C> $anon$1 andThen(Function1 k) {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public Function1<Object, None$> lift() {
/* 251 */       return this.lift;
/*     */     }
/*     */     
/*     */     public class $anonfun$3 extends AbstractFunction1<Object, None$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final None$ apply(Object x) {
/* 251 */         return None$.MODULE$;
/*     */       }
/*     */       
/*     */       public $anonfun$3(PartialFunction.$anon$1 $outer) {}
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 252 */       return PartialFunction$.MODULE$.scala$PartialFunction$$constFalse;
/*     */     }
/*     */   }
/*     */   
/*     */   public <A, B> PartialFunction<A, B> empty() {
/* 259 */     return (PartialFunction)this.empty_pf;
/*     */   }
/*     */   
/*     */   public <T> boolean cond(Object x, PartialFunction pf) {
/* 269 */     return BoxesRunTime.unboxToBoolean(pf.applyOrElse(x, this.scala$PartialFunction$$constFalse));
/*     */   }
/*     */   
/*     */   public <T, U> Option<U> condOpt(Object x, PartialFunction<Object, U> pf) {
/* 281 */     return pf.lift().apply(x);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\PartialFunction$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */