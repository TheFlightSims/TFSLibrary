/*     */ package akka.actor;
/*     */ 
/*     */ import akka.actor.dsl.Inbox;
/*     */ import akka.util.Timeout;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.Awaitable;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055q!B\001\003\021\0039\021\001C!di>\024Hi\025'\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005!\t5\r^8s\tNc5\003B\005\r%a\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007CA\n\027\033\005!\"BA\013\003\003\r!7\017\\\005\003/Q\021Q!\0238c_b\004\"aE\r\n\005i!\"\001C\"sK\006$xN]:\t\013qIA\021A\017\002\rqJg.\033;?)\0059q!B\020\n\021#\001\023!C#yi\026t7/[8o!\t\t#%D\001\n\r\025\031\023\002#\005%\005%)\005\020^3og&|gn\005\003#\031\025\022\bc\001\005'Q%\021qE\001\002\f\013b$XM\\:j_:LE\r\005\002\"S\031!1%\003\005+'\021ICbK\027\021\005!a\023BA\022\003!\t\tc&\003\0020-\tq\021J\0342pq\026CH/\0328tS>t\007\002C\031*\005\013\007I\021\001\032\002\rML8\017^3n+\005\031\004C\001\0055\023\t)$AA\nFqR,g\016Z3e\003\016$xN]*zgR,W\016\003\0058S\t\005\t\025!\0034\003\035\031\030p\035;f[\002BQ\001H\025\005\002e\"\"\001\013\036\t\013EB\004\031A\032\t\017qJ#\031!C\001{\005!!m\\:t+\005q\004C\001\005@\023\t\001%AA\nSKB|\027N\034;bE2,\027i\031;peJ+g\r\003\004CS\001\006IAP\001\006E>\0348\017\t\005\t\t&B)\031!C\001\013\00611m\0348gS\036,\022A\022\t\003\0176k\021\001\023\006\003\t&S!AS&\002\021QL\b/Z:bM\026T\021\001T\001\004G>l\027B\001(I\005\031\031uN\0344jO\"A\001+\013E\001B\003&a)A\004d_:4\027n\032\021\t\017IK#\031!C\001'\006\tBi\025'EK\032\fW\017\034;US6,w.\036;\026\003Q\003\"!\026.\016\003YS!a\026-\002\021\021,(/\031;j_:T!!\027\b\002\025\r|gnY;se\026tG/\003\002\\-\nqa)\0338ji\026$UO]1uS>t\007BB/*A\003%A+\001\nE'2#UMZ1vYR$\026.\\3pkR\004\003\"B0*\t\003\001\027aB7l\007\"LG\016\032\013\004C\022L\007C\001\005c\023\t\031'A\001\005BGR|'OU3g\021\025)g\f1\001g\003\005\001\bC\001\005h\023\tA'AA\003Qe>\0048\017C\003k=\002\0071.\001\003oC6,\007C\0017p\035\tiQ.\003\002o\035\0051\001K]3eK\032L!\001]9\003\rM#(/\0338h\025\tqg\002\005\002\tg&\021AO\001\002\024\013b$XM\\:j_:LE\r\025:pm&$WM\035\005\0069\t\"\tA\036\013\002A!)\001P\tC!s\0061An\\8lkB$\022A\037\b\003CyAQ\001 \022\005Bu\fqb\031:fCR,W\t\037;f]NLwN\034\013\003QyDQ!M>A\002MBq!!\001#\t\003\n\031!A\002hKR$2\001KA\003\021\031\tt\0201\001\002\bA\031\001\"!\003\n\007\005-!AA\006BGR|'oU=ti\026l\007")
/*     */ public final class ActorDSL {
/*     */   public static ActorRef senderFromInbox(Inbox.Inbox paramInbox) {
/*     */     return ActorDSL$.MODULE$.senderFromInbox(paramInbox);
/*     */   }
/*     */   
/*     */   public static Inbox.Inbox inbox(ActorSystem paramActorSystem) {
/*     */     return ActorDSL$.MODULE$.inbox(paramActorSystem);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> ActorRef actor(ActorRefFactory paramActorRefFactory, Function0<T> paramFunction0, ClassTag<T> paramClassTag) {
/*     */     return ActorDSL$.MODULE$.actor(paramActorRefFactory, paramFunction0, paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> ActorRef actor(ActorRefFactory paramActorRefFactory, String paramString, Function0<T> paramFunction0, ClassTag<T> paramClassTag) {
/*     */     return ActorDSL$.MODULE$.actor(paramActorRefFactory, paramString, paramFunction0, paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> ActorRef actor(String paramString, Function0<T> paramFunction0, ClassTag<T> paramClassTag, ActorRefFactory paramActorRefFactory) {
/*     */     return ActorDSL$.MODULE$.actor(paramString, paramFunction0, paramClassTag, paramActorRefFactory);
/*     */   }
/*     */   
/*     */   public static <T extends Actor> ActorRef actor(Function0<T> paramFunction0, ClassTag<T> paramClassTag, ActorRefFactory paramActorRefFactory) {
/*     */     return ActorDSL$.MODULE$.actor(paramFunction0, paramClassTag, paramActorRefFactory);
/*     */   }
/*     */   
/*     */   public static class Extension$ implements ExtensionId<Extension>, ExtensionIdProvider {
/*     */     public static final Extension$ MODULE$;
/*     */     
/*     */     public Extension apply(ActorSystem system) {
/*  77 */       return ExtensionId$class.apply(this, system);
/*     */     }
/*     */     
/*     */     public final int hashCode() {
/*  77 */       return ExtensionId$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public final boolean equals(Object other) {
/*  77 */       return ExtensionId$class.equals(this, other);
/*     */     }
/*     */     
/*     */     public Extension$() {
/*  77 */       MODULE$ = this;
/*  77 */       ExtensionId$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Extension$ lookup() {
/*  79 */       return this;
/*     */     }
/*     */     
/*     */     public ActorDSL.Extension createExtension(ExtendedActorSystem system) {
/*  81 */       return new ActorDSL.Extension(system);
/*     */     }
/*     */     
/*     */     public ActorDSL.Extension get(ActorSystem system) {
/*  86 */       return (ActorDSL.Extension)ExtensionId$class.get(this, system);
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction0<ActorDSL.Extension.$anonfun$1.$anon$1> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final $anon$1 apply() {
/*  92 */         return new $anon$1(this);
/*     */       }
/*     */       
/*     */       public $anonfun$1(ActorDSL.Extension $outer) {}
/*     */       
/*     */       public class $anon$1 implements Actor {
/*     */         private final ActorContext context;
/*     */         
/*     */         private final ActorRef self;
/*     */         
/*     */         public ActorContext context() {
/*  92 */           return this.context;
/*     */         }
/*     */         
/*     */         public final ActorRef self() {
/*  92 */           return this.self;
/*     */         }
/*     */         
/*     */         public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  92 */           this.context = x$1;
/*     */         }
/*     */         
/*     */         public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  92 */           this.self = x$1;
/*     */         }
/*     */         
/*     */         public final ActorRef sender() {
/*  92 */           return Actor$class.sender(this);
/*     */         }
/*     */         
/*     */         public void aroundReceive(PartialFunction receive, Object msg) {
/*  92 */           Actor$class.aroundReceive(this, receive, msg);
/*     */         }
/*     */         
/*     */         public void aroundPreStart() {
/*  92 */           Actor$class.aroundPreStart(this);
/*     */         }
/*     */         
/*     */         public void aroundPostStop() {
/*  92 */           Actor$class.aroundPostStop(this);
/*     */         }
/*     */         
/*     */         public void aroundPreRestart(Throwable reason, Option message) {
/*  92 */           Actor$class.aroundPreRestart(this, reason, message);
/*     */         }
/*     */         
/*     */         public void aroundPostRestart(Throwable reason) {
/*  92 */           Actor$class.aroundPostRestart(this, reason);
/*     */         }
/*     */         
/*     */         public SupervisorStrategy supervisorStrategy() {
/*  92 */           return Actor$class.supervisorStrategy(this);
/*     */         }
/*     */         
/*     */         public void preStart() throws Exception {
/*  92 */           Actor$class.preStart(this);
/*     */         }
/*     */         
/*     */         public void postStop() throws Exception {
/*  92 */           Actor$class.postStop(this);
/*     */         }
/*     */         
/*     */         public void preRestart(Throwable reason, Option message) throws Exception {
/*  92 */           Actor$class.preRestart(this, reason, message);
/*     */         }
/*     */         
/*     */         public void postRestart(Throwable reason) throws Exception {
/*  92 */           Actor$class.postRestart(this, reason);
/*     */         }
/*     */         
/*     */         public void unhandled(Object message) {
/*  92 */           Actor$class.unhandled(this, message);
/*     */         }
/*     */         
/*     */         public $anon$1(ActorDSL.Extension.$anonfun$1 $outer) {
/*  92 */           Actor$class.$init$(this);
/*     */         }
/*     */         
/*     */         public PartialFunction<Object, BoxedUnit> receive() {
/*  93 */           return (PartialFunction<Object, BoxedUnit>)new ActorDSL$Extension$$anonfun$1$$anon$1$$anonfun$receive$1(this);
/*     */         }
/*     */         
/*     */         public class ActorDSL$Extension$$anonfun$1$$anon$1$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */           public static final long serialVersionUID = 0L;
/*     */           
/*     */           public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*  93 */             Object object = x1;
/*  93 */             package$.MODULE$.actorRef2Scala(this.$outer.sender()).$bang(object, this.$outer.self());
/*  93 */             return (B1)BoxedUnit.UNIT;
/*     */           }
/*     */           
/*     */           public final boolean isDefinedAt(Object x1) {
/*  93 */             Object object = x1;
/*  93 */             return true;
/*     */           }
/*     */           
/*     */           public ActorDSL$Extension$$anonfun$1$$anon$1$$anonfun$receive$1(ActorDSL.Extension.$anonfun$1.$anon$1 $outer) {}
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Extension implements Extension, Inbox.InboxExtension {
/*     */     private final ExtendedActorSystem system;
/*     */     
/*     */     private final RepointableActorRef boss;
/*     */     
/*     */     private Config config;
/*     */     
/*     */     private final FiniteDuration DSLDefaultTimeout;
/*     */     
/*     */     private final int DSLInboxQueueSize;
/*     */     
/*     */     private final AtomicInteger inboxNr;
/*     */     
/*     */     private final Props inboxProps;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public int DSLInboxQueueSize() {
/*     */       return this.DSLInboxQueueSize;
/*     */     }
/*     */     
/*     */     public AtomicInteger inboxNr() {
/*     */       return this.inboxNr;
/*     */     }
/*     */     
/*     */     public Props inboxProps() {
/*     */       return this.inboxProps;
/*     */     }
/*     */     
/*     */     public void akka$actor$dsl$Inbox$InboxExtension$_setter_$DSLInboxQueueSize_$eq(int x$1) {
/*     */       this.DSLInboxQueueSize = x$1;
/*     */     }
/*     */     
/*     */     public void akka$actor$dsl$Inbox$InboxExtension$_setter_$inboxNr_$eq(AtomicInteger x$1) {
/*     */       this.inboxNr = x$1;
/*     */     }
/*     */     
/*     */     public void akka$actor$dsl$Inbox$InboxExtension$_setter_$inboxProps_$eq(Props x$1) {
/*     */       this.inboxProps = x$1;
/*     */     }
/*     */     
/*     */     public ActorRef newReceiver() {
/*     */       return Inbox.InboxExtension.class.newReceiver(this);
/*     */     }
/*     */     
/*     */     public ExtendedActorSystem system() {
/*     */       return this.system;
/*     */     }
/*     */     
/*     */     public Extension(ExtendedActorSystem system) {
/*     */       Inbox.InboxExtension.class.$init$(this);
/*  94 */       this.boss = (RepointableActorRef)system.systemActorOf(Props$.MODULE$.apply((Function0<Actor>)new $anonfun$1(this), scala.reflect.ClassTag$.MODULE$.apply(Actor.class)), "dsl");
/*  97 */       Timeout timeout = system.settings().CreationTimeout();
/*  98 */       String str = "OK";
/*  98 */       if (scala.concurrent.Await$.MODULE$.result((Awaitable)akka.pattern.AskableActorRef$.MODULE$.$qmark$extension(akka.pattern.package$.MODULE$.ask(boss()), "OK", timeout), (Duration)system.settings().CreationTimeout().duration()) == null) {
/*  98 */         scala.concurrent.Await$.MODULE$.result((Awaitable)akka.pattern.AskableActorRef$.MODULE$.$qmark$extension(akka.pattern.package$.MODULE$.ask(boss()), "OK", timeout), (Duration)system.settings().CreationTimeout().duration());
/*  98 */         if (str != null)
/*  99 */           throw new IllegalStateException("Creation of boss actor did not succeed!"); 
/*     */       } else if (!scala.concurrent.Await$.MODULE$.result((Awaitable)akka.pattern.AskableActorRef$.MODULE$.$qmark$extension(akka.pattern.package$.MODULE$.ask(boss()), "OK", timeout), (Duration)system.settings().CreationTimeout().duration()).equals(str)) {
/*  99 */         throw new IllegalStateException("Creation of boss actor did not succeed!");
/*     */       } 
/* 104 */       this.DSLDefaultTimeout = akka.util.Helpers$ConfigOps$.MODULE$.getMillisDuration$extension(akka.util.Helpers$.MODULE$.ConfigOps(config()), "default-timeout");
/*     */     }
/*     */     
/*     */     public RepointableActorRef boss() {
/*     */       return this.boss;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction0<$anonfun$1.$anon$1> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final $anon$1 apply() {
/*     */         return new $anon$1(this);
/*     */       }
/*     */       
/*     */       public $anonfun$1(ActorDSL.Extension $outer) {}
/*     */       
/*     */       public class $anon$1 implements Actor {
/*     */         private final ActorContext context;
/*     */         
/*     */         private final ActorRef self;
/*     */         
/*     */         public ActorContext context() {
/*     */           return this.context;
/*     */         }
/*     */         
/*     */         public final ActorRef self() {
/*     */           return this.self;
/*     */         }
/*     */         
/*     */         public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*     */           this.context = x$1;
/*     */         }
/*     */         
/*     */         public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*     */           this.self = x$1;
/*     */         }
/*     */         
/*     */         public final ActorRef sender() {
/*     */           return Actor$class.sender(this);
/*     */         }
/*     */         
/*     */         public void aroundReceive(PartialFunction receive, Object msg) {
/*     */           Actor$class.aroundReceive(this, receive, msg);
/*     */         }
/*     */         
/*     */         public void aroundPreStart() {
/*     */           Actor$class.aroundPreStart(this);
/*     */         }
/*     */         
/*     */         public void aroundPostStop() {
/*     */           Actor$class.aroundPostStop(this);
/*     */         }
/*     */         
/*     */         public void aroundPreRestart(Throwable reason, Option message) {
/*     */           Actor$class.aroundPreRestart(this, reason, message);
/*     */         }
/*     */         
/*     */         public void aroundPostRestart(Throwable reason) {
/*     */           Actor$class.aroundPostRestart(this, reason);
/*     */         }
/*     */         
/*     */         public SupervisorStrategy supervisorStrategy() {
/*     */           return Actor$class.supervisorStrategy(this);
/*     */         }
/*     */         
/*     */         public void preStart() throws Exception {
/*     */           Actor$class.preStart(this);
/*     */         }
/*     */         
/*     */         public void postStop() throws Exception {
/*     */           Actor$class.postStop(this);
/*     */         }
/*     */         
/*     */         public void preRestart(Throwable reason, Option message) throws Exception {
/*     */           Actor$class.preRestart(this, reason, message);
/*     */         }
/*     */         
/*     */         public void postRestart(Throwable reason) throws Exception {
/*     */           Actor$class.postRestart(this, reason);
/*     */         }
/*     */         
/*     */         public void unhandled(Object message) {
/*     */           Actor$class.unhandled(this, message);
/*     */         }
/*     */         
/*     */         public $anon$1(ActorDSL.Extension.$anonfun$1 $outer) {
/*     */           Actor$class.$init$(this);
/*     */         }
/*     */         
/*     */         public PartialFunction<Object, BoxedUnit> receive() {
/*     */           return (PartialFunction<Object, BoxedUnit>)new ActorDSL$Extension$$anonfun$1$$anon$1$$anonfun$receive$1(this);
/*     */         }
/*     */         
/*     */         public class ActorDSL$Extension$$anonfun$1$$anon$1$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */           public static final long serialVersionUID = 0L;
/*     */           
/*     */           public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */             Object object = x1;
/*     */             package$.MODULE$.actorRef2Scala(this.$outer.sender()).$bang(object, this.$outer.self());
/*     */             return (B1)BoxedUnit.UNIT;
/*     */           }
/*     */           
/*     */           public final boolean isDefinedAt(Object x1) {
/*     */             Object object = x1;
/*     */             return true;
/*     */           }
/*     */           
/*     */           public ActorDSL$Extension$$anonfun$1$$anon$1$$anonfun$receive$1(ActorDSL.Extension.$anonfun$1.$anon$1 $outer) {}
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private Config config$lzycompute() {
/*     */       synchronized (this) {
/*     */         if (!this.bitmap$0) {
/*     */           this.config = system().settings().config().getConfig("akka.actor.dsl");
/*     */           this.bitmap$0 = true;
/*     */         } 
/*     */         return this.config;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Config config() {
/*     */       return this.bitmap$0 ? this.config : config$lzycompute();
/*     */     }
/*     */     
/*     */     public FiniteDuration DSLDefaultTimeout() {
/* 104 */       return this.DSLDefaultTimeout;
/*     */     }
/*     */     
/*     */     public ActorRef mkChild(Props p, String name) {
/* 106 */       return ((ActorCell)boss().underlying()).attachChild(p, name, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorDSL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */