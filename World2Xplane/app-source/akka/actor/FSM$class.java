/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Map$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.package$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class FSM$class {
/*     */   public static void $init$(FSM<S, D> $this) {
/* 272 */     $this.akka$actor$FSM$_setter_$Event_$eq(FSM.Event$.MODULE$);
/* 273 */     $this.akka$actor$FSM$_setter_$StopEvent_$eq(FSM.StopEvent$.MODULE$);
/* 279 */     $this.akka$actor$FSM$_setter_$$minus$greater_$eq(FSM.$minus$greater$.MODULE$);
/* 284 */     $this.akka$actor$FSM$_setter_$StateTimeout_$eq(FSM.StateTimeout$.MODULE$);
/* 500 */     $this.akka$actor$FSM$$timeoutFuture_$eq((Option<Cancellable>)None$.MODULE$);
/* 502 */     $this.akka$actor$FSM$$generation_$eq(0L);
/* 507 */     $this.akka$actor$FSM$_setter_$akka$actor$FSM$$timers_$eq((Map)Map$.MODULE$.apply((Seq)Nil$.MODULE$));
/* 508 */     $this.akka$actor$FSM$_setter_$akka$actor$FSM$$timerGen_$eq(package$.MODULE$.Iterator().from(0));
/* 513 */     $this.akka$actor$FSM$_setter_$akka$actor$FSM$$stateFunctions_$eq((Map)Map$.MODULE$.apply((Seq)Nil$.MODULE$));
/* 514 */     $this.akka$actor$FSM$_setter_$akka$actor$FSM$$stateTimeouts_$eq((Map)Map$.MODULE$.apply((Seq)Nil$.MODULE$));
/* 529 */     $this.akka$actor$FSM$_setter_$akka$actor$FSM$$handleEventDefault_$eq((PartialFunction)new FSM.$anonfun$1($this));
/* 534 */     $this.akka$actor$FSM$$handleEvent_$eq($this.akka$actor$FSM$$handleEventDefault());
/* 539 */     $this.akka$actor$FSM$$terminateEvent_$eq(FSM.NullFunction$.MODULE$);
/* 544 */     $this.akka$actor$FSM$$transitionEvent_$eq((List<PartialFunction<Tuple2<S, S>, BoxedUnit>>)Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public static final FiniteDuration when$default$2(FSM $this) {
/*     */     null;
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static final void when(FSM $this, Object stateName, FiniteDuration stateTimeout, PartialFunction stateFunction) {
/*     */     register($this, stateName, stateFunction, Option$.MODULE$.apply(stateTimeout));
/*     */   }
/*     */   
/*     */   public static final Option startWith$default$3(FSM $this) {
/*     */     return (Option)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public static final void startWith(FSM $this, Object stateName, Object stateData, Option<FiniteDuration> timeout) {
/*     */     $this.akka$actor$FSM$$currentState_$eq(new FSM.State<Object, Object>(stateName, stateData, timeout, FSM.State$.MODULE$.apply$default$4(), FSM.State$.MODULE$.apply$default$5()));
/*     */   }
/*     */   
/*     */   public static final FSM.State goto(FSM $this, Object nextStateName) {
/*     */     return new FSM.State<Object, Object>(nextStateName, $this.akka$actor$FSM$$currentState().stateData(), FSM.State$.MODULE$.apply$default$3(), FSM.State$.MODULE$.apply$default$4(), FSM.State$.MODULE$.apply$default$5());
/*     */   }
/*     */   
/*     */   public static final FSM.State stay(FSM $this) {
/*     */     return $this.goto($this.akka$actor$FSM$$currentState().stateName());
/*     */   }
/*     */   
/*     */   public static final FSM.State stop(FSM $this) {
/*     */     return $this.stop(FSM.Normal$.MODULE$);
/*     */   }
/*     */   
/*     */   public static final FSM.State stop(FSM $this, FSM.Reason reason) {
/*     */     return $this.stop(reason, $this.akka$actor$FSM$$currentState().stateData());
/*     */   }
/*     */   
/*     */   public static final FSM.State stop(FSM $this, FSM.Reason reason, Object stateData) {
/*     */     return $this.stay().using(stateData).withStopReason(reason);
/*     */   }
/*     */   
/*     */   public static final FSM.TransformHelper transform(FSM<S, D> $this, PartialFunction<FSM.Event<D>, FSM.State<S, D>> func) {
/*     */     return new FSM.TransformHelper($this, func);
/*     */   }
/*     */   
/*     */   public static final boolean setTimer$default$4(FSM $this) {
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static final void setTimer(FSM $this, String name, Object msg, FiniteDuration timeout, boolean repeat) {
/*     */     if ($this.debugEvent())
/*     */       $this.log().debug((new StringBuilder()).append("setting ").append(repeat ? "repeating " : "").append("timer '").append(name).append("'/").append(timeout).append(": ").append(msg).toString()); 
/*     */     if ($this.akka$actor$FSM$$timers().contains(name))
/*     */       ((FSM.Timer)$this.akka$actor$FSM$$timers().apply(name)).cancel(); 
/*     */     FSM.Timer timer = new FSM.Timer(name, msg, repeat, BoxesRunTime.unboxToInt($this.akka$actor$FSM$$timerGen().next()), $this.context());
/*     */     timer.schedule($this.self(), timeout);
/*     */     $this.akka$actor$FSM$$timers().update(name, timer);
/*     */   }
/*     */   
/*     */   public static final void cancelTimer(FSM $this, String name) {
/*     */     if ($this.debugEvent())
/*     */       $this.log().debug((new StringBuilder()).append("canceling timer '").append(name).append("'").toString()); 
/*     */     if ($this.akka$actor$FSM$$timers().contains(name)) {
/*     */       ((FSM.Timer)$this.akka$actor$FSM$$timers().apply(name)).cancel();
/*     */       $this.akka$actor$FSM$$timers().$minus$eq(name);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final boolean isTimerActive(FSM $this, String name) {
/*     */     return $this.akka$actor$FSM$$timers().contains(name);
/*     */   }
/*     */   
/*     */   public static final void setStateTimeout(FSM $this, Object state, Option timeout) {
/*     */     $this.akka$actor$FSM$$stateTimeouts().update(state, timeout);
/*     */   }
/*     */   
/*     */   public static final boolean isStateTimerActive(FSM $this) {
/*     */     return $this.akka$actor$FSM$$timeoutFuture().isDefined();
/*     */   }
/*     */   
/*     */   public static final void onTransition(FSM $this, PartialFunction transitionHandler) {
/*     */     $this.akka$actor$FSM$$transitionEvent_$eq((List)$this.akka$actor$FSM$$transitionEvent().$colon$plus(transitionHandler, List$.MODULE$.canBuildFrom()));
/*     */   }
/*     */   
/*     */   public static final PartialFunction total2pf(FSM $this, Function2 transitionHandler) {
/*     */     return new FSM$$anon$1($this, (FSM<S, D>)transitionHandler);
/*     */   }
/*     */   
/*     */   public static final void onTermination(FSM $this, PartialFunction terminationHandler) {
/*     */     $this.akka$actor$FSM$$terminateEvent_$eq(terminationHandler);
/*     */   }
/*     */   
/*     */   public static final void whenUnhandled(FSM $this, PartialFunction stateFunction) {
/*     */     $this.akka$actor$FSM$$handleEvent_$eq(stateFunction.orElse($this.akka$actor$FSM$$handleEventDefault()));
/*     */   }
/*     */   
/*     */   public static final void initialize(FSM $this) {
/*     */     $this.makeTransition($this.akka$actor$FSM$$currentState());
/*     */   }
/*     */   
/*     */   public static final Object stateName(FSM $this) {
/*     */     return $this.akka$actor$FSM$$currentState().stateName();
/*     */   }
/*     */   
/*     */   public static final Object stateData(FSM $this) {
/*     */     return $this.akka$actor$FSM$$currentState().stateData();
/*     */   }
/*     */   
/*     */   public static final Object nextStateData(FSM $this) {
/*     */     FSM.State state = $this.akka$actor$FSM$$nextState();
/*     */     if (state == null)
/*     */       throw new IllegalStateException("nextStateData is only available during onTransition"); 
/*     */     return state.stateData();
/*     */   }
/*     */   
/*     */   public static boolean debugEvent(FSM $this) {
/*     */     return false;
/*     */   }
/*     */   
/*     */   private static void register(FSM<S, D> $this, Object name, PartialFunction function, Option timeout) {
/*     */     if ($this.akka$actor$FSM$$stateFunctions().contains(name)) {
/*     */       $this.akka$actor$FSM$$stateFunctions().update(name, ((PartialFunction)$this.akka$actor$FSM$$stateFunctions().apply(name)).orElse(function));
/*     */       $this.akka$actor$FSM$$stateTimeouts().update(name, timeout.orElse((Function0)new FSM$$anonfun$register$1($this, (FSM<S, D>)name)));
/*     */     } else {
/*     */       $this.akka$actor$FSM$$stateFunctions().update(name, function);
/*     */       $this.akka$actor$FSM$$stateTimeouts().update(name, timeout);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void handleTransition(FSM $this, Object prev, Object next) {
/* 546 */     Tuple2 tuple = new Tuple2(prev, next);
/* 547 */     $this.akka$actor$FSM$$transitionEvent().foreach((Function1)new FSM$$anonfun$handleTransition$1($this, (FSM<S, D>)tuple));
/*     */   }
/*     */   
/*     */   public static PartialFunction receive(FSM<S, D> $this) {
/* 555 */     return (PartialFunction)new FSM$$anonfun$receive$1($this);
/*     */   }
/*     */   
/*     */   public static void akka$actor$FSM$$processMsg(FSM $this, Object value, Object source) {
/* 597 */     FSM.Event<?> event = $this.Event().apply(value, $this.akka$actor$FSM$$currentState().stateData());
/* 598 */     $this.processEvent(event, source);
/*     */   }
/*     */   
/*     */   public static void processEvent(FSM<S, D> $this, FSM.Event event, Object source) {
/* 602 */     PartialFunction stateFunc = (PartialFunction)$this.akka$actor$FSM$$stateFunctions().apply($this.akka$actor$FSM$$currentState().stateName());
/* 603 */     FSM.State<S, D> nextState = stateFunc.isDefinedAt(event) ? 
/* 604 */       (FSM.State)stateFunc.apply(event) : 
/*     */       
/* 607 */       (FSM.State)$this.akka$actor$FSM$$handleEvent().apply(event);
/* 609 */     $this.applyState(nextState);
/*     */   }
/*     */   
/*     */   public static void applyState(FSM<S, D> $this, FSM.State<S, D> nextState) {
/* 613 */     Option<FSM.Reason> option1 = nextState.stopReason();
/* 614 */     Option<FSM.Reason> option2 = option1;
/* 614 */     if (None$.MODULE$ == null) {
/* 614 */       if (option2 != null) {
/* 616 */         nextState.replies().reverse().foreach((Function1)new FSM$$anonfun$applyState$1($this));
/* 617 */         terminate($this, nextState);
/* 618 */         $this.context().stop($this.self());
/*     */       } 
/*     */     } else {
/*     */       if (None$.MODULE$.equals(option2)) {
/*     */         $this.makeTransition(nextState);
/*     */         BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */         return;
/*     */       } 
/*     */       nextState.replies().reverse().foreach((Function1)new FSM$$anonfun$applyState$1($this));
/*     */       terminate($this, nextState);
/* 618 */       $this.context().stop($this.self());
/*     */     } 
/*     */     $this.makeTransition(nextState);
/*     */     BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */   }
/*     */   
/*     */   public static void makeTransition(FSM<S, D> $this, FSM.State<S, D> nextState) {
/* 623 */     if ($this.akka$actor$FSM$$stateFunctions().contains(nextState.stateName())) {
/* 626 */       nextState.replies().reverse().foreach((Function1)new FSM$$anonfun$makeTransition$1($this));
/* 627 */       if (!BoxesRunTime.equals($this.akka$actor$FSM$$currentState().stateName(), nextState.stateName())) {
/* 628 */         $this.akka$actor$FSM$$nextState_$eq(nextState);
/* 629 */         handleTransition($this, $this.akka$actor$FSM$$currentState().stateName(), nextState.stateName());
/* 630 */         $this.gossip(new FSM.Transition($this.self(), $this.akka$actor$FSM$$currentState().stateName(), nextState.stateName()), $this.self());
/* 631 */         null;
/* 631 */         $this.akka$actor$FSM$$nextState_$eq((FSM.State<S, D>)null);
/*     */       } 
/* 633 */       $this.akka$actor$FSM$$currentState_$eq(nextState);
/* 634 */       Option timeout = $this.akka$actor$FSM$$currentState().timeout().isDefined() ? $this.akka$actor$FSM$$currentState().timeout() : (Option)$this.akka$actor$FSM$$stateTimeouts().apply($this.akka$actor$FSM$$currentState().stateName());
/* 635 */       if (timeout.isDefined()) {
/* 636 */         FiniteDuration t = (FiniteDuration)timeout.get();
/* 637 */         if (t.isFinite() && t.length() >= 0L)
/* 639 */           $this.akka$actor$FSM$$timeoutFuture_$eq((Option<Cancellable>)new Some($this.context().system().scheduler().scheduleOnce(t, $this.self(), new FSM.TimeoutMarker($this.akka$actor$FSM$$generation()), (ExecutionContext)$this.context().dispatcher(), $this.self()))); 
/*     */       } 
/*     */     } else {
/*     */       terminate($this, $this.stay().withStopReason(new FSM.Failure((new StringOps(Predef$.MODULE$.augmentString("Next state %s does not exist"))).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { nextState.stateName() })))));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void postStop(FSM $this) {
/* 658 */     terminate($this, $this.stay().withStopReason(FSM.Shutdown$.MODULE$));
/* 659 */     $this.akka$actor$FSM$$super$postStop();
/*     */   }
/*     */   
/*     */   private static void terminate(FSM<S, D> $this, FSM.State<S, D> nextState) {
/* 663 */     if ($this.akka$actor$FSM$$currentState().stopReason().isEmpty()) {
/* 664 */       FSM.Reason reason = (FSM.Reason)nextState.stopReason().get();
/* 665 */       $this.logTermination(reason);
/* 666 */       $this.akka$actor$FSM$$timers().values().foreach((Function1)new FSM$$anonfun$terminate$1($this));
/* 667 */       $this.akka$actor$FSM$$timers().clear();
/* 668 */       $this.akka$actor$FSM$$currentState_$eq(nextState);
/* 670 */       FSM.StopEvent<?, ?> stopEvent = $this.StopEvent().apply(reason, $this.akka$actor$FSM$$currentState().stateName(), $this.akka$actor$FSM$$currentState().stateData());
/* 671 */       if ($this.akka$actor$FSM$$terminateEvent().isDefinedAt(stopEvent))
/* 672 */         $this.akka$actor$FSM$$terminateEvent().apply(stopEvent); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void logTermination(FSM $this, FSM.Reason reason) {
/* 681 */     boolean bool = false;
/* 681 */     null;
/* 681 */     FSM.Failure failure = null;
/*     */     FSM.Reason reason1 = reason;
/* 681 */     if (reason1 instanceof FSM.Failure) {
/* 681 */       bool = true;
/* 681 */       failure = (FSM.Failure)reason1;
/* 681 */       Object ex = failure.cause();
/* 681 */       if (ex instanceof Throwable) {
/* 681 */         Throwable throwable = (Throwable)ex;
/* 681 */         $this.log().error(throwable, "terminating due to Failure");
/* 681 */         BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     if (bool) {
/* 682 */       Object msg = failure.cause();
/* 682 */       if (msg instanceof Object) {
/* 682 */         Object object = msg;
/* 682 */         $this.log().error(object.toString());
/* 682 */         BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */         return;
/*     */       } 
/*     */     } 
/* 683 */     BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\FSM$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */