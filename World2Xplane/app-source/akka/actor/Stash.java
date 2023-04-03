package akka.actor;

import akka.dispatch.DequeBasedMessageQueueSemantics;
import akka.dispatch.RequiresMessageQueue;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m1q!\001\002\021\002G\005qAA\003Ti\006\034\bN\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\005\001!q!\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021\021#\0268sKN$(/[2uK\022\034F/Y:i!\r\031b\003G\007\002))\021Q\003B\001\tI&\034\b/\031;dQ&\021q\003\006\002\025%\026\fX/\033:fg6+7o]1hKF+X-^3\021\005MI\022B\001\016\025\005}!U-];f\005\006\034X\rZ'fgN\fw-Z)vKV,7+Z7b]RL7m\035")
public interface Stash extends UnrestrictedStash, RequiresMessageQueue<DequeBasedMessageQueueSemantics> {}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Stash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */