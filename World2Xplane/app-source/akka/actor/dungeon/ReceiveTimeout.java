package akka.actor.dungeon;

import akka.actor.Cancellable;
import scala.Tuple2;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\001-;a!\001\002\t\002\031A\021A\004*fG\026Lg/\032+j[\026|W\017\036\006\003\007\021\tq\001Z;oO\026|gN\003\002\006\r\005)\021m\031;pe*\tq!\001\003bW.\f\007CA\005\013\033\005\021aAB\006\003\021\0031AB\001\bSK\016,\027N^3US6,w.\036;\024\005)i\001C\001\b\022\033\005y!\"\001\t\002\013M\034\027\r\\1\n\005Iy!AB!osJ+g\rC\003\025\025\021\005a#\001\004=S:LGOP\002\001)\005A\001b\002\r\013\005\004%)!G\001\030K6\004H/\037*fG\026Lg/\032+j[\026|W\017\036#bi\006,\022A\007\t\005\035miR%\003\002\035\037\t1A+\0369mKJ\002\"AH\022\016\003}Q!\001I\021\002\021\021,(/\031;j_:T!AI\b\002\025\r|gnY;se\026tG/\003\002%?\tAA)\036:bi&|g\016\005\002'O5\tA!\003\002)\t\tY1)\0318dK2d\027M\0317f\021\031Q#\002)A\0075\005AR-\0349usJ+7-Z5wKRKW.Z8vi\022\013G/\031\021\007\023-\021\001\023aA\001\r1B5CA\026\016\021\025q3\006\"\0010\003\031!\023N\\5uIQ\t\001\007\005\002\017c%\021!g\004\002\005+:LG\017C\0045W\001\007I\021B\r\002%I,7-Z5wKRKW.Z8vi\022\013G/\031\005\bm-\002\r\021\"\0038\003Y\021XmY3jm\026$\026.\\3pkR$\025\r^1`I\025\fHC\001\0319\021\035IT'!AA\002i\t1\001\037\0232\021\031Y4\006)Q\0055\005\031\"/Z2fSZ,G+[7f_V$H)\031;bA!)Qh\013C\003}\005q!/Z2fSZ,G+[7f_V$X#A\017\t\013\001[CQA!\002#M,GOU3dK&4X\rV5nK>,H\017\006\0021\005\")1i\020a\001;\0059A/[7f_V$\b\"B#,\t\013y\023aE2iK\016\\'+Z2fSZ,G+[7f_V$\b\"B$,\t\013y\023\001F2b]\016,GNU3dK&4X\rV5nK>,H\017\005\002'\023&\021!\n\002\002\n\003\016$xN]\"fY2\004")
public interface ReceiveTimeout {
  Tuple2<Duration, Cancellable> akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData();
  
  @TraitSetter
  void akka$actor$dungeon$ReceiveTimeout$$receiveTimeoutData_$eq(Tuple2<Duration, Cancellable> paramTuple2);
  
  Duration receiveTimeout();
  
  void setReceiveTimeout(Duration paramDuration);
  
  void checkReceiveTimeout();
  
  void cancelReceiveTimeout();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\ReceiveTimeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */