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
/*     */ @ScalaSignature(bytes = "\006\001\t\025b\001B\001\003\005\036\021QB\021:pC\022\034\027m\035;Q_>d'BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\r\001AaB\005\f\032!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\005!>|G\016E\002\020'UI!\001\006\002\003/A{w\016\\(wKJ\024\030\016Z3V]N,GoQ8oM&<\007CA\b\001!\tIq#\003\002\031\025\t9\001K]8ek\016$\bCA\005\033\023\tY\"B\001\007TKJL\027\r\\5{C\ndW\r\003\005\036\001\tU\r\021\"\021\037\0035q'o\0244J]N$\030M\\2fgV\tq\004\005\002\nA%\021\021E\003\002\004\023:$\b\002C\022\001\005#\005\013\021B\020\002\0359\024xJZ%ogR\fgnY3tA!AQ\005\001BK\002\023\005c%A\004sKNL'0\032:\026\003\035\0022!\003\025+\023\tI#B\001\004PaRLwN\034\t\003\037-J!\001\f\002\003\017I+7/\033>fe\"Aa\006\001B\tB\003%q%\001\005sKNL'0\032:!\021!\001\004A!f\001\n\003\n\024AE:va\026\024h/[:peN#(/\031;fOf,\022A\r\t\003gYj\021\001\016\006\003k\021\tQ!Y2u_JL!a\016\033\003%M+\b/\032:wSN|'o\025;sCR,w-\037\005\ts\001\021\t\022)A\005e\005\0312/\0369feZL7o\034:TiJ\fG/Z4zA!A1\b\001BK\002\023\005C(\001\ts_V$XM\035#jgB\fGo\0315feV\tQ\b\005\002?\003:\021\021bP\005\003\001*\ta\001\025:fI\0264\027B\001\"D\005\031\031FO]5oO*\021\001I\003\005\t\013\002\021\t\022)A\005{\005\t\"o\\;uKJ$\025n\0359bi\016DWM\035\021\t\021\035\003!Q3A\005B!\013\021#^:f!>|G\016R5ta\006$8\r[3s+\005I\005CA\005K\023\tY%BA\004C_>dW-\0318\t\0215\003!\021#Q\001\n%\013!#^:f!>|G\016R5ta\006$8\r[3sA!)q\n\001C\001!\0061A(\0338jiz\"b!F)S'R+\006\"B\017O\001\004y\002bB\023O!\003\005\ra\n\005\ba9\003\n\0211\0013\021\035Yd\n%AA\002uBqa\022(\021\002\003\007\021\nC\003P\001\021\005q\013\006\002\0261\")\021L\026a\0015\00611m\0348gS\036\004\"aW1\016\003qS!!W/\013\005y{\026\001\003;za\026\034\030MZ3\013\003\001\f1aY8n\023\t\021GL\001\004D_:4\027n\032\005\006\037\002!\t\001\032\013\003+\025DQAZ2A\002}\t!A\034:\t\013!\004A\021I5\002\031\r\024X-\031;f%>,H/\032:\025\005)l\007CA\bl\023\ta'A\001\004S_V$XM\035\005\006]\036\004\ra\\\001\007gf\034H/Z7\021\005M\002\030BA95\005-\t5\r^8s'f\034H/Z7\t\013M\004A\021\001;\002-]LG\017[*va\026\024h/[:peN#(/\031;fOf$\"!F;\t\013Y\024\b\031\001\032\002\021M$(/\031;fOfDQ\001\037\001\005\002e\f1b^5uQJ+7/\033>feR\021QC\037\005\006K]\004\rA\013\005\006y\002!\t!`\001\017o&$\b\016R5ta\006$8\r[3s)\t)b\020C\003\000w\002\007Q(\001\007eSN\004\030\r^2iKJLE\rC\004\002\004\001!\t%!\002\002\031]LG\017\033$bY2\024\027mY6\025\t\005\035\021Q\002\t\004\037\005%\021bAA\006\005\ta!k\\;uKJ\034uN\0344jO\"A\021qBA\001\001\004\t9!A\003pi\",'\017C\005\002\024\001\t\t\021\"\001\002\026\005!1m\0349z)-)\022qCA\r\0037\ti\"a\b\t\021u\t\t\002%AA\002}A\001\"JA\t!\003\005\ra\n\005\ta\005E\001\023!a\001e!A1(!\005\021\002\003\007Q\b\003\005H\003#\001\n\0211\001J\021%\t\031\003AI\001\n\003\t)#\001\bd_BLH\005Z3gCVdG\017J\031\026\005\005\035\"fA\020\002*-\022\0211\006\t\005\003[\t9$\004\002\0020)!\021\021GA\032\003%)hn\0315fG.,GMC\002\0026)\t!\"\0318o_R\fG/[8o\023\021\tI$a\f\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\005\002>\001\t\n\021\"\001\002@\005q1m\0349zI\021,g-Y;mi\022\022TCAA!U\r9\023\021\006\005\n\003\013\002\021\023!C\001\003\017\nabY8qs\022\"WMZ1vYR$3'\006\002\002J)\032!'!\013\t\023\0055\003!%A\005\002\005=\023AD2paf$C-\0324bk2$H\005N\013\003\003#R3!PA\025\021%\t)\006AI\001\n\003\t9&\001\bd_BLH\005Z3gCVdG\017J\033\026\005\005e#fA%\002*!I\021Q\f\001\002\002\023\005\023qL\001\016aJ|G-^2u!J,g-\033=\026\005\005\005\004\003BA2\003[j!!!\032\013\t\005\035\024\021N\001\005Y\006twM\003\002\002l\005!!.\031<b\023\r\021\025Q\r\005\t\003c\002\021\021!C\001=\005a\001O]8ek\016$\030I]5us\"I\021Q\017\001\002\002\023\005\021qO\001\017aJ|G-^2u\0132,W.\0328u)\021\tI(a \021\007%\tY(C\002\002~)\0211!\0218z\021%\t\t)a\035\002\002\003\007q$A\002yIEB\021\"!\"\001\003\003%\t%a\"\002\037A\024x\016Z;di&#XM]1u_J,\"!!#\021\r\005-\025\021SA=\033\t\tiIC\002\002\020*\t!bY8mY\026\034G/[8o\023\021\t\031*!$\003\021%#XM]1u_JD\021\"a&\001\003\003%\t!!'\002\021\r\fg.R9vC2$2!SAN\021)\t\t)!&\002\002\003\007\021\021\020\005\n\003?\003\021\021!C!\003C\013\001\002[1tQ\016{G-\032\013\002?!I\021Q\025\001\002\002\023\005\023qU\001\ti>\034FO]5oOR\021\021\021\r\005\n\003W\003\021\021!C!\003[\013a!Z9vC2\034HcA%\0020\"Q\021\021QAU\003\003\005\r!!\037)\013\001\t\031,!/\021\007%\t),C\002\0028*\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059\021\"!0\003\003\003E\t!a0\002\033\t\023x.\0313dCN$\bk\\8m!\ry\021\021\031\004\t\003\t\t\t\021#\001\002DN)\021\021YAc3AQ\021qYAg?\035\022T(S\013\016\005\005%'bAAf\025\0059!/\0368uS6,\027\002BAh\003\023\024\021#\0212tiJ\f7\r\036$v]\016$\030n\03486\021\035y\025\021\031C\001\003'$\"!a0\t\025\005\025\026\021YA\001\n\013\n9\013\003\006\002Z\006\005\027\021!CA\0037\fQ!\0319qYf$2\"FAo\003?\f\t/a9\002f\"1Q$a6A\002}A\001\"JAl!\003\005\ra\n\005\ta\005]\007\023!a\001e!A1(a6\021\002\003\007Q\b\003\005H\003/\004\n\0211\001J\021)\tI/!1\002\002\023\005\0251^\001\bk:\f\007\017\0357z)\021\ti/!>\021\t%A\023q\036\t\t\023\005Exd\n\032>\023&\031\0211\037\006\003\rQ+\b\017\\36\021%\t90a:\002\002\003\007Q#A\002yIAB!\"a?\002BF\005I\021AA \003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%e!Q\021q`Aa#\003%\t!a\022\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0234\021)\021\031!!1\022\002\023\005\021qJ\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\033\t\025\t\035\021\021YI\001\n\003\t9&A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\016\005\013\005\027\t\t-%A\005\002\005}\022aD1qa2LH\005Z3gCVdG\017\n\032\t\025\t=\021\021YI\001\n\003\t9%A\bbaBd\027\020\n3fM\006,H\016\036\0234\021)\021\031\"!1\022\002\023\005\021qJ\001\020CB\004H.\037\023eK\032\fW\017\034;%i!Q!qCAa#\003%\t!a\026\002\037\005\004\b\017\\=%I\0264\027-\0367uIUB!Ba\007\002B\006\005I\021\002B\017\003-\021X-\0313SKN|GN^3\025\005\t}\001\003BA2\005CIAAa\t\002f\t1qJ\0316fGR\004")
/*     */ public final class BroadcastPool implements Pool, PoolOverrideUnsetConfig<BroadcastPool>, Product, Serializable {
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
/*  60 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/*  60 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/*  60 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/*  60 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/*  60 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/*  60 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/*  60 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/*  60 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/*  60 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public BroadcastPool copy(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*  60 */     return new BroadcastPool(
/*  61 */         nrOfInstances, resizer, 
/*  62 */         supervisorStrategy, 
/*  63 */         routerDispatcher, 
/*  64 */         usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "BroadcastPool";
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
/*     */     return x$1 instanceof BroadcastPool;
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
/*     */     //   8: instanceof akka/routing/BroadcastPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 163
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/BroadcastPool
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
/*     */     //   #60	-> 0
/*     */     //   #63	-> 14
/*     */     //   #60	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	165	0	this	Lakka/routing/BroadcastPool;
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
/*     */   public BroadcastPool(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
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
/*  64 */     return this.usePoolDispatcher;
/*     */   }
/*     */   
/*     */   public boolean copy$default$5() {
/*  64 */     return usePoolDispatcher();
/*     */   }
/*     */   
/*     */   public BroadcastPool(Config config) {
/*  68 */     this(
/*  69 */         x$1, 
/*  70 */         (Option)x$2, x$4, x$5, 
/*  71 */         x$3);
/*     */   }
/*     */   
/*     */   public BroadcastPool(int nr) {
/*  77 */     this(nr, BroadcastPool$.MODULE$.$lessinit$greater$default$2(), BroadcastPool$.MODULE$.$lessinit$greater$default$3(), BroadcastPool$.MODULE$.$lessinit$greater$default$4(), BroadcastPool$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/*  79 */     return new Router(BroadcastRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public BroadcastPool withSupervisorStrategy(SupervisorStrategy strategy) {
/*  84 */     SupervisorStrategy x$6 = strategy;
/*  84 */     int x$7 = copy$default$1();
/*  84 */     Option<Resizer> x$8 = copy$default$2();
/*  84 */     String x$9 = copy$default$4();
/*  84 */     boolean x$10 = copy$default$5();
/*  84 */     return copy(x$7, x$8, x$6, x$9, x$10);
/*     */   }
/*     */   
/*     */   public BroadcastPool withResizer(Resizer resizer) {
/*  89 */     Some x$11 = new Some(resizer);
/*  89 */     int x$12 = copy$default$1();
/*  89 */     SupervisorStrategy x$13 = copy$default$3();
/*  89 */     String x$14 = copy$default$4();
/*  89 */     boolean x$15 = copy$default$5();
/*  89 */     return copy(x$12, (Option<Resizer>)x$11, x$13, x$14, x$15);
/*     */   }
/*     */   
/*     */   public BroadcastPool withDispatcher(String dispatcherId) {
/*  95 */     String x$16 = dispatcherId;
/*  95 */     int x$17 = copy$default$1();
/*  95 */     Option<Resizer> x$18 = copy$default$2();
/*  95 */     SupervisorStrategy x$19 = copy$default$3();
/*  95 */     boolean x$20 = copy$default$5();
/*  95 */     return copy(x$17, x$18, x$19, x$16, x$20);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 102 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public static boolean apply$default$5() {
/*     */     return BroadcastPool$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return BroadcastPool$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$3() {
/*     */     return BroadcastPool$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$2() {
/*     */     return BroadcastPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$5() {
/*     */     return BroadcastPool$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return BroadcastPool$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$3() {
/*     */     return BroadcastPool$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$2() {
/*     */     return BroadcastPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>, BroadcastPool> tupled() {
/*     */     return BroadcastPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Option<Resizer>, Function1<SupervisorStrategy, Function1<String, Function1<Object, BroadcastPool>>>>> curried() {
/*     */     return BroadcastPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BroadcastPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */