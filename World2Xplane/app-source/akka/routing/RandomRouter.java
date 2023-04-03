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
/*     */ @ScalaSignature(bytes = "\006\001\tUs!B\001\003\021\0039\021\001\004*b]\022|WNU8vi\026\024(BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\031I\013g\016Z8n%>,H/\032:\024\007%a!\003\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\t\003\033MI!\001\006\b\003\031M+'/[1mSj\f'\r\\3\t\013YIA\021A\f\002\rqJg.\033;?)\0059\001\"B\r\n\t\003Q\022!B1qa2LHcA\016\002hB\021\001\002\b\004\005\025\t\001Ud\005\004\035\031y\tCE\005\t\003\021}I!\001\t\002\003-\021+\007O]3dCR,GMU8vi\026\0248i\0348gS\036\0042\001\003\022\034\023\t\031#AA\fQ_>dwJ^3se&$W-\0268tKR\034uN\0344jOB\021Q\"J\005\003M9\021q\001\025:pIV\034G\017\003\005)9\tU\r\021\"\001*\0035q'o\0244J]N$\030M\\2fgV\t!\006\005\002\016W%\021AF\004\002\004\023:$\b\002\003\030\035\005#\005\013\021\002\026\002\0359\024xJZ%ogR\fgnY3tA!A\001\007\bBK\002\023\005\021'A\004s_V$X-Z:\026\003I\0022a\r\035;\033\005!$BA\0337\003%IW.\\;uC\ndWM\003\0028\035\005Q1m\0347mK\016$\030n\0348\n\005e\"$\001C%uKJ\f'\r\\3\021\005mrdBA\007=\023\tid\"\001\004Qe\026$WMZ\005\003\001\023aa\025;sS:<'BA\037\017\021!\021ED!E!\002\023\021\024\001\003:pkR,Wm\035\021\t\021\021c\"Q3A\005B\025\013qA]3tSj,'/F\001G!\riq)S\005\003\021:\021aa\0249uS>t\007C\001\005K\023\tY%AA\004SKNL'0\032:\t\0215c\"\021#Q\001\n\031\013\001B]3tSj,'\017\t\005\t\037r\021)\032!C\001!\006\001\"o\\;uKJ$\025n\0359bi\016DWM]\013\002u!A!\013\bB\tB\003%!(A\ts_V$XM\035#jgB\fGo\0315fe\002B\001\002\026\017\003\026\004%\t!V\001\023gV\004XM\035<jg>\0248\013\036:bi\026<\0270F\001W!\t9&,D\001Y\025\tIF!A\003bGR|'/\003\002\\1\n\0212+\0369feZL7o\034:TiJ\fG/Z4z\021!iFD!E!\002\0231\026aE:va\026\024h/[:peN#(/\031;fOf\004\003\"\002\f\035\t\003yFCB\016aC\n\034G\rC\004)=B\005\t\031\001\026\t\017Ar\006\023!a\001e!9AI\030I\001\002\0041\005bB(_!\003\005\rA\017\005\b)z\003\n\0211\001W\021\0251B\004\"\001g)\tYr\rC\003iK\002\007!&\001\002oe\")a\003\bC\001UR\0211d\033\005\006Y&\004\r!\\\001\fe>,H/Z3QCRD7\017E\002ogjj\021a\034\006\003aF\fA\001\\1oO*\t!/\001\003kCZ\f\027BA\035p\021\0251B\004\"\001v)\tYb\017C\003Ei\002\007\021\nC\003y9\021\005\023'A\003qCRD7\017C\003{9\021\00510\001\bxSRDG)[:qCR\034\007.\032:\025\005ma\b\"B?z\001\004Q\024\001\0043jgB\fGo\0315fe&#\007BB@\035\t\003\t\t!\001\fxSRD7+\0369feZL7o\034:TiJ\fG/Z4z)\rY\0221\001\005\007\003\013q\b\031\001,\002\021M$(/\031;fOfDq!!\003\035\t\003\tY!A\006xSRD'+Z:ju\026\024HcA\016\002\016!1A)a\002A\002%Cq!!\005\035\t\003\n\031\"\001\007xSRDg)\0317mE\006\0347\016\006\003\002\026\005m\001c\001\005\002\030%\031\021\021\004\002\003\031I{W\017^3s\007>tg-[4\t\021\005u\021q\002a\001\003+\tQa\034;iKJDq!!\t\035\t\003\n\031#\001\007de\026\fG/\032*pkR,'\017\006\003\002&\005-\002c\001\005\002(%\031\021\021\006\002\003\rI{W\017^3s\021!\ti#a\bA\002\005=\022AB:zgR,W\016E\002X\003cI1!a\rY\005-\t5\r^8s'f\034H/Z7\t\023\005]B$!A\005\002\005e\022\001B2paf$2bGA\036\003{\ty$!\021\002D!A\001&!\016\021\002\003\007!\006\003\0051\003k\001\n\0211\0013\021!!\025Q\007I\001\002\0041\005\002C(\0026A\005\t\031\001\036\t\021Q\013)\004%AA\002YC\021\"a\022\035#\003%\t!!\023\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\021\0211\n\026\004U\00553FAA(!\021\t\t&a\027\016\005\005M#\002BA+\003/\n\021\"\0368dQ\026\0347.\0323\013\007\005ec\"\001\006b]:|G/\031;j_:LA!!\030\002T\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\023\005\005D$%A\005\002\005\r\024AD2paf$C-\0324bk2$HEM\013\003\003KR3AMA'\021%\tI\007HI\001\n\003\tY'\001\bd_BLH\005Z3gCVdG\017J\032\026\005\0055$f\001$\002N!I\021\021\017\017\022\002\023\005\0211O\001\017G>\004\030\020\n3fM\006,H\016\036\0235+\t\t)HK\002;\003\033B\021\"!\037\035#\003%\t!a\037\002\035\r|\007/\037\023eK\032\fW\017\034;%kU\021\021Q\020\026\004-\0065\003\"CAA9\005\005I\021IAB\0035\001(o\0343vGR\004&/\0324jqV\021\021Q\021\t\004]\006\035\025BA p\021!\tY\tHA\001\n\003I\023\001\0049s_\022,8\r^!sSRL\b\"CAH9\005\005I\021AAI\0039\001(o\0343vGR,E.Z7f]R$B!a%\002\032B\031Q\"!&\n\007\005]eBA\002B]fD\021\"a'\002\016\006\005\t\031\001\026\002\007a$\023\007C\005\002 r\t\t\021\"\021\002\"\006y\001O]8ek\016$\030\n^3sCR|'/\006\002\002$B1\021QUAT\003'k\021AN\005\004\003S3$\001C%uKJ\fGo\034:\t\023\0055F$!A\005\002\005=\026\001C2b]\026\013X/\0317\025\t\005E\026q\027\t\004\033\005M\026bAA[\035\t9!i\\8mK\006t\007BCAN\003W\013\t\0211\001\002\024\"I\0211\030\017\002\002\023\005\023QX\001\tQ\006\034\bnQ8eKR\t!\006C\005\002Br\t\t\021\"\021\002D\006AAo\\*ue&tw\r\006\002\002\006\"I\021q\031\017\002\002\023\005\023\021Z\001\007KF,\030\r\\:\025\t\005E\0261\032\005\013\0037\013)-!AA\002\005M\005f\002\017\002P\006U\027\021\034\t\004\033\005E\027bAAj\035\tQA-\0329sK\016\fG/\0323\"\005\005]\027!H+tK\002\022\026M\0343p[B{w\016\034\021pe\002\022\026M\0343p[\036\023x.\0369\"\005\005m\027a\001\032/g!*A$a8\002fB\031Q\"!9\n\007\005\rhB\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021\001\003\00411\001\007\021\021\036\t\005ga\nY\017E\002X\003[L1!a<Y\005!\t5\r^8s%\0264\007bBAz\023\021\005\021Q_\001\007GJ,\027\r^3\025\007m\t9\020C\0041\003c\004\r!!?\021\t9\034\0301\036\005\t3%\t\t\021\"!\002~RY1$a@\003\002\t\r!Q\001B\004\021!A\0231 I\001\002\004Q\003\002\003\031\002|B\005\t\031\001\032\t\021\021\013Y\020%AA\002\031C\001bTA~!\003\005\rA\017\005\t)\006m\b\023!a\001-\"I!1B\005\002\002\023\005%QB\001\bk:\f\007\017\0357z)\021\021yAa\006\021\t59%\021\003\t\t\033\tM!F\r$;-&\031!Q\003\b\003\rQ+\b\017\\36\021%\021IB!\003\002\002\003\0071$A\002yIAB\021B!\b\n#\003%\t!!\023\002\037\005\004\b\017\\=%I\0264\027-\0367uIEB\021B!\t\n#\003%\t!a\031\002\037\005\004\b\017\\=%I\0264\027-\0367uIIB\021B!\n\n#\003%\t!a\033\002\037\005\004\b\017\\=%I\0264\027-\0367uIMB\021B!\013\n#\003%\t!a\035\002\037\005\004\b\017\\=%I\0264\027-\0367uIQB\021B!\f\n#\003%\t!a\037\002\037\005\004\b\017\\=%I\0264\027-\0367uIUB\021B!\r\n#\003%\t!!\023\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0232\021%\021)$CI\001\n\003\t\031'A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$HE\r\005\n\005sI\021\023!C\001\003W\n1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\032\004\"\003B\037\023E\005I\021AA:\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%i!I!\021I\005\022\002\023\005\0211P\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\033\t\023\t\025\023\"!A\005\n\t\035\023a\003:fC\022\024Vm]8mm\026$\"A!\023\021\0079\024Y%C\002\003N=\024aa\0242kK\016$\bfB\005\002P\006U\027\021\034\025\b\023\005=\027Q[AmQ\035\001\021qZAk\0033\004")
/*     */ public class RandomRouter implements DeprecatedRouterConfig, PoolOverrideUnsetConfig<RandomRouter>, Product, Serializable {
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
/*     */   public static class RandomRouter$$anonfun$2 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$2) {
/* 158 */       return x$2.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 196 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 196 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 196 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 196 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 196 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 196 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 196 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Props props() {
/* 196 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 196 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 196 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 196 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 196 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/* 196 */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Iterable<String> routees() {
/* 196 */     return this.routees;
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/* 196 */     return this.resizer;
/*     */   }
/*     */   
/*     */   public RandomRouter copy(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 196 */     return new RandomRouter(nrOfInstances, routees, resizer, 
/* 197 */         routerDispatcher, 
/* 198 */         supervisorStrategy);
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
/*     */     return "RandomRouter";
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
/*     */     return x$1 instanceof RandomRouter;
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
/*     */     //   8: instanceof akka/routing/RandomRouter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 192
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RandomRouter
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
/*     */     //   #196	-> 0
/*     */     //   #63	-> 14
/*     */     //   #196	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	194	0	this	Lakka/routing/RandomRouter;
/*     */     //   0	194	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RandomRouter(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
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
/* 198 */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$5() {
/* 198 */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public RandomRouter(int nr) {
/* 204 */     this(nr, RandomRouter$.MODULE$.$lessinit$greater$default$2(), RandomRouter$.MODULE$.$lessinit$greater$default$3(), RandomRouter$.MODULE$.$lessinit$greater$default$4(), RandomRouter$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public RandomRouter(Iterable routeePaths) {
/* 212 */     this(x$42, (Iterable<String>)x$41, x$43, x$44, x$45);
/*     */   }
/*     */   
/*     */   public RandomRouter(Resizer resizer) {
/* 217 */     this(x$47, x$48, (Option<Resizer>)x$46, x$49, x$50);
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/* 219 */     return routees();
/*     */   }
/*     */   
/*     */   public RandomRouter withDispatcher(String dispatcherId) {
/* 224 */     String x$51 = dispatcherId;
/* 224 */     int x$52 = copy$default$1();
/* 224 */     Iterable<String> x$53 = copy$default$2();
/* 224 */     Option<Resizer> x$54 = copy$default$3();
/* 224 */     SupervisorStrategy x$55 = copy$default$5();
/* 224 */     return copy(x$52, x$53, x$54, x$51, x$55);
/*     */   }
/*     */   
/*     */   public RandomRouter withSupervisorStrategy(SupervisorStrategy strategy) {
/* 230 */     SupervisorStrategy x$56 = strategy;
/* 230 */     int x$57 = copy$default$1();
/* 230 */     Iterable<String> x$58 = copy$default$2();
/* 230 */     Option<Resizer> x$59 = copy$default$3();
/* 230 */     String x$60 = copy$default$4();
/* 230 */     return copy(x$57, x$58, x$59, x$60, x$56);
/*     */   }
/*     */   
/*     */   public RandomRouter withResizer(Resizer resizer) {
/* 235 */     Some x$61 = new Some(resizer);
/* 235 */     int x$62 = copy$default$1();
/* 235 */     Iterable<String> x$63 = copy$default$2();
/* 235 */     String x$64 = copy$default$4();
/* 235 */     SupervisorStrategy x$65 = copy$default$5();
/* 235 */     return copy(x$62, x$63, (Option<Resizer>)x$61, x$64, x$65);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 242 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 244 */     return new Router(RandomRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$5() {
/*     */     return RandomRouter$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return RandomRouter$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$3() {
/*     */     return RandomRouter$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> $lessinit$greater$default$2() {
/*     */     return RandomRouter$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return RandomRouter$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$5() {
/*     */     return RandomRouter$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return RandomRouter$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$3() {
/*     */     return RandomRouter$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> apply$default$2() {
/*     */     return RandomRouter$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return RandomRouter$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static RandomRouter create(Iterable<ActorRef> paramIterable) {
/*     */     return RandomRouter$.MODULE$.create(paramIterable);
/*     */   }
/*     */   
/*     */   public static RandomRouter apply(Iterable<ActorRef> paramIterable) {
/*     */     return RandomRouter$.MODULE$.apply(paramIterable);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RandomRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */