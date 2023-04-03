package scala.collection.generic;

import scala.collection.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m2Q!\001\002\002\002%\021!\"T1q\r\006\034Go\034:z\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t2C\001\001\f!\raQbD\007\002\005%\021aB\001\002\016\017\026tW*\0319GC\016$xN]=\021\005A\tB\002\001\003\006%\001\021\ra\005\002\003\007\016+2\001F\021)#\t)\022\004\005\002\027/5\ta!\003\002\031\r\t9aj\034;iS:<'c\001\016\035U\031!1\004\001\001\032\0051a$/\0324j]\026lWM\034;?!\021ib\004I\024\016\003\021I!a\b\003\003\0075\013\007\017\005\002\021C\021)!%\005b\001G\t\t\021)\005\002\026IA\021a#J\005\003M\031\0211!\0218z!\t\001\002\006B\003*#\t\0071EA\001C!\025i2\006I\024.\023\taCAA\004NCBd\025n[3\021\tA\t\002e\n\005\006_\001!\t\001M\001\007y%t\027\016\036 \025\003E\0022\001\004\001\020\021\025\031\004A\"\0015\003\025)W\016\035;z+\r)\004HO\013\002mA!\001#E\034:!\t\001\002\bB\003#e\t\0071\005\005\002\021u\021)\021F\rb\001G\001")
public abstract class MapFactory<CC extends Map<Object, Object>> extends GenMapFactory<CC> {
  public abstract <A, B> CC empty();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\MapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */