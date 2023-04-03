/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.concurrent.forkjoin.RecursiveAction;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t4q!\001\002\021\002\007\005\021BA\021BI\006\004H/\033<f/>\0248n\025;fC2Lgn\032$pe.Tu.\0338UCN\\7O\003\002\004\t\005A\001/\031:bY2,GN\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001a\005\003\001\0259\021\002CA\006\r\033\0051\021BA\007\007\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\016\r>\0248NS8j]R\0137o[:\021\005=\031\022B\001\013\003\005e\tE-\0319uSZ,wk\034:l'R,\027\r\\5oOR\0137o[:\t\013Y\001A\021A\f\002\r\021Jg.\033;%)\005A\002CA\006\032\023\tQbA\001\003V]&$h\001\002\017\001\001u\0211b\026:baB,G\rV1tWV\031a\004\f\034\024\tmyr\005\017\t\003A\025j\021!\t\006\003E\r\n\001BZ8sW*|\027N\034\006\003I\031\t!bY8oGV\024(/\0328u\023\t1\023EA\bSK\016,(o]5wK\006\033G/[8o!\021A\023FK\033\016\003\001I!\001\b\t\021\005-bC\002\001\003\006[m\021\rA\f\002\002%F\021qF\r\t\003\027AJ!!\r\004\003\0179{G\017[5oOB\0211bM\005\003i\031\0211!\0218z!\tYc\007B\00387\t\007aF\001\002UaB!\001&\017\0266\023\ta2\003\003\005<7\t\025\r\021\"\001=\003\021\021w\016Z=\026\003u\002Ba\004 +k%\021qH\001\002\005)\006\0348\016\003\005B7\t\005\t\025!\003>\003\025\021w\016Z=!\021\025\0315\004\"\001E\003\031a\024N\\5u}Q\021QI\022\t\005QmQS\007C\003<\005\002\007Q\bC\003I7\021\005\021*A\003ta2LG/F\001K!\rY5\013\017\b\003\031Fs!!\024)\016\0039S!a\024\005\002\rq\022xn\034;?\023\0059\021B\001*\007\003\035\001\030mY6bO\026L!\001V+\003\007M+\027O\003\002S\r!)q\013\001C\0011\006qa.Z<Xe\006\004\b/\0323UCN\\WcA-]=R\021!l\030\t\005QmYV\f\005\002,9\022)QF\026b\001]A\0211F\030\003\006oY\023\rA\f\005\006AZ\003\r!Y\001\002EB!qBP.^\001")
/*     */ public interface AdaptiveWorkStealingForkJoinTasks extends ForkJoinTasks, AdaptiveWorkStealingTasks {
/*     */   <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> paramTask);
/*     */   
/*     */   public class WrappedTask<R, Tp> extends RecursiveAction implements ForkJoinTasks.WrappedTask<R, Tp>, AdaptiveWorkStealingTasks.WrappedTask<R, Tp> {
/*     */     private final Task<R, Tp> body;
/*     */     
/*     */     private volatile AdaptiveWorkStealingTasks.WrappedTask<Object, Object> next;
/*     */     
/*     */     private volatile boolean shouldWaitFor;
/*     */     
/*     */     public AdaptiveWorkStealingTasks.WrappedTask<R, Tp> next() {
/* 514 */       return (AdaptiveWorkStealingTasks.WrappedTask)this.next;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void next_$eq(AdaptiveWorkStealingTasks.WrappedTask<Object, Object> x$1) {
/* 514 */       this.next = x$1;
/*     */     }
/*     */     
/*     */     public boolean shouldWaitFor() {
/* 514 */       return this.shouldWaitFor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void shouldWaitFor_$eq(boolean x$1) {
/* 514 */       this.shouldWaitFor = x$1;
/*     */     }
/*     */     
/*     */     public void compute() {
/* 514 */       AdaptiveWorkStealingTasks.WrappedTask$class.compute(this);
/*     */     }
/*     */     
/*     */     public void internal() {
/* 514 */       AdaptiveWorkStealingTasks.WrappedTask$class.internal(this);
/*     */     }
/*     */     
/*     */     public AdaptiveWorkStealingTasks.WrappedTask<R, Tp> spawnSubtasks() {
/* 514 */       return AdaptiveWorkStealingTasks.WrappedTask$class.spawnSubtasks(this);
/*     */     }
/*     */     
/*     */     public void printChain() {
/* 514 */       AdaptiveWorkStealingTasks.WrappedTask$class.printChain(this);
/*     */     }
/*     */     
/*     */     public void start() {
/* 514 */       ForkJoinTasks.WrappedTask$class.start(this);
/*     */     }
/*     */     
/*     */     public void sync() {
/* 514 */       ForkJoinTasks.WrappedTask$class.sync(this);
/*     */     }
/*     */     
/*     */     public boolean tryCancel() {
/* 514 */       return ForkJoinTasks.WrappedTask$class.tryCancel(this);
/*     */     }
/*     */     
/*     */     public void release() {
/* 514 */       Tasks.WrappedTask$class.release(this);
/*     */     }
/*     */     
/*     */     public Task<R, Tp> body() {
/* 514 */       return this.body;
/*     */     }
/*     */     
/*     */     public WrappedTask(AdaptiveWorkStealingForkJoinTasks $outer, Task<R, Tp> body) {
/* 514 */       Tasks.WrappedTask$class.$init$(this);
/* 514 */       ForkJoinTasks.WrappedTask$class.$init$(this);
/* 514 */       AdaptiveWorkStealingTasks.WrappedTask$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Seq<AdaptiveWorkStealingTasks.WrappedTask<R, Tp>> split() {
/* 516 */       return (Seq<AdaptiveWorkStealingTasks.WrappedTask<R, Tp>>)body().split().map((Function1)new AdaptiveWorkStealingForkJoinTasks$WrappedTask$$anonfun$split$1(this), Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class AdaptiveWorkStealingForkJoinTasks$WrappedTask$$anonfun$split$1 extends AbstractFunction1<Task<R, Tp>, WrappedTask<R, Tp>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final AdaptiveWorkStealingForkJoinTasks.WrappedTask<R, Tp> apply(Task<R, Tp> b) {
/* 516 */         return this.$outer.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$WrappedTask$$$outer().newWrappedTask(b);
/*     */       }
/*     */       
/*     */       public AdaptiveWorkStealingForkJoinTasks$WrappedTask$$anonfun$split$1(AdaptiveWorkStealingForkJoinTasks.WrappedTask $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AdaptiveWorkStealingForkJoinTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */