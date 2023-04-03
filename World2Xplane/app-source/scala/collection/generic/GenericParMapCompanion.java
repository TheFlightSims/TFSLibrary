package scala.collection.generic;

import scala.Tuple2;
import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Y2q!\001\002\021\002G\005\021B\001\fHK:,'/[2QCJl\025\r]\"p[B\fg.[8o\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)Y3C\001\001\f!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\005\006!\0011\t!E\001\f]\026<8i\\7cS:,'/F\002\023=!*\022a\005\t\005)]I\"&D\001\026\025\t1B!\001\005qCJ\fG\016\\3m\023\tARC\001\005D_6\024\027N\\3s!\021a!\004H\024\n\005m1!A\002+va2,'\007\005\002\036=1\001A!B\020\020\005\004\001#!\001)\022\005\005\"\003C\001\007#\023\t\031cAA\004O_RD\027N\\4\021\0051)\023B\001\024\007\005\r\te.\037\t\003;!\"Q!K\bC\002\001\022\021!\025\t\005;-br\005\002\004-\001\021\025\r!\f\002\003\007\016+2AL\0326#\t\ts\006\005\003\025aI\"\024BA\031\026\005\031\001\026M]'baB\021Qd\r\003\006?-\022\r\001\t\t\003;U\"Q!K\026C\002\001\002")
public interface GenericParMapCompanion<CC extends scala.collection.parallel.ParMap<Object, Object>> {
  <P, Q> Combiner<Tuple2<P, Q>, CC> newCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericParMapCompanion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */