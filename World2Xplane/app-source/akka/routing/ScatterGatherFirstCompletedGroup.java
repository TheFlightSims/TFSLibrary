/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.japi.Util$;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-g\001B\001\003\005\036\021\001eU2biR,'oR1uQ\026\024h)\033:ti\016{W\016\0357fi\026$wI]8va*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001aE\003\001\0219\021R\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021Qa\022:pkB\004\"!C\n\n\005QQ!a\002)s_\022,8\r\036\t\003\023YI!a\006\006\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005Bi\tQ\001]1uQN,\022a\007\t\0049\005\032S\"A\017\013\005yy\022!C5n[V$\030M\0317f\025\t\001#\"\001\006d_2dWm\031;j_:L!AI\017\003\021%#XM]1cY\026\004\"\001J\024\017\005%)\023B\001\024\013\003\031\001&/\0323fM&\021\001&\013\002\007'R\024\030N\\4\013\005\031R\001\002C\026\001\005#\005\013\021B\016\002\rA\fG\017[:!\021!i\003A!f\001\n\003q\023AB<ji\"Lg.F\0010!\t\001T'D\0012\025\t\0214'\001\005ekJ\fG/[8o\025\t!$\"\001\006d_:\034WO\035:f]RL!AN\031\003\035\031Kg.\033;f\tV\024\030\r^5p]\"A\001\b\001B\tB\003%q&A\004xSRD\027N\034\021\t\021i\002!Q3A\005Bm\n\001C]8vi\026\024H)[:qCR\034\007.\032:\026\003\rB\001\"\020\001\003\022\003\006IaI\001\022e>,H/\032:ESN\004\030\r^2iKJ\004\003\"B \001\t\003\001\025A\002\037j]&$h\b\006\003B\005\016#\005CA\b\001\021\025Ib\b1\001\034\021\025ic\b1\0010\021\035Qd\b%AA\002\rBQa\020\001\005\002\031#\"!Q$\t\013!+\005\031A%\002\r\r|gNZ5h!\tQ\005+D\001L\025\tAEJ\003\002N\035\006AA/\0379fg\0064WMC\001P\003\r\031w.\\\005\003#.\023aaQ8oM&<\007\"B \001\t\003\031FcA!U;\")QK\025a\001-\006Y!o\\;uK\026\004\026\r\0365t!\r9FlI\007\0021*\021\021LW\001\005Y\006twMC\001\\\003\021Q\027M^1\n\005\tB\006\"B\027S\001\004y\003\"B0\001\t\003\002\027\001D2sK\006$XMU8vi\026\024HCA1e!\ty!-\003\002d\005\t1!k\\;uKJDQ!\0320A\002\031\faa]=ti\026l\007CA4k\033\005A'BA5\005\003\025\t7\r^8s\023\tY\007NA\006BGR|'oU=ti\026l\007\"B7\001\t\003q\027AD<ji\"$\025n\0359bi\016DWM\035\013\003\003>DQ\001\0357A\002\r\nA\002Z5ta\006$8\r[3s\023\022DqA\035\001\002\002\023\0051/\001\003d_BLH\003B!ukZDq!G9\021\002\003\0071\004C\004.cB\005\t\031A\030\t\017i\n\b\023!a\001G!9\001\020AI\001\n\003I\030AD2paf$C-\0324bk2$H%M\013\002u*\0221d_\026\002yB\031Q0!\002\016\003yT1a`A\001\003%)hn\0315fG.,GMC\002\002\004)\t!\"\0318o_R\fG/[8o\023\r\t9A \002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\"CA\006\001E\005I\021AA\007\0039\031w\016]=%I\0264\027-\0367uII*\"!a\004+\005=Z\b\"CA\n\001E\005I\021AA\013\0039\031w\016]=%I\0264\027-\0367uIM*\"!a\006+\005\rZ\b\"CA\016\001\005\005I\021IA\017\0035\001(o\0343vGR\004&/\0324jqV\021\021q\004\t\004/\006\005\022B\001\025Y\021%\t)\003AA\001\n\003\t9#\001\007qe>$Wo\031;Be&$\0300\006\002\002*A\031\021\"a\013\n\007\0055\"BA\002J]RD\021\"!\r\001\003\003%\t!a\r\002\035A\024x\016Z;di\026cW-\\3oiR!\021QGA\036!\rI\021qG\005\004\003sQ!aA!os\"Q\021QHA\030\003\003\005\r!!\013\002\007a$\023\007C\005\002B\001\t\t\021\"\021\002D\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\002FA1\021qIA%\003ki\021aH\005\004\003\027z\"\001C%uKJ\fGo\034:\t\023\005=\003!!A\005\002\005E\023\001C2b]\026\013X/\0317\025\t\005M\023\021\f\t\004\023\005U\023bAA,\025\t9!i\\8mK\006t\007BCA\037\003\033\n\t\0211\001\0026!I\021Q\f\001\002\002\023\005\023qL\001\tQ\006\034\bnQ8eKR\021\021\021\006\005\n\003G\002\021\021!C!\003K\n\001\002^8TiJLgn\032\013\003\003?A\021\"!\033\001\003\003%\t%a\033\002\r\025\fX/\0317t)\021\t\031&!\034\t\025\005u\022qMA\001\002\004\t)\004K\003\001\003c\n9\bE\002\n\003gJ1!!\036\013\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017%\tYHAA\001\022\003\ti(\001\021TG\006$H/\032:HCRDWM\035$jeN$8i\\7qY\026$X\rZ$s_V\004\bcA\b\002\000\031A\021AAA\001\022\003\t\tiE\003\002\000\005\rU\003\005\005\002\006\006-5dL\022B\033\t\t9IC\002\002\n*\tqA];oi&lW-\003\003\002\016\006\035%!E!cgR\024\030m\031;Gk:\034G/[8og!9q(a \005\002\005EECAA?\021)\t\031'a \002\002\023\025\023Q\r\005\013\003/\013y(!A\005\002\006e\025!B1qa2LHcB!\002\034\006u\025q\024\005\0073\005U\005\031A\016\t\r5\n)\n1\0010\021!Q\024Q\023I\001\002\004\031\003BCAR\003\n\t\021\"!\002&\0069QO\\1qa2LH\003BAT\003g\003R!CAU\003[K1!a+\013\005\031y\005\017^5p]B1\021\"a,\034_\rJ1!!-\013\005\031!V\017\0357fg!I\021QWAQ\003\003\005\r!Q\001\004q\022\002\004BCA]\003\n\n\021\"\001\002\026\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIMB!\"!0\002\000E\005I\021AA\013\003=\t\007\017\0357zI\021,g-Y;mi\022\032\004BCAa\003\n\t\021\"\003\002D\006Y!/Z1e%\026\034x\016\034<f)\t\t)\rE\002X\003\017L1!!3Y\005\031y%M[3di\002")
/*     */ public final class ScatterGatherFirstCompletedGroup implements Group, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Iterable<String> paths;
/*     */   
/*     */   private final FiniteDuration within;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   public Props props() {
/* 162 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 162 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 162 */     return Group$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 162 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 162 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 162 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 162 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 162 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedGroup copy(Iterable<String> paths, FiniteDuration within, String routerDispatcher) {
/* 162 */     return new ScatterGatherFirstCompletedGroup(
/* 163 */         paths, 
/* 164 */         within, 
/* 165 */         routerDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ScatterGatherFirstCompletedGroup";
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
/*     */     return paths();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof ScatterGatherFirstCompletedGroup;
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
/*     */     //   2: if_acmpeq -> 135
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ScatterGatherFirstCompletedGroup
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 139
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ScatterGatherFirstCompletedGroup
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
/*     */     //   52: goto -> 131
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 131
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 131
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 131
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   99: aload #4
/*     */     //   101: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   104: astore #7
/*     */     //   106: dup
/*     */     //   107: ifnonnull -> 119
/*     */     //   110: pop
/*     */     //   111: aload #7
/*     */     //   113: ifnull -> 127
/*     */     //   116: goto -> 131
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   124: ifeq -> 131
/*     */     //   127: iconst_1
/*     */     //   128: goto -> 132
/*     */     //   131: iconst_0
/*     */     //   132: ifeq -> 139
/*     */     //   135: iconst_1
/*     */     //   136: goto -> 140
/*     */     //   139: iconst_0
/*     */     //   140: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #162	-> 0
/*     */     //   #63	-> 14
/*     */     //   #162	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	141	0	this	Lakka/routing/ScatterGatherFirstCompletedGroup;
/*     */     //   0	141	1	x$1	Ljava/lang/Object;
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
/*     */   public ScatterGatherFirstCompletedGroup(Iterable<String> paths, FiniteDuration within, String routerDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public FiniteDuration within() {
/*     */     return this.within;
/*     */   }
/*     */   
/*     */   public FiniteDuration copy$default$2() {
/*     */     return within();
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/* 165 */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$3() {
/* 165 */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedGroup(Config config) {
/* 169 */     this(
/* 170 */         (Iterable<String>)Util$.MODULE$.immutableSeq(config.getStringList("routees.paths")), Helpers$ConfigOps$.MODULE$
/* 171 */         .getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(config), "within"), ScatterGatherFirstCompletedGroup$.MODULE$.$lessinit$greater$default$3());
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedGroup(Iterable routeePaths, FiniteDuration within) {
/* 181 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(routeePaths), within, ScatterGatherFirstCompletedGroup$.MODULE$.$lessinit$greater$default$3());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 183 */     return new Router(new ScatterGatherFirstCompletedRoutingLogic(within()));
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedGroup withDispatcher(String dispatcherId) {
/* 189 */     String x$33 = dispatcherId;
/* 189 */     Iterable<String> x$34 = copy$default$1();
/* 189 */     FiniteDuration x$35 = copy$default$2();
/* 189 */     return copy(x$34, x$35, x$33);
/*     */   }
/*     */   
/*     */   public static String apply$default$3() {
/*     */     return ScatterGatherFirstCompletedGroup$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$3() {
/*     */     return ScatterGatherFirstCompletedGroup$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple3<Iterable<String>, FiniteDuration, String>, ScatterGatherFirstCompletedGroup> tupled() {
/*     */     return ScatterGatherFirstCompletedGroup$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Iterable<String>, Function1<FiniteDuration, Function1<String, ScatterGatherFirstCompletedGroup>>> curried() {
/*     */     return ScatterGatherFirstCompletedGroup$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */