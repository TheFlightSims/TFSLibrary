package akka.dispatch;

import akka.actor.ActorRef;
import java.util.Queue;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\t3q!\001\002\021\002\007\005qA\001\fRk\026,XMQ1tK\022lUm]:bO\026\fV/Z;f\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\021\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\taQ*Z:tC\036,\027+^3vKB\021qbE\005\003)\t\021\021$T;mi&\004H.Z\"p]N,X.\032:TK6\fg\016^5dg\")a\003\001C\001/\0051A%\0338ji\022\"\022\001\007\t\003\023eI!A\007\006\003\tUs\027\016\036\005\0069\0011\t!H\001\006cV,W/Z\013\002=A\031q\004\n\024\016\003\001R!!\t\022\002\tU$\030\016\034\006\002G\005!!.\031<b\023\t)\003EA\003Rk\026,X\r\005\002\020O%\021\001F\001\002\t\013:4X\r\\8qK\")!\006\001C\001W\005\001b.^7cKJ|e-T3tg\006<Wm]\013\002YA\021\021\"L\005\003])\0211!\0238u\021\025\001\004\001\"\0012\003-A\027m]'fgN\fw-Z:\026\003I\002\"!C\032\n\005QR!a\002\"p_2,\027M\034\005\006m\001!\taN\001\bG2,\027M\\+q)\rA\002\b\021\005\006sU\002\rAO\001\006_^tWM\035\t\003wyj\021\001\020\006\003{\021\tQ!Y2u_JL!a\020\037\003\021\005\033Go\034:SK\032DQ!Q\033A\0029\t1\002Z3bI2+G\017^3sg\002")
public interface QueueBasedMessageQueue extends MessageQueue, MultipleConsumerSemantics {
  Queue<Envelope> queue();
  
  int numberOfMessages();
  
  boolean hasMessages();
  
  void cleanUp(ActorRef paramActorRef, MessageQueue paramMessageQueue);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\QueueBasedMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */