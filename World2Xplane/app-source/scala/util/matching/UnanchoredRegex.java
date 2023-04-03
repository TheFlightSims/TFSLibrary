package scala.util.matching;

import java.util.regex.Matcher;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)2q!\001\002\021\002\007\005\021BA\bV]\006t7\r[8sK\022\024VmZ3y\025\t\031A!\001\005nCR\034\007.\0338h\025\t)a!\001\003vi&d'\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AA\005\003\033\t\021QAU3hKbDQa\004\001\005\002A\ta\001J5oSR$C#A\t\021\005I\031R\"\001\004\n\005Q1!\001B+oSRDQA\006\001\005R]\t!B];o\033\006$8\r[3s)\tA2\004\005\002\0233%\021!D\002\002\b\005>|G.Z1o\021\025aR\0031\001\036\003\005i\007C\001\020%\033\005y\"B\001\021\"\003\025\021XmZ3y\025\t)!EC\001$\003\021Q\027M^1\n\005\025z\"aB'bi\016DWM\035\005\006O\001!\t\005K\001\013k:\fgn\0315pe\026$W#A\025\021\005-\001\001")
public interface UnanchoredRegex {
  boolean runMatcher(Matcher paramMatcher);
  
  UnanchoredRegex unanchored();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\matching\UnanchoredRegex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */