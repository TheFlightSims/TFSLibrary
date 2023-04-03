/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.BatchingExecutor;
/*     */ import akka.dispatch.DefaultDispatcherPrerequisites;
/*     */ import akka.dispatch.DispatcherPrerequisites;
/*     */ import akka.dispatch.Dispatchers;
/*     */ import akka.dispatch.Mailboxes;
/*     */ import akka.dispatch.MonitorableThreadFactory;
/*     */ import akka.dispatch.MonitorableThreadFactory$;
/*     */ import akka.event.BusLogging;
/*     */ import akka.event.DeadLetterListener;
/*     */ import akka.event.EventStream;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.event.LoggingBus;
/*     */ import akka.japi.Util$;
/*     */ import akka.util.ReentrantGuard;
/*     */ import com.typesafe.config.Config;
/*     */ import java.io.Closeable;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Seq$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.Await$;
/*     */ import scala.concurrent.Awaitable;
/*     */ import scala.concurrent.CanAwait;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.ExecutionContextExecutor;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.Duration$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ import scala.util.Try$;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\reg!B\001\003\001\0211!aD!di>\0248+_:uK6LU\016\0357\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\n\003\001\035\001\"\001C\005\016\003\tI!A\003\002\003'\025CH/\0328eK\022\f5\r^8s'f\034H/Z7\t\0211\001!Q1A\005\0029\tAA\\1nK\016\001Q#A\b\021\005A1bBA\t\025\033\005\021\"\"A\n\002\013M\034\027\r\\1\n\005U\021\022A\002)sK\022,g-\003\002\0301\t11\013\036:j]\036T!!\006\n\t\021i\001!\021!Q\001\n=\tQA\\1nK\002B\001\002\b\001\003\002\003\006I!H\001\022CB\004H.[2bi&|gnQ8oM&<\007C\001\020&\033\005y\"B\001\021\"\003\031\031wN\0344jO*\021!eI\001\tif\004Xm]1gK*\tA%A\002d_6L!AJ\020\003\r\r{gNZ5h\021!A\003A!A!\002\023I\023aC2mCN\034Hj\\1eKJ\004\"AK\030\016\003-R!\001L\027\002\t1\fgn\032\006\002]\005!!.\031<b\023\t\0014FA\006DY\006\0348\017T8bI\026\024\b\002\003\032\001\005\003\005\013\021B\032\002/\021,g-Y;mi\026CXmY;uS>t7i\0348uKb$\bcA\t5m%\021QG\005\002\007\037B$\030n\0348\021\005]RT\"\001\035\013\005e\022\022AC2p]\016,(O]3oi&\0211\b\017\002\021\013b,7-\036;j_:\034uN\034;fqRDQ!\020\001\005\002y\na\001P5oSRtD#B A\003\n\033\005C\001\005\001\021\025aA\b1\001\020\021\025aB\b1\001\036\021\025AC\b1\001*\021\025\021D\b1\0014\021\035)\005\0011A\005\n\031\013Q\003\\8h\t\026\fG\rT3ui\026\024H*[:uK:,'/F\001H!\r\tB\007\023\t\003\021%K!A\023\002\003\021\005\033Go\034:SK\032Dq\001\024\001A\002\023%Q*A\rm_\036$U-\0313MKR$XM\035'jgR,g.\032:`I\025\fHC\001(R!\t\tr*\003\002Q%\t!QK\\5u\021\035\0216*!AA\002\035\0131\001\037\0232\021\031!\006\001)Q\005\017\0061Bn\\4EK\006$G*\032;uKJd\025n\035;f]\026\024\b\005\013\002T-B\021\021cV\005\0031J\021\001B^8mCRLG.\032\005\b5\002\021\r\021\"\002\\\003!\031X\r\036;j]\036\034X#\001/\021\005u\003gB\001\005_\023\ty&!A\006BGR|'oU=ti\026l\027BA1c\005!\031V\r\036;j]\036\034(BA0\003\021\031!\007\001)A\0079\006I1/\032;uS:<7\017\t\005\006M\002!\tbZ\001\031k:\034\027-^4ii\026C8-\0329uS>t\007*\0318eY\026\024X#\0015\021\005%dgB\001\026k\023\tY7&\001\004UQJ,\027\rZ\005\003[:\024\001$\0268dCV<\007\016^#yG\026\004H/[8o\021\006tG\r\\3s\025\tY7\006C\004q\001\t\007IQA9\002\033QD'/Z1e\r\006\034Go\034:z+\005\021\bCA:w\033\005!(BA;\005\003!!\027n\0359bi\016D\027BA<u\005aiuN\\5u_J\f'\r\\3UQJ,\027\r\032$bGR|'/\037\005\007s\002\001\013Q\002:\002\035QD'/Z1e\r\006\034Go\034:zA!)1\020\001C\ty\006\0312M]3bi\026$\025P\\1nS\016\f5mY3tgR\tQ\020\005\002\t}&\021qP\001\002\016\tft\027-\\5d\003\016\034Wm]:\t\023\005\r\001A1A\005\n\005\025\021aA0q[V\tQ\020C\004\002\n\001\001\013\021B?\002\t}\003X\016\t\005\b\003\033\001A\021AA\003\0035!\027P\\1nS\016\f5mY3tg\"9\021\021\003\001\005\002\005M\021\001\0057pO\016{gNZ5hkJ\fG/[8o)\005q\005bBA\f\001\021E\021\021D\001\013gf\034H/Z7J[BdW#A \t\017\005u\001\001\"\001\002 \005i1/_:uK6\f5\r^8s\037\032$R\001SA\021\003WA\001\"a\t\002\034\001\007\021QE\001\006aJ|\007o\035\t\004\021\005\035\022bAA\025\005\t)\001K]8qg\"1A\"a\007A\002=Aq!a\f\001\t\003\t\t$A\004bGR|'o\0244\025\013!\013\031$!\016\t\021\005\r\022Q\006a\001\003KAa\001DA\027\001\004y\001bBA\030\001\021\005\021\021\b\013\004\021\006m\002\002CA\022\003o\001\r!!\n\t\017\005}\002\001\"\001\002B\005!1\017^8q)\rq\0251\t\005\007\007\005u\002\031\001%\t\023\005\035\003A1A\005\002\005%\023aC3wK:$8\013\036:fC6,\"!a\023\021\t\0055\0231K\007\003\003\037R1!!\025\005\003\025)g/\0328u\023\021\t)&a\024\003\027\0253XM\034;TiJ,\027-\034\005\t\0033\002\001\025!\003\002L\005aQM^3oiN#(/Z1nA!I\021Q\f\001C\002\023\005\021qL\001\004Y><WCAA1!\021\ti%a\031\n\t\005\025\024q\n\002\017\031><w-\0338h\003\022\f\007\017^3s\021!\tI\007\001Q\001\n\005\005\024\001\0027pO\002B\021\"!\034\001\005\004%\t!a\034\002\023M\034\007.\0323vY\026\024XCAA9!\rA\0211O\005\004\003k\022!!C*dQ\026$W\017\\3s\021!\tI\b\001Q\001\n\005E\024AC:dQ\026$W\017\\3sA!I\021Q\020\001C\002\023\005\021qP\001\taJ|g/\0333feV\021\021\021\021\t\004\021\005\r\025bAAC\005\t\001\022i\031;peJ+g\r\025:pm&$WM\035\005\t\003\023\003\001\025!\003\002\002\006I\001O]8wS\022,'\017\t\005\b\003\033\003A\021AAH\003-!W-\0313MKR$XM]:\026\003!C\021\"a%\001\005\004%\t!!&\002\0235\f\027\016\0342pq\026\034XCAAL!\r\031\030\021T\005\004\0037#(!C'bS2\024w\016_3t\021!\ty\n\001Q\001\n\005]\025AC7bS2\024w\016_3tA!I\0211\025\001C\002\023\005\021QU\001\fI&\034\b/\031;dQ\026\0248/\006\002\002(B\0311/!+\n\007\005-FOA\006ESN\004\030\r^2iKJ\034\b\002CAX\001\001\006I!a*\002\031\021L7\017]1uG\",'o\035\021\t\023\005M\006A1A\005\002\005U\026A\0033jgB\fGo\0315feV\021\021q\027\t\004o\005e\026bAA^q\tAR\t_3dkRLwN\\\"p]R,\007\020^#yK\016,Ho\034:\t\021\005}\006\001)A\005\003o\0131\002Z5ta\006$8\r[3sA!I\0211\031\001C\002\023\005\021QY\001&S:$XM\0358bY\016\013G\016\\5oORC'/Z1e\013b,7-\036;j_:\034uN\034;fqR,\022A\016\005\b\003\023\004\001\025!\0037\003\031Jg\016^3s]\006d7)\0317mS:<G\013\033:fC\022,\0050Z2vi&|gnQ8oi\026DH\017\t\005\b\003\033\004A\021AAh\003E!XM]7j]\006$\030n\0348GkR,(/Z\013\003\003#\004BaNAj\035&\031\021Q\033\035\003\r\031+H/\036:f\021\035\tI\016\001C\001\0037\f!\002\\8pWV\004(k\\8u+\t\ti\016E\002\t\003?L1!!9\003\005AIe\016^3s]\006d\027i\031;peJ+g\rC\004\002f\002!\t!a:\002\021\035,\030M\0353jC:,\"!!;\021\007!\tY/C\002\002n\n\021Q\002T8dC2\f5\r^8s%\0264\007bBAy\001\021\005\021q]\001\017gf\034H/Z7Hk\006\024H-[1o\021\035\t)\020\001C\001\003o\fA\001\n3jmR!\021\021`A\000!\rA\0211`\005\004\003{\024!!C!di>\024\b+\031;i\021\035\021\t!a=A\002=\t\021\"Y2u_Jt\025-\\3\t\017\005U\b\001\"\001\003\006Q!\021\021 B\004\021!\021IAa\001A\002\t-\021\001\0029bi\"\004RA!\004\003\036=qAAa\004\003\0329!!\021\003B\f\033\t\021\031BC\002\003\0265\ta\001\020:p_Rt\024\"A\n\n\007\tm!#A\004qC\016\\\027mZ3\n\t\t}!\021\005\002\t\023R,'/\0312mK*\031!1\004\n\t\025\t\025\002\001#b\001\n\023\0219#\001\004`gR\f'\017^\013\003\005Si\021\001\001\005\013\005[\001\001\022!Q!\n\t%\022aB0ti\006\024H\017\t\005\b\005c\001A\021\001B\032\003\025\031H/\031:u)\t\021I\003\003\006\0038\001A)\031!C\005\005s\tA\003^3s[&t\027\r^5p]\016\013G\016\0342bG.\034XC\001B\036!\021\021IC!\020\007\r\t}\002A\001B!\005Q!VM]7j]\006$\030n\0348DC2d'-Y2lgNA!Q\bB\"\005\023\022y\005E\002+\005\013J1Aa\022,\005\031y%M[3diB\031!Fa\023\n\007\t53F\001\005Sk:t\027M\0317f!\0219$\021\013(\n\007\tM\003HA\005Bo\006LG/\0312mK\"9QH!\020\005\002\t]CC\001B\036\021)\021YF!\020C\002\023%!QL\001\005Y>\0347.\006\002\003`A!!\021\rB4\033\t\021\031GC\002\003f\021\tA!\036;jY&!!\021\016B2\0059\021V-\0328ue\006tGoR;be\022D\021B!\034\003>\001\006IAa\030\002\0131|7m\033\021\t\031\tE$Q\ba\001\002\004%IAa\035\002\023\r\fG\016\0342bG.\034XC\001B;!\031\021iAa\036\003J%!!\021\020B\021\005\021a\025n\035;\t\031\tu$Q\ba\001\002\004%IAa \002\033\r\fG\016\0342bG.\034x\fJ3r)\rq%\021\021\005\n%\nm\024\021!a\001\005kB\021B!\"\003>\001\006KA!\036\002\025\r\fG\016\0342bG.\034\b\005\003\006\003\n\nu\"\031!C\005\005\027\013Q\001\\1uG\",\"A!$\021\t\t=%QS\007\003\005#S1!\017BJ\025\r\021)'L\005\005\005/\023\tJ\001\bD_VtG\017R8x]2\013Go\0315\t\023\tm%Q\bQ\001\n\t5\025A\0027bi\016D\007\005\003\005\003 \nuBQ\001BQ\003\r\tG\r\032\013\004\035\n\r\006\002\003BS\005;\003\rA!\023\002\021\r\fG\016\0342bG.D\001B!+\003>\021\025\0211C\001\004eVt\007\002\003BW\005{!)Aa,\002\013I,\027\rZ=\025\t\tE&q\030\013\005\005g\023),\004\002\003>!A!q\027BV\001\b\021I,\001\004qKJl\027\016\036\t\004o\tm\026b\001B_q\tA1)\0318Bo\006LG\017\003\005\003B\n-\006\031\001Bb\003\031\tG/T8tiB!!Q\031Bf\033\t\0219MC\002\003Jb\n\001\002Z;sCRLwN\\\005\005\005\033\0249M\001\005EkJ\fG/[8o\021!\021\tN!\020\005\006\tM\027A\002:fgVdG\017\006\003\003V\neGc\001(\003X\"A!q\027Bh\001\b\021I\f\003\005\003B\n=\007\031\001Bb\021!\021iN!\020\005\006\t}\027\001D5t)\026\024X.\0338bi\026$WC\001Bq!\r\t\"1]\005\004\005K\024\"a\002\"p_2,\027M\034\005\013\005S\004\001\022!Q!\n\tm\022!\006;fe6Lg.\031;j_:\034\025\r\0347cC\016\\7\017\t\005\b\005[\004A\021\001Bx\003U\021XmZ5ti\026\024xJ\034+fe6Lg.\031;j_:,BA!=\004\002Q\031aJa=\t\023\tU(1\036CA\002\t]\030\001B2pI\026\004R!\005B}\005{L1Aa?\023\005!a$-\0378b[\026t\004\003\002B\000\007\003a\001\001\002\005\004\004\t-(\031AB\003\005\005!\026\003BB\004\007\033\0012!EB\005\023\r\031YA\005\002\b\035>$\b.\0338h!\r\t2qB\005\004\007#\021\"aA!os\"9!Q\036\001\005\002\rUAc\001(\004\030!A!Q_B\n\001\004\021I\005C\004\004\034\001!\ta!\b\002!\005<\030-\033;UKJl\027N\\1uS>tGc\001(\004 !A1\021EB\r\001\004\021\031-A\004uS6,w.\036;\t\017\rm\001\001\"\001\002\024!9!Q\034\001\005\002\t}\007bBB\025\001\021\005\0211C\001\tg\",H\017Z8x]\"I1Q\006\001A\002\023\005!q\\\001\tC\n|'\017^5oO\"I1\021\007\001A\002\023\00511G\001\rC\n|'\017^5oO~#S-\035\013\004\035\016U\002\"\003*\0040\005\005\t\031\001Bq\021!\031I\004\001Q!\n\t\005\030!C1c_J$\030N\\4!Q\r\0319D\026\005\b\007\001A\021AA\n\003\025\t'm\034:u\021\035\031\031\005\001C\t\007\013\nqb\031:fCR,7k\0315fIVdWM\035\013\003\003cBqa!\023\001\t#\t\031\"A\007ti>\0048k\0315fIVdWM\035\005\n\007\033\002!\031!C\005\007\037\n!\"\032=uK:\034\030n\0348t+\t\031\t\006\005\005\003\020\016M3qKB5\023\021\031)F!%\003#\r{gnY;se\026tG\017S1tQ6\013\007\017\r\003\004Z\r\005\004#\002\005\004\\\r}\023bAB/\005\tYQ\t\037;f]NLwN\\%e!\021\021yp!\031\005\031\r\r4QMA\001\002\003\025\ta!\002\003\007}#3\007\003\005\004h\001\001\013\021BB)\003-)\007\020^3og&|gn\035\021\021\007E\031Y'C\002\004nI\021a!\0218z%\0264\007bBB9\001\021%11O\001\016M&tG-\022=uK:\034\030n\0348\026\t\rU4\021\020\013\005\007o\032\031\t\005\003\003\000\016eD\001CB\002\007_\022\raa\037\022\t\r\0351Q\020\t\004\021\r}\024bABA\005\tIQ\t\037;f]NLwN\034\005\t\007\013\033y\0071\001\004\b\006\031Q\r\037;\021\013!\031Yfa\036)\t\r=41\022\t\005\007\033\033\031*\004\002\004\020*\0311\021\023\n\002\025\005tgn\034;bi&|g.\003\003\004\026\016=%a\002;bS2\024Xm\031\005\b\0073\003AQABN\003E\021XmZ5ti\026\024X\t\037;f]NLwN\\\013\005\007;\033\t\013\006\003\004 \016\r\006\003\002B\000\007C#\001ba\001\004\030\n\00711\020\005\t\007\013\0339\n1\001\004&B)\001ba\027\004 \"\"1qSBF\021\035\031Y\013\001C\001\007[\013\021\"\032=uK:\034\030n\0348\026\t\r=61\027\013\005\007c\033)\f\005\003\003\000\016MF\001CB\002\007S\023\raa\037\t\021\r\0255\021\026a\001\007o\003R\001CB.\007cCqaa/\001\t\003\031i,\001\007iCN,\005\020^3og&|g\016\006\003\003b\016}\006\002CBC\007s\003\ra!11\t\r\r7q\031\t\006\021\rm3Q\031\t\005\005\0349\r\002\007\004J\016}\026\021!A\001\006\003\031YHA\002`IQBqa!4\001\t\023\t\031\"\001\bm_\006$W\t\037;f]NLwN\\:\t\017\rE\007\001\"\021\004T\006AAo\\*ue&tw\rF\001\020\021\031\0319\016\001C!\035\005I\001O]5oiR\023X-\032")
/*     */ public class ActorSystemImpl extends ExtendedActorSystem {
/*     */   private final String name;
/*     */   
/*     */   private final ClassLoader classLoader;
/*     */   
/*     */   private volatile Option<ActorRef> logDeadLetterListener;
/*     */   
/*     */   private final ActorSystem.Settings settings;
/*     */   
/*     */   private final MonitorableThreadFactory threadFactory;
/*     */   
/*     */   private final DynamicAccess _pm;
/*     */   
/*     */   private final EventStream eventStream;
/*     */   
/*     */   private final LoggingAdapter log;
/*     */   
/*     */   private final Scheduler scheduler;
/*     */   
/*     */   private final ActorRefProvider provider;
/*     */   
/*     */   private final Mailboxes mailboxes;
/*     */   
/*     */   private final Dispatchers dispatchers;
/*     */   
/*     */   private final ExecutionContextExecutor dispatcher;
/*     */   
/*     */   private final ExecutionContext internalCallingThreadExecutionContext;
/*     */   
/*     */   private ActorSystemImpl _start;
/*     */   
/*     */   private TerminationCallbacks terminationCallbacks;
/*     */   
/*     */   private volatile boolean aborting;
/*     */   
/*     */   private final ConcurrentHashMap<ExtensionId<?>, Object> extensions;
/*     */   
/*     */   private volatile byte bitmap$0;
/*     */   
/*     */   public String name() {
/* 494 */     return this.name;
/*     */   }
/*     */   
/*     */   public ActorSystemImpl(String name, Config applicationConfig, ClassLoader classLoader, Option defaultExecutionContext) {
/* 496 */     if (name.matches("^[a-zA-Z0-9][a-zA-Z0-9-]*$")) {
/* 503 */       this.logDeadLetterListener = (Option<ActorRef>)None$.MODULE$;
/* 504 */       this.settings = new ActorSystem.Settings(classLoader, applicationConfig, name);
/* 534 */       this.threadFactory = 
/* 535 */         new MonitorableThreadFactory(name, settings().Daemonicity(), Option$.MODULE$.apply(classLoader), uncaughtExceptionHandler(), MonitorableThreadFactory$.MODULE$.apply$default$5());
/* 543 */       this._pm = createDynamicAccess();
/* 570 */       this.eventStream = new EventStream(settings().DebugEventStream());
/* 571 */       eventStream().startStdoutLogger(settings());
/* 573 */       this.log = (LoggingAdapter)new BusLogging((LoggingBus)eventStream(), (new StringBuilder()).append("ActorSystem(").append(name).append(")").toString(), getClass());
/* 575 */       this.scheduler = createScheduler();
/* 577 */       this.provider = liftedTree1$1();
/* 593 */       this.mailboxes = new Mailboxes(settings(), eventStream(), dynamicAccess(), deadLetters());
/* 595 */       this.dispatchers = new Dispatchers(settings(), (DispatcherPrerequisites)new DefaultDispatcherPrerequisites(
/* 596 */             (ThreadFactory)threadFactory(), eventStream(), scheduler(), dynamicAccess(), settings(), mailboxes(), defaultExecutionContext));
/* 598 */       this.dispatcher = (ExecutionContextExecutor)dispatchers().defaultGlobalDispatcher();
/* 600 */       this.internalCallingThreadExecutionContext = 
/* 601 */         (ExecutionContext)dynamicAccess().<T>getObjectFor("scala.concurrent.Future$InternalCallbackExecutor$", ClassTag$.MODULE$.apply(ExecutionContext.class)).getOrElse(
/* 602 */           (Function0)new $anonfun$7(this));
/* 651 */       this.aborting = false;
/* 691 */       this.extensions = new ConcurrentHashMap<ExtensionId<?>, Object>();
/*     */       return;
/*     */     } 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("invalid ActorSystem name [").append(name).append("], must contain only word characters (i.e. [a-zA-Z0-9] plus non-leading '-')").toString());
/*     */   }
/*     */   
/*     */   private Option<ActorRef> logDeadLetterListener() {
/*     */     return this.logDeadLetterListener;
/*     */   }
/*     */   
/*     */   private void logDeadLetterListener_$eq(Option<ActorRef> x$1) {
/*     */     this.logDeadLetterListener = x$1;
/*     */   }
/*     */   
/*     */   public final ActorSystem.Settings settings() {
/*     */     return this.settings;
/*     */   }
/*     */   
/*     */   public Thread.UncaughtExceptionHandler uncaughtExceptionHandler() {
/*     */     return new ActorSystemImpl$$anon$2(this);
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anon$2 implements Thread.UncaughtExceptionHandler {
/*     */     public ActorSystemImpl$$anon$2(ActorSystemImpl $outer) {}
/*     */     
/*     */     public void uncaughtException(Thread thread, Throwable cause) {
/*     */       boolean bool;
/*     */       Throwable throwable = cause;
/*     */       Option option = NonFatal$.MODULE$.unapply(throwable);
/*     */       if (option.isEmpty()) {
/*     */         if (throwable instanceof InterruptedException) {
/*     */           bool = true;
/*     */         } else if (throwable instanceof scala.NotImplementedError) {
/*     */           bool = true;
/*     */         } else if (throwable instanceof scala.util.control.ControlThrowable) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */       } else {
/*     */         bool = true;
/*     */       } 
/*     */       if (bool) {
/*     */         this.$outer.log().error(cause, "Uncaught error from thread [{}]", thread.getName());
/*     */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/*     */         if (this.$outer.settings().JvmExitOnFatalError())
/*     */           try {
/*     */             this.$outer.log().error(cause, "Uncaught error from thread [{}] shutting down JVM since 'akka.jvm-exit-on-fatal-error' is enabled", thread.getName());
/*     */             System.err.print("Uncaught error from thread [");
/*     */             System.err.print(thread.getName());
/*     */             System.err.print("] shutting down JVM since 'akka.jvm-exit-on-fatal-error' is enabled for ActorSystem[");
/*     */             System.err.print(this.$outer.name());
/*     */             System.err.println("]");
/*     */             cause.printStackTrace(System.err);
/*     */             System.err.flush();
/*     */           } finally {
/*     */             System.exit(-1);
/*     */           }  
/*     */         this.$outer.log().error(cause, "Uncaught fatal error from thread [{}] shutting down ActorSystem [{}]", thread.getName(), this.$outer.name());
/*     */         this.$outer.shutdown();
/*     */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public final MonitorableThreadFactory threadFactory() {
/*     */     return this.threadFactory;
/*     */   }
/*     */   
/*     */   public DynamicAccess createDynamicAccess() {
/*     */     return new ReflectiveDynamicAccess(this.classLoader);
/*     */   }
/*     */   
/*     */   private DynamicAccess _pm() {
/*     */     return this._pm;
/*     */   }
/*     */   
/*     */   public DynamicAccess dynamicAccess() {
/*     */     return _pm();
/*     */   }
/*     */   
/*     */   public void logConfiguration() {
/*     */     log().info(settings().toString());
/*     */   }
/*     */   
/*     */   public ActorSystemImpl systemImpl() {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public ActorRef systemActorOf(Props props, String name) {
/*     */     return systemGuardian().underlying().attachChild(props, name, true);
/*     */   }
/*     */   
/*     */   public ActorRef actorOf(Props props, String name) {
/*     */     return guardian().underlying().attachChild(props, name, false);
/*     */   }
/*     */   
/*     */   public ActorRef actorOf(Props props) {
/*     */     return guardian().underlying().attachChild(props, false);
/*     */   }
/*     */   
/*     */   public void stop(ActorRef actor) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   4: astore_2
/*     */     //   5: aload_0
/*     */     //   6: invokevirtual guardian : ()Lakka/actor/LocalActorRef;
/*     */     //   9: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   12: astore_3
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual systemGuardian : ()Lakka/actor/LocalActorRef;
/*     */     //   17: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   20: astore #4
/*     */     //   22: aload_2
/*     */     //   23: invokeinterface parent : ()Lakka/actor/ActorPath;
/*     */     //   28: astore #5
/*     */     //   30: aload_3
/*     */     //   31: aload #5
/*     */     //   33: astore #6
/*     */     //   35: dup
/*     */     //   36: ifnonnull -> 48
/*     */     //   39: pop
/*     */     //   40: aload #6
/*     */     //   42: ifnull -> 56
/*     */     //   45: goto -> 98
/*     */     //   48: aload #6
/*     */     //   50: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   53: ifeq -> 98
/*     */     //   56: aload_0
/*     */     //   57: invokevirtual guardian : ()Lakka/actor/LocalActorRef;
/*     */     //   60: astore #8
/*     */     //   62: new akka/actor/StopChild
/*     */     //   65: dup
/*     */     //   66: aload_1
/*     */     //   67: invokespecial <init> : (Lakka/actor/ActorRef;)V
/*     */     //   70: astore #9
/*     */     //   72: aload #8
/*     */     //   74: aload #9
/*     */     //   76: invokevirtual $bang$default$2 : (Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   79: astore #10
/*     */     //   81: aload #8
/*     */     //   83: aload #9
/*     */     //   85: aload #10
/*     */     //   87: invokevirtual $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   90: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   93: astore #7
/*     */     //   95: goto -> 179
/*     */     //   98: aload #4
/*     */     //   100: aload #5
/*     */     //   102: astore #11
/*     */     //   104: dup
/*     */     //   105: ifnonnull -> 117
/*     */     //   108: pop
/*     */     //   109: aload #11
/*     */     //   111: ifnull -> 125
/*     */     //   114: goto -> 167
/*     */     //   117: aload #11
/*     */     //   119: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   122: ifeq -> 167
/*     */     //   125: aload_0
/*     */     //   126: invokevirtual systemGuardian : ()Lakka/actor/LocalActorRef;
/*     */     //   129: astore #12
/*     */     //   131: new akka/actor/StopChild
/*     */     //   134: dup
/*     */     //   135: aload_1
/*     */     //   136: invokespecial <init> : (Lakka/actor/ActorRef;)V
/*     */     //   139: astore #13
/*     */     //   141: aload #12
/*     */     //   143: aload #13
/*     */     //   145: invokevirtual $bang$default$2 : (Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   148: astore #14
/*     */     //   150: aload #12
/*     */     //   152: aload #13
/*     */     //   154: aload #14
/*     */     //   156: invokevirtual $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   159: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   162: astore #7
/*     */     //   164: goto -> 179
/*     */     //   167: aload_1
/*     */     //   168: checkcast akka/actor/InternalActorRef
/*     */     //   171: invokevirtual stop : ()V
/*     */     //   174: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   177: astore #7
/*     */     //   179: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #557	-> 0
/*     */     //   #558	-> 5
/*     */     //   #559	-> 13
/*     */     //   #560	-> 22
/*     */     //   #561	-> 30
/*     */     //   #562	-> 98
/*     */     //   #563	-> 167
/*     */     //   #556	-> 179
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	180	0	this	Lakka/actor/ActorSystemImpl;
/*     */     //   0	180	1	actor	Lakka/actor/ActorRef;
/*     */     //   5	175	2	path	Lakka/actor/ActorPath;
/*     */     //   13	167	3	guard	Lakka/actor/ActorPath;
/*     */     //   22	158	4	sys	Lakka/actor/ActorPath;
/*     */     //   62	31	8	qual$1	Lakka/actor/LocalActorRef;
/*     */     //   72	21	9	x$4	Lakka/actor/StopChild;
/*     */     //   81	12	10	x$5	Lakka/actor/ActorRef;
/*     */     //   131	31	12	qual$2	Lakka/actor/LocalActorRef;
/*     */     //   141	21	13	x$6	Lakka/actor/StopChild;
/*     */     //   150	12	14	x$7	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   public EventStream eventStream() {
/*     */     return this.eventStream;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*     */     return this.log;
/*     */   }
/*     */   
/*     */   public Scheduler scheduler() {
/*     */     return this.scheduler;
/*     */   }
/*     */   
/*     */   public ActorRefProvider provider() {
/*     */     return this.provider;
/*     */   }
/*     */   
/*     */   private final ActorRefProvider liftedTree1$1() {
/*     */     try {
/*     */       return (ActorRefProvider)dynamicAccess().<T>createInstanceFor(settings().ProviderClass(), (Seq<Tuple2<Class<?>, Object>>)arguments, ClassTag$.MODULE$.apply(ActorRefProvider.class)).get();
/*     */     } finally {
/*     */       Exception exception1 = null, exception2 = exception1;
/*     */       Option option = NonFatal$.MODULE$.unapply(exception2);
/*     */       if (option.isEmpty())
/*     */         throw exception1; 
/*     */       Throwable e = (Throwable)option.get();
/*     */       Try$.MODULE$.apply((Function0)new ActorSystemImpl$$anonfun$liftedTree1$1$1(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$liftedTree1$1$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.$outer.stopScheduler();
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$liftedTree1$1$1(ActorSystemImpl $outer) {}
/*     */   }
/*     */   
/*     */   public ActorRef deadLetters() {
/*     */     return provider().deadLetters();
/*     */   }
/*     */   
/*     */   public Mailboxes mailboxes() {
/*     */     return this.mailboxes;
/*     */   }
/*     */   
/*     */   public Dispatchers dispatchers() {
/*     */     return this.dispatchers;
/*     */   }
/*     */   
/*     */   public ExecutionContextExecutor dispatcher() {
/*     */     return this.dispatcher;
/*     */   }
/*     */   
/*     */   public ExecutionContext internalCallingThreadExecutionContext() {
/*     */     return this.internalCallingThreadExecutionContext;
/*     */   }
/*     */   
/*     */   public class $anonfun$7 extends AbstractFunction0<$anonfun$7.$anon$1> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final $anon$1 apply() {
/*     */       return new $anon$1(this);
/*     */     }
/*     */     
/*     */     public $anonfun$7(ActorSystemImpl $outer) {}
/*     */     
/*     */     public class $anon$1 implements ExecutionContext, BatchingExecutor {
/*     */       private final ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal;
/*     */       
/*     */       public ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal() {
/*     */         return this.akka$dispatch$BatchingExecutor$$_tasksLocal;
/*     */       }
/*     */       
/*     */       public void akka$dispatch$BatchingExecutor$_setter_$akka$dispatch$BatchingExecutor$$_tasksLocal_$eq(ThreadLocal<List<Runnable>> x$1) {
/*     */         this.akka$dispatch$BatchingExecutor$$_tasksLocal = x$1;
/*     */       }
/*     */       
/*     */       public void execute(Runnable runnable) {
/*     */         BatchingExecutor.class.execute(this, runnable);
/*     */       }
/*     */       
/*     */       public boolean batchable(Runnable runnable) {
/*     */         return BatchingExecutor.class.batchable(this, runnable);
/*     */       }
/*     */       
/*     */       public ExecutionContext prepare() {
/*     */         return ExecutionContext.class.prepare(this);
/*     */       }
/*     */       
/*     */       public $anon$1(ActorSystemImpl.$anonfun$7 $outer) {
/*     */         ExecutionContext.class.$init$(this);
/*     */         BatchingExecutor.class.$init$(this);
/*     */       }
/*     */       
/*     */       public void unbatchedExecute(Runnable r) {
/*     */         r.run();
/*     */       }
/*     */       
/*     */       public void reportFailure(Throwable t) {
/*     */         this.$outer.akka$actor$ActorSystemImpl$$anonfun$$$outer().dispatcher().reportFailure(t);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Future<BoxedUnit> terminationFuture() {
/*     */     return provider().terminationFuture();
/*     */   }
/*     */   
/*     */   public InternalActorRef lookupRoot() {
/*     */     return provider().rootGuardian();
/*     */   }
/*     */   
/*     */   public LocalActorRef guardian() {
/*     */     return provider().guardian();
/*     */   }
/*     */   
/*     */   public LocalActorRef systemGuardian() {
/*     */     return provider().systemGuardian();
/*     */   }
/*     */   
/*     */   public ActorPath $div(String actorName) {
/*     */     return guardian().path().$div(actorName);
/*     */   }
/*     */   
/*     */   public ActorPath $div(Iterable<String> path) {
/*     */     return guardian().path().$div(path);
/*     */   }
/*     */   
/*     */   private ActorSystemImpl _start$lzycompute() {
/*     */     synchronized (this) {
/*     */       if ((byte)(this.bitmap$0 & 0x1) == 0) {
/*     */         this._start = liftedTree2$1();
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */       } 
/*     */       return this._start;
/*     */     } 
/*     */   }
/*     */   
/*     */   private ActorSystemImpl _start() {
/*     */     return ((byte)(this.bitmap$0 & 0x1) == 0) ? _start$lzycompute() : this._start;
/*     */   }
/*     */   
/*     */   private final ActorSystemImpl liftedTree2$1() {
/*     */     try {
/*     */       provider().init(this);
/*     */       if (settings().LogDeadLetters() > 0)
/*     */         logDeadLetterListener_$eq((Option<ActorRef>)new Some(systemActorOf(Props$.MODULE$.apply(ClassTag$.MODULE$.apply(DeadLetterListener.class)), "deadLetterListener"))); 
/*     */       registerOnTermination((Function0<?>)new ActorSystemImpl$$anonfun$liftedTree2$1$1(this));
/*     */       return this;
/*     */     } finally {
/*     */       Throwable e;
/*     */       Exception exception1 = null, exception2 = exception1;
/*     */       Option option = NonFatal$.MODULE$.unapply(exception2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$liftedTree2$1$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.$outer.stopScheduler();
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$liftedTree2$1$1(ActorSystemImpl $outer) {}
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$liftedTree2$1$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.$outer.stopScheduler();
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$liftedTree2$1$2(ActorSystemImpl $outer) {}
/*     */   }
/*     */   
/*     */   public ActorSystemImpl start() {
/*     */     return _start();
/*     */   }
/*     */   
/*     */   private TerminationCallbacks terminationCallbacks$lzycompute() {
/*     */     synchronized (this) {
/*     */       if ((byte)(this.bitmap$0 & 0x2) == 0) {
/*     */         ExecutionContextExecutor d = dispatcher();
/*     */         TerminationCallbacks callbacks = new TerminationCallbacks(this);
/*     */         terminationFuture().onComplete((Function1)new ActorSystemImpl$$anonfun$terminationCallbacks$1(this, callbacks), (ExecutionContext)d);
/*     */         this.terminationCallbacks = callbacks;
/*     */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */       } 
/*     */       return this.terminationCallbacks;
/*     */     } 
/*     */   }
/*     */   
/*     */   private TerminationCallbacks terminationCallbacks() {
/*     */     return ((byte)(this.bitmap$0 & 0x2) == 0) ? terminationCallbacks$lzycompute() : this.terminationCallbacks;
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$terminationCallbacks$1 extends AbstractFunction1<Try<BoxedUnit>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorSystemImpl.TerminationCallbacks callbacks$1;
/*     */     
/*     */     public final void apply(Try x$1) {
/*     */       this.callbacks$1.run();
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$terminationCallbacks$1(ActorSystemImpl $outer, ActorSystemImpl.TerminationCallbacks callbacks$1) {}
/*     */   }
/*     */   
/*     */   public <T> void registerOnTermination(Function0 code) {
/*     */     registerOnTermination(new ActorSystemImpl$$anon$3(this, code));
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anon$3 implements Runnable {
/*     */     private final Function0 code$1;
/*     */     
/*     */     public void run() {
/*     */       this.code$1.apply();
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anon$3(ActorSystemImpl $outer, Function0 code$1) {}
/*     */   }
/*     */   
/*     */   public void registerOnTermination(Runnable code) {
/*     */     terminationCallbacks().add(code);
/*     */   }
/*     */   
/*     */   public void awaitTermination(Duration timeout) {
/*     */     Await$.MODULE$.ready(terminationCallbacks(), timeout);
/*     */   }
/*     */   
/*     */   public void awaitTermination() {
/*     */     awaitTermination((Duration)Duration$.MODULE$.Inf());
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/*     */     return terminationCallbacks().isTerminated();
/*     */   }
/*     */   
/*     */   public void shutdown() {
/*     */     if (!settings().LogDeadLettersDuringShutdown())
/*     */       logDeadLetterListener().foreach((Function1)new ActorSystemImpl$$anonfun$shutdown$1(this)); 
/*     */     guardian().stop();
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$shutdown$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef actor) {
/*     */       this.$outer.stop(actor);
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$shutdown$1(ActorSystemImpl $outer) {}
/*     */   }
/*     */   
/*     */   public boolean aborting() {
/*     */     return this.aborting;
/*     */   }
/*     */   
/*     */   public void aborting_$eq(boolean x$1) {
/*     */     this.aborting = x$1;
/*     */   }
/*     */   
/*     */   public void abort() {
/*     */     aborting_$eq(true);
/*     */     shutdown();
/*     */   }
/*     */   
/*     */   public Scheduler createScheduler() {
/*     */     (new Tuple2[3])[0] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(Config.class), settings().config());
/*     */     (new Tuple2[3])[1] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(LoggingAdapter.class), log());
/*     */     (new Tuple2[3])[2] = Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(ThreadFactory.class), threadFactory().withName((new StringBuilder()).append(threadFactory().name()).append("-scheduler").toString()));
/*     */     return (Scheduler)dynamicAccess().<T>createInstanceFor(settings().SchedulerClass(), (Seq<Tuple2<Class<?>, Object>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[3])), ClassTag$.MODULE$.apply(Scheduler.class)).get();
/*     */   }
/*     */   
/*     */   public void stopScheduler() {
/*     */     Scheduler scheduler = scheduler();
/*     */     if (scheduler instanceof Closeable) {
/*     */       Scheduler scheduler1 = scheduler;
/*     */       ((Closeable)scheduler1).close();
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<ExtensionId<?>, Object> extensions() {
/* 691 */     return this.extensions;
/*     */   }
/*     */   
/*     */   private <T extends Extension> T findExtension(ExtensionId ext) {
/*     */     Object object;
/*     */     while (true) {
/* 697 */       object = extensions().get(ext);
/* 698 */       if (object instanceof CountDownLatch) {
/* 698 */         CountDownLatch countDownLatch = (CountDownLatch)object;
/* 699 */         countDownLatch.await();
/* 699 */         ext = ext;
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 701 */     return (T)object;
/*     */   }
/*     */   
/*     */   public final <T extends Extension> T registerExtension(ExtensionId<Object> ext) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokespecial findExtension : (Lakka/actor/ExtensionId;)Lakka/actor/Extension;
/*     */     //   5: astore_3
/*     */     //   6: aload_3
/*     */     //   7: ifnonnull -> 123
/*     */     //   10: new java/util/concurrent/CountDownLatch
/*     */     //   13: dup
/*     */     //   14: iconst_1
/*     */     //   15: invokespecial <init> : (I)V
/*     */     //   18: astore #5
/*     */     //   20: aload_0
/*     */     //   21: invokespecial extensions : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   24: aload_1
/*     */     //   25: aload #5
/*     */     //   27: invokevirtual putIfAbsent : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   30: astore #6
/*     */     //   32: aload #6
/*     */     //   34: ifnonnull -> 118
/*     */     //   37: aload_1
/*     */     //   38: aload_0
/*     */     //   39: invokeinterface createExtension : (Lakka/actor/ExtendedActorSystem;)Lakka/actor/Extension;
/*     */     //   44: astore #10
/*     */     //   46: aload #10
/*     */     //   48: ifnonnull -> 85
/*     */     //   51: new java/lang/IllegalStateException
/*     */     //   54: dup
/*     */     //   55: new scala/collection/mutable/StringBuilder
/*     */     //   58: dup
/*     */     //   59: invokespecial <init> : ()V
/*     */     //   62: ldc_w 'Extension instance created as 'null' for extension ['
/*     */     //   65: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   68: aload_1
/*     */     //   69: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   72: ldc_w ']'
/*     */     //   75: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   78: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   81: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   84: athrow
/*     */     //   85: aload_0
/*     */     //   86: invokespecial extensions : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   89: aload_1
/*     */     //   90: aload #5
/*     */     //   92: aload #10
/*     */     //   94: invokevirtual replace : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   97: pop
/*     */     //   98: aload #10
/*     */     //   100: astore #11
/*     */     //   102: aload #11
/*     */     //   104: aload #5
/*     */     //   106: invokevirtual countDown : ()V
/*     */     //   109: astore #7
/*     */     //   111: aload #7
/*     */     //   113: astore #4
/*     */     //   115: goto -> 126
/*     */     //   118: aload_1
/*     */     //   119: astore_1
/*     */     //   120: goto -> 0
/*     */     //   123: aload_3
/*     */     //   124: astore #4
/*     */     //   126: aload #4
/*     */     //   128: areturn
/*     */     //   129: astore #8
/*     */     //   131: aload_0
/*     */     //   132: invokespecial extensions : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   135: aload_1
/*     */     //   136: aload #5
/*     */     //   138: invokevirtual remove : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   141: pop
/*     */     //   142: aload #8
/*     */     //   144: athrow
/*     */     //   145: astore #9
/*     */     //   147: aload #5
/*     */     //   149: invokevirtual countDown : ()V
/*     */     //   152: aload #9
/*     */     //   154: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #706	-> 0
/*     */     //   #707	-> 6
/*     */     //   #708	-> 10
/*     */     //   #709	-> 20
/*     */     //   #710	-> 32
/*     */     //   #711	-> 37
/*     */     //   #712	-> 46
/*     */     //   #714	-> 85
/*     */     //   #715	-> 98
/*     */     //   #713	-> 100
/*     */     //   #711	-> 102
/*     */     //   #722	-> 104
/*     */     //   #710	-> 109
/*     */     //   #709	-> 111
/*     */     //   #707	-> 113
/*     */     //   #724	-> 118
/*     */     //   #726	-> 123
/*     */     //   #706	-> 126
/*     */     //   #718	-> 129
/*     */     //   #710	-> 129
/*     */     //   #719	-> 131
/*     */     //   #720	-> 142
/*     */     //   #722	-> 145
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	155	0	this	Lakka/actor/ActorSystemImpl;
/*     */     //   0	155	1	ext	Lakka/actor/ExtensionId;
/*     */     //   20	93	5	inProcessOfRegistration	Ljava/util/concurrent/CountDownLatch;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   37	104	129	finally
/*     */     //   37	104	145	finally
/*     */     //   129	145	145	finally
/*     */   }
/*     */   
/*     */   public <T extends Extension> T extension(ExtensionId<Object> ext) {
/* 730 */     T t = (T)findExtension(ext);
/* 731 */     if (t == null)
/* 731 */       throw new IllegalArgumentException((new StringBuilder()).append("Trying to get non-registered extension [").append(ext).append("]").toString()); 
/* 732 */     return t;
/*     */   }
/*     */   
/*     */   public boolean hasExtension(ExtensionId<Extension> ext) {
/* 735 */     return !(findExtension(ext) == null);
/*     */   }
/*     */   
/*     */   private void loadExtensions() {
/* 738 */     Util$.MODULE$.immutableSeq(settings().config().getStringList("akka.extensions")).foreach((Function1)new ActorSystemImpl$$anonfun$loadExtensions$1(this));
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$loadExtensions$1 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ActorSystemImpl$$anonfun$loadExtensions$1(ActorSystemImpl $outer) {}
/*     */     
/*     */     public class ActorSystemImpl$$anonfun$loadExtensions$1$$anonfun$2 extends AbstractPartialFunction<Throwable, Try<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String fqcn$1;
/*     */       
/*     */       public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 739 */         Throwable throwable = x1;
/* 739 */         return (B1)this.$outer.akka$actor$ActorSystemImpl$$anonfun$$$outer().dynamicAccess().createInstanceFor(this.fqcn$1, (Seq<Tuple2<Class<?>, Object>>)Nil$.MODULE$, ClassTag$.MODULE$.AnyRef());
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Throwable x1) {
/* 739 */         Throwable throwable = x1;
/* 739 */         return true;
/*     */       }
/*     */       
/*     */       public ActorSystemImpl$$anonfun$loadExtensions$1$$anonfun$2(ActorSystemImpl$$anonfun$loadExtensions$1 $outer, String fqcn$1) {}
/*     */     }
/*     */     
/*     */     public final Object apply(String fqcn) {
/* 740 */       boolean bool = false;
/* 740 */       null;
/* 740 */       Success success = null;
/*     */       Try try_ = this.$outer.dynamicAccess().<T>getObjectFor(fqcn, ClassTag$.MODULE$.AnyRef()).recoverWith((PartialFunction)new ActorSystemImpl$$anonfun$loadExtensions$1$$anonfun$2(this, fqcn));
/* 740 */       if (try_ instanceof Success) {
/* 740 */         bool = true;
/* 740 */         success = (Success)try_;
/* 740 */         Object p = success.value();
/* 740 */         if (p instanceof ExtensionIdProvider) {
/* 740 */           ExtensionIdProvider extensionIdProvider = (ExtensionIdProvider)p;
/* 740 */           return this.$outer.registerExtension((ExtensionId)extensionIdProvider.lookup());
/*     */         } 
/*     */       } 
/*     */       if (bool) {
/* 741 */         Object p = success.value();
/* 741 */         if (p instanceof ExtensionId) {
/* 741 */           ExtensionId<Object> extensionId = (ExtensionId)p;
/* 741 */           return this.$outer.registerExtension(extensionId);
/*     */         } 
/*     */       } 
/*     */       if (bool) {
/* 742 */         this.$outer.log().error("[{}] is not an 'ExtensionIdProvider' or 'ExtensionId', skipping...", fqcn);
/* 742 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/* 743 */         if (try_ instanceof Failure) {
/* 743 */           Failure failure = (Failure)try_;
/* 743 */           Throwable problem = failure.exception();
/* 743 */           this.$outer.log().error(problem, "While trying to load extension [{}], skipping...", fqcn);
/* 743 */           return BoxedUnit.UNIT;
/*     */         } 
/*     */         throw new MatchError(try_);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_7;
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 748 */     return lookupRoot().path().root().address().toString();
/*     */   }
/*     */   
/*     */   public final String akka$actor$ActorSystemImpl$$printNode$1(ActorRef node, String indent) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_3
/*     */     //   2: aload_3
/*     */     //   3: instanceof akka/actor/ActorRefWithCell
/*     */     //   6: ifeq -> 725
/*     */     //   9: aload_3
/*     */     //   10: checkcast akka/actor/ActorRefWithCell
/*     */     //   13: astore #4
/*     */     //   15: aload #4
/*     */     //   17: invokevirtual underlying : ()Lakka/actor/Cell;
/*     */     //   20: astore #6
/*     */     //   22: new scala/collection/mutable/StringBuilder
/*     */     //   25: dup
/*     */     //   26: invokespecial <init> : ()V
/*     */     //   29: aload_2
/*     */     //   30: invokevirtual isEmpty : ()Z
/*     */     //   33: ifeq -> 42
/*     */     //   36: ldc_w '-> '
/*     */     //   39: goto -> 82
/*     */     //   42: new scala/collection/mutable/StringBuilder
/*     */     //   45: dup
/*     */     //   46: invokespecial <init> : ()V
/*     */     //   49: new scala/collection/immutable/StringOps
/*     */     //   52: dup
/*     */     //   53: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   56: aload_2
/*     */     //   57: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   60: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   63: iconst_1
/*     */     //   64: invokevirtual dropRight : (I)Ljava/lang/Object;
/*     */     //   67: checkcast java/lang/String
/*     */     //   70: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   73: ldc_w '⌊-> '
/*     */     //   76: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   79: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   82: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   85: aload_1
/*     */     //   86: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   89: invokeinterface name : ()Ljava/lang/String;
/*     */     //   94: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   97: ldc_w ' '
/*     */     //   100: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   103: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   106: aload_1
/*     */     //   107: invokevirtual simpleName : (Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   110: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   113: ldc_w ' '
/*     */     //   116: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   119: aload #6
/*     */     //   121: astore #7
/*     */     //   123: aload #7
/*     */     //   125: instanceof akka/actor/ActorCell
/*     */     //   128: ifeq -> 165
/*     */     //   131: aload #7
/*     */     //   133: checkcast akka/actor/ActorCell
/*     */     //   136: astore #8
/*     */     //   138: aload #8
/*     */     //   140: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   143: ifnull -> 157
/*     */     //   146: aload #8
/*     */     //   148: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   151: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   154: goto -> 160
/*     */     //   157: ldc_w 'null'
/*     */     //   160: astore #9
/*     */     //   162: goto -> 175
/*     */     //   165: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   168: aload #6
/*     */     //   170: invokevirtual simpleName : (Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   173: astore #9
/*     */     //   175: aload #9
/*     */     //   177: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   180: aload #6
/*     */     //   182: astore #10
/*     */     //   184: aload #10
/*     */     //   186: instanceof akka/actor/ActorCell
/*     */     //   189: ifeq -> 234
/*     */     //   192: aload #10
/*     */     //   194: checkcast akka/actor/ActorCell
/*     */     //   197: astore #11
/*     */     //   199: new scala/collection/mutable/StringBuilder
/*     */     //   202: dup
/*     */     //   203: invokespecial <init> : ()V
/*     */     //   206: ldc_w ' status='
/*     */     //   209: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   212: aload #11
/*     */     //   214: invokevirtual mailbox : ()Lakka/dispatch/Mailbox;
/*     */     //   217: invokevirtual status : ()I
/*     */     //   220: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   223: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   226: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   229: astore #12
/*     */     //   231: goto -> 239
/*     */     //   234: ldc_w ''
/*     */     //   237: astore #12
/*     */     //   239: aload #12
/*     */     //   241: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   244: ldc_w ' '
/*     */     //   247: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   250: aload #6
/*     */     //   252: invokeinterface childrenRefs : ()Lakka/actor/dungeon/ChildrenContainer;
/*     */     //   257: astore #13
/*     */     //   259: aload #13
/*     */     //   261: instanceof akka/actor/dungeon/ChildrenContainer$TerminatingChildrenContainer
/*     */     //   264: ifeq -> 410
/*     */     //   267: aload #13
/*     */     //   269: checkcast akka/actor/dungeon/ChildrenContainer$TerminatingChildrenContainer
/*     */     //   272: astore #14
/*     */     //   274: aload #14
/*     */     //   276: invokevirtual toDie : ()Lscala/collection/immutable/Set;
/*     */     //   279: astore #15
/*     */     //   281: aload #14
/*     */     //   283: invokevirtual reason : ()Lakka/actor/dungeon/ChildrenContainer$SuspendReason;
/*     */     //   286: astore #16
/*     */     //   288: new scala/collection/mutable/StringBuilder
/*     */     //   291: dup
/*     */     //   292: invokespecial <init> : ()V
/*     */     //   295: ldc_w 'Terminating('
/*     */     //   298: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   301: aload #16
/*     */     //   303: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   306: ldc_w ')'
/*     */     //   309: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   312: aload #15
/*     */     //   314: invokeinterface toSeq : ()Lscala/collection/Seq;
/*     */     //   319: getstatic scala/math/Ordering$.MODULE$ : Lscala/math/Ordering$;
/*     */     //   322: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   325: invokevirtual conforms : ()Lscala/Predef$$less$colon$less;
/*     */     //   328: invokevirtual ordered : (Lscala/Function1;)Lscala/math/Ordering;
/*     */     //   331: invokeinterface sorted : (Lscala/math/Ordering;)Ljava/lang/Object;
/*     */     //   336: checkcast scala/collection/TraversableOnce
/*     */     //   339: new scala/collection/mutable/StringBuilder
/*     */     //   342: dup
/*     */     //   343: invokespecial <init> : ()V
/*     */     //   346: ldc_w '\\n'
/*     */     //   349: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   352: aload_2
/*     */     //   353: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   356: ldc_w '   |    toDie: '
/*     */     //   359: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   362: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   365: new scala/collection/mutable/StringBuilder
/*     */     //   368: dup
/*     */     //   369: invokespecial <init> : ()V
/*     */     //   372: ldc_w '\\n'
/*     */     //   375: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   378: aload_2
/*     */     //   379: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   382: ldc_w '   |           '
/*     */     //   385: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   388: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   391: ldc_w ''
/*     */     //   394: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   399: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   402: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   405: astore #17
/*     */     //   407: goto -> 553
/*     */     //   410: getstatic akka/actor/dungeon/ChildrenContainer$TerminatedChildrenContainer$.MODULE$ : Lakka/actor/dungeon/ChildrenContainer$TerminatedChildrenContainer$;
/*     */     //   413: aload #13
/*     */     //   415: astore #18
/*     */     //   417: dup
/*     */     //   418: ifnonnull -> 430
/*     */     //   421: pop
/*     */     //   422: aload #18
/*     */     //   424: ifnull -> 438
/*     */     //   427: goto -> 444
/*     */     //   430: aload #18
/*     */     //   432: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   435: ifeq -> 444
/*     */     //   438: iconst_1
/*     */     //   439: istore #19
/*     */     //   441: goto -> 481
/*     */     //   444: getstatic akka/actor/dungeon/ChildrenContainer$EmptyChildrenContainer$.MODULE$ : Lakka/actor/dungeon/ChildrenContainer$EmptyChildrenContainer$;
/*     */     //   447: aload #13
/*     */     //   449: astore #20
/*     */     //   451: dup
/*     */     //   452: ifnonnull -> 464
/*     */     //   455: pop
/*     */     //   456: aload #20
/*     */     //   458: ifnull -> 472
/*     */     //   461: goto -> 478
/*     */     //   464: aload #20
/*     */     //   466: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   469: ifeq -> 478
/*     */     //   472: iconst_1
/*     */     //   473: istore #19
/*     */     //   475: goto -> 481
/*     */     //   478: iconst_0
/*     */     //   479: istore #19
/*     */     //   481: iload #19
/*     */     //   483: ifeq -> 496
/*     */     //   486: aload #13
/*     */     //   488: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   491: astore #17
/*     */     //   493: goto -> 553
/*     */     //   496: aload #13
/*     */     //   498: instanceof akka/actor/dungeon/ChildrenContainer$NormalChildrenContainer
/*     */     //   501: ifeq -> 543
/*     */     //   504: aload #13
/*     */     //   506: checkcast akka/actor/dungeon/ChildrenContainer$NormalChildrenContainer
/*     */     //   509: astore #21
/*     */     //   511: new scala/collection/mutable/StringBuilder
/*     */     //   514: dup
/*     */     //   515: invokespecial <init> : ()V
/*     */     //   518: aload #21
/*     */     //   520: invokevirtual c : ()Lscala/collection/immutable/TreeMap;
/*     */     //   523: invokevirtual size : ()I
/*     */     //   526: invokevirtual append : (I)Lscala/collection/mutable/StringBuilder;
/*     */     //   529: ldc_w ' children'
/*     */     //   532: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   535: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   538: astore #17
/*     */     //   540: goto -> 553
/*     */     //   543: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   546: aload #13
/*     */     //   548: invokevirtual simpleName : (Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   551: astore #17
/*     */     //   553: aload #17
/*     */     //   555: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   558: aload #6
/*     */     //   560: invokeinterface childrenRefs : ()Lakka/actor/dungeon/ChildrenContainer;
/*     */     //   565: invokeinterface children : ()Lscala/collection/immutable/Iterable;
/*     */     //   570: invokeinterface isEmpty : ()Z
/*     */     //   575: ifeq -> 584
/*     */     //   578: ldc_w ''
/*     */     //   581: goto -> 587
/*     */     //   584: ldc_w '\\n'
/*     */     //   587: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   590: aload #6
/*     */     //   592: invokeinterface childrenRefs : ()Lakka/actor/dungeon/ChildrenContainer;
/*     */     //   597: invokeinterface children : ()Lscala/collection/immutable/Iterable;
/*     */     //   602: invokeinterface toSeq : ()Lscala/collection/Seq;
/*     */     //   607: getstatic scala/math/Ordering$.MODULE$ : Lscala/math/Ordering$;
/*     */     //   610: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   613: invokevirtual conforms : ()Lscala/Predef$$less$colon$less;
/*     */     //   616: invokevirtual ordered : (Lscala/Function1;)Lscala/math/Ordering;
/*     */     //   619: invokeinterface sorted : (Lscala/math/Ordering;)Ljava/lang/Object;
/*     */     //   624: checkcast scala/collection/Seq
/*     */     //   627: astore #22
/*     */     //   629: aload #22
/*     */     //   631: iconst_1
/*     */     //   632: invokeinterface dropRight : (I)Ljava/lang/Object;
/*     */     //   637: checkcast scala/collection/TraversableLike
/*     */     //   640: new akka/actor/ActorSystemImpl$$anonfun$8
/*     */     //   643: dup
/*     */     //   644: aload_0
/*     */     //   645: aload_2
/*     */     //   646: invokespecial <init> : (Lakka/actor/ActorSystemImpl;Ljava/lang/String;)V
/*     */     //   649: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */     //   652: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   655: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   660: checkcast scala/collection/Seq
/*     */     //   663: astore #23
/*     */     //   665: aload #23
/*     */     //   667: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */     //   670: aload #22
/*     */     //   672: invokeinterface lastOption : ()Lscala/Option;
/*     */     //   677: new akka/actor/ActorSystemImpl$$anonfun$akka$actor$ActorSystemImpl$$printNode$1$1
/*     */     //   680: dup
/*     */     //   681: aload_0
/*     */     //   682: aload_2
/*     */     //   683: invokespecial <init> : (Lakka/actor/ActorSystemImpl;Ljava/lang/String;)V
/*     */     //   686: invokevirtual map : (Lscala/Function1;)Lscala/Option;
/*     */     //   689: invokevirtual option2Iterable : (Lscala/Option;)Lscala/collection/Iterable;
/*     */     //   692: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */     //   695: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   698: invokeinterface $plus$plus : (Lscala/collection/GenTraversableOnce;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   703: checkcast scala/collection/TraversableOnce
/*     */     //   706: ldc_w '\\n'
/*     */     //   709: invokeinterface mkString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   714: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   717: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   720: astore #5
/*     */     //   722: goto -> 769
/*     */     //   725: new scala/collection/mutable/StringBuilder
/*     */     //   728: dup
/*     */     //   729: invokespecial <init> : ()V
/*     */     //   732: aload_2
/*     */     //   733: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   736: aload_1
/*     */     //   737: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   740: invokeinterface name : ()Ljava/lang/String;
/*     */     //   745: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   748: ldc_w ' '
/*     */     //   751: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   754: getstatic akka/event/Logging$.MODULE$ : Lakka/event/Logging$;
/*     */     //   757: aload_1
/*     */     //   758: invokevirtual simpleName : (Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   761: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   764: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   767: astore #5
/*     */     //   769: aload #5
/*     */     //   771: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #752	-> 0
/*     */     //   #753	-> 2
/*     */     //   #754	-> 15
/*     */     //   #773	-> 22
/*     */     //   #755	-> 29
/*     */     //   #756	-> 85
/*     */     //   #757	-> 119
/*     */     //   #758	-> 123
/*     */     //   #759	-> 165
/*     */     //   #757	-> 175
/*     */     //   #761	-> 180
/*     */     //   #762	-> 184
/*     */     //   #763	-> 234
/*     */     //   #761	-> 239
/*     */     //   #765	-> 244
/*     */     //   #766	-> 259
/*     */     //   #767	-> 288
/*     */     //   #768	-> 312
/*     */     //   #767	-> 402
/*     */     //   #769	-> 410
/*     */     //   #770	-> 496
/*     */     //   #771	-> 543
/*     */     //   #765	-> 553
/*     */     //   #773	-> 558
/*     */     //   #775	-> 590
/*     */     //   #776	-> 629
/*     */     //   #777	-> 665
/*     */     //   #778	-> 706
/*     */     //   #773	-> 717
/*     */     //   #753	-> 720
/*     */     //   #780	-> 725
/*     */     //   #752	-> 769
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	772	0	this	Lakka/actor/ActorSystemImpl;
/*     */     //   0	772	1	node	Lakka/actor/ActorRef;
/*     */     //   0	772	2	indent	Ljava/lang/String;
/*     */     //   22	698	6	cell	Lakka/actor/Cell;
/*     */     //   281	491	15	toDie	Lscala/collection/immutable/Set;
/*     */     //   288	484	16	reason	Lakka/actor/dungeon/ChildrenContainer$SuspendReason;
/*     */     //   629	74	22	children	Lscala/collection/Seq;
/*     */     //   665	38	23	bulk	Lscala/collection/Seq;
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$8 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String indent$1;
/*     */     
/*     */     public final String apply(ActorRef x$2) {
/* 776 */       return this.$outer.akka$actor$ActorSystemImpl$$printNode$1(x$2, (new StringBuilder()).append(this.indent$1).append("   |").toString());
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$8(ActorSystemImpl $outer, String indent$1) {}
/*     */   }
/*     */   
/*     */   public class ActorSystemImpl$$anonfun$akka$actor$ActorSystemImpl$$printNode$1$1 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String indent$1;
/*     */     
/*     */     public final String apply(ActorRef x$3) {
/* 777 */       return this.$outer.akka$actor$ActorSystemImpl$$printNode$1(x$3, (new StringBuilder()).append(this.indent$1).append("    ").toString());
/*     */     }
/*     */     
/*     */     public ActorSystemImpl$$anonfun$akka$actor$ActorSystemImpl$$printNode$1$1(ActorSystemImpl $outer, String indent$1) {}
/*     */   }
/*     */   
/*     */   public String printTree() {
/* 783 */     return akka$actor$ActorSystemImpl$$printNode$1(lookupRoot(), "");
/*     */   }
/*     */   
/*     */   public class TerminationCallbacks implements Runnable, Awaitable<BoxedUnit> {
/*     */     private final ReentrantGuard lock;
/*     */     
/*     */     private List<Runnable> akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks;
/*     */     
/*     */     private final CountDownLatch akka$actor$ActorSystemImpl$TerminationCallbacks$$latch;
/*     */     
/*     */     public TerminationCallbacks(ActorSystemImpl $outer) {
/* 787 */       this.lock = new ReentrantGuard();
/* 789 */       lock().withGuard((Function0)new ActorSystemImpl$TerminationCallbacks$$anonfun$1(this));
/* 791 */       this.akka$actor$ActorSystemImpl$TerminationCallbacks$$latch = new CountDownLatch(1);
/*     */     }
/*     */     
/*     */     private ReentrantGuard lock() {
/*     */       return this.lock;
/*     */     }
/*     */     
/*     */     public List<Runnable> akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks() {
/*     */       return this.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks;
/*     */     }
/*     */     
/*     */     public void akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks_$eq(List<Runnable> x$1) {
/*     */       this.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks = x$1;
/*     */     }
/*     */     
/*     */     public class ActorSystemImpl$TerminationCallbacks$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks_$eq((List<Runnable>)Nil$.MODULE$);
/*     */       }
/*     */       
/*     */       public ActorSystemImpl$TerminationCallbacks$$anonfun$1(ActorSystemImpl.TerminationCallbacks $outer) {}
/*     */     }
/*     */     
/*     */     public CountDownLatch akka$actor$ActorSystemImpl$TerminationCallbacks$$latch() {
/* 791 */       return this.akka$actor$ActorSystemImpl$TerminationCallbacks$$latch;
/*     */     }
/*     */     
/*     */     public final void add(Runnable callback) {
/* 794 */       long l = akka$actor$ActorSystemImpl$TerminationCallbacks$$latch().getCount();
/* 795 */       if (0L == l)
/* 795 */         throw new RejectedExecutionException("Must be called prior to system shutdown."); 
/* 796 */       BoxedUnit boxedUnit = (BoxedUnit)lock().withGuard(
/* 797 */           (Function0)new ActorSystemImpl$TerminationCallbacks$$anonfun$add$1(this, callback));
/*     */     }
/*     */     
/*     */     public class ActorSystemImpl$TerminationCallbacks$$anonfun$add$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Runnable callback$1;
/*     */       
/*     */       public final void apply() {
/* 797 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/* 797 */         if (this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$latch().getCount() == 0L)
/* 797 */           throw new RejectedExecutionException("Must be called prior to system shutdown."); 
/* 798 */         this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks_$eq(this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks().$colon$colon(this.callback$1));
/*     */       }
/*     */       
/*     */       public ActorSystemImpl$TerminationCallbacks$$anonfun$add$1(ActorSystemImpl.TerminationCallbacks $outer, Runnable callback$1) {}
/*     */     }
/*     */     
/*     */     public final void run() {
/* 803 */       lock().withGuard((Function0)new ActorSystemImpl$TerminationCallbacks$$anonfun$run$1(this));
/*     */     }
/*     */     
/*     */     public class ActorSystemImpl$TerminationCallbacks$$anonfun$run$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply() {
/* 803 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ActorSystemImpl$TerminationCallbacks$$anonfun$run$1(ActorSystemImpl.TerminationCallbacks $outer) {}
/*     */       
/*     */       private final List runNext$1(List c) {
/*     */         // Byte code:
/*     */         //   0: aload_1
/*     */         //   1: astore_3
/*     */         //   2: goto -> 77
/*     */         //   5: astore #9
/*     */         //   7: aload #9
/*     */         //   9: astore #10
/*     */         //   11: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */         //   14: aload #10
/*     */         //   16: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */         //   19: astore #11
/*     */         //   21: aload #11
/*     */         //   23: invokevirtual isEmpty : ()Z
/*     */         //   26: ifeq -> 32
/*     */         //   29: aload #9
/*     */         //   31: athrow
/*     */         //   32: aload #11
/*     */         //   34: invokevirtual get : ()Ljava/lang/Object;
/*     */         //   37: checkcast java/lang/Throwable
/*     */         //   40: astore #12
/*     */         //   42: aload_0
/*     */         //   43: getfield $outer : Lakka/actor/ActorSystemImpl$TerminationCallbacks;
/*     */         //   46: invokevirtual akka$actor$ActorSystemImpl$TerminationCallbacks$$$outer : ()Lakka/actor/ActorSystemImpl;
/*     */         //   49: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */         //   52: aload #12
/*     */         //   54: ldc 'Failed to run termination callback, due to [{}]'
/*     */         //   56: aload #12
/*     */         //   58: invokevirtual getMessage : ()Ljava/lang/String;
/*     */         //   61: invokeinterface error : (Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/Object;)V
/*     */         //   66: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   69: astore #13
/*     */         //   71: aload #8
/*     */         //   73: astore_1
/*     */         //   74: goto -> 0
/*     */         //   77: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */         //   80: aload_3
/*     */         //   81: astore #4
/*     */         //   83: dup
/*     */         //   84: ifnonnull -> 96
/*     */         //   87: pop
/*     */         //   88: aload #4
/*     */         //   90: ifnull -> 104
/*     */         //   93: goto -> 112
/*     */         //   96: aload #4
/*     */         //   98: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   101: ifeq -> 112
/*     */         //   104: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */         //   107: astore #5
/*     */         //   109: aload #5
/*     */         //   111: areturn
/*     */         //   112: aload_3
/*     */         //   113: instanceof scala/collection/immutable/$colon$colon
/*     */         //   116: ifeq -> 152
/*     */         //   119: aload_3
/*     */         //   120: checkcast scala/collection/immutable/$colon$colon
/*     */         //   123: astore #6
/*     */         //   125: aload #6
/*     */         //   127: invokevirtual hd$1 : ()Ljava/lang/Object;
/*     */         //   130: checkcast java/lang/Runnable
/*     */         //   133: astore #7
/*     */         //   135: aload #6
/*     */         //   137: invokevirtual tl$1 : ()Lscala/collection/immutable/List;
/*     */         //   140: astore #8
/*     */         //   142: aload #7
/*     */         //   144: invokeinterface run : ()V
/*     */         //   149: goto -> 71
/*     */         //   152: new scala/MatchError
/*     */         //   155: dup
/*     */         //   156: aload_3
/*     */         //   157: invokespecial <init> : (Ljava/lang/Object;)V
/*     */         //   160: athrow
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #804	-> 0
/*     */         //   #807	-> 5
/*     */         //   #804	-> 32
/*     */         //   #807	-> 34
/*     */         //   #808	-> 71
/*     */         //   #805	-> 77
/*     */         //   #804	-> 109
/*     */         //   #806	-> 112
/*     */         //   #807	-> 142
/*     */         //   #804	-> 152
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	161	0	this	Lakka/actor/ActorSystemImpl$TerminationCallbacks$$anonfun$run$1;
/*     */         //   0	161	1	c	Lscala/collection/immutable/List;
/*     */         //   42	119	12	e	Ljava/lang/Throwable;
/*     */         //   135	26	7	callback	Ljava/lang/Runnable;
/*     */         //   142	19	8	rest	Lscala/collection/immutable/List;
/*     */         // Exception table:
/*     */         //   from	to	target	type
/*     */         //   142	152	5	finally
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         try {
/* 810 */           this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks_$eq(runNext$1(this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$callbacks()));
/*     */           return;
/*     */         } finally {
/* 810 */           this.$outer.akka$actor$ActorSystemImpl$TerminationCallbacks$$latch().countDown();
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     public final TerminationCallbacks ready(Duration atMost, CanAwait permit) {
/* 814 */       if (atMost.isFinite()) {
/* 815 */         if (!akka$actor$ActorSystemImpl$TerminationCallbacks$$latch().await(atMost.length(), atMost.unit()))
/* 816 */           throw new TimeoutException((new StringOps(Predef$.MODULE$.augmentString("Await termination timed out after [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { atMost.toString() }))); 
/*     */       } else {
/* 817 */         akka$actor$ActorSystemImpl$TerminationCallbacks$$latch().await();
/*     */       } 
/* 819 */       return this;
/*     */     }
/*     */     
/*     */     public final void result(Duration atMost, CanAwait permit) {
/* 822 */       ready(atMost, permit);
/*     */     }
/*     */     
/*     */     public final boolean isTerminated() {
/* 824 */       return (akka$actor$ActorSystemImpl$TerminationCallbacks$$latch().getCount() == 0L);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorSystemImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */