/*    */ package akka.actor;
/*    */ 
/*    */ import akka.event.LoggingAdapter;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M1Q!\001\002\002\002\035\021A#\0212tiJ\f7\r\036'pO\036LgnZ!di>\024(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001aE\002\001\0211\001\"!\003\006\016\003\tI!a\003\002\003\033\005\0237\017\036:bGR\f5\r^8s!\tIQ\"\003\002\017\005\ta\021i\031;pe2{wmZ5oO\")\001\003\001C\001#\0051A(\0338jiz\"\022A\005\t\003\023\001\001")
/*    */ public abstract class AbstractLoggingActor extends AbstractActor implements ActorLogging {
/*    */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*    */   
/*    */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/* 81 */     return this.akka$actor$ActorLogging$$_log;
/*    */   }
/*    */   
/*    */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/* 81 */     this.akka$actor$ActorLogging$$_log = x$1;
/*    */   }
/*    */   
/*    */   public LoggingAdapter log() {
/* 81 */     return ActorLogging$class.log(this);
/*    */   }
/*    */   
/*    */   public AbstractLoggingActor() {
/* 81 */     ActorLogging$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractLoggingActor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */