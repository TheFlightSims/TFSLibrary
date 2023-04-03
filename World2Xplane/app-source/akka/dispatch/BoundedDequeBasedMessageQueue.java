package akka.dispatch;

import akka.actor.ActorRef;
import java.util.concurrent.BlockingDeque;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)3q!\001\002\021\002\007\005qAA\017C_VtG-\0323EKF,XMQ1tK\022lUm]:bO\026\fV/Z;f\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\021\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\t1B)Z9vK\n\0137/\0323NKN\034\030mZ3Rk\026,X\r\005\002\020'%\021AC\001\002'\005>,h\016Z3e\t\026\fX/\032\"bg\026$W*Z:tC\036,\027+^3vKN+W.\0318uS\016\034\b\"\002\f\001\t\0039\022A\002\023j]&$H\005F\001\031!\tI\021$\003\002\033\025\t!QK\\5u\021\025a\002A\"\001\036\003-\001Xo\0355US6,w*\036;\026\003y\001\"a\b\023\016\003\001R!!\t\022\002\021\021,(/\031;j_:T!a\t\006\002\025\r|gnY;se\026tG/\003\002&A\tAA)\036:bi&|g\016C\003(\001\031\005\003&A\003rk\026,X-F\001*!\rQ\003GM\007\002W)\0211\005\f\006\003[9\nA!\036;jY*\tq&\001\003kCZ\f\027BA\031,\0055\021En\\2lS:<G)Z9vKB\021qbM\005\003i\t\021\001\"\0228wK2|\007/\032\005\006m\001!\taN\001\bK:\fX/Z;f)\rA\002\b\021\005\006sU\002\rAO\001\te\026\034W-\033<feB\0211HP\007\002y)\021Q\bB\001\006C\016$xN]\005\003q\022\001\"Q2u_J\024VM\032\005\006\003V\002\rAM\001\007Q\006tG\r\\3\t\013\r\003A\021\001#\002\031\025t\027/^3vK\032K'o\035;\025\007a)e\tC\003:\005\002\007!\bC\003B\005\002\007!\007C\003I\001\021\005\021*A\004eKF,X-^3\025\003I\002")
public interface BoundedDequeBasedMessageQueue extends DequeBasedMessageQueue, BoundedDequeBasedMessageQueueSemantics {
  Duration pushTimeOut();
  
  BlockingDeque<Envelope> queue();
  
  void enqueue(ActorRef paramActorRef, Envelope paramEnvelope);
  
  void enqueueFirst(ActorRef paramActorRef, Envelope paramEnvelope);
  
  Envelope dequeue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedDequeBasedMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */