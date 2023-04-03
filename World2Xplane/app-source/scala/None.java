package scala;

import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

@ScalaSignature(bytes = "\006\001Q;Q!\001\002\t\002\026\tAAT8oK*\t1!A\003tG\006d\027m\001\001\021\005\0319Q\"\001\002\007\013!\021\001\022Q\005\003\t9{g.Z\n\005\017)\0012\003E\002\007\0275I!\001\004\002\003\r=\003H/[8o!\t1a\"\003\002\020\005\t9aj\034;iS:<\007C\001\004\022\023\t\021\"AA\004Qe>$Wo\031;\021\005\031!\022BA\013\003\0051\031VM]5bY&T\030M\0317f\021\0259r\001\"\001\031\003\031a\024N\\5u}Q\tQ\001C\003\033\017\021\0051$A\004jg\026k\007\017^=\026\003q\001\"AB\017\n\005y\021!a\002\"p_2,\027M\034\005\006A\035!\t!I\001\004O\026$X#A\007\t\017\r:\021\021!C!I\005i\001O]8ek\016$\bK]3gSb,\022!\n\t\003M-j\021a\n\006\003Q%\nA\001\\1oO*\t!&\001\003kCZ\f\027B\001\027(\005\031\031FO]5oO\"9afBA\001\n\003y\023\001\0049s_\022,8\r^!sSRLX#\001\031\021\005\031\t\024B\001\032\003\005\rIe\016\036\005\bi\035\t\t\021\"\0016\0039\001(o\0343vGR,E.Z7f]R$\"AN\035\021\005\0319\024B\001\035\003\005\r\te.\037\005\buM\n\t\0211\0011\003\rAH%\r\005\by\035\t\t\021\"\021>\003=\001(o\0343vGRLE/\032:bi>\024X#\001 \021\007}\022e'D\001A\025\t\t%!\001\006d_2dWm\031;j_:L!a\021!\003\021%#XM]1u_JDq!R\004\002\002\023\005a)\001\005dC:,\025/^1m)\tar\tC\004;\t\006\005\t\031\001\034\t\017%;\021\021!C!\025\006A\001.Y:i\007>$W\rF\0011\021\035au!!A\005B5\013\001\002^8TiJLgn\032\013\002K!9qjBA\001\n\023\001\026a\003:fC\022\024Vm]8mm\026$\022!\025\t\003MIK!aU\024\003\r=\023'.Z2u\001")
public final class None {
  public static String toString() {
    return None$.MODULE$.toString();
  }
  
  public static int hashCode() {
    return None$.MODULE$.hashCode();
  }
  
  public static boolean canEqual(Object paramObject) {
    return None$.MODULE$.canEqual(paramObject);
  }
  
  public static Iterator<Object> productIterator() {
    return None$.MODULE$.productIterator();
  }
  
  public static Object productElement(int paramInt) {
    return None$.MODULE$.productElement(paramInt);
  }
  
  public static int productArity() {
    return None$.MODULE$.productArity();
  }
  
  public static String productPrefix() {
    return None$.MODULE$.productPrefix();
  }
  
  public static Nothing$ get() {
    return None$.MODULE$.get();
  }
  
  public static boolean isEmpty() {
    return None$.MODULE$.isEmpty();
  }
  
  public static <X> Serializable toLeft(Function0<X> paramFunction0) {
    return None$.MODULE$.toLeft(paramFunction0);
  }
  
  public static <X> Serializable toRight(Function0<X> paramFunction0) {
    return None$.MODULE$.toRight(paramFunction0);
  }
  
  public static List<Nothing$> toList() {
    return None$.MODULE$.toList();
  }
  
  public static Iterator<Nothing$> iterator() {
    return None$.MODULE$.iterator();
  }
  
  public static <B> Option<B> orElse(Function0<Option<B>> paramFunction0) {
    return None$.MODULE$.orElse(paramFunction0);
  }
  
  public static <B> Option<B> collect(PartialFunction<Nothing$, B> paramPartialFunction) {
    return None$.MODULE$.collect(paramPartialFunction);
  }
  
  public static <U> void foreach(Function1<Nothing$, U> paramFunction1) {
    None$.MODULE$.foreach(paramFunction1);
  }
  
  public static boolean forall(Function1<Nothing$, Object> paramFunction1) {
    return None$.MODULE$.forall(paramFunction1);
  }
  
  public static boolean exists(Function1<Nothing$, Object> paramFunction1) {
    return None$.MODULE$.exists(paramFunction1);
  }
  
  public static Option<Nothing$>.WithFilter withFilter(Function1<Nothing$, Object> paramFunction1) {
    return None$.MODULE$.withFilter(paramFunction1);
  }
  
  public static boolean nonEmpty() {
    return None$.MODULE$.nonEmpty();
  }
  
  public static Option<Nothing$> filterNot(Function1<Nothing$, Object> paramFunction1) {
    return None$.MODULE$.filterNot(paramFunction1);
  }
  
  public static Option<Nothing$> filter(Function1<Nothing$, Object> paramFunction1) {
    return None$.MODULE$.filter(paramFunction1);
  }
  
  public static <B> Option<B> flatten(Predef.$less$colon$less<Nothing$, Option<B>> param$less$colon$less) {
    return None$.MODULE$.flatten(param$less$colon$less);
  }
  
  public static <B> Option<B> flatMap(Function1<Nothing$, Option<B>> paramFunction1) {
    return None$.MODULE$.flatMap(paramFunction1);
  }
  
  public static <B> B fold(Function0<B> paramFunction0, Function1<Nothing$, B> paramFunction1) {
    return (B)None$.MODULE$.fold(paramFunction0, paramFunction1);
  }
  
  public static <B> Option<B> map(Function1<Nothing$, B> paramFunction1) {
    return None$.MODULE$.map(paramFunction1);
  }
  
  public static <A1> A1 orNull(Predef.$less$colon$less<Null$, A1> param$less$colon$less) {
    return (A1)None$.MODULE$.orNull(param$less$colon$less);
  }
  
  public static <B> B getOrElse(Function0<B> paramFunction0) {
    return (B)None$.MODULE$.getOrElse(paramFunction0);
  }
  
  public static boolean isDefined() {
    return None$.MODULE$.isDefined();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\None.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */