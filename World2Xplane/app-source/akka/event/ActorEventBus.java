package akka.event;

import akka.actor.ActorRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)2q!\001\002\021\002\007\005qAA\007BGR|'/\022<f]R\024Uo\035\006\003\007\021\tQ!\032<f]RT\021!B\001\005C.\\\027m\001\001\024\007\001Aa\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021\001\"\022<f]R\024Uo\035\005\006'\001!\t\001F\001\007I%t\027\016\036\023\025\003U\001\"!\003\f\n\005]Q!\001B+oSR,A!\007\001\0015\tQ1+\0362tGJL'-\032:\021\005mqR\"\001\017\013\005u!\021!B1di>\024\030BA\020\035\005!\t5\r^8s%\0264\007\"B\021\001\t#\021\023AE2p[B\f'/Z*vEN\034'/\0332feN$2a\t\024)!\tIA%\003\002&\025\t\031\021J\034;\t\013\035\002\003\031\001\016\002\003\005DQ!\013\021A\002i\t\021A\031")
public interface ActorEventBus extends EventBus {
  int compareSubscribers(ActorRef paramActorRef1, ActorRef paramActorRef2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\ActorEventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */