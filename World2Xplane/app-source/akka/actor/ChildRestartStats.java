/*    */ package akka.actor;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.runtime.Statics;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]e\001B\001\003\001\036\021\021c\0215jY\022\024Vm\035;beR\034F/\031;t\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M)\001\001\003\b\023+A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\025\rC\027\016\0343Ti\006$8\017\005\002\n'%\021AC\003\002\b!J|G-^2u!\tIa#\003\002\030\025\ta1+\032:jC2L'0\0312mK\"A\021\004\001BK\002\023\005!$A\003dQ&dG-F\001\034!\tyA$\003\002\036\005\tA\021i\031;peJ+g\r\003\005 \001\tE\t\025!\003\034\003\031\031\007.\0337eA!A\021\005\001BI\002\023\005!%A\nnCbt%o\0244SKR\024\030.Z:D_VtG/F\001$!\tIA%\003\002&\025\t\031\021J\034;\t\021\035\002!\0211A\005\002!\nq#\\1y\035J|eMU3ue&,7oQ8v]R|F%Z9\025\005%b\003CA\005+\023\tY#B\001\003V]&$\bbB\027'\003\003\005\raI\001\004q\022\n\004\002C\030\001\005#\005\013\025B\022\002)5\f\007P\024:PMJ+GO]5fg\016{WO\034;!\021!\t\004A!e\001\n\003\021\024a\007:fgR\f'\017\036+j[\026<\026N\0343poN#\030M\035;OC:|7/F\0014!\tIA'\003\0026\025\t!Aj\0348h\021!9\004A!a\001\n\003A\024a\b:fgR\f'\017\036+j[\026<\026N\0343poN#\030M\035;OC:|7o\030\023fcR\021\021&\017\005\b[Y\n\t\0211\0014\021!Y\004A!E!B\023\031\024\001\b:fgR\f'\017\036+j[\026<\026N\0343poN#\030M\035;OC:|7\017\t\005\006{\001!\tAP\001\007y%t\027\016\036 \025\t}\002\025I\021\t\003\037\001AQ!\007\037A\002mAq!\t\037\021\002\003\0071\005C\0042yA\005\t\031A\032\t\013\021\003A\021\001\022\002\007ULG\rC\003G\001\021\005q)\001\rsKF,Xm\035;SKN$\030M\035;QKJl\027n]:j_:$\"\001S&\021\005%I\025B\001&\013\005\035\021un\0347fC:DQ\001T#A\0025\013QB]3ue&,7oV5oI><\b\003B\005O!BK!a\024\006\003\rQ+\b\017\\33!\rI\021kI\005\003%*\021aa\0249uS>t\007\"\002+\001\t\023)\026a\005:fiJLWm]%o/&tGm\\<PW\006LHc\001%W1\")qk\025a\001G\0059!/\032;sS\026\034\b\"B-T\001\004\031\023AB<j]\022|w\017C\004\\\001\005\005I\021\001/\002\t\r|\007/\037\013\005usv\fC\004\0325B\005\t\031A\016\t\017\005R\006\023!a\001G!9\021G\027I\001\002\004\031\004bB1\001#\003%\tAY\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005\031'FA\016eW\005)\007C\0014l\033\0059'B\0015j\003%)hn\0315fG.,GM\003\002k\025\005Q\021M\0348pi\006$\030n\0348\n\0051<'!E;oG\",7m[3e-\006\024\030.\0318dK\"9a\016AI\001\n\003y\027AD2paf$C-\0324bk2$HEM\013\002a*\0221\005\032\005\be\002\t\n\021\"\001t\0039\031w\016]=%I\0264\027-\0367uIM*\022\001\036\026\003g\021DqA\036\001\002\002\023\005s/A\007qe>$Wo\031;Qe\0264\027\016_\013\002qB\021\021P`\007\002u*\0211\020`\001\005Y\006twMC\001~\003\021Q\027M^1\n\005}T(AB*ue&tw\r\003\005\002\004\001\t\t\021\"\001#\0031\001(o\0343vGR\f%/\033;z\021%\t9\001AA\001\n\003\tI!\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005-\021\021\003\t\004\023\0055\021bAA\b\025\t\031\021I\\=\t\0215\n)!!AA\002\rB\021\"!\006\001\003\003%\t%a\006\002\037A\024x\016Z;di&#XM]1u_J,\"!!\007\021\r\005m\021\021EA\006\033\t\tiBC\002\002 )\t!bY8mY\026\034G/[8o\023\021\t\031#!\b\003\021%#XM]1u_JD\021\"a\n\001\003\003%\t!!\013\002\021\r\fg.R9vC2$2\001SA\026\021%i\023QEA\001\002\004\tY\001C\005\0020\001\t\t\021\"\021\0022\005A\001.Y:i\007>$W\rF\001$\021%\t)\004AA\001\n\003\n9$\001\005u_N#(/\0338h)\005A\b\"CA\036\001\005\005I\021IA\037\003\031)\027/^1mgR\031\001*a\020\t\0235\nI$!AA\002\005-q!CA\"\005\005\005\t\022AA#\003E\031\005.\0337e%\026\034H/\031:u'R\fGo\035\t\004\037\005\035c\001C\001\003\003\003E\t!!\023\024\013\005\035\0231J\013\021\021\0055\0231K\016$g}j!!a\024\013\007\005E#\"A\004sk:$\030.\\3\n\t\005U\023q\n\002\022\003\n\034HO]1di\032+hn\031;j_:\034\004bB\037\002H\021\005\021\021\f\013\003\003\013B!\"!\016\002H\005\005IQIA\034\021)\ty&a\022\002\002\023\005\025\021M\001\006CB\004H.\037\013\b\005\r\024QMA4\021\031I\022Q\fa\0017!A\021%!\030\021\002\003\0071\005\003\0052\003;\002\n\0211\0014\021)\tY'a\022\002\002\023\005\025QN\001\bk:\f\007\017\0357z)\021\ty'a\036\021\t%\t\026\021\017\t\007\023\005M4dI\032\n\007\005U$B\001\004UkBdWm\r\005\n\003s\nI'!AA\002}\n1\001\037\0231\021%\ti(a\022\022\002\023\005q.A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$HE\r\005\n\003\003\0139%%A\005\002M\f1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\032\004\"CAC\003\017\n\n\021\"\001p\003=\t\007\017\0357zI\021,g-Y;mi\022\022\004\"CAE\003\017\n\n\021\"\001t\003=\t\007\017\0357zI\021,g-Y;mi\022\032\004BCAG\003\017\n\t\021\"\003\002\020\006Y!/Z1e%\026\034x\016\034<f)\t\t\t\nE\002z\003'K1!!&{\005\031y%M[3di\002")
/*    */ public class ChildRestartStats implements ChildStats, Product, Serializable {
/*    */   private final ActorRef child;
/*    */   
/*    */   private int maxNrOfRetriesCount;
/*    */   
/*    */   private long restartTimeWindowStartNanos;
/*    */   
/*    */   public ActorRef child() {
/* 32 */     return this.child;
/*    */   }
/*    */   
/*    */   public int maxNrOfRetriesCount() {
/* 32 */     return this.maxNrOfRetriesCount;
/*    */   }
/*    */   
/*    */   public void maxNrOfRetriesCount_$eq(int x$1) {
/* 32 */     this.maxNrOfRetriesCount = x$1;
/*    */   }
/*    */   
/*    */   public long restartTimeWindowStartNanos() {
/* 32 */     return this.restartTimeWindowStartNanos;
/*    */   }
/*    */   
/*    */   public void restartTimeWindowStartNanos_$eq(long x$1) {
/* 32 */     this.restartTimeWindowStartNanos = x$1;
/*    */   }
/*    */   
/*    */   public ChildRestartStats copy(ActorRef child, int maxNrOfRetriesCount, long restartTimeWindowStartNanos) {
/* 32 */     return new ChildRestartStats(child, maxNrOfRetriesCount, restartTimeWindowStartNanos);
/*    */   }
/*    */   
/*    */   public ActorRef copy$default$1() {
/* 32 */     return child();
/*    */   }
/*    */   
/*    */   public int copy$default$2() {
/* 32 */     return maxNrOfRetriesCount();
/*    */   }
/*    */   
/*    */   public long copy$default$3() {
/* 32 */     return restartTimeWindowStartNanos();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 32 */     return "ChildRestartStats";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 32 */     return 3;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 32 */     int i = x$1;
/* 32 */     switch (i) {
/*    */       default:
/* 32 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 32 */     return child();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 32 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 32 */     return x$1 instanceof ChildRestartStats;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 32 */     int i = -889275714;
/* 32 */     i = Statics.mix(i, Statics.anyHash(child()));
/* 32 */     i = Statics.mix(i, maxNrOfRetriesCount());
/* 32 */     i = Statics.mix(i, Statics.longHash(restartTimeWindowStartNanos()));
/* 32 */     return Statics.finalizeHash(i, 3);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 32 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 105
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/actor/ChildRestartStats
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 109
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/actor/ChildRestartStats
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual child : ()Lakka/actor/ActorRef;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual child : ()Lakka/actor/ActorRef;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 101
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 101
/*    */     //   63: aload_0
/*    */     //   64: invokevirtual maxNrOfRetriesCount : ()I
/*    */     //   67: aload #4
/*    */     //   69: invokevirtual maxNrOfRetriesCount : ()I
/*    */     //   72: if_icmpne -> 101
/*    */     //   75: aload_0
/*    */     //   76: invokevirtual restartTimeWindowStartNanos : ()J
/*    */     //   79: aload #4
/*    */     //   81: invokevirtual restartTimeWindowStartNanos : ()J
/*    */     //   84: lcmp
/*    */     //   85: ifne -> 101
/*    */     //   88: aload #4
/*    */     //   90: aload_0
/*    */     //   91: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   94: ifeq -> 101
/*    */     //   97: iconst_1
/*    */     //   98: goto -> 102
/*    */     //   101: iconst_0
/*    */     //   102: ifeq -> 109
/*    */     //   105: iconst_1
/*    */     //   106: goto -> 110
/*    */     //   109: iconst_0
/*    */     //   110: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     //   #63	-> 14
/*    */     //   #32	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	111	0	this	Lakka/actor/ChildRestartStats;
/*    */     //   0	111	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ChildRestartStats(ActorRef child, int maxNrOfRetriesCount, long restartTimeWindowStartNanos) {
/* 32 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int uid() {
/* 35 */     return child().path().uid();
/*    */   }
/*    */   
/*    */   public boolean requestRestartPermission(Tuple2 retriesWindow) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: astore_2
/*    */     //   2: aload_2
/*    */     //   3: ifnull -> 49
/*    */     //   6: aload_2
/*    */     //   7: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   10: checkcast scala/Option
/*    */     //   13: astore_3
/*    */     //   14: aload_3
/*    */     //   15: instanceof scala/Some
/*    */     //   18: ifeq -> 49
/*    */     //   21: aload_3
/*    */     //   22: checkcast scala/Some
/*    */     //   25: astore #4
/*    */     //   27: aload #4
/*    */     //   29: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   32: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   35: istore #5
/*    */     //   37: iload #5
/*    */     //   39: iconst_1
/*    */     //   40: if_icmpge -> 49
/*    */     //   43: iconst_0
/*    */     //   44: istore #6
/*    */     //   46: goto -> 275
/*    */     //   49: aload_2
/*    */     //   50: ifnull -> 153
/*    */     //   53: aload_2
/*    */     //   54: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   57: checkcast scala/Option
/*    */     //   60: astore #7
/*    */     //   62: aload_2
/*    */     //   63: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   66: checkcast scala/Option
/*    */     //   69: astore #8
/*    */     //   71: aload #7
/*    */     //   73: instanceof scala/Some
/*    */     //   76: ifeq -> 153
/*    */     //   79: aload #7
/*    */     //   81: checkcast scala/Some
/*    */     //   84: astore #9
/*    */     //   86: aload #9
/*    */     //   88: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   91: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   94: istore #10
/*    */     //   96: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   99: aload #8
/*    */     //   101: astore #11
/*    */     //   103: dup
/*    */     //   104: ifnonnull -> 116
/*    */     //   107: pop
/*    */     //   108: aload #11
/*    */     //   110: ifnull -> 124
/*    */     //   113: goto -> 153
/*    */     //   116: aload #11
/*    */     //   118: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   121: ifeq -> 153
/*    */     //   124: aload_0
/*    */     //   125: aload_0
/*    */     //   126: invokevirtual maxNrOfRetriesCount : ()I
/*    */     //   129: iconst_1
/*    */     //   130: iadd
/*    */     //   131: invokevirtual maxNrOfRetriesCount_$eq : (I)V
/*    */     //   134: aload_0
/*    */     //   135: invokevirtual maxNrOfRetriesCount : ()I
/*    */     //   138: iload #10
/*    */     //   140: if_icmpgt -> 147
/*    */     //   143: iconst_1
/*    */     //   144: goto -> 148
/*    */     //   147: iconst_0
/*    */     //   148: istore #6
/*    */     //   150: goto -> 275
/*    */     //   153: aload_2
/*    */     //   154: ifnull -> 231
/*    */     //   157: aload_2
/*    */     //   158: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   161: checkcast scala/Option
/*    */     //   164: astore #12
/*    */     //   166: aload_2
/*    */     //   167: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   170: checkcast scala/Option
/*    */     //   173: astore #13
/*    */     //   175: aload #13
/*    */     //   177: instanceof scala/Some
/*    */     //   180: ifeq -> 231
/*    */     //   183: aload #13
/*    */     //   185: checkcast scala/Some
/*    */     //   188: astore #14
/*    */     //   190: aload #14
/*    */     //   192: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   195: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   198: istore #15
/*    */     //   200: aload_0
/*    */     //   201: aload #12
/*    */     //   203: invokevirtual isDefined : ()Z
/*    */     //   206: ifeq -> 220
/*    */     //   209: aload #12
/*    */     //   211: invokevirtual get : ()Ljava/lang/Object;
/*    */     //   214: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   217: goto -> 221
/*    */     //   220: iconst_1
/*    */     //   221: iload #15
/*    */     //   223: invokespecial retriesInWindowOkay : (II)Z
/*    */     //   226: istore #6
/*    */     //   228: goto -> 275
/*    */     //   231: aload_2
/*    */     //   232: ifnull -> 278
/*    */     //   235: aload_2
/*    */     //   236: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   239: checkcast scala/Option
/*    */     //   242: astore #16
/*    */     //   244: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   247: aload #16
/*    */     //   249: astore #17
/*    */     //   251: dup
/*    */     //   252: ifnonnull -> 264
/*    */     //   255: pop
/*    */     //   256: aload #17
/*    */     //   258: ifnull -> 272
/*    */     //   261: goto -> 278
/*    */     //   264: aload #17
/*    */     //   266: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   269: ifeq -> 278
/*    */     //   272: iconst_1
/*    */     //   273: istore #6
/*    */     //   275: iload #6
/*    */     //   277: ireturn
/*    */     //   278: new scala/MatchError
/*    */     //   281: dup
/*    */     //   282: aload_2
/*    */     //   283: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   286: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #39	-> 0
/*    */     //   #40	-> 6
/*    */     //   #39	-> 49
/*    */     //   #41	-> 53
/*    */     //   #39	-> 153
/*    */     //   #42	-> 157
/*    */     //   #39	-> 231
/*    */     //   #43	-> 235
/*    */     //   #39	-> 275
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	287	0	this	Lakka/actor/ChildRestartStats;
/*    */     //   0	287	1	retriesWindow	Lscala/Tuple2;
/*    */     //   37	250	5	retries	I
/*    */     //   96	191	10	retries	I
/*    */     //   166	121	12	x	Lscala/Option;
/*    */     //   200	87	15	window	I
/*    */   }
/*    */   
/*    */   private boolean retriesInWindowOkay(int retries, int window) {
/* 52 */     int retriesDone = maxNrOfRetriesCount() + 1;
/* 53 */     long now = System.nanoTime();
/* 56 */     restartTimeWindowStartNanos_$eq(now);
/* 57 */     long windowStart = (restartTimeWindowStartNanos() == 0L) ? now : 
/* 58 */       restartTimeWindowStartNanos();
/* 59 */     boolean insideWindow = (now - windowStart <= TimeUnit.MILLISECONDS.toNanos(window));
/* 61 */     maxNrOfRetriesCount_$eq(retriesDone);
/* 64 */     maxNrOfRetriesCount_$eq(1);
/* 65 */     restartTimeWindowStartNanos_$eq(now);
/*    */     return insideWindow ? ((retriesDone <= retries)) : true;
/*    */   }
/*    */   
/*    */   public static long apply$default$3() {
/*    */     return ChildRestartStats$.MODULE$.apply$default$3();
/*    */   }
/*    */   
/*    */   public static int apply$default$2() {
/*    */     return ChildRestartStats$.MODULE$.apply$default$2();
/*    */   }
/*    */   
/*    */   public static long $lessinit$greater$default$3() {
/*    */     return ChildRestartStats$.MODULE$.$lessinit$greater$default$3();
/*    */   }
/*    */   
/*    */   public static int $lessinit$greater$default$2() {
/*    */     return ChildRestartStats$.MODULE$.$lessinit$greater$default$2();
/*    */   }
/*    */   
/*    */   public static Function1<Tuple3<ActorRef, Object, Object>, ChildRestartStats> tupled() {
/*    */     return ChildRestartStats$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<ActorRef, Function1<Object, Function1<Object, ChildRestartStats>>> curried() {
/*    */     return ChildRestartStats$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ChildRestartStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */