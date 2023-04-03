package scala.reflect;

import scala.Equals;
import scala.collection.immutable.List;

@ScalaSignature(bytes = "\006\001y3q!\001\002\021\002\007\005qA\001\005NC:Lg-Z:u\025\t\031A!A\004sK\032dWm\031;\013\003\025\tQa]2bY\006\034\001!\006\002\t/M!\001!C\007!!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\004\035I)bBA\b\021\033\005\021\021BA\t\003\003\035\001\030mY6bO\026L!a\005\013\003\033\rc\027m]:NC:Lg-Z:u\025\t\t\"\001\005\002\027/1\001A!\002\r\001\005\004I\"!\001+\022\005ii\002C\001\006\034\023\taBAA\004O_RD\027N\\4\021\005)q\022BA\020\005\005\r\te.\037\t\003\025\005J!A\t\003\003\r\025\013X/\0317t\021\025!\003\001\"\001&\003\031!\023N\\5uIQ\ta\005\005\002\013O%\021\001\006\002\002\005+:LG\017C\003+\001\021\0053&A\007usB,\027I]4v[\026tGo]\013\002YA\031Q\006N\034\017\0059\032dBA\0303\033\005\001$BA\031\007\003\031a$o\\8u}%\tQ!\003\002\022\t%\021QG\016\002\005\031&\034HO\003\002\022\tA\022\001H\017\t\004\037\001I\004C\001\f;\t%Y\024&!A\001\002\013\005\021DA\002`IEBQ!\020\001\005By\nQ\"\031:sCfl\025M\\5gKN$X#A \021\007=\001\001\tE\002\013\003VI!A\021\003\003\013\005\023(/Y=\t\013\021\003A\021I#\002\021\r\fg.R9vC2$\"AR%\021\005)9\025B\001%\005\005\035\021un\0347fC:DQAS\"A\002u\tA\001\0365bi\")A\n\001C!\033\0061Q-];bYN$\"A\022(\t\013)[\005\031A\017\t\013A\003A\021I)\002\021!\f7\017[\"pI\026$\022A\025\t\003\025MK!\001\026\003\003\007%sG\017K\002\001-r\003\"a\026.\016\003aS!!\027\003\002\025\005tgn\034;bi&|g.\003\002\\1\n\001\022.\0349mS\016LGOT8u\r>,h\016Z\021\002;\006ybj\034\021NC:Lg-Z:uA\0054\030-\0337bE2,\007EZ8sA\021ZH+ \030")
public interface Manifest<T> extends ClassTag<T>, Equals {
  List<Manifest<?>> typeArguments();
  
  Manifest<Object> arrayManifest();
  
  boolean canEqual(Object paramObject);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\Manifest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */