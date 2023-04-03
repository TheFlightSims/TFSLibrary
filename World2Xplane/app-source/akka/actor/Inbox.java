package akka.actor;

import scala.concurrent.duration.FiniteDuration;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0313Q!\001\002\002\002\035\021Q!\0238c_bT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fM\")q\002\001C\001!\0051A(\0338jiz\"\022!\005\t\003%\001i\021A\001\005\006)\0011\t!F\001\be\026\034W-\033<f)\t1\022\004\005\002\n/%\021\001D\003\002\004\003:L\b\"\002\016\024\001\004Y\022aA7bqB\021A$I\007\002;)\021adH\001\tIV\024\030\r^5p]*\021\001EC\001\013G>t7-\036:sK:$\030B\001\022\036\00591\025N\\5uK\022+(/\031;j_:DQ\001\n\001\007\002\025\nQa^1uG\"$\"AJ\025\021\005%9\023B\001\025\013\005\021)f.\033;\t\013)\032\003\031A\026\002\rQ\f'oZ3u!\t\021B&\003\002.\005\tA\021i\031;peJ+g\rC\0030\001\031\005\001'\001\004hKR\024VM\032\013\002W!)!\007\001D\001g\005!1/\0328e)\r1C'\016\005\006UE\002\ra\013\005\006mE\002\r\001C\001\004[N<w!\002\035\003\021\003I\024!B%oE>D\bC\001\n;\r\025\t!\001#\001<'\tQ\004\002C\003\020u\021\005Q\bF\001:\021\025y$\b\"\001A\003\031\031'/Z1uKR\021\021#\021\005\006\005z\002\raQ\001\007gf\034H/Z7\021\005I!\025BA#\003\005-\t5\r^8s'f\034H/Z7")
public abstract class Inbox {
  public static Inbox create(ActorSystem paramActorSystem) {
    return Inbox$.MODULE$.create(paramActorSystem);
  }
  
  public abstract Object receive(FiniteDuration paramFiniteDuration);
  
  public abstract void watch(ActorRef paramActorRef);
  
  public abstract ActorRef getRef();
  
  public abstract void send(ActorRef paramActorRef, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Inbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */