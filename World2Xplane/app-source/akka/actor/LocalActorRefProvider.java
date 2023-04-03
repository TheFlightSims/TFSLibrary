/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.MailboxType;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import akka.dispatch.RequiresMessageQueue;
/*     */ import akka.dispatch.UnboundedMessageQueueSemantics;
/*     */ import akka.dispatch.sysmsg.Failed;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.Watch;
/*     */ import akka.event.EventStream;
/*     */ import akka.event.LogSource$;
/*     */ import akka.event.Logging$;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.event.LoggingBus;
/*     */ import akka.routing.NoRouter$;
/*     */ import akka.util.Collections$EmptyImmutableSeq$;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Switch;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.concurrent.Promise$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\rvAB\001\003\021\003!a!A\013M_\016\fG.Q2u_J\024VM\032)s_ZLG-\032:\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.\031\t\003\017!i\021A\001\004\007\023\tA\t\001\002\006\003+1{7-\0317BGR|'OU3g!J|g/\0333feN\021\001b\003\t\003\031=i\021!\004\006\002\035\005)1oY1mC&\021\001#\004\002\007\003:L(+\0324\t\013IAA\021\001\013\002\rqJg.\033;?\007\001!\022A\002\004\005-!!qC\001\005Hk\006\024H-[1o'\021)2\002G\016\021\005\035I\022B\001\016\003\005\025\t5\r^8s!\rar$I\007\002;)\021a\004B\001\tI&\034\b/\031;dQ&\021\001%\b\002\025%\026\fX/\033:fg6+7o]1hKF+X-^3\021\005q\021\023BA\022\036\005y)fNY8v]\022,G-T3tg\006<W-U;fk\026\034V-\\1oi&\0347\017\003\005&+\t\025\r\021\"\021'\003I\031X\017]3sm&\034xN]*ue\006$XmZ=\026\003\035\002\"a\002\025\n\005%\022!AE*va\026\024h/[:peN#(/\031;fOfD\001bK\013\003\002\003\006IaJ\001\024gV\004XM\035<jg>\0248\013\036:bi\026<\027\020\t\005\006%U!\t!\f\013\003]A\002\"aL\013\016\003!AQ!\n\027A\002\035BQAM\013\005\002M\nqA]3dK&4X-F\0015!\021aQg\016\036\n\005Yj!a\004)beRL\027\r\034$v]\016$\030n\0348\021\0051A\024BA\035\016\005\r\te.\037\t\003\031mJ!\001P\007\003\tUs\027\016\036\005\006}U!\teP\001\013aJ,'+Z:uCJ$Hc\001\036A\035\")\021)\020a\001\005\006)1-Y;tKB\0211i\023\b\003\t&s!!\022%\016\003\031S!aR\n\002\rq\022xn\034;?\023\005q\021B\001&\016\003\035\001\030mY6bO\026L!\001T'\003\023QC'o\\<bE2,'B\001&\016\021\025yU\b1\001Q\003\ri7o\032\t\004\031E;\024B\001*\016\005\031y\005\017^5p]\032!A\013\003\003V\0059\031\026p\035;f[\036+\030M\0353jC:\034BaU\006\0317!AQe\025BC\002\023\005c\005\003\005,'\n\005\t\025!\003(\021!I6K!b\001\n\003Q\026\001C4vCJ$\027.\0318\026\003m\003\"a\002/\n\005u\023!\001C!di>\024(+\0324\t\021}\033&\021!Q\001\nm\013\021bZ;be\022L\027M\034\021\t\013I\031F\021A1\025\007\t\034G\r\005\0020'\")Q\005\031a\001O!)\021\f\031a\0017\"9am\025a\001\n\0039\027\001\005;fe6Lg.\031;j_:Dun\\6t+\005A\007cA5o76\t!N\003\002lY\006I\021.\\7vi\006\024G.\032\006\003[6\t!bY8mY\026\034G/[8o\023\ty'NA\002TKRDq!]*A\002\023\005!/\001\013uKJl\027N\\1uS>t\007j\\8lg~#S-\035\013\003uMDq\001\0369\002\002\003\007\001.A\002yIEBaA^*!B\023A\027!\005;fe6Lg.\031;j_:Dun\\6tA!)!g\025C\001g!)\021p\025C\001u\006YA/\032:nS:\fG/\0338h+\005Y\bC\001?~\033\005\031\026B\001@\032\005\035\021VmY3jm\026Dq!!\001T\t\003\t\031!A\020ti>\004x\013[3o\0032dG+\032:nS:\fG/[8o\021>|7n\035#p]\026$2AOA\003\021\031\t9a a\0017\0061!/Z7pm\026Dq!!\001T\t\003\tY\001F\001;\021\031q4\013\"\021\002\020Q)!(!\005\002\024!1\021)!\004A\002\tCaaTA\007\001\004\001fAB\005\003\001\021\t9bE\003\002\026-\tI\002E\002\b\0037I1!!\b\003\005A\t5\r^8s%\0264\007K]8wS\022,'\017C\006\002\"\005U!\021!Q\001\n\005\r\022aC0tsN$X-\034(b[\026\004B!!\n\002,9\031A\"a\n\n\007\005%R\"\001\004Qe\026$WMZ\005\005\003[\tyC\001\004TiJLgn\032\006\004\003Si\001bCA\032\003+\021)\031!C!\003k\t\001b]3ui&twm]\013\003\003o\001B!!\017\002@9\031q!a\017\n\007\005u\"!A\006BGR|'oU=ti\026l\027\002BA!\003\007\022\001bU3ui&twm\035\006\004\003{\021\001bCA$\003+\021\t\021)A\005\003o\t\021b]3ui&twm\035\021\t\027\005-\023Q\003BC\002\023\005\021QJ\001\fKZ,g\016^*ue\026\fW.\006\002\002PA!\021\021KA,\033\t\t\031FC\002\002V\021\tQ!\032<f]RLA!!\027\002T\tYQI^3oiN#(/Z1n\021-\ti&!\006\003\002\003\006I!a\024\002\031\0254XM\034;TiJ,\027-\034\021\t\027\005\005\024Q\003BC\002\023\005\0211M\001\016Ift\027-\\5d\003\016\034Wm]:\026\005\005\025\004cA\004\002h%\031\021\021\016\002\003\033\021Kh.Y7jG\006\0337-Z:t\021-\ti'!\006\003\002\003\006I!!\032\002\035\021Lh.Y7jG\006\0337-Z:tA!Y\021\021OA\013\005\013\007I\021IA:\003!!W\r\0357ps\026\024XCAA;!\r9\021qO\005\004\003s\022!\001\003#fa2|\0270\032:\t\027\005u\024Q\003B\001B\003%\021QO\001\nI\026\004Hn\\=fe\002B1\"!!\002\026\t\005\t\025!\003\002\004\006aq\fZ3bI2+G\017^3sgB!A\"UAC!\035a\021qQAF\003#K1!!#\016\005%1UO\\2uS>t\027\007E\002\b\003\033K1!a$\003\005%\t5\r^8s!\006$\b\016E\002\b\003'K1!!&\003\005AIe\016^3s]\006d\027i\031;peJ+g\r\003\005\023\003+!\t\001BAM)9\tY*!(\002 \006\005\0261UAS\003O\0032aBA\013\021!\t\t#a&A\002\005\r\002\002CA\032\003/\003\r!a\016\t\021\005-\023q\023a\001\003\037B\001\"!\031\002\030\002\007\021Q\r\005\t\003c\n9\n1\001\002v!A\021\021QAL\001\004\t\031\tC\004\023\003+!\t!a+\025\025\005m\025QVAX\003c\013\031\f\003\005\002\"\005%\006\031AA\022\021!\t\031$!+A\002\005]\002\002CA&\003S\003\r!a\024\t\021\005\005\024\021\026a\001\003KB!\"a.\002\026\t\007I\021IA]\003!\021xn\034;QCRDWCAAF\021%\ti,!\006!\002\023\tY)A\005s_>$\b+\031;iA!Y\021\021YA\013\005\004%\t\001BAb\003\rawnZ\013\003\003\013\004B!!\025\002H&!\021\021ZA*\0059aunZ4j]\036\fE-\0319uKJD\021\"!4\002\026\001\006I!!2\002\t1|w\r\t\005\013\003#\f)B1A\005B\005M\027a\0033fC\022dU\r\036;feN,\"!!%\t\023\005]\027Q\003Q\001\n\005E\025\001\0043fC\022dU\r\036;feN\004\003BCAn\003+\021\r\021\"\003\002^\006QA/Z7q\035Vl'-\032:\026\005\005}\007\003BAq\003gl!!a9\013\t\005\025\030q]\001\007CR|W.[2\013\t\005%\0301^\001\013G>t7-\036:sK:$(\002BAw\003_\fA!\036;jY*\021\021\021_\001\005U\0064\030-\003\003\002v\006\r(AC!u_6L7\rT8oO\"I\021\021`A\013A\003%\021q\\\001\fi\026l\007OT;nE\026\024\b\005\003\005\002~\006UA\021BA\000\003!!X-\0349OC6,GCAA\022\021)\021\031!!\006C\002\023%\021\021X\001\ti\026l\007OT8eK\"I!qAA\013A\003%\0211R\001\ni\026l\007OT8eK\002B\001Ba\003\002\026\021\005#QB\001\ti\026l\007\017U1uQR\021\0211\022\005\f\005#\t)B1A\005\002\021\t\031.A\022uQ\026|e.Z,i_^\013Gn[:UQ\026\024UO\0312mKN|em\0259bG\026$\026.\\3\t\023\tU\021Q\003Q\001\n\005E\025\001\n;iK>sWm\0265p/\006d7n\035+iK\n+(M\0317fg>37\013]1dKRKW.\032\021\t\031\te\021Q\003a\001\002\004%IAa\007\002\rML8\017^3n+\t\021i\002E\002\b\005?I1A!\t\003\005=\t5\r^8s'f\034H/Z7J[Bd\007\002\004B\023\003+\001\r\0211A\005\n\t\035\022AC:zgR,Wn\030\023fcR\031!H!\013\t\023Q\024\031#!AA\002\tu\001\"\003B\027\003+\001\013\025\002B\017\003\035\031\030p\035;f[\002BCAa\013\0032A\031ABa\r\n\007\tURB\001\005w_2\fG/\0337f\021-\021I$!\006\t\006\004%\tAa\017\002%Q,'/\\5oCRLwN\034)s_6L7/Z\013\003\005{\001RAa\020\003Dij!A!\021\013\007\005%X\"\003\003\003F\t\005#a\002)s_6L7/\032\005\f\005\023\n)\002#A!B\023\021i$A\nuKJl\027N\\1uS>t\007K]8nSN,\007\005\003\005\003N\005UA\021\001B(\003E!XM]7j]\006$\030n\0348GkR,(/Z\013\003\005#\002RAa\020\003TiJAA!\026\003B\t1a)\036;ve\026D!B!\027\002\026\001\007I\021\002B.\003))\007\020\036:b\035\006lWm]\013\003\005;\002\002\"!\n\003`\005\r\022\021S\005\005\005C\nyCA\002NCBD!B!\032\002\026\001\007I\021\002B4\0039)\007\020\036:b\035\006lWm]0%KF$2A\017B5\021%!(1MA\001\002\004\021i\006C\005\003n\005U\001\025)\003\003^\005YQ\r\037;sC:\013W.Z:!Q\021\021YG!\r\t\021\tM\024Q\003C\001\005k\n!C]3hSN$XM]#yiJ\fg*Y7fgR\031!Ha\036\t\021\te$\021\017a\001\005;\nqaX3yiJ\f7\017\003\005\003~\005UA\021\002B@\003\031:W/\031:eS\006t7+\0369feZL7o\034:TiJ\fG/Z4z\007>tg-[4ve\006$xN]\013\003\005\003\0032a\002BB\023\r\021)I\001\002\037'V\004XM\035<jg>\0248\013\036:bi\026<\027pQ8oM&<WO]1u_JDqA!#\002\026\021Ea%\001\013s_>$x)^1sI&\fgn\025;sCR,w-\037\005\b\005\033\013)\002\"\005'\003A9W/\031:eS\006t7\013\036:bi\026<\027\020C\004\003\022\006UA\021\003\024\002-ML8\017^3n\017V\f'\017Z5b]N#(/\031;fOfD1B!&\002\026!\025\r\021\"\003\003\030\006\tB-\0324bk2$H)[:qCR\034\007.\032:\026\005\te\005c\001\017\003\034&\031!QT\017\003#5+7o]1hK\022K7\017]1uG\",'\017C\006\003\"\006U\001\022!Q!\n\te\025A\0053fM\006,H\016\036#jgB\fGo\0315fe\002B1B!*\002\026!\025\r\021\"\003\003(\006qA-\0324bk2$X*Y5mE>DXC\001BU!\ra\"1V\005\004\005[k\"aC'bS2\024w\016\037+za\026D1B!-\002\026!\005\t\025)\003\003*\006yA-\0324bk2$X*Y5mE>D\b\005C\006\0036\006U\001R1A\005B\t]\026\001\004:p_R<U/\031:eS\006tWC\001B]!\r9!1X\005\004\005{\023!!\004'pG\006d\027i\031;peJ+g\rC\006\003B\006U\001\022!Q!\n\te\026!\004:p_R<U/\031:eS\006t\007\005\003\005\003F\006UA\021\tBd\0039\021xn\034;Hk\006\024H-[1o\003R$2a\027Be\021!\021YMa1A\002\t5\027aB1eIJ,7o\035\t\004\017\t=\027b\001Bi\005\t9\021\t\0323sKN\034\bBC-\002\026!\025\r\021\"\021\0038\"Qq,!\006\t\002\003\006KA!/\t\027\te\027Q\003EC\002\023\005#qW\001\017gf\034H/Z7Hk\006\024H-[1o\021-\021i.!\006\t\002\003\006KA!/\002\037ML8\017^3n\017V\f'\017Z5b]\002B1B!9\002\026!\025\r\021\"\001\003d\006iA/Z7q\007>tG/Y5oKJ,\"A!:\021\007\035\0219/C\002\003j\n\021ACV5siV\fG\016U1uQ\016{g\016^1j]\026\024\bb\003Bw\003+A\t\021)Q\005\005K\fa\002^3na\016{g\016^1j]\026\024\b\005\003\005\003r\006UA\021\001Bz\003E\021XmZ5ti\026\024H+Z7q\003\016$xN\035\013\006u\tU(\021 \005\t\005o\024y\0171\001\002\022\006A\021m\031;peJ+g\r\003\005\003|\n=\b\031AAF\003\021\001\030\r\0365\t\021\t}\030Q\003C\001\007\003\t1#\0368sK\036L7\017^3s)\026l\007/Q2u_J$2AOB\002\021!\021YP!@A\002\005-\005\002CB\004\003+!\ta!\003\002\t%t\027\016\036\013\004u\r-\001\002CB\007\007\013\001\rA!\b\002\017}\033\030p\035;f[\"A1\021CA\013\t\003\032\031\"\001\005bGR|'OR8s)\031\t\tj!\006\004\032!A1qCB\b\001\004\t\t*A\002sK\032D\001Ba?\004\020\001\007\0211\005\025\t\007\037\031iba\t\004(A\031Aba\b\n\007\r\005RB\001\006eKB\024XmY1uK\022\f#a!\n\002MU\034X\rI1di>\0248+\0327fGRLwN\034\021j]N$X-\0313!_\032\004\023m\031;pe\032{'/\t\002\004*\005\031!G\f\032\t\021\rE\021Q\003C!\007[!B!!%\0040!A!1`B\026\001\004\tY\t\013\005\004,\ru11EB\024\021!\031\t\"!\006\005B\rUBCBAI\007o\031I\004\003\005\004\030\rM\002\031AAI\021!\021Ypa\rA\002\rm\002#B\"\004>\005\r\022bAB \033\nA\021\n^3sC\ndW\r\013\005\0044\ru11EB\024\021!\031)%!\006\005\002\r\035\023a\004:fg>dg/Z!di>\024(+\0324\025\007m\033I\005\003\005\003|\016\r\003\031AA\022\021!\031)%!\006\005\002\r5CcA.\004P!A!1`B&\001\004\tY\tC\005\004F\005UA\021\001\003\004TQ1\021\021SB+\007/B\001ba\006\004R\001\007\021\021\023\005\t\0073\032\t\0061\001\004<\005a\001/\031;i\0132,W.\0328ug\"A1QLA\013\t\003\031y&A\004bGR|'o\0244\025%\005E5\021MB2\007[\032\tha\035\004~\r%5Q\022\005\t\0053\031Y\0061\001\003\036!A1QMB.\001\004\0319'A\003qe>\0048\017E\002\b\007SJ1aa\033\003\005\025\001&o\0349t\021!\031yga\027A\002\005E\025AC:va\026\024h/[:pe\"A!1`B.\001\004\tY\t\003\005\004v\rm\003\031AB<\0035\031\030p\035;f[N+'O^5dKB\031Ab!\037\n\007\rmTBA\004C_>dW-\0318\t\021\r}41\fa\001\007\003\013a\001Z3qY>L\b\003\002\007R\007\007\0032aBBC\023\r\0319I\001\002\007\t\026\004Hn\\=\t\021\r-51\fa\001\007o\nA\002\\8pWV\004H)\0329m_fD\001ba$\004\\\001\0071qO\001\006CNLhn\031\005\t\007'\013)\002\"\001\004\026\006)r-\032;FqR,'O\\1m\003\022$'/Z:t\r>\024H\003BBL\0073\003B\001D)\003N\"A11TBI\001\004\021i-\001\003bI\022\024\b\002CBP\003+!\ta!)\002#\035,G\017R3gCVdG/\0213ee\026\0348/\006\002\003N\002")
/*     */ public class LocalActorRefProvider implements ActorRefProvider {
/*     */   private final ActorSystem.Settings settings;
/*     */   
/*     */   private final EventStream eventStream;
/*     */   
/*     */   private final DynamicAccess dynamicAccess;
/*     */   
/*     */   private final Deployer deployer;
/*     */   
/*     */   private final ActorPath rootPath;
/*     */   
/*     */   private final LoggingAdapter log;
/*     */   
/*     */   private final InternalActorRef deadLetters;
/*     */   
/*     */   private final AtomicLong tempNumber;
/*     */   
/*     */   private final ActorPath tempNode;
/*     */   
/*     */   private final InternalActorRef theOneWhoWalksTheBubblesOfSpaceTime;
/*     */   
/*     */   private volatile ActorSystemImpl akka$actor$LocalActorRefProvider$$system;
/*     */   
/*     */   private Promise<BoxedUnit> terminationPromise;
/*     */   
/*     */   private volatile Map<String, InternalActorRef> akka$actor$LocalActorRefProvider$$extraNames;
/*     */   
/*     */   private MessageDispatcher akka$actor$LocalActorRefProvider$$defaultDispatcher;
/*     */   
/*     */   private MailboxType akka$actor$LocalActorRefProvider$$defaultMailbox;
/*     */   
/*     */   private LocalActorRef rootGuardian;
/*     */   
/*     */   private LocalActorRef guardian;
/*     */   
/*     */   private LocalActorRef systemGuardian;
/*     */   
/*     */   private VirtualPathContainer tempContainer;
/*     */   
/*     */   private volatile byte bitmap$0;
/*     */   
/*     */   public static class Guardian implements Actor, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */     private final SupervisorStrategy supervisorStrategy;
/*     */     
/*     */     private final ActorContext context;
/*     */     
/*     */     private final ActorRef self;
/*     */     
/*     */     public ActorContext context() {
/* 367 */       return this.context;
/*     */     }
/*     */     
/*     */     public final ActorRef self() {
/* 367 */       return this.self;
/*     */     }
/*     */     
/*     */     public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 367 */       this.context = x$1;
/*     */     }
/*     */     
/*     */     public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 367 */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public final ActorRef sender() {
/* 367 */       return Actor$class.sender(this);
/*     */     }
/*     */     
/*     */     public void aroundReceive(PartialFunction receive, Object msg) {
/* 367 */       Actor$class.aroundReceive(this, receive, msg);
/*     */     }
/*     */     
/*     */     public void aroundPreStart() {
/* 367 */       Actor$class.aroundPreStart(this);
/*     */     }
/*     */     
/*     */     public void aroundPostStop() {
/* 367 */       Actor$class.aroundPostStop(this);
/*     */     }
/*     */     
/*     */     public void aroundPreRestart(Throwable reason, Option message) {
/* 367 */       Actor$class.aroundPreRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void aroundPostRestart(Throwable reason) {
/* 367 */       Actor$class.aroundPostRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void preStart() throws Exception {
/* 367 */       Actor$class.preStart(this);
/*     */     }
/*     */     
/*     */     public void postStop() throws Exception {
/* 367 */       Actor$class.postStop(this);
/*     */     }
/*     */     
/*     */     public void postRestart(Throwable reason) throws Exception {
/* 367 */       Actor$class.postRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void unhandled(Object message) {
/* 367 */       Actor$class.unhandled(this, message);
/*     */     }
/*     */     
/*     */     public SupervisorStrategy supervisorStrategy() {
/* 367 */       return this.supervisorStrategy;
/*     */     }
/*     */     
/*     */     public Guardian(SupervisorStrategy supervisorStrategy) {
/* 367 */       Actor$class.$init$(this);
/*     */     }
/*     */     
/*     */     public PartialFunction<Object, BoxedUnit> receive() {
/* 370 */       return (PartialFunction<Object, BoxedUnit>)new LocalActorRefProvider$Guardian$$anonfun$receive$1(this);
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$Guardian$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */         BoxedUnit boxedUnit;
/* 370 */         Object object = x1;
/* 371 */         if (object instanceof Terminated) {
/* 371 */           this.$outer.context().stop(this.$outer.self());
/* 371 */           boxedUnit = BoxedUnit.UNIT;
/* 372 */         } else if (object instanceof StopChild) {
/* 372 */           StopChild stopChild = (StopChild)object;
/* 372 */           ActorRef child = stopChild.child();
/* 372 */           this.$outer.context().stop(child);
/* 372 */           boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/* 373 */           this.$outer.context().system().deadLetters().forward(new DeadLetter(object, this.$outer.sender(), this.$outer.self()), this.$outer.context());
/* 373 */           boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */         return (B1)boxedUnit;
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Object x1) {
/*     */         boolean bool;
/*     */         Object object = x1;
/*     */         if (object instanceof Terminated) {
/*     */           bool = true;
/*     */         } else if (object instanceof StopChild) {
/*     */           bool = true;
/*     */         } else {
/* 373 */           bool = true;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$Guardian$$anonfun$receive$1(LocalActorRefProvider.Guardian $outer) {}
/*     */     }
/*     */     
/*     */     public void preRestart(Throwable cause, Option msg) {}
/*     */   }
/*     */   
/*     */   public static class SystemGuardian implements Actor, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */     private final SupervisorStrategy supervisorStrategy;
/*     */     
/*     */     private final ActorRef guardian;
/*     */     
/*     */     private Set<ActorRef> terminationHooks;
/*     */     
/*     */     private final ActorContext context;
/*     */     
/*     */     private final ActorRef self;
/*     */     
/*     */     public ActorContext context() {
/* 383 */       return this.context;
/*     */     }
/*     */     
/*     */     public final ActorRef self() {
/* 383 */       return this.self;
/*     */     }
/*     */     
/*     */     public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 383 */       this.context = x$1;
/*     */     }
/*     */     
/*     */     public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 383 */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public final ActorRef sender() {
/* 383 */       return Actor$class.sender(this);
/*     */     }
/*     */     
/*     */     public void aroundReceive(PartialFunction receive, Object msg) {
/* 383 */       Actor$class.aroundReceive(this, receive, msg);
/*     */     }
/*     */     
/*     */     public void aroundPreStart() {
/* 383 */       Actor$class.aroundPreStart(this);
/*     */     }
/*     */     
/*     */     public void aroundPostStop() {
/* 383 */       Actor$class.aroundPostStop(this);
/*     */     }
/*     */     
/*     */     public void aroundPreRestart(Throwable reason, Option message) {
/* 383 */       Actor$class.aroundPreRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void aroundPostRestart(Throwable reason) {
/* 383 */       Actor$class.aroundPostRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void preStart() throws Exception {
/* 383 */       Actor$class.preStart(this);
/*     */     }
/*     */     
/*     */     public void postStop() throws Exception {
/* 383 */       Actor$class.postStop(this);
/*     */     }
/*     */     
/*     */     public void postRestart(Throwable reason) throws Exception {
/* 383 */       Actor$class.postRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void unhandled(Object message) {
/* 383 */       Actor$class.unhandled(this, message);
/*     */     }
/*     */     
/*     */     public SupervisorStrategy supervisorStrategy() {
/* 383 */       return this.supervisorStrategy;
/*     */     }
/*     */     
/*     */     public ActorRef guardian() {
/* 383 */       return this.guardian;
/*     */     }
/*     */     
/*     */     public SystemGuardian(SupervisorStrategy supervisorStrategy, ActorRef guardian) {
/* 383 */       Actor$class.$init$(this);
/* 387 */       this.terminationHooks = Predef$.MODULE$.Set().empty();
/*     */     }
/*     */     
/*     */     public Set<ActorRef> terminationHooks() {
/* 387 */       return this.terminationHooks;
/*     */     }
/*     */     
/*     */     public void terminationHooks_$eq(Set<ActorRef> x$1) {
/* 387 */       this.terminationHooks = x$1;
/*     */     }
/*     */     
/*     */     public PartialFunction<Object, BoxedUnit> receive() {
/* 389 */       return (PartialFunction<Object, BoxedUnit>)new LocalActorRefProvider$SystemGuardian$$anonfun$receive$2(this);
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$SystemGuardian$$anonfun$receive$2 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public LocalActorRefProvider$SystemGuardian$$anonfun$receive$2(LocalActorRefProvider.SystemGuardian $outer) {}
/*     */       
/*     */       public final boolean isDefinedAt(Object x2) {
/*     */         // Byte code:
/*     */         //   0: iconst_0
/*     */         //   1: istore_2
/*     */         //   2: aconst_null
/*     */         //   3: pop
/*     */         //   4: aconst_null
/*     */         //   5: astore_3
/*     */         //   6: aload_1
/*     */         //   7: astore #4
/*     */         //   9: aload #4
/*     */         //   11: instanceof akka/actor/Terminated
/*     */         //   14: ifeq -> 69
/*     */         //   17: iconst_1
/*     */         //   18: istore_2
/*     */         //   19: aload #4
/*     */         //   21: checkcast akka/actor/Terminated
/*     */         //   24: astore_3
/*     */         //   25: aload_3
/*     */         //   26: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */         //   29: astore #5
/*     */         //   31: aload_0
/*     */         //   32: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   35: invokevirtual guardian : ()Lakka/actor/ActorRef;
/*     */         //   38: aload #5
/*     */         //   40: astore #6
/*     */         //   42: dup
/*     */         //   43: ifnonnull -> 55
/*     */         //   46: pop
/*     */         //   47: aload #6
/*     */         //   49: ifnull -> 63
/*     */         //   52: goto -> 69
/*     */         //   55: aload #6
/*     */         //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   60: ifeq -> 69
/*     */         //   63: iconst_1
/*     */         //   64: istore #7
/*     */         //   66: goto -> 175
/*     */         //   69: iload_2
/*     */         //   70: ifeq -> 79
/*     */         //   73: iconst_1
/*     */         //   74: istore #7
/*     */         //   76: goto -> 175
/*     */         //   79: aload #4
/*     */         //   81: instanceof akka/actor/StopChild
/*     */         //   84: ifeq -> 93
/*     */         //   87: iconst_1
/*     */         //   88: istore #7
/*     */         //   90: goto -> 175
/*     */         //   93: getstatic akka/actor/SystemGuardian$RegisterTerminationHook$.MODULE$ : Lakka/actor/SystemGuardian$RegisterTerminationHook$;
/*     */         //   96: aload #4
/*     */         //   98: astore #8
/*     */         //   100: dup
/*     */         //   101: ifnonnull -> 113
/*     */         //   104: pop
/*     */         //   105: aload #8
/*     */         //   107: ifnull -> 121
/*     */         //   110: goto -> 172
/*     */         //   113: aload #8
/*     */         //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   118: ifeq -> 172
/*     */         //   121: aload_0
/*     */         //   122: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   125: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   128: aload_0
/*     */         //   129: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   132: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   135: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */         //   140: invokevirtual deadLetters : ()Lakka/actor/ActorRef;
/*     */         //   143: astore #9
/*     */         //   145: dup
/*     */         //   146: ifnonnull -> 158
/*     */         //   149: pop
/*     */         //   150: aload #9
/*     */         //   152: ifnull -> 172
/*     */         //   155: goto -> 166
/*     */         //   158: aload #9
/*     */         //   160: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   163: ifne -> 172
/*     */         //   166: iconst_1
/*     */         //   167: istore #7
/*     */         //   169: goto -> 175
/*     */         //   172: iconst_1
/*     */         //   173: istore #7
/*     */         //   175: iload #7
/*     */         //   177: ireturn
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #390	-> 0
/*     */         //   #389	-> 6
/*     */         //   #390	-> 9
/*     */         //   #389	-> 69
/*     */         //   #400	-> 73
/*     */         //   #401	-> 79
/*     */         //   #402	-> 93
/*     */         //   #405	-> 172
/*     */         //   #389	-> 175
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	178	0	this	Lakka/actor/LocalActorRefProvider$SystemGuardian$$anonfun$receive$2;
/*     */         //   0	178	1	x2	Ljava/lang/Object;
/*     */       }
/*     */       
/*     */       public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */         // Byte code:
/*     */         //   0: iconst_0
/*     */         //   1: istore_3
/*     */         //   2: aconst_null
/*     */         //   3: pop
/*     */         //   4: aconst_null
/*     */         //   5: astore #4
/*     */         //   7: aload_1
/*     */         //   8: astore #5
/*     */         //   10: aload #5
/*     */         //   12: instanceof akka/actor/Terminated
/*     */         //   15: ifeq -> 120
/*     */         //   18: iconst_1
/*     */         //   19: istore_3
/*     */         //   20: aload #5
/*     */         //   22: checkcast akka/actor/Terminated
/*     */         //   25: astore #4
/*     */         //   27: aload #4
/*     */         //   29: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */         //   32: astore #6
/*     */         //   34: aload_0
/*     */         //   35: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   38: invokevirtual guardian : ()Lakka/actor/ActorRef;
/*     */         //   41: aload #6
/*     */         //   43: astore #7
/*     */         //   45: dup
/*     */         //   46: ifnonnull -> 58
/*     */         //   49: pop
/*     */         //   50: aload #7
/*     */         //   52: ifnull -> 66
/*     */         //   55: goto -> 120
/*     */         //   58: aload #7
/*     */         //   60: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   63: ifeq -> 120
/*     */         //   66: aload_0
/*     */         //   67: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   70: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   73: aload_0
/*     */         //   74: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   77: invokevirtual terminating : ()Lscala/PartialFunction;
/*     */         //   80: invokeinterface become : (Lscala/PartialFunction;)V
/*     */         //   85: aload_0
/*     */         //   86: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   89: invokevirtual terminationHooks : ()Lscala/collection/immutable/Set;
/*     */         //   92: new akka/actor/LocalActorRefProvider$SystemGuardian$$anonfun$receive$2$$anonfun$applyOrElse$1
/*     */         //   95: dup
/*     */         //   96: aload_0
/*     */         //   97: invokespecial <init> : (Lakka/actor/LocalActorRefProvider$SystemGuardian$$anonfun$receive$2;)V
/*     */         //   100: invokeinterface foreach : (Lscala/Function1;)V
/*     */         //   105: aload_0
/*     */         //   106: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   109: invokevirtual stopWhenAllTerminationHooksDone : ()V
/*     */         //   112: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   115: astore #8
/*     */         //   117: goto -> 390
/*     */         //   120: iload_3
/*     */         //   121: ifeq -> 163
/*     */         //   124: aload #4
/*     */         //   126: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */         //   129: astore #9
/*     */         //   131: aload_0
/*     */         //   132: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   135: aload_0
/*     */         //   136: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   139: invokevirtual terminationHooks : ()Lscala/collection/immutable/Set;
/*     */         //   142: aload #9
/*     */         //   144: invokeinterface $minus : (Ljava/lang/Object;)Lscala/collection/Set;
/*     */         //   149: checkcast scala/collection/immutable/Set
/*     */         //   152: invokevirtual terminationHooks_$eq : (Lscala/collection/immutable/Set;)V
/*     */         //   155: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   158: astore #8
/*     */         //   160: goto -> 390
/*     */         //   163: aload #5
/*     */         //   165: instanceof akka/actor/StopChild
/*     */         //   168: ifeq -> 207
/*     */         //   171: aload #5
/*     */         //   173: checkcast akka/actor/StopChild
/*     */         //   176: astore #10
/*     */         //   178: aload #10
/*     */         //   180: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */         //   183: astore #11
/*     */         //   185: aload_0
/*     */         //   186: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   189: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   192: aload #11
/*     */         //   194: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */         //   199: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   202: astore #8
/*     */         //   204: goto -> 390
/*     */         //   207: getstatic akka/actor/SystemGuardian$RegisterTerminationHook$.MODULE$ : Lakka/actor/SystemGuardian$RegisterTerminationHook$;
/*     */         //   210: aload #5
/*     */         //   212: astore #12
/*     */         //   214: dup
/*     */         //   215: ifnonnull -> 227
/*     */         //   218: pop
/*     */         //   219: aload #12
/*     */         //   221: ifnull -> 235
/*     */         //   224: goto -> 337
/*     */         //   227: aload #12
/*     */         //   229: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   232: ifeq -> 337
/*     */         //   235: aload_0
/*     */         //   236: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   239: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   242: aload_0
/*     */         //   243: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   246: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   249: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */         //   254: invokevirtual deadLetters : ()Lakka/actor/ActorRef;
/*     */         //   257: astore #13
/*     */         //   259: dup
/*     */         //   260: ifnonnull -> 272
/*     */         //   263: pop
/*     */         //   264: aload #13
/*     */         //   266: ifnull -> 337
/*     */         //   269: goto -> 280
/*     */         //   272: aload #13
/*     */         //   274: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   277: ifne -> 337
/*     */         //   280: aload_0
/*     */         //   281: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   284: aload_0
/*     */         //   285: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   288: invokevirtual terminationHooks : ()Lscala/collection/immutable/Set;
/*     */         //   291: aload_0
/*     */         //   292: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   295: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   298: invokeinterface $plus : (Ljava/lang/Object;)Lscala/collection/Set;
/*     */         //   303: checkcast scala/collection/immutable/Set
/*     */         //   306: invokevirtual terminationHooks_$eq : (Lscala/collection/immutable/Set;)V
/*     */         //   309: aload_0
/*     */         //   310: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   313: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   316: aload_0
/*     */         //   317: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   320: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   323: invokeinterface watch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */         //   328: pop
/*     */         //   329: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   332: astore #8
/*     */         //   334: goto -> 390
/*     */         //   337: aload_0
/*     */         //   338: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   341: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   344: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */         //   349: invokevirtual deadLetters : ()Lakka/actor/ActorRef;
/*     */         //   352: new akka/actor/DeadLetter
/*     */         //   355: dup
/*     */         //   356: aload #5
/*     */         //   358: aload_0
/*     */         //   359: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   362: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   365: aload_0
/*     */         //   366: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   369: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */         //   372: invokespecial <init> : (Ljava/lang/Object;Lakka/actor/ActorRef;Lakka/actor/ActorRef;)V
/*     */         //   375: aload_0
/*     */         //   376: getfield $outer : Lakka/actor/LocalActorRefProvider$SystemGuardian;
/*     */         //   379: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   382: invokevirtual forward : (Ljava/lang/Object;Lakka/actor/ActorContext;)V
/*     */         //   385: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   388: astore #8
/*     */         //   390: aload #8
/*     */         //   392: areturn
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #390	-> 0
/*     */         //   #389	-> 7
/*     */         //   #390	-> 10
/*     */         //   #394	-> 66
/*     */         //   #395	-> 85
/*     */         //   #396	-> 105
/*     */         //   #390	-> 115
/*     */         //   #389	-> 120
/*     */         //   #397	-> 124
/*     */         //   #400	-> 131
/*     */         //   #401	-> 163
/*     */         //   #402	-> 207
/*     */         //   #403	-> 280
/*     */         //   #404	-> 309
/*     */         //   #402	-> 332
/*     */         //   #405	-> 337
/*     */         //   #389	-> 390
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	393	0	this	Lakka/actor/LocalActorRefProvider$SystemGuardian$$anonfun$receive$2;
/*     */         //   0	393	1	x2	Ljava/lang/Object;
/*     */         //   0	393	2	default	Lscala/Function1;
/*     */         //   131	262	9	a	Lakka/actor/ActorRef;
/*     */         //   185	208	11	child	Lakka/actor/ActorRef;
/*     */       }
/*     */       
/*     */       public class LocalActorRefProvider$SystemGuardian$$anonfun$receive$2$$anonfun$applyOrElse$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final void apply(ActorRef x$1) {
/* 395 */           package$.MODULE$.actorRef2Scala(x$1).$bang(SystemGuardian.TerminationHook$.MODULE$, this.$outer.akka$actor$LocalActorRefProvider$SystemGuardian$$anonfun$$$outer().self());
/*     */         }
/*     */         
/*     */         public LocalActorRefProvider$SystemGuardian$$anonfun$receive$2$$anonfun$applyOrElse$1(LocalActorRefProvider$SystemGuardian$$anonfun$receive$2 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public PartialFunction<Object, BoxedUnit> terminating() {
/* 408 */       return (PartialFunction<Object, BoxedUnit>)new LocalActorRefProvider$SystemGuardian$$anonfun$terminating$1(this);
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$SystemGuardian$$anonfun$terminating$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/*     */         BoxedUnit boxedUnit;
/* 408 */         Object object = x3;
/* 409 */         if (object instanceof Terminated) {
/* 409 */           Terminated terminated = (Terminated)object;
/* 409 */           ActorRef a = terminated.actor();
/* 409 */           this.$outer.stopWhenAllTerminationHooksDone(a);
/* 409 */           boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/* 410 */           Object object1 = object;
/* 410 */           if (SystemGuardian.TerminationHookDone$.MODULE$ == null) {
/* 410 */             if (object1 != null) {
/* 411 */               this.$outer.context().system().deadLetters().forward(new DeadLetter(object, this.$outer.sender(), this.$outer.self()), this.$outer.context());
/* 411 */               BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */             } 
/*     */           } else {
/*     */             if (SystemGuardian.TerminationHookDone$.MODULE$.equals(object1)) {
/*     */               this.$outer.stopWhenAllTerminationHooksDone(this.$outer.sender());
/*     */               BoxedUnit boxedUnit2 = BoxedUnit.UNIT;
/*     */             } 
/* 411 */             this.$outer.context().system().deadLetters().forward(new DeadLetter(object, this.$outer.sender(), this.$outer.self()), this.$outer.context());
/* 411 */             BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */           } 
/*     */           this.$outer.stopWhenAllTerminationHooksDone(this.$outer.sender());
/*     */           boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */         return (B1)boxedUnit;
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Object x3) {
/*     */         boolean bool;
/*     */         Object object = x3;
/*     */         if (object instanceof Terminated) {
/*     */           bool = true;
/*     */         } else {
/*     */           Object object1 = object;
/*     */           if (SystemGuardian.TerminationHookDone$.MODULE$ == null) {
/*     */             if (object1 != null)
/* 411 */               boolean bool1 = true; 
/*     */           } else {
/*     */             if (SystemGuardian.TerminationHookDone$.MODULE$.equals(object1))
/*     */               boolean bool2 = true; 
/* 411 */             boolean bool1 = true;
/*     */           } 
/*     */           bool = true;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$SystemGuardian$$anonfun$terminating$1(LocalActorRefProvider.SystemGuardian $outer) {}
/*     */     }
/*     */     
/*     */     public void stopWhenAllTerminationHooksDone(ActorRef remove) {
/* 415 */       terminationHooks_$eq((Set<ActorRef>)terminationHooks().$minus(remove));
/* 416 */       stopWhenAllTerminationHooksDone();
/*     */     }
/*     */     
/*     */     public void stopWhenAllTerminationHooksDone() {
/* 420 */       if (terminationHooks().isEmpty()) {
/* 421 */         context().system().eventStream().stopDefaultLoggers(context().system());
/* 422 */         context().stop(self());
/*     */       } 
/*     */     }
/*     */     
/*     */     public void preRestart(Throwable cause, Option msg) {}
/*     */   }
/*     */   
/*     */   public ActorSystem.Settings settings() {
/* 440 */     return this.settings;
/*     */   }
/*     */   
/*     */   public LocalActorRefProvider(String _systemName, ActorSystem.Settings settings, EventStream eventStream, DynamicAccess dynamicAccess, Deployer deployer, Option _deadLetters) {
/* 459 */     this.rootPath = new RootActorPath(Address$.MODULE$.apply("akka", _systemName), RootActorPath$.MODULE$.apply$default$2());
/* 461 */     this.log = Logging$.MODULE$.apply((LoggingBus)eventStream, (new StringBuilder()).append("LocalActorRefProvider(").append(rootPath().address()).append(")").toString(), LogSource$.MODULE$.fromString());
/* 463 */     this.deadLetters = 
/* 464 */       (InternalActorRef)((Function1)_deadLetters.getOrElse((Function0)new $anonfun$1(this))).apply(rootPath().$div("deadLetters"));
/* 469 */     this.tempNumber = new AtomicLong();
/* 473 */     this.tempNode = rootPath().$div("temp");
/* 481 */     this.theOneWhoWalksTheBubblesOfSpaceTime = new $anon$2(this);
/* 527 */     this.akka$actor$LocalActorRefProvider$$extraNames = (Map<String, InternalActorRef>)Predef$.MODULE$.Map().apply((Seq)Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public EventStream eventStream() {
/*     */     return this.eventStream;
/*     */   }
/*     */   
/*     */   public DynamicAccess dynamicAccess() {
/*     */     return this.dynamicAccess;
/*     */   }
/*     */   
/*     */   public Deployer deployer() {
/*     */     return this.deployer;
/*     */   }
/*     */   
/*     */   public LocalActorRefProvider(String _systemName, ActorSystem.Settings settings, EventStream eventStream, DynamicAccess dynamicAccess) {
/*     */     this(_systemName, settings, eventStream, dynamicAccess, new Deployer(settings, dynamicAccess), (Option<Function1<ActorPath, InternalActorRef>>)None$.MODULE$);
/*     */   }
/*     */   
/*     */   public ActorPath rootPath() {
/*     */     return this.rootPath;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*     */     return this.log;
/*     */   }
/*     */   
/*     */   public InternalActorRef deadLetters() {
/*     */     return this.deadLetters;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction0<Function1<ActorPath, DeadLetterActorRef>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function1<ActorPath, DeadLetterActorRef> apply() {
/*     */       return (Function1<ActorPath, DeadLetterActorRef>)new LocalActorRefProvider$$anonfun$1$$anonfun$apply$1(this);
/*     */     }
/*     */     
/*     */     public $anonfun$1(LocalActorRefProvider $outer) {}
/*     */     
/*     */     public class LocalActorRefProvider$$anonfun$1$$anonfun$apply$1 extends AbstractFunction1<ActorPath, DeadLetterActorRef> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final DeadLetterActorRef apply(ActorPath p) {
/*     */         return new DeadLetterActorRef(this.$outer.akka$actor$LocalActorRefProvider$$anonfun$$$outer(), p, this.$outer.akka$actor$LocalActorRefProvider$$anonfun$$$outer().eventStream());
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anonfun$1$$anonfun$apply$1(LocalActorRefProvider.$anonfun$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private AtomicLong tempNumber() {
/*     */     return this.tempNumber;
/*     */   }
/*     */   
/*     */   private String tempName() {
/*     */     return Helpers$.MODULE$.base64(tempNumber().getAndIncrement(), Helpers$.MODULE$.base64$default$2());
/*     */   }
/*     */   
/*     */   private ActorPath tempNode() {
/*     */     return this.tempNode;
/*     */   }
/*     */   
/*     */   public ActorPath tempPath() {
/*     */     return tempNode().$div(tempName());
/*     */   }
/*     */   
/*     */   public InternalActorRef theOneWhoWalksTheBubblesOfSpaceTime() {
/*     */     return this.theOneWhoWalksTheBubblesOfSpaceTime;
/*     */   }
/*     */   
/*     */   public class $anon$2 extends InternalActorRef implements MinimalActorRef {
/*     */     private final Switch stopped;
/*     */     
/*     */     private volatile Option<Throwable> akka$actor$LocalActorRefProvider$$anon$$causeOfTermination;
/*     */     
/*     */     private final ActorPath path;
/*     */     
/*     */     public InternalActorRef getParent() {
/*     */       return MinimalActorRef$class.getParent(this);
/*     */     }
/*     */     
/*     */     public InternalActorRef getChild(Iterator names) {
/*     */       return MinimalActorRef$class.getChild(this, names);
/*     */     }
/*     */     
/*     */     public void start() {
/*     */       MinimalActorRef$class.start(this);
/*     */     }
/*     */     
/*     */     public void suspend() {
/*     */       MinimalActorRef$class.suspend(this);
/*     */     }
/*     */     
/*     */     public void resume(Throwable causedByFailure) {
/*     */       MinimalActorRef$class.resume(this, causedByFailure);
/*     */     }
/*     */     
/*     */     public void restart(Throwable cause) {
/*     */       MinimalActorRef$class.restart(this, cause);
/*     */     }
/*     */     
/*     */     public Object writeReplace() throws ObjectStreamException {
/*     */       return MinimalActorRef$class.writeReplace(this);
/*     */     }
/*     */     
/*     */     public final boolean isLocal() {
/*     */       return LocalRef$class.isLocal(this);
/*     */     }
/*     */     
/*     */     public $anon$2(LocalActorRefProvider $outer) {
/*     */       LocalRef$class.$init$(this);
/*     */       MinimalActorRef$class.$init$(this);
/*     */       this.stopped = new Switch(false);
/*     */       this.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination = (Option<Throwable>)None$.MODULE$;
/*     */       this.path = $outer.rootPath().$div("bubble-walker");
/*     */     }
/*     */     
/*     */     private Switch stopped() {
/*     */       return this.stopped;
/*     */     }
/*     */     
/*     */     public Option<Throwable> akka$actor$LocalActorRefProvider$$anon$$causeOfTermination() {
/*     */       return this.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination;
/*     */     }
/*     */     
/*     */     public void akka$actor$LocalActorRefProvider$$anon$$causeOfTermination_$eq(Option<Throwable> x$1) {
/*     */       this.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination = x$1;
/*     */     }
/*     */     
/*     */     public ActorPath path() {
/*     */       return this.path;
/*     */     }
/*     */     
/*     */     public ActorRefProvider provider() {
/*     */       return this.$outer;
/*     */     }
/*     */     
/*     */     public void stop() {
/*     */       stopped().switchOn((Function0)new LocalActorRefProvider$$anon$2$$anonfun$stop$1(this));
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$2$$anonfun$stop$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().terminationPromise().complete((Try)this.$outer.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination().map((Function1)new LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$1(this)).getOrElse((Function0)new LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$2(this)));
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$2$$anonfun$stop$1(LocalActorRefProvider.$anon$2 $outer) {}
/*     */       
/*     */       public class LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction1<Throwable, Failure<Nothing$>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Failure<Nothing$> apply(Throwable x$2) {
/*     */           return new Failure(x$2);
/*     */         }
/*     */         
/*     */         public LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$1(LocalActorRefProvider$$anon$2$$anonfun$stop$1 $outer) {}
/*     */       }
/*     */       
/*     */       public class LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction0<Success<BoxedUnit>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Success<BoxedUnit> apply() {
/*     */           return new Success(BoxedUnit.UNIT);
/*     */         }
/*     */         
/*     */         public LocalActorRefProvider$$anon$2$$anonfun$stop$1$$anonfun$apply$mcV$sp$2(LocalActorRefProvider$$anon$2$$anonfun$stop$1 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean isTerminated() {
/*     */       return stopped().isOn();
/*     */     }
/*     */     
/*     */     public void $bang(Object message, ActorRef sender) {
/*     */       stopped().ifOff((Function0)new LocalActorRefProvider$$anon$2$$anonfun$$bang$1(this, message));
/*     */     }
/*     */     
/*     */     public ActorRef $bang$default$2(Object message) {
/*     */       return Actor$.MODULE$.noSender();
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$2$$anonfun$$bang$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object message$1;
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         Object object = this.message$1;
/*     */         if (object == null)
/*     */           throw new InvalidMessageException("Message is null"); 
/*     */         (new String[3])[0] = "";
/*     */         (new String[3])[1] = " received unexpected message [";
/*     */         (new String[3])[2] = "]";
/*     */         this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().log().error((new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.$outer, this.message$1 })));
/*     */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$2$$anonfun$$bang$1(LocalActorRefProvider.$anon$2 $outer, Object message$1) {}
/*     */     }
/*     */     
/*     */     public void sendSystemMessage(SystemMessage message) {
/*     */       stopped().ifOff((Function0)new LocalActorRefProvider$$anon$2$$anonfun$sendSystemMessage$1(this, message));
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$2$$anonfun$sendSystemMessage$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SystemMessage message$2;
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         SystemMessage systemMessage = this.message$2;
/*     */         if (systemMessage instanceof Failed) {
/*     */           Failed failed = (Failed)systemMessage;
/*     */           ActorRef child = failed.child();
/*     */           Throwable ex = failed.cause();
/*     */           (new String[2])[0] = "guardian ";
/*     */           (new String[2])[1] = " failed, shutting down!";
/*     */           this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().log().error(ex, (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { child })));
/*     */           this.$outer.akka$actor$LocalActorRefProvider$$anon$$causeOfTermination_$eq((Option<Throwable>)new Some(ex));
/*     */           ((InternalActorRef)child).stop();
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else if (systemMessage instanceof akka.dispatch.sysmsg.Supervise) {
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else if (systemMessage instanceof akka.dispatch.sysmsg.DeathWatchNotification) {
/*     */           this.$outer.stop();
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/*     */           (new String[3])[0] = "";
/*     */           (new String[3])[1] = " received unexpected system message [";
/*     */           (new String[3])[2] = "]";
/*     */           this.$outer.akka$actor$LocalActorRefProvider$$anon$$$outer().log().error((new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { this.$outer, this.message$2 })));
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$2$$anonfun$sendSystemMessage$1(LocalActorRefProvider.$anon$2 $outer, SystemMessage message$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public ActorSystemImpl akka$actor$LocalActorRefProvider$$system() {
/*     */     return this.akka$actor$LocalActorRefProvider$$system;
/*     */   }
/*     */   
/*     */   private void akka$actor$LocalActorRefProvider$$system_$eq(ActorSystemImpl x$1) {
/*     */     this.akka$actor$LocalActorRefProvider$$system = x$1;
/*     */   }
/*     */   
/*     */   private Promise terminationPromise$lzycompute() {
/*     */     synchronized (this) {
/*     */       if ((byte)(this.bitmap$0 & 0x1) == 0) {
/*     */         this.terminationPromise = Promise$.MODULE$.apply();
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */       } 
/*     */       return this.terminationPromise;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Promise<BoxedUnit> terminationPromise() {
/*     */     return ((byte)(this.bitmap$0 & 0x1) == 0) ? terminationPromise$lzycompute() : this.terminationPromise;
/*     */   }
/*     */   
/*     */   public Future<BoxedUnit> terminationFuture() {
/*     */     return terminationPromise().future();
/*     */   }
/*     */   
/*     */   public Map<String, InternalActorRef> akka$actor$LocalActorRefProvider$$extraNames() {
/* 527 */     return this.akka$actor$LocalActorRefProvider$$extraNames;
/*     */   }
/*     */   
/*     */   private void akka$actor$LocalActorRefProvider$$extraNames_$eq(Map<String, InternalActorRef> x$1) {
/* 527 */     this.akka$actor$LocalActorRefProvider$$extraNames = x$1;
/*     */   }
/*     */   
/*     */   public void registerExtraNames(Map _extras) {
/* 535 */     akka$actor$LocalActorRefProvider$$extraNames_$eq(akka$actor$LocalActorRefProvider$$extraNames().$plus$plus((GenTraversableOnce)_extras));
/*     */   }
/*     */   
/*     */   private SupervisorStrategyConfigurator guardianSupervisorStrategyConfigurator() {
/* 538 */     return (SupervisorStrategyConfigurator)dynamicAccess().<T>createInstanceFor(settings().SupervisorStrategyClass(), (Seq<Tuple2<Class<?>, Object>>)Collections$EmptyImmutableSeq$.MODULE$, ClassTag$.MODULE$.apply(SupervisorStrategyConfigurator.class)).get();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy rootGuardianStrategy() {
/* 543 */     return new OneForOneStrategy(OneForOneStrategy$.MODULE$.apply$default$1(), OneForOneStrategy$.MODULE$.apply$default$2(), OneForOneStrategy$.MODULE$.apply$default$3(), (PartialFunction<Throwable, SupervisorStrategy.Directive>)new LocalActorRefProvider$$anonfun$rootGuardianStrategy$1(this));
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$rootGuardianStrategy$1 extends AbstractPartialFunction<Throwable, SupervisorStrategy.Directive> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x4, Function1 default) {
/* 543 */       Throwable throwable = x4;
/* 545 */       this.$outer.log().error(throwable, "guardian failed, shutting down system");
/* 546 */       return (B1)SupervisorStrategy.Stop$.MODULE$;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x4) {
/*     */       Throwable throwable = x4;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$rootGuardianStrategy$1(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public SupervisorStrategy guardianStrategy() {
/* 552 */     return guardianSupervisorStrategyConfigurator().create();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy systemGuardianStrategy() {
/* 557 */     return SupervisorStrategy$.MODULE$.defaultStrategy();
/*     */   }
/*     */   
/*     */   private MessageDispatcher akka$actor$LocalActorRefProvider$$defaultDispatcher$lzycompute() {
/* 559 */     synchronized (this) {
/* 559 */       if ((byte)(this.bitmap$0 & 0x2) == 0) {
/* 559 */         this.akka$actor$LocalActorRefProvider$$defaultDispatcher = akka$actor$LocalActorRefProvider$$system().dispatchers().defaultGlobalDispatcher();
/* 559 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */       } 
/* 559 */       return this.akka$actor$LocalActorRefProvider$$defaultDispatcher;
/*     */     } 
/*     */   }
/*     */   
/*     */   public MessageDispatcher akka$actor$LocalActorRefProvider$$defaultDispatcher() {
/* 559 */     return ((byte)(this.bitmap$0 & 0x2) == 0) ? akka$actor$LocalActorRefProvider$$defaultDispatcher$lzycompute() : this.akka$actor$LocalActorRefProvider$$defaultDispatcher;
/*     */   }
/*     */   
/*     */   private MailboxType akka$actor$LocalActorRefProvider$$defaultMailbox$lzycompute() {
/* 561 */     synchronized (this) {
/* 561 */       if ((byte)(this.bitmap$0 & 0x4) == 0) {
/* 561 */         this.akka$actor$LocalActorRefProvider$$defaultMailbox = akka$actor$LocalActorRefProvider$$system().mailboxes().lookup("akka.actor.default-mailbox");
/* 561 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x4);
/*     */       } 
/* 561 */       return this.akka$actor$LocalActorRefProvider$$defaultMailbox;
/*     */     } 
/*     */   }
/*     */   
/*     */   public MailboxType akka$actor$LocalActorRefProvider$$defaultMailbox() {
/* 561 */     return ((byte)(this.bitmap$0 & 0x4) == 0) ? akka$actor$LocalActorRefProvider$$defaultMailbox$lzycompute() : this.akka$actor$LocalActorRefProvider$$defaultMailbox;
/*     */   }
/*     */   
/*     */   private LocalActorRef rootGuardian$lzycompute() {
/* 563 */     synchronized (this) {
/* 563 */       if ((byte)(this.bitmap$0 & 0x8) == 0) {
/* 563 */         this.rootGuardian = 
/* 564 */           new LocalActorRefProvider$$anon$1(this);
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x8);
/*     */       } 
/*     */       return this.rootGuardian;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LocalActorRef rootGuardian() {
/*     */     return ((byte)(this.bitmap$0 & 0x8) == 0) ? rootGuardian$lzycompute() : this.rootGuardian;
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anon$1 extends LocalActorRef {
/*     */     public LocalActorRefProvider$$anon$1(LocalActorRefProvider $outer) {
/* 564 */       super(
/* 565 */           $outer.akka$actor$LocalActorRefProvider$$system(), 
/* 566 */           Props$.MODULE$.apply(LocalActorRefProvider.Guardian.class, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { $outer.rootGuardianStrategy() })), $outer.akka$actor$LocalActorRefProvider$$defaultDispatcher(), 
/* 568 */           $outer.akka$actor$LocalActorRefProvider$$defaultMailbox(), 
/* 569 */           $outer.theOneWhoWalksTheBubblesOfSpaceTime(), 
/* 570 */           $outer.rootPath());
/*     */     }
/*     */     
/*     */     public InternalActorRef getParent() {
/* 571 */       return this;
/*     */     }
/*     */     
/*     */     public InternalActorRef getSingleChild(String name) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: ldc 'temp'
/*     */       //   4: aload_2
/*     */       //   5: astore_3
/*     */       //   6: dup
/*     */       //   7: ifnonnull -> 18
/*     */       //   10: pop
/*     */       //   11: aload_3
/*     */       //   12: ifnull -> 25
/*     */       //   15: goto -> 37
/*     */       //   18: aload_3
/*     */       //   19: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   22: ifeq -> 37
/*     */       //   25: aload_0
/*     */       //   26: getfield $outer : Lakka/actor/LocalActorRefProvider;
/*     */       //   29: invokevirtual tempContainer : ()Lakka/actor/VirtualPathContainer;
/*     */       //   32: astore #4
/*     */       //   34: goto -> 105
/*     */       //   37: ldc 'deadLetters'
/*     */       //   39: aload_2
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 75
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 75
/*     */       //   63: aload_0
/*     */       //   64: getfield $outer : Lakka/actor/LocalActorRefProvider;
/*     */       //   67: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */       //   70: astore #4
/*     */       //   72: goto -> 105
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/actor/LocalActorRefProvider;
/*     */       //   79: invokevirtual akka$actor$LocalActorRefProvider$$extraNames : ()Lscala/collection/immutable/Map;
/*     */       //   82: aload_2
/*     */       //   83: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*     */       //   88: new akka/actor/LocalActorRefProvider$$anon$1$$anonfun$getSingleChild$1
/*     */       //   91: dup
/*     */       //   92: aload_0
/*     */       //   93: aload_2
/*     */       //   94: invokespecial <init> : (Lakka/actor/LocalActorRefProvider$$anon$1;Ljava/lang/String;)V
/*     */       //   97: invokevirtual getOrElse : (Lscala/Function0;)Ljava/lang/Object;
/*     */       //   100: checkcast akka/actor/InternalActorRef
/*     */       //   103: astore #4
/*     */       //   105: aload #4
/*     */       //   107: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #572	-> 0
/*     */       //   #573	-> 2
/*     */       //   #574	-> 37
/*     */       //   #575	-> 75
/*     */       //   #572	-> 105
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	108	0	this	Lakka/actor/LocalActorRefProvider$$anon$1;
/*     */       //   0	108	1	name	Ljava/lang/String;
/*     */     }
/*     */     
/*     */     public InternalActorRef akka$actor$LocalActorRefProvider$$anon$$super$getSingleChild(String name) {
/* 575 */       return super.getSingleChild(name);
/*     */     }
/*     */     
/*     */     public class LocalActorRefProvider$$anon$1$$anonfun$getSingleChild$1 extends AbstractFunction0<InternalActorRef> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String x1$1;
/*     */       
/*     */       public final InternalActorRef apply() {
/* 575 */         return this.$outer.akka$actor$LocalActorRefProvider$$anon$$super$getSingleChild(this.x1$1);
/*     */       }
/*     */       
/*     */       public LocalActorRefProvider$$anon$1$$anonfun$getSingleChild$1(LocalActorRefProvider$$anon$1 $outer, String x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public ActorRef rootGuardianAt(Address address) {
/* 580 */     Address address1 = rootPath().address();
/* 580 */     if (address == null) {
/* 580 */       if (address1 != null);
/* 580 */     } else if (address.equals(address1)) {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private LocalActorRef guardian$lzycompute() {
/* 583 */     synchronized (this) {
/* 583 */       if ((byte)(this.bitmap$0 & 0x10) == 0) {
/* 584 */         ActorCell cell = rootGuardian().underlying();
/* 585 */         cell.reserveChild("user");
/* 586 */         LocalActorRef ref = new LocalActorRef(akka$actor$LocalActorRefProvider$$system(), Props$.MODULE$.apply(Guardian.class, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { guardianStrategy() })), akka$actor$LocalActorRefProvider$$defaultDispatcher(), akka$actor$LocalActorRefProvider$$defaultMailbox(), rootGuardian(), rootPath().$div("user"));
/* 588 */         cell.initChild(ref);
/* 589 */         ref.start();
/* 590 */         this.guardian = ref;
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x10);
/*     */       } 
/*     */       return this.guardian;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LocalActorRef guardian() {
/*     */     return ((byte)(this.bitmap$0 & 0x10) == 0) ? guardian$lzycompute() : this.guardian;
/*     */   }
/*     */   
/*     */   private LocalActorRef systemGuardian$lzycompute() {
/* 593 */     synchronized (this) {
/* 593 */       if ((byte)(this.bitmap$0 & 0x20) == 0) {
/* 594 */         ActorCell cell = rootGuardian().underlying();
/* 595 */         cell.reserveChild("system");
/* 596 */         LocalActorRef ref = new LocalActorRef(
/* 597 */             akka$actor$LocalActorRefProvider$$system(), Props$.MODULE$.apply(SystemGuardian.class, (Seq<Object>)Predef$.MODULE$.genericWrapArray(new Object[] { systemGuardianStrategy(), guardian() })), akka$actor$LocalActorRefProvider$$defaultDispatcher(), akka$actor$LocalActorRefProvider$$defaultMailbox(), rootGuardian(), rootPath().$div("system"));
/* 599 */         cell.initChild(ref);
/* 600 */         ref.start();
/* 601 */         this.systemGuardian = ref;
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x20);
/*     */       } 
/*     */       return this.systemGuardian;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LocalActorRef systemGuardian() {
/*     */     return ((byte)(this.bitmap$0 & 0x20) == 0) ? systemGuardian$lzycompute() : this.systemGuardian;
/*     */   }
/*     */   
/*     */   private VirtualPathContainer tempContainer$lzycompute() {
/* 604 */     synchronized (this) {
/* 604 */       if ((byte)(this.bitmap$0 & 0x40) == 0) {
/* 604 */         this.tempContainer = new VirtualPathContainer(akka$actor$LocalActorRefProvider$$system().provider(), tempNode(), rootGuardian(), log());
/* 604 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x40);
/*     */       } 
/* 604 */       return this.tempContainer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public VirtualPathContainer tempContainer() {
/* 604 */     return ((byte)(this.bitmap$0 & 0x40) == 0) ? tempContainer$lzycompute() : this.tempContainer;
/*     */   }
/*     */   
/*     */   public void registerTempActor(InternalActorRef actorRef, ActorPath path) {
/* 607 */     Predef$.MODULE$.assert((path.parent() == tempNode()), (Function0)new LocalActorRefProvider$$anonfun$registerTempActor$1(this));
/* 608 */     tempContainer().addChild(path.name(), actorRef);
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$registerTempActor$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "cannot registerTempActor() with anything not obtained from tempPath()";
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$registerTempActor$1(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public void unregisterTempActor(ActorPath path) {
/* 612 */     Predef$.MODULE$.assert((path.parent() == tempNode()), (Function0)new LocalActorRefProvider$$anonfun$unregisterTempActor$1(this));
/* 613 */     tempContainer().removeChild(path.name());
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$unregisterTempActor$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "cannot unregisterTempActor() with anything not obtained from tempPath()";
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$unregisterTempActor$1(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public void init(ActorSystemImpl _system) {
/* 617 */     akka$actor$LocalActorRefProvider$$system_$eq(_system);
/* 618 */     rootGuardian().start();
/* 620 */     systemGuardian().sendSystemMessage((SystemMessage)new Watch(guardian(), systemGuardian()));
/* 621 */     rootGuardian().sendSystemMessage((SystemMessage)new Watch(systemGuardian(), rootGuardian()));
/* 622 */     eventStream().startDefaultLoggers(_system);
/*     */   }
/*     */   
/*     */   public InternalActorRef actorFor(InternalActorRef ref, String path) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: astore_3
/*     */     //   2: getstatic akka/actor/RelativeActorPath$.MODULE$ : Lakka/actor/RelativeActorPath$;
/*     */     //   5: aload_3
/*     */     //   6: invokevirtual unapply : (Ljava/lang/String;)Lscala/Option;
/*     */     //   9: astore #4
/*     */     //   11: aload #4
/*     */     //   13: invokevirtual isEmpty : ()Z
/*     */     //   16: ifeq -> 139
/*     */     //   19: getstatic akka/actor/ActorPathExtractor$.MODULE$ : Lakka/actor/ActorPathExtractor$;
/*     */     //   22: aload_3
/*     */     //   23: invokevirtual unapply : (Ljava/lang/String;)Lscala/Option;
/*     */     //   26: astore #7
/*     */     //   28: aload #7
/*     */     //   30: invokevirtual isEmpty : ()Z
/*     */     //   33: ifne -> 117
/*     */     //   36: aload #7
/*     */     //   38: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   41: checkcast scala/Tuple2
/*     */     //   44: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   47: checkcast akka/actor/Address
/*     */     //   50: astore #8
/*     */     //   52: aload #7
/*     */     //   54: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   57: checkcast scala/Tuple2
/*     */     //   60: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   63: checkcast scala/collection/immutable/Iterable
/*     */     //   66: astore #9
/*     */     //   68: aload #8
/*     */     //   70: aload_0
/*     */     //   71: invokevirtual rootPath : ()Lakka/actor/ActorPath;
/*     */     //   74: invokeinterface address : ()Lakka/actor/Address;
/*     */     //   79: astore #10
/*     */     //   81: dup
/*     */     //   82: ifnonnull -> 94
/*     */     //   85: pop
/*     */     //   86: aload #10
/*     */     //   88: ifnull -> 102
/*     */     //   91: goto -> 117
/*     */     //   94: aload #10
/*     */     //   96: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   99: ifeq -> 117
/*     */     //   102: aload_0
/*     */     //   103: aload_0
/*     */     //   104: invokevirtual rootGuardian : ()Lakka/actor/LocalActorRef;
/*     */     //   107: aload #9
/*     */     //   109: invokevirtual actorFor : (Lakka/actor/InternalActorRef;Lscala/collection/Iterable;)Lakka/actor/InternalActorRef;
/*     */     //   112: astore #6
/*     */     //   114: goto -> 225
/*     */     //   117: aload_0
/*     */     //   118: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   121: ldc_w 'look-up of unknown path [{}] failed'
/*     */     //   124: aload_2
/*     */     //   125: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   130: aload_0
/*     */     //   131: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */     //   134: astore #6
/*     */     //   136: goto -> 225
/*     */     //   139: aload #4
/*     */     //   141: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   144: checkcast scala/collection/immutable/Seq
/*     */     //   147: astore #5
/*     */     //   149: aload #5
/*     */     //   151: invokeinterface isEmpty : ()Z
/*     */     //   156: ifeq -> 179
/*     */     //   159: aload_0
/*     */     //   160: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   163: ldc_w 'look-up of empty path string [{}] fails (per definition)'
/*     */     //   166: aload_2
/*     */     //   167: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   172: aload_0
/*     */     //   173: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */     //   176: goto -> 223
/*     */     //   179: aload #5
/*     */     //   181: invokeinterface head : ()Ljava/lang/Object;
/*     */     //   186: checkcast java/lang/String
/*     */     //   189: invokevirtual isEmpty : ()Z
/*     */     //   192: ifeq -> 216
/*     */     //   195: aload_0
/*     */     //   196: aload_0
/*     */     //   197: invokevirtual rootGuardian : ()Lakka/actor/LocalActorRef;
/*     */     //   200: aload #5
/*     */     //   202: invokeinterface tail : ()Ljava/lang/Object;
/*     */     //   207: checkcast scala/collection/Iterable
/*     */     //   210: invokevirtual actorFor : (Lakka/actor/InternalActorRef;Lscala/collection/Iterable;)Lakka/actor/InternalActorRef;
/*     */     //   213: goto -> 223
/*     */     //   216: aload_0
/*     */     //   217: aload_1
/*     */     //   218: aload #5
/*     */     //   220: invokevirtual actorFor : (Lakka/actor/InternalActorRef;Lscala/collection/Iterable;)Lakka/actor/InternalActorRef;
/*     */     //   223: astore #6
/*     */     //   225: aload #6
/*     */     //   227: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #626	-> 0
/*     */     //   #627	-> 2
/*     */     //   #633	-> 19
/*     */     //   #626	-> 36
/*     */     //   #633	-> 38
/*     */     //   #626	-> 52
/*     */     //   #633	-> 54
/*     */     //   #635	-> 117
/*     */     //   #636	-> 130
/*     */     //   #634	-> 134
/*     */     //   #626	-> 139
/*     */     //   #627	-> 141
/*     */     //   #628	-> 149
/*     */     //   #629	-> 159
/*     */     //   #630	-> 172
/*     */     //   #631	-> 179
/*     */     //   #632	-> 216
/*     */     //   #628	-> 223
/*     */     //   #626	-> 225
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	228	0	this	Lakka/actor/LocalActorRefProvider;
/*     */     //   0	228	1	ref	Lakka/actor/InternalActorRef;
/*     */     //   0	228	2	path	Ljava/lang/String;
/*     */     //   52	176	8	address	Lakka/actor/Address;
/*     */     //   68	160	9	elems	Lscala/collection/immutable/Iterable;
/*     */     //   149	79	5	elems	Lscala/collection/immutable/Seq;
/*     */   }
/*     */   
/*     */   public InternalActorRef actorFor(ActorPath path) {
/* 641 */     ActorPath actorPath = rootPath();
/* 641 */     if (path.root() == null) {
/* 641 */       path.root();
/* 641 */       if (actorPath != null)
/* 643 */         log().debug("look-up of foreign ActorPath [{}] failed", path); 
/*     */     } else {
/*     */       if (path.root().equals(actorPath));
/* 643 */       log().debug("look-up of foreign ActorPath [{}] failed", path);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InternalActorRef actorFor(InternalActorRef ref, Iterable path) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: invokeinterface isEmpty : ()Z
/*     */     //   6: ifeq -> 28
/*     */     //   9: aload_0
/*     */     //   10: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   13: ldc_w 'look-up of empty path sequence fails (per definition)'
/*     */     //   16: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   21: aload_0
/*     */     //   22: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */     //   25: goto -> 125
/*     */     //   28: aload_1
/*     */     //   29: aload_2
/*     */     //   30: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   35: invokevirtual getChild : (Lscala/collection/Iterator;)Lakka/actor/InternalActorRef;
/*     */     //   38: astore_3
/*     */     //   39: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   42: aload_3
/*     */     //   43: astore #4
/*     */     //   45: dup
/*     */     //   46: ifnonnull -> 58
/*     */     //   49: pop
/*     */     //   50: aload #4
/*     */     //   52: ifnull -> 66
/*     */     //   55: goto -> 120
/*     */     //   58: aload #4
/*     */     //   60: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   63: ifeq -> 120
/*     */     //   66: aload_0
/*     */     //   67: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   70: ldc_w 'look-up of path sequence [/{}] failed'
/*     */     //   73: aload_2
/*     */     //   74: ldc_w '/'
/*     */     //   77: invokeinterface mkString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   82: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   87: new akka/actor/EmptyLocalActorRef
/*     */     //   90: dup
/*     */     //   91: aload_0
/*     */     //   92: invokevirtual akka$actor$LocalActorRefProvider$$system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   95: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   98: aload_1
/*     */     //   99: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   102: aload_2
/*     */     //   103: invokeinterface $div : (Lscala/collection/Iterable;)Lakka/actor/ActorPath;
/*     */     //   108: aload_0
/*     */     //   109: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */     //   112: invokespecial <init> : (Lakka/actor/ActorRefProvider;Lakka/actor/ActorPath;Lakka/event/EventStream;)V
/*     */     //   115: astore #5
/*     */     //   117: goto -> 123
/*     */     //   120: aload_3
/*     */     //   121: astore #5
/*     */     //   123: aload #5
/*     */     //   125: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #649	-> 0
/*     */     //   #650	-> 9
/*     */     //   #651	-> 21
/*     */     //   #652	-> 28
/*     */     //   #653	-> 39
/*     */     //   #654	-> 66
/*     */     //   #655	-> 87
/*     */     //   #653	-> 115
/*     */     //   #656	-> 120
/*     */     //   #652	-> 123
/*     */     //   #649	-> 125
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	126	0	this	Lakka/actor/LocalActorRefProvider;
/*     */     //   0	126	1	ref	Lakka/actor/InternalActorRef;
/*     */     //   0	126	2	path	Lscala/collection/Iterable;
/*     */   }
/*     */   
/*     */   public ActorRef resolveActorRef(String path) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: getstatic akka/actor/ActorPathExtractor$.MODULE$ : Lakka/actor/ActorPathExtractor$;
/*     */     //   5: aload_2
/*     */     //   6: invokevirtual unapply : (Ljava/lang/String;)Lscala/Option;
/*     */     //   9: astore_3
/*     */     //   10: aload_3
/*     */     //   11: invokevirtual isEmpty : ()Z
/*     */     //   14: ifne -> 96
/*     */     //   17: aload_3
/*     */     //   18: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   21: checkcast scala/Tuple2
/*     */     //   24: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   27: checkcast akka/actor/Address
/*     */     //   30: astore #4
/*     */     //   32: aload_3
/*     */     //   33: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   36: checkcast scala/Tuple2
/*     */     //   39: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   42: checkcast scala/collection/immutable/Iterable
/*     */     //   45: astore #5
/*     */     //   47: aload #4
/*     */     //   49: aload_0
/*     */     //   50: invokevirtual rootPath : ()Lakka/actor/ActorPath;
/*     */     //   53: invokeinterface address : ()Lakka/actor/Address;
/*     */     //   58: astore #6
/*     */     //   60: dup
/*     */     //   61: ifnonnull -> 73
/*     */     //   64: pop
/*     */     //   65: aload #6
/*     */     //   67: ifnull -> 81
/*     */     //   70: goto -> 96
/*     */     //   73: aload #6
/*     */     //   75: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   78: ifeq -> 96
/*     */     //   81: aload_0
/*     */     //   82: aload_0
/*     */     //   83: invokevirtual rootGuardian : ()Lakka/actor/LocalActorRef;
/*     */     //   86: aload #5
/*     */     //   88: invokevirtual resolveActorRef : (Lakka/actor/InternalActorRef;Lscala/collection/Iterable;)Lakka/actor/InternalActorRef;
/*     */     //   91: astore #7
/*     */     //   93: goto -> 115
/*     */     //   96: aload_0
/*     */     //   97: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   100: ldc_w 'resolve of unknown path [{}] failed'
/*     */     //   103: aload_1
/*     */     //   104: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   109: aload_0
/*     */     //   110: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */     //   113: astore #7
/*     */     //   115: aload #7
/*     */     //   117: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #659	-> 0
/*     */     //   #660	-> 2
/*     */     //   #659	-> 17
/*     */     //   #660	-> 18
/*     */     //   #659	-> 32
/*     */     //   #660	-> 33
/*     */     //   #662	-> 96
/*     */     //   #663	-> 109
/*     */     //   #661	-> 113
/*     */     //   #659	-> 115
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lakka/actor/LocalActorRefProvider;
/*     */     //   0	118	1	path	Ljava/lang/String;
/*     */     //   32	86	4	address	Lakka/actor/Address;
/*     */     //   47	71	5	elems	Lscala/collection/immutable/Iterable;
/*     */   }
/*     */   
/*     */   public ActorRef resolveActorRef(ActorPath path) {
/* 667 */     ActorPath actorPath = rootPath();
/* 667 */     if (path.root() == null) {
/* 667 */       path.root();
/* 667 */       if (actorPath != null)
/* 669 */         log().debug("resolve of foreign ActorPath [{}] failed", path); 
/*     */     } else {
/*     */       if (path.root().equals(actorPath));
/* 669 */       log().debug("resolve of foreign ActorPath [{}] failed", path);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InternalActorRef resolveActorRef(InternalActorRef ref, Iterable pathElements) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: invokeinterface isEmpty : ()Z
/*     */     //   6: ifeq -> 28
/*     */     //   9: aload_0
/*     */     //   10: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   13: ldc_w 'resolve of empty path sequence fails (per definition)'
/*     */     //   16: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   21: aload_0
/*     */     //   22: invokevirtual deadLetters : ()Lakka/actor/InternalActorRef;
/*     */     //   25: goto -> 125
/*     */     //   28: aload_1
/*     */     //   29: aload_2
/*     */     //   30: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   35: invokevirtual getChild : (Lscala/collection/Iterator;)Lakka/actor/InternalActorRef;
/*     */     //   38: astore_3
/*     */     //   39: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   42: aload_3
/*     */     //   43: astore #4
/*     */     //   45: dup
/*     */     //   46: ifnonnull -> 58
/*     */     //   49: pop
/*     */     //   50: aload #4
/*     */     //   52: ifnull -> 66
/*     */     //   55: goto -> 120
/*     */     //   58: aload #4
/*     */     //   60: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   63: ifeq -> 120
/*     */     //   66: aload_0
/*     */     //   67: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   70: ldc_w 'resolve of path sequence [/{}] failed'
/*     */     //   73: aload_2
/*     */     //   74: ldc_w '/'
/*     */     //   77: invokeinterface mkString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   82: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   87: new akka/actor/EmptyLocalActorRef
/*     */     //   90: dup
/*     */     //   91: aload_0
/*     */     //   92: invokevirtual akka$actor$LocalActorRefProvider$$system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   95: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   98: aload_1
/*     */     //   99: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   102: aload_2
/*     */     //   103: invokeinterface $div : (Lscala/collection/Iterable;)Lakka/actor/ActorPath;
/*     */     //   108: aload_0
/*     */     //   109: invokevirtual eventStream : ()Lakka/event/EventStream;
/*     */     //   112: invokespecial <init> : (Lakka/actor/ActorRefProvider;Lakka/actor/ActorPath;Lakka/event/EventStream;)V
/*     */     //   115: astore #5
/*     */     //   117: goto -> 123
/*     */     //   120: aload_3
/*     */     //   121: astore #5
/*     */     //   123: aload #5
/*     */     //   125: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #678	-> 0
/*     */     //   #679	-> 9
/*     */     //   #680	-> 21
/*     */     //   #681	-> 28
/*     */     //   #682	-> 39
/*     */     //   #683	-> 66
/*     */     //   #684	-> 87
/*     */     //   #682	-> 115
/*     */     //   #685	-> 120
/*     */     //   #681	-> 123
/*     */     //   #678	-> 125
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	126	0	this	Lakka/actor/LocalActorRefProvider;
/*     */     //   0	126	1	ref	Lakka/actor/InternalActorRef;
/*     */     //   0	126	2	pathElements	Lscala/collection/Iterable;
/*     */   }
/*     */   
/*     */   public InternalActorRef actorOf(ActorSystemImpl system, Props props, InternalActorRef supervisor, ActorPath path, boolean systemService, Option deploy, boolean lookupDeploy, boolean async) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   4: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   7: astore #9
/*     */     //   9: getstatic akka/routing/NoRouter$.MODULE$ : Lakka/routing/NoRouter$;
/*     */     //   12: aload #9
/*     */     //   14: astore #10
/*     */     //   16: dup
/*     */     //   17: ifnonnull -> 29
/*     */     //   20: pop
/*     */     //   21: aload #10
/*     */     //   23: ifnull -> 37
/*     */     //   26: goto -> 583
/*     */     //   29: aload #10
/*     */     //   31: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   34: ifeq -> 583
/*     */     //   37: aload_0
/*     */     //   38: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   41: invokevirtual DebugRouterMisconfiguration : ()Z
/*     */     //   44: ifeq -> 69
/*     */     //   47: aload_0
/*     */     //   48: invokevirtual deployer : ()Lakka/actor/Deployer;
/*     */     //   51: aload #4
/*     */     //   53: invokevirtual lookup : (Lakka/actor/ActorPath;)Lscala/Option;
/*     */     //   56: new akka/actor/LocalActorRefProvider$$anonfun$actorOf$1
/*     */     //   59: dup
/*     */     //   60: aload_0
/*     */     //   61: aload #4
/*     */     //   63: invokespecial <init> : (Lakka/actor/LocalActorRefProvider;Lakka/actor/ActorPath;)V
/*     */     //   66: invokevirtual foreach : (Lscala/Function1;)V
/*     */     //   69: iload #7
/*     */     //   71: ifeq -> 86
/*     */     //   74: aload_0
/*     */     //   75: invokevirtual deployer : ()Lakka/actor/Deployer;
/*     */     //   78: aload #4
/*     */     //   80: invokevirtual lookup : (Lakka/actor/ActorPath;)Lscala/Option;
/*     */     //   83: goto -> 88
/*     */     //   86: aload #6
/*     */     //   88: astore #13
/*     */     //   90: aload #13
/*     */     //   92: instanceof scala/Some
/*     */     //   95: ifeq -> 404
/*     */     //   98: aload #13
/*     */     //   100: checkcast scala/Some
/*     */     //   103: astore #14
/*     */     //   105: aload #14
/*     */     //   107: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   110: checkcast akka/actor/Deploy
/*     */     //   113: astore #15
/*     */     //   115: new scala/Tuple2
/*     */     //   118: dup
/*     */     //   119: aload #15
/*     */     //   121: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   124: aload #15
/*     */     //   126: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   129: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   132: astore #17
/*     */     //   134: aload #17
/*     */     //   136: ifnull -> 221
/*     */     //   139: aload #17
/*     */     //   141: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   144: checkcast java/lang/String
/*     */     //   147: astore #18
/*     */     //   149: aload #17
/*     */     //   151: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   154: checkcast java/lang/String
/*     */     //   157: astore #19
/*     */     //   159: ldc_w ''
/*     */     //   162: aload #18
/*     */     //   164: astore #20
/*     */     //   166: dup
/*     */     //   167: ifnonnull -> 179
/*     */     //   170: pop
/*     */     //   171: aload #20
/*     */     //   173: ifnull -> 187
/*     */     //   176: goto -> 221
/*     */     //   179: aload #20
/*     */     //   181: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   184: ifeq -> 221
/*     */     //   187: ldc_w ''
/*     */     //   190: aload #19
/*     */     //   192: astore #21
/*     */     //   194: dup
/*     */     //   195: ifnonnull -> 207
/*     */     //   198: pop
/*     */     //   199: aload #21
/*     */     //   201: ifnull -> 215
/*     */     //   204: goto -> 221
/*     */     //   207: aload #21
/*     */     //   209: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   212: ifeq -> 221
/*     */     //   215: aload_2
/*     */     //   216: astore #22
/*     */     //   218: goto -> 387
/*     */     //   221: aload #17
/*     */     //   223: ifnull -> 285
/*     */     //   226: aload #17
/*     */     //   228: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   231: checkcast java/lang/String
/*     */     //   234: astore #23
/*     */     //   236: aload #17
/*     */     //   238: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   241: checkcast java/lang/String
/*     */     //   244: astore #24
/*     */     //   246: ldc_w ''
/*     */     //   249: aload #24
/*     */     //   251: astore #25
/*     */     //   253: dup
/*     */     //   254: ifnonnull -> 266
/*     */     //   257: pop
/*     */     //   258: aload #25
/*     */     //   260: ifnull -> 274
/*     */     //   263: goto -> 285
/*     */     //   266: aload #25
/*     */     //   268: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   271: ifeq -> 285
/*     */     //   274: aload_2
/*     */     //   275: aload #23
/*     */     //   277: invokevirtual withDispatcher : (Ljava/lang/String;)Lakka/actor/Props;
/*     */     //   280: astore #22
/*     */     //   282: goto -> 387
/*     */     //   285: aload #17
/*     */     //   287: ifnull -> 349
/*     */     //   290: aload #17
/*     */     //   292: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   295: checkcast java/lang/String
/*     */     //   298: astore #26
/*     */     //   300: aload #17
/*     */     //   302: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   305: checkcast java/lang/String
/*     */     //   308: astore #27
/*     */     //   310: ldc_w ''
/*     */     //   313: aload #26
/*     */     //   315: astore #28
/*     */     //   317: dup
/*     */     //   318: ifnonnull -> 330
/*     */     //   321: pop
/*     */     //   322: aload #28
/*     */     //   324: ifnull -> 338
/*     */     //   327: goto -> 349
/*     */     //   330: aload #28
/*     */     //   332: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   335: ifeq -> 349
/*     */     //   338: aload_2
/*     */     //   339: aload #27
/*     */     //   341: invokevirtual withMailbox : (Ljava/lang/String;)Lakka/actor/Props;
/*     */     //   344: astore #22
/*     */     //   346: goto -> 387
/*     */     //   349: aload #17
/*     */     //   351: ifnull -> 394
/*     */     //   354: aload #17
/*     */     //   356: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   359: checkcast java/lang/String
/*     */     //   362: astore #29
/*     */     //   364: aload #17
/*     */     //   366: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   369: checkcast java/lang/String
/*     */     //   372: astore #30
/*     */     //   374: aload_2
/*     */     //   375: aload #29
/*     */     //   377: invokevirtual withDispatcher : (Ljava/lang/String;)Lakka/actor/Props;
/*     */     //   380: aload #30
/*     */     //   382: invokevirtual withMailbox : (Ljava/lang/String;)Lakka/actor/Props;
/*     */     //   385: astore #22
/*     */     //   387: aload #22
/*     */     //   389: astore #16
/*     */     //   391: goto -> 407
/*     */     //   394: new scala/MatchError
/*     */     //   397: dup
/*     */     //   398: aload #17
/*     */     //   400: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   403: athrow
/*     */     //   404: aload_2
/*     */     //   405: astore #16
/*     */     //   407: aload #16
/*     */     //   409: astore #12
/*     */     //   411: aload_1
/*     */     //   412: invokevirtual dispatchers : ()Lakka/dispatch/Dispatchers;
/*     */     //   415: aload #12
/*     */     //   417: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   420: invokevirtual hasDispatcher : (Ljava/lang/String;)Z
/*     */     //   423: ifeq -> 511
/*     */     //   426: aload_1
/*     */     //   427: invokevirtual dispatchers : ()Lakka/dispatch/Dispatchers;
/*     */     //   430: aload #12
/*     */     //   432: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   435: invokevirtual lookup : (Ljava/lang/String;)Lakka/dispatch/MessageDispatcher;
/*     */     //   438: astore #36
/*     */     //   440: aload_1
/*     */     //   441: invokevirtual mailboxes : ()Lakka/dispatch/Mailboxes;
/*     */     //   444: aload #12
/*     */     //   446: aload #36
/*     */     //   448: invokevirtual configurator : ()Lakka/dispatch/MessageDispatcherConfigurator;
/*     */     //   451: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   454: invokevirtual getMailboxType : (Lakka/actor/Props;Lcom/typesafe/config/Config;)Lakka/dispatch/MailboxType;
/*     */     //   457: astore #37
/*     */     //   459: iload #8
/*     */     //   461: ifeq -> 489
/*     */     //   464: new akka/actor/RepointableActorRef
/*     */     //   467: dup
/*     */     //   468: aload_1
/*     */     //   469: aload #12
/*     */     //   471: aload #36
/*     */     //   473: aload #37
/*     */     //   475: aload_3
/*     */     //   476: aload #4
/*     */     //   478: invokespecial <init> : (Lakka/actor/ActorSystemImpl;Lakka/actor/Props;Lakka/dispatch/MessageDispatcher;Lakka/dispatch/MailboxType;Lakka/actor/InternalActorRef;Lakka/actor/ActorPath;)V
/*     */     //   481: iload #8
/*     */     //   483: invokevirtual initialize : (Z)Lakka/actor/RepointableActorRef;
/*     */     //   486: goto -> 506
/*     */     //   489: new akka/actor/LocalActorRef
/*     */     //   492: dup
/*     */     //   493: aload_1
/*     */     //   494: aload #12
/*     */     //   496: aload #36
/*     */     //   498: aload #37
/*     */     //   500: aload_3
/*     */     //   501: aload #4
/*     */     //   503: invokespecial <init> : (Lakka/actor/ActorSystemImpl;Lakka/actor/Props;Lakka/dispatch/MessageDispatcher;Lakka/dispatch/MailboxType;Lakka/actor/InternalActorRef;Lakka/actor/ActorPath;)V
/*     */     //   506: astore #11
/*     */     //   508: goto -> 1042
/*     */     //   511: new akka/ConfigurationException
/*     */     //   514: dup
/*     */     //   515: new scala/StringContext
/*     */     //   518: dup
/*     */     //   519: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   522: iconst_3
/*     */     //   523: anewarray java/lang/String
/*     */     //   526: dup
/*     */     //   527: iconst_0
/*     */     //   528: ldc_w 'Dispatcher ['
/*     */     //   531: aastore
/*     */     //   532: dup
/*     */     //   533: iconst_1
/*     */     //   534: ldc_w '] not configured for path '
/*     */     //   537: aastore
/*     */     //   538: dup
/*     */     //   539: iconst_2
/*     */     //   540: ldc_w ''
/*     */     //   543: aastore
/*     */     //   544: checkcast [Ljava/lang/Object;
/*     */     //   547: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   550: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   553: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   556: iconst_2
/*     */     //   557: anewarray java/lang/Object
/*     */     //   560: dup
/*     */     //   561: iconst_0
/*     */     //   562: aload #12
/*     */     //   564: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   567: aastore
/*     */     //   568: dup
/*     */     //   569: iconst_1
/*     */     //   570: aload #4
/*     */     //   572: aastore
/*     */     //   573: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   576: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   579: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   582: athrow
/*     */     //   583: iload #7
/*     */     //   585: ifeq -> 600
/*     */     //   588: aload_0
/*     */     //   589: invokevirtual deployer : ()Lakka/actor/Deployer;
/*     */     //   592: aload #4
/*     */     //   594: invokevirtual lookup : (Lakka/actor/ActorPath;)Lscala/Option;
/*     */     //   597: goto -> 603
/*     */     //   600: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   603: astore #38
/*     */     //   605: getstatic scala/package$.MODULE$ : Lscala/package$;
/*     */     //   608: invokevirtual Iterator : ()Lscala/collection/Iterator$;
/*     */     //   611: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   614: iconst_1
/*     */     //   615: anewarray akka/actor/Deploy
/*     */     //   618: dup
/*     */     //   619: iconst_0
/*     */     //   620: aload_2
/*     */     //   621: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   624: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   627: aload #9
/*     */     //   629: invokeinterface withFallback : (Lakka/routing/RouterConfig;)Lakka/routing/RouterConfig;
/*     */     //   634: astore #40
/*     */     //   636: aload_2
/*     */     //   637: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   640: invokevirtual copy$default$1 : ()Ljava/lang/String;
/*     */     //   643: astore #41
/*     */     //   645: aload_2
/*     */     //   646: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   649: invokevirtual copy$default$2 : ()Lcom/typesafe/config/Config;
/*     */     //   652: astore #42
/*     */     //   654: aload_2
/*     */     //   655: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   658: invokevirtual copy$default$4 : ()Lakka/actor/Scope;
/*     */     //   661: astore #43
/*     */     //   663: aload_2
/*     */     //   664: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   667: invokevirtual copy$default$5 : ()Ljava/lang/String;
/*     */     //   670: astore #44
/*     */     //   672: aload_2
/*     */     //   673: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   676: invokevirtual copy$default$6 : ()Ljava/lang/String;
/*     */     //   679: astore #45
/*     */     //   681: aload_2
/*     */     //   682: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   685: aload #41
/*     */     //   687: aload #42
/*     */     //   689: aload #40
/*     */     //   691: aload #43
/*     */     //   693: aload #44
/*     */     //   695: aload #45
/*     */     //   697: invokevirtual copy : (Ljava/lang/String;Lcom/typesafe/config/Config;Lakka/routing/RouterConfig;Lakka/actor/Scope;Ljava/lang/String;Ljava/lang/String;)Lakka/actor/Deploy;
/*     */     //   700: aastore
/*     */     //   701: checkcast [Ljava/lang/Object;
/*     */     //   704: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   707: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/Iterator;
/*     */     //   710: astore #39
/*     */     //   712: aload #39
/*     */     //   714: new akka/actor/LocalActorRefProvider$$anonfun$2
/*     */     //   717: dup
/*     */     //   718: aload_0
/*     */     //   719: aload #6
/*     */     //   721: invokespecial <init> : (Lakka/actor/LocalActorRefProvider;Lscala/Option;)V
/*     */     //   724: invokeinterface $plus$plus : (Lscala/Function0;)Lscala/collection/Iterator;
/*     */     //   729: new akka/actor/LocalActorRefProvider$$anonfun$3
/*     */     //   732: dup
/*     */     //   733: aload_0
/*     */     //   734: aload #38
/*     */     //   736: invokespecial <init> : (Lakka/actor/LocalActorRefProvider;Lscala/Option;)V
/*     */     //   739: invokeinterface $plus$plus : (Lscala/Function0;)Lscala/collection/Iterator;
/*     */     //   744: new akka/actor/LocalActorRefProvider$$anonfun$4
/*     */     //   747: dup
/*     */     //   748: aload_0
/*     */     //   749: invokespecial <init> : (Lakka/actor/LocalActorRefProvider;)V
/*     */     //   752: invokeinterface reduce : (Lscala/Function2;)Ljava/lang/Object;
/*     */     //   757: checkcast akka/actor/Deploy
/*     */     //   760: astore #46
/*     */     //   762: aload_2
/*     */     //   763: aload #46
/*     */     //   765: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   768: invokevirtual withRouter : (Lakka/routing/RouterConfig;)Lakka/actor/Props;
/*     */     //   771: astore #47
/*     */     //   773: aload_1
/*     */     //   774: invokevirtual dispatchers : ()Lakka/dispatch/Dispatchers;
/*     */     //   777: aload #47
/*     */     //   779: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   782: invokevirtual hasDispatcher : (Ljava/lang/String;)Z
/*     */     //   785: ifeq -> 1117
/*     */     //   788: aload_1
/*     */     //   789: invokevirtual dispatchers : ()Lakka/dispatch/Dispatchers;
/*     */     //   792: aload #46
/*     */     //   794: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   797: invokeinterface routerDispatcher : ()Ljava/lang/String;
/*     */     //   802: invokevirtual hasDispatcher : (Ljava/lang/String;)Z
/*     */     //   805: ifeq -> 1045
/*     */     //   808: new akka/actor/Props
/*     */     //   811: dup
/*     */     //   812: aload #47
/*     */     //   814: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   817: invokeinterface routerDispatcher : ()Ljava/lang/String;
/*     */     //   822: astore #49
/*     */     //   824: aload #47
/*     */     //   826: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   829: invokevirtual copy$default$1 : ()Ljava/lang/String;
/*     */     //   832: astore #50
/*     */     //   834: aload #47
/*     */     //   836: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   839: invokevirtual copy$default$2 : ()Lcom/typesafe/config/Config;
/*     */     //   842: astore #51
/*     */     //   844: aload #47
/*     */     //   846: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   849: invokevirtual copy$default$3 : ()Lakka/routing/RouterConfig;
/*     */     //   852: astore #52
/*     */     //   854: aload #47
/*     */     //   856: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   859: invokevirtual copy$default$4 : ()Lakka/actor/Scope;
/*     */     //   862: astore #53
/*     */     //   864: aload #47
/*     */     //   866: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   869: invokevirtual copy$default$6 : ()Ljava/lang/String;
/*     */     //   872: astore #54
/*     */     //   874: aload #47
/*     */     //   876: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   879: aload #50
/*     */     //   881: aload #51
/*     */     //   883: aload #52
/*     */     //   885: aload #53
/*     */     //   887: aload #49
/*     */     //   889: aload #54
/*     */     //   891: invokevirtual copy : (Ljava/lang/String;Lcom/typesafe/config/Config;Lakka/routing/RouterConfig;Lakka/actor/Scope;Ljava/lang/String;Ljava/lang/String;)Lakka/actor/Deploy;
/*     */     //   894: ldc_w akka/routing/RoutedActorCell$RouterActorCreator
/*     */     //   897: getstatic scala/package$.MODULE$ : Lscala/package$;
/*     */     //   900: invokevirtual Vector : ()Lscala/collection/immutable/Vector$;
/*     */     //   903: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   906: iconst_1
/*     */     //   907: anewarray akka/routing/RouterConfig
/*     */     //   910: dup
/*     */     //   911: iconst_0
/*     */     //   912: aload #47
/*     */     //   914: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   917: aastore
/*     */     //   918: checkcast [Ljava/lang/Object;
/*     */     //   921: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   924: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/GenTraversable;
/*     */     //   927: checkcast scala/collection/immutable/Seq
/*     */     //   930: invokespecial <init> : (Lakka/actor/Deploy;Ljava/lang/Class;Lscala/collection/immutable/Seq;)V
/*     */     //   933: astore #48
/*     */     //   935: aload #47
/*     */     //   937: getstatic akka/routing/NoRouter$.MODULE$ : Lakka/routing/NoRouter$;
/*     */     //   940: invokevirtual withRouter : (Lakka/routing/RouterConfig;)Lakka/actor/Props;
/*     */     //   943: astore #55
/*     */     //   945: aload_1
/*     */     //   946: invokevirtual dispatchers : ()Lakka/dispatch/Dispatchers;
/*     */     //   949: aload #47
/*     */     //   951: invokevirtual routerConfig : ()Lakka/routing/RouterConfig;
/*     */     //   954: invokeinterface routerDispatcher : ()Ljava/lang/String;
/*     */     //   959: invokevirtual lookup : (Ljava/lang/String;)Lakka/dispatch/MessageDispatcher;
/*     */     //   962: astore #61
/*     */     //   964: aload_1
/*     */     //   965: invokevirtual mailboxes : ()Lakka/dispatch/Mailboxes;
/*     */     //   968: aload #48
/*     */     //   970: aload #61
/*     */     //   972: invokevirtual configurator : ()Lakka/dispatch/MessageDispatcherConfigurator;
/*     */     //   975: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   978: invokevirtual getMailboxType : (Lakka/actor/Props;Lcom/typesafe/config/Config;)Lakka/dispatch/MailboxType;
/*     */     //   981: astore #62
/*     */     //   983: aload_1
/*     */     //   984: invokevirtual dispatchers : ()Lakka/dispatch/Dispatchers;
/*     */     //   987: aload #47
/*     */     //   989: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   992: invokevirtual lookup : (Ljava/lang/String;)Lakka/dispatch/MessageDispatcher;
/*     */     //   995: astore #63
/*     */     //   997: aload_1
/*     */     //   998: invokevirtual mailboxes : ()Lakka/dispatch/Mailboxes;
/*     */     //   1001: aload #55
/*     */     //   1003: aload #63
/*     */     //   1005: invokevirtual configurator : ()Lakka/dispatch/MessageDispatcherConfigurator;
/*     */     //   1008: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */     //   1011: invokevirtual getMailboxType : (Lakka/actor/Props;Lcom/typesafe/config/Config;)Lakka/dispatch/MailboxType;
/*     */     //   1014: astore #64
/*     */     //   1016: new akka/routing/RoutedActorRef
/*     */     //   1019: dup
/*     */     //   1020: aload_1
/*     */     //   1021: aload #48
/*     */     //   1023: aload #61
/*     */     //   1025: aload #62
/*     */     //   1027: aload #55
/*     */     //   1029: aload_3
/*     */     //   1030: aload #4
/*     */     //   1032: invokespecial <init> : (Lakka/actor/ActorSystemImpl;Lakka/actor/Props;Lakka/dispatch/MessageDispatcher;Lakka/dispatch/MailboxType;Lakka/actor/Props;Lakka/actor/InternalActorRef;Lakka/actor/ActorPath;)V
/*     */     //   1035: iload #8
/*     */     //   1037: invokevirtual initialize : (Z)Lakka/actor/RepointableActorRef;
/*     */     //   1040: astore #11
/*     */     //   1042: aload #11
/*     */     //   1044: areturn
/*     */     //   1045: new akka/ConfigurationException
/*     */     //   1048: dup
/*     */     //   1049: new scala/StringContext
/*     */     //   1052: dup
/*     */     //   1053: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1056: iconst_3
/*     */     //   1057: anewarray java/lang/String
/*     */     //   1060: dup
/*     */     //   1061: iconst_0
/*     */     //   1062: ldc_w 'Dispatcher ['
/*     */     //   1065: aastore
/*     */     //   1066: dup
/*     */     //   1067: iconst_1
/*     */     //   1068: ldc_w '] not configured for router of '
/*     */     //   1071: aastore
/*     */     //   1072: dup
/*     */     //   1073: iconst_2
/*     */     //   1074: ldc_w ''
/*     */     //   1077: aastore
/*     */     //   1078: checkcast [Ljava/lang/Object;
/*     */     //   1081: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1084: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   1087: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1090: iconst_2
/*     */     //   1091: anewarray java/lang/Object
/*     */     //   1094: dup
/*     */     //   1095: iconst_0
/*     */     //   1096: aload #47
/*     */     //   1098: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   1101: aastore
/*     */     //   1102: dup
/*     */     //   1103: iconst_1
/*     */     //   1104: aload #4
/*     */     //   1106: aastore
/*     */     //   1107: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1110: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   1113: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1116: athrow
/*     */     //   1117: new akka/ConfigurationException
/*     */     //   1120: dup
/*     */     //   1121: new scala/StringContext
/*     */     //   1124: dup
/*     */     //   1125: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1128: iconst_3
/*     */     //   1129: anewarray java/lang/String
/*     */     //   1132: dup
/*     */     //   1133: iconst_0
/*     */     //   1134: ldc_w 'Dispatcher ['
/*     */     //   1137: aastore
/*     */     //   1138: dup
/*     */     //   1139: iconst_1
/*     */     //   1140: ldc_w '] not configured for routees of '
/*     */     //   1143: aastore
/*     */     //   1144: dup
/*     */     //   1145: iconst_2
/*     */     //   1146: ldc_w ''
/*     */     //   1149: aastore
/*     */     //   1150: checkcast [Ljava/lang/Object;
/*     */     //   1153: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1156: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   1159: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1162: iconst_2
/*     */     //   1163: anewarray java/lang/Object
/*     */     //   1166: dup
/*     */     //   1167: iconst_0
/*     */     //   1168: aload #47
/*     */     //   1170: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   1173: aastore
/*     */     //   1174: dup
/*     */     //   1175: iconst_1
/*     */     //   1176: aload #4
/*     */     //   1178: aastore
/*     */     //   1179: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1182: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   1185: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1188: athrow
/*     */     //   1189: astore #31
/*     */     //   1191: aload #31
/*     */     //   1193: astore #32
/*     */     //   1195: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   1198: aload #32
/*     */     //   1200: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   1203: astore #33
/*     */     //   1205: aload #33
/*     */     //   1207: invokevirtual isEmpty : ()Z
/*     */     //   1210: ifeq -> 1216
/*     */     //   1213: aload #31
/*     */     //   1215: athrow
/*     */     //   1216: aload #33
/*     */     //   1218: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   1221: checkcast java/lang/Throwable
/*     */     //   1224: astore #34
/*     */     //   1226: new akka/ConfigurationException
/*     */     //   1229: dup
/*     */     //   1230: new scala/StringContext
/*     */     //   1233: dup
/*     */     //   1234: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1237: iconst_4
/*     */     //   1238: anewarray java/lang/String
/*     */     //   1241: dup
/*     */     //   1242: iconst_0
/*     */     //   1243: ldc_w 'configuration problem while creating ['
/*     */     //   1246: aastore
/*     */     //   1247: dup
/*     */     //   1248: iconst_1
/*     */     //   1249: ldc_w '] with dispatcher ['
/*     */     //   1252: aastore
/*     */     //   1253: dup
/*     */     //   1254: iconst_2
/*     */     //   1255: ldc_w '] and mailbox ['
/*     */     //   1258: aastore
/*     */     //   1259: dup
/*     */     //   1260: iconst_3
/*     */     //   1261: ldc_w ']'
/*     */     //   1264: aastore
/*     */     //   1265: checkcast [Ljava/lang/Object;
/*     */     //   1268: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1271: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   1274: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1277: iconst_3
/*     */     //   1278: anewarray java/lang/Object
/*     */     //   1281: dup
/*     */     //   1282: iconst_0
/*     */     //   1283: aload #4
/*     */     //   1285: aastore
/*     */     //   1286: dup
/*     */     //   1287: iconst_1
/*     */     //   1288: aload #12
/*     */     //   1290: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   1293: aastore
/*     */     //   1294: dup
/*     */     //   1295: iconst_2
/*     */     //   1296: aload #12
/*     */     //   1298: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   1301: aastore
/*     */     //   1302: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1305: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   1308: aload #34
/*     */     //   1310: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   1313: athrow
/*     */     //   1314: astore #56
/*     */     //   1316: aload #56
/*     */     //   1318: astore #57
/*     */     //   1320: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   1323: aload #57
/*     */     //   1325: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   1328: astore #58
/*     */     //   1330: aload #58
/*     */     //   1332: invokevirtual isEmpty : ()Z
/*     */     //   1335: ifeq -> 1341
/*     */     //   1338: aload #56
/*     */     //   1340: athrow
/*     */     //   1341: aload #58
/*     */     //   1343: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   1346: checkcast java/lang/Throwable
/*     */     //   1349: astore #59
/*     */     //   1351: new akka/ConfigurationException
/*     */     //   1354: dup
/*     */     //   1355: new scala/collection/mutable/StringBuilder
/*     */     //   1358: dup
/*     */     //   1359: invokespecial <init> : ()V
/*     */     //   1362: new scala/StringContext
/*     */     //   1365: dup
/*     */     //   1366: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1369: iconst_4
/*     */     //   1370: anewarray java/lang/String
/*     */     //   1373: dup
/*     */     //   1374: iconst_0
/*     */     //   1375: ldc_w 'configuration problem while creating ['
/*     */     //   1378: aastore
/*     */     //   1379: dup
/*     */     //   1380: iconst_1
/*     */     //   1381: ldc_w '] with router dispatcher ['
/*     */     //   1384: aastore
/*     */     //   1385: dup
/*     */     //   1386: iconst_2
/*     */     //   1387: ldc_w '] and mailbox ['
/*     */     //   1390: aastore
/*     */     //   1391: dup
/*     */     //   1392: iconst_3
/*     */     //   1393: ldc_w '] '
/*     */     //   1396: aastore
/*     */     //   1397: checkcast [Ljava/lang/Object;
/*     */     //   1400: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1403: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   1406: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1409: iconst_3
/*     */     //   1410: anewarray java/lang/Object
/*     */     //   1413: dup
/*     */     //   1414: iconst_0
/*     */     //   1415: aload #4
/*     */     //   1417: aastore
/*     */     //   1418: dup
/*     */     //   1419: iconst_1
/*     */     //   1420: aload #48
/*     */     //   1422: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   1425: aastore
/*     */     //   1426: dup
/*     */     //   1427: iconst_2
/*     */     //   1428: aload #48
/*     */     //   1430: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   1433: aastore
/*     */     //   1434: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1437: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   1440: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   1443: new scala/StringContext
/*     */     //   1446: dup
/*     */     //   1447: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1450: iconst_3
/*     */     //   1451: anewarray java/lang/String
/*     */     //   1454: dup
/*     */     //   1455: iconst_0
/*     */     //   1456: ldc_w 'and routee dispatcher ['
/*     */     //   1459: aastore
/*     */     //   1460: dup
/*     */     //   1461: iconst_1
/*     */     //   1462: ldc_w '] and mailbox ['
/*     */     //   1465: aastore
/*     */     //   1466: dup
/*     */     //   1467: iconst_2
/*     */     //   1468: ldc_w ']'
/*     */     //   1471: aastore
/*     */     //   1472: checkcast [Ljava/lang/Object;
/*     */     //   1475: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1478: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   1481: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   1484: iconst_2
/*     */     //   1485: anewarray java/lang/Object
/*     */     //   1488: dup
/*     */     //   1489: iconst_0
/*     */     //   1490: aload #55
/*     */     //   1492: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   1495: aastore
/*     */     //   1496: dup
/*     */     //   1497: iconst_1
/*     */     //   1498: aload #55
/*     */     //   1500: invokevirtual mailbox : ()Ljava/lang/String;
/*     */     //   1503: aastore
/*     */     //   1504: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   1507: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   1510: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   1513: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1516: aload #59
/*     */     //   1518: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   1521: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #690	-> 0
/*     */     //   #691	-> 9
/*     */     //   #692	-> 37
/*     */     //   #693	-> 47
/*     */     //   #701	-> 69
/*     */     //   #702	-> 90
/*     */     //   #703	-> 115
/*     */     //   #704	-> 139
/*     */     //   #703	-> 221
/*     */     //   #705	-> 226
/*     */     //   #703	-> 285
/*     */     //   #706	-> 290
/*     */     //   #703	-> 349
/*     */     //   #707	-> 354
/*     */     //   #703	-> 387
/*     */     //   #709	-> 404
/*     */     //   #701	-> 407
/*     */     //   #699	-> 409
/*     */     //   #712	-> 411
/*     */     //   #716	-> 426
/*     */     //   #717	-> 440
/*     */     //   #719	-> 459
/*     */     //   #720	-> 489
/*     */     //   #691	-> 506
/*     */     //   #713	-> 511
/*     */     //   #727	-> 583
/*     */     //   #728	-> 605
/*     */     //   #729	-> 712
/*     */     //   #730	-> 762
/*     */     //   #732	-> 773
/*     */     //   #734	-> 788
/*     */     //   #737	-> 808
/*     */     //   #738	-> 894
/*     */     //   #737	-> 930
/*     */     //   #739	-> 935
/*     */     //   #742	-> 945
/*     */     //   #743	-> 964
/*     */     //   #747	-> 983
/*     */     //   #748	-> 997
/*     */     //   #750	-> 1016
/*     */     //   #726	-> 1040
/*     */     //   #690	-> 1042
/*     */     //   #735	-> 1045
/*     */     //   #733	-> 1117
/*     */     //   #715	-> 1189
/*     */     //   #722	-> 1195
/*     */     //   #715	-> 1213
/*     */     //   #690	-> 1216
/*     */     //   #722	-> 1218
/*     */     //   #723	-> 1230
/*     */     //   #722	-> 1310
/*     */     //   #741	-> 1314
/*     */     //   #752	-> 1320
/*     */     //   #741	-> 1338
/*     */     //   #690	-> 1341
/*     */     //   #752	-> 1343
/*     */     //   #753	-> 1355
/*     */     //   #754	-> 1443
/*     */     //   #753	-> 1513
/*     */     //   #754	-> 1516
/*     */     //   #752	-> 1518
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	1522	0	this	Lakka/actor/LocalActorRefProvider;
/*     */     //   0	1522	1	system	Lakka/actor/ActorSystemImpl;
/*     */     //   0	1522	2	props	Lakka/actor/Props;
/*     */     //   0	1522	3	supervisor	Lakka/actor/InternalActorRef;
/*     */     //   0	1522	4	path	Lakka/actor/ActorPath;
/*     */     //   0	1522	5	systemService	Z
/*     */     //   0	1522	6	deploy	Lscala/Option;
/*     */     //   0	1522	7	lookupDeploy	Z
/*     */     //   0	1522	8	async	Z
/*     */     //   115	1407	15	d	Lakka/actor/Deploy;
/*     */     //   236	1286	23	dsp	Ljava/lang/String;
/*     */     //   310	1212	27	mbx	Ljava/lang/String;
/*     */     //   364	1158	29	dsp	Ljava/lang/String;
/*     */     //   374	1148	30	mbx	Ljava/lang/String;
/*     */     //   411	1111	12	props2	Lakka/actor/Props;
/*     */     //   440	1082	36	dispatcher	Lakka/dispatch/MessageDispatcher;
/*     */     //   459	1063	37	mailboxType	Lakka/dispatch/MailboxType;
/*     */     //   605	917	38	lookup	Lscala/Option;
/*     */     //   636	64	40	x$3	Lakka/routing/RouterConfig;
/*     */     //   645	55	41	x$4	Ljava/lang/String;
/*     */     //   654	46	42	x$5	Lcom/typesafe/config/Config;
/*     */     //   663	37	43	x$6	Lakka/actor/Scope;
/*     */     //   672	28	44	x$7	Ljava/lang/String;
/*     */     //   681	19	45	x$8	Ljava/lang/String;
/*     */     //   712	810	39	fromProps	Lscala/collection/Iterator;
/*     */     //   762	760	46	d	Lakka/actor/Deploy;
/*     */     //   773	749	47	p	Lakka/actor/Props;
/*     */     //   824	70	49	x$9	Ljava/lang/String;
/*     */     //   834	60	50	x$10	Ljava/lang/String;
/*     */     //   844	50	51	x$11	Lcom/typesafe/config/Config;
/*     */     //   854	40	52	x$12	Lakka/routing/RouterConfig;
/*     */     //   864	30	53	x$13	Lakka/actor/Scope;
/*     */     //   874	20	54	x$14	Ljava/lang/String;
/*     */     //   935	587	48	routerProps	Lakka/actor/Props;
/*     */     //   945	577	55	routeeProps	Lakka/actor/Props;
/*     */     //   964	76	61	routerDispatcher	Lakka/dispatch/MessageDispatcher;
/*     */     //   983	57	62	routerMailbox	Lakka/dispatch/MailboxType;
/*     */     //   997	43	63	routeeDispatcher	Lakka/dispatch/MessageDispatcher;
/*     */     //   1016	24	64	routeeMailbox	Lakka/dispatch/MailboxType;
/*     */     //   1226	296	34	e	Ljava/lang/Throwable;
/*     */     //   1351	171	59	e	Ljava/lang/Throwable;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   426	506	1189	finally
/*     */     //   945	1040	1314	finally
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$actorOf$1 extends AbstractFunction1<Deploy, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorPath path$1;
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$actorOf$1(LocalActorRefProvider $outer, ActorPath path$1) {}
/*     */     
/*     */     public final void apply(Deploy d) {
/* 694 */       NoRouter$ noRouter$ = NoRouter$.MODULE$;
/* 694 */       if (d.routerConfig() == null) {
/* 694 */         d.routerConfig();
/* 694 */         if (noRouter$ != null) {
/* 695 */           this.$outer.log().warning("Configuration says that [{}] should be a router, but code disagrees. Remove the config or add a routerConfig to its Props.", this.path$1);
/*     */           return;
/*     */         } 
/*     */       } else if (!d.routerConfig().equals(noRouter$)) {
/* 695 */         this.$outer.log().warning("Configuration says that [{}] should be a router, but code disagrees. Remove the config or add a routerConfig to its Props.", this.path$1);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$2 extends AbstractFunction0<Iterator<Deploy>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Option deploy$1;
/*     */     
/*     */     public final Iterator<Deploy> apply() {
/* 729 */       return this.deploy$1.iterator();
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$2(LocalActorRefProvider $outer, Option deploy$1) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$3 extends AbstractFunction0<Iterator<Deploy>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Option lookup$1;
/*     */     
/*     */     public final Iterator<Deploy> apply() {
/* 729 */       return this.lookup$1.iterator();
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$3(LocalActorRefProvider $outer, Option lookup$1) {}
/*     */   }
/*     */   
/*     */   public class LocalActorRefProvider$$anonfun$4 extends AbstractFunction2<Deploy, Deploy, Deploy> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Deploy apply(Deploy a, Deploy b) {
/* 729 */       return b.withFallback(a);
/*     */     }
/*     */     
/*     */     public LocalActorRefProvider$$anonfun$4(LocalActorRefProvider $outer) {}
/*     */   }
/*     */   
/*     */   public Option<Address> getExternalAddressFor(Address addr) {
/* 759 */     Address address = rootPath().address();
/* 759 */     if (addr == null) {
/* 759 */       if (address != null);
/* 759 */     } else if (addr.equals(address)) {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public Address getDefaultAddress() {
/* 761 */     return rootPath().address();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LocalActorRefProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */