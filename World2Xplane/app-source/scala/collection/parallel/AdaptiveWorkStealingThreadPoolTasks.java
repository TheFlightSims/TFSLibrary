/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t4q!\001\002\021\002\007\005\021BA\022BI\006\004H/\033<f/>\0248n\025;fC2Lgn\032+ie\026\fG\rU8pYR\0137o[:\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\024\t\001QaB\005\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005=!\006N]3bIB{w\016\034+bg.\034\bCA\b\024\023\t!\"AA\rBI\006\004H/\033<f/>\0248n\025;fC2Lgn\032+bg.\034\b\"\002\f\001\t\0039\022A\002\023j]&$H\005F\001\031!\tY\021$\003\002\033\r\t!QK\\5u\r\021a\002\001A\017\003\027]\023\030\r\0359fIR\0137o[\013\004=124\003B\016 Oa\002\"\001I\023\016\003\005R!AI\022\002\t1\fgn\032\006\002I\005!!.\031<b\023\t1\023E\001\004PE*,7\r\036\t\005Q%RS'D\001\001\023\ta\002\003\005\002,Y1\001A!B\027\034\005\004q#!\001*\022\005=\022\004CA\0061\023\t\tdAA\004O_RD\027N\\4\021\005-\031\024B\001\033\007\005\r\te.\037\t\003WY\"QaN\016C\0029\022!\001\0269\021\t!J$&N\005\0039MA\001bO\016\003\006\004%\t\001P\001\005E>$\0270F\001>!\021yaHK\033\n\005}\022!\001\002+bg.D\001\"Q\016\003\002\003\006I!P\001\006E>$\027\020\t\005\006\007n!\t\001R\001\007y%t\027\016\036 \025\005\0253\005\003\002\025\034UUBQa\017\"A\002uBQ\001S\016\005\002%\013Qa\0359mSR,\022A\023\t\004\027NCdB\001'R\035\ti\005+D\001O\025\ty\005\"\001\004=e>|GOP\005\002\017%\021!KB\001\ba\006\0347.Y4f\023\t!VKA\002TKFT!A\025\004\t\013]\003A\021\001-\002\0359,wo\026:baB,G\rV1tWV\031\021\f\0300\025\005i{\006\003\002\025\0347v\003\"a\013/\005\01352&\031\001\030\021\005-rF!B\034W\005\004q\003\"\0021W\001\004\t\027!\0012\021\t=q4,\030")
/*     */ public interface AdaptiveWorkStealingThreadPoolTasks extends ThreadPoolTasks, AdaptiveWorkStealingTasks {
/*     */   <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> paramTask);
/*     */   
/*     */   public class WrappedTask<R, Tp> implements ThreadPoolTasks.WrappedTask<R, Tp>, AdaptiveWorkStealingTasks.WrappedTask<R, Tp> {
/*     */     private final Task<R, Tp> body;
/*     */     
/*     */     private volatile AdaptiveWorkStealingTasks.WrappedTask<Object, Object> next;
/*     */     
/*     */     private volatile boolean shouldWaitFor;
/*     */     
/*     */     private volatile boolean owned;
/*     */     
/*     */     private volatile boolean completed;
/*     */     
/*     */     public AdaptiveWorkStealingTasks.WrappedTask<R, Tp> next() {
/* 526 */       return (AdaptiveWorkStealingTasks.WrappedTask)this.next;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void next_$eq(AdaptiveWorkStealingTasks.WrappedTask<Object, Object> x$1) {
/* 526 */       this.next = x$1;
/*     */     }
/*     */     
/*     */     public boolean shouldWaitFor() {
/* 526 */       return this.shouldWaitFor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void shouldWaitFor_$eq(boolean x$1) {
/* 526 */       this.shouldWaitFor = x$1;
/*     */     }
/*     */     
/*     */     public void compute() {
/* 526 */       AdaptiveWorkStealingTasks.WrappedTask$class.compute(this);
/*     */     }
/*     */     
/*     */     public void internal() {
/* 526 */       AdaptiveWorkStealingTasks.WrappedTask$class.internal(this);
/*     */     }
/*     */     
/*     */     public AdaptiveWorkStealingTasks.WrappedTask<R, Tp> spawnSubtasks() {
/* 526 */       return AdaptiveWorkStealingTasks.WrappedTask$class.spawnSubtasks(this);
/*     */     }
/*     */     
/*     */     public void printChain() {
/* 526 */       AdaptiveWorkStealingTasks.WrappedTask$class.printChain(this);
/*     */     }
/*     */     
/*     */     public boolean owned() {
/* 526 */       return this.owned;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void owned_$eq(boolean x$1) {
/* 526 */       this.owned = x$1;
/*     */     }
/*     */     
/*     */     public boolean completed() {
/* 526 */       return this.completed;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void completed_$eq(boolean x$1) {
/* 526 */       this.completed = x$1;
/*     */     }
/*     */     
/*     */     public void start() {
/* 526 */       ThreadPoolTasks.WrappedTask$class.start(this);
/*     */     }
/*     */     
/*     */     public void sync() {
/* 526 */       ThreadPoolTasks.WrappedTask$class.sync(this);
/*     */     }
/*     */     
/*     */     public boolean tryCancel() {
/* 526 */       return ThreadPoolTasks.WrappedTask$class.tryCancel(this);
/*     */     }
/*     */     
/*     */     public void run() {
/* 526 */       ThreadPoolTasks.WrappedTask$class.run(this);
/*     */     }
/*     */     
/*     */     public void release() {
/* 526 */       ThreadPoolTasks.WrappedTask$class.release(this);
/*     */     }
/*     */     
/*     */     public Task<R, Tp> body() {
/* 526 */       return this.body;
/*     */     }
/*     */     
/*     */     public WrappedTask(AdaptiveWorkStealingThreadPoolTasks $outer, Task<R, Tp> body) {
/* 526 */       Tasks.WrappedTask$class.$init$(this);
/* 526 */       ThreadPoolTasks.WrappedTask$class.$init$(this);
/* 526 */       AdaptiveWorkStealingTasks.WrappedTask$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Seq<AdaptiveWorkStealingTasks.WrappedTask<R, Tp>> split() {
/* 528 */       return (Seq<AdaptiveWorkStealingTasks.WrappedTask<R, Tp>>)body().split().map((Function1)new AdaptiveWorkStealingThreadPoolTasks$WrappedTask$$anonfun$split$2(this), Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class AdaptiveWorkStealingThreadPoolTasks$WrappedTask$$anonfun$split$2 extends AbstractFunction1<Task<R, Tp>, WrappedTask<R, Tp>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final AdaptiveWorkStealingThreadPoolTasks.WrappedTask<R, Tp> apply(Task<R, Tp> b) {
/* 528 */         return this.$outer.scala$collection$parallel$AdaptiveWorkStealingThreadPoolTasks$WrappedTask$$$outer().newWrappedTask(b);
/*     */       }
/*     */       
/*     */       public AdaptiveWorkStealingThreadPoolTasks$WrappedTask$$anonfun$split$2(AdaptiveWorkStealingThreadPoolTasks.WrappedTask $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AdaptiveWorkStealingThreadPoolTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */