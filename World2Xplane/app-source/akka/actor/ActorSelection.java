/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.ExecutionContexts$sameThreadExecutionContext$;
/*     */ import akka.pattern.AskableActorSelection$;
/*     */ import akka.pattern.package$;
/*     */ import akka.routing.MurmurHash$;
/*     */ import akka.util.Timeout;
/*     */ import akka.util.Timeout$;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.concurrent.Promise$;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=eaB\001\003\003\0039\021Q\001\002\017\003\016$xN]*fY\026\034G/[8o\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M\031\001\001\003\b\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\tIq\"\003\002\021\025\ta1+\032:jC2L'0\0312mK\")!\003\001C\001'\0051A(\0338jiz\"\022\001\006\t\003+\001i\021A\001\005\t/\001\021\rQ\"\005\0051\0051\021M\\2i_J,\022!\007\t\003+iI!a\007\002\003\021\005\033Go\034:SK\032Dq!\b\001C\002\033Ea$\001\003qCRDW#A\020\021\007\001*s%D\001\"\025\t\0213%A\005j[6,H/\0312mK*\021AEC\001\013G>dG.Z2uS>t\027B\001\024\"\005)Ie\016Z3yK\022\034V-\035\t\003+!J!!\013\002\003)M+G.Z2uS>t\007+\031;i\0132,W.\0328u\021\025Y\003\001\"\001-\003\021!X\r\0347\025\0075\002T\007\005\002\n]%\021qF\003\002\005+:LG\017C\0032U\001\007!'A\002ng\036\004\"!C\032\n\005QR!aA!os\")aG\013a\0013\00511/\0328eKJDQ\001\017\001\005\002e\nqAZ8so\006\024H\r\006\002;\001R\021Qf\017\005\006y]\002\035!P\001\bG>tG/\032=u!\t)b(\003\002@\005\ta\021i\031;pe\016{g\016^3yi\")\021i\016a\001e\0059Q.Z:tC\036,\007\"B\"\001\t\003!\025A\003:fg>dg/Z(oKR\tQ\t\006\002G\031B\031qIS\r\016\003!S!!\023\006\002\025\r|gnY;se\026tG/\003\002L\021\n1a)\036;ve\026DQ!\024\"A\0049\013q\001^5nK>,H\017\005\002P%6\t\001K\003\002R\t\005!Q\017^5m\023\t\031\006KA\004US6,w.\036;\t\013\r\003A\021A+\025\005\0313\006\"B'U\001\0049\006C\001-\\\033\005I&B\001.I\003!!WO]1uS>t\027B\001/Z\00591\025N\\5uK\022+(/\031;j_:DQA\030\001\005B}\013\001\002^8TiJLgn\032\013\002AB\021\021\r\032\b\003\023\tL!a\031\006\002\rA\023X\rZ3g\023\t)gM\001\004TiJLgn\032\006\003G*AQ\001\033\001\005\002%\f!\"\0318dQ>\024\b+\031;i+\005Q\007CA\013l\023\ta'AA\005BGR|'\017U1uQ\")a\016\001C\001_\006Q\001/\031;i'R\024\030N\\4\026\003\001DQ!\035\001\005\002=\fQ\003^8TKJL\027\r\\5{CRLwN\034$pe6\fG\017C\003t\001\021\005C/\001\004fcV\fGn\035\013\003kb\004\"!\003<\n\005]T!a\002\"p_2,\027M\034\005\006sJ\004\rAM\001\004_\nT\007\002C>\001\021\013\007I\021\t?\002\021!\f7\017[\"pI\026,\022! \t\003\023yL!a \006\003\007%sG\017C\005\002\004\001A\t\021)Q\005{\006I\001.Y:i\007>$W\r\t\n\006\003\017!\0221\002\004\007\003\023\001\001!!\002\003\031q\022XMZ5oK6,g\016\036 \021\007U\ti!C\002\002\020\t\0211cU2bY\006\f5\r^8s'\026dWm\031;j_:DS\001AA\n\0033\0012!CA\013\023\r\t9B\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\b\003;\021\001\022AA\020\0039\t5\r^8s'\026dWm\031;j_:\0042!FA\021\r\031\t!\001#\001\002$M!\021\021\005\005\017\021\035\021\022\021\005C\001\003O!\"!a\b\t\021\005-\022\021\005C\002\003[\tq\001^8TG\006d\027\r\006\003\002\f\005=\002bBA\031\003S\001\r\001F\001\004g\026d\007\002CA\033\003C!\t!a\016\002\013\005\004\b\017\\=\025\013Q\tI$!\020\t\017\005m\0221\007a\0013\005I\021M\\2i_J\024VM\032\005\007;\005M\002\031\0011\t\021\005U\022\021\005C\001\003\003\"R\001FA\"\003\013Bq!a\017\002@\001\007\021\004\003\005\002H\005}\002\031AA%\003!)G.Z7f]R\034\b#BA&\0037\002g\002BA'\003/rA!a\024\002V5\021\021\021\013\006\004\003'2\021A\002\037s_>$h(C\001\f\023\r\tIFC\001\ba\006\0347.Y4f\023\021\ti&a\030\003\021%#XM]1cY\026T1!!\027\013\021%\t\031'!\t\005\002\021\t)'\001\teK2Lg/\032:TK2,7\r^5p]R9Q&a\032\002p\005E\004bB\f\002b\001\007\021\021\016\t\004+\005-\024bAA7\005\t\001\022J\034;fe:\fG.Q2u_J\024VM\032\005\007m\005\005\004\031A\r\t\021\005E\022\021\ra\001\003g\0022!FA;\023\r\t9H\001\002\026\003\016$xN]*fY\026\034G/[8o\033\026\0348/Y4f\021)\tY(!\t\002\002\023%\021QP\001\fe\026\fGMU3t_24X\r\006\002\002\000A!\021\021QAF\033\t\t\031I\003\003\002\006\006\035\025\001\0027b]\036T!!!#\002\t)\fg/Y\005\005\003\033\013\031I\001\004PE*,7\r\036")
/*     */ public abstract class ActorSelection implements Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private int hashCode;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   public void tell(Object msg, ActorRef sender) {
/*  40 */     ActorSelection$.MODULE$.deliverSelection((InternalActorRef)anchor(), sender, 
/*  41 */         new ActorSelectionMessage(msg, (Iterable<SelectionPathElement>)path()));
/*     */   }
/*     */   
/*     */   public void forward(Object message, ActorContext context) {
/*  48 */     tell(message, context.sender());
/*     */   }
/*     */   
/*     */   public Future<ActorRef> resolveOne(Timeout timeout) {
/*  61 */     ExecutionContexts$sameThreadExecutionContext$ ec = ExecutionContexts$sameThreadExecutionContext$.MODULE$;
/*  62 */     Promise p = Promise$.MODULE$.apply();
/*  62 */     AskableActorSelection$.MODULE$
/*  63 */       .ask$extension(package$.MODULE$.ask(this), new Identify(None$.MODULE$), timeout).onComplete((Function1)new ActorSelection$$anonfun$resolveOne$1(this, p), (ExecutionContext)ec);
/*  67 */     return p.future();
/*     */   }
/*     */   
/*     */   public class ActorSelection$$anonfun$resolveOne$1 extends AbstractFunction1<Try<Object>, Promise<ActorRef>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$1;
/*     */     
/*     */     public final Promise<ActorRef> apply(Try x0$1) {
/*     */       Try try_ = x0$1;
/*     */       if (try_ instanceof Success) {
/*     */         Success success = (Success)try_;
/*     */         Object object = success.value();
/*     */         if (object instanceof ActorIdentity) {
/*     */           ActorIdentity actorIdentity = (ActorIdentity)object;
/*     */           Option<ActorRef> option = actorIdentity.ref();
/*     */           if (option instanceof Some) {
/*     */             Some some = (Some)option;
/*     */             ActorRef ref = (ActorRef)some.x();
/*     */             return this.p$1.success(ref);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       return this.p$1.failure(new ActorNotFound(this.$outer));
/*     */     }
/*     */     
/*     */     public ActorSelection$$anonfun$resolveOne$1(ActorSelection $outer, Promise p$1) {}
/*     */   }
/*     */   
/*     */   public Future<ActorRef> resolveOne(FiniteDuration timeout) {
/*  80 */     return resolveOne(Timeout$.MODULE$.durationToTimeout(timeout));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  83 */     StringBuilder builder = new StringBuilder();
/*  84 */     builder.append("ActorSelection[Anchor(").append(anchor().path());
/*  85 */     (anchor().path().uid() != 0) ? 
/*  86 */       builder.append("#").append(anchor().path().uid()) : BoxedUnit.UNIT;
/*  88 */     builder.append("), Path(").append(path().mkString("/", "/", "")).append(")]");
/*  89 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public ActorPath anchorPath() {
/*  95 */     return anchor().path();
/*     */   }
/*     */   
/*     */   public String pathString() {
/* 100 */     return path().mkString("/", "/", "");
/*     */   }
/*     */   
/*     */   public String toSerializationFormat() {
/*     */     String str1;
/* 108 */     ActorRef actorRef = anchor();
/* 109 */     if (actorRef instanceof ActorRefWithCell) {
/* 109 */       ActorRefWithCell actorRefWithCell = (ActorRefWithCell)actorRef;
/* 109 */       str1 = anchor().path().toStringWithAddress(actorRefWithCell.provider().getDefaultAddress());
/*     */     } else {
/* 110 */       str1 = anchor().path().toString();
/*     */     } 
/*     */     String anchorPath = str1;
/* 113 */     StringBuilder builder = new StringBuilder();
/* 114 */     builder.append(anchorPath);
/* 115 */     char lastChar = builder.charAt(builder.length() - 1);
/* 116 */     (path().nonEmpty() && lastChar != '/') ? 
/* 117 */       builder.append(path().mkString("/", "/", "")) : (
/* 118 */       path().nonEmpty() ? 
/* 119 */       builder.append(path().mkString("/")) : BoxedUnit.UNIT);
/* 120 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: instanceof akka/actor/ActorSelection
/*     */     //   6: ifeq -> 86
/*     */     //   9: aload_2
/*     */     //   10: checkcast akka/actor/ActorSelection
/*     */     //   13: astore_3
/*     */     //   14: aload_0
/*     */     //   15: invokevirtual anchor : ()Lakka/actor/ActorRef;
/*     */     //   18: aload_3
/*     */     //   19: invokevirtual anchor : ()Lakka/actor/ActorRef;
/*     */     //   22: astore #5
/*     */     //   24: dup
/*     */     //   25: ifnonnull -> 37
/*     */     //   28: pop
/*     */     //   29: aload #5
/*     */     //   31: ifnull -> 45
/*     */     //   34: goto -> 80
/*     */     //   37: aload #5
/*     */     //   39: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   42: ifeq -> 80
/*     */     //   45: aload_0
/*     */     //   46: invokevirtual path : ()Lscala/collection/immutable/IndexedSeq;
/*     */     //   49: aload_3
/*     */     //   50: invokevirtual path : ()Lscala/collection/immutable/IndexedSeq;
/*     */     //   53: astore #6
/*     */     //   55: dup
/*     */     //   56: ifnonnull -> 68
/*     */     //   59: pop
/*     */     //   60: aload #6
/*     */     //   62: ifnull -> 76
/*     */     //   65: goto -> 80
/*     */     //   68: aload #6
/*     */     //   70: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   73: ifeq -> 80
/*     */     //   76: iconst_1
/*     */     //   77: goto -> 81
/*     */     //   80: iconst_0
/*     */     //   81: istore #4
/*     */     //   83: goto -> 89
/*     */     //   86: iconst_0
/*     */     //   87: istore #4
/*     */     //   89: iload #4
/*     */     //   91: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #123	-> 0
/*     */     //   #124	-> 2
/*     */     //   #125	-> 86
/*     */     //   #123	-> 89
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	92	0	this	Lakka/actor/ActorSelection;
/*     */     //   0	92	1	obj	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   private int hashCode$lzycompute() {
/* 128 */     synchronized (this) {
/* 128 */       if (!this.bitmap$0) {
/* 130 */         int h = MurmurHash$.MODULE$.startHash(ScalaRunTime$.MODULE$.hash(anchor()));
/* 131 */         h = MurmurHash$.MODULE$.extendHash(h, ScalaRunTime$.MODULE$.hash(path()), MurmurHash$.MODULE$.startMagicA(), MurmurHash$.MODULE$.startMagicB());
/* 132 */         this.hashCode = MurmurHash$.MODULE$.finalizeHash(h);
/*     */         this.bitmap$0 = true;
/*     */       } 
/*     */       return this.hashCode;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     return this.bitmap$0 ? this.hashCode : hashCode$lzycompute();
/*     */   }
/*     */   
/*     */   public static ActorSelection apply(ActorRef paramActorRef, Iterable<String> paramIterable) {
/*     */     return ActorSelection$.MODULE$.apply(paramActorRef, paramIterable);
/*     */   }
/*     */   
/*     */   public static ActorSelection apply(ActorRef paramActorRef, String paramString) {
/*     */     return ActorSelection$.MODULE$.apply(paramActorRef, paramString);
/*     */   }
/*     */   
/*     */   public static ScalaActorSelection toScala(ActorSelection paramActorSelection) {
/*     */     return ActorSelection$.MODULE$.toScala(paramActorSelection);
/*     */   }
/*     */   
/*     */   public abstract ActorRef anchor();
/*     */   
/*     */   public abstract IndexedSeq<SelectionPathElement> path();
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
/* 159 */       String str = x1;
/* 160 */       if (str.isEmpty()) {
/*     */         bool = false;
/*     */       } else {
/* 161 */         bool = true;
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
/*     */   public static class ActorSelection$$anonfun$2 extends AbstractFunction1<SelectionPathElement, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(SelectionPathElement x$1) {
/* 195 */       return x$1.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$rec$1$1 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SelectChildPattern x4$1;
/*     */     
/*     */     public final boolean apply(ActorRef c) {
/* 206 */       return this.x4$1.pattern().matcher(c.path().name()).matches();
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
/* 207 */       c.tell(this.sel$1.msg(), this.sender$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ActorSelection$$anonfun$rec$1$3 extends AbstractFunction1<ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SelectChildPattern x4$1;
/*     */     
/*     */     public final boolean apply(ActorRef c) {
/* 210 */       return this.x4$1.pattern().matcher(c.path().name()).matches();
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
/* 211 */       ActorSelection$.MODULE$.deliverSelection((InternalActorRef)c, this.sender$1, this.m$1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorSelection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */