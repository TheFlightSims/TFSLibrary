package akka.serialization;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001U3q!\001\002\021\002\007\005qA\001\006TKJL\027\r\\5{KJT!a\001\003\002\033M,'/[1mSj\fG/[8o\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032DQa\004\001\005\002A\ta\001J5oSR$C#A\t\021\005%\021\022BA\n\013\005\021)f.\033;\t\013U\001a\021\001\f\002\025%$WM\034;jM&,'/F\001\030!\tI\001$\003\002\032\025\t\031\021J\034;\t\013m\001a\021\001\017\002\021Q|')\0338bef$\"!H\022\021\007%q\002%\003\002 \025\t)\021I\035:bsB\021\021\"I\005\003E)\021AAQ=uK\")AE\007a\001\021\005\tq\016C\003'\001\031\005q%A\bj]\016dW\017Z3NC:Lg-Z:u+\005A\003CA\005*\023\tQ#BA\004C_>dW-\0318\t\0131\002a\021A\027\002\025\031\024x.\034\"j]\006\024\030\020F\002\t]ABQaL\026A\002u\tQAY=uKNDQ!M\026A\002I\n\001\"\\1oS\032,7\017\036\t\004\023M*\024B\001\033\013\005\031y\005\017^5p]B\022ag\020\t\004oijdBA\0059\023\tI$\"\001\004Qe\026$WMZ\005\003wq\022Qa\0217bgNT!!\017\006\021\005yzD\002\001\003\n\001B\n\t\021!A\003\002\005\0231a\030\0232#\t\021U\t\005\002\n\007&\021AI\003\002\b\035>$\b.\0338h!\tIa)\003\002H\025\t\031\021I\\=\t\0131\002AQA%\025\005!Q\005\"B\030I\001\004i\002\"\002\027\001\t\013aEc\001\005N\035\")qf\023a\001;!)qj\023a\001!\006)1\r\\1{uB\022\021k\025\t\004oi\022\006C\001 T\t%!f*!A\001\002\013\005\021IA\002`II\002")
public interface Serializer {
  int identifier();
  
  byte[] toBinary(Object paramObject);
  
  boolean includeManifest();
  
  Object fromBinary(byte[] paramArrayOfbyte, Option<Class<?>> paramOption);
  
  Object fromBinary(byte[] paramArrayOfbyte);
  
  Object fromBinary(byte[] paramArrayOfbyte, Class<?> paramClass);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\Serializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */