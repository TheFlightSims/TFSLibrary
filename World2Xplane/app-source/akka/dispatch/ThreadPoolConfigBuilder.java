/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.RejectedExecutionHandler;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.Duration$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uh\001B\001\003\001\036\021q\003\0265sK\006$\007k\\8m\007>tg-[4Ck&dG-\032:\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lC\016\0011\003\002\001\t\035E\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\005\020\023\t\001\"BA\004Qe>$Wo\031;\021\005%\021\022BA\n\013\0051\031VM]5bY&T\030M\0317f\021!)\002A!f\001\n\0031\022AB2p]\032Lw-F\001\030!\tA\022$D\001\003\023\tQ\"A\001\tUQJ,\027\r\032)p_2\034uN\0344jO\"AA\004\001B\tB\003%q#A\004d_:4\027n\032\021\t\013y\001A\021A\020\002\rqJg.\033;?)\t\001\023\005\005\002\031\001!)Q#\ba\001/!)1\005\001C\001I\005As/\033;i\035\026<H\013\033:fC\022\004vn\0347XSRD7)^:u_6\024En\\2lS:<\027+^3vKR\021\001%\n\005\006M\t\002\raJ\001\020]\026<\030+^3vK\032\0137\r^8ssB\021\001f\013\b\0031%J!A\013\002\002!QC'/Z1e!>|GnQ8oM&<\027B\001\027.\0051\tV/Z;f\r\006\034Go\034:z\025\tQ#\001C\003$\001\021\005q\006\006\002!a!)\021G\fa\001e\005)\021/^3vKB\0311G\017\037\016\003QR!!\016\034\002\025\r|gnY;se\026tGO\003\0028q\005!Q\017^5m\025\005I\024\001\0026bm\006L!a\017\033\003\033\tcwnY6j]\036\fV/Z;f!\ti\004)D\001?\025\ty\004(\001\003mC:<\027BA!?\005!\021VO\0348bE2,\007\"B\"\001\t\003!\025!P<ji\"tUm\036+ie\026\fG\rU8pY^KG\017\033'j].,GM\0217pG.LgnZ)vKV,w+\033;i+:\024w.\0368eK\022\034\025\r]1dSRLX#\001\021\t\013\031\003A\021A$\002i]LG\017\033(foRC'/Z1e!>|GnV5uQ2Kgn[3e\0052|7m[5oOF+X-^3XSRD7)\0319bG&$\030\020\006\002!\021\")\021*\022a\001\025\006A1-\0319bG&$\030\020\005\002\n\027&\021AJ\003\002\004\023:$\b\"\002(\001\t\003y\025!M<ji\"tUm\036+ie\026\fG\rU8pY^KG\017[*z]\016D'o\0348pkN\fV/Z;f/&$\bNR1je:,7o\035\013\003AACQ!U'A\002I\013AAZ1jeB\021\021bU\005\003)*\021qAQ8pY\026\fg\016C\003W\001\021\005q+\001 xSRDg*Z<UQJ,\027\r\032)p_2<\026\016\0365BeJ\f\027P\0217pG.LgnZ)vKV,w+\033;i\007\006\004\030mY5us\006sGMR1je:,7o\035\013\004AaK\006\"B%V\001\004Q\005\"B)V\001\004\021\006\"B.\001\t\003a\026aD:fi\016{'/\032)p_2\034\026N_3\025\005\001j\006\"\0020[\001\004Q\025\001B:ju\026DQ\001\031\001\005\002\005\fab]3u\033\006D\bk\\8m'&TX\r\006\002!E\")al\030a\001\025\")A\r\001C\001K\006I2/\032;D_J,\007k\\8m'&TXM\022:p[\032\0137\r^8s)\021\001c\r[7\t\013\035\034\007\031\001&\002\0075Lg\016C\003jG\002\007!.\001\006nk2$\030\016\0357jKJ\004\"!C6\n\0051T!A\002#pk\ndW\rC\003oG\002\007!*A\002nCbDQ\001\035\001\005\002E\f\001d]3u\033\006D\bk\\8m'&TXM\022:p[\032\0137\r^8s)\021\001#o\035;\t\013\035|\007\031\001&\t\013%|\007\031\0016\t\0139|\007\031\001&\t\013Y\004A\021A<\0021M,GoS3fa\006c\027N^3US6,\027J\\'jY2L7\017\006\002!q\")\0210\036a\001u\006!A/[7f!\tI10\003\002}\025\t!Aj\0348h\021\025q\b\001\"\001\000\003A\031X\r^&fKB\fE.\033<f)&lW\rF\002!\003\003Aa!_?A\002\005\r\001\003BA\003\003\033i!!a\002\013\t\005%\0211B\001\tIV\024\030\r^5p]*\021QGC\005\005\003\037\t9A\001\005EkJ\fG/[8o\021\035\t\031\002\001C\001\003+\t\021d]3u\0032dwn^\"pe\026$\006N]3bIRKW.Z8viR\031\001%a\006\t\017\005e\021\021\003a\001%\006)\021\r\0347po\"9\021Q\004\001\005\002\005}\021aD:fiF+X-^3GC\016$xN]=\025\007\001\n\t\003\003\004'\0037\001\ra\n\005\b\003K\001A\021AA\024\003%\031wN\0344jOV\024X\rF\002!\003SA\001\"a\013\002$\001\007\021QF\001\003MN\004R!CA\030\003gI1!!\r\013\005)a$/\0329fCR,GM\020\t\006\023\005U\022\021H\005\004\003oQ!AB(qi&|g\016\005\004\002<\005\005\003\005\t\b\004\023\005u\022bAA \025\0051\001K]3eK\032LA!a\021\002F\tAa)\0368di&|gNC\002\002@)A\021\"!\023\001\003\003%\t!a\023\002\t\r|\007/\037\013\004A\0055\003\002C\013\002HA\005\t\031A\f\t\023\005E\003!%A\005\002\005M\023AD2paf$C-\0324bk2$H%M\013\003\003+R3aFA,W\t\tI\006\005\003\002\\\005\025TBAA/\025\021\ty&!\031\002\023Ut7\r[3dW\026$'bAA2\025\005Q\021M\0348pi\006$\030n\0348\n\t\005\035\024Q\f\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\"CA6\001\005\005I\021IA7\0035\001(o\0343vGR\004&/\0324jqV\021\021q\016\t\004{\005E\024bAA:}\t11\013\036:j]\036D\021\"a\036\001\003\003%\t!!\037\002\031A\024x\016Z;di\006\023\030\016^=\026\003)C\021\"! \001\003\003%\t!a \002\035A\024x\016Z;di\026cW-\\3oiR!\021\021QAD!\rI\0211Q\005\004\003\013S!aA!os\"I\021\021RA>\003\003\005\rAS\001\004q\022\n\004\"CAG\001\005\005I\021IAH\003=\001(o\0343vGRLE/\032:bi>\024XCAAI!\031\t\031*!'\002\0026\021\021Q\023\006\004\003/S\021AC2pY2,7\r^5p]&!\0211TAK\005!IE/\032:bi>\024\b\"CAP\001\005\005I\021AAQ\003!\031\027M\\#rk\006dGc\001*\002$\"Q\021\021RAO\003\003\005\r!!!\t\023\005\035\006!!A\005B\005%\026\001\0035bg\"\034u\016Z3\025\003)C\021\"!,\001\003\003%\t%a,\002\021Q|7\013\036:j]\036$\"!a\034\t\023\005M\006!!A\005B\005U\026AB3rk\006d7\017F\002S\003oC!\"!#\0022\006\005\t\031AAA\017%\tYLAA\001\022\003\ti,A\fUQJ,\027\r\032)p_2\034uN\0344jO\n+\030\016\0343feB\031\001$a0\007\021\005\021\021\021!E\001\003\003\034R!a0\002DF\001b!!2\002L^\001SBAAd\025\r\tIMC\001\beVtG/[7f\023\021\ti-a2\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004\037\003#\t!!5\025\005\005u\006BCAW\003\013\t\021\"\022\0020\"Q\021q[A`\003\003%\t)!7\002\013\005\004\b\017\\=\025\007\001\nY\016\003\004\026\003+\004\ra\006\005\013\003?\fy,!A\005\002\006\005\030aB;oCB\004H.\037\013\005\003G\f)\017\005\003\n\003k9\002\"CAt\003;\f\t\0211\001!\003\rAH\005\r\005\013\003W\fy,!A\005\n\0055\030a\003:fC\022\024Vm]8mm\026$\"!a<\021\007u\n\t0C\002\002tz\022aa\0242kK\016$\b")
/*     */ public class ThreadPoolConfigBuilder implements Product, Serializable {
/*     */   private final ThreadPoolConfig config;
/*     */   
/*     */   public ThreadPoolConfig config() {
/* 105 */     return this.config;
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder copy(ThreadPoolConfig config) {
/* 105 */     return new ThreadPoolConfigBuilder(config);
/*     */   }
/*     */   
/*     */   public ThreadPoolConfig copy$default$1() {
/* 105 */     return config();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 105 */     return "ThreadPoolConfigBuilder";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 105 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 105 */     int i = x$1;
/* 105 */     switch (i) {
/*     */       default:
/* 105 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 105 */     return config();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 105 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 105 */     return x$1 instanceof ThreadPoolConfigBuilder;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 105 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 80
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/ThreadPoolConfigBuilder
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/ThreadPoolConfigBuilder
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual config : ()Lakka/dispatch/ThreadPoolConfig;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual config : ()Lakka/dispatch/ThreadPoolConfig;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 76
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 76
/*     */     //   63: aload #4
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   69: ifeq -> 76
/*     */     //   72: iconst_1
/*     */     //   73: goto -> 77
/*     */     //   76: iconst_0
/*     */     //   77: ifeq -> 84
/*     */     //   80: iconst_1
/*     */     //   81: goto -> 85
/*     */     //   84: iconst_0
/*     */     //   85: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #105	-> 0
/*     */     //   #63	-> 14
/*     */     //   #105	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/dispatch/ThreadPoolConfigBuilder;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder(ThreadPoolConfig config) {
/* 105 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder withNewThreadPoolWithCustomBlockingQueue(Function0<BlockingQueue<Runnable>> newQueueFactory) {
/* 109 */     Function0<BlockingQueue<Runnable>> x$2 = newQueueFactory;
/* 109 */     boolean x$3 = config().copy$default$1();
/* 109 */     int x$4 = config().copy$default$2(), x$5 = config().copy$default$3();
/* 109 */     Duration x$6 = config().copy$default$4();
/* 109 */     RejectedExecutionHandler x$7 = config().copy$default$6();
/* 109 */     return copy(config().copy(x$3, x$4, x$5, x$6, x$2, x$7));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder withNewThreadPoolWithCustomBlockingQueue(BlockingQueue<Runnable> queue) {
/* 112 */     return withNewThreadPoolWithCustomBlockingQueue(ThreadPoolConfig$.MODULE$.reusableQueue(queue));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder withNewThreadPoolWithLinkedBlockingQueueWithUnboundedCapacity() {
/* 115 */     Function0<BlockingQueue<Runnable>> x$8 = ThreadPoolConfig$.MODULE$.linkedBlockingQueue();
/* 115 */     boolean x$9 = config().copy$default$1();
/* 115 */     int x$10 = config().copy$default$2(), x$11 = config().copy$default$3();
/* 115 */     Duration x$12 = config().copy$default$4();
/* 115 */     RejectedExecutionHandler x$13 = config().copy$default$6();
/* 115 */     return copy(config().copy(x$9, x$10, x$11, x$12, x$8, x$13));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder withNewThreadPoolWithLinkedBlockingQueueWithCapacity(int capacity) {
/* 118 */     Function0<BlockingQueue<Runnable>> x$14 = ThreadPoolConfig$.MODULE$.linkedBlockingQueue(capacity);
/* 118 */     boolean x$15 = config().copy$default$1();
/* 118 */     int x$16 = config().copy$default$2(), x$17 = config().copy$default$3();
/* 118 */     Duration x$18 = config().copy$default$4();
/* 118 */     RejectedExecutionHandler x$19 = config().copy$default$6();
/* 118 */     return copy(config().copy(x$15, x$16, x$17, x$18, x$14, x$19));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder withNewThreadPoolWithSynchronousQueueWithFairness(boolean fair) {
/* 121 */     Function0<BlockingQueue<Runnable>> x$20 = ThreadPoolConfig$.MODULE$.synchronousQueue(fair);
/* 121 */     boolean x$21 = config().copy$default$1();
/* 121 */     int x$22 = config().copy$default$2(), x$23 = config().copy$default$3();
/* 121 */     Duration x$24 = config().copy$default$4();
/* 121 */     RejectedExecutionHandler x$25 = config().copy$default$6();
/* 121 */     return copy(config().copy(x$21, x$22, x$23, x$24, x$20, x$25));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder withNewThreadPoolWithArrayBlockingQueueWithCapacityAndFairness(int capacity, boolean fair) {
/* 124 */     Function0<BlockingQueue<Runnable>> x$26 = ThreadPoolConfig$.MODULE$.arrayBlockingQueue(capacity, fair);
/* 124 */     boolean x$27 = config().copy$default$1();
/* 124 */     int x$28 = config().copy$default$2(), x$29 = config().copy$default$3();
/* 124 */     Duration x$30 = config().copy$default$4();
/* 124 */     RejectedExecutionHandler x$31 = config().copy$default$6();
/* 124 */     return copy(config().copy(x$27, x$28, x$29, x$30, x$26, x$31));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setCorePoolSize(int size) {
/* 128 */     int x$32 = size, x$33 = size;
/* 128 */     boolean x$34 = config().copy$default$1();
/* 128 */     Duration x$35 = config().copy$default$4();
/* 128 */     Function0<BlockingQueue<Runnable>> x$36 = config().copy$default$5();
/* 128 */     RejectedExecutionHandler x$37 = config().copy$default$6();
/* 130 */     int x$38 = size;
/* 130 */     boolean x$39 = config().copy$default$1();
/* 130 */     int x$40 = config().copy$default$3();
/* 130 */     Duration x$41 = config().copy$default$4();
/* 130 */     Function0<BlockingQueue<Runnable>> x$42 = config().copy$default$5();
/* 130 */     RejectedExecutionHandler x$43 = config().copy$default$6();
/* 130 */     return (config().maxPoolSize() < size) ? copy(config().copy(x$34, x$32, x$33, x$35, x$36, x$37)) : copy(config().copy(x$39, x$38, x$40, x$41, x$42, x$43));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setMaxPoolSize(int size) {
/* 134 */     int x$44 = size, x$45 = size;
/* 134 */     boolean x$46 = config().copy$default$1();
/* 134 */     Duration x$47 = config().copy$default$4();
/* 134 */     Function0<BlockingQueue<Runnable>> x$48 = config().copy$default$5();
/* 134 */     RejectedExecutionHandler x$49 = config().copy$default$6();
/* 136 */     int x$50 = size;
/* 136 */     boolean x$51 = config().copy$default$1();
/* 136 */     int x$52 = config().copy$default$2();
/* 136 */     Duration x$53 = config().copy$default$4();
/* 136 */     Function0<BlockingQueue<Runnable>> x$54 = config().copy$default$5();
/* 136 */     RejectedExecutionHandler x$55 = config().copy$default$6();
/* 136 */     return (config().corePoolSize() > size) ? copy(config().copy(x$46, x$44, x$45, x$47, x$48, x$49)) : copy(config().copy(x$51, x$52, x$50, x$53, x$54, x$55));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setCorePoolSizeFromFactor(int min, double multiplier, int max) {
/* 139 */     return setCorePoolSize(ThreadPoolConfig$.MODULE$.scaledPoolSize(min, multiplier, max));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setMaxPoolSizeFromFactor(int min, double multiplier, int max) {
/* 142 */     return setMaxPoolSize(ThreadPoolConfig$.MODULE$.scaledPoolSize(min, multiplier, max));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setKeepAliveTimeInMillis(long time) {
/* 145 */     return setKeepAliveTime((Duration)Duration$.MODULE$.apply(time, TimeUnit.MILLISECONDS));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setKeepAliveTime(Duration time) {
/* 148 */     Duration x$56 = time;
/* 148 */     boolean x$57 = config().copy$default$1();
/* 148 */     int x$58 = config().copy$default$2(), x$59 = config().copy$default$3();
/* 148 */     Function0<BlockingQueue<Runnable>> x$60 = config().copy$default$5();
/* 148 */     RejectedExecutionHandler x$61 = config().copy$default$6();
/* 148 */     return copy(config().copy(x$57, x$58, x$59, x$56, x$60, x$61));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setAllowCoreThreadTimeout(boolean allow) {
/* 151 */     return copy(config().copy(allow, config().copy$default$2(), config().copy$default$3(), config().copy$default$4(), config().copy$default$5(), config().copy$default$6()));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder setQueueFactory(Function0<BlockingQueue<Runnable>> newQueueFactory) {
/* 154 */     Function0<BlockingQueue<Runnable>> x$62 = newQueueFactory;
/* 154 */     boolean x$63 = config().copy$default$1();
/* 154 */     int x$64 = config().copy$default$2(), x$65 = config().copy$default$3();
/* 154 */     Duration x$66 = config().copy$default$4();
/* 154 */     RejectedExecutionHandler x$67 = config().copy$default$6();
/* 154 */     return copy(config().copy(x$63, x$64, x$65, x$66, x$62, x$67));
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder configure(Seq fs) {
/* 157 */     return (ThreadPoolConfigBuilder)fs.foldLeft(this, (Function2)new ThreadPoolConfigBuilder$$anonfun$configure$1(this));
/*     */   }
/*     */   
/*     */   public static <A> Function1<ThreadPoolConfig, A> andThen(Function1<ThreadPoolConfigBuilder, A> paramFunction1) {
/*     */     return ThreadPoolConfigBuilder$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, ThreadPoolConfigBuilder> compose(Function1<A, ThreadPoolConfig> paramFunction1) {
/*     */     return ThreadPoolConfigBuilder$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public class ThreadPoolConfigBuilder$$anonfun$configure$1 extends AbstractFunction2<ThreadPoolConfigBuilder, Option<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>>, ThreadPoolConfigBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ThreadPoolConfigBuilder apply(ThreadPoolConfigBuilder c, Option f) {
/* 157 */       return (ThreadPoolConfigBuilder)f.map((Function1)new ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$1(this, c)).getOrElse((Function0)new ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$2(this, c));
/*     */     }
/*     */     
/*     */     public ThreadPoolConfigBuilder$$anonfun$configure$1(ThreadPoolConfigBuilder $outer) {}
/*     */     
/*     */     public class ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$1 extends AbstractFunction1<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>, ThreadPoolConfigBuilder> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ThreadPoolConfigBuilder c$1;
/*     */       
/*     */       public final ThreadPoolConfigBuilder apply(Function1 x$1) {
/* 157 */         return (ThreadPoolConfigBuilder)x$1.apply(this.c$1);
/*     */       }
/*     */       
/*     */       public ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$1(ThreadPoolConfigBuilder$$anonfun$configure$1 $outer, ThreadPoolConfigBuilder c$1) {}
/*     */     }
/*     */     
/*     */     public class ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$2 extends AbstractFunction0<ThreadPoolConfigBuilder> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ThreadPoolConfigBuilder c$1;
/*     */       
/*     */       public final ThreadPoolConfigBuilder apply() {
/* 157 */         return this.c$1;
/*     */       }
/*     */       
/*     */       public ThreadPoolConfigBuilder$$anonfun$configure$1$$anonfun$apply$2(ThreadPoolConfigBuilder$$anonfun$configure$1 $outer, ThreadPoolConfigBuilder c$1) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ThreadPoolConfigBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */