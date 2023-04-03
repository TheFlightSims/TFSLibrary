package scala.collection.generic;

import scala.Tuple2;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001a3q!\001\002\021\002\007\005\021BA\013HK:,'/[2QCJl\025\r\035+f[Bd\027\r^3\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\005\025a\021sgE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\023\016\003\tI!A\005\002\003%\035+g.\032:jGB\013'\017V3na2\fG/\032\t\005\031Q1\022%\003\002\026\r\t1A+\0369mKJ\002\"a\006\r\r\001\021)\021\004\001b\0015\t\t1*\005\002\034=A\021A\002H\005\003;\031\021qAT8uQ&tw\r\005\002\r?%\021\001E\002\002\004\003:L\bCA\f#\t\031\031\003\001\"b\0015\t\ta\013\005\002&Q5\taE\003\002(\t\005A\001/\031:bY2,G.\003\002*M\tY\001+\031:Ji\026\024\030M\0317f\021\025Y\003\001\"\001-\003\031!\023N\\5uIQ\tQ\006\005\002\r]%\021qF\002\002\005+:LG\017\003\0042\001\001&\tFM\001\f]\026<8i\\7cS:,'/F\0014!\021)Cg\005\034\n\005U2#\001C\"p[\nLg.\032:\021\t]9d#\t\003\007q\001!)\031A\035\003\005\r\033Uc\001\036@\005F\0211d\017\t\005Kqr\024)\003\002>M\t1\001+\031:NCB\004\"aF \005\013\001;$\031\001\016\003\003a\003\"a\006\"\005\013\r;$\031\001\016\003\003eCQ!\022\001\007\002\031\013A\"\\1q\007>l\007/\0318j_:,\022a\022\t\004!!S\025BA%\003\005Y9UM\\3sS\016\004\026M]'ba\016{W\016]1oS>t\007CA\f8\021\025a\005\001\"\001N\003I9WM\\3sS\016l\025\r]\"p[\nLg.\032:\026\0079\023V+F\001P!\021)C\007U,\021\t1!\022\013\026\t\003/I#QaU&C\002i\021\021\001\025\t\003/U#QAV&C\002i\021\021!\025\t\005/]\nF\013")
public interface GenericParMapTemplate<K, V, CC extends scala.collection.parallel.ParMap<Object, Object>> extends GenericParTemplate<Tuple2<K, V>, ParIterable> {
  Combiner<Tuple2<K, V>, CC> newCombiner();
  
  GenericParMapCompanion<CC> mapCompanion();
  
  <P, Q> Combiner<Tuple2<P, Q>, CC> genericMapCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericParMapTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */