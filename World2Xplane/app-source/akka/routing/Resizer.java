package akka.routing;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00152q!\001\002\021\002G\005qAA\004SKNL'0\032:\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\031\"\001\001\005\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g\021\025y\001A\"\001\021\003=I7\017V5nK\032{'OU3tSj,GCA\t\025!\tI!#\003\002\024\025\t9!i\\8mK\006t\007\"B\013\017\001\0041\022AD7fgN\fw-Z\"pk:$XM\035\t\003\023]I!\001\007\006\003\t1{gn\032\005\0065\0011\taG\001\007e\026\034\030N_3\025\005qy\002CA\005\036\023\tq\"BA\002J]RDQ\001I\rA\002\005\nabY;se\026tGOU8vi\026,7\017E\002#O%j\021a\t\006\003I\025\n\021\"[7nkR\f'\r\\3\013\005\031R\021AC2pY2,7\r^5p]&\021\001f\t\002\013\023:$W\r_3e'\026\f\bC\001\026,\033\005\021\021B\001\027\003\005\031\021v.\036;fK\002")
public interface Resizer {
  boolean isTimeForResize(long paramLong);
  
  int resize(IndexedSeq<Routee> paramIndexedSeq);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Resizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */