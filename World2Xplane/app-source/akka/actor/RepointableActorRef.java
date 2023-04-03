/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.MailboxType;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import akka.dispatch.sysmsg.Supervise;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.util.Unsafe;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tEc!B\001\003\001\0211!a\005*fa>Lg\016^1cY\026\f5\r^8s%\0264'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\0342\001A\004\f!\tA\021\"D\001\003\023\tQ!A\001\tBGR|'OU3g/&$\bnQ3mYB\021\001\002D\005\003\033\t\021aBU3q_&tG/\0312mKJ+g\r\003\005\020\001\t\025\r\021\"\001\022\003\031\031\030p\035;f[\016\001Q#\001\n\021\005!\031\022B\001\013\003\005=\t5\r^8s'f\034H/Z7J[Bd\007\002\003\f\001\005\003\005\013\021\002\n\002\017ML8\017^3nA!A\001\004\001BC\002\023\005\021$A\003qe>\0048/F\001\033!\tA1$\003\002\035\005\t)\001K]8qg\"Aa\004\001B\001B\003%!$\001\004qe>\0048\017\t\005\tA\001\021)\031!C\001C\005QA-[:qCR\034\007.\032:\026\003\t\002\"a\t\024\016\003\021R!!\n\003\002\021\021L7\017]1uG\"L!a\n\023\003#5+7o]1hK\022K7\017]1uG\",'\017\003\005*\001\t\005\t\025!\003#\003-!\027n\0359bi\016DWM\035\021\t\021-\002!Q1A\005\0021\n1\"\\1jY\n|\007\020V=qKV\tQ\006\005\002$]%\021q\006\n\002\f\033\006LGNY8y)f\004X\r\003\0052\001\t\005\t\025!\003.\0031i\027-\0337c_b$\026\020]3!\021!\031\004A!b\001\n\003!\024AC:va\026\024h/[:peV\tQ\007\005\002\tm%\021qG\001\002\021\023:$XM\0358bY\006\033Go\034:SK\032D\001\"\017\001\003\002\003\006I!N\001\fgV\004XM\035<jg>\024\b\005\003\005<\001\t\025\r\021\"\001=\003\021\001\030\r\0365\026\003u\002\"\001\003 \n\005}\022!!C!di>\024\b+\031;i\021!\t\005A!A!\002\023i\024!\0029bi\"\004\003\"B\"\001\t\003!\025A\002\037j]&$h\bF\004F\r\036C\025JS&\021\005!\001\001\"B\bC\001\004\021\002\"\002\rC\001\004Q\002\"\002\021C\001\004\021\003\"B\026C\001\004i\003\"B\032C\001\004)\004\"B\036C\001\004i\004\"C'\001\001\004\005\r\021\"\003O\003ay6-\0327m\t>tu\016^\"bY2lU\rR5sK\016$H._\013\002\037B\021\001\002U\005\003#\n\021AaQ3mY\"I1\013\001a\001\002\004%I\001V\001\035?\016,G\016\034#p\035>$8)\0317m\033\026$\025N]3di2Lx\fJ3r)\t)6\f\005\002W36\tqKC\001Y\003\025\0318-\0317b\023\tQvK\001\003V]&$\bb\002/S\003\003\005\raT\001\004q\022\n\004B\0020\001A\003&q*A\r`G\026dG\016R8O_R\034\025\r\0347NK\022K'/Z2uYf\004\003FA/a!\t1\026-\003\002c/\nAao\0347bi&dW\rC\005e\001\001\007\t\031!C\005\035\006Qr\f\\8pWV\004Hi\034(pi\016\013G\016\\'f\t&\024Xm\031;ms\"Ia\r\001a\001\002\004%IaZ\001\037?2|wn[;q\t>tu\016^\"bY2lU\rR5sK\016$H._0%KF$\"!\0265\t\017q+\027\021!a\001\037\"1!\016\001Q!\n=\0131d\0307p_.,\b\017R8O_R\034\025\r\0347NK\022K'/Z2uYf\004\003FA5a\021\025i\007\001\"\001O\003))h\016Z3sYfLgn\032\005\006_\002!\tAT\001\007Y>|7.\0369\t\013E\004AQ\001:\002\021M<\030\r]\"fY2$\"aT:\t\013Q\004\b\031A(\002\t9,\007\020\036\025\003aZ\004\"a\036>\016\003aT!!_,\002\025\005tgn\034;bi&|g.\003\002|q\n9A/Y5me\026\034\007\"B?\001\t\013q\030AC:xCBdun\\6vaR\021qj \005\006ir\004\ra\024\025\003yZDq!!\002\001\t\003\t9!\001\006j]&$\030.\0317ju\026$B!!\003\002\f5\t\001\001\003\005\002\016\005\r\001\031AA\b\003\025\t7/\0378d!\r1\026\021C\005\004\003'9&a\002\"p_2,\027M\034\005\b\003/\001A\021AA\r\003\025\001x.\0338u)\t\tI\001C\004\002\036\001!\t!a\b\002\0179,woQ3mYR\031q*!\t\t\021\005\r\0221\004a\001\003K\t1a\0347e!\rA\021qE\005\004\003S\021!!D+ogR\f'\017^3e\007\026dG\016C\004\002.\001!\t!a\f\002\013M$\030M\035;\025\003UCq!a\r\001\t\003\ty#A\004tkN\004XM\0343\t\017\005]\002\001\"\001\002:\0051!/Z:v[\026$2!VA\036\021!\ti$!\016A\002\005}\022aD2bkN,GMQ=GC&dWO]3\021\t\005\005\023\021\013\b\005\003\007\niE\004\003\002F\005-SBAA$\025\r\tI\005E\001\007yI|w\016\036 \n\003aK1!a\024X\003\035\001\030mY6bO\026LA!a\025\002V\tIA\013\033:po\006\024G.\032\006\004\003\037:\006bBA-\001\021\005\021qF\001\005gR|\007\017C\004\002^\001!\t!a\030\002\017I,7\017^1siR\031Q+!\031\t\021\005\r\0241\fa\001\003\tQaY1vg\026Dq!a\032\001\t\003\tI'A\005jgN#\030M\035;fIV\021\021q\002\005\b\003[\002A\021AA5\0031I7\017V3s[&t\027\r^3eQ!\tY'!\035\002x\005m\004c\001,\002t%\031\021QO,\003\025\021,\007O]3dCR,G-\t\002\002z\0051Tk]3!G>tG/\032=u]]\fGo\0315)C\016$xN]\025!C:$\007E]3dK&4X\r\t+fe6Lg.\031;fI\"\n7\r^8sS\005\022\021QP\001\004e9\022\004bBAA\001\021\005\0211Q\001\taJ|g/\0333feV\021\021Q\021\t\004\021\005\035\025bAAE\005\t\001\022i\031;peJ+g\r\025:pm&$WM\035\005\b\003\033\003A\021AA5\003\035I7\017T8dC2Da!!%\001\t\003!\024!C4fiB\013'/\0328u\021\035\t)\n\001C\001\003/\013\001bZ3u\007\"LG\016\032\013\004k\005e\005\002CAN\003'\003\r!!(\002\t9\fW.\032\t\007\003\003\ny*a)\n\t\005\005\026Q\013\002\t\023R,'/\031;peB!\021QUAV\035\r1\026qU\005\004\003S;\026A\002)sK\022,g-\003\003\002.\006=&AB*ue&twMC\002\002*^Cq!a-\001\t\003\t),\001\bhKR\034\026N\\4mK\016C\027\016\0343\025\007U\n9\f\003\005\002\034\006E\006\031AAR\021\035\tY\f\001C\001\003{\013\001b\0315jY\022\024XM\\\013\003\003\003b!!1\002L\006=WBAAb\025\021\t)-a2\002\023%lW.\036;bE2,'bAAe/\006Q1m\0347mK\016$\030n\0348\n\t\0055\0271\031\002\t\023R,'/\0312mKB\031\001\"!5\n\007\005M'A\001\005BGR|'OU3g\021\035\t9\016\001C\001\0033\fQ\001\n2b]\036$B!a7\002bR\031Q+!8\t\025\005}\027Q\033I\001\002\b\ty-\001\004tK:$WM\035\005\t\003G\f)\0161\001\002f\0069Q.Z:tC\036,\007c\001,\002h&\031\021\021^,\003\007\005s\027\020C\004\002n\002!\t!a<\002#M,g\016Z*zgR,W.T3tg\006<W\rF\002V\003cD\001\"a9\002l\002\007\0211\037\t\005\003k\fY0\004\002\002x*\031\021\021 \023\002\rML8/\\:h\023\021\ti0a>\003\033MK8\017^3n\033\026\0348/Y4f\021\035\021\t\001\001C\t\005\007\tAb\036:ji\026\024V\r\0357bG\026$\"A!\002\021\007Y\0239!C\002\003\n]\023a!\0218z%\0264\007FBA\000\005\033\0219\003E\003W\005\037\021\031\"C\002\003\022]\023a\001\0365s_^\034\b\003\002B\013\005/a\001\001B\004\003\032\001\021\rAa\007\003\003Q\013BA!\b\003$A\031aKa\b\n\007\t\005rKA\004O_RD\027N\\4\021\t\t\025\022\021\013\b\004-\00653E\001B\025!\021\021YC!\016\016\005\t5\"\002\002B\030\005c\t!![8\013\005\tM\022\001\0026bm\006LAAa\016\003.\t)rJ\0316fGR\034FO]3b[\026C8-\0329uS>t\007\"\003B\036\001E\005I\021\tB\037\003=!#-\0318hI\021,g-Y;mi\022\022D\003\002B \005\037RC!a4\003B-\022!1\t\t\005\005\013\022Y%\004\002\003H)\031!\021\n=\002\023Ut7\r[3dW\026$\027\002\002B'\005\017\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021!\t\031O!\017A\002\005\025\b")
/*     */ public class RepointableActorRef extends ActorRefWithCell implements RepointableRef {
/*     */   private final ActorSystemImpl system;
/*     */   
/*     */   private final Props props;
/*     */   
/*     */   private final MessageDispatcher dispatcher;
/*     */   
/*     */   private final MailboxType mailboxType;
/*     */   
/*     */   private final InternalActorRef supervisor;
/*     */   
/*     */   private final ActorPath path;
/*     */   
/*     */   private volatile Cell _cellDoNotCallMeDirectly;
/*     */   
/*     */   private volatile Cell _lookupDoNotCallMeDirectly;
/*     */   
/*     */   public ActorSystemImpl system() {
/*  31 */     return this.system;
/*     */   }
/*     */   
/*     */   public RepointableActorRef(ActorSystemImpl system, Props props, MessageDispatcher dispatcher, MailboxType mailboxType, InternalActorRef supervisor, ActorPath path) {}
/*     */   
/*     */   public Props props() {
/*  32 */     return this.props;
/*     */   }
/*     */   
/*     */   public MessageDispatcher dispatcher() {
/*  33 */     return this.dispatcher;
/*     */   }
/*     */   
/*     */   public MailboxType mailboxType() {
/*  34 */     return this.mailboxType;
/*     */   }
/*     */   
/*     */   public InternalActorRef supervisor() {
/*  35 */     return this.supervisor;
/*     */   }
/*     */   
/*     */   public ActorPath path() {
/*  36 */     return this.path;
/*     */   }
/*     */   
/*     */   private Cell _cellDoNotCallMeDirectly() {
/*  51 */     return this._cellDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   private void _cellDoNotCallMeDirectly_$eq(Cell x$1) {
/*  51 */     this._cellDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   private Cell _lookupDoNotCallMeDirectly() {
/*  52 */     return this._lookupDoNotCallMeDirectly;
/*     */   }
/*     */   
/*     */   private void _lookupDoNotCallMeDirectly_$eq(Cell x$1) {
/*  52 */     this._lookupDoNotCallMeDirectly = x$1;
/*     */   }
/*     */   
/*     */   public Cell underlying() {
/*  54 */     return (Cell)Unsafe.instance.getObjectVolatile(this, AbstractActorRef.cellOffset);
/*     */   }
/*     */   
/*     */   public Cell lookup() {
/*  55 */     return (Cell)Unsafe.instance.getObjectVolatile(this, AbstractActorRef.lookupOffset);
/*     */   }
/*     */   
/*     */   public final Cell swapCell(Cell next) {
/*     */     while (true) {
/*  58 */       Cell old = underlying();
/*  59 */       if (Unsafe.instance.compareAndSwapObject(this, AbstractActorRef.cellOffset, old, next))
/*  59 */         return old; 
/*  59 */       next = next;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Cell swapLookup(Cell next) {
/*     */     while (true) {
/*  63 */       Cell old = lookup();
/*  64 */       if (Unsafe.instance.compareAndSwapObject(this, AbstractActorRef.lookupOffset, old, next))
/*  64 */         return old; 
/*  64 */       next = next;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RepointableActorRef initialize(boolean async) {
/*  77 */     Cell cell = underlying();
/*  78 */     if (cell == null) {
/*  79 */       swapCell(new UnstartedCell(system(), this, props(), supervisor()));
/*  80 */       swapLookup(underlying());
/*  81 */       supervisor().sendSystemMessage((SystemMessage)new Supervise(this, async));
/*  82 */       async ? BoxedUnit.UNIT : point();
/*  83 */       return this;
/*     */     } 
/*  84 */     throw new IllegalStateException("initialize called more than once!");
/*     */   }
/*     */   
/*     */   public RepointableActorRef point() {
/*     */     RepointableActorRef repointableActorRef;
/*  94 */     Cell cell = underlying();
/*  95 */     if (cell instanceof UnstartedCell) {
/*  95 */       UnstartedCell unstartedCell = (UnstartedCell)cell;
/* 103 */       Cell cell1 = newCell(unstartedCell);
/* 104 */       swapLookup(cell1);
/* 105 */       cell1.start();
/* 106 */       unstartedCell.replaceWith(cell1);
/* 107 */       repointableActorRef = this;
/*     */     } else {
/* 108 */       if (cell == null)
/* 108 */         throw new IllegalStateException("underlying cell is null"); 
/* 109 */       repointableActorRef = this;
/*     */     } 
/*     */     return repointableActorRef;
/*     */   }
/*     */   
/*     */   public Cell newCell(UnstartedCell old) {
/* 117 */     return (new ActorCell(system(), this, props(), dispatcher(), supervisor())).init(false, mailboxType());
/*     */   }
/*     */   
/*     */   public void start() {}
/*     */   
/*     */   public void suspend() {
/* 121 */     underlying().suspend();
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 123 */     underlying().resume(causedByFailure);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 125 */     underlying().stop();
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 127 */     underlying().restart(cause);
/*     */   }
/*     */   
/*     */   public boolean isStarted() {
/*     */     boolean bool;
/* 129 */     Cell cell = underlying();
/* 130 */     if (cell instanceof UnstartedCell) {
/* 130 */       bool = false;
/*     */     } else {
/* 131 */       if (cell == null)
/* 131 */         throw new IllegalStateException("isStarted called before initialized"); 
/* 132 */       bool = true;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 135 */     return underlying().isTerminated();
/*     */   }
/*     */   
/*     */   public ActorRefProvider provider() {
/* 137 */     return system().provider();
/*     */   }
/*     */   
/*     */   public boolean isLocal() {
/* 139 */     return underlying().isLocal();
/*     */   }
/*     */   
/*     */   public InternalActorRef getParent() {
/* 141 */     return underlying().parent();
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(Iterator name) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokeinterface hasNext : ()Z
/*     */     //   6: ifeq -> 269
/*     */     //   9: aload_1
/*     */     //   10: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   15: checkcast java/lang/String
/*     */     //   18: astore_2
/*     */     //   19: ldc '..'
/*     */     //   21: aload_2
/*     */     //   22: astore_3
/*     */     //   23: dup
/*     */     //   24: ifnonnull -> 35
/*     */     //   27: pop
/*     */     //   28: aload_3
/*     */     //   29: ifnull -> 42
/*     */     //   32: goto -> 55
/*     */     //   35: aload_3
/*     */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   39: ifeq -> 55
/*     */     //   42: aload_0
/*     */     //   43: invokevirtual getParent : ()Lakka/actor/InternalActorRef;
/*     */     //   46: aload_1
/*     */     //   47: invokevirtual getChild : (Lscala/collection/Iterator;)Lakka/actor/InternalActorRef;
/*     */     //   50: astore #4
/*     */     //   52: goto -> 254
/*     */     //   55: ldc ''
/*     */     //   57: aload_2
/*     */     //   58: astore #5
/*     */     //   60: dup
/*     */     //   61: ifnonnull -> 73
/*     */     //   64: pop
/*     */     //   65: aload #5
/*     */     //   67: ifnull -> 81
/*     */     //   70: goto -> 91
/*     */     //   73: aload #5
/*     */     //   75: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   78: ifeq -> 91
/*     */     //   81: aload_0
/*     */     //   82: aload_1
/*     */     //   83: invokevirtual getChild : (Lscala/collection/Iterator;)Lakka/actor/InternalActorRef;
/*     */     //   86: astore #4
/*     */     //   88: goto -> 254
/*     */     //   91: getstatic akka/actor/ActorCell$.MODULE$ : Lakka/actor/ActorCell$;
/*     */     //   94: aload_2
/*     */     //   95: invokevirtual splitNameAndUid : (Ljava/lang/String;)Lscala/Tuple2;
/*     */     //   98: astore #7
/*     */     //   100: aload #7
/*     */     //   102: ifnull -> 259
/*     */     //   105: aload #7
/*     */     //   107: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   110: checkcast java/lang/String
/*     */     //   113: astore #8
/*     */     //   115: aload #7
/*     */     //   117: invokevirtual _2$mcI$sp : ()I
/*     */     //   120: istore #9
/*     */     //   122: new scala/Tuple2
/*     */     //   125: dup
/*     */     //   126: aload #8
/*     */     //   128: iload #9
/*     */     //   130: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   133: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   136: astore #10
/*     */     //   138: aload #10
/*     */     //   140: astore #6
/*     */     //   142: aload #6
/*     */     //   144: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   147: checkcast java/lang/String
/*     */     //   150: astore #11
/*     */     //   152: aload #6
/*     */     //   154: invokevirtual _2$mcI$sp : ()I
/*     */     //   157: istore #12
/*     */     //   159: aload_0
/*     */     //   160: invokevirtual lookup : ()Lakka/actor/Cell;
/*     */     //   163: aload #11
/*     */     //   165: invokeinterface getChildByName : (Ljava/lang/String;)Lscala/Option;
/*     */     //   170: astore #13
/*     */     //   172: aload #13
/*     */     //   174: instanceof scala/Some
/*     */     //   177: ifeq -> 245
/*     */     //   180: aload #13
/*     */     //   182: checkcast scala/Some
/*     */     //   185: astore #14
/*     */     //   187: aload #14
/*     */     //   189: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   192: checkcast akka/actor/ChildStats
/*     */     //   195: astore #15
/*     */     //   197: aload #15
/*     */     //   199: instanceof akka/actor/ChildRestartStats
/*     */     //   202: ifeq -> 245
/*     */     //   205: aload #15
/*     */     //   207: checkcast akka/actor/ChildRestartStats
/*     */     //   210: astore #16
/*     */     //   212: iload #12
/*     */     //   214: iconst_0
/*     */     //   215: if_icmpeq -> 228
/*     */     //   218: iload #12
/*     */     //   220: aload #16
/*     */     //   222: invokevirtual uid : ()I
/*     */     //   225: if_icmpne -> 245
/*     */     //   228: aload #16
/*     */     //   230: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   233: checkcast akka/actor/InternalActorRef
/*     */     //   236: aload_1
/*     */     //   237: invokevirtual getChild : (Lscala/collection/Iterator;)Lakka/actor/InternalActorRef;
/*     */     //   240: astore #17
/*     */     //   242: goto -> 250
/*     */     //   245: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   248: astore #17
/*     */     //   250: aload #17
/*     */     //   252: astore #4
/*     */     //   254: aload #4
/*     */     //   256: goto -> 270
/*     */     //   259: new scala/MatchError
/*     */     //   262: dup
/*     */     //   263: aload #7
/*     */     //   265: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   268: athrow
/*     */     //   269: aload_0
/*     */     //   270: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #144	-> 0
/*     */     //   #145	-> 9
/*     */     //   #146	-> 19
/*     */     //   #147	-> 55
/*     */     //   #149	-> 91
/*     */     //   #150	-> 159
/*     */     //   #151	-> 172
/*     */     //   #152	-> 228
/*     */     //   #153	-> 245
/*     */     //   #150	-> 250
/*     */     //   #148	-> 252
/*     */     //   #145	-> 254
/*     */     //   #149	-> 259
/*     */     //   #156	-> 269
/*     */     //   #144	-> 270
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	271	0	this	Lakka/actor/RepointableActorRef;
/*     */     //   0	271	1	name	Lscala/collection/Iterator;
/*     */     //   115	156	8	childName	Ljava/lang/String;
/*     */     //   122	149	9	uid	I
/*     */     //   152	100	11	childName	Ljava/lang/String;
/*     */     //   159	93	12	uid	I
/*     */     //   197	74	15	crs	Lakka/actor/ChildStats;
/*     */   }
/*     */   
/*     */   public InternalActorRef getSingleChild(String name) {
/* 162 */     return lookup().getSingleChild(name);
/*     */   }
/*     */   
/*     */   public Iterable<ActorRef> children() {
/* 164 */     return lookup().childrenRefs().children();
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 166 */     underlying().sendMessage(message, sender);
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/* 166 */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 168 */     underlying().sendSystemMessage(message);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 171 */     return SerializedActorRef$.MODULE$.apply(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\RepointableActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */