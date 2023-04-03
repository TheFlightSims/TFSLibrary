package scala.util.control;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001A:Q!\001\002\t\002%\t\001BT8o\r\006$\030\r\034\006\003\007\021\tqaY8oiJ|GN\003\002\006\r\005!Q\017^5m\025\0059\021!B:dC2\f7\001\001\t\003\025-i\021A\001\004\006\031\tA\t!\004\002\t\035>tg)\031;bYN\0211B\004\t\003\037Ai\021AB\005\003#\031\021a!\0218z%\0264\007\"B\n\f\t\003!\022A\002\037j]&$h\bF\001\n\021\02512\002\"\001\030\003\025\t\007\017\0357z)\tA2\004\005\002\0203%\021!D\002\002\b\005>|G.Z1o\021\025aR\0031\001\036\003\005!\bC\001\020'\035\tyBE\004\002!G5\t\021E\003\002#\021\0051AH]8pizJ\021aB\005\003K\031\tq\001]1dW\006<W-\003\002(Q\tIA\013\033:po\006\024G.\032\006\003K\031AQAK\006\005\002-\nq!\0368baBd\027\020\006\002-_A\031q\"L\017\n\00592!AB(qi&|g\016C\003\035S\001\007Q\004")
public final class NonFatal {
  public static Option<Throwable> unapply(Throwable paramThrowable) {
    return NonFatal$.MODULE$.unapply(paramThrowable);
  }
  
  public static boolean apply(Throwable paramThrowable) {
    return NonFatal$.MODULE$.apply(paramThrowable);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\NonFatal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */