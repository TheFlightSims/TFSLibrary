/*     */ package akka.dispatch;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001e2Q!\001\002\002\002\035\021Q$T3tg\006<W\rR5ta\006$8\r[3s\007>tg-[4ve\006$xN\035\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\031\"\001\001\005\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g\021!y\001A!A!\002\023\001\022aB0d_:4\027n\032\t\003#ai\021A\005\006\003'Q\taaY8oM&<'BA\013\027\003!!\030\020]3tC\032,'\"A\f\002\007\r|W.\003\002\032%\t11i\0348gS\036D\001b\007\001\003\006\004%\t\001H\001\016aJ,'/Z9vSNLG/Z:\026\003u\001\"AH\020\016\003\tI!\001\t\002\003/\021K7\017]1uG\",'\017\025:fe\026\fX/[:ji\026\034\b\002\003\022\001\005\003\005\013\021B\017\002\035A\024XM]3rk&\034\030\016^3tA!)A\005\001C\001K\0051A(\0338jiz\"2AJ\024)!\tq\002\001C\003\020G\001\007\001\003C\003\034G\001\007Q\004C\004\024\001\t\007I\021\001\026\026\003AAa\001\f\001!\002\023\001\022aB2p]\032Lw\r\t\005\006]\0011\taL\001\013I&\034\b/\031;dQ\026\024H#\001\031\021\005y\t\024B\001\032\003\005EiUm]:bO\026$\025n\0359bi\016DWM\035\005\006i\001!\t!N\001\022G>tg-[4ve\026,\0050Z2vi>\024H#\001\034\021\005y9\024B\001\035\003\005m)\0050Z2vi>\0248+\032:wS\016,7i\0348gS\036,(/\031;pe\002")
/*     */ public abstract class MessageDispatcherConfigurator {
/*     */   private final DispatcherPrerequisites prerequisites;
/*     */   
/*     */   private final Config config;
/*     */   
/*     */   public DispatcherPrerequisites prerequisites() {
/* 310 */     return this.prerequisites;
/*     */   }
/*     */   
/*     */   public MessageDispatcherConfigurator(Config _config, DispatcherPrerequisites prerequisites) {
/* 312 */     this.config = new CachingConfig(_config);
/*     */   }
/*     */   
/*     */   public Config config() {
/* 312 */     return this.config;
/*     */   }
/*     */   
/*     */   private final ExecutorServiceConfigurator configurator$1(String executor) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: ifnonnull -> 11
/*     */     //   6: iconst_1
/*     */     //   7: istore_3
/*     */     //   8: goto -> 75
/*     */     //   11: ldc ''
/*     */     //   13: aload_2
/*     */     //   14: astore #4
/*     */     //   16: dup
/*     */     //   17: ifnonnull -> 29
/*     */     //   20: pop
/*     */     //   21: aload #4
/*     */     //   23: ifnull -> 37
/*     */     //   26: goto -> 42
/*     */     //   29: aload #4
/*     */     //   31: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   34: ifeq -> 42
/*     */     //   37: iconst_1
/*     */     //   38: istore_3
/*     */     //   39: goto -> 75
/*     */     //   42: ldc 'fork-join-executor'
/*     */     //   44: aload_2
/*     */     //   45: astore #5
/*     */     //   47: dup
/*     */     //   48: ifnonnull -> 60
/*     */     //   51: pop
/*     */     //   52: aload #5
/*     */     //   54: ifnull -> 68
/*     */     //   57: goto -> 73
/*     */     //   60: aload #5
/*     */     //   62: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   65: ifeq -> 73
/*     */     //   68: iconst_1
/*     */     //   69: istore_3
/*     */     //   70: goto -> 75
/*     */     //   73: iconst_0
/*     */     //   74: istore_3
/*     */     //   75: iload_3
/*     */     //   76: ifeq -> 106
/*     */     //   79: new akka/dispatch/ForkJoinExecutorConfigurator
/*     */     //   82: dup
/*     */     //   83: aload_0
/*     */     //   84: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   87: ldc 'fork-join-executor'
/*     */     //   89: invokeinterface getConfig : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   94: aload_0
/*     */     //   95: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   98: invokespecial <init> : (Lcom/typesafe/config/Config;Lakka/dispatch/DispatcherPrerequisites;)V
/*     */     //   101: astore #6
/*     */     //   103: goto -> 265
/*     */     //   106: ldc 'thread-pool-executor'
/*     */     //   108: aload_2
/*     */     //   109: astore #7
/*     */     //   111: dup
/*     */     //   112: ifnonnull -> 124
/*     */     //   115: pop
/*     */     //   116: aload #7
/*     */     //   118: ifnull -> 132
/*     */     //   121: goto -> 159
/*     */     //   124: aload #7
/*     */     //   126: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   129: ifeq -> 159
/*     */     //   132: new akka/dispatch/ThreadPoolExecutorConfigurator
/*     */     //   135: dup
/*     */     //   136: aload_0
/*     */     //   137: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   140: ldc 'thread-pool-executor'
/*     */     //   142: invokeinterface getConfig : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   147: aload_0
/*     */     //   148: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   151: invokespecial <init> : (Lcom/typesafe/config/Config;Lakka/dispatch/DispatcherPrerequisites;)V
/*     */     //   154: astore #6
/*     */     //   156: goto -> 265
/*     */     //   159: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   162: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   165: iconst_2
/*     */     //   166: anewarray scala/Tuple2
/*     */     //   169: dup
/*     */     //   170: iconst_0
/*     */     //   171: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   174: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   177: ldc com/typesafe/config/Config
/*     */     //   179: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   182: aload_0
/*     */     //   183: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   186: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   189: aastore
/*     */     //   190: dup
/*     */     //   191: iconst_1
/*     */     //   192: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   195: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   198: ldc akka/dispatch/DispatcherPrerequisites
/*     */     //   200: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   203: aload_0
/*     */     //   204: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   207: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   210: aastore
/*     */     //   211: checkcast [Ljava/lang/Object;
/*     */     //   214: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   217: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/immutable/List;
/*     */     //   220: astore #8
/*     */     //   222: aload_0
/*     */     //   223: invokevirtual prerequisites : ()Lakka/dispatch/DispatcherPrerequisites;
/*     */     //   226: invokeinterface dynamicAccess : ()Lakka/actor/DynamicAccess;
/*     */     //   231: aload_2
/*     */     //   232: aload #8
/*     */     //   234: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   237: ldc akka/dispatch/ExecutorServiceConfigurator
/*     */     //   239: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   242: invokevirtual createInstanceFor : (Ljava/lang/String;Lscala/collection/immutable/Seq;Lscala/reflect/ClassTag;)Lscala/util/Try;
/*     */     //   245: new akka/dispatch/MessageDispatcherConfigurator$$anonfun$configurator$1$1
/*     */     //   248: dup
/*     */     //   249: aload_0
/*     */     //   250: aload_2
/*     */     //   251: invokespecial <init> : (Lakka/dispatch/MessageDispatcherConfigurator;Ljava/lang/String;)V
/*     */     //   254: invokevirtual recover : (Lscala/PartialFunction;)Lscala/util/Try;
/*     */     //   257: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   260: checkcast akka/dispatch/ExecutorServiceConfigurator
/*     */     //   263: astore #6
/*     */     //   265: aload #6
/*     */     //   267: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #322	-> 0
/*     */     //   #323	-> 2
/*     */     //   #324	-> 106
/*     */     //   #326	-> 159
/*     */     //   #327	-> 174
/*     */     //   #326	-> 190
/*     */     //   #328	-> 195
/*     */     //   #326	-> 214
/*     */     //   #329	-> 222
/*     */     //   #334	-> 257
/*     */     //   #325	-> 263
/*     */     //   #322	-> 265
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	268	0	this	Lakka/dispatch/MessageDispatcherConfigurator;
/*     */     //   0	268	1	executor	Ljava/lang/String;
/*     */     //   222	41	8	args	Lscala/collection/immutable/List;
/*     */   }
/*     */   
/*     */   public class MessageDispatcherConfigurator$$anonfun$configurator$1$1 extends AbstractPartialFunction<Throwable, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String x1$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 329 */       Throwable throwable = x1;
/* 330 */       throw new IllegalArgumentException((
/* 331 */           new StringOps(Predef$.MODULE$.augmentString("Cannot instantiate ExecutorServiceConfigurator (\"executor = [%s]\"), defined in [%s],\n                make sure it has an accessible constructor with a [%s,%s] signature")))
/*     */           
/* 333 */           .format(Predef$.MODULE$.genericWrapArray(new Object[] { this.x1$1, this.$outer.config().getString("id"), Config.class, DispatcherPrerequisites.class }, )), throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       Throwable throwable = x1;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public MessageDispatcherConfigurator$$anonfun$configurator$1$1(MessageDispatcherConfigurator $outer, String x1$1) {}
/*     */   }
/*     */   
/*     */   public ExecutorServiceConfigurator configureExecutor() {
/* 337 */     String str1 = config().getString("executor");
/* 338 */     String str2 = str1;
/* 338 */     if ("default-executor" == null) {
/* 338 */       "default-executor";
/* 338 */       if (str2 != null)
/* 339 */         ExecutorServiceConfigurator executorServiceConfigurator = configurator$1(str1); 
/*     */     } else {
/*     */       if ("default-executor".equals(str2))
/*     */         return new DefaultExecutorServiceConfigurator(config().getConfig("default-executor"), prerequisites(), configurator$1(config().getString("default-executor.fallback"))); 
/* 339 */       ExecutorServiceConfigurator executorServiceConfigurator = configurator$1(str1);
/*     */     } 
/*     */     return new DefaultExecutorServiceConfigurator(config().getConfig("default-executor"), prerequisites(), configurator$1(config().getString("default-executor.fallback")));
/*     */   }
/*     */   
/*     */   public abstract MessageDispatcher dispatcher();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MessageDispatcherConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */