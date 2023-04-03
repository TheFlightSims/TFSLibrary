/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.japi.Procedure;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.ExecutionContextExecutor;
/*    */ import scala.concurrent.ExecutionContextExecutorService;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Q<Q!\001\002\t\002\035\t\021#\022=fGV$\030n\0348D_:$X\r\037;t\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\tFq\026\034W\017^5p]\016{g\016^3yiN\034\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\003\027\023\021\005q#\001\007ge>lW\t_3dkR|'\017\006\002\031=A\021\021\004H\007\0025)\0211DD\001\013G>t7-\036:sK:$\030BA\017\033\005a)\0050Z2vi&|gnQ8oi\026DH/\022=fGV$xN\035\005\006?U\001\r\001I\001\tKb,7-\036;peB\021\021eJ\007\002E)\0211d\t\006\003I\025\nA!\036;jY*\ta%\001\003kCZ\f\027B\001\025#\005!)\0050Z2vi>\024\b\"\002\f\n\t\003QCc\001\r,Y!)q$\013a\001A!)Q&\013a\001]\005iQM\035:peJ+\007o\034:uKJ\0042a\f\0325\033\005\001$BA\031\005\003\021Q\027\r]5\n\005M\002$!\003)s_\016,G-\036:f!\t)TH\004\0027w9\021qGO\007\002q)\021\021HB\001\007yI|w\016\036 \n\003=I!\001\020\b\002\017A\f7m[1hK&\021ah\020\002\n)\"\024xn^1cY\026T!\001\020\b\t\013\005KA\021\001\"\002'\031\024x.\\#yK\016,Ho\034:TKJ4\030nY3\025\005\r3\005CA\rE\023\t)%DA\020Fq\026\034W\017^5p]\016{g\016^3yi\026CXmY;u_J\034VM\035<jG\026DQa\022!A\002!\013q\"\032=fGV$xN]*feZL7-\032\t\003C%K!A\023\022\003\037\025CXmY;u_J\034VM\035<jG\026DQ!Q\005\005\0021#2aQ'O\021\02595\n1\001I\021\025i3\n1\001/\021\025\001\026\002\"\001R\003\0319Gn\0342bYR\t\001d\002\004T\023!\005A\001V\001\033g\006lW\r\0265sK\006$W\t_3dkRLwN\\\"p]R,\007\020\036\t\003+Zk\021!\003\004\007/&A\t\001\002-\0035M\fW.\032+ie\026\fG-\022=fGV$\030n\0348D_:$X\r\037;\024\tYc\021\f\030\t\0033iK!a\027\016\003!\025CXmY;uS>t7i\0348uKb$\bC\001\005^\023\tq&A\001\tCCR\034\007.\0338h\013b,7-\036;pe\")1C\026C\001AR\tA\013C\003c-\022E3-\001\tv]\n\fGo\0315fI\026CXmY;uKR\021Am\032\t\003\033\025L!A\032\b\003\tUs\027\016\036\005\006Q\006\004\r![\001\teVtg.\0312mKB\021!.\\\007\002W*\021A.J\001\005Y\006tw-\003\002oW\nA!+\0368oC\ndW\rC\003q-\022\005\023/A\007sKB|'\017\036$bS2,(/\032\013\003IJDQa]8A\002Q\n\021\001\036")
/*    */ public final class ExecutionContexts {
/*    */   public static ExecutionContextExecutor global() {
/*    */     return ExecutionContexts$.MODULE$.global();
/*    */   }
/*    */   
/*    */   public static ExecutionContextExecutorService fromExecutorService(ExecutorService paramExecutorService, Procedure<Throwable> paramProcedure) {
/*    */     return ExecutionContexts$.MODULE$.fromExecutorService(paramExecutorService, paramProcedure);
/*    */   }
/*    */   
/*    */   public static ExecutionContextExecutorService fromExecutorService(ExecutorService paramExecutorService) {
/*    */     return ExecutionContexts$.MODULE$.fromExecutorService(paramExecutorService);
/*    */   }
/*    */   
/*    */   public static ExecutionContextExecutor fromExecutor(Executor paramExecutor, Procedure<Throwable> paramProcedure) {
/*    */     return ExecutionContexts$.MODULE$.fromExecutor(paramExecutor, paramProcedure);
/*    */   }
/*    */   
/*    */   public static ExecutionContextExecutor fromExecutor(Executor paramExecutor) {
/*    */     return ExecutionContexts$.MODULE$.fromExecutor(paramExecutor);
/*    */   }
/*    */   
/*    */   public static class ExecutionContexts$$anonfun$fromExecutor$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Procedure errorReporter$1;
/*    */     
/*    */     public final void apply(Throwable param) {
/* 38 */       this.errorReporter$1.apply(param);
/*    */     }
/*    */     
/*    */     public ExecutionContexts$$anonfun$fromExecutor$1(Procedure errorReporter$1) {}
/*    */   }
/*    */   
/*    */   public static class ExecutionContexts$$anonfun$fromExecutorService$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Procedure errorReporter$2;
/*    */     
/*    */     public final void apply(Throwable param) {
/* 59 */       this.errorReporter$2.apply(param);
/*    */     }
/*    */     
/*    */     public ExecutionContexts$$anonfun$fromExecutorService$1(Procedure errorReporter$2) {}
/*    */   }
/*    */   
/*    */   public static class sameThreadExecutionContext$ implements ExecutionContext, BatchingExecutor {
/*    */     public static final sameThreadExecutionContext$ MODULE$;
/*    */     
/*    */     private final ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal;
/*    */     
/*    */     public ThreadLocal<List<Runnable>> akka$dispatch$BatchingExecutor$$_tasksLocal() {
/* 73 */       return this.akka$dispatch$BatchingExecutor$$_tasksLocal;
/*    */     }
/*    */     
/*    */     public void akka$dispatch$BatchingExecutor$_setter_$akka$dispatch$BatchingExecutor$$_tasksLocal_$eq(ThreadLocal<List<Runnable>> x$1) {
/* 73 */       this.akka$dispatch$BatchingExecutor$$_tasksLocal = x$1;
/*    */     }
/*    */     
/*    */     public void execute(Runnable runnable) {
/* 73 */       BatchingExecutor$class.execute(this, runnable);
/*    */     }
/*    */     
/*    */     public boolean batchable(Runnable runnable) {
/* 73 */       return BatchingExecutor$class.batchable(this, runnable);
/*    */     }
/*    */     
/*    */     public ExecutionContext prepare() {
/* 73 */       return ExecutionContext.class.prepare(this);
/*    */     }
/*    */     
/*    */     public sameThreadExecutionContext$() {
/* 73 */       MODULE$ = this;
/* 73 */       ExecutionContext.class.$init$(this);
/* 73 */       BatchingExecutor$class.$init$(this);
/*    */     }
/*    */     
/*    */     public void unbatchedExecute(Runnable runnable) {
/* 74 */       runnable.run();
/*    */     }
/*    */     
/*    */     public void reportFailure(Throwable t) {
/* 76 */       throw new IllegalStateException("exception in sameThreadExecutionContext", t);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ExecutionContexts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */