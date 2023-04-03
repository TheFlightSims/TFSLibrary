package scala.collection.parallel.mutable;

import scala.collection.GenSetLike;
import scala.collection.generic.Growable;
import scala.collection.generic.Shrinkable;
import scala.collection.mutable.Cloneable;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParSetLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0354q!\001\002\021\002\007\0051B\001\006QCJ\034V\r\036'jW\026T!a\001\003\002\0175,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\004\001U!AbF\021+'!\001Q\"\005\036?\001\032K\005C\001\b\020\033\005A\021B\001\t\t\005\031\te.\037*fMB!!cE\013!\033\0051\021B\001\013\007\005)9UM\\*fi2K7.\032\t\003-]a\001\001B\003\031\001\t\007\021DA\001U#\tQR\004\005\002\0177%\021A\004\003\002\b\035>$\b.\0338h!\tqa$\003\002 \021\t\031\021I\\=\021\005Y\tCA\002\022\001\t\013\0071E\001\003SKB\024\030C\001\016%%\r)se\016\004\005M\001\001AE\001\007=e\0264\027N\\3nK:$h\bE\003)\001U\001\023&D\001\003!\t1\"\006\002\004,\001\021\025\r\001\f\002\013'\026\fX/\0328uS\006d\027C\001\016.%\rqs\006\016\004\005M\001\001Q\006E\0021eUi\021!\r\006\003\007\031I!aM\031\003\007M+G\017\005\0031kUI\023B\001\0342\005\035\031V\r\036'jW\026\0042\001\013\035\026\023\tI$A\001\004QCJ\034V\r\036\t\006wq*\002%K\007\002\t%\021Q\b\002\002\020!\006\024\030\n^3sC\ndW\rT5lKB)1hP\013!S%\021\021\001\002\t\004\003\022+R\"\001\"\013\005\r3\021aB4f]\026\024\030nY\005\003\013\n\023\001b\022:po\006\024G.\032\t\004\003\036+\022B\001%C\005)\031\006N]5oW\006\024G.\032\t\004a)\003\023BA&2\005%\031En\0348fC\ndW\rC\003N\001\021\005a*\001\004%S:LG\017\n\013\002\037B\021a\002U\005\003#\"\021A!\0268ji\")1\013\001D!)\006)Q-\0349usV\t\001\005C\003W\001\031\005q+\001\005%a2,8\017J3r)\tA\026,D\001\001\021\025QV\0131\001\026\003\021)G.Z7\t\013q\003a\021A/\002\023\021j\027N\\;tI\025\fHC\001-_\021\025Q6\f1\001\026\021\025\001\007\001\"\001b\003\025!\003\017\\;t)\t\001#\rC\003[?\002\007Q\003C\003e\001\021\005Q-\001\004%[&tWo\035\013\003A\031DQAW2A\002U\001")
public interface ParSetLike<T, Repr extends ParSetLike<T, Repr, Sequential> & ParSet<T>, Sequential extends scala.collection.mutable.Set<T> & scala.collection.mutable.SetLike<T, Sequential>> extends GenSetLike<T, Repr>, ParIterableLike<T, Repr, Sequential>, ParSetLike<T, Repr, Sequential>, Growable<T>, Shrinkable<T>, Cloneable<Repr> {
  Repr empty();
  
  ParSetLike<T, Repr, Sequential> $plus$eq(T paramT);
  
  ParSetLike<T, Repr, Sequential> $minus$eq(T paramT);
  
  Repr $plus(T paramT);
  
  Repr $minus(T paramT);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */