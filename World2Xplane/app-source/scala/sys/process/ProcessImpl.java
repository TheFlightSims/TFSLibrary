/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.SyncVar;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Either;
/*     */ import scala.util.Left;
/*     */ import scala.util.Right;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\005aAC\001\003!\003\r\tA\001\005\003|\nY\001K]8dKN\034\030*\0349m\025\t\031A!A\004qe>\034Wm]:\013\005\0251\021aA:zg*\tq!A\003tG\006d\027m\005\002\001\023A\021!bC\007\002\r%\021AB\002\002\007\003:L(+\0324\t\0139\001A\021\001\t\002\r\021Jg.\033;%\007\001!\022!\005\t\003\025II!a\005\004\003\tUs\027\016^\004\007+\001A\tA\001\f\002\013M\003\030m\0368\021\005]AR\"\001\001\007\re\001\001\022\001\002\033\005\025\031\006/Y<o'\tA\022\002C\003\0351\021\005Q$\001\004=S:LGO\020\013\002-!)q\004\007C\001A\005)\021\r\0359msR\021\021%\013\t\003E\035j\021a\t\006\003I\025\nA\001\\1oO*\ta%\001\003kCZ\f\027B\001\025$\005\031!\006N]3bI\"1!F\bCA\002-\n\021A\032\t\004\0251\n\022BA\027\007\005!a$-\0378b[\026t\004\"B\020\031\t\003yCcA\0211c!1!F\fCA\002-BQA\r\030A\002M\na\001Z1f[>t\007C\001\0065\023\t)dAA\004C_>dW-\0318\b\r]\002\001\022\001\0029\003\0311U\017^;sKB\021q#\017\004\007u\001A\tAA\036\003\r\031+H/\036:f'\tI\024\002C\003\035s\021\005Q\bF\0019\021\025y\022\b\"\001@+\t\001e\t\006\002B\037B\031!B\021#\n\005\r3!!\003$v]\016$\030n\03481!\t)e\t\004\001\005\013\035s$\031\001%\003\003Q\013\"!\023'\021\005)Q\025BA&\007\005\035qu\016\0365j]\036\004\"AC'\n\00593!aA!os\"1!F\020CA\002A\0032A\003\027E\r\025\021\006\001\001\002T\005)\te\016\032)s_\016,7o]\n\003#R\003\"aF+\007\013Y\003\001AA,\003#M+\027/^3oi&\fG\016\025:pG\026\0348o\005\002V1B\021q#\027\004\0075\002\t\tAA.\003\037\r{W\016]8v]\022\004&o\\2fgN\034\"!\027/\021\005]ifA\0020\001\003\003\021qL\001\007CCNL7\r\025:pG\026\0348oE\002^\023\001\004\"!\0312\016\003\tI!a\031\002\003\017A\023xnY3tg\")A$\030C\001KR\tA\fC\003h;\032\005\001#A\003ti\006\024H\017C\003\0353\022\005\021\016F\001Y\021\025Y\027\f\"\001\021\003\035!Wm\035;s_fDQ!\\-\005\0029\f\021\"\032=jiZ\013G.^3\025\003=\004\"A\0039\n\005E4!aA%oi\")q-\027C\001!!AA/\027EDB\023%Q/A\002yIQ*\022A\036\t\005\025]LX0\003\002y\r\t1A+\0369mKJ\0022A\003\"{!\rQ1p\\\005\003y\032\021aa\0249uS>t\007c\001\006C#!Aq0\027E\001B\003&a/\001\003yIQ\002\003BCA\0023\"\025\r\021\"\005\002\006\005aq-\032;Fq&$h+\0317vKV\t\021\020C\005\002\neC\t\021)Q\005s\006iq-\032;Fq&$h+\0317vK\002B!\"!\004Z\021\013\007I\021CA\b\003%!Wm\035;s_f,'/F\001~\021%\t\031\"\027E\001B\003&Q0\001\006eKN$(o\\=fe\002B\001\"a\006ZA\033E\021\021D\001\020eVt\027I\0343Fq&$h+\0317vKR\t!\020\003\005\002\036e\003K\021CA\020\003A\021XO\\%oi\026\024(/\0369uS\ndW-\006\003\002\"\005%B\003BA\022\003_!B!!\n\002,A!!b_A\024!\r)\025\021\006\003\007\017\006m!\031\001%\t\021\0055\0221\004CA\002-\n1\002Z3tiJ|\0270S7qY\"I\021\021GA\016\t\003\007\0211G\001\007C\016$\030n\0348\021\t)a\023q\005\005\013\003o)&\021!Q\001\n\005e\022!A1\021\007\005\fY$C\002\002>\t\021a\002\025:pG\026\0348OQ;jY\022,'\017\003\006\002BU\023\t\021)A\005\003s\t\021A\031\005\013\003\013*&\021!Q\001\n\005\035\023AA5p!\r\t\027\021J\005\004\003\027\022!!\003)s_\016,7o]%P\021)\ty%\026B\001B\003%\021\021K\001\026KZ\fG.^1uKN+7m\0348e!J|7-Z:t!\025Q\0211K84\023\r\t)F\002\002\n\rVt7\r^5p]FBa\001H+\005\002\005eC#\003+\002\\\005u\023qLA1\021!\t9$a\026A\002\005e\002\002CA!\003/\002\r!!\017\t\021\005\025\023q\013a\001\003\017B\001\"a\024\002X\001\007\021\021\013\005\t\003/)\006\025\"\025\002\032!Q\021qG)\003\002\003\006I!!\017\t\025\005\005\023K!A!\002\023\tI\004\003\006\002FE\023\t\021)A\005\003\017Ba\001H)\005\002\0055D\003CA8\003c\n\031(!\036\021\005]\t\006\002CA\034\003W\002\r!!\017\t\021\005\005\0231\016a\001\003sA\001\"!\022\002l\001\007\021q\t\004\b\003s\002\001AAA>\005%y%\017\025:pG\026\0348oE\002\002xQC1\"a\016\002x\t\005\t\025!\003\002:!Y\021\021IA<\005\003\005\013\021BA\035\021-\t)%a\036\003\002\003\006I!a\022\t\017q\t9\b\"\001\002\006RA\021qQAE\003\027\013i\tE\002\030\003oB\001\"a\016\002\004\002\007\021\021\b\005\t\003\003\n\031\t1\001\002:!A\021QIAB\001\004\t9EB\004\002\022\002\001!!a%\003\037A\023xnY3tgN+\027/^3oG\026\0342!a$U\021-\t9$a$\003\002\003\006I!!\017\t\027\005\005\023q\022B\001B\003%\021\021\b\005\f\003\013\nyI!A!\002\023\t9\005C\004\035\003\037#\t!!(\025\021\005}\025\021UAR\003K\0032aFAH\021!\t9$a'A\002\005e\002\002CA!\0037\003\r!!\017\t\021\005\025\0231\024a\001\003\0172q!!+\001\001\t\tYK\001\bQSB,G\r\025:pG\026\0348/Z:\024\007\005\035\006\fC\006\0028\005\035&\021!Q\001\n\005e\002bCA!\003O\023\t\021)A\005\003sA1\"a-\002(\n\005\t\025!\003\002H\005IA-\0324bk2$\030j\024\005\013\003o\0139K!A!\002\023\031\024a\002;p\013J\024xN\035\005\b9\005\035F\021AA^))\ti,a0\002B\006\r\027Q\031\t\004/\005\035\006\002CA\034\003s\003\r!!\017\t\021\005\005\023\021\030a\001\003sA\001\"a-\002:\002\007\021q\t\005\b\003o\013I\f1\0014\021%\t9\"a*!\n#\nIB\002\005\002L\002\t\tAAAg\005)\001\026\016]3UQJ,\027\rZ\n\004\003\023\f\003BCAi\003\023\024\t\021)A\005g\0051\021n]*j].D1\"!6\002J\n\005\t\025!\003\002X\0069A.\0312fY\032s\007\003\002\006C\0033\004B!a7\002b:\031!\"!8\n\007\005}g!\001\004Qe\026$WMZ\005\005\003G\f)O\001\004TiJLgn\032\006\004\003?4\001b\002\017\002J\022\005\021\021\036\013\007\003W\fi/a<\021\007]\tI\rC\004\002R\006\035\b\031A\032\t\021\005U\027q\035a\001\003/Dq!a=\002J\032\005\001#A\002sk:D\021\"a>\002J\022\005!!!?\002\017I,h\016\\8paR)\021#a?\003\016!A\021Q`A{\001\004\ty0A\002te\016\004BA!\001\003\b9\031\021Ma\001\n\007\t\025!!A\bqe>\034Wm]:J]R,'O\\1m\023\021\021IAa\003\003\027%s\007/\036;TiJ,\027-\034\006\004\005\013\021\001\002\003B\b\003k\004\rA!\005\002\007\021\034H\017\005\003\003\002\tM\021\002\002B\013\005\027\021AbT;uaV$8\013\036:fC6D\001B!\007\002J\022%!1D\001\nS>D\025M\0343mKJ$2!\005B\017\021!\021yBa\006A\002\t\005\022!A3\021\t\t\005!1E\005\005\005K\021YAA\006J\037\026C8-\0329uS>tga\002B\025\001\001\021!1\006\002\013!&\004XmU8ve\016,7\003\002B\024\003WD1Ba\f\003(\t\005\t\025!\003\0032\005i1-\036:sK:$8k\\;sG\026\004bA!\001\0034\t]\022\002\002B\033\005\027\021qaU=oGZ\013'\017\005\003\013w\006}\bb\003B\036\005O\021\t\021)A\005\005{\tA\001]5qKB!!q\bB\"\033\t\021\tEC\002\002F\025JAA!\022\003B\t\t\002+\0339fI>+H\017];u'R\024X-Y7\t\027\t%#q\005B\001J\003%!1J\001\006Y\006\024W\r\034\t\005\0251\nI\016C\004\035\005O!\tAa\024\025\021\tE#1\013B+\005/\0022a\006B\024\021!\021yC!\024A\002\tE\002\002\003B\036\005\033\002\rA!\020\t\023\t%#Q\nCA\002\t-\003bBAz\005O!)\005\005\004\b\005;\002\001A\001B0\005!\001\026\016]3TS:\\7\003\002B.\003WD1Ba\017\003\\\t\005\t\025!\003\003dA!!q\bB3\023\021\0219G!\021\003!AK\007/\0323J]B,Ho\025;sK\006l\007b\003B6\0057\022\t\021)A\005\005[\n1bY;se\026tGoU5oWB1!\021\001B\032\005_\002BAC>\003\022!Y!\021\nB.\005\003%\013\021\002B&\021\035a\"1\fC\001\005k\"\002Ba\036\003z\tm$Q\020\t\004/\tm\003\002\003B\036\005g\002\rAa\031\t\021\t-$1\017a\001\005[B\021B!\023\003t\021\005\rAa\023\t\017\005M(1\fC#!\0319!1\021\001\001\005\t\025%\001\004#v[6L\bK]8dKN\0348\003\002BA\023\001D1\"!\r\003\002\n\005I\025!\003\003\nB\031!\002L8\t\017q\021\t\t\"\001\003\016R!!q\022BI!\r9\"\021\021\005\n\003c\021Y\t\"a\001\005\023C\021B!&\003\002\002\006IAa&\002\021\025D\030\016^\"pI\026\0042A\003\"p\021\031i'\021\021C!]\"11N!!\005BA1qAa(\001\001\t\021\tKA\007TS6\004H.\032)s_\016,7o]\n\005\005;K\001\rC\006\003&\nu%\021!Q\001\n\t\035\026!\0019\021\t\t\005!\021V\005\005\005W\023YA\001\005K!J|7-Z:t\021)\021yK!(\003\002\003\006I!I\001\fS:\004X\017\036+ie\026\fG\rC\006\0034\nu%\021!Q\001\n\tU\026!D8viB,H\017\0265sK\006$7\017E\003\0038\n\035\027E\004\003\003:\n\rg\002\002B^\005\003l!A!0\013\007\t}v\"\001\004=e>|GOP\005\002\017%\031!Q\031\004\002\017A\f7m[1hK&!!\021\032Bf\005\021a\025n\035;\013\007\t\025g\001C\004\035\005;#\tAa4\025\021\tE'1\033Bk\005/\0042a\006BO\021!\021)K!4A\002\t\035\006b\002BX\005\033\004\r!\t\005\t\005g\023i\r1\001\0036\"1QN!(\005B9Daa\033BO\t\003\002ba\002Bp\001\t\021!\021\035\002\016)\"\024X-\0313Qe>\034Wm]:\024\t\tu\027\002\031\005\013\005K\024iN!A!\002\023\t\023A\002;ie\026\fG\rC\006\003j\nu'\021!Q\001\n\t-\030aB:vG\016,7o\035\t\006\005\003\021\031d\r\005\b9\tuG\021\001Bx)\031\021\tPa=\003vB\031qC!8\t\017\t\025(Q\036a\001C!A!\021\036Bw\001\004\021Y\017\003\004n\005;$\tE\034\005\007W\nuG\021\t\t\017\007\005\024i0C\002\003\000\n\tq\001\025:pG\026\0348\017")
/*     */ public interface ProcessImpl {
/*     */   Spawn$ Spawn();
/*     */   
/*     */   Future$ Future();
/*     */   
/*     */   public class Spawn$ {
/*     */     public Spawn$(Process$ $outer) {}
/*     */     
/*     */     public Thread apply(Function0<BoxedUnit> f) {
/*  20 */       return apply(f, false);
/*     */     }
/*     */     
/*     */     public Thread apply(Function0 f, boolean daemon) {
/*  22 */       Thread thread = new ProcessImpl$Spawn$$anon$1(this, f);
/*  23 */       thread.setDaemon(daemon);
/*  24 */       thread.start();
/*  25 */       return thread;
/*     */     }
/*     */     
/*     */     public static class ProcessImpl$Spawn$$anon$1 extends Thread {
/*     */       private final Function0 f$1;
/*     */       
/*     */       public void run() {
/*     */         this.f$1.apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ProcessImpl$Spawn$$anon$1(ProcessImpl.Spawn$ $outer, Function0 f$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$ {
/*     */     public Future$(Process$ $outer) {}
/*     */     
/*     */     public <T> Function0<T> apply(Function0 f) {
/*  30 */       SyncVar result = new SyncVar();
/*  35 */       ProcessImpl$Future$$anonfun$apply$1 processImpl$Future$$anonfun$apply$1 = new ProcessImpl$Future$$anonfun$apply$1(this, f, result);
/*  35 */       ProcessImpl.Spawn$ spawn$ = this.$outer.Spawn();
/*  35 */       Thread thread1 = new ProcessImpl$Spawn$$anon$1(spawn$, (Function0)processImpl$Future$$anonfun$apply$1);
/*  35 */       thread1.setDaemon(false);
/*  35 */       thread1.start();
/*  37 */       return (Function0<T>)new ProcessImpl$Future$$anonfun$apply$4(this, result);
/*     */     }
/*     */     
/*     */     public final void scala$sys$process$ProcessImpl$Future$$run$1(Function0 f$2, SyncVar result$1) {
/*     */       try {
/*     */         result$1.set(scala.package$.MODULE$.Right().apply(f$2.apply()));
/*     */       } catch (Exception exception) {
/*     */         result$1.set(scala.package$.MODULE$.Left().apply(exception));
/*     */       } 
/*     */     }
/*     */     
/*     */     public static class ProcessImpl$Future$$anonfun$apply$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Function0 f$2;
/*     */       
/*     */       public final SyncVar result$1;
/*     */       
/*     */       public final void apply() {
/*     */         this.$outer.scala$sys$process$ProcessImpl$Future$$run$1(this.f$2, this.result$1);
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.$outer.scala$sys$process$ProcessImpl$Future$$run$1(this.f$2, this.result$1);
/*     */       }
/*     */       
/*     */       public ProcessImpl$Future$$anonfun$apply$1(ProcessImpl.Future$ $outer, Function0 f$2, SyncVar result$1) {}
/*     */     }
/*     */     
/*     */     public static class ProcessImpl$Future$$anonfun$apply$4 extends AbstractFunction0<T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SyncVar result$1;
/*     */       
/*     */       public final T apply() {
/*  37 */         Either either = (Either)this.result$1.get();
/*  38 */         if (either instanceof Right)
/*  38 */           Right right = (Right)either; 
/*  39 */         if (either instanceof Left) {
/*  39 */           Left left = (Left)either;
/*  39 */           throw (Throwable)left.a();
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       }
/*     */       
/*     */       public ProcessImpl$Future$$anonfun$apply$4(ProcessImpl.Future$ $outer, SyncVar result$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class AndProcess extends SequentialProcess {
/*     */     public AndProcess(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io) {
/*  44 */       super($outer, 
/*     */           
/*  48 */           a, b, io, (Function1<Object, Object>)new ProcessImpl$AndProcess$$anonfun$$init$$1($outer));
/*     */     }
/*     */     
/*     */     public class ProcessImpl$AndProcess$$anonfun$$init$$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$1) {
/*  48 */         return apply$mcZI$sp(x$1);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$1) {
/*  48 */         return (x$1 == 0);
/*     */       }
/*     */       
/*     */       public ProcessImpl$AndProcess$$anonfun$$init$$1(Process$ $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class OrProcess extends SequentialProcess {
/*     */     public OrProcess(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io) {
/*  50 */       super($outer, 
/*     */           
/*  54 */           a, b, io, (Function1<Object, Object>)new ProcessImpl$OrProcess$$anonfun$$init$$2($outer));
/*     */     }
/*     */     
/*     */     public class ProcessImpl$OrProcess$$anonfun$$init$$2 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$2) {
/*  54 */         return apply$mcZI$sp(x$2);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$2) {
/*  54 */         return (x$2 != 0);
/*     */       }
/*     */       
/*     */       public ProcessImpl$OrProcess$$anonfun$$init$$2(Process$ $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class ProcessSequence extends SequentialProcess {
/*     */     public ProcessSequence(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io) {
/*  56 */       super($outer, 
/*     */           
/*  60 */           a, b, io, (Function1<Object, Object>)new ProcessImpl$ProcessSequence$$anonfun$$init$$3($outer));
/*     */     }
/*     */     
/*     */     public class ProcessImpl$ProcessSequence$$anonfun$$init$$3 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$3) {
/*  60 */         return true;
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$3) {
/*  60 */         return true;
/*     */       }
/*     */       
/*     */       public ProcessImpl$ProcessSequence$$anonfun$$init$$3(Process$ $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class SequentialProcess extends CompoundProcess {
/*     */     private final ProcessBuilder a;
/*     */     
/*     */     public final ProcessBuilder scala$sys$process$ProcessImpl$SequentialProcess$$b;
/*     */     
/*     */     public final ProcessIO scala$sys$process$ProcessImpl$SequentialProcess$$io;
/*     */     
/*     */     public final Function1<Object, Object> scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess;
/*     */     
/*     */     public SequentialProcess(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO io, Function1<Object, Object> evaluateSecondProcess) {
/*  62 */       super($outer);
/*     */     }
/*     */     
/*     */     public Option<Object> runAndExitValue() {
/*  70 */       Process first = this.a.run(this.scala$sys$process$ProcessImpl$SequentialProcess$$io);
/*  71 */       ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5 processImpl$SequentialProcess$$anonfun$runAndExitValue$5 = new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5(this);
/*  71 */       int i = BoxesRunTime.unboxToInt(option.get());
/*  71 */       Process second1 = this.scala$sys$process$ProcessImpl$SequentialProcess$$b.run(this.scala$sys$process$ProcessImpl$SequentialProcess$$io);
/*     */       Option<?> option;
/*  71 */       return (option = runInterruptible((Function0<?>)new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$1(this, first), (Function0<BoxedUnit>)new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$2(this, first))).isEmpty() ? (Option<Object>)scala.None$.MODULE$ : (this.scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess.apply$mcZI$sp(i) ? runInterruptible((Function0)new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$2(processImpl$SequentialProcess$$anonfun$runAndExitValue$5, second1), (Function0<BoxedUnit>)new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$3(processImpl$SequentialProcess$$anonfun$runAndExitValue$5, second1)) : (Option<Object>)new Some(BoxesRunTime.boxToInteger(i)));
/*     */     }
/*     */     
/*     */     public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Process first$1;
/*     */       
/*     */       public final int apply() {
/*  71 */         return this.first$1.exitValue();
/*     */       }
/*     */       
/*     */       public int apply$mcI$sp() {
/*  71 */         return this.first$1.exitValue();
/*     */       }
/*     */       
/*     */       public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$1(ProcessImpl.SequentialProcess $outer, Process first$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Process first$1;
/*     */       
/*     */       public final void apply() {
/*  71 */         this.first$1.destroy();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*  71 */         this.first$1.destroy();
/*     */       }
/*     */       
/*     */       public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$2(ProcessImpl.SequentialProcess $outer, Process first$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5 extends AbstractFunction1<Object, Option<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5(ProcessImpl.SequentialProcess $outer) {}
/*     */       
/*     */       public final Option<Object> apply(int codeA) {
/*  73 */         Process second = this.$outer.scala$sys$process$ProcessImpl$SequentialProcess$$b.run(this.$outer.scala$sys$process$ProcessImpl$SequentialProcess$$io);
/*  74 */         return this.$outer.scala$sys$process$ProcessImpl$SequentialProcess$$evaluateSecondProcess.apply$mcZI$sp(codeA) ? this.$outer.<Object>runInterruptible((Function0)new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$2(this, second), (Function0<BoxedUnit>)new ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$3(this, second)) : 
/*     */           
/*  76 */           (Option<Object>)new Some(BoxesRunTime.boxToInteger(codeA));
/*     */       }
/*     */       
/*     */       public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$2 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Process second$1;
/*     */         
/*     */         public final int apply() {
/*     */           return this.second$1.exitValue();
/*     */         }
/*     */         
/*     */         public int apply$mcI$sp() {
/*     */           return this.second$1.exitValue();
/*     */         }
/*     */         
/*     */         public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$2(ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5 $outer, Process second$1) {}
/*     */       }
/*     */       
/*     */       public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$3 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Process second$1;
/*     */         
/*     */         public final void apply() {
/*     */           this.second$1.destroy();
/*     */         }
/*     */         
/*     */         public void apply$mcV$sp() {
/*     */           this.second$1.destroy();
/*     */         }
/*     */         
/*     */         public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$3(ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5 $outer, Process second$1) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$2 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Process second$1;
/*     */       
/*     */       public final int apply() {
/*     */         return this.second$1.exitValue();
/*     */       }
/*     */       
/*     */       public int apply$mcI$sp() {
/*     */         return this.second$1.exitValue();
/*     */       }
/*     */       
/*     */       public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$2(ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5 $outer, Process second$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$3 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Process second$1;
/*     */       
/*     */       public final void apply() {
/*     */         this.second$1.destroy();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.second$1.destroy();
/*     */       }
/*     */       
/*     */       public ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5$$anonfun$apply$3(ProcessImpl$SequentialProcess$$anonfun$runAndExitValue$5 $outer, Process second$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class BasicProcess implements Process {
/*     */     public abstract void start();
/*     */     
/*     */     public BasicProcess(Process$ $outer) {}
/*     */   }
/*     */   
/*     */   public abstract class CompoundProcess extends BasicProcess {
/*     */     private Tuple2<Function0<Option<Object>>, Function0<BoxedUnit>> x$4;
/*     */     
/*     */     private Function0<Option<Object>> getExitValue;
/*     */     
/*     */     private Function0<BoxedUnit> destroyer;
/*     */     
/*     */     private volatile byte bitmap$0;
/*     */     
/*     */     public CompoundProcess(Process$ $outer) {
/*  85 */       super($outer);
/*     */     }
/*     */     
/*     */     public void destroy() {
/*  86 */       destroyer().apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public int exitValue() {
/*     */       Option option;
/*  87 */       if ((option = (Option)getExitValue().apply()).isEmpty()) {
/*  87 */         scala.sys.package$ package$ = scala.sys.package$.MODULE$;
/*  87 */         throw new RuntimeException("No exit code: process destroyed.");
/*     */       } 
/*  87 */       return BoxesRunTime.unboxToInt(option.get());
/*     */     }
/*     */     
/*     */     public void start() {
/*  88 */       getExitValue();
/*     */     }
/*     */     
/*     */     private Tuple2 x$4$lzycompute() {
/*  90 */       synchronized (this) {
/*  90 */         if ((byte)(this.bitmap$0 & 0x1) == 0) {
/*  91 */           SyncVar code = new SyncVar();
/*  92 */           code.set(scala.None$.MODULE$);
/*  93 */           $anonfun$1 $anonfun$1 = new $anonfun$1(this, code);
/*  93 */           ProcessImpl.Spawn$ spawn$1 = scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Spawn();
/*  93 */           Thread thread1 = new ProcessImpl$Spawn$$anon$1(spawn$1, (Function0)$anonfun$1);
/*  93 */           thread1.setDaemon(false);
/*  93 */           thread1.start();
/*  96 */           $anonfun$3 $anonfun$3 = new $anonfun$3(this, code, thread1);
/*  96 */           ProcessImpl.Future$ future$ = scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Future();
/*  96 */           SyncVar result1 = new SyncVar();
/*  96 */           ProcessImpl$Future$$anonfun$apply$1 processImpl$Future$$anonfun$apply$1 = new ProcessImpl$Future$$anonfun$apply$1(future$, (Function0)$anonfun$3, result1);
/*  96 */           ProcessImpl.Spawn$ spawn$2 = future$.$outer.Spawn();
/*  96 */           Thread thread11 = new ProcessImpl$Spawn$$anon$1(spawn$2, (Function0)processImpl$Future$$anonfun$apply$1);
/*  96 */           thread11.setDaemon(false);
/*  96 */           thread11.start();
/*  96 */           Tuple2 tuple2 = new Tuple2(new ProcessImpl$Future$$anonfun$apply$4(future$, result1), 
/*  97 */               new $anonfun$2(this, thread1));
/*     */           if (tuple2 != null) {
/*     */             Tuple2<Function0<Option<Object>>, Function0<BoxedUnit>> tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/*     */             this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */           } else {
/*     */             MatchError matchError = new MatchError(tuple2);
/*     */             this;
/*     */             /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/sys/process/ProcessImpl}.Lscala/sys/process/ProcessImpl$CompoundProcess;}} */
/*     */             throw matchError;
/*     */           } 
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/sys/process/ProcessImpl}.Lscala/sys/process/ProcessImpl$CompoundProcess;}} */
/*     */         return this.x$4;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Function0 getExitValue$lzycompute() {
/*     */       synchronized (this) {
/*     */         if ((byte)(this.bitmap$0 & 0x2) == 0) {
/*     */           this.getExitValue = (Function0<Option<Object>>)x$4()._1();
/*     */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/sys/process/ProcessImpl}.Lscala/sys/process/ProcessImpl$CompoundProcess;}} */
/*     */         return this.getExitValue;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Function0 destroyer$lzycompute() {
/*     */       synchronized (this) {
/*     */         if ((byte)(this.bitmap$0 & 0x4) == 0) {
/*     */           this.destroyer = (Function0<BoxedUnit>)x$4()._2();
/*     */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x4);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/sys/process/ProcessImpl}.Lscala/sys/process/ProcessImpl$CompoundProcess;}} */
/*     */         return this.destroyer;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Tuple2<Function0<Option<Object>>, Function0<BoxedUnit>> x$4() {
/*     */       return ((byte)(this.bitmap$0 & 0x1) == 0) ? x$4$lzycompute() : this.x$4;
/*     */     }
/*     */     
/*     */     public Function0<Option<Object>> getExitValue() {
/*     */       return ((byte)(this.bitmap$0 & 0x2) == 0) ? getExitValue$lzycompute() : this.getExitValue;
/*     */     }
/*     */     
/*     */     public Function0<BoxedUnit> destroyer() {
/*     */       return ((byte)(this.bitmap$0 & 0x4) == 0) ? destroyer$lzycompute() : this.destroyer;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final SyncVar code$1;
/*     */       
/*     */       public final void apply() {
/*     */         this.code$1.set(this.$outer.runAndExitValue());
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.code$1.set(this.$outer.runAndExitValue());
/*     */       }
/*     */       
/*     */       public $anonfun$1(ProcessImpl.CompoundProcess $outer, SyncVar code$1) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$3 extends AbstractFunction0<Option<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SyncVar code$1;
/*     */       
/*     */       private final Thread thread$1;
/*     */       
/*     */       public final Option<Object> apply() {
/*     */         this.thread$1.join();
/*     */         return (Option<Object>)this.code$1.get();
/*     */       }
/*     */       
/*     */       public $anonfun$3(ProcessImpl.CompoundProcess $outer, SyncVar code$1, Thread thread$1) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Thread thread$1;
/*     */       
/*     */       public final void apply() {
/*  97 */         this.thread$1.interrupt();
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*  97 */         this.thread$1.interrupt();
/*     */       }
/*     */       
/*     */       public $anonfun$2(ProcessImpl.CompoundProcess $outer, Thread thread$1) {}
/*     */     }
/*     */     
/*     */     public <T> Option<T> runInterruptible(Function0 action, Function0 destroyImpl) {
/*     */       try {
/*     */       
/*     */       } finally {
/*     */         boolean bool;
/* 105 */         Exception exception = null;
/* 106 */         ProcessImpl$CompoundProcess$$anonfun$4 processImpl$CompoundProcess$$anonfun$4 = new ProcessImpl$CompoundProcess$$anonfun$4(this, destroyImpl);
/* 106 */         processInternal$ processInternal$ = processInternal$.MODULE$;
/* 106 */         processInternal$$anonfun$onInterrupt$1 processInternal$$anonfun$onInterrupt$1 = new processInternal$$anonfun$onInterrupt$1((Function0)processImpl$CompoundProcess$$anonfun$4);
/* 106 */         Throwable throwable = exception;
/* 106 */         if (throwable instanceof InterruptedException) {
/* 106 */           bool = true;
/*     */         } else {
/* 106 */           bool = false;
/*     */         } 
/*     */       } 
/*     */       return (Option<T>)new Some(action.apply());
/*     */     }
/*     */     
/*     */     public abstract Option<Object> runAndExitValue();
/*     */     
/*     */     public class ProcessImpl$CompoundProcess$$anonfun$4 extends AbstractFunction0<scala.None$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 destroyImpl$1;
/*     */       
/*     */       public final scala.None$ apply() {
/* 106 */         this.destroyImpl$1.apply$mcV$sp();
/* 106 */         return scala.None$.MODULE$;
/*     */       }
/*     */       
/*     */       public ProcessImpl$CompoundProcess$$anonfun$4(ProcessImpl.CompoundProcess $outer, Function0 destroyImpl$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class PipedProcesses extends CompoundProcess {
/*     */     public final ProcessBuilder scala$sys$process$ProcessImpl$PipedProcesses$$a;
/*     */     
/*     */     public final ProcessBuilder scala$sys$process$ProcessImpl$PipedProcesses$$b;
/*     */     
/*     */     private final ProcessIO defaultIO;
/*     */     
/*     */     private final boolean toError;
/*     */     
/*     */     public PipedProcesses(Process$ $outer, ProcessBuilder a, ProcessBuilder b, ProcessIO defaultIO, boolean toError) {
/* 110 */       super($outer);
/*     */     }
/*     */     
/*     */     public Option<Object> runAndExitValue() {
/* 112 */       SyncVar<Option<InputStream>> currentSource = new SyncVar();
/* 113 */       PipedOutputStream pipeOut = new PipedOutputStream();
/* 114 */       ProcessImpl.PipeSource source = new ProcessImpl.PipeSource(scala$sys$process$ProcessImpl$PipedProcesses$$$outer(), currentSource, pipeOut, (Function0<String>)new ProcessImpl$PipedProcesses$$anonfun$5(this));
/* 115 */       source.start();
/* 117 */       PipedInputStream pipeIn = new PipedInputStream(pipeOut);
/* 118 */       SyncVar<Option<OutputStream>> currentSink = new SyncVar();
/* 119 */       ProcessImpl.PipeSink sink = new ProcessImpl.PipeSink(scala$sys$process$ProcessImpl$PipedProcesses$$$outer(), pipeIn, currentSink, (Function0<String>)new ProcessImpl$PipedProcesses$$anonfun$6(this));
/* 120 */       sink.start();
/* 124 */       ProcessIO firstIO = 
/* 125 */         this.toError ? 
/* 126 */         this.defaultIO.withError((Function1<InputStream, BoxedUnit>)new ProcessImpl$PipedProcesses$$anonfun$7(this, currentSource)) : 
/*     */         
/* 128 */         this.defaultIO.withOutput((Function1<InputStream, BoxedUnit>)new ProcessImpl$PipedProcesses$$anonfun$8(this, currentSource));
/* 129 */       ProcessIO secondIO = this.defaultIO.withInput((Function1<OutputStream, BoxedUnit>)new ProcessImpl$PipedProcesses$$anonfun$9(this, currentSink));
/* 131 */       Process second = this.scala$sys$process$ProcessImpl$PipedProcesses$$b.run(secondIO);
/* 132 */       Process first = this.scala$sys$process$ProcessImpl$PipedProcesses$$a.run(firstIO);
/*     */       try {
/* 142 */         return runInterruptible((Function0)new ProcessImpl$PipedProcesses$$anonfun$runAndExitValue$3(this, currentSource, currentSink, second, first), (Function0<BoxedUnit>)new ProcessImpl$PipedProcesses$$anonfun$runAndExitValue$4(this, second, first));
/*     */       } finally {
/* 148 */         BasicIO$.MODULE$.close(pipeIn);
/* 149 */         BasicIO$.MODULE$.close(pipeOut);
/*     */       } 
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$5 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$a.toString();
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$5(ProcessImpl.PipedProcesses $outer) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$6 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$b.toString();
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$6(ProcessImpl.PipedProcesses $outer) {}
/*     */     }
/*     */     
/*     */     public final void scala$sys$process$ProcessImpl$PipedProcesses$$handleOutOrError$1(InputStream fromOutput, SyncVar currentSource$1) {
/*     */       currentSource$1.put(new Some(fromOutput));
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$7 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SyncVar currentSource$1;
/*     */       
/*     */       public final void apply(InputStream fromOutput) {
/*     */         this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$handleOutOrError$1(fromOutput, this.currentSource$1);
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$7(ProcessImpl.PipedProcesses $outer, SyncVar currentSource$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$8 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SyncVar currentSource$1;
/*     */       
/*     */       public final void apply(InputStream fromOutput) {
/*     */         this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$handleOutOrError$1(fromOutput, this.currentSource$1);
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$8(ProcessImpl.PipedProcesses $outer, SyncVar currentSource$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$9 extends AbstractFunction1<OutputStream, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SyncVar currentSink$1;
/*     */       
/*     */       public final void apply(OutputStream toInput) {
/*     */         this.currentSink$1.put(new Some(toInput));
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$9(ProcessImpl.PipedProcesses $outer, SyncVar currentSink$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$runAndExitValue$3 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final SyncVar currentSource$1;
/*     */       
/*     */       private final SyncVar currentSink$1;
/*     */       
/*     */       private final Process second$2;
/*     */       
/*     */       private final Process first$2;
/*     */       
/*     */       public final int apply() {
/*     */         return apply$mcI$sp();
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$runAndExitValue$3(ProcessImpl.PipedProcesses $outer, SyncVar currentSource$1, SyncVar currentSink$1, Process second$2, Process first$2) {}
/*     */       
/*     */       public int apply$mcI$sp() {
/*     */         int exit1 = this.first$2.exitValue();
/*     */         this.currentSource$1.put(scala.None$.MODULE$);
/*     */         this.currentSink$1.put(scala.None$.MODULE$);
/*     */         int exit2 = this.second$2.exitValue();
/*     */         return this.$outer.scala$sys$process$ProcessImpl$PipedProcesses$$b.hasExitValue() ? exit2 : exit1;
/*     */       }
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipedProcesses$$anonfun$runAndExitValue$4 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Process second$2;
/*     */       
/*     */       public final Process first$2;
/*     */       
/*     */       public final void apply() {
/*     */         this.first$2.destroy();
/*     */         this.second$2.destroy();
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipedProcesses$$anonfun$runAndExitValue$4(ProcessImpl.PipedProcesses $outer, Process second$2, Process first$2) {}
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.first$2.destroy();
/*     */         this.second$2.destroy();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class PipeThread extends Thread {
/*     */     private final boolean isSink;
/*     */     
/*     */     private final Function0<String> labelFn;
/*     */     
/*     */     public PipeThread(Process$ $outer, boolean isSink, Function0<String> labelFn) {}
/*     */     
/*     */     public void runloop(InputStream src, OutputStream dst) {
/*     */       Exception exception;
/*     */       try {
/* 158 */         BasicIO$.MODULE$.transferFully(src, dst);
/* 160 */         BasicIO$.MODULE$.close(
/* 161 */             this.isSink ? dst : src);
/*     */         return;
/*     */       } finally {}
/* 161 */       if (this.isSink);
/* 161 */       BasicIO$.MODULE$.close(src);
/*     */       throw exception;
/*     */     }
/*     */     
/*     */     public class ProcessImpl$PipeThread$$anonfun$10 extends AbstractFunction1<IOException, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(IOException e) {
/*     */         this.$outer.scala$sys$process$ProcessImpl$PipeThread$$ioHandler(e);
/*     */       }
/*     */       
/*     */       public ProcessImpl$PipeThread$$anonfun$10(ProcessImpl.PipeThread $outer) {}
/*     */     }
/*     */     
/*     */     public void scala$sys$process$ProcessImpl$PipeThread$$ioHandler(IOException e) {
/* 165 */       scala.Predef$.MODULE$.println((new StringBuilder()).append("I/O error ").append(e.getMessage()).append(" for process: ").append(this.labelFn.apply()).toString());
/* 166 */       e.printStackTrace();
/*     */     }
/*     */     
/*     */     public abstract void run();
/*     */   }
/*     */   
/*     */   public class PipeSource extends PipeThread {
/*     */     private final SyncVar<Option<InputStream>> currentSource;
/*     */     
/*     */     private final PipedOutputStream pipe;
/*     */     
/*     */     public PipeSource(Process$ $outer, SyncVar<Option<InputStream>> currentSource, PipedOutputStream pipe, Function0<String> label) {
/* 170 */       super($outer, 
/*     */           
/* 174 */           false, label);
/*     */     }
/*     */     
/*     */     public final void run() {
/*     */       while (true) {
/* 176 */         Option option = (Option)this.currentSource.get();
/* 177 */         if (option instanceof Some) {
/* 177 */           Some some = (Some)option;
/*     */           try {
/* 178 */             runloop((InputStream)some.x(), this.pipe);
/*     */           } finally {
/* 179 */             this.currentSource.unset();
/*     */           } 
/*     */           continue;
/*     */         } 
/* 182 */         if (scala.None$.MODULE$ == null) {
/* 182 */           if (option != null)
/*     */             throw new MatchError(option); 
/* 182 */         } else if (!scala.None$.MODULE$.equals(option)) {
/*     */           throw new MatchError(option);
/*     */         } 
/* 183 */         this.currentSource.unset();
/* 184 */         BasicIO$.MODULE$.close(this.pipe);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class PipeSink extends PipeThread {
/*     */     private final PipedInputStream pipe;
/*     */     
/*     */     private final SyncVar<Option<OutputStream>> currentSink;
/*     */     
/*     */     public PipeSink(Process$ $outer, PipedInputStream pipe, SyncVar<Option<OutputStream>> currentSink, Function0<String> label) {
/* 187 */       super($outer, 
/*     */           
/* 191 */           true, label);
/*     */     }
/*     */     
/*     */     public final void run() {
/*     */       while (true) {
/* 193 */         Option option = (Option)this.currentSink.get();
/* 194 */         if (option instanceof Some) {
/* 194 */           Some some = (Some)option;
/*     */           try {
/* 195 */             runloop(this.pipe, (OutputStream)some.x());
/*     */           } finally {
/* 196 */             this.currentSink.unset();
/*     */           } 
/*     */           continue;
/*     */         } 
/* 199 */         if (scala.None$.MODULE$ == null) {
/* 199 */           if (option != null)
/*     */             throw new MatchError(option); 
/* 199 */         } else if (!scala.None$.MODULE$.equals(option)) {
/*     */           throw new MatchError(option);
/*     */         } 
/* 200 */         this.currentSink.unset();
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class DummyProcess implements Process {
/*     */     private final Function0<Object> exitCode;
/*     */     
/*     */     public DummyProcess(Process$ $outer, Function0 action) {
/* 207 */       this.exitCode = $outer.Future().apply(action);
/*     */     }
/*     */     
/*     */     public int exitValue() {
/* 208 */       return this.exitCode.apply$mcI$sp();
/*     */     }
/*     */     
/*     */     public void destroy() {}
/*     */   }
/*     */   
/*     */   public class SimpleProcess implements Process {
/*     */     private final java.lang.Process p;
/*     */     
/*     */     private final Thread inputThread;
/*     */     
/*     */     private final List<Thread> outputThreads;
/*     */     
/*     */     public SimpleProcess(Process$ $outer, java.lang.Process p, Thread inputThread, List<Thread> outputThreads) {}
/*     */     
/*     */     public int exitValue() {
/*     */       try {
/* 219 */         this.inputThread.interrupt();
/*     */         this.p.waitFor();
/* 220 */         List<Thread> these1 = this.outputThreads;
/*     */         while (true) {
/* 220 */           if (these1.isEmpty())
/* 222 */             return this.p.exitValue(); 
/*     */           ((Thread)these1.head()).join();
/*     */           these1 = (List<Thread>)these1.tail();
/*     */         } 
/*     */       } finally {
/*     */         this.inputThread.interrupt();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void destroy() {
/*     */       try {
/* 226 */         List<Thread> list1 = this.outputThreads, these1 = list1;
/*     */         while (true) {
/* 226 */           if (these1.isEmpty()) {
/* 227 */             this.p.destroy();
/*     */             return;
/*     */           } 
/*     */           Object object = these1.head();
/*     */           Thread thread = (Thread)object;
/*     */           thread.stop();
/*     */           these1 = (List<Thread>)these1.tail();
/*     */         } 
/*     */       } finally {
/* 229 */         this.inputThread.interrupt();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class ThreadProcess implements Process {
/*     */     private final Thread thread;
/*     */     
/*     */     private final SyncVar<Object> success;
/*     */     
/*     */     public ThreadProcess(Process$ $outer, Thread thread, SyncVar<Object> success) {}
/*     */     
/*     */     public int exitValue() {
/* 234 */       this.thread.join();
/* 235 */       return BoxesRunTime.unboxToBoolean(this.success.get()) ? 0 : 1;
/*     */     }
/*     */     
/*     */     public void destroy() {
/* 237 */       this.thread.interrupt();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */