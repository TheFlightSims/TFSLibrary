/*     */ package akka.actor;
/*     */ 
/*     */ import akka.routing.Deafen;
/*     */ import akka.routing.Listen;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class FSM$ {
/*     */   public static final FSM$ MODULE$;
/*     */   
/*     */   private FSM$() {
/*  12 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public static class $minus$greater$ {
/*     */     public static final $minus$greater$ MODULE$;
/*     */     
/*     */     public $minus$greater$() {
/* 112 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public <S> Some<Tuple2<S, S>> unapply(Tuple2 in) {
/* 113 */       return new Some(in);
/*     */     }
/*     */   }
/*     */   
/*     */   public class FSM$$anon$1 implements PartialFunction<Tuple2<S, S>, BoxedUnit> {
/*     */     private final Function2 transitionHandler$1;
/*     */     
/*     */     public <A1 extends Tuple2<S, S>, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 440 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Tuple2<S, S>, C> andThen(Function1 k) {
/* 440 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Tuple2<S, S>, Option<BoxedUnit>> lift() {
/* 440 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1 extends Tuple2<S, S>, B1> B1 applyOrElse(Object x, Function1 default) {
/* 440 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Tuple2<S, S>, Object> runWith(Function1 action) {
/* 440 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 440 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 440 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 440 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 440 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 440 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 440 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 440 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 440 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 440 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 440 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 440 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 440 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 440 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 440 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 440 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 440 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 440 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 440 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 440 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 440 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 440 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 440 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 440 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 440 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/* 440 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 440 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 440 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 440 */       return Function1.class.toString((Function1)this);
/*     */     }
/*     */     
/*     */     public FSM$$anon$1(FSM $outer, Function2 transitionHandler$1) {
/* 440 */       Function1.class.$init$((Function1)this);
/* 440 */       PartialFunction.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Tuple2 in) {
/* 441 */       return true;
/*     */     }
/*     */     
/*     */     public void apply(Tuple2 in) {
/* 442 */       this.transitionHandler$1.apply(in._1(), in._2());
/*     */     }
/*     */   }
/*     */   
/*     */   public class FSM$$anonfun$register$1 extends AbstractFunction0<Option<FiniteDuration>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object name$1;
/*     */     
/*     */     public final Option<FiniteDuration> apply() {
/* 519 */       return (Option<FiniteDuration>)this.$outer.akka$actor$FSM$$stateTimeouts().apply(this.name$1);
/*     */     }
/*     */     
/*     */     public FSM$$anonfun$register$1(FSM $outer, Object name$1) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractPartialFunction<FSM.Event<D>, FSM.State<S, D>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends FSM.Event<D>, B1> B1 applyOrElse(FSM.Event x2, Function1 default) {
/*     */       Object object;
/* 529 */       FSM.Event event = x2;
/* 529 */       if (event != null) {
/* 530 */         Object value = event.event();
/* 531 */         this.$outer.log().warning((new StringBuilder()).append("unhandled event ").append(value).append(" in state ").append(this.$outer.stateName()).toString());
/* 532 */         object = this.$outer.stay();
/*     */       } else {
/*     */         object = default.apply(x2);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(FSM.Event x2) {
/*     */       boolean bool;
/*     */       FSM.Event event = x2;
/*     */       if (event != null) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public $anonfun$1(FSM $outer) {}
/*     */   }
/*     */   
/*     */   public class FSM$$anonfun$handleTransition$1 extends AbstractFunction1<PartialFunction<Tuple2<S, S>, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Tuple2 tuple$1;
/*     */     
/*     */     public final void apply(PartialFunction te) {
/* 547 */       if (te.isDefinedAt(this.tuple$1))
/* 547 */         te.apply(this.tuple$1); 
/*     */     }
/*     */     
/*     */     public FSM$$anonfun$handleTransition$1(FSM $outer, Tuple2 tuple$1) {}
/*     */   }
/*     */   
/*     */   public class FSM$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/*     */       BoxedUnit boxedUnit;
/* 555 */       Object object = x3;
/* 556 */       if (object instanceof FSM.TimeoutMarker) {
/* 556 */         FSM.TimeoutMarker timeoutMarker = (FSM.TimeoutMarker)object;
/* 556 */         long gen = timeoutMarker.generation();
/* 558 */         FSM$class.akka$actor$FSM$$processMsg(this.$outer, this.$outer.StateTimeout(), "state timeout");
/* 558 */         boxedUnit = (this.$outer.akka$actor$FSM$$generation() == gen) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 560 */       } else if (object instanceof FSM.Timer) {
/* 560 */         FSM.Timer timer = (FSM.Timer)object;
/* 560 */         String name = timer.name();
/* 560 */         Object msg = timer.msg();
/* 560 */         boolean repeat = timer.repeat();
/* 560 */         int gen = timer.generation();
/* 562 */         if (this.$outer.akka$actor$FSM$$timeoutFuture().isDefined()) {
/* 563 */           ((Cancellable)this.$outer.akka$actor$FSM$$timeoutFuture().get()).cancel();
/* 564 */           this.$outer.akka$actor$FSM$$timeoutFuture_$eq((Option<Cancellable>)scala.None$.MODULE$);
/*     */         } 
/* 566 */         this.$outer.akka$actor$FSM$$generation_$eq(this.$outer.akka$actor$FSM$$generation() + 1L);
/* 567 */         repeat ? BoxedUnit.UNIT : 
/* 568 */           this.$outer.akka$actor$FSM$$timers().$minus$eq(name);
/* 570 */         FSM$class.akka$actor$FSM$$processMsg(this.$outer, msg, timer);
/* 570 */         boxedUnit = (this.$outer.akka$actor$FSM$$timers().contains(name) && ((FSM.Timer)this.$outer.akka$actor$FSM$$timers().apply(name)).generation() == gen) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 572 */       } else if (object instanceof FSM.SubscribeTransitionCallBack) {
/* 572 */         FSM.SubscribeTransitionCallBack subscribeTransitionCallBack = (FSM.SubscribeTransitionCallBack)object;
/* 572 */         ActorRef actorRef = subscribeTransitionCallBack.actorRef();
/* 574 */         this.$outer.listeners().add(actorRef);
/* 576 */         package$.MODULE$.actorRef2Scala(actorRef).$bang(new FSM.CurrentState(this.$outer.self(), this.$outer.akka$actor$FSM$$currentState().stateName()), this.$outer.self());
/* 576 */         boxedUnit = BoxedUnit.UNIT;
/* 577 */       } else if (object instanceof Listen) {
/* 577 */         Listen listen = (Listen)object;
/* 577 */         ActorRef actorRef = listen.listener();
/* 579 */         this.$outer.listeners().add(actorRef);
/* 581 */         package$.MODULE$.actorRef2Scala(actorRef).$bang(new FSM.CurrentState(this.$outer.self(), this.$outer.akka$actor$FSM$$currentState().stateName()), this.$outer.self());
/* 581 */         boxedUnit = BoxedUnit.UNIT;
/* 582 */       } else if (object instanceof FSM.UnsubscribeTransitionCallBack) {
/* 582 */         FSM.UnsubscribeTransitionCallBack unsubscribeTransitionCallBack = (FSM.UnsubscribeTransitionCallBack)object;
/* 582 */         ActorRef actorRef = unsubscribeTransitionCallBack.actorRef();
/* 583 */         this.$outer.listeners().remove(actorRef);
/* 583 */         boxedUnit = BoxedUnit.UNIT;
/* 584 */       } else if (object instanceof Deafen) {
/* 584 */         Deafen deafen = (Deafen)object;
/* 584 */         ActorRef actorRef = deafen.listener();
/* 585 */         this.$outer.listeners().remove(actorRef);
/* 585 */         boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/* 587 */         if (this.$outer.akka$actor$FSM$$timeoutFuture().isDefined()) {
/* 588 */           ((Cancellable)this.$outer.akka$actor$FSM$$timeoutFuture().get()).cancel();
/* 589 */           this.$outer.akka$actor$FSM$$timeoutFuture_$eq((Option<Cancellable>)scala.None$.MODULE$);
/*     */         } 
/* 591 */         this.$outer.akka$actor$FSM$$generation_$eq(this.$outer.akka$actor$FSM$$generation() + 1L);
/* 592 */         FSM$class.akka$actor$FSM$$processMsg(this.$outer, object, this.$outer.sender());
/* 592 */         boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */       return (B1)boxedUnit;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x3) {
/*     */       boolean bool;
/*     */       Object object = x3;
/*     */       if (object instanceof FSM.TimeoutMarker) {
/*     */         bool = true;
/*     */       } else if (object instanceof FSM.Timer) {
/*     */         bool = true;
/*     */       } else if (object instanceof FSM.SubscribeTransitionCallBack) {
/*     */         bool = true;
/*     */       } else if (object instanceof Listen) {
/*     */         bool = true;
/*     */       } else if (object instanceof FSM.UnsubscribeTransitionCallBack) {
/*     */         bool = true;
/*     */       } else if (object instanceof Deafen) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public FSM$$anonfun$receive$1(FSM $outer) {}
/*     */   }
/*     */   
/*     */   public class FSM$$anonfun$applyState$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object r) {
/* 616 */       package$.MODULE$.actorRef2Scala(this.$outer.sender()).$bang(r, this.$outer.self());
/*     */     }
/*     */     
/*     */     public FSM$$anonfun$applyState$1(FSM $outer) {}
/*     */   }
/*     */   
/*     */   public class FSM$$anonfun$makeTransition$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object r) {
/* 626 */       package$.MODULE$.actorRef2Scala(this.$outer.sender()).$bang(r, this.$outer.self());
/*     */     }
/*     */     
/*     */     public FSM$$anonfun$makeTransition$1(FSM $outer) {}
/*     */   }
/*     */   
/*     */   public class FSM$$anonfun$terminate$1 extends AbstractFunction1<FSM.Timer, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(FSM.Timer timer) {
/* 666 */       timer.cancel();
/*     */     }
/*     */     
/*     */     public FSM$$anonfun$terminate$1(FSM $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\FSM$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */