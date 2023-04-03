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
/*     */ @ScalaSignature(bytes = "\006\001\005\005f\001B\001\003\005\036\021aB\021:pC\022\034\027m\035;He>,\bO\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\025\001\001B\004\n\026!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\006\017J|W\017\035\t\003\023MI!\001\006\006\003\017A\023x\016Z;diB\021\021BF\005\003/)\021AbU3sS\006d\027N_1cY\026D\001\"\007\001\003\026\004%\tAG\001\006a\006$\bn]\013\0027A\031A$I\022\016\003uQ!AH\020\002\023%lW.\036;bE2,'B\001\021\013\003)\031w\016\0347fGRLwN\\\005\003Eu\021\001\"\023;fe\006\024G.\032\t\003I\035r!!C\023\n\005\031R\021A\002)sK\022,g-\003\002)S\t11\013\036:j]\036T!A\n\006\t\021-\002!\021#Q\001\nm\ta\001]1uQN\004\003\002C\027\001\005+\007I\021\t\030\002!I|W\017^3s\t&\034\b/\031;dQ\026\024X#A\022\t\021A\002!\021#Q\001\n\r\n\021C]8vi\026\024H)[:qCR\034\007.\032:!\021\025\021\004\001\"\0014\003\031a\024N\\5u}Q\031A'\016\034\021\005=\001\001\"B\r2\001\004Y\002bB\0272!\003\005\ra\t\005\006e\001!\t\001\017\013\003ieBQAO\034A\002m\naaY8oM&<\007C\001\037C\033\005i$B\001\036?\025\ty\004)\001\005usB,7/\0314f\025\005\t\025aA2p[&\0211)\020\002\007\007>tg-[4\t\013I\002A\021A#\025\005Q2\005\"B$E\001\004A\025a\003:pkR,W\rU1uQN\0042!\023($\033\005Q%BA&M\003\021a\027M\\4\013\0035\013AA[1wC&\021!E\023\005\006!\002!\t%U\001\rGJ,\027\r^3S_V$XM\035\013\003%V\003\"aD*\n\005Q\023!A\002*pkR,'\017C\003W\037\002\007q+\001\004tsN$X-\034\t\0031nk\021!\027\006\0035\022\tQ!Y2u_JL!\001X-\003\027\005\033Go\034:TsN$X-\034\005\006=\002!\taX\001\017o&$\b\016R5ta\006$8\r[3s)\t!\004\rC\003b;\002\0071%\001\007eSN\004\030\r^2iKJLE\rC\004d\001\005\005I\021\0013\002\t\r|\007/\037\013\004i\0254\007bB\rc!\003\005\ra\007\005\b[\t\004\n\0211\001$\021\035A\007!%A\005\002%\fabY8qs\022\"WMZ1vYR$\023'F\001kU\tY2nK\001m!\ti'/D\001o\025\ty\007/A\005v]\016DWmY6fI*\021\021OC\001\013C:tw\016^1uS>t\027BA:o\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bk\002\t\n\021\"\001w\0039\031w\016]=%I\0264\027-\0367uII*\022a\036\026\003G-Dq!\037\001\002\002\023\005#0A\007qe>$Wo\031;Qe\0264\027\016_\013\002wB\021\021\n`\005\003Q)CqA \001\002\002\023\005q0\001\007qe>$Wo\031;Be&$\0300\006\002\002\002A\031\021\"a\001\n\007\005\025!BA\002J]RD\021\"!\003\001\003\003%\t!a\003\002\035A\024x\016Z;di\026cW-\\3oiR!\021QBA\n!\rI\021qB\005\004\003#Q!aA!os\"Q\021QCA\004\003\003\005\r!!\001\002\007a$\023\007C\005\002\032\001\t\t\021\"\021\002\034\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\002\036A1\021qDA\021\003\033i\021aH\005\004\003Gy\"\001C%uKJ\fGo\034:\t\023\005\035\002!!A\005\002\005%\022\001C2b]\026\013X/\0317\025\t\005-\022\021\007\t\004\023\0055\022bAA\030\025\t9!i\\8mK\006t\007BCA\013\003K\t\t\0211\001\002\016!I\021Q\007\001\002\002\023\005\023qG\001\tQ\006\034\bnQ8eKR\021\021\021\001\005\n\003w\001\021\021!C!\003{\t\001\002^8TiJLgn\032\013\002w\"I\021\021\t\001\002\002\023\005\0231I\001\007KF,\030\r\\:\025\t\005-\022Q\t\005\013\003+\ty$!AA\002\0055\001&\002\001\002J\005=\003cA\005\002L%\031\021Q\n\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\023\005M#!!A\t\002\005U\023A\004\"s_\006$7-Y:u\017J|W\017\035\t\004\037\005]c\001C\001\003\003\003E\t!!\027\024\013\005]\0231L\013\021\017\005u\0231M\016$i5\021\021q\f\006\004\003CR\021a\002:v]RLW.Z\005\005\003K\nyFA\tBEN$(/Y2u\rVt7\r^5p]JBqAMA,\t\003\tI\007\006\002\002V!Q\0211HA,\003\003%)%!\020\t\025\005=\024qKA\001\n\003\013\t(A\003baBd\027\020F\0035\003g\n)\b\003\004\032\003[\002\ra\007\005\t[\0055\004\023!a\001G!Q\021\021PA,\003\003%\t)a\037\002\017Ut\027\r\0359msR!\021QPAE!\025I\021qPAB\023\r\t\tI\003\002\007\037B$\030n\0348\021\013%\t)iG\022\n\007\005\035%B\001\004UkBdWM\r\005\n\003\027\0139(!AA\002Q\n1\001\037\0231\021%\ty)a\026\022\002\023\005a/A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$HE\r\005\n\003'\0139&%A\005\002Y\fq\"\0319qYf$C-\0324bk2$HE\r\005\013\003/\0139&!A\005\n\005e\025a\003:fC\022\024Vm]8mm\026$\"!a'\021\007%\013i*C\002\002 *\023aa\0242kK\016$\b")
/*     */ public final class BroadcastGroup implements Group, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Iterable<String> paths;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   public Props props() {
/* 120 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 120 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 120 */     return Group$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 120 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 120 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 120 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 120 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 120 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public BroadcastGroup copy(Iterable<String> paths, String routerDispatcher) {
/* 120 */     return new BroadcastGroup(
/* 121 */         paths, 
/* 122 */         routerDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "BroadcastGroup";
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
/*     */     return x$1 instanceof BroadcastGroup;
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
/*     */     //   8: instanceof akka/routing/BroadcastGroup
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 107
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/BroadcastGroup
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
/*     */     //   #120	-> 0
/*     */     //   #63	-> 14
/*     */     //   #120	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	109	0	this	Lakka/routing/BroadcastGroup;
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
/*     */   public BroadcastGroup(Iterable<String> paths, String routerDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/* 122 */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/* 122 */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public BroadcastGroup(Config config) {
/* 126 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(config.getStringList("routees.paths")), BroadcastGroup$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public BroadcastGroup(Iterable routeePaths) {
/* 133 */     this((Iterable<String>)Util$.MODULE$.immutableSeq(routeePaths), BroadcastGroup$.MODULE$.$lessinit$greater$default$2());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 135 */     return new Router(BroadcastRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public BroadcastGroup withDispatcher(String dispatcherId) {
/* 141 */     String x$21 = dispatcherId;
/* 141 */     Iterable<String> x$22 = copy$default$1();
/* 141 */     return copy(x$22, x$21);
/*     */   }
/*     */   
/*     */   public static String apply$default$2() {
/*     */     return BroadcastGroup$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$2() {
/*     */     return BroadcastGroup$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Iterable<String>, String>, BroadcastGroup> tupled() {
/*     */     return BroadcastGroup$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Iterable<String>, Function1<String, BroadcastGroup>> curried() {
/*     */     return BroadcastGroup$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BroadcastGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */