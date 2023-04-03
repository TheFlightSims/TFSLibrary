/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055eaB\001\003!\003\r\t!\003\002\026\rV$XO]3UQJ,\027\r\032)p_2$\026m]6t\025\t\031A!\001\005qCJ\fG\016\\3m\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001'\r\001!B\004\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005\025!\026m]6t\021\025\031\002\001\"\001\025\003\031!\023N\\5uIQ\tQ\003\005\002\f-%\021qC\002\002\005+:LGOB\004\032\001A\005\031\021\001\016\003\027]\023\030\r\0359fIR\0137o[\013\0047124\003\002\r\035I\035\002\"!\b\022\016\003yQ!a\b\021\002\t1\fgn\032\006\002C\005!!.\031<b\023\t\031cD\001\004PE*,7\r\036\t\003;\025J!A\n\020\003\021I+hN\\1cY\026\004B\001K\025+k5\t\001!\003\002\032!A\0211\006\f\007\001\t\025i\003D1\001/\005\005\021\026CA\0303!\tY\001'\003\0022\r\t9aj\034;iS:<\007CA\0064\023\t!dAA\002B]f\004\"a\013\034\005\r]BBQ1\001/\005\t!\006\017C\003\0241\021\005A\003C\004;1\001\007I\021A\036\002\r\031,H/\036:f+\005a\004GA\037G!\rq4)R\007\002)\021\001)Q\001\013G>t7-\036:sK:$(B\001\"!\003\021)H/\0337\n\005\021{$A\002$viV\024X\r\005\002,\r\022Iq\tSA\001\002\003\025\tA\f\002\004?\022\032\004BB%\031A\003&!*A\004gkR,(/\032\0211\005-k\005c\001 D\031B\0211&\024\003\n\017\"\013\t\021!A\003\0029B#\001S(\021\005-\001\026BA)\007\005!1x\016\\1uS2,\007bB*\031\001\004%\t\001V\001\013MV$XO]3`I\025\fHCA\013V\021\0351&+!AA\002]\0131\001\037\0232a\tA&\fE\002?\007f\003\"a\013.\005\023\035C\025\021!A\001\006\003q\003\"\002/\031\t\003!\022!B:uCJ$\b\"\0020\031\t\003!\022\001B:z]\016DQ\001\031\r\005\002\005\f\021\002\036:z\007\006t7-\0327\025\003\t\004\"aC2\n\005\0214!a\002\"p_2,\027M\034\005\006Mb!\t\001F\001\004eVt\007\"\0025\001\r#I\027A\0048fo^\023\030\r\0359fIR\0137o[\013\004U6|GCA6q!\021A\003\004\0348\021\005-jG!B\027h\005\004q\003CA\026p\t\0259tM1\001/\021\025\tx\r1\001s\003\005\021\007\003B\btY:L!\001\036\002\003\tQ\0137o\033\005\bm\002\021\r\021\"\001x\003-)gN^5s_:lWM\034;\026\003)Aa!\037\001!\002\023Q\021\001D3om&\024xN\\7f]R\004\003\"B>\001\t\003a\030\001C3yK\016,Ho\034:\026\003u\004\"A\020@\n\005}|$A\005+ie\026\fG\rU8pY\026CXmY;u_JDq!a\001\001\t\003\t)!A\004fq\026\034W\017^3\026\r\005\035\021\021CA\016)\021\tI!a\005\021\013-\tY!a\004\n\007\0055aAA\005Gk:\034G/[8oaA\0311&!\005\005\r5\n\tA1\001/\021!\t)\"!\001A\002\005]\021\001\002;bg.\004baD:\002\020\005e\001cA\026\002\034\0211q'!\001C\0029Bq!a\b\001\t\003\t\t#\001\013fq\026\034W\017^3B]\022<\026-\033;SKN,H\016^\013\007\003G\t9#a\f\025\t\005\025\022\021\006\t\004W\005\035BAB\027\002\036\t\007a\006\003\005\002\026\005u\001\031AA\026!\031y1/!\n\002.A\0311&a\f\005\r]\niB1\001/\021\035\t\031\004\001C\001\003k\t\001\003]1sC2dW\r\\5t[2+g/\0327\026\005\005]\002cA\006\002:%\031\0211\b\004\003\007%sG\017K\004\001\003\t)%!\023\021\007-\t\t%C\002\002D\031\021!\002Z3qe\026\034\027\r^3eC\t\t9%\001\021UQ&\034\b%[7qY\026lWM\034;bi&|g\016I5tA9|G\017I;tK\022t\023EAA&\003\031\021d&\r\031/a\0359\021q\n\002\t\002\005E\023!\006$viV\024X\r\0265sK\006$\007k\\8m)\006\0348n\035\t\004\037\005McAB\001\003\021\003\t)fE\002\002T)A\001\"!\027\002T\021\005\0211L\001\007y%t\027\016\036 \025\005\005E\003BCA0\003'\022\r\021\"\001\0026\005Aa.^7D_J,7\017C\005\002d\005M\003\025!\003\0028\005Ia.^7D_J,7\017\t\005\013\003O\n\031F1A\005\002\005%\024A\002;d_VtG/\006\002\002lA!\021QNA:\033\t\tyGC\002\002r}\na!\031;p[&\034\027\002BA;\003_\022!\"\021;p[&\034Gj\0348h\021%\tI(a\025!\002\023\tY'A\004uG>,h\016\036\021\t\025\005u\0241\013b\001\n\003\ty(A\teK\032\fW\017\034;UQJ,\027\r\032)p_2,\"!!!\021\007y\n\031)C\002\002\006~\022q\"\022=fGV$xN]*feZL7-\032\005\n\003\023\013\031\006)A\005\003\003\013!\003Z3gCVdG\017\0265sK\006$\007k\\8mA!B\0211KA \003\013\nI\005")
/*     */ public interface FutureThreadPoolTasks extends Tasks {
/*     */   void scala$collection$parallel$FutureThreadPoolTasks$_setter_$environment_$eq(Object paramObject);
/*     */   
/*     */   <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> paramTask);
/*     */   
/*     */   Object environment();
/*     */   
/*     */   ThreadPoolExecutor executor();
/*     */   
/*     */   <R, Tp> Function0<R> execute(Task<R, Tp> paramTask);
/*     */   
/*     */   <R, Tp> R executeAndWaitResult(Task<R, Tp> paramTask);
/*     */   
/*     */   int parallelismLevel();
/*     */   
/*     */   public interface WrappedTask<R, Tp> extends Runnable, Tasks.WrappedTask<R, Tp> {
/*     */     Future<?> future();
/*     */     
/*     */     @TraitSetter
/*     */     void future_$eq(Future<?> param1Future);
/*     */     
/*     */     void start();
/*     */     
/*     */     void sync();
/*     */     
/*     */     boolean tryCancel();
/*     */     
/*     */     void run();
/*     */   }
/*     */   
/*     */   public abstract class WrappedTask$class {
/*     */     public static void $init$(FutureThreadPoolTasks.WrappedTask $this) {
/* 367 */       $this.future_$eq((Future<?>)null);
/*     */     }
/*     */     
/*     */     public static void start(FutureThreadPoolTasks.WrappedTask $this) {
/* 370 */       synchronized ($this.scala$collection$parallel$FutureThreadPoolTasks$WrappedTask$$$outer().executor()) {
/* 371 */         $this.future_$eq($this.scala$collection$parallel$FutureThreadPoolTasks$WrappedTask$$$outer().executor().submit($this));
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void sync(FutureThreadPoolTasks.WrappedTask $this) {
/* 374 */       $this.future().get();
/*     */     }
/*     */     
/*     */     public static boolean tryCancel(FutureThreadPoolTasks.WrappedTask $this) {
/* 375 */       return false;
/*     */     }
/*     */     
/*     */     public static void run(FutureThreadPoolTasks.WrappedTask $this) {
/* 377 */       $this.compute();
/*     */     }
/*     */   }
/*     */   
/*     */   public class FutureThreadPoolTasks$$anonfun$execute$2 extends AbstractFunction0<R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final FutureThreadPoolTasks.WrappedTask t$2;
/*     */     
/*     */     public FutureThreadPoolTasks$$anonfun$execute$2(FutureThreadPoolTasks $outer, FutureThreadPoolTasks.WrappedTask t$2) {}
/*     */     
/*     */     public final R apply() {
/* 393 */       this.t$2.sync();
/* 394 */       this.t$2.body().forwardThrowable();
/* 395 */       return (R)this.t$2.body().result();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\FutureThreadPoolTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */