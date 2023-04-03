package scala.ref;

import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q2q!\001\002\021\002\007\005qAA\005SK\032,'/\0328dK*\0211\001B\001\004e\0264'\"A\003\002\013M\034\027\r\\1\004\001U\021\001BE\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB\031!B\004\t\n\005=!!!\003$v]\016$\030n\03481!\t\t\"\003\004\001\005\rM\001AQ1\001\025\005\005!\026CA\013\n!\tQa#\003\002\030\t\t9aj\034;iS:<\007\"B\r\001\t\003Q\022A\002\023j]&$H\005F\001\034!\tQA$\003\002\036\t\t!QK\\5u\021\025y\002A\"\001!\003\025\t\007\017\0357z)\005\001\002\"\002\022\001\r\003\031\023aA4fiV\tA\005E\002\013KAI!A\n\003\003\r=\003H/[8o\021\025A\003\001\"\021*\003!!xn\025;sS:<G#\001\026\021\005-\002T\"\001\027\013\0055r\023\001\0027b]\036T\021aL\001\005U\0064\030-\003\0022Y\t11\013\036:j]\036DQa\r\001\007\002i\tQa\0317fCJDQ!\016\001\007\002Y\nq!\0328rk\026,X\rF\0018!\tQ\001(\003\002:\t\t9!i\\8mK\006t\007\"B\036\001\r\0031\024AC5t\013:\fX/Z;fI\002")
public interface Reference<T> extends Function0<T> {
  T apply();
  
  Option<T> get();
  
  String toString();
  
  void clear();
  
  boolean enqueue();
  
  boolean isEnqueued();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\Reference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */