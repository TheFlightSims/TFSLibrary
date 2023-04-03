/*     */ package akka.routing;
/*     */ 
/*     */ import akka.ConfigurationException;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.Props$;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rw!B\001\003\021\003;\021A\003$s_6\034uN\0344jO*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0055B\001\006Ge>l7i\0348gS\036\034R!\003\007\000\003\013\001\"\001C\007\007\t)\021\001AD\n\004\033=)\002C\001\t\024\033\005\t\"\"\001\n\002\013M\034\027\r\\1\n\005Q\t\"AB!osJ+g\r\005\002\t-%\021qC\001\002\005!>|G\016\003\005\032\033\t\025\r\021\"\021\033\003\035\021Xm]5{KJ,\022a\007\t\004!qq\022BA\017\022\005\031y\005\017^5p]B\021\001bH\005\003A\t\021qAU3tSj,'\017\003\005#\033\t\005\t\025!\003\034\003!\021Xm]5{KJ\004\003\002\003\023\016\005\013\007I\021I\023\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\002MA\021qEK\007\002Q)\021\021\006B\001\006C\016$xN]\005\003W!\022!cU;qKJ4\030n]8s'R\024\030\r^3hs\"AQ&\004B\001B\003%a%A\ntkB,'O^5t_J\034FO]1uK\036L\b\005\003\0050\033\t\025\r\021\"\0211\003A\021x.\036;fe\022K7\017]1uG\",'/F\0012!\t\021TG\004\002\021g%\021A'E\001\007!J,G-\0324\n\005Y:$AB*ue&twM\003\0025#!A\021(\004B\001B\003%\021'A\ts_V$XM\035#jgB\fGo\0315fe\002BQaO\007\005\002q\na\001P5oSRtD\003\002\007>}}BQ!\007\036A\002mAQ\001\n\036A\002\031BQa\f\036A\002EBQaO\007\005\002\005#\022\001\004\005\006\0076!\t\005R\001\rGJ,\027\r^3S_V$XM\035\013\003\013\"\003\"\001\003$\n\005\035\023!A\002*pkR,'\017C\003J\005\002\007!*\001\004tsN$X-\034\t\003O-K!\001\024\025\003\027\005\033Go\034:TsN$X-\034\005\007\0356!\t\005B(\002#\r\024X-\031;f%>,H/\032:BGR|'\017F\001Q!\tA\021+\003\002S\005\tY!k\\;uKJ\f5\r^8s\021\025!V\002\"\021V\00311XM]5gs\016{gNZ5h)\t1\026\f\005\002\021/&\021\001,\005\002\005+:LG\017C\003['\002\0071,\001\003qCRD\007CA\024]\023\ti\006FA\005BGR|'\017U1uQ\")q,\004C\001A\0061r/\033;i'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\006\002\rC\")!M\030a\001M\005A1\017\036:bi\026<\027\020C\003e\033\021\005Q-A\006xSRD'+Z:ju\026\024HC\001\007g\021\025I2\r1\001\037\021\025AW\002\"\001j\00399\030\016\0365ESN\004\030\r^2iKJ$\"\001\0046\t\013-<\007\031A\031\002\031\021L7\017]1uG\",'/\0233\t\0175l!\031!C!]\006iaN](g\023:\034H/\0318dKN,\022a\034\t\003!AL!!]\t\003\007%sG\017\003\004t\033\001\006Ia\\\001\017]J|e-\0238ti\006t7-Z:!\021\025)X\002\"\001w\003\025\001(o\0349t)\0059\bCA\024y\023\tI\bFA\003Qe>\0048\017K\002\016wz\004\"\001\005?\n\005u\f\"\001E*fe&\fGNV3sg&|g.V%E=\005\t\001c\001\t\002\002%\031\0211A\t\003\017A\023x\016Z;diB\031\001#a\002\n\007\005%\021C\001\007TKJL\027\r\\5{C\ndW\r\003\004<\023\021\005\021Q\002\013\002\017!9\021\021C\005\005\002\005M\021aC4fi&s7\017^1oG\026,\"!!\006\017\005!\001\001bBA\r\023\021\025\0211D\001\006CB\004H.\037\013\b\031\005u\021qDA\021\021!I\022q\003I\001\002\004Y\002\002\003\023\002\030A\005\t\031\001\024\t\021=\n9\002%AA\002EBC!a\006\002&A\031\001#a\n\n\007\005%\022C\001\004j]2Lg.\032\005\b\003[IAQAA\030\003\035)h.\0319qYf$B!!\r\0024A\031\001\003H\031\t\017\005U\0221\006a\001\031\005\021am\031\025\005\003W\t)\003C\005\002<%\t\n\021\"\002\002>\005y\021\r\0359ms\022\"WMZ1vYR$\023'\006\002\002@)\0321$!\021,\005\005\r\003\003BA#\003\037j!!a\022\013\t\005%\0231J\001\nk:\034\007.Z2lK\022T1!!\024\022\003)\tgN\\8uCRLwN\\\005\005\003#\n9EA\tv]\016DWmY6fIZ\013'/[1oG\026D\021\"!\026\n#\003%)!a\026\002\037\005\004\b\017\\=%I\0264\027-\0367uII*\"!!\027+\007\031\n\t\005C\005\002^%\t\n\021\"\002\002`\005y\021\r\0359ms\022\"WMZ1vYR$3'\006\002\002b)\032\021'!\021\t\023\005\025\024\"!A\005B\005\035\024!\0049s_\022,8\r\036)sK\032L\0070\006\002\002jA!\0211NA;\033\t\tiG\003\003\002p\005E\024\001\0027b]\036T!!a\035\002\t)\fg/Y\005\004m\0055\004\002CA=\023\005\005I\021\0018\002\031A\024x\016Z;di\006\023\030\016^=\t\023\005u\024\"!A\005\002\005}\024A\0049s_\022,8\r^#mK6,g\016\036\013\005\003\003\0139\tE\002\021\003\007K1!!\"\022\005\r\te.\037\005\n\003\023\013Y(!AA\002=\f1\001\037\0232\021%\ti)CA\001\n\003\ny)A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\t\t\n\005\004\002\024\006e\025\021Q\007\003\003+S1!a&\022\003)\031w\016\0347fGRLwN\\\005\005\0037\013)J\001\005Ji\026\024\030\r^8s\021%\ty*CA\001\n\003\t\t+\001\005dC:,\025/^1m)\021\t\031+!+\021\007A\t)+C\002\002(F\021qAQ8pY\026\fg\016\003\006\002\n\006u\025\021!a\001\003\003C\021\"!,\n\003\003%\t%a,\002\021!\f7\017[\"pI\026$\022a\034\005\n\003gK\021\021!C!\003k\013\001\002^8TiJLgn\032\013\003\003SB\021\"!/\n\003\003%I!a/\002\027I,\027\r\032*fg>dg/\032\013\003\003{\003B!a\033\002@&!\021\021YA7\005\031y%M[3di\002")
/*     */ public class FromConfig implements Pool {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 281 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 281 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 281 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 281 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 281 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 281 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 281 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 281 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/* 281 */     return this.resizer;
/*     */   }
/*     */   
/*     */   public FromConfig(Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher) {
/* 281 */     RouterConfig$class.$init$(this);
/* 281 */     Pool$class.$init$(this);
/* 318 */     this.nrOfInstances = 0;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/*     */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public FromConfig() {
/*     */     this((Option<Resizer>)None$.MODULE$, Pool$.MODULE$.defaultSupervisorStrategy(), "akka.actor.default-dispatcher");
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/*     */     throw new UnsupportedOperationException("FromConfig must not create Router");
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/*     */     throw new UnsupportedOperationException("FromConfig must not create RouterActor");
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/*     */     (new String[2])[0] = "Configuration missing for router [";
/*     */     (new String[2])[1] = "] in 'akka.actor.deployment' section.";
/*     */     throw new ConfigurationException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { path })));
/*     */   }
/*     */   
/*     */   public FromConfig withSupervisorStrategy(SupervisorStrategy strategy) {
/*     */     return new FromConfig(resizer(), strategy, routerDispatcher());
/*     */   }
/*     */   
/*     */   public FromConfig withResizer(Resizer resizer) {
/*     */     return new FromConfig((Option<Resizer>)new Some(resizer), supervisorStrategy(), routerDispatcher());
/*     */   }
/*     */   
/*     */   public FromConfig withDispatcher(String dispatcherId) {
/*     */     return new FromConfig(resizer(), supervisorStrategy(), dispatcherId);
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/* 318 */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Props props() {
/* 324 */     return Props$.MODULE$.empty().withRouter(this);
/*     */   }
/*     */   
/*     */   public static boolean canEqual(Object paramObject) {
/*     */     return FromConfig$.MODULE$.canEqual(paramObject);
/*     */   }
/*     */   
/*     */   public static Iterator<Object> productIterator() {
/*     */     return FromConfig$.MODULE$.productIterator();
/*     */   }
/*     */   
/*     */   public static Object productElement(int paramInt) {
/*     */     return FromConfig$.MODULE$.productElement(paramInt);
/*     */   }
/*     */   
/*     */   public static int productArity() {
/*     */     return FromConfig$.MODULE$.productArity();
/*     */   }
/*     */   
/*     */   public static String productPrefix() {
/*     */     return FromConfig$.MODULE$.productPrefix();
/*     */   }
/*     */   
/*     */   public static String apply$default$3() {
/*     */     return FromConfig$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$2() {
/*     */     return FromConfig$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$1() {
/*     */     return FromConfig$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static Option<String> unapply(FromConfig paramFromConfig) {
/*     */     return FromConfig$.MODULE$.unapply(paramFromConfig);
/*     */   }
/*     */   
/*     */   public static FromConfig apply(Option<Resizer> paramOption, SupervisorStrategy paramSupervisorStrategy, String paramString) {
/*     */     return FromConfig$.MODULE$.apply(paramOption, paramSupervisorStrategy, paramString);
/*     */   }
/*     */   
/*     */   public static FromConfig$ getInstance() {
/*     */     return FromConfig$.MODULE$.getInstance();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\FromConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */