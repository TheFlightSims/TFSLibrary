/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005UaaB\001\003!\003\r\ta\002\002\n'\016DW\rZ;mKJT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fM\")q\002\001C\001!\0051A%\0338ji\022\"\022!\005\t\003\023II!a\005\006\003\tUs\027\016\036\005\006+\001!)AF\001\tg\016DW\rZ;mKR)q#K\0314kQ\031\001\004\b\023\021\005eQR\"\001\002\n\005m\021!aC\"b]\016,G\016\\1cY\026DQ!\b\013A\004y\t\001\"\032=fGV$xN\035\t\003?\tj\021\001\t\006\003C)\t!bY8oGV\024(/\0328u\023\t\031\003E\001\tFq\026\034W\017^5p]\016{g\016^3yi\"9Q\005\006I\001\002\b1\023AB:f]\022,'\017\005\002\032O%\021\001F\001\002\t\003\016$xN\035*fM\")!\006\006a\001W\005a\021N\\5uS\006dG)\0327bsB\021AfL\007\002[)\021a\006I\001\tIV\024\030\r^5p]&\021\001'\f\002\017\r&t\027\016^3EkJ\fG/[8o\021\025\021D\0031\001,\003!Ig\016^3sm\006d\007\"\002\033\025\001\0041\023\001\003:fG\026Lg/\032:\t\013Y\"\002\031A\034\002\0175,7o]1hKB\021\021\002O\005\003s)\0211!\0218z\021\025)\002\001\"\002<)\raD)\022\013\003{}\"\"\001\007 \t\013uQ\0049\001\020\t\r\001SD\0211\001B\003\0051\007cA\005C#%\0211I\003\002\ty\tLh.Y7f}!)!F\017a\001W!)!G\017a\001W!)Q\003\001D\001\017R!\001JS&M)\tA\022\nC\003\036\r\002\017a\004C\003+\r\002\0071\006C\0033\r\002\0071\006C\003N\r\002\007a*\001\005sk:t\027M\0317f!\tyE+D\001Q\025\t\t&+\001\003mC:<'\"A*\002\t)\fg/Y\005\003+B\023\001BU;o]\006\024G.\032\005\006/\002!)\001W\001\rg\016DW\rZ;mK>s7-\032\013\0053rsv\fF\002\0315nCQ!\b,A\004yAq!\n,\021\002\003\017a\005C\003^-\002\0071&A\003eK2\f\027\020C\0035-\002\007a\005C\0037-\002\007q\007C\003X\001\021\025\021\r\006\002cMR\0211-\032\013\0031\021DQ!\b1A\004yAa\001\0211\005\002\004\t\005\"B/a\001\004Y\003\"B,\001\r\003AGcA5lYR\021\001D\033\005\006;\035\004\035A\b\005\006;\036\004\ra\013\005\006\033\036\004\rA\024\005\006]\0021\ta\\\001\r[\006DhI]3rk\026t7-_\013\002aB\021\021\"]\005\003e*\021a\001R8vE2,\007b\002;\001#\003%)!^\001\023g\016DW\rZ;mK\022\"WMZ1vYR$c\007F\005w\003\003\t\031!!\002\002\b)\022ae^\026\002qB\021\021P`\007\002u*\0211\020`\001\nk:\034\007.Z2lK\022T!! \006\002\025\005tgn\034;bi&|g.\003\002\000u\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\013)\032\b\031A\026\t\013I\032\b\031A\026\t\013Q\032\b\031\001\024\t\013Y\032\b\031A\034\t\023\005-\001!%A\005\006\0055\021AF:dQ\026$W\017\\3P]\016,G\005Z3gCVdG\017J\033\025\017Y\fy!!\005\002\024!1Q,!\003A\002-Ba\001NA\005\001\0041\003B\002\034\002\n\001\007q\007")
/*     */ public interface Scheduler {
/*     */   Cancellable schedule(FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2, ActorRef paramActorRef1, Object paramObject, ExecutionContext paramExecutionContext, ActorRef paramActorRef2);
/*     */   
/*     */   Cancellable schedule(FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2, Function0<BoxedUnit> paramFunction0, ExecutionContext paramExecutionContext);
/*     */   
/*     */   Cancellable schedule(FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2, Runnable paramRunnable, ExecutionContext paramExecutionContext);
/*     */   
/*     */   ActorRef schedule$default$6(FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2, ActorRef paramActorRef, Object paramObject);
/*     */   
/*     */   Cancellable scheduleOnce(FiniteDuration paramFiniteDuration, ActorRef paramActorRef1, Object paramObject, ExecutionContext paramExecutionContext, ActorRef paramActorRef2);
/*     */   
/*     */   Cancellable scheduleOnce(FiniteDuration paramFiniteDuration, Function0<BoxedUnit> paramFunction0, ExecutionContext paramExecutionContext);
/*     */   
/*     */   Cancellable scheduleOnce(FiniteDuration paramFiniteDuration, Runnable paramRunnable, ExecutionContext paramExecutionContext);
/*     */   
/*     */   ActorRef scheduleOnce$default$5(FiniteDuration paramFiniteDuration, ActorRef paramActorRef, Object paramObject);
/*     */   
/*     */   double maxFrequency();
/*     */   
/*     */   public class Scheduler$$anon$4 implements Runnable {
/*     */     private final ActorRef receiver$1;
/*     */     
/*     */     private final Object message$1;
/*     */     
/*     */     private final ActorRef sender$1;
/*     */     
/*     */     public Scheduler$$anon$4(Scheduler $outer, ActorRef receiver$1, Object message$1, ActorRef sender$1) {}
/*     */     
/*     */     public void run() {
/*  61 */       package$.MODULE$.actorRef2Scala(this.receiver$1).$bang(this.message$1, this.sender$1);
/*  62 */       if (this.receiver$1.isTerminated())
/*  63 */         throw new SchedulerException("timer active for terminated actor"); 
/*     */     }
/*     */   }
/*     */   
/*     */   public class Scheduler$$anon$5 implements Runnable {
/*     */     private final Function0 f$1;
/*     */     
/*     */     public void run() {
/*  79 */       this.f$1.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public Scheduler$$anon$5(Scheduler $outer, Function0 f$1) {}
/*     */   }
/*     */   
/*     */   public class Scheduler$$anon$6 implements Runnable {
/*     */     private final ActorRef receiver$2;
/*     */     
/*     */     private final Object message$2;
/*     */     
/*     */     private final ActorRef sender$2;
/*     */     
/*     */     public Scheduler$$anon$6(Scheduler $outer, ActorRef receiver$2, Object message$2, ActorRef sender$2) {}
/*     */     
/*     */     public void run() {
/* 106 */       package$.MODULE$.actorRef2Scala(this.receiver$2).$bang(this.message$2, this.sender$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public class Scheduler$$anon$7 implements Runnable {
/*     */     private final Function0 f$2;
/*     */     
/*     */     public void run() {
/* 117 */       this.f$2.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public Scheduler$$anon$7(Scheduler $outer, Function0 f$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Scheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */