package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.collection.parallel.mutable.ParArray;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q3q!\001\002\021\002\007\005\021B\001\bUe\0064XM]:bE2,w\n]:\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)a4C\001\001\f!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\005\006!\001!\t!E\001\007I%t\027\016\036\023\025\003I\001\"\001D\n\n\005Q1!\001B+oSR4qA\006\001\021\002G\005qCA\005Pi\",'o^5tKV\021\001DH\n\003+-AQAG\013\007\002m\t\021b\034;iKJ<\030n]3\025\005q9\003CA\017\037\031\001!QaH\013C\002\001\022\021AU\t\003C\021\002\"\001\004\022\n\005\r2!a\002(pi\"Lgn\032\t\003\031\025J!A\n\004\003\007\005s\027\020\003\004)3\021\005\r!K\001\b]>$(m\0343z!\ra!\006H\005\003W\031\021\001\002\0202z]\006lWM\020\005\006[\0011\tAL\001\013SN\004\026M]1mY\026dW#A\030\021\0051\001\024BA\031\007\005\035\021un\0347fC:DQa\r\001\007\0029\nQ\"[:QCJLE/\032:bE2,\007\"B\033\001\r\0031\024!D1t!\006\024\030\n^3sC\ndW-F\0018!\rA\024hO\007\002\005%\021!H\001\002\f!\006\024\030\n^3sC\ndW\r\005\002\036y\021)Q\b\001b\001A\t\tA\013C\003@\001\031\005a&\001\005jgB\013'oU3r\021\025\t\005A\"\001C\003!\t7\017U1s'\026\fX#A\"\021\007a\"5(\003\002F\005\t1\001+\031:TKFDQa\022\001\007\002!\013\001\"\0334QCJ\034V-]\013\003\0236#\"A\023(\021\007-+B*D\001\001!\tiR\nB\003 \r\n\007\001\005C\003P\r\002\007\001+\001\004jg\n|G-\037\t\005\031E\033E*\003\002S\r\tIa)\0368di&|g.\r\005\006)\0021\t!V\001\013i>\004\026M]!se\006LX#\001,\021\007]S6(D\001Y\025\tI&!A\004nkR\f'\r\\3\n\005mC&\001\003)be\006\023(/Y=")
public interface TraversableOps<T> {
  boolean isParallel();
  
  boolean isParIterable();
  
  ParIterable<T> asParIterable();
  
  boolean isParSeq();
  
  ParSeq<T> asParSeq();
  
  <R> Otherwise<R> ifParSeq(Function1<ParSeq<T>, R> paramFunction1);
  
  ParArray<T> toParArray();
  
  public interface Otherwise<R> {
    R otherwise(Function0<R> param1Function0);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\TraversableOps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */