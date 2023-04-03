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
/*     */ import scala.Product;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005f\001B\001\003\005\036\0211BU1oI>lwI]8va*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001aE\003\001\0219\021R\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021Qa\022:pkB\004\"!C\n\n\005QQ!a\002)s_\022,8\r\036\t\003\023YI!a\006\006\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005Bi\tQ\001]1uQN,\022a\007\t\0049\005\032S\"A\017\013\005yy\022!C5n[V$\030M\0317f\025\t\001#\"\001\006d_2dWm\031;j_:L!AI\017\003\021%#XM]1cY\026\004\"\001J\024\017\005%)\023B\001\024\013\003\031\001&/\0323fM&\021\001&\013\002\007'R\024\030N\\4\013\005\031R\001\002C\026\001\005#\005\013\021B\016\002\rA\fG\017[:!\021!i\003A!f\001\n\003r\023\001\005:pkR,'\017R5ta\006$8\r[3s+\005\031\003\002\003\031\001\005#\005\013\021B\022\002#I|W\017^3s\t&\034\b/\031;dQ\026\024\b\005C\0033\001\021\0051'\001\004=S:LGO\020\013\004iU2\004CA\b\001\021\025I\022\0071\001\034\021\035i\023\007%AA\002\rBQA\r\001\005\002a\"\"\001N\035\t\013i:\004\031A\036\002\r\r|gNZ5h!\ta$)D\001>\025\tQdH\003\002@\001\006AA/\0379fg\0064WMC\001B\003\r\031w.\\\005\003\007v\022aaQ8oM&<\007\"\002\032\001\t\003)EC\001\033G\021\0259E\t1\001I\003-\021x.\036;fKB\013G\017[:\021\007%s5%D\001K\025\tYE*\001\003mC:<'\"A'\002\t)\fg/Y\005\003E)CQ\001\025\001\005BE\013Ab\031:fCR,'k\\;uKJ$\"AU+\021\005=\031\026B\001+\003\005\031\021v.\036;fe\")ak\024a\001/\00611/_:uK6\004\"\001W.\016\003eS!A\027\003\002\013\005\034Go\034:\n\005qK&aC!di>\0248+_:uK6DQA\030\001\005\002}\013ab^5uQ\022K7\017]1uG\",'\017\006\0025A\")\021-\030a\001G\005aA-[:qCR\034\007.\032:JI\"91\rAA\001\n\003!\027\001B2paf$2\001N3g\021\035I\"\r%AA\002mAq!\f2\021\002\003\0071\005C\004i\001E\005I\021A5\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t!N\013\002\034W.\nA\016\005\002ne6\taN\003\002pa\006IQO\\2iK\016\\W\r\032\006\003c*\t!\"\0318o_R\fG/[8o\023\t\031hNA\tv]\016DWmY6fIZ\013'/[1oG\026Dq!\036\001\022\002\023\005a/\001\bd_BLH\005Z3gCVdG\017\n\032\026\003]T#aI6\t\017e\004\021\021!C!u\006i\001O]8ek\016$\bK]3gSb,\022a\037\t\003\023rL!\001\013&\t\017y\004\021\021!C\001\006a\001O]8ek\016$\030I]5usV\021\021\021\001\t\004\023\005\r\021bAA\003\025\t\031\021J\034;\t\023\005%\001!!A\005\002\005-\021A\0049s_\022,8\r^#mK6,g\016\036\013\005\003\033\t\031\002E\002\n\003\037I1!!\005\013\005\r\te.\037\005\013\003+\t9!!AA\002\005\005\021a\001=%c!I\021\021\004\001\002\002\023\005\0231D\001\020aJ|G-^2u\023R,'/\031;peV\021\021Q\004\t\007\003?\t\t#!\004\016\003}I1!a\t \005!IE/\032:bi>\024\b\"CA\024\001\005\005I\021AA\025\003!\031\027M\\#rk\006dG\003BA\026\003c\0012!CA\027\023\r\tyC\003\002\b\005>|G.Z1o\021)\t)\"!\n\002\002\003\007\021Q\002\005\n\003k\001\021\021!C!\003o\t\001\002[1tQ\016{G-\032\013\003\003\003A\021\"a\017\001\003\003%\t%!\020\002\021Q|7\013\036:j]\036$\022a\037\005\n\003\003\002\021\021!C!\003\007\na!Z9vC2\034H\003BA\026\003\013B!\"!\006\002@\005\005\t\031AA\007Q\025\001\021\021JA(!\rI\0211J\005\004\003\033R!\001E*fe&\fGNV3sg&|g.V%E=\005\tq!CA*\005\005\005\t\022AA+\003-\021\026M\0343p[\036\023x.\0369\021\007=\t9F\002\005\002\005\005\005\t\022AA-'\025\t9&a\027\026!\035\ti&a\031\034GQj!!a\030\013\007\005\005$\"A\004sk:$\030.\\3\n\t\005\025\024q\f\002\022\003\n\034HO]1di\032+hn\031;j_:\024\004b\002\032\002X\021\005\021\021\016\013\003\003+B!\"a\017\002X\005\005IQIA\037\021)\ty'a\026\002\002\023\005\025\021O\001\006CB\004H.\037\013\006i\005M\024Q\017\005\0073\0055\004\031A\016\t\0215\ni\007%AA\002\rB!\"!\037\002X\005\005I\021QA>\003\035)h.\0319qYf$B!! \002\nB)\021\"a \002\004&\031\021\021\021\006\003\r=\003H/[8o!\025I\021QQ\016$\023\r\t9I\003\002\007)V\004H.\032\032\t\023\005-\025qOA\001\002\004!\024a\001=%a!I\021qRA,#\003%\tA^\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\032\t\023\005M\025qKI\001\n\0031\030aD1qa2LH\005Z3gCVdG\017\n\032\t\025\005]\025qKA\001\n\023\tI*A\006sK\006$'+Z:pYZ,GCAAN!\rI\025QT\005\004\003?S%AB(cU\026\034G\017")
/*     */ public final class RandomGroup implements Group, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Iterable<String> paths;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   public Props props() {
/* 121 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 121 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 121 */     return Group$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 121 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 121 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 121 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 121 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 121 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public RandomGroup copy(Iterable<String> paths, String routerDispatcher) {
/* 121 */     return new RandomGroup(
/* 122 */         paths, 
/* 123 */         routerDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "RandomGroup";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
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
/*     */     return x$1 instanceof RandomGroup;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   2: if_acmpeq -> 103
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/RandomGroup
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 107
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RandomGroup
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
/*     */     //   52: goto -> 99
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 99
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 99
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 99
/*     */     //   95: iconst_1
/*     */     //   96: goto -> 100
/*     */     //   99: iconst_0
/*     */     //   100: ifeq -> 107
/*     */     //   103: iconst_1
/*     */     //   104: goto -> 108
/*     */     //   107: iconst_0
/*     */     //   108: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     //   #63	-> 14
/*     */     //   #121	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	109	0	this	Lakka/routing/RandomGroup;
/*     */     //   0	109	1	x$1	Ljava/lang/Object;
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
/*     */   public RandomGroup(Iterable<String> paths, String routerDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/* 123 */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/* 123 */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public RandomGroup(Config config) {
/* 127 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(config.getStringList("routees.paths")), RandomGroup$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public RandomGroup(Iterable routeePaths) {
/* 134 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(routeePaths), RandomGroup$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 136 */     return new Router(RandomRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public RandomGroup withDispatcher(String dispatcherId) {
/* 142 */     String x$21 = dispatcherId;
/* 142 */     Iterable<String> x$22 = copy$default$1();
/* 142 */     return copy(x$22, x$21);
/*     */   }
/*     */   
/*     */   public static String apply$default$2() {
/*     */     return RandomGroup$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$2() {
/*     */     return RandomGroup$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Iterable<String>, String>, RandomGroup> tupled() {
/*     */     return RandomGroup$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Iterable<String>, Function1<String, RandomGroup>> curried() {
/*     */     return RandomGroup$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RandomGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */