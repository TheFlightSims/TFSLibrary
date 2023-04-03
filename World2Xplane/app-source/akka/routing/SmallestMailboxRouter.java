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
/*     */ @ScalaSignature(bytes = "\006\001\tUs!B\001\003\021\0039\021!F*nC2dWm\035;NC&d'm\034=S_V$XM\035\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t)2+\\1mY\026\034H/T1jY\n|\007PU8vi\026\0248cA\005\r%A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032\004\"!D\n\n\005Qq!\001D*fe&\fG.\033>bE2,\007\"\002\f\n\t\0039\022A\002\037j]&$h\bF\001\b\021\025I\022\002\"\001\033\003\025\t\007\017\0357z)\rY\022q\035\t\003\021q1AA\003\002A;M1A\004\004\020\"II\001\"\001C\020\n\005\001\022!A\006#faJ,7-\031;fIJ{W\017^3s\007>tg-[4\021\007!\0213$\003\002$\005\t9\002k\\8m\037Z,'O]5eKVs7/\032;D_:4\027n\032\t\003\033\025J!A\n\b\003\017A\023x\016Z;di\"A\001\006\bBK\002\023\005\021&A\007oe>3\027J\\:uC:\034Wm]\013\002UA\021QbK\005\003Y9\0211!\0238u\021!qCD!E!\002\023Q\023A\0048s\037\032Len\035;b]\016,7\017\t\005\taq\021)\032!C\001c\0059!o\\;uK\026\034X#\001\032\021\007MB$(D\0015\025\t)d'A\005j[6,H/\0312mK*\021qGD\001\013G>dG.Z2uS>t\027BA\0355\005!IE/\032:bE2,\007CA\036?\035\tiA(\003\002>\035\0051\001K]3eK\032L!a\020!\003\rM#(/\0338h\025\tid\002\003\005C9\tE\t\025!\0033\003!\021x.\036;fKN\004\003\002\003#\035\005+\007I\021I#\002\017I,7/\033>feV\ta\tE\002\016\017&K!\001\023\b\003\r=\003H/[8o!\tA!*\003\002L\005\t9!+Z:ju\026\024\b\002C'\035\005#\005\013\021\002$\002\021I,7/\033>fe\002B\001b\024\017\003\026\004%\t\001U\001\021e>,H/\032:ESN\004\030\r^2iKJ,\022A\017\005\t%r\021\t\022)A\005u\005\t\"o\\;uKJ$\025n\0359bi\016DWM\035\021\t\021Qc\"Q3A\005\002U\013!c];qKJ4\030n]8s'R\024\030\r^3hsV\ta\013\005\002X56\t\001L\003\002Z\t\005)\021m\031;pe&\0211\f\027\002\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\003\005^9\tE\t\025!\003W\003M\031X\017]3sm&\034xN]*ue\006$XmZ=!\021\0251B\004\"\001`)\031Y\002-\0312dI\"9\001F\030I\001\002\004Q\003b\002\031_!\003\005\rA\r\005\b\tz\003\n\0211\001G\021\035ye\f%AA\002iBq\001\0260\021\002\003\007a\013C\003\0279\021\005a\r\006\002\034O\")\001.\032a\001U\005\021aN\035\005\006-q!\tA\033\013\0037-DQ\001\\5A\0025\f1B]8vi\026,\007+\031;igB\031an\035\036\016\003=T!\001]9\002\t1\fgn\032\006\002e\006!!.\031<b\023\tIt\016C\003\0279\021\005Q\017\006\002\034m\")A\t\036a\001\023\")\001\020\bC!c\005)\001/\031;ig\")!\020\bC\001w\006qq/\033;i\t&\034\b/\031;dQ\026\024HCA\016}\021\025i\030\0201\001;\0031!\027n\0359bi\016DWM]%e\021\031yH\004\"\001\002\002\0051r/\033;i'V\004XM\035<jg>\0248\013\036:bi\026<\027\020F\002\034\003\007Aa!!\002\001\0041\026\001C:ue\006$XmZ=\t\017\005%A\004\"\001\002\f\005Yq/\033;i%\026\034\030N_3s)\rY\022Q\002\005\007\t\006\035\001\031A%\t\017\005EA\004\"\021\002\024\005aq/\033;i\r\006dGNY1dWR!\021QCA\016!\rA\021qC\005\004\0033\021!\001\004*pkR,'oQ8oM&<\007\002CA\017\003\037\001\r!!\006\002\013=$\b.\032:\t\017\005\005B\004\"\021\002$\005a1M]3bi\026\024v.\036;feR!\021QEA\026!\rA\021qE\005\004\003S\021!A\002*pkR,'\017\003\005\002.\005}\001\031AA\030\003\031\031\030p\035;f[B\031q+!\r\n\007\005M\002LA\006BGR|'oU=ti\026l\007\"CA\0349\005\005I\021AA\035\003\021\031w\016]=\025\027m\tY$!\020\002@\005\005\0231\t\005\tQ\005U\002\023!a\001U!A\001'!\016\021\002\003\007!\007\003\005E\003k\001\n\0211\001G\021!y\025Q\007I\001\002\004Q\004\002\003+\0026A\005\t\031\001,\t\023\005\035C$%A\005\002\005%\023AD2paf$C-\0324bk2$H%M\013\003\003\027R3AKA'W\t\ty\005\005\003\002R\005mSBAA*\025\021\t)&a\026\002\023Ut7\r[3dW\026$'bAA-\035\005Q\021M\0348pi\006$\030n\0348\n\t\005u\0231\013\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\"CA19E\005I\021AA2\0039\031w\016]=%I\0264\027-\0367uII*\"!!\032+\007I\ni\005C\005\002jq\t\n\021\"\001\002l\005q1m\0349zI\021,g-Y;mi\022\032TCAA7U\r1\025Q\n\005\n\003cb\022\023!C\001\003g\nabY8qs\022\"WMZ1vYR$C'\006\002\002v)\032!(!\024\t\023\005eD$%A\005\002\005m\024AD2paf$C-\0324bk2$H%N\013\003\003{R3AVA'\021%\t\t\tHA\001\n\003\n\031)A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003\013\0032A\\AD\023\tyt\016\003\005\002\fr\t\t\021\"\001*\0031\001(o\0343vGR\f%/\033;z\021%\ty\tHA\001\n\003\t\t*\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005M\025\021\024\t\004\033\005U\025bAAL\035\t\031\021I\\=\t\023\005m\025QRA\001\002\004Q\023a\001=%c!I\021q\024\017\002\002\023\005\023\021U\001\020aJ|G-^2u\023R,'/\031;peV\021\0211\025\t\007\003K\0139+a%\016\003YJ1!!+7\005!IE/\032:bi>\024\b\"CAW9\005\005I\021AAX\003!\031\027M\\#rk\006dG\003BAY\003o\0032!DAZ\023\r\t)L\004\002\b\005>|G.Z1o\021)\tY*a+\002\002\003\007\0211\023\005\n\003wc\022\021!C!\003{\013\001\002[1tQ\016{G-\032\013\002U!I\021\021\031\017\002\002\023\005\0231Y\001\ti>\034FO]5oOR\021\021Q\021\005\n\003\017d\022\021!C!\003\023\fa!Z9vC2\034H\003BAY\003\027D!\"a'\002F\006\005\t\031AAJQ\035a\022qZAk\0033\0042!DAi\023\r\t\031N\004\002\013I\026\004(/Z2bi\026$\027EAAl\003])6/\032\021T[\006dG.Z:u\033\006LGNY8y!>|G.\t\002\002\\\006\031!GL\032)\013q\ty.!:\021\0075\t\t/C\002\002d:\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\005Aa\001\r\rA\002\005%\b\003B\0329\003W\0042aVAw\023\r\ty\017\027\002\t\003\016$xN\035*fM\"9\0211_\005\005\002\005U\030AB2sK\006$X\rF\002\034\003oDq\001MAy\001\004\tI\020\005\003og\006-\b\002C\r\n\003\003%\t)!@\025\027m\tyP!\001\003\004\t\025!q\001\005\tQ\005m\b\023!a\001U!A\001'a?\021\002\003\007!\007\003\005E\003w\004\n\0211\001G\021!y\0251 I\001\002\004Q\004\002\003+\002|B\005\t\031\001,\t\023\t-\021\"!A\005\002\n5\021aB;oCB\004H.\037\013\005\005\037\0219\002\005\003\016\017\nE\001\003C\007\003\024)\022dI\017,\n\007\tUaB\001\004UkBdW-\016\005\n\0053\021I!!AA\002m\t1\001\037\0231\021%\021i\"CI\001\n\003\tI%A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\r\005\n\005CI\021\023!C\001\003G\n1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004\"\003B\023\023E\005I\021AA6\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%g!I!\021F\005\022\002\023\005\0211O\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\033\t\023\t5\022\"%A\005\002\005m\024a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$S\007C\005\0032%\t\n\021\"\001\002J\005y\021\r\0359ms\022\"WMZ1vYR$\023\007C\005\0036%\t\n\021\"\001\002d\005y\021\r\0359ms\022\"WMZ1vYR$#\007C\005\003:%\t\n\021\"\001\002l\005y\021\r\0359ms\022\"WMZ1vYR$3\007C\005\003>%\t\n\021\"\001\002t\005y\021\r\0359ms\022\"WMZ1vYR$C\007C\005\003B%\t\n\021\"\001\002|\005y\021\r\0359ms\022\"WMZ1vYR$S\007C\005\003F%\t\t\021\"\003\003H\005Y!/Z1e%\026\034x\016\034<f)\t\021I\005E\002o\005\027J1A!\024p\005\031y%M[3di\":\021\"a4\002V\006e\007fB\005\002P\006U\027\021\034\025\b\001\005=\027Q[Am\001")
/*     */ public class SmallestMailboxRouter implements DeprecatedRouterConfig, PoolOverrideUnsetConfig<SmallestMailboxRouter>, Product, Serializable {
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
/*     */   public static class SmallestMailboxRouter$$anonfun$3 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$3) {
/* 253 */       return x$3.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig overrideUnsetConfig(RouterConfig other) {
/* 300 */     return PoolOverrideUnsetConfig$class.overrideUnsetConfig(this, other);
/*     */   }
/*     */   
/*     */   public boolean usePoolDispatcher() {
/* 300 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 300 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 300 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 300 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 300 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 300 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Props props() {
/* 300 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 300 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 300 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 300 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 300 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public int nrOfInstances() {
/* 300 */     return this.nrOfInstances;
/*     */   }
/*     */   
/*     */   public Iterable<String> routees() {
/* 300 */     return this.routees;
/*     */   }
/*     */   
/*     */   public Option<Resizer> resizer() {
/* 300 */     return this.resizer;
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter copy(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 300 */     return new SmallestMailboxRouter(nrOfInstances, routees, resizer, 
/* 301 */         routerDispatcher, 
/* 302 */         supervisorStrategy);
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
/*     */     return "SmallestMailboxRouter";
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
/*     */     return x$1 instanceof SmallestMailboxRouter;
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
/*     */     //   8: instanceof akka/routing/SmallestMailboxRouter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 192
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/SmallestMailboxRouter
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
/*     */     //   #300	-> 0
/*     */     //   #63	-> 14
/*     */     //   #300	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	194	0	this	Lakka/routing/SmallestMailboxRouter;
/*     */     //   0	194	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter(int nrOfInstances, Iterable<String> routees, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
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
/* 302 */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy copy$default$5() {
/* 302 */     return supervisorStrategy();
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter(int nr) {
/* 308 */     this(nr, SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$2(), SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$3(), SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$4(), SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$5());
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter(Iterable routeePaths) {
/* 316 */     this(x$72, (Iterable<String>)x$71, x$73, x$74, x$75);
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter(Resizer resizer) {
/* 321 */     this(x$77, x$78, (Option<Resizer>)x$76, x$79, x$80);
/*     */   }
/*     */   
/*     */   public Iterable<String> paths() {
/* 323 */     return routees();
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter withDispatcher(String dispatcherId) {
/* 328 */     String x$81 = dispatcherId;
/* 328 */     int x$82 = copy$default$1();
/* 328 */     Iterable<String> x$83 = copy$default$2();
/* 328 */     Option<Resizer> x$84 = copy$default$3();
/* 328 */     SupervisorStrategy x$85 = copy$default$5();
/* 328 */     return copy(x$82, x$83, x$84, x$81, x$85);
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter withSupervisorStrategy(SupervisorStrategy strategy) {
/* 334 */     SupervisorStrategy x$86 = strategy;
/* 334 */     int x$87 = copy$default$1();
/* 334 */     Iterable<String> x$88 = copy$default$2();
/* 334 */     Option<Resizer> x$89 = copy$default$3();
/* 334 */     String x$90 = copy$default$4();
/* 334 */     return copy(x$87, x$88, x$89, x$90, x$86);
/*     */   }
/*     */   
/*     */   public SmallestMailboxRouter withResizer(Resizer resizer) {
/* 339 */     Some x$91 = new Some(resizer);
/* 339 */     int x$92 = copy$default$1();
/* 339 */     Iterable<String> x$93 = copy$default$2();
/* 339 */     String x$94 = copy$default$4();
/* 339 */     SupervisorStrategy x$95 = copy$default$5();
/* 339 */     return copy(x$92, x$93, (Option<Resizer>)x$91, x$94, x$95);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 346 */     return overrideUnsetConfig(other);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 348 */     return new Router(SmallestMailboxRoutingLogic$.MODULE$.apply());
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy apply$default$5() {
/*     */     return SmallestMailboxRouter$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static String apply$default$4() {
/*     */     return SmallestMailboxRouter$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> apply$default$3() {
/*     */     return SmallestMailboxRouter$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> apply$default$2() {
/*     */     return SmallestMailboxRouter$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return SmallestMailboxRouter$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static SupervisorStrategy $lessinit$greater$default$5() {
/*     */     return SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$4() {
/*     */     return SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static Option<Resizer> $lessinit$greater$default$3() {
/*     */     return SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Iterable<String> $lessinit$greater$default$2() {
/*     */     return SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return SmallestMailboxRouter$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static SmallestMailboxRouter create(Iterable<ActorRef> paramIterable) {
/*     */     return SmallestMailboxRouter$.MODULE$.create(paramIterable);
/*     */   }
/*     */   
/*     */   public static SmallestMailboxRouter apply(Iterable<ActorRef> paramIterable) {
/*     */     return SmallestMailboxRouter$.MODULE$.apply(paramIterable);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SmallestMailboxRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */