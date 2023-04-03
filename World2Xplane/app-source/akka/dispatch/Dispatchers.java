/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.ConfigurationException;
/*     */ import akka.actor.ActorSystem;
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.collection.JavaConverters$;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005er!B\001\003\021\0039\021a\003#jgB\fGo\0315feNT!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\027\021K7\017]1uG\",'o]\n\003\0231\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007\"B\n\n\t\003!\022A\002\037j]&$h\bF\001\b\021\0351\022B1A\005\006]\t1\003R3gCVdG\017R5ta\006$8\r[3s\023\022,\022\001G\b\0023\005\n!$A\017bW.\fg&Y2u_JtC-\0324bk2$X\006Z5ta\006$8\r[3s\021\031a\022\002)A\0071\005!B)\0324bk2$H)[:qCR\034\007.\032:JI\0022AA\003\002\001=M\021Q\004\004\005\tAu\021)\031!C\001C\005A1/\032;uS:<7/F\001#!\t\031\023F\004\002%O5\tQE\003\002'\t\005)\021m\031;pe&\021\001&J\001\f\003\016$xN]*zgR,W.\003\002+W\tA1+\032;uS:<7O\003\002)K!AQ&\bB\001B\003%!%A\005tKR$\030N\\4tA!Aq&\bBC\002\023\005\001'A\007qe\026\024X-];jg&$Xm]\013\002cA\021\001BM\005\003g\t\021q\003R5ta\006$8\r[3s!J,'/Z9vSNLG/Z:\t\021Uj\"\021!Q\001\nE\na\002\035:fe\026\fX/[:ji\026\034\b\005C\003\024;\021\005q\007F\0029si\002\"\001C\017\t\013\0012\004\031\001\022\t\013=2\004\031A\031\t\017qj\"\031!C\001{\005i1-Y2iS:<7i\0348gS\036,\022A\020\t\003\021}J!\001\021\002\003\033\r\0137\r[5oO\016{gNZ5h\021\031\021U\004)A\005}\005q1-Y2iS:<7i\0348gS\036\004\003b\002#\036\005\004%\t!R\001\030I\0264\027-\0367u\t&\034\b/\031;dQ\026\0248i\0348gS\036,\022A\022\t\003\017:k\021\001\023\006\003\023*\013aaY8oM&<'BA&M\003!!\030\020]3tC\032,'\"A'\002\007\r|W.\003\002P\021\n11i\0348gS\036Da!U\017!\002\0231\025\001\0073fM\006,H\016\036#jgB\fGo\0315fe\016{gNZ5hA!)1+\bC\001)\0069B-\0324bk2$x\t\\8cC2$\025n\0359bi\016DWM]\013\002+B\021\001BV\005\003/\n\021\021#T3tg\006<W\rR5ta\006$8\r[3s\021\035IVD1A\005\ni\013q\003Z5ta\006$8\r[3s\007>tg-[4ve\006$xN]:\026\003m\003B\001X2fY6\tQL\003\002_?\006Q1m\0348dkJ\024XM\034;\013\005\001\f\027\001B;uS2T\021AY\001\005U\0064\030-\003\002e;\n\t2i\0348dkJ\024XM\034;ICNDW*\0319\021\005\031LgBA\007h\023\tAg\"\001\004Qe\026$WMZ\005\003U.\024aa\025;sS:<'B\0015\017!\tAQ.\003\002o\005\tiR*Z:tC\036,G)[:qCR\034\007.\032:D_:4\027nZ;sCR|'\017\003\004q;\001\006IaW\001\031I&\034\b/\031;dQ\026\0248i\0348gS\036,(/\031;peN\004\003\"\002:\036\t\003\031\030A\0027p_.,\b\017\006\002Vi\")Q/\035a\001K\006\021\021\016\032\005\006ov!\t\001_\001\016Q\006\034H)[:qCR\034\007.\032:\025\005ed\bCA\007{\023\tYhBA\004C_>dW-\0318\t\013U4\b\031A3\t\013ylB\021B@\002%1|wn[;q\007>tg-[4ve\006$xN\035\013\004Y\006\005\001\"B;~\001\004)\007bBA\003;\021\005\021qA\001\025e\026<\027n\035;fe\016{gNZ5hkJ\fGo\034:\025\013e\fI!a\003\t\rU\f\031\0011\001f\021\035\ti!a\001A\0021\fAbY8oM&<WO]1u_JDq!S\017\005\002\021\t\t\002F\002G\003'Aa!^A\b\001\004)\007bB%\036\t\003!\021q\003\013\006\r\006e\0211\004\005\007k\006U\001\031A3\t\017\005u\021Q\003a\001\r\006I\021\r\0359D_:4\027n\032\005\b\003CiB\021BA\022\003!IGmQ8oM&<Gc\001$\002&!1Q/a\bA\002\025D\001\"!\013\036\t\003!\0211F\001\005MJ|W\016F\002V\003[Aq!a\f\002(\001\007a)A\002dM\036Dq!a\r\036\t\023\t)$\001\td_:4\027nZ;sCR|'O\022:p[R\031A.a\016\t\017\005=\022\021\007a\001\r\002")
/*     */ public class Dispatchers {
/*     */   private final ActorSystem.Settings settings;
/*     */   
/*     */   private final DispatcherPrerequisites prerequisites;
/*     */   
/*     */   private final CachingConfig cachingConfig;
/*     */   
/*     */   private final Config defaultDispatcherConfig;
/*     */   
/*     */   private final ConcurrentHashMap<String, MessageDispatcherConfigurator> dispatcherConfigurators;
/*     */   
/*     */   public ActorSystem.Settings settings() {
/*  59 */     return this.settings;
/*     */   }
/*     */   
/*     */   public DispatcherPrerequisites prerequisites() {
/*  59 */     return this.prerequisites;
/*     */   }
/*     */   
/*     */   public Dispatchers(ActorSystem.Settings settings, DispatcherPrerequisites prerequisites) {
/*  63 */     this.cachingConfig = new CachingConfig(settings.config());
/*  65 */     this.defaultDispatcherConfig = 
/*  66 */       idConfig("akka.actor.default-dispatcher").withFallback((ConfigMergeable)settings.config().getConfig("akka.actor.default-dispatcher"));
/*  73 */     this.dispatcherConfigurators = new ConcurrentHashMap<String, MessageDispatcherConfigurator>();
/*     */   }
/*     */   
/*     */   public CachingConfig cachingConfig() {
/*     */     return this.cachingConfig;
/*     */   }
/*     */   
/*     */   public Config defaultDispatcherConfig() {
/*     */     return this.defaultDispatcherConfig;
/*     */   }
/*     */   
/*     */   public MessageDispatcher defaultGlobalDispatcher() {
/*     */     return lookup("akka.actor.default-dispatcher");
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<String, MessageDispatcherConfigurator> dispatcherConfigurators() {
/*  73 */     return this.dispatcherConfigurators;
/*     */   }
/*     */   
/*     */   public MessageDispatcher lookup(String id) {
/*  81 */     return lookupConfigurator(id).dispatcher();
/*     */   }
/*     */   
/*     */   public boolean hasDispatcher(String id) {
/*  89 */     return (dispatcherConfigurators().containsKey(id) || cachingConfig().hasPath(id));
/*     */   }
/*     */   
/*     */   private MessageDispatcherConfigurator lookupConfigurator(String id) {
/*  92 */     MessageDispatcherConfigurator messageDispatcherConfigurator = dispatcherConfigurators().get(id);
/*  93 */     if (messageDispatcherConfigurator == null) {
/*  98 */       if (cachingConfig().hasPath(id)) {
/*  98 */         MessageDispatcherConfigurator newConfigurator = configuratorFrom(config(id));
/* 101 */         MessageDispatcherConfigurator messageDispatcherConfigurator1 = dispatcherConfigurators().putIfAbsent(id, newConfigurator);
/*     */       } 
/*     */       (new String[2])[0] = "Dispatcher [";
/*     */       (new String[2])[1] = "] not configured";
/*     */       throw new ConfigurationException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { id })));
/*     */     } 
/* 106 */     return messageDispatcherConfigurator;
/*     */   }
/*     */   
/*     */   public boolean registerConfigurator(String id, MessageDispatcherConfigurator configurator) {
/* 123 */     return (dispatcherConfigurators().putIfAbsent(id, configurator) == null);
/*     */   }
/*     */   
/*     */   public Config config(String id) {
/* 129 */     return config(id, settings().config().getConfig(id));
/*     */   }
/*     */   
/*     */   private final String simpleName$1(String id$1) {
/* 137 */     return id$1.substring(id$1.lastIndexOf('.') + 1);
/*     */   }
/*     */   
/*     */   public Config config(String id, Config appConfig) {
/* 140 */     (new scala.Tuple2[1])[0] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc("name"), simpleName$1(id));
/* 141 */     return idConfig(id).withFallback((ConfigMergeable)appConfig).withFallback((ConfigMergeable)ConfigFactory.parseMap((Map)JavaConverters$.MODULE$.mapAsJavaMapConverter((Map)Predef$.MODULE$.Map().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new scala.Tuple2[1]))).asJava())).withFallback((ConfigMergeable)defaultDispatcherConfig());
/*     */   }
/*     */   
/*     */   private Config idConfig(String id) {
/* 146 */     (new scala.Tuple2[1])[0] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc("id"), id);
/* 146 */     return ConfigFactory.parseMap((Map)JavaConverters$.MODULE$.mapAsJavaMapConverter((Map)Predef$.MODULE$.Map().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new scala.Tuple2[1]))).asJava());
/*     */   }
/*     */   
/*     */   public MessageDispatcher from(Config cfg) {
/* 161 */     return configuratorFrom(cfg).dispatcher();
/*     */   }
/*     */   
/*     */   private MessageDispatcherConfigurator configuratorFrom(Config cfg) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'id'
/*     */     //   3: invokeinterface hasPath : (Ljava/lang/String;)Z
/*     */     //   8: ifeq -> 270
/*     */     //   11: aload_1
/*     */     //   12: ldc 'type'
/*     */     //   14: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   19: astore_2
/*     */     //   20: ldc 'Dispatcher'
/*     */     //   22: aload_2
/*     */     //   23: astore_3
/*     */     //   24: dup
/*     */     //   25: ifnonnull -> 36
/*     */     //   28: pop
/*     */     //   29: aload_3
/*     */     //   30: ifnull -> 43
/*     */     //   33: goto -> 60
/*     */     //   36: aload_3
/*     */     //   37: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   40: ifeq -> 60
/*     */     //   43: new akka/dispatch/DispatcherConfigurator
/*     */     //   46: dup
/*     */     //   47: aload_1
/*     */     //   48: aload_0
/*     */     //   49: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   52: invokespecial <init> : (Lcom/typesafe/config/Config;Lakka/dispatch/DispatcherPrerequisites;)V
/*     */     //   55: astore #4
/*     */     //   57: goto -> 267
/*     */     //   60: ldc 'BalancingDispatcher'
/*     */     //   62: aload_2
/*     */     //   63: astore #5
/*     */     //   65: dup
/*     */     //   66: ifnonnull -> 78
/*     */     //   69: pop
/*     */     //   70: aload #5
/*     */     //   72: ifnull -> 86
/*     */     //   75: goto -> 118
/*     */     //   78: aload #5
/*     */     //   80: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   83: ifeq -> 118
/*     */     //   86: new java/lang/IllegalArgumentException
/*     */     //   89: dup
/*     */     //   90: new scala/collection/mutable/StringBuilder
/*     */     //   93: dup
/*     */     //   94: invokespecial <init> : ()V
/*     */     //   97: ldc 'BalancingDispatcher is deprecated, use a BalancingPool instead. During a migration period you can still use BalancingDispatcher by specifying the full class name: '
/*     */     //   99: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   102: ldc_w akka/dispatch/BalancingDispatcherConfigurator
/*     */     //   105: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   108: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   111: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   114: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   117: athrow
/*     */     //   118: ldc_w 'PinnedDispatcher'
/*     */     //   121: aload_2
/*     */     //   122: astore #6
/*     */     //   124: dup
/*     */     //   125: ifnonnull -> 137
/*     */     //   128: pop
/*     */     //   129: aload #6
/*     */     //   131: ifnull -> 145
/*     */     //   134: goto -> 162
/*     */     //   137: aload #6
/*     */     //   139: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   142: ifeq -> 162
/*     */     //   145: new akka/dispatch/PinnedDispatcherConfigurator
/*     */     //   148: dup
/*     */     //   149: aload_1
/*     */     //   150: aload_0
/*     */     //   151: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   154: invokespecial <init> : (Lcom/typesafe/config/Config;Lakka/dispatch/DispatcherPrerequisites;)V
/*     */     //   157: astore #4
/*     */     //   159: goto -> 267
/*     */     //   162: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   165: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   168: iconst_2
/*     */     //   169: anewarray scala/Tuple2
/*     */     //   172: dup
/*     */     //   173: iconst_0
/*     */     //   174: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   177: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   180: ldc com/typesafe/config/Config
/*     */     //   182: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   185: aload_1
/*     */     //   186: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   189: aastore
/*     */     //   190: dup
/*     */     //   191: iconst_1
/*     */     //   192: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   195: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   198: ldc_w akka/dispatch/DispatcherPrerequisites
/*     */     //   201: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   204: aload_0
/*     */     //   205: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   208: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   211: aastore
/*     */     //   212: checkcast [Ljava/lang/Object;
/*     */     //   215: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   218: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/immutable/List;
/*     */     //   221: astore #7
/*     */     //   223: aload_0
/*     */     //   224: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   227: invokeinterface dynamicAccess : ()Lakka/actor/DynamicAccess;
/*     */     //   232: aload_2
/*     */     //   233: aload #7
/*     */     //   235: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   238: ldc akka/dispatch/MessageDispatcherConfigurator
/*     */     //   240: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   243: invokevirtual createInstanceFor : (Ljava/lang/String;Lscala/collection/immutable/Seq;Lscala/reflect/ClassTag;)Lscala/util/Try;
/*     */     //   246: new akka/dispatch/Dispatchers$$anonfun$configuratorFrom$1
/*     */     //   249: dup
/*     */     //   250: aload_0
/*     */     //   251: aload_1
/*     */     //   252: aload_2
/*     */     //   253: invokespecial <init> : (Lakka/dispatch/Dispatchers;Lcom/typesafe/config/Config;Ljava/lang/String;)V
/*     */     //   256: invokevirtual recover : (Lscala/PartialFunction;)Lscala/util/Try;
/*     */     //   259: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   262: checkcast akka/dispatch/MessageDispatcherConfigurator
/*     */     //   265: astore #4
/*     */     //   267: aload #4
/*     */     //   269: areturn
/*     */     //   270: new akka/ConfigurationException
/*     */     //   273: dup
/*     */     //   274: new scala/collection/mutable/StringBuilder
/*     */     //   277: dup
/*     */     //   278: invokespecial <init> : ()V
/*     */     //   281: ldc_w 'Missing dispatcher 'id' property in config: '
/*     */     //   284: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   287: aload_1
/*     */     //   288: invokeinterface root : ()Lcom/typesafe/config/ConfigObject;
/*     */     //   293: invokeinterface render : ()Ljava/lang/String;
/*     */     //   298: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   301: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   304: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   307: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #174	-> 0
/*     */     //   #176	-> 11
/*     */     //   #177	-> 20
/*     */     //   #178	-> 60
/*     */     //   #180	-> 86
/*     */     //   #181	-> 90
/*     */     //   #180	-> 97
/*     */     //   #182	-> 102
/*     */     //   #181	-> 111
/*     */     //   #180	-> 114
/*     */     //   #183	-> 118
/*     */     //   #185	-> 162
/*     */     //   #186	-> 223
/*     */     //   #193	-> 259
/*     */     //   #184	-> 265
/*     */     //   #176	-> 267
/*     */     //   #174	-> 270
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	308	0	this	Lakka/dispatch/Dispatchers;
/*     */     //   0	308	1	cfg	Lcom/typesafe/config/Config;
/*     */     //   223	42	7	args	Lscala/collection/immutable/List;
/*     */   }
/*     */   
/*     */   public static String DefaultDispatcherId() {
/*     */     return Dispatchers$.MODULE$.DefaultDispatcherId();
/*     */   }
/*     */   
/*     */   public class Dispatchers$$anonfun$configuratorFrom$1 extends AbstractPartialFunction<Throwable, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Config cfg$1;
/*     */     
/*     */     private final String x1$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 186 */       Throwable throwable = x1;
/* 188 */       throw new ConfigurationException((
/*     */           
/* 190 */           new StringOps(Predef$.MODULE$.augmentString("Cannot instantiate MessageDispatcherConfigurator type [%s], defined in [%s], make sure it has constructor with [com.typesafe.config.Config] and [akka.dispatch.DispatcherPrerequisites] parameters")))
/*     */           
/* 192 */           .format(Predef$.MODULE$.genericWrapArray(new Object[] { this.x1$1, this.cfg$1.getString("id") }, )), throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       Throwable throwable = x1;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public Dispatchers$$anonfun$configuratorFrom$1(Dispatchers $outer, Config cfg$1, String x1$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Dispatchers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */