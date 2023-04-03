package akka.actor;

import scala.Option;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001=:Q!\001\002\t\002\035\t\021CU3mCRLg/Z!di>\024\b+\031;i\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t\t\"+\0327bi&4X-Q2u_J\004\026\r\0365\024\007%a!\003\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\t\003\021MI!\001\006\002\003\023A\013G\017[+uS2\034\b\"\002\f\n\t\0039\022A\002\037j]&$h\bF\001\b\021\025I\022\002\"\001\033\003\035)h.\0319qYf$\"aG\027\021\0075ab$\003\002\036\035\t1q\n\035;j_:\0042a\b\023'\033\005\001#BA\021#\003%IW.\\;uC\ndWM\003\002$\035\005Q1m\0347mK\016$\030n\0348\n\005\025\002#aA*fcB\021qE\013\b\003\033!J!!\013\b\002\rA\023X\rZ3g\023\tYCF\001\004TiJLgn\032\006\003S9AQA\f\rA\002\031\nA!\0313ee\002")
public final class RelativeActorPath {
  public static List<String> split(String paramString1, String paramString2) {
    return RelativeActorPath$.MODULE$.split(paramString1, paramString2);
  }
  
  public static Option<Seq<String>> unapply(String paramString) {
    return RelativeActorPath$.MODULE$.unapply(paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\RelativeActorPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */