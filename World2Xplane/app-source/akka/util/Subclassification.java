package akka.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001%2q!\001\002\021\002G\005qAA\tTk\n\034G.Y:tS\032L7-\031;j_:T!a\001\003\002\tU$\030\016\034\006\002\013\005!\021m[6b\007\001)\"\001C\r\024\005\001I\001C\001\006\016\033\005Y!\"\001\007\002\013M\034\027\r\\1\n\0059Y!AB!osJ+g\rC\003\021\001\031\005\021#A\004jg\026\013X/\0317\025\007I)\"\005\005\002\013'%\021Ac\003\002\b\005>|G.Z1o\021\0251r\0021\001\030\003\005A\bC\001\r\032\031\001!QA\007\001C\002m\021\021aS\t\0039}\001\"AC\017\n\005yY!a\002(pi\"Lgn\032\t\003\025\001J!!I\006\003\007\005s\027\020C\003$\037\001\007q#A\001z\021\025)\003A\"\001'\003)I7oU;cG2\f7o\035\013\004%\035B\003\"\002\f%\001\0049\002\"B\022%\001\0049\002")
public interface Subclassification<K> {
  boolean isEqual(K paramK1, K paramK2);
  
  boolean isSubclass(K paramK1, K paramK2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Subclassification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */