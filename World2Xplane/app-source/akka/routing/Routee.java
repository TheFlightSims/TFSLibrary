package akka.routing;

import akka.actor.ActorRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0052q!\001\002\021\002G\005qA\001\004S_V$X-\032\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\t\013=\001a\021\001\t\002\tM,g\016\032\013\004#QI\002CA\005\023\023\t\031\"B\001\003V]&$\b\"B\013\017\001\0041\022aB7fgN\fw-\032\t\003\023]I!\001\007\006\003\007\005s\027\020C\003\033\035\001\0071$\001\004tK:$WM\035\t\0039}i\021!\b\006\003=\021\tQ!Y2u_JL!\001I\017\003\021\005\033Go\034:SK\032\004")
public interface Routee {
  void send(Object paramObject, ActorRef paramActorRef);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Routee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */