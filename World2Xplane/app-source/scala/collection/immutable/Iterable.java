package scala.collection.immutable;

import scala.collection.Iterable;
import scala.collection.IterableLike;
import scala.collection.Parallelizable;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.immutable.ParIterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001U4q!\001\002\021\002\007\005\021B\001\005Ji\026\024\030M\0317f\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\b\001-ya$\t\025-!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\002\n\005I\021!a\003+sCZ,'o]1cY\026\004\"\001F\013\r\001\0211a\003\001CC\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020E\002 AMi\021\001B\005\003\003\021\001BAI\023\024O5\t1E\003\002%\t\0059q-\0328fe&\034\027B\001\024$\005i9UM\\3sS\016$&/\031<feN\f'\r\\3UK6\004H.\031;f!\t\001\002\001\005\003 SMY\023B\001\026\005\0051IE/\032:bE2,G*[6f!\r\001\002a\005\t\005?5\032r&\003\002/\t\tq\001+\031:bY2,G.\033>bE2,\007c\001\0315'5\t\021G\003\002\004e)\0211\007B\001\ta\006\024\030\r\0347fY&\021Q'\r\002\f!\006\024\030\n^3sC\ndW\rC\0038\001\021\005\001(\001\004%S:LG\017\n\013\002sA\021ABO\005\003w\031\021A!\0268ji\")Q\b\001C!}\005I1m\\7qC:LwN\\\013\002A\031!\005Q\024\n\005\005\033#\001E$f]\026\024\030nY\"p[B\fg.[8o\021\031\031\005\001)C)\t\006Y\001/\031:D_6\024\027N\\3s+\005)\005\003\002$H'=j\021AM\005\003\021J\022\001bQ8nE&tWM\035\005\006\025\002!\teS\001\004g\026\fX#A\026\b\0135\023\001\022\001(\002\021%#XM]1cY\026\004\"\001E(\007\013\005\021\001\022\001)\024\007=\013F\013E\002#%\036J!aU\022\003+\035+g\016\026:bm\026\0248/\0312mK\032\0137\r^8ssB\031!%V\024\n\005Y\033#A\005+sCZ,'o]1cY\0264\025m\031;pefDQ\001W(\005\002e\013a\001P5oSRtD#\001(\t\013m{E1\001/\002\031\r\fgNQ;jY\0224%o\\7\026\005u3W#\0010\021\013\tz\026-Z4\n\005\001\034#\001D\"b]\n+\030\016\0343Ge>l\007C\0012d\033\005y\025B\0013A\005\021\031u\016\0347\021\005Q1G!\002\f[\005\0049\002c\001\t\001K\")\021n\024C\001U\006Qa.Z<Ck&dG-\032:\026\005-\034X#\0017\021\t5\004(\017^\007\002]*\021q\016B\001\b[V$\030M\0317f\023\t\thNA\004Ck&dG-\032:\021\005Q\031H!\002\fi\005\0049\002c\001\t\001e\002")
public interface Iterable<A> extends Traversable<A>, Iterable<A>, GenericTraversableTemplate<A, Iterable>, IterableLike<A, Iterable<A>>, Parallelizable<A, ParIterable<A>> {
  GenericCompanion<Iterable> companion();
  
  Combiner<A, ParIterable<A>> parCombiner();
  
  Iterable<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Iterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */