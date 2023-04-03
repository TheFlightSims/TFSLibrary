/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.event.Logging;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.AbstractExecutorService;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001-3A!\001\002\001\017\t\021C)\0324bk2$X\t_3dkR|'oU3sm&\034WmQ8oM&<WO]1u_JT!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\005\001A\001CA\005\013\033\005\021\021BA\006\003\005m)\0050Z2vi>\0248+\032:wS\016,7i\0348gS\036,(/\031;pe\"AQ\002\001B\001B\003%a\"\001\004d_:4\027n\032\t\003\037Ui\021\001\005\006\003\033EQ!AE\n\002\021QL\b/Z:bM\026T\021\001F\001\004G>l\027B\001\f\021\005\031\031uN\0344jO\"A\001\004\001B\001B\003%\021$A\007qe\026\024X-];jg&$Xm\035\t\003\023iI!a\007\002\003/\021K7\017]1uG\",'\017\025:fe\026\fX/[:ji\026\034\b\002C\017\001\005\003\005\013\021\002\005\002\021\031\fG\016\0342bG.DQa\b\001\005\002\001\na\001P5oSRtD\003B\021#G\021\002\"!\003\001\t\0135q\002\031\001\b\t\013aq\002\031A\r\t\013uq\002\031\001\005\t\017\031\002!\031!C\001O\005A\001O]8wS\022,'/F\001)!\tI\021&\003\002+\005\tqR\t_3dkR|'oU3sm&\034WMR1di>\024\030\020\025:pm&$WM\035\005\007Y\001\001\013\021\002\025\002\023A\024xN^5eKJ\004\003\"\002\030\001\t\003y\023\001H2sK\006$X-\022=fGV$xN]*feZL7-\032$bGR|'/\037\013\004aMz\004CA\0052\023\t\021$A\001\fFq\026\034W\017^8s'\026\024h/[2f\r\006\034Go\034:z\021\025!T\0061\0016\003\tIG\r\005\0027y9\021qGO\007\002q)\t\021(A\003tG\006d\027-\003\002<q\0051\001K]3eK\032L!!\020 \003\rM#(/\0338h\025\tY\004\bC\003A[\001\007\021)A\007uQJ,\027\r\032$bGR|'/\037\t\003\005&k\021a\021\006\003\t\026\013!bY8oGV\024(/\0328u\025\t1u)\001\003vi&d'\"\001%\002\t)\fg/Y\005\003\025\016\023Q\002\0265sK\006$g)Y2u_JL\b")
/*     */ public class DefaultExecutorServiceConfigurator extends ExecutorServiceConfigurator {
/*     */   private final ExecutorServiceFactoryProvider provider;
/*     */   
/*     */   public DefaultExecutorServiceConfigurator(Config config, DispatcherPrerequisites prerequisites, ExecutorServiceConfigurator fallback) {
/* 433 */     super(config, prerequisites);
/*     */     ExecutorServiceConfigurator executorServiceConfigurator;
/* 435 */     Option<ExecutionContext> option = prerequisites.defaultExecutionContext();
/* 436 */     if (option instanceof Some) {
/* 436 */       Some some = (Some)option;
/* 436 */       ExecutionContext ec = (ExecutionContext)some.x();
/* 437 */       (new String[1])[0] = "Using passed in ExecutionContext as default executor for this ActorSystem. If you want to use a different executor, please specify one in akka.actor.default-dispatcher.default-executor.";
/* 437 */       prerequisites.eventStream().publish(new Logging.Debug("DefaultExecutorServiceConfigurator", getClass(), (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[1]))).s((Seq)Nil$.MODULE$)));
/* 439 */       $anon$1 $anon$1 = new $anon$1(this, ec);
/*     */     } else {
/* 449 */       Option<ExecutionContext> option1 = option;
/* 449 */       if (None$.MODULE$ == null) {
/* 449 */         if (option1 != null)
/*     */           throw new MatchError(option); 
/* 449 */       } else if (!None$.MODULE$.equals(option1)) {
/*     */         throw new MatchError(option);
/*     */       } 
/* 449 */       executorServiceConfigurator = fallback;
/*     */     } 
/* 449 */     ((DefaultExecutorServiceConfigurator)None$.MODULE$).provider = executorServiceConfigurator;
/*     */   }
/*     */   
/*     */   public ExecutorServiceFactoryProvider provider() {
/*     */     return this.provider;
/*     */   }
/*     */   
/*     */   public class $anon$1 extends AbstractExecutorService implements ExecutorServiceFactory, ExecutorServiceFactoryProvider {
/*     */     private final ExecutionContext ec$1;
/*     */     
/*     */     public $anon$1(DefaultExecutorServiceConfigurator $outer, ExecutionContext ec$1) {}
/*     */     
/*     */     public ExecutorServiceFactory createExecutorServiceFactory(String id, ThreadFactory threadFactory) {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public ExecutorService createExecutorService() {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public void shutdown() {}
/*     */     
/*     */     public boolean isTerminated() {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public boolean awaitTermination(long timeout, TimeUnit unit) {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public List<Runnable> shutdownNow() {
/*     */       return Collections.emptyList();
/*     */     }
/*     */     
/*     */     public void execute(Runnable command) {
/*     */       this.ec$1.execute(command);
/*     */     }
/*     */     
/*     */     public boolean isShutdown() {
/*     */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public ExecutorServiceFactory createExecutorServiceFactory(String id, ThreadFactory threadFactory) {
/* 453 */     return provider().createExecutorServiceFactory(id, threadFactory);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DefaultExecutorServiceConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */