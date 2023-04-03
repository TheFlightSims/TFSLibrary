package akka.dispatch;

import akka.actor.ActorRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00192q!\001\002\021\002\007\005qAA\020V]\n|WO\0343fIF+X-^3CCN,G-T3tg\006<W-U;fk\026T!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\t\001AaB\005\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!AF)vKV,')Y:fI6+7o]1hKF+X-^3\021\005=\031\022B\001\013\003\005y)fNY8v]\022,G-T3tg\006<W-U;fk\026\034V-\\1oi&\0347\017C\003\027\001\021\005q#\001\004%S:LG\017\n\013\0021A\021\021\"G\005\0035)\021A!\0268ji\")A\004\001C\001;\0059QM\\9vKV,Gc\001\r\037M!)qd\007a\001A\005A!/Z2fSZ,'\017\005\002\"I5\t!E\003\002$\t\005)\021m\031;pe&\021QE\t\002\t\003\016$xN\035*fM\")qe\007a\001Q\0051\001.\0318eY\026\004\"aD\025\n\005)\022!\001C#om\026dw\016]3\t\0131\002A\021A\027\002\017\021,\027/^3vKR\t\001\006")
public interface UnboundedQueueBasedMessageQueue extends QueueBasedMessageQueue, UnboundedMessageQueueSemantics {
  void enqueue(ActorRef paramActorRef, Envelope paramEnvelope);
  
  Envelope dequeue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\UnboundedQueueBasedMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */