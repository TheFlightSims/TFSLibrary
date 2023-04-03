/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.concurrent.forkjoin.ForkJoinTask;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035aaB\001\003!\003\r\t!\003\002\016\r>\0248NS8j]R\0137o[:\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\024\t\001QaB\005\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005\025!\026m]6t!\ty1#\003\002\025\005\t\021\002*\031<j]\0364uN]6K_&t\007k\\8m\021\0251\002\001\"\001\030\003\031!\023N\\5uIQ\t\001\004\005\002\f3%\021!D\002\002\005+:LGOB\004\035\001A\005\031\021A\017\003\027]\023\030\r\0359fIR\0137o[\013\004=124cA\016 OA\021\001%J\007\002C)\021!eI\001\tM>\0248N[8j]*\021AEB\001\013G>t7-\036:sK:$\030B\001\024\"\005=\021VmY;sg&4X-Q2uS>t\007\003\002\025*UUj\021\001A\005\0039A\001\"a\013\027\r\001\021)Qf\007b\001]\t\t!+\005\0020eA\0211\002M\005\003c\031\021qAT8uQ&tw\r\005\002\fg%\021AG\002\002\004\003:L\bCA\0267\t\03194\004\"b\001]\t\021A\013\035\005\006-m!\ta\006\005\006um!\taF\001\006gR\f'\017\036\005\006ym!\taF\001\005gft7\rC\003?7\021\005q(A\005uef\034\025M\\2fYR\t\001\t\005\002\f\003&\021!I\002\002\b\005>|G.Z1o\021\025!\005A\"\005F\0039qWm^,sCB\004X\r\032+bg.,2AR%L)\t9E\n\005\003)7!S\005CA\026J\t\025i3I1\001/!\tY3\nB\0038\007\n\007a\006C\003N\007\002\007a*A\001c!\021yq\n\023&\n\005A\023!\001\002+bg.DQA\025\001\005\002M\013ABZ8sW*{\027N\034)p_2,\022\001\026\t\003AUK!AV\021\003\031\031{'o\033&pS:\004vn\0347\t\017a\003!\031!D\001'\006YQM\034<je>tW.\0328u\021\025Q\006\001\"\001\\\003\035)\0070Z2vi\026,2\001X1g)\ti&\rE\002\f=\002L!a\030\004\003\023\031+hn\031;j_:\004\004CA\026b\t\025i\023L1\001/\021\025\031\027\f1\001e\003\021!\030m]6\021\t=y\005-\032\t\003W\031$QaN-C\0029BQ\001\033\001\005\002%\fA#\032=fGV$X-\0218e/\006LGOU3tk2$Xc\0016maR\0211.\034\t\003W1$Q!L4C\0029BQaY4A\0029\004BaD(l_B\0211\006\035\003\006o\035\024\rA\f\005\006e\002!\ta]\001\021a\006\024\030\r\0347fY&\034X\016T3wK2,\022\001\036\t\003\027UL!A\036\004\003\007%sGoB\003y\005!\005\0210A\007G_J\\'j\\5o)\006\0348n\035\t\003\037i4Q!\001\002\t\002m\034\"A\037\006\t\013uTH\021\001@\002\rqJg.\033;?)\005I\b\002CA\001u\n\007I\021A*\002'\021,g-Y;mi\032{'o\033&pS:\004vn\0347\t\017\005\025!\020)A\005)\006!B-\0324bk2$hi\034:l\025>Lg\016U8pY\002\002")
/*     */ public interface ForkJoinTasks extends Tasks, HavingForkJoinPool {
/*     */   <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> paramTask);
/*     */   
/*     */   ForkJoinPool forkJoinPool();
/*     */   
/*     */   ForkJoinPool environment();
/*     */   
/*     */   <R, Tp> Function0<R> execute(Task<R, Tp> paramTask);
/*     */   
/*     */   <R, Tp> R executeAndWaitResult(Task<R, Tp> paramTask);
/*     */   
/*     */   int parallelismLevel();
/*     */   
/*     */   public interface WrappedTask<R, Tp> extends Tasks.WrappedTask<R, Tp> {
/*     */     void start();
/*     */     
/*     */     void sync();
/*     */     
/*     */     boolean tryCancel();
/*     */   }
/*     */   
/*     */   public abstract class WrappedTask$class {
/*     */     public static void $init$(ForkJoinTasks.WrappedTask $this) {}
/*     */     
/*     */     public static void start(ForkJoinTasks.WrappedTask $this) {
/* 443 */       ((ForkJoinTask)$this).fork();
/*     */     }
/*     */     
/*     */     public static void sync(ForkJoinTasks.WrappedTask $this) {
/* 444 */       ((ForkJoinTask)$this).join();
/*     */     }
/*     */     
/*     */     public static boolean tryCancel(ForkJoinTasks.WrappedTask $this) {
/* 445 */       return ((ForkJoinTask)$this).tryUnfork();
/*     */     }
/*     */   }
/*     */   
/*     */   public class ForkJoinTasks$$anonfun$execute$3 extends AbstractFunction0<R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ForkJoinTasks.WrappedTask fjtask$1;
/*     */     
/*     */     public ForkJoinTasks$$anonfun$execute$3(ForkJoinTasks $outer, ForkJoinTasks.WrappedTask fjtask$1) {}
/*     */     
/*     */     public final R apply() {
/* 470 */       this.fjtask$1.sync();
/* 471 */       this.fjtask$1.body().forwardThrowable();
/* 472 */       return (R)this.fjtask$1.body().result();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ForkJoinTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */