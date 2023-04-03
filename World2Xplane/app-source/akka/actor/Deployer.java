/*     */ package akka.actor;
/*     */ 
/*     */ import akka.routing.RouterConfig;
/*     */ import akka.util.WildcardTree;
/*     */ import akka.util.WildcardTree$;
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.JavaConverters$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.mutable.Iterable$;
/*     */ import scala.collection.mutable.Map$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%c!B\001\003\001\0211!\001\003#fa2|\0270\032:\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\n\003\001\035\001\"\001C\006\016\003%Q\021AC\001\006g\016\fG.Y\005\003\031%\021a!\0218z%\0264\007\002\003\b\001\005\013\007I\021\001\t\002\021M,G\017^5oON\034\001!F\001\022!\t\021bC\004\002\024)5\t!!\003\002\026\005\005Y\021i\031;peNK8\017^3n\023\t9\002D\001\005TKR$\030N\\4t\025\t)\"\001\003\005\033\001\t\005\t\025!\003\022\003%\031X\r\036;j]\036\034\b\005\003\005\035\001\t\025\r\021\"\001\036\0035!\027P\\1nS\016\f5mY3tgV\ta\004\005\002\024?%\021\001E\001\002\016\tft\027-\\5d\003\016\034Wm]:\t\021\t\002!\021!Q\001\ny\ta\002Z=oC6L7-Q2dKN\034\b\005C\003%\001\021\005Q%\001\004=S:LGO\020\013\004M\035B\003CA\n\001\021\025q1\0051\001\022\021\025a2\0051\001\037\021\035Q\003A1A\005\n-\naB]3tSj,'/\0228bE2,G-F\001-!\tiC'D\001/\025\ty\003'\001\004d_:4\027n\032\006\003cI\n\001\002^=qKN\fg-\032\006\002g\005\0311m\\7\n\005Ur#AB\"p]\032Lw\r\003\0048\001\001\006I\001L\001\020e\026\034\030N_3s\013:\f'\r\\3eA!9\021\b\001b\001\n\023Q\024a\0033fa2|\0270\\3oiN,\022a\017\t\004y\025;U\"A\037\013\005yz\024AB1u_6L7M\003\002A\003\006Q1m\0348dkJ\024XM\034;\013\005\t\033\025\001B;uS2T\021\001R\001\005U\0064\030-\003\002G{\ty\021\t^8nS\016\024VMZ3sK:\034W\rE\002I\0252k\021!\023\006\003\005\022I!aS%\003\031]KG\016Z2be\022$&/Z3\021\005Mi\025B\001(\003\005\031!U\r\0357ps\"1\001\013\001Q\001\nm\nA\002Z3qY>LX.\0328ug\002Bqa\f\001C\002\023%1\006\003\004T\001\001\006I\001L\001\bG>tg-[4!\021\035)\006A1A\005\022-\nq\001Z3gCVdG\017\003\004X\001\001\006I\001L\001\tI\0264\027-\0367uA!9\021\f\001b\001\n\003Q\026!\005:pkR,'\017V=qK6\013\007\017]5oOV\t1\f\005\003]?\n\024gB\001\005^\023\tq\026\"\001\004Qe\026$WMZ\005\003A\006\0241!T1q\025\tq\026\002\005\002]G&\021A-\031\002\007'R\024\030N\\4\t\r\031\004\001\025!\003\\\003I\021x.\036;feRK\b/Z'baBLgn\032\021\t\013!\004A\021A5\002\r1|wn[;q)\tQW\016E\002\tW2K!\001\\\005\003\r=\003H/[8o\021\025qw\r1\001p\003\021\001\030\r\0365\021\005M\001\030BA9\003\005%\t5\r^8s!\006$\b\016C\003i\001\021\0051\017\006\002ki\")aN\035a\001kB\031aO 2\017\005]dhB\001=|\033\005I(B\001>\020\003\031a$o\\8u}%\t!\"\003\002~\023\0059\001/Y2lC\036,\027bA@\002\002\tA\021\n^3sC\ndWM\003\002~\023!1\001\016\001C\001\003\013!2A[A\004\021\035q\0271\001a\001\003\023\001BA^A\006E&!\021QBA\001\005!IE/\032:bi>\024\bbBA\t\001\021\005\0211C\001\007I\026\004Hn\\=\025\t\005U\0211\004\t\004\021\005]\021bAA\r\023\t!QK\\5u\021\035\ti\"a\004A\0021\013\021\001\032\005\b\003C\001A\021AA\022\003-\001\030M]:f\007>tg-[4\025\013)\f)#!\013\t\017\005\035\022q\004a\001E\006\0311.Z=\t\r=\ny\0021\001-\021\035\ti\003\001C\t\003_\t!c\031:fCR,'k\\;uKJ\034uN\0344jORQ\021\021GA\037\003\003\n\031%!\022\021\t\005M\022\021H\007\003\003kQ1!a\016\005\003\035\021x.\036;j]\036LA!a\017\0026\ta!k\\;uKJ\034uN\0344jO\"9\021qHA\026\001\004\021\027A\003:pkR,'\017V=qK\"9\021qEA\026\001\004\021\007BB\030\002,\001\007A\006C\004\002H\005-\002\031\001\027\002\025\021,\007\017\\8z[\026tG\017")
/*     */ public class Deployer {
/*     */   private final ActorSystem.Settings settings;
/*     */   
/*     */   private final DynamicAccess dynamicAccess;
/*     */   
/*     */   private final Config resizerEnabled;
/*     */   
/*     */   private final AtomicReference<WildcardTree<Deploy>> deployments;
/*     */   
/*     */   private final Config config;
/*     */   
/*     */   private final Config default;
/*     */   
/*     */   private final Map<String, String> routerTypeMapping;
/*     */   
/*     */   public ActorSystem.Settings settings() {
/* 131 */     return this.settings;
/*     */   }
/*     */   
/*     */   public DynamicAccess dynamicAccess() {
/* 131 */     return this.dynamicAccess;
/*     */   }
/*     */   
/*     */   public Deployer(ActorSystem.Settings settings, DynamicAccess dynamicAccess) {
/* 135 */     this.resizerEnabled = ConfigFactory.parseString("resizer.enabled=on");
/* 136 */     this.deployments = new AtomicReference<WildcardTree<Deploy>>(WildcardTree$.MODULE$.apply());
/* 137 */     this.config = settings.config().getConfig("akka.actor.deployment");
/* 138 */     this.default = config().getConfig("default");
/* 139 */     this.routerTypeMapping = (
/* 140 */       (TraversableOnce)((TraversableLike)JavaConverters$.MODULE$.mapAsScalaMapConverter(settings.config().getConfig("akka.actor.router.type-mapping").root().unwrapped()).asScala()).collect((PartialFunction)new $anonfun$1(this), Map$.MODULE$.canBuildFrom()))
/*     */       
/* 142 */       .toMap(Predef$.MODULE$.conforms());
/* 144 */     ((IterableLike)((TraversableLike)JavaConverters$.MODULE$.mapAsScalaMapConverter((Map)config().root()).asScala()).flatMap((Function1)new Deployer$$anonfun$2(this), Iterable$.MODULE$.canBuildFrom()))
/*     */       
/* 148 */       .foreach((Function1)new Deployer$$anonfun$3(this));
/*     */   }
/*     */   
/*     */   private Config resizerEnabled() {
/*     */     return this.resizerEnabled;
/*     */   }
/*     */   
/*     */   private AtomicReference<WildcardTree<Deploy>> deployments() {
/*     */     return this.deployments;
/*     */   }
/*     */   
/*     */   private Config config() {
/*     */     return this.config;
/*     */   }
/*     */   
/*     */   public Config default() {
/*     */     return this.default;
/*     */   }
/*     */   
/*     */   public Map<String, String> routerTypeMapping() {
/*     */     return this.routerTypeMapping;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractPartialFunction<Tuple2<String, Object>, Tuple2<String, String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Tuple2<String, Object>, B1> B1 applyOrElse(Tuple2 x1, Function1 default) {
/*     */       Tuple2 tuple2 = x1;
/*     */       if (tuple2 != null) {
/*     */         String key = (String)tuple2._1();
/*     */         Object value = tuple2._2();
/*     */         if (value instanceof String) {
/*     */           String str = (String)value;
/*     */           return (B1)Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(key), str);
/*     */         } 
/*     */       } 
/*     */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Tuple2 x1) {
/*     */       Tuple2 tuple2 = x1;
/*     */       if (tuple2 != null) {
/*     */         Object value = tuple2._2();
/*     */         if (value instanceof String)
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public $anonfun$1(Deployer $outer) {}
/*     */   }
/*     */   
/*     */   public class Deployer$$anonfun$2 extends AbstractFunction1<Tuple2<String, ConfigValue>, Iterable<Deploy>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Iterable<Deploy> apply(Tuple2 x0$1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: aload_2
/*     */       //   3: ifnull -> 54
/*     */       //   6: aload_2
/*     */       //   7: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   10: checkcast java/lang/String
/*     */       //   13: astore_3
/*     */       //   14: ldc 'default'
/*     */       //   16: aload_3
/*     */       //   17: astore #4
/*     */       //   19: dup
/*     */       //   20: ifnonnull -> 32
/*     */       //   23: pop
/*     */       //   24: aload #4
/*     */       //   26: ifnull -> 40
/*     */       //   29: goto -> 54
/*     */       //   32: aload #4
/*     */       //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   37: ifeq -> 54
/*     */       //   40: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */       //   43: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   46: invokevirtual option2Iterable : (Lscala/Option;)Lscala/collection/Iterable;
/*     */       //   49: astore #5
/*     */       //   51: goto -> 129
/*     */       //   54: aload_2
/*     */       //   55: ifnull -> 118
/*     */       //   58: aload_2
/*     */       //   59: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   62: checkcast java/lang/String
/*     */       //   65: astore #6
/*     */       //   67: aload_2
/*     */       //   68: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   71: checkcast com/typesafe/config/ConfigValue
/*     */       //   74: astore #7
/*     */       //   76: aload #7
/*     */       //   78: instanceof com/typesafe/config/ConfigObject
/*     */       //   81: ifeq -> 118
/*     */       //   84: aload #7
/*     */       //   86: checkcast com/typesafe/config/ConfigObject
/*     */       //   89: astore #8
/*     */       //   91: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */       //   94: aload_0
/*     */       //   95: getfield $outer : Lakka/actor/Deployer;
/*     */       //   98: aload #6
/*     */       //   100: aload #8
/*     */       //   102: invokeinterface toConfig : ()Lcom/typesafe/config/Config;
/*     */       //   107: invokevirtual parseConfig : (Ljava/lang/String;Lcom/typesafe/config/Config;)Lscala/Option;
/*     */       //   110: invokevirtual option2Iterable : (Lscala/Option;)Lscala/collection/Iterable;
/*     */       //   113: astore #5
/*     */       //   115: goto -> 129
/*     */       //   118: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */       //   121: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   124: invokevirtual option2Iterable : (Lscala/Option;)Lscala/collection/Iterable;
/*     */       //   127: astore #5
/*     */       //   129: aload #5
/*     */       //   131: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #144	-> 0
/*     */       //   #145	-> 6
/*     */       //   #144	-> 54
/*     */       //   #146	-> 58
/*     */       //   #147	-> 118
/*     */       //   #144	-> 129
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	132	0	this	Lakka/actor/Deployer$$anonfun$2;
/*     */       //   0	132	1	x0$1	Lscala/Tuple2;
/*     */       //   67	65	6	key	Ljava/lang/String;
/*     */       //   76	56	7	value	Lcom/typesafe/config/ConfigValue;
/*     */     }
/*     */     
/*     */     public Deployer$$anonfun$2(Deployer $outer) {}
/*     */   }
/*     */   
/*     */   public class Deployer$$anonfun$3 extends AbstractFunction1<Deploy, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Deploy d) {
/* 148 */       this.$outer.deploy(d);
/*     */     }
/*     */     
/*     */     public Deployer$$anonfun$3(Deployer $outer) {}
/*     */   }
/*     */   
/*     */   public Option<Deploy> lookup(ActorPath path) {
/* 150 */     return lookup(((IterableLike)path.elements().drop(1)).iterator());
/*     */   }
/*     */   
/*     */   public Option<Deploy> lookup(Iterable path) {
/* 152 */     return lookup(path.iterator());
/*     */   }
/*     */   
/*     */   public Option<Deploy> lookup(Iterator path) {
/* 154 */     return ((WildcardTree)deployments().get()).find(path).data();
/*     */   }
/*     */   
/*     */   private final WildcardTree add$default$3$1() {
/* 157 */     return deployments().get();
/*     */   }
/*     */   
/*     */   private final void add$1(String[] path, Deploy d, WildcardTree<Deploy> w) {
/*     */     while (true) {
/* 159 */       RichInt$.MODULE$.until$extension0(Predef$.MODULE$.intWrapper(0), path.length).foreach$mVc$sp((Function1)new Deployer$$anonfun$add$1$1(this, path, d));
/* 168 */       if (deployments().compareAndSet(w, w.insert(Predef$.MODULE$.refArrayOps((Object[])path).iterator(), d)))
/*     */         return; 
/* 168 */       w = add$default$3$1();
/* 168 */       d = d;
/* 168 */       path = path;
/*     */     } 
/*     */   }
/*     */   
/*     */   public class Deployer$$anonfun$add$1$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String[] path$1;
/*     */     
/*     */     private final Deploy d$1;
/*     */     
/*     */     public final void apply(int i) {
/*     */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/*     */       String str1 = this.path$1[i];
/*     */       String str2 = str1;
/*     */       if ("" == null) {
/*     */         "";
/*     */         if (str2 != null)
/*     */           Option option = ActorPath$.MODULE$.ElementRegex().unapplySeq(str1); 
/*     */       } else {
/*     */         if ("".equals(str2)) {
/*     */           (new String[2])[0] = "actor name in deployment [";
/*     */           (new String[2])[1] = "] must not be empty";
/*     */           throw new InvalidActorNameException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { this.d$1.path() })));
/*     */         } 
/*     */         Option option = ActorPath$.MODULE$.ElementRegex().unapplySeq(str1);
/*     */       } 
/*     */       (new String[2])[0] = "actor name in deployment [";
/*     */       (new String[2])[1] = "] must not be empty";
/*     */       throw new InvalidActorNameException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { this.d$1.path() })));
/*     */     }
/*     */     
/*     */     public Deployer$$anonfun$add$1$1(Deployer $outer, String[] path$1, Deploy d$1) {}
/*     */   }
/*     */   
/*     */   public void deploy(Deploy d) {
/* 171 */     add$1((String[])Predef$.MODULE$.refArrayOps((Object[])d.path().split("/")).drop(1), d, add$default$3$1());
/*     */   }
/*     */   
/*     */   public Option<Deploy> parseConfig(String key, Config config) {
/* 175 */     Config deployment = config.withFallback((ConfigMergeable)default());
/* 176 */     RouterConfig router = createRouterConfig(deployment.getString("router"), key, config, deployment);
/* 177 */     String dispatcher = deployment.getString("dispatcher");
/* 178 */     String mailbox = deployment.getString("mailbox");
/* 179 */     return (Option<Deploy>)new Some(new Deploy(key, deployment, router, NoScopeGiven$.MODULE$, dispatcher, mailbox));
/*     */   }
/*     */   
/*     */   public RouterConfig createRouterConfig(String routerType, String key, Config config, Config deployment) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'from-code'
/*     */     //   3: astore #5
/*     */     //   5: dup
/*     */     //   6: ifnonnull -> 18
/*     */     //   9: pop
/*     */     //   10: aload #5
/*     */     //   12: ifnull -> 26
/*     */     //   15: goto -> 32
/*     */     //   18: aload #5
/*     */     //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   23: ifeq -> 32
/*     */     //   26: getstatic akka/routing/NoRouter$.MODULE$ : Lakka/routing/NoRouter$;
/*     */     //   29: goto -> 241
/*     */     //   32: aload_3
/*     */     //   33: ldc 'resizer'
/*     */     //   35: invokeinterface hasPath : (Ljava/lang/String;)Z
/*     */     //   40: ifeq -> 69
/*     */     //   43: aload #4
/*     */     //   45: ldc 'resizer.enabled'
/*     */     //   47: invokeinterface getBoolean : (Ljava/lang/String;)Z
/*     */     //   52: ifne -> 69
/*     */     //   55: aload_0
/*     */     //   56: invokespecial resizerEnabled : ()Lcom/typesafe/config/Config;
/*     */     //   59: aload #4
/*     */     //   61: invokeinterface withFallback : (Lcom/typesafe/config/ConfigMergeable;)Lcom/typesafe/config/Config;
/*     */     //   66: goto -> 71
/*     */     //   69: aload #4
/*     */     //   71: astore #6
/*     */     //   73: aload_0
/*     */     //   74: invokevirtual routerTypeMapping : ()Lscala/collection/immutable/Map;
/*     */     //   77: aload_1
/*     */     //   78: new akka/actor/Deployer$$anonfun$4
/*     */     //   81: dup
/*     */     //   82: aload_0
/*     */     //   83: aload_1
/*     */     //   84: aload #4
/*     */     //   86: invokespecial <init> : (Lakka/actor/Deployer;Ljava/lang/String;Lcom/typesafe/config/Config;)V
/*     */     //   89: invokeinterface getOrElse : (Ljava/lang/Object;Lscala/Function0;)Ljava/lang/Object;
/*     */     //   94: checkcast java/lang/String
/*     */     //   97: astore #7
/*     */     //   99: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   102: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   105: iconst_1
/*     */     //   106: anewarray scala/Tuple2
/*     */     //   109: dup
/*     */     //   110: iconst_0
/*     */     //   111: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   114: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   117: ldc com/typesafe/config/Config
/*     */     //   119: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   122: aload #6
/*     */     //   124: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   127: aastore
/*     */     //   128: checkcast [Ljava/lang/Object;
/*     */     //   131: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   134: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/immutable/List;
/*     */     //   137: astore #8
/*     */     //   139: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   142: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   145: iconst_2
/*     */     //   146: anewarray scala/Tuple2
/*     */     //   149: dup
/*     */     //   150: iconst_0
/*     */     //   151: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   154: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   157: ldc com/typesafe/config/Config
/*     */     //   159: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   162: aload #6
/*     */     //   164: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   167: aastore
/*     */     //   168: dup
/*     */     //   169: iconst_1
/*     */     //   170: getstatic scala/Predef$ArrowAssoc$.MODULE$ : Lscala/Predef$ArrowAssoc$;
/*     */     //   173: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   176: ldc akka/actor/DynamicAccess
/*     */     //   178: invokevirtual any2ArrowAssoc : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   181: aload_0
/*     */     //   182: invokevirtual dynamicAccess : ()Lakka/actor/DynamicAccess;
/*     */     //   185: invokevirtual $minus$greater$extension : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/Tuple2;
/*     */     //   188: aastore
/*     */     //   189: checkcast [Ljava/lang/Object;
/*     */     //   192: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   195: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/immutable/List;
/*     */     //   198: astore #9
/*     */     //   200: aload_0
/*     */     //   201: invokevirtual dynamicAccess : ()Lakka/actor/DynamicAccess;
/*     */     //   204: aload #7
/*     */     //   206: aload #8
/*     */     //   208: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   211: ldc akka/routing/RouterConfig
/*     */     //   213: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   216: invokevirtual createInstanceFor : (Ljava/lang/String;Lscala/collection/immutable/Seq;Lscala/reflect/ClassTag;)Lscala/util/Try;
/*     */     //   219: new akka/actor/Deployer$$anonfun$createRouterConfig$1
/*     */     //   222: dup
/*     */     //   223: aload_0
/*     */     //   224: aload_2
/*     */     //   225: aload #7
/*     */     //   227: aload #9
/*     */     //   229: invokespecial <init> : (Lakka/actor/Deployer;Ljava/lang/String;Ljava/lang/String;Lscala/collection/immutable/List;)V
/*     */     //   232: invokevirtual recover : (Lscala/PartialFunction;)Lscala/util/Try;
/*     */     //   235: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   238: checkcast akka/routing/RouterConfig
/*     */     //   241: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #190	-> 0
/*     */     //   #194	-> 32
/*     */     //   #195	-> 55
/*     */     //   #196	-> 69
/*     */     //   #193	-> 71
/*     */     //   #198	-> 73
/*     */     //   #199	-> 78
/*     */     //   #198	-> 89
/*     */     //   #211	-> 99
/*     */     //   #212	-> 139
/*     */     //   #213	-> 200
/*     */     //   #221	-> 235
/*     */     //   #190	-> 241
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	242	0	this	Lakka/actor/Deployer;
/*     */     //   0	242	1	routerType	Ljava/lang/String;
/*     */     //   0	242	2	key	Ljava/lang/String;
/*     */     //   0	242	3	config	Lcom/typesafe/config/Config;
/*     */     //   0	242	4	deployment	Lcom/typesafe/config/Config;
/*     */     //   73	168	6	deployment2	Lcom/typesafe/config/Config;
/*     */     //   99	142	7	fqn	Ljava/lang/String;
/*     */     //   139	102	8	args1	Lscala/collection/immutable/List;
/*     */     //   200	41	9	args2	Lscala/collection/immutable/List;
/*     */   }
/*     */   
/*     */   public class Deployer$$anonfun$4 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String routerType$1;
/*     */     
/*     */     private final Config deployment$1;
/*     */     
/*     */     public final String apply() {
/* 199 */       return this.deployment$1.getStringList("routees.paths").isEmpty() ? 
/* 200 */         (String)this.$outer.routerTypeMapping().getOrElse((new StringBuilder()).append(this.routerType$1).append("-pool").toString(), (Function0)new Deployer$$anonfun$4$$anonfun$apply$1(this)) : 
/*     */         
/* 202 */         (String)this.$outer.routerTypeMapping().getOrElse((new StringBuilder()).append(this.routerType$1).append("-group").toString(), (Function0)new Deployer$$anonfun$4$$anonfun$apply$2(this));
/*     */     }
/*     */     
/*     */     public Deployer$$anonfun$4(Deployer $outer, String routerType$1, Config deployment$1) {}
/*     */     
/*     */     public class Deployer$$anonfun$4$$anonfun$apply$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return this.$outer.routerType$1;
/*     */       }
/*     */       
/*     */       public Deployer$$anonfun$4$$anonfun$apply$1(Deployer$$anonfun$4 $outer) {}
/*     */     }
/*     */     
/*     */     public class Deployer$$anonfun$4$$anonfun$apply$2 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/* 202 */         return this.$outer.routerType$1;
/*     */       }
/*     */       
/*     */       public Deployer$$anonfun$4$$anonfun$apply$2(Deployer$$anonfun$4 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public final Nothing$ akka$actor$Deployer$$throwCannotInstantiateRouter$1(Seq args, Throwable cause, String key$1, String fqn$1) {
/* 206 */     (new String[3])[0] = "Cannot instantiate router [";
/* 206 */     (new String[3])[1] = "], defined in [";
/* 206 */     (new String[3])[2] = "], ";
/* 207 */     (new String[2])[0] = "make sure it extends [";
/* 207 */     (new String[2])[1] = "] and has constructor with ";
/* 208 */     (new String[3])[0] = "[";
/* 208 */     (new String[3])[1] = "] and optional [";
/* 208 */     (new String[3])[2] = "] parameter";
/* 208 */     throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { fqn$1, key$1 }, ))).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { RouterConfig.class }, ))).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { ((Class)((Tuple2)args.apply(0))._1()).getName(), ((Class)((Tuple2)args.apply(1))._1()).getName() }, ))).toString(), cause);
/*     */   }
/*     */   
/*     */   public class Deployer$$anonfun$createRouterConfig$1 extends AbstractPartialFunction<Throwable, RouterConfig> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String key$1;
/*     */     
/*     */     public final String fqn$1;
/*     */     
/*     */     public final List args2$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       boolean bool;
/* 213 */       Throwable throwable = x2;
/* 214 */       if (throwable instanceof IllegalArgumentException) {
/* 214 */         bool = true;
/* 214 */       } else if (throwable instanceof com.typesafe.config.ConfigException) {
/* 214 */         bool = true;
/*     */       } else {
/* 214 */         bool = false;
/*     */       } 
/* 214 */       if (bool)
/* 214 */         throw throwable; 
/* 215 */       if (throwable instanceof NoSuchMethodException) {
/* 215 */         NoSuchMethodException noSuchMethodException = (NoSuchMethodException)throwable;
/* 216 */         return (B1)this.$outer.dynamicAccess().<T>createInstanceFor(this.fqn$1, (Seq<Tuple2<Class<?>, Object>>)this.args2$1, ClassTag$.MODULE$.apply(RouterConfig.class)).recover((PartialFunction)new Deployer$$anonfun$createRouterConfig$1$$anonfun$applyOrElse$1(this, noSuchMethodException))
/*     */           
/* 219 */           .get();
/*     */       } 
/* 220 */       throw this.$outer.akka$actor$Deployer$$throwCannotInstantiateRouter$1(this.args2$1, throwable, this.key$1, this.fqn$1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       boolean bool1, bool2;
/*     */       Throwable throwable = x2;
/*     */       if (throwable instanceof IllegalArgumentException) {
/*     */         bool1 = true;
/*     */       } else if (throwable instanceof com.typesafe.config.ConfigException) {
/*     */         bool1 = true;
/*     */       } else {
/*     */         bool1 = false;
/*     */       } 
/*     */       if (bool1) {
/*     */         bool2 = true;
/*     */       } else if (throwable instanceof NoSuchMethodException) {
/*     */         bool2 = true;
/*     */       } else {
/* 220 */         bool2 = true;
/*     */       } 
/*     */       return bool2;
/*     */     }
/*     */     
/*     */     public Deployer$$anonfun$createRouterConfig$1(Deployer $outer, String key$1, String fqn$1, List args2$1) {}
/*     */     
/*     */     public class Deployer$$anonfun$createRouterConfig$1$$anonfun$applyOrElse$1 extends AbstractPartialFunction<Throwable, Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final NoSuchMethodException x4$1;
/*     */       
/*     */       public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x3, Function1 default) {
/*     */         boolean bool;
/*     */         Throwable throwable = x3;
/*     */         if (throwable instanceof IllegalArgumentException) {
/*     */           bool = true;
/*     */         } else if (throwable instanceof com.typesafe.config.ConfigException) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */         if (bool)
/*     */           throw throwable; 
/*     */         throw this.$outer.akka$actor$Deployer$$anonfun$$$outer().akka$actor$Deployer$$throwCannotInstantiateRouter$1(this.$outer.args2$1, this.x4$1, this.$outer.key$1, this.$outer.fqn$1);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Throwable x3) {
/*     */         boolean bool1, bool2;
/*     */         Throwable throwable = x3;
/*     */         if (throwable instanceof IllegalArgumentException) {
/*     */           bool1 = true;
/*     */         } else if (throwable instanceof com.typesafe.config.ConfigException) {
/*     */           bool1 = true;
/*     */         } else {
/*     */           bool1 = false;
/*     */         } 
/*     */         if (bool1) {
/*     */           bool2 = true;
/*     */         } else {
/*     */           bool2 = true;
/*     */         } 
/*     */         return bool2;
/*     */       }
/*     */       
/*     */       public Deployer$$anonfun$createRouterConfig$1$$anonfun$applyOrElse$1(Deployer$$anonfun$createRouterConfig$1 $outer, NoSuchMethodException x4$1) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Deployer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */