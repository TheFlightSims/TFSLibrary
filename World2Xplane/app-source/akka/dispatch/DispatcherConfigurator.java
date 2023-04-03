/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001E2A!\001\002\001\017\t1B)[:qCR\034\007.\032:D_:4\027nZ;sCR|'O\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\023)i\021AA\005\003\027\t\021Q$T3tg\006<W\rR5ta\006$8\r[3s\007>tg-[4ve\006$xN\035\005\t\033\001\021\t\021)A\005\035\00511m\0348gS\036\004\"aD\013\016\003AQ!!D\t\013\005I\031\022\001\003;za\026\034\030MZ3\013\003Q\t1aY8n\023\t1\002C\001\004D_:4\027n\032\005\n1\001\021\t\021)A\0053q\tQ\002\035:fe\026\fX/[:ji\026\034\bCA\005\033\023\tY\"AA\fESN\004\030\r^2iKJ\004&/\032:fcVL7/\033;fg&\021\001D\003\005\006=\001!\taH\001\007y%t\027\016\036 \025\007\001\n#\005\005\002\n\001!)Q\"\ba\001\035!)\001$\ba\0013!9A\005\001b\001\n\023)\023\001C5ogR\fgnY3\026\003\031\002\"!C\024\n\005!\022!A\003#jgB\fGo\0315fe\"1!\006\001Q\001\n\031\n\021\"\0338ti\006t7-\032\021\t\0131\002A\021I\027\002\025\021L7\017]1uG\",'\017F\001/!\tIq&\003\0021\005\t\tR*Z:tC\036,G)[:qCR\034\007.\032:")
/*     */ public class DispatcherConfigurator extends MessageDispatcherConfigurator {
/*     */   private final Dispatcher instance;
/*     */   
/*     */   public DispatcherConfigurator(Config config, DispatcherPrerequisites prerequisites) {
/* 203 */     super(
/* 204 */         config, prerequisites);
/* 206 */     this.instance = new Dispatcher(
/* 207 */         this, 
/* 208 */         config.getString("id"), 
/* 209 */         config.getInt("throughput"), (Duration)Helpers$ConfigOps$.MODULE$
/* 210 */         .getNanosDuration$extension(Helpers$.MODULE$.ConfigOps(config), "throughput-deadline-time"), 
/* 211 */         configureExecutor(), Helpers$ConfigOps$.MODULE$
/* 212 */         .getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(config), "shutdown-timeout"));
/*     */   }
/*     */   
/*     */   private Dispatcher instance() {
/*     */     return this.instance;
/*     */   }
/*     */   
/*     */   public MessageDispatcher dispatcher() {
/* 217 */     return instance();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DispatcherConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */