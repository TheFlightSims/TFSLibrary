package scala.collection.mutable;

import scala.collection.Set;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericSetTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m3q!\001\002\021\002\007\005\021BA\002TKRT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\007\001-ya$\t\025\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\002\021#Mi\021AA\005\003%\t\021\001\"\023;fe\006\024G.\032\t\003)Ua\001\001B\003\027\001\t\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\007}\0013#D\001\005\023\t\tA\001\005\003#KM9S\"A\022\013\005\021\"\021aB4f]\026\024\030nY\005\003M\r\022!cR3oKJL7mU3u)\026l\007\017\\1uKB\021\001\003\001\t\005!%\0322&\003\002+\005\t91+\032;MS.,\007c\001\t\001'!)Q\006\001C\001]\0051A%\0338ji\022\"\022a\f\t\003\031AJ!!\r\004\003\tUs\027\016\036\005\006g\001!\t\005N\001\nG>l\007/\0318j_:,\022!\016\t\004EY:\023BA\034$\005A9UM\\3sS\016\034u.\0349b]&|g\016C\003:\001\021\005#(A\002tKF,\022aK\004\006y\tA\t!P\001\004'\026$\bC\001\t?\r\025\t!\001#\001@'\tq\004\tE\002#\003\036J!AQ\022\003#5+H/\0312mKN+GOR1di>\024\030\020C\003E}\021\005Q)\001\004=S:LGO\020\013\002{!)qI\020C\002\021\006a1-\0318Ck&dGM\022:p[V\021\021JU\013\002\025B)!eS'R'&\021Aj\t\002\r\007\006t')^5mI\032\023x.\034\t\003\035>k\021AP\005\003!Z\022AaQ8mYB\021AC\025\003\006-\031\023\ra\006\t\004!\001\t\006\"B+?\t\0032\026!B3naRLXCA,[+\005A\006c\001\t\0013B\021AC\027\003\006-Q\023\ra\006")
public interface Set<A> extends Iterable<A>, Set<A>, GenericSetTemplate<A, Set>, SetLike<A, Set<A>> {
  GenericCompanion<Set> companion();
  
  Set<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Set.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */