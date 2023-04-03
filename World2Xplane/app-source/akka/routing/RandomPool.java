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
/*     */ @ScalaSignature(bytes = "\006\001\t\025b\001B\001\003\005\036\021!BU1oI>l\007k\\8m\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lC\016\0011C\002\001\t\035I1\022\004\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021A\001U8pYB\031qbE\013\n\005Q\021!a\006)p_2|e/\032:sS\022,WK\\:fi\016{gNZ5h!\ty\001\001\005\002\n/%\021\001D\003\002\b!J|G-^2u!\tI!$\003\002\034\025\ta1+\032:jC2L'0\0312mK\"AQ\004\001BK\002\023\005c$A\007oe>3\027J\\:uC:\034Wm]\013\002?A\021\021\002I\005\003C)\0211!\0238u\021!\031\003A!E!\002\023y\022A\0048s\037\032Len\035;b]\016,7\017\t\005\tK\001\021)\032!C!M\0059!/Z:ju\026\024X#A\024\021\007%A#&\003\002*\025\t1q\n\035;j_:\004\"aD\026\n\0051\022!a\002*fg&TXM\035\005\t]\001\021\t\022)A\005O\005A!/Z:ju\026\024\b\005\003\0051\001\tU\r\021\"\0212\003I\031X\017]3sm&\034xN]*ue\006$XmZ=\026\003I\002\"a\r\034\016\003QR!!\016\003\002\013\005\034Go\034:\n\005]\"$AE*va\026\024h/[:peN#(/\031;fOfD\001\"\017\001\003\022\003\006IAM\001\024gV\004XM\035<jg>\0248\013\036:bi\026<\027\020\t\005\tw\001\021)\032!C!y\005\001\"o\\;uKJ$\025n\0359bi\016DWM]\013\002{A\021a(\021\b\003\023}J!\001\021\006\002\rA\023X\rZ3g\023\t\0215I\001\004TiJLgn\032\006\003\001*A\001\"\022\001\003\022\003\006I!P\001\022e>,H/\032:ESN\004\030\r^2iKJ\004\003\002C$\001\005+\007I\021\t%\002#U\034X\rU8pY\022K7\017]1uG\",'/F\001J!\tI!*\003\002L\025\t9!i\\8mK\006t\007\002C'\001\005#\005\013\021B%\002%U\034X\rU8pY\022K7\017]1uG\",'\017\t\005\006\037\002!\t\001U\001\007y%t\027\016\036 \025\rU\t&k\025+V\021\025ib\n1\001 \021\035)c\n%AA\002\035Bq\001\r(\021\002\003\007!\007C\004<\035B\005\t\031A\037\t\017\035s\005\023!a\001\023\")q\n\001C\001/R\021Q\003\027\005\0063Z\003\rAW\001\007G>tg-[4\021\005m\013W\"\001/\013\005ek&B\0010`\003!!\030\020]3tC\032,'\"\0011\002\007\r|W.\003\002c9\n11i\0348gS\036DQa\024\001\005\002\021$\"!F3\t\013\031\034\007\031A\020\002\0059\024\b\"\0025\001\t\003J\027\001D2sK\006$XMU8vi\026\024HC\0016n!\ty1.\003\002m\005\t1!k\\;uKJDQA\\4A\002=\faa]=ti\026l\007CA\032q\023\t\tHGA\006BGR|'oU=ti\026l\007\"B:\001\t\003!\030AF<ji\"\034V\017]3sm&\034xN]*ue\006$XmZ=\025\005U)\b\"\002<s\001\004\021\024\001C:ue\006$XmZ=\t\013a\004A\021A=\002\027]LG\017\033*fg&TXM\035\013\003+iDQ!J<A\002)BQ\001 \001\005\002u\fab^5uQ\022K7\017]1uG\",'\017\006\002\026}\")qp\037a\001{\005aA-[:qCR\034\007.\032:JI\"9\0211\001\001\005B\005\025\021\001D<ji\"4\025\r\0347cC\016\\G\003BA\004\003\033\0012aDA\005\023\r\tYA\001\002\r%>,H/\032:D_:4\027n\032\005\t\003\037\t\t\0011\001\002\b\005)q\016\0365fe\"I\0211\003\001\002\002\023\005\021QC\001\005G>\004\030\020F\006\026\003/\tI\"a\007\002\036\005}\001\002C\017\002\022A\005\t\031A\020\t\021\025\n\t\002%AA\002\035B\001\002MA\t!\003\005\rA\r\005\tw\005E\001\023!a\001{!Aq)!\005\021\002\003\007\021\nC\005\002$\001\t\n\021\"\001\002&\005q1m\0349zI\021,g-Y;mi\022\nTCAA\024U\ry\022\021F\026\003\003W\001B!!\f\00285\021\021q\006\006\005\003c\t\031$A\005v]\016DWmY6fI*\031\021Q\007\006\002\025\005tgn\034;bi&|g.\003\003\002:\005=\"!E;oG\",7m[3e-\006\024\030.\0318dK\"I\021Q\b\001\022\002\023\005\021qH\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\t\t\tEK\002(\003SA\021\"!\022\001#\003%\t!a\022\002\035\r|\007/\037\023eK\032\fW\017\034;%gU\021\021\021\n\026\004e\005%\002\"CA'\001E\005I\021AA(\0039\031w\016]=%I\0264\027-\0367uIQ*\"!!\025+\007u\nI\003C\005\002V\001\t\n\021\"\001\002X\005q1m\0349zI\021,g-Y;mi\022*TCAA-U\rI\025\021\006\005\n\003;\002\021\021!C!\003?\nQ\002\035:pIV\034G\017\025:fM&DXCAA1!\021\t\031'!\034\016\005\005\025$\002BA4\003S\nA\001\\1oO*\021\0211N\001\005U\0064\030-C\002C\003KB\001\"!\035\001\003\003%\tAH\001\raJ|G-^2u\003JLG/\037\005\n\003k\002\021\021!C\001\003o\na\002\035:pIV\034G/\0227f[\026tG\017\006\003\002z\005}\004cA\005\002|%\031\021Q\020\006\003\007\005s\027\020C\005\002\002\006M\024\021!a\001?\005\031\001\020J\031\t\023\005\025\005!!A\005B\005\035\025a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\005%\005CBAF\003#\013I(\004\002\002\016*\031\021q\022\006\002\025\r|G\016\\3di&|g.\003\003\002\024\0065%\001C%uKJ\fGo\034:\t\023\005]\005!!A\005\002\005e\025\001C2b]\026\013X/\0317\025\007%\013Y\n\003\006\002\002\006U\025\021!a\001\003sB\021\"a(\001\003\003%\t%!)\002\021!\f7\017[\"pI\026$\022a\b\005\n\003K\003\021\021!C!\003O\013\001\002^8TiJLgn\032\013\003\003CB\021\"a+\001\003\003%\t%!,\002\r\025\fX/\0317t)\rI\025q\026\005\013\003\003\013I+!AA\002\005e\004&\002\001\0024\006e\006cA\005\0026&\031\021q\027\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\023\005u&!!A\t\002\005}\026A\003*b]\022|W\016U8pYB\031q\"!1\007\021\005\021\021\021!E\001\003\007\034R!!1\002Ff\001\"\"a2\002N~9#'P%\026\033\t\tIMC\002\002L*\tqA];oi&lW-\003\003\002P\006%'!E!cgR\024\030m\031;Gk:\034G/[8ok!9q*!1\005\002\005MGCAA`\021)\t)+!1\002\002\023\025\023q\025\005\013\0033\f\t-!A\005\002\006m\027!B1qa2LHcC\013\002^\006}\027\021]Ar\003KDa!HAl\001\004y\002\002C\023\002XB\005\t\031A\024\t\021A\n9\016%AA\002IB\001bOAl!\003\005\r!\020\005\t\017\006]\007\023!a\001\023\"Q\021\021^Aa\003\003%\t)a;\002\017Ut\027\r\0359msR!\021Q^A{!\021I\001&a<\021\021%\t\tpH\0243{%K1!a=\013\005\031!V\017\0357fk!I\021q_At\003\003\005\r!F\001\004q\022\002\004BCA~\003\003\f\n\021\"\001\002@\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIIB!\"a@\002BF\005I\021AA$\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%g!Q!1AAa#\003%\t!a\024\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0235\021)\0219!!1\022\002\023\005\021qK\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\033\t\025\t-\021\021YI\001\n\003\ty$A\bbaBd\027\020\n3fM\006,H\016\036\0233\021)\021y!!1\022\002\023\005\021qI\001\020CB\004H.\037\023eK\032\fW\017\034;%g!Q!1CAa#\003%\t!a\024\002\037\005\004\b\017\\=%I\0264\027-\0367uIQB!Ba\006\002BF\005I\021AA,\003=\t\007\017\0357zI\021,g-Y;mi\022*\004B\003B\016\003\003\f\t\021\"\003\003\036\005Y!/Z1e%\026\034x\016\034<f)\t\021y\002\005\003\002d\t\005\022\002\002B\022\003K\022aa\0242kK\016$\b")
/*     */ public final class RandomPool implements Pool, PoolOverrideUnsetConfig<RandomPool>, Product, Serializable {
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
/*  61 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/*  61 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/*  61 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/*  61 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/*  61 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/*  61 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/*  61 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/*  61 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/*  61 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public RandomPool copy(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*  61 */     return new RandomPool(
/*  62 */         nrOfInstances, resizer, 
/*  63 */         supervisorStrategy, 
/*  64 */         routerDispatcher, 
/*  65 */         usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "RandomPool";
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
/*     */     return x$1 instanceof RandomPool;
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
/*     */     //   8: instanceof akka/routing/RandomPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 163
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RandomPool
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
/*     */     //   #61	-> 0
/*     */     //   #63	-> 14
/*     */     //   #61	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	165	0	this	Lakka/routing/RandomPool;
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
/*     */   public RandomPool(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
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
/*  65 */     return this.usePoolDispatcher;
/*     */   }
/*     */   
/*     */   public boolean copy$default$5() {
/*  65 */     return usePoolDispatcher();
/*     */   }
/*     */   
/*     */   public RandomPool(Config config) {
/*  69 */     this(
/*  70 */         x$1, 
/*  71 */         (Option)x$2, x$4, x$5, 
/*  72 */         x$3);
/*     */   }
/*     */   
/*     */   public RandomPool(int nr) {
/*  78 */     this(nr, RandomPool$.MODULE$.$lessinit$greater$default$2(), RandomPool$.MODULE$.$lessinit$greater$default$3(), RandomPool$.MODULE$.$lessinit$greater$default$4(), RandomPool$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/*  80 */     return new Router(RandomRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public RandomPool withSupervisorStrategy(SupervisorStrategy strategy) {
/*  85 */     SupervisorStrategy x$6 = strategy;
/*  85 */     int x$7 = copy$default$1();
/*  85 */     Option<Resizer> x$8 = copy$default$2();
/*  85 */     String x$9 = copy$default$4();
/*  85 */     boolean x$10 = copy$default$5();
/*  85 */     return copy(x$7, x$8, x$6, x$9, x$10);
/*     */   }
/*     */   
/*     */   public RandomPool withResizer(Resizer resizer) {
/*  90 */     Some x$11 = new Some(resizer);
/*  90 */     int x$12 = copy$default$1();
/*  90 */     SupervisorStrategy x$13 = copy$default$3();
/*  90 */     String x$14 = copy$default$4();
/*  90 */     boolean x$15 = copy$default$5();
/*  90 */     return copy(x$12, (Option<Resizer>)x$11, x$13, x$14, x$15);
/*     */   }
/*     */   
/*     */   public RandomPool withDispatcher(String dispatcherId) {
/*  96 */     String x$16 = dispatcherId;
/*  96 */     int x$17 = copy$default$1();
/*  96 */     Option<Resizer> x$18 = copy$default$2();
/*  96 */     SupervisorStrategy x$19 = copy$default$3();
/*  96 */     boolean x$20 = copy$default$5();
/*  96 */     return copy(x$17, x$18, x$19, x$16, x$20);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 103 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public static boolean apply$default$5() {
/*     */     return RandomPool$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return RandomPool$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$3() {
/*     */     return RandomPool$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$2() {
/*     */     return RandomPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$5() {
/*     */     return RandomPool$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return RandomPool$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$3() {
/*     */     return RandomPool$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$2() {
/*     */     return RandomPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>, RandomPool> tupled() {
/*     */     return RandomPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Option<Resizer>, Function1<SupervisorStrategy, Function1<String, Function1<Object, RandomPool>>>>> curried() {
/*     */     return RandomPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RandomPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */