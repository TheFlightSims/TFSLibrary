/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorPath$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ChildRestartStats;
/*     */ import akka.actor.ChildStats;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.InvalidActorNameException;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.RepointableRef;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Unsafe;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.JavaConverters$;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.TreeMap;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class Children$class {
/*     */   public static void $init$(ActorCell $this) {
/*  20 */     $this.akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly_$eq(ChildrenContainer.EmptyChildrenContainer$.MODULE$);
/*  44 */     $this.akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly_$eq(0L);
/*     */   }
/*     */   
/*     */   public static ChildrenContainer childrenRefs(ActorCell $this) {
/*     */     return (ChildrenContainer)Unsafe.instance.getObjectVolatile($this, AbstractActorCell.childrenOffset);
/*     */   }
/*     */   
/*     */   public static final Iterable children(ActorCell $this) {
/*     */     return $this.childrenRefs().children();
/*     */   }
/*     */   
/*     */   public static final Iterable getChildren(ActorCell $this) {
/*     */     return (Iterable)JavaConverters$.MODULE$.asJavaIterableConverter((Iterable)$this.children()).asJava();
/*     */   }
/*     */   
/*     */   public static final Option child(ActorCell $this, String name) {
/*     */     return Option$.MODULE$.apply($this.getChild(name));
/*     */   }
/*     */   
/*     */   public static final ActorRef getChild(ActorCell $this, String name) {
/*     */     Option<ChildStats> option = $this.childrenRefs().getByName(name);
/*     */     if (option instanceof Some) {
/*     */       Some some = (Some)option;
/*     */       ChildStats s = (ChildStats)some.x();
/*     */       if (s instanceof ChildRestartStats) {
/*     */         ChildRestartStats childRestartStats = (ChildRestartStats)s;
/*     */         return childRestartStats.child();
/*     */       } 
/*     */     } 
/*     */     null;
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static ActorRef actorOf(ActorCell $this, Props props) {
/*     */     return makeChild($this, $this, props, $this.randomName(), false, false);
/*     */   }
/*     */   
/*     */   public static ActorRef actorOf(ActorCell $this, Props props, String name) {
/*     */     return makeChild($this, $this, props, checkName($this, name), false, false);
/*     */   }
/*     */   
/*     */   public static ActorRef attachChild(ActorCell $this, Props props, boolean systemService) {
/*     */     return makeChild($this, $this, props, $this.randomName(), true, systemService);
/*     */   }
/*     */   
/*     */   public static ActorRef attachChild(ActorCell $this, Props props, String name, boolean systemService) {
/*     */     return makeChild($this, $this, props, checkName($this, name), true, systemService);
/*     */   }
/*     */   
/*     */   private static final long inc$1(ActorCell $this) {
/*     */     while (true) {
/*  47 */       long current = Unsafe.instance.getLongVolatile($this, AbstractActorCell.nextNameOffset);
/*  48 */       if (Unsafe.instance.compareAndSwapLong($this, AbstractActorCell.nextNameOffset, current, current + 1L))
/*  48 */         return current; 
/*  49 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final String randomName(ActorCell $this) {
/*  51 */     return Helpers$.MODULE$.base64(inc$1($this), Helpers$.MODULE$.base64$default$2());
/*     */   }
/*     */   
/*     */   public static final void stop(ActorCell $this, ActorRef actor) {
/*     */     boolean bool;
/*  61 */     ActorRef actorRef = actor;
/*  62 */     if (actorRef instanceof RepointableRef) {
/*  62 */       ActorRef actorRef1 = actorRef;
/*  62 */       bool = ((RepointableRef)actorRef1).isStarted();
/*     */     } else {
/*  63 */       bool = true;
/*     */     } 
/*  64 */     $this.childrenRefs().getByRef(actor).isDefined() ? (bool ? BoxesRunTime.boxToBoolean(shallDie$1($this, actor)) : BoxedUnit.UNIT) : BoxedUnit.UNIT;
/*  66 */     ((InternalActorRef)actor).stop();
/*     */   }
/*     */   
/*     */   private static final boolean shallDie$1(ActorCell $this, ActorRef ref) {
/*     */     while (true) {
/*     */       ChildrenContainer c = $this.childrenRefs();
/*     */       if (swapChildrenRefs($this, c, c.shallDie(ref)))
/*     */         return true; 
/*     */       ref = ref;
/*     */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final boolean swapChildrenRefs(ActorCell $this, ChildrenContainer oldChildren, ChildrenContainer newChildren) {
/*  73 */     return Unsafe.instance.compareAndSwapObject($this, AbstractActorCell.childrenOffset, oldChildren, newChildren);
/*     */   }
/*     */   
/*     */   public static final boolean reserveChild(ActorCell $this, String name) {
/*     */     while (true) {
/*  76 */       ChildrenContainer c = $this.childrenRefs();
/*  77 */       if (swapChildrenRefs($this, c, c.reserve(name)))
/*     */         return true; 
/*  77 */       name = name;
/*  77 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final boolean unreserveChild(ActorCell $this, String name) {
/*     */     while (true) {
/*  81 */       ChildrenContainer c = $this.childrenRefs();
/*  82 */       if (swapChildrenRefs($this, c, c.unreserve(name)))
/*     */         return true; 
/*  82 */       name = name;
/*  82 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final Option initChild(ActorCell $this, ActorRef ref) {
/*     */     Some some;
/*     */     while (true) {
/*  86 */       ChildrenContainer cc = $this.childrenRefs();
/*  88 */       boolean bool = false;
/*  88 */       null;
/*  88 */       Some some1 = null;
/*     */       Option<ChildStats> option = cc.getByName(ref.path().name());
/*  88 */       if (option instanceof Some) {
/*  88 */         bool = true;
/*  88 */         some1 = (Some)option;
/*  88 */         if (some1.x() instanceof ChildRestartStats) {
/*  88 */           some = some1;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     return (Option)some;
/*     */   }
/*     */   
/*     */   public static final boolean setChildrenTerminationReason(ActorCell $this, ChildrenContainer.SuspendReason reason) {
/*     */     boolean bool;
/*     */     while (true) {
/*  98 */       ChildrenContainer childrenContainer = $this.childrenRefs();
/*  99 */       if (childrenContainer instanceof ChildrenContainer.TerminatingChildrenContainer) {
/*  99 */         ChildrenContainer.TerminatingChildrenContainer terminatingChildrenContainer = (ChildrenContainer.TerminatingChildrenContainer)childrenContainer;
/* 100 */         ChildrenContainer.SuspendReason x$2 = reason;
/* 100 */         TreeMap<String, ChildStats> x$3 = terminatingChildrenContainer.copy$default$1();
/* 100 */         Set<ActorRef> x$4 = terminatingChildrenContainer.copy$default$2();
/* 100 */         if (swapChildrenRefs($this, terminatingChildrenContainer, terminatingChildrenContainer.copy(x$3, x$4, x$2))) {
/* 100 */           boolean bool1 = true;
/*     */           break;
/*     */         } 
/* 100 */         reason = reason;
/* 100 */         $this = $this;
/*     */         continue;
/*     */       } 
/* 101 */       bool = false;
/*     */       break;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public static final void setTerminated(ActorCell $this) {
/* 105 */     Unsafe.instance.putObjectVolatile($this, AbstractActorCell.childrenOffset, ChildrenContainer.TerminatedChildrenContainer$.MODULE$);
/*     */   }
/*     */   
/*     */   public static boolean isNormal(ActorCell $this) {
/* 111 */     return $this.childrenRefs().isNormal();
/*     */   }
/*     */   
/*     */   public static boolean isTerminating(ActorCell $this) {
/* 113 */     return $this.childrenRefs().isTerminating();
/*     */   }
/*     */   
/*     */   public static ChildrenContainer.SuspendReason waitingForChildrenOrNull(ActorCell $this) {
/* 115 */     ChildrenContainer childrenContainer = $this.childrenRefs();
/* 116 */     if (childrenContainer instanceof ChildrenContainer.TerminatingChildrenContainer) {
/* 116 */       ChildrenContainer.TerminatingChildrenContainer terminatingChildrenContainer = (ChildrenContainer.TerminatingChildrenContainer)childrenContainer;
/* 116 */       ChildrenContainer.SuspendReason w = terminatingChildrenContainer.reason();
/* 116 */       if (w instanceof ChildrenContainer.WaitingForChildren) {
/*     */         ChildrenContainer.SuspendReason suspendReason;
/* 116 */         return suspendReason = w;
/*     */       } 
/*     */     } 
/* 117 */     null;
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public static Set suspendChildren$default$1(ActorCell $this) {
/* 120 */     return Predef$.MODULE$.Set().empty();
/*     */   }
/*     */   
/*     */   public static void suspendChildren(ActorCell $this, Set exceptFor) {
/* 121 */     $this.childrenRefs().stats().foreach((Function1)new Children$$anonfun$suspendChildren$1($this, exceptFor));
/*     */   }
/*     */   
/*     */   public static void resumeChildren(ActorCell $this, Throwable causedByFailure, ActorRef perp) {
/* 127 */     $this.childrenRefs().stats().foreach((Function1)new Children$$anonfun$resumeChildren$1($this, causedByFailure, perp));
/*     */   }
/*     */   
/*     */   public static Option getChildByName(ActorCell $this, String name) {
/* 132 */     return $this.childrenRefs().getByName(name);
/*     */   }
/*     */   
/*     */   public static Option getChildByRef(ActorCell $this, ActorRef ref) {
/* 134 */     return $this.childrenRefs().getByRef(ref);
/*     */   }
/*     */   
/*     */   public static Iterable getAllChildStats(ActorCell $this) {
/* 136 */     return $this.childrenRefs().stats();
/*     */   }
/*     */   
/*     */   public static InternalActorRef getSingleChild(ActorCell $this, String name) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: bipush #35
/*     */     //   3: invokevirtual indexOf : (I)I
/*     */     //   6: iconst_m1
/*     */     //   7: if_icmpne -> 75
/*     */     //   10: aload_0
/*     */     //   11: aload_1
/*     */     //   12: invokevirtual getChildByName : (Ljava/lang/String;)Lscala/Option;
/*     */     //   15: astore_2
/*     */     //   16: aload_2
/*     */     //   17: instanceof scala/Some
/*     */     //   20: ifeq -> 65
/*     */     //   23: aload_2
/*     */     //   24: checkcast scala/Some
/*     */     //   27: astore_3
/*     */     //   28: aload_3
/*     */     //   29: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   32: checkcast akka/actor/ChildStats
/*     */     //   35: astore #4
/*     */     //   37: aload #4
/*     */     //   39: instanceof akka/actor/ChildRestartStats
/*     */     //   42: ifeq -> 65
/*     */     //   45: aload #4
/*     */     //   47: checkcast akka/actor/ChildRestartStats
/*     */     //   50: astore #5
/*     */     //   52: aload #5
/*     */     //   54: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   57: checkcast akka/actor/InternalActorRef
/*     */     //   60: astore #6
/*     */     //   62: goto -> 70
/*     */     //   65: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   68: astore #6
/*     */     //   70: aload #6
/*     */     //   72: goto -> 227
/*     */     //   75: getstatic akka/actor/ActorCell$.MODULE$ : Lakka/actor/ActorCell$;
/*     */     //   78: aload_1
/*     */     //   79: invokevirtual splitNameAndUid : (Ljava/lang/String;)Lscala/Tuple2;
/*     */     //   82: astore #8
/*     */     //   84: aload #8
/*     */     //   86: ifnull -> 228
/*     */     //   89: aload #8
/*     */     //   91: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   94: checkcast java/lang/String
/*     */     //   97: astore #9
/*     */     //   99: aload #8
/*     */     //   101: invokevirtual _2$mcI$sp : ()I
/*     */     //   104: istore #10
/*     */     //   106: new scala/Tuple2
/*     */     //   109: dup
/*     */     //   110: aload #9
/*     */     //   112: iload #10
/*     */     //   114: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   117: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   120: astore #11
/*     */     //   122: aload #11
/*     */     //   124: astore #7
/*     */     //   126: aload #7
/*     */     //   128: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   131: checkcast java/lang/String
/*     */     //   134: astore #12
/*     */     //   136: aload #7
/*     */     //   138: invokevirtual _2$mcI$sp : ()I
/*     */     //   141: istore #13
/*     */     //   143: aload_0
/*     */     //   144: aload #12
/*     */     //   146: invokevirtual getChildByName : (Ljava/lang/String;)Lscala/Option;
/*     */     //   149: astore #14
/*     */     //   151: aload #14
/*     */     //   153: instanceof scala/Some
/*     */     //   156: ifeq -> 220
/*     */     //   159: aload #14
/*     */     //   161: checkcast scala/Some
/*     */     //   164: astore #15
/*     */     //   166: aload #15
/*     */     //   168: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   171: checkcast akka/actor/ChildStats
/*     */     //   174: astore #16
/*     */     //   176: aload #16
/*     */     //   178: instanceof akka/actor/ChildRestartStats
/*     */     //   181: ifeq -> 220
/*     */     //   184: aload #16
/*     */     //   186: checkcast akka/actor/ChildRestartStats
/*     */     //   189: astore #17
/*     */     //   191: iload #13
/*     */     //   193: iconst_0
/*     */     //   194: if_icmpeq -> 207
/*     */     //   197: iload #13
/*     */     //   199: aload #17
/*     */     //   201: invokevirtual uid : ()I
/*     */     //   204: if_icmpne -> 220
/*     */     //   207: aload #17
/*     */     //   209: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   212: checkcast akka/actor/InternalActorRef
/*     */     //   215: astore #18
/*     */     //   217: goto -> 225
/*     */     //   220: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   223: astore #18
/*     */     //   225: aload #18
/*     */     //   227: areturn
/*     */     //   228: new scala/MatchError
/*     */     //   231: dup
/*     */     //   232: aload #8
/*     */     //   234: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   237: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #139	-> 0
/*     */     //   #141	-> 10
/*     */     //   #142	-> 16
/*     */     //   #143	-> 65
/*     */     //   #141	-> 70
/*     */     //   #146	-> 75
/*     */     //   #147	-> 143
/*     */     //   #148	-> 151
/*     */     //   #149	-> 207
/*     */     //   #150	-> 220
/*     */     //   #147	-> 225
/*     */     //   #139	-> 227
/*     */     //   #146	-> 228
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	238	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	238	1	name	Ljava/lang/String;
/*     */     //   37	201	4	crs	Lakka/actor/ChildStats;
/*     */     //   99	139	9	childName	Ljava/lang/String;
/*     */     //   106	132	10	uid	I
/*     */     //   136	91	12	childName	Ljava/lang/String;
/*     */     //   143	84	13	uid	I
/*     */     //   176	62	16	crs	Lakka/actor/ChildStats;
/*     */   }
/*     */   
/*     */   private static final ChildrenContainer removeChild$1(ActorCell $this, ActorRef ref) {
/*     */     while (true) {
/* 156 */       ChildrenContainer c = $this.childrenRefs();
/* 157 */       ChildrenContainer n = c.remove(ref);
/* 158 */       if (swapChildrenRefs($this, c, n))
/* 158 */         return n; 
/* 158 */       ref = ref;
/* 158 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Option removeChildAndGetStateChange(ActorCell $this, ActorRef child) {
/*     */     None$ none$;
/* 161 */     ChildrenContainer childrenContainer = $this.childrenRefs();
/* 162 */     if (childrenContainer instanceof ChildrenContainer.TerminatingChildrenContainer) {
/*     */       Some some2;
/* 162 */       ChildrenContainer.TerminatingChildrenContainer terminatingChildrenContainer = (ChildrenContainer.TerminatingChildrenContainer)childrenContainer;
/* 162 */       ChildrenContainer.SuspendReason reason = terminatingChildrenContainer.reason();
/* 163 */       ChildrenContainer childrenContainer1 = removeChild$1($this, child);
/* 164 */       if (childrenContainer1 instanceof ChildrenContainer.TerminatingChildrenContainer) {
/* 164 */         None$ none$1 = None$.MODULE$;
/*     */       } else {
/* 165 */         some2 = new Some(reason);
/*     */       } 
/*     */       Some some1 = some2;
/*     */     } else {
/* 168 */       removeChild$1($this, child);
/* 169 */       none$ = None$.MODULE$;
/*     */     } 
/*     */     return (Option)none$;
/*     */   }
/*     */   
/*     */   private static String checkName(ActorCell $this, String name) {
/* 178 */     String str1 = name;
/* 179 */     if (str1 == null)
/* 179 */       throw new InvalidActorNameException("actor name must not be null"); 
/* 180 */     String str2 = str1;
/* 180 */     if ("" == null) {
/* 180 */       "";
/* 180 */       if (str2 != null)
/* 181 */         Option option = ActorPath$.MODULE$.ElementRegex().unapplySeq(str1); 
/*     */     } else {
/*     */       if ("".equals(str2))
/*     */         throw new InvalidActorNameException("actor name must not be empty"); 
/* 181 */       Option option = ActorPath$.MODULE$.ElementRegex().unapplySeq(str1);
/*     */     } 
/*     */     throw new InvalidActorNameException("actor name must not be empty");
/*     */   }
/*     */   
/*     */   private static ActorRef makeChild(ActorCell $this, ActorCell cell, Props props, String name, boolean async, boolean systemService) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   4: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   7: invokevirtual SerializeAllCreators : ()Z
/*     */     //   10: ifeq -> 91
/*     */     //   13: iload #5
/*     */     //   15: ifne -> 91
/*     */     //   18: aload_2
/*     */     //   19: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   22: invokevirtual scope : ()Lakka/actor/Scope;
/*     */     //   25: getstatic akka/actor/LocalScope$.MODULE$ : Lakka/actor/LocalScope$;
/*     */     //   28: astore #6
/*     */     //   30: dup
/*     */     //   31: ifnonnull -> 43
/*     */     //   34: pop
/*     */     //   35: aload #6
/*     */     //   37: ifnull -> 91
/*     */     //   40: goto -> 51
/*     */     //   43: aload #6
/*     */     //   45: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   48: ifne -> 91
/*     */     //   51: getstatic akka/serialization/SerializationExtension$.MODULE$ : Lakka/serialization/SerializationExtension$;
/*     */     //   54: aload_1
/*     */     //   55: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   58: invokevirtual apply : (Lakka/actor/ActorSystem;)Lakka/actor/Extension;
/*     */     //   61: checkcast akka/serialization/Serialization
/*     */     //   64: astore #12
/*     */     //   66: aload_2
/*     */     //   67: invokevirtual args : ()Lscala/collection/immutable/Seq;
/*     */     //   70: new akka/actor/dungeon/Children$$anonfun$makeChild$2
/*     */     //   73: dup
/*     */     //   74: aload_0
/*     */     //   75: aload #12
/*     */     //   77: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/serialization/Serialization;)V
/*     */     //   80: invokeinterface forall : (Lscala/Function1;)Z
/*     */     //   85: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   88: goto -> 94
/*     */     //   91: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   94: pop
/*     */     //   95: aload_1
/*     */     //   96: invokevirtual childrenRefs : ()Lakka/actor/dungeon/ChildrenContainer;
/*     */     //   99: invokeinterface isTerminating : ()Z
/*     */     //   104: ifeq -> 118
/*     */     //   107: new java/lang/IllegalStateException
/*     */     //   110: dup
/*     */     //   111: ldc_w 'cannot create children while terminating or terminated'
/*     */     //   114: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   117: athrow
/*     */     //   118: aload_0
/*     */     //   119: aload_3
/*     */     //   120: invokevirtual reserveChild : (Ljava/lang/String;)Z
/*     */     //   123: pop
/*     */     //   124: new akka/actor/ChildActorPath
/*     */     //   127: dup
/*     */     //   128: aload_1
/*     */     //   129: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   132: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   135: aload_3
/*     */     //   136: getstatic akka/actor/ActorCell$.MODULE$ : Lakka/actor/ActorCell$;
/*     */     //   139: invokevirtual newUid : ()I
/*     */     //   142: invokespecial <init> : (Lakka/actor/ActorPath;Ljava/lang/String;I)V
/*     */     //   145: astore #20
/*     */     //   147: aload_1
/*     */     //   148: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   151: aload_1
/*     */     //   152: invokevirtual systemImpl : ()Lakka/actor/ActorSystemImpl;
/*     */     //   155: aload_2
/*     */     //   156: aload_1
/*     */     //   157: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   160: aload #20
/*     */     //   162: iload #5
/*     */     //   164: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   167: iconst_1
/*     */     //   168: iload #4
/*     */     //   170: invokeinterface actorOf : (Lakka/actor/ActorSystemImpl;Lakka/actor/Props;Lakka/actor/InternalActorRef;Lakka/actor/ActorPath;ZLscala/Option;ZZ)Lakka/actor/InternalActorRef;
/*     */     //   175: astore #13
/*     */     //   177: aload_0
/*     */     //   178: invokevirtual mailbox : ()Lakka/dispatch/Mailbox;
/*     */     //   181: ifnull -> 217
/*     */     //   184: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   187: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   190: iconst_1
/*     */     //   191: invokevirtual intWrapper : (I)I
/*     */     //   194: aload_0
/*     */     //   195: invokevirtual mailbox : ()Lakka/dispatch/Mailbox;
/*     */     //   198: invokevirtual suspendCount : ()I
/*     */     //   201: invokevirtual to$extension0 : (II)Lscala/collection/immutable/Range$Inclusive;
/*     */     //   204: new akka/actor/dungeon/Children$$anonfun$makeChild$1
/*     */     //   207: dup
/*     */     //   208: aload_0
/*     */     //   209: aload #13
/*     */     //   211: invokespecial <init> : (Lakka/actor/ActorCell;Lakka/actor/InternalActorRef;)V
/*     */     //   214: invokevirtual foreach$mVc$sp : (Lscala/Function1;)V
/*     */     //   217: aload_0
/*     */     //   218: aload #13
/*     */     //   220: invokevirtual initChild : (Lakka/actor/ActorRef;)Lscala/Option;
/*     */     //   223: pop
/*     */     //   224: aload #13
/*     */     //   226: invokevirtual start : ()V
/*     */     //   229: aload #13
/*     */     //   231: areturn
/*     */     //   232: astore #7
/*     */     //   234: aload #7
/*     */     //   236: astore #8
/*     */     //   238: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   241: aload #8
/*     */     //   243: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   246: astore #9
/*     */     //   248: aload #9
/*     */     //   250: invokevirtual isEmpty : ()Z
/*     */     //   253: ifeq -> 259
/*     */     //   256: aload #7
/*     */     //   258: athrow
/*     */     //   259: aload #9
/*     */     //   261: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   264: checkcast java/lang/Throwable
/*     */     //   267: astore #10
/*     */     //   269: new java/lang/IllegalArgumentException
/*     */     //   272: dup
/*     */     //   273: new scala/StringContext
/*     */     //   276: dup
/*     */     //   277: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   280: iconst_3
/*     */     //   281: anewarray java/lang/String
/*     */     //   284: dup
/*     */     //   285: iconst_0
/*     */     //   286: ldc_w 'pre-creation serialization check failed at ['
/*     */     //   289: aastore
/*     */     //   290: dup
/*     */     //   291: iconst_1
/*     */     //   292: ldc_w '/'
/*     */     //   295: aastore
/*     */     //   296: dup
/*     */     //   297: iconst_2
/*     */     //   298: ldc_w ']'
/*     */     //   301: aastore
/*     */     //   302: checkcast [Ljava/lang/Object;
/*     */     //   305: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   308: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   311: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   314: iconst_2
/*     */     //   315: anewarray java/lang/Object
/*     */     //   318: dup
/*     */     //   319: iconst_0
/*     */     //   320: aload_1
/*     */     //   321: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   324: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   327: aastore
/*     */     //   328: dup
/*     */     //   329: iconst_1
/*     */     //   330: aload_3
/*     */     //   331: aastore
/*     */     //   332: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   335: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   338: aload #10
/*     */     //   340: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   343: athrow
/*     */     //   344: astore #14
/*     */     //   346: aload #14
/*     */     //   348: astore #15
/*     */     //   350: aload #15
/*     */     //   352: instanceof java/lang/InterruptedException
/*     */     //   355: ifeq -> 378
/*     */     //   358: aload #15
/*     */     //   360: checkcast java/lang/InterruptedException
/*     */     //   363: astore #16
/*     */     //   365: aload_0
/*     */     //   366: aload_3
/*     */     //   367: invokevirtual unreserveChild : (Ljava/lang/String;)Z
/*     */     //   370: pop
/*     */     //   371: invokestatic interrupted : ()Z
/*     */     //   374: pop
/*     */     //   375: aload #16
/*     */     //   377: athrow
/*     */     //   378: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   381: aload #15
/*     */     //   383: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   386: astore #18
/*     */     //   388: aload #18
/*     */     //   390: invokevirtual isEmpty : ()Z
/*     */     //   393: ifeq -> 399
/*     */     //   396: aload #14
/*     */     //   398: athrow
/*     */     //   399: aload #18
/*     */     //   401: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   404: checkcast java/lang/Throwable
/*     */     //   407: astore #19
/*     */     //   409: aload_0
/*     */     //   410: aload_3
/*     */     //   411: invokevirtual unreserveChild : (Ljava/lang/String;)Z
/*     */     //   414: pop
/*     */     //   415: aload #19
/*     */     //   417: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #187	-> 0
/*     */     //   #189	-> 51
/*     */     //   #190	-> 66
/*     */     //   #188	-> 85
/*     */     //   #187	-> 91
/*     */     //   #200	-> 95
/*     */     //   #202	-> 118
/*     */     //   #206	-> 124
/*     */     //   #207	-> 147
/*     */     //   #208	-> 162
/*     */     //   #207	-> 170
/*     */     //   #204	-> 175
/*     */     //   #219	-> 177
/*     */     //   #220	-> 217
/*     */     //   #221	-> 224
/*     */     //   #222	-> 229
/*     */     //   #186	-> 231
/*     */     //   #188	-> 232
/*     */     //   #194	-> 238
/*     */     //   #188	-> 256
/*     */     //   #194	-> 261
/*     */     //   #205	-> 344
/*     */     //   #210	-> 350
/*     */     //   #211	-> 365
/*     */     //   #212	-> 371
/*     */     //   #213	-> 375
/*     */     //   #214	-> 378
/*     */     //   #205	-> 396
/*     */     //   #214	-> 401
/*     */     //   #215	-> 409
/*     */     //   #216	-> 415
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	418	0	$this	Lakka/actor/ActorCell;
/*     */     //   0	418	1	cell	Lakka/actor/ActorCell;
/*     */     //   0	418	2	props	Lakka/actor/Props;
/*     */     //   0	418	3	name	Ljava/lang/String;
/*     */     //   0	418	4	async	Z
/*     */     //   0	418	5	systemService	Z
/*     */     //   66	19	12	ser	Lakka/serialization/Serialization;
/*     */     //   147	28	20	childPath	Lakka/actor/ChildActorPath;
/*     */     //   177	54	13	actor	Lakka/actor/InternalActorRef;
/*     */     //   269	149	10	e	Ljava/lang/Throwable;
/*     */     //   409	9	19	e	Ljava/lang/Throwable;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   51	85	232	finally
/*     */     //   124	175	344	finally
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\Children$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */