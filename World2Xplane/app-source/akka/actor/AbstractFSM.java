/*      */ package akka.actor;
/*      */ 
/*      */ import akka.event.LoggingAdapter;
/*      */ import akka.japi.pf.FI;
/*      */ import akka.japi.pf.FSMStateFunctionBuilder;
/*      */ import akka.japi.pf.FSMStopBuilder;
/*      */ import akka.japi.pf.FSMTransitionHandlerBuilder;
/*      */ import akka.japi.pf.UnitMatch;
/*      */ import akka.japi.pf.UnitPFBuilder;
/*      */ import akka.routing.Listeners;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import scala.Function2;
/*      */ import scala.Option;
/*      */ import scala.Option$;
/*      */ import scala.PartialFunction;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.Iterator;
/*      */ import scala.collection.immutable.List;
/*      */ import scala.collection.mutable.Map;
/*      */ import scala.concurrent.duration.FiniteDuration;
/*      */ import scala.reflect.ScalaSignature;
/*      */ import scala.runtime.BoxedUnit;
/*      */ import scala.runtime.TraitSetter;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\tux!B\001\003\021\0039\021aC!cgR\024\030m\031;G'6S!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!aC!cgR\024\030m\031;G'6\033\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\003\027\023\021\005q#\001\007Ok2dg)\0368di&|g.F\002\031=!*\022!\007\t\005\033iar%\003\002\034\035\ty\001+\031:uS\006dg)\0368di&|g\016\005\002\036=1\001A!B\020\026\005\004\001#!A*\022\005\005\"\003CA\007#\023\t\031cBA\004O_RD\027N\\4\021\0055)\023B\001\024\017\005\r\te.\037\t\003;!\"Q!K\013C\002\001\022\021\001\022\004\006\025\t\t\taK\013\004YE\0324c\001\026\r[A!\001B\f\0313\023\ty#AA\002G'6\003\"!H\031\005\013}Q#\031\001\021\021\005u\031D!B\025+\005\004\001\003\"B\n+\t\003)D#\001\034\021\t!Q\003G\r\005\006q)\")!O\001\005o\",g\016\006\002;\tR\0211H\020\t\003\033qJ!!\020\b\003\tUs\027\016\036\005\006]\002\r\001Q\001\016gR\fG/\032$v]\016$\030n\0348\021\005\005\023U\"\001\026\n\005\rs#!D*uCR,g)\0368di&|g\016C\003Fo\001\007\001'A\005ti\006$XMT1nK\")\001H\013C\003\017R\0311\bS%\t\013\0253\005\031\001\031\t\013)3\005\031A&\002)M$\030\r^3Gk:\034G/[8o\005VLG\016Z3s!\021a\025\013\r\032\016\0035S!AT(\002\005A4'B\001)\005\003\021Q\027\r]5\n\005Ik%a\006$T\033N#\030\r^3Gk:\034G/[8o\005VLG\016Z3s\021\025A$\006\"\002U)\021YTK\0261\t\013\025\033\006\031\001\031\t\013]\033\006\031\001-\002\031M$\030\r^3US6,w.\036;\021\005esV\"\001.\013\005mc\026\001\0033ve\006$\030n\0348\013\005us\021AC2p]\016,(O]3oi&\021qL\027\002\017\r&t\027\016^3EkJ\fG/[8o\021\025Q5\0131\001L\021\025\021'\006\"\002d\003%\031H/\031:u/&$\b\016F\002<I\026DQ!R1A\002ABQAZ1A\002I\n\021b\035;bi\026$\025\r^1\t\013\tTCQ\0015\025\tmJ'n\033\005\006\013\036\004\r\001\r\005\006M\036\004\rA\r\005\006Y\036\004\r\001W\001\bi&lWm\\;u\021\025q'\006\"\002p\0031yg\016\026:b]NLG/[8o)\tY\004\017C\003r[\002\007!/\001\rue\006t7/\033;j_:D\025M\0343mKJ\024U/\0337eKJ\0042\001T:1\023\t!XJA\016G'6#&/\0318tSRLwN\034%b]\022dWM\035\"vS2$WM\035\005\006]*\")A\036\013\003w]DQ\001_;A\002e\f\021\003\036:b]NLG/[8o\021\006tG\r\\3s!\025Q\030Q\002\0311\035\rY\030\021\002\b\004y\006\035abA?\002\0069\031a0a\001\016\003}T1!!\001\007\003\031a$o\\8u}%\tQ!\003\002Q\t%\021ajT\005\004\003\027i\025A\001$J\023\021\ty!!\005\003\025Us\027\016^!qa2L(GC\002\002\f5Cq!!\006+\t\013\t9\"A\007xQ\026tWK\0345b]\022dW\r\032\013\004w\005e\001B\002&\002\024\001\0071\nC\004\002\036)\")!a\b\002\033=tG+\032:nS:\fG/[8o)\rY\024\021\005\005\t\003G\tY\0021\001\002&\005Y1\017^8q\005VLG\016Z3s!\025a\025q\005\0313\023\r\tI#\024\002\017\rNk5\013^8q\005VLG\016Z3s\021\035\tiC\013C\003\003_\t!\"\\1uG\",e/\0328u+\031\t\t$a\022\002TQI1*a\r\002L\005e\0231\r\005\t\003k\tY\0031\001\0028\005IQM^3oiRK\b/\032\t\007\003s\ty$!\022\017\0075\tY$C\002\002>9\ta\001\025:fI\0264\027\002BA!\003\007\022Qa\0217bgNT1!!\020\017!\ri\022q\t\003\b\003\023\nYC1\001!\005\t)E\013\003\005\002N\005-\002\031AA(\003!!\027\r^1UsB,\007CBA\035\003\t\t\006E\002\036\003'\"\001\"!\026\002,\t\007\021q\013\002\003\tR\013\"!\t\032\t\021\005m\0231\006a\001\003;\n\021\002\035:fI&\034\027\r^3\021\017i\fy&!\022\002R%!\021\021MA\t\005=!\026\020]3e!J,G-[2bi\026\024\004\002CA3\003W\001\r!a\032\002\013\005\004\b\017\\=\021\023i\fI'!\022\002R\0055\024\002BA6\003#\021a!\0219qYf\024\004cA!\002p%\031\021\021\017\030\003\013M#\030\r^3\t\017\0055\"\006\"\002\002vU1\021qOA@\003\017#raSA=\003\003\013I\t\003\005\0026\005M\004\031AA>!\031\tI$a\020\002~A\031Q$a \005\017\005%\0231\017b\001A!A\021QJA:\001\004\t\031\t\005\004\002:\005}\022Q\021\t\004;\005\035E\001CA+\003g\022\r!a\026\t\021\005\025\0241\017a\001\003\027\003\022B_A5\003{\n))!\034\t\017\0055\"\006\"\002\002\020V!\021\021SAM)\035Y\0251SAN\003?C\001\"!\016\002\016\002\007\021Q\023\t\007\003s\ty$a&\021\007u\tI\nB\004\002J\0055%\031\001\021\t\021\005m\023Q\022a\001\003;\003bA_A0\003/\023\004\002CA3\003\033\003\r!!)\021\021i\fI'a&3\003[Bq!!\f+\t\013\t)+\006\003\002(\006=F#B&\002*\006E\006\002CA\033\003G\003\r!a+\021\r\005e\022qHAW!\ri\022q\026\003\b\003\023\n\031K1\001!\021!\t)'a)A\002\005M\006\003\003>\002j\0055&'!\034\t\017\0055\"\006\"\002\0028R)1*!/\002>\"A\0211LA[\001\004\tY\fE\003{\003?b!\007\003\005\002f\005U\006\031AA`!\035Q\030\021\016\0073\003[Bq!!\f+\t\013\t\031-\006\003\002F\006\005HcB&\002H\006m\0271\035\005\t\003\023\f\t\r1\001\002L\006aQM^3oi6\013Go\0315fgB)\021QZAl\0315\021\021q\032\006\005\003#\f\031.\001\003vi&d'BAAk\003\021Q\027M^1\n\t\005e\027q\032\002\005\031&\034H\017\003\005\002N\005\005\007\031AAo!\031\tI$a\020\002`B\031Q$!9\005\021\005U\023\021\031b\001\003/B\001\"!\032\002B\002\007\021Q\035\t\tu\006%D\"a8\002n!9\021Q\006\026\005\006\005%H#B&\002l\0065\b\002CAe\003O\004\r!a3\t\021\005\025\024q\035a\001\003Cq!!=+\t\013\t\0310\001\tnCR\034\007.\022<f]R,\025/^1mgV1\021Q_A\005\017!raSA|\005\003\021I\001\003\005\002z\006=\b\031AA~\003\025)g/\0328u!\ri\022Q \003\b\003\fyO1\001!\005\005)\005\002CA'\003_\004\rAa\001\021\r\005e\022q\bB\003!\ri\"q\001\003\t\003+\nyO1\001\002X!A\021QMAx\001\004\021Y\001E\005{\003S\nYP!\002\002n!9\021\021\037\026\005\006\t=Q\003\002B\t\005/!Ra\023B\n\0053A\001\"!?\003\016\001\007!Q\003\t\004;\t]AaBA\000\005\033\021\r\001\t\005\t\003K\022i\0011\001\003\034AA!0!\033\003\026I\ni\007C\004\003 )\")A!\t\002\0335\fGo\0315B]f,e/\0328u)\rY%1\005\005\t\003K\022i\0021\001\002@\"9!q\005\026\005\006\t%\022AC7bi\016D7\013^1uKR9!Oa\013\0030\tM\002b\002B\027\005K\001\r\001M\001\nMJ|Wn\025;bi\026DqA!\r\003&\001\007\001'A\004u_N#\030\r^3\t\021\005\025$Q\005a\001\005k\0012A\037B\034\023\021\021I$!\005\003\033Us\027\016^!qa2Lhk\\5e\021\035\0219C\013C\003\005{!rA\035B \005\003\022\031\005C\004\003.\tm\002\031\001\031\t\017\tE\"1\ba\001a!9\021Q\rB\036\001\004I\bb\002B$U\021\025!\021J\001\n[\006$8\r[*u_B$b!!\n\003L\tu\003\002\003B'\005\013\002\rAa\024\002\rI,\027m]8o!\021\021\tFa\026\017\007!\021\031&C\002\003V\t\t1AR*N\023\021\021IFa\027\003\rI+\027m]8o\025\r\021)F\001\005\t\003K\022)\0051\001\003`A)!0!\0041e!9!q\t\026\005\006\t\rT\003\002B3\005_\"b!!\n\003h\tU\004\002\003B5\005C\002\rAa\033\002\025I,\027m]8o)f\004X\r\005\004\002:\005}\"Q\016\t\004;\t=D\001\003B9\005C\022\rAa\035\003\005I#\026cA\021\003P!A\021Q\rB1\001\004\0219\bE\004{\005s\022i\007\r\032\n\t\tm\024\021\003\002\013+:LG/\0219qYf\034\004b\002B$U\021\025!qP\013\005\005\003\023I\t\006\005\002&\t\r%1\022BJ\021!\021IG! A\002\t\025\005CBA\035\003\0219\tE\002\036\005\023#\001B!\035\003~\t\007!1\017\005\t\0037\022i\b1\001\003\016B)!Pa$\003\b&!!\021SA\t\0059!\026\020]3e!J,G-[2bi\026D\001\"!\032\003~\001\007!Q\023\t\bu\ne$q\021\0313\021\035\021IJ\013C\003\0057\013\021\"\\1uG\"$\025\r^1\026\t\tu%1\026\013\007\005?\023)K!,\021\t1\023\tKM\005\004\005Gk%!D+oSR\004fIQ;jY\022,'\017\003\005\002N\t]\005\031\001BT!\031\tI$a\020\003*B\031QDa+\005\021\005U#q\023b\001\003/B\001\"!\032\003\030\002\007!q\026\t\006u\nE&\021V\005\005\005g\013\tBA\005V]&$\030\t\0359ms\"9!\021\024\026\005\006\t]V\003\002B]\005\003$\002Ba(\003<\n\r'q\031\005\t\003\033\022)\f1\001\003>B1\021\021HA \005\0032!\bBa\t!\t)F!.C\002\005]\003\002CA.\005k\003\rA!2\021\013i\024yIa0\t\021\005\025$Q\027a\001\005\023\004RA\037BY\005CqA!4+\t\013\021y-\001\003h_R{G\003BA7\005#DqAa5\003L\002\007\001'A\007oKb$8\013^1uK:\013W.\032\005\b\005/TCQ\001Bm\003!\031X\r\036+j[\026\024HcB\036\003\\\n\025(\021\036\005\t\005;\024)\0161\001\003`\006!a.Y7f!\021\tID!9\n\t\t\r\0301\t\002\007'R\024\030N\\4\t\017\t\035(Q\033a\001I\005\031Qn]4\t\r1\024)\0161\001Y\021%\021iO\013b\001\n\003\021y/\001\004O_Jl\027\r\\\013\003\005\037B\001Ba=+A\003%!qJ\001\b\035>\024X.\0317!\021%\0219P\013b\001\n\003\021y/\001\005TQV$Hm\\<o\021!\021YP\013Q\001\n\t=\023!C*ikR$wn\0368!\001")
/*      */ public abstract class AbstractFSM<S, D> implements FSM<S, D> {
/*      */   private final FSM.Reason Normal;
/*      */   
/*      */   private final FSM.Reason Shutdown;
/*      */   
/*      */   private final FSM.Event$ Event;
/*      */   
/*      */   private final FSM.StopEvent$ StopEvent;
/*      */   
/*      */   private final FSM.$minus$greater$ $minus$greater;
/*      */   
/*      */   private final FSM.StateTimeout$ StateTimeout;
/*      */   
/*      */   private FSM.State<Object, Object> akka$actor$FSM$$currentState;
/*      */   
/*      */   private Option<Cancellable> akka$actor$FSM$$timeoutFuture;
/*      */   
/*      */   private FSM.State<Object, Object> akka$actor$FSM$$nextState;
/*      */   
/*      */   private long akka$actor$FSM$$generation;
/*      */   
/*      */   private final Map<String, FSM.Timer> akka$actor$FSM$$timers;
/*      */   
/*      */   private final Iterator<Object> akka$actor$FSM$$timerGen;
/*      */   
/*      */   private final Map<Object, PartialFunction<FSM.Event<Object>, FSM.State<Object, Object>>> akka$actor$FSM$$stateFunctions;
/*      */   
/*      */   private final Map<Object, Option<FiniteDuration>> akka$actor$FSM$$stateTimeouts;
/*      */   
/*      */   private final PartialFunction<FSM.Event<Object>, FSM.State<Object, Object>> akka$actor$FSM$$handleEventDefault;
/*      */   
/*      */   private PartialFunction<FSM.Event<Object>, FSM.State<Object, Object>> akka$actor$FSM$$handleEvent;
/*      */   
/*      */   private PartialFunction<FSM.StopEvent<Object, Object>, BoxedUnit> akka$actor$FSM$$terminateEvent;
/*      */   
/*      */   private List<PartialFunction<Tuple2<Object, Object>, BoxedUnit>> akka$actor$FSM$$transitionEvent;
/*      */   
/*      */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*      */   
/*      */   private final Set<ActorRef> listeners;
/*      */   
/*      */   private final ActorContext context;
/*      */   
/*      */   private final ActorRef self;
/*      */   
/*      */   public FSM.Event$ Event() {
/*  781 */     return this.Event;
/*      */   }
/*      */   
/*      */   public FSM.StopEvent$ StopEvent() {
/*  781 */     return this.StopEvent;
/*      */   }
/*      */   
/*      */   public FSM.$minus$greater$ $minus$greater() {
/*  781 */     return this.$minus$greater;
/*      */   }
/*      */   
/*      */   public FSM.StateTimeout$ StateTimeout() {
/*  781 */     return this.StateTimeout;
/*      */   }
/*      */   
/*      */   public FSM.State<S, D> akka$actor$FSM$$currentState() {
/*  781 */     return (FSM.State)this.akka$actor$FSM$$currentState;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$currentState_$eq(FSM.State<Object, Object> x$1) {
/*  781 */     this.akka$actor$FSM$$currentState = x$1;
/*      */   }
/*      */   
/*      */   public Option<Cancellable> akka$actor$FSM$$timeoutFuture() {
/*  781 */     return this.akka$actor$FSM$$timeoutFuture;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$timeoutFuture_$eq(Option<Cancellable> x$1) {
/*  781 */     this.akka$actor$FSM$$timeoutFuture = x$1;
/*      */   }
/*      */   
/*      */   public FSM.State<S, D> akka$actor$FSM$$nextState() {
/*  781 */     return (FSM.State)this.akka$actor$FSM$$nextState;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$nextState_$eq(FSM.State<Object, Object> x$1) {
/*  781 */     this.akka$actor$FSM$$nextState = x$1;
/*      */   }
/*      */   
/*      */   public long akka$actor$FSM$$generation() {
/*  781 */     return this.akka$actor$FSM$$generation;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$generation_$eq(long x$1) {
/*  781 */     this.akka$actor$FSM$$generation = x$1;
/*      */   }
/*      */   
/*      */   public Map<String, FSM.Timer> akka$actor$FSM$$timers() {
/*  781 */     return this.akka$actor$FSM$$timers;
/*      */   }
/*      */   
/*      */   public Iterator<Object> akka$actor$FSM$$timerGen() {
/*  781 */     return this.akka$actor$FSM$$timerGen;
/*      */   }
/*      */   
/*      */   public Map<S, PartialFunction<FSM.Event<D>, FSM.State<S, D>>> akka$actor$FSM$$stateFunctions() {
/*  781 */     return (Map)this.akka$actor$FSM$$stateFunctions;
/*      */   }
/*      */   
/*      */   public Map<S, Option<FiniteDuration>> akka$actor$FSM$$stateTimeouts() {
/*  781 */     return (Map)this.akka$actor$FSM$$stateTimeouts;
/*      */   }
/*      */   
/*      */   public PartialFunction<FSM.Event<D>, FSM.State<S, D>> akka$actor$FSM$$handleEventDefault() {
/*  781 */     return (PartialFunction)this.akka$actor$FSM$$handleEventDefault;
/*      */   }
/*      */   
/*      */   public PartialFunction<FSM.Event<D>, FSM.State<S, D>> akka$actor$FSM$$handleEvent() {
/*  781 */     return (PartialFunction)this.akka$actor$FSM$$handleEvent;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$handleEvent_$eq(PartialFunction<FSM.Event<Object>, FSM.State<Object, Object>> x$1) {
/*  781 */     this.akka$actor$FSM$$handleEvent = x$1;
/*      */   }
/*      */   
/*      */   public PartialFunction<FSM.StopEvent<S, D>, BoxedUnit> akka$actor$FSM$$terminateEvent() {
/*  781 */     return (PartialFunction)this.akka$actor$FSM$$terminateEvent;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$terminateEvent_$eq(PartialFunction<FSM.StopEvent<Object, Object>, BoxedUnit> x$1) {
/*  781 */     this.akka$actor$FSM$$terminateEvent = x$1;
/*      */   }
/*      */   
/*      */   public List<PartialFunction<Tuple2<S, S>, BoxedUnit>> akka$actor$FSM$$transitionEvent() {
/*  781 */     return (List)this.akka$actor$FSM$$transitionEvent;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$FSM$$transitionEvent_$eq(List<PartialFunction<Tuple2<Object, Object>, BoxedUnit>> x$1) {
/*  781 */     this.akka$actor$FSM$$transitionEvent = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$$super$postStop() {
/*  781 */     Actor$class.postStop(this);
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$Event_$eq(FSM.Event$ x$1) {
/*  781 */     this.Event = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$StopEvent_$eq(FSM.StopEvent$ x$1) {
/*  781 */     this.StopEvent = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$$minus$greater_$eq(FSM.$minus$greater$ x$1) {
/*  781 */     this.$minus$greater = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$StateTimeout_$eq(FSM.StateTimeout$ x$1) {
/*  781 */     this.StateTimeout = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$akka$actor$FSM$$timers_$eq(Map<String, FSM.Timer> x$1) {
/*  781 */     this.akka$actor$FSM$$timers = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$akka$actor$FSM$$timerGen_$eq(Iterator<Object> x$1) {
/*  781 */     this.akka$actor$FSM$$timerGen = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$akka$actor$FSM$$stateFunctions_$eq(Map<Object, PartialFunction<FSM.Event<Object>, FSM.State<Object, Object>>> x$1) {
/*  781 */     this.akka$actor$FSM$$stateFunctions = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$akka$actor$FSM$$stateTimeouts_$eq(Map<Object, Option<FiniteDuration>> x$1) {
/*  781 */     this.akka$actor$FSM$$stateTimeouts = x$1;
/*      */   }
/*      */   
/*      */   public void akka$actor$FSM$_setter_$akka$actor$FSM$$handleEventDefault_$eq(PartialFunction<FSM.Event<Object>, FSM.State<Object, Object>> x$1) {
/*  781 */     this.akka$actor$FSM$$handleEventDefault = x$1;
/*      */   }
/*      */   
/*      */   public final void when(Object stateName, FiniteDuration stateTimeout, PartialFunction stateFunction) {
/*  781 */     FSM$class.when(this, stateName, stateTimeout, stateFunction);
/*      */   }
/*      */   
/*      */   public final void startWith(Object stateName, Object stateData, Option timeout) {
/*  781 */     FSM$class.startWith(this, stateName, stateData, timeout);
/*      */   }
/*      */   
/*      */   public final FSM.State<S, D> goto(Object nextStateName) {
/*  781 */     return FSM$class.goto(this, nextStateName);
/*      */   }
/*      */   
/*      */   public final FSM.State<S, D> stay() {
/*  781 */     return FSM$class.stay(this);
/*      */   }
/*      */   
/*      */   public final FSM.State<S, D> stop() {
/*  781 */     return FSM$class.stop(this);
/*      */   }
/*      */   
/*      */   public final FSM.State<S, D> stop(FSM.Reason reason) {
/*  781 */     return FSM$class.stop(this, reason);
/*      */   }
/*      */   
/*      */   public final FSM.State<S, D> stop(FSM.Reason reason, Object stateData) {
/*  781 */     return FSM$class.stop(this, reason, stateData);
/*      */   }
/*      */   
/*      */   public final FSM<S, D>.TransformHelper transform(PartialFunction func) {
/*  781 */     return FSM$class.transform(this, func);
/*      */   }
/*      */   
/*      */   public final void setTimer(String name, Object msg, FiniteDuration timeout, boolean repeat) {
/*  781 */     FSM$class.setTimer(this, name, msg, timeout, repeat);
/*      */   }
/*      */   
/*      */   public final void cancelTimer(String name) {
/*  781 */     FSM$class.cancelTimer(this, name);
/*      */   }
/*      */   
/*      */   public final boolean isTimerActive(String name) {
/*  781 */     return FSM$class.isTimerActive(this, name);
/*      */   }
/*      */   
/*      */   public final void setStateTimeout(Object state, Option timeout) {
/*  781 */     FSM$class.setStateTimeout(this, state, timeout);
/*      */   }
/*      */   
/*      */   public final boolean isStateTimerActive() {
/*  781 */     return FSM$class.isStateTimerActive(this);
/*      */   }
/*      */   
/*      */   public final void onTransition(PartialFunction transitionHandler) {
/*  781 */     FSM$class.onTransition(this, transitionHandler);
/*      */   }
/*      */   
/*      */   public final PartialFunction<Tuple2<S, S>, BoxedUnit> total2pf(Function2 transitionHandler) {
/*  781 */     return FSM$class.total2pf(this, transitionHandler);
/*      */   }
/*      */   
/*      */   public final void onTermination(PartialFunction terminationHandler) {
/*  781 */     FSM$class.onTermination(this, terminationHandler);
/*      */   }
/*      */   
/*      */   public final void whenUnhandled(PartialFunction stateFunction) {
/*  781 */     FSM$class.whenUnhandled(this, stateFunction);
/*      */   }
/*      */   
/*      */   public final void initialize() {
/*  781 */     FSM$class.initialize(this);
/*      */   }
/*      */   
/*      */   public final S stateName() {
/*  781 */     return (S)FSM$class.stateName(this);
/*      */   }
/*      */   
/*      */   public final D stateData() {
/*  781 */     return (D)FSM$class.stateData(this);
/*      */   }
/*      */   
/*      */   public final D nextStateData() {
/*  781 */     return (D)FSM$class.nextStateData(this);
/*      */   }
/*      */   
/*      */   public boolean debugEvent() {
/*  781 */     return FSM$class.debugEvent(this);
/*      */   }
/*      */   
/*      */   public PartialFunction<Object, BoxedUnit> receive() {
/*  781 */     return FSM$class.receive(this);
/*      */   }
/*      */   
/*      */   public void processEvent(FSM.Event event, Object source) {
/*  781 */     FSM$class.processEvent(this, event, source);
/*      */   }
/*      */   
/*      */   public void applyState(FSM.State nextState) {
/*  781 */     FSM$class.applyState(this, nextState);
/*      */   }
/*      */   
/*      */   public void makeTransition(FSM.State nextState) {
/*  781 */     FSM$class.makeTransition(this, nextState);
/*      */   }
/*      */   
/*      */   public void postStop() {
/*  781 */     FSM$class.postStop(this);
/*      */   }
/*      */   
/*      */   public void logTermination(FSM.Reason reason) {
/*  781 */     FSM$class.logTermination(this, reason);
/*      */   }
/*      */   
/*      */   public final FiniteDuration when$default$2() {
/*  781 */     return FSM$class.when$default$2(this);
/*      */   }
/*      */   
/*      */   public final Option<FiniteDuration> startWith$default$3() {
/*  781 */     return FSM$class.startWith$default$3(this);
/*      */   }
/*      */   
/*      */   public final boolean setTimer$default$4() {
/*  781 */     return FSM$class.setTimer$default$4(this);
/*      */   }
/*      */   
/*      */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/*  781 */     return this.akka$actor$ActorLogging$$_log;
/*      */   }
/*      */   
/*      */   @TraitSetter
/*      */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/*  781 */     this.akka$actor$ActorLogging$$_log = x$1;
/*      */   }
/*      */   
/*      */   public LoggingAdapter log() {
/*  781 */     return ActorLogging$class.log(this);
/*      */   }
/*      */   
/*      */   public Set<ActorRef> listeners() {
/*  781 */     return this.listeners;
/*      */   }
/*      */   
/*      */   public void akka$routing$Listeners$_setter_$listeners_$eq(Set<ActorRef> x$1) {
/*  781 */     this.listeners = x$1;
/*      */   }
/*      */   
/*      */   public PartialFunction<Object, BoxedUnit> listenerManagement() {
/*  781 */     return Listeners.class.listenerManagement(this);
/*      */   }
/*      */   
/*      */   public void gossip(Object msg, ActorRef sender) {
/*  781 */     Listeners.class.gossip(this, msg, sender);
/*      */   }
/*      */   
/*      */   public ActorRef gossip$default$2(Object msg) {
/*  781 */     return Listeners.class.gossip$default$2(this, msg);
/*      */   }
/*      */   
/*      */   public ActorContext context() {
/*  781 */     return this.context;
/*      */   }
/*      */   
/*      */   public final ActorRef self() {
/*  781 */     return this.self;
/*      */   }
/*      */   
/*      */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  781 */     this.context = x$1;
/*      */   }
/*      */   
/*      */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  781 */     this.self = x$1;
/*      */   }
/*      */   
/*      */   public final ActorRef sender() {
/*  781 */     return Actor$class.sender(this);
/*      */   }
/*      */   
/*      */   public void aroundReceive(PartialFunction receive, Object msg) {
/*  781 */     Actor$class.aroundReceive(this, receive, msg);
/*      */   }
/*      */   
/*      */   public void aroundPreStart() {
/*  781 */     Actor$class.aroundPreStart(this);
/*      */   }
/*      */   
/*      */   public void aroundPostStop() {
/*  781 */     Actor$class.aroundPostStop(this);
/*      */   }
/*      */   
/*      */   public void aroundPreRestart(Throwable reason, Option message) {
/*  781 */     Actor$class.aroundPreRestart(this, reason, message);
/*      */   }
/*      */   
/*      */   public void aroundPostRestart(Throwable reason) {
/*  781 */     Actor$class.aroundPostRestart(this, reason);
/*      */   }
/*      */   
/*      */   public SupervisorStrategy supervisorStrategy() {
/*  781 */     return Actor$class.supervisorStrategy(this);
/*      */   }
/*      */   
/*      */   public void preStart() throws Exception {
/*  781 */     Actor$class.preStart(this);
/*      */   }
/*      */   
/*      */   public void preRestart(Throwable reason, Option message) throws Exception {
/*  781 */     Actor$class.preRestart(this, reason, message);
/*      */   }
/*      */   
/*      */   public void postRestart(Throwable reason) throws Exception {
/*  781 */     Actor$class.postRestart(this, reason);
/*      */   }
/*      */   
/*      */   public void unhandled(Object message) {
/*  781 */     Actor$class.unhandled(this, message);
/*      */   }
/*      */   
/*      */   public AbstractFSM() {
/*  781 */     Actor$class.$init$(this);
/*  781 */     Listeners.class.$init$(this);
/*  781 */     ActorLogging$class.$init$(this);
/*  781 */     FSM$class.$init$(this);
/* 1116 */     this.Normal = FSM.Normal$.MODULE$;
/* 1122 */     this.Shutdown = FSM.Shutdown$.MODULE$;
/*      */   }
/*      */   
/*      */   public final void when(Object stateName, PartialFunction<FSM.Event<D>, FSM.State<S, D>> stateFunction) {
/*      */     null;
/*      */     when((S)stateName, (FiniteDuration)null, stateFunction);
/*      */   }
/*      */   
/*      */   public final void when(Object stateName, FSMStateFunctionBuilder<S, D> stateFunctionBuilder) {
/*      */     null;
/*      */     when((S)stateName, (FiniteDuration)null, stateFunctionBuilder);
/*      */   }
/*      */   
/*      */   public final void when(Object stateName, FiniteDuration stateTimeout, FSMStateFunctionBuilder stateFunctionBuilder) {
/*      */     when((S)stateName, stateTimeout, stateFunctionBuilder.build());
/*      */   }
/*      */   
/*      */   public final void startWith(Object stateName, Object stateData) {
/*      */     null;
/*      */     startWith((S)stateName, (D)stateData, (FiniteDuration)null);
/*      */   }
/*      */   
/*      */   public final void startWith(Object stateName, Object stateData, FiniteDuration timeout) {
/*      */     startWith((S)stateName, (D)stateData, Option$.MODULE$.apply(timeout));
/*      */   }
/*      */   
/*      */   public final void onTransition(FSMTransitionHandlerBuilder transitionHandlerBuilder) {
/*      */     onTransition(transitionHandlerBuilder.build());
/*      */   }
/*      */   
/*      */   public final void onTransition(FI.UnitApply2 transitionHandler) {
/*      */     while (true)
/*      */       transitionHandler = transitionHandler; 
/*      */   }
/*      */   
/*      */   public final void whenUnhandled(FSMStateFunctionBuilder stateFunctionBuilder) {
/*      */     whenUnhandled(stateFunctionBuilder.build());
/*      */   }
/*      */   
/*      */   public final void onTermination(FSMStopBuilder stopBuilder) {
/*      */     onTermination(stopBuilder.build());
/*      */   }
/*      */   
/*      */   public final <ET, DT extends D> FSMStateFunctionBuilder<S, D> matchEvent(Class eventType, Class dataType, FI.TypedPredicate2 predicate, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(eventType, dataType, apply);
/*      */   }
/*      */   
/*      */   public final <ET, DT extends D> FSMStateFunctionBuilder<S, D> matchEvent(Class eventType, Class dataType, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(eventType, dataType, apply);
/*      */   }
/*      */   
/*      */   public final <ET> FSMStateFunctionBuilder<S, D> matchEvent(Class eventType, FI.TypedPredicate2 predicate, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(eventType, predicate, apply);
/*      */   }
/*      */   
/*      */   public final <ET> FSMStateFunctionBuilder<S, D> matchEvent(Class eventType, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(eventType, apply);
/*      */   }
/*      */   
/*      */   public final FSMStateFunctionBuilder<S, D> matchEvent(FI.TypedPredicate2 predicate, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(predicate, apply);
/*      */   }
/*      */   
/*      */   public final <DT extends D> FSMStateFunctionBuilder<S, D> matchEvent(List eventMatches, Class dataType, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(eventMatches, dataType, apply);
/*      */   }
/*      */   
/*      */   public final FSMStateFunctionBuilder<S, D> matchEvent(List eventMatches, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).event(eventMatches, apply);
/*      */   }
/*      */   
/*      */   public final <E, DT extends D> FSMStateFunctionBuilder<S, D> matchEventEquals(Object event, Class dataType, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).eventEquals(event, dataType, apply);
/*      */   }
/*      */   
/*      */   public final <E> FSMStateFunctionBuilder<S, D> matchEventEquals(Object event, FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).eventEquals(event, apply);
/*      */   }
/*      */   
/*      */   public final FSMStateFunctionBuilder<S, D> matchAnyEvent(FI.Apply2 apply) {
/*      */     return (new FSMStateFunctionBuilder()).anyEvent(apply);
/*      */   }
/*      */   
/*      */   public final FSMTransitionHandlerBuilder<S> matchState(Object fromState, Object toState, FI.UnitApplyVoid apply) {
/*      */     return (new FSMTransitionHandlerBuilder()).state(fromState, toState, apply);
/*      */   }
/*      */   
/*      */   public final FSMTransitionHandlerBuilder<S> matchState(Object fromState, Object toState, FI.UnitApply2 apply) {
/*      */     return (new FSMTransitionHandlerBuilder()).state(fromState, toState, apply);
/*      */   }
/*      */   
/*      */   public final FSMStopBuilder<S, D> matchStop(FSM.Reason reason, FI.UnitApply2 apply) {
/*      */     return (new FSMStopBuilder()).stop(reason, apply);
/*      */   }
/*      */   
/*      */   public final <RT extends FSM.Reason> FSMStopBuilder<S, D> matchStop(Class reasonType, FI.UnitApply3 apply) {
/*      */     return (new FSMStopBuilder()).stop(reasonType, apply);
/*      */   }
/*      */   
/*      */   public final <RT extends FSM.Reason> FSMStopBuilder<S, D> matchStop(Class reasonType, FI.TypedPredicate predicate, FI.UnitApply3 apply) {
/*      */     return (new FSMStopBuilder()).stop(reasonType, predicate, apply);
/*      */   }
/*      */   
/*      */   public final <DT extends D> UnitPFBuilder<D> matchData(Class dataType, FI.UnitApply apply) {
/*      */     return UnitMatch.match(dataType, apply);
/*      */   }
/*      */   
/*      */   public final <DT extends D> UnitPFBuilder<D> matchData(Class dataType, FI.TypedPredicate predicate, FI.UnitApply apply) {
/*      */     return UnitMatch.match(dataType, predicate, apply);
/*      */   }
/*      */   
/*      */   public final FSM.State<S, D> goTo(Object nextStateName) {
/*      */     return goto((S)nextStateName);
/*      */   }
/*      */   
/*      */   public final void setTimer(String name, Object msg, FiniteDuration timeout) {
/*      */     setTimer(name, msg, timeout, false);
/*      */   }
/*      */   
/*      */   public FSM.Reason Normal() {
/*      */     return this.Normal;
/*      */   }
/*      */   
/*      */   public FSM.Reason Shutdown() {
/* 1122 */     return this.Shutdown;
/*      */   }
/*      */   
/*      */   public static <S, D> PartialFunction<S, D> NullFunction() {
/*      */     return AbstractFSM$.MODULE$.NullFunction();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractFSM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */