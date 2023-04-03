/*    */ package akka.pattern;
/*    */ 
/*    */ import java.util.concurrent.TimeoutException;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e2A!\001\002\001\017\t\031\022i]6US6,w.\036;Fq\016,\007\017^5p]*\0211\001B\001\ba\006$H/\032:o\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021\002E\007\002\025)\0211\002D\001\013G>t7-\036:sK:$(BA\007\017\003\021)H/\0337\013\003=\tAA[1wC&\021\021C\003\002\021)&lWm\\;u\013b\034W\r\035;j_:D\001b\005\001\003\002\003\006I\001F\001\b[\026\0348/Y4f!\t)2D\004\002\02735\tqCC\001\031\003\025\0318-\0317b\023\tQr#\001\004Qe\026$WMZ\005\0039u\021aa\025;sS:<'B\001\016\030\021!y\002A!A!\002\023\001\023!B2bkN,\007CA\021*\035\t\021sE\004\002$M5\tAE\003\002&\r\0051AH]8pizJ\021\001G\005\003Q]\tq\001]1dW\006<W-\003\002+W\tIA\013\033:po\006\024G.\032\006\003Q]AQ!\f\001\005\0029\na\001P5oSRtDcA\0302eA\021\001\007A\007\002\005!)1\003\fa\001)!)q\004\fa\001A!)Q\006\001C\001iQ\021q&\016\005\006'M\002\r\001\006\005\006o\001!\t\005O\001\tO\026$8)Y;tKR\t\001\005")
/*    */ public class AskTimeoutException extends TimeoutException {
/*    */   private final Throwable cause;
/*    */   
/*    */   public AskTimeoutException(String message, Throwable cause) {
/* 21 */     super(message);
/*    */   }
/*    */   
/*    */   public AskTimeoutException(String message) {
/* 22 */     this(message, null);
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 23 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AskTimeoutException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */