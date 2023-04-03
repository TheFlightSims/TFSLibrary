/*     */ package akka.event;
/*     */ 
/*     */ import akka.ConfigurationException;
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ActorSystemImpl;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.actor.UnhandledMessage;
/*     */ import akka.util.ReentrantGuard;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=caB\001\003!\003\r\ta\002\002\013\031><w-\0338h\005V\034(BA\002\005\003\025)g/\0328u\025\005)\021\001B1lW\006\034\001aE\002\001\0219\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\0055\t5\r^8s\013Z,g\016\036\"vg\")1\003\001C\001)\0051A%\0338ji\022\"\022!\006\t\003\023YI!a\006\006\003\tUs\027\016\036\003\0063\001\021\tA\007\002\006\013Z,g\016^\t\0037\t\002\"\001H\020\017\005=i\022B\001\020\003\003\035aunZ4j]\036L!\001I\021\003\0211{w-\022<f]RT!A\b\002\021\005%\031\023B\001\023\013\005\r\te.\037\003\006M\001\021\ta\n\002\013\0072\f7o]5gS\026\024\030C\001\025#a\tI#\007E\002+[Ar!!C\026\n\0051R\021A\002)sK\022,g-\003\002/_\t)1\t\\1tg*\021AF\003\t\003cIb\001\001B\0054K\005\005\t\021!B\001i\t\031q\fJ\031\022\005U\022\003CA\0057\023\t9$BA\004O_RD\027N\\4\t\017e\002!\031!C\005u\005)q-^1sIV\t1\b\005\002=5\tQH\003\002?\t\005!Q\017^5m\023\t\001UH\001\bSK\026tGO]1oi\036+\030M\0353\t\r\t\003\001\025!\003<\003\0319W/\031:eA!9A\t\001a\001\n\023)\025a\0027pO\036,'o]\013\002\rB\031qI\023'\016\003!S!!\023\006\002\025\r|G\016\\3di&|g.\003\002L\021\n\0311+Z9\021\0055\003V\"\001(\013\005=#\021!B1di>\024\030BA)O\005!\t5\r^8s%\0264\007bB*\001\001\004%I\001V\001\fY><w-\032:t?\022*\027\017\006\002\026+\"9aKUA\001\002\0041\025a\001=%c!1\001\f\001Q!\n\031\013\001\002\\8hO\026\0248\017\t\005\n5\002\001\r\0211A\005\nm\013\021b\0307pO2+g/\0327\026\003q\003\"\001H/\n\005y\013#\001\003'pO2+g/\0327\t\023\001\004\001\031!a\001\n\023\t\027!D0m_\036dUM^3m?\022*\027\017\006\002\026E\"9akXA\001\002\004a\006B\0023\001A\003&A,\001\006`Y><G*\032<fY\002BQA\032\001\005\002m\013\001\002\\8h\031\0264X\r\034\005\006Q\002!\t![\001\fg\026$Hj\\4MKZ,G\016\006\002\026U\")1n\032a\0019\006)A.\032<fY\")Q\016\001C\005]\006\t2/\032;VaN#Hm\\;u\031><w-\032:\025\005Uy\007\"\0029m\001\004\t\030AB2p]\032Lw\r\005\002sy:\0211O\037\b\003ift!!\036=\016\003YT!a\036\004\002\rq\022xn\034;?\023\005)\021BA(\005\023\tYh*A\006BGR|'oU=ti\026l\027BA?\005!\031V\r\036;j]\036\034(BA>O\021!\t\t\001\001C\001\t\005\r\021!E:uCJ$8\013\0363pkRdunZ4feR\031Q#!\002\t\013A|\b\031A9\t\021\005%\001\001\"\001\005\003\027\t1c\035;beR$UMZ1vYRdunZ4feN$2!FA\007\021!\ty!a\002A\002\005E\021AB:zgR,W\016E\002N\003'I1!!\006O\005=\t5\r^8s'f\034H/Z7J[Bd\007\002CA\r\001\021\005A!a\007\002%M$x\016\035#fM\006,H\016\036'pO\036,'o\035\013\004+\005u\001\002CA\b\003/\001\r!a\b\021\0075\013\t#C\002\002$9\0231\"Q2u_J\034\026p\035;f[\"9\021q\005\001\005\n\005%\022!C1eI2{wmZ3s)%a\0251FA\027\003\007\n)\005\003\005\002\020\005\025\002\031AA\t\021!\ty#!\nA\002\005E\022!B2mCjT\b\007BA\032\003o\001BAK\027\0026A\031\021'a\016\005\031\005e\022QFA\001\002\003\025\t!a\017\003\007}##'E\0026\003{\0012!TA \023\r\t\tE\024\002\006\003\016$xN\035\005\007W\006\025\002\031\001/\t\021\005\035\023Q\005a\001\003\023\nq\001\\8h\035\006lW\rE\002+\003\027J1!!\0240\005\031\031FO]5oO\002")
/*     */ public interface LoggingBus extends ActorEventBus {
/*     */   void akka$event$LoggingBus$_setter_$akka$event$LoggingBus$$guard_$eq(ReentrantGuard paramReentrantGuard);
/*     */   
/*     */   ReentrantGuard akka$event$LoggingBus$$guard();
/*     */   
/*     */   Seq<ActorRef> akka$event$LoggingBus$$loggers();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$event$LoggingBus$$loggers_$eq(Seq<ActorRef> paramSeq);
/*     */   
/*     */   int akka$event$LoggingBus$$_logLevel();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$event$LoggingBus$$_logLevel_$eq(int paramInt);
/*     */   
/*     */   int logLevel();
/*     */   
/*     */   void setLogLevel(int paramInt);
/*     */   
/*     */   void startStdoutLogger(ActorSystem.Settings paramSettings);
/*     */   
/*     */   void startDefaultLoggers(ActorSystemImpl paramActorSystemImpl);
/*     */   
/*     */   void stopDefaultLoggers(ActorSystem paramActorSystem);
/*     */   
/*     */   public class LoggingBus$$anonfun$logLevel$1 extends AbstractFunction0<Logging.LogLevel> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply() {
/*  42 */       return this.$outer.akka$event$LoggingBus$$_logLevel();
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$logLevel$1(LoggingBus $outer) {}
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$setLogLevel$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int level$4;
/*     */     
/*     */     public final void apply() {
/*  54 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$setLogLevel$1(LoggingBus $outer, int level$4) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/*  56 */       Logging$.MODULE$.AllLogLevels().withFilter((Function1)new LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$1(this)).foreach((Function1)new LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2(this));
/*  62 */       Logging$.MODULE$.AllLogLevels().withFilter((Function1)new LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$3(this)).foreach((Function1)new LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4(this));
/*  67 */       this.$outer.akka$event$LoggingBus$$_logLevel_$eq(this.level$4);
/*     */     }
/*     */     
/*     */     public class LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction1<Logging.LogLevel, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$1(LoggingBus$$anonfun$setLogLevel$1 $outer) {}
/*     */       
/*     */       public final boolean apply(int l) {
/*     */         return (Logging.LogLevel$.MODULE$.$greater$extension(l, this.$outer.akka$event$LoggingBus$$anonfun$$$outer().akka$event$LoggingBus$$_logLevel()) && Logging.LogLevel$.MODULE$.$less$eq$extension(l, this.$outer.level$4));
/*     */       }
/*     */     }
/*     */     
/*     */     public class LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction1<Logging.LogLevel, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2(LoggingBus$$anonfun$setLogLevel$1 $outer) {}
/*     */       
/*     */       public final void apply(int l) {
/*     */         this.$outer.akka$event$LoggingBus$$anonfun$$$outer().akka$event$LoggingBus$$loggers().foreach((Function1)new LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2$$anonfun$apply$2(this, l));
/*     */       }
/*     */       
/*     */       public class LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2$$anonfun$apply$2 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final int l$1;
/*     */         
/*     */         public LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2$$anonfun$apply$2(LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$2 $outer, int l$1) {}
/*     */         
/*     */         public final boolean apply(ActorRef log) {
/*     */           return this.$outer.akka$event$LoggingBus$$anonfun$$anonfun$$$outer().akka$event$LoggingBus$$anonfun$$$outer().subscribe(log, Logging$.MODULE$.classFor(this.l$1));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public class LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$3 extends AbstractFunction1<Logging.LogLevel, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$3(LoggingBus$$anonfun$setLogLevel$1 $outer) {}
/*     */       
/*     */       public final boolean apply(int l) {
/*     */         return (Logging.LogLevel$.MODULE$.$less$eq$extension(l, this.$outer.akka$event$LoggingBus$$anonfun$$$outer().akka$event$LoggingBus$$_logLevel()) && Logging.LogLevel$.MODULE$.$greater$extension(l, this.$outer.level$4));
/*     */       }
/*     */     }
/*     */     
/*     */     public class LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4 extends AbstractFunction1<Logging.LogLevel, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4(LoggingBus$$anonfun$setLogLevel$1 $outer) {}
/*     */       
/*     */       public final void apply(int l) {
/*     */         this.$outer.akka$event$LoggingBus$$anonfun$$$outer().akka$event$LoggingBus$$loggers().foreach((Function1)new LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4$$anonfun$apply$3(this, l));
/*     */       }
/*     */       
/*     */       public class LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4$$anonfun$apply$3 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final int l$2;
/*     */         
/*     */         public LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4$$anonfun$apply$3(LoggingBus$$anonfun$setLogLevel$1$$anonfun$apply$mcV$sp$4 $outer, int l$2) {}
/*     */         
/*     */         public final boolean apply(ActorRef log) {
/*     */           return this.$outer.akka$event$LoggingBus$$anonfun$$anonfun$$$outer().akka$event$LoggingBus$$anonfun$$$outer().unsubscribe(log, Logging$.MODULE$.classFor(this.l$2));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$1 extends AbstractFunction0<Logging.LogLevel> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorSystem.Settings config$1;
/*     */     
/*     */     public LoggingBus$$anonfun$1(LoggingBus $outer, ActorSystem.Settings config$1) {}
/*     */     
/*     */     public final int apply() {
/*  73 */       Logging$.MODULE$.StandardOutLogger().print(new Logging.Error((Throwable)new Logging.LoggerException(), Logging$.MODULE$.simpleName(this.$outer), this.$outer.getClass(), (new StringBuilder()).append("unknown akka.stdout-loglevel ").append(this.config$1.StdoutLogLevel()).toString()));
/*  74 */       return Logging$.MODULE$.ErrorLevel();
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$setUpStdoutLogger$2 extends AbstractFunction1<Logging.LogLevel, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int level$1;
/*     */     
/*     */     public final boolean apply(int x$1) {
/*  76 */       return Logging.LogLevel$.MODULE$.$greater$eq$extension(this.level$1, x$1);
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$setUpStdoutLogger$2(LoggingBus $outer, int level$1) {}
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$setUpStdoutLogger$3 extends AbstractFunction1<Logging.LogLevel, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(int l) {
/*  76 */       return this.$outer.subscribe(Logging$.MODULE$.StandardOutLogger(), Logging$.MODULE$.classFor(l));
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$setUpStdoutLogger$3(LoggingBus $outer) {}
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$setUpStdoutLogger$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int level$1;
/*     */     
/*     */     public final void apply() {
/*  77 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$setUpStdoutLogger$1(LoggingBus $outer, int level$1) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/*  78 */       this.$outer.akka$event$LoggingBus$$loggers_$eq((Seq<ActorRef>)this.$outer.akka$event$LoggingBus$$loggers().$colon$plus(Logging$.MODULE$.StandardOutLogger(), Seq$.MODULE$.canBuildFrom()));
/*  79 */       this.$outer.akka$event$LoggingBus$$_logLevel_$eq(this.level$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$2 extends AbstractFunction0<Logging.LogLevel> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String logName$1;
/*     */     
/*     */     private final ActorSystemImpl system$2;
/*     */     
/*     */     public LoggingBus$$anonfun$2(LoggingBus $outer, String logName$1, ActorSystemImpl system$2) {}
/*     */     
/*     */     public final int apply() {
/*  98 */       Logging$.MODULE$.StandardOutLogger().print(new Logging.Error((Throwable)new Logging.LoggerException(), this.logName$1, this.$outer.getClass(), (new StringBuilder()).append("unknown akka.loglevel ").append(this.system$2.settings().LogLevel()).toString()));
/*  99 */       return Logging$.MODULE$.ErrorLevel();
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$3 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public LoggingBus$$anonfun$3(LoggingBus $outer) {}
/*     */     
/*     */     public final boolean apply(String loggerName) {
/* 109 */       String str = Logging$.MODULE$.StandardOutLogger().getClass().getName();
/* 109 */       if (loggerName == null) {
/* 109 */         if (str != null);
/* 109 */       } else if (loggerName.equals(str)) {
/*     */       
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$4 extends AbstractFunction1<String, ActorRef> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String logName$1;
/*     */     
/*     */     public final int level$2;
/*     */     
/*     */     public final ActorSystemImpl system$2;
/*     */     
/*     */     public LoggingBus$$anonfun$4(LoggingBus $outer, String logName$1, int level$2, ActorSystemImpl system$2) {}
/*     */     
/*     */     public final ActorRef apply(String loggerName) {
/* 111 */       return (ActorRef)this.system$2.dynamicAccess().getClassFor(loggerName, ClassTag$.MODULE$.apply(Actor.class)).map((Function1)new LoggingBus$$anonfun$4$$anonfun$apply$4(this))
/*     */         
/* 113 */         .recover((PartialFunction)new LoggingBus$$anonfun$4$$anonfun$apply$1(this, loggerName))
/*     */         
/* 117 */         .get();
/*     */     }
/*     */     
/*     */     public class LoggingBus$$anonfun$4$$anonfun$apply$4 extends AbstractFunction1<Class<? extends Actor>, ActorRef> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ActorRef apply(Class x0$1) {
/*     */         Class clazz = x0$1;
/*     */         return LoggingBus$class.akka$event$LoggingBus$$addLogger(this.$outer.akka$event$LoggingBus$$anonfun$$$outer(), this.$outer.system$2, clazz, this.$outer.level$2, this.$outer.logName$1);
/*     */       }
/*     */       
/*     */       public LoggingBus$$anonfun$4$$anonfun$apply$4(LoggingBus$$anonfun$4 $outer) {}
/*     */     }
/*     */     
/*     */     public class LoggingBus$$anonfun$4$$anonfun$apply$1 extends AbstractPartialFunction<Throwable, Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String loggerName$1;
/*     */       
/*     */       public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */         Throwable throwable = x1;
/*     */         throw new ConfigurationException((new StringBuilder()).append("Logger specified in config can't be loaded [").append(this.loggerName$1).append("] due to [").append(throwable.toString()).append("]").toString(), throwable);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Throwable x1) {
/*     */         Throwable throwable = x1;
/*     */         return true;
/*     */       }
/*     */       
/*     */       public LoggingBus$$anonfun$4$$anonfun$apply$1(LoggingBus$$anonfun$4 $outer, String loggerName$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$startDefaultLoggers$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int level$2;
/*     */     
/*     */     private final Seq myloggers$1;
/*     */     
/*     */     public final void apply() {
/* 119 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$startDefaultLoggers$1(LoggingBus $outer, int level$2, Seq myloggers$1) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/* 120 */       this.$outer.akka$event$LoggingBus$$loggers_$eq((Seq<ActorRef>)this.myloggers$1);
/* 121 */       this.$outer.akka$event$LoggingBus$$_logLevel_$eq(this.level$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$startDefaultLoggers$2 extends AbstractFunction0<LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3 apply() {
/* 125 */       return new LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3(this);
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$startDefaultLoggers$2(LoggingBus $outer) {}
/*     */     
/*     */     public class LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3 implements Actor {
/*     */       private final ActorContext context;
/*     */       
/*     */       private final ActorRef self;
/*     */       
/*     */       public ActorContext context() {
/* 125 */         return this.context;
/*     */       }
/*     */       
/*     */       public final ActorRef self() {
/* 125 */         return this.self;
/*     */       }
/*     */       
/*     */       public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 125 */         this.context = x$1;
/*     */       }
/*     */       
/*     */       public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 125 */         this.self = x$1;
/*     */       }
/*     */       
/*     */       public final ActorRef sender() {
/* 125 */         return Actor.class.sender(this);
/*     */       }
/*     */       
/*     */       public void aroundReceive(PartialFunction receive, Object msg) {
/* 125 */         Actor.class.aroundReceive(this, receive, msg);
/*     */       }
/*     */       
/*     */       public void aroundPreStart() {
/* 125 */         Actor.class.aroundPreStart(this);
/*     */       }
/*     */       
/*     */       public void aroundPostStop() {
/* 125 */         Actor.class.aroundPostStop(this);
/*     */       }
/*     */       
/*     */       public void aroundPreRestart(Throwable reason, Option message) {
/* 125 */         Actor.class.aroundPreRestart(this, reason, message);
/*     */       }
/*     */       
/*     */       public void aroundPostRestart(Throwable reason) {
/* 125 */         Actor.class.aroundPostRestart(this, reason);
/*     */       }
/*     */       
/*     */       public SupervisorStrategy supervisorStrategy() {
/* 125 */         return Actor.class.supervisorStrategy(this);
/*     */       }
/*     */       
/*     */       public void preStart() throws Exception {
/* 125 */         Actor.class.preStart(this);
/*     */       }
/*     */       
/*     */       public void postStop() throws Exception {
/* 125 */         Actor.class.postStop(this);
/*     */       }
/*     */       
/*     */       public void preRestart(Throwable reason, Option message) throws Exception {
/* 125 */         Actor.class.preRestart(this, reason, message);
/*     */       }
/*     */       
/*     */       public void postRestart(Throwable reason) throws Exception {
/* 125 */         Actor.class.postRestart(this, reason);
/*     */       }
/*     */       
/*     */       public void unhandled(Object message) {
/* 125 */         Actor.class.unhandled(this, message);
/*     */       }
/*     */       
/*     */       public LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3(LoggingBus$$anonfun$startDefaultLoggers$2 $outer) {
/* 125 */         Actor.class.$init$(this);
/*     */       }
/*     */       
/*     */       public PartialFunction<Object, BoxedUnit> receive() {
/* 126 */         return (PartialFunction<Object, BoxedUnit>)new LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3$$anonfun$receive$1(this);
/*     */       }
/*     */       
/*     */       public class LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/* 126 */           Object object2, object1 = x2;
/* 127 */           if (object1 instanceof UnhandledMessage) {
/* 127 */             UnhandledMessage unhandledMessage = (UnhandledMessage)object1;
/* 127 */             Object msg = unhandledMessage.message();
/* 127 */             ActorRef sender = unhandledMessage.sender(), rcp = unhandledMessage.recipient();
/* 128 */             this.$outer.akka$event$LoggingBus$$anonfun$$anon$$$outer().akka$event$LoggingBus$$anonfun$$$outer().publish(new Logging.Debug(rcp.path().toString(), rcp.getClass(), (new StringBuilder()).append("unhandled message from ").append(sender).append(": ").append(msg).toString()));
/* 128 */             object2 = BoxedUnit.UNIT;
/*     */           } else {
/*     */             object2 = default.apply(x2);
/*     */           } 
/*     */           return (B1)object2;
/*     */         }
/*     */         
/*     */         public final boolean isDefinedAt(Object x2) {
/*     */           boolean bool;
/*     */           Object object = x2;
/*     */           if (object instanceof UnhandledMessage) {
/* 128 */             bool = true;
/*     */           } else {
/*     */             bool = false;
/*     */           } 
/*     */           return bool;
/*     */         }
/*     */         
/*     */         public LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3$$anonfun$receive$1(LoggingBus$$anonfun$startDefaultLoggers$2$$anon$3 $outer) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$stopDefaultLoggers$1 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public LoggingBus$$anonfun$stopDefaultLoggers$1(LoggingBus $outer) {}
/*     */     
/*     */     public final boolean apply(ActorRef logger) {
/* 157 */       Logging.StandardOutLogger standardOutLogger = Logging$.MODULE$.StandardOutLogger();
/* 157 */       if (logger == null) {
/* 157 */         if (standardOutLogger != null);
/* 157 */       } else if (logger.equals(standardOutLogger)) {
/*     */       
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$stopDefaultLoggers$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public LoggingBus$$anonfun$stopDefaultLoggers$2(LoggingBus $outer) {}
/*     */     
/*     */     public final void apply(ActorRef logger) {
/* 160 */       this.$outer.unsubscribe(logger);
/* 161 */       ActorRef actorRef = logger;
/* 162 */       if (actorRef instanceof InternalActorRef) {
/* 162 */         InternalActorRef internalActorRef = (InternalActorRef)actorRef;
/* 162 */         internalActorRef.stop();
/* 162 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/* 163 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$akka$event$LoggingBus$$addLogger$1 extends AbstractFunction1<Logging.LogLevel, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int level$3;
/*     */     
/*     */     public final boolean apply(int x$3) {
/* 184 */       return Logging.LogLevel$.MODULE$.$greater$eq$extension(this.level$3, x$3);
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$akka$event$LoggingBus$$addLogger$1(LoggingBus $outer, int level$3) {}
/*     */   }
/*     */   
/*     */   public class LoggingBus$$anonfun$akka$event$LoggingBus$$addLogger$2 extends AbstractFunction1<Logging.LogLevel, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef actor$1;
/*     */     
/*     */     public final boolean apply(int l) {
/* 184 */       return this.$outer.subscribe(this.actor$1, Logging$.MODULE$.classFor(l));
/*     */     }
/*     */     
/*     */     public LoggingBus$$anonfun$akka$event$LoggingBus$$addLogger$2(LoggingBus $outer, ActorRef actor$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LoggingBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */