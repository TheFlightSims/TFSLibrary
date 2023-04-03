package akka.dispatch;

import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001e1q!\001\002\021\002G\005qA\001\017C_VtG-\0323NKN\034\030mZ3Rk\026,XmU3nC:$\030nY:\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fM\")q\002\001D\001!\005Y\001/^:i)&lWmT;u+\005\t\002C\001\n\030\033\005\031\"B\001\013\026\003!!WO]1uS>t'B\001\f\013\003)\031wN\\2veJ,g\016^\005\0031M\021\001\002R;sCRLwN\034")
public interface BoundedMessageQueueSemantics {
  Duration pushTimeOut();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedMessageQueueSemantics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */