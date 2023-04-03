package akka.event;

import java.util.Map;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\00193q!\001\002\021\002\007\005qA\001\rES\006<gn\\:uS\016dunZ4j]\036\fE-\0319uKJT!a\001\003\002\013\0254XM\034;\013\003\025\tA!Y6lC\016\0011c\001\001\t\035A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\0351{wmZ5oO\006#\027\r\035;fe\")1\003\001C\001)\0051A%\0338ji\022\"\022!\006\t\003\023YI!a\006\006\003\tUs\027\016\036\005\b3\001\001\r\021\"\003\033\003\021yV\016Z2\026\003m\001\"\001H\020\017\005=i\022B\001\020\003\003\035aunZ4j]\036L!\001I\021\003\0075#5I\003\002\037\005!91\005\001a\001\n\023!\023\001C0nI\016|F%Z9\025\005U)\003b\002\024#\003\003\005\raG\001\004q\022\n\004B\002\025\001A\003&1$A\003`[\022\034\007\005C\003+\001\021\0053&A\002nI\016,\022\001\f\t\003[9j\021\001A\005\003AAAQA\013\001\005\002A\"\"!F\031\t\013)z\003\031\001\027\t\013M\002A\021\001\033\002\r\035,G/\024#D+\005)\004\003\002\034<{\021k\021a\016\006\003qe\nA!\036;jY*\t!(\001\003kCZ\f\027B\001\0378\005\ri\025\r\035\t\003}\005s!!C \n\005\001S\021A\002)sK\022,g-\003\002C\007\n11\013\036:j]\036T!\001\021\006\021\005%)\025B\001$\013\005\r\te.\037\005\006\021\002!\t!S\001\007g\026$X\nR\"\025\005UQ\005\"B&H\001\004)\024\001\0026NI\016DQ!\024\001\005\002Q\t\001b\0317fCJlEi\021")
public interface DiagnosticLoggingAdapter extends LoggingAdapter {
  Map<String, Object> akka$event$DiagnosticLoggingAdapter$$_mdc();
  
  @TraitSetter
  void akka$event$DiagnosticLoggingAdapter$$_mdc_$eq(Map<String, Object> paramMap);
  
  Map<String, Object> mdc();
  
  void mdc(Map<String, Object> paramMap);
  
  Map<String, Object> getMDC();
  
  void setMDC(Map<String, Object> paramMap);
  
  void clearMDC();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\DiagnosticLoggingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */