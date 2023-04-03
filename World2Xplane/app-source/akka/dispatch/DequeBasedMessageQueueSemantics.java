package akka.dispatch;

import akka.actor.ActorRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\t2q!\001\002\021\002G\005qAA\020EKF,XMQ1tK\022lUm]:bO\026\fV/Z;f'\026l\027M\034;jGNT!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\005\001A\001CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\rC\003\020\001\031\005\001#\001\007f]F,X-^3GSJ\034H\017F\002\022)q\001\"!\003\n\n\005MQ!\001B+oSRDQ!\006\bA\002Y\t\001B]3dK&4XM\035\t\003/ii\021\001\007\006\0033\021\tQ!Y2u_JL!a\007\r\003\021\005\033Go\034:SK\032DQ!\b\bA\002y\ta\001[1oI2,\007CA\020!\033\005\021\021BA\021\003\005!)eN^3m_B,\007")
public interface DequeBasedMessageQueueSemantics {
  void enqueueFirst(ActorRef paramActorRef, Envelope paramEnvelope);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DequeBasedMessageQueueSemantics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */