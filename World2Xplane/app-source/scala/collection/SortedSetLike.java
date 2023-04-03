package scala.collection;

import scala.Option;
import scala.collection.generic.Sorted;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001E4q!\001\002\021\002\007\005qAA\007T_J$X\rZ*fi2K7.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\004\021Uy2\003\002\001\n\033)\002\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\021q\021c\005\020\016\003=Q!\001\005\002\002\017\035,g.\032:jG&\021!c\004\002\007'>\024H/\0323\021\005Q)B\002\001\003\006-\001\021\ra\006\002\002\003F\021\001d\007\t\003\025eI!A\007\003\003\0179{G\017[5oOB\021!\002H\005\003;\021\0211!\0218z!\t!r\004\002\004!\001\021\025\r!\t\002\005)\"L7/\005\002\031EI\0311%J\025\007\t\021\002\001A\t\002\ryI,g-\0338f[\026tGO\020\t\004M\035\032R\"\001\002\n\005!\022!!C*peR,GmU3u!\0211\003a\005\020\021\t\031Z3CH\005\003Y\t\021qaU3u\031&\\W\rC\003/\001\021\005q&\001\004%S:LG\017\n\013\002aA\021!\"M\005\003e\021\021A!\0268ji\")A\007\001D\002k\005AqN\0353fe&tw-F\0017!\r9th\005\b\003qur!!\017\037\016\003iR!a\017\004\002\rq\022xn\034;?\023\005)\021B\001 \005\003\035\001\030mY6bO\026L!\001Q!\003\021=\023H-\032:j]\036T!A\020\003\t\013\r\003A\021\t#\002\r-,\027pU3u+\005q\002\"\002$\001\t\003:\025\001\0034jeN$8*Z=\026\003MAQ!\023\001\005B\035\013q\001\\1ti.+\027\020C\003L\001\031\005A*A\005sC:<W-S7qYR\031a$\024*\t\0139S\005\031A(\002\t\031\024x.\034\t\004\025A\033\022BA)\005\005\031y\005\017^5p]\")1K\023a\001\037\006)QO\034;jY\")a\n\001C!+R\021aD\026\005\006\035R\003\ra\005\005\006'\002!\t\005\027\013\003=eCQaU,A\002MAQa\027\001\005Bq\013QA]1oO\026$2AH/_\021\025q%\f1\001\024\021\025\031&\f1\001\024\021\025\001\007\001\"\021b\003!\031XOY:fi>3GC\0012f!\tQ1-\003\002e\t\t9!i\\8mK\006t\007\"\0024`\001\0049\027\001\002;iCR\0042A\n5\024\023\tI'A\001\004HK:\034V\r\036\005\nW\002\t\t\021!C\005Y:\fab];qKJ$3/\0362tKR|e\r\006\002c[\")aM\033a\001O&\021\001m\\\005\003a\n\021!bR3o'\026$H*[6f\001")
public interface SortedSetLike<A, This extends SortedSet<A> & SortedSetLike<A, This>> extends Sorted<A, This>, SetLike<A, This> {
  boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet<A> paramGenSet);
  
  Ordering<A> ordering();
  
  This keySet();
  
  A firstKey();
  
  A lastKey();
  
  This rangeImpl(Option<A> paramOption1, Option<A> paramOption2);
  
  This from(A paramA);
  
  This until(A paramA);
  
  This range(A paramA1, A paramA2);
  
  boolean subsetOf(GenSet<A> paramGenSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedSetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */