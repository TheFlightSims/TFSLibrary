/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSelection;
/*     */ import akka.actor.Scheduler;
/*     */ import akka.util.Timeout;
/*     */ import java.util.concurrent.Callable;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005s!B\001\003\021\0039\021\001\003)biR,'O\\:\013\005\r!\021a\0029biR,'O\034\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021\001\002U1ui\026\024hn]\n\003\0231\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007\"B\n\n\t\003!\022A\002\037j]&$h\bF\001\b\021\0251\022\002\"\001\030\003\r\t7o\033\013\0051y)#\006E\002\03291i\021A\007\006\00379\t!bY8oGV\024(/\0328u\023\ti\"D\001\004GkR,(/\032\005\006?U\001\r\001I\001\006C\016$xN\035\t\003C\rj\021A\t\006\003?\021I!\001\n\022\003\021\005\033Go\034:SK\032DQAJ\013A\002\035\nq!\\3tg\006<W\r\005\002\016Q%\021\021F\004\002\004\003:L\b\"B\026\026\001\004a\023a\002;j[\026|W\017\036\t\003[Aj\021A\f\006\003_\021\tA!\036;jY&\021\021G\f\002\b)&lWm\\;u\021\0251\022\002\"\0014)\021AB'\016\034\t\013}\021\004\031\001\021\t\013\031\022\004\031A\024\t\013]\022\004\031\001\035\002\033QLW.Z8vi6KG\016\\5t!\ti\021(\003\002;\035\t!Aj\0348h\021\0251\022\002\"\001=)\021ARHQ\"\t\013yZ\004\031A \002\023M,G.Z2uS>t\007CA\021A\023\t\t%E\001\bBGR|'oU3mK\016$\030n\0348\t\013\031Z\004\031A\024\t\013-Z\004\031\001\027\t\013YIA\021A#\025\ta1u\t\023\005\006}\021\003\ra\020\005\006M\021\003\ra\n\005\006o\021\003\r\001\017\005\006\025&!\taS\001\005a&\004X-\006\002M/R\031Q*\0301\021\0079\013VK\004\002\t\037&\021\001KA\001\ba\006\0347.Y4f\023\t\0216K\001\bQSB,\027M\0317f\rV$XO]3\n\005Q\023!!\004)ja\026$vnU;qa>\024H\017\005\002W/2\001A!\002-J\005\004I&!\001+\022\005i;\003CA\007\\\023\tafBA\004O_RD\027N\\4\t\013yK\005\031A0\002\r\031,H/\036:f!\rIB$\026\005\006C&\003\rAY\001\bG>tG/\032=u!\tI2-\003\002e5\t\001R\t_3dkRLwN\\\"p]R,\007\020\036\005\006M&!\taZ\001\rOJ\f7-\0324vYN#x\016\035\013\004QF\034\bcA\r\035SB\021!n\\\007\002W*\021A.\\\001\005Y\006twMC\001o\003\021Q\027M^1\n\005A\\'a\002\"p_2,\027M\034\005\006e\026\004\r\001I\001\007i\006\024x-\032;\t\013-*\007\031\001;\021\005UDX\"\001<\013\005]T\022\001\0033ve\006$\030n\0348\n\005e4(A\004$j]&$X\rR;sCRLwN\034\005\006M&!\ta\037\013\005Qrlh\020C\003su\002\007\001\005C\003,u\002\007A\017C\003\000u\002\007q%A\006ti>\004X*Z:tC\036,\007bBA\002\023\021\005\021QA\001\006C\032$XM]\013\005\003\017\ti\001\006\006\002\n\005=\021\021CA\016\003;\001B!\007\017\002\fA\031a+!\004\005\ra\013\tA1\001Z\021\0319\030\021\001a\001i\"A\0211CA\001\001\004\t)\"A\005tG\",G-\0367feB\031\021%a\006\n\007\005e!EA\005TG\",G-\0367fe\"1\021-!\001A\002\tD\001\"a\b\002\002\001\007\021\021E\001\006m\006dW/\032\t\007\003G\tI#!\003\016\005\005\025\"bA\016\002()\021q&\\\005\005\003W\t)C\001\005DC2d\027M\0317f\021\035\t\031!\003C\001\003_)B!!\r\0028QQ\0211GA\035\003w\ti$a\020\021\tea\022Q\007\t\004-\006]BA\002-\002.\t\007\021\f\003\004x\003[\001\r\001\036\005\t\003'\ti\0031\001\002\026!1\021-!\fA\002\tD\001\"a\b\002.\001\007\0211\007")
/*     */ public final class Patterns {
/*     */   public static <T> Future<T> after(FiniteDuration paramFiniteDuration, Scheduler paramScheduler, ExecutionContext paramExecutionContext, Future<T> paramFuture) {
/*     */     return Patterns$.MODULE$.after(paramFiniteDuration, paramScheduler, paramExecutionContext, paramFuture);
/*     */   }
/*     */   
/*     */   public static <T> Future<T> after(FiniteDuration paramFiniteDuration, Scheduler paramScheduler, ExecutionContext paramExecutionContext, Callable<Future<T>> paramCallable) {
/*     */     return Patterns$.MODULE$.after(paramFiniteDuration, paramScheduler, paramExecutionContext, paramCallable);
/*     */   }
/*     */   
/*     */   public static Future<Boolean> gracefulStop(ActorRef paramActorRef, FiniteDuration paramFiniteDuration, Object paramObject) {
/*     */     return Patterns$.MODULE$.gracefulStop(paramActorRef, paramFiniteDuration, paramObject);
/*     */   }
/*     */   
/*     */   public static Future<Boolean> gracefulStop(ActorRef paramActorRef, FiniteDuration paramFiniteDuration) {
/*     */     return Patterns$.MODULE$.gracefulStop(paramActorRef, paramFiniteDuration);
/*     */   }
/*     */   
/*     */   public static <T> PipeToSupport.PipeableFuture<T> pipe(Future<T> paramFuture, ExecutionContext paramExecutionContext) {
/*     */     return Patterns$.MODULE$.pipe(paramFuture, paramExecutionContext);
/*     */   }
/*     */   
/*     */   public static Future<Object> ask(ActorSelection paramActorSelection, Object paramObject, long paramLong) {
/*     */     return Patterns$.MODULE$.ask(paramActorSelection, paramObject, paramLong);
/*     */   }
/*     */   
/*     */   public static Future<Object> ask(ActorSelection paramActorSelection, Object paramObject, Timeout paramTimeout) {
/*     */     return Patterns$.MODULE$.ask(paramActorSelection, paramObject, paramTimeout);
/*     */   }
/*     */   
/*     */   public static Future<Object> ask(ActorRef paramActorRef, Object paramObject, long paramLong) {
/*     */     return Patterns$.MODULE$.ask(paramActorRef, paramObject, paramLong);
/*     */   }
/*     */   
/*     */   public static Future<Object> ask(ActorRef paramActorRef, Object paramObject, Timeout paramTimeout) {
/*     */     return Patterns$.MODULE$.ask(paramActorRef, paramObject, paramTimeout);
/*     */   }
/*     */   
/*     */   public static class Patterns$$anonfun$after$1 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable value$1;
/*     */     
/*     */     public final Future<T> apply() {
/* 198 */       return this.value$1.call();
/*     */     }
/*     */     
/*     */     public Patterns$$anonfun$after$1(Callable value$1) {}
/*     */   }
/*     */   
/*     */   public static class Patterns$$anonfun$after$2 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Future value$2;
/*     */     
/*     */     public final Future<T> apply() {
/* 205 */       return this.value$2;
/*     */     }
/*     */     
/*     */     public Patterns$$anonfun$after$2(Future value$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\Patterns.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */