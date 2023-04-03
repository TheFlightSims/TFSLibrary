package akka.serialization;

import akka.actor.ActorSystem;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Q:Q!\001\002\t\002\035\tacU3sS\006d\027N_1uS>tW\t\037;f]NLwN\034\006\003\007\021\tQb]3sS\006d\027N_1uS>t'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005Y\031VM]5bY&T\030\r^5p]\026CH/\0328tS>t7\003B\005\r%m\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007cA\n\02715\tAC\003\002\026\t\005)\021m\031;pe&\021q\003\006\002\f\013b$XM\\:j_:LE\r\005\002\t3%\021!D\001\002\016'\026\024\030.\0317ju\006$\030n\0348\021\005Ma\022BA\017\025\005M)\005\020^3og&|g.\0233Qe>4\030\016Z3s\021\025y\022\002\"\001!\003\031a\024N\\5u}Q\tq\001C\003#\023\021\0053%A\002hKR$\"\001\007\023\t\013\025\n\003\031\001\024\002\rML8\017^3n!\t\031r%\003\002))\tY\021i\031;peNK8\017^3n\021\025Q\023\002\"\021,\003\031awn\\6vaR\tAF\004\002\t\001!)a&\003C!_\005y1M]3bi\026,\005\020^3og&|g\016\006\002\031a!)Q%\fa\001cA\0211CM\005\003gQ\0211#\022=uK:$W\rZ!di>\0248+_:uK6\004")
public final class SerializationExtension {
  public static boolean equals(Object paramObject) {
    return SerializationExtension$.MODULE$.equals(paramObject);
  }
  
  public static int hashCode() {
    return SerializationExtension$.MODULE$.hashCode();
  }
  
  public static Extension apply(ActorSystem paramActorSystem) {
    return SerializationExtension$.MODULE$.apply(paramActorSystem);
  }
  
  public static Serialization createExtension(ExtendedActorSystem paramExtendedActorSystem) {
    return SerializationExtension$.MODULE$.createExtension(paramExtendedActorSystem);
  }
  
  public static SerializationExtension$ lookup() {
    return SerializationExtension$.MODULE$.lookup();
  }
  
  public static Serialization get(ActorSystem paramActorSystem) {
    return SerializationExtension$.MODULE$.get(paramActorSystem);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\SerializationExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */