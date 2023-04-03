/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Procedure;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.concurrent.forkjoin.ThreadLocalRandom;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ActorCell$ {
/*     */   public static final ActorCell$ MODULE$;
/*     */   
/*     */   private final ThreadLocal<List<ActorContext>> contextStack;
/*     */   
/*     */   private final Cancellable emptyCancellable;
/*     */   
/*     */   private final List<PartialFunction<Object, BoxedUnit>> emptyBehaviorStack;
/*     */   
/*     */   private final Set<ActorRef> emptyActorRefSet;
/*     */   
/*     */   private final Props terminatedProps;
/*     */   
/*     */   private final int undefinedUid;
/*     */   
/*     */   private final int DefaultState;
/*     */   
/*     */   private final int SuspendedState;
/*     */   
/*     */   private final int SuspendedWaitForChildrenState;
/*     */   
/*     */   private ActorCell$() {
/* 324 */     MODULE$ = this;
/* 325 */     this.contextStack = new ActorCell.$anon$1();
/* 329 */     this.emptyCancellable = new ActorCell.$anon$2();
/* 334 */     this.emptyBehaviorStack = (List<PartialFunction<Object, BoxedUnit>>)scala.collection.immutable.Nil$.MODULE$;
/* 336 */     this.emptyActorRefSet = (Set<ActorRef>)scala.collection.immutable.HashSet$.MODULE$.empty();
/* 338 */     this.terminatedProps = Props$.MODULE$.apply((Function0<Actor>)new ActorCell.$anonfun$1(), scala.reflect.ClassTag$.MODULE$.apply(Actor.class));
/*     */   }
/*     */   
/*     */   public ThreadLocal<List<ActorContext>> contextStack() {
/*     */     return this.contextStack;
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends ThreadLocal<List<ActorContext>> {
/*     */     public List<ActorContext> initialValue() {
/*     */       return (List<ActorContext>)scala.collection.immutable.Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public final Cancellable emptyCancellable() {
/*     */     return this.emptyCancellable;
/*     */   }
/*     */   
/*     */   public static class $anon$2 implements Cancellable {
/*     */     public boolean isCancelled() {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public boolean cancel() {
/*     */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public final List<PartialFunction<Object, BoxedUnit>> emptyBehaviorStack() {
/*     */     return this.emptyBehaviorStack;
/*     */   }
/*     */   
/*     */   public final Set<ActorRef> emptyActorRefSet() {
/*     */     return this.emptyActorRefSet;
/*     */   }
/*     */   
/*     */   public final Props terminatedProps() {
/* 338 */     return this.terminatedProps;
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction0<Actor> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Actor apply() {
/* 338 */       throw new IllegalActorStateException("This Actor has been terminated");
/*     */     }
/*     */   }
/*     */   
/*     */   public final int undefinedUid() {
/* 340 */     return 0;
/*     */   }
/*     */   
/*     */   public final int newUid() {
/*     */     while (true) {
/* 345 */       int uid = ThreadLocalRandom.current().nextInt();
/* 346 */       if (uid != 0)
/* 347 */         return uid; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Tuple2<String, Object> splitNameAndUid(String name) {
/* 351 */     int i = name.indexOf('#');
/* 352 */     return (i < 0) ? new Tuple2(name, BoxesRunTime.boxToInteger(0)) : 
/* 353 */       new Tuple2(name.substring(0, i), BoxesRunTime.boxToInteger(scala.Predef$.MODULE$.Integer2int(Integer.valueOf(name.substring(i + 1)))));
/*     */   }
/*     */   
/*     */   public final int DefaultState() {
/* 356 */     return 0;
/*     */   }
/*     */   
/*     */   public final int SuspendedState() {
/* 357 */     return 1;
/*     */   }
/*     */   
/*     */   public final int SuspendedWaitForChildrenState() {
/* 358 */     return 2;
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$2 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ActorCell$$anonfun$2(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 467 */       this.$outer.handleInvokeFailure((Iterable<ActorRef>)scala.collection.immutable.Nil$.MODULE$, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$3 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ActorCell$$anonfun$3(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 491 */       this.$outer.handleInvokeFailure((Iterable<ActorRef>)scala.collection.immutable.Nil$.MODULE$, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$become$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Procedure behavior$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/* 534 */       Object object = x3;
/* 534 */       this.behavior$1.apply(object);
/* 534 */       return (B1)BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x3) {
/* 534 */       Object object = x3;
/* 534 */       return true;
/*     */     }
/*     */     
/*     */     public ActorCell$$anonfun$become$1(ActorCell $outer, Procedure behavior$1) {}
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$create$1 extends AbstractFunction1<ActorInitializationException, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(ActorInitializationException x$6) {
/* 575 */       throw x$6;
/*     */     }
/*     */     
/*     */     public ActorCell$$anonfun$create$1(ActorCell $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorCell$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */