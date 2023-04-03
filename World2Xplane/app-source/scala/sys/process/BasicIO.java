/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.Closeable;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Either;
/*     */ import scala.util.Left;
/*     */ import scala.util.Right;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\005r!B\001\003\021\003I\021a\002\"bg&\034\027j\024\006\003\007\021\tq\001\035:pG\026\0348O\003\002\006\r\005\0311/_:\013\003\035\tQa]2bY\006\034\001\001\005\002\013\0275\t!AB\003\r\005!\005QBA\004CCNL7-S(\024\005-q\001CA\b\021\033\0051\021BA\t\007\005\031\te.\037*fM\")1c\003C\001)\0051A(\0338jiz\"\022!\003\005\b--\021\r\021\"\002\030\003)\021UO\0324feNK'0Z\013\0021=\t\021$\b\002!\001!11d\003Q\001\016a\t1BQ;gM\026\0248+\033>fA!9Qd\003b\001\n\013q\022a\002(fo2Lg.Z\013\002?A\021\001e\t\b\003\037\005J!A\t\004\002\rA\023X\rZ3g\023\t!SE\001\004TiJLgn\032\006\003E\031AaaJ\006!\002\033y\022\001\003(fo2Lg.\032\021\007\013%Z!A\001\026\003\021M#(/Z1nK\022,\"aK\032\024\005!r\001\002C\002)\005\013\007I\021A\027\026\0039\002BaD\0302y%\021\001G\002\002\n\rVt7\r^5p]F\002\"AM\032\r\001\021)A\007\013b\001k\t\tA+\005\0027sA\021qbN\005\003q\031\021qAT8uQ&tw\r\005\002\020u%\0211H\002\002\004\003:L\bCA\b>\023\tqdA\001\003V]&$\b\002\003!)\005\003\005\013\021\002\030\002\021A\024xnY3tg\002B\001B\021\025\003\006\004%\taQ\001\005I>tW-F\001E!\021yq&\022\037\021\005=1\025BA$\007\005\rIe\016\036\005\t\023\"\022\t\021)A\005\t\006)Am\0348fA!A1\n\013BC\002\023\005A*\001\004tiJ,\027-\\\013\002\033B\031qB\024)\n\005=3!!\003$v]\016$\030n\03481!\r\tf+M\007\002%*\0211\013V\001\nS6lW\017^1cY\026T!!\026\004\002\025\r|G\016\\3di&|g.\003\002X%\n11\013\036:fC6D\001\"\027\025\003\002\003\006I!T\001\bgR\024X-Y7!\021\025\031\002\006\"\001\\)\021afl\0301\021\007uC\023'D\001\f\021\025\031!\f1\001/\021\025\021%\f1\001E\021\025Y%\f1\001N\017\031\0217\002#\001\003G\006A1\013\036:fC6,G\r\005\002^I\0321\021f\003E\001\005\025\034\"\001\032\b\t\013M!G\021A4\025\003\rDQ!\0333\005\002)\fQ!\0319qYf,\"a\0338\025\0051|\007cA/)[B\021!G\034\003\006i!\024\r!\016\005\006a\"\004\r!]\001\021]>t'0\032:p\013b\034W\r\035;j_:\004\"a\004:\n\005M4!a\002\"p_2,\027M\034\004\tk.\001\n1!\001\003m\nYQK\\2m_N,\027M\0317f'\r!xo \t\003qvl\021!\037\006\003un\fA\001\\1oO*\tA0\001\003kCZ\f\027B\001@z\005\031y%M[3diB!\021\021AA\004\035\rQ\0211A\005\004\003\013\021\021a\0049s_\016,7o]%oi\026\024h.\0317\n\t\005%\0211\002\002\n\0072|7/Z1cY\026T1!!\002\003\021\035\ty\001\036C\001\003#\ta\001J5oSR$C#\001\037\t\017\005UA\017\"\022\002\022\005)1\r\\8tK\036A\021\021D\006\t\002\t\tY\"A\006V]\016dwn]3bE2,\007cA/\002\036\0319Qo\003E\001\005\005}1cAA\017\035!91#!\b\005\002\005\rBCAA\016\021\035I\027Q\004C\001\003O!B!!\013\0020A!\021\021AA\026\023\021\ti#a\003\003\027%s\007/\036;TiJ,\027-\034\005\t\003c\t)\0031\001\002*\005\021\021N\034\005\bS\006uA\021AA\033)\021\t9$!\020\021\t\005\005\021\021H\005\005\003w\tYA\001\007PkR\004X\017^*ue\026\fW\016\003\005\002@\005M\002\031AA\034\003\ryW\017\036\005\t\003\007\ni\002\"\001\002F\0059\001O]8uK\016$H\003BA\025\003\017B\001\"!\r\002B\001\007\021\021\006\005\t\003\007\ni\002\"\001\002LQ!\021qGA'\021!\ty$!\023A\002\005]\002BB5\f\t\003\t\t\006\006\005\002T\005e\023QLA2!\rQ\021QK\005\004\003/\022!!\003)s_\016,7o]%P\021\035\tY&a\024A\002E\faa^5uQ&s\007\002CA0\003\037\002\r!!\031\002\r=,H\017];u!\021yqf\b\037\t\021\005\025\024q\na\001\003O\n1\001\\8h!\025y\021\021NA7\023\r\tYG\002\002\007\037B$\030n\0348\021\007)\ty'C\002\002r\t\021Q\002\025:pG\026\0348\017T8hO\026\024\bBB5\f\t\003\t)\b\006\005\002T\005]\024\021PAB\021\035\tY&a\035A\002ED\001\"a\037\002t\001\007\021QP\001\007EV4g-\032:\021\007a\fy(C\002\002\002f\024Ab\025;sS:<')\0364gKJD\001\"!\032\002t\001\007\021q\r\005\007S.!\t!a\"\025\r\005M\023\021RAF\021\035\tY&!\"A\002ED\001\"!\032\002\006\002\007\021Q\016\005\b\003\037[A\021AAI\003\0319W\r^#seR!\0211SAK!\025yq&!\013=\021!\t)'!$A\002\005\035\004bBAM\027\021%\0211T\001\020aJ|7-Z:t\013J\024h)\0367msR!\0211SAO\021!\t)'a&A\002\0055\004bBAQ\027\021%\0211U\001\020aJ|7-Z:t\037V$h)\0367msR!\0211SAS\021!\t)'a(A\002\0055\004bBA\013\027\021\005\021\021\026\013\004y\005-\006bBAW\003O\003\ra`\001\002G\"9\021\021W\006\005\002\005M\026\001\0049s_\016,7o\035$vY2LH\003BAJ\003kC\001\"a\037\0020\002\007\021q\027\t\004q\006e\026bAA^s\nQ\021\t\0359f]\022\f'\r\\3\t\017\005E6\002\"\001\002@R!\0211SAa\021!\t\031-!0A\002\005\005\024a\0039s_\016,7o\035'j]\026Dq!a2\f\t\003\tI-A\tqe>\034Wm]:MS:,7OR;mYf$B!a3\002TR\031A(!4\t\021\005=\027Q\031a\001\003#\f\001B]3bI2Kg.\032\t\004\0379{\002\002CAb\003\013\004\r!!\031\t\017\005]7\002\"\001\002Z\006Y1m\0348oK\016$Hk\\%o)\ra\0241\034\005\t\003;\f)\0161\001\0028\005\tq\016C\004\002b.!\t!a9\002\013%t\007/\036;\025\t\005\025\030q\035\t\006\037=\n9\004\020\005\b\003S\fy\0161\001r\003\035\031wN\0348fGRDq!!<\f\t\003\ty/\001\005ti\006tG-\031:e)\021\t\031&!=\t\017\005M\0301\036a\001c\006a1m\0348oK\016$\030J\0349vi\"9\021Q^\006\005\002\005]H\003BA*\003sD\001\"!\r\002v\002\007\021Q\035\005\b\003{\\A\021AA\000\003!!xn\025;e\013J\024XCAAJ\021\035\021\031a\003C\001\003\f\001\002^8Ti\022|U\017\036\005\b\005\017YA\021\001B\005\0035!(/\0318tM\026\024h)\0367msR)AHa\003\003\016!A\021\021\007B\003\001\004\tI\003\003\005\002@\t\025\001\031AA\034\021!\021\tb\003Q\005\n\tM\021AC1qa\026tG\rT5oKR!\021\021\rB\013\021!\tYHa\004A\002\005]\006\002\003B\r\027\001&IAa\007\002#Q\024\030M\\:gKJ4U\017\0347z\0236\004H\016F\003=\005;\021y\002\003\005\0022\t]\001\031AA\025\021!\tyDa\006A\002\005]\002")
/*     */ public final class BasicIO {
/*     */   public static void transferFully(InputStream paramInputStream, OutputStream paramOutputStream) {
/*     */     BasicIO$.MODULE$.transferFully(paramInputStream, paramOutputStream);
/*     */   }
/*     */   
/*     */   public static Function1<InputStream, BoxedUnit> toStdOut() {
/*     */     return BasicIO$.MODULE$.toStdOut();
/*     */   }
/*     */   
/*     */   public static Function1<InputStream, BoxedUnit> toStdErr() {
/*     */     return BasicIO$.MODULE$.toStdErr();
/*     */   }
/*     */   
/*     */   public static ProcessIO standard(Function1<OutputStream, BoxedUnit> paramFunction1) {
/*     */     return BasicIO$.MODULE$.standard(paramFunction1);
/*     */   }
/*     */   
/*     */   public static ProcessIO standard(boolean paramBoolean) {
/*     */     return BasicIO$.MODULE$.standard(paramBoolean);
/*     */   }
/*     */   
/*     */   public static Function1<OutputStream, BoxedUnit> input(boolean paramBoolean) {
/*     */     return BasicIO$.MODULE$.input(paramBoolean);
/*     */   }
/*     */   
/*     */   public static void connectToIn(OutputStream paramOutputStream) {
/*     */     BasicIO$.MODULE$.connectToIn(paramOutputStream);
/*     */   }
/*     */   
/*     */   public static void processLinesFully(Function1<String, BoxedUnit> paramFunction1, Function0<String> paramFunction0) {
/*     */     BasicIO$.MODULE$.processLinesFully(paramFunction1, paramFunction0);
/*     */   }
/*     */   
/*     */   public static Function1<InputStream, BoxedUnit> processFully(Function1<String, BoxedUnit> paramFunction1) {
/*     */     return BasicIO$.MODULE$.processFully(paramFunction1);
/*     */   }
/*     */   
/*     */   public static Function1<InputStream, BoxedUnit> processFully(Appendable paramAppendable) {
/*     */     return BasicIO$.MODULE$.processFully(paramAppendable);
/*     */   }
/*     */   
/*     */   public static void close(Closeable paramCloseable) {
/*     */     BasicIO$.MODULE$.close(paramCloseable);
/*     */   }
/*     */   
/*     */   public static Function1<InputStream, BoxedUnit> getErr(Option<ProcessLogger> paramOption) {
/*     */     return BasicIO$.MODULE$.getErr(paramOption);
/*     */   }
/*     */   
/*     */   public static ProcessIO apply(boolean paramBoolean, ProcessLogger paramProcessLogger) {
/*     */     return BasicIO$.MODULE$.apply(paramBoolean, paramProcessLogger);
/*     */   }
/*     */   
/*     */   public static ProcessIO apply(boolean paramBoolean, StringBuffer paramStringBuffer, Option<ProcessLogger> paramOption) {
/*     */     return BasicIO$.MODULE$.apply(paramBoolean, paramStringBuffer, paramOption);
/*     */   }
/*     */   
/*     */   public static ProcessIO apply(boolean paramBoolean, Function1<String, BoxedUnit> paramFunction1, Option<ProcessLogger> paramOption) {
/*     */     return BasicIO$.MODULE$.apply(paramBoolean, paramFunction1, paramOption);
/*     */   }
/*     */   
/*     */   public static String Newline() {
/*     */     return BasicIO$.MODULE$.Newline();
/*     */   }
/*     */   
/*     */   public static int BufferSize() {
/*     */     return BasicIO$.MODULE$.BufferSize();
/*     */   }
/*     */   
/*     */   public static class Streamed<T> {
/*     */     private final Function1<T, BoxedUnit> process;
/*     */     
/*     */     private final Function1<Object, BoxedUnit> done;
/*     */     
/*     */     private final Function0<Stream<T>> stream;
/*     */     
/*     */     public Function1<T, BoxedUnit> process() {
/*  38 */       return this.process;
/*     */     }
/*     */     
/*     */     public Streamed(Function1<T, BoxedUnit> process, Function1<Object, BoxedUnit> done, Function0<Stream<T>> stream) {}
/*     */     
/*     */     public Function1<Object, BoxedUnit> done() {
/*  39 */       return this.done;
/*     */     }
/*     */     
/*     */     public Function0<Stream<T>> stream() {
/*  40 */       return this.stream;
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1 extends AbstractFunction0<Stream<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final boolean nonzeroException$1;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final Stream<T> apply() {
/*  49 */         return BasicIO.Streamed$.MODULE$.scala$sys$process$BasicIO$Streamed$$next$1(this.nonzeroException$1, this.q$1);
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1(boolean nonzeroException$1, LinkedBlockingQueue q$1) {}
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$apply$3 extends AbstractFunction0<Stream<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final boolean nonzeroException$1;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final Stream<T> apply() {
/*  51 */         return BasicIO.Streamed$.MODULE$.scala$sys$process$BasicIO$Streamed$$next$1(this.nonzeroException$1, this.q$1);
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$apply$3(boolean nonzeroException$1, LinkedBlockingQueue q$1) {}
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$apply$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final void apply(int code) {
/*  51 */         apply$mcVI$sp(code);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int code) {
/*  51 */         this.q$1.put(scala.package$.MODULE$.Left().apply(BoxesRunTime.boxToInteger(code)));
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$apply$1(LinkedBlockingQueue q$1) {}
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$apply$2 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final void apply(Object s) {
/*  51 */         this.q$1.put(scala.package$.MODULE$.Right().apply(s));
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$apply$2(LinkedBlockingQueue q$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Streamed$ {
/*     */     public static final Streamed$ MODULE$;
/*     */     
/*     */     public Streamed$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public <T> BasicIO.Streamed<T> apply(boolean nonzeroException) {
/*     */       LinkedBlockingQueue q = new LinkedBlockingQueue();
/*  51 */       return new BasicIO.Streamed<T>((Function1<T, BoxedUnit>)new BasicIO$Streamed$$anonfun$apply$2(q), (Function1<Object, BoxedUnit>)new BasicIO$Streamed$$anonfun$apply$1(q), (Function0<Stream<T>>)new BasicIO$Streamed$$anonfun$apply$3(nonzeroException, q));
/*     */     }
/*     */     
/*     */     public final Stream scala$sys$process$BasicIO$Streamed$$next$1(boolean nonzeroException$1, LinkedBlockingQueue<Either> q$1) {
/*     */       Stream.Cons cons;
/*     */       boolean bool = false;
/*     */       Left left = null;
/*     */       Either either = q$1.take();
/*     */       if (either instanceof Left) {
/*     */         bool = true;
/*     */         left = (Left)either;
/*     */         if (0 == BoxesRunTime.unboxToInt(left.a())) {
/*     */           scala.collection.immutable.Stream$ stream$ = scala.collection.immutable.Stream$.MODULE$;
/*     */           return (Stream)scala.collection.immutable.Stream$Empty$.MODULE$;
/*     */         } 
/*     */       } 
/*     */       if (bool) {
/*     */         if (nonzeroException$1) {
/*     */           String str = (new StringBuilder()).append("Nonzero exit code: ").append(left.a()).toString();
/*     */           scala.sys.package$ package$ = scala.sys.package$.MODULE$;
/*     */           throw new RuntimeException(str);
/*     */         } 
/*     */         scala.collection.immutable.Stream$ stream$ = scala.collection.immutable.Stream$.MODULE$;
/*     */         scala.collection.immutable.Stream$Empty$ stream$Empty$ = scala.collection.immutable.Stream$Empty$.MODULE$;
/*     */       } else {
/*     */         if (either instanceof Right) {
/*     */           Right right = (Right)either;
/*     */           BasicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1 basicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1 = new BasicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1(nonzeroException$1, q$1);
/*     */           Object object = right.b();
/*     */           scala.collection.immutable.Stream$cons$ stream$cons$ = scala.collection.immutable.Stream$cons$.MODULE$;
/*     */           cons = new Stream.Cons(object, (Function0)basicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1);
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return (Stream)cons;
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1 extends AbstractFunction0<Stream<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final boolean nonzeroException$1;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final Stream<T> apply() {
/*     */         return BasicIO.Streamed$.MODULE$.scala$sys$process$BasicIO$Streamed$$next$1(this.nonzeroException$1, this.q$1);
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$scala$sys$process$BasicIO$Streamed$$next$1$1(boolean nonzeroException$1, LinkedBlockingQueue q$1) {}
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$apply$3 extends AbstractFunction0<Stream<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final boolean nonzeroException$1;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final Stream<T> apply() {
/*  51 */         return BasicIO.Streamed$.MODULE$.scala$sys$process$BasicIO$Streamed$$next$1(this.nonzeroException$1, this.q$1);
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$apply$3(boolean nonzeroException$1, LinkedBlockingQueue q$1) {}
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$apply$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final void apply(int code) {
/*  51 */         apply$mcVI$sp(code);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int code) {
/*  51 */         this.q$1.put(scala.package$.MODULE$.Left().apply(BoxesRunTime.boxToInteger(code)));
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$apply$1(LinkedBlockingQueue q$1) {}
/*     */     }
/*     */     
/*     */     public static class BasicIO$Streamed$$anonfun$apply$2 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final LinkedBlockingQueue q$1;
/*     */       
/*     */       public final void apply(Object s) {
/*  51 */         this.q$1.put(scala.package$.MODULE$.Right().apply(s));
/*     */       }
/*     */       
/*     */       public BasicIO$Streamed$$anonfun$apply$2(LinkedBlockingQueue q$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class Uncloseable$class {
/*     */     public static void $init$(BasicIO.Uncloseable $this) {}
/*     */     
/*     */     public static final void close(BasicIO.Uncloseable $this) {}
/*     */   }
/*     */   
/*     */   public static interface Uncloseable extends Closeable {
/*     */     void close();
/*     */     
/*     */     public static class BasicIO$Uncloseable$$anon$2 extends FilterInputStream implements Uncloseable {
/*     */       public final void close() {
/*  59 */         BasicIO.Uncloseable$class.close(this);
/*     */       }
/*     */       
/*     */       public BasicIO$Uncloseable$$anon$2(InputStream in$1) {
/*  59 */         super(in$1);
/*  59 */         BasicIO.Uncloseable$class.$init$(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public static class BasicIO$Uncloseable$$anon$1 extends FilterOutputStream implements Uncloseable {
/*     */       public final void close() {
/*  60 */         BasicIO.Uncloseable$class.close(this);
/*     */       }
/*     */       
/*     */       public BasicIO$Uncloseable$$anon$1(OutputStream out$1) {
/*  60 */         super(out$1);
/*  60 */         BasicIO.Uncloseable$class.$init$(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Uncloseable$ {
/*     */     public static final Uncloseable$ MODULE$;
/*     */     
/*     */     public Uncloseable$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public InputStream apply(InputStream in) {
/*     */       return new BasicIO$Uncloseable$$anon$2(in);
/*     */     }
/*     */     
/*     */     public static class BasicIO$Uncloseable$$anon$2 extends FilterInputStream implements BasicIO.Uncloseable {
/*     */       public final void close() {
/*     */         BasicIO.Uncloseable$class.close(this);
/*     */       }
/*     */       
/*     */       public BasicIO$Uncloseable$$anon$2(InputStream in$1) {
/*     */         super(in$1);
/*     */         BasicIO.Uncloseable$class.$init$(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public OutputStream apply(OutputStream out) {
/*  60 */       return new BasicIO$Uncloseable$$anon$1(out);
/*     */     }
/*     */     
/*     */     public static class BasicIO$Uncloseable$$anon$1 extends FilterOutputStream implements BasicIO.Uncloseable {
/*     */       public final void close() {
/*  60 */         BasicIO.Uncloseable$class.close(this);
/*     */       }
/*     */       
/*     */       public BasicIO$Uncloseable$$anon$1(OutputStream out$1) {
/*  60 */         super(out$1);
/*  60 */         BasicIO.Uncloseable$class.$init$(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public InputStream protect(InputStream in) {
/*  61 */       return (in == package$.MODULE$.stdin()) ? apply(in) : in;
/*     */     }
/*     */     
/*     */     public OutputStream protect(OutputStream out) {
/*  62 */       return (out == package$.MODULE$.stdout() || out == package$.MODULE$.stderr()) ? apply(out) : out;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$processErrFully$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ProcessLogger log$1;
/*     */     
/*     */     public final void apply(String x$1) {
/* 134 */       this.log$1.err((Function0<String>)new BasicIO$$anonfun$processErrFully$1$$anonfun$apply$4(this, x$1));
/*     */     }
/*     */     
/*     */     public BasicIO$$anonfun$processErrFully$1(ProcessLogger log$1) {}
/*     */     
/*     */     public class BasicIO$$anonfun$processErrFully$1$$anonfun$apply$4 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String x$1$1;
/*     */       
/*     */       public final String apply() {
/* 134 */         return this.x$1$1;
/*     */       }
/*     */       
/*     */       public BasicIO$$anonfun$processErrFully$1$$anonfun$apply$4(BasicIO$$anonfun$processErrFully$1 $outer, String x$1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$processOutFully$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ProcessLogger log$2;
/*     */     
/*     */     public final void apply(String x$2) {
/* 135 */       this.log$2.out((Function0<String>)new BasicIO$$anonfun$processOutFully$1$$anonfun$apply$5(this, x$2));
/*     */     }
/*     */     
/*     */     public BasicIO$$anonfun$processOutFully$1(ProcessLogger log$2) {}
/*     */     
/*     */     public class BasicIO$$anonfun$processOutFully$1$$anonfun$apply$5 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String x$2$1;
/*     */       
/*     */       public final String apply() {
/* 135 */         return this.x$2$1;
/*     */       }
/*     */       
/*     */       public BasicIO$$anonfun$processOutFully$1$$anonfun$apply$5(BasicIO$$anonfun$processOutFully$1 $outer, String x$2$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$processFully$1 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 processLine$1;
/*     */     
/*     */     public BasicIO$$anonfun$processFully$1(Function1 processLine$1) {}
/*     */     
/*     */     public final void apply(InputStream in) {
/* 163 */       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/* 164 */       BasicIO$.MODULE$.processLinesFully(this.processLine$1, (Function0<String>)new BasicIO$$anonfun$processFully$1$$anonfun$apply$6(this, reader));
/* 165 */       reader.close();
/*     */     }
/*     */     
/*     */     public class BasicIO$$anonfun$processFully$1$$anonfun$apply$6 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final BufferedReader reader$1;
/*     */       
/*     */       public final String apply() {
/*     */         return this.reader$1.readLine();
/*     */       }
/*     */       
/*     */       public BasicIO$$anonfun$processFully$1$$anonfun$apply$6(BasicIO$$anonfun$processFully$1 $outer, BufferedReader reader$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$input$1 extends AbstractFunction1<OutputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final boolean connect$1;
/*     */     
/*     */     public BasicIO$$anonfun$input$1(boolean connect$1) {}
/*     */     
/*     */     public final void apply(OutputStream outputToProcess) {
/* 190 */       if (this.connect$1)
/* 190 */         BasicIO$.MODULE$.connectToIn(outputToProcess); 
/* 191 */       outputToProcess.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$toStdErr$1 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(InputStream in) {
/* 203 */       BasicIO$.MODULE$.transferFully(in, package$.MODULE$.stderr());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$toStdOut$1 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(InputStream in) {
/* 208 */       BasicIO$.MODULE$.transferFully(in, package$.MODULE$.stdout());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {}
/*     */     
/*     */     public void apply$mcV$sp() {}
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$appendLine$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Appendable buffer$1;
/*     */     
/*     */     public BasicIO$$anonfun$appendLine$1(Appendable buffer$1) {}
/*     */     
/*     */     public final void apply(String line) {
/* 218 */       this.buffer$1.append(line);
/* 219 */       this.buffer$1.append(BasicIO$.MODULE$.Newline());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\BasicIO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */