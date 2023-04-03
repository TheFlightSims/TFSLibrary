/*     */ package scala.util.control;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Exception$ {
/*     */   public static final Exception$ MODULE$;
/*     */   
/*     */   private final PartialFunction<Throwable, scala.runtime.Nothing$> nothingCatcher;
/*     */   
/*     */   private final Exception.Catch<scala.runtime.Nothing$> noCatch;
/*     */   
/*     */   private Exception$() {
/*  36 */     MODULE$ = this;
/* 147 */     Exception.$anonfun$2 $anonfun$2 = new Exception.$anonfun$2();
/* 147 */     Exception.$anonfun$1 $anonfun$1 = new Exception.$anonfun$1();
/* 147 */     ClassTag classTag = scala.reflect.ClassTag$.MODULE$.apply(Throwable.class);
/* 147 */     this.nothingCatcher = new Exception$$anon$1((Function1)$anonfun$1, (Function1)$anonfun$2, classTag);
/* 152 */     Exception.Catch$ catch$1 = Exception.Catch$.MODULE$, catch$2 = Exception.Catch$.MODULE$;
/* 152 */     this.noCatch = (Exception.Catch<scala.runtime.Nothing$>)(new Exception.Catch(nothingCatcher(), (Option<Exception.Finally>)scala.None$.MODULE$, (Function1<Throwable, Object>)new Exception$Catch$$anonfun$$lessinit$greater$default$3$1())).withDesc("<nothing>");
/*     */   }
/*     */   
/*     */   public <Ex extends Throwable, T> Object mkCatcher(Function1 isDef, Function1 f, ClassTag evidence$1) {
/*     */     return new Exception$$anon$1(isDef, f, evidence$1);
/*     */   }
/*     */   
/*     */   public static class Exception$$anon$1 implements PartialFunction<Throwable, T> {
/*     */     private final Function1 isDef$1;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     private final ClassTag evidence$1$1;
/*     */     
/*     */     public <A1 extends Throwable, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*     */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Throwable, C> andThen(Function1 k) {
/*     */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Throwable, Option<T>> lift() {
/*     */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1 extends Throwable, B1> B1 applyOrElse(Object x, Function1 default) {
/*     */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Throwable, Object> runWith(Function1 action) {
/*     */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/*     */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*     */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*     */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*     */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*     */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*     */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*     */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*     */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*     */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*     */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*     */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*     */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*     */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*     */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*     */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*     */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*     */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*     */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*     */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*     */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*     */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*     */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*     */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*     */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, T> compose(Function1 g) {
/*     */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*     */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*     */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*     */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*     */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*     */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*     */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*     */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*     */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*     */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*     */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*     */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*     */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*     */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*     */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*     */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*     */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*     */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*     */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*     */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*     */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*     */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*     */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*     */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*     */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*     */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return Function1.class.toString((Function1)this);
/*     */     }
/*     */     
/*     */     public Exception$$anon$1(Function1 isDef$1, Function1 f$1, ClassTag evidence$1$1) {
/*     */       Function1.class.$init$((Function1)this);
/*     */       PartialFunction.class.$init$(this);
/*     */     }
/*     */     
/*     */     private Option<Ex> downcast(Throwable x) {
/*     */       return scala.reflect.package$.MODULE$.classTag(this.evidence$1$1).runtimeClass().isAssignableFrom(x.getClass()) ? (Option<Ex>)new Some(x) : (Option<Ex>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Throwable x) {
/*     */       Function1 function1 = this.isDef$1;
/*     */       Option<Ex> option;
/*     */       return (!(option = downcast(x)).isEmpty() && BoxesRunTime.unboxToBoolean(function1.apply(option.get())));
/*     */     }
/*     */     
/*     */     public T apply(Throwable x) {
/*     */       return (T)this.f$1.apply(downcast(x).get());
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Object mkThrowableCatcher(Function1 isDef, Function1 f) {
/*     */     ClassTag classTag = scala.reflect.ClassTag$.MODULE$.apply(Throwable.class);
/*     */     return new Exception$$anon$1(isDef, f, classTag);
/*     */   }
/*     */   
/*     */   public <Ex extends Throwable, T> Object throwableSubtypeToCatcher(PartialFunction pf, ClassTag evidence$2) {
/*     */     Exception$$anonfun$throwableSubtypeToCatcher$2 exception$$anonfun$throwableSubtypeToCatcher$2 = new Exception$$anonfun$throwableSubtypeToCatcher$2(pf);
/*     */     Exception$$anonfun$throwableSubtypeToCatcher$1 exception$$anonfun$throwableSubtypeToCatcher$1 = new Exception$$anonfun$throwableSubtypeToCatcher$1(pf);
/*     */     return new Exception$$anon$1((Function1)exception$$anonfun$throwableSubtypeToCatcher$1, (Function1)exception$$anonfun$throwableSubtypeToCatcher$2, evidence$2);
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$throwableSubtypeToCatcher$1 extends AbstractFunction1<Ex, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public final boolean apply(Throwable x) {
/*     */       return this.pf$1.isDefinedAt(x);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$throwableSubtypeToCatcher$1(PartialFunction pf$1) {}
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$throwableSubtypeToCatcher$2 extends AbstractFunction1<Ex, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public final T apply(Throwable v1) {
/*     */       return (T)this.pf$1.apply(v1);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$throwableSubtypeToCatcher$2(PartialFunction pf$1) {}
/*     */   }
/*     */   
/*     */   public boolean shouldRethrow(Throwable x) {
/*     */     boolean bool;
/*     */     if (x instanceof ControlThrowable) {
/*     */       bool = true;
/*     */     } else if (x instanceof InterruptedException) {
/*     */       bool = true;
/*     */     } else {
/*     */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public final PartialFunction<Throwable, scala.runtime.Nothing$> nothingCatcher() {
/*     */     return this.nothingCatcher;
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x$4) {
/*     */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x$5) {
/*     */       throw x$5;
/*     */     }
/*     */   }
/*     */   
/*     */   public final <T> PartialFunction<Throwable, T> nonFatalCatcher() {
/*     */     return (PartialFunction<Throwable, T>)mkThrowableCatcher((Function1<Throwable, Object>)new Exception$$anonfun$nonFatalCatcher$1(), (Function1<Throwable, ?>)new Exception$$anonfun$nonFatalCatcher$2());
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$nonFatalCatcher$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x0$1) {
/*     */       boolean bool;
/*     */       Option<Throwable> option = NonFatal$.MODULE$.unapply(x0$1);
/*     */       if (option.isEmpty()) {
/*     */         bool = false;
/*     */       } else {
/*     */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$nonFatalCatcher$2 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x$6) {
/*     */       throw x$6;
/*     */     }
/*     */   }
/*     */   
/*     */   public final <T> PartialFunction<Throwable, T> allCatcher() {
/*     */     Exception$$anonfun$allCatcher$2 exception$$anonfun$allCatcher$2 = new Exception$$anonfun$allCatcher$2();
/*     */     Exception$$anonfun$allCatcher$1 exception$$anonfun$allCatcher$1 = new Exception$$anonfun$allCatcher$1();
/*     */     ClassTag classTag = scala.reflect.ClassTag$.MODULE$.apply(Throwable.class);
/*     */     return new Exception$$anon$1((Function1)exception$$anonfun$allCatcher$1, (Function1)exception$$anonfun$allCatcher$2, classTag);
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$allCatcher$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x$7) {
/*     */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$allCatcher$2 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x$8) {
/*     */       throw x$8;
/*     */     }
/*     */   }
/*     */   
/*     */   public final Exception.Catch<scala.runtime.Nothing$> noCatch() {
/* 152 */     return this.noCatch;
/*     */   }
/*     */   
/*     */   public final <T> Exception.Catch<T> allCatch() {
/* 155 */     return (Exception.Catch<T>)(new Exception.Catch(allCatcher(), Exception.Catch$.MODULE$.$lessinit$greater$default$2(), Exception.Catch$.MODULE$.$lessinit$greater$default$3())).withDesc("<everything>");
/*     */   }
/*     */   
/*     */   public final <T> Exception.Catch<T> nonFatalCatch() {
/* 158 */     return (Exception.Catch<T>)(new Exception.Catch(nonFatalCatcher(), Exception.Catch$.MODULE$.$lessinit$greater$default$2(), Exception.Catch$.MODULE$.$lessinit$greater$default$3())).withDesc("<non-fatal>");
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> catching(Seq<Class<?>> exceptions) {
/* 170 */     return (Exception.Catch<T>)(new Exception.Catch(pfFromExceptions(exceptions), Exception.Catch$.MODULE$.$lessinit$greater$default$2(), Exception.Catch$.MODULE$.$lessinit$greater$default$3())).withDesc(((TraversableOnce)exceptions.map((Function1)new Exception$$anonfun$catching$1(), scala.collection.Seq$.MODULE$.canBuildFrom())).mkString(", "));
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$catching$1 extends AbstractFunction1<Class<?>, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Class x$9) {
/* 170 */       return x$9.getName();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> catching(PartialFunction<Throwable, T> c) {
/* 172 */     return new Exception.Catch<T>(c, Exception.Catch$.MODULE$.$lessinit$greater$default$2(), Exception.Catch$.MODULE$.$lessinit$greater$default$3());
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> catchingPromiscuously(Seq<Class<?>> exceptions) {
/* 179 */     return catchingPromiscuously((PartialFunction)pfFromExceptions(exceptions));
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> catchingPromiscuously(PartialFunction<Throwable, T> c) {
/* 180 */     return new Exception.Catch<T>(c, (Option<Exception.Finally>)scala.None$.MODULE$, (Function1<Throwable, Object>)new Exception$$anonfun$catchingPromiscuously$1());
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$catchingPromiscuously$1 extends AbstractFunction1<Throwable, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Throwable x$10) {
/* 180 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public Exception.Catch<BoxedUnit> ignoring(Seq<Class<?>> exceptions) {
/* 184 */     return catching(exceptions).withApply((Function1)new Exception$$anonfun$ignoring$1());
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$ignoring$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Throwable x$11) {}
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<Option<T>> failing(Seq<Class<?>> exceptions) {
/* 188 */     return catching(exceptions).withApply((Function1)new Exception$$anonfun$failing$1());
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$failing$1 extends AbstractFunction1<Throwable, scala.None$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.None$ apply(Throwable x$12) {
/* 188 */       return scala.None$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> failAsValue(Seq<Class<?>> exceptions, Function0 value) {
/* 192 */     return catching(exceptions).withApply((Function1)new Exception$$anonfun$failAsValue$1(value));
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$failAsValue$1 extends AbstractFunction1<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function0 value$1;
/*     */     
/*     */     public final T apply(Throwable x$13) {
/* 192 */       return (T)this.value$1.apply();
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$failAsValue$1(Function0 value$1) {}
/*     */   }
/*     */   
/*     */   public final Exception.Catch scala$util$control$Exception$$fun$1(Function1 f, Seq<Class<?>> exceptions$2) {
/* 204 */     return catching(exceptions$2).withApply(f);
/*     */   }
/*     */   
/*     */   public <T> Exception.By<Function1<Throwable, T>, Exception.Catch<T>> handling(Seq exceptions) {
/* 205 */     return new Exception.By<Function1<Throwable, T>, Exception.Catch<T>>((Function1<Function1<Throwable, T>, Exception.Catch<T>>)new Exception$$anonfun$handling$1(exceptions));
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$handling$1 extends AbstractFunction1<Function1<Throwable, T>, Exception.Catch<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq exceptions$2;
/*     */     
/*     */     public final Exception.Catch<T> apply(Function1 f) {
/* 205 */       return Exception$.MODULE$.scala$util$control$Exception$$fun$1(f, this.exceptions$2);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$handling$1(Seq exceptions$2) {}
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> ultimately(Function0<BoxedUnit> body) {
/* 209 */     return (Exception.Catch)noCatch().andFinally(body);
/*     */   }
/*     */   
/*     */   public final Throwable scala$util$control$Exception$$unwrap$1(Throwable x, Seq<Class<?>> exceptions$3) {
/* 214 */     for (; scala$util$control$Exception$$wouldMatch(x, exceptions$3) && x.getCause() != null; x = x.getCause());
/* 215 */     return x;
/*     */   }
/*     */   
/*     */   public <T> Exception.Catch<T> unwrapping(Seq<Class<?>> exceptions) {
/* 217 */     return catching(exceptions).withApply((Function1)new Exception$$anonfun$unwrapping$1(exceptions));
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$unwrapping$1 extends AbstractFunction1<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq exceptions$3;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(Throwable x) {
/* 217 */       throw Exception$.MODULE$.scala$util$control$Exception$$unwrap$1(x, this.exceptions$3);
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$unwrapping$1(Seq exceptions$3) {}
/*     */   }
/*     */   
/*     */   public boolean scala$util$control$Exception$$wouldMatch(Throwable x, Seq classes) {
/* 222 */     return classes.exists((Function1)new Exception$$anonfun$scala$util$control$Exception$$wouldMatch$1(x));
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$scala$util$control$Exception$$wouldMatch$1 extends AbstractFunction1<Class<?>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Throwable x$15;
/*     */     
/*     */     public final boolean apply(Class x$14) {
/* 222 */       return x$14.isAssignableFrom(this.x$15.getClass());
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$scala$util$control$Exception$$wouldMatch$1(Throwable x$15) {}
/*     */   }
/*     */   
/*     */   private PartialFunction<Throwable, scala.runtime.Nothing$> pfFromExceptions(Seq exceptions) {
/* 225 */     return (PartialFunction<Throwable, scala.runtime.Nothing$>)new Exception$$anonfun$pfFromExceptions$1(exceptions);
/*     */   }
/*     */   
/*     */   public static class Exception$$anonfun$pfFromExceptions$1 extends AbstractPartialFunction<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq exceptions$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 225 */       if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$1))
/* 225 */         throw x1; 
/* 225 */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/* 225 */       if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$1)) {
/* 225 */         bool = true;
/*     */       } else {
/* 225 */         bool = false;
/*     */       } 
/* 225 */       return bool;
/*     */     }
/*     */     
/*     */     public Exception$$anonfun$pfFromExceptions$1(Seq exceptions$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\Exception$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */