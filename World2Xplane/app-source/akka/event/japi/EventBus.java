package akka.event.japi;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q2q!\001\002\021\002G\005\021B\001\005Fm\026tGOQ;t\025\t\031A!\001\003kCBL'BA\003\007\003\025)g/\0328u\025\0059\021\001B1lW\006\034\001!\006\003\013um93C\001\001\f!\taq\"D\001\016\025\005q\021!B:dC2\f\027B\001\t\016\005\031\te.\037*fM\")!\003\001D\001'\005I1/\0362tGJL'-\032\013\004)]!\003C\001\007\026\023\t1RBA\004C_>dW-\0318\t\013a\t\002\031A\r\002\025M,(m]2sS\n,'\017\005\002\03371\001A!\002\017\001\005\004i\"!A*\022\005y\t\003C\001\007 \023\t\001SBA\004O_RD\027N\\4\021\0051\021\023BA\022\016\005\r\te.\037\005\006KE\001\rAJ\001\003i>\004\"AG\024\005\013!\002!\031A\017\003\003\rCQA\013\001\007\002-\n1\"\0368tk\n\0348M]5cKR\031A\003L\027\t\013aI\003\031A\r\t\0139J\003\031\001\024\002\t\031\024x.\034\005\006U\0011\t\001\r\013\003cQ\002\"\001\004\032\n\005Mj!\001B+oSRDQ\001G\030A\002eAQA\016\001\007\002]\nq\001];cY&\034\b\016\006\0022q!)Q!\016a\001sA\021!D\017\003\006w\001\021\r!\b\002\002\013\002")
public interface EventBus<E, S, C> {
  boolean subscribe(S paramS, C paramC);
  
  boolean unsubscribe(S paramS, C paramC);
  
  void unsubscribe(S paramS);
  
  void publish(E paramE);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\japi\EventBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */