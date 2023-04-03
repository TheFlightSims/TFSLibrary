package akka.dispatch;

import akka.actor.ActorRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001e2q!\001\002\021\002G\005qA\001\007NKN\034\030mZ3Rk\026,XM\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\t\013=\001a\021\001\t\002\017\025t\027/^3vKR\031\021\003\006\017\021\005%\021\022BA\n\013\005\021)f.\033;\t\013Uq\001\031\001\f\002\021I,7-Z5wKJ\004\"a\006\016\016\003aQ!!\007\003\002\013\005\034Go\034:\n\005mA\"\001C!di>\024(+\0324\t\013uq\001\031\001\020\002\r!\fg\016\0327f!\ty\002%D\001\003\023\t\t#A\001\005F]Z,Gn\0349f\021\025\031\003A\"\001%\003\035!W-];fk\026$\022A\b\005\006M\0011\taJ\001\021]Vl'-\032:PM6+7o]1hKN,\022\001\013\t\003\023%J!A\013\006\003\007%sG\017C\003-\001\031\005Q&A\006iCNlUm]:bO\026\034X#\001\030\021\005%y\023B\001\031\013\005\035\021un\0347fC:DQA\r\001\007\002M\nqa\0317fC:,\006\017F\002\022iYBQ!N\031A\002Y\tQa\\<oKJDQaN\031A\002a\n1\002Z3bI2+G\017^3sgB\021q\004\001")
public interface MessageQueue {
  void enqueue(ActorRef paramActorRef, Envelope paramEnvelope);
  
  Envelope dequeue();
  
  int numberOfMessages();
  
  boolean hasMessages();
  
  void cleanUp(ActorRef paramActorRef, MessageQueue paramMessageQueue);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */