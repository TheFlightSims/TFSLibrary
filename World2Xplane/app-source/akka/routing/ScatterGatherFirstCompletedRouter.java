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
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.duration.Duration$;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t%u!B\001\003\021\0039\021!I*dCR$XM]$bi\",'OR5sgR\034u.\0349mKR,GMU8vi\026\024(BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003CM\033\027\r\036;fe\036\013G\017[3s\r&\0248\017^\"p[BdW\r^3e%>,H/\032:\024\007%a!\003\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\t\003\033MI!\001\006\b\003\031M+'/[1mSj\f'\r\\3\t\013YIA\021A\f\002\rqJg.\033;?)\0059\001\"B\r\n\t\003Q\022!B1qa2LH#B\016\003\026\t}\001C\001\005\035\r\021Q!\001Q\017\024\rqaa$\t\023\023!\tAq$\003\002!\005\t1B)\0329sK\016\fG/\0323S_V$XM]\"p]\032Lw\rE\002\tEmI!a\t\002\003/A{w\016\\(wKJ\024\030\016Z3V]N,GoQ8oM&<\007CA\007&\023\t1cBA\004Qe>$Wo\031;\t\021!b\"Q3A\005\002%\nQB\034:PM&s7\017^1oG\026\034X#\001\026\021\0055Y\023B\001\027\017\005\rIe\016\036\005\t]q\021\t\022)A\005U\005qaN](g\023:\034H/\0318dKN\004\003\002\003\031\035\005+\007I\021A\031\002\017I|W\017^3fgV\t!\007E\0024qij\021\001\016\006\003kY\n\021\"[7nkR\f'\r\\3\013\005]r\021AC2pY2,7\r^5p]&\021\021\b\016\002\t\023R,'/\0312mKB\0211H\020\b\003\033qJ!!\020\b\002\rA\023X\rZ3g\023\ty\004I\001\004TiJLgn\032\006\003{9A\001B\021\017\003\022\003\006IAM\001\te>,H/Z3tA!AA\t\bBK\002\023\005Q)\001\004xSRD\027N\\\013\002\rB\021q\tT\007\002\021*\021\021JS\001\tIV\024\030\r^5p]*\0211JD\001\013G>t7-\036:sK:$\030BA'I\00591\025N\\5uK\022+(/\031;j_:D\001b\024\017\003\022\003\006IAR\001\bo&$\b.\0338!\021!\tFD!f\001\n\003\022\026a\002:fg&TXM]\013\002'B\031Q\002\026,\n\005Us!AB(qi&|g\016\005\002\t/&\021\001L\001\002\b%\026\034\030N_3s\021!QFD!E!\002\023\031\026\001\003:fg&TXM\035\021\t\021qc\"Q3A\005\002u\013\001C]8vi\026\024H)[:qCR\034\007.\032:\026\003iB\001b\030\017\003\022\003\006IAO\001\022e>,H/\032:ESN\004\030\r^2iKJ\004\003\002C1\035\005+\007I\021\0012\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\002GB\021AmZ\007\002K*\021a\rB\001\006C\016$xN]\005\003Q\026\024!cU;qKJ4\030n]8s'R\024\030\r^3hs\"A!\016\bB\tB\003%1-A\ntkB,'O^5t_J\034FO]1uK\036L\b\005C\003\0279\021\005A\016F\004\034[:|\007/\035:\t\017!Z\007\023!a\001U!9\001g\033I\001\002\004\021\004\"\002#l\001\0041\005bB)l!\003\005\ra\025\005\b9.\004\n\0211\001;\021\035\t7\016%AA\002\rDQA\006\017\005\002Q$2aG;x\021\02518\0171\001+\003\tq'\017C\003yg\002\007a)A\001x\021\0251B\004\"\001{)\021Y20!\003\t\013qL\b\031A?\002\027I|W\017^3f!\006$\bn\035\t\005}\006\035!(D\001\000\025\021\t\t!a\001\002\t1\fgn\032\006\003\003\013\tAA[1wC&\021\021h \005\006qf\004\rA\022\005\007-q!\t!!\004\025\013m\ty!!\005\t\rE\013Y\0011\001W\021\031A\0301\002a\001\r\"1\021Q\003\017\005BE\nQ\001]1uQNDq!!\007\035\t\003\tY\"\001\bxSRDG)[:qCR\034\007.\032:\025\007m\ti\002C\004\002 \005]\001\031\001\036\002\031\021L7\017]1uG\",'/\0233\t\017\005\rB\004\"\001\002&\0051r/\033;i'V\004XM\035<jg>\0248\013\036:bi\026<\027\020F\002\034\003OAq!!\013\002\"\001\0071-\001\005tiJ\fG/Z4z\021\035\ti\003\bC\001\003_\t1b^5uQJ+7/\033>feR\0311$!\r\t\rE\013Y\0031\001W\021\035\t)\004\bC!\003o\tAb^5uQ\032\013G\016\0342bG.$B!!\017\002@A\031\001\"a\017\n\007\005u\"A\001\007S_V$XM]\"p]\032Lw\r\003\005\002B\005M\002\031AA\035\003\025yG\017[3s\021\035\t)\005\bC!\003\017\nAb\031:fCR,'k\\;uKJ$B!!\023\002PA\031\001\"a\023\n\007\0055#A\001\004S_V$XM\035\005\t\003#\n\031\0051\001\002T\00511/_:uK6\0042\001ZA+\023\r\t9&\032\002\f\003\016$xN]*zgR,W\016C\005\002\\q\t\t\021\"\001\002^\005!1m\0349z)5Y\022qLA1\003G\n)'a\032\002j!A\001&!\027\021\002\003\007!\006\003\0051\0033\002\n\0211\0013\021!!\025\021\fI\001\002\0041\005\002C)\002ZA\005\t\031A*\t\021q\013I\006%AA\002iB\001\"YA-!\003\005\ra\031\005\n\003[b\022\023!C\001\003_\nabY8qs\022\"WMZ1vYR$\023'\006\002\002r)\032!&a\035,\005\005U\004\003BA<\003\003k!!!\037\013\t\005m\024QP\001\nk:\034\007.Z2lK\022T1!a \017\003)\tgN\\8uCRLwN\\\005\005\003\007\013IHA\tv]\016DWmY6fIZ\013'/[1oG\026D\021\"a\"\035#\003%\t!!#\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\021\0211\022\026\004e\005M\004\"CAH9E\005I\021AAI\0039\031w\016]=%I\0264\027-\0367uIM*\"!a%+\007\031\013\031\bC\005\002\030r\t\n\021\"\001\002\032\006q1m\0349zI\021,g-Y;mi\022\"TCAANU\r\031\0261\017\005\n\003?c\022\023!C\001\003C\013abY8qs\022\"WMZ1vYR$S'\006\002\002$*\032!(a\035\t\023\005\035F$%A\005\002\005%\026AD2paf$C-\0324bk2$HEN\013\003\003WS3aYA:\021%\ty\013HA\001\n\003\n\t,A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003g\0032A`A[\023\tyt\020\003\005\002:r\t\t\021\"\001*\0031\001(o\0343vGR\f%/\033;z\021%\ti\fHA\001\n\003\ty,\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005\005\027q\031\t\004\033\005\r\027bAAc\035\t\031\021I\\=\t\023\005%\0271XA\001\002\004Q\023a\001=%c!I\021Q\032\017\002\002\023\005\023qZ\001\020aJ|G-^2u\023R,'/\031;peV\021\021\021\033\t\007\003'\f).!1\016\003YJ1!a67\005!IE/\032:bi>\024\b\"CAn9\005\005I\021AAo\003!\031\027M\\#rk\006dG\003BAp\003K\0042!DAq\023\r\t\031O\004\002\b\005>|G.Z1o\021)\tI-!7\002\002\003\007\021\021\031\005\n\003Sd\022\021!C!\003W\f\001\002[1tQ\016{G-\032\013\002U!I\021q\036\017\002\002\023\005\023\021_\001\ti>\034FO]5oOR\021\0211\027\005\n\003kd\022\021!C!\003o\fa!Z9vC2\034H\003BAp\003sD!\"!3\002t\006\005\t\031AAaQ\035a\022Q B\002\005\017\0012!DA\000\023\r\021\tA\004\002\013I\026\004(/Z2bi\026$\027E\001B\003\003\035+6/\032\021TG\006$H/\032:HCRDWM\035$jeN$8i\\7qY\026$X\r\032)p_2\004sN\035\021TG\006$H/\032:HCRDWM\035$jeN$8i\\7qY\026$X\rZ$s_V\004\030E\001B\005\003\r\021df\r\025\0069\t5!1\003\t\004\033\t=\021b\001B\t\035\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003!1\001\007\007a\001\005/\001Ba\r\035\003\032A\031AMa\007\n\007\tuQM\001\005BGR|'OU3g\021\025!\005\0041\001G\021\035\021\031#\003C\001\005K\taa\031:fCR,G#B\016\003(\t-\002b\002\031\003\"\001\007!\021\006\t\006}\006\035!\021\004\005\007\t\n\005\002\031\001$\t\021eI\021\021!CA\005_!Rb\007B\031\005g\021)Da\016\003:\tm\002\002\003\025\003.A\005\t\031\001\026\t\021A\022i\003%AA\002IBa\001\022B\027\001\0041\005\002C)\003.A\005\t\031A*\t\021q\023i\003%AA\002iB\001\"\031B\027!\003\005\ra\031\005\n\005I\021\021!CA\005\003\nq!\0368baBd\027\020\006\003\003D\t-\003\003B\007U\005\013\002\022\"\004B$UI25KO2\n\007\t%cB\001\004UkBdWM\016\005\n\005\033\022i$!AA\002m\t1\001\037\0231\021%\021\t&CI\001\n\003\ty'A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\r\005\n\005+J\021\023!C\001\003\023\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004\"\003B-\023E\005I\021AAM\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%i!I!QL\005\022\002\023\005\021\021U\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\033\t\023\t\005\024\"%A\005\002\005%\026a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$c\007C\005\003f%\t\n\021\"\001\002p\005y\021\r\0359ms\022\"WMZ1vYR$\023\007C\005\003j%\t\n\021\"\001\002\n\006y\021\r\0359ms\022\"WMZ1vYR$#\007C\005\003n%\t\n\021\"\001\002\032\006y\021\r\0359ms\022\"WMZ1vYR$C\007C\005\003r%\t\n\021\"\001\002\"\006y\021\r\0359ms\022\"WMZ1vYR$S\007C\005\003v%\t\n\021\"\001\002*\006y\021\r\0359ms\022\"WMZ1vYR$c\007C\005\003z%\t\t\021\"\003\003|\005Y!/Z1e%\026\034x\016\034<f)\t\021i\bE\002\005J1A!!\000\005\031y%M[3di\":\021\"!@\003\004\t\035\001fB\005\002~\n\r!q\001\025\b\001\005u(1\001B\004\001")
/*     */ public class ScatterGatherFirstCompletedRouter implements DeprecatedRouterConfig, PoolOverrideUnsetConfig<ScatterGatherFirstCompletedRouter>, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int nrOfInstances;
/*     */   
/*     */   private final Iterable<String> routees;
/*     */   
/*     */   private final FiniteDuration within;
/*     */   
/*     */   private final Option<Resizer> resizer;
/*     */   
/*     */   private final String routerDispatcher;
/*     */   
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   public static class ScatterGatherFirstCompletedRouter$$anonfun$5 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$5) {
/* 451 */       return x$5.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 491 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 491 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 491 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 491 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 491 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 491 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 491 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Props props() {
/* 491 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 491 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 491 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 491 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 491 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/* 491 */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Iterable<String> routees() {
/* 491 */     return this.routees;
/*     */   }
/*     */   
/*     */   public FiniteDuration within() {
/* 491 */     return this.within;
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter copy(int nrOfInstances, Iterable<String> routees, FiniteDuration within, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 491 */     return new ScatterGatherFirstCompletedRouter(nrOfInstances, routees, within, 
/* 492 */         resizer, 
/* 493 */         routerDispatcher, 
/* 494 */         supervisorStrategy);
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
/*     */   public FiniteDuration copy$default$3() {
/*     */     return within();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "ScatterGatherFirstCompletedRouter";
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
/*     */     return x$1 instanceof ScatterGatherFirstCompletedRouter;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, nrOfInstances());
/*     */     i = Statics.mix(i, Statics.anyHash(routees()));
/*     */     i = Statics.mix(i, Statics.anyHash(within()));
/*     */     i = Statics.mix(i, Statics.anyHash(resizer()));
/*     */     i = Statics.mix(i, Statics.anyHash(routerDispatcher()));
/*     */     i = Statics.mix(i, Statics.anyHash(supervisorStrategy()));
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
/*     */     //   2: if_acmpeq -> 220
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/ScatterGatherFirstCompletedRouter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 224
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ScatterGatherFirstCompletedRouter
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual nrOfInstances : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual nrOfInstances : ()I
/*     */     //   40: if_icmpne -> 216
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
/*     */     //   64: goto -> 216
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 216
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
/*     */     //   96: goto -> 216
/*     */     //   99: aload #6
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 216
/*     */     //   107: aload_0
/*     */     //   108: invokevirtual resizer : ()Lscala/Option;
/*     */     //   111: aload #4
/*     */     //   113: invokevirtual resizer : ()Lscala/Option;
/*     */     //   116: astore #7
/*     */     //   118: dup
/*     */     //   119: ifnonnull -> 131
/*     */     //   122: pop
/*     */     //   123: aload #7
/*     */     //   125: ifnull -> 139
/*     */     //   128: goto -> 216
/*     */     //   131: aload #7
/*     */     //   133: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   136: ifeq -> 216
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
/*     */     //   160: goto -> 216
/*     */     //   163: aload #8
/*     */     //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 216
/*     */     //   171: aload_0
/*     */     //   172: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   175: aload #4
/*     */     //   177: invokevirtual supervisorStrategy : ()Lakka/actor/SupervisorStrategy;
/*     */     //   180: astore #9
/*     */     //   182: dup
/*     */     //   183: ifnonnull -> 195
/*     */     //   186: pop
/*     */     //   187: aload #9
/*     */     //   189: ifnull -> 203
/*     */     //   192: goto -> 216
/*     */     //   195: aload #9
/*     */     //   197: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   200: ifeq -> 216
/*     */     //   203: aload #4
/*     */     //   205: aload_0
/*     */     //   206: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   209: ifeq -> 216
/*     */     //   212: iconst_1
/*     */     //   213: goto -> 217
/*     */     //   216: iconst_0
/*     */     //   217: ifeq -> 224
/*     */     //   220: iconst_1
/*     */     //   221: goto -> 225
/*     */     //   224: iconst_0
/*     */     //   225: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #491	-> 0
/*     */     //   #63	-> 14
/*     */     //   #491	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	226	0	this	Lakka/routing/ScatterGatherFirstCompletedRouter;
/*     */     //   0	226	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter(int nrOfInstances, Iterable<String> routees, FiniteDuration within, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/*     */     RouterConfig$class.$init$(this);
/*     */     Group$class.$init$(this);
/*     */     Pool$class.$init$(this);
/*     */     PoolOverrideUnsetConfig$class.$init$(this);
/*     */     Product.class.$init$(this);
/* 497 */     if (within.$less$eq(Duration$.MODULE$.Zero()))
/* 497 */       throw new IllegalArgumentException((
/* 498 */           new StringBuilder()).append("[within: Duration] can not be zero or negative, was [").append(within).append("]").toString()); 
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/*     */     return this.resizer;
/*     */   }
/*     */   
/*     */   public Option<Resizer> copy$default$4() {
/*     */     return resizer();
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
/*     */   public SupervisorStrategy supervisorStrategy() {
/*     */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$6() {
/*     */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter(int nr, FiniteDuration w) {
/* 503 */     this(x$132, x$134, x$133, x$135, x$136, x$137);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter(Iterable routeePaths, FiniteDuration w) {
/* 511 */     this(x$140, (Iterable<String>)x$138, x$139, x$141, x$142, x$143);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter(Resizer resizer, FiniteDuration w) {
/* 516 */     this(x$146, x$147, x$145, (Option<Resizer>)x$144, x$148, x$149);
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/* 518 */     return routees();
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter withDispatcher(String dispatcherId) {
/* 523 */     String x$150 = dispatcherId;
/* 523 */     int x$151 = copy$default$1();
/* 523 */     Iterable<String> x$152 = copy$default$2();
/* 523 */     FiniteDuration x$153 = copy$default$3();
/* 523 */     Option<Resizer> x$154 = copy$default$4();
/* 523 */     SupervisorStrategy x$155 = copy$default$6();
/* 523 */     return copy(x$151, x$152, x$153, x$154, x$150, x$155);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter withSupervisorStrategy(SupervisorStrategy strategy) {
/* 529 */     SupervisorStrategy x$156 = strategy;
/* 529 */     int x$157 = copy$default$1();
/* 529 */     Iterable<String> x$158 = copy$default$2();
/* 529 */     FiniteDuration x$159 = copy$default$3();
/* 529 */     Option<Resizer> x$160 = copy$default$4();
/* 529 */     String x$161 = copy$default$5();
/* 529 */     return copy(x$157, x$158, x$159, x$160, x$161, x$156);
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter withResizer(Resizer resizer) {
/* 534 */     Some x$162 = new Some(resizer);
/* 534 */     int x$163 = copy$default$1();
/* 534 */     Iterable<String> x$164 = copy$default$2();
/* 534 */     FiniteDuration x$165 = copy$default$3();
/* 534 */     String x$166 = copy$default$5();
/* 534 */     SupervisorStrategy x$167 = copy$default$6();
/* 534 */     return copy(x$163, x$164, x$165, (Option<Resizer>)x$162, x$166, x$167);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 541 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 543 */     return new Router(SmallestMailboxRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$6() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.apply$default$6();
/*     */   }
/*     */   
/*     */   public static String apply$default$5() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$4() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Iterable<String> apply$default$2() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$6() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.$lessinit$greater$default$6();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$5() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$4() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Iterable<String> $lessinit$greater$default$2() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static ScatterGatherFirstCompletedRouter create(Iterable<ActorRef> paramIterable, FiniteDuration paramFiniteDuration) {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.create(paramIterable, paramFiniteDuration);
/*     */   }
/*     */   
/*     */   public static ScatterGatherFirstCompletedRouter apply(Iterable<ActorRef> paramIterable, FiniteDuration paramFiniteDuration) {
/*     */     return ScatterGatherFirstCompletedRouter$.MODULE$.apply(paramIterable, paramFiniteDuration);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */