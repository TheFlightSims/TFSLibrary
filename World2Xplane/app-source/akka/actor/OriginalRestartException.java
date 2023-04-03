package akka.actor;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001I:Q!\001\002\t\002\035\t\001d\024:jO&t\027\r\034*fgR\f'\017^#yG\026\004H/[8o\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\tArJ]5hS:\fGNU3ti\006\024H/\022=dKB$\030n\0348\024\005%a\001CA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!AB!osJ+g\rC\003\024\023\021\005A#\001\004=S:LGO\020\013\002\017!)a#\003C\001/\0059QO\\1qa2LHC\001\r(!\ri\021dG\005\00359\021aa\0249uS>t\007C\001\017%\035\ti\"E\004\002\037C5\tqD\003\002!\r\0051AH]8pizJ\021aD\005\003G9\tq\001]1dW\006<W-\003\002&M\tIA\013\033:po\006\024G.\032\006\003G9AQ\001K\013A\002%\n!!\032=\021\005!Q\023BA\026\003\005Q\001vn\035;SKN$\030M\035;Fq\016,\007\017^5p]\"\032\021\"\f\031\021\0055q\023BA\030\017\005A\031VM]5bYZ+'o]5p]VKEIH\001\002Q\r\001Q\006\r")
public final class OriginalRestartException {
  public static Option<Throwable> unapply(PostRestartException paramPostRestartException) {
    return OriginalRestartException$.MODULE$.unapply(paramPostRestartException);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\OriginalRestartException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */