package scala.collection.parallel.mutable;

import scala.Option;
import scala.Tuple2;
import scala.collection.GenMapLike;
import scala.collection.generic.Growable;
import scala.collection.generic.Shrinkable;
import scala.collection.mutable.Cloneable;
import scala.collection.parallel.ParMapLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001i4q!\001\002\021\002\007\0051B\001\006QCJl\025\r\035'jW\026T!a\001\003\002\0175,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\004\001U)AbF\021%[M9\001!D\t>\001&c\005C\001\b\020\033\005A\021B\001\t\t\005\031\te.\037*fMB)!cE\013!G5\ta!\003\002\025\r\tQq)\0328NCBd\025n[3\021\005Y9B\002\001\003\0061\001\021\r!\007\002\002\027F\021!$\b\t\003\035mI!\001\b\005\003\0179{G\017[5oOB\021aBH\005\003?!\0211!\0218z!\t1\022\005B\003#\001\t\007\021DA\001W!\t1B\005\002\004&\001\021\025\rA\n\002\005%\026\004(/\005\002\033OI\031\001F\013\036\007\t%\002\001a\n\002\ryI,g-\0338f[\026tGO\020\t\007W\001)\002e\t\027\016\003\t\001\"AF\027\005\r9\002AQ1\0010\005)\031V-];f]RL\027\r\\\t\0035A\0222!\r\0328\r\021I\003\001\001\031\021\tM*T\003I\007\002i)\0211AB\005\003mQ\0221!T1q!\025\031\004(\006\021-\023\tIDGA\004NCBd\025n[3\021\t-ZT\003I\005\003y\t\021a\001U1s\033\006\004\bC\002 @+\001\032C&D\001\005\023\t\tA\001E\002B\t\032k\021A\021\006\003\007\032\tqaZ3oKJL7-\003\002F\005\nAqI]8xC\ndW\r\005\003\017\017V\001\023B\001%\t\005\031!V\017\0357feA\031\021IS\013\n\005-\023%AC*ie&t7.\0312mKB\0311'T\022\n\0059#$!C\"m_:,\027M\0317f\021\025\001\006\001\"\001R\003\031!\023N\\5uIQ\t!\013\005\002\017'&\021A\013\003\002\005+:LG\017C\003W\001\031\005q+A\002qkR$2\001W.^!\rq\021\fI\005\0035\"\021aa\0249uS>t\007\"\002/V\001\004)\022aA6fs\")a,\026a\001A\005)a/\0317vK\")\001\r\001D\001C\006AA\005\0357vg\022*\027\017\006\002cG6\t\001\001C\003e?\002\007a)\001\002lm\")a\r\001D\001O\006IA%\\5okN$S-\035\013\003E\"DQ\001X3A\002UAQA\033\001\005\002-\fQ\001\n9mkN,\"\001\\8\025\0055\024\b\003B\026<+9\004\"AF8\005\013AL'\031A9\003\003U\013\"\001I\017\t\013\021L\007\031A:\021\t99UC\034\005\006k\002!\tA^\001\007I5Lg.^:\025\005\r:\b\"\002/u\001\004)\002\"B=\001\r\003\t\026!B2mK\006\024\b")
public interface ParMapLike<K, V, Repr extends ParMapLike<K, V, Repr, Sequential> & ParMap<K, V>, Sequential extends scala.collection.mutable.Map<K, V> & scala.collection.mutable.MapLike<K, V, Sequential>> extends GenMapLike<K, V, Repr>, ParMapLike<K, V, Repr, Sequential>, Growable<Tuple2<K, V>>, Shrinkable<K>, Cloneable<Repr> {
  Option<V> put(K paramK, V paramV);
  
  ParMapLike<K, V, Repr, Sequential> $plus$eq(Tuple2<K, V> paramTuple2);
  
  ParMapLike<K, V, Repr, Sequential> $minus$eq(K paramK);
  
  <U> ParMap<K, U> $plus(Tuple2<K, U> paramTuple2);
  
  Repr $minus(K paramK);
  
  void clear();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParMapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */