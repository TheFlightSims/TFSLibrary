package akka.actor;

import akka.event.LoggingAdapter;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\00192\001\"\001\002\021\002\007\005qA\n\002\r\003\016$xN\035'pO\036Lgn\032\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\001\001\024\005\001A\001CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\rC\003\020\001\021\005\001#\001\004%S:LG\017\n\013\002#A\021\021BE\005\003')\021A!\0268ji\"IQ\003\001a\001\002\004%IAF\001\005?2|w-F\001\030!\tA2$D\001\032\025\tQB!A\003fm\026tG/\003\002\0353\tqAj\\4hS:<\027\tZ1qi\026\024\b\"\003\020\001\001\004\005\r\021\"\003 \003!yFn\\4`I\025\fHCA\t!\021\035\tS$!AA\002]\t1\001\037\0232\021\031\031\003\001)Q\005/\005)q\f\\8hA!)Q\005\001C\001-\005\031An\\4\023\007\035J3F\002\003)\001\0011#\001\004\037sK\032Lg.Z7f]Rt\004C\001\026\001\033\005\021\001C\001\026-\023\ti#AA\003BGR|'\017")
public interface ActorLogging {
  LoggingAdapter akka$actor$ActorLogging$$_log();
  
  @TraitSetter
  void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter paramLoggingAdapter);
  
  LoggingAdapter log();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorLogging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */