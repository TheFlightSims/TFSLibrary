package akka.actor;

import java.net.URI;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m:Q!\001\002\t\002\035\tA#\0213ee\026\0348O\022:p[V\023\026j\025;sS:<'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\013BI\022\024Xm]:Ge>lWKU%TiJLgnZ\n\003\0231\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007\"B\n\n\t\003!\022A\002\037j]&$h\bF\001\b\021\0251\022\002\"\001\030\003\035)h.\0319qYf$\"\001\007\020\021\0075I2$\003\002\033\035\t1q\n\035;j_:\004\"\001\003\017\n\005u\021!aB!eIJ,7o\035\005\006?U\001\r\001I\001\005C\022$'\017\005\002\"I9\021QBI\005\003G9\ta\001\025:fI\0264\027BA\023'\005\031\031FO]5oO*\0211E\004\005\006-%!\t\001\013\013\0031%BQAK\024A\002-\n1!\036:j!\ta\023'D\001.\025\tqs&A\002oKRT\021\001M\001\005U\0064\030-\003\0023[\t\031QKU%\t\013QJA\021A\033\002\013\005\004\b\017\\=\025\005m1\004\"B\0204\001\004\001\003\"\002\035\n\t\003I\024!\0029beN,GCA\016;\021\025yr\0071\001!\001")
public final class AddressFromURIString {
  public static Address parse(String paramString) {
    return AddressFromURIString$.MODULE$.parse(paramString);
  }
  
  public static Address apply(String paramString) {
    return AddressFromURIString$.MODULE$.apply(paramString);
  }
  
  public static Option<Address> unapply(URI paramURI) {
    return AddressFromURIString$.MODULE$.unapply(paramURI);
  }
  
  public static Option<Address> unapply(String paramString) {
    return AddressFromURIString$.MODULE$.unapply(paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AddressFromURIString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */