/*     */ package akka.actor;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.Option;
/*     */ import scala.Tuple3;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M4A!\001\002\001\017\ta\022i\031;pe&s\027\016^5bY&T\030\r^5p]\026C8-\0329uS>t'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021BC\007\002\t%\0211\002\002\002\016\003.\\\027-\022=dKB$\030n\0348\t\021\r\001!\021!Q\001\n5\001\"AD\b\016\003\tI!\001\005\002\003\021\005\033Go\034:SK\032D\001B\005\001\003\002\003\006IaE\001\b[\026\0348/Y4f!\t!\"D\004\002\02615\taCC\001\030\003\025\0318-\0317b\023\tIb#\001\004Qe\026$WMZ\005\0037q\021aa\025;sS:<'BA\r\027\021!q\002A!A!\002\023y\022!B2bkN,\007C\001\021)\035\t\tcE\004\002#K5\t1E\003\002%\r\0051AH]8pizJ\021aF\005\003OY\tq\001]1dW\006<W-\003\002*U\tIA\013\033:po\006\024G.\032\006\003OYAQ\001\f\001\005\0225\na\001P5oSRtD\003\002\0300aE\002\"A\004\001\t\013\rY\003\031A\007\t\013IY\003\031A\n\t\013yY\003\031A\020\t\013M\002A\021\001\033\002\021\035,G/Q2u_J,\022!\004\025\004\001YJ\004CA\0138\023\tAdC\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\003<\005!\005A(\001\017BGR|'/\0238ji&\fG.\033>bi&|g.\022=dKB$\030n\0348\021\0059id!B\001\003\021\003q4cA\037@\005B\021Q\003Q\005\003\003Z\021a!\0218z%\0264\007CA\013D\023\t!eC\001\007TKJL\027\r\\5{C\ndW\rC\003-{\021\005a\tF\001=\021\031AU\b\"\001\005\023\006)\021\r\0359msR!aFS&M\021\025\031q\t1\001\016\021\025\021r\t1\001\024\021\035qr\t%AA\002}Aa\001S\037\005\002\021qEC\001\030P\021\025\021R\n1\001\024\021\025\tV\b\"\001S\003\035)h.\0319qYf$\"aU-\021\007U!f+\003\002V-\t1q\n\035;j_:\004R!F,\016'}I!\001\027\f\003\rQ+\b\017\\34\021\025Q\006\0131\001/\003\t)\007\020C\004]{E\005I\021A/\002\037\005\004\b\017\\=%I\0264\027-\0367uIM*\022A\030\026\003?}[\023\001\031\t\003C\032l\021A\031\006\003G\022\f\021\"\0368dQ\026\0347.\0323\013\005\0254\022AC1o]>$\030\r^5p]&\021qM\031\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB5>\003\003%IA[\001\fe\026\fGMU3t_24X\rF\001l!\ta\027/D\001n\025\tqw.\001\003mC:<'\"\0019\002\t)\fg/Y\005\003e6\024aa\0242kK\016$\b")
/*     */ public class ActorInitializationException extends AkkaException {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef actor;
/*     */   
/*     */   public ActorInitializationException(ActorRef actor, String message, Throwable cause) {
/* 158 */     super(
/* 159 */         message, cause);
/*     */   }
/*     */   
/*     */   public ActorRef getActor() {
/* 160 */     return this.actor;
/*     */   }
/*     */   
/*     */   public static Option<Tuple3<ActorRef, String, Throwable>> unapply(ActorInitializationException paramActorInitializationException) {
/*     */     return ActorInitializationException$.MODULE$.unapply(paramActorInitializationException);
/*     */   }
/*     */   
/*     */   public static Throwable apply$default$3() {
/*     */     return ActorInitializationException$.MODULE$.apply$default$3();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorInitializationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */