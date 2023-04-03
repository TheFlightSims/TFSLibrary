package akka.dispatch;

import akka.actor.ActorRef;
import java.util.concurrent.BlockingQueue;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m2q!\001\002\021\002\007\005qAA\017C_VtG-\0323Rk\026,XMQ1tK\022lUm]:bO\026\fV/Z;f\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\021\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\t1\022+^3vK\n\0137/\0323NKN\034\030mZ3Rk\026,X\r\005\002\020'%\021AC\001\002\035\005>,h\016Z3e\033\026\0348/Y4f#V,W/Z*f[\006tG/[2t\021\0251\002\001\"\001\030\003\031!\023N\\5uIQ\t\001\004\005\002\n3%\021!D\003\002\005+:LG\017C\003\035\001\031\005S$A\003rk\026,X-F\001\037!\ryb\005K\007\002A)\021\021EI\001\013G>t7-\036:sK:$(BA\022%\003\021)H/\0337\013\003\025\nAA[1wC&\021q\005\t\002\016\0052|7m[5oOF+X-^3\021\005=I\023B\001\026\003\005!)eN^3m_B,\007\"\002\027\001\t\003i\023aB3ocV,W/\032\013\004192\004\"B\030,\001\004\001\024\001\003:fG\026Lg/\032:\021\005E\"T\"\001\032\013\005M\"\021!B1di>\024\030BA\0333\005!\t5\r^8s%\0264\007\"B\034,\001\004A\023A\0025b]\022dW\rC\003:\001\021\005!(A\004eKF,X-^3\025\003!\002")
public interface BoundedQueueBasedMessageQueue extends QueueBasedMessageQueue, BoundedMessageQueueSemantics {
  BlockingQueue<Envelope> queue();
  
  void enqueue(ActorRef paramActorRef, Envelope paramEnvelope);
  
  Envelope dequeue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BoundedQueueBasedMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */