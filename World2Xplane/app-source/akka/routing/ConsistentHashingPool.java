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
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple7;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\035e\001B\001\003\005\036\021QcQ8og&\034H/\0328u\021\006\034\b.\0338h!>|GN\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\031\001\001B\004\n\0273A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\tA{w\016\034\t\004\037M)\022B\001\013\003\005]\001vn\0347Pm\026\024(/\0333f+:\034X\r^\"p]\032Lw\r\005\002\020\001A\021\021bF\005\0031)\021q\001\025:pIV\034G\017\005\002\n5%\0211D\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t;\001\021)\032!C!=\005iaN](g\023:\034H/\0318dKN,\022a\b\t\003\023\001J!!\t\006\003\007%sG\017\003\005$\001\tE\t\025!\003 \0039q'o\0244J]N$\030M\\2fg\002B\001\"\n\001\003\026\004%\tEJ\001\be\026\034\030N_3s+\0059\003cA\005)U%\021\021F\003\002\007\037B$\030n\0348\021\005=Y\023B\001\027\003\005\035\021Vm]5{KJD\001B\f\001\003\022\003\006IaJ\001\te\026\034\030N_3sA!A\001\007\001BK\002\023\005a$\001\nwSJ$X/\0317O_\022,7OR1di>\024\b\002\003\032\001\005#\005\013\021B\020\002'YL'\017^;bY:{G-Z:GC\016$xN\035\021\t\021Q\002!Q3A\005\002U\n1\002[1tQ6\013\007\017]5oOV\ta\007\005\0028u9\021q\002O\005\003s\t\tqcQ8og&\034H/\0328u\021\006\034\b.\0338h%>,H/\032:\n\005mb$!F\"p]NL7\017^3oi\"\0137\017['baBLgn\032\006\003s\tA\001B\020\001\003\022\003\006IAN\001\rQ\006\034\b.T1qa&tw\r\t\005\t\001\002\021)\032!C!\003\006\0212/\0369feZL7o\034:TiJ\fG/Z4z+\005\021\005CA\"G\033\005!%BA#\005\003\025\t7\r^8s\023\t9EI\001\nTkB,'O^5t_J\034FO]1uK\036L\b\002C%\001\005#\005\013\021\002\"\002'M,\b/\032:wSN|'o\025;sCR,w-\037\021\t\021-\003!Q3A\005B1\013\001C]8vi\026\024H)[:qCR\034\007.\032:\026\0035\003\"AT)\017\005%y\025B\001)\013\003\031\001&/\0323fM&\021!k\025\002\007'R\024\030N\\4\013\005AS\001\002C+\001\005#\005\013\021B'\002#I|W\017^3s\t&\034\b/\031;dQ\026\024\b\005\003\005X\001\tU\r\021\"\021Y\003E)8/\032)p_2$\025n\0359bi\016DWM]\013\0023B\021\021BW\005\0037*\021qAQ8pY\026\fg\016\003\005^\001\tE\t\025!\003Z\003I)8/\032)p_2$\025n\0359bi\016DWM\035\021\t\013}\003A\021\0011\002\rqJg.\033;?)!)\022MY2eK\032<\007\"B\017_\001\004y\002bB\023_!\003\005\ra\n\005\bay\003\n\0211\001 \021\035!d\f%AA\002YBq\001\0210\021\002\003\007!\tC\004L=B\005\t\031A'\t\017]s\006\023!a\0013\")q\f\001C\001SR\021QC\033\005\006W\"\004\r\001\\\001\007G>tg-[4\021\0055\034X\"\0018\013\005-|'B\0019r\003!!\030\020]3tC\032,'\"\001:\002\007\r|W.\003\002u]\n11i\0348gS\036DQa\030\001\005\002Y$\"!F<\t\013a,\b\031A\020\002\0059\024\b\"\002>\001\t\003Z\030\001D2sK\006$XMU8vi\026\024HC\001?\000!\tyQ0\003\002\005\t1!k\\;uKJDq!!\001z\001\004\t\031!\001\004tsN$X-\034\t\004\007\006\025\021bAA\004\t\nY\021i\031;peNK8\017^3n\021\035\tY\001\001C\001\003\033\tac^5uQN+\b/\032:wSN|'o\025;sCR,w-\037\013\004+\005=\001bBA\t\003\023\001\rAQ\001\tgR\024\030\r^3hs\"9\021Q\003\001\005\002\005]\021aC<ji\"\024Vm]5{KJ$2!FA\r\021\031)\0231\003a\001U!9\021Q\004\001\005\002\005}\021AD<ji\"$\025n\0359bi\016DWM\035\013\004+\005\005\002bBA\022\0037\001\r!T\001\rI&\034\b/\031;dQ\026\024\030\n\032\005\b\003O\001A\021AA\025\003Y9\030\016\0365WSJ$X/\0317O_\022,7OR1di>\024HcA\013\002,!9\021QFA\023\001\004y\022A\002<o_\022,7\017C\004\0022\001!\t!a\r\002\035]LG\017\033%bg\"l\025\r\0359feR\031Q#!\016\t\021\005]\022q\006a\001\003s\ta!\\1qa\026\024\bcA\034\002<%\031\021Q\b\037\003)\r{gn]5ti\026tG\017S1tQ6\013\007\017]3s\021\035\t\t\005\001C!\003\007\nAb^5uQ\032\013G\016\0342bG.$B!!\022\002LA\031q\"a\022\n\007\005%#A\001\007S_V$XM]\"p]\032Lw\r\003\005\002N\005}\002\031AA#\003\025yG\017[3s\021%\t\t\006AA\001\n\003\t\031&\001\003d_BLHcD\013\002V\005]\023\021LA.\003;\ny&!\031\t\021u\ty\005%AA\002}A\001\"JA(!\003\005\ra\n\005\ta\005=\003\023!a\001?!AA'a\024\021\002\003\007a\007\003\005A\003\037\002\n\0211\001C\021!Y\025q\nI\001\002\004i\005\002C,\002PA\005\t\031A-\t\023\005\025\004!%A\005\002\005\035\024AD2paf$C-\0324bk2$H%M\013\003\003SR3aHA6W\t\ti\007\005\003\002p\005eTBAA9\025\021\t\031(!\036\002\023Ut7\r[3dW\026$'bAA<\025\005Q\021M\0348pi\006$\030n\0348\n\t\005m\024\021\017\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\"CA@\001E\005I\021AAA\0039\031w\016]=%I\0264\027-\0367uII*\"!a!+\007\035\nY\007C\005\002\b\002\t\n\021\"\001\002h\005q1m\0349zI\021,g-Y;mi\022\032\004\"CAF\001E\005I\021AAG\0039\031w\016]=%I\0264\027-\0367uIQ*\"!a$+\007Y\nY\007C\005\002\024\002\t\n\021\"\001\002\026\006q1m\0349zI\021,g-Y;mi\022*TCAALU\r\021\0251\016\005\n\0037\003\021\023!C\001\003;\013abY8qs\022\"WMZ1vYR$c'\006\002\002 *\032Q*a\033\t\023\005\r\006!%A\005\002\005\025\026AD2paf$C-\0324bk2$HeN\013\003\003OS3!WA6\021%\tY\013AA\001\n\003\ni+A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003_\003B!!-\002<6\021\0211\027\006\005\003k\0139,\001\003mC:<'BAA]\003\021Q\027M^1\n\007I\013\031\f\003\005\002@\002\t\t\021\"\001\037\0031\001(o\0343vGR\f%/\033;z\021%\t\031\rAA\001\n\003\t)-\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005\035\027Q\032\t\004\023\005%\027bAAf\025\t\031\021I\\=\t\023\005=\027\021YA\001\002\004y\022a\001=%c!I\0211\033\001\002\002\023\005\023Q[\001\020aJ|G-^2u\023R,'/\031;peV\021\021q\033\t\007\0033\fy.a2\016\005\005m'bAAo\025\005Q1m\0347mK\016$\030n\0348\n\t\005\005\0301\034\002\t\023R,'/\031;pe\"I\021Q\035\001\002\002\023\005\021q]\001\tG\006tW)];bYR\031\021,!;\t\025\005=\0271]A\001\002\004\t9\rC\005\002n\002\t\t\021\"\021\002p\006A\001.Y:i\007>$W\rF\001 \021%\t\031\020AA\001\n\003\n)0\001\005u_N#(/\0338h)\t\ty\013C\005\002z\002\t\t\021\"\021\002|\0061Q-];bYN$2!WA\021)\ty-a>\002\002\003\007\021q\031\025\006\001\t\005!q\001\t\004\023\t\r\021b\001B\003\025\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\035I!1\002\002\002\002#\005!QB\001\026\007>t7/[:uK:$\b*Y:iS:<\007k\\8m!\ry!q\002\004\t\003\t\t\t\021#\001\003\022M)!q\002B\n3Aa!Q\003B\016?\035zbGQ'Z+5\021!q\003\006\004\0053Q\021a\002:v]RLW.Z\005\005\005;\0219BA\tBEN$(/Y2u\rVt7\r^5p]^Bqa\030B\b\t\003\021\t\003\006\002\003\016!Q\0211\037B\b\003\003%)%!>\t\025\t\035\"qBA\001\n\003\023I#A\003baBd\027\020F\b\026\005W\021iCa\f\0032\tM\"Q\007B\034\021\031i\"Q\005a\001?!AQE!\n\021\002\003\007q\005\003\0051\005K\001\n\0211\001 \021!!$Q\005I\001\002\0041\004\002\003!\003&A\005\t\031\001\"\t\021-\023)\003%AA\0025C\001b\026B\023!\003\005\r!\027\005\013\005w\021y!!A\005\002\nu\022aB;oCB\004H.\037\013\005\005\0219\005\005\003\nQ\t\005\003CC\005\003D}9sD\016\"N3&\031!Q\t\006\003\rQ+\b\017\\38\021%\021IE!\017\002\002\003\007Q#A\002yIAB!B!\024\003\020E\005I\021AAA\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%e!Q!\021\013B\b#\003%\t!a\032\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0234\021)\021)Fa\004\022\002\023\005\021QR\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\033\t\025\te#qBI\001\n\003\t)*A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\016\005\013\005;\022y!%A\005\002\005u\025a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$c\007\003\006\003b\t=\021\023!C\001\003K\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022:\004B\003B3\005\037\t\n\021\"\001\002\002\006y\021\r\0359ms\022\"WMZ1vYR$#\007\003\006\003j\t=\021\023!C\001\003O\nq\"\0319qYf$C-\0324bk2$He\r\005\013\005[\022y!%A\005\002\0055\025aD1qa2LH\005Z3gCVdG\017\n\033\t\025\tE$qBI\001\n\003\t)*A\bbaBd\027\020\n3fM\006,H\016\036\0236\021)\021)Ha\004\022\002\023\005\021QT\001\020CB\004H.\037\023eK\032\fW\017\034;%m!Q!\021\020B\b#\003%\t!!*\002\037\005\004\b\017\\=%I\0264\027-\0367uI]B!B! \003\020\005\005I\021\002B@\003-\021X-\0313SKN|GN^3\025\005\t\005\005\003BAY\005\007KAA!\"\0024\n1qJ\0316fGR\004")
/*     */ public final class ConsistentHashingPool implements Pool, PoolOverrideUnsetConfig<ConsistentHashingPool>, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final int virtualNodesFactor;
/*     */   
/*     */   private final PartialFunction<Object, Object> hashMapping;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final boolean usePoolDispatcher;
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 275 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 275 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 275 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 275 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 275 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 275 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 275 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 275 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 275 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool copy(int nrOfInstances, Option<Resizer> resizer, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/* 275 */     return new ConsistentHashingPool(
/* 276 */         nrOfInstances, resizer, 
/* 277 */         virtualNodesFactor, 
/* 278 */         hashMapping, 
/* 279 */         supervisorStrategy, 
/* 280 */         routerDispatcher, 
/* 281 */         usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ConsistentHashingPool";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 7;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 6:
/*     */       
/*     */       case 5:
/*     */       
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
/*     */     return x$1 instanceof ConsistentHashingPool;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(resizer()));
/*     */     i = Statics.mix(i, virtualNodesFactor());
/*     */     i = Statics.mix(i, Statics.anyHash(hashMapping()));
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     i = Statics.mix(i, usePoolDispatcher() ? 1231 : 1237);
/*     */     return Statics.finalizeHash(i, 7);
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
/*     */     //   2: if_acmpeq -> 203
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ConsistentHashingPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 207
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ConsistentHashingPool
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 199
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
/*     */     //   64: goto -> 199
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 199
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual virtualNodesFactor : ()I
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual virtualNodesFactor : ()I
/*     */     //   84: if_icmpne -> 199
/*     */     //   87: aload_0
/*     */     //   88: invokevirtual hashMapping : ()Lscala/PartialFunction;
/*     */     //   91: aload #4
/*     */     //   93: invokevirtual hashMapping : ()Lscala/PartialFunction;
/*     */     //   96: astore #6
/*     */     //   98: dup
/*     */     //   99: ifnonnull -> 111
/*     */     //   102: pop
/*     */     //   103: aload #6
/*     */     //   105: ifnull -> 119
/*     */     //   108: goto -> 199
/*     */     //   111: aload #6
/*     */     //   113: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   116: ifeq -> 199
/*     */     //   119: aload_0
/*     */     //   120: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   123: aload #4
/*     */     //   125: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   128: astore #7
/*     */     //   130: dup
/*     */     //   131: ifnonnull -> 143
/*     */     //   134: pop
/*     */     //   135: aload #7
/*     */     //   137: ifnull -> 151
/*     */     //   140: goto -> 199
/*     */     //   143: aload #7
/*     */     //   145: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   148: ifeq -> 199
/*     */     //   151: aload_0
/*     */     //   152: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   155: aload #4
/*     */     //   157: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   160: astore #8
/*     */     //   162: dup
/*     */     //   163: ifnonnull -> 175
/*     */     //   166: pop
/*     */     //   167: aload #8
/*     */     //   169: ifnull -> 183
/*     */     //   172: goto -> 199
/*     */     //   175: aload #8
/*     */     //   177: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   180: ifeq -> 199
/*     */     //   183: aload_0
/*     */     //   184: invokevirtual usePoolDispatcher : ()Z
/*     */     //   187: aload #4
/*     */     //   189: invokevirtual usePoolDispatcher : ()Z
/*     */     //   192: if_icmpne -> 199
/*     */     //   195: iconst_1
/*     */     //   196: goto -> 200
/*     */     //   199: iconst_0
/*     */     //   200: ifeq -> 207
/*     */     //   203: iconst_1
/*     */     //   204: goto -> 208
/*     */     //   207: iconst_0
/*     */     //   208: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #275	-> 0
/*     */     //   #63	-> 14
/*     */     //   #275	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	209	0	this	Lakka/routing/ConsistentHashingPool;
/*     */     //   0	209	1	x$1	Ljava/lang/Object;
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
/*     */   public ConsistentHashingPool(int nrOfInstances, Option<Resizer> resizer, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     PoolOverrideUnsetConfig$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public int virtualNodesFactor() {
/*     */     return this.virtualNodesFactor;
/*     */   }
/*     */   
/*     */   public int copy$default$3() {
/*     */     return virtualNodesFactor();
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> hashMapping() {
/*     */     return this.hashMapping;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> copy$default$4() {
/*     */     return hashMapping();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$5() {
/*     */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/*     */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$6() {
/*     */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 281 */     return this.usePoolDispatcher;
/*     */   }
/*     */   
/*     */   public boolean copy$default$7() {
/* 281 */     return usePoolDispatcher();
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool(Config config) {
/* 285 */     this(
/* 286 */         x$17, 
/* 287 */         (Option)x$18, x$20, x$21, x$22, x$23, 
/* 288 */         x$19);
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool(int nr) {
/* 294 */     this(nr, ConsistentHashingPool$.MODULE$.$lessinit$greater$default$2(), ConsistentHashingPool$.MODULE$.$lessinit$greater$default$3(), ConsistentHashingPool$.MODULE$.$lessinit$greater$default$4(), ConsistentHashingPool$.MODULE$.$lessinit$greater$default$5(), ConsistentHashingPool$.MODULE$.$lessinit$greater$default$6(), ConsistentHashingPool$.MODULE$.$lessinit$greater$default$7());
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 297 */     return new Router(new ConsistentHashingRoutingLogic(system, virtualNodesFactor(), hashMapping()));
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool withSupervisorStrategy(SupervisorStrategy strategy) {
/* 302 */     SupervisorStrategy x$24 = strategy;
/* 302 */     int x$25 = copy$default$1();
/* 302 */     Option<Resizer> x$26 = copy$default$2();
/* 302 */     int x$27 = copy$default$3();
/* 302 */     PartialFunction<Object, Object> x$28 = copy$default$4();
/* 302 */     String x$29 = copy$default$6();
/* 302 */     boolean x$30 = copy$default$7();
/* 302 */     return copy(x$25, x$26, x$27, x$28, x$24, x$29, x$30);
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool withResizer(Resizer resizer) {
/* 307 */     Some x$31 = new Some(resizer);
/* 307 */     int x$32 = copy$default$1(), x$33 = copy$default$3();
/* 307 */     PartialFunction<Object, Object> x$34 = copy$default$4();
/* 307 */     SupervisorStrategy x$35 = copy$default$5();
/* 307 */     String x$36 = copy$default$6();
/* 307 */     boolean x$37 = copy$default$7();
/* 307 */     return copy(x$32, (Option<Resizer>)x$31, x$33, x$34, x$35, x$36, x$37);
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool withDispatcher(String dispatcherId) {
/* 313 */     String x$38 = dispatcherId;
/* 313 */     int x$39 = copy$default$1();
/* 313 */     Option<Resizer> x$40 = copy$default$2();
/* 313 */     int x$41 = copy$default$3();
/* 313 */     PartialFunction<Object, Object> x$42 = copy$default$4();
/* 313 */     SupervisorStrategy x$43 = copy$default$5();
/* 313 */     boolean x$44 = copy$default$7();
/* 313 */     return copy(x$39, x$40, x$41, x$42, x$43, x$38, x$44);
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool withVirtualNodesFactor(int vnodes) {
/* 318 */     int x$45 = vnodes, x$46 = copy$default$1();
/* 318 */     Option<Resizer> x$47 = copy$default$2();
/* 318 */     PartialFunction<Object, Object> x$48 = copy$default$4();
/* 318 */     SupervisorStrategy x$49 = copy$default$5();
/* 318 */     String x$50 = copy$default$6();
/* 318 */     boolean x$51 = copy$default$7();
/* 318 */     return copy(x$46, x$47, x$45, x$48, x$49, x$50, x$51);
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool withHashMapper(ConsistentHashingRouter.ConsistentHashMapper mapper) {
/* 324 */     PartialFunction<Object, Object> x$52 = ConsistentHashingRouter$.MODULE$.hashMappingAdapter(mapper);
/* 324 */     int x$53 = copy$default$1();
/* 324 */     Option<Resizer> x$54 = copy$default$2();
/* 324 */     int x$55 = copy$default$3();
/* 324 */     SupervisorStrategy x$56 = copy$default$5();
/* 324 */     String x$57 = copy$default$6();
/* 324 */     boolean x$58 = copy$default$7();
/* 324 */     return copy(x$53, x$54, x$55, x$52, x$56, x$57, x$58);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/*     */     boolean bool;
/* 332 */     RouterConfig routerConfig = other;
/* 333 */     if (routerConfig instanceof FromConfig) {
/* 333 */       bool = true;
/* 333 */     } else if (routerConfig instanceof NoRouter) {
/* 333 */       bool = true;
/*     */     } else {
/* 333 */       bool = false;
/*     */     } 
/* 333 */     if (bool) {
/* 333 */       RouterConfig routerConfig1 = overrideUnsetConfig(other);
/* 334 */     } else if (routerConfig instanceof ConsistentHashingPool) {
/* 334 */       ConsistentHashingPool consistentHashingPool = (ConsistentHashingPool)routerConfig;
/* 334 */       PartialFunction<Object, Object> x$59 = consistentHashingPool.hashMapping();
/* 334 */       int x$60 = copy$default$1();
/* 334 */       Option<Resizer> x$61 = copy$default$2();
/* 334 */       int x$62 = copy$default$3();
/* 334 */       SupervisorStrategy x$63 = copy$default$5();
/* 334 */       String x$64 = copy$default$6();
/* 334 */       boolean x$65 = copy$default$7();
/* 334 */       RouterConfig routerConfig1 = copy(x$60, x$61, x$62, x$59, x$63, x$64, x$65).overrideUnsetConfig(other);
/*     */     } else {
/* 335 */       if (routerConfig instanceof ConsistentHashingRouter) {
/* 335 */         ConsistentHashingRouter consistentHashingRouter = (ConsistentHashingRouter)routerConfig;
/* 335 */         PartialFunction<Object, Object> x$66 = consistentHashingRouter.hashMapping();
/* 335 */         int x$67 = copy$default$1();
/* 335 */         Option<Resizer> x$68 = copy$default$2();
/* 335 */         int x$69 = copy$default$3();
/* 335 */         SupervisorStrategy x$70 = copy$default$5();
/* 335 */         String x$71 = copy$default$6();
/* 335 */         boolean x$72 = copy$default$7();
/* 335 */         return copy(x$67, x$68, x$69, x$66, x$70, x$71, x$72).overrideUnsetConfig(other);
/*     */       } 
/* 336 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("Expected ConsistentHashingPool, got [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { other })));
/*     */     } 
/*     */     return (RouterConfig)SYNTHETIC_LOCAL_VARIABLE_4;
/*     */   }
/*     */   
/*     */   public static boolean apply$default$7() {
/*     */     return ConsistentHashingPool$.MODULE$.apply$default$7();
/*     */   }
/*     */   
/*     */   public static String apply$default$6() {
/*     */     return ConsistentHashingPool$.MODULE$.apply$default$6();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$5() {
/*     */     return ConsistentHashingPool$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> apply$default$4() {
/*     */     return ConsistentHashingPool$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static int apply$default$3() {
/*     */     return ConsistentHashingPool$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$2() {
/*     */     return ConsistentHashingPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$7() {
/*     */     return ConsistentHashingPool$.MODULE$.$lessinit$greater$default$7();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$6() {
/*     */     return ConsistentHashingPool$.MODULE$.$lessinit$greater$default$6();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$5() {
/*     */     return ConsistentHashingPool$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static PartialFunction<Object, Object> $lessinit$greater$default$4() {
/*     */     return ConsistentHashingPool$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$3() {
/*     */     return ConsistentHashingPool$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$2() {
/*     */     return ConsistentHashingPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple7<Object, Option<Resizer>, Object, PartialFunction<Object, Object>, SupervisorStrategy, String, Object>, ConsistentHashingPool> tupled() {
/*     */     return ConsistentHashingPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Option<Resizer>, Function1<Object, Function1<PartialFunction<Object, Object>, Function1<SupervisorStrategy, Function1<String, Function1<Object, ConsistentHashingPool>>>>>>> curried() {
/*     */     return ConsistentHashingPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */