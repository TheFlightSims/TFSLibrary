package scala.collection;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001I2q!\001\002\021\002\007\005qA\001\013DkN$x.\034)be\006dG.\0327ju\006\024G.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\004\021MQ2c\001\001\n\033A\021!bC\007\002\t%\021A\002\002\002\004\003:L\b\003\002\b\020#ei\021AA\005\003!\t\021a\002U1sC2dW\r\\5{C\ndW\r\005\002\023'1\001AA\002\013\001\t\013\007QCA\001B#\t1\022\002\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h!\t\021\"\004\002\004\034\001\021\025\r\001\b\002\b!\006\024(+\0329s#\t1R\004\005\002\017=%\021qD\001\002\t!\006\024\030\r\0347fY\")\021\005\001C\001E\0051A%\0338ji\022\"\022a\t\t\003\025\021J!!\n\003\003\tUs\027\016\036\005\006O\0011\t\005K\001\004a\006\024X#A\r\t\r)\002\001\025\"\025,\003-\001\030M]\"p[\nLg.\032:\026\0031\002B!\f\031\02235\taF\003\0020\005\005A\001/\031:bY2,G.\003\0022]\tA1i\\7cS:,'\017")
public interface CustomParallelizable<A, ParRepr extends Parallel> extends Parallelizable<A, ParRepr> {
  ParRepr par();
  
  Combiner<A, ParRepr> parCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\CustomParallelizable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */