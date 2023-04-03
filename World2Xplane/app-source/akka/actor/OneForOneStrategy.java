/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Function;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tec\001B\001\003\001\036\021\021c\0248f\r>\024xJ\\3TiJ\fG/Z4z\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M!\001\001\003\007\023!\tI!\"D\001\003\023\tY!A\001\nTkB,'O^5t_J\034FO]1uK\036L\bCA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!a\002)s_\022,8\r\036\t\003\033MI!\001\006\b\003\031M+'/[1mSj\f'\r\\3\t\021Y\001!Q3A\005\002]\ta\"\\1y\035J|eMU3ue&,7/F\001\031!\ti\021$\003\002\033\035\t\031\021J\034;\t\021q\001!\021#Q\001\na\tq\"\\1y\035J|eMU3ue&,7\017\t\005\t=\001\021)\032!C\001?\005yq/\033;iS:$\026.\\3SC:<W-F\001!!\t\tc%D\001#\025\t\031C%\001\005ekJ\fG/[8o\025\t)c\"\001\006d_:\034WO\035:f]RL!a\n\022\003\021\021+(/\031;j_:D\001\"\013\001\003\022\003\006I\001I\001\021o&$\b.\0338US6,'+\0318hK\002B\001b\013\001\003\026\004%\t\005L\001\017Y><w-\0338h\013:\f'\r\\3e+\005i\003CA\007/\023\tycBA\004C_>dW-\0318\t\021E\002!\021#Q\001\n5\nq\002\\8hO&tw-\0228bE2,G\r\t\005\tg\001\021)\031!C\001i\0059A-Z2jI\026\024X#A\033\021\005YJdBA\0058\023\tA$!\001\nTkB,'O^5t_J\034FO]1uK\036L\030B\001\036<\005\035!UmY5eKJT!\001\017\002\t\021u\002!\021!Q\001\nU\n\001\002Z3dS\022,'\017\t\005\006\001!\t\001Q\001\007y%t\027\016\036 \025\t\005#UI\022\013\003\005\016\003\"!\003\001\t\013Mr\004\031A\033\t\017Yq\004\023!a\0011!9aD\020I\001\002\004\001\003bB\026?!\003\005\r!\f\005\006\001!\t\001\023\013\006\005&S5j\024\005\006-\035\003\r\001\007\005\006=\035\003\r\001\t\005\006g\035\003\r\001\024\t\003m5K!AT\036\003\021)#UmY5eKJDQaK$A\0025BQa\020\001\005\002E#BA\021*T)\")a\003\025a\0011!)a\004\025a\001A!)1\007\025a\001\031\")q\b\001C\001-R!!i\026-Z\021\0251R\0131\001\031\021\025qR\0131\001!\021\025QV\0131\001\\\003!!(/\0319Fq&$\bc\001/bG6\tQL\003\002_?\006!A.\0318h\025\005\001\027\001\0026bm\006L!AY/\003\021%#XM]1cY\026\004$\001Z7\021\007\025D7N\004\002\016M&\021qMD\001\007!J,G-\0324\n\005%T'!B\"mCN\034(BA4\017!\taW\016\004\001\005\0239L\026\021!A\001\006\003y'aA0%mE\021\001o\035\t\003\033EL!A\035\b\003\0179{G\017[5oOB\021A\017 \b\003kjt!A^=\016\003]T!\001\037\004\002\rq\022xn\034;?\023\005y\021BA>\017\003\035\001\030mY6bO\026L!! @\003\023QC'o\\<bE2,'BA>\017\021\031y\004\001\"\001\002\002Q9!)a\001\002\006\005\035\001\"\002\f\000\001\004A\002\"\002\020\000\001\004\001\003\"B\032\000\001\004)\004BB \001\t\003\tY\001F\003C\003\033\ty\001\003\004,\003\023\001\r!\f\005\007g\005%\001\031A\033\t\r}\002A\021AA\n)\r\021\025Q\003\005\007g\005E\001\031A\033\t\023\005e\001A1A\005\n\005m\021!\004:fiJLWm],j]\022|w/\006\002\002\036A9Q\"a\b\002$\005\r\022bAA\021\035\t1A+\0369mKJ\002B!DA\0231%\031\021q\005\b\003\r=\003H/[8o\021!\tY\003\001Q\001\n\005u\021A\004:fiJLWm],j]\022|w\017\t\005\b\003_\001A\021AA\031\003UA\027M\0343mK\016C\027\016\0343UKJl\027N\\1uK\022$\002\"a\r\002:\005\r\023Q\n\t\004\033\005U\022bAA\034\035\t!QK\\5u\021!\tY$!\fA\002\005u\022aB2p]R,\007\020\036\t\004\023\005}\022bAA!\005\ta\021i\031;pe\016{g\016^3yi\"A\021QIA\027\001\004\t9%A\003dQ&dG\rE\002\n\003\023J1!a\023\003\005!\t5\r^8s%\0264\007\002CA(\003[\001\r!!\025\002\021\rD\027\016\0343sK:\004R\001^A*\003\017J!A\031@\t\017\005]\003\001\"\001\002Z\005q\001O]8dKN\034h)Y5mkJ,GCDA\032\0037\ni&!\031\002d\005\035\024\021\017\005\t\003w\t)\0061\001\002>!9\021qLA+\001\004i\023a\002:fgR\f'\017\036\005\t\003\013\n)\0061\001\002H!9\021QMA+\001\004\031\030!B2bkN,\007\002CA5\003+\002\r!a\033\002\013M$\030\r^:\021\007%\ti'C\002\002p\t\021\021c\0215jY\022\024Vm\035;beR\034F/\031;t\021!\ty%!\026A\002\005M\004#\002;\002T\005-\004\"CA<\001\005\005I\021AA=\003\021\031w\016]=\025\021\005m\024qPAA\003\007#2AQA?\021\031\031\024Q\017a\001k!Aa#!\036\021\002\003\007\001\004\003\005\037\003k\002\n\0211\001!\021!Y\023Q\017I\001\002\004i\003\"CAD\001E\005I\021AAE\0039\031w\016]=%I\0264\027-\0367uIE*\"!a#+\007a\tii\013\002\002\020B!\021\021SAN\033\t\t\031J\003\003\002\026\006]\025!C;oG\",7m[3e\025\r\tIJD\001\013C:tw\016^1uS>t\027\002BAO\003'\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021%\t\t\013AI\001\n\003\t\031+\001\bd_BLH\005Z3gCVdG\017\n\032\026\005\005\025&f\001\021\002\016\"I\021\021\026\001\022\002\023\005\0211V\001\017G>\004\030\020\n3fM\006,H\016\036\0234+\t\tiKK\002.\003\033C\021\"!-\001\003\003%\t%a-\002\033A\024x\016Z;diB\023XMZ5y+\t\t)\fE\002]\003oK1!!/^\005\031\031FO]5oO\"A\021Q\030\001\002\002\023\005q#\001\007qe>$Wo\031;Be&$\030\020C\005\002B\002\t\t\021\"\001\002D\006q\001O]8ek\016$X\t\\3nK:$H\003BAc\003\027\0042!DAd\023\r\tIM\004\002\004\003:L\b\"CAg\003\013\t\0211\001\031\003\rAH%\r\005\n\003#\004\021\021!C!\003'\fq\002\035:pIV\034G/\023;fe\006$xN]\013\003\003+\004b!a6\002^\006\025WBAAm\025\r\tYND\001\013G>dG.Z2uS>t\027\002BAp\0033\024\001\"\023;fe\006$xN\035\005\n\003G\004\021\021!C\001\003K\f\001bY1o\013F,\030\r\034\013\004[\005\035\bBCAg\003C\f\t\0211\001\002F\"I\0211\036\001\002\002\023\005\023Q^\001\tQ\006\034\bnQ8eKR\t\001\004C\005\002r\002\t\t\021\"\021\002t\006AAo\\*ue&tw\r\006\002\0026\"I\021q\037\001\002\002\023\005\023\021`\001\007KF,\030\r\\:\025\0075\nY\020\003\006\002N\006U\030\021!a\001\003\013<\021\"a@\003\003\003E\tA!\001\002#=sWMR8s\037:,7\013\036:bi\026<\027\020E\002\n\005\0071\001\"\001\002\002\002#\005!QA\n\006\005\007\0219A\005\t\004\033\t%\021b\001B\006\035\t1\021I\\=SK\032Dqa\020B\002\t\003\021y\001\006\002\003\002!Q\021\021\037B\002\003\003%)%a=\t\025\tU!1AA\001\n\003\0239\"A\003baBd\027\020\006\005\003\032\tu!q\004B\021)\r\021%1\004\005\007g\tM\001\031A\033\t\021Y\021\031\002%AA\002aA\001B\bB\n!\003\005\r\001\t\005\tW\tM\001\023!a\001[!Q!Q\005B\002\003\003%\tIa\n\002\017Ut\027\r\0359msR!!\021\006B\031!\025i\021Q\005B\026!\031i!Q\006\r![%\031!q\006\b\003\rQ+\b\017\\34\021%\021\031Da\t\002\002\003\007!)A\002yIAB!Ba\016\003\004E\005I\021AAE\003=\t\007\017\0357zI\021,g-Y;mi\022\n\004B\003B\036\005\007\t\n\021\"\001\002$\006y\021\r\0359ms\022\"WMZ1vYR$#\007\003\006\003@\t\r\021\023!C\001\003W\013q\"\0319qYf$C-\0324bk2$He\r\005\013\005\007\022\031!%A\005\002\005%\025a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$\023\007\003\006\003H\t\r\021\023!C\001\003G\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004B\003B&\005\007\t\n\021\"\001\002,\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIMB!Ba\024\003\004\005\005I\021\002B)\003-\021X-\0313SKN|GN^3\025\005\tM\003c\001/\003V%\031!qK/\003\r=\023'.Z2u\001")
/*     */ public class OneForOneStrategy extends SupervisorStrategy implements Product, Serializable {
/*     */   private final int maxNrOfRetries;
/*     */   
/*     */   private final Duration withinTimeRange;
/*     */   
/*     */   private final boolean loggingEnabled;
/*     */   
/*     */   private final PartialFunction<Throwable, SupervisorStrategy.Directive> decider;
/*     */   
/*     */   private final Tuple2<Option<Object>, Option<Object>> retriesWindow;
/*     */   
/*     */   public OneForOneStrategy copy(int maxNrOfRetries, Duration withinTimeRange, boolean loggingEnabled, PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/* 460 */     return new OneForOneStrategy(
/* 461 */         maxNrOfRetries, 
/* 462 */         withinTimeRange, 
/* 463 */         loggingEnabled, decider);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "OneForOneStrategy";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return BoxesRunTime.boxToInteger(maxNrOfRetries());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof OneForOneStrategy;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, maxNrOfRetries());
/*     */     i = Statics.mix(i, Statics.anyHash(withinTimeRange()));
/*     */     i = Statics.mix(i, loggingEnabled() ? 1231 : 1237);
/*     */     return Statics.finalizeHash(i, 3);
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
/*     */     //   2: if_acmpeq -> 104
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/OneForOneStrategy
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 108
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/OneForOneStrategy
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual maxNrOfRetries : ()I
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual maxNrOfRetries : ()I
/*     */     //   40: if_icmpne -> 100
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual withinTimeRange : ()Lscala/concurrent/duration/Duration;
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual withinTimeRange : ()Lscala/concurrent/duration/Duration;
/*     */     //   52: astore #5
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 67
/*     */     //   58: pop
/*     */     //   59: aload #5
/*     */     //   61: ifnull -> 75
/*     */     //   64: goto -> 100
/*     */     //   67: aload #5
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 100
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual loggingEnabled : ()Z
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual loggingEnabled : ()Z
/*     */     //   84: if_icmpne -> 100
/*     */     //   87: aload #4
/*     */     //   89: aload_0
/*     */     //   90: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   93: ifeq -> 100
/*     */     //   96: iconst_1
/*     */     //   97: goto -> 101
/*     */     //   100: iconst_0
/*     */     //   101: ifeq -> 108
/*     */     //   104: iconst_1
/*     */     //   105: goto -> 109
/*     */     //   108: iconst_0
/*     */     //   109: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #460	-> 0
/*     */     //   #63	-> 14
/*     */     //   #460	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	110	0	this	Lakka/actor/OneForOneStrategy;
/*     */     //   0	110	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public int maxNrOfRetries() {
/*     */     return this.maxNrOfRetries;
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*     */     return maxNrOfRetries();
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(int maxNrOfRetries, Duration withinTimeRange, boolean loggingEnabled, PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/*     */     Product.class.$init$(this);
/* 510 */     this.retriesWindow = new Tuple2(
/* 511 */         SupervisorStrategy$.MODULE$.maxNrOfRetriesOption(maxNrOfRetries), 
/* 512 */         SupervisorStrategy$.MODULE$.withinTimeRangeOption(withinTimeRange).map((Function1)new $anonfun$5(this)));
/*     */   }
/*     */   
/*     */   public Duration withinTimeRange() {
/*     */     return this.withinTimeRange;
/*     */   }
/*     */   
/*     */   public Duration copy$default$2() {
/*     */     return withinTimeRange();
/*     */   }
/*     */   
/*     */   public boolean loggingEnabled() {
/*     */     return this.loggingEnabled;
/*     */   }
/*     */   
/*     */   public PartialFunction<Throwable, SupervisorStrategy.Directive> decider() {
/*     */     return this.decider;
/*     */   }
/*     */   
/*     */   public boolean copy$default$3() {
/*     */     return loggingEnabled();
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(int maxNrOfRetries, Duration withinTimeRange, Function<Throwable, SupervisorStrategy.Directive> decider, boolean loggingEnabled) {
/*     */     this(maxNrOfRetries, withinTimeRange, loggingEnabled, SupervisorStrategy$.MODULE$.makeDecider(decider));
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(int maxNrOfRetries, Duration withinTimeRange, Function<Throwable, SupervisorStrategy.Directive> decider) {
/*     */     this(maxNrOfRetries, withinTimeRange, OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), SupervisorStrategy$.MODULE$.makeDecider(decider));
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(int maxNrOfRetries, Duration withinTimeRange, Iterable<Class<? extends Throwable>> trapExit) {
/*     */     this(maxNrOfRetries, withinTimeRange, OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), SupervisorStrategy$.MODULE$.makeDecider(trapExit));
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(int maxNrOfRetries, Duration withinTimeRange, PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/*     */     this(maxNrOfRetries, withinTimeRange, OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), decider);
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(boolean loggingEnabled, PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/*     */     this(x$13, x$14, x$12, x$15);
/*     */   }
/*     */   
/*     */   public OneForOneStrategy(PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/*     */     this(OneForOneStrategy$.MODULE$.$lessinit$greater$default$1(), OneForOneStrategy$.MODULE$.$lessinit$greater$default$2(), OneForOneStrategy$.MODULE$.$lessinit$greater$default$3(), decider);
/*     */   }
/*     */   
/*     */   private Tuple2<Option<Object>, Option<Object>> retriesWindow() {
/*     */     return this.retriesWindow;
/*     */   }
/*     */   
/*     */   public class $anonfun$5 extends AbstractFunction1<Duration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Duration x$7) {
/* 512 */       return (int)x$7.toMillis();
/*     */     }
/*     */     
/*     */     public $anonfun$5(OneForOneStrategy $outer) {}
/*     */   }
/*     */   
/*     */   public void handleChildTerminated(ActorContext context, ActorRef child, Iterable children) {}
/*     */   
/*     */   public void processFailure(ActorContext context, boolean restart, ActorRef child, Throwable cause, ChildRestartStats stats, Iterable children) {
/* 517 */     if (restart && stats.requestRestartPermission(retriesWindow())) {
/* 518 */       restartChild(child, cause, false);
/*     */     } else {
/* 520 */       context.stop(child);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean $lessinit$greater$default$3() {
/*     */     return OneForOneStrategy$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static Duration $lessinit$greater$default$2() {
/*     */     return OneForOneStrategy$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return OneForOneStrategy$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static boolean apply$default$3() {
/*     */     return OneForOneStrategy$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static Duration apply$default$2() {
/*     */     return OneForOneStrategy$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return OneForOneStrategy$.MODULE$.apply$default$1();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\OneForOneStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */