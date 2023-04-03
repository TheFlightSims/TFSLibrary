/*     */ package akka.actor;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001!2A!\001\002\001\017\tI\022i\031;pe&sG/\032:skB$X\rZ#yG\026\004H/[8o\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\023)i\021\001B\005\003\027\021\021Q\"Q6lC\026C8-\0329uS>t\007\002C\007\001\005\003\005\013\021\002\b\002\013\r\fWo]3\021\005=IbB\001\t\027\035\t\tB#D\001\023\025\t\031b!\001\004=e>|GOP\005\002+\005)1oY1mC&\021q\003G\001\ba\006\0347.Y4f\025\005)\022B\001\016\034\005%!\006N]8xC\ndWM\003\002\0301!1Q\004\001C\001\ty\ta\001P5oSRtDCA\020\"!\t\001\003!D\001\003\021\025iA\0041\001\017Q\r\0011e\n\t\003I\025j\021\001G\005\003Ma\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\005\001")
/*     */ public class ActorInterruptedException extends AkkaException {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   public ActorInterruptedException(Throwable cause) {
/* 239 */     super(cause.getMessage(), cause);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorInterruptedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */