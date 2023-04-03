package scala.collection.mutable;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0353q!\001\002\021\002G\005\021BA\007D_:\034WO\035:f]Rl\025\r\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\013+}\0312\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\005!E\031b$D\001\003\023\t\021\"AA\002NCB\004\"\001F\013\r\001\021)a\003\001b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bC\001\013 \t\025\001\003A1\001\030\005\005\021\005\"\002\022\001\r\003\031\023a\0039vi&3\027IY:f]R$2\001J\024*!\raQEH\005\003M\031\021aa\0249uS>t\007\"\002\025\"\001\004\031\022!A6\t\013)\n\003\031\001\020\002\003YDQ\001\f\001\007\0025\naA]3n_Z,Gc\001\0302eA\021AbL\005\003a\031\021qAQ8pY\026\fg\016C\003)W\001\0071\003C\003+W\001\007a\004C\0035\001\031\005Q'A\004sKBd\027mY3\025\t92t'\017\005\006QM\002\ra\005\005\006qM\002\rAH\001\t_2$g/\0317vK\")!h\ra\001=\005Aa.Z<wC2,X\rC\0035\001\031\005A\bF\002%{yBQ\001K\036A\002MAQAK\036A\002yAC\001\001!D\013B\021A\"Q\005\003\005\032\021!\002Z3qe\026\034\027\r^3eC\005!\025AL+tK\002\0027oY1mC:\032w\016\0347fGRLwN\034\030d_:\034WO\035:f]RtS*\0319aA%t7\017^3bI:\n\023AR\001\007e9\n\004G\f\031")
public interface ConcurrentMap<A, B> extends Map<A, B> {
  Option<B> putIfAbsent(A paramA, B paramB);
  
  boolean remove(A paramA, B paramB);
  
  boolean replace(A paramA, B paramB1, B paramB2);
  
  Option<B> replace(A paramA, B paramB);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ConcurrentMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */