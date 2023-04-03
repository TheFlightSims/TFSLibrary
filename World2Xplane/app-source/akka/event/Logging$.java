/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.UntypedActor;
/*     */ import java.util.Map;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ public final class Logging$ {
/*     */   public static final Logging$ MODULE$;
/*     */   
/*     */   private final int ErrorLevel;
/*     */   
/*     */   private final int WarningLevel;
/*     */   
/*     */   private final int InfoLevel;
/*     */   
/*     */   private final int DebugLevel;
/*     */   
/*     */   private final int OffLevel;
/*     */   
/*     */   private final Seq<Logging.LogLevel> AllLogLevels;
/*     */   
/*     */   private final Logging.StandardOutLogger StandardOutLogger;
/*     */   
/*     */   private final Map<String, Object> emptyMDC;
/*     */   
/*     */   private Logging$() {
/* 375 */     MODULE$ = this;
/* 424 */     this.ErrorLevel = 1;
/* 425 */     this.WarningLevel = 2;
/* 426 */     this.InfoLevel = 3;
/* 427 */     this.DebugLevel = 4;
/* 435 */     this.OffLevel = Integer.MIN_VALUE;
/* 474 */     this.AllLogLevels = (Seq<Logging.LogLevel>)scala.package$.MODULE$.Vector().apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Logging.LogLevel[] { new Logging.LogLevel(ErrorLevel()), new Logging.LogLevel(WarningLevel()), new Logging.LogLevel(InfoLevel()), new Logging.LogLevel(DebugLevel()) }));
/* 787 */     this.StandardOutLogger = new Logging.StandardOutLogger();
/* 817 */     this.emptyMDC = (Map<String, Object>)scala.Predef$.MODULE$.Map().apply((Seq)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public String simpleName(Object obj) {
/*     */     return simpleName(obj.getClass());
/*     */   }
/*     */   
/*     */   public String simpleName(Class clazz) {
/*     */     String n = clazz.getName();
/*     */     int i = n.lastIndexOf('.');
/*     */     return n.substring(i + 1);
/*     */   }
/*     */   
/*     */   public final int ErrorLevel() {
/*     */     return this.ErrorLevel;
/*     */   }
/*     */   
/*     */   public final int WarningLevel() {
/*     */     return this.WarningLevel;
/*     */   }
/*     */   
/*     */   public final int InfoLevel() {
/*     */     return this.InfoLevel;
/*     */   }
/*     */   
/*     */   public final int DebugLevel() {
/*     */     return this.DebugLevel;
/*     */   }
/*     */   
/*     */   private final int OffLevel() {
/*     */     return this.OffLevel;
/*     */   }
/*     */   
/*     */   public Option<Logging.LogLevel> levelFor(String s) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual toLowerCase : ()Ljava/lang/String;
/*     */     //   4: astore_2
/*     */     //   5: ldc 'off'
/*     */     //   7: aload_2
/*     */     //   8: astore_3
/*     */     //   9: dup
/*     */     //   10: ifnonnull -> 21
/*     */     //   13: pop
/*     */     //   14: aload_3
/*     */     //   15: ifnull -> 28
/*     */     //   18: goto -> 51
/*     */     //   21: aload_3
/*     */     //   22: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   25: ifeq -> 51
/*     */     //   28: new scala/Some
/*     */     //   31: dup
/*     */     //   32: new akka/event/Logging$LogLevel
/*     */     //   35: dup
/*     */     //   36: aload_0
/*     */     //   37: invokespecial OffLevel : ()I
/*     */     //   40: invokespecial <init> : (I)V
/*     */     //   43: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   46: astore #4
/*     */     //   48: goto -> 252
/*     */     //   51: ldc 'error'
/*     */     //   53: aload_2
/*     */     //   54: astore #5
/*     */     //   56: dup
/*     */     //   57: ifnonnull -> 69
/*     */     //   60: pop
/*     */     //   61: aload #5
/*     */     //   63: ifnull -> 77
/*     */     //   66: goto -> 100
/*     */     //   69: aload #5
/*     */     //   71: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   74: ifeq -> 100
/*     */     //   77: new scala/Some
/*     */     //   80: dup
/*     */     //   81: new akka/event/Logging$LogLevel
/*     */     //   84: dup
/*     */     //   85: aload_0
/*     */     //   86: invokevirtual ErrorLevel : ()I
/*     */     //   89: invokespecial <init> : (I)V
/*     */     //   92: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   95: astore #4
/*     */     //   97: goto -> 252
/*     */     //   100: ldc 'warning'
/*     */     //   102: aload_2
/*     */     //   103: astore #6
/*     */     //   105: dup
/*     */     //   106: ifnonnull -> 118
/*     */     //   109: pop
/*     */     //   110: aload #6
/*     */     //   112: ifnull -> 126
/*     */     //   115: goto -> 149
/*     */     //   118: aload #6
/*     */     //   120: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   123: ifeq -> 149
/*     */     //   126: new scala/Some
/*     */     //   129: dup
/*     */     //   130: new akka/event/Logging$LogLevel
/*     */     //   133: dup
/*     */     //   134: aload_0
/*     */     //   135: invokevirtual WarningLevel : ()I
/*     */     //   138: invokespecial <init> : (I)V
/*     */     //   141: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   144: astore #4
/*     */     //   146: goto -> 252
/*     */     //   149: ldc 'info'
/*     */     //   151: aload_2
/*     */     //   152: astore #7
/*     */     //   154: dup
/*     */     //   155: ifnonnull -> 167
/*     */     //   158: pop
/*     */     //   159: aload #7
/*     */     //   161: ifnull -> 175
/*     */     //   164: goto -> 198
/*     */     //   167: aload #7
/*     */     //   169: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   172: ifeq -> 198
/*     */     //   175: new scala/Some
/*     */     //   178: dup
/*     */     //   179: new akka/event/Logging$LogLevel
/*     */     //   182: dup
/*     */     //   183: aload_0
/*     */     //   184: invokevirtual InfoLevel : ()I
/*     */     //   187: invokespecial <init> : (I)V
/*     */     //   190: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   193: astore #4
/*     */     //   195: goto -> 252
/*     */     //   198: ldc 'debug'
/*     */     //   200: aload_2
/*     */     //   201: astore #8
/*     */     //   203: dup
/*     */     //   204: ifnonnull -> 216
/*     */     //   207: pop
/*     */     //   208: aload #8
/*     */     //   210: ifnull -> 224
/*     */     //   213: goto -> 247
/*     */     //   216: aload #8
/*     */     //   218: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   221: ifeq -> 247
/*     */     //   224: new scala/Some
/*     */     //   227: dup
/*     */     //   228: new akka/event/Logging$LogLevel
/*     */     //   231: dup
/*     */     //   232: aload_0
/*     */     //   233: invokevirtual DebugLevel : ()I
/*     */     //   236: invokespecial <init> : (I)V
/*     */     //   239: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   242: astore #4
/*     */     //   244: goto -> 252
/*     */     //   247: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   250: astore #4
/*     */     //   252: aload #4
/*     */     //   254: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #442	-> 0
/*     */     //   #443	-> 5
/*     */     //   #444	-> 51
/*     */     //   #445	-> 100
/*     */     //   #446	-> 149
/*     */     //   #447	-> 198
/*     */     //   #448	-> 247
/*     */     //   #442	-> 252
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	255	0	this	Lakka/event/Logging$;
/*     */     //   0	255	1	s	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public int levelFor(Class<?> eventClass) {
/*     */     return Logging.Error.class.isAssignableFrom(eventClass) ? ErrorLevel() : (Logging.Warning.class.isAssignableFrom(eventClass) ? WarningLevel() : (Logging.Info.class.isAssignableFrom(eventClass) ? InfoLevel() : (Logging.Debug.class.isAssignableFrom(eventClass) ? DebugLevel() : DebugLevel())));
/*     */   }
/*     */   
/*     */   public Class<? extends Logging.LogEvent> classFor(int level) {
/*     */     int i = level;
/*     */     if (ErrorLevel() == i) {
/*     */       Class<Logging.Error> clazz = Logging.Error.class;
/*     */     } else if (WarningLevel() == i) {
/*     */       Class<Logging.Warning> clazz = Logging.Warning.class;
/*     */     } else if (InfoLevel() == i) {
/*     */       Class<Logging.Info> clazz = Logging.Info.class;
/*     */     } else {
/*     */       if (DebugLevel() == i)
/*     */         return (Class)Logging.Debug.class; 
/*     */       throw new MatchError(new Logging.LogLevel(i));
/*     */     } 
/*     */     return (Class<? extends Logging.LogEvent>)SYNTHETIC_LOCAL_VARIABLE_3;
/*     */   }
/*     */   
/*     */   public Seq<Logging.LogLevel> AllLogLevels() {
/*     */     return this.AllLogLevels;
/*     */   }
/*     */   
/*     */   public <T> LoggingAdapter apply(ActorSystem system, Object logSource, LogSource evidence$3) {
/*     */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.apply(logSource, system, evidence$3);
/*     */     if (tuple2 != null) {
/*     */       String str = (String)tuple2._1();
/*     */       Class clazz = (Class)tuple2._2();
/*     */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       Class<?> clazz1 = (Class)tuple21._2();
/*     */       return new BusLogging(system.eventStream(), str1, clazz1);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public <T> LoggingAdapter apply(LoggingBus bus, Object logSource, LogSource evidence$4) {
/*     */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.apply(logSource, evidence$4);
/*     */     if (tuple2 != null) {
/*     */       String str = (String)tuple2._1();
/*     */       Class clazz = (Class)tuple2._2();
/*     */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       Class<?> clazz1 = (Class)tuple21._2();
/*     */       return new BusLogging(bus, str1, clazz1);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public DiagnosticLoggingAdapter apply(Actor logSource) {
/*     */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.apply(logSource, LogSource$.MODULE$.fromActor());
/*     */     if (tuple2 != null) {
/*     */       String str = (String)tuple2._1();
/*     */       Class clazz = (Class)tuple2._2();
/*     */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       Class clazz1 = (Class)tuple21._2();
/*     */       return new Logging$$anon$1(logSource, str1, clazz1);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static class Logging$$anon$1 extends BusLogging implements DiagnosticLoggingAdapter {
/*     */     private Map<String, Object> akka$event$DiagnosticLoggingAdapter$$_mdc;
/*     */     
/*     */     public Map<String, Object> akka$event$DiagnosticLoggingAdapter$$_mdc() {
/*     */       return this.akka$event$DiagnosticLoggingAdapter$$_mdc;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void akka$event$DiagnosticLoggingAdapter$$_mdc_$eq(Map<String, Object> x$1) {
/*     */       this.akka$event$DiagnosticLoggingAdapter$$_mdc = x$1;
/*     */     }
/*     */     
/*     */     public Map<String, Object> mdc() {
/*     */       return DiagnosticLoggingAdapter$class.mdc(this);
/*     */     }
/*     */     
/*     */     public void mdc(Map mdc) {
/*     */       DiagnosticLoggingAdapter$class.mdc(this, mdc);
/*     */     }
/*     */     
/*     */     public Map<String, Object> getMDC() {
/*     */       return DiagnosticLoggingAdapter$class.getMDC(this);
/*     */     }
/*     */     
/*     */     public void setMDC(Map jMdc) {
/*     */       DiagnosticLoggingAdapter$class.setMDC(this, jMdc);
/*     */     }
/*     */     
/*     */     public void clearMDC() {
/*     */       DiagnosticLoggingAdapter$class.clearMDC(this);
/*     */     }
/*     */     
/*     */     public Logging$$anon$1(Actor logSource$1, String str$1, Class<?> clazz$1) {
/*     */       super(logSource$1.context().system().eventStream(), str$1, clazz$1);
/*     */       DiagnosticLoggingAdapter$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public LoggingAdapter getLogger(ActorSystem system, Object logSource) {
/*     */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.fromAnyRef(logSource, system);
/*     */     if (tuple2 != null) {
/*     */       String str = (String)tuple2._1();
/*     */       Class clazz = (Class)tuple2._2();
/*     */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       Class<?> clazz1 = (Class)tuple21._2();
/*     */       return new BusLogging(system.eventStream(), str1, clazz1);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public LoggingAdapter getLogger(LoggingBus bus, Object logSource) {
/*     */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.fromAnyRef(logSource);
/*     */     if (tuple2 != null) {
/*     */       String str = (String)tuple2._1();
/*     */       Class clazz = (Class)tuple2._2();
/*     */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       Class<?> clazz1 = (Class)tuple21._2();
/*     */       return new BusLogging(bus, str1, clazz1);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public DiagnosticLoggingAdapter getLogger(UntypedActor logSource) {
/*     */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.fromAnyRef(logSource);
/*     */     if (tuple2 != null) {
/*     */       String str = (String)tuple2._1();
/*     */       Class clazz = (Class)tuple2._2();
/*     */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       Class clazz1 = (Class)tuple21._2();
/*     */       return new Logging$$anon$2(logSource, str1, clazz1);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public static class Logging$$anon$2 extends BusLogging implements DiagnosticLoggingAdapter {
/*     */     private Map<String, Object> akka$event$DiagnosticLoggingAdapter$$_mdc;
/*     */     
/*     */     public Map<String, Object> akka$event$DiagnosticLoggingAdapter$$_mdc() {
/*     */       return this.akka$event$DiagnosticLoggingAdapter$$_mdc;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void akka$event$DiagnosticLoggingAdapter$$_mdc_$eq(Map<String, Object> x$1) {
/*     */       this.akka$event$DiagnosticLoggingAdapter$$_mdc = x$1;
/*     */     }
/*     */     
/*     */     public Map<String, Object> mdc() {
/*     */       return DiagnosticLoggingAdapter$class.mdc(this);
/*     */     }
/*     */     
/*     */     public void mdc(Map mdc) {
/*     */       DiagnosticLoggingAdapter$class.mdc(this, mdc);
/*     */     }
/*     */     
/*     */     public Map<String, Object> getMDC() {
/*     */       return DiagnosticLoggingAdapter$class.getMDC(this);
/*     */     }
/*     */     
/*     */     public void setMDC(Map jMdc) {
/*     */       DiagnosticLoggingAdapter$class.setMDC(this, jMdc);
/*     */     }
/*     */     
/*     */     public void clearMDC() {
/*     */       DiagnosticLoggingAdapter$class.clearMDC(this);
/*     */     }
/*     */     
/*     */     public Logging$$anon$2(UntypedActor logSource$2, String str$2, Class<?> clazz$2) {
/*     */       super(logSource$2.getContext().system().eventStream(), str$2, clazz$2);
/*     */       DiagnosticLoggingAdapter$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public Logging.Error$.oCause$ noCause() {
/*     */     return Logging.Error$.oCause$.MODULE$;
/*     */   }
/*     */   
/*     */   public Logging.LoggerInitialized$ loggerInitialized() {
/*     */     return Logging.LoggerInitialized$.MODULE$;
/*     */   }
/*     */   
/*     */   public Logging.StandardOutLogger StandardOutLogger() {
/*     */     return this.StandardOutLogger;
/*     */   }
/*     */   
/*     */   public String stackTraceFor(Throwable e) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: ifnonnull -> 11
/*     */     //   6: iconst_1
/*     */     //   7: istore_3
/*     */     //   8: goto -> 45
/*     */     //   11: getstatic akka/event/Logging$Error$NoCause$.MODULE$ : Lakka/event/Logging$Error$NoCause$;
/*     */     //   14: aload_2
/*     */     //   15: astore #4
/*     */     //   17: dup
/*     */     //   18: ifnonnull -> 30
/*     */     //   21: pop
/*     */     //   22: aload #4
/*     */     //   24: ifnull -> 38
/*     */     //   27: goto -> 43
/*     */     //   30: aload #4
/*     */     //   32: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   35: ifeq -> 43
/*     */     //   38: iconst_1
/*     */     //   39: istore_3
/*     */     //   40: goto -> 45
/*     */     //   43: iconst_0
/*     */     //   44: istore_3
/*     */     //   45: iload_3
/*     */     //   46: ifeq -> 56
/*     */     //   49: ldc ''
/*     */     //   51: astore #5
/*     */     //   53: goto -> 139
/*     */     //   56: aload_2
/*     */     //   57: instanceof scala/util/control/NoStackTrace
/*     */     //   60: ifeq -> 98
/*     */     //   63: new scala/collection/mutable/StringBuilder
/*     */     //   66: dup
/*     */     //   67: invokespecial <init> : ()V
/*     */     //   70: ldc ' ('
/*     */     //   72: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   75: aload_1
/*     */     //   76: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   79: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   82: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   85: ldc ')'
/*     */     //   87: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   90: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   93: astore #5
/*     */     //   95: goto -> 139
/*     */     //   98: new java/io/StringWriter
/*     */     //   101: dup
/*     */     //   102: invokespecial <init> : ()V
/*     */     //   105: astore #6
/*     */     //   107: new java/io/PrintWriter
/*     */     //   110: dup
/*     */     //   111: aload #6
/*     */     //   113: invokespecial <init> : (Ljava/io/Writer;)V
/*     */     //   116: astore #7
/*     */     //   118: aload #7
/*     */     //   120: bipush #10
/*     */     //   122: invokevirtual append : (C)Ljava/io/PrintWriter;
/*     */     //   125: pop
/*     */     //   126: aload_2
/*     */     //   127: aload #7
/*     */     //   129: invokevirtual printStackTrace : (Ljava/io/PrintWriter;)V
/*     */     //   132: aload #6
/*     */     //   134: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   137: astore #5
/*     */     //   139: aload #5
/*     */     //   141: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #804	-> 0
/*     */     //   #805	-> 2
/*     */     //   #806	-> 56
/*     */     //   #808	-> 98
/*     */     //   #809	-> 107
/*     */     //   #810	-> 118
/*     */     //   #811	-> 126
/*     */     //   #812	-> 132
/*     */     //   #807	-> 137
/*     */     //   #804	-> 139
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	142	0	this	Lakka/event/Logging$;
/*     */     //   0	142	1	e	Ljava/lang/Throwable;
/*     */     //   107	30	6	sw	Ljava/io/StringWriter;
/*     */     //   118	19	7	pw	Ljava/io/PrintWriter;
/*     */   }
/*     */   
/*     */   public Map<String, Object> emptyMDC() {
/* 817 */     return this.emptyMDC;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\Logging$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */