package scala.collection.mutable;

import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\001A2q!\001\002\021\002\007\005\021BA\005ICNDWI\034;ss*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQ!dJ\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\021\025\001\002\001\"\001\022\003\031!\023N\\5uIQ\t!\003\005\002\r'%\021AC\002\002\005+:LG\017C\004\027\001\t\007i\021A\f\002\007-,\0270F\001\031!\tI\"\004\004\001\005\013m\001!\031\001\017\003\003\005\013\"!\b\021\021\0051q\022BA\020\007\005\035qu\016\0365j]\036\004\"\001D\021\n\005\t2!aA!os\"IA\005\001a\001\002\004%\t!J\001\005]\026DH/F\001'!\tIr\005B\003)\001\t\007ADA\001F\021%Q\003\0011AA\002\023\0051&\001\005oKb$x\fJ3r)\t\021B\006C\004.S\005\005\t\031\001\024\002\007a$\023\007\003\0040\001\001\006KAJ\001\006]\026DH\017\t")
public interface HashEntry<A, E> {
  A key();
  
  E next();
  
  @TraitSetter
  void next_$eq(E paramE);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */