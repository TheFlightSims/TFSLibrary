/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.event.Logging;
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E2A!\001\002\001\017\ta\002+\0338oK\022$\025n\0359bi\016DWM]\"p]\032Lw-\036:bi>\024(BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0255\t!!\003\002\f\005\tiR*Z:tC\036,G)[:qCR\034\007.\032:D_:4\027nZ;sCR|'\017\003\005\016\001\t\005\t\025!\003\017\003\031\031wN\0344jOB\021q\"F\007\002!)\021Q\"\005\006\003%M\t\001\002^=qKN\fg-\032\006\002)\005\0311m\\7\n\005Y\001\"AB\"p]\032Lw\rC\005\031\001\t\005\t\025!\003\0329\005i\001O]3sKF,\030n]5uKN\004\"!\003\016\n\005m\021!a\006#jgB\fGo\0315feB\023XM]3rk&\034\030\016^3t\023\tA\"\002C\003\037\001\021\005q$\001\004=S:LGO\020\013\004A\005\022\003CA\005\001\021\025iQ\0041\001\017\021\025AR\0041\001\032\021\035!\003A1A\005\n\025\n\001\003\0365sK\006$\007k\\8m\007>tg-[4\026\003\031\002\"!C\024\n\005!\022!\001\005+ie\026\fG\rU8pY\016{gNZ5h\021\031Q\003\001)A\005M\005\tB\017\033:fC\022\004vn\0347D_:4\027n\032\021\t\0131\002A\021I\027\002\025\021L7\017]1uG\",'\017F\001/!\tIq&\003\0021\005\t\tR*Z:tC\036,G)[:qCR\034\007.\032:")
/*     */ public class PinnedDispatcherConfigurator extends MessageDispatcherConfigurator {
/*     */   private final Config config;
/*     */   
/*     */   private final ThreadPoolConfig threadPoolConfig;
/*     */   
/*     */   public PinnedDispatcherConfigurator(Config config, DispatcherPrerequisites prerequisites) {
/* 280 */     super(
/* 281 */         config, prerequisites);
/*     */     ThreadPoolConfig threadPoolConfig;
/* 283 */     ExecutorServiceConfigurator executorServiceConfigurator = configureExecutor();
/* 284 */     if (executorServiceConfigurator instanceof ThreadPoolExecutorConfigurator) {
/* 284 */       ThreadPoolExecutorConfigurator threadPoolExecutorConfigurator = (ThreadPoolExecutorConfigurator)executorServiceConfigurator;
/* 284 */       threadPoolConfig = threadPoolExecutorConfigurator.threadPoolConfig();
/*     */     } else {
/* 286 */       prerequisites().eventStream().publish(
/* 287 */           new Logging.Warning("PinnedDispatcherConfigurator", 
/* 288 */             getClass(), (
/* 289 */             new StringOps(Predef$.MODULE$.augmentString("PinnedDispatcher [%s] not configured to use ThreadPoolExecutor, falling back to default config."))).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { config.getString("id") }))));
/* 291 */       threadPoolConfig = new ThreadPoolConfig(ThreadPoolConfig$.MODULE$.apply$default$1(), ThreadPoolConfig$.MODULE$.apply$default$2(), ThreadPoolConfig$.MODULE$.apply$default$3(), ThreadPoolConfig$.MODULE$.apply$default$4(), ThreadPoolConfig$.MODULE$.apply$default$5(), ThreadPoolConfig$.MODULE$.apply$default$6());
/*     */     } 
/*     */     this.threadPoolConfig = threadPoolConfig;
/*     */   }
/*     */   
/*     */   private ThreadPoolConfig threadPoolConfig() {
/*     */     return this.threadPoolConfig;
/*     */   }
/*     */   
/*     */   public MessageDispatcher dispatcher() {
/* 298 */     null;
/* 298 */     return new PinnedDispatcher(this, null, this.config.getString("id"), Helpers$ConfigOps$.MODULE$
/* 299 */         .getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(this.config), "shutdown-timeout"), threadPoolConfig());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\PinnedDispatcherConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */