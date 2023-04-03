/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.AbstractNodeQueue;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import akka.util.Helpers$Requiring$;
/*     */ import com.typesafe.config.Config;
/*     */ import java.io.Closeable;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import scala.Array$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.IndexedSeq$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.Vector$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.Await$;
/*     */ import scala.concurrent.Awaitable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.Future$;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.concurrent.Promise$;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.Duration$;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.concurrent.duration.package;
/*     */ import scala.concurrent.duration.package$;
/*     */ import scala.package$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tue\001B\001\003\001\035\0211\004T5hQR\f%O]1z%\0264x\016\034<feN\033\007.\0323vY\026\024(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\021\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\tI1k\0315fIVdWM\035\t\003'ai\021\001\006\006\003+Y\t!![8\013\003]\tAA[1wC&\021\021\004\006\002\n\0072|7/Z1cY\026D\001b\007\001\003\002\003\006I\001H\001\007G>tg-[4\021\005u\031S\"\001\020\013\005my\"B\001\021\"\003!!\030\020]3tC\032,'\"\001\022\002\007\r|W.\003\002%=\t11i\0348gS\036D\001B\n\001\003\002\003\006IaJ\001\004Y><\007C\001\025,\033\005I#B\001\026\005\003\025)g/\0328u\023\ta\023F\001\bM_\036<\027N\\4BI\006\004H/\032:\t\0219\002!\021!Q\001\n=\nQ\002\0365sK\006$g)Y2u_JL\bC\001\0316\033\005\t$B\001\0324\003)\031wN\\2veJ,g\016\036\006\003iY\tA!\036;jY&\021a'\r\002\016)\"\024X-\0313GC\016$xN]=\t\013a\002A\021A\035\002\rqJg.\033;?)\021Q4\bP\037\021\005=\001\001\"B\0168\001\004a\002\"\002\0248\001\0049\003\"\002\0308\001\004y\003bB \001\005\004%\t\001Q\001\n/\",W\r\\*ju\026,\022!\021\t\003\023\tK!a\021\006\003\007%sG\017\003\004F\001\001\006I!Q\001\013/\",W\r\\*ju\026\004\003bB$\001\005\004%\t\001S\001\r)&\0347\016R;sCRLwN\\\013\002\023B\021!JT\007\002\027*\021A*T\001\tIV\024\030\r^5p]*\021!GC\005\003\037.\023aBR5oSR,G)\036:bi&|g\016\003\004R\001\001\006I!S\001\016)&\0347\016R;sCRLwN\034\021\t\017M\003!\031!C\001\021\006y1\013[;uI><h\016V5nK>,H\017\003\004V\001\001\006I!S\001\021'\",H\017Z8x]RKW.Z8vi\002Bqa\026\001C\002\023%\001*A\003p]\026t5\017\003\004Z\001\001\006I!S\001\007_:,gj\035\021\t\013m\003A\021\002/\002\017I|WO\0343VaR\021\021*\030\005\006=j\003\r!S\001\002I\")\001\r\001C\tC\006)1\r\\8dWR\t!\r\005\002\nG&\021AM\003\002\005\031>tw\rC\003g\001\021E\001*\001\nhKR\034\006.\036;e_^tG+[7f_V$\b\"\0025\001\t#I\027!C<bSRt\025M\\8t)\tQW\016\005\002\nW&\021AN\003\002\005+:LG\017C\003oO\002\007!-A\003oC:|7\017C\003q\001\021\005\023/\001\005tG\",G-\0367f)\025\021HP`A\001)\t\031h\017\005\002\020i&\021QO\001\002\f\007\006t7-\0327mC\ndW\rC\003x_\002\017\0010\001\005fq\026\034W\017^8s!\tI(0D\001N\023\tYXJ\001\tFq\026\034W\017^5p]\016{g\016^3yi\")Qp\034a\001\023\006a\021N\\5uS\006dG)\0327bs\")qp\034a\001\023\006)A-\0327bs\"9\0211A8A\002\005\025\021\001\003:v]:\f'\r\\3\021\t\005\035\021QB\007\003\003\023Q1!a\003\027\003\021a\027M\\4\n\t\005=\021\021\002\002\t%Vtg.\0312mK\"9\0211\003\001\005B\005U\021\001D:dQ\026$W\017\\3P]\016,GCBA\f\0037\ti\002F\002t\0033Aaa^A\t\001\bA\bBB@\002\022\001\007\021\n\003\005\002\004\005E\001\031AA\003\021\035\t\t\003\001C!\003G\tQa\0317pg\026$\022A\033\005\n\003O\001!\031!C!\003S\tA\"\\1y\rJ,\027/^3oGf,\"!a\013\021\007%\ti#C\002\0020)\021a\001R8vE2,\007\002CA\032\001\001\006I!a\013\002\0335\f\007P\022:fcV,gnY=!\021%\t9\004\001b\001\n\023\tI$A\003ti\006\024H/F\001c\021\035\ti\004\001Q\001\n\t\faa\035;beR\004\003\"CA!\001\t\007I\021BA\035\003%!\030nY6OC:|7\017C\004\002F\001\001\013\021\0022\002\025QL7m\033(b]>\034\b\005\003\005\002J\001\021\r\021\"\003A\003%9\b.Z3m\033\006\0348\016C\004\002N\001\001\013\021B!\002\025]DW-\0327NCN\\\007\005C\005\002R\001\021\r\021\"\003\002T\005)\021/^3vKV\021\021Q\013\t\005\003/\nYGD\002\020\0033:q!a\027\003\021\003\ti&A\016MS\036DG/\021:sCf\024VM^8mm\026\0248k\0315fIVdWM\035\t\004\037\005}cAB\001\003\021\003\t\tgE\002\002`!Aq\001OA0\t\003\t)\007\006\002\002^!A\021\021NA0A\003%!-\001\006uCN\\wJ\0324tKR4q!!\034\002`\021\tyGA\005UCN\\\027+^3vKN!\0211NA9!\031\t\031(!\037\002~5\021\021Q\017\006\004\003o\"\021\001\0033jgB\fGo\0315\n\t\005m\024Q\017\002\022\003\n\034HO]1di:{G-Z)vKV,\007\003BA@\003\003k!!a\030\007\021\005\r\025q\f\005\003\003\013\023!\002V1tW\"{G\016Z3s'\031\t\t)a\"\002\016B!\021qAAE\023\021\tY)!\003\003\r=\023'.Z2u!\021\ty(a$\007\027\005E\025q\fI\001$#\021\0211\023\002\n)&lWM\035+bg.\034r!a$\002\b\006\0251\017C\006\002\030\006\005%\0211A\005\002\005e\025\001\002;bg.,\"!!\002\t\027\005u\025\021\021BA\002\023\005\021qT\001\ti\006\0348n\030\023fcR\031!.!)\t\025\005\r\0261TA\001\002\004\t)!A\002yIEB1\"a*\002\002\n\005\t\025)\003\002\006\005)A/Y:lA!\"\021QUAV!\rI\021QV\005\004\003_S!\001\003<pY\006$\030\016\\3\t\025\005M\026\021\021BA\002\023\005\001)A\003uS\016\\7\017C\006\0028\006\005%\0211A\005\002\005e\026!\003;jG.\034x\fJ3r)\rQ\0271\030\005\n\003G\013),!AA\002\005C!\"a0\002\002\n\005\t\025)\003B\003\031!\030nY6tA!Q\0211YAA\005\003\005\013\021\002=\002!\025DXmY;uS>t7i\0348uKb$\bb\002\035\002\002\022\005\021q\031\013\t\003{\nI-a3\002N\"A\021qSAc\001\004\t)\001C\004\0024\006\025\007\031A!\t\017\005\r\027Q\031a\001q\"A\021\021[AA\t\033\t\031.A\006fqR\024\030m\031;UCN\\G\003BA\003\003+D\001\"a6\002P\002\007\021QA\001\fe\026\004H.Y2f/&$\b\016\013\003\002P\006m\007\003BAo\003Gl!!a8\013\007\005\005(\"\001\006b]:|G/\031;j_:LA!!:\002`\n9A/Y5me\026\034\007\"CAu\003\003#)\001BAv\003-)\0070Z2vi\026$\026m]6\025\005\0055\bcA\005\002p&\031\021\021\037\006\003\017\t{w\016\\3b]\"A\021Q_AA\t\003\n\031#A\002sk:D\001\"!?\002\002\022\005\0231^\001\007G\006t7-\0327\t\021\005u\030\021\021C!\003\f1\"[:DC:\034W\r\0347fIV\021\021Q\036\005\bq\005-D\021\001B\002)\t\021)\001\005\003\002\000\005-\004\"\003B\005\003?\002\013\021\002B\006\0035\031\025M\\2fY2,G\rV1tWJ1!QBAD\003\0131qAa\004\003\b\001\021YA\001\007=e\0264\027N\\3nK:$h\bC\005\003\024\005}\003\025!\003\003\026\005aQ\t_3dkR,G\rV1tWJ1!qCAD\003\0131qAa\004\003\022\001\021)\002\003\006\003\034\005}#\031!C\005\005;\taBT8u\007\006t7-\0327mC\ndW-\006\002\002\016\"I!\021EA0A\003%\021QR\001\020\035>$8)\0318dK2d\027M\0317fA!Q!QEA0\005\004%IAa\n\002'%s\027\016^5bYJ+\007/Z1u\033\006\0248.\032:\026\003MD\001Ba\013\002`\001\006Ia]\001\025\023:LG/[1m%\026\004X-\031;NCJ\\WM\035\021\t\021\t=\002\001)A\005\003+\na!];fk\026\004\003B\0029\001\t\023\021\031\004\006\005\0036\t]\"1\bB !\021\t9&a$\t\017\te\"\021\007a\001q\006\021Qm\031\005\t\005{\021\t\0041\001\002\006\005\t!\017\003\004\000\005c\001\r!\023\005\b\005\007\002A\021\002B#\0035\031\007.Z2l\033\006DH)\0327bsR\031!Na\022\t\017\t%#\021\ta\001E\006QA-\0327bs:\013gn\\:\t\023\t5\003A1A\005\n\t=\023aB:u_B\004X\rZ\013\003\005#\002bAa\025\003Z\tuSB\001B+\025\r\0219&M\001\007CR|W.[2\n\t\tm#Q\013\002\020\003R|W.[2SK\032,'/\0328dKB)\021Pa\030\003d%\031!\021M'\003\017A\023x.\\5tKB1!Q\rB8\005ki!Aa\032\013\t\t%$1N\001\nS6lW\017^1cY\026T1A!\034\013\003)\031w\016\0347fGRLwN\\\005\005\005c\0229GA\002TKFD\001B!\036\001A\003%!\021K\001\tgR|\007\017]3eA!9!\021\020\001\005\n\tm\024\001B:u_B$\"A! \021\013e\024yHa\031\n\007\t\005UJ\001\004GkR,(/\032\005\n\005\013\003\001\031!C\005\005\017\0131\002^5nKJ$\006N]3bIV\021!\021\022\t\005\003\017\021Y)\003\003\003\016\006%!A\002+ie\026\fG\rC\005\003\022\002\001\r\021\"\003\003\024\006yA/[7feRC'/Z1e?\022*\027\017F\002k\005+C!\"a)\003\020\006\005\t\031\001BE\021!\021I\n\001Q!\n\t%\025\001\004;j[\026\024H\013\033:fC\022\004\003\006\002BL\003W\003")
/*     */ public class LightArrayRevolverScheduler implements Scheduler, Closeable {
/*     */   public final LoggingAdapter akka$actor$LightArrayRevolverScheduler$$log;
/*     */   
/*     */   public final ThreadFactory akka$actor$LightArrayRevolverScheduler$$threadFactory;
/*     */   
/*     */   private final int WheelSize;
/*     */   
/*     */   private final FiniteDuration TickDuration;
/*     */   
/*     */   private final FiniteDuration ShutdownTimeout;
/*     */   
/*     */   private final FiniteDuration oneNs;
/*     */   
/*     */   private final double maxFrequency;
/*     */   
/*     */   private final long akka$actor$LightArrayRevolverScheduler$$start;
/*     */   
/*     */   private final long akka$actor$LightArrayRevolverScheduler$$tickNanos;
/*     */   
/*     */   private final int akka$actor$LightArrayRevolverScheduler$$wheelMask;
/*     */   
/*     */   private final TaskQueue akka$actor$LightArrayRevolverScheduler$$queue;
/*     */   
/*     */   private final AtomicReference<Promise<Seq<TimerTask>>> akka$actor$LightArrayRevolverScheduler$$stopped;
/*     */   
/*     */   private volatile Thread akka$actor$LightArrayRevolverScheduler$$timerThread;
/*     */   
/*     */   public final Cancellable schedule(FiniteDuration initialDelay, FiniteDuration interval, ActorRef receiver, Object message, ExecutionContext executor, ActorRef sender) {
/* 182 */     return Scheduler$class.schedule(this, initialDelay, interval, receiver, message, executor, sender);
/*     */   }
/*     */   
/*     */   public final Cancellable schedule(FiniteDuration initialDelay, FiniteDuration interval, Function0 f, ExecutionContext executor) {
/* 182 */     return Scheduler$class.schedule(this, initialDelay, interval, f, executor);
/*     */   }
/*     */   
/*     */   public final Cancellable scheduleOnce(FiniteDuration delay, ActorRef receiver, Object message, ExecutionContext executor, ActorRef sender) {
/* 182 */     return Scheduler$class.scheduleOnce(this, delay, receiver, message, executor, sender);
/*     */   }
/*     */   
/*     */   public final Cancellable scheduleOnce(FiniteDuration delay, Function0 f, ExecutionContext executor) {
/* 182 */     return Scheduler$class.scheduleOnce(this, delay, f, executor);
/*     */   }
/*     */   
/*     */   public final ActorRef schedule$default$6(FiniteDuration initialDelay, FiniteDuration interval, ActorRef receiver, Object message) {
/* 182 */     return Scheduler$class.schedule$default$6(this, initialDelay, interval, receiver, message);
/*     */   }
/*     */   
/*     */   public final ActorRef scheduleOnce$default$5(FiniteDuration delay, ActorRef receiver, Object message) {
/* 182 */     return Scheduler$class.scheduleOnce$default$5(this, delay, receiver, message);
/*     */   }
/*     */   
/*     */   public LightArrayRevolverScheduler(Config config, LoggingAdapter log, ThreadFactory threadFactory) {
/* 182 */     Scheduler$class.$init$(this);
/* 190 */     this.WheelSize = BoxesRunTime.unboxToInt(Helpers$Requiring$.MODULE$.requiring$extension1(
/* 191 */           Helpers$.MODULE$.Requiring(BoxesRunTime.boxToInteger(config.getInt("akka.scheduler.ticks-per-wheel"))), 
/* 192 */           (Function1)new $anonfun$1(this), (Function0)new $anonfun$2(this)));
/* 193 */     this.TickDuration = (FiniteDuration)Helpers$Requiring$.MODULE$.requiring$extension1(
/*     */         
/* 195 */         Helpers$.MODULE$.Requiring(Helpers$Requiring$.MODULE$.requiring$extension1(Helpers$.MODULE$.Requiring(Helpers$ConfigOps$.MODULE$.getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(config), "akka.scheduler.tick-duration")), (Function1)new $anonfun$4(this), (Function0)new $anonfun$3(this))), 
/* 196 */         (Function1)new $anonfun$6(this), (Function0)new $anonfun$5(this));
/* 197 */     this.ShutdownTimeout = Helpers$ConfigOps$.MODULE$.getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(config), "akka.scheduler.shutdown-timeout");
/* 201 */     this.oneNs = Duration$.MODULE$.fromNanos(1L);
/* 289 */     this.maxFrequency = (new package.DurationInt(package$.MODULE$.DurationInt(1))).second().$div((Duration)TickDuration());
/* 295 */     this.akka$actor$LightArrayRevolverScheduler$$start = clock();
/* 296 */     this.akka$actor$LightArrayRevolverScheduler$$tickNanos = TickDuration().toNanos();
/* 297 */     this.akka$actor$LightArrayRevolverScheduler$$wheelMask = WheelSize() - 1;
/* 298 */     this.akka$actor$LightArrayRevolverScheduler$$queue = new TaskQueue();
/* 325 */     this.akka$actor$LightArrayRevolverScheduler$$stopped = new AtomicReference<Promise<Seq<TimerTask>>>();
/* 337 */     this.akka$actor$LightArrayRevolverScheduler$$timerThread = threadFactory.newThread(new $anon$8(this));
/* 437 */     akka$actor$LightArrayRevolverScheduler$$timerThread().start();
/*     */   }
/*     */   
/*     */   public int WheelSize() {
/*     */     return this.WheelSize;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(int ticks) {
/*     */       return apply$mcZI$sp(ticks);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int ticks) {
/*     */       return ((ticks & ticks - 1) == 0);
/*     */     }
/*     */     
/*     */     public $anonfun$1(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "ticks-per-wheel must be a power of 2";
/*     */     }
/*     */     
/*     */     public $anonfun$2(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public FiniteDuration TickDuration() {
/*     */     return this.TickDuration;
/*     */   }
/*     */   
/*     */   public class $anonfun$4 extends AbstractFunction1<FiniteDuration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(FiniteDuration x$1) {
/*     */       return !(!x$1.$greater$eq((new package.DurationInt(package$.MODULE$.DurationInt(10))).millis()) && Helpers$.MODULE$.isWindows());
/*     */     }
/*     */     
/*     */     public $anonfun$4(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$3 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "minimum supported akka.scheduler.tick-duration on Windows is 10ms";
/*     */     }
/*     */     
/*     */     public $anonfun$3(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$6 extends AbstractFunction1<FiniteDuration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(FiniteDuration x$2) {
/*     */       return x$2.$greater$eq((new package.DurationInt(package$.MODULE$.DurationInt(1))).millis());
/*     */     }
/*     */     
/*     */     public $anonfun$6(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$5 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "minimum supported akka.scheduler.tick-duration is 1ms";
/*     */     }
/*     */     
/*     */     public $anonfun$5(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public FiniteDuration ShutdownTimeout() {
/*     */     return this.ShutdownTimeout;
/*     */   }
/*     */   
/*     */   private FiniteDuration oneNs() {
/*     */     return this.oneNs;
/*     */   }
/*     */   
/*     */   public FiniteDuration akka$actor$LightArrayRevolverScheduler$$roundUp(FiniteDuration d) {
/*     */     try {
/*     */     
/*     */     } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     return d;
/*     */   }
/*     */   
/*     */   public long clock() {
/*     */     return System.nanoTime();
/*     */   }
/*     */   
/*     */   public FiniteDuration getShutdownTimeout() {
/*     */     return ShutdownTimeout();
/*     */   }
/*     */   
/*     */   public void waitNanos(long nanos) {
/*     */     long sleepMs = Helpers$.MODULE$.isWindows() ? ((nanos + 4999999L) / 10000000L * 10L) : ((nanos + 999999L) / 1000000L);
/*     */     try {
/*     */       Thread.sleep(sleepMs);
/*     */     } catch (InterruptedException interruptedException) {
/*     */       Thread.currentThread().interrupt();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Cancellable schedule(FiniteDuration initialDelay, FiniteDuration delay, Runnable runnable, ExecutionContext executor) {
/*     */     checkMaxDelay(akka$actor$LightArrayRevolverScheduler$$roundUp(delay).toNanos());
/*     */     ExecutionContext preparedEC = executor.prepare();
/*     */     try {
/*     */       return new LightArrayRevolverScheduler$$anon$2(this, initialDelay, delay, runnable, preparedEC);
/*     */     } finally {
/*     */       Exception exception1 = null, exception2 = exception1;
/*     */       if (exception2 instanceof SchedulerException) {
/*     */         SchedulerException schedulerException = (SchedulerException)exception2;
/*     */         String msg = schedulerException.msg();
/*     */         throw new IllegalStateException(msg);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public class LightArrayRevolverScheduler$$anon$2 extends AtomicReference<Cancellable> implements Cancellable {
/*     */     public LightArrayRevolverScheduler$$anon$2(LightArrayRevolverScheduler $outer, FiniteDuration initialDelay$1, FiniteDuration delay$1, Runnable runnable$1, ExecutionContext preparedEC$1) {
/*     */       super(LightArrayRevolverScheduler$.MODULE$.akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker());
/*     */       compareAndSet(LightArrayRevolverScheduler$.MODULE$.akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker(), $outer.akka$actor$LightArrayRevolverScheduler$$schedule(preparedEC$1, new LightArrayRevolverScheduler$$anon$2$$anon$1(this, initialDelay$1, delay$1, runnable$1, preparedEC$1), $outer.akka$actor$LightArrayRevolverScheduler$$roundUp(initialDelay$1)));
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$2$$anon$1 extends AtomicLong implements Runnable {
/*     */       private final FiniteDuration delay$1;
/*     */       
/*     */       private final Runnable runnable$1;
/*     */       
/*     */       private final ExecutionContext preparedEC$1;
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$2$$anon$1(LightArrayRevolverScheduler$$anon$2 $outer, FiniteDuration initialDelay$1, FiniteDuration delay$1, Runnable runnable$1, ExecutionContext preparedEC$1) {
/*     */         super($outer.akka$actor$LightArrayRevolverScheduler$$anon$$$outer().clock() + initialDelay$1.toNanos());
/*     */       }
/*     */       
/*     */       public void run() {
/*     */         try {
/*     */           this.runnable$1.run();
/*     */           long driftNanos = this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$$outer().clock() - getAndAdd(this.delay$1.toNanos());
/*     */           if (this.$outer.get() != null)
/*     */             this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$swap(this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$$outer().akka$actor$LightArrayRevolverScheduler$$schedule(this.preparedEC$1, this, Duration$.MODULE$.fromNanos(Math.max(this.delay$1.toNanos() - driftNanos, 1L)))); 
/*     */         } catch (SchedulerException schedulerException) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public void akka$actor$LightArrayRevolverScheduler$$anon$$swap(Cancellable c) {
/*     */       while (true) {
/*     */         Cancellable cancellable = get();
/*     */         if (cancellable == null) {
/*     */           c.cancel();
/*     */           BoxedUnit boxedUnit = (c == null) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */         } else if (compareAndSet(cancellable, c)) {
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/*     */           c = c;
/*     */           continue;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean cancel() {
/*     */       boolean bool;
/*     */       while (true) {
/*     */         Cancellable cancellable = get();
/*     */         if (cancellable == null) {
/*     */           boolean bool1 = false;
/*     */           break;
/*     */         } 
/*     */         null;
/*     */         null;
/*     */         if (compareAndSet(cancellable, null)) {
/*     */         
/*     */         } else {
/*     */           continue;
/*     */         } 
/*     */         bool = cancellable.cancel() ? compareAndSet(cancellable, null) : true;
/*     */         break;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/*     */       return (get() == null);
/*     */     }
/*     */   }
/*     */   
/*     */   public Cancellable scheduleOnce(FiniteDuration delay, Runnable runnable, ExecutionContext executor) {
/*     */     try {
/*     */       return akka$actor$LightArrayRevolverScheduler$$schedule(executor.prepare(), runnable, akka$actor$LightArrayRevolverScheduler$$roundUp(delay));
/*     */     } finally {
/*     */       Exception exception1 = null, exception2 = exception1;
/*     */       if (exception2 instanceof SchedulerException) {
/*     */         SchedulerException schedulerException = (SchedulerException)exception2;
/*     */         String msg = schedulerException.msg();
/*     */         throw new IllegalStateException(msg);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     ((IterableLike)Await$.MODULE$.result((Awaitable)stop(), (Duration)getShutdownTimeout())).foreach((Function1)new LightArrayRevolverScheduler$$anonfun$close$1(this));
/*     */   }
/*     */   
/*     */   public class LightArrayRevolverScheduler$$anonfun$close$1 extends AbstractFunction1<TimerTask, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public LightArrayRevolverScheduler$$anonfun$close$1(LightArrayRevolverScheduler $outer) {}
/*     */     
/*     */     public final void apply(LightArrayRevolverScheduler.TimerTask task) {
/*     */       try {
/*     */         task.run();
/*     */       } finally {
/*     */         Exception exception1 = null, exception2 = exception1;
/*     */         if (exception2 instanceof InterruptedException) {
/*     */           InterruptedException interruptedException = (InterruptedException)exception2;
/*     */           throw interruptedException;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public double maxFrequency() {
/*     */     return this.maxFrequency;
/*     */   }
/*     */   
/*     */   public long akka$actor$LightArrayRevolverScheduler$$start() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$start;
/*     */   }
/*     */   
/*     */   public long akka$actor$LightArrayRevolverScheduler$$tickNanos() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$tickNanos;
/*     */   }
/*     */   
/*     */   public int akka$actor$LightArrayRevolverScheduler$$wheelMask() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$wheelMask;
/*     */   }
/*     */   
/*     */   public TaskQueue akka$actor$LightArrayRevolverScheduler$$queue() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$queue;
/*     */   }
/*     */   
/*     */   public TimerTask akka$actor$LightArrayRevolverScheduler$$schedule(ExecutionContext ec, Runnable r, FiniteDuration delay) {
/*     */     // Byte code:
/*     */     //   0: aload_3
/*     */     //   1: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */     //   4: invokevirtual Zero : ()Lscala/concurrent/duration/FiniteDuration;
/*     */     //   7: invokevirtual $less$eq : (Ljava/lang/Object;)Z
/*     */     //   10: ifeq -> 50
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   17: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   20: ifnonnull -> 39
/*     */     //   23: aload_1
/*     */     //   24: aload_2
/*     */     //   25: invokeinterface execute : (Ljava/lang/Runnable;)V
/*     */     //   30: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */     //   33: invokevirtual akka$actor$LightArrayRevolverScheduler$$NotCancellable : ()Lakka/actor/LightArrayRevolverScheduler$TimerTask;
/*     */     //   36: goto -> 135
/*     */     //   39: new akka/actor/SchedulerException
/*     */     //   42: dup
/*     */     //   43: ldc_w 'cannot enqueue after timer shutdown'
/*     */     //   46: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   49: athrow
/*     */     //   50: aload_0
/*     */     //   51: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   54: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   57: ifnonnull -> 136
/*     */     //   60: aload_3
/*     */     //   61: invokevirtual toNanos : ()J
/*     */     //   64: lstore #4
/*     */     //   66: aload_0
/*     */     //   67: lload #4
/*     */     //   69: invokespecial checkMaxDelay : (J)V
/*     */     //   72: lload #4
/*     */     //   74: aload_0
/*     */     //   75: invokevirtual akka$actor$LightArrayRevolverScheduler$$tickNanos : ()J
/*     */     //   78: ldiv
/*     */     //   79: l2i
/*     */     //   80: istore #6
/*     */     //   82: new akka/actor/LightArrayRevolverScheduler$TaskHolder
/*     */     //   85: dup
/*     */     //   86: aload_2
/*     */     //   87: iload #6
/*     */     //   89: aload_1
/*     */     //   90: invokespecial <init> : (Ljava/lang/Runnable;ILscala/concurrent/ExecutionContext;)V
/*     */     //   93: astore #7
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual akka$actor$LightArrayRevolverScheduler$$queue : ()Lakka/actor/LightArrayRevolverScheduler$TaskQueue;
/*     */     //   99: aload #7
/*     */     //   101: invokevirtual add : (Ljava/lang/Object;)V
/*     */     //   104: aload_0
/*     */     //   105: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   108: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   111: ifnull -> 133
/*     */     //   114: aload #7
/*     */     //   116: invokevirtual cancel : ()Z
/*     */     //   119: ifeq -> 133
/*     */     //   122: new akka/actor/SchedulerException
/*     */     //   125: dup
/*     */     //   126: ldc_w 'cannot enqueue after timer shutdown'
/*     */     //   129: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   132: athrow
/*     */     //   133: aload #7
/*     */     //   135: areturn
/*     */     //   136: new akka/actor/SchedulerException
/*     */     //   139: dup
/*     */     //   140: ldc_w 'cannot enqueue after timer shutdown'
/*     */     //   143: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   146: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #301	-> 0
/*     */     //   #302	-> 13
/*     */     //   #303	-> 23
/*     */     //   #304	-> 30
/*     */     //   #302	-> 39
/*     */     //   #305	-> 50
/*     */     //   #308	-> 60
/*     */     //   #309	-> 66
/*     */     //   #311	-> 72
/*     */     //   #312	-> 82
/*     */     //   #313	-> 95
/*     */     //   #314	-> 104
/*     */     //   #315	-> 122
/*     */     //   #316	-> 133
/*     */     //   #301	-> 135
/*     */     //   #306	-> 136
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	147	0	this	Lakka/actor/LightArrayRevolverScheduler;
/*     */     //   0	147	1	ec	Lscala/concurrent/ExecutionContext;
/*     */     //   0	147	2	r	Ljava/lang/Runnable;
/*     */     //   0	147	3	delay	Lscala/concurrent/duration/FiniteDuration;
/*     */     //   66	69	4	delayNanos	J
/*     */     //   82	53	6	ticks	I
/*     */     //   95	40	7	task	Lakka/actor/LightArrayRevolverScheduler$TaskHolder;
/*     */   }
/*     */   
/*     */   private void checkMaxDelay(long delayNanos) {
/*     */     if (delayNanos / akka$actor$LightArrayRevolverScheduler$$tickNanos() > 2147483647L) {
/*     */       (new String[2])[0] = "Task scheduled with [";
/*     */       (new String[2])[1] = "] seconds delay, ";
/*     */       (new String[2])[0] = "which is too far in future, maximum delay is [";
/*     */       (new String[2])[1] = "] seconds";
/*     */       throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToLong((new package.DurationLong(package$.MODULE$.DurationLong(delayNanos))).nanos().toSeconds()) }))).append((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToLong((new package.DurationLong(package$.MODULE$.DurationLong(akka$actor$LightArrayRevolverScheduler$$tickNanos() * 2147483647L))).nanos().toSeconds() - 1L) }))).toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public AtomicReference<Promise<Seq<TimerTask>>> akka$actor$LightArrayRevolverScheduler$$stopped() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$stopped;
/*     */   }
/*     */   
/*     */   private Future<Seq<TimerTask>> stop() {
/*     */     Promise<Seq<TimerTask>> p = Promise$.MODULE$.apply();
/*     */     null;
/*     */     return akka$actor$LightArrayRevolverScheduler$$stopped().compareAndSet(null, p) ? p.future() : Future$.MODULE$.successful(Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   private Thread akka$actor$LightArrayRevolverScheduler$$timerThread() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$timerThread;
/*     */   }
/*     */   
/*     */   public void akka$actor$LightArrayRevolverScheduler$$timerThread_$eq(Thread x$1) {
/*     */     this.akka$actor$LightArrayRevolverScheduler$$timerThread = x$1;
/*     */   }
/*     */   
/*     */   public class $anon$8 implements Runnable {
/*     */     private int tick;
/*     */     
/*     */     private final LightArrayRevolverScheduler.TaskQueue[] akka$actor$LightArrayRevolverScheduler$$anon$$wheel;
/*     */     
/*     */     public $anon$8(LightArrayRevolverScheduler $outer) {
/*     */       this.tick = 0;
/*     */       this.akka$actor$LightArrayRevolverScheduler$$anon$$wheel = (LightArrayRevolverScheduler.TaskQueue[])Array$.MODULE$.fill($outer.WheelSize(), (Function0)new $anonfun$7(this), ClassTag$.MODULE$.apply(LightArrayRevolverScheduler.TaskQueue.class));
/*     */     }
/*     */     
/*     */     private int tick() {
/*     */       return this.tick;
/*     */     }
/*     */     
/*     */     private void tick_$eq(int x$1) {
/*     */       this.tick = x$1;
/*     */     }
/*     */     
/*     */     public LightArrayRevolverScheduler.TaskQueue[] akka$actor$LightArrayRevolverScheduler$$anon$$wheel() {
/*     */       return this.akka$actor$LightArrayRevolverScheduler$$anon$$wheel;
/*     */     }
/*     */     
/*     */     public class $anonfun$7 extends AbstractFunction0<LightArrayRevolverScheduler.TaskQueue> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final LightArrayRevolverScheduler.TaskQueue apply() {
/*     */         return new LightArrayRevolverScheduler.TaskQueue();
/*     */       }
/*     */       
/*     */       public $anonfun$7(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */     
/*     */     public final Vector akka$actor$LightArrayRevolverScheduler$$anon$$collect$1(LightArrayRevolverScheduler.TaskQueue q, Vector acc) {
/*     */       while (true) {
/*     */         LightArrayRevolverScheduler.TaskHolder taskHolder = (LightArrayRevolverScheduler.TaskHolder)q.poll();
/*     */         if (taskHolder == null)
/*     */           return acc; 
/*     */         acc = (Vector)acc.$colon$plus(taskHolder, Vector$.MODULE$.canBuildFrom());
/*     */         q = q;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Seq<LightArrayRevolverScheduler.TimerTask> clearAll() {
/*     */       return (Seq<LightArrayRevolverScheduler.TimerTask>)((TraversableLike)RichInt$.MODULE$.until$extension0(Predef$.MODULE$.intWrapper(0), this.$outer.WheelSize()).flatMap((Function1)new LightArrayRevolverScheduler$$anon$8$$anonfun$clearAll$1(this), IndexedSeq$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)akka$actor$LightArrayRevolverScheduler$$anon$$collect$1(this.$outer.akka$actor$LightArrayRevolverScheduler$$queue(), package$.MODULE$.Vector().empty()), IndexedSeq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$8$$anonfun$clearAll$1 extends AbstractFunction1<Object, Vector<LightArrayRevolverScheduler.TimerTask>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Vector<LightArrayRevolverScheduler.TimerTask> apply(int i) {
/*     */         return this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$collect$1(this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[i], package$.MODULE$.Vector().empty());
/*     */       }
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$8$$anonfun$clearAll$1(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */     
/*     */     private void checkQueue(long time) {
/*     */       while (true) {
/*     */         int futureTick, offset, bucket;
/*     */         AbstractNodeQueue.Node node = this.$outer.akka$actor$LightArrayRevolverScheduler$$queue().pollNode();
/*     */         if (node == null)
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT; 
/*     */         int i = ((LightArrayRevolverScheduler.TaskHolder)node.value).ticks();
/*     */         switch (i) {
/*     */           default:
/*     */             futureTick = (int)((time - this.$outer.akka$actor$LightArrayRevolverScheduler$$start() + i * this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos() + this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos() - 1L) / this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos());
/*     */             offset = futureTick - tick();
/*     */             bucket = futureTick & this.$outer.akka$actor$LightArrayRevolverScheduler$$wheelMask();
/*     */             ((LightArrayRevolverScheduler.TaskHolder)node.value).ticks_$eq(offset);
/*     */             akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[bucket].addNode(node);
/*     */           case 0:
/*     */             break;
/*     */         } 
/*     */         BoxesRunTime.boxToBoolean(((LightArrayRevolverScheduler.TaskHolder)node.value).executeTask());
/*     */         time = time;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void run() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokespecial nextTick : ()V
/*     */       //   4: return
/*     */       //   5: astore_1
/*     */       //   6: aload_0
/*     */       //   7: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   10: getfield akka$actor$LightArrayRevolverScheduler$$log : Lakka/event/LoggingAdapter;
/*     */       //   13: aload_1
/*     */       //   14: ldc 'exception on LARS’ timer thread'
/*     */       //   16: invokeinterface error : (Ljava/lang/Throwable;Ljava/lang/String;)V
/*     */       //   21: aload_0
/*     */       //   22: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   25: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */       //   28: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   31: checkcast scala/concurrent/Promise
/*     */       //   34: astore_2
/*     */       //   35: aload_2
/*     */       //   36: ifnonnull -> 79
/*     */       //   39: aload_0
/*     */       //   40: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   43: getfield akka$actor$LightArrayRevolverScheduler$$threadFactory : Ljava/util/concurrent/ThreadFactory;
/*     */       //   46: aload_0
/*     */       //   47: invokeinterface newThread : (Ljava/lang/Runnable;)Ljava/lang/Thread;
/*     */       //   52: astore #4
/*     */       //   54: aload_0
/*     */       //   55: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   58: getfield akka$actor$LightArrayRevolverScheduler$$log : Lakka/event/LoggingAdapter;
/*     */       //   61: ldc 'starting new LARS thread'
/*     */       //   63: invokeinterface info : (Ljava/lang/String;)V
/*     */       //   68: aload #4
/*     */       //   70: invokevirtual start : ()V
/*     */       //   73: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   76: goto -> 168
/*     */       //   79: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   82: aload_0
/*     */       //   83: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   86: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */       //   89: aload_2
/*     */       //   90: getstatic scala/concurrent/Promise$.MODULE$ : Lscala/concurrent/Promise$;
/*     */       //   93: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */       //   96: invokevirtual successful : (Ljava/lang/Object;)Lscala/concurrent/Promise;
/*     */       //   99: invokevirtual compareAndSet : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   102: new akka/actor/LightArrayRevolverScheduler$$anon$8$$anonfun$run$1
/*     */       //   105: dup
/*     */       //   106: aload_0
/*     */       //   107: invokespecial <init> : (Lakka/actor/LightArrayRevolverScheduler$$anon$8;)V
/*     */       //   110: invokevirtual assert : (ZLscala/Function0;)V
/*     */       //   113: aload_2
/*     */       //   114: aload_0
/*     */       //   115: invokespecial clearAll : ()Lscala/collection/immutable/Seq;
/*     */       //   118: invokeinterface success : (Ljava/lang/Object;)Lscala/concurrent/Promise;
/*     */       //   123: astore_3
/*     */       //   124: goto -> 182
/*     */       //   127: astore #5
/*     */       //   129: aload_0
/*     */       //   130: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   133: getfield akka$actor$LightArrayRevolverScheduler$$log : Lakka/event/LoggingAdapter;
/*     */       //   136: aload #5
/*     */       //   138: ldc 'LARS cannot start new thread, ship’s going down!'
/*     */       //   140: invokeinterface error : (Ljava/lang/Throwable;Ljava/lang/String;)V
/*     */       //   145: aload_0
/*     */       //   146: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   149: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */       //   152: getstatic scala/concurrent/Promise$.MODULE$ : Lscala/concurrent/Promise$;
/*     */       //   155: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */       //   158: invokevirtual successful : (Ljava/lang/Object;)Lscala/concurrent/Promise;
/*     */       //   161: invokevirtual set : (Ljava/lang/Object;)V
/*     */       //   164: aload_0
/*     */       //   165: invokespecial clearAll : ()Lscala/collection/immutable/Seq;
/*     */       //   168: pop
/*     */       //   169: aload_0
/*     */       //   170: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   173: aload #4
/*     */       //   175: invokevirtual akka$actor$LightArrayRevolverScheduler$$timerThread_$eq : (Ljava/lang/Thread;)V
/*     */       //   178: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   181: astore_3
/*     */       //   182: aload_3
/*     */       //   183: pop
/*     */       //   184: aload_1
/*     */       //   185: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #375	-> 0
/*     */       //   #377	-> 5
/*     */       //   #378	-> 6
/*     */       //   #379	-> 21
/*     */       //   #380	-> 35
/*     */       //   #381	-> 39
/*     */       //   #382	-> 54
/*     */       //   #383	-> 68
/*     */       //   #392	-> 79
/*     */       //   #393	-> 113
/*     */       //   #391	-> 123
/*     */       //   #385	-> 127
/*     */       //   #383	-> 127
/*     */       //   #386	-> 129
/*     */       //   #387	-> 145
/*     */       //   #388	-> 164
/*     */       //   #383	-> 168
/*     */       //   #390	-> 169
/*     */       //   #380	-> 181
/*     */       //   #379	-> 182
/*     */       //   #395	-> 184
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	186	0	this	Lakka/actor/LightArrayRevolverScheduler$$anon$8;
/*     */       //   54	127	4	thread	Ljava/lang/Thread;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	4	5	finally
/*     */       //   68	79	127	finally
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$8$$anonfun$run$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "Stop signal violated in LARS";
/*     */       }
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$8$$anonfun$run$1(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */     
/*     */     private final void nextTick() {
/*     */       Promise<Seq<LightArrayRevolverScheduler.TimerTask>> promise;
/*     */       do {
/*     */         long time = this.$outer.clock();
/*     */         long sleepTime = this.$outer.akka$actor$LightArrayRevolverScheduler$$start() + tick() * this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos() - time;
/*     */         if (sleepTime > 0L) {
/*     */           checkQueue(time);
/*     */           this.$outer.waitNanos(sleepTime);
/*     */         } else {
/*     */           int bucket = tick() & this.$outer.akka$actor$LightArrayRevolverScheduler$$wheelMask();
/*     */           LightArrayRevolverScheduler.TaskQueue tasks = akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[bucket];
/*     */           LightArrayRevolverScheduler.TaskQueue putBack = new LightArrayRevolverScheduler.TaskQueue();
/*     */           executeBucket$1(tasks, putBack);
/*     */           akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[bucket] = putBack;
/*     */           tick_$eq(tick() + 1);
/*     */         } 
/*     */         promise = this.$outer.akka$actor$LightArrayRevolverScheduler$$stopped().get();
/*     */       } while (promise == null);
/*     */       Predef$.MODULE$.assert(this.$outer.akka$actor$LightArrayRevolverScheduler$$stopped().compareAndSet(promise, Promise$.MODULE$.successful(Nil$.MODULE$)), (Function0)new LightArrayRevolverScheduler$$anon$8$$anonfun$nextTick$1(this));
/*     */       promise.success(clearAll());
/*     */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     private final void executeBucket$1(LightArrayRevolverScheduler.TaskQueue tasks$1, LightArrayRevolverScheduler.TaskQueue putBack$1) {
/*     */       while (true) {
/*     */         AbstractNodeQueue.Node node = tasks$1.pollNode();
/*     */         if (node == null)
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT; 
/*     */         LightArrayRevolverScheduler.TaskHolder task = (LightArrayRevolverScheduler.TaskHolder)node.value;
/*     */         task.ticks_$eq(task.ticks() - this.$outer.WheelSize());
/*     */         putBack$1.addNode(node);
/*     */         task.isCancelled() ? BoxedUnit.UNIT : ((task.ticks() >= this.$outer.WheelSize()) ? BoxedUnit.UNIT : BoxesRunTime.boxToBoolean(task.executeTask()));
/*     */       } 
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$8$$anonfun$nextTick$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "Stop signal violated in LARS";
/*     */       }
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$8$$anonfun$nextTick$1(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TaskQueue extends AbstractNodeQueue<TaskHolder> {}
/*     */   
/*     */   public static class TaskHolder implements TimerTask {
/*     */     private volatile Runnable task;
/*     */     
/*     */     private int ticks;
/*     */     
/*     */     private final ExecutionContext executionContext;
/*     */     
/*     */     public Runnable task() {
/* 453 */       return this.task;
/*     */     }
/*     */     
/*     */     public void task_$eq(Runnable x$1) {
/* 453 */       this.task = x$1;
/*     */     }
/*     */     
/*     */     public int ticks() {
/* 453 */       return this.ticks;
/*     */     }
/*     */     
/*     */     public void ticks_$eq(int x$1) {
/* 453 */       this.ticks = x$1;
/*     */     }
/*     */     
/*     */     public TaskHolder(Runnable task, int ticks, ExecutionContext executionContext) {}
/*     */     
/*     */     private final Runnable extractTask(Runnable replaceWith) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual task : ()Ljava/lang/Runnable;
/*     */       //   4: astore_3
/*     */       //   5: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   8: getfield akka$actor$LightArrayRevolverScheduler$$ExecutedTask : Ljava/lang/Runnable;
/*     */       //   11: aload_3
/*     */       //   12: astore #4
/*     */       //   14: dup
/*     */       //   15: ifnonnull -> 27
/*     */       //   18: pop
/*     */       //   19: aload #4
/*     */       //   21: ifnull -> 35
/*     */       //   24: goto -> 41
/*     */       //   27: aload #4
/*     */       //   29: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   32: ifeq -> 41
/*     */       //   35: iconst_1
/*     */       //   36: istore #5
/*     */       //   38: goto -> 80
/*     */       //   41: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   44: getfield akka$actor$LightArrayRevolverScheduler$$CancelledTask : Ljava/lang/Runnable;
/*     */       //   47: aload_3
/*     */       //   48: astore #6
/*     */       //   50: dup
/*     */       //   51: ifnonnull -> 63
/*     */       //   54: pop
/*     */       //   55: aload #6
/*     */       //   57: ifnull -> 71
/*     */       //   60: goto -> 77
/*     */       //   63: aload #6
/*     */       //   65: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   68: ifeq -> 77
/*     */       //   71: iconst_1
/*     */       //   72: istore #5
/*     */       //   74: goto -> 80
/*     */       //   77: iconst_0
/*     */       //   78: istore #5
/*     */       //   80: iload #5
/*     */       //   82: ifeq -> 91
/*     */       //   85: aload_3
/*     */       //   86: astore #7
/*     */       //   88: goto -> 112
/*     */       //   91: getstatic akka/util/Unsafe.instance : Lsun/misc/Unsafe;
/*     */       //   94: aload_0
/*     */       //   95: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   98: getfield akka$actor$LightArrayRevolverScheduler$$taskOffset : J
/*     */       //   101: aload_3
/*     */       //   102: aload_1
/*     */       //   103: invokevirtual compareAndSwapObject : (Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   106: ifeq -> 115
/*     */       //   109: aload_3
/*     */       //   110: astore #7
/*     */       //   112: aload #7
/*     */       //   114: areturn
/*     */       //   115: aload_1
/*     */       //   116: astore_1
/*     */       //   117: goto -> 0
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #458	-> 0
/*     */       //   #459	-> 5
/*     */       //   #460	-> 91
/*     */       //   #458	-> 112
/*     */       //   #460	-> 115
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	120	0	this	Lakka/actor/LightArrayRevolverScheduler$TaskHolder;
/*     */       //   0	120	1	replaceWith	Ljava/lang/Runnable;
/*     */     }
/*     */     
/*     */     public final boolean executeTask() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   4: getfield akka$actor$LightArrayRevolverScheduler$$ExecutedTask : Ljava/lang/Runnable;
/*     */       //   7: invokespecial extractTask : (Ljava/lang/Runnable;)Ljava/lang/Runnable;
/*     */       //   10: astore_1
/*     */       //   11: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   14: getfield akka$actor$LightArrayRevolverScheduler$$ExecutedTask : Ljava/lang/Runnable;
/*     */       //   17: aload_1
/*     */       //   18: astore_2
/*     */       //   19: dup
/*     */       //   20: ifnonnull -> 31
/*     */       //   23: pop
/*     */       //   24: aload_2
/*     */       //   25: ifnull -> 38
/*     */       //   28: goto -> 43
/*     */       //   31: aload_2
/*     */       //   32: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   35: ifeq -> 43
/*     */       //   38: iconst_1
/*     */       //   39: istore_3
/*     */       //   40: goto -> 80
/*     */       //   43: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   46: getfield akka$actor$LightArrayRevolverScheduler$$CancelledTask : Ljava/lang/Runnable;
/*     */       //   49: aload_1
/*     */       //   50: astore #4
/*     */       //   52: dup
/*     */       //   53: ifnonnull -> 65
/*     */       //   56: pop
/*     */       //   57: aload #4
/*     */       //   59: ifnull -> 73
/*     */       //   62: goto -> 78
/*     */       //   65: aload #4
/*     */       //   67: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   70: ifeq -> 78
/*     */       //   73: iconst_1
/*     */       //   74: istore_3
/*     */       //   75: goto -> 80
/*     */       //   78: iconst_0
/*     */       //   79: istore_3
/*     */       //   80: iload_3
/*     */       //   81: ifeq -> 90
/*     */       //   84: iconst_0
/*     */       //   85: istore #5
/*     */       //   87: goto -> 179
/*     */       //   90: aload_0
/*     */       //   91: getfield executionContext : Lscala/concurrent/ExecutionContext;
/*     */       //   94: aload_1
/*     */       //   95: invokeinterface execute : (Ljava/lang/Runnable;)V
/*     */       //   100: iconst_1
/*     */       //   101: goto -> 177
/*     */       //   104: astore #6
/*     */       //   106: aload #6
/*     */       //   108: astore #7
/*     */       //   110: aload #7
/*     */       //   112: instanceof java/lang/InterruptedException
/*     */       //   115: ifeq -> 130
/*     */       //   118: invokestatic currentThread : ()Ljava/lang/Thread;
/*     */       //   121: invokevirtual interrupt : ()V
/*     */       //   124: iconst_0
/*     */       //   125: istore #8
/*     */       //   127: goto -> 175
/*     */       //   130: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   133: aload #7
/*     */       //   135: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   138: astore #9
/*     */       //   140: aload #9
/*     */       //   142: invokevirtual isEmpty : ()Z
/*     */       //   145: ifeq -> 151
/*     */       //   148: aload #6
/*     */       //   150: athrow
/*     */       //   151: aload #9
/*     */       //   153: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   156: checkcast java/lang/Throwable
/*     */       //   159: astore #10
/*     */       //   161: aload_0
/*     */       //   162: getfield executionContext : Lscala/concurrent/ExecutionContext;
/*     */       //   165: aload #10
/*     */       //   167: invokeinterface reportFailure : (Ljava/lang/Throwable;)V
/*     */       //   172: iconst_0
/*     */       //   173: istore #8
/*     */       //   175: iload #8
/*     */       //   177: istore #5
/*     */       //   179: iload #5
/*     */       //   181: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #463	-> 0
/*     */       //   #464	-> 11
/*     */       //   #467	-> 90
/*     */       //   #468	-> 100
/*     */       //   #466	-> 104
/*     */       //   #470	-> 110
/*     */       //   #471	-> 130
/*     */       //   #466	-> 148
/*     */       //   #463	-> 151
/*     */       //   #471	-> 153
/*     */       //   #466	-> 175
/*     */       //   #463	-> 179
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	182	0	this	Lakka/actor/LightArrayRevolverScheduler$TaskHolder;
/*     */       //   161	21	10	e	Ljava/lang/Throwable;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   90	104	104	finally
/*     */     }
/*     */     
/*     */     public void run() {
/* 476 */       extractTask(LightArrayRevolverScheduler$.MODULE$.akka$actor$LightArrayRevolverScheduler$$ExecutedTask).run();
/*     */     }
/*     */     
/*     */     public boolean cancel() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   4: getfield akka$actor$LightArrayRevolverScheduler$$CancelledTask : Ljava/lang/Runnable;
/*     */       //   7: invokespecial extractTask : (Ljava/lang/Runnable;)Ljava/lang/Runnable;
/*     */       //   10: astore_1
/*     */       //   11: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   14: getfield akka$actor$LightArrayRevolverScheduler$$ExecutedTask : Ljava/lang/Runnable;
/*     */       //   17: aload_1
/*     */       //   18: astore_2
/*     */       //   19: dup
/*     */       //   20: ifnonnull -> 31
/*     */       //   23: pop
/*     */       //   24: aload_2
/*     */       //   25: ifnull -> 38
/*     */       //   28: goto -> 43
/*     */       //   31: aload_2
/*     */       //   32: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   35: ifeq -> 43
/*     */       //   38: iconst_1
/*     */       //   39: istore_3
/*     */       //   40: goto -> 80
/*     */       //   43: getstatic akka/actor/LightArrayRevolverScheduler$.MODULE$ : Lakka/actor/LightArrayRevolverScheduler$;
/*     */       //   46: getfield akka$actor$LightArrayRevolverScheduler$$CancelledTask : Ljava/lang/Runnable;
/*     */       //   49: aload_1
/*     */       //   50: astore #4
/*     */       //   52: dup
/*     */       //   53: ifnonnull -> 65
/*     */       //   56: pop
/*     */       //   57: aload #4
/*     */       //   59: ifnull -> 73
/*     */       //   62: goto -> 78
/*     */       //   65: aload #4
/*     */       //   67: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   70: ifeq -> 78
/*     */       //   73: iconst_1
/*     */       //   74: istore_3
/*     */       //   75: goto -> 80
/*     */       //   78: iconst_0
/*     */       //   79: istore_3
/*     */       //   80: iload_3
/*     */       //   81: ifeq -> 90
/*     */       //   84: iconst_0
/*     */       //   85: istore #5
/*     */       //   87: goto -> 93
/*     */       //   90: iconst_1
/*     */       //   91: istore #5
/*     */       //   93: iload #5
/*     */       //   95: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #478	-> 0
/*     */       //   #479	-> 11
/*     */       //   #480	-> 90
/*     */       //   #478	-> 93
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	96	0	this	Lakka/actor/LightArrayRevolverScheduler$TaskHolder;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/* 483 */       return (task() == LightArrayRevolverScheduler$.MODULE$.akka$actor$LightArrayRevolverScheduler$$CancelledTask);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$10 implements Runnable {
/*     */     public void run() {}
/*     */   }
/*     */   
/*     */   public static class $anon$9 implements Runnable {
/*     */     public void run() {}
/*     */   }
/*     */   
/*     */   public static class $anon$3 implements TimerTask {
/*     */     public boolean cancel() {
/* 490 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/* 491 */       return false;
/*     */     }
/*     */     
/*     */     public void run() {}
/*     */   }
/*     */   
/*     */   public static class $anon$11 implements Cancellable {
/*     */     public boolean cancel() {
/* 496 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/* 497 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface TimerTask extends Runnable, Cancellable {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LightArrayRevolverScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */