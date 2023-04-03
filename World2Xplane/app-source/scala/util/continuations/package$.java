/*     */ package scala.util.continuations;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class package$ {
/*     */   public static final package$ MODULE$;
/*     */   
/*     */   private package$() {
/*  75 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B, C> ControlContext<A, B, C> shift(Function1 fun) {
/* 122 */     throw new NoSuchMethodException("this code has to be compiled with the Scala continuations plugin enabled");
/*     */   }
/*     */   
/*     */   public <A, C> C reset(Function0 ctx) {
/* 133 */     ControlContext<A, B, C> ctxR = (ControlContext)ctx.apply();
/* 137 */     package$$anonfun$reset$1 package$$anonfun$reset$1 = new package$$anonfun$reset$1();
/* 137 */     ControlContext$$anonfun$foreach$1 controlContext$$anonfun$foreach$1 = new ControlContext$$anonfun$foreach$1(ctxR);
/* 137 */     return ctxR.isTrivial() ? (C)ctxR.x() : ((ctxR.fun() == null) ? (C)ctxR.x() : (C)ctxR.fun().apply(package$$anonfun$reset$1, controlContext$$anonfun$foreach$1));
/*     */   }
/*     */   
/*     */   public static class package$$anonfun$reset$1 extends AbstractFunction1<A, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final A apply(Object x) {
/* 137 */       return (A)x;
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> A reset0(Function0<ControlContext<?, ?, A>> ctx) {
/* 140 */     return reset(ctx);
/*     */   }
/*     */   
/*     */   public <A> A run(Function0 ctx) {
/* 143 */     ControlContext<A, B, C> ctxR = (ControlContext)ctx.apply();
/* 147 */     package$$anonfun$run$1 package$$anonfun$run$1 = new package$$anonfun$run$1();
/* 147 */     ControlContext$$anonfun$foreach$1 controlContext$$anonfun$foreach$1 = new ControlContext$$anonfun$foreach$1(ctxR);
/* 147 */     return ctxR.isTrivial() ? (A)ctxR.x() : ((ctxR.fun() == null) ? (A)BoxedUnit.UNIT : (A)ctxR.fun().apply(package$$anonfun$run$1, controlContext$$anonfun$foreach$1));
/*     */   }
/*     */   
/*     */   public static class package$$anonfun$run$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object x) {}
/*     */   }
/*     */   
/*     */   public <A, B> ControlContext<A, B, B> shiftUnit0(Object x) {
/* 155 */     return shiftUnitR((A)x);
/*     */   }
/*     */   
/*     */   public <A, B, C> ControlContext<A, B, C> shiftUnit(Object x) {
/* 159 */     throw new NoSuchMethodException("this code has to be compiled with the Scala continuations plugin enabled");
/*     */   }
/*     */   
/*     */   public <A, B, C> ControlContext<A, B, C> reify(Function0 ctx) {
/* 166 */     throw new NoSuchMethodException("this code has to be compiled with the Scala continuations plugin enabled");
/*     */   }
/*     */   
/*     */   public <A, B> ControlContext<A, B, B> shiftUnitR(Object x) {
/* 170 */     return new ControlContext<A, B, B>(null, (A)x);
/*     */   }
/*     */   
/*     */   public <A, B, C> ControlContext<A, B, C> shiftR(Function1 fun) {
/* 180 */     return new ControlContext<A, B, C>((Function2<Function1<A, B>, Function1<Exception, B>, C>)new package$$anonfun$shiftR$1(fun), null);
/*     */   }
/*     */   
/*     */   public static class package$$anonfun$shiftR$1 extends AbstractFunction2<Function1<A, B>, Function1<Exception, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 fun$1;
/*     */     
/*     */     public final C apply(Function1 f, Function1 g) {
/* 180 */       return (C)this.fun$1.apply(f);
/*     */     }
/*     */     
/*     */     public package$$anonfun$shiftR$1(Function1 fun$1) {}
/*     */   }
/*     */   
/*     */   public <A, B, C> ControlContext<A, B, C> reifyR(Function0 ctx) {
/* 184 */     return (ControlContext<A, B, C>)ctx.apply();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\continuations\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */