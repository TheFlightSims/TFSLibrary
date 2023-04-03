package akka.dispatch;

import java.util.Deque;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r2q!\001\002\021\002G\005qA\001\fEKF,XMQ1tK\022lUm]:bO\026\fV/Z;f\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\021\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\t1\022+^3vK\n\0137/\0323NKN\034\030mZ3Rk\026,X\r\005\002\020'%\021AC\001\002 \t\026\fX/\032\"bg\026$W*Z:tC\036,\027+^3vKN+W.\0318uS\016\034\b\"\002\f\001\r\0039\022!B9vKV,W#\001\r\021\007eq\002%D\001\033\025\tYB$\001\003vi&d'\"A\017\002\t)\fg/Y\005\003?i\021Q\001R3rk\026\004\"aD\021\n\005\t\022!\001C#om\026dw\016]3")
public interface DequeBasedMessageQueue extends QueueBasedMessageQueue, DequeBasedMessageQueueSemantics {
  Deque<Envelope> queue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DequeBasedMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */