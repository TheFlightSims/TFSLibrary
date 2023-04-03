package akka.actor;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0253q!\001\002\021\002\007\005qAA\006FqR,gn]5p]&#'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001!\006\002\t5M\021\001!\003\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\t\013A\001A\021A\t\002\r\021Jg.\033;%)\005\021\002C\001\006\024\023\t!2B\001\003V]&$\b\"\002\f\001\t\0039\022!B1qa2LHC\001\r%!\tI\"\004\004\001\005\013m\001!\031\001\017\003\003Q\013\"!\b\021\021\005)q\022BA\020\f\005\035qu\016\0365j]\036\004\"!\t\022\016\003\tI!a\t\002\003\023\025CH/\0328tS>t\007\"B\023\026\001\0041\023AB:zgR,W\016\005\002\"O%\021\001F\001\002\f\003\016$xN]*zgR,W\016C\003+\001\021\0051&A\002hKR$\"\001\007\027\t\013\025J\003\031\001\024\t\0139\002a\021A\030\002\037\r\024X-\031;f\013b$XM\\:j_:$\"\001\007\031\t\013\025j\003\031A\031\021\005\005\022\024BA\032\003\005M)\005\020^3oI\026$\027i\031;peNK8\017^3n\021\025)\004\001\"\0227\003!A\027m\0355D_\022,G#A\034\021\005)A\024BA\035\f\005\rIe\016\036\005\006w\001!)\005P\001\007KF,\030\r\\:\025\005u\002\005C\001\006?\023\ty4BA\004C_>dW-\0318\t\013\005S\004\031\001\"\002\013=$\b.\032:\021\005)\031\025B\001#\f\005\r\te.\037")
public interface ExtensionId<T extends Extension> {
  T apply(ActorSystem paramActorSystem);
  
  T get(ActorSystem paramActorSystem);
  
  T createExtension(ExtendedActorSystem paramExtendedActorSystem);
  
  int hashCode();
  
  boolean equals(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ExtensionId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */