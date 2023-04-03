package scala;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0313q!\001\002\021\002\007\005QAA\003Qe>D\030PC\001\004\003\025\0318-\0317b\007\001\031\"\001\001\004\021\005\035AQ\"\001\002\n\005%\021!aA!os\")1\002\001C\001\031\0051A%\0338ji\022\"\022!\004\t\003\0179I!a\004\002\003\tUs\027\016\036\005\006#\0011\tAE\001\005g\026dg-F\001\007\021\025!\002\001\"\021\026\003!A\027m\0355D_\022,G#\001\f\021\005\0359\022B\001\r\003\005\rIe\016\036\005\0065\001!\teG\001\007KF,\030\r\\:\025\005qy\002CA\004\036\023\tq\"AA\004C_>dW-\0318\t\013\001J\002\031\001\004\002\tQD\027\r\036\005\006E\001!\teI\001\ti>\034FO]5oOR\tA\005\005\002&U5\taE\003\002(Q\005!A.\0318h\025\005I\023\001\0026bm\006L!a\013\024\003\rM#(/\0338h\017\025i#\001#\001/\003\025\001&o\034=z!\t9qFB\003\002\005!\005\001g\005\0020cA\021qAM\005\003g\t\021a!\0218z%\0264\007\"B\0330\t\0031\024A\002\037j]&$h\bF\001/\r\035At\006%A\022\002e\022Q\001V=qK\022,\"A\017!\024\007]21\b\005\002\b\001!)\021c\016D\001{U\ta\b\005\002@\0012\001A!B!8\005\004\021%!\001+\022\005\r3\001CA\004E\023\t)%AA\004O_RD\027N\\4")
public interface Proxy {
  Object self();
  
  int hashCode();
  
  boolean equals(Object paramObject);
  
  String toString();
  
  public static interface Typed<T> extends Proxy {
    T self();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */