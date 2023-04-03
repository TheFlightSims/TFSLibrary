package akka.actor;

import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001E2\001\"\001\002\021\002\007\005qA\f\002''V\004XM\035<jg>\0248\013\036:bi\026<\027\020T8x!JLwN]5us&k\007\017\\5dSR\034(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032DQa\004\001\005\002A\ta\001J5oSR$C#A\t\021\005%\021\022BA\n\013\005\021)f.\033;\t\013U\001A1\001\f\0023M,\027oQ1vg\026$\025N]3di&4XM\r#fG&$WM\035\013\003/u\001\"\001G\r\016\003\001I!AG\016\003\017\021+7-\0333fe*\021ADA\001\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020C\003\037)\001\007q$\001\005ue\006\004X\t_5u!\r\001\003f\013\b\003C\031r!AI\023\016\003\rR!\001\n\004\002\rq\022xn\034;?\023\005Y\021BA\024\013\003\035\001\030mY6bO\026L!!\013\026\003\021%#XM]1cY\026T!a\n\006\021\005aa\023BA\027\034\0059\031\025-^:f\t&\024Xm\031;jm\026t!a\f\031\016\003\tI!\001\b\002")
public interface SupervisorStrategyLowPriorityImplicits {
  PartialFunction<Throwable, SupervisorStrategy.Directive> seqCauseDirective2Decider(Iterable<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> paramIterable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SupervisorStrategyLowPriorityImplicits.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */