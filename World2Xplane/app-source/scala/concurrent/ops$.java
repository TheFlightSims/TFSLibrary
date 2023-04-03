/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.util.Either;
/*    */ 
/*    */ public final class ops$ {
/*    */   public static final ops$ MODULE$;
/*    */   
/*    */   private final FutureTaskRunner defaultRunner;
/*    */   
/*    */   private ops$() {
/* 20 */     MODULE$ = this;
/* 21 */     this.defaultRunner = TaskRunners$.MODULE$.threadRunner();
/*    */   }
/*    */   
/*    */   public FutureTaskRunner defaultRunner() {
/* 21 */     return this.defaultRunner;
/*    */   }
/*    */   
/*    */   public <A> Either<Throwable, A> scala$concurrent$ops$$tryCatch(Function0 body) {
/* 28 */     return scala.util.control.Exception$.MODULE$.allCatch().either(body);
/*    */   }
/*    */   
/*    */   private <T extends Throwable, A> A getOrThrow(Either x) {
/* 31 */     return (A)x.fold((Function1)new ops$$anonfun$getOrThrow$1(), (Function1)new ops$$anonfun$getOrThrow$2());
/*    */   }
/*    */   
/*    */   public static class ops$$anonfun$getOrThrow$2 extends AbstractFunction1<A, A> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final A apply(Object x) {
/* 31 */       return (A)scala.Predef$.MODULE$.identity(x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ops$$anonfun$getOrThrow$1 extends AbstractFunction1<T, scala.runtime.Nothing$> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final scala.runtime.Nothing$ apply(Throwable x$1) {
/* 31 */       throw x$1;
/*    */     }
/*    */   }
/*    */   
/*    */   public TaskRunner spawn$default$2(Function0 p) {
/* 37 */     return defaultRunner();
/*    */   }
/*    */   
/*    */   public void spawn(Function0<?> p, TaskRunner runner) {
/* 38 */     runner.execute(runner.functionAsTask(p));
/*    */   }
/*    */   
/*    */   public <A> FutureTaskRunner future$default$2(Function0 p) {
/* 47 */     return defaultRunner();
/*    */   }
/*    */   
/*    */   public <A> Function0<A> future(Function0<?> p, FutureTaskRunner runner) {
/* 48 */     return runner.futureAsFunction(runner.submit(runner.functionAsTask(p)));
/*    */   }
/*    */   
/*    */   public <A, B> TaskRunner par$default$3(Function0 xp, Function0 yp) {
/* 59 */     return defaultRunner();
/*    */   }
/*    */   
/*    */   public <A, B> Tuple2<A, B> par(Function0 xp, Function0 yp, TaskRunner runner) {
/* 60 */     SyncVar<Either> y = new SyncVar();
/* 61 */     ops$$anonfun$par$1 ops$$anonfun$par$1 = new ops$$anonfun$par$1(yp, y);
/* 61 */     runner.execute(runner.functionAsTask((Function0<?>)ops$$anonfun$par$1));
/* 62 */     return new Tuple2(xp.apply(), ((Either)y.get()).fold((Function1)new ops$$anonfun$getOrThrow$1(), (Function1)new ops$$anonfun$getOrThrow$2()));
/*    */   }
/*    */   
/*    */   public static class ops$$anonfun$par$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function0 yp$1;
/*    */     
/*    */     public final SyncVar y$1;
/*    */     
/*    */     public final void apply() {
/*    */       this.y$1.set(ops$.MODULE$.scala$concurrent$ops$$tryCatch(this.yp$1));
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/*    */       this.y$1.set(ops$.MODULE$.scala$concurrent$ops$$tryCatch(this.yp$1));
/*    */     }
/*    */     
/*    */     public ops$$anonfun$par$1(Function0 yp$1, SyncVar y$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ops$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */