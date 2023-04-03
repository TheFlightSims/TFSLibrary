package akka.actor;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]1q!\001\002\021\002G\005qAA\006DC:\034W\r\0347bE2,'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032DQa\004\001\007\002A\taaY1oG\026dG#A\t\021\005%\021\022BA\n\013\005\035\021un\0347fC:DQ!\006\001\007\002Y\t1\"[:DC:\034W\r\0347fIV\t\021\003")
public interface Cancellable {
  boolean cancel();
  
  boolean isCancelled();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Cancellable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */