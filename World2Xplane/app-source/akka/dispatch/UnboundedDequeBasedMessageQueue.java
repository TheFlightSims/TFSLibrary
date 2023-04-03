package akka.dispatch;

import akka.actor.ActorRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001M2q!\001\002\021\002\007\005qAA\020V]\n|WO\0343fI\022+\027/^3CCN,G-T3tg\006<W-U;fk\026T!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\t\001AaB\005\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!A\006#fcV,')Y:fI6+7o]1hKF+X-^3\021\005=\031\022B\001\013\003\005!*fNY8v]\022,G\rR3rk\026\024\025m]3e\033\026\0348/Y4f#V,W/Z*f[\006tG/[2t\021\0251\002\001\"\001\030\003\031!\023N\\5uIQ\t\001\004\005\002\n3%\021!D\003\002\005+:LG\017C\003\035\001\021\005Q$A\004f]F,X-^3\025\007aqb\005C\003 7\001\007\001%\001\005sK\016,\027N^3s!\t\tC%D\001#\025\t\031C!A\003bGR|'/\003\002&E\tA\021i\031;peJ+g\rC\003(7\001\007\001&\001\004iC:$G.\032\t\003\037%J!A\013\002\003\021\025sg/\0327pa\026DQ\001\f\001\005\0025\nA\"\0328rk\026,XMR5sgR$2\001\007\0300\021\025y2\0061\001!\021\02593\0061\001)\021\025\t\004\001\"\0013\003\035!W-];fk\026$\022\001\013")
public interface UnboundedDequeBasedMessageQueue extends DequeBasedMessageQueue, UnboundedDequeBasedMessageQueueSemantics {
  void enqueue(ActorRef paramActorRef, Envelope paramEnvelope);
  
  void enqueueFirst(ActorRef paramActorRef, Envelope paramEnvelope);
  
  Envelope dequeue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedDequeBasedMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */