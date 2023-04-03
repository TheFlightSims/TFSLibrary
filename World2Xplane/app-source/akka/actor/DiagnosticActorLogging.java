package akka.actor;

import akka.event.DiagnosticLoggingAdapter;
import scala.PartialFunction;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes = "\006\00153q!\001\002\021\002\007\005qA\001\fES\006<gn\\:uS\016\f5\r^8s\031><w-\0338h\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M\031\001\001\003\b\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\003BGR|'\017C\003\024\001\021\005A#\001\004%S:LG\017\n\013\002+A\021\021BF\005\003/)\021A!\0268ji\"9\021\004\001b\001\n\003Q\022a\0017pOV\t1\004\005\002\035?5\tQD\003\002\037\t\005)QM^3oi&\021\001%\b\002\031\t&\fwM\\8ti&\034Gj\\4hS:<\027\tZ1qi\026\024\bB\002\022\001A\003%1$\001\003m_\036\004\003\"\002\023\001\t\003)\023aA7eGR\021a\005\016\t\003OEr!\001K\030\017\005%rcB\001\026.\033\005Y#B\001\027\007\003\031a$o\\8u}%\tQ!\003\002\037\t%\021\001'H\001\b\031><w-\0338h\023\t\0214GA\002N\t\016S!\001M\017\t\013U\032\003\031\001\034\002\035\r,(O]3oi6+7o]1hKB\021\021bN\005\003q)\0211!\0218z\021\031Q\004\001\"\025\005w\005i\021M]8v]\022\024VmY3jm\026$2!\006\037F\021\025i\024\b1\001?\003\035\021XmY3jm\026\004\"a\020\"\017\005=\001\025BA!\003\003\025\t5\r^8s\023\t\031EIA\004SK\016,\027N^3\013\005\005\023\001\"\002$:\001\0041\024aA7tO\"I\001\nAA\001\002\023%\021\nT\001\024gV\004XM\035\023be>,h\016\032*fG\026Lg/\032\013\004+)[\005\"B\037H\001\004q\004\"\002$H\001\0041\024B\001\036\021\001")
public interface DiagnosticActorLogging extends Actor {
  void akka$actor$DiagnosticActorLogging$_setter_$log_$eq(DiagnosticLoggingAdapter paramDiagnosticLoggingAdapter);
  
  void akka$actor$DiagnosticActorLogging$$super$aroundReceive(PartialFunction<Object, BoxedUnit> paramPartialFunction, Object paramObject);
  
  DiagnosticLoggingAdapter log();
  
  Map<String, Object> mdc(Object paramObject);
  
  void aroundReceive(PartialFunction<Object, BoxedUnit> paramPartialFunction, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DiagnosticActorLogging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */