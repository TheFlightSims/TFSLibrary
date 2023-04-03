/*     */ package akka.actor;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ActorSystem$ {
/*     */   public static final ActorSystem$ MODULE$;
/*     */   
/*     */   private final String Version;
/*     */   
/*     */   private final Option<String> EnvHome;
/*     */   
/*     */   private final Option<String> SystemHome;
/*     */   
/*     */   private final Option<String> GlobalHome;
/*     */   
/*     */   private ActorSystem$() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial <init> : ()V
/*     */     //   4: aload_0
/*     */     //   5: putstatic akka/actor/ActorSystem$.MODULE$ : Lakka/actor/ActorSystem$;
/*     */     //   8: aload_0
/*     */     //   9: ldc '2.3.3'
/*     */     //   11: putfield Version : Ljava/lang/String;
/*     */     //   14: aload_0
/*     */     //   15: ldc 'AKKA_HOME'
/*     */     //   17: invokestatic getenv : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   20: astore_1
/*     */     //   21: aload_1
/*     */     //   22: ifnonnull -> 30
/*     */     //   25: iconst_1
/*     */     //   26: istore_2
/*     */     //   27: goto -> 91
/*     */     //   30: ldc ''
/*     */     //   32: aload_1
/*     */     //   33: astore_3
/*     */     //   34: dup
/*     */     //   35: ifnonnull -> 46
/*     */     //   38: pop
/*     */     //   39: aload_3
/*     */     //   40: ifnull -> 53
/*     */     //   43: goto -> 58
/*     */     //   46: aload_3
/*     */     //   47: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   50: ifeq -> 58
/*     */     //   53: iconst_1
/*     */     //   54: istore_2
/*     */     //   55: goto -> 91
/*     */     //   58: ldc '.'
/*     */     //   60: aload_1
/*     */     //   61: astore #4
/*     */     //   63: dup
/*     */     //   64: ifnonnull -> 76
/*     */     //   67: pop
/*     */     //   68: aload #4
/*     */     //   70: ifnull -> 84
/*     */     //   73: goto -> 89
/*     */     //   76: aload #4
/*     */     //   78: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   81: ifeq -> 89
/*     */     //   84: iconst_1
/*     */     //   85: istore_2
/*     */     //   86: goto -> 91
/*     */     //   89: iconst_0
/*     */     //   90: istore_2
/*     */     //   91: iload_2
/*     */     //   92: ifeq -> 103
/*     */     //   95: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   98: astore #5
/*     */     //   100: goto -> 113
/*     */     //   103: new scala/Some
/*     */     //   106: dup
/*     */     //   107: aload_1
/*     */     //   108: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   111: astore #5
/*     */     //   113: aload #5
/*     */     //   115: putfield EnvHome : Lscala/Option;
/*     */     //   118: aload_0
/*     */     //   119: ldc 'akka.home'
/*     */     //   121: invokestatic getProperty : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   124: astore #6
/*     */     //   126: aload #6
/*     */     //   128: ifnonnull -> 137
/*     */     //   131: iconst_1
/*     */     //   132: istore #7
/*     */     //   134: goto -> 173
/*     */     //   137: ldc ''
/*     */     //   139: aload #6
/*     */     //   141: astore #8
/*     */     //   143: dup
/*     */     //   144: ifnonnull -> 156
/*     */     //   147: pop
/*     */     //   148: aload #8
/*     */     //   150: ifnull -> 164
/*     */     //   153: goto -> 170
/*     */     //   156: aload #8
/*     */     //   158: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   161: ifeq -> 170
/*     */     //   164: iconst_1
/*     */     //   165: istore #7
/*     */     //   167: goto -> 173
/*     */     //   170: iconst_0
/*     */     //   171: istore #7
/*     */     //   173: iload #7
/*     */     //   175: ifeq -> 186
/*     */     //   178: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   181: astore #9
/*     */     //   183: goto -> 197
/*     */     //   186: new scala/Some
/*     */     //   189: dup
/*     */     //   190: aload #6
/*     */     //   192: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   195: astore #9
/*     */     //   197: aload #9
/*     */     //   199: putfield SystemHome : Lscala/Option;
/*     */     //   202: aload_0
/*     */     //   203: aload_0
/*     */     //   204: invokevirtual SystemHome : ()Lscala/Option;
/*     */     //   207: new akka/actor/ActorSystem$$anonfun$3
/*     */     //   210: dup
/*     */     //   211: invokespecial <init> : ()V
/*     */     //   214: invokevirtual orElse : (Lscala/Function0;)Lscala/Option;
/*     */     //   217: putfield GlobalHome : Lscala/Option;
/*     */     //   220: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #24	-> 0
/*     */     //   #26	-> 8
/*     */     //   #28	-> 14
/*     */     //   #29	-> 21
/*     */     //   #30	-> 103
/*     */     //   #28	-> 113
/*     */     //   #33	-> 118
/*     */     //   #34	-> 126
/*     */     //   #35	-> 186
/*     */     //   #33	-> 197
/*     */     //   #38	-> 202
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	221	0	this	Lakka/actor/ActorSystem$;
/*     */   }
/*     */   
/*     */   public String Version() {
/*  26 */     return this.Version;
/*     */   }
/*     */   
/*     */   public Option<String> EnvHome() {
/*  28 */     return this.EnvHome;
/*     */   }
/*     */   
/*     */   public Option<String> SystemHome() {
/*  33 */     return this.SystemHome;
/*     */   }
/*     */   
/*     */   public Option<String> GlobalHome() {
/*  38 */     return this.GlobalHome;
/*     */   }
/*     */   
/*     */   public static class $anonfun$3 extends AbstractFunction0<Option<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Option<String> apply() {
/*  38 */       return ActorSystem$.MODULE$.EnvHome();
/*     */     }
/*     */   }
/*     */   
/*     */   public ActorSystem create() {
/*  47 */     return apply();
/*     */   }
/*     */   
/*     */   public ActorSystem create(String name) {
/*  56 */     return apply(name);
/*     */   }
/*     */   
/*     */   public ActorSystem create(String name, Config config) {
/*  66 */     return apply(name, config);
/*     */   }
/*     */   
/*     */   public ActorSystem create(String name, Config config, ClassLoader classLoader) {
/*  73 */     return apply(name, config, classLoader);
/*     */   }
/*     */   
/*     */   public ActorSystem create(String name, Config config, ClassLoader classLoader, ExecutionContext defaultExecutionContext) {
/*  90 */     return apply(name, scala.Option$.MODULE$.apply(config), scala.Option$.MODULE$.apply(classLoader), scala.Option$.MODULE$.apply(defaultExecutionContext));
/*     */   }
/*     */   
/*     */   public ActorSystem apply() {
/*  99 */     return apply("default");
/*     */   }
/*     */   
/*     */   public ActorSystem apply(String name) {
/* 108 */     return apply(name, (Option<Config>)scala.None$.MODULE$, (Option<ClassLoader>)scala.None$.MODULE$, (Option<ExecutionContext>)scala.None$.MODULE$);
/*     */   }
/*     */   
/*     */   public ActorSystem apply(String name, Config config) {
/* 118 */     return apply(name, scala.Option$.MODULE$.apply(config), (Option<ClassLoader>)scala.None$.MODULE$, (Option<ExecutionContext>)scala.None$.MODULE$);
/*     */   }
/*     */   
/*     */   public ActorSystem apply(String name, Config config, ClassLoader classLoader) {
/* 125 */     return apply(name, scala.Option$.MODULE$.apply(config), scala.Option$.MODULE$.apply(classLoader), (Option<ExecutionContext>)scala.None$.MODULE$);
/*     */   }
/*     */   
/*     */   public Option<Config> apply$default$2() {
/* 138 */     return (Option<Config>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<ClassLoader> apply$default$3() {
/* 138 */     return (Option<ClassLoader>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<ExecutionContext> apply$default$4() {
/* 138 */     return (Option<ExecutionContext>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public ActorSystem apply(String name, Option config, Option classLoader, Option<ExecutionContext> defaultExecutionContext) {
/* 139 */     ClassLoader cl = (ClassLoader)classLoader.getOrElse((Function0)new ActorSystem$$anonfun$4());
/* 140 */     Config appConfig = (Config)config.getOrElse((Function0)new ActorSystem$$anonfun$5(cl));
/* 141 */     return (new ActorSystemImpl(name, appConfig, cl, defaultExecutionContext)).start();
/*     */   }
/*     */   
/*     */   public static class ActorSystem$$anonfun$4 extends AbstractFunction0<ClassLoader> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ClassLoader apply() {
/*     */       return ActorSystem$.MODULE$.findClassLoader();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSystem$$anonfun$5 extends AbstractFunction0<Config> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ClassLoader cl$1;
/*     */     
/*     */     public final Config apply() {
/*     */       return ConfigFactory.load(this.cl$1);
/*     */     }
/*     */     
/*     */     public ActorSystem$$anonfun$5(ClassLoader cl$1) {}
/*     */   }
/*     */   
/*     */   public final ClassLoader akka$actor$ActorSystem$$findCaller$1(Function1 get) {
/*     */     ClassLoader classLoader;
/* 229 */     Class clazz = (Class)scala.package$.MODULE$.Iterator().from(2).map(get).dropWhile((Function1)new ActorSystem.$anonfun$6()).next();
/* 230 */     if (clazz == null) {
/* 230 */       classLoader = getClass().getClassLoader();
/*     */     } else {
/* 231 */       classLoader = clazz.getClassLoader();
/*     */     } 
/*     */     return classLoader;
/*     */   }
/*     */   
/*     */   public static class $anonfun$6 extends AbstractFunction1<Class<?>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Class c) {
/*     */       return (c != null && (c.getName().startsWith("akka.actor.ActorSystem") || c.getName().startsWith("scala.Option") || c.getName().startsWith("scala.collection.Iterator") || c.getName().startsWith("akka.util.Reflect")));
/*     */     }
/*     */   }
/*     */   
/*     */   public ClassLoader findClassLoader() {
/* 234 */     return (ClassLoader)scala.Option$.MODULE$.apply(Thread.currentThread().getContextClassLoader()).orElse(
/* 235 */         (Function0)new ActorSystem$$anonfun$findClassLoader$1()).getOrElse(
/* 236 */         (Function0)new ActorSystem$$anonfun$findClassLoader$2());
/*     */   }
/*     */   
/*     */   public static class ActorSystem$$anonfun$findClassLoader$1 extends AbstractFunction0<Option<ClassLoader>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Option<ClassLoader> apply() {
/*     */       return akka.util.Reflect$.MODULE$.getCallerClass().map((Function1)new ActorSystem$$anonfun$findClassLoader$1$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public class ActorSystem$$anonfun$findClassLoader$1$$anonfun$apply$1 extends AbstractFunction1<Function1<Object, Class<?>>, ClassLoader> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ClassLoader apply(Function1 get) {
/*     */         return ActorSystem$.MODULE$.akka$actor$ActorSystem$$findCaller$1(get);
/*     */       }
/*     */       
/*     */       public ActorSystem$$anonfun$findClassLoader$1$$anonfun$apply$1(ActorSystem$$anonfun$findClassLoader$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSystem$$anonfun$findClassLoader$2 extends AbstractFunction0<ClassLoader> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ClassLoader apply() {
/* 236 */       return ActorSystem$.MODULE$.getClass().getClassLoader();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorSystem$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */