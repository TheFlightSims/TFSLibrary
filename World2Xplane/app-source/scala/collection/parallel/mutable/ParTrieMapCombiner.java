package scala.collection.parallel.mutable;

import scala.Tuple2;
import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0253\001\"\001\002\021\002\007\005!A\003\002\023!\006\024HK]5f\033\006\0048i\\7cS:,'O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003!\001\030M]1mY\026d'BA\004\t\003)\031w\016\0347fGRLwN\034\006\002\023\005)1oY1mCV\0311\"\007\023\024\007\001a\001\003\005\002\016\0355\t\001\"\003\002\020\021\t1\021I\\=SK\032\004B!\005\n\025M5\tA!\003\002\024\t\tA1i\\7cS:,'\017\005\003\016+]\031\023B\001\f\t\005\031!V\017\0357feA\021\001$\007\007\001\t\025Q\002A1\001\035\005\005Y5\001A\t\003;\001\002\"!\004\020\n\005}A!a\002(pi\"Lgn\032\t\003\033\005J!A\t\005\003\007\005s\027\020\005\002\031I\021)Q\005\001b\0019\t\ta\013\005\003(Q]\031S\"\001\002\n\005%\022!A\003)beR\023\030.Z'ba\")1\006\001C\001Y\0051A%\0338ji\022\"\022!\f\t\003\0339J!a\f\005\003\tUs\027\016\036\005\006c\001!\tAM\001\bG>l'-\0338f+\r\031dG\017\013\003iu\002B!\005\n6sA\021\001D\016\003\006oA\022\r\001\017\002\002\035F\021Q\004\006\t\0031i\"Qa\017\031C\002q\022QAT3x)>\f\"A\n\021\t\013y\002\004\031\001\033\002\013=$\b.\032:\t\013\001\003A\021I!\002\027\r\fgNQ3TQ\006\024X\rZ\013\002\005B\021QbQ\005\003\t\"\021qAQ8pY\026\fg\016")
public interface ParTrieMapCombiner<K, V> extends Combiner<Tuple2<K, V>, ParTrieMap<K, V>> {
  <N extends Tuple2<K, V>, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> paramCombiner);
  
  boolean canBeShared();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParTrieMapCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */