/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple5;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\025b\001B\001\003\005\036\021aBU8v]\022\024vNY5o!>|GN\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\031\001\001B\004\n\0273A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\tA{w\016\034\t\004\037M)\022B\001\013\003\005]\001vn\0347Pm\026\024(/\0333f+:\034X\r^\"p]\032Lw\r\005\002\020\001A\021\021bF\005\0031)\021q\001\025:pIV\034G\017\005\002\n5%\0211D\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t;\001\021)\032!C!=\005iaN](g\023:\034H/\0318dKN,\022a\b\t\003\023\001J!!\t\006\003\007%sG\017\003\005$\001\tE\t\025!\003 \0039q'o\0244J]N$\030M\\2fg\002B\001\"\n\001\003\026\004%\tEJ\001\be\026\034\030N_3s+\0059\003cA\005)U%\021\021F\003\002\007\037B$\030n\0348\021\005=Y\023B\001\027\003\005\035\021Vm]5{KJD\001B\f\001\003\022\003\006IaJ\001\te\026\034\030N_3sA!A\001\007\001BK\002\023\005\023'\001\ntkB,'O^5t_J\034FO]1uK\036LX#\001\032\021\005M2T\"\001\033\013\005U\"\021!B1di>\024\030BA\0345\005I\031V\017]3sm&\034xN]*ue\006$XmZ=\t\021e\002!\021#Q\001\nI\n1c];qKJ4\030n]8s'R\024\030\r^3hs\002B\001b\017\001\003\026\004%\t\005P\001\021e>,H/\032:ESN\004\030\r^2iKJ,\022!\020\t\003}\005s!!C \n\005\001S\021A\002)sK\022,g-\003\002C\007\n11\013\036:j]\036T!\001\021\006\t\021\025\003!\021#Q\001\nu\n\021C]8vi\026\024H)[:qCR\034\007.\032:!\021!9\005A!f\001\n\003B\025!E;tKB{w\016\034#jgB\fGo\0315feV\t\021\n\005\002\n\025&\0211J\003\002\b\005>|G.Z1o\021!i\005A!E!\002\023I\025AE;tKB{w\016\034#jgB\fGo\0315fe\002BQa\024\001\005\002A\013a\001P5oSRtDCB\013R%N#V\013C\003\036\035\002\007q\004C\004&\035B\005\t\031A\024\t\017Ar\005\023!a\001e!91H\024I\001\002\004i\004bB$O!\003\005\r!\023\005\006\037\002!\ta\026\013\003+aCQ!\027,A\002i\013aaY8oM&<\007CA.b\033\005a&BA-^\025\tqv,\001\005usB,7/\0314f\025\005\001\027aA2p[&\021!\r\030\002\007\007>tg-[4\t\013=\003A\021\0013\025\005U)\007\"\0024d\001\004y\022A\0018s\021\025A\007\001\"\021j\0031\031'/Z1uKJ{W\017^3s)\tQW\016\005\002\020W&\021AN\001\002\007%>,H/\032:\t\0139<\007\031A8\002\rML8\017^3n!\t\031\004/\003\002ri\tY\021i\031;peNK8\017^3n\021\025\031\b\001\"\001u\003Y9\030\016\0365TkB,'O^5t_J\034FO]1uK\036LHCA\013v\021\0251(\0171\0013\003!\031HO]1uK\036L\b\"\002=\001\t\003I\030aC<ji\"\024Vm]5{KJ$\"!\006>\t\013\025:\b\031\001\026\t\013q\004A\021A?\002\035]LG\017\033#jgB\fGo\0315feR\021QC \005\006n\004\r!P\001\rI&\034\b/\031;dQ\026\024\030\n\032\005\b\003\007\001A\021IA\003\00319\030\016\0365GC2d'-Y2l)\021\t9!!\004\021\007=\tI!C\002\002\f\t\021ABU8vi\026\0248i\0348gS\036D\001\"a\004\002\002\001\007\021qA\001\006_RDWM\035\005\n\003'\001\021\021!C\001\003+\tAaY8qsRYQ#a\006\002\032\005m\021QDA\020\021!i\022\021\003I\001\002\004y\002\002C\023\002\022A\005\t\031A\024\t\021A\n\t\002%AA\002IB\001bOA\t!\003\005\r!\020\005\t\017\006E\001\023!a\001\023\"I\0211\005\001\022\002\023\005\021QE\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\t\t9CK\002 \003SY#!a\013\021\t\0055\022qG\007\003\003_QA!!\r\0024\005IQO\\2iK\016\\W\r\032\006\004\003kQ\021AC1o]>$\030\r^5p]&!\021\021HA\030\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\003{\001\021\023!C\001\003\tabY8qs\022\"WMZ1vYR$#'\006\002\002B)\032q%!\013\t\023\005\025\003!%A\005\002\005\035\023AD2paf$C-\0324bk2$HeM\013\003\003\023R3AMA\025\021%\ti\005AI\001\n\003\ty%\001\bd_BLH\005Z3gCVdG\017\n\033\026\005\005E#fA\037\002*!I\021Q\013\001\022\002\023\005\021qK\001\017G>\004\030\020\n3fM\006,H\016\036\0236+\t\tIFK\002J\003SA\021\"!\030\001\003\003%\t%a\030\002\033A\024x\016Z;diB\023XMZ5y+\t\t\t\007\005\003\002d\0055TBAA3\025\021\t9'!\033\002\t1\fgn\032\006\003\003W\nAA[1wC&\031!)!\032\t\021\005E\004!!A\005\002y\tA\002\035:pIV\034G/\021:jifD\021\"!\036\001\003\003%\t!a\036\002\035A\024x\016Z;di\026cW-\\3oiR!\021\021PA@!\rI\0211P\005\004\003{R!aA!os\"I\021\021QA:\003\003\005\raH\001\004q\022\n\004\"CAC\001\005\005I\021IAD\003=\001(o\0343vGRLE/\032:bi>\024XCAAE!\031\tY)!%\002z5\021\021Q\022\006\004\003\037S\021AC2pY2,7\r^5p]&!\0211SAG\005!IE/\032:bi>\024\b\"CAL\001\005\005I\021AAM\003!\031\027M\\#rk\006dGcA%\002\034\"Q\021\021QAK\003\003\005\r!!\037\t\023\005}\005!!A\005B\005\005\026\001\0035bg\"\034u\016Z3\025\003}A\021\"!*\001\003\003%\t%a*\002\021Q|7\013\036:j]\036$\"!!\031\t\023\005-\006!!A\005B\0055\026AB3rk\006d7\017F\002J\003_C!\"!!\002*\006\005\t\031AA=Q\025\001\0211WA]!\rI\021QW\005\004\003oS!\001E*fe&\fGNV3sg&|g.V%E=\005\tq!CA_\005\005\005\t\022AA`\0039\021v.\0368e%>\024\027N\034)p_2\0042aDAa\r!\t!!!A\t\002\005\r7#BAa\003\013L\002CCAd\003\033|rEM\037J+5\021\021\021\032\006\004\003\027T\021a\002:v]RLW.Z\005\005\003\037\fIMA\tBEN$(/Y2u\rVt7\r^5p]VBqaTAa\t\003\t\031\016\006\002\002@\"Q\021QUAa\003\003%)%a*\t\025\005e\027\021YA\001\n\003\013Y.A\003baBd\027\020F\006\026\003;\fy.!9\002d\006\025\bBB\017\002X\002\007q\004\003\005&\003/\004\n\0211\001(\021!\001\024q\033I\001\002\004\021\004\002C\036\002XB\005\t\031A\037\t\021\035\0139\016%AA\002%C!\"!;\002B\006\005I\021QAv\003\035)h.\0319qYf$B!!<\002vB!\021\002KAx!!I\021\021_\020(euJ\025bAAz\025\t1A+\0369mKVB\021\"a>\002h\006\005\t\031A\013\002\007a$\003\007\003\006\002|\006\005\027\023!C\001\003\t1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004BCA\000\003\003\f\n\021\"\001\002H\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIMB!Ba\001\002BF\005I\021AA(\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%i!Q!qAAa#\003%\t!a\026\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0236\021)\021Y!!1\022\002\023\005\021qH\001\020CB\004H.\037\023eK\032\fW\017\034;%e!Q!qBAa#\003%\t!a\022\002\037\005\004\b\017\\=%I\0264\027-\0367uIMB!Ba\005\002BF\005I\021AA(\003=\t\007\017\0357zI\021,g-Y;mi\022\"\004B\003B\f\003\003\f\n\021\"\001\002X\005y\021\r\0359ms\022\"WMZ1vYR$S\007\003\006\003\034\005\005\027\021!C\005\005;\t1B]3bIJ+7o\0347wKR\021!q\004\t\005\003G\022\t#\003\003\003$\005\025$AB(cU\026\034G\017")
/*     */ public final class RoundRobinPool implements Pool, PoolOverrideUnsetConfig<RoundRobinPool>, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final boolean usePoolDispatcher;
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/*  66 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/*  66 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/*  66 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/*  66 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/*  66 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/*  66 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/*  66 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/*  66 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/*  66 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public RoundRobinPool copy(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*  66 */     return new RoundRobinPool(
/*  67 */         nrOfInstances, resizer, 
/*  68 */         supervisorStrategy, 
/*  69 */         routerDispatcher, 
/*  70 */         usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "RoundRobinPool";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 5;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 4:
/*     */       
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return BoxesRunTime.boxToInteger(nrOfInstances());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof RoundRobinPool;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(resizer()));
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     i = Statics.mix(i, usePoolDispatcher() ? 1231 : 1237);
/*     */     return Statics.finalizeHash(i, 5);
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
/*     */     //   2: if_acmpeq -> 159
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/RoundRobinPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 163
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RoundRobinPool
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 155
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual resizer : ()Lscala/Option;
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual resizer : ()Lscala/Option;
/*     */     //   52: astore #5
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 67
/*     */     //   58: pop
/*     */     //   59: aload #5
/*     */     //   61: ifnull -> 75
/*     */     //   64: goto -> 155
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 155
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   84: astore #6
/*     */     //   86: dup
/*     */     //   87: ifnonnull -> 99
/*     */     //   90: pop
/*     */     //   91: aload #6
/*     */     //   93: ifnull -> 107
/*     */     //   96: goto -> 155
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 155
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
/*     */     //   128: goto -> 155
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 155
/*     */     //   139: aload_0
/*     */     //   140: invokevirtual usePoolDispatcher : ()Z
/*     */     //   143: aload #4
/*     */     //   145: invokevirtual usePoolDispatcher : ()Z
/*     */     //   148: if_icmpne -> 155
/*     */     //   151: iconst_1
/*     */     //   152: goto -> 156
/*     */     //   155: iconst_0
/*     */     //   156: ifeq -> 163
/*     */     //   159: iconst_1
/*     */     //   160: goto -> 164
/*     */     //   163: iconst_0
/*     */     //   164: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #66	-> 0
/*     */     //   #63	-> 14
/*     */     //   #66	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	165	0	this	Lakka/routing/RoundRobinPool;
/*     */     //   0	165	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/*     */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/*     */     return this.resizer;
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*     */     return nrOfInstances();
/*     */   }
/*     */   
/*     */   public Option<Resizer> copy$default$2() {
/*     */     return resizer();
/*     */   }
/*     */   
/*     */   public RoundRobinPool(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     PoolOverrideUnsetConfig$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$3() {
/*     */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/*     */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$4() {
/*     */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/*  70 */     return this.usePoolDispatcher;
/*     */   }
/*     */   
/*     */   public boolean copy$default$5() {
/*  70 */     return usePoolDispatcher();
/*     */   }
/*     */   
/*     */   public RoundRobinPool(Config config) {
/*  74 */     this(x$1, 
/*  75 */         (Option)x$2, x$4, x$5, 
/*  76 */         x$3);
/*     */   }
/*     */   
/*     */   public RoundRobinPool(int nr) {
/*  82 */     this(nr, RoundRobinPool$.MODULE$.$lessinit$greater$default$2(), RoundRobinPool$.MODULE$.$lessinit$greater$default$3(), RoundRobinPool$.MODULE$.$lessinit$greater$default$4(), RoundRobinPool$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/*  84 */     return new Router(RoundRobinRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public RoundRobinPool withSupervisorStrategy(SupervisorStrategy strategy) {
/*  89 */     SupervisorStrategy x$6 = strategy;
/*  89 */     int x$7 = copy$default$1();
/*  89 */     Option<Resizer> x$8 = copy$default$2();
/*  89 */     String x$9 = copy$default$4();
/*  89 */     boolean x$10 = copy$default$5();
/*  89 */     return copy(x$7, x$8, x$6, x$9, x$10);
/*     */   }
/*     */   
/*     */   public RoundRobinPool withResizer(Resizer resizer) {
/*  94 */     Some x$11 = new Some(resizer);
/*  94 */     int x$12 = copy$default$1();
/*  94 */     SupervisorStrategy x$13 = copy$default$3();
/*  94 */     String x$14 = copy$default$4();
/*  94 */     boolean x$15 = copy$default$5();
/*  94 */     return copy(x$12, (Option<Resizer>)x$11, x$13, x$14, x$15);
/*     */   }
/*     */   
/*     */   public RoundRobinPool withDispatcher(String dispatcherId) {
/* 100 */     String x$16 = dispatcherId;
/* 100 */     int x$17 = copy$default$1();
/* 100 */     Option<Resizer> x$18 = copy$default$2();
/* 100 */     SupervisorStrategy x$19 = copy$default$3();
/* 100 */     boolean x$20 = copy$default$5();
/* 100 */     return copy(x$17, x$18, x$19, x$16, x$20);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 107 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public static boolean apply$default$5() {
/*     */     return RoundRobinPool$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return RoundRobinPool$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$3() {
/*     */     return RoundRobinPool$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$2() {
/*     */     return RoundRobinPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$5() {
/*     */     return RoundRobinPool$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return RoundRobinPool$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$3() {
/*     */     return RoundRobinPool$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$2() {
/*     */     return RoundRobinPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>, RoundRobinPool> tupled() {
/*     */     return RoundRobinPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Option<Resizer>, Function1<SupervisorStrategy, Function1<String, Function1<Object, RoundRobinPool>>>>> curried() {
/*     */     return RoundRobinPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoundRobinPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */