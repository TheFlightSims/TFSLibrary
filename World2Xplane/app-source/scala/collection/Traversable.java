package scala.collection;

import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001=4q!\001\002\021\002\007\005qAA\006Ue\0064XM]:bE2,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001QC\001\005\024'\031\001\021\"D\017!GA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\t9y\021\003H\007\002\005%\021\001C\001\002\020)J\fg/\032:tC\ndW\rT5lKB\021!c\005\007\001\t\031!\002\001\"b\001+\t\t\021)\005\002\0273A\021!bF\005\0031\021\021qAT8uQ&tw\r\005\002\0135%\0211\004\002\002\004\003:L\bc\001\b\001#A\031aBH\t\n\005}\021!AD$f]R\023\030M^3sg\006\024G.\032\t\004\035\005\n\022B\001\022\003\005=!&/\031<feN\f'\r\\3P]\016,\007\003\002\023(#%j\021!\n\006\003M\t\tqaZ3oKJL7-\003\002)K\tQr)\0328fe&\034GK]1wKJ\034\030M\0317f)\026l\007\017\\1uKB\021a\002\001\005\006W\001!\t\001L\001\007I%t\027\016\036\023\025\0035\002\"A\003\030\n\005=\"!\001B+oSRDQ!\r\001\005BI\n\021bY8na\006t\027n\0348\026\003M\0022\001\n\033*\023\t)TE\001\tHK:,'/[2D_6\004\030M\\5p]\")q\007\001C!q\005\0311/Z9\026\003q9QA\017\002\t\002m\n1\002\026:bm\026\0248/\0312mKB\021a\002\020\004\006\003\tA\t!P\n\004yy\n\005c\001\023@S%\021\001)\n\002\026\017\026tGK]1wKJ\034\030M\0317f\r\006\034Go\034:z!\r!#)K\005\003\007\026\022!\003\026:bm\026\0248/\0312mK\032\0137\r^8ss\")Q\t\020C\001\r\0061A(\0338jiz\"\022a\017\005\t\021r\022\r\021\"\001\003\023\0061!M]3bWN,\022A\023\t\003\027Bk\021\001\024\006\003\033:\013qaY8oiJ|GN\003\002P\t\005!Q\017^5m\023\t\tFJ\001\004Ce\026\f7n\035\005\007'r\002\013\021\002&\002\017\t\024X-Y6tA!)Q\013\020C\002-\006a1-\0318Ck&dGM\022:p[V\021q\013Y\013\0021B)A%W.`C&\021!,\n\002\r\007\006t')^5mI\032\023x.\034\t\0039vk\021\001P\005\003=R\022AaQ8mYB\021!\003\031\003\006)Q\023\r!\006\t\004\035\001y\006\"B2=\t\003!\027A\0038fo\n+\030\016\0343feV\021Q-\\\013\002MB!qM\0337o\033\005A'BA5\003\003\035iW\017^1cY\026L!a\0335\003\017\t+\030\016\0343feB\021!#\034\003\006)\t\024\r!\006\t\004\035\001a\007")
public interface Traversable<A> extends TraversableLike<A, Traversable<A>>, GenTraversable<A>, TraversableOnce<A>, GenericTraversableTemplate<A, Traversable> {
  GenericCompanion<Traversable> companion();
  
  Traversable<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Traversable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */