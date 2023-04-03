package akka.actor;

import akka.dispatch.RequiresMessageQueue;
import akka.dispatch.UnboundedDequeBasedMessageQueueSemantics;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m1q!\001\002\021\002G\005qA\001\bV]\n|WO\0343fIN#\030m\0355\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\002\001'\021\001\001B\004\n\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\tV]J,7\017\036:jGR,Gm\025;bg\"\0042a\005\f\031\033\005!\"BA\013\005\003!!\027n\0359bi\016D\027BA\f\025\005Q\021V-];je\026\034X*Z:tC\036,\027+^3vKB\0211#G\005\0035Q\021\001&\0268c_VtG-\0323EKF,XMQ1tK\022lUm]:bO\026\fV/Z;f'\026l\027M\034;jGN\004")
public interface UnboundedStash extends UnrestrictedStash, RequiresMessageQueue<UnboundedDequeBasedMessageQueueSemantics> {}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UnboundedStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */