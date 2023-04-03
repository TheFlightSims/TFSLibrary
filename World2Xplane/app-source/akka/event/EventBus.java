package akka.event;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001e2q!\001\002\021\002G\005qA\001\005Fm\026tGOQ;t\025\t\031A!A\003fm\026tGOC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\005\013=\001!\021\001\t\003\013\0253XM\034;\022\005E!\002CA\005\023\023\t\031\"BA\004O_RD\027N\\4\021\005%)\022B\001\f\013\005\r\te.\037\003\0061\001\021\t\001\005\002\013\0072\f7o]5gS\026\024H!\002\016\001\005\003\001\"AC*vEN\034'/\0332fe\")A\004\001D\001;\005I1/\0362tGJL'-\032\013\004=\005*\003CA\005 \023\t\001#BA\004C_>dW-\0318\t\013\tZ\002\031A\022\002\025M,(m]2sS\n,'\017\005\002%35\t\001\001C\003'7\001\007q%\001\002u_B\021Ae\006\005\006S\0011\tAK\001\fk:\034XOY:de&\024W\rF\002\037W1BQA\t\025A\002\rBQ!\f\025A\002\035\nAA\032:p[\")\021\006\001D\001_Q\021\001g\r\t\003\023EJ!A\r\006\003\tUs\027\016\036\005\006E9\002\ra\t\005\006k\0011\tAN\001\baV\024G.[:i)\t\001t\007C\003\004i\001\007\001\b\005\002%\035\001")
public interface EventBus {
  boolean subscribe(Object paramObject1, Object paramObject2);
  
  boolean unsubscribe(Object paramObject1, Object paramObject2);
  
  void unsubscribe(Object paramObject);
  
  void publish(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\EventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */