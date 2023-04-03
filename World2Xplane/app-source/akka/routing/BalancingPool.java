/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.dispatch.BalancingDispatcherConfigurator;
/*     */ import akka.dispatch.Dispatchers;
/*     */ import akka.dispatch.MessageDispatcherConfigurator;
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\005a\001B\001\003\005\036\021QBQ1mC:\034\027N\\4Q_>d'BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\013\001AaBE\013\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\003Q_>d\007CA\005\024\023\t!\"BA\004Qe>$Wo\031;\021\005%1\022BA\f\013\0051\031VM]5bY&T\030M\0317f\021!I\002A!f\001\n\003R\022!\0048s\037\032Len\035;b]\016,7/F\001\034!\tIA$\003\002\036\025\t\031\021J\034;\t\021}\001!\021#Q\001\nm\taB\034:PM&s7\017^1oG\026\034\b\005\003\005\"\001\tU\r\021\"\021#\003I\031X\017]3sm&\034xN]*ue\006$XmZ=\026\003\r\002\"\001J\024\016\003\025R!A\n\003\002\013\005\034Go\034:\n\005!*#AE*va\026\024h/[:peN#(/\031;fOfD\001B\013\001\003\022\003\006IaI\001\024gV\004XM\035<jg>\0248\013\036:bi\026<\027\020\t\005\tY\001\021)\032!C![\005\001\"o\\;uKJ$\025n\0359bi\016DWM]\013\002]A\021qF\r\b\003\023AJ!!\r\006\002\rA\023X\rZ3g\023\t\031DG\001\004TiJLgn\032\006\003c)A\001B\016\001\003\022\003\006IAL\001\022e>,H/\032:ESN\004\030\r^2iKJ\004\003\"\002\035\001\t\003I\024A\002\037j]&$h\b\006\003;wqj\004CA\b\001\021\025Ir\0071\001\034\021\035\ts\007%AA\002\rBq\001L\034\021\002\003\007a\006C\0039\001\021\005q\b\006\002;\001\")\021I\020a\001\005\00611m\0348gS\036\004\"aQ%\016\003\021S!!Q#\013\005\031;\025\001\003;za\026\034\030MZ3\013\003!\0131aY8n\023\tQEI\001\004D_:4\027n\032\005\006q\001!\t\001\024\013\003u5CQAT&A\002m\t!A\034:\t\013A\003A\021I)\002\031\r\024X-\031;f%>,H/\032:\025\005I+\006CA\bT\023\t!&A\001\004S_V$XM\035\005\006->\003\raV\001\007gf\034H/Z7\021\005\021B\026BA-&\005-\t5\r^8s'f\034H/Z7\t\013m\003A\021\001/\002-]LG\017[*va\026\024h/[:peN#(/\031;fOf$\"AO/\t\013yS\006\031A\022\002\021M$(/\031;fOfDQ\001\031\001\005\002\005\fab^5uQ\022K7\017]1uG\",'\017\006\002;E\")1m\030a\001]\005aA-[:qCR\034\007.\032:JI\"1Q\r\001C!\t\031\f\021B\\3x%>,H/Z3\025\007\035Tw\016\005\002\020Q&\021\021N\001\002\007%>,H/Z3\t\013-$\007\031\0017\002\027I|W\017^3f!J|\007o\035\t\003I5L!A\\\023\003\013A\023x\016]:\t\013A$\007\031A9\002\017\r|g\016^3yiB\021AE]\005\003g\026\022A\"Q2u_J\034uN\034;fqRDQ!\036\001\005BY\fAb^5uQ\032\013G\016\0342bG.$\"a\036>\021\005=A\030BA=\003\0051\021v.\036;fe\016{gNZ5h\021\025YH\0171\001x\003\025yG\017[3s\021\035i\bA1A\005By\fqA]3tSj,'/F\001\000!\025I\021\021AA\003\023\r\t\031A\003\002\007\037B$\030n\0348\021\007=\t9!C\002\002\n\t\021qAU3tSj,'\017C\004\002\016\001\001\013\021B@\002\021I,7/\033>fe\002B\021\"!\005\001\003\003%\t!a\005\002\t\r|\007/\037\013\bu\005U\021qCA\r\021!I\022q\002I\001\002\004Y\002\002C\021\002\020A\005\t\031A\022\t\0211\ny\001%AA\0029B\021\"!\b\001#\003%\t!a\b\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\021\021\021\005\026\0047\005\r2FAA\023!\021\t9#!\r\016\005\005%\"\002BA\026\003[\t\021\"\0368dQ\026\0347.\0323\013\007\005=\"\"\001\006b]:|G/\031;j_:LA!a\r\002*\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\023\005]\002!%A\005\002\005e\022AD2paf$C-\0324bk2$HEM\013\003\003wQ3aIA\022\021%\ty\004AI\001\n\003\t\t%\001\bd_BLH\005Z3gCVdG\017J\032\026\005\005\r#f\001\030\002$!I\021q\t\001\002\002\023\005\023\021J\001\016aJ|G-^2u!J,g-\033=\026\005\005-\003\003BA'\003/j!!a\024\013\t\005E\0231K\001\005Y\006twM\003\002\002V\005!!.\031<b\023\r\031\024q\n\005\t\0037\002\021\021!C\0015\005a\001O]8ek\016$\030I]5us\"I\021q\f\001\002\002\023\005\021\021M\001\017aJ|G-^2u\0132,W.\0328u)\021\t\031'!\033\021\007%\t)'C\002\002h)\0211!\0218z\021%\tY'!\030\002\002\003\0071$A\002yIEB\021\"a\034\001\003\003%\t%!\035\002\037A\024x\016Z;di&#XM]1u_J,\"!a\035\021\r\005U\0241PA2\033\t\t9HC\002\002z)\t!bY8mY\026\034G/[8o\023\021\ti(a\036\003\021%#XM]1u_JD\021\"!!\001\003\003%\t!a!\002\021\r\fg.R9vC2$B!!\"\002\fB\031\021\"a\"\n\007\005%%BA\004C_>dW-\0318\t\025\005-\024qPA\001\002\004\t\031\007C\005\002\020\002\t\t\021\"\021\002\022\006A\001.Y:i\007>$W\rF\001\034\021%\t)\nAA\001\n\003\n9*\001\005u_N#(/\0338h)\t\tY\005C\005\002\034\002\t\t\021\"\021\002\036\0061Q-];bYN$B!!\"\002 \"Q\0211NAM\003\003\005\r!a\031)\013\001\t\031+!+\021\007%\t)+C\002\002(*\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059\021\"!,\003\003\003E\t!a,\002\033\t\013G.\0318dS:<\007k\\8m!\ry\021\021\027\004\t\003\t\t\t\021#\001\0024N)\021\021WA[+AA\021qWA_7\rr#(\004\002\002:*\031\0211\030\006\002\017I,h\016^5nK&!\021qXA]\005E\t%m\035;sC\016$h)\0368di&|gn\r\005\bq\005EF\021AAb)\t\ty\013\003\006\002\026\006E\026\021!C#\003/C!\"!3\0022\006\005I\021QAf\003\025\t\007\017\0357z)\035Q\024QZAh\003#Da!GAd\001\004Y\002\002C\021\002HB\005\t\031A\022\t\0211\n9\r%AA\0029B!\"!6\0022\006\005I\021QAl\003\035)h.\0319qYf$B!!7\002bB)\021\"!\001\002\\B1\021\"!8\034G9J1!a8\013\005\031!V\017\0357fg!I\0211]Aj\003\003\005\rAO\001\004q\022\002\004BCAt\003c\013\n\021\"\001\002:\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIIB!\"a;\0022F\005I\021AA!\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%g!Q\021q^AY#\003%\t!!\017\002\037\005\004\b\017\\=%I\0264\027-\0367uIIB!\"a=\0022F\005I\021AA!\003=\t\007\017\0357zI\021,g-Y;mi\022\032\004BCA|\003c\013\t\021\"\003\002z\006Y!/Z1e%\026\034x\016\034<f)\t\tY\020\005\003\002N\005u\030\002BA\000\003\037\022aa\0242kK\016$\b")
/*     */ public final class BalancingPool implements Pool, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   public boolean usePoolDispatcher() {
/*  68 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/*  68 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/*  68 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/*  68 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/*  68 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/*  68 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/*  68 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/*  68 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public BalancingPool copy(int nrOfInstances, SupervisorStrategy supervisorStrategy, String routerDispatcher) {
/*  68 */     return new BalancingPool(
/*  69 */         nrOfInstances, 
/*  70 */         supervisorStrategy, 
/*  71 */         routerDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "BalancingPool";
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
/*     */     return BoxesRunTime.boxToInteger(nrOfInstances());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof BalancingPool;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
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
/*     */     //   8: instanceof akka/routing/BalancingPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 119
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/BalancingPool
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 111
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   52: astore #5
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 67
/*     */     //   58: pop
/*     */     //   59: aload #5
/*     */     //   61: ifnull -> 75
/*     */     //   64: goto -> 111
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 111
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual routerDispatcher : ()Ljava/lang/String;
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
/*     */     //   #68	-> 0
/*     */     //   #63	-> 14
/*     */     //   #68	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	121	0	this	Lakka/routing/BalancingPool;
/*     */     //   0	121	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/*     */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*     */     return nrOfInstances();
/*     */   }
/*     */   
/*     */   public BalancingPool(int nrOfInstances, SupervisorStrategy supervisorStrategy, String routerDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     Product.class.$init$(this);
/* 146 */     this.resizer = (Option<Resizer>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$2() {
/*     */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/*     */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$3() {
/*     */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public BalancingPool(Config config) {
/*     */     this(config.getInt("nr-of-instances"), BalancingPool$.MODULE$.$lessinit$greater$default$2(), BalancingPool$.MODULE$.$lessinit$greater$default$3());
/*     */   }
/*     */   
/*     */   public BalancingPool(int nr) {
/*     */     this(nr, BalancingPool$.MODULE$.$lessinit$greater$default$2(), BalancingPool$.MODULE$.$lessinit$greater$default$3());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/*     */     return new Router(BalancingRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public BalancingPool withSupervisorStrategy(SupervisorStrategy strategy) {
/*     */     SupervisorStrategy x$1 = strategy;
/*     */     int x$2 = copy$default$1();
/*     */     String x$3 = copy$default$3();
/*     */     return copy(x$2, x$1, x$3);
/*     */   }
/*     */   
/*     */   public BalancingPool withDispatcher(String dispatcherId) {
/*     */     String x$4 = dispatcherId;
/*     */     int x$5 = copy$default$1();
/*     */     SupervisorStrategy x$6 = copy$default$2();
/*     */     return copy(x$5, x$6, x$4);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/*     */     String deployPath = ((TraversableOnce)context.self().path().elements().drop(1)).mkString("/", "/", "");
/*     */     (new String[2])[0] = "BalancingPool-";
/*     */     (new String[2])[1] = "";
/*     */     String dispatcherId = (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { deployPath }));
/*     */     (new String[2])[0] = "akka.actor.deployment.";
/*     */     (new String[2])[1] = ".pool-dispatcher";
/*     */     String deployDispatcherConfigPath = (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { deployPath }));
/*     */     Config systemConfig = context.system().settings().config();
/*     */     Config dispatcherConfig = context.system().dispatchers().config(dispatcherId, systemConfig.hasPath(deployDispatcherConfigPath) ? systemConfig.getConfig(deployDispatcherConfigPath) : ConfigFactory.empty());
/*     */     dispatchers$1(context).hasDispatcher(dispatcherId) ? BoxedUnit.UNIT : BoxesRunTime.boxToBoolean(dispatchers$1(context).registerConfigurator(dispatcherId, (MessageDispatcherConfigurator)new BalancingDispatcherConfigurator(dispatcherConfig, dispatchers$1(context).prerequisites())));
/*     */     Props routeePropsWithDispatcher = routeeProps.withDispatcher(dispatcherId);
/*     */     return new ActorRefRoutee(context.actorOf(routeePropsWithDispatcher));
/*     */   }
/*     */   
/*     */   private final Dispatchers dispatchers$1(ActorContext context$1) {
/*     */     return context$1.system().dispatchers();
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/*     */     NoRouter$ noRouter$ = NoRouter$.MODULE$;
/*     */     if (other == null) {
/*     */       if (noRouter$ != null)
/*     */         RouterConfig routerConfig = other; 
/*     */     } else {
/*     */       if (other.equals(noRouter$));
/*     */       RouterConfig routerConfig = other;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/* 146 */     return this.resizer;
/*     */   }
/*     */   
/*     */   public static String apply$default$3() {
/*     */     return BalancingPool$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$2() {
/*     */     return BalancingPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$3() {
/*     */     return BalancingPool$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$2() {
/*     */     return BalancingPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple3<Object, SupervisorStrategy, String>, BalancingPool> tupled() {
/*     */     return BalancingPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<SupervisorStrategy, Function1<String, BalancingPool>>> curried() {
/*     */     return BalancingPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BalancingPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */