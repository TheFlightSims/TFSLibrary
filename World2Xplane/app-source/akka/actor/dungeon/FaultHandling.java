/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefScope;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.PostRestartException;
/*     */ import akka.actor.PreRestartException;
/*     */ import akka.dispatch.sysmsg.DeathWatchNotification;
/*     */ import akka.dispatch.sysmsg.Failed;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.Logging;
/*     */ import akka.event.Logging$;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005UbAC\001\003!\003\r\tA\002\005\0020\tia)Y;mi\"\013g\016\0327j]\036T!a\001\003\002\017\021,hnZ3p]*\021QAB\001\006C\016$xN\035\006\002\017\005!\021m[6b'\t\001\021\002\005\002\013\0335\t1BC\001\r\003\025\0318-\0317b\023\tq1B\001\004B]f\024VM\032\005\006!\001!\tAE\001\007I%t\027\016\036\023\004\001Q\t1\003\005\002\013)%\021Qc\003\002\005+:LG\017C\003\030\001\021%!#A\ntkN\004XM\0343O_:\024VmY;sg&4X\rC\003\032\001\021%!#\001\nsKN,X.\032(p]J+7-\036:tSZ,\007bB\016\001\001\004%I\001H\001\b?\032\f\027\016\\3e+\005i\002C\001\020 \033\005!\021B\001\021\005\005!\t5\r^8s%\0264\007b\002\022\001\001\004%IaI\001\f?\032\f\027\016\\3e?\022*\027\017\006\002\024I!9Q%IA\001\002\004i\022a\001=%c!1q\005\001Q!\nu\t\001b\0304bS2,G\r\t\005\006S\001!IAK\001\tSN4\025-\0337fIV\t1\006\005\002\013Y%\021Qf\003\002\b\005>|G.Z1o\021\025y\003\001\"\0031\003%\031X\r\036$bS2,G\r\006\002\024c!)!G\fa\001;\005Y\001/\032:qKR\024\030\r^8s\021\025!\004\001\"\003\023\003-\031G.Z1s\r\006LG.\0323\t\013I\002A\021\002\017\t\013]\002A\021\003\035\002\033\031\fW\017\034;SK\016\024X-\031;f)\t\031\022\bC\003;m\001\0071(A\003dCV\034X\r\005\002=\t:\021QH\021\b\003}\005k\021a\020\006\003\001F\ta\001\020:p_Rt\024\"\001\007\n\005\r[\021a\0029bG.\fw-Z\005\003\013\032\023\021\002\0265s_^\f'\r\\3\013\005\r[\001\"\002%\001\t#\021\022\001\0044bk2$8+^:qK:$\007\"\002&\001\t#Y\025a\0034bk2$(+Z:v[\026$\"a\005'\t\0135K\005\031A\036\002\037\r\fWo]3e\005f4\025-\0337ve\026DQa\024\001\005\022I\t1BZ1vYR\034%/Z1uK\")\021\013\001C\005%\005aa-\0338jg\"\034%/Z1uK\")1\013\001C\t%\005IA/\032:nS:\fG/\032\005\006+\002!)AV\001\024Q\006tG\r\\3J]Z|7.\032$bS2,(/\032\013\004']\013\007\"\002-U\001\004I\026\001F2iS2$'/\0328O_R$vnU;ta\026tG\rE\002[?vi\021a\027\006\0039v\013\021\"[7nkR\f'\r\\3\013\005y[\021AC2pY2,7\r^5p]&\021\001m\027\002\t\023R,'/\0312mK\")!\r\026a\001w\005\tA\017C\003e\001\021%!#A\bgS:L7\017\033+fe6Lg.\031;f\021\0251\007\001\"\003h\00391\027N\\5tQJ+7M]3bi\026$2a\0055j\021\025QT\r1\001<\021\025QW\r1\001l\003-1\027-\0337fI\006\033Go\034:\021\005ya\027BA7\005\005\025\t5\r^8s\021\025y\007\001\"\006q\0035A\027M\0343mK\032\013\027\016\\;sKR\0211#\035\005\006e:\004\ra]\001\002MB\021A/_\007\002k*\021ao^\001\007gf\034Xn]4\013\005a4\021\001\0033jgB\fGo\0315\n\005i,(A\002$bS2,G\rC\003}\001\021UQ0A\013iC:$G.Z\"iS2$G+\032:nS:\fG/\0323\025\005Mq\b\"B@|\001\004i\022!B2iS2$\007bBA\002\001\021U\021QA\001%Q\006tG\r\\3O_:4\025\r^1m\037JLe\016^3seV\004H/\0323Fq\016,\007\017^5p]R!\021qAA\023!\025\tI!a\b\024\035\021\tY!!\007\017\t\0055\0211\003\b\004{\005=\021bAA\t\027\005!Q\017^5m\023\021\t)\"a\006\002\017\r|g\016\036:pY*\031\021\021C\006\n\t\005m\021QD\001\n\013b\034W\r\035;j_:TA!!\006\002\030%!\021\021EA\022\005\035\031\025\r^2iKJTA!a\007\002\036!A\021qEA\001\001\004\tI#A\003uQVt7\016E\003\013\003WY4#C\002\002.-\021\021BR;oGRLwN\\\031\021\007y\t\t$C\002\0024\021\021\021\"Q2u_J\034U\r\0347")
/*     */ public interface FaultHandling {
/*     */   ActorRef akka$actor$dungeon$FaultHandling$$_failed();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$dungeon$FaultHandling$$_failed_$eq(ActorRef paramActorRef);
/*     */   
/*     */   void faultRecreate(Throwable paramThrowable);
/*     */   
/*     */   void faultSuspend();
/*     */   
/*     */   void faultResume(Throwable paramThrowable);
/*     */   
/*     */   void faultCreate();
/*     */   
/*     */   void terminate();
/*     */   
/*     */   void handleInvokeFailure(Iterable<ActorRef> paramIterable, Throwable paramThrowable);
/*     */   
/*     */   void handleFailure(Failed paramFailed);
/*     */   
/*     */   void handleChildTerminated(ActorRef paramActorRef);
/*     */   
/*     */   PartialFunction<Throwable, BoxedUnit> handleNonFatalOrInterruptedException(Function1<Throwable, BoxedUnit> paramFunction1);
/*     */   
/*     */   public class FaultHandling$$anonfun$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Actor failedActor$1;
/*     */     
/*     */     private final Option optionalMessage$1;
/*     */     
/*     */     private final Throwable cause$2;
/*     */     
/*     */     public FaultHandling$$anonfun$1(ActorCell $outer, Actor failedActor$1, Option optionalMessage$1, Throwable cause$2) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/*  69 */       PreRestartException ex = new PreRestartException((ActorRef)this.$outer.self(), e, this.cause$2, this.optionalMessage$1);
/*  70 */       this.$outer.publish((Logging.LogEvent)new Logging.Error((Throwable)ex, this.$outer.self().path().toString(), this.$outer.clazz(this.failedActor$1), e.getMessage()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$faultRecreate$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*  75 */       return (new StringBuilder()).append("mailbox must be suspended during restart, status=").append(BoxesRunTime.boxToInteger(this.$outer.mailbox().status())).toString();
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$faultRecreate$1(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$faultCreate$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 121 */       return (new StringBuilder()).append("mailbox must be suspended during failed creation, status=").append(BoxesRunTime.boxToInteger(this.$outer.mailbox().status())).toString();
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$faultCreate$1(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$faultCreate$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef actor) {
/* 128 */       this.$outer.stop(actor);
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$faultCreate$2(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$2 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public FaultHandling$$anonfun$2(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 138 */       this.$outer.handleInvokeFailure((Iterable)Nil$.MODULE$, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$terminate$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef actor) {
/* 150 */       this.$outer.stop(actor);
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$terminate$1(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$terminate$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef x0$1) {
/* 154 */       ActorRef actorRef = x0$1;
/* 155 */       if (actorRef instanceof ActorRefScope) {
/* 155 */         ActorRef actorRef1 = actorRef;
/* 155 */         if (!((ActorRefScope)actorRef1).isLocal()) {
/* 155 */           this.$outer.self().sendSystemMessage((SystemMessage)new DeathWatchNotification(actorRef1, true, false));
/* 155 */           BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */           return;
/*     */         } 
/*     */       } 
/* 156 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$terminate$2(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$3 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Throwable t$1;
/*     */     
/*     */     public FaultHandling$$anonfun$3(ActorCell $outer, Throwable t$1) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 196 */       this.$outer.publish((Logging.LogEvent)new Logging.Error(e, this.$outer.self().path().toString(), this.$outer.clazz(this.$outer.actor()), (
/* 197 */             new StringBuilder()).append("emergency stop: exception in failure handling for ").append(this.t$1.getClass()).append(Logging$.MODULE$.stackTraceFor(this.t$1)).toString()));
/*     */       try {
/* 198 */         this.$outer.children().foreach((Function1)new FaultHandling$$anonfun$3$$anonfun$apply$1(this));
/*     */         return;
/*     */       } finally {
/* 199 */         FaultHandling$class.akka$actor$dungeon$FaultHandling$$finishTerminate(this.$outer);
/*     */       } 
/*     */     }
/*     */     
/*     */     public class FaultHandling$$anonfun$3$$anonfun$apply$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(ActorRef actor) {
/*     */         this.$outer.akka$actor$dungeon$FaultHandling$$anonfun$$$outer().stop(actor);
/*     */       }
/*     */       
/*     */       public FaultHandling$$anonfun$3$$anonfun$apply$1(FaultHandling$$anonfun$3 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$4 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Actor a$1;
/*     */     
/*     */     public final void apply(Throwable e) {
/* 211 */       this.$outer.publish((Logging.LogEvent)new Logging.Error(e, this.$outer.self().path().toString(), this.$outer.clazz(this.a$1), e.getMessage()));
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$4(ActorCell $outer, Actor a$1) {}
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$finishRecreate$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Throwable cause$1;
/*     */     
/*     */     public final Actor freshActor$1;
/*     */     
/*     */     public FaultHandling$$anonfun$finishRecreate$1(ActorCell $outer, Throwable cause$1, Actor freshActor$1) {}
/*     */     
/*     */     public final void apply(ActorRef child) {
/*     */       try {
/* 243 */         ((InternalActorRef)child).restart(this.cause$1);
/*     */       } finally {
/* 243 */         Exception exception = null;
/* 244 */         PartialFunction catchExpr5 = this.$outer.handleNonFatalOrInterruptedException((Function1)new FaultHandling$$anonfun$finishRecreate$1$$anonfun$5(this, child));
/*     */       } 
/*     */     }
/*     */     
/*     */     public class FaultHandling$$anonfun$finishRecreate$1$$anonfun$5 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ActorRef child$1;
/*     */       
/*     */       public FaultHandling$$anonfun$finishRecreate$1$$anonfun$5(FaultHandling$$anonfun$finishRecreate$1 $outer, ActorRef child$1) {}
/*     */       
/*     */       public final void apply(Throwable e) {
/* 245 */         this.$outer.akka$actor$dungeon$FaultHandling$$anonfun$$$outer().publish((Logging.LogEvent)new Logging.Error(e, this.$outer.akka$actor$dungeon$FaultHandling$$anonfun$$$outer().self().path().toString(), this.$outer.akka$actor$dungeon$FaultHandling$$anonfun$$$outer().clazz(this.$outer.freshActor$1), (new StringBuilder()).append("restarting ").append(this.child$1).toString()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$6 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Throwable cause$1;
/*     */     
/*     */     private final Iterable survivors$1;
/*     */     
/*     */     public FaultHandling$$anonfun$6(ActorCell $outer, Throwable cause$1, Iterable survivors$1) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 248 */       this.$outer.clearActorFields(this.$outer.actor());
/* 249 */       this.$outer.handleInvokeFailure(this.survivors$1, (Throwable)new PostRestartException((ActorRef)this.$outer.self(), e, this.cause$1));
/*     */     }
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$7 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public FaultHandling$$anonfun$7(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 281 */       this.$outer.publish((Logging.LogEvent)new Logging.Error(e, this.$outer.self().path().toString(), this.$outer.clazz(this.$outer.actor()), "handleChildTerminated failed"));
/* 282 */       this.$outer.handleInvokeFailure((Iterable)Nil$.MODULE$, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public class FaultHandling$$anonfun$handleNonFatalOrInterruptedException$1 extends AbstractPartialFunction.mcVL.sp<Throwable> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 thunk$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x8, Function1 default) {
/*     */       Object object;
/* 297 */       Throwable throwable = x8;
/* 298 */       if (throwable instanceof InterruptedException) {
/* 298 */         InterruptedException interruptedException = (InterruptedException)throwable;
/* 299 */         this.thunk$1.apply(interruptedException);
/* 300 */         Thread.currentThread().interrupt();
/* 300 */         object = BoxedUnit.UNIT;
/*     */       } else {
/* 301 */         Option option = NonFatal$.MODULE$.unapply(throwable);
/* 301 */         if (option.isEmpty()) {
/*     */           object = default.apply(x8);
/*     */         } else {
/* 301 */           Throwable e = (Throwable)option.get();
/* 302 */           object = this.thunk$1.apply(e);
/*     */         } 
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x8) {
/*     */       boolean bool;
/*     */       Throwable throwable = x8;
/*     */       if (throwable instanceof InterruptedException) {
/*     */         bool = true;
/*     */       } else {
/*     */         Option option = NonFatal$.MODULE$.unapply(throwable);
/*     */         if (option.isEmpty()) {
/*     */           bool = false;
/*     */         } else {
/* 302 */           bool = true;
/*     */         } 
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public FaultHandling$$anonfun$handleNonFatalOrInterruptedException$1(ActorCell $outer, Function1 thunk$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\FaultHandling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */