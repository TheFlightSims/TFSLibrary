/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.japi.Function;
/*     */ import akka.japi.Function2;
/*     */ import akka.japi.Option;
/*     */ import akka.japi.Option$;
/*     */ import java.util.LinkedList;
/*     */ import java.util.concurrent.Callable;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005v!B\001\003\021\0039\021a\002$viV\024Xm\035\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021qAR;ukJ,7o\005\002\n\031A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032DQaE\005\005\002Q\ta\001P5oSRtD#A\004\t\013YIA\021A\f\002\r\031,H/\036:f+\tA\022\005F\002\032UU\0022AG\017 \033\005Y\"B\001\017\017\003)\031wN\\2veJ,g\016^\005\003=m\021aAR;ukJ,\007C\001\021\"\031\001!QAI\013C\002\r\022\021\001V\t\003I\035\002\"!D\023\n\005\031r!a\002(pi\"Lgn\032\t\003\033!J!!\013\b\003\007\005s\027\020C\003,+\001\007A&\001\003c_\022L\bcA\0274?5\taF\003\002\035_)\021\001'M\001\005kRLGNC\0013\003\021Q\027M^1\n\005Qr#\001C\"bY2\f'\r\\3\t\013Y*\002\031A\034\002\021\025DXmY;u_J\004\"A\007\035\n\005eZ\"\001E#yK\016,H/[8o\007>tG/\032=u\021\025Y\024\002\"\001=\003\035\001(o\\7jg\026,\"!\020\"\025\003y\0022AG B\023\t\0015DA\004Qe>l\027n]3\021\005\001\022E!\002\022;\005\004\031\003\"\002#\n\t\003)\025A\0024bS2,G-\006\002G\023R\021qI\023\t\0045uA\005C\001\021J\t\025\0213I1\001$\021\025Y5\t1\001M\003%)\007pY3qi&|g\016\005\002N+:\021aj\025\b\003\037Jk\021\001\025\006\003#\032\ta\001\020:p_Rt\024\"A\b\n\005Qs\021a\0029bG.\fw-Z\005\003-^\023\021\002\0265s_^\f'\r\\3\013\005Qs\001\"B-\n\t\003Q\026AC:vG\016,7o\0354vYV\0211L\030\013\0039~\0032AG\017^!\t\001c\fB\003#1\n\0071\005C\003a1\002\007Q,\001\004sKN,H\016\036\005\006E&!\taY\001\005M&tG-\006\002e[R)Qm\034=\002\002A\031!$\b4\021\007\035TG.D\001i\025\tIG!\001\003kCBL\027BA6i\005\031y\005\017^5p]B\021\001%\034\003\006E\005\024\rA\\\t\003I1AQ\001]1A\002E\fqAZ;ukJ,7\017E\002sk^l\021a\035\006\003iF\nA\001\\1oO&\021ao\035\002\t\023R,'/\0312mKB\031!$\b7\t\013e\f\007\031\001>\002\023A\024X\rZ5dCR,\007\003B4|YvL!\001 5\003\021\031+hn\031;j_:\004\"A\035@\n\005}\034(a\002\"p_2,\027M\034\005\006m\005\004\ra\016\005\b\003\013IA\021AA\004\003A1\027N]:u\007>l\007\017\\3uK\022|e-\006\003\002\n\005=ACBA\006\003#\t)\002\005\003\033;\0055\001c\001\021\002\020\0211!%a\001C\0029Dq\001]A\002\001\004\t\031\002\005\003sk\006-\001B\002\034\002\004\001\007q\007C\004\002\032%!\t!a\007\002\t\031|G\016Z\013\007\003;\t\031$a\t\025\025\005}\021qEA\026\003k\ty\004\005\003\033;\005\005\002c\001\021\002$\0219\021QEA\f\005\004q'!\001*\t\021\005%\022q\003a\001\003C\tAA_3s_\"9\001/a\006A\002\0055\002\003\002:v\003_\001BAG\017\0022A\031\001%a\r\005\r\t\n9B1\001o\021!\t9$a\006A\002\005e\022a\0014v]BIq-a\017\002\"\005E\022\021E\005\004\003{A'!\003$v]\016$\030n\03483\021\0311\024q\003a\001o!9\0211I\005\005\002\005\025\023A\002:fIV\034W-\006\004\002H\005M\023Q\n\013\t\003\023\n)&a\027\002`A!!$HA&!\r\001\023Q\n\003\t\003K\t\tE1\001\002PE\031\021\021K\024\021\007\001\n\031\006\002\004#\003\003\022\rA\034\005\ba\006\005\003\031AA,!\021\021X/!\027\021\tii\022\021\013\005\t\003o\t\t\0051\001\002^AIq-a\017\002L\005E\0231\n\005\007m\005\005\003\031A\034\t\017\005\r\024\002\"\001\002f\005A1/Z9vK:\034W-\006\003\002h\005=DCBA5\003g\nY\b\005\003\033;\005-\004\003\002:v\003[\0022\001IA8\t\035\t\t(!\031C\002\r\022\021!\021\005\t\003k\n\t\0071\001\002x\005\021\021N\034\t\005eV\fI\b\005\003\033;\0055\004B\002\034\002b\001\007q\007C\004\002\000%!\t!!!\002\021Q\024\030M^3sg\026,b!a!\002\026\006-E\003CAC\003\037\0139*a(\021\tii\022q\021\t\005eV\fI\tE\002!\003\027#q!!$\002~\t\0071EA\001C\021!\t)(! A\002\005E\005\003\002:v\003'\0032\001IAK\t\035\t\t(! C\002\rB\001\"!'\002~\001\007\0211T\001\003M:\004baZ>\002\024\006u\005\003\002\016\036\003\023CaANA?\001\0049\004")
/*     */ public final class Futures {
/*     */   public static <A, B> Future<Iterable<B>> traverse(Iterable<A> paramIterable, Function<A, Future<B>> paramFunction, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.traverse(paramIterable, paramFunction, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static <A> Future<Iterable<A>> sequence(Iterable<Future<A>> paramIterable, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.sequence(paramIterable, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static <T, R> Future<R> reduce(Iterable<Future<T>> paramIterable, Function2<R, T, R> paramFunction2, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.reduce(paramIterable, paramFunction2, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static <T, R> Future<R> fold(R paramR, Iterable<Future<T>> paramIterable, Function2<R, T, R> paramFunction2, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.fold(paramR, paramIterable, paramFunction2, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static <T> Future<T> firstCompletedOf(Iterable<Future<T>> paramIterable, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.firstCompletedOf(paramIterable, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static <T> Future<Option<T>> find(Iterable<Future<T>> paramIterable, Function<T, Boolean> paramFunction, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.find(paramIterable, paramFunction, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static <T> Future<T> successful(T paramT) {
/*     */     return Futures$.MODULE$.successful(paramT);
/*     */   }
/*     */   
/*     */   public static <T> Future<T> failed(Throwable paramThrowable) {
/*     */     return Futures$.MODULE$.failed(paramThrowable);
/*     */   }
/*     */   
/*     */   public static <T> Promise<T> promise() {
/*     */     return Futures$.MODULE$.promise();
/*     */   }
/*     */   
/*     */   public static <T> Future<T> future(Callable<T> paramCallable, ExecutionContext paramExecutionContext) {
/*     */     return Futures$.MODULE$.future(paramCallable, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$future$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable body$1;
/*     */     
/*     */     public final T apply() {
/*  94 */       return this.body$1.call();
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$future$1(Callable body$1) {}
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$find$1 extends AbstractFunction1<T, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function predicate$1;
/*     */     
/*     */     public final boolean apply(Object x$1) {
/* 118 */       return Predef$.MODULE$.Boolean2boolean((Boolean)this.predicate$1.apply(x$1));
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$find$1(Function predicate$1) {}
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$find$2 extends AbstractFunction1<Option<T>, Option<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Option<T> apply(Option scalaOption) {
/* 118 */       return Option$.MODULE$.fromScalaOption(scalaOption);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$fold$1 extends AbstractFunction2<R, T, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 fun$1;
/*     */     
/*     */     public final R apply(Object arg1, Object arg2) {
/* 134 */       return (R)this.fun$1.apply(arg1, arg2);
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$fold$1(Function2 fun$1) {}
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$reduce$1 extends AbstractFunction2<R, T, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 fun$2;
/*     */     
/*     */     public final R apply(Object arg1, Object arg2) {
/* 140 */       return (R)this.fun$2.apply(arg1, arg2);
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$reduce$1(Function2 fun$2) {}
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$sequence$1 extends AbstractFunction0<LinkedList<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LinkedList<A> apply() {
/* 148 */       return new LinkedList<A>();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$sequence$2 extends AbstractFunction2<Future<LinkedList<A>>, Future<A>, Future<LinkedList<A>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ExecutionContext d$1;
/*     */     
/*     */     public final Future<LinkedList<A>> apply(Future fr, Future fa) {
/* 148 */       return fr.flatMap((Function1)new Futures$$anonfun$sequence$2$$anonfun$apply$1(this, fa), this.d$1);
/*     */     }
/*     */     
/*     */     public Futures$$anonfun$sequence$2(ExecutionContext d$1) {}
/*     */     
/*     */     public class Futures$$anonfun$sequence$2$$anonfun$apply$1 extends AbstractFunction1<LinkedList<A>, Future<LinkedList<A>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Future fa$1;
/*     */       
/*     */       public final Future<LinkedList<A>> apply(LinkedList r) {
/* 148 */         return this.fa$1.map((Function1)new Futures$$anonfun$sequence$2$$anonfun$apply$1$$anonfun$apply$2(this, r), this.$outer.d$1);
/*     */       }
/*     */       
/*     */       public Futures$$anonfun$sequence$2$$anonfun$apply$1(Futures$$anonfun$sequence$2 $outer, Future fa$1) {}
/*     */       
/*     */       public class Futures$$anonfun$sequence$2$$anonfun$apply$1$$anonfun$apply$2 extends AbstractFunction1<A, LinkedList<A>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final LinkedList r$1;
/*     */         
/*     */         public final LinkedList<A> apply(Object a) {
/* 148 */           this.r$1.add(a);
/* 148 */           return this.r$1;
/*     */         }
/*     */         
/*     */         public Futures$$anonfun$sequence$2$$anonfun$apply$1$$anonfun$apply$2(Futures$$anonfun$sequence$2$$anonfun$apply$1 $outer, LinkedList r$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$traverse$1 extends AbstractFunction0<LinkedList<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LinkedList<B> apply() {
/* 158 */       return new LinkedList<B>();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Futures$$anonfun$traverse$2 extends AbstractFunction2<Future<LinkedList<B>>, A, Future<LinkedList<B>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function fn$1;
/*     */     
/*     */     public final ExecutionContext d$2;
/*     */     
/*     */     public Futures$$anonfun$traverse$2(Function fn$1, ExecutionContext d$2) {}
/*     */     
/*     */     public final Future<LinkedList<B>> apply(Future fr, Object a) {
/* 159 */       Future fb = (Future)this.fn$1.apply(a);
/* 160 */       return fr.flatMap((Function1)new Futures$$anonfun$traverse$2$$anonfun$apply$3(this, fb), this.d$2);
/*     */     }
/*     */     
/*     */     public class Futures$$anonfun$traverse$2$$anonfun$apply$3 extends AbstractFunction1<LinkedList<B>, Future<LinkedList<B>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Future fb$1;
/*     */       
/*     */       public final Future<LinkedList<B>> apply(LinkedList r) {
/* 160 */         return this.fb$1.map((Function1)new Futures$$anonfun$traverse$2$$anonfun$apply$3$$anonfun$apply$4(this, r), this.$outer.d$2);
/*     */       }
/*     */       
/*     */       public Futures$$anonfun$traverse$2$$anonfun$apply$3(Futures$$anonfun$traverse$2 $outer, Future fb$1) {}
/*     */       
/*     */       public class Futures$$anonfun$traverse$2$$anonfun$apply$3$$anonfun$apply$4 extends AbstractFunction1<B, LinkedList<B>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final LinkedList r$2;
/*     */         
/*     */         public final LinkedList<B> apply(Object b) {
/* 160 */           this.r$2.add(b);
/* 160 */           return this.r$2;
/*     */         }
/*     */         
/*     */         public Futures$$anonfun$traverse$2$$anonfun$apply$3$$anonfun$apply$4(Futures$$anonfun$traverse$2$$anonfun$apply$3 $outer, LinkedList r$2) {}
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Futures.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */