package akka.routing;

import akka.actor.ActorContext;
import akka.actor.Props;
import scala.collection.immutable.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0313q!\001\002\021\002\007\005qAA\003He>,\bO\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!\001\004*pkR,'oQ8oM&<\007\"B\n\001\t\003!\022A\002\023j]&$H\005F\001\026!\tIa#\003\002\030\025\t!QK\\5u\021\025I\002A\"\001\033\003\025\001\030\r\0365t+\005Y\002c\001\017\"G5\tQD\003\002\037?\005I\021.\\7vi\006\024G.\032\006\003A)\t!bY8mY\026\034G/[8o\023\t\021SD\001\005Ji\026\024\030M\0317f!\t!sE\004\002\nK%\021aEC\001\007!J,G-\0324\n\005!J#AB*ue&twM\003\002'\025!)1\006\001C\001Y\005)\001O]8qgR\tQ\006\005\002/c5\tqF\003\0021\t\005)\021m\031;pe&\021!g\f\002\006!J|\007o\035\005\007i\001!\t\001B\033\002\023I|W\017^3f\r>\024Hc\001\034:wA\021qbN\005\003q\t\021aAU8vi\026,\007\"\002\0364\001\004\031\023\001\0029bi\"DQ\001P\032A\002u\nqaY8oi\026DH\017\005\002/}%\021qh\f\002\r\003\016$xN]\"p]R,\007\020\036\005\007\003\002!\t\005\002\"\002#\r\024X-\031;f%>,H/\032:BGR|'\017F\001D!\tyA)\003\002F\005\tY!k\\;uKJ\f5\r^8s\001")
public interface Group extends RouterConfig {
  Iterable<String> paths();
  
  Props props();
  
  Routee routeeFor(String paramString, ActorContext paramActorContext);
  
  RouterActor createRouterActor();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Group.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */