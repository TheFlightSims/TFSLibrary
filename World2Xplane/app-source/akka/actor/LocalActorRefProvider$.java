/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.sysmsg.Failed;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.util.Switch;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public final class LocalActorRefProvider$ {
/*     */   public static final LocalActorRefProvider$ MODULE$;
/*     */   
/*     */   private LocalActorRefProvider$() {
/* 362 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction0<Function1<ActorPath, DeadLetterActorRef>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function1<ActorPath, DeadLetterActorRef> apply() {
/* 464 */       return (Function1<ActorPath, DeadLetterActorRef>)new LocalActorRefProvider$$anonfun$1$$anonfun$apply$1(this);
/*     */     }
/*     */     
/*     */     public $anonfun$1(LocalActorRefProvider $outer) {}
/*     */     
/*     */     public class LocalActorRefProvider$$anonfun$1$$anonfun$apply$1 extends AbstractFunction1<ActorPath, DeadLetterActorRef> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final DeadLetterActorRef apply(ActorPath p) {
/* 464 */         return new DeadLetterActorRef(this.$outer.akka$actor$LocalActorRefProvider$$anonfun$$$outer(), p, this.$outer.akka$actor$LocalActorRefProvider$$anonfun$$$outer().eventStream());
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anonfun$1$$anonfun$apply$1(LocalActorRefProvider.$anonfun$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anon$2 extends InternalActorRef implements MinimalActorRef {
/*     */     private final Switch stopped;
/*     */     
/*     */     private volatile Option<Throwable> akka$actor$LocalActorRefProvider$$anon$$causeOfTermination;
/*     */     
/*     */     private final ActorPath path;
/*     */     
/*     */     public InternalActorRef getParent() {
/* 481 */       return MinimalActorRef$class.getParent(this);
/*     */     }
/*     */     
/*     */     public InternalActorRef getChild(Iterator names) {
/* 481 */       return MinimalActorRef$class.getChild(this, names);
/*     */     }
/*     */     
/*     */     public void start() {
/* 481 */       MinimalActorRef$class.start(this);
/*     */     }
/*     */     
/*     */     public void suspend() {
/* 481 */       MinimalActorRef$class.suspend(this);
/*     */     }
/*     */     
/*     */     public void resume(Throwable causedByFailure) {
/* 481 */       MinimalActorRef$class.resume(this, causedByFailure);
/*     */     }
/*     */     
/*     */     public void restart(Throwable cause) {
/* 481 */       MinimalActorRef$class.restart(this, cause);
/*     */     }
/*     */     
/*     */     public Object writeReplace() throws ObjectStreamException {
/* 481 */       return MinimalActorRef$class.writeReplace(this);
/*     */     }
/*     */     
/*     */     public final boolean isLocal() {
/* 481 */       return LocalRef$class.isLocal(this);
/*     */     }
/*     */     
/*     */     public $anon$2(LocalActorRefProvider $outer) {
/* 481 */       LocalRef$class.$init$(this);
/* 481 */       MinimalActorRef$class.$init$(this);
/* 482 */       this.stopped = new Switch(false);
/* 485 */       this.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination = (Option<Throwable>)scala.None$.MODULE$;
/* 487 */       this.path = $outer.rootPath().$div("bubble-walker");
/*     */     }
/*     */     
/*     */     private Switch stopped() {
/*     */       return this.stopped;
/*     */     }
/*     */     
/*     */     public Option<Throwable> akka$actor$LocalActorRefProvider$$anon$$causeOfTermination() {
/*     */       return this.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination;
/*     */     }
/*     */     
/*     */     public void akka$actor$LocalActorRefProvider$$anon$$causeOfTermination_$eq(Option<Throwable> x$1) {
/*     */       this.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination = x$1;
/*     */     }
/*     */     
/*     */     public ActorPath path() {
/* 487 */       return this.path;
/*     */     }
/*     */     
/*     */     public ActorRefProvider provider() {
/* 489 */       return this.$outer;
/*     */     }
/*     */     
/*     */     public void stop() {
/* 491 */       stopped().switchOn((Function0)new LocalActorRefProvider$$anon$2$$anonfun$stop$1(this));
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$2$$anonfun$stop$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply() {
/* 491 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/* 491 */         this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().terminationPromise().complete((Try)this.$outer.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination().map((Function1)new LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$1(this)).getOrElse((Function0)new LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$2(this)));
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$2$$anonfun$stop$1(LocalActorRefProvider.$anon$2 $outer) {}
/*     */       
/*     */       public class LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction1<Throwable, Failure<scala.runtime.Nothing$>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Failure<scala.runtime.Nothing$> apply(Throwable x$2) {
/* 491 */           return new Failure(x$2);
/*     */         }
/*     */         
/*     */         public LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$1(LocalActorRefProvider$$anon$2$$anonfun$stop$1 $outer) {}
/*     */       }
/*     */       
/*     */       public class LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction0<Success<BoxedUnit>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Success<BoxedUnit> apply() {
/* 491 */           return new Success(BoxedUnit.UNIT);
/*     */         }
/*     */         
/*     */         public LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$2(LocalActorRefProvider$$anon$2$$anonfun$stop$1 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean isTerminated() {
/* 492 */       return stopped().isOn();
/*     */     }
/*     */     
/*     */     public void $bang(Object message, ActorRef sender) {
/* 494 */       stopped().ifOff((Function0)new LocalActorRefProvider$$anon$2$$anonfun$$bang$1(this, message));
/*     */     }
/*     */     
/*     */     public ActorRef $bang$default$2(Object message) {
/* 494 */       return Actor$.MODULE$.noSender();
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$2$$anonfun$$bang$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object message$1;
/*     */       
/*     */       public final void apply() {
/* 494 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/* 494 */         Object object = this.message$1;
/* 495 */         if (object == null)
/* 495 */           throw new InvalidMessageException("Message is null"); 
/* 496 */         (new String[3])[0] = "";
/* 496 */         (new String[3])[1] = " received unexpected message [";
/* 496 */         (new String[3])[2] = "]";
/* 496 */         this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().log().error((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.$outer, this.message$1 })));
/* 496 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$2$$anonfun$$bang$1(LocalActorRefProvider.$anon$2 $outer, Object message$1) {}
/*     */     }
/*     */     
/*     */     public void sendSystemMessage(SystemMessage message) {
/* 499 */       stopped().ifOff(
/* 500 */           (Function0)new LocalActorRefProvider$$anon$2$$anonfun$sendSystemMessage$1(this, message));
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$2$$anonfun$sendSystemMessage$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SystemMessage message$2;
/*     */       
/*     */       public final void apply() {
/* 500 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/* 500 */         SystemMessage systemMessage = this.message$2;
/* 501 */         if (systemMessage instanceof Failed) {
/* 501 */           Failed failed = (Failed)systemMessage;
/* 501 */           ActorRef child = failed.child();
/* 501 */           Throwable ex = failed.cause();
/* 502 */           (new String[2])[0] = "guardian ";
/* 502 */           (new String[2])[1] = " failed, shutting down!";
/* 502 */           this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().log().error(ex, (new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { child })));
/* 503 */           this.$outer.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination_$eq((Option<Throwable>)new Some(ex));
/* 504 */           ((InternalActorRef)child).stop();
/* 504 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 505 */         } else if (systemMessage instanceof akka.dispatch.sysmsg.Supervise) {
/* 505 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 506 */         } else if (systemMessage instanceof akka.dispatch.sysmsg.DeathWatchNotification) {
/* 506 */           this.$outer.stop();
/* 506 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/* 507 */           (new String[3])[0] = "";
/* 507 */           (new String[3])[1] = " received unexpected system message [";
/* 507 */           (new String[3])[2] = "]";
/* 507 */           this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().log().error((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.$outer, this.message$2 })));
/* 507 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$2$$anonfun$sendSystemMessage$1(LocalActorRefProvider.$anon$2 $outer, SystemMessage message$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$rootGuardianStrategy$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x4, Function1 default) {
/* 543 */       Throwable throwable = x4;
/* 545 */       this.$outer.log().error(throwable, "guardian failed, shutting down system");
/* 546 */       return (B1)SupervisorStrategy.Stop$.MODULE$;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x4) {
/*     */       Throwable throwable = x4;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$rootGuardianStrategy$1(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anon$1 extends LocalActorRef {
/*     */     public LocalActorRefProvider$$anon$1(LocalActorRefProvider $outer) {
/* 564 */       super(
/* 565 */           $outer.akka$actor$LocalActorRefProvider$$system(), 
/* 566 */           Props$.MODULE$.apply(LocalActorRefProvider.Guardian.class, (Seq<Object>)scala.Predef$.MODULE$.genericWrapArray(new Object[] { $outer.rootGuardianStrategy() })), $outer.akka$actor$LocalActorRefProvider$$defaultDispatcher(), 
/* 568 */           $outer.akka$actor$LocalActorRefProvider$$defaultMailbox(), 
/* 569 */           $outer.theOneWhoWalksTheBubblesOfSpaceTime(), 
/* 570 */           $outer.rootPath());
/*     */     }
/*     */     
/*     */     public InternalActorRef getParent() {
/* 571 */       return this;
/*     */     }
/*     */     
/*     */     public InternalActorRef getSingleChild(String name) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: ldc 'temp'
/*     */       //   4: aload_2
/*     */       //   5: astore_3
/*     */       //   6: dup
/*     */       //   7: ifnonnull -> 18
/*     */       //   10: pop
/*     */       //   11: aload_3
/*     */       //   12: ifnull -> 25
/*     */       //   15: goto -> 37
/*     */       //   18: aload_3
/*     */       //   19: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   22: ifeq -> 37
/*     */       //   25: aload_0
/*     */       //   26: getfield $outer : Lakka/actor/LocalActorRefProvider;
/*     */       //   29: invokevirtual tempContainer : ()Lakka/actor/VirtualPathContainer;
/*     */       //   32: astore #4
/*     */       //   34: goto -> 105
/*     */       //   37: ldc 'deadLetters'
/*     */       //   39: aload_2
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 75
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 75
/*     */       //   63: aload_0
/*     */       //   64: getfield $outer : Lakka/actor/LocalActorRefProvider;
/*     */       //   67: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */       //   70: astore #4
/*     */       //   72: goto -> 105
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/actor/LocalActorRefProvider;
/*     */       //   79: invokevirtual akka$actor$LocalActorRefProvider$$extraNames : ()Lscala/collection/immutable/Map;
/*     */       //   82: aload_2
/*     */       //   83: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*     */       //   88: new akka/actor/LocalActorRefProvider$$anon$1$$anonfun$getSingleChild$1
/*     */       //   91: dup
/*     */       //   92: aload_0
/*     */       //   93: aload_2
/*     */       //   94: invokespecial <init> : (Lakka/actor/LocalActorRefProvider$$anon$1;Ljava/lang/String;)V
/*     */       //   97: invokevirtual getOrElse : (Lscala/Function0;)Ljava/lang/Object;
/*     */       //   100: checkcast akka/actor/InternalActorRef
/*     */       //   103: astore #4
/*     */       //   105: aload #4
/*     */       //   107: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #572	-> 0
/*     */       //   #573	-> 2
/*     */       //   #574	-> 37
/*     */       //   #575	-> 75
/*     */       //   #572	-> 105
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	108	0	this	Lakka/actor/LocalActorRefProvider$$anon$1;
/*     */       //   0	108	1	name	Ljava/lang/String;
/*     */     }
/*     */     
/*     */     public InternalActorRef akka$actor$LocalActorRefProvider$$anon$$super$getSingleChild(String name) {
/* 575 */       return super.getSingleChild(name);
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$1$$anonfun$getSingleChild$1 extends AbstractFunction0<InternalActorRef> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String x1$1;
/*     */       
/*     */       public final InternalActorRef apply() {
/* 575 */         return this.$outer.akka$actor$LocalActorRefProvider$$anon$$super$getSingleChild(this.x1$1);
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$1$$anonfun$getSingleChild$1(LocalActorRefProvider$$anon$1 $outer, String x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$registerTempActor$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 607 */       return "cannot registerTempActor() with anything not obtained from tempPath()";
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$registerTempActor$1(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$unregisterTempActor$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 612 */       return "cannot unregisterTempActor() with anything not obtained from tempPath()";
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$unregisterTempActor$1(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$actorOf$1 extends AbstractFunction1<Deploy, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorPath path$1;
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$actorOf$1(LocalActorRefProvider $outer, ActorPath path$1) {}
/*     */     
/*     */     public final void apply(Deploy d) {
/* 694 */       akka.routing.NoRouter$ noRouter$ = akka.routing.NoRouter$.MODULE$;
/* 694 */       if (d.routerConfig() == null) {
/* 694 */         d.routerConfig();
/* 694 */         if (noRouter$ != null) {
/* 695 */           this.$outer.log().warning("Configuration says that [{}] should be a router, but code disagrees. Remove the config or add a routerConfig to its Props.", this.path$1);
/*     */           return;
/*     */         } 
/*     */       } else if (!d.routerConfig().equals(noRouter$)) {
/* 695 */         this.$outer.log().warning("Configuration says that [{}] should be a router, but code disagrees. Remove the config or add a routerConfig to its Props.", this.path$1);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$2 extends AbstractFunction0<Iterator<Deploy>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Option deploy$1;
/*     */     
/*     */     public final Iterator<Deploy> apply() {
/* 729 */       return this.deploy$1.iterator();
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$2(LocalActorRefProvider $outer, Option deploy$1) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$3 extends AbstractFunction0<Iterator<Deploy>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Option lookup$1;
/*     */     
/*     */     public final Iterator<Deploy> apply() {
/* 729 */       return this.lookup$1.iterator();
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$3(LocalActorRefProvider $outer, Option lookup$1) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$4 extends AbstractFunction2<Deploy, Deploy, Deploy> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Deploy apply(Deploy a, Deploy b) {
/* 729 */       return b.withFallback(a);
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$4(LocalActorRefProvider $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LocalActorRefProvider$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */