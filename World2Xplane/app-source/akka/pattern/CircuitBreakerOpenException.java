/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.util.control.NoStackTrace;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001e3A!\001\002\001\017\tY2)\033:dk&$(I]3bW\026\024x\n]3o\013b\034W\r\035;j_:T!a\001\003\002\017A\fG\017^3s]*\tQ!\001\003bW.\f7\001A\n\004\001!a\001CA\005\013\033\005!\021BA\006\005\0055\t5n[1Fq\016,\007\017^5p]B\021Q\002F\007\002\035)\021q\002E\001\bG>tGO]8m\025\t\t\"#\001\003vi&d'\"A\n\002\013M\034\027\r\\1\n\005Uq!\001\004(p'R\f7m\033+sC\016,\007\002C\f\001\005\013\007I\021\001\r\002#I,W.Y5oS:<G)\036:bi&|g.F\001\032!\tQr$D\001\034\025\taR$\001\005ekJ\fG/[8o\025\tq\"#\001\006d_:\034WO\035:f]RL!\001I\016\003\035\031Kg.\033;f\tV\024\030\r^5p]\"A!\005\001B\001B\003%\021$\001\nsK6\f\027N\\5oO\022+(/\031;j_:\004\003\002\003\023\001\005\003\005\013\021B\023\002\0175,7o]1hKB\021aE\013\b\003O!j\021AE\005\003SI\ta\001\025:fI\0264\027BA\026-\005\031\031FO]5oO*\021\021F\005\005\006]\001!\taL\001\007y%t\027\016\036 \025\007A\0224\007\005\0022\0015\t!\001C\003\030[\001\007\021\004C\004%[A\005\t\031A\023\b\017U\022\021\021!E\001m\005Y2)\033:dk&$(I]3bW\026\024x\n]3o\013b\034W\r\035;j_:\004\"!M\034\007\017\005\021\021\021!E\001qM\031q'\017\037\021\005\035R\024BA\036\023\005\031\te.\037*fMB\021q%P\005\003}I\021AbU3sS\006d\027N_1cY\026DQAL\034\005\002\001#\022A\016\005\b\005^\n\n\021\"\001D\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%eU\tAI\013\002&\013.\na\t\005\002H\0316\t\001J\003\002J\025\006IQO\\2iK\016\\W\r\032\006\003\027J\t!\"\0318o_R\fG/[8o\023\ti\005JA\tv]\016DWmY6fIZ\013'/[1oG\026DqaT\034\002\002\023%\001+A\006sK\006$'+Z:pYZ,G#A)\021\005I;V\"A*\013\005Q+\026\001\0027b]\036T\021AV\001\005U\0064\030-\003\002Y'\n1qJ\0316fGR\004")
/*     */ public class CircuitBreakerOpenException extends AkkaException implements NoStackTrace {
/*     */   private final FiniteDuration remainingDuration;
/*     */   
/*     */   public static String $lessinit$greater$default$2() {
/*     */     return CircuitBreakerOpenException$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/* 501 */     return super.fillInStackTrace();
/*     */   }
/*     */   
/*     */   public Throwable fillInStackTrace() {
/* 501 */     return NoStackTrace.class.fillInStackTrace(this);
/*     */   }
/*     */   
/*     */   public FiniteDuration remainingDuration() {
/* 502 */     return this.remainingDuration;
/*     */   }
/*     */   
/*     */   public CircuitBreakerOpenException(FiniteDuration remainingDuration, String message) {
/* 504 */     super(message);
/*     */     NoStackTrace.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\CircuitBreakerOpenException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */