/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.japi.Util$;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t]a\001B\001\003\005\036\021acQ8og&\034H/\0328u\021\006\034\b.\0338h\017J|W\017\035\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001M)\001\001\003\b\023+A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\013\035\023x.\0369\021\005%\031\022B\001\013\013\005\035\001&o\0343vGR\004\"!\003\f\n\005]Q!\001D*fe&\fG.\033>bE2,\007\002C\r\001\005+\007I\021\t\016\002\013A\fG\017[:\026\003m\0012\001H\021$\033\005i\"B\001\020 \003%IW.\\;uC\ndWM\003\002!\025\005Q1m\0347mK\016$\030n\0348\n\005\tj\"\001C%uKJ\f'\r\\3\021\005\021:cBA\005&\023\t1#\"\001\004Qe\026$WMZ\005\003Q%\022aa\025;sS:<'B\001\024\013\021!Y\003A!E!\002\023Y\022A\0029bi\"\034\b\005\003\005.\001\tU\r\021\"\001/\003I1\030N\035;vC2tu\016Z3t\r\006\034Go\034:\026\003=\002\"!\003\031\n\005ER!aA%oi\"A1\007\001B\tB\003%q&A\nwSJ$X/\0317O_\022,7OR1di>\024\b\005\003\0056\001\tU\r\021\"\0017\003-A\027m\0355NCB\004\030N\\4\026\003]\002\"\001O\036\017\005=I\024B\001\036\003\003]\031uN\\:jgR,g\016\036%bg\"Lgn\032*pkR,'/\003\002={\t)2i\0348tSN$XM\034;ICNDW*\0319qS:<'B\001\036\003\021!y\004A!E!\002\0239\024\001\0045bg\"l\025\r\0359j]\036\004\003\002C!\001\005+\007I\021\t\"\002!I|W\017^3s\t&\034\b/\031;dQ\026\024X#A\022\t\021\021\003!\021#Q\001\n\r\n\021C]8vi\026\024H)[:qCR\034\007.\032:!\021\0251\005\001\"\001H\003\031a\024N\\5u}Q)\001*\023&L\031B\021q\002\001\005\0063\025\003\ra\007\005\b[\025\003\n\0211\0010\021\035)T\t%AA\002]Bq!Q#\021\002\003\0071\005C\003G\001\021\005a\n\006\002I\037\")\001+\024a\001#\00611m\0348gS\036\004\"A\025-\016\003MS!\001\025+\013\005U3\026\001\003;za\026\034\030MZ3\013\003]\0131aY8n\023\tI6K\001\004D_:4\027n\032\005\006\r\002!\ta\027\013\003\021rCQ!\030.A\002y\0131B]8vi\026,\007+\031;igB\031q\fZ\022\016\003\001T!!\0312\002\t1\fgn\032\006\002G\006!!.\031<b\023\t\021\003\rC\003g\001\021\005s-\001\007de\026\fG/\032*pkR,'\017\006\002iWB\021q\"[\005\003U\n\021aAU8vi\026\024\b\"\0027f\001\004i\027AB:zgR,W\016\005\002oc6\tqN\003\002q\t\005)\021m\031;pe&\021!o\034\002\f\003\016$xN]*zgR,W\016C\003u\001\021\005Q/\001\bxSRDG)[:qCR\034\007.\032:\025\005!3\b\"B<t\001\004\031\023\001\0043jgB\fGo\0315fe&#\007\"B=\001\t\003Q\030AF<ji\"4\026N\035;vC2tu\016Z3t\r\006\034Go\034:\025\005![\b\"\002?y\001\004y\023A\002<o_\022,7\017C\003\001\021\005q0\001\bxSRD\007*Y:i\033\006\004\b/\032:\025\007!\013\t\001C\004\002\004u\004\r!!\002\002\r5\f\007\017]3s!\rA\024qA\005\004\003\023i$\001F\"p]NL7\017^3oi\"\0137\017['baB,'\017C\004\002\016\001!\t%a\004\002\031]LG\017\033$bY2\024\027mY6\025\t\005E\021q\003\t\004\037\005M\021bAA\013\005\ta!k\\;uKJ\034uN\0344jO\"A\021\021DA\006\001\004\t\t\"A\003pi\",'\017C\005\002\036\001\t\t\021\"\001\002 \005!1m\0349z)%A\025\021EA\022\003K\t9\003\003\005\032\0037\001\n\0211\001\034\021!i\0231\004I\001\002\004y\003\002C\033\002\034A\005\t\031A\034\t\021\005\013Y\002%AA\002\rB\021\"a\013\001#\003%\t!!\f\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\021\021q\006\026\0047\005E2FAA\032!\021\t)$a\020\016\005\005]\"\002BA\035\003w\t\021\"\0368dQ\026\0347.\0323\013\007\005u\"\"\001\006b]:|G/\031;j_:LA!!\021\0028\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\023\005\025\003!%A\005\002\005\035\023AD2paf$C-\0324bk2$HEM\013\003\003\023R3aLA\031\021%\ti\005AI\001\n\003\ty%\001\bd_BLH\005Z3gCVdG\017J\032\026\005\005E#fA\034\0022!I\021Q\013\001\022\002\023\005\021qK\001\017G>\004\030\020\n3fM\006,H\016\036\0235+\t\tIFK\002$\003cA\021\"!\030\001\003\003%\t%a\030\002\033A\024x\016Z;diB\023XMZ5y+\t\t\t\007E\002`\003GJ!\001\0131\t\021\005\035\004!!A\005\0029\nA\002\035:pIV\034G/\021:jifD\021\"a\033\001\003\003%\t!!\034\002\035A\024x\016Z;di\026cW-\\3oiR!\021qNA;!\rI\021\021O\005\004\003gR!aA!os\"I\021qOA5\003\003\005\raL\001\004q\022\n\004\"CA>\001\005\005I\021IA?\003=\001(o\0343vGRLE/\032:bi>\024XCAA@!\031\t\t)a!\002p5\tq$C\002\002\006~\021\001\"\023;fe\006$xN\035\005\n\003\023\003\021\021!C\001\003\027\013\001bY1o\013F,\030\r\034\013\005\003\033\013\031\nE\002\n\003\037K1!!%\013\005\035\021un\0347fC:D!\"a\036\002\b\006\005\t\031AA8\021%\t9\nAA\001\n\003\nI*\001\005iCND7i\0343f)\005y\003\"CAO\001\005\005I\021IAP\003!!xn\025;sS:<GCAA1\021%\t\031\013AA\001\n\003\n)+\001\004fcV\fGn\035\013\005\003\033\0139\013\003\006\002x\005\005\026\021!a\001\003_BS\001AAV\003c\0032!CAW\023\r\tyK\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\n\003k\023\021\021!E\001\003o\013acQ8og&\034H/\0328u\021\006\034\b.\0338h\017J|W\017\035\t\004\037\005ef\001C\001\003\003\003E\t!a/\024\013\005e\026QX\013\021\023\005}\026QY\0160o\rBUBAAa\025\r\t\031MC\001\beVtG/[7f\023\021\t9-!1\003#\005\0237\017\036:bGR4UO\\2uS>tG\007C\004G\003s#\t!a3\025\005\005]\006BCAO\003s\013\t\021\"\022\002 \"Q\021\021[A]\003\003%\t)a5\002\013\005\004\b\017\\=\025\023!\013).a6\002Z\006m\007BB\r\002P\002\0071\004\003\005.\003\037\004\n\0211\0010\021!)\024q\032I\001\002\0049\004\002C!\002PB\005\t\031A\022\t\025\005}\027\021XA\001\n\003\013\t/A\004v]\006\004\b\017\\=\025\t\005\r\030q\036\t\006\023\005\025\030\021^\005\004\003OT!AB(qi&|g\016E\004\n\003W\\rfN\022\n\007\0055(B\001\004UkBdW\r\016\005\n\003c\fi.!AA\002!\0131\001\037\0231\021)\t)0!/\022\002\023\005\021qI\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\032\t\025\005e\030\021XI\001\n\003\ty%A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$He\r\005\013\003{\fI,%A\005\002\005]\023a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$C\007\003\006\003\002\005e\026\023!C\001\003\017\nq\"\0319qYf$C-\0324bk2$HE\r\005\013\005\013\tI,%A\005\002\005=\023aD1qa2LH\005Z3gCVdG\017J\032\t\025\t%\021\021XI\001\n\003\t9&A\bbaBd\027\020\n3fM\006,H\016\036\0235\021)\021i!!/\002\002\023%!qB\001\fe\026\fGMU3t_24X\r\006\002\003\022A\031qLa\005\n\007\tU\001M\001\004PE*,7\r\036")
/*     */ public final class ConsistentHashingGroup implements Group, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Iterable<String> paths;
/*     */   
/*     */   private final int virtualNodesFactor;
/*     */   
/*     */   private final PartialFunction<Object, Object> hashMapping;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   public Props props() {
/* 361 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 361 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 361 */     return Group$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 361 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 361 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 361 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 361 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup copy(Iterable<String> paths, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping, String routerDispatcher) {
/* 361 */     return new ConsistentHashingGroup(
/* 362 */         paths, 
/* 363 */         virtualNodesFactor, 
/* 364 */         hashMapping, 
/* 365 */         routerDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ConsistentHashingGroup";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 4;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return paths();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof ConsistentHashingGroup;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, Statics.anyHash(paths()));
/*     */     i = Statics.mix(i, virtualNodesFactor());
/*     */     i = Statics.mix(i, Statics.anyHash(hashMapping()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     return Statics.finalizeHash(i, 4);
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
/*     */     //   2: if_acmpeq -> 147
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ConsistentHashingGroup
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 151
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ConsistentHashingGroup
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual paths : ()Lscala/collection/immutable/Iterable;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual paths : ()Lscala/collection/immutable/Iterable;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 143
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 143
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual virtualNodesFactor : ()I
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual virtualNodesFactor : ()I
/*     */     //   72: if_icmpne -> 143
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
/*     */     //   96: goto -> 143
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 143
/*     */     //   107: aload_0
/*     */     //   108: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   111: aload #4
/*     */     //   113: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   116: astore #7
/*     */     //   118: dup
/*     */     //   119: ifnonnull -> 131
/*     */     //   122: pop
/*     */     //   123: aload #7
/*     */     //   125: ifnull -> 139
/*     */     //   128: goto -> 143
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 143
/*     */     //   139: iconst_1
/*     */     //   140: goto -> 144
/*     */     //   143: iconst_0
/*     */     //   144: ifeq -> 151
/*     */     //   147: iconst_1
/*     */     //   148: goto -> 152
/*     */     //   151: iconst_0
/*     */     //   152: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #361	-> 0
/*     */     //   #63	-> 14
/*     */     //   #361	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	153	0	this	Lakka/routing/ConsistentHashingGroup;
/*     */     //   0	153	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/*     */     return this.paths;
/*     */   }
/*     */   
/*     */   public Iterable<String> copy$default$1() {
/*     */     return paths();
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup(Iterable<String> paths, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping, String routerDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Product.class.$init$(this);
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
/*     */   public String routerDispatcher() {
/* 365 */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$4() {
/* 365 */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup(Config config) {
/* 369 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(config.getStringList("routees.paths")), ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$2(), ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$3(), ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$4());
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup(Iterable routeePaths) {
/* 376 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(routeePaths), ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$2(), ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$3(), ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$4());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 379 */     return new Router(new ConsistentHashingRoutingLogic(system, virtualNodesFactor(), hashMapping()));
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup withDispatcher(String dispatcherId) {
/* 385 */     String x$73 = dispatcherId;
/* 385 */     Iterable<String> x$74 = copy$default$1();
/* 385 */     int x$75 = copy$default$2();
/* 385 */     PartialFunction<Object, Object> x$76 = copy$default$3();
/* 385 */     return copy(x$74, x$75, x$76, x$73);
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup withVirtualNodesFactor(int vnodes) {
/* 390 */     int x$77 = vnodes;
/* 390 */     Iterable<String> x$78 = copy$default$1();
/* 390 */     PartialFunction<Object, Object> x$79 = copy$default$3();
/* 390 */     String x$80 = copy$default$4();
/* 390 */     return copy(x$78, x$77, x$79, x$80);
/*     */   }
/*     */   
/*     */   public ConsistentHashingGroup withHashMapper(ConsistentHashingRouter.ConsistentHashMapper mapper) {
/* 396 */     PartialFunction<Object, Object> x$81 = ConsistentHashingRouter$.MODULE$.hashMappingAdapter(mapper);
/* 396 */     Iterable<String> x$82 = copy$default$1();
/* 396 */     int x$83 = copy$default$2();
/* 396 */     String x$84 = copy$default$4();
/* 396 */     return copy(x$82, x$83, x$81, x$84);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/*     */     boolean bool;
/* 401 */     RouterConfig routerConfig = other;
/* 402 */     if (routerConfig instanceof FromConfig) {
/* 402 */       bool = true;
/* 402 */     } else if (routerConfig instanceof NoRouter) {
/* 402 */       bool = true;
/*     */     } else {
/* 402 */       bool = false;
/*     */     } 
/* 402 */     if (bool) {
/* 402 */       RouterConfig routerConfig1 = RouterConfig$class.withFallback(this, other);
/*     */     } else {
/* 403 */       if (routerConfig instanceof ConsistentHashingGroup) {
/* 403 */         ConsistentHashingGroup consistentHashingGroup = (ConsistentHashingGroup)routerConfig;
/* 403 */         PartialFunction<Object, Object> x$85 = consistentHashingGroup.hashMapping();
/* 403 */         Iterable<String> x$86 = copy$default$1();
/* 403 */         int x$87 = copy$default$2();
/* 403 */         String x$88 = copy$default$4();
/* 403 */         return copy(x$86, x$87, x$85, x$88);
/*     */       } 
/* 404 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("Expected ConsistentHashingGroup, got [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { other })));
/*     */     } 
/*     */     return (RouterConfig)SYNTHETIC_LOCAL_VARIABLE_4;
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return ConsistentHashingGroup$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> apply$default$3() {
/*     */     return ConsistentHashingGroup$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static int apply$default$2() {
/*     */     return ConsistentHashingGroup$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> $lessinit$greater$default$3() {
/*     */     return ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$2() {
/*     */     return ConsistentHashingGroup$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple4<Iterable<String>, Object, PartialFunction<Object, Object>, String>, ConsistentHashingGroup> tupled() {
/*     */     return ConsistentHashingGroup$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Iterable<String>, Function1<Object, Function1<PartialFunction<Object, Object>, Function1<String, ConsistentHashingGroup>>>> curried() {
/*     */     return ConsistentHashingGroup$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */