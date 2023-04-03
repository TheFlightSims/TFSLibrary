package akka.routing;

import akka.actor.NoSerializationVerificationNeeded;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)2q!\001\002\021\002G\005qA\001\007S_V$\030N\\4M_\036L7M\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\021R\"\001\t\013\005E!\021!B1di>\024\030BA\n\021\005\005runU3sS\006d\027N_1uS>tg+\032:jM&\034\027\r^5p]:+W\rZ3e\021\025)\002A\"\001\027\003\031\031X\r\\3diR\031qc\007\021\021\005aIR\"\001\002\n\005i\021!A\002*pkR,W\rC\003\035)\001\007Q$A\004nKN\034\030mZ3\021\005%q\022BA\020\013\005\r\te.\037\005\006CQ\001\rAI\001\be>,H/Z3t!\r\031\003fF\007\002I)\021QEJ\001\nS6lW\017^1cY\026T!a\n\006\002\025\r|G\016\\3di&|g.\003\002*I\tQ\021J\0343fq\026$7+Z9")
public interface RoutingLogic extends NoSerializationVerificationNeeded {
  Routee select(Object paramObject, IndexedSeq<Routee> paramIndexedSeq);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */