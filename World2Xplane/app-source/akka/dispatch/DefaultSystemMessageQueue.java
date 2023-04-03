package akka.dispatch;

import akka.actor.ActorRef;
import akka.dispatch.sysmsg.SystemMessage;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001!3\021\"\001\002\021\002\007\005AA\002!\0033\021+g-Y;miNK8\017^3n\033\026\0348/Y4f#V,W/\032\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b'\t\001q\001\005\002\t\0275\t\021BC\001\013\003\025\0318-\0317b\023\ta\021B\001\004B]f\024VM\032\005\006\035\001!\t\001E\001\007I%t\027\016\036\023\004\001Q\t\021\003\005\002\t%%\0211#\003\002\005+:LG\017C\003\026\001\021\025a#A\007tsN$X-\\#ocV,W/\032\013\004#]y\002\"\002\r\025\001\004I\022\001\003:fG\026Lg/\032:\021\005iiR\"A\016\013\005q!\021!B1di>\024\030B\001\020\034\005!\t5\r^8s%\0264\007\"\002\021\025\001\004\t\023aB7fgN\fw-\032\t\003E\025j\021a\t\006\003I\t\taa]=t[N<\027B\001\024$\0055\031\026p\035;f[6+7o]1hK\"\022A\003\013\t\003S1j\021A\013\006\003W%\t!\"\0318o_R\fG/[8o\023\ti#FA\004uC&d'/Z2\t\013=\002AQ\001\031\002\027ML8\017^3n\tJ\f\027N\034\013\003cQ\002\"A\t\032\n\005M\032#AH#be2LWm\035;GSJ\034HoU=ti\026lW*Z:tC\036,G*[:u\021\025)d\0061\0017\003-qWm^\"p]R,g\016^:\021\005\t:\024B\001\035$\005qa\025\r^3ti\032K'o\035;TsN$X-\\'fgN\fw-\032'jgRD#A\f\025\t\013m\002A\021\001\037\002#!\f7oU=ti\026lW*Z:tC\036,7/F\001>!\tAa(\003\002@\023\t9!i\\8mK\006t'cA!D\013\032!!\t\001\001A\0051a$/\0324j]\026lWM\034;?!\t!\005!D\001\003!\t!e)\003\002H\005\t9Q*Y5mE>D\b")
public interface DefaultSystemMessageQueue {
  void systemEnqueue(ActorRef paramActorRef, SystemMessage paramSystemMessage);
  
  SystemMessage systemDrain(SystemMessage paramSystemMessage);
  
  boolean hasSystemMessages();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DefaultSystemMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */