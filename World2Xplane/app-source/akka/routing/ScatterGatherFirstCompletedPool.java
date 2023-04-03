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
/*     */ import scala.Tuple6;
/*     */ import scala.collection.Iterator;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t=c\001B\001\003\005\036\021qdU2biR,'oR1uQ\026\024h)\033:ti\016{W\016\0357fi\026$\007k\\8m\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lC\016\0011C\002\001\t\035I1\022\004\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021A\001U8pYB\031qbE\013\n\005Q\021!a\006)p_2|e/\032:sS\022,WK\\:fi\016{gNZ5h!\ty\001\001\005\002\n/%\021\001D\003\002\b!J|G-^2u!\tI!$\003\002\034\025\ta1+\032:jC2L'0\0312mK\"AQ\004\001BK\002\023\005c$A\007oe>3\027J\\:uC:\034Wm]\013\002?A\021\021\002I\005\003C)\0211!\0238u\021!\031\003A!E!\002\023y\022A\0048s\037\032Len\035;b]\016,7\017\t\005\tK\001\021)\032!C!M\0059!/Z:ju\026\024X#A\024\021\007%A#&\003\002*\025\t1q\n\035;j_:\004\"aD\026\n\0051\022!a\002*fg&TXM\035\005\t]\001\021\t\022)A\005O\005A!/Z:ju\026\024\b\005\003\0051\001\tU\r\021\"\0012\003\0319\030\016\0365j]V\t!\007\005\0024q5\tAG\003\0026m\005AA-\036:bi&|gN\003\0028\025\005Q1m\0348dkJ\024XM\034;\n\005e\"$A\004$j]&$X\rR;sCRLwN\034\005\tw\001\021\t\022)A\005e\0059q/\033;iS:\004\003\002C\037\001\005+\007I\021\t \002%M,\b/\032:wSN|'o\025;sCR,w-_\013\002A\021\001iQ\007\002\003*\021!\tB\001\006C\016$xN]\005\003\t\006\023!cU;qKJ4\030n]8s'R\024\030\r^3hs\"Aa\t\001B\tB\003%q(A\ntkB,'O^5t_J\034FO]1uK\036L\b\005\003\005I\001\tU\r\021\"\021J\003A\021x.\036;fe\022K7\017]1uG\",'/F\001K!\tYeJ\004\002\n\031&\021QJC\001\007!J,G-\0324\n\005=\003&AB*ue&twM\003\002N\025!A!\013\001B\tB\003%!*A\ts_V$XM\035#jgB\fGo\0315fe\002B\001\002\026\001\003\026\004%\t%V\001\022kN,\007k\\8m\t&\034\b/\031;dQ\026\024X#\001,\021\005%9\026B\001-\013\005\035\021un\0347fC:D\001B\027\001\003\022\003\006IAV\001\023kN,\007k\\8m\t&\034\b/\031;dQ\026\024\b\005C\003]\001\021\005Q,\001\004=S:LGO\020\013\b+y{\006-\0312d\021\025i2\f1\001 \021\035)3\f%AA\002\035BQ\001M.A\002IBq!P.\021\002\003\007q\bC\004I7B\005\t\031\001&\t\017Q[\006\023!a\001-\")A\f\001C\001KR\021QC\032\005\006O\022\004\r\001[\001\007G>tg-[4\021\005%|W\"\0016\013\005\035\\'B\0017n\003!!\030\020]3tC\032,'\"\0018\002\007\r|W.\003\002qU\n11i\0348gS\036DQ\001\030\001\005\002I$2!F:v\021\025!\030\0171\001 \003\tq'\017C\0031c\002\007!\007C\003x\001\021\005\0030\001\007de\026\fG/\032*pkR,'\017\006\002zyB\021qB_\005\003w\n\021aAU8vi\026\024\b\"B?w\001\004q\030AB:zgR,W\016\005\002A&\031\021\021A!\003\027\005\033Go\034:TsN$X-\034\005\b\003\013\001A\021AA\004\003Y9\030\016\0365TkB,'O^5t_J\034FO]1uK\036LHcA\013\002\n!9\0211BA\002\001\004y\024\001C:ue\006$XmZ=\t\017\005=\001\001\"\001\002\022\005Yq/\033;i%\026\034\030N_3s)\r)\0221\003\005\007K\0055\001\031\001\026\t\017\005]\001\001\"\001\002\032\005qq/\033;i\t&\034\b/\031;dQ\026\024HcA\013\002\034!9\021QDA\013\001\004Q\025\001\0043jgB\fGo\0315fe&#\007bBA\021\001\021\005\0231E\001\ro&$\bNR1mY\n\f7m\033\013\005\003K\tY\003E\002\020\003OI1!!\013\003\0051\021v.\036;fe\016{gNZ5h\021!\ti#a\bA\002\005\025\022!B8uQ\026\024\b\"CA\031\001\005\005I\021AA\032\003\021\031w\016]=\025\033U\t)$a\016\002:\005m\022QHA \021!i\022q\006I\001\002\004y\002\002C\023\0020A\005\t\031A\024\t\021A\ny\003%AA\002IB\001\"PA\030!\003\005\ra\020\005\t\021\006=\002\023!a\001\025\"AA+a\f\021\002\003\007a\013C\005\002D\001\t\n\021\"\001\002F\005q1m\0349zI\021,g-Y;mi\022\nTCAA$U\ry\022\021J\026\003\003\027\002B!!\024\002X5\021\021q\n\006\005\003#\n\031&A\005v]\016DWmY6fI*\031\021Q\013\006\002\025\005tgn\034;bi&|g.\003\003\002Z\005=#!E;oG\",7m[3e-\006\024\030.\0318dK\"I\021Q\f\001\022\002\023\005\021qL\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\t\t\tGK\002(\003\023B\021\"!\032\001#\003%\t!a\032\002\035\r|\007/\037\023eK\032\fW\017\034;%gU\021\021\021\016\026\004e\005%\003\"CA7\001E\005I\021AA8\0039\031w\016]=%I\0264\027-\0367uIQ*\"!!\035+\007}\nI\005C\005\002v\001\t\n\021\"\001\002x\005q1m\0349zI\021,g-Y;mi\022*TCAA=U\rQ\025\021\n\005\n\003{\002\021\023!C\001\003\nabY8qs\022\"WMZ1vYR$c'\006\002\002\002*\032a+!\023\t\023\005\025\005!!A\005B\005\035\025!\0049s_\022,8\r\036)sK\032L\0070\006\002\002\nB!\0211RAK\033\t\tiI\003\003\002\020\006E\025\001\0027b]\036T!!a%\002\t)\fg/Y\005\004\037\0065\005\002CAM\001\005\005I\021\001\020\002\031A\024x\016Z;di\006\023\030\016^=\t\023\005u\005!!A\005\002\005}\025A\0049s_\022,8\r^#mK6,g\016\036\013\005\003C\0139\013E\002\n\003GK1!!*\013\005\r\te.\037\005\n\003S\013Y*!AA\002}\t1\001\037\0232\021%\ti\013AA\001\n\003\ny+A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\t\t\f\005\004\0024\006e\026\021U\007\003\003kS1!a.\013\003)\031w\016\0347fGRLwN\\\005\005\003w\013)L\001\005Ji\026\024\030\r^8s\021%\ty\fAA\001\n\003\t\t-\001\005dC:,\025/^1m)\r1\0261\031\005\013\003S\013i,!AA\002\005\005\006\"CAd\001\005\005I\021IAe\003!A\027m\0355D_\022,G#A\020\t\023\0055\007!!A\005B\005=\027\001\003;p'R\024\030N\\4\025\005\005%\005\"CAj\001\005\005I\021IAk\003\031)\027/^1mgR\031a+a6\t\025\005%\026\021[A\001\002\004\t\t\013K\003\001\0037\f\t\017E\002\n\003;L1!a8\013\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017%\t)OAA\001\022\003\t9/A\020TG\006$H/\032:HCRDWM\035$jeN$8i\\7qY\026$X\r\032)p_2\0042aDAu\r!\t!!!A\t\002\005-8#BAu\003[L\002cCAx\003k|rEM K-Vi!!!=\013\007\005M(\"A\004sk:$\030.\\3\n\t\005]\030\021\037\002\022\003\n\034HO]1di\032+hn\031;j_:4\004b\002/\002j\022\005\0211 \013\003\003OD!\"!4\002j\006\005IQIAh\021)\021\t!!;\002\002\023\005%1A\001\006CB\004H.\037\013\016+\t\025!q\001B\005\005\027\021iAa\004\t\ru\ty\0201\001 \021!)\023q I\001\002\0049\003B\002\031\002\000\002\007!\007\003\005>\003\004\n\0211\001@\021!A\025q I\001\002\004Q\005\002\003+\002\000B\005\t\031\001,\t\025\tM\021\021^A\001\n\003\023)\"A\004v]\006\004\b\017\\=\025\t\t]!q\004\t\005\023!\022I\002E\005\n\0057yrEM K-&\031!Q\004\006\003\rQ+\b\017\\37\021%\021\tC!\005\002\002\003\007Q#A\002yIAB!B!\n\002jF\005I\021AA0\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%e!Q!\021FAu#\003%\t!a\034\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0235\021)\021i#!;\022\002\023\005\021qO\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\033\t\025\tE\022\021^I\001\n\003\ty(A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$HE\016\005\013\005k\tI/%A\005\002\005}\023aD1qa2LH\005Z3gCVdG\017\n\032\t\025\te\022\021^I\001\n\003\ty'A\bbaBd\027\020\n3fM\006,H\016\036\0235\021)\021i$!;\022\002\023\005\021qO\001\020CB\004H.\037\023eK\032\fW\017\034;%k!Q!\021IAu#\003%\t!a \002\037\005\004\b\017\\=%I\0264\027-\0367uIYB!B!\022\002j\006\005I\021\002B$\003-\021X-\0313SKN|GN^3\025\005\t%\003\003BAF\005\027JAA!\024\002\016\n1qJ\0316fGR\004")
/*     */ public final class ScatterGatherFirstCompletedPool implements Pool, PoolOverrideUnsetConfig<ScatterGatherFirstCompletedPool>, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final FiniteDuration within;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final boolean usePoolDispatcher;
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/*  95 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/*  95 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/*  95 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/*  95 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/*  95 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/*  95 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/*  95 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/*  95 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/*  95 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool copy(int nrOfInstances, Option<Resizer> resizer, FiniteDuration within, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*  95 */     return new ScatterGatherFirstCompletedPool(
/*  96 */         nrOfInstances, resizer, 
/*  97 */         within, 
/*  98 */         supervisorStrategy, 
/*  99 */         routerDispatcher, 
/* 100 */         usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ScatterGatherFirstCompletedPool";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 6;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
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
/*     */     return x$1 instanceof ScatterGatherFirstCompletedPool;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(resizer()));
/*     */     i = Statics.mix(i, Statics.anyHash(within()));
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     i = Statics.mix(i, usePoolDispatcher() ? 1231 : 1237);
/*     */     return Statics.finalizeHash(i, 6);
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
/*     */     //   2: if_acmpeq -> 191
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ScatterGatherFirstCompletedPool
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 195
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ScatterGatherFirstCompletedPool
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 187
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
/*     */     //   64: goto -> 187
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 187
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   84: astore #6
/*     */     //   86: dup
/*     */     //   87: ifnonnull -> 99
/*     */     //   90: pop
/*     */     //   91: aload #6
/*     */     //   93: ifnull -> 107
/*     */     //   96: goto -> 187
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 187
/*     */     //   107: aload_0
/*     */     //   108: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   111: aload #4
/*     */     //   113: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   116: astore #7
/*     */     //   118: dup
/*     */     //   119: ifnonnull -> 131
/*     */     //   122: pop
/*     */     //   123: aload #7
/*     */     //   125: ifnull -> 139
/*     */     //   128: goto -> 187
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 187
/*     */     //   139: aload_0
/*     */     //   140: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   143: aload #4
/*     */     //   145: invokevirtual routerDispatcher : ()Ljava/lang/String;
/*     */     //   148: astore #8
/*     */     //   150: dup
/*     */     //   151: ifnonnull -> 163
/*     */     //   154: pop
/*     */     //   155: aload #8
/*     */     //   157: ifnull -> 171
/*     */     //   160: goto -> 187
/*     */     //   163: aload #8
/*     */     //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 187
/*     */     //   171: aload_0
/*     */     //   172: invokevirtual usePoolDispatcher : ()Z
/*     */     //   175: aload #4
/*     */     //   177: invokevirtual usePoolDispatcher : ()Z
/*     */     //   180: if_icmpne -> 187
/*     */     //   183: iconst_1
/*     */     //   184: goto -> 188
/*     */     //   187: iconst_0
/*     */     //   188: ifeq -> 195
/*     */     //   191: iconst_1
/*     */     //   192: goto -> 196
/*     */     //   195: iconst_0
/*     */     //   196: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 0
/*     */     //   #63	-> 14
/*     */     //   #95	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	197	0	this	Lakka/routing/ScatterGatherFirstCompletedPool;
/*     */     //   0	197	1	x$1	Ljava/lang/Object;
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
/*     */   public ScatterGatherFirstCompletedPool(int nrOfInstances, Option<Resizer> resizer, FiniteDuration within, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     PoolOverrideUnsetConfig$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public FiniteDuration within() {
/*     */     return this.within;
/*     */   }
/*     */   
/*     */   public FiniteDuration copy$default$3() {
/*     */     return within();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$4() {
/*     */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/*     */     return this.routerDispatcher;
/*     */   }
/*     */   
/*     */   public String copy$default$5() {
/*     */     return routerDispatcher();
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 100 */     return this.usePoolDispatcher;
/*     */   }
/*     */   
/*     */   public boolean copy$default$6() {
/* 100 */     return usePoolDispatcher();
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool(Config config) {
/* 104 */     this(
/* 105 */         x$3, 
/*     */         
/* 107 */         (Option)x$5, x$4, x$7, x$8, 
/* 108 */         x$6);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool(int nr, FiniteDuration within) {
/* 116 */     this(x$9, x$11, x$10, x$12, x$13, x$14);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 118 */     return new Router(new ScatterGatherFirstCompletedRoutingLogic(within()));
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool withSupervisorStrategy(SupervisorStrategy strategy) {
/* 123 */     SupervisorStrategy x$15 = strategy;
/* 123 */     int x$16 = copy$default$1();
/* 123 */     Option<Resizer> x$17 = copy$default$2();
/* 123 */     FiniteDuration x$18 = copy$default$3();
/* 123 */     String x$19 = copy$default$5();
/* 123 */     boolean x$20 = copy$default$6();
/* 123 */     return copy(x$16, x$17, x$18, x$15, x$19, x$20);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool withResizer(Resizer resizer) {
/* 128 */     Some x$21 = new Some(resizer);
/* 128 */     int x$22 = copy$default$1();
/* 128 */     FiniteDuration x$23 = copy$default$3();
/* 128 */     SupervisorStrategy x$24 = copy$default$4();
/* 128 */     String x$25 = copy$default$5();
/* 128 */     boolean x$26 = copy$default$6();
/* 128 */     return copy(x$22, (Option<Resizer>)x$21, x$23, x$24, x$25, x$26);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool withDispatcher(String dispatcherId) {
/* 134 */     String x$27 = dispatcherId;
/* 134 */     int x$28 = copy$default$1();
/* 134 */     Option<Resizer> x$29 = copy$default$2();
/* 134 */     FiniteDuration x$30 = copy$default$3();
/* 134 */     SupervisorStrategy x$31 = copy$default$4();
/* 134 */     boolean x$32 = copy$default$6();
/* 134 */     return copy(x$28, x$29, x$30, x$31, x$27, x$32);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 141 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public static boolean apply$default$6() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.apply$default$6();
/*     */   }
/*     */   
/*     */   public static String apply$default$5() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$4() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$2() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$6() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.$lessinit$greater$default$6();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$5() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$4() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$2() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple6<Object, Option<Resizer>, FiniteDuration, SupervisorStrategy, String, Object>, ScatterGatherFirstCompletedPool> tupled() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Option<Resizer>, Function1<FiniteDuration, Function1<SupervisorStrategy, Function1<String, Function1<Object, ScatterGatherFirstCompletedPool>>>>>> curried() {
/*     */     return ScatterGatherFirstCompletedPool$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */