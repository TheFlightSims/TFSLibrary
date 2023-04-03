package scala.collection.parallel.mutable;

import scala.collection.GenSet;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.mutable.Set;
import scala.collection.parallel.ParSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001i4q!\001\002\021\002\007\0051B\001\004QCJ\034V\r\036\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005A\001/\031:bY2,GN\003\002\b\021\005Q1m\0347mK\016$\030n\0348\013\003%\tQa]2bY\006\034\001!\006\002\r/M9\001!D\t!I\035r\003C\001\b\020\033\005A\021B\001\t\t\005\031\te.\037*fMB\031!cE\013\016\003\031I!\001\006\004\003\r\035+gnU3u!\t1r\003\004\001\005\013a\001!\031A\r\003\003Q\013\"AG\017\021\0059Y\022B\001\017\t\005\035qu\016\0365j]\036\004\"A\004\020\n\005}A!aA!osB\031\021EI\013\016\003\tI!a\t\002\003\027A\013'/\023;fe\006\024G.\032\t\004K\031*R\"\001\003\n\005\005!\001\003\002\025,+5j\021!\013\006\003U\031\tqaZ3oKJL7-\003\002-S\t\021r)\0328fe&\034\007+\031:UK6\004H.\031;f!\t\t\003\001E\003\"_U\t$'\003\0021\005\tQ\001+\031:TKRd\025n[3\021\007\005\002Q\003E\0024kUi\021\001\016\006\003\007\031I!A\016\033\003\007M+G\017C\0039\001\021\005\021(\001\004%S:LG\017\n\013\002uA\021abO\005\003y!\021A!\0268ji\")a\b\001C!\005I1m\\7qC:LwN\\\013\002\001J\031\021i\021$\007\t\t\003\001\001\021\002\ryI,g-\0338f[\026tGO\020\t\004Q\021k\023BA#*\005A9UM\\3sS\016\034u.\0349b]&|g\016E\002)\0176J!\001S\025\003'\035+g.\032:jGB\013'oQ8na\006t\027n\0348\t\013)\003A\021I&\002\013\025l\007\017^=\026\003EBQ!\024\001\007\0029\0131a]3r+\005\021t!\002)\003\021\003\t\026A\002)beN+G\017\005\002\"%\032)\021A\001E\001'N\021!\013\026\t\004QUk\023B\001,*\0055\001\026M]*fi\032\0137\r^8ss\")\001L\025C\0013\0061A(\0338jiz\"\022!\025\005\0067J#\031\001X\001\rG\006t')^5mI\032\023x.\\\013\003;\032,\022A\030\t\006Q}\013WmZ\005\003A&\022abQ1o\007>l'-\0338f\rJ|W\016\005\002cG6\t!+\003\002e\t\n!1i\0347m!\t1b\rB\003\0315\n\007\021\004E\002\"\001\025DQ!\033*\005B)\f!B\\3x\005VLG\016Z3s+\tY\007/F\001m!\021)Sn\\9\n\0059$!\001C\"p[\nLg.\032:\021\005Y\001H!\002\ri\005\004I\002cA\021\001_\")1O\025C!i\006Ya.Z<D_6\024\027N\\3s+\t)\b0F\001w!\021)Sn^=\021\005YAH!\002\rs\005\004I\002cA\021\001o\002")
public interface ParSet<T> extends GenSet<T>, ParIterable<T>, ParSet<T>, GenericParTemplate<T, ParSet>, ParSetLike<T, ParSet<T>, Set<T>> {
  GenericCompanion<ParSet> companion();
  
  ParSet<T> empty();
  
  Set<T> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */