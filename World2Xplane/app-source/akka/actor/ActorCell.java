/*     */ package akka.actor;
/*     */ 
/*     */ import akka.actor.dungeon.Children;
/*     */ import akka.actor.dungeon.ChildrenContainer;
/*     */ import akka.actor.dungeon.DeathWatch;
/*     */ import akka.actor.dungeon.Dispatch;
/*     */ import akka.actor.dungeon.FaultHandling;
/*     */ import akka.actor.dungeon.ReceiveTimeout;
/*     */ import akka.dispatch.Envelope;
/*     */ import akka.dispatch.Envelope$;
/*     */ import akka.dispatch.Mailbox;
/*     */ import akka.dispatch.MailboxType;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import akka.dispatch.sysmsg.EarliestFirstSystemMessageList$;
/*     */ import akka.dispatch.sysmsg.Failed;
/*     */ import akka.dispatch.sysmsg.LatestFirstSystemMessageList$;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.SystemMessageList$;
/*     */ import akka.event.Logging;
/*     */ import akka.event.Logging$Error$;
/*     */ import akka.japi.Procedure;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Field;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.ExecutionContextExecutor;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.StringAdd$;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\ruAB\001\003\021\003!a!A\005BGR|'oQ3mY*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b!\t9\001\"D\001\003\r\031I!\001#\001\005\025\tI\021i\031;pe\016+G\016\\\n\003\021-\001\"\001D\b\016\0035Q\021AD\001\006g\016\fG.Y\005\003!5\021a!\0218z%\0264\007\"\002\n\t\t\003!\022A\002\037j]&$hh\001\001\025\003\031AqA\006\005C\002\023\005q#\001\007d_:$X\r\037;Ti\006\0347.F\001\031!\rIb\004I\007\0025)\0211\004H\001\005Y\006twMC\001\036\003\021Q\027M^1\n\005}Q\"a\003+ie\026\fG\rT8dC2\0042!I\025-\035\t\021sE\004\002$M5\tAE\003\002&'\0051AH]8pizJ\021AD\005\003Q5\tq\001]1dW\006<W-\003\002+W\t!A*[:u\025\tAS\002\005\002\b[%\021aF\001\002\r\003\016$xN]\"p]R,\007\020\036\005\007a!\001\013\021\002\r\002\033\r|g\016^3yiN#\030mY6!\021\035\021\004B1A\005\006M\n\001#Z7qif\034\025M\\2fY2\f'\r\\3\026\003Q\002\"aB\033\n\005Y\022!aC\"b]\016,G\016\\1cY\026Da\001\017\005!\002\033!\024!E3naRL8)\0318dK2d\027M\0317fA!9!\b\003b\001\n\013Y\024AE3naRL()\0325bm&|'o\025;bG.,\022\001\020\t\004C%j\004C\001 B\035\t9q(\003\002A\005\005)\021i\031;pe&\021!i\021\002\b%\026\034W-\033<f\025\t\001%\001\003\004F\021\001\006i\001P\001\024K6\004H/\037\"fQ\0064\030n\034:Ti\006\0347\016\t\005\b\017\"\021\r\021\"\002I\003A)W\016\035;z\003\016$xN\035*fMN+G/F\001J!\rQU\n\025\b\003\031-K!\001T\007\002\rA\023X\rZ3g\023\tquJA\002TKRT!\001T\007\021\005\035\t\026B\001*\003\005!\t5\r^8s%\0264\007B\002+\tA\0035\021*A\tf[B$\0300Q2u_J\024VMZ*fi\002BqA\026\005C\002\023\025q+A\buKJl\027N\\1uK\022\004&o\0349t+\005A\006CA\004Z\023\tQ&AA\003Qe>\0048\017\003\004]\021\001\006i\001W\001\021i\026\024X.\0338bi\026$\007K]8qg\002BqA\030\005C\002\023\025q,\001\007v]\022,g-\0338fIVKG-F\001a\037\005\tW$\001\001\t\r\rD\001\025!\004a\0035)h\016Z3gS:,G-V5eA!)Q\r\003C\003M\0061a.Z<VS\022$\022a\032\t\003\031!L!![\007\003\007%sG\017\013\002eWB\021An\\\007\002[*\021a.D\001\013C:tw\016^1uS>t\027B\0019n\005\035!\030-\0337sK\016DQA\035\005\005\006M\fqb\0359mSRt\025-\\3B]\022,\026\016\032\013\003ij\004B\001D;xO&\021a/\004\002\007)V\004H.\032\032\021\005)C\030BA=P\005\031\031FO]5oO\")10\035a\001o\006!a.Y7f\021\035i\bB1A\005\006}\013A\002R3gCVdGo\025;bi\026Daa \005!\002\033\001\027!\004#fM\006,H\016^*uCR,\007\005C\005\002\004!\021\r\021\"\002\002\006\005q1+^:qK:$W\rZ*uCR,WCAA\004\037\t\tI!H\001\002\021!\ti\001\003Q\001\016\005\035\021aD*vgB,g\016Z3e'R\fG/\032\021\t\023\005E\001B1A\005\006\005M\021!H*vgB,g\016Z3e/\006LGOR8s\007\"LG\016\032:f]N#\030\r^3\026\005\005UqBAA\f;\005\021\001\002CA\016\021\001\006i!!\006\002=M+8\017]3oI\026$w+Y5u\r>\0248\t[5mIJ,gn\025;bi\026\004cAB\005\003\001\021\tybE\n\002\036-\t\t#a\n\002.\005M\022qHA#\003\027\n\t\006E\002\b\003GI1!!\n\003\005M)f\016^=qK\022\f5\r^8s\007>tG/\032=u!\r9\021\021F\005\004\003W\021!\001F!cgR\024\030m\031;BGR|'oQ8oi\026DH\017E\002\b\003_I1!!\r\003\005\021\031U\r\0347\021\t\005U\0221H\007\003\003oQ1!!\017\003\003\035!WO\\4f_:LA!!\020\0028\tq!+Z2fSZ,G+[7f_V$\b\003BA\033\003\003JA!a\021\0028\tA1\t[5mIJ,g\016\005\003\0026\005\035\023\002BA%\003o\021\001\002R5ta\006$8\r\033\t\005\003k\ti%\003\003\002P\005]\"A\003#fCRDw+\031;dQB!\021QGA*\023\021\t)&a\016\003\033\031\013W\017\034;IC:$G.\0338h\021-\tI&!\b\003\006\004%\t!a\027\002\rML8\017^3n+\t\ti\006E\002\b\003?J1!!\031\003\005=\t5\r^8s'f\034H/Z7J[Bd\007bCA3\003;\021\t\021)A\005\003;\nqa]=ti\026l\007\005C\006\002j\005u!Q1A\005\002\005-\024\001B:fY\032,\"!!\034\021\007\035\ty'C\002\002r\t\021\001#\0238uKJt\027\r\\!di>\024(+\0324\t\027\005U\024Q\004B\001B\003%\021QN\001\006g\026dg\r\t\005\013\003s\niB!b\001\n\0139\026!\0029s_B\034\bBCA?\003;\021\t\021)A\0071\0061\001O]8qg\002B1\"!!\002\036\t\025\r\021\"\001\002\004\006QA-[:qCR\034\007.\032:\026\005\005\025\005\003BAD\003\033k!!!#\013\007\005-E!\001\005eSN\004\030\r^2i\023\021\ty)!#\003#5+7o]1hK\022K7\017]1uG\",'\017C\006\002\024\006u!\021!Q\001\n\005\025\025a\0033jgB\fGo\0315fe\002B1\"a&\002\036\t\025\r\021\"\001\002l\0051\001/\031:f]RD1\"a'\002\036\t\005\t\025!\003\002n\0059\001/\031:f]R\004\003b\002\n\002\036\021\005\021q\024\013\r\003C\013\031+!*\002(\006%\0261\026\t\004\017\005u\001\002CA-\003;\003\r!!\030\t\021\005%\024Q\024a\001\003[Bq!!\037\002\036\002\007\001\f\003\005\002\002\006u\005\031AAC\021!\t9*!(A\002\0055\004\002CAX\003;!)!!-\002\017%\034Hj\\2bYV\021\0211\027\t\004\031\005U\026bAA\\\033\t9!i\\8mK\006t\007\002CA^\003;!)!a\027\002\025ML8\017^3n\0236\004H\016\003\005\002@\006uAQCA6\003!9W/\031:eS\006t\007\002CAb\003;!)\"a\033\002\0251|wn[;q%>|G\017\003\005\002H\006uAQAAe\003!\001(o\034<jI\026\024XCAAf!\r9\021QZ\005\004\003\037\024!\001E!di>\024(+\0324Qe>4\030\016Z3s\021!\t\031.!\b\005\022\005U\027aA;jIV\tq\r\003\007\002Z\006u\001\031!A!B\023\tY.\001\004`C\016$xN\035\t\004\017\005u\027bAAp\005\t)\021i\031;pe\"91!!\b\005\002\005\rXCAAn\021!\t9/!\b\005\022\005%\030!C1di>\024x\fJ3r)\021\tY/!=\021\0071\ti/C\002\002p6\021A!\0268ji\"A\0211_As\001\004\tY.A\001b\0211\t90!\bA\002\003\007I\021AA}\0039\031WO\035:f]RlUm]:bO\026,\"!a?\021\t\005\035\025Q`\005\005\003\fII\001\005F]Z,Gn\0349f\0211\021\031!!\bA\002\003\007I\021\001B\003\003I\031WO\035:f]RlUm]:bO\026|F%Z9\025\t\005-(q\001\005\013\005\023\021\t!!AA\002\005m\030a\001=%c!I!QBA\017A\003&\0211`\001\020GV\024(/\0328u\033\026\0348/Y4fA!I!\021CA\017\001\004%IaO\001\016E\026D\027M^5peN#\030mY6\t\025\tU\021Q\004a\001\n\023\0219\"A\tcK\"\fg/[8s'R\f7m[0%KF$B!a;\003\032!I!\021\002B\n\003\003\005\r\001\020\005\t\005;\ti\002)Q\005y\005q!-\0325bm&|'o\025;bG.\004\003\"\003B\021\003;\001\013\025\002B\022\003-\031\030p]7tON#\030m\0355\021\t\t\025\"1F\007\003\005OQAA!\013\002\n\00611/_:ng\036LAA!\f\003(\taB*\031;fgR4\025N]:u'f\034H/Z7NKN\034\030mZ3MSN$\b\002\003B\031\003;!\tBa\r\002\013M$\030m\0355\025\t\005-(Q\007\005\t\005o\021y\0031\001\003:\005\031Qn]4\021\t\t\025\"1H\005\005\005{\0219CA\007TsN$X-\\'fgN\fw-\032\005\t\005\003\ni\002\"\003\003D\005QQO\\:uCND\027\t\0347\025\005\t\r\002\002\003B$\003;!)A!\023\002\031ML8\017^3n\023:4xn[3\025\t\005-(1\n\005\t\005\033\022)\0051\001\003:\0059Q.Z:tC\036,\007\002\003B)\003;!)Aa\025\002\r%tgo\\6f)\021\tYO!\026\t\021\t]#q\na\001\003w\fQ\"\\3tg\006<W\rS1oI2,\007\002\003B.\003;!\tA!\030\002%\005,Ho\034*fG\026Lg/Z'fgN\fw-\032\013\005\003W\024y\006\003\005\0038\te\003\031AA~\021!\021\031'!\b\005\n\t\025\024\001\005:fG\026Lg/Z*fY\026\034G/[8o)\021\tYOa\032\t\021\t%$\021\ra\001\005W\n1a]3m!\r9!QN\005\004\005_\022!!F!di>\0248+\0327fGRLwN\\'fgN\fw-\032\005\t\005g\ni\002\"\002\003v\005q!/Z2fSZ,W*Z:tC\036,G\003BAv\005oB\001Ba\016\003r\001\007!\021\020\t\004\031\tm\024b\001B?\033\t\031\021I\\=\t\021\t\005\025Q\004C\003\005\007\013aa]3oI\026\024H#\001)\t\021\t\035\025Q\004C\001\005\023\013aAY3d_6,GCBAv\005\027\023y\tC\004\003\016\n\025\005\031A\037\002\021\t,\007.\031<j_JD!B!%\003\006B\005\t\031AAZ\003)!\027n]2be\022|E\016\032\005\t\005\017\013i\002\"\001\003\026R!\0211\036BL\021!\021iIa%A\002\te\005C\002BN\005C\023I(\004\002\003\036*\031!q\024\003\002\t)\f\007/[\005\005\005G\023iJA\005Qe>\034W\rZ;sK\"A!qQA\017\t\003\0219\013\006\004\002l\n%&1\026\005\t\005\033\023)\0131\001\003\032\"A!\021\023BS\001\004\t\031\f\003\005\0030\006uA\021\001BY\003!)hNY3d_6,GCAAv\021!\021),!\b\005\022\t]\026\001\0038fo\006\033Go\034:\025\005\005m\007\002\003B^\003;!\tB!0\002\r\r\024X-\031;f)\021\tYOa0\t\021\t\005'\021\030a\001\005\007\fqAZ1jYV\024X\rE\003\r\005\013\024I-C\002\003H6\021aa\0249uS>t\007cA\004\003L&\031!Q\032\002\0039\005\033Go\034:J]&$\030.\0317ju\006$\030n\0348Fq\016,\007\017^5p]\"A!\021[A\017\t\023\021\031.A\005tkB,'O^5tKR1\0211\036Bk\0053DqAa6\003P\002\007\001+A\003dQ&dG\r\003\005\003\\\n=\007\031AAZ\003\025\t7/\0378d\021!\021y.!\b\005\022\t\005\030a\0045b]\022dWmU;qKJ4\030n]3\025\r\005-(1\035Bs\021\035\0219N!8A\002AC\001Ba7\003^\002\007\0211\027\005\t\005S\fi\002\"\004\003l\006\tBn\\8lkB\fe\016Z*fi\032KW\r\0343\025\025\005M&Q^B\005\007\033\031y\001\003\005\003p\n\035\b\031\001By\003\025\031G.\031>{a\021\021\031P!@\021\013)\023)P!?\n\007\t]xJA\003DY\006\0348\017\005\003\003|\nuH\002\001\003\r\005\024i/!A\001\002\013\0051\021\001\002\004?\022\n\024\003BB\002\005s\0022\001DB\003\023\r\0319!\004\002\b\035>$\b.\0338h\021\035\031YAa:A\002-\t\001\"\0338ti\006t7-\032\005\007w\n\035\b\031A<\t\021\rE!q\035a\001\005s\nQA^1mk\026D3Aa:l\021!\0319\"!\b\005\026\re\021\001F2mK\006\024\030i\031;pe\016+G\016\034$jK2$7\017\006\003\002l\016m\001\002CB\017\007+\001\r!!)\002\t\r,G\016\034\005\t\007C\ti\002\"\006\004$\005\0012\r\\3be\006\033Go\034:GS\026dGm\035\013\005\003W\034)\003\003\005\004(\r}\001\031AAn\0035\t7\r^8s\023:\034H/\0318dK\"A11FA\017\t+\031i#\001\btKR\f5\r^8s\r&,G\016Z:\025\021\005-8qFB\031\007kA\001ba\n\004*\001\007\0211\034\005\b\007g\031I\0031\001-\003\035\031wN\034;fqRDq!!\033\004*\001\007\001\013\003\005\004:\005uAQCB\036\003\035\001XO\0317jg\"$B!a;\004>!A1qHB\034\001\004\031\t%A\001f!\021\031\031e!\026\017\t\r\0253q\n\b\005\007\017\032YED\002$\007\023J\021!B\005\004\007\033\"\021!B3wK:$\030\002BB)\007'\nq\001T8hO&twMC\002\004N\021IAaa\026\004Z\tAAj\\4Fm\026tGO\003\003\004R\rM\003\002\003Bx\003;!)b!\030\025\t\r}3\021\016\031\005\007C\032)\007E\003K\005k\034\031\007\005\003\003|\016\025D\001DB4\0077\n\t\021!A\003\002\r\005!aA0%e!911NB.\001\004Y\021!A8\t\025\r=\024QDI\001\n\003\031\t(\001\tcK\016|W.\032\023eK\032\fW\017\034;%eU\02111\017\026\005\003g\033)h\013\002\004xA!1\021PB@\033\t\031YHC\002\004~5\f\021\"\0368dQ\026\0347.\0323\n\t\r\00551\020\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007")
/*     */ public class ActorCell implements UntypedActorContext, AbstractActorContext, Cell, ReceiveTimeout, Children, Dispatch, DeathWatch, FaultHandling {
/*     */   private final ActorSystemImpl system;
/*     */   
/*     */   private final InternalActorRef self;
/*     */   
/*     */   private final Props props;
/*     */   
/*     */   private final MessageDispatcher dispatcher;
/*     */   
/*     */   private final InternalActorRef parent;
/*     */   
/*     */   private Actor _actor;
/*     */   
/*     */   private Envelope currentMessage;
/*     */   
/*     */   private List<PartialFunction<Object, BoxedUnit>> behaviorStack;
/*     */   
/*     */   private SystemMessage sysmsgStash;
/*     */   
/*     */   private ActorRef akka$actor$dungeon$FaultHandling$$_failed;
/*     */   
/*     */   private Set<ActorRef> akka$actor$dungeon$DeathWatch$$watching;
/*     */   
/*     */   private Set<ActorRef> akka$actor$dungeon$DeathWatch$$watchedBy;
/*     */   
/*     */   private Set<ActorRef> akka$actor$dungeon$DeathWatch$$terminatedQueued;
/*     */   
/*     */   private volatile Mailbox akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly;
/*     */   
/*     */   private volatile ChildrenContainer akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly;
/*     */   
/*     */   private volatile long akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly;
/*     */   
/*     */   private Tuple2<Duration, Cancellable> akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData;
/*     */   
/*     */   public static class $anon$1 extends ThreadLocal<List<ActorContext>> {
/*     */     public List<ActorContext> initialValue() {
/* 326 */       return (List<ActorContext>)Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$2 implements Cancellable {
/*     */     public boolean isCancelled() {
/* 330 */       return false;
/*     */     }
/*     */     
/*     */     public boolean cancel() {
/* 331 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction0<Actor> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Actor apply() {
/* 338 */       throw new IllegalActorStateException("This Actor has been terminated");
/*     */     }
/*     */   }
/*     */   
/*     */   public ActorRef akka$actor$dungeon$FaultHandling$$_failed() {
/* 369 */     return this.akka$actor$dungeon$FaultHandling$$_failed;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$FaultHandling$$_failed_$eq(ActorRef x$1) {
/* 369 */     this.akka$actor$dungeon$FaultHandling$$_failed = x$1;
/*     */   }
/*     */   
/*     */   public void faultRecreate(Throwable cause) {
/* 369 */     FaultHandling.class.faultRecreate(this, cause);
/*     */   }
/*     */   
/*     */   public void faultSuspend() {
/* 369 */     FaultHandling.class.faultSuspend(this);
/*     */   }
/*     */   
/*     */   public void faultResume(Throwable causedByFailure) {
/* 369 */     FaultHandling.class.faultResume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public void faultCreate() {
/* 369 */     FaultHandling.class.faultCreate(this);
/*     */   }
/*     */   
/*     */   public void terminate() {
/* 369 */     FaultHandling.class.terminate(this);
/*     */   }
/*     */   
/*     */   public final void handleInvokeFailure(Iterable childrenNotToSuspend, Throwable t) {
/* 369 */     FaultHandling.class.handleInvokeFailure(this, childrenNotToSuspend, t);
/*     */   }
/*     */   
/*     */   public final void handleFailure(Failed f) {
/* 369 */     FaultHandling.class.handleFailure(this, f);
/*     */   }
/*     */   
/*     */   public final void handleChildTerminated(ActorRef child) {
/* 369 */     FaultHandling.class.handleChildTerminated(this, child);
/*     */   }
/*     */   
/*     */   public final PartialFunction<Throwable, BoxedUnit> handleNonFatalOrInterruptedException(Function1 thunk) {
/* 369 */     return FaultHandling.class.handleNonFatalOrInterruptedException(this, thunk);
/*     */   }
/*     */   
/*     */   public Set<ActorRef> akka$actor$dungeon$DeathWatch$$watching() {
/* 369 */     return this.akka$actor$dungeon$DeathWatch$$watching;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$DeathWatch$$watching_$eq(Set<ActorRef> x$1) {
/* 369 */     this.akka$actor$dungeon$DeathWatch$$watching = x$1;
/*     */   }
/*     */   
/*     */   public Set<ActorRef> akka$actor$dungeon$DeathWatch$$watchedBy() {
/* 369 */     return this.akka$actor$dungeon$DeathWatch$$watchedBy;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$DeathWatch$$watchedBy_$eq(Set<ActorRef> x$1) {
/* 369 */     this.akka$actor$dungeon$DeathWatch$$watchedBy = x$1;
/*     */   }
/*     */   
/*     */   public Set<ActorRef> akka$actor$dungeon$DeathWatch$$terminatedQueued() {
/* 369 */     return this.akka$actor$dungeon$DeathWatch$$terminatedQueued;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$DeathWatch$$terminatedQueued_$eq(Set<ActorRef> x$1) {
/* 369 */     this.akka$actor$dungeon$DeathWatch$$terminatedQueued = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef watch(ActorRef subject) {
/* 369 */     return DeathWatch.class.watch(this, subject);
/*     */   }
/*     */   
/*     */   public final ActorRef unwatch(ActorRef subject) {
/* 369 */     return DeathWatch.class.unwatch(this, subject);
/*     */   }
/*     */   
/*     */   public void receivedTerminated(Terminated t) {
/* 369 */     DeathWatch.class.receivedTerminated(this, t);
/*     */   }
/*     */   
/*     */   public void watchedActorTerminated(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/* 369 */     DeathWatch.class.watchedActorTerminated(this, actor, existenceConfirmed, addressTerminated);
/*     */   }
/*     */   
/*     */   public void terminatedQueuedFor(ActorRef subject) {
/* 369 */     DeathWatch.class.terminatedQueuedFor(this, subject);
/*     */   }
/*     */   
/*     */   public void tellWatchersWeDied() {
/* 369 */     DeathWatch.class.tellWatchersWeDied(this);
/*     */   }
/*     */   
/*     */   public void unwatchWatchedActors(Actor actor) {
/* 369 */     DeathWatch.class.unwatchWatchedActors(this, actor);
/*     */   }
/*     */   
/*     */   public void addWatcher(ActorRef watchee, ActorRef watcher) {
/* 369 */     DeathWatch.class.addWatcher(this, watchee, watcher);
/*     */   }
/*     */   
/*     */   public void remWatcher(ActorRef watchee, ActorRef watcher) {
/* 369 */     DeathWatch.class.remWatcher(this, watchee, watcher);
/*     */   }
/*     */   
/*     */   public void addressTerminated(Address address) {
/* 369 */     DeathWatch.class.addressTerminated(this, address);
/*     */   }
/*     */   
/*     */   public Mailbox akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly() {
/* 369 */     return this.akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly_$eq(Mailbox x$1) {
/* 369 */     this.akka$actor$dungeon$Dispatch$$_mailboxDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   public final Mailbox mailbox() {
/* 369 */     return Dispatch.class.mailbox(this);
/*     */   }
/*     */   
/*     */   public final Mailbox swapMailbox(Mailbox newMailbox) {
/* 369 */     return Dispatch.class.swapMailbox(this, newMailbox);
/*     */   }
/*     */   
/*     */   public final boolean hasMessages() {
/* 369 */     return Dispatch.class.hasMessages(this);
/*     */   }
/*     */   
/*     */   public final int numberOfMessages() {
/* 369 */     return Dispatch.class.numberOfMessages(this);
/*     */   }
/*     */   
/*     */   public final boolean isTerminated() {
/* 369 */     return Dispatch.class.isTerminated(this);
/*     */   }
/*     */   
/*     */   public final ActorCell init(boolean sendSupervise, MailboxType mailboxType) {
/* 369 */     return Dispatch.class.init(this, sendSupervise, mailboxType);
/*     */   }
/*     */   
/*     */   public ActorCell start() {
/* 369 */     return Dispatch.class.start(this);
/*     */   }
/*     */   
/*     */   public final void suspend() {
/* 369 */     Dispatch.class.suspend(this);
/*     */   }
/*     */   
/*     */   public final void resume(Throwable causedByFailure) {
/* 369 */     Dispatch.class.resume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public final void restart(Throwable cause) {
/* 369 */     Dispatch.class.restart(this, cause);
/*     */   }
/*     */   
/*     */   public final void stop() {
/* 369 */     Dispatch.class.stop(this);
/*     */   }
/*     */   
/*     */   public void sendMessage(Envelope msg) {
/* 369 */     Dispatch.class.sendMessage(this, msg);
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 369 */     Dispatch.class.sendSystemMessage(this, message);
/*     */   }
/*     */   
/*     */   public ChildrenContainer akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly() {
/* 369 */     return this.akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly_$eq(ChildrenContainer x$1) {
/* 369 */     this.akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   public long akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly() {
/* 369 */     return this.akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly_$eq(long x$1) {
/* 369 */     this.akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   public ChildrenContainer childrenRefs() {
/* 369 */     return Children.class.childrenRefs(this);
/*     */   }
/*     */   
/*     */   public final Iterable<ActorRef> children() {
/* 369 */     return Children.class.children(this);
/*     */   }
/*     */   
/*     */   public final Iterable<ActorRef> getChildren() {
/* 369 */     return Children.class.getChildren(this);
/*     */   }
/*     */   
/*     */   public final Option<ActorRef> child(String name) {
/* 369 */     return Children.class.child(this, name);
/*     */   }
/*     */   
/*     */   public final ActorRef getChild(String name) {
/* 369 */     return Children.class.getChild(this, name);
/*     */   }
/*     */   
/*     */   public ActorRef actorOf(Props props) {
/* 369 */     return Children.class.actorOf(this, props);
/*     */   }
/*     */   
/*     */   public ActorRef actorOf(Props props, String name) {
/* 369 */     return Children.class.actorOf(this, props, name);
/*     */   }
/*     */   
/*     */   public ActorRef attachChild(Props props, boolean systemService) {
/* 369 */     return Children.class.attachChild(this, props, systemService);
/*     */   }
/*     */   
/*     */   public ActorRef attachChild(Props props, String name, boolean systemService) {
/* 369 */     return Children.class.attachChild(this, props, name, systemService);
/*     */   }
/*     */   
/*     */   public final String randomName() {
/* 369 */     return Children.class.randomName(this);
/*     */   }
/*     */   
/*     */   public final void stop(ActorRef actor) {
/* 369 */     Children.class.stop(this, actor);
/*     */   }
/*     */   
/*     */   public final boolean reserveChild(String name) {
/* 369 */     return Children.class.reserveChild(this, name);
/*     */   }
/*     */   
/*     */   public final boolean unreserveChild(String name) {
/* 369 */     return Children.class.unreserveChild(this, name);
/*     */   }
/*     */   
/*     */   public final Option<ChildRestartStats> initChild(ActorRef ref) {
/* 369 */     return Children.class.initChild(this, ref);
/*     */   }
/*     */   
/*     */   public final boolean setChildrenTerminationReason(ChildrenContainer.SuspendReason reason) {
/* 369 */     return Children.class.setChildrenTerminationReason(this, reason);
/*     */   }
/*     */   
/*     */   public final void setTerminated() {
/* 369 */     Children.class.setTerminated(this);
/*     */   }
/*     */   
/*     */   public boolean isNormal() {
/* 369 */     return Children.class.isNormal(this);
/*     */   }
/*     */   
/*     */   public boolean isTerminating() {
/* 369 */     return Children.class.isTerminating(this);
/*     */   }
/*     */   
/*     */   public ChildrenContainer.SuspendReason waitingForChildrenOrNull() {
/* 369 */     return Children.class.waitingForChildrenOrNull(this);
/*     */   }
/*     */   
/*     */   public void suspendChildren(Set exceptFor) {
/* 369 */     Children.class.suspendChildren(this, exceptFor);
/*     */   }
/*     */   
/*     */   public void resumeChildren(Throwable causedByFailure, ActorRef perp) {
/* 369 */     Children.class.resumeChildren(this, causedByFailure, perp);
/*     */   }
/*     */   
/*     */   public Option<ChildStats> getChildByName(String name) {
/* 369 */     return Children.class.getChildByName(this, name);
/*     */   }
/*     */   
/*     */   public Option<ChildRestartStats> getChildByRef(ActorRef ref) {
/* 369 */     return Children.class.getChildByRef(this, ref);
/*     */   }
/*     */   
/*     */   public Iterable<ChildRestartStats> getAllChildStats() {
/* 369 */     return Children.class.getAllChildStats(this);
/*     */   }
/*     */   
/*     */   public InternalActorRef getSingleChild(String name) {
/* 369 */     return Children.class.getSingleChild(this, name);
/*     */   }
/*     */   
/*     */   public Option<ChildrenContainer.SuspendReason> removeChildAndGetStateChange(ActorRef child) {
/* 369 */     return Children.class.removeChildAndGetStateChange(this, child);
/*     */   }
/*     */   
/*     */   public Set<ActorRef> suspendChildren$default$1() {
/* 369 */     return Children.class.suspendChildren$default$1(this);
/*     */   }
/*     */   
/*     */   public Tuple2<Duration, Cancellable> akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData() {
/* 369 */     return this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData;
/*     */   }
/*     */   
/*     */   public void akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData_$eq(Tuple2<Duration, Cancellable> x$1) {
/* 369 */     this.akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData = x$1;
/*     */   }
/*     */   
/*     */   public final Duration receiveTimeout() {
/* 369 */     return ReceiveTimeout.class.receiveTimeout(this);
/*     */   }
/*     */   
/*     */   public final void setReceiveTimeout(Duration timeout) {
/* 369 */     ReceiveTimeout.class.setReceiveTimeout(this, timeout);
/*     */   }
/*     */   
/*     */   public final void checkReceiveTimeout() {
/* 369 */     ReceiveTimeout.class.checkReceiveTimeout(this);
/*     */   }
/*     */   
/*     */   public final void cancelReceiveTimeout() {
/* 369 */     ReceiveTimeout.class.cancelReceiveTimeout(this);
/*     */   }
/*     */   
/*     */   public final void sendMessage(Object message, ActorRef sender) {
/* 369 */     Cell$class.sendMessage(this, message, sender);
/*     */   }
/*     */   
/*     */   public void become(PartialFunction behavior) {
/* 369 */     ActorContext$class.become(this, behavior);
/*     */   }
/*     */   
/*     */   public final void writeObject(ObjectOutputStream o) {
/* 369 */     ActorContext$class.writeObject(this, o);
/*     */   }
/*     */   
/*     */   public ActorRef actorFor(ActorPath path) {
/* 369 */     return ActorRefFactory$class.actorFor(this, path);
/*     */   }
/*     */   
/*     */   public ActorRef actorFor(String path) {
/* 369 */     return ActorRefFactory$class.actorFor(this, path);
/*     */   }
/*     */   
/*     */   public ActorRef actorFor(Iterable path) {
/* 369 */     return ActorRefFactory$class.actorFor(this, path);
/*     */   }
/*     */   
/*     */   public ActorRef actorFor(Iterable path) {
/* 369 */     return ActorRefFactory$class.actorFor(this, path);
/*     */   }
/*     */   
/*     */   public ActorSelection actorSelection(String path) {
/* 369 */     return ActorRefFactory$class.actorSelection(this, path);
/*     */   }
/*     */   
/*     */   public ActorSelection actorSelection(ActorPath path) {
/* 369 */     return ActorRefFactory$class.actorSelection(this, path);
/*     */   }
/*     */   
/*     */   public ActorSystemImpl system() {
/* 370 */     return this.system;
/*     */   }
/*     */   
/*     */   public ActorCell(ActorSystemImpl system, InternalActorRef self, Props props, MessageDispatcher dispatcher, InternalActorRef parent) {
/*     */     ActorRefFactory$class.$init$(this);
/*     */     ActorContext$class.$init$(this);
/*     */     Cell$class.$init$(this);
/*     */     ReceiveTimeout.class.$init$(this);
/*     */     Children.class.$init$(this);
/*     */     Dispatch.class.$init$(this);
/*     */     DeathWatch.class.$init$(this);
/*     */     FaultHandling.class.$init$(this);
/* 396 */     this.behaviorStack = ActorCell$.MODULE$.emptyBehaviorStack();
/* 397 */     this.sysmsgStash = SystemMessageList$.MODULE$.LNil();
/*     */   }
/*     */   
/*     */   public InternalActorRef self() {
/*     */     return this.self;
/*     */   }
/*     */   
/*     */   public final Props props() {
/*     */     return this.props;
/*     */   }
/*     */   
/*     */   public MessageDispatcher dispatcher() {
/*     */     return this.dispatcher;
/*     */   }
/*     */   
/*     */   public InternalActorRef parent() {
/*     */     return this.parent;
/*     */   }
/*     */   
/*     */   public final boolean isLocal() {
/*     */     return true;
/*     */   }
/*     */   
/*     */   public final ActorSystemImpl systemImpl() {
/*     */     return system();
/*     */   }
/*     */   
/*     */   public final InternalActorRef guardian() {
/*     */     return self();
/*     */   }
/*     */   
/*     */   public final InternalActorRef lookupRoot() {
/*     */     return self();
/*     */   }
/*     */   
/*     */   public final ActorRefProvider provider() {
/*     */     return system().provider();
/*     */   }
/*     */   
/*     */   public int uid() {
/*     */     return self().path().uid();
/*     */   }
/*     */   
/*     */   public Actor actor() {
/*     */     return this._actor;
/*     */   }
/*     */   
/*     */   public void actor_$eq(Actor a) {
/*     */     this._actor = a;
/*     */   }
/*     */   
/*     */   public Envelope currentMessage() {
/*     */     return this.currentMessage;
/*     */   }
/*     */   
/*     */   public void currentMessage_$eq(Envelope x$1) {
/*     */     this.currentMessage = x$1;
/*     */   }
/*     */   
/*     */   private List<PartialFunction<Object, BoxedUnit>> behaviorStack() {
/*     */     return this.behaviorStack;
/*     */   }
/*     */   
/*     */   private void behaviorStack_$eq(List<PartialFunction<Object, BoxedUnit>> x$1) {
/*     */     this.behaviorStack = x$1;
/*     */   }
/*     */   
/*     */   public void stash(SystemMessage msg) {
/* 400 */     Predef$.MODULE$.assert(msg.unlinked());
/* 401 */     this.sysmsgStash = LatestFirstSystemMessageList$.MODULE$.$colon$colon$extension(this.sysmsgStash, msg);
/*     */   }
/*     */   
/*     */   private SystemMessage unstashAll() {
/* 405 */     SystemMessage unstashed = this.sysmsgStash;
/* 406 */     this.sysmsgStash = SystemMessageList$.MODULE$.LNil();
/* 407 */     return unstashed;
/*     */   }
/*     */   
/*     */   private final int calculateState$1() {
/* 426 */     return (waitingForChildrenOrNull() != null) ? 2 : (
/* 427 */       mailbox().isSuspended() ? 1 : 
/* 428 */       0);
/*     */   }
/*     */   
/*     */   private final void sendAllToDeadLetters$1(SystemMessage messages) {
/* 431 */     while (EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(messages)) {
/* 432 */       SystemMessage tail = EarliestFirstSystemMessageList$.MODULE$.tail$extension(messages);
/* 433 */       SystemMessage msg = messages;
/* 434 */       msg.unlink();
/* 435 */       ScalaActorRef qual$1 = package$.MODULE$.actorRef2Scala(provider().deadLetters());
/* 435 */       SystemMessage x$7 = msg;
/* 435 */       ActorRef x$8 = qual$1.$bang$default$2(x$7);
/* 435 */       qual$1.$bang(x$7, x$8);
/* 436 */       messages = tail;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean shouldStash$1(SystemMessage m, int state) {
/* 440 */     int i = state;
/* 440 */     switch (i) {
/*     */       default:
/* 440 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 440 */     return false;
/*     */   }
/*     */   
/*     */   private final void invokeAll$1(SystemMessage messages, int currentState) {
/*     */     // Byte code:
/*     */     //   0: getstatic akka/dispatch/sysmsg/EarliestFirstSystemMessageList$.MODULE$ : Lakka/dispatch/sysmsg/EarliestFirstSystemMessageList$;
/*     */     //   3: aload_1
/*     */     //   4: invokevirtual tail$extension : (Lakka/dispatch/sysmsg/SystemMessage;)Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   7: astore #4
/*     */     //   9: aload_1
/*     */     //   10: astore #5
/*     */     //   12: aload #5
/*     */     //   14: invokeinterface unlink : ()V
/*     */     //   19: goto -> 134
/*     */     //   22: astore #6
/*     */     //   24: aload_0
/*     */     //   25: new akka/actor/ActorCell$$anonfun$2
/*     */     //   28: dup
/*     */     //   29: aload_0
/*     */     //   30: invokespecial <init> : (Lakka/actor/ActorCell;)V
/*     */     //   33: invokevirtual handleNonFatalOrInterruptedException : (Lscala/Function1;)Lscala/PartialFunction;
/*     */     //   36: astore #7
/*     */     //   38: aload #7
/*     */     //   40: aload #6
/*     */     //   42: invokeinterface isDefinedAt : (Ljava/lang/Object;)Z
/*     */     //   47: ifeq -> 591
/*     */     //   50: aload #7
/*     */     //   52: aload #6
/*     */     //   54: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   59: pop
/*     */     //   60: aload_0
/*     */     //   61: invokespecial calculateState$1 : ()I
/*     */     //   64: istore #32
/*     */     //   66: iload #32
/*     */     //   68: iload_2
/*     */     //   69: if_icmpge -> 91
/*     */     //   72: aload_0
/*     */     //   73: invokespecial unstashAll : ()Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   76: astore #34
/*     */     //   78: getstatic akka/dispatch/sysmsg/EarliestFirstSystemMessageList$.MODULE$ : Lakka/dispatch/sysmsg/EarliestFirstSystemMessageList$;
/*     */     //   81: aload #4
/*     */     //   83: aload #34
/*     */     //   85: invokevirtual reverse_$colon$colon$colon$extension : (Lakka/dispatch/sysmsg/SystemMessage;Lakka/dispatch/sysmsg/SystemMessage;)Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   88: goto -> 93
/*     */     //   91: aload #4
/*     */     //   93: astore #33
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual isTerminated : ()Z
/*     */     //   99: ifeq -> 114
/*     */     //   102: aload_0
/*     */     //   103: aload #33
/*     */     //   105: invokespecial sendAllToDeadLetters$1 : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   108: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   111: goto -> 589
/*     */     //   114: getstatic akka/dispatch/sysmsg/EarliestFirstSystemMessageList$.MODULE$ : Lakka/dispatch/sysmsg/EarliestFirstSystemMessageList$;
/*     */     //   117: aload #33
/*     */     //   119: invokevirtual nonEmpty$extension : (Lakka/dispatch/sysmsg/SystemMessage;)Z
/*     */     //   122: ifeq -> 586
/*     */     //   125: aload #33
/*     */     //   127: iload #32
/*     */     //   129: istore_2
/*     */     //   130: astore_1
/*     */     //   131: goto -> 0
/*     */     //   134: aload #5
/*     */     //   136: astore #8
/*     */     //   138: aload #8
/*     */     //   140: ifnull -> 171
/*     */     //   143: aload #8
/*     */     //   145: astore #9
/*     */     //   147: aload_0
/*     */     //   148: aload #9
/*     */     //   150: iload_2
/*     */     //   151: invokespecial shouldStash$1 : (Lakka/dispatch/sysmsg/SystemMessage;I)Z
/*     */     //   154: ifeq -> 171
/*     */     //   157: aload_0
/*     */     //   158: aload #9
/*     */     //   160: invokevirtual stash : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   163: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   166: astore #10
/*     */     //   168: goto -> 570
/*     */     //   171: aload #8
/*     */     //   173: instanceof akka/dispatch/sysmsg/Failed
/*     */     //   176: ifeq -> 200
/*     */     //   179: aload #8
/*     */     //   181: checkcast akka/dispatch/sysmsg/Failed
/*     */     //   184: astore #11
/*     */     //   186: aload_0
/*     */     //   187: aload #11
/*     */     //   189: invokevirtual handleFailure : (Lakka/dispatch/sysmsg/Failed;)V
/*     */     //   192: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   195: astore #10
/*     */     //   197: goto -> 570
/*     */     //   200: aload #8
/*     */     //   202: instanceof akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   205: ifeq -> 254
/*     */     //   208: aload #8
/*     */     //   210: checkcast akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   213: astore #12
/*     */     //   215: aload #12
/*     */     //   217: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */     //   220: astore #13
/*     */     //   222: aload #12
/*     */     //   224: invokevirtual existenceConfirmed : ()Z
/*     */     //   227: istore #14
/*     */     //   229: aload #12
/*     */     //   231: invokevirtual addressTerminated : ()Z
/*     */     //   234: istore #15
/*     */     //   236: aload_0
/*     */     //   237: aload #13
/*     */     //   239: iload #14
/*     */     //   241: iload #15
/*     */     //   243: invokevirtual watchedActorTerminated : (Lakka/actor/ActorRef;ZZ)V
/*     */     //   246: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   249: astore #10
/*     */     //   251: goto -> 570
/*     */     //   254: aload #8
/*     */     //   256: instanceof akka/dispatch/sysmsg/Create
/*     */     //   259: ifeq -> 290
/*     */     //   262: aload #8
/*     */     //   264: checkcast akka/dispatch/sysmsg/Create
/*     */     //   267: astore #16
/*     */     //   269: aload #16
/*     */     //   271: invokevirtual failure : ()Lscala/Option;
/*     */     //   274: astore #17
/*     */     //   276: aload_0
/*     */     //   277: aload #17
/*     */     //   279: invokevirtual create : (Lscala/Option;)V
/*     */     //   282: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   285: astore #10
/*     */     //   287: goto -> 570
/*     */     //   290: aload #8
/*     */     //   292: instanceof akka/dispatch/sysmsg/Watch
/*     */     //   295: ifeq -> 335
/*     */     //   298: aload #8
/*     */     //   300: checkcast akka/dispatch/sysmsg/Watch
/*     */     //   303: astore #18
/*     */     //   305: aload #18
/*     */     //   307: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   310: astore #19
/*     */     //   312: aload #18
/*     */     //   314: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   317: astore #20
/*     */     //   319: aload_0
/*     */     //   320: aload #19
/*     */     //   322: aload #20
/*     */     //   324: invokevirtual addWatcher : (Lakka/actor/ActorRef;Lakka/actor/ActorRef;)V
/*     */     //   327: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   330: astore #10
/*     */     //   332: goto -> 570
/*     */     //   335: aload #8
/*     */     //   337: instanceof akka/dispatch/sysmsg/Unwatch
/*     */     //   340: ifeq -> 380
/*     */     //   343: aload #8
/*     */     //   345: checkcast akka/dispatch/sysmsg/Unwatch
/*     */     //   348: astore #21
/*     */     //   350: aload #21
/*     */     //   352: invokevirtual watchee : ()Lakka/actor/ActorRef;
/*     */     //   355: astore #22
/*     */     //   357: aload #21
/*     */     //   359: invokevirtual watcher : ()Lakka/actor/ActorRef;
/*     */     //   362: astore #23
/*     */     //   364: aload_0
/*     */     //   365: aload #22
/*     */     //   367: aload #23
/*     */     //   369: invokevirtual remWatcher : (Lakka/actor/ActorRef;Lakka/actor/ActorRef;)V
/*     */     //   372: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   375: astore #10
/*     */     //   377: goto -> 570
/*     */     //   380: aload #8
/*     */     //   382: instanceof akka/dispatch/sysmsg/Recreate
/*     */     //   385: ifeq -> 416
/*     */     //   388: aload #8
/*     */     //   390: checkcast akka/dispatch/sysmsg/Recreate
/*     */     //   393: astore #24
/*     */     //   395: aload #24
/*     */     //   397: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */     //   400: astore #25
/*     */     //   402: aload_0
/*     */     //   403: aload #25
/*     */     //   405: invokevirtual faultRecreate : (Ljava/lang/Throwable;)V
/*     */     //   408: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   411: astore #10
/*     */     //   413: goto -> 570
/*     */     //   416: aload #8
/*     */     //   418: instanceof akka/dispatch/sysmsg/Suspend
/*     */     //   421: ifeq -> 436
/*     */     //   424: aload_0
/*     */     //   425: invokevirtual faultSuspend : ()V
/*     */     //   428: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   431: astore #10
/*     */     //   433: goto -> 570
/*     */     //   436: aload #8
/*     */     //   438: instanceof akka/dispatch/sysmsg/Resume
/*     */     //   441: ifeq -> 472
/*     */     //   444: aload #8
/*     */     //   446: checkcast akka/dispatch/sysmsg/Resume
/*     */     //   449: astore #26
/*     */     //   451: aload #26
/*     */     //   453: invokevirtual causedByFailure : ()Ljava/lang/Throwable;
/*     */     //   456: astore #27
/*     */     //   458: aload_0
/*     */     //   459: aload #27
/*     */     //   461: invokevirtual faultResume : (Ljava/lang/Throwable;)V
/*     */     //   464: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   467: astore #10
/*     */     //   469: goto -> 570
/*     */     //   472: aload #8
/*     */     //   474: instanceof akka/dispatch/sysmsg/Terminate
/*     */     //   477: ifeq -> 492
/*     */     //   480: aload_0
/*     */     //   481: invokevirtual terminate : ()V
/*     */     //   484: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   487: astore #10
/*     */     //   489: goto -> 570
/*     */     //   492: aload #8
/*     */     //   494: instanceof akka/dispatch/sysmsg/Supervise
/*     */     //   497: ifeq -> 537
/*     */     //   500: aload #8
/*     */     //   502: checkcast akka/dispatch/sysmsg/Supervise
/*     */     //   505: astore #28
/*     */     //   507: aload #28
/*     */     //   509: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   512: astore #29
/*     */     //   514: aload #28
/*     */     //   516: invokevirtual async : ()Z
/*     */     //   519: istore #30
/*     */     //   521: aload_0
/*     */     //   522: aload #29
/*     */     //   524: iload #30
/*     */     //   526: invokespecial supervise : (Lakka/actor/ActorRef;Z)V
/*     */     //   529: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   532: astore #10
/*     */     //   534: goto -> 570
/*     */     //   537: getstatic akka/dispatch/sysmsg/NoMessage$.MODULE$ : Lakka/dispatch/sysmsg/NoMessage$;
/*     */     //   540: aload #8
/*     */     //   542: astore #31
/*     */     //   544: dup
/*     */     //   545: ifnonnull -> 557
/*     */     //   548: pop
/*     */     //   549: aload #31
/*     */     //   551: ifnull -> 565
/*     */     //   554: goto -> 576
/*     */     //   557: aload #31
/*     */     //   559: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   562: ifeq -> 576
/*     */     //   565: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   568: astore #10
/*     */     //   570: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   573: goto -> 59
/*     */     //   576: new scala/MatchError
/*     */     //   579: dup
/*     */     //   580: aload #8
/*     */     //   582: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   585: athrow
/*     */     //   586: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   589: pop
/*     */     //   590: return
/*     */     //   591: aload #6
/*     */     //   593: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #448	-> 3
/*     */     //   #449	-> 9
/*     */     //   #450	-> 12
/*     */     //   #451	-> 22
/*     */     //   #466	-> 24
/*     */     //   #451	-> 59
/*     */     //   #469	-> 60
/*     */     //   #472	-> 66
/*     */     //   #474	-> 95
/*     */     //   #475	-> 117
/*     */     //   #452	-> 134
/*     */     //   #453	-> 138
/*     */     //   #454	-> 171
/*     */     //   #455	-> 200
/*     */     //   #456	-> 254
/*     */     //   #457	-> 290
/*     */     //   #458	-> 335
/*     */     //   #459	-> 380
/*     */     //   #460	-> 416
/*     */     //   #461	-> 436
/*     */     //   #462	-> 472
/*     */     //   #463	-> 492
/*     */     //   #464	-> 537
/*     */     //   #452	-> 570
/*     */     //   #475	-> 586
/*     */     //   #474	-> 589
/*     */     //   #466	-> 591
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	594	0	this	Lakka/actor/ActorCell;
/*     */     //   0	594	1	messages	Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   0	594	2	currentState	I
/*     */     //   9	581	4	rest	Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   12	578	5	message	Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   38	556	7	catchExpr1	Lscala/PartialFunction;
/*     */     //   66	524	32	newState	I
/*     */     //   95	495	33	todo	Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   222	372	13	a	Lakka/actor/ActorRef;
/*     */     //   229	365	14	ec	Z
/*     */     //   236	358	15	at	Z
/*     */     //   276	318	17	failure	Lscala/Option;
/*     */     //   312	282	19	watchee	Lakka/actor/InternalActorRef;
/*     */     //   319	275	20	watcher	Lakka/actor/InternalActorRef;
/*     */     //   357	237	22	watchee	Lakka/actor/ActorRef;
/*     */     //   364	230	23	watcher	Lakka/actor/ActorRef;
/*     */     //   402	192	25	cause	Ljava/lang/Throwable;
/*     */     //   458	136	27	inRespToFailure	Ljava/lang/Throwable;
/*     */     //   514	80	29	child	Lakka/actor/ActorRef;
/*     */     //   521	73	30	async	Z
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   134	586	22	finally
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$2 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ActorCell$$anonfun$2(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/* 467 */       this.$outer.handleInvokeFailure((Iterable<ActorRef>)Nil$.MODULE$, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public final void systemInvoke(SystemMessage message) {
/* 478 */     invokeAll$1(message, calculateState$1());
/*     */   }
/*     */   
/*     */   public final void invoke(Envelope messageHandle) {
/*     */     try {
/* 483 */       currentMessage_$eq(messageHandle);
/* 484 */       cancelReceiveTimeout();
/* 485 */       Object object = messageHandle.message();
/* 486 */       if (object instanceof AutoReceivedMessage) {
/* 486 */         autoReceiveMessage(messageHandle);
/* 486 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/* 487 */         receiveMessage(object);
/* 487 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/* 489 */       null;
/* 489 */       currentMessage_$eq(null);
/*     */       return;
/*     */     } finally {
/* 493 */       checkReceiveTimeout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$3 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ActorCell$$anonfun$3(ActorCell $outer) {}
/*     */     
/*     */     public final void apply(Throwable e) {
/*     */       this.$outer.handleInvokeFailure((Iterable<ActorRef>)Nil$.MODULE$, e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void autoReceiveMessage(Envelope msg) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual system : ()Lakka/actor/ActorSystemImpl;
/*     */     //   4: invokevirtual settings : ()Lakka/actor/ActorSystem$Settings;
/*     */     //   7: invokevirtual DebugAutoReceive : ()Z
/*     */     //   10: ifeq -> 62
/*     */     //   13: aload_0
/*     */     //   14: new akka/event/Logging$Debug
/*     */     //   17: dup
/*     */     //   18: aload_0
/*     */     //   19: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   22: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   25: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   28: aload_0
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual actor : ()Lakka/actor/Actor;
/*     */     //   33: invokevirtual clazz : (Ljava/lang/Object;)Ljava/lang/Class;
/*     */     //   36: new scala/collection/mutable/StringBuilder
/*     */     //   39: dup
/*     */     //   40: invokespecial <init> : ()V
/*     */     //   43: ldc_w 'received AutoReceiveMessage '
/*     */     //   46: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   49: aload_1
/*     */     //   50: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   53: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   56: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;)V
/*     */     //   59: invokevirtual publish : (Lakka/event/Logging$LogEvent;)V
/*     */     //   62: aload_1
/*     */     //   63: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   66: astore_2
/*     */     //   67: aload_2
/*     */     //   68: instanceof akka/actor/Terminated
/*     */     //   71: ifeq -> 92
/*     */     //   74: aload_2
/*     */     //   75: checkcast akka/actor/Terminated
/*     */     //   78: astore_3
/*     */     //   79: aload_0
/*     */     //   80: aload_3
/*     */     //   81: invokevirtual receivedTerminated : (Lakka/actor/Terminated;)V
/*     */     //   84: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   87: astore #4
/*     */     //   89: goto -> 314
/*     */     //   92: aload_2
/*     */     //   93: instanceof akka/actor/AddressTerminated
/*     */     //   96: ifeq -> 126
/*     */     //   99: aload_2
/*     */     //   100: checkcast akka/actor/AddressTerminated
/*     */     //   103: astore #5
/*     */     //   105: aload #5
/*     */     //   107: invokevirtual address : ()Lakka/actor/Address;
/*     */     //   110: astore #6
/*     */     //   112: aload_0
/*     */     //   113: aload #6
/*     */     //   115: invokevirtual addressTerminated : (Lakka/actor/Address;)V
/*     */     //   118: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   121: astore #4
/*     */     //   123: goto -> 314
/*     */     //   126: getstatic akka/actor/Kill$.MODULE$ : Lakka/actor/Kill$;
/*     */     //   129: aload_2
/*     */     //   130: astore #7
/*     */     //   132: dup
/*     */     //   133: ifnonnull -> 145
/*     */     //   136: pop
/*     */     //   137: aload #7
/*     */     //   139: ifnull -> 153
/*     */     //   142: goto -> 164
/*     */     //   145: aload #7
/*     */     //   147: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   150: ifeq -> 164
/*     */     //   153: new akka/actor/ActorKilledException
/*     */     //   156: dup
/*     */     //   157: ldc_w 'Kill'
/*     */     //   160: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   163: athrow
/*     */     //   164: getstatic akka/actor/PoisonPill$.MODULE$ : Lakka/actor/PoisonPill$;
/*     */     //   167: aload_2
/*     */     //   168: astore #8
/*     */     //   170: dup
/*     */     //   171: ifnonnull -> 183
/*     */     //   174: pop
/*     */     //   175: aload #8
/*     */     //   177: ifnull -> 191
/*     */     //   180: goto -> 206
/*     */     //   183: aload #8
/*     */     //   185: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   188: ifeq -> 206
/*     */     //   191: aload_0
/*     */     //   192: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   195: invokevirtual stop : ()V
/*     */     //   198: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   201: astore #4
/*     */     //   203: goto -> 314
/*     */     //   206: aload_2
/*     */     //   207: instanceof akka/actor/ActorSelectionMessage
/*     */     //   210: ifeq -> 233
/*     */     //   213: aload_2
/*     */     //   214: checkcast akka/actor/ActorSelectionMessage
/*     */     //   217: astore #9
/*     */     //   219: aload_0
/*     */     //   220: aload #9
/*     */     //   222: invokespecial receiveSelection : (Lakka/actor/ActorSelectionMessage;)V
/*     */     //   225: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   228: astore #4
/*     */     //   230: goto -> 314
/*     */     //   233: aload_2
/*     */     //   234: instanceof akka/actor/Identify
/*     */     //   237: ifeq -> 315
/*     */     //   240: aload_2
/*     */     //   241: checkcast akka/actor/Identify
/*     */     //   244: astore #10
/*     */     //   246: aload #10
/*     */     //   248: invokevirtual messageId : ()Ljava/lang/Object;
/*     */     //   251: astore #11
/*     */     //   253: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   256: aload_0
/*     */     //   257: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */     //   260: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   263: astore #12
/*     */     //   265: new akka/actor/ActorIdentity
/*     */     //   268: dup
/*     */     //   269: aload #11
/*     */     //   271: new scala/Some
/*     */     //   274: dup
/*     */     //   275: aload_0
/*     */     //   276: invokevirtual self : ()Lakka/actor/InternalActorRef;
/*     */     //   279: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   282: invokespecial <init> : (Ljava/lang/Object;Lscala/Option;)V
/*     */     //   285: astore #13
/*     */     //   287: aload #12
/*     */     //   289: aload #13
/*     */     //   291: invokeinterface $bang$default$2 : (Ljava/lang/Object;)Lakka/actor/ActorRef;
/*     */     //   296: astore #14
/*     */     //   298: aload #12
/*     */     //   300: aload #13
/*     */     //   302: aload #14
/*     */     //   304: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   309: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   312: astore #4
/*     */     //   314: return
/*     */     //   315: new scala/MatchError
/*     */     //   318: dup
/*     */     //   319: aload_2
/*     */     //   320: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   323: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #497	-> 0
/*     */     //   #498	-> 13
/*     */     //   #500	-> 62
/*     */     //   #501	-> 67
/*     */     //   #502	-> 92
/*     */     //   #503	-> 126
/*     */     //   #504	-> 164
/*     */     //   #505	-> 206
/*     */     //   #506	-> 233
/*     */     //   #496	-> 314
/*     */     //   #500	-> 315
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	324	0	this	Lakka/actor/ActorCell;
/*     */     //   0	324	1	msg	Lakka/dispatch/Envelope;
/*     */     //   112	212	6	address	Lakka/actor/Address;
/*     */     //   253	71	11	messageId	Ljava/lang/Object;
/*     */     //   265	47	12	qual$2	Lakka/actor/ScalaActorRef;
/*     */     //   287	25	13	x$9	Lakka/actor/ActorIdentity;
/*     */     //   298	14	14	x$10	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   private void receiveSelection(ActorSelectionMessage sel) {
/* 511 */     if (sel.elements().isEmpty()) {
/* 512 */       invoke(Envelope$.MODULE$.apply(sel.msg(), sender(), system()));
/*     */     } else {
/* 514 */       ActorSelection$.MODULE$.deliverSelection(self(), sender(), sel);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void receiveMessage(Object msg) {
/* 516 */     actor().aroundReceive((PartialFunction<Object, BoxedUnit>)behaviorStack().head(), msg);
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/*     */     ActorRef actorRef;
/* 522 */     Envelope envelope = currentMessage();
/* 523 */     if (envelope == null) {
/* 523 */       actorRef = system().deadLetters();
/* 524 */     } else if (envelope.sender() != null) {
/* 524 */       actorRef = envelope.sender();
/*     */     } else {
/* 525 */       actorRef = system().deadLetters();
/*     */     } 
/*     */     return actorRef;
/*     */   }
/*     */   
/*     */   public boolean become$default$2() {
/* 528 */     return true;
/*     */   }
/*     */   
/*     */   public void become(PartialFunction behavior, boolean discardOld) {
/* 529 */     PartialFunction partialFunction = behavior;
/* 529 */     behaviorStack_$eq(((List)((discardOld && behaviorStack().nonEmpty()) ? (List)behaviorStack().tail() : behaviorStack())).$colon$colon(partialFunction));
/*     */   }
/*     */   
/*     */   public void become(Procedure<Object> behavior) {
/* 531 */     become(behavior, true);
/*     */   }
/*     */   
/*     */   public void become(Procedure behavior, boolean discardOld) {
/* 534 */     become((PartialFunction<Object, BoxedUnit>)new ActorCell$$anonfun$become$1(this, behavior), discardOld);
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$become$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Procedure behavior$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/* 534 */       Object object = x3;
/* 534 */       this.behavior$1.apply(object);
/* 534 */       return (B1)BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x3) {
/* 534 */       Object object = x3;
/* 534 */       return true;
/*     */     }
/*     */     
/*     */     public ActorCell$$anonfun$become$1(ActorCell $outer, Procedure behavior$1) {}
/*     */   }
/*     */   
/*     */   public void unbecome() {
/* 537 */     List<PartialFunction<Object, BoxedUnit>> original = behaviorStack();
/* 539 */     PartialFunction<Object, BoxedUnit> partialFunction = actor().receive();
/* 539 */     behaviorStack_$eq((original.isEmpty() || ((SeqLike)original.tail()).isEmpty()) ? ActorCell$.MODULE$.emptyBehaviorStack().$colon$colon(partialFunction) : 
/* 540 */         (List<PartialFunction<Object, BoxedUnit>>)original.tail());
/*     */   }
/*     */   
/*     */   public Actor newActor() {
/* 549 */     ActorCell actorCell = this;
/* 549 */     ActorCell$.MODULE$.contextStack().set(((List)ActorCell$.MODULE$.contextStack().get()).$colon$colon(actorCell));
/*     */     try {
/* 551 */       behaviorStack_$eq(ActorCell$.MODULE$.emptyBehaviorStack());
/* 552 */       Actor instance = props().newActor();
/* 554 */       if (instance == null)
/* 555 */         throw ActorInitializationException$.MODULE$.apply(self(), "Actor instance passed to actorOf can't be 'null'", ActorInitializationException$.MODULE$.apply$default$3()); 
/* 558 */       PartialFunction<Object, BoxedUnit> partialFunction = instance.receive();
/* 558 */       behaviorStack_$eq(behaviorStack().isEmpty() ? behaviorStack().$colon$colon(partialFunction) : behaviorStack());
/* 559 */       return instance;
/*     */     } finally {
/* 561 */       List list = ActorCell$.MODULE$.contextStack().get();
/* 562 */       if (list.nonEmpty())
/* 563 */         ActorCell$.MODULE$.contextStack().set((list.head() == null) ? ((TraversableLike)list.tail()).tail() : list.tail()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void clearOutActorIfNonNull$1() {
/* 569 */     if (actor() != null) {
/* 570 */       clearActorFields(actor());
/* 571 */       null;
/* 571 */       actor_$eq(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void create(Option failure) {
/* 575 */     failure.foreach((Function1)new ActorCell$$anonfun$create$1(this));
/*     */     try {
/*     */       return;
/*     */     } finally {
/* 577 */       Exception exception1 = null, exception2 = exception1;
/* 584 */       if (exception2 instanceof InterruptedException) {
/* 584 */         InterruptedException interruptedException = (InterruptedException)exception2;
/* 585 */         clearOutActorIfNonNull$1();
/* 586 */         Thread.currentThread().interrupt();
/* 587 */         throw ActorInitializationException$.MODULE$.apply(self(), "interruption during creation", interruptedException);
/*     */       } 
/* 588 */       Option option = NonFatal$.MODULE$.unapply(exception2);
/* 588 */       if (option.isEmpty())
/*     */         throw exception1; 
/* 588 */       Throwable e = (Throwable)option.get();
/* 589 */       clearOutActorIfNonNull$1();
/* 590 */       Throwable throwable1 = e;
/* 591 */       if (throwable1 instanceof InstantiationException) {
/* 591 */         InstantiationException instantiationException = (InstantiationException)throwable1;
/* 591 */         throw ActorInitializationException$.MODULE$.apply(self(), 
/* 592 */             "exception during creation, this problem is likely to occur because the class of the Actor you tried to create is either,\n               a non-static inner class (in which case make it a static inner class or use Props(new ...) or Props( new UntypedActorFactory ... )\n               or is missing an appropriate, reachable no-args constructor.\n              ", 
/*     */             
/* 595 */             instantiationException.getCause());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public class ActorCell$$anonfun$create$1 extends AbstractFunction1<ActorInitializationException, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply(ActorInitializationException x$6) {
/*     */       throw x$6;
/*     */     }
/*     */     
/*     */     public ActorCell$$anonfun$create$1(ActorCell $outer) {}
/*     */   }
/*     */   
/*     */   private void supervise(ActorRef child, boolean async) {
/* 602 */     if (!isTerminating()) {
/* 604 */       Option<ChildRestartStats> option = initChild(child);
/* 605 */       if (option instanceof scala.Some) {
/* 606 */         handleSupervise(child, async);
/* 607 */         publish((Logging.LogEvent)new Logging.Debug(self().path().toString(), clazz(actor()), (new StringBuilder()).append("now supervising ").append(child).toString()));
/* 607 */         BoxedUnit boxedUnit = system().settings().DebugLifecycle() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */       } else {
/* 608 */         Option<ChildRestartStats> option1 = option;
/* 608 */         if (None$.MODULE$ == null) {
/* 608 */           if (option1 != null)
/*     */             throw new MatchError(option); 
/* 608 */         } else if (!None$.MODULE$.equals(option1)) {
/*     */           throw new MatchError(option);
/*     */         } 
/* 608 */         publish((Logging.LogEvent)Logging$Error$.MODULE$.apply(self().path().toString(), clazz(actor()), (new StringBuilder()).append("received Supervise from unregistered child ").append(child).append(", this will not end well").toString()));
/* 608 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void handleSupervise(ActorRef child, boolean async) {
/* 613 */     ActorRef actorRef = child;
/* 614 */     if (actorRef instanceof RepointableActorRef) {
/* 614 */       RepointableActorRef repointableActorRef = (RepointableActorRef)actorRef;
/* 614 */       if (async) {
/* 614 */         repointableActorRef.point();
/* 614 */         BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */         return;
/*     */       } 
/*     */     } 
/* 615 */     BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */   }
/*     */   
/*     */   private final boolean clearFirst$1(Field[] fields, int idx, Object instance$1, String name$1, Object value$1) {
/*     */     // Byte code:
/*     */     //   0: iload_2
/*     */     //   1: aload_1
/*     */     //   2: arraylength
/*     */     //   3: if_icmpge -> 68
/*     */     //   6: aload_1
/*     */     //   7: iload_2
/*     */     //   8: aaload
/*     */     //   9: astore #7
/*     */     //   11: aload #7
/*     */     //   13: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   16: aload #4
/*     */     //   18: astore #8
/*     */     //   20: dup
/*     */     //   21: ifnonnull -> 33
/*     */     //   24: pop
/*     */     //   25: aload #8
/*     */     //   27: ifnull -> 41
/*     */     //   30: goto -> 59
/*     */     //   33: aload #8
/*     */     //   35: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   38: ifeq -> 59
/*     */     //   41: aload #7
/*     */     //   43: iconst_1
/*     */     //   44: invokevirtual setAccessible : (Z)V
/*     */     //   47: aload #7
/*     */     //   49: aload_3
/*     */     //   50: aload #5
/*     */     //   52: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   55: iconst_1
/*     */     //   56: goto -> 69
/*     */     //   59: aload_1
/*     */     //   60: iload_2
/*     */     //   61: iconst_1
/*     */     //   62: iadd
/*     */     //   63: istore_2
/*     */     //   64: astore_1
/*     */     //   65: goto -> 0
/*     */     //   68: iconst_0
/*     */     //   69: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #620	-> 0
/*     */     //   #621	-> 6
/*     */     //   #622	-> 11
/*     */     //   #623	-> 41
/*     */     //   #624	-> 47
/*     */     //   #625	-> 55
/*     */     //   #626	-> 59
/*     */     //   #627	-> 68
/*     */     //   #619	-> 69
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	70	0	this	Lakka/actor/ActorCell;
/*     */     //   0	70	1	fields	[Ljava/lang/reflect/Field;
/*     */     //   0	70	2	idx	I
/*     */     //   0	70	3	instance$1	Ljava/lang/Object;
/*     */     //   0	70	4	name$1	Ljava/lang/String;
/*     */     //   0	70	5	value$1	Ljava/lang/Object;
/*     */     //   11	59	7	field	Ljava/lang/reflect/Field;
/*     */   }
/*     */   
/*     */   private final boolean lookupAndSetField(Class clazz, Object instance, String name, Object value) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokevirtual getDeclaredFields : ()[Ljava/lang/reflect/Field;
/*     */     //   5: iconst_0
/*     */     //   6: aload_2
/*     */     //   7: aload_3
/*     */     //   8: aload #4
/*     */     //   10: invokespecial clearFirst$1 : ([Ljava/lang/reflect/Field;ILjava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z
/*     */     //   13: ifne -> 35
/*     */     //   16: aload_1
/*     */     //   17: invokevirtual getSuperclass : ()Ljava/lang/Class;
/*     */     //   20: astore #6
/*     */     //   22: aload #6
/*     */     //   24: ifnonnull -> 41
/*     */     //   27: iconst_0
/*     */     //   28: istore #7
/*     */     //   30: iload #7
/*     */     //   32: ifeq -> 39
/*     */     //   35: iconst_1
/*     */     //   36: goto -> 40
/*     */     //   39: iconst_0
/*     */     //   40: ireturn
/*     */     //   41: aload #6
/*     */     //   43: aload_2
/*     */     //   44: aload_3
/*     */     //   45: aload #4
/*     */     //   47: astore #4
/*     */     //   49: astore_3
/*     */     //   50: astore_2
/*     */     //   51: astore_1
/*     */     //   52: goto -> 0
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #629	-> 0
/*     */     //   #630	-> 16
/*     */     //   #631	-> 22
/*     */     //   #630	-> 30
/*     */     //   #629	-> 35
/*     */     //   #618	-> 40
/*     */     //   #632	-> 41
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	55	0	this	Lakka/actor/ActorCell;
/*     */     //   0	55	1	clazz	Ljava/lang/Class;
/*     */     //   0	55	2	instance	Ljava/lang/Object;
/*     */     //   0	55	3	name	Ljava/lang/String;
/*     */     //   0	55	4	value	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public final void clearActorCellFields(ActorCell cell) {
/* 638 */     cell.unstashAll();
/* 639 */     if (lookupAndSetField(ActorCell.class, cell, "props", ActorCell$.MODULE$.terminatedProps()))
/*     */       return; 
/* 640 */     throw new IllegalArgumentException("ActorCell has no props field");
/*     */   }
/*     */   
/*     */   public final void clearActorFields(Actor actorInstance) {
/* 644 */     null;
/* 644 */     setActorFields(actorInstance, null, system().deadLetters());
/* 645 */     null;
/* 645 */     currentMessage_$eq(null);
/* 646 */     behaviorStack_$eq(ActorCell$.MODULE$.emptyBehaviorStack());
/*     */   }
/*     */   
/*     */   public final void setActorFields(Actor actorInstance, ActorContext context, ActorRef self) {
/* 650 */     if (actorInstance != null && (
/* 651 */       !lookupAndSetField(actorInstance.getClass(), actorInstance, "context", context) || 
/* 652 */       !lookupAndSetField(actorInstance.getClass(), actorInstance, "self", self)))
/* 653 */       throw new IllegalActorStateException(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(actorInstance.getClass()), " is not an Actor since it have not mixed in the 'Actor' trait")); 
/*     */   }
/*     */   
/*     */   public final void publish(Logging.LogEvent e) {
/*     */     try {
/* 657 */       system().eventStream().publish(e);
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/* 657 */       Exception exception1 = null, exception2 = exception1;
/* 657 */       Option option = NonFatal$.MODULE$.unapply(exception2);
/* 657 */       if (option.isEmpty())
/* 657 */         throw exception1; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Class<?> clazz(Object o) {
/* 659 */     return (o == null) ? getClass() : o.getClass();
/*     */   }
/*     */   
/*     */   public static int SuspendedWaitForChildrenState() {
/*     */     return ActorCell$.MODULE$.SuspendedWaitForChildrenState();
/*     */   }
/*     */   
/*     */   public static int SuspendedState() {
/*     */     return ActorCell$.MODULE$.SuspendedState();
/*     */   }
/*     */   
/*     */   public static int DefaultState() {
/*     */     return ActorCell$.MODULE$.DefaultState();
/*     */   }
/*     */   
/*     */   public static Tuple2<String, Object> splitNameAndUid(String paramString) {
/*     */     return ActorCell$.MODULE$.splitNameAndUid(paramString);
/*     */   }
/*     */   
/*     */   public static int newUid() {
/*     */     return ActorCell$.MODULE$.newUid();
/*     */   }
/*     */   
/*     */   public static int undefinedUid() {
/*     */     return ActorCell$.MODULE$.undefinedUid();
/*     */   }
/*     */   
/*     */   public static Props terminatedProps() {
/*     */     return ActorCell$.MODULE$.terminatedProps();
/*     */   }
/*     */   
/*     */   public static Set<ActorRef> emptyActorRefSet() {
/*     */     return ActorCell$.MODULE$.emptyActorRefSet();
/*     */   }
/*     */   
/*     */   public static List<PartialFunction<Object, BoxedUnit>> emptyBehaviorStack() {
/*     */     return ActorCell$.MODULE$.emptyBehaviorStack();
/*     */   }
/*     */   
/*     */   public static Cancellable emptyCancellable() {
/*     */     return ActorCell$.MODULE$.emptyCancellable();
/*     */   }
/*     */   
/*     */   public static ThreadLocal<List<ActorContext>> contextStack() {
/*     */     return ActorCell$.MODULE$.contextStack();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */