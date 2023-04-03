package scala.parallel;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001A2q!\001\002\021\002G\005qA\001\004GkR,(/\032\006\003\007\021\t\001\002]1sC2dW\r\034\006\002\013\005)1oY1mC\016\001QC\001\005\023'\r\001\021\"\004\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\006\017!%\021q\002\002\002\n\rVt7\r^5p]B\002\"!\005\n\r\001\021I1\003\001Q\001\002\023\025\r\001\006\002\002%F\021Q\003\007\t\003\025YI!a\006\003\003\0179{G\017[5oOB\021!\"G\005\0035\021\0211!\0218zQ\t\021B\004\005\002\013;%\021a\004\002\002\fgB,7-[1mSj,G\rC\003!\001\031\005\021%A\003baBd\027\020F\001\021\021\025\031\003A\"\001%\003\031I7\017R8oKR\tQ\005\005\002\013M%\021q\005\002\002\b\005>|G.Z1oQ\021\001\021\006\f\030\021\005)Q\023BA\026\005\005)!W\r\035:fG\006$X\rZ\021\002[\0051Sk]3!AN\034\027\r\\1/G>t7-\036:sK:$hFR;ukJ,\007\rI5ogR,\027\r\032\030\"\003=\naA\r\0302a9\002\004")
public interface Future<R> extends Function0<R> {
  R apply();
  
  boolean isDone();
  
  boolean apply$mcZ$sp();
  
  byte apply$mcB$sp();
  
  char apply$mcC$sp();
  
  double apply$mcD$sp();
  
  float apply$mcF$sp();
  
  int apply$mcI$sp();
  
  long apply$mcJ$sp();
  
  short apply$mcS$sp();
  
  void apply$mcV$sp();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\parallel\Future.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */