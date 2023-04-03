/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Address;
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import akka.event.LogSource$;
/*     */ import akka.event.Logging$;
/*     */ import akka.event.LoggingAdapter;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t-q!B\001\003\021\0039\021!H\"p]NL7\017^3oi\"\0137\017[5oOJ{W\017^5oO2{w-[2\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021QdQ8og&\034H/\0328u\021\006\034\b.\0338h%>,H/\0338h\031><\027nY\n\004\0231\021\002CA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!AB!osJ+g\r\005\002\016'%\021AC\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\006-%!\taF\001\007y%t\027\016\036 \025\003\035AQ!G\005\005\002i\ta\002Z3gCVdG/\0213ee\026\0348\017\006\002\034CA\021AdH\007\002;)\021a\004B\001\006C\016$xN]\005\003Au\021q!\0213ee\026\0348\017C\003#1\001\0071%\001\004tsN$X-\034\t\0039\021J!!J\017\003\027\005\033Go\034:TsN$X-\034\005\bO%\t\t\021\"!)\003\025\t\007\017\0357z)\035I\0231[Ak\003/\004\"\001\003\026\007\t)\021!iK\n\006U1asF\005\t\003\0215J!A\f\002\003\031I{W\017^5oO2{w-[2\021\0055\001\024BA\031\017\005\035\001&o\0343vGRD\001B\t\026\003\026\004%\taM\013\002G!AQG\013B\tB\003%1%A\004tsN$X-\034\021\t\021]R#Q3A\005\002a\n!C^5siV\fGNT8eKN4\025m\031;peV\t\021\b\005\002\016u%\0211H\004\002\004\023:$\b\002C\037+\005#\005\013\021B\035\002'YL'\017^;bY:{G-Z:GC\016$xN\035\021\t\021}R#Q3A\005\002\001\0131\002[1tQ6\013\007\017]5oOV\t\021\t\005\002C\013:\021\001bQ\005\003\t\n\tqcQ8og&\034H/\0328u\021\006\034\b.\0338h%>,H/\032:\n\005\031;%!F\"p]NL7\017^3oi\"\0137\017['baBLgn\032\006\003\t\nA\001\"\023\026\003\022\003\006I!Q\001\rQ\006\034\b.T1qa&tw\r\t\005\006-)\"\ta\023\013\005S1ke\nC\003#\025\002\0071\005C\0048\025B\005\t\031A\035\t\017}R\005\023!a\001\003\")aC\013C\001!R\021\021&\025\005\006E=\003\ra\t\005\b'*\022\r\021\"\003U\003-\031X\r\0344BI\022\024Xm]:\026\003mAaA\026\026!\002\023Y\022\001D:fY\032\fE\r\032:fgN\004\003b\002-+\005\004%\t\001O\001\007m:|G-Z:\t\riS\003\025!\003:\003\0351hn\0343fg\002B\001\002\030\026\t\006\004%I!X\001\004Y><W#\0010\021\005}\023W\"\0011\013\005\005$\021!B3wK:$\030BA2a\0059aunZ4j]\036\fE-\0319uKJD\001\"\032\026\t\002\003\006KAX\001\005Y><\007\005C\003hU\021\005\001.\001\fxSRDg+\033:uk\006dgj\0343fg\032\0137\r^8s)\tI\023\016C\003YM\002\007\021\bC\003lU\021\005A.\001\bxSRD\007*Y:i\033\006\004\b/\032:\025\005%j\007\"\0028k\001\004y\027AB7baB,'\017\005\002Ca&\021\021o\022\002\025\007>t7/[:uK:$\b*Y:i\033\006\004\b/\032:\t\017MT#\031!C\005i\006\t2m\0348tSN$XM\034;ICND'+\0324\026\003U\004BA^@\002\0045\tqO\003\002ys\0061\021\r^8nS\016T!A_>\002\025\r|gnY;se\026tGO\003\002}{\006!Q\017^5m\025\005q\030\001\0026bm\006L1!!\001x\005=\tEo\\7jGJ+g-\032:f]\016,\007cB\007\002\006\005%\021qD\005\004\003\017q!A\002+va2,'\007\005\004\002\f\005U\021\021D\007\003\003\033QA!a\004\002\022\005I\021.\\7vi\006\024G.\032\006\004\003'q\021AC2pY2,7\r^5p]&!\021qCA\007\005)Ie\016Z3yK\022\034V-\035\t\004\021\005m\021bAA\017\005\t1!k\\;uK\026\004R\001CA\021\003KI1!a\t\003\0059\031uN\\:jgR,g\016\036%bg\"\0042\001CA\024\023\r\tIC\001\002\021\007>t7/[:uK:$(k\\;uK\026Dq!!\f+A\003%Q/\001\nd_:\034\030n\035;f]RD\025m\0355SK\032\004\003bBA\031U\021\005\0231G\001\007g\026dWm\031;\025\r\005e\021QGA \021!\t9$a\fA\002\005e\022aB7fgN\fw-\032\t\004\033\005m\022bAA\037\035\t\031\021I\\=\t\021\005\005\023q\006a\001\003\023\tqA]8vi\026,7\017C\005\002F)\n\t\021\"\001\002H\005!1m\0349z)\035I\023\021JA&\003\033B\001BIA\"!\003\005\ra\t\005\to\005\r\003\023!a\001s!Aq(a\021\021\002\003\007\021\tC\005\002R)\n\n\021\"\001\002T\005q1m\0349zI\021,g-Y;mi\022\nTCAA+U\r\031\023qK\026\003\0033\002B!a\027\002f5\021\021Q\f\006\005\003?\n\t'A\005v]\016DWmY6fI*\031\0211\r\b\002\025\005tgn\034;bi&|g.\003\003\002h\005u#!E;oG\",7m[3e-\006\024\030.\0318dK\"I\0211\016\026\022\002\023\005\021QN\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\t\tyGK\002:\003/B\021\"a\035+#\003%\t!!\036\002\035\r|\007/\037\023eK\032\fW\017\034;%gU\021\021q\017\026\004\003\006]\003\"CA>U\005\005I\021IA?\0035\001(o\0343vGR\004&/\0324jqV\021\021q\020\t\005\003\003\0139)\004\002\002\004*\031\021QQ?\002\t1\fgnZ\005\005\003\023\013\031I\001\004TiJLgn\032\005\t\003\033S\023\021!C\001q\005a\001O]8ek\016$\030I]5us\"I\021\021\023\026\002\002\023\005\0211S\001\017aJ|G-^2u\0132,W.\0328u)\021\tI$!&\t\023\005]\025qRA\001\002\004I\024a\001=%c!I\0211\024\026\002\002\023\005\023QT\001\020aJ|G-^2u\023R,'/\031;peV\021\021q\024\t\007\003C\013\031+!\017\016\005\005E\021\002BAS\003#\021\001\"\023;fe\006$xN\035\005\n\003SS\023\021!C\001\003W\013\001bY1o\013F,\030\r\034\013\005\003[\013\031\fE\002\016\003_K1!!-\017\005\035\021un\0347fC:D!\"a&\002(\006\005\t\031AA\035\021%\t9LKA\001\n\003\nI,\001\005iCND7i\0343f)\005I\004\"CA_U\005\005I\021IA`\003!!xn\025;sS:<GCAA@\021%\t\031MKA\001\n\003\n)-\001\004fcV\fGn\035\013\005\003[\0139\r\003\006\002\030\006\005\027\021!a\001\003sASAKAf\003#\0042!DAg\023\r\tyM\004\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!\001\005\006E\031\002\ra\t\005\bo\031\002\n\0211\001:\021\035yd\005%AA\002\005C\021\"a7\n\003\003%\t)!8\002\017Ut\027\r\0359msR!\021q\\Av!\025i\021\021]As\023\r\t\031O\004\002\007\037B$\030n\0348\021\r5\t9oI\035B\023\r\tIO\004\002\007)V\004H.Z\032\t\023\0055\030\021\\A\001\002\004I\023a\001=%a!I\021\021_\005\022\002\023\005\021QN\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\032\t\023\005U\030\"%A\005\002\005U\024a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$3\007C\005\002z&\t\n\021\"\001\002n\005y\021\r\0359ms\022\"WMZ1vYR$#\007C\005\002~&\t\n\021\"\001\002v\005y\021\r\0359ms\022\"WMZ1vYR$3\007C\005\003\002%\t\t\021\"\003\003\004\005Y!/Z1e%\026\034x\016\034<f)\t\021)\001\005\003\002\002\n\035\021\002\002B\005\003\007\023aa\0242kK\016$\b")
/*     */ public final class ConsistentHashingRoutingLogic implements RoutingLogic, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorSystem system;
/*     */   
/*     */   private final int virtualNodesFactor;
/*     */   
/*     */   private final PartialFunction<Object, Object> hashMapping;
/*     */   
/*     */   private final Address akka$routing$ConsistentHashingRoutingLogic$$selfAddress;
/*     */   
/*     */   private final int vnodes;
/*     */   
/*     */   private LoggingAdapter log;
/*     */   
/*     */   private final AtomicReference<Tuple2<IndexedSeq<Routee>, ConsistentHash<ConsistentRoutee>>> consistentHashRef;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   public ConsistentHashingRoutingLogic copy(ActorSystem system, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping) {
/* 153 */     return new ConsistentHashingRoutingLogic(
/* 154 */         system, 
/* 155 */         virtualNodesFactor, 
/* 156 */         hashMapping);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ConsistentHashingRoutingLogic";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return system();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof ConsistentHashingRoutingLogic;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, Statics.anyHash(system()));
/*     */     i = Statics.mix(i, virtualNodesFactor());
/*     */     i = Statics.mix(i, Statics.anyHash(hashMapping()));
/*     */     return Statics.finalizeHash(i, 3);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 115
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ConsistentHashingRoutingLogic
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 119
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ConsistentHashingRoutingLogic
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual system : ()Lakka/actor/ActorSystem;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual system : ()Lakka/actor/ActorSystem;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 111
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 111
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual virtualNodesFactor : ()I
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual virtualNodesFactor : ()I
/*     */     //   72: if_icmpne -> 111
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual hashMapping : ()Lscala/PartialFunction;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual hashMapping : ()Lscala/PartialFunction;
/*     */     //   84: astore #6
/*     */     //   86: dup
/*     */     //   87: ifnonnull -> 99
/*     */     //   90: pop
/*     */     //   91: aload #6
/*     */     //   93: ifnull -> 107
/*     */     //   96: goto -> 111
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 111
/*     */     //   107: iconst_1
/*     */     //   108: goto -> 112
/*     */     //   111: iconst_0
/*     */     //   112: ifeq -> 119
/*     */     //   115: iconst_1
/*     */     //   116: goto -> 120
/*     */     //   119: iconst_0
/*     */     //   120: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #153	-> 0
/*     */     //   #63	-> 14
/*     */     //   #153	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	121	0	this	Lakka/routing/ConsistentHashingRoutingLogic;
/*     */     //   0	121	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ActorSystem system() {
/*     */     return this.system;
/*     */   }
/*     */   
/*     */   public ActorSystem copy$default$1() {
/*     */     return system();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRoutingLogic(ActorSystem system, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping) {
/*     */     Product.class.$init$(this);
/* 168 */     this.akka$routing$ConsistentHashingRoutingLogic$$selfAddress = ((ExtendedActorSystem)system).provider().getDefaultAddress();
/* 169 */     this.vnodes = 
/* 170 */       (virtualNodesFactor == 0) ? system.settings().DefaultVirtualNodesFactor() : 
/* 171 */       virtualNodesFactor;
/* 187 */     null;
/* 187 */     null;
/* 187 */     this.consistentHashRef = new AtomicReference<Tuple2<IndexedSeq<Routee>, ConsistentHash<ConsistentRoutee>>>(new Tuple2(null, null));
/*     */   }
/*     */   
/*     */   public int virtualNodesFactor() {
/*     */     return this.virtualNodesFactor;
/*     */   }
/*     */   
/*     */   public int copy$default$2() {
/*     */     return virtualNodesFactor();
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> hashMapping() {
/*     */     return this.hashMapping;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> copy$default$3() {
/*     */     return hashMapping();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRoutingLogic(ActorSystem system) {
/*     */     this(system, 0, ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$);
/*     */   }
/*     */   
/*     */   public Address akka$routing$ConsistentHashingRoutingLogic$$selfAddress() {
/*     */     return this.akka$routing$ConsistentHashingRoutingLogic$$selfAddress;
/*     */   }
/*     */   
/*     */   public int vnodes() {
/*     */     return this.vnodes;
/*     */   }
/*     */   
/*     */   private LoggingAdapter log$lzycompute() {
/*     */     synchronized (this) {
/*     */       if (!this.bitmap$0) {
/*     */         this.log = Logging$.MODULE$.apply(system(), getClass(), LogSource$.MODULE$.fromAnyClass());
/*     */         this.bitmap$0 = true;
/*     */       } 
/*     */       return this.log;
/*     */     } 
/*     */   }
/*     */   
/*     */   private LoggingAdapter log() {
/*     */     return this.bitmap$0 ? this.log : log$lzycompute();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRoutingLogic withVirtualNodesFactor(int vnodes) {
/*     */     int x$11 = vnodes;
/*     */     ActorSystem x$12 = copy$default$1();
/*     */     PartialFunction<Object, Object> x$13 = copy$default$3();
/*     */     return copy(x$12, x$11, x$13);
/*     */   }
/*     */   
/*     */   public ConsistentHashingRoutingLogic withHashMapper(ConsistentHashingRouter.ConsistentHashMapper mapper) {
/*     */     PartialFunction<Object, Object> x$14 = ConsistentHashingRouter$.MODULE$.hashMappingAdapter(mapper);
/*     */     ActorSystem x$15 = copy$default$1();
/*     */     int x$16 = copy$default$2();
/*     */     return copy(x$15, x$16, x$14);
/*     */   }
/*     */   
/*     */   private AtomicReference<Tuple2<IndexedSeq<Routee>, ConsistentHash<ConsistentRoutee>>> consistentHashRef() {
/* 187 */     return this.consistentHashRef;
/*     */   }
/*     */   
/*     */   public Routee select(Object message, IndexedSeq routees) {
/*     */     Routee routee;
/* 225 */     Object object = message;
/* 226 */     if (hashMapping().isDefinedAt(message)) {
/* 226 */       routee = target$1(hashMapping().apply(message), routees);
/* 227 */     } else if (object instanceof ConsistentHashingRouter.ConsistentHashable) {
/* 227 */       ConsistentHashingRouter.ConsistentHashable consistentHashable = (ConsistentHashingRouter.ConsistentHashable)object;
/* 227 */       routee = target$1(consistentHashable.consistentHashKey(), routees);
/*     */     } else {
/* 229 */       log().warning("Message [{}] must be handled by hashMapping, or implement [{}] or be wrapped in [{}]", 
/* 230 */           message.getClass().getName(), ConsistentHashingRouter.ConsistentHashable.class.getName(), 
/* 231 */           ConsistentHashingRouter.ConsistentHashableEnvelope.class.getName());
/* 232 */       routee = NoRoutee$.MODULE$;
/*     */     } 
/*     */     return routees.isEmpty() ? NoRoutee$.MODULE$ : routee;
/*     */   }
/*     */   
/*     */   private final ConsistentHash updateConsistentHash$1(IndexedSeq routees$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial consistentHashRef : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   4: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   7: checkcast scala/Tuple2
/*     */     //   10: astore_2
/*     */     //   11: aload_2
/*     */     //   12: astore #4
/*     */     //   14: aload #4
/*     */     //   16: ifnull -> 181
/*     */     //   19: aload #4
/*     */     //   21: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   24: checkcast scala/collection/immutable/IndexedSeq
/*     */     //   27: astore #5
/*     */     //   29: aload #4
/*     */     //   31: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   34: checkcast akka/routing/ConsistentHash
/*     */     //   37: astore #6
/*     */     //   39: new scala/Tuple2
/*     */     //   42: dup
/*     */     //   43: aload #5
/*     */     //   45: aload #6
/*     */     //   47: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   50: astore #7
/*     */     //   52: aload #7
/*     */     //   54: astore_3
/*     */     //   55: aload_3
/*     */     //   56: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   59: checkcast scala/collection/immutable/IndexedSeq
/*     */     //   62: astore #8
/*     */     //   64: aload_3
/*     */     //   65: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   68: checkcast akka/routing/ConsistentHash
/*     */     //   71: astore #9
/*     */     //   73: aload_1
/*     */     //   74: aload #8
/*     */     //   76: if_acmpeq -> 178
/*     */     //   79: aload_1
/*     */     //   80: aload #8
/*     */     //   82: astore #11
/*     */     //   84: dup
/*     */     //   85: ifnonnull -> 97
/*     */     //   88: pop
/*     */     //   89: aload #11
/*     */     //   91: ifnull -> 105
/*     */     //   94: goto -> 110
/*     */     //   97: aload #11
/*     */     //   99: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   102: ifeq -> 110
/*     */     //   105: aload #9
/*     */     //   107: goto -> 152
/*     */     //   110: getstatic akka/routing/ConsistentHash$.MODULE$ : Lakka/routing/ConsistentHash$;
/*     */     //   113: aload_1
/*     */     //   114: new akka/routing/ConsistentHashingRoutingLogic$$anonfun$2
/*     */     //   117: dup
/*     */     //   118: aload_0
/*     */     //   119: invokespecial <init> : (Lakka/routing/ConsistentHashingRoutingLogic;)V
/*     */     //   122: getstatic scala/collection/immutable/IndexedSeq$.MODULE$ : Lscala/collection/immutable/IndexedSeq$;
/*     */     //   125: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   128: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   133: checkcast scala/collection/Iterable
/*     */     //   136: aload_0
/*     */     //   137: invokevirtual vnodes : ()I
/*     */     //   140: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   143: ldc_w akka/routing/ConsistentRoutee
/*     */     //   146: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   149: invokevirtual apply : (Lscala/collection/Iterable;ILscala/reflect/ClassTag;)Lakka/routing/ConsistentHash;
/*     */     //   152: astore #10
/*     */     //   154: aload_0
/*     */     //   155: invokespecial consistentHashRef : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   158: aload_2
/*     */     //   159: new scala/Tuple2
/*     */     //   162: dup
/*     */     //   163: aload_1
/*     */     //   164: aload #10
/*     */     //   166: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   169: invokevirtual compareAndSet : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   172: pop
/*     */     //   173: aload #10
/*     */     //   175: goto -> 180
/*     */     //   178: aload #9
/*     */     //   180: areturn
/*     */     //   181: new scala/MatchError
/*     */     //   184: dup
/*     */     //   185: aload #4
/*     */     //   187: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   190: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #196	-> 0
/*     */     //   #197	-> 11
/*     */     //   #199	-> 73
/*     */     //   #202	-> 79
/*     */     //   #203	-> 110
/*     */     //   #201	-> 152
/*     */     //   #205	-> 154
/*     */     //   #206	-> 173
/*     */     //   #207	-> 178
/*     */     //   #195	-> 180
/*     */     //   #197	-> 181
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	191	0	this	Lakka/routing/ConsistentHashingRoutingLogic;
/*     */     //   0	191	1	routees$1	Lscala/collection/immutable/IndexedSeq;
/*     */     //   11	180	2	oldConsistentHashTuple	Lscala/Tuple2;
/*     */     //   29	162	5	oldRoutees	Lscala/collection/immutable/IndexedSeq;
/*     */     //   39	152	6	oldConsistentHash	Lakka/routing/ConsistentHash;
/*     */     //   64	127	8	oldRoutees	Lscala/collection/immutable/IndexedSeq;
/*     */     //   73	118	9	oldConsistentHash	Lakka/routing/ConsistentHash;
/*     */     //   154	21	10	consistentHash	Lakka/routing/ConsistentHash;
/*     */   }
/*     */   
/*     */   public class ConsistentHashingRoutingLogic$$anonfun$2 extends AbstractFunction1<Routee, ConsistentRoutee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ConsistentRoutee apply(Routee x$3) {
/*     */       return new ConsistentRoutee(x$3, this.$outer.akka$routing$ConsistentHashingRoutingLogic$$selfAddress());
/*     */     }
/*     */     
/*     */     public ConsistentHashingRoutingLogic$$anonfun$2(ConsistentHashingRoutingLogic $outer) {}
/*     */   }
/*     */   
/*     */   private final Routee target$1(Object hashData, IndexedSeq routees$1) {
/*     */     try {
/*     */       ConsistentHash<ConsistentRoutee> currentConsistenHash;
/*     */       Routee routee;
/*     */     } finally {
/*     */       NoRoutee$ noRoutee$;
/*     */       Exception exception1 = null, exception2 = exception1;
/*     */       Option option = NonFatal$.MODULE$.unapply(exception2);
/*     */       if (option.isEmpty())
/*     */         throw exception1; 
/*     */       Throwable e = (Throwable)option.get();
/*     */       log().warning("Couldn't route message with consistent hash key [{}] due to [{}]", hashData, e.getMessage());
/*     */     } 
/*     */     return noRoutee$;
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> apply$default$3() {
/*     */     return ConsistentHashingRoutingLogic$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static int apply$default$2() {
/*     */     return ConsistentHashingRoutingLogic$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> $lessinit$greater$default$3() {
/*     */     return ConsistentHashingRoutingLogic$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$2() {
/*     */     return ConsistentHashingRoutingLogic$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Address defaultAddress(ActorSystem paramActorSystem) {
/*     */     return ConsistentHashingRoutingLogic$.MODULE$.defaultAddress(paramActorSystem);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */