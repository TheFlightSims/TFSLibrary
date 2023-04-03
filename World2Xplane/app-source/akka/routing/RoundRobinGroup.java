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
/*     */ @ScalaSignature(bytes = "\006\001\005\005f\001B\001\003\005\036\021qBU8v]\022\024vNY5o\017J|W\017\035\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001M)\001\001\003\b\023+A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\013\035\023x.\0369\021\005%\031\022B\001\013\013\005\035\001&o\0343vGR\004\"!\003\f\n\005]Q!\001D*fe&\fG.\033>bE2,\007\002C\r\001\005+\007I\021\t\016\002\013A\fG\017[:\026\003m\0012\001H\021$\033\005i\"B\001\020 \003%IW.\\;uC\ndWM\003\002!\025\005Q1m\0347mK\016$\030n\0348\n\005\tj\"\001C%uKJ\f'\r\\3\021\005\021:cBA\005&\023\t1#\"\001\004Qe\026$WMZ\005\003Q%\022aa\025;sS:<'B\001\024\013\021!Y\003A!E!\002\023Y\022A\0029bi\"\034\b\005\003\005.\001\tU\r\021\"\021/\003A\021x.\036;fe\022K7\017]1uG\",'/F\001$\021!\001\004A!E!\002\023\031\023!\005:pkR,'\017R5ta\006$8\r[3sA!)!\007\001C\001g\0051A(\0338jiz\"2\001N\0337!\ty\001\001C\003\032c\001\0071\004C\004.cA\005\t\031A\022\t\013I\002A\021\001\035\025\005QJ\004\"\002\0368\001\004Y\024AB2p]\032Lw\r\005\002=\0056\tQH\003\002;})\021q\bQ\001\tif\004Xm]1gK*\t\021)A\002d_6L!aQ\037\003\r\r{gNZ5h\021\025\021\004\001\"\001F)\t!d\tC\003H\t\002\007\001*A\006s_V$X-\032)bi\"\034\bcA%OG5\t!J\003\002L\031\006!A.\0318h\025\005i\025\001\0026bm\006L!A\t&\t\013A\003A\021I)\002\031\r\024X-\031;f%>,H/\032:\025\005I+\006CA\bT\023\t!&A\001\004S_V$XM\035\005\006->\003\raV\001\007gf\034H/Z7\021\005a[V\"A-\013\005i#\021!B1di>\024\030B\001/Z\005-\t5\r^8s'f\034H/Z7\t\013y\003A\021A0\002\035]LG\017\033#jgB\fGo\0315feR\021A\007\031\005\006Cv\003\raI\001\rI&\034\b/\031;dQ\026\024\030\n\032\005\bG\002\t\t\021\"\001e\003\021\031w\016]=\025\007Q*g\rC\004\032EB\005\t\031A\016\t\0175\022\007\023!a\001G!9\001\016AI\001\n\003I\027AD2paf$C-\0324bk2$H%M\013\002U*\0221d[\026\002YB\021QN]\007\002]*\021q\016]\001\nk:\034\007.Z2lK\022T!!\035\006\002\025\005tgn\034;bi&|g.\003\002t]\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017U\004\021\023!C\001m\006q1m\0349zI\021,g-Y;mi\022\022T#A<+\005\rZ\007bB=\001\003\003%\tE_\001\016aJ|G-^2u!J,g-\033=\026\003m\004\"!\023?\n\005!R\005b\002@\001\003\003%\ta`\001\raJ|G-^2u\003JLG/_\013\003\003\003\0012!CA\002\023\r\t)A\003\002\004\023:$\b\"CA\005\001\005\005I\021AA\006\0039\001(o\0343vGR,E.Z7f]R$B!!\004\002\024A\031\021\"a\004\n\007\005E!BA\002B]fD!\"!\006\002\b\005\005\t\031AA\001\003\rAH%\r\005\n\0033\001\021\021!C!\0037\tq\002\035:pIV\034G/\023;fe\006$xN]\013\003\003;\001b!a\b\002\"\0055Q\"A\020\n\007\005\rrD\001\005Ji\026\024\030\r^8s\021%\t9\003AA\001\n\003\tI#\001\005dC:,\025/^1m)\021\tY#!\r\021\007%\ti#C\002\0020)\021qAQ8pY\026\fg\016\003\006\002\026\005\025\022\021!a\001\003\033A\021\"!\016\001\003\003%\t%a\016\002\021!\f7\017[\"pI\026$\"!!\001\t\023\005m\002!!A\005B\005u\022\001\003;p'R\024\030N\\4\025\003mD\021\"!\021\001\003\003%\t%a\021\002\r\025\fX/\0317t)\021\tY#!\022\t\025\005U\021qHA\001\002\004\ti\001K\003\001\003\023\ny\005E\002\n\003\027J1!!\024\013\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017%\t\031FAA\001\022\003\t)&A\bS_VtGMU8cS:<%o\\;q!\ry\021q\013\004\t\003\t\t\t\021#\001\002ZM)\021qKA.+A9\021QLA27\r\"TBAA0\025\r\t\tGC\001\beVtG/[7f\023\021\t)'a\030\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\0043\003/\"\t!!\033\025\005\005U\003BCA\036\003/\n\t\021\"\022\002>!Q\021qNA,\003\003%\t)!\035\002\013\005\004\b\017\\=\025\013Q\n\031(!\036\t\re\ti\0071\001\034\021!i\023Q\016I\001\002\004\031\003BCA=\003/\n\t\021\"!\002|\0059QO\\1qa2LH\003BA?\003\023\003R!CA@\003\007K1!!!\013\005\031y\005\017^5p]B)\021\"!\"\034G%\031\021q\021\006\003\rQ+\b\017\\33\021%\tY)a\036\002\002\003\007A'A\002yIAB\021\"a$\002XE\005I\021\001<\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0233\021%\t\031*a\026\022\002\023\005a/A\bbaBd\027\020\n3fM\006,H\016\036\0233\021)\t9*a\026\002\002\023%\021\021T\001\fe\026\fGMU3t_24X\r\006\002\002\034B\031\021*!(\n\007\005}%J\001\004PE*,7\r\036")
/*     */ public final class RoundRobinGroup implements Group, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Iterable<String> paths;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   public Props props() {
/* 126 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 126 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 126 */     return Group$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 126 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 126 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 126 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 126 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 126 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public RoundRobinGroup copy(Iterable<String> paths, String routerDispatcher) {
/* 126 */     return new RoundRobinGroup(
/* 127 */         paths, 
/* 128 */         routerDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "RoundRobinGroup";
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
/*     */     return x$1 instanceof RoundRobinGroup;
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
/*     */     //   8: instanceof akka/routing/RoundRobinGroup
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 107
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RoundRobinGroup
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
/*     */     //   #126	-> 0
/*     */     //   #63	-> 14
/*     */     //   #126	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	109	0	this	Lakka/routing/RoundRobinGroup;
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
/*     */   public RoundRobinGroup(Iterable<String> paths, String routerDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/* 128 */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/* 128 */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public RoundRobinGroup(Config config) {
/* 132 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(config.getStringList("routees.paths")), RoundRobinGroup$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public RoundRobinGroup(Iterable routeePaths) {
/* 139 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(routeePaths), RoundRobinGroup$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 141 */     return new Router(RoundRobinRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public RoundRobinGroup withDispatcher(String dispatcherId) {
/* 147 */     String x$21 = dispatcherId;
/* 147 */     Iterable<String> x$22 = copy$default$1();
/* 147 */     return copy(x$22, x$21);
/*     */   }
/*     */   
/*     */   public static String apply$default$2() {
/*     */     return RoundRobinGroup$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$2() {
/*     */     return RoundRobinGroup$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Iterable<String>, String>, RoundRobinGroup> tupled() {
/*     */     return RoundRobinGroup$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Iterable<String>, Function1<String, RoundRobinGroup>> curried() {
/*     */     return RoundRobinGroup$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoundRobinGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */