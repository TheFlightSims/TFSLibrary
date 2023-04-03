package akka.io;

import akka.actor.NoSerializationVerificationNeeded;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r2\001\"\001\002\021\002G\005!A\002\002\024\007\"\fgN\\3m%\026<\027n\035;sCRLwN\034\006\003\007\021\t!![8\013\003\025\tA!Y6lCN\031\001aB\007\021\005!YQ\"A\005\013\003)\tQa]2bY\006L!\001D\005\003\r\005s\027PU3g!\tq\021#D\001\020\025\t\001B!A\003bGR|'/\003\002\023\037\t\tcj\\*fe&\fG.\033>bi&|gNV3sS\032L7-\031;j_:tU-\0323fI\")A\003\001D\001-\005qQM\\1cY\026Le\016^3sKN$8\001\001\013\003/i\001\"\001\003\r\n\005eI!\001B+oSRDQaG\nA\002q\t!a\0349\021\005!i\022B\001\020\n\005\rIe\016\036\005\006A\0011\t!I\001\020I&\034\030M\0317f\023:$XM]3tiR\021qC\t\005\0067}\001\r\001\b")
public interface ChannelRegistration extends NoSerializationVerificationNeeded {
  void enableInterest(int paramInt);
  
  void disableInterest(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\ChannelRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */