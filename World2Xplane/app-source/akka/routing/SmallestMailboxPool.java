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
/*     */ @ScalaSignature(bytes = "\006\001\t\025b\001B\001\003\005\036\0211cU7bY2,7\017^'bS2\024w\016\037)p_2T!a\001\003\002\017I|W\017^5oO*\tQ!\001\003bW.\f7\001A\n\007\001!q!CF\r\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\003Q_>d\007cA\b\024+%\021AC\001\002\030!>|Gn\024<feJLG-Z+og\026$8i\0348gS\036\004\"a\004\001\021\005%9\022B\001\r\013\005\035\001&o\0343vGR\004\"!\003\016\n\005mQ!\001D*fe&\fG.\033>bE2,\007\002C\017\001\005+\007I\021\t\020\002\0339\024xJZ%ogR\fgnY3t+\005y\002CA\005!\023\t\t#BA\002J]RD\001b\t\001\003\022\003\006IaH\001\017]J|e-\0238ti\006t7-Z:!\021!)\003A!f\001\n\0032\023a\002:fg&TXM]\013\002OA\031\021\002\013\026\n\005%R!AB(qi&|g\016\005\002\020W%\021AF\001\002\b%\026\034\030N_3s\021!q\003A!E!\002\0239\023\001\003:fg&TXM\035\021\t\021A\002!Q3A\005BE\n!c];qKJ4\030n]8s'R\024\030\r^3hsV\t!\007\005\0024m5\tAG\003\0026\t\005)\021m\031;pe&\021q\007\016\002\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\003\005:\001\tE\t\025!\0033\003M\031X\017]3sm&\034xN]*ue\006$XmZ=!\021!Y\004A!f\001\n\003b\024\001\005:pkR,'\017R5ta\006$8\r[3s+\005i\004C\001 B\035\tIq(\003\002A\025\0051\001K]3eK\032L!AQ\"\003\rM#(/\0338h\025\t\001%\002\003\005F\001\tE\t\025!\003>\003E\021x.\036;fe\022K7\017]1uG\",'\017\t\005\t\017\002\021)\032!C!\021\006\tRo]3Q_>dG)[:qCR\034\007.\032:\026\003%\003\"!\003&\n\005-S!a\002\"p_2,\027M\034\005\t\033\002\021\t\022)A\005\023\006\021Ro]3Q_>dG)[:qCR\034\007.\032:!\021\025y\005\001\"\001Q\003\031a\024N\\5u}Q1Q#\025*T)VCQ!\b(A\002}Aq!\n(\021\002\003\007q\005C\0041\035B\005\t\031\001\032\t\017mr\005\023!a\001{!9qI\024I\001\002\004I\005\"B(\001\t\0039FCA\013Y\021\025If\0131\001[\003\031\031wN\0344jOB\0211,Y\007\0029*\021\021,\030\006\003=~\013\001\002^=qKN\fg-\032\006\002A\006\0311m\\7\n\005\td&AB\"p]\032Lw\rC\003P\001\021\005A\r\006\002\026K\")am\031a\001?\005\021aN\035\005\006Q\002!\t%[\001\rGJ,\027\r^3S_V$XM\035\013\003U6\004\"aD6\n\0051\024!A\002*pkR,'\017C\003oO\002\007q.\001\004tsN$X-\034\t\003gAL!!\035\033\003\027\005\033Go\034:TsN$X-\034\005\006g\002!\t\001^\001\027o&$\bnU;qKJ4\030n]8s'R\024\030\r^3hsR\021Q#\036\005\006mJ\004\rAM\001\tgR\024\030\r^3hs\")\001\020\001C\001s\006Yq/\033;i%\026\034\030N_3s)\t)\"\020C\003&o\002\007!\006C\003}\001\021\005Q0\001\bxSRDG)[:qCR\034\007.\032:\025\005Uq\b\"B@|\001\004i\024\001\0043jgB\fGo\0315fe&#\007bBA\002\001\021\005\023QA\001\ro&$\bNR1mY\n\f7m\033\013\005\003\017\ti\001E\002\020\003\023I1!a\003\003\0051\021v.\036;fe\016{gNZ5h\021!\ty!!\001A\002\005\035\021!B8uQ\026\024\b\"CA\n\001\005\005I\021AA\013\003\021\031w\016]=\025\027U\t9\"!\007\002\034\005u\021q\004\005\t;\005E\001\023!a\001?!AQ%!\005\021\002\003\007q\005\003\0051\003#\001\n\0211\0013\021!Y\024\021\003I\001\002\004i\004\002C$\002\022A\005\t\031A%\t\023\005\r\002!%A\005\002\005\025\022AD2paf$C-\0324bk2$H%M\013\003\003OQ3aHA\025W\t\tY\003\005\003\002.\005]RBAA\030\025\021\t\t$a\r\002\023Ut7\r[3dW\026$'bAA\033\025\005Q\021M\0348pi\006$\030n\0348\n\t\005e\022q\006\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\"CA\037\001E\005I\021AA \0039\031w\016]=%I\0264\027-\0367uII*\"!!\021+\007\035\nI\003C\005\002F\001\t\n\021\"\001\002H\005q1m\0349zI\021,g-Y;mi\022\032TCAA%U\r\021\024\021\006\005\n\003\033\002\021\023!C\001\003\037\nabY8qs\022\"WMZ1vYR$C'\006\002\002R)\032Q(!\013\t\023\005U\003!%A\005\002\005]\023AD2paf$C-\0324bk2$H%N\013\003\0033R3!SA\025\021%\ti\006AA\001\n\003\ny&A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003C\002B!a\031\002n5\021\021Q\r\006\005\003O\nI'\001\003mC:<'BAA6\003\021Q\027M^1\n\007\t\013)\007\003\005\002r\001\t\t\021\"\001\037\0031\001(o\0343vGR\f%/\033;z\021%\t)\bAA\001\n\003\t9(\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005e\024q\020\t\004\023\005m\024bAA?\025\t\031\021I\\=\t\023\005\005\0251OA\001\002\004y\022a\001=%c!I\021Q\021\001\002\002\023\005\023qQ\001\020aJ|G-^2u\023R,'/\031;peV\021\021\021\022\t\007\003\027\013\t*!\037\016\005\0055%bAAH\025\005Q1m\0347mK\016$\030n\0348\n\t\005M\025Q\022\002\t\023R,'/\031;pe\"I\021q\023\001\002\002\023\005\021\021T\001\tG\006tW)];bYR\031\021*a'\t\025\005\005\025QSA\001\002\004\tI\bC\005\002 \002\t\t\021\"\021\002\"\006A\001.Y:i\007>$W\rF\001 \021%\t)\013AA\001\n\003\n9+\001\005u_N#(/\0338h)\t\t\t\007C\005\002,\002\t\t\021\"\021\002.\0061Q-];bYN$2!SAX\021)\t\t)!+\002\002\003\007\021\021\020\025\006\001\005M\026\021\030\t\004\023\005U\026bAA\\\025\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\035I\021Q\030\002\002\002#\005\021qX\001\024'6\fG\016\\3ti6\013\027\016\0342pqB{w\016\034\t\004\037\005\005g\001C\001\003\003\003E\t!a1\024\013\005\005\027QY\r\021\025\005\035\027QZ\020(euJU#\004\002\002J*\031\0211\032\006\002\017I,h\016^5nK&!\021qZAe\005E\t%m\035;sC\016$h)\0368di&|g.\016\005\b\037\006\005G\021AAj)\t\ty\f\003\006\002&\006\005\027\021!C#\003OC!\"!7\002B\006\005I\021QAn\003\025\t\007\017\0357z)-)\022Q\\Ap\003C\f\031/!:\t\ru\t9\0161\001 \021!)\023q\033I\001\002\0049\003\002\003\031\002XB\005\t\031\001\032\t\021m\n9\016%AA\002uB\001bRAl!\003\005\r!\023\005\013\003S\f\t-!A\005\002\006-\030aB;oCB\004H.\037\013\005\003[\f)\020\005\003\nQ\005=\b\003C\005\002r~9#'P%\n\007\005M(B\001\004UkBdW-\016\005\n\003o\f9/!AA\002U\t1\001\037\0231\021)\tY0!1\022\002\023\005\021qH\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\032\t\025\005}\030\021YI\001\n\003\t9%A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$He\r\005\013\005\007\t\t-%A\005\002\005=\023a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$C\007\003\006\003\b\005\005\027\023!C\001\003/\n1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022*\004B\003B\006\003\003\f\n\021\"\001\002@\005y\021\r\0359ms\022\"WMZ1vYR$#\007\003\006\003\020\005\005\027\023!C\001\003\017\nq\"\0319qYf$C-\0324bk2$He\r\005\013\005'\t\t-%A\005\002\005=\023aD1qa2LH\005Z3gCVdG\017\n\033\t\025\t]\021\021YI\001\n\003\t9&A\bbaBd\027\020\n3fM\006,H\016\036\0236\021)\021Y\"!1\002\002\023%!QD\001\fe\026\fGMU3t_24X\r\006\002\003 A!\0211\rB\021\023\021\021\031#!\032\003\r=\023'.Z2u\001")
/*     */ public final class SmallestMailboxPool implements Pool, PoolOverrideUnsetConfig<SmallestMailboxPool>, Product, Serializable {
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
/* 176 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 176 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 176 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 176 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 176 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 176 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 176 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 176 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 176 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool copy(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/* 176 */     return new SmallestMailboxPool(
/* 177 */         nrOfInstances, resizer, 
/* 178 */         supervisorStrategy, 
/* 179 */         routerDispatcher, 
/* 180 */         usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "SmallestMailboxPool";
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
/*     */     return x$1 instanceof SmallestMailboxPool;
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
/*     */     //   8: instanceof akka/routing/SmallestMailboxPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 163
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/SmallestMailboxPool
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
/*     */     //   #176	-> 0
/*     */     //   #63	-> 14
/*     */     //   #176	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	165	0	this	Lakka/routing/SmallestMailboxPool;
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
/*     */   public SmallestMailboxPool(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
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
/* 180 */     return this.usePoolDispatcher;
/*     */   }
/*     */   
/*     */   public boolean copy$default$5() {
/* 180 */     return usePoolDispatcher();
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool(Config config) {
/* 184 */     this(
/* 185 */         x$1, 
/* 186 */         (Option)x$2, x$4, x$5, 
/* 187 */         x$3);
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool(int nr) {
/* 193 */     this(nr, SmallestMailboxPool$.MODULE$.$lessinit$greater$default$2(), SmallestMailboxPool$.MODULE$.$lessinit$greater$default$3(), SmallestMailboxPool$.MODULE$.$lessinit$greater$default$4(), SmallestMailboxPool$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 195 */     return new Router(SmallestMailboxRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool withSupervisorStrategy(SupervisorStrategy strategy) {
/* 200 */     SupervisorStrategy x$6 = strategy;
/* 200 */     int x$7 = copy$default$1();
/* 200 */     Option<Resizer> x$8 = copy$default$2();
/* 200 */     String x$9 = copy$default$4();
/* 200 */     boolean x$10 = copy$default$5();
/* 200 */     return copy(x$7, x$8, x$6, x$9, x$10);
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool withResizer(Resizer resizer) {
/* 205 */     Some x$11 = new Some(resizer);
/* 205 */     int x$12 = copy$default$1();
/* 205 */     SupervisorStrategy x$13 = copy$default$3();
/* 205 */     String x$14 = copy$default$4();
/* 205 */     boolean x$15 = copy$default$5();
/* 205 */     return copy(x$12, (Option<Resizer>)x$11, x$13, x$14, x$15);
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool withDispatcher(String dispatcherId) {
/* 211 */     String x$16 = dispatcherId;
/* 211 */     int x$17 = copy$default$1();
/* 211 */     Option<Resizer> x$18 = copy$default$2();
/* 211 */     SupervisorStrategy x$19 = copy$default$3();
/* 211 */     boolean x$20 = copy$default$5();
/* 211 */     return copy(x$17, x$18, x$19, x$16, x$20);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 218 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public static boolean apply$default$5() {
/*     */     return SmallestMailboxPool$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return SmallestMailboxPool$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$3() {
/*     */     return SmallestMailboxPool$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$2() {
/*     */     return SmallestMailboxPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$5() {
/*     */     return SmallestMailboxPool$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return SmallestMailboxPool$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$3() {
/*     */     return SmallestMailboxPool$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$2() {
/*     */     return SmallestMailboxPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>, SmallestMailboxPool> tupled() {
/*     */     return SmallestMailboxPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Option<Resizer>, Function1<SupervisorStrategy, Function1<String, Function1<Object, SmallestMailboxPool>>>>> curried() {
/*     */     return SmallestMailboxPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SmallestMailboxPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */