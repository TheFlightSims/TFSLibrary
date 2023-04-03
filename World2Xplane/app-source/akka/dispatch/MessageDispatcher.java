/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Cell;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.event.EventStream;
/*     */ import akka.event.Logging;
/*     */ import akka.util.Index;
/*     */ import akka.util.Unsafe;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.ExecutionContextExecutor;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\ruAB\001\003\021\003!a!A\tNKN\034\030mZ3ESN\004\030\r^2iKJT!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027\r\005\002\b\0215\t!A\002\004\n\005!\005AA\003\002\022\033\026\0348/Y4f\t&\034\b/\031;dQ\026\0248C\001\005\f!\taq\"D\001\016\025\005q\021!B:dC2\f\027B\001\t\016\005\031\te.\037*fM\")!\003\003C\001)\0051A(\0338jiz\032\001\001F\001\007\021\0351\002B1A\005\002]\t1\"\026(T\007\"+E)\026'F\tV\t\001\004\005\002\r3%\021!$\004\002\004\023:$\bB\002\017\tA\003%\001$\001\007V\035N\033\005*\022#V\031\026#\005\005C\004\037\021\t\007I\021A\f\002\023M\033\005*\022#V\031\026#\005B\002\021\tA\003%\001$\001\006T\007\"+E)\026'F\t\002BqA\t\005C\002\023\005q#A\006S\013N\033\005*\022#V\031\026#\005B\002\023\tA\003%\001$\001\007S\013N\033\005*\022#V\031\026#\005\005C\004'\021\t\007IQA\024\002\013\021,'-^4\026\003!z\021!K\r\002\001!11\006\003Q\001\016!\na\001Z3ck\036\004\003\002C\027\t\021\013\007I\021\001\030\002\r\005\034Go\034:t+\005y\003#\002\0314k\tUT\"A\031\013\005I\"\021\001B;uS2L!\001N\031\003\013%sG-\032=\021\005\0351d!B\005\003\003\00394\003\002\0349wy\002\"aB\035\n\005i\022!!G!cgR\024\030m\031;NKN\034\030mZ3ESN\004\030\r^2iKJ\004\"a\002\037\n\005u\022!\001\005\"bi\016D\027N\\4Fq\026\034W\017^8s!\ty$)D\001A\025\t\tU\"\001\006d_:\034WO\035:f]RL!a\021!\0031\025CXmY;uS>t7i\0348uKb$X\t_3dkR|'\017\003\005Fm\t\025\r\021\"\001G\0031\031wN\0344jOV\024\030\r^8s+\0059\005CA\004I\023\tI%AA\017NKN\034\030mZ3ESN\004\030\r^2iKJ\034uN\0344jOV\024\030\r^8s\021!YeG!A!\002\0239\025!D2p]\032Lw-\036:bi>\024\b\005C\003\023m\021\005Q\n\006\0026\035\")Q\t\024a\001\017\"9\001K\016b\001\n\003\t\026!C7bS2\024w\016_3t+\005\021\006CA\004T\023\t!&AA\005NC&d'm\034=fg\"1aK\016Q\001\nI\013!\"\\1jY\n|\0070Z:!\021\035AfG1A\005\002e\0131\"\032<f]R\034FO]3b[V\t!\f\005\002\\=6\tAL\003\002^\t\005)QM^3oi&\021q\f\030\002\f\013Z,g\016^*ue\026\fW\016\003\004bm\001\006IAW\001\rKZ,g\016^*ue\026\fW\016\t\005\nGZ\002\r\021!Q!\n\021\fqdX5oQ\006\024\027\016^1oiN$uNT8u\007\006dG.T3ESJ,7\r\0367z!\taQ-\003\002g\033\t!Aj\0348hQ\t\021\007\016\005\002\rS&\021!.\004\002\tm>d\027\r^5mK\"IAN\016a\001\002\003\006K\001G\001%?NDW\017\0363po:\0346\r[3ek2,Gi\034(pi\016\013G\016\\'f\t&\024Xm\031;ms\"\0221\016\033\005\006_Z\"i\001]\001\017C\022$\027J\0345bE&$\030M\034;t)\t!\027\017C\003s]\002\007A-A\002bI\022D#A\034;\021\005UDX\"\001<\013\005]l\021AC1o]>$\030\r^5p]&\021\021P\036\002\bi\006LGN]3d\021\025Yh\007\"\002}\003-Ig\016[1cSR\fg\016^:\026\003\021DQA \034\005\016]\t\001c\0355vi\022|wO\\*dQ\026$W\017\\3\t\017\005\005a\007\"\004\002\004\0051R\017\0353bi\026\034\006.\036;e_^t7k\0315fIVdW\r\006\004\002\006\005-\021q\002\t\004\031\005\035\021bAA\005\033\t9!i\\8mK\006t\007BBA\007\002\007\001$\001\004fqB,7\r\036\005\007\003#y\b\031\001\r\002\rU\004H-\031;f\021!\t)B\016D\t\t\005]\021!D2sK\006$X-T1jY\n|\007\020\006\004\002\032\005}\021Q\006\t\004\017\005m\021bAA\017\005\t9Q*Y5mE>D\b\002CA\021\003'\001\r!a\t\002\013\005\034Go\034:\021\t\005\025\022\021F\007\003\003OQ1!!\t\005\023\021\tY#a\n\003\t\r+G\016\034\005\t\003_\t\031\0021\001\0022\005YQ.Y5mE>DH+\0379f!\r9\0211G\005\004\003k\021!aC'bS2\024w\016\037+za\026Dq!!\0177\r\003\tY$\001\002jIV\021\021Q\b\t\005\003\t)ED\002\r\003\003J1!a\021\016\003\031\001&/\0323fM&!\021qIA%\005\031\031FO]5oO*\031\0211I\007\t\017\0055c\007\"\002\002P\0051\021\r\036;bG\"$B!!\025\002XA\031A\"a\025\n\007\005USB\001\003V]&$\b\002CA\021\003\027\002\r!!\027\021\t\005\025\0221L\005\005\003;\n9CA\005BGR|'oQ3mY\"9\021\021\r\034\005\006\005\r\024A\0023fi\006\034\007\016\006\003\002R\005\025\004\002CA\021\003?\002\r!!\027\t\017\005%d\007\"\026\002l\005\001RO\0342bi\016DW\rZ#yK\016,H/\032\013\005\003#\ni\007\003\005\002p\005\035\004\031AA9\003\005\021\b\003BA:\003{j!!!\036\013\t\005]\024\021P\001\005Y\006twM\003\002\002|\005!!.\031<b\023\021\ty(!\036\003\021I+hN\\1cY\026Dq!a!7\t\003\n))A\007sKB|'\017\036$bS2,(/\032\013\005\003#\n9\t\003\005\002\n\006\005\005\031AAF\003\005!\b\003BAG\003;sA!a$\002\032:!\021\021SAL\033\t\t\031JC\002\002\026N\ta\001\020:p_Rt\024\"\001\b\n\007\005mU\"A\004qC\016\\\027mZ3\n\t\005}\025\021\025\002\n)\"\024xn^1cY\026T1!a'\016\021\035\t)K\016C\007\003O\013A%\0334TK:\034\030N\0317f)>$unU8UQ\026t7k\0315fIVdWm\0255vi\022|wO\034\013\003\003#B3!a)u\021\035\tiK\016C\005\003O\013ac]2iK\022,H.Z*ikR$wn\0368BGRLwN\034\005\n\003c3$\031!C\007\003g\0131\002^1tW\016cW-\0318vaV\021\021Q\027\t\006\031\005]\026\021K\005\004\003sk!!\003$v]\016$\030n\03481\021!\tiL\016Q\001\016\005U\026\001\004;bg.\034E.Z1okB\004\003\002CAam\021EA!a1\002\021I,w-[:uKJ$B!!\025\002F\"A\021\021EA`\001\004\tI\006\003\005\002JZ\"\t\002BAf\003))hN]3hSN$XM\035\013\005\003#\ni\r\003\005\002\"\005\035\007\031AA-\021%\t\tN\016b\001\n\023\t\031.\001\btQV$Hm\\<o\003\016$\030n\0348\026\005\005U'CBAl\003?\f\tHB\004\002Z\006m\007!!6\003\031q\022XMZ5oK6,g\016\036 \t\021\005ug\007)A\005\003+\fqb\0355vi\022|wO\\!di&|g\016\t\t\005\003g\n\t/\003\003\002d\006U$AB(cU\026\034G\017\003\005\002hZ2\t\002BAu\003=\031\b.\036;e_^tG+[7f_V$XCAAv!\021\ti/a=\016\005\005=(bAAy\001\006AA-\036:bi&|g.\003\003\002v\006=(A\004$j]&$X\rR;sCRLwN\034\005\t\003s4D\021\003\003\002|\00691/^:qK:$G\003BA)\003{D\001\"!\t\002x\002\007\021\021\f\005\t\005\0031D\021\003\003\003\004\0051!/Z:v[\026$B!!\025\003\006!A\021\021EA\000\001\004\tI\006\003\005\003\nY2\t\002\002B\006\0039\031\030p\035;f[\022K7\017]1uG\"$b!!\025\003\016\tE\001\002\003B\b\005\017\001\r!!\027\002\021I,7-Z5wKJD\001Ba\005\003\b\001\007!QC\001\013S:4xnY1uS>t\007\003\002B\f\005;i!A!\007\013\007\tm!!\001\004tsNl7oZ\005\005\005?\021IBA\007TsN$X-\\'fgN\fw-\032\005\b\007Y2\t\002\002B\022)\031\t\tF!\n\003(!A!q\002B\021\001\004\tI\006\003\005\003\024\t\005\002\031\001B\025!\r9!1F\005\004\005[\021!\001C#om\026dw\016]3\t\021\tEbG\"\005\005\005g\tAC]3hSN$XM\035$pe\026CXmY;uS>tG\003CA\003\005k\021ID!\020\t\021\t]\"q\006a\001\0033\tA!\0342pq\"A!1\bB\030\001\004\t)!\001\biCNlUm]:bO\026D\025N\034;\t\021\t}\"q\006a\001\003\013\tA\003[1t'f\034H/Z7NKN\034\030mZ3IS:$\bb\002B\"m\031EAaF\001\013i\"\024x.^4iaV$\b\002\003B$m\031EAA!\023\002-QD'o\\;hQB,H\017R3bI2Lg.\032+j[\026,\"Aa\023\021\t\0055(QJ\005\005\005\037\nyO\001\005EkJ\fG/[8o\021)\021\031F\016b\001\n+!!QK\001 SN$\006N]8vO\"\004X\017\036#fC\022d\027N\\3US6,G)\0324j]\026$WCAA\003\021!\021IF\016Q\001\016\005\025\021\001I5t)\"\024x.^4iaV$H)Z1eY&tW\rV5nK\022+g-\0338fI\002BCAa\026\003^A\031ABa\030\n\007\t\005TB\001\004j]2Lg.\032\005\t\005K2d\021\003\003\003h\005YQ\r_3dkR,G+Y:l)\021\t\tF!\033\t\021\tM!1\ra\001\005W\0022a\002B7\023\r\021yG\001\002\017)\006\0348.\0238w_\016\fG/[8o\021!\021\031H\016D\t\t\005\035\026\001C:ikR$wn\0368\021\t\005\025\"qO\005\005\005s\n9C\001\005BGR|'OU3g\021%\021i\b\003E\001B\003&q&A\004bGR|'o\035\021\t\017\t\005\005\002\"\001\002(\006Y\001O]5oi\006\033Go\034:t\001")
/*     */ public abstract class MessageDispatcher extends AbstractMessageDispatcher implements BatchingExecutor, ExecutionContextExecutor {
/*     */   private final MessageDispatcherConfigurator configurator;
/*     */   
/*     */   private final Mailboxes mailboxes;
/*     */   
/*     */   private final EventStream eventStream;
/*     */   
/*     */   private volatile long _inhabitantsDoNotCallMeDirectly;
/*     */   
/*     */   private volatile int _shutdownScheduleDoNotCallMeDirectly;
/*     */   
/*     */   private final Function0<BoxedUnit> taskCleanup;
/*     */   
/*     */   private final Runnable shutdownAction;
/*     */   
/*     */   private final boolean isThroughputDeadlineTimeDefined;
/*     */   
/*     */   private final ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal;
/*     */   
/*     */   public static class MessageDispatcher$$anonfun$actors$1 extends AbstractFunction2<ActorRef, ActorRef, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(ActorRef x$1, ActorRef x$2) {
/*  64 */       return x$1.compareTo(x$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public ExecutionContext prepare() {
/*  85 */     return ExecutionContext.class.prepare((ExecutionContext)this);
/*     */   }
/*     */   
/*     */   public ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal() {
/*  85 */     return this.akka$dispatch$BatchingExecutor$$_tasksLocal;
/*     */   }
/*     */   
/*     */   public void akka$dispatch$BatchingExecutor$_setter_$akka$dispatch$BatchingExecutor$$_tasksLocal_$eq(ThreadLocal<List<Runnable>> x$1) {
/*  85 */     this.akka$dispatch$BatchingExecutor$$_tasksLocal = x$1;
/*     */   }
/*     */   
/*     */   public void execute(Runnable runnable) {
/*  85 */     BatchingExecutor$class.execute(this, runnable);
/*     */   }
/*     */   
/*     */   public boolean batchable(Runnable runnable) {
/*  85 */     return BatchingExecutor$class.batchable(this, runnable);
/*     */   }
/*     */   
/*     */   public MessageDispatcherConfigurator configurator() {
/*  85 */     return this.configurator;
/*     */   }
/*     */   
/*     */   public MessageDispatcher(MessageDispatcherConfigurator configurator) {
/*  85 */     BatchingExecutor$class.$init$(this);
/*  85 */     ExecutionContext.class.$init$((ExecutionContext)this);
/*  91 */     this.mailboxes = configurator.prerequisites().mailboxes();
/*  92 */     this.eventStream = configurator.prerequisites().eventStream();
/* 181 */     this.taskCleanup = (Function0<BoxedUnit>)new $anonfun$1(this);
/* 206 */     this.shutdownAction = new $anon$2(this);
/* 286 */     this.isThroughputDeadlineTimeDefined = (throughputDeadlineTime().toMillis() > 0L);
/*     */   }
/*     */   
/*     */   public Mailboxes mailboxes() {
/*     */     return this.mailboxes;
/*     */   }
/*     */   
/*     */   public EventStream eventStream() {
/*     */     return this.eventStream;
/*     */   }
/*     */   
/*     */   public final long akka$dispatch$MessageDispatcher$$addInhabitants(long add) {
/*     */     while (true) {
/*     */       long c = inhabitants();
/*     */       long r = c + add;
/*     */       if (r < 0L) {
/*     */         IllegalStateException e = new IllegalStateException("ACTOR SYSTEM CORRUPTED!!! A dispatcher can't have less than 0 inhabitants!");
/*     */         reportFailure(e);
/*     */         throw e;
/*     */       } 
/*     */       if (Unsafe.instance.compareAndSwapLong(this, AbstractMessageDispatcher.inhabitantsOffset, c, r))
/*     */         return r; 
/*     */       add = add;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final long inhabitants() {
/*     */     return Unsafe.instance.getLongVolatile(this, AbstractMessageDispatcher.inhabitantsOffset);
/*     */   }
/*     */   
/*     */   public final int akka$dispatch$MessageDispatcher$$shutdownSchedule() {
/*     */     return Unsafe.instance.getIntVolatile(this, AbstractMessageDispatcher.shutdownScheduleOffset);
/*     */   }
/*     */   
/*     */   public final boolean akka$dispatch$MessageDispatcher$$updateShutdownSchedule(int expect, int update) {
/*     */     return Unsafe.instance.compareAndSwapInt(this, AbstractMessageDispatcher.shutdownScheduleOffset, expect, update);
/*     */   }
/*     */   
/*     */   public final void attach(ActorCell actor) {
/*     */     register(actor);
/*     */     registerForExecution(actor.mailbox(), false, true);
/*     */   }
/*     */   
/*     */   public final void detach(ActorCell actor) {
/*     */     try {
/*     */       unregister(actor);
/*     */       return;
/*     */     } finally {
/*     */       akka$dispatch$MessageDispatcher$$ifSensibleToDoSoThenScheduleShutdown();
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void unbatchedExecute(Runnable r) {
/*     */     TaskInvocation invocation = new TaskInvocation(eventStream(), r, taskCleanup());
/*     */     akka$dispatch$MessageDispatcher$$addInhabitants(1L);
/*     */     try {
/*     */       return;
/*     */     } finally {
/*     */       akka$dispatch$MessageDispatcher$$addInhabitants(-1L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reportFailure(Throwable t) {
/*     */     Throwable throwable = t;
/*     */     if (throwable instanceof Logging.LogEventException) {
/*     */       Logging.LogEventException logEventException = (Logging.LogEventException)throwable;
/*     */       eventStream().publish(logEventException.event());
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/*     */       eventStream().publish(new Logging.Error(t, getClass().getName(), getClass(), t.getMessage()));
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void akka$dispatch$MessageDispatcher$$ifSensibleToDoSoThenScheduleShutdown() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual inhabitants : ()J
/*     */     //   4: lconst_0
/*     */     //   5: lcmp
/*     */     //   6: ifgt -> 122
/*     */     //   9: aload_0
/*     */     //   10: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */     //   13: istore_2
/*     */     //   14: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   17: invokevirtual UNSCHEDULED : ()I
/*     */     //   20: iload_2
/*     */     //   21: if_icmpne -> 54
/*     */     //   24: aload_0
/*     */     //   25: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   28: invokevirtual UNSCHEDULED : ()I
/*     */     //   31: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   34: invokevirtual SCHEDULED : ()I
/*     */     //   37: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */     //   40: ifeq -> 0
/*     */     //   43: aload_0
/*     */     //   44: invokevirtual akka$dispatch$MessageDispatcher$$scheduleShutdownAction : ()V
/*     */     //   47: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   50: astore_3
/*     */     //   51: goto -> 104
/*     */     //   54: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   57: invokevirtual SCHEDULED : ()I
/*     */     //   60: iload_2
/*     */     //   61: if_icmpne -> 90
/*     */     //   64: aload_0
/*     */     //   65: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   68: invokevirtual SCHEDULED : ()I
/*     */     //   71: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   74: invokevirtual RESCHEDULED : ()I
/*     */     //   77: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */     //   80: ifeq -> 0
/*     */     //   83: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   86: astore_3
/*     */     //   87: goto -> 104
/*     */     //   90: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */     //   93: invokevirtual RESCHEDULED : ()I
/*     */     //   96: iload_2
/*     */     //   97: if_icmpne -> 110
/*     */     //   100: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   103: astore_3
/*     */     //   104: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   107: goto -> 125
/*     */     //   110: new scala/MatchError
/*     */     //   113: dup
/*     */     //   114: iload_2
/*     */     //   115: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   118: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   121: athrow
/*     */     //   122: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   125: pop
/*     */     //   126: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #160	-> 0
/*     */     //   #161	-> 14
/*     */     //   #162	-> 24
/*     */     //   #164	-> 54
/*     */     //   #165	-> 64
/*     */     //   #167	-> 90
/*     */     //   #160	-> 104
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	127	0	this	Lakka/dispatch/MessageDispatcher;
/*     */   }
/*     */   
/*     */   public void akka$dispatch$MessageDispatcher$$scheduleShutdownAction() {
/*     */     try {
/*     */       configurator().prerequisites().scheduler().scheduleOnce(shutdownTimeout(), shutdownAction(), new MessageDispatcher$$anon$3(this));
/*     */     } catch (IllegalStateException illegalStateException) {
/*     */       shutdown();
/*     */     } 
/*     */   }
/*     */   
/*     */   public class MessageDispatcher$$anon$3 implements ExecutionContext {
/*     */     public ExecutionContext prepare() {
/*     */       return ExecutionContext.class.prepare(this);
/*     */     }
/*     */     
/*     */     public MessageDispatcher$$anon$3(MessageDispatcher $outer) {
/*     */       ExecutionContext.class.$init$(this);
/*     */     }
/*     */     
/*     */     public void execute(Runnable runnable) {
/*     */       runnable.run();
/*     */     }
/*     */     
/*     */     public void reportFailure(Throwable t) {
/*     */       this.$outer.reportFailure(t);
/*     */     }
/*     */   }
/*     */   
/*     */   private final Function0<BoxedUnit> taskCleanup() {
/*     */     return this.taskCleanup;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       if (this.$outer.akka$dispatch$MessageDispatcher$$addInhabitants(-1L) == 0L)
/*     */         this.$outer.akka$dispatch$MessageDispatcher$$ifSensibleToDoSoThenScheduleShutdown(); 
/*     */     }
/*     */     
/*     */     public $anonfun$1(MessageDispatcher $outer) {}
/*     */   }
/*     */   
/*     */   public void register(ActorCell actor) {
/*     */     akka$dispatch$MessageDispatcher$$addInhabitants(1L);
/*     */   }
/*     */   
/*     */   public void unregister(ActorCell actor) {
/*     */     akka$dispatch$MessageDispatcher$$addInhabitants(-1L);
/*     */     Mailbox mailBox = actor.swapMailbox(mailboxes().deadLetterMailbox());
/*     */     mailBox.becomeClosed();
/*     */     mailBox.cleanUp();
/*     */   }
/*     */   
/*     */   private Runnable shutdownAction() {
/*     */     return this.shutdownAction;
/*     */   }
/*     */   
/*     */   public class $anon$2 implements Runnable {
/*     */     public $anon$2(MessageDispatcher $outer) {}
/*     */     
/*     */     public final void run() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   4: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */       //   7: istore_2
/*     */       //   8: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   11: invokevirtual SCHEDULED : ()I
/*     */       //   14: iload_2
/*     */       //   15: if_icmpne -> 73
/*     */       //   18: aload_0
/*     */       //   19: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   22: invokevirtual inhabitants : ()J
/*     */       //   25: lconst_0
/*     */       //   26: lcmp
/*     */       //   27: ifne -> 43
/*     */       //   30: aload_0
/*     */       //   31: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   34: invokevirtual shutdown : ()V
/*     */       //   37: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   40: goto -> 46
/*     */       //   43: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   46: aload_0
/*     */       //   47: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   50: aload_0
/*     */       //   51: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   54: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */       //   57: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   60: invokevirtual UNSCHEDULED : ()I
/*     */       //   63: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */       //   66: ifeq -> 46
/*     */       //   69: astore_3
/*     */       //   70: goto -> 133
/*     */       //   73: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   76: invokevirtual RESCHEDULED : ()I
/*     */       //   79: iload_2
/*     */       //   80: if_icmpne -> 119
/*     */       //   83: aload_0
/*     */       //   84: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   87: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   90: invokevirtual RESCHEDULED : ()I
/*     */       //   93: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   96: invokevirtual SCHEDULED : ()I
/*     */       //   99: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */       //   102: ifeq -> 0
/*     */       //   105: aload_0
/*     */       //   106: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   109: invokevirtual akka$dispatch$MessageDispatcher$$scheduleShutdownAction : ()V
/*     */       //   112: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   115: astore_3
/*     */       //   116: goto -> 133
/*     */       //   119: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   122: invokevirtual UNSCHEDULED : ()I
/*     */       //   125: iload_2
/*     */       //   126: if_icmpne -> 138
/*     */       //   129: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   132: astore_3
/*     */       //   133: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   136: pop
/*     */       //   137: return
/*     */       //   138: new scala/MatchError
/*     */       //   141: dup
/*     */       //   142: iload_2
/*     */       //   143: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   146: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   149: athrow
/*     */       //   150: astore #4
/*     */       //   152: aload_0
/*     */       //   153: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   156: aload_0
/*     */       //   157: getfield $outer : Lakka/dispatch/MessageDispatcher;
/*     */       //   160: invokevirtual akka$dispatch$MessageDispatcher$$shutdownSchedule : ()I
/*     */       //   163: getstatic akka/dispatch/MessageDispatcher$.MODULE$ : Lakka/dispatch/MessageDispatcher$;
/*     */       //   166: invokevirtual UNSCHEDULED : ()I
/*     */       //   169: invokevirtual akka$dispatch$MessageDispatcher$$updateShutdownSchedule : (II)Z
/*     */       //   172: ifeq -> 152
/*     */       //   175: aload #4
/*     */       //   177: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #209	-> 0
/*     */       //   #210	-> 8
/*     */       //   #212	-> 18
/*     */       //   #214	-> 46
/*     */       //   #211	-> 69
/*     */       //   #216	-> 73
/*     */       //   #217	-> 83
/*     */       //   #219	-> 119
/*     */       //   #209	-> 133
/*     */       //   #214	-> 150
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	178	0	this	Lakka/dispatch/MessageDispatcher$$anon$2;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   18	46	150	finally
/*     */     }
/*     */   }
/*     */   
/*     */   public void suspend(ActorCell actor) {
/*     */     Mailbox mbox = actor.mailbox();
/*     */     if (mbox.actor() == actor && mbox.dispatcher() == this)
/*     */       mbox.suspend(); 
/*     */   }
/*     */   
/*     */   public void resume(ActorCell actor) {
/*     */     Mailbox mbox = actor.mailbox();
/*     */     if (mbox.actor() == actor && mbox.dispatcher() == this && mbox.resume())
/*     */       registerForExecution(mbox, false, false); 
/*     */   }
/*     */   
/*     */   public final boolean isThroughputDeadlineTimeDefined() {
/* 286 */     return this.isThroughputDeadlineTimeDefined;
/*     */   }
/*     */   
/*     */   public static void printActors() {
/*     */     MessageDispatcher$.MODULE$.printActors();
/*     */   }
/*     */   
/*     */   public static Index<MessageDispatcher, ActorRef> actors() {
/*     */     return MessageDispatcher$.MODULE$.actors();
/*     */   }
/*     */   
/*     */   public static boolean debug() {
/*     */     return MessageDispatcher$.MODULE$.debug();
/*     */   }
/*     */   
/*     */   public static int RESCHEDULED() {
/*     */     return MessageDispatcher$.MODULE$.RESCHEDULED();
/*     */   }
/*     */   
/*     */   public static int SCHEDULED() {
/*     */     return MessageDispatcher$.MODULE$.SCHEDULED();
/*     */   }
/*     */   
/*     */   public static int UNSCHEDULED() {
/*     */     return MessageDispatcher$.MODULE$.UNSCHEDULED();
/*     */   }
/*     */   
/*     */   public abstract Mailbox createMailbox(Cell paramCell, MailboxType paramMailboxType);
/*     */   
/*     */   public abstract String id();
/*     */   
/*     */   public abstract FiniteDuration shutdownTimeout();
/*     */   
/*     */   public abstract void systemDispatch(ActorCell paramActorCell, SystemMessage paramSystemMessage);
/*     */   
/*     */   public abstract void dispatch(ActorCell paramActorCell, Envelope paramEnvelope);
/*     */   
/*     */   public abstract boolean registerForExecution(Mailbox paramMailbox, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   public abstract int throughput();
/*     */   
/*     */   public abstract Duration throughputDeadlineTime();
/*     */   
/*     */   public abstract void executeTask(TaskInvocation paramTaskInvocation);
/*     */   
/*     */   public abstract void shutdown();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MessageDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */