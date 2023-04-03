package scala.collection.generic;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0312q!\001\002\021\002G\005\021B\001\bICNtUm^\"p[\nLg.\032:\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025i!3C\001\001\f!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\005\007!\001\001k\021C\t\002\0279,woQ8nE&tWM]\013\002%A!1C\006\r$\033\005!\"BA\013\005\003!\001\030M]1mY\026d\027BA\f\025\005!\031u.\0342j]\026\024\bCA\r\033\031\001!aa\007\001\005\006\004a\"!\001+\022\005u\001\003C\001\007\037\023\tybAA\004O_RD\027N\\4\021\0051\t\023B\001\022\007\005\r\te.\037\t\0033\021\"a!\n\001\005\006\004a\"\001\002*faJ\004")
public interface HasNewCombiner<T, Repr> {
  Combiner<T, Repr> newCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\HasNewCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */