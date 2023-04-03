package scala.collection.mutable;

import scala.collection.SortedSet;
import scala.collection.SortedSetLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0314q!\001\002\021\002\007\005\021BA\005T_J$X\rZ*fi*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQAc\005\004\001\027=i\"%\n\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022%5\tA!\003\002\002\tA\0211\003\006\007\001\t\025)\002A1\001\027\005\005\t\025CA\f\033!\ta\001$\003\002\032\r\t9aj\034;iS:<\007C\001\007\034\023\tabAA\002B]f\004B\001\005\020\023A%\021q\004\002\002\016'>\024H/\0323TKRd\025n[3\021\007\005\002!#D\001\003!\r\t3EE\005\003I\t\0211aU3u!\021\tcE\005\021\n\005\035\022!aB*fi2K7.\032\005\006S\001!\tAK\001\007I%t\027\016\036\023\025\003-\002\"\001\004\027\n\00552!\001B+oSRDQa\f\001\005BA\nQ!Z7qif,\022\001I\004\006e\tA\taM\001\n'>\024H/\0323TKR\004\"!\t\033\007\013\005\021\001\022A\033\024\005Q2\004cA\034;y5\t\001H\003\002:\t\0059q-\0328fe&\034\027BA\0369\005]iU\017^1cY\026\034vN\035;fIN+GOR1di>\024\030\020\005\002\"\001!)a\b\016C\001\0051A(\0338jiz\"\022a\r\005\006\003R\"\031AQ\001\rG\006t')^5mI\032\023x.\\\013\003\007:#\"\001\022)\021\013]*u)T(\n\005\031C$\001D\"b]\n+\030\016\0343Ge>l\007C\001%J\033\005!\024B\001&L\005\021\031u\016\0347\n\0051C$\001E*peR,GmU3u\r\006\034Go\034:z!\t\031b\nB\003\026\001\n\007a\003E\002\"\0015CQ!\025!A\004I\0131a\034:e!\r\0316,\024\b\003)fs!!\026-\016\003YS!a\026\005\002\rq\022xn\034;?\023\0059\021B\001.\007\003\035\001\030mY6bO\026L!\001X/\003\021=\023H-\032:j]\036T!A\027\004\t\013=\"D\021A0\026\005\001\034GCA1e!\r\t\003A\031\t\003'\r$Q!\0060C\002YAQ!\0250A\004\025\0042aU.c\001")
public interface SortedSet<A> extends SortedSet<A>, SortedSetLike<A, SortedSet<A>>, Set<A>, SetLike<A, SortedSet<A>> {
  SortedSet<A> empty();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SortedSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */