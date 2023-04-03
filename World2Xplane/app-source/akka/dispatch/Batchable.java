package akka.dispatch;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q1\001\"\001\002\021\002G\005AA\002\002\n\005\006$8\r[1cY\026T!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027mE\002\001\017=\001\"\001C\007\016\003%Q!AC\006\002\t1\fgn\032\006\002\031\005!!.\031<b\023\tq\021B\001\004PE*,7\r\036\t\003\021AI!!E\005\003\021I+hN\\1cY\026DQa\005\001\007\002U\t1\"[:CCR\034\007.\0312mK\016\001Q#\001\f\021\005]QR\"\001\r\013\003e\tQa]2bY\006L!a\007\r\003\017\t{w\016\\3b]\002")
public interface Batchable extends Runnable {
  boolean isBatchable();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Batchable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */