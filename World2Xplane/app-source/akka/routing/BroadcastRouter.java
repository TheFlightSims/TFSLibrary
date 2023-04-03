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
/*     */ @ScalaSignature(bytes = "\006\001\tUs!B\001\003\021\0039\021a\004\"s_\006$7-Y:u%>,H/\032:\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021qB\021:pC\022\034\027m\035;S_V$XM]\n\004\0231\021\002CA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!AB!osJ+g\r\005\002\016'%\021AC\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\006-%!\taF\001\007y%t\027\016\036 \025\003\035AQ!G\005\005\002i\tQ!\0319qYf$2aGAt!\tAAD\002\003\013\005\001k2C\002\017\r=\005\"#\003\005\002\t?%\021\001E\001\002\027\t\026\004(/Z2bi\026$'k\\;uKJ\034uN\0344jOB\031\001BI\016\n\005\r\022!a\006)p_2|e/\032:sS\022,WK\\:fi\016{gNZ5h!\tiQ%\003\002'\035\t9\001K]8ek\016$\b\002\003\025\035\005+\007I\021A\025\002\0339\024xJZ%ogR\fgnY3t+\005Q\003CA\007,\023\tacBA\002J]RD\001B\f\017\003\022\003\006IAK\001\017]J|e-\0238ti\006t7-Z:!\021!\001DD!f\001\n\003\t\024a\002:pkR,Wm]\013\002eA\0311\007\017\036\016\003QR!!\016\034\002\023%lW.\036;bE2,'BA\034\017\003)\031w\016\0347fGRLwN\\\005\003sQ\022\001\"\023;fe\006\024G.\032\t\003wyr!!\004\037\n\005ur\021A\002)sK\022,g-\003\002@\001\n11\013\036:j]\036T!!\020\b\t\021\tc\"\021#Q\001\nI\n\001B]8vi\026,7\017\t\005\t\tr\021)\032!C!\013\0069!/Z:ju\026\024X#\001$\021\00759\025*\003\002I\035\t1q\n\035;j_:\004\"\001\003&\n\005-\023!a\002*fg&TXM\035\005\t\033r\021\t\022)A\005\r\006A!/Z:ju\026\024\b\005\003\005P9\tU\r\021\"\001Q\003A\021x.\036;fe\022K7\017]1uG\",'/F\001;\021!\021FD!E!\002\023Q\024!\005:pkR,'\017R5ta\006$8\r[3sA!AA\013\bBK\002\023\005Q+\001\ntkB,'O^5t_J\034FO]1uK\036LX#\001,\021\005]SV\"\001-\013\005e#\021!B1di>\024\030BA.Y\005I\031V\017]3sm&\034xN]*ue\006$XmZ=\t\021uc\"\021#Q\001\nY\0131c];qKJ4\030n]8s'R\024\030\r^3hs\002BQA\006\017\005\002}#ba\0071bE\016$\007b\002\025_!\003\005\rA\013\005\bay\003\n\0211\0013\021\035!e\f%AA\002\031Cqa\0240\021\002\003\007!\bC\004U=B\005\t\031\001,\t\013YaB\021\0014\025\005m9\007\"\0025f\001\004Q\023A\0018s\021\0251B\004\"\001k)\tY2\016C\003mS\002\007Q.A\006s_V$X-\032)bi\"\034\bc\0018tu5\tqN\003\002qc\006!A.\0318h\025\005\021\030\001\0026bm\006L!!O8\t\013YaB\021A;\025\005m1\b\"\002#u\001\004I\005\"\002=\035\t\003\n\024!\0029bi\"\034\b\"\002>\035\t\003Y\030AD<ji\"$\025n\0359bi\016DWM\035\013\0037qDQ!`=A\002i\nA\002Z5ta\006$8\r[3s\023\022Daa \017\005\002\005\005\021AF<ji\"\034V\017]3sm&\034xN]*ue\006$XmZ=\025\007m\t\031\001\003\004\002\006y\004\rAV\001\tgR\024\030\r^3hs\"9\021\021\002\017\005\002\005-\021aC<ji\"\024Vm]5{KJ$2aGA\007\021\031!\025q\001a\001\023\"9\021\021\003\017\005B\005M\021\001D<ji\"4\025\r\0347cC\016\\G\003BA\013\0037\0012\001CA\f\023\r\tIB\001\002\r%>,H/\032:D_:4\027n\032\005\t\003;\ty\0011\001\002\026\005)q\016\0365fe\"9\021\021\005\017\005B\005\r\022\001D2sK\006$XMU8vi\026\024H\003BA\023\003W\0012\001CA\024\023\r\tIC\001\002\007%>,H/\032:\t\021\0055\022q\004a\001\003_\taa]=ti\026l\007cA,\0022%\031\0211\007-\003\027\005\033Go\034:TsN$X-\034\005\n\003oa\022\021!C\001\003s\tAaY8qsRY1$a\017\002>\005}\022\021IA\"\021!A\023Q\007I\001\002\004Q\003\002\003\031\0026A\005\t\031\001\032\t\021\021\013)\004%AA\002\031C\001bTA\033!\003\005\rA\017\005\t)\006U\002\023!a\001-\"I\021q\t\017\022\002\023\005\021\021J\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\t\tYEK\002+\003\033Z#!a\024\021\t\005E\0231L\007\003\003'RA!!\026\002X\005IQO\\2iK\016\\W\r\032\006\004\0033r\021AC1o]>$\030\r^5p]&!\021QLA*\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\003Cb\022\023!C\001\003G\nabY8qs\022\"WMZ1vYR$#'\006\002\002f)\032!'!\024\t\023\005%D$%A\005\002\005-\024AD2paf$C-\0324bk2$HeM\013\003\003[R3ARA'\021%\t\t\bHI\001\n\003\t\031(\001\bd_BLH\005Z3gCVdG\017\n\033\026\005\005U$f\001\036\002N!I\021\021\020\017\022\002\023\005\0211P\001\017G>\004\030\020\n3fM\006,H\016\036\0236+\t\tiHK\002W\003\033B\021\"!!\035\003\003%\t%a!\002\033A\024x\016Z;diB\023XMZ5y+\t\t)\tE\002o\003\017K!aP8\t\021\005-E$!A\005\002%\nA\002\035:pIV\034G/\021:jifD\021\"a$\035\003\003%\t!!%\002\035A\024x\016Z;di\026cW-\\3oiR!\0211SAM!\ri\021QS\005\004\003/s!aA!os\"I\0211TAG\003\003\005\rAK\001\004q\022\n\004\"CAP9\005\005I\021IAQ\003=\001(o\0343vGRLE/\032:bi>\024XCAAR!\031\t)+a*\002\0246\ta'C\002\002*Z\022\001\"\023;fe\006$xN\035\005\n\003[c\022\021!C\001\003_\013\001bY1o\013F,\030\r\034\013\005\003c\0139\fE\002\016\003gK1!!.\017\005\035\021un\0347fC:D!\"a'\002,\006\005\t\031AAJ\021%\tY\fHA\001\n\003\ni,\001\005iCND7i\0343f)\005Q\003\"CAa9\005\005I\021IAb\003!!xn\025;sS:<GCAAC\021%\t9\rHA\001\n\003\nI-\001\004fcV\fGn\035\013\005\003c\013Y\r\003\006\002\034\006\025\027\021!a\001\003'Cs\001HAh\003+\fI\016E\002\016\003#L1!a5\017\005)!W\r\035:fG\006$X\rZ\021\003\003/\f1%V:fA\t\023x.\0313dCN$\bk\\8mA=\024\bE\021:pC\022\034\027m\035;He>,\b/\t\002\002\\\006\031!GL\032)\013q\ty.!:\021\0075\t\t/C\002\002d:\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\005Aa\001\r\rA\002\005%\b\003B\0329\003W\0042aVAw\023\r\ty\017\027\002\t\003\016$xN\035*fM\"9\0211_\005\005\002\005U\030AB2sK\006$X\rF\002\034\003oDq\001MAy\001\004\tI\020\005\003og\006-\b\002C\r\n\003\003%\t)!@\025\027m\tyP!\001\003\004\t\025!q\001\005\tQ\005m\b\023!a\001U!A\001'a?\021\002\003\007!\007\003\005E\003w\004\n\0211\001G\021!y\0251 I\001\002\004Q\004\002\003+\002|B\005\t\031\001,\t\023\t-\021\"!A\005\002\n5\021aB;oCB\004H.\037\013\005\005\037\0219\002\005\003\016\017\nE\001\003C\007\003\024)\022dI\017,\n\007\tUaB\001\004UkBdW-\016\005\n\0053\021I!!AA\002m\t1\001\037\0231\021%\021i\"CI\001\n\003\tI%A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\r\005\n\005CI\021\023!C\001\003G\n1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004\"\003B\023\023E\005I\021AA6\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%g!I!\021F\005\022\002\023\005\0211O\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\033\t\023\t5\022\"%A\005\002\005m\024a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$S\007C\005\0032%\t\n\021\"\001\002J\005y\021\r\0359ms\022\"WMZ1vYR$\023\007C\005\0036%\t\n\021\"\001\002d\005y\021\r\0359ms\022\"WMZ1vYR$#\007C\005\003:%\t\n\021\"\001\002l\005y\021\r\0359ms\022\"WMZ1vYR$3\007C\005\003>%\t\n\021\"\001\002t\005y\021\r\0359ms\022\"WMZ1vYR$C\007C\005\003B%\t\n\021\"\001\002|\005y\021\r\0359ms\022\"WMZ1vYR$S\007C\005\003F%\t\t\021\"\003\003H\005Y!/Z1e%\026\034x\016\034<f)\t\021I\005E\002o\005\027J1A!\024p\005\031y%M[3di\":\021\"a4\002V\006e\007fB\005\002P\006U\027\021\034\025\b\001\005=\027Q[Am\001")
/*     */ public class BroadcastRouter implements DeprecatedRouterConfig, PoolOverrideUnsetConfig<BroadcastRouter>, Product, Serializable {
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
/*     */   public static class BroadcastRouter$$anonfun$4 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$4) {
/* 356 */       return x$4.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 394 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 394 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 394 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 394 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 394 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 394 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 394 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Props props() {
/* 394 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 394 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 394 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 394 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 394 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/* 394 */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Iterable<String> routees() {
/* 394 */     return this.routees;
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/* 394 */     return this.resizer;
/*     */   }
/*     */   
/*     */   public BroadcastRouter copy(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 394 */     return new BroadcastRouter(nrOfInstances, routees, resizer, 
/* 395 */         routerDispatcher, 
/* 396 */         supervisorStrategy);
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
/*     */     return "BroadcastRouter";
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
/*     */     return x$1 instanceof BroadcastRouter;
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
/*     */     //   8: instanceof akka/routing/BroadcastRouter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 192
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/BroadcastRouter
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
/*     */     //   #394	-> 0
/*     */     //   #63	-> 14
/*     */     //   #394	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	194	0	this	Lakka/routing/BroadcastRouter;
/*     */     //   0	194	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public BroadcastRouter(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
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
/* 396 */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$5() {
/* 396 */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public BroadcastRouter(int nr) {
/* 402 */     this(nr, BroadcastRouter$.MODULE$.$lessinit$greater$default$2(), BroadcastRouter$.MODULE$.$lessinit$greater$default$3(), BroadcastRouter$.MODULE$.$lessinit$greater$default$4(), BroadcastRouter$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public BroadcastRouter(Iterable routeePaths) {
/* 410 */     this(x$102, (Iterable<String>)x$101, x$103, x$104, x$105);
/*     */   }
/*     */   
/*     */   public BroadcastRouter(Resizer resizer) {
/* 415 */     this(x$107, x$108, (Option<Resizer>)x$106, x$109, x$110);
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/* 417 */     return routees();
/*     */   }
/*     */   
/*     */   public BroadcastRouter withDispatcher(String dispatcherId) {
/* 422 */     String x$111 = dispatcherId;
/* 422 */     int x$112 = copy$default$1();
/* 422 */     Iterable<String> x$113 = copy$default$2();
/* 422 */     Option<Resizer> x$114 = copy$default$3();
/* 422 */     SupervisorStrategy x$115 = copy$default$5();
/* 422 */     return copy(x$112, x$113, x$114, x$111, x$115);
/*     */   }
/*     */   
/*     */   public BroadcastRouter withSupervisorStrategy(SupervisorStrategy strategy) {
/* 428 */     SupervisorStrategy x$116 = strategy;
/* 428 */     int x$117 = copy$default$1();
/* 428 */     Iterable<String> x$118 = copy$default$2();
/* 428 */     Option<Resizer> x$119 = copy$default$3();
/* 428 */     String x$120 = copy$default$4();
/* 428 */     return copy(x$117, x$118, x$119, x$120, x$116);
/*     */   }
/*     */   
/*     */   public BroadcastRouter withResizer(Resizer resizer) {
/* 433 */     Some x$121 = new Some(resizer);
/* 433 */     int x$122 = copy$default$1();
/* 433 */     Iterable<String> x$123 = copy$default$2();
/* 433 */     String x$124 = copy$default$4();
/* 433 */     SupervisorStrategy x$125 = copy$default$5();
/* 433 */     return copy(x$122, x$123, (Option<Resizer>)x$121, x$124, x$125);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 440 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 442 */     return new Router(BroadcastRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$5() {
/*     */     return BroadcastRouter$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return BroadcastRouter$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$3() {
/*     */     return BroadcastRouter$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> apply$default$2() {
/*     */     return BroadcastRouter$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return BroadcastRouter$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$5() {
/*     */     return BroadcastRouter$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return BroadcastRouter$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$3() {
/*     */     return BroadcastRouter$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> $lessinit$greater$default$2() {
/*     */     return BroadcastRouter$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return BroadcastRouter$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static BroadcastRouter create(Iterable<ActorRef> paramIterable) {
/*     */     return BroadcastRouter$.MODULE$.create(paramIterable);
/*     */   }
/*     */   
/*     */   public static BroadcastRouter apply(Iterable<ActorRef> paramIterable) {
/*     */     return BroadcastRouter$.MODULE$.apply(paramIterable);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BroadcastRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */