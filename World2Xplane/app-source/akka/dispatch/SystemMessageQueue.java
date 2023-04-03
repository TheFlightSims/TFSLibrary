package akka.dispatch;

import akka.actor.ActorRef;
import akka.dispatch.sysmsg.SystemMessage;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001U2\001\"\001\002\021\002G\005AA\002\002\023'f\034H/Z7NKN\034\030mZ3Rk\026,XM\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\024\005\0019\001C\001\005\f\033\005I!\"\001\006\002\013M\034\027\r\\1\n\0051I!AB!osJ+g\rC\003\017\001\031\005\001#A\007tsN$X-\\#ocV,W/Z\002\001)\r\tB\003\b\t\003\021II!aE\005\003\tUs\027\016\036\005\006+5\001\rAF\001\te\026\034W-\033<feB\021qCG\007\0021)\021\021\004B\001\006C\016$xN]\005\0037a\021\001\"Q2u_J\024VM\032\005\006;5\001\rAH\001\b[\026\0348/Y4f!\ty\"%D\001!\025\t\t#!\001\004tsNl7oZ\005\003G\001\022QbU=ti\026lW*Z:tC\036,\007\"B\023\001\r\0031\023aC:zgR,W\016\022:bS:$\"a\n\026\021\005}A\023BA\025!\005y)\025M\0357jKN$h)\033:tiNK8\017^3n\033\026\0348/Y4f\031&\034H\017C\003,I\001\007A&A\006oK^\034uN\034;f]R\034\bCA\020.\023\tq\003E\001\017MCR,7\017\036$jeN$8+_:uK6lUm]:bO\026d\025n\035;\t\013A\002a\021A\031\002#!\f7oU=ti\026lW*Z:tC\036,7/F\0013!\tA1'\003\0025\023\t9!i\\8mK\006t\007")
public interface SystemMessageQueue {
  void systemEnqueue(ActorRef paramActorRef, SystemMessage paramSystemMessage);
  
  SystemMessage systemDrain(SystemMessage paramSystemMessage);
  
  boolean hasSystemMessages();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\SystemMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */