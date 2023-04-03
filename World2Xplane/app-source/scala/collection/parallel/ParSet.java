package scala.collection.parallel;

import scala.collection.GenSet;
import scala.collection.Set;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001M4q!\001\002\021\002\007\005\021B\001\004QCJ\034V\r\036\006\003\007\021\t\001\002]1sC2dW\r\034\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025U\031b\001A\006\020=\031J\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB\031\001#E\n\016\003\021I!A\005\003\003\r\035+gnU3u!\t!R\003\004\001\005\013Y\001!\031A\f\003\003Q\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB!qDI\n%\033\005\001#BA\021\005\003\0359WM\\3sS\016L!a\t\021\003%\035+g.\032:jGB\013'\017V3na2\fG/\032\t\003K\001i\021A\001\t\004K\035\032\022B\001\025\003\005-\001\026M]%uKJ\f'\r\\3\021\013\025R3\003L\027\n\005-\022!A\003)beN+G\017T5lKB\031Q\005A\n\021\007Aq3#\003\0020\t\t\0311+\032;\t\013E\002A\021\001\032\002\r\021Jg.\033;%)\005\031\004C\001\0075\023\t)dA\001\003V]&$\b\"B\034\001\t\003B\024!B3naRLX#\001\027\t\013i\002A\021I\036\002\023\r|W\016]1oS>tW#\001\037\023\007uz$I\002\003?\001\001a$\001\004\037sK\032Lg.Z7f]Rt\004cA\020AI%\021\021\t\t\002\021\017\026tWM]5d\007>l\007/\0318j_:\0042aH\"%\023\t!\005EA\nHK:,'/[2QCJ\034u.\0349b]&|g\016C\003G\001\021\005s)\001\007tiJLgn\032)sK\032L\0070F\001I!\tIe*D\001K\025\tYE*\001\003mC:<'\"A'\002\t)\fg/Y\005\003\037*\023aa\025;sS:<w!B)\003\021\003\021\026A\002)beN+G\017\005\002&'\032)\021A\001E\001)N\0211+\026\t\004?Y#\023BA,!\0055\001\026M]*fi\032\0137\r^8ss\")\021l\025C\0015\0061A(\0338jiz\"\022A\025\005\0069N#\t!X\001\f]\026<8i\\7cS:,'/\006\002_GV\tq\f\005\003&A\n$\027BA1\003\005!\031u.\0342j]\026\024\bC\001\013d\t\02512L1\001\030!\r)\003A\031\005\006MN#\031aZ\001\rG\006t')^5mI\032\023x.\\\013\003QF,\022!\033\t\006?)d\007O]\005\003W\002\022abQ1o\007>l'-\0338f\rJ|W\016\005\002n]6\t1+\003\002p\001\n!1i\0347m!\t!\022\017B\003\027K\n\007q\003E\002&\001A\004")
public interface ParSet<T> extends GenSet<T>, GenericParTemplate<T, ParSet>, ParIterable<T>, ParSetLike<T, ParSet<T>, Set<T>> {
  ParSet<T> empty();
  
  GenericCompanion<ParSet> companion();
  
  String stringPrefix();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */