/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Function;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class SupervisorStrategy$ implements SupervisorStrategyLowPriorityImplicits {
/*     */   public static final SupervisorStrategy$ MODULE$;
/*     */   
/*     */   private final PartialFunction<Throwable, SupervisorStrategy.Directive> defaultDecider;
/*     */   
/*     */   private final SupervisorStrategy defaultStrategy;
/*     */   
/*     */   private final SupervisorStrategy stoppingStrategy;
/*     */   
/*     */   private final Function1<Object, SupervisorStrategy.Escalate$> escalateDefault;
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> seqCauseDirective2Decider(Iterable trapExit) {
/*  97 */     return SupervisorStrategyLowPriorityImplicits$class.seqCauseDirective2Decider(this, trapExit);
/*     */   }
/*     */   
/*     */   private SupervisorStrategy$() {
/*  97 */     MODULE$ = this;
/*  97 */     SupervisorStrategyLowPriorityImplicits$class.$init$(this);
/* 154 */     this.defaultDecider = (PartialFunction<Throwable, SupervisorStrategy.Directive>)new SupervisorStrategy.$anonfun$1();
/* 166 */     this.defaultStrategy = 
/* 167 */       new OneForOneStrategy(OneForOneStrategy$.MODULE$.apply$default$1(), OneForOneStrategy$.MODULE$.apply$default$2(), OneForOneStrategy$.MODULE$.apply$default$3(), defaultDecider());
/* 174 */     this.stoppingStrategy = 
/*     */       
/* 178 */       new OneForOneStrategy(OneForOneStrategy$.MODULE$.apply$default$1(), OneForOneStrategy$.MODULE$.apply$default$2(), OneForOneStrategy$.MODULE$.apply$default$3(), stoppingDecider$1());
/* 243 */     this.escalateDefault = (Function1<Object, SupervisorStrategy.Escalate$>)new SupervisorStrategy.$anonfun$3();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy.Resume$ resume() {
/*     */     return SupervisorStrategy.Resume$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy.Restart$ restart() {
/*     */     return SupervisorStrategy.Restart$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy.Stop$ stop() {
/*     */     return SupervisorStrategy.Stop$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy.Escalate$ escalate() {
/*     */     return SupervisorStrategy.Escalate$.MODULE$;
/*     */   }
/*     */   
/*     */   public final PartialFunction<Throwable, SupervisorStrategy.Directive> defaultDecider() {
/*     */     return this.defaultDecider;
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */       Object object;
/*     */       Throwable throwable = x1;
/*     */       if (throwable instanceof ActorInitializationException) {
/*     */         object = SupervisorStrategy.Stop$.MODULE$;
/*     */       } else if (throwable instanceof ActorKilledException) {
/*     */         object = SupervisorStrategy.Stop$.MODULE$;
/*     */       } else if (throwable instanceof DeathPactException) {
/*     */         object = SupervisorStrategy.Stop$.MODULE$;
/*     */       } else if (throwable instanceof Exception) {
/*     */         object = SupervisorStrategy.Restart$.MODULE$;
/*     */       } else {
/*     */         object = default.apply(x1);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/*     */       Throwable throwable = x1;
/*     */       if (throwable instanceof ActorInitializationException) {
/*     */         bool = true;
/*     */       } else if (throwable instanceof ActorKilledException) {
/*     */         bool = true;
/*     */       } else if (throwable instanceof DeathPactException) {
/*     */         bool = true;
/*     */       } else if (throwable instanceof Exception) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public final SupervisorStrategy defaultStrategy() {
/*     */     return this.defaultStrategy;
/*     */   }
/*     */   
/*     */   public final SupervisorStrategy stoppingStrategy() {
/*     */     return this.stoppingStrategy;
/*     */   }
/*     */   
/*     */   private final PartialFunction stoppingDecider$1() {
/*     */     return (PartialFunction)new SupervisorStrategy$$anonfun$stoppingDecider$1$1();
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$stoppingDecider$1$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       Object object;
/*     */       Throwable throwable = x2;
/*     */       if (throwable instanceof Exception) {
/*     */         object = SupervisorStrategy.Stop$.MODULE$;
/*     */       } else {
/*     */         object = default.apply(x2);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       boolean bool;
/*     */       Throwable throwable = x2;
/*     */       if (throwable instanceof Exception) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> seqThrowable2Decider(Seq<Class<? extends Throwable>> trapExit) {
/*     */     return makeDecider(trapExit);
/*     */   }
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> makeDecider(Seq trapExit) {
/*     */     return (PartialFunction<Throwable, SupervisorStrategy.Directive>)new SupervisorStrategy$$anonfun$makeDecider$1(trapExit);
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$makeDecider$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq trapExit$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x3, Function1 default) {
/*     */       Throwable throwable = x3;
/*     */       return (B1)(this.trapExit$1.exists((Function1)new SupervisorStrategy$$anonfun$makeDecider$1$$anonfun$applyOrElse$2(this, throwable)) ? SupervisorStrategy.Restart$.MODULE$ : SupervisorStrategy.Escalate$.MODULE$);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x3) {
/*     */       Throwable throwable = x3;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public SupervisorStrategy$$anonfun$makeDecider$1(Seq trapExit$1) {}
/*     */     
/*     */     public class SupervisorStrategy$$anonfun$makeDecider$1$$anonfun$applyOrElse$2 extends AbstractFunction1<Class<? extends Throwable>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Throwable x1$1;
/*     */       
/*     */       public final boolean apply(Class x$1) {
/*     */         return x$1.isInstance(this.x1$1);
/*     */       }
/*     */       
/*     */       public SupervisorStrategy$$anonfun$makeDecider$1$$anonfun$applyOrElse$2(SupervisorStrategy$$anonfun$makeDecider$1 $outer, Throwable x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> makeDecider(Iterable trapExit) {
/*     */     return makeDecider(akka.japi.Util$.MODULE$.immutableSeq(trapExit));
/*     */   }
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> makeDecider(Iterable<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> flat) {
/*     */     Seq<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> directives = sort(flat);
/*     */     return (PartialFunction<Throwable, SupervisorStrategy.Directive>)new SupervisorStrategy$$anonfun$makeDecider$2(directives);
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$makeDecider$2 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq directives$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x4, Function1 default) {
/*     */       Throwable throwable = x4;
/*     */       return (B1)this.directives$1.collectFirst((PartialFunction)new SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$1(this, throwable)).getOrElse((Function0)new SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$3(this));
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x4) {
/*     */       Throwable throwable = x4;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public SupervisorStrategy$$anonfun$makeDecider$2(Seq directives$1) {}
/*     */     
/*     */     public class SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$1 extends AbstractPartialFunction<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, SupervisorStrategy.Directive> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Throwable x1$2;
/*     */       
/*     */       public final <A1 extends Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, B1> B1 applyOrElse(Tuple2 x5, Function1 default) {
/*     */         Tuple2 tuple2 = x5;
/*     */         if (tuple2 != null) {
/*     */           Class c = (Class)tuple2._1();
/*     */           SupervisorStrategy.Directive d = (SupervisorStrategy.Directive)tuple2._2();
/*     */           if (c.isInstance(this.x1$2))
/*     */             return (B1)d; 
/*     */         } 
/*     */         return (B1)default.apply(x5);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Tuple2 x5) {
/*     */         Tuple2 tuple2 = x5;
/*     */         if (tuple2 != null) {
/*     */           Class c = (Class)tuple2._1();
/*     */           if (c.isInstance(this.x1$2))
/*     */             return true; 
/*     */         } 
/*     */         return false;
/*     */       }
/*     */       
/*     */       public SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$1(SupervisorStrategy$$anonfun$makeDecider$2 $outer, Throwable x1$2) {}
/*     */     }
/*     */     
/*     */     public class SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$3 extends AbstractFunction0<SupervisorStrategy.Escalate$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final SupervisorStrategy.Escalate$ apply() {
/*     */         return SupervisorStrategy.Escalate$.MODULE$;
/*     */       }
/*     */       
/*     */       public SupervisorStrategy$$anonfun$makeDecider$2$$anonfun$applyOrElse$3(SupervisorStrategy$$anonfun$makeDecider$2 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> makeDecider(Function func) {
/*     */     return (PartialFunction<Throwable, SupervisorStrategy.Directive>)new SupervisorStrategy$$anonfun$makeDecider$3(func);
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$makeDecider$3 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function func$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x6, Function1 default) {
/*     */       Throwable throwable = x6;
/*     */       return (B1)this.func$1.apply(throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x6) {
/*     */       Throwable throwable = x6;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public SupervisorStrategy$$anonfun$makeDecider$3(Function func$1) {}
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> sort(Iterable in) {
/*     */     ArrayBuffer arrayBuffer = new ArrayBuffer(in.size());
/*     */     return (Seq<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>>)((TraversableLike)in.$div$colon(arrayBuffer, (Function2)new SupervisorStrategy$$anonfun$sort$1())).to(scala.Predef$.MODULE$.fallbackStringCanBuildFrom());
/*     */   }
/*     */   
/*     */   public static class SupervisorStrategy$$anonfun$sort$1 extends AbstractFunction2<ArrayBuffer<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>>, Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, ArrayBuffer<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayBuffer<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> apply(ArrayBuffer<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> buf, Tuple2 ca) {
/*     */       int i = buf.indexWhere((Function1)new $anonfun$2(this, ca));
/*     */       switch (i) {
/*     */         default:
/*     */           (new Tuple2[1])[0] = ca;
/*     */           buf.insert(i, (Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */           return buf;
/*     */         case -1:
/*     */           break;
/*     */       } 
/*     */       (new Tuple2[1])[0] = ca;
/*     */       buf.append((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */       return buf;
/*     */     }
/*     */     
/*     */     public class $anonfun$2 extends AbstractFunction1<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 ca$1;
/*     */       
/*     */       public final boolean apply(Tuple2 x$3) {
/*     */         return ((Class)x$3._1()).isAssignableFrom((Class)this.ca$1._1());
/*     */       }
/*     */       
/*     */       public $anonfun$2(SupervisorStrategy$$anonfun$sort$1 $outer, Tuple2 ca$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Option<Duration> withinTimeRangeOption(Duration withinTimeRange) {
/*     */     return (withinTimeRange.isFinite() && withinTimeRange.$greater$eq(scala.concurrent.duration.Duration$.MODULE$.Zero())) ? (Option<Duration>)new Some(withinTimeRange) : (Option<Duration>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Object> maxNrOfRetriesOption(int maxNrOfRetries) {
/*     */     return (maxNrOfRetries < 0) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(BoxesRunTime.boxToInteger(maxNrOfRetries));
/*     */   }
/*     */   
/*     */   public Function1<Object, SupervisorStrategy.Escalate$> escalateDefault() {
/* 243 */     return this.escalateDefault;
/*     */   }
/*     */   
/*     */   public static class $anonfun$3 extends AbstractFunction1<Object, SupervisorStrategy.Escalate$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final SupervisorStrategy.Escalate$ apply(Object x$4) {
/* 243 */       return SupervisorStrategy.Escalate$.MODULE$;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SupervisorStrategy$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */