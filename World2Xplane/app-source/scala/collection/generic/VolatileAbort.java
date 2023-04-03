package scala.collection.generic;

import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\00152q!\001\002\021\002\007\005\021BA\007W_2\fG/\0337f\003\n|'\017\036\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001aE\002\001\0259\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\006TS\036t\027\r\0347j]\036DQa\005\001\005\002Q\ta\001J5oSR$C#A\013\021\005-1\022BA\f\007\005\021)f.\033;\t\017e\001\001\031!C\0055\005I\021MY8si\032d\027mZ\013\0027A\0211\002H\005\003;\031\021qAQ8pY\026\fg\016C\004 \001\001\007I\021\002\021\002\033\005\024wN\035;gY\006<w\fJ3r)\t)\022\005C\004#=\005\005\t\031A\016\002\007a$\023\007\003\004%\001\001\006KaG\001\013C\n|'\017\0364mC\036\004\003FA\022'!\tYq%\003\002)\r\tAao\0347bi&dW\rC\003+\001\021\005#$A\005jg\006\023wN\035;fI\")A\006\001C!)\005)\021MY8si\002")
public interface VolatileAbort extends Signalling {
  boolean scala$collection$generic$VolatileAbort$$abortflag();
  
  @TraitSetter
  void scala$collection$generic$VolatileAbort$$abortflag_$eq(boolean paramBoolean);
  
  boolean isAborted();
  
  void abort();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\VolatileAbort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */