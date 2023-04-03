/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public final class ActorSelection$ implements Serializable {
/*     */   public static final ActorSelection$ MODULE$;
/*     */   
/*     */   public class ActorSelection$$anonfun$resolveOne$1 extends AbstractFunction1<Try<Object>, Promise<ActorRef>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$1;
/*     */     
/*     */     public final Promise<ActorRef> apply(Try x0$1) {
/*  63 */       Try try_ = x0$1;
/*  64 */       if (try_ instanceof Success) {
/*  64 */         Success success = (Success)try_;
/*  64 */         Object object = success.value();
/*  64 */         if (object instanceof ActorIdentity) {
/*  64 */           ActorIdentity actorIdentity = (ActorIdentity)object;
/*  64 */           Option<ActorRef> option = actorIdentity.ref();
/*  64 */           if (option instanceof Some) {
/*  64 */             Some some = (Some)option;
/*  64 */             ActorRef ref = (ActorRef)some.x();
/*  64 */             return this.p$1.success(ref);
/*     */           } 
/*     */         } 
/*     */       } 
/*  65 */       return this.p$1.failure(new ActorNotFound(this.$outer));
/*     */     }
/*     */     
/*     */     public ActorSelection$$anonfun$resolveOne$1(ActorSelection $outer, Promise p$1) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ActorSelection$() {
/* 140 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public ScalaActorSelection toScala(ActorSelection sel) {
/* 142 */     return (ScalaActorSelection)sel;
/*     */   }
/*     */   
/*     */   public ActorSelection apply(ActorRef anchorRef, String path) {
/* 150 */     return apply(anchorRef, (Iterable<String>)scala.Predef$.MODULE$.wrapRefArray((Object[])path.split("/+")));
/*     */   }
/*     */   
/*     */   public ActorSelection apply(ActorRef anchorRef, Iterable elements) {
/* 159 */     IndexedSeq compiled = (IndexedSeq)elements
/*     */       
/* 164 */       .collect((PartialFunction)new ActorSelection$$anonfun$1(), scala.collection.package$.MODULE$.breakOut(scala.Predef$.MODULE$.fallbackStringCanBuildFrom()));
/* 165 */     return new ActorSelection$$anon$1(anchorRef, compiled);
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$1 extends AbstractPartialFunction<String, Product> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends String, B1> B1 applyOrElse(String x1, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: aload_3
/*     */       //   3: invokevirtual isEmpty : ()Z
/*     */       //   6: ifeq -> 21
/*     */       //   9: aload_2
/*     */       //   10: aload_1
/*     */       //   11: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   16: astore #4
/*     */       //   18: goto -> 94
/*     */       //   21: aload_3
/*     */       //   22: bipush #63
/*     */       //   24: invokevirtual indexOf : (I)I
/*     */       //   27: iconst_m1
/*     */       //   28: if_icmpne -> 41
/*     */       //   31: aload_3
/*     */       //   32: bipush #42
/*     */       //   34: invokevirtual indexOf : (I)I
/*     */       //   37: iconst_m1
/*     */       //   38: if_icmpeq -> 52
/*     */       //   41: new akka/actor/SelectChildPattern
/*     */       //   44: dup
/*     */       //   45: aload_3
/*     */       //   46: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   49: goto -> 92
/*     */       //   52: aload_3
/*     */       //   53: ldc '..'
/*     */       //   55: astore #5
/*     */       //   57: dup
/*     */       //   58: ifnonnull -> 70
/*     */       //   61: pop
/*     */       //   62: aload #5
/*     */       //   64: ifnull -> 78
/*     */       //   67: goto -> 84
/*     */       //   70: aload #5
/*     */       //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   75: ifeq -> 84
/*     */       //   78: getstatic akka/actor/SelectParent$.MODULE$ : Lakka/actor/SelectParent$;
/*     */       //   81: goto -> 92
/*     */       //   84: new akka/actor/SelectChildName
/*     */       //   87: dup
/*     */       //   88: aload_3
/*     */       //   89: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   92: astore #4
/*     */       //   94: aload #4
/*     */       //   96: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #159	-> 0
/*     */       //   #160	-> 2
/*     */       //   #159	-> 9
/*     */       //   #161	-> 21
/*     */       //   #162	-> 52
/*     */       //   #163	-> 84
/*     */       //   #161	-> 92
/*     */       //   #159	-> 94
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	97	0	this	Lakka/actor/ActorSelection$$anonfun$1;
/*     */       //   0	97	1	x1	Ljava/lang/String;
/*     */       //   0	97	2	default	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(String x1) {
/*     */       boolean bool;
/*     */       String str = x1;
/*     */       if (str.isEmpty()) {
/*     */         bool = false;
/*     */       } else {
/*     */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anon$1 extends ActorSelection implements ScalaActorSelection {
/*     */     private final ActorRef anchor;
/*     */     
/*     */     private final IndexedSeq<SelectionPathElement> path;
/*     */     
/*     */     public void $bang(Object msg, ActorRef sender) {
/* 165 */       ScalaActorSelection$class.$bang(this, msg, sender);
/*     */     }
/*     */     
/*     */     public ActorRef $bang$default$2(Object msg) {
/* 165 */       return ScalaActorSelection$class.$bang$default$2(this, msg);
/*     */     }
/*     */     
/*     */     public ActorSelection$$anon$1(ActorRef anchorRef$1, IndexedSeq<SelectionPathElement> compiled$1) {
/* 165 */       ScalaActorSelection$class.$init$(this);
/* 166 */       this.anchor = anchorRef$1;
/* 167 */       this.path = compiled$1;
/*     */     }
/*     */     
/*     */     public ActorRef anchor() {
/*     */       return this.anchor;
/*     */     }
/*     */     
/*     */     public IndexedSeq<SelectionPathElement> path() {
/* 167 */       return this.path;
/*     */     }
/*     */   }
/*     */   
/*     */   public void deliverSelection(InternalActorRef anchor, ActorRef sender, ActorSelectionMessage sel) {
/* 177 */     if (sel.elements().isEmpty()) {
/* 178 */       anchor.tell(sel.msg(), sender);
/*     */     } else {
/* 180 */       Iterator iter = sel.elements().iterator();
/* 221 */       rec$1(anchor, anchor, sender, sel, iter);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void rec$1(InternalActorRef ref, InternalActorRef anchor$1, ActorRef sender$1, ActorSelectionMessage sel$1, Iterator iter$1) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore #7
/*     */     //   3: aload #7
/*     */     //   5: instanceof akka/actor/ActorRefWithCell
/*     */     //   8: ifeq -> 419
/*     */     //   11: aload #7
/*     */     //   13: checkcast akka/actor/ActorRefWithCell
/*     */     //   16: astore #8
/*     */     //   18: aload #5
/*     */     //   20: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   25: checkcast akka/actor/SelectionPathElement
/*     */     //   28: astore #10
/*     */     //   30: getstatic akka/actor/SelectParent$.MODULE$ : Lakka/actor/SelectParent$;
/*     */     //   33: aload #10
/*     */     //   35: astore #11
/*     */     //   37: dup
/*     */     //   38: ifnonnull -> 50
/*     */     //   41: pop
/*     */     //   42: aload #11
/*     */     //   44: ifnull -> 58
/*     */     //   47: goto -> 99
/*     */     //   50: aload #11
/*     */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   55: ifeq -> 99
/*     */     //   58: aload_1
/*     */     //   59: invokevirtual getParent : ()Lakka/actor/InternalActorRef;
/*     */     //   62: astore #13
/*     */     //   64: aload #5
/*     */     //   66: invokeinterface isEmpty : ()Z
/*     */     //   71: ifeq -> 93
/*     */     //   74: aload #13
/*     */     //   76: aload #4
/*     */     //   78: invokevirtual msg : ()Ljava/lang/Object;
/*     */     //   81: aload_3
/*     */     //   82: invokevirtual tell : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   85: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   88: astore #12
/*     */     //   90: goto -> 401
/*     */     //   93: aload #13
/*     */     //   95: astore_1
/*     */     //   96: goto -> 0
/*     */     //   99: aload #10
/*     */     //   101: instanceof akka/actor/SelectChildName
/*     */     //   104: ifeq -> 269
/*     */     //   107: aload #10
/*     */     //   109: checkcast akka/actor/SelectChildName
/*     */     //   112: astore #14
/*     */     //   114: aload #14
/*     */     //   116: invokevirtual name : ()Ljava/lang/String;
/*     */     //   119: astore #15
/*     */     //   121: aload #8
/*     */     //   123: aload #15
/*     */     //   125: invokevirtual getSingleChild : (Ljava/lang/String;)Lakka/actor/InternalActorRef;
/*     */     //   128: astore #16
/*     */     //   130: aload #16
/*     */     //   132: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   135: astore #17
/*     */     //   137: dup
/*     */     //   138: ifnonnull -> 150
/*     */     //   141: pop
/*     */     //   142: aload #17
/*     */     //   144: ifnull -> 158
/*     */     //   147: goto -> 234
/*     */     //   150: aload #17
/*     */     //   152: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   155: ifeq -> 234
/*     */     //   158: new akka/actor/EmptyLocalActorRef
/*     */     //   161: dup
/*     */     //   162: aload #8
/*     */     //   164: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   167: aload_2
/*     */     //   168: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   171: aload #4
/*     */     //   173: invokevirtual elements : ()Lscala/collection/immutable/Iterable;
/*     */     //   176: new akka/actor/ActorSelection$$anonfun$2
/*     */     //   179: dup
/*     */     //   180: invokespecial <init> : ()V
/*     */     //   183: getstatic scala/collection/immutable/Iterable$.MODULE$ : Lscala/collection/immutable/Iterable$;
/*     */     //   186: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   189: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   194: checkcast scala/collection/Iterable
/*     */     //   197: invokeinterface $div : (Lscala/collection/Iterable;)Lakka/actor/ActorPath;
/*     */     //   202: aload #8
/*     */     //   204: invokevirtual underlying : ()Lakka/actor/Cell;
/*     */     //   207: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */     //   212: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */     //   215: invokespecial <init> : (Lakka/actor/ActorRefProvider;Lakka/actor/ActorPath;Lakka/event/EventStream;)V
/*     */     //   218: astore #18
/*     */     //   220: aload #18
/*     */     //   222: aload #4
/*     */     //   224: aload_3
/*     */     //   225: invokevirtual tell : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   228: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   231: goto -> 258
/*     */     //   234: aload #5
/*     */     //   236: invokeinterface isEmpty : ()Z
/*     */     //   241: ifeq -> 263
/*     */     //   244: aload #16
/*     */     //   246: aload #4
/*     */     //   248: invokevirtual msg : ()Ljava/lang/Object;
/*     */     //   251: aload_3
/*     */     //   252: invokevirtual tell : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   255: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   258: astore #12
/*     */     //   260: goto -> 401
/*     */     //   263: aload #16
/*     */     //   265: astore_1
/*     */     //   266: goto -> 0
/*     */     //   269: aload #10
/*     */     //   271: instanceof akka/actor/SelectChildPattern
/*     */     //   274: ifeq -> 409
/*     */     //   277: aload #10
/*     */     //   279: checkcast akka/actor/SelectChildPattern
/*     */     //   282: astore #19
/*     */     //   284: aload #8
/*     */     //   286: invokevirtual children : ()Lscala/collection/immutable/Iterable;
/*     */     //   289: astore #20
/*     */     //   291: aload #5
/*     */     //   293: invokeinterface isEmpty : ()Z
/*     */     //   298: ifeq -> 338
/*     */     //   301: aload #20
/*     */     //   303: new akka/actor/ActorSelection$$anonfun$rec$1$1
/*     */     //   306: dup
/*     */     //   307: aload #19
/*     */     //   309: invokespecial <init> : (Lakka/actor/SelectChildPattern;)V
/*     */     //   312: invokeinterface withFilter : (Lscala/Function1;)Lscala/collection/generic/FilterMonadic;
/*     */     //   317: new akka/actor/ActorSelection$$anonfun$rec$1$2
/*     */     //   320: dup
/*     */     //   321: aload_3
/*     */     //   322: aload #4
/*     */     //   324: invokespecial <init> : (Lakka/actor/ActorRef;Lakka/actor/ActorSelectionMessage;)V
/*     */     //   327: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   332: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   335: goto -> 399
/*     */     //   338: aload #5
/*     */     //   340: invokeinterface toVector : ()Lscala/collection/immutable/Vector;
/*     */     //   345: astore #22
/*     */     //   347: aload #4
/*     */     //   349: invokevirtual copy$default$1 : ()Ljava/lang/Object;
/*     */     //   352: astore #23
/*     */     //   354: aload #4
/*     */     //   356: aload #23
/*     */     //   358: aload #22
/*     */     //   360: invokevirtual copy : (Ljava/lang/Object;Lscala/collection/immutable/Iterable;)Lakka/actor/ActorSelectionMessage;
/*     */     //   363: astore #21
/*     */     //   365: aload #20
/*     */     //   367: new akka/actor/ActorSelection$$anonfun$rec$1$3
/*     */     //   370: dup
/*     */     //   371: aload #19
/*     */     //   373: invokespecial <init> : (Lakka/actor/SelectChildPattern;)V
/*     */     //   376: invokeinterface withFilter : (Lscala/Function1;)Lscala/collection/generic/FilterMonadic;
/*     */     //   381: new akka/actor/ActorSelection$$anonfun$rec$1$4
/*     */     //   384: dup
/*     */     //   385: aload_3
/*     */     //   386: aload #21
/*     */     //   388: invokespecial <init> : (Lakka/actor/ActorRef;Lakka/actor/ActorSelectionMessage;)V
/*     */     //   391: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   396: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   399: astore #12
/*     */     //   401: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   404: astore #9
/*     */     //   406: goto -> 454
/*     */     //   409: new scala/MatchError
/*     */     //   412: dup
/*     */     //   413: aload #10
/*     */     //   415: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   418: athrow
/*     */     //   419: aload_1
/*     */     //   420: aload #5
/*     */     //   422: invokeinterface toVector : ()Lscala/collection/immutable/Vector;
/*     */     //   427: astore #24
/*     */     //   429: aload #4
/*     */     //   431: invokevirtual copy$default$1 : ()Ljava/lang/Object;
/*     */     //   434: astore #25
/*     */     //   436: aload #4
/*     */     //   438: aload #25
/*     */     //   440: aload #24
/*     */     //   442: invokevirtual copy : (Ljava/lang/Object;Lscala/collection/immutable/Iterable;)Lakka/actor/ActorSelectionMessage;
/*     */     //   445: aload_3
/*     */     //   446: invokevirtual tell : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   449: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   452: astore #9
/*     */     //   454: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   457: pop
/*     */     //   458: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #183	-> 0
/*     */     //   #184	-> 3
/*     */     //   #185	-> 18
/*     */     //   #186	-> 30
/*     */     //   #187	-> 58
/*     */     //   #188	-> 64
/*     */     //   #189	-> 74
/*     */     //   #186	-> 88
/*     */     //   #191	-> 93
/*     */     //   #192	-> 99
/*     */     //   #193	-> 121
/*     */     //   #194	-> 130
/*     */     //   #195	-> 158
/*     */     //   #196	-> 202
/*     */     //   #195	-> 215
/*     */     //   #197	-> 220
/*     */     //   #198	-> 234
/*     */     //   #199	-> 244
/*     */     //   #192	-> 258
/*     */     //   #201	-> 263
/*     */     //   #202	-> 269
/*     */     //   #204	-> 284
/*     */     //   #205	-> 291
/*     */     //   #206	-> 301
/*     */     //   #209	-> 338
/*     */     //   #210	-> 365
/*     */     //   #202	-> 399
/*     */     //   #185	-> 401
/*     */     //   #217	-> 419
/*     */     //   #183	-> 454
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	459	0	this	Lakka/actor/ActorSelection$;
/*     */     //   0	459	1	ref	Lakka/actor/InternalActorRef;
/*     */     //   0	459	2	anchor$1	Lakka/actor/InternalActorRef;
/*     */     //   0	459	3	sender$1	Lakka/actor/ActorRef;
/*     */     //   0	459	4	sel$1	Lakka/actor/ActorSelectionMessage;
/*     */     //   0	459	5	iter$1	Lscala/collection/Iterator;
/*     */     //   64	395	13	parent	Lakka/actor/InternalActorRef;
/*     */     //   121	338	15	name	Ljava/lang/String;
/*     */     //   130	329	16	child	Lakka/actor/InternalActorRef;
/*     */     //   220	11	18	emptyRef	Lakka/actor/EmptyLocalActorRef;
/*     */     //   291	168	20	chldr	Lscala/collection/immutable/Iterable;
/*     */     //   347	16	22	x$2	Lscala/collection/immutable/Vector;
/*     */     //   354	9	23	x$3	Ljava/lang/Object;
/*     */     //   365	34	21	m	Lakka/actor/ActorSelectionMessage;
/*     */     //   429	16	24	x$4	Lscala/collection/immutable/Vector;
/*     */     //   436	9	25	x$5	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$2 extends AbstractFunction1<SelectionPathElement, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(SelectionPathElement x$1) {
/*     */       return x$1.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$rec$1$1 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SelectChildPattern x4$1;
/*     */     
/*     */     public final boolean apply(ActorRef c) {
/*     */       return this.x4$1.pattern().matcher(c.path().name()).matches();
/*     */     }
/*     */     
/*     */     public ActorSelection$$anonfun$rec$1$1(SelectChildPattern x4$1) {}
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$rec$1$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef sender$1;
/*     */     
/*     */     private final ActorSelectionMessage sel$1;
/*     */     
/*     */     public ActorSelection$$anonfun$rec$1$2(ActorRef sender$1, ActorSelectionMessage sel$1) {}
/*     */     
/*     */     public final void apply(ActorRef c) {
/*     */       c.tell(this.sel$1.msg(), this.sender$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$rec$1$3 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SelectChildPattern x4$1;
/*     */     
/*     */     public final boolean apply(ActorRef c) {
/*     */       return this.x4$1.pattern().matcher(c.path().name()).matches();
/*     */     }
/*     */     
/*     */     public ActorSelection$$anonfun$rec$1$3(SelectChildPattern x4$1) {}
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$rec$1$4 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef sender$1;
/*     */     
/*     */     private final ActorSelectionMessage m$1;
/*     */     
/*     */     public ActorSelection$$anonfun$rec$1$4(ActorRef sender$1, ActorSelectionMessage m$1) {}
/*     */     
/*     */     public final void apply(ActorRef c) {
/*     */       ActorSelection$.MODULE$.deliverSelection((InternalActorRef)c, this.sender$1, this.m$1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorSelection$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */