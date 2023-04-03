package scala.collection.parallel;

import scala.collection.GenIterable;
import scala.collection.Iterable;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y4q!\001\002\021\002\007\005\021BA\006QCJLE/\032:bE2,'BA\002\005\003!\001\030M]1mY\026d'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\026'\025\0011b\004\020'!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\003\n\005I!!aC$f]&#XM]1cY\026\004\"\001F\013\r\001\0211a\003\001CC\002]\021\021\001V\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\003 EM!S\"\001\021\013\005\005\"\021aB4f]\026\024\030nY\005\003G\001\022!cR3oKJL7\rU1s)\026l\007\017\\1uKB\021Q\005A\007\002\005A)QeJ\n*U%\021\001F\001\002\020!\006\024\030\n^3sC\ndW\rT5lKB\031Q\005A\n\021\007-\0324C\004\002-c9\021Q\006M\007\002])\021q\006C\001\007yI|w\016\036 \n\003\035I!A\r\004\002\017A\f7m[1hK&\021A'\016\002\t\023R,'/\0312mK*\021!G\002\005\006o\001!\t\001O\001\007I%t\027\016\036\023\025\003e\002\"\001\004\036\n\005m2!\001B+oSRDQ!\020\001\005By\n\021bY8na\006t\027n\0348\026\003}\0222\001\021\"F\r\021\t\005\001A \003\031q\022XMZ5oK6,g\016\036 \021\007}\031E%\003\002EA\t\001r)\0328fe&\0347i\\7qC:LwN\034\t\004?\031#\023BA$!\005M9UM\\3sS\016\004\026M]\"p[B\fg.[8o\021\025I\005\001\"\001K\0031\031HO]5oOB\023XMZ5y+\005Y\005C\001'R\033\005i%B\001(P\003\021a\027M\\4\013\003A\013AA[1wC&\021!+\024\002\007'R\024\030N\\4\b\013Q\023\001\022A+\002\027A\013'/\023;fe\006\024G.\032\t\003KY3Q!\001\002\t\002]\033\"A\026-\021\007}IF%\003\002[A\tQ\001+\031:GC\016$xN]=\t\013q3F\021A/\002\rqJg.\033;?)\005)\006\"B0W\t\007\001\027\001D2b]\n+\030\016\0343Ge>lWCA1k+\005\021\007#B\020dK&\\\027B\0013!\0059\031\025M\\\"p[\nLg.\032$s_6\004\"AZ4\016\003YK!\001[\"\003\t\r{G\016\034\t\003))$QA\0060C\002]\0012!\n\001j\021\025ig\013\"\001o\003)qWm\036\"vS2$WM]\013\003_R,\022\001\035\t\005KE\034X/\003\002s\005\tA1i\\7cS:,'\017\005\002\025i\022)a\003\034b\001/A\031Q\005A:\t\013]4F\021\001=\002\0279,woQ8nE&tWM]\013\003sr,\022A\037\t\005KE\\X\020\005\002\025y\022)aC\036b\001/A\031Q\005A>")
public interface ParIterable<T> extends GenIterable<T>, GenericParTemplate<T, ParIterable>, ParIterableLike<T, ParIterable<T>, Iterable<T>> {
  GenericCompanion<ParIterable> companion();
  
  String stringPrefix();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */