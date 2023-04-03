package scala.collection.generic;

import scala.collection.Parallel;
import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001U2q!\001\002\021\002G\005\021B\001\bDC:\034u.\0342j]\0264%o\\7\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\005\025Uy\"e\005\003\001\027=!\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB)\001#E\n\037C5\t!!\003\002\023\005\ta1)\0318Ck&dGM\022:p[B\021A#\006\007\001\t\0311\002\001#b\001/\t!aI]8n#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\005QyBA\002\021\001\021\013\007qC\001\003FY\026l\007C\001\013#\t\031\031\003\001\"b\001/\t\021Ak\034\t\003K\031j\021\001B\005\003O\021\021\001\002U1sC2dW\r\034\005\006S\0011\tAK\001\006CB\004H.\037\013\003WE\002B\001L\030\037C5\tQF\003\002/\t\005A\001/\031:bY2,G.\003\0021[\tA1i\\7cS:,'\017C\0033Q\001\0071#\001\003ge>l\007\"B\025\001\r\003!D#A\026")
public interface CanCombineFrom<From, Elem, To> extends CanBuildFrom<From, Elem, To>, Parallel {
  Combiner<Elem, To> apply(From paramFrom);
  
  Combiner<Elem, To> apply();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\CanCombineFrom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */