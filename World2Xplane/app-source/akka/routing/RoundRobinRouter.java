/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tUs!B\001\003\021\0039\021\001\005*pk:$'k\0342j]J{W\017^3s\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!\001\005*pk:$'k\0342j]J{W\017^3s'\rIAB\005\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\021\0055\031\022B\001\013\017\0051\031VM]5bY&T\030M\0317f\021\0251\022\002\"\001\030\003\031a\024N\\5u}Q\tq\001C\003\032\023\021\005!$A\003baBd\027\020F\002\034\003O\004\"\001\003\017\007\t)\021\001)H\n\00791q\022\005\n\n\021\005!y\022B\001\021\003\005Y!U\r\035:fG\006$X\r\032*pkR,'oQ8oM&<\007c\001\005#7%\0211E\001\002\030!>|Gn\024<feJLG-Z+og\026$8i\0348gS\036\004\"!D\023\n\005\031r!a\002)s_\022,8\r\036\005\tQq\021)\032!C\001S\005iaN](g\023:\034H/\0318dKN,\022A\013\t\003\033-J!\001\f\b\003\007%sG\017\003\005/9\tE\t\025!\003+\0039q'o\0244J]N$\030M\\2fg\002B\001\002\r\017\003\026\004%\t!M\001\be>,H/Z3t+\005\021\004cA\0329u5\tAG\003\0026m\005I\021.\\7vi\006\024G.\032\006\003o9\t!bY8mY\026\034G/[8o\023\tIDG\001\005Ji\026\024\030M\0317f!\tYdH\004\002\016y%\021QHD\001\007!J,G-\0324\n\005}\002%AB*ue&twM\003\002>\035!A!\t\bB\tB\003%!'\001\005s_V$X-Z:!\021!!ED!f\001\n\003*\025a\002:fg&TXM]\013\002\rB\031QbR%\n\005!s!AB(qi&|g\016\005\002\t\025&\0211J\001\002\b%\026\034\030N_3s\021!iED!E!\002\0231\025\001\003:fg&TXM\035\021\t\021=c\"Q3A\005\002A\013\001C]8vi\026\024H)[:qCR\034\007.\032:\026\003iB\001B\025\017\003\022\003\006IAO\001\022e>,H/\032:ESN\004\030\r^2iKJ\004\003\002\003+\035\005+\007I\021A+\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\002-B\021qKW\007\0021*\021\021\fB\001\006C\016$xN]\005\0037b\023!cU;qKJ4\030n]8s'R\024\030\r^3hs\"AQ\f\bB\tB\003%a+A\ntkB,'O^5t_J\034FO]1uK\036L\b\005C\003\0279\021\005q\f\006\004\034A\006\0247\r\032\005\bQy\003\n\0211\001+\021\035\001d\f%AA\002IBq\001\0220\021\002\003\007a\tC\004P=B\005\t\031\001\036\t\017Qs\006\023!a\001-\")a\003\bC\001MR\0211d\032\005\006Q\026\004\rAK\001\003]JDQA\006\017\005\002)$\"aG6\t\0131L\007\031A7\002\027I|W\017^3f!\006$\bn\035\t\004]NTT\"A8\013\005A\f\030\001\0027b]\036T\021A]\001\005U\0064\030-\003\002:_\")a\003\bC\001kR\0211D\036\005\006\tR\004\r!\023\005\006qr!\t%M\001\006a\006$\bn\035\005\006ur!\ta_\001\017o&$\b\016R5ta\006$8\r[3s)\tYB\020C\003~s\002\007!(\001\007eSN\004\030\r^2iKJLE\r\003\004\0009\021\005\021\021A\001\027o&$\bnU;qKJ4\030n]8s'R\024\030\r^3hsR\0311$a\001\t\r\005\025a\0201\001W\003!\031HO]1uK\036L\bbBA\0059\021\005\0211B\001\fo&$\bNU3tSj,'\017F\002\034\003\033Aa\001RA\004\001\004I\005bBA\t9\021\005\0231C\001\ro&$\bNR1mY\n\f7m\033\013\005\003+\tY\002E\002\t\003/I1!!\007\003\0051\021v.\036;fe\016{gNZ5h\021!\ti\"a\004A\002\005U\021!B8uQ\026\024\bbBA\0219\021\005\0231E\001\rGJ,\027\r^3S_V$XM\035\013\005\003K\tY\003E\002\t\003OI1!!\013\003\005\031\021v.\036;fe\"A\021QFA\020\001\004\ty#\001\004tsN$X-\034\t\004/\006E\022bAA\0321\nY\021i\031;peNK8\017^3n\021%\t9\004HA\001\n\003\tI$\001\003d_BLHcC\016\002<\005u\022qHA!\003\007B\001\002KA\033!\003\005\rA\013\005\ta\005U\002\023!a\001e!AA)!\016\021\002\003\007a\t\003\005P\003k\001\n\0211\001;\021!!\026Q\007I\001\002\0041\006\"CA$9E\005I\021AA%\0039\031w\016]=%I\0264\027-\0367uIE*\"!a\023+\007)\nie\013\002\002PA!\021\021KA.\033\t\t\031F\003\003\002V\005]\023!C;oG\",7m[3e\025\r\tIFD\001\013C:tw\016^1uS>t\027\002BA/\003'\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021%\t\t\007HI\001\n\003\t\031'\001\bd_BLH\005Z3gCVdG\017\n\032\026\005\005\025$f\001\032\002N!I\021\021\016\017\022\002\023\005\0211N\001\017G>\004\030\020\n3fM\006,H\016\036\0234+\t\tiGK\002G\003\033B\021\"!\035\035#\003%\t!a\035\002\035\r|\007/\037\023eK\032\fW\017\034;%iU\021\021Q\017\026\004u\0055\003\"CA=9E\005I\021AA>\0039\031w\016]=%I\0264\027-\0367uIU*\"!! +\007Y\013i\005C\005\002\002r\t\t\021\"\021\002\004\006i\001O]8ek\016$\bK]3gSb,\"!!\"\021\0079\f9)\003\002@_\"A\0211\022\017\002\002\023\005\021&\001\007qe>$Wo\031;Be&$\030\020C\005\002\020r\t\t\021\"\001\002\022\006q\001O]8ek\016$X\t\\3nK:$H\003BAJ\0033\0032!DAK\023\r\t9J\004\002\004\003:L\b\"CAN\003\033\013\t\0211\001+\003\rAH%\r\005\n\003?c\022\021!C!\003C\013q\002\035:pIV\034G/\023;fe\006$xN]\013\003\003G\003b!!*\002(\006MU\"\001\034\n\007\005%fG\001\005Ji\026\024\030\r^8s\021%\ti\013HA\001\n\003\ty+\001\005dC:,\025/^1m)\021\t\t,a.\021\0075\t\031,C\002\0026:\021qAQ8pY\026\fg\016\003\006\002\034\006-\026\021!a\001\003'C\021\"a/\035\003\003%\t%!0\002\021!\f7\017[\"pI\026$\022A\013\005\n\003\003d\022\021!C!\003\007\f\001\002^8TiJLgn\032\013\003\003\013C\021\"a2\035\003\003%\t%!3\002\r\025\fX/\0317t)\021\t\t,a3\t\025\005m\025QYA\001\002\004\t\031\nK\004\035\003\037\f).!7\021\0075\t\t.C\002\002T:\021!\002Z3qe\026\034\027\r^3eC\t\t9.A\023Vg\026\004#k\\;oIJ{'-\0338Q_>d\007e\034:!%>,h\016\032*pE&twI]8va\006\022\0211\\\001\004e9\032\004&\002\017\002`\006\025\bcA\007\002b&\031\0211\035\b\003!M+'/[1m-\026\0248/[8o+&#e$A\001\t\rAB\002\031AAu!\021\031\004(a;\021\007]\013i/C\002\002pb\023\001\"Q2u_J\024VM\032\005\b\003gLA\021AA{\003\031\031'/Z1uKR\0311$a>\t\017A\n\t\0201\001\002zB!an]Av\021!I\022\"!A\005\002\006uHcC\016\002\000\n\005!1\001B\003\005\017A\001\002KA~!\003\005\rA\013\005\ta\005m\b\023!a\001e!AA)a?\021\002\003\007a\t\003\005P\003w\004\n\0211\001;\021!!\0261 I\001\002\0041\006\"\003B\006\023\005\005I\021\021B\007\003\035)h.\0319qYf$BAa\004\003\030A!Qb\022B\t!!i!1\003\0263\rj2\026b\001B\013\035\t1A+\0369mKVB\021B!\007\003\n\005\005\t\031A\016\002\007a$\003\007C\005\003\036%\t\n\021\"\001\002J\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIEB\021B!\t\n#\003%\t!a\031\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0233\021%\021)#CI\001\n\003\tY'A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$He\r\005\n\005SI\021\023!C\001\003g\n1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\"\004\"\003B\027\023E\005I\021AA>\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%k!I!\021G\005\022\002\023\005\021\021J\001\020CB\004H.\037\023eK\032\fW\017\034;%c!I!QG\005\022\002\023\005\0211M\001\020CB\004H.\037\023eK\032\fW\017\034;%e!I!\021H\005\022\002\023\005\0211N\001\020CB\004H.\037\023eK\032\fW\017\034;%g!I!QH\005\022\002\023\005\0211O\001\020CB\004H.\037\023eK\032\fW\017\034;%i!I!\021I\005\022\002\023\005\0211P\001\020CB\004H.\037\023eK\032\fW\017\034;%k!I!QI\005\002\002\023%!qI\001\fe\026\fGMU3t_24X\r\006\002\003JA\031aNa\023\n\007\t5sN\001\004PE*,7\r\036\025\b\023\005=\027Q[AmQ\035I\021qZAk\0033Ds\001AAh\003+\fI\016")
/*     */ public class RoundRobinRouter implements DeprecatedRouterConfig, PoolOverrideUnsetConfig<RoundRobinRouter>, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final Iterable<String> routees;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   public static class RoundRobinRouter$$anonfun$1 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$1) {
/*  64 */       return x$1.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 102 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 102 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 102 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 102 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 102 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 102 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 102 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Props props() {
/* 102 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 102 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 102 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 102 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 102 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/* 102 */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Iterable<String> routees() {
/* 102 */     return this.routees;
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/* 102 */     return this.resizer;
/*     */   }
/*     */   
/*     */   public RoundRobinRouter copy(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 102 */     return new RoundRobinRouter(nrOfInstances, routees, resizer, 
/* 103 */         routerDispatcher, 
/* 104 */         supervisorStrategy);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*     */     return nrOfInstances();
/*     */   }
/*     */   
/*     */   public Iterable<String> copy$default$2() {
/*     */     return routees();
/*     */   }
/*     */   
/*     */   public Option<Resizer> copy$default$3() {
/*     */     return resizer();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "RoundRobinRouter";
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
/*     */     return x$1 instanceof RoundRobinRouter;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(routees()));
/*     */     i = Statics.mix(i, Statics.anyHash(resizer()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
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
/*     */     //   2: if_acmpeq -> 188
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/RoundRobinRouter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 192
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RoundRobinRouter
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 184
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual routees : ()Lscala/collection/immutable/Iterable;
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual routees : ()Lscala/collection/immutable/Iterable;
/*     */     //   52: astore #5
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 67
/*     */     //   58: pop
/*     */     //   59: aload #5
/*     */     //   61: ifnull -> 75
/*     */     //   64: goto -> 184
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 184
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual resizer : ()Lscala/Option;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual resizer : ()Lscala/Option;
/*     */     //   84: astore #6
/*     */     //   86: dup
/*     */     //   87: ifnonnull -> 99
/*     */     //   90: pop
/*     */     //   91: aload #6
/*     */     //   93: ifnull -> 107
/*     */     //   96: goto -> 184
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 184
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
/*     */     //   128: goto -> 184
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 184
/*     */     //   139: aload_0
/*     */     //   140: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   143: aload #4
/*     */     //   145: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   148: astore #8
/*     */     //   150: dup
/*     */     //   151: ifnonnull -> 163
/*     */     //   154: pop
/*     */     //   155: aload #8
/*     */     //   157: ifnull -> 171
/*     */     //   160: goto -> 184
/*     */     //   163: aload #8
/*     */     //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 184
/*     */     //   171: aload #4
/*     */     //   173: aload_0
/*     */     //   174: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   177: ifeq -> 184
/*     */     //   180: iconst_1
/*     */     //   181: goto -> 185
/*     */     //   184: iconst_0
/*     */     //   185: ifeq -> 192
/*     */     //   188: iconst_1
/*     */     //   189: goto -> 193
/*     */     //   192: iconst_0
/*     */     //   193: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #102	-> 0
/*     */     //   #63	-> 14
/*     */     //   #102	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	194	0	this	Lakka/routing/RoundRobinRouter;
/*     */     //   0	194	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RoundRobinRouter(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     PoolOverrideUnsetConfig$class.$init$(this);
/*     */     Product.class.$init$(this);
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
/*     */   public SupervisorStrategy supervisorStrategy() {
/* 104 */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$5() {
/* 104 */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public RoundRobinRouter(int nr) {
/* 110 */     this(nr, RoundRobinRouter$.MODULE$.$lessinit$greater$default$2(), RoundRobinRouter$.MODULE$.$lessinit$greater$default$3(), RoundRobinRouter$.MODULE$.$lessinit$greater$default$4(), RoundRobinRouter$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public RoundRobinRouter(Iterable routeePaths) {
/* 118 */     this(x$12, (Iterable<String>)x$11, x$13, x$14, x$15);
/*     */   }
/*     */   
/*     */   public RoundRobinRouter(Resizer resizer) {
/* 123 */     this(x$17, x$18, (Option<Resizer>)x$16, x$19, x$20);
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/* 125 */     return routees();
/*     */   }
/*     */   
/*     */   public RoundRobinRouter withDispatcher(String dispatcherId) {
/* 130 */     String x$21 = dispatcherId;
/* 130 */     int x$22 = copy$default$1();
/* 130 */     Iterable<String> x$23 = copy$default$2();
/* 130 */     Option<Resizer> x$24 = copy$default$3();
/* 130 */     SupervisorStrategy x$25 = copy$default$5();
/* 130 */     return copy(x$22, x$23, x$24, x$21, x$25);
/*     */   }
/*     */   
/*     */   public RoundRobinRouter withSupervisorStrategy(SupervisorStrategy strategy) {
/* 136 */     SupervisorStrategy x$26 = strategy;
/* 136 */     int x$27 = copy$default$1();
/* 136 */     Iterable<String> x$28 = copy$default$2();
/* 136 */     Option<Resizer> x$29 = copy$default$3();
/* 136 */     String x$30 = copy$default$4();
/* 136 */     return copy(x$27, x$28, x$29, x$30, x$26);
/*     */   }
/*     */   
/*     */   public RoundRobinRouter withResizer(Resizer resizer) {
/* 141 */     Some x$31 = new Some(resizer);
/* 141 */     int x$32 = copy$default$1();
/* 141 */     Iterable<String> x$33 = copy$default$2();
/* 141 */     String x$34 = copy$default$4();
/* 141 */     SupervisorStrategy x$35 = copy$default$5();
/* 141 */     return copy(x$32, x$33, (Option<Resizer>)x$31, x$34, x$35);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 148 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 150 */     return new Router(RoundRobinRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$5() {
/*     */     return RoundRobinRouter$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return RoundRobinRouter$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$3() {
/*     */     return RoundRobinRouter$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> apply$default$2() {
/*     */     return RoundRobinRouter$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return RoundRobinRouter$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$5() {
/*     */     return RoundRobinRouter$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return RoundRobinRouter$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$3() {
/*     */     return RoundRobinRouter$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> $lessinit$greater$default$2() {
/*     */     return RoundRobinRouter$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return RoundRobinRouter$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static RoundRobinRouter create(Iterable<ActorRef> paramIterable) {
/*     */     return RoundRobinRouter$.MODULE$.create(paramIterable);
/*     */   }
/*     */   
/*     */   public static RoundRobinRouter apply(Iterable<ActorRef> paramIterable) {
/*     */     return RoundRobinRouter$.MODULE$.apply(paramIterable);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoundRobinRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */