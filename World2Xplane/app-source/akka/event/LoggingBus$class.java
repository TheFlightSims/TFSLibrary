/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ActorSystemImpl;
/*     */ import akka.actor.Props$;
/*     */ import akka.util.ReentrantGuard;
/*     */ import akka.util.Timeout;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class LoggingBus$class {
/*     */   public static void $init$(LoggingBus $this) {
/*  35 */     $this.akka$event$LoggingBus$_setter_$akka$event$LoggingBus$$guard_$eq(new ReentrantGuard());
/*  36 */     $this.akka$event$LoggingBus$$loggers_$eq((Seq<ActorRef>)Seq$.MODULE$.empty());
/*     */   }
/*     */   
/*     */   public static int logLevel(LoggingBus $this) {
/*  42 */     return ((Logging.LogLevel)$this.akka$event$LoggingBus$$guard().withGuard((Function0)new LoggingBus$$anonfun$logLevel$1($this))).asInt();
/*     */   }
/*     */   
/*     */   public static void setLogLevel(LoggingBus $this, int level) {
/*  54 */     $this.akka$event$LoggingBus$$guard().withGuard((Function0)new LoggingBus$$anonfun$setLogLevel$1($this, level));
/*     */   }
/*     */   
/*     */   private static void setUpStdoutLogger(LoggingBus $this, ActorSystem.Settings config) {
/*  71 */     int level = ((Logging.LogLevel)Logging$.MODULE$.levelFor(config.StdoutLogLevel()).getOrElse((Function0)new LoggingBus$$anonfun$1($this, config))).asInt();
/*  76 */     ((IterableLike)Logging$.MODULE$.AllLogLevels().filter((Function1)new LoggingBus$$anonfun$setUpStdoutLogger$2($this, level))).foreach((Function1)new LoggingBus$$anonfun$setUpStdoutLogger$3($this));
/*  77 */     $this.akka$event$LoggingBus$$guard().withGuard((Function0)new LoggingBus$$anonfun$setUpStdoutLogger$1($this, level));
/*     */   }
/*     */   
/*     */   public static void startStdoutLogger(LoggingBus $this, ActorSystem.Settings config) {
/*  87 */     setUpStdoutLogger($this, config);
/*  88 */     $this.publish(new Logging.Debug(Logging$.MODULE$.simpleName($this), $this.getClass(), "StandardOutLogger started"));
/*     */   }
/*     */   
/*     */   public static void startDefaultLoggers(LoggingBus $this, ActorSystemImpl system) {
/*     */     // Byte code:
/*     */     //   0: new scala/collection/mutable/StringBuilder
/*     */     //   3: dup
/*     */     //   4: invokespecial <init> : ()V
/*     */     //   7: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   10: aload_0
/*     */     //   11: invokevirtual simpleName : (Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   14: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   17: ldc '('
/*     */     //   19: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   22: aload_1
/*     */     //   23: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   26: ldc ')'
/*     */     //   28: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   31: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   34: astore_2
/*     */     //   35: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   38: aload_1
/*     */     //   39: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   42: invokevirtual LogLevel : ()Ljava/lang/String;
/*     */     //   45: invokevirtual levelFor : (Ljava/lang/String;)Lscala/Option;
/*     */     //   48: new akka/event/LoggingBus$$anonfun$2
/*     */     //   51: dup
/*     */     //   52: aload_0
/*     */     //   53: aload_2
/*     */     //   54: aload_1
/*     */     //   55: invokespecial <init> : (Lakka/event/LoggingBus;Ljava/lang/String;Lakka/actor/ActorSystemImpl;)V
/*     */     //   58: invokevirtual getOrElse : (Lscala/Function0;)Ljava/lang/Object;
/*     */     //   61: checkcast akka/event/Logging$LogLevel
/*     */     //   64: invokevirtual asInt : ()I
/*     */     //   67: istore_3
/*     */     //   68: aload_1
/*     */     //   69: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   72: invokevirtual Loggers : ()Lscala/collection/immutable/Seq;
/*     */     //   75: astore #6
/*     */     //   77: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   80: aload #6
/*     */     //   82: astore #7
/*     */     //   84: dup
/*     */     //   85: ifnonnull -> 97
/*     */     //   88: pop
/*     */     //   89: aload #7
/*     */     //   91: ifnull -> 105
/*     */     //   94: goto -> 125
/*     */     //   97: aload #7
/*     */     //   99: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   102: ifeq -> 125
/*     */     //   105: ldc akka/event/Logging$DefaultLogger
/*     */     //   107: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   110: astore #9
/*     */     //   112: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   115: aload #9
/*     */     //   117: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */     //   120: astore #8
/*     */     //   122: goto -> 129
/*     */     //   125: aload #6
/*     */     //   127: astore #8
/*     */     //   129: aload #8
/*     */     //   131: astore #5
/*     */     //   133: aload #5
/*     */     //   135: new akka/event/LoggingBus$$anonfun$3
/*     */     //   138: dup
/*     */     //   139: aload_0
/*     */     //   140: invokespecial <init> : (Lakka/event/LoggingBus;)V
/*     */     //   143: invokeinterface withFilter : (Lscala/Function1;)Lscala/collection/generic/FilterMonadic;
/*     */     //   148: new akka/event/LoggingBus$$anonfun$4
/*     */     //   151: dup
/*     */     //   152: aload_0
/*     */     //   153: aload_2
/*     */     //   154: iload_3
/*     */     //   155: aload_1
/*     */     //   156: invokespecial <init> : (Lakka/event/LoggingBus;Ljava/lang/String;ILakka/actor/ActorSystemImpl;)V
/*     */     //   159: getstatic scala/collection/immutable/Seq$.MODULE$ : Lscala/collection/immutable/Seq$;
/*     */     //   162: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   165: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   170: checkcast scala/collection/immutable/Seq
/*     */     //   173: astore #10
/*     */     //   175: aload_0
/*     */     //   176: invokeinterface akka$event$LoggingBus$$guard : ()Lakka/util/ReentrantGuard;
/*     */     //   181: new akka/event/LoggingBus$$anonfun$startDefaultLoggers$1
/*     */     //   184: dup
/*     */     //   185: aload_0
/*     */     //   186: iload_3
/*     */     //   187: aload #10
/*     */     //   189: invokespecial <init> : (Lakka/event/LoggingBus;ILscala/collection/immutable/Seq;)V
/*     */     //   192: invokevirtual withGuard : (Lscala/Function0;)Ljava/lang/Object;
/*     */     //   195: pop
/*     */     //   196: aload_1
/*     */     //   197: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   200: invokevirtual DebugUnhandledMessage : ()Z
/*     */     //   203: ifeq -> 248
/*     */     //   206: aload_0
/*     */     //   207: aload_1
/*     */     //   208: getstatic akka/actor/Props$.MODULE$ : Lakka/actor/Props$;
/*     */     //   211: new akka/event/LoggingBus$$anonfun$startDefaultLoggers$2
/*     */     //   214: dup
/*     */     //   215: aload_0
/*     */     //   216: invokespecial <init> : (Lakka/event/LoggingBus;)V
/*     */     //   219: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   222: ldc akka/actor/Actor
/*     */     //   224: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   227: invokevirtual apply : (Lscala/Function0;Lscala/reflect/ClassTag;)Lakka/actor/Props;
/*     */     //   230: ldc 'UnhandledMessageForwarder'
/*     */     //   232: invokevirtual systemActorOf : (Lakka/actor/Props;Ljava/lang/String;)Lakka/actor/ActorRef;
/*     */     //   235: ldc akka/actor/UnhandledMessage
/*     */     //   237: invokeinterface subscribe : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   242: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   245: goto -> 258
/*     */     //   248: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   251: goto -> 258
/*     */     //   254: pop
/*     */     //   255: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   258: pop
/*     */     //   259: aload_0
/*     */     //   260: new akka/event/Logging$Debug
/*     */     //   263: dup
/*     */     //   264: aload_2
/*     */     //   265: aload_0
/*     */     //   266: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   269: ldc_w 'Default Loggers started'
/*     */     //   272: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   275: invokeinterface publish : (Ljava/lang/Object;)V
/*     */     //   280: aload #5
/*     */     //   282: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   285: invokevirtual StandardOutLogger : ()Lakka/event/Logging$StandardOutLogger;
/*     */     //   288: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   291: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   294: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   299: ifne -> 371
/*     */     //   302: aload_0
/*     */     //   303: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   306: invokevirtual StandardOutLogger : ()Lakka/event/Logging$StandardOutLogger;
/*     */     //   309: invokeinterface unsubscribe : (Ljava/lang/Object;)V
/*     */     //   314: goto -> 371
/*     */     //   317: astore #4
/*     */     //   319: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*     */     //   322: ldc_w 'error while starting up loggers'
/*     */     //   325: invokevirtual println : (Ljava/lang/String;)V
/*     */     //   328: aload #4
/*     */     //   330: invokevirtual printStackTrace : ()V
/*     */     //   333: new akka/ConfigurationException
/*     */     //   336: dup
/*     */     //   337: new scala/collection/mutable/StringBuilder
/*     */     //   340: dup
/*     */     //   341: invokespecial <init> : ()V
/*     */     //   344: ldc_w 'Could not start logger due to ['
/*     */     //   347: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   350: aload #4
/*     */     //   352: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   355: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   358: ldc_w ']'
/*     */     //   361: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   364: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   367: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   370: athrow
/*     */     //   371: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 0
/*     */     //   #96	-> 35
/*     */     //   #102	-> 68
/*     */     //   #103	-> 77
/*     */     //   #104	-> 125
/*     */     //   #102	-> 129
/*     */     //   #108	-> 133
/*     */     //   #106	-> 173
/*     */     //   #119	-> 175
/*     */     //   #124	-> 196
/*     */     //   #125	-> 206
/*     */     //   #130	-> 230
/*     */     //   #125	-> 232
/*     */     //   #130	-> 235
/*     */     //   #125	-> 237
/*     */     //   #124	-> 248
/*     */     //   #123	-> 254
/*     */     //   #132	-> 255
/*     */     //   #123	-> 258
/*     */     //   #134	-> 259
/*     */     //   #135	-> 280
/*     */     //   #136	-> 302
/*     */     //   #139	-> 317
/*     */     //   #101	-> 317
/*     */     //   #140	-> 319
/*     */     //   #141	-> 328
/*     */     //   #142	-> 333
/*     */     //   #94	-> 371
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	372	0	$this	Lakka/event/LoggingBus;
/*     */     //   0	372	1	system	Lakka/actor/ActorSystemImpl;
/*     */     //   35	337	2	logName	Ljava/lang/String;
/*     */     //   68	304	3	level	I
/*     */     //   133	239	5	defaultLoggers	Lscala/collection/immutable/Seq;
/*     */     //   175	197	10	myloggers	Lscala/collection/immutable/Seq;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   68	317	317	java/lang/Exception
/*     */     //   196	254	254	akka/actor/InvalidActorNameException
/*     */   }
/*     */   
/*     */   public static void stopDefaultLoggers(LoggingBus $this, ActorSystem system) {
/* 150 */     int level = $this.akka$event$LoggingBus$$_logLevel();
/* 151 */     if (!$this.akka$event$LoggingBus$$loggers().contains(Logging$.MODULE$.StandardOutLogger())) {
/* 152 */       setUpStdoutLogger($this, system.settings());
/* 153 */       $this.publish(new Logging.Debug(Logging$.MODULE$.simpleName($this), $this.getClass(), "shutting down: StandardOutLogger started"));
/*     */     } 
/* 156 */     $this.akka$event$LoggingBus$$loggers().withFilter((Function1)new LoggingBus$$anonfun$stopDefaultLoggers$1($this)).foreach((Function1)new LoggingBus$$anonfun$stopDefaultLoggers$2($this));
/* 166 */     $this.publish(new Logging.Debug(Logging$.MODULE$.simpleName($this), $this.getClass(), "all default loggers stopped"));
/*     */   }
/*     */   
/*     */   public static ActorRef akka$event$LoggingBus$$addLogger(LoggingBus $this, ActorSystemImpl system, Class<?> clazz, int level, String logName) {
/* 173 */     String name = (new StringBuilder()).append("log").append(BoxesRunTime.boxToInteger(((Logging.LogExt)Logging.Extension$.MODULE$.apply((ActorSystem)system)).id())).append("-").append(Logging$.MODULE$.simpleName(clazz)).toString();
/* 174 */     ActorRef actor = system.systemActorOf(Props$.MODULE$.apply(clazz, (Seq)Predef$.MODULE$.genericWrapArray(new Object[0])), name);
/*     */     try {
/*     */     
/* 177 */     } catch (TimeoutException timeoutException) {
/* 179 */       $this.publish(new Logging.Warning(logName, $this.getClass(), (new StringBuilder()).append("Logger ").append(name).append(" did not respond within ").append(timeout$1($this, system)).append(" to InitializeLogger(bus)").toString()));
/*     */     } 
/* 180 */     Object response = "[TIMEOUT]";
/* 182 */     Logging.LoggerInitialized$ loggerInitialized$ = Logging.LoggerInitialized$.MODULE$;
/* 182 */     if (response == null) {
/* 182 */       if (loggerInitialized$ != null)
/* 183 */         throw new Logging.LoggerInitializationException((new StringBuilder()).append("Logger ").append(name).append(" did not respond with LoggerInitialized, sent instead ").append(response).toString()); 
/*     */     } else if (!response.equals(loggerInitialized$)) {
/* 183 */       throw new Logging.LoggerInitializationException((new StringBuilder()).append("Logger ").append(name).append(" did not respond with LoggerInitialized, sent instead ").append(response).toString());
/*     */     } 
/* 184 */     ((IterableLike)Logging$.MODULE$.AllLogLevels().filter((Function1)new LoggingBus$$anonfun$akka$event$LoggingBus$$addLogger$1($this, level))).foreach((Function1)new LoggingBus$$anonfun$akka$event$LoggingBus$$addLogger$2($this, actor));
/* 185 */     $this.publish(new Logging.Debug(logName, $this.getClass(), (new StringBuilder()).append("logger ").append(name).append(" started").toString()));
/* 186 */     return actor;
/*     */   }
/*     */   
/*     */   private static final Timeout timeout$1(LoggingBus $this, ActorSystemImpl system$1) {
/*     */     return system$1.settings().LoggerStartTimeout();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LoggingBus$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */