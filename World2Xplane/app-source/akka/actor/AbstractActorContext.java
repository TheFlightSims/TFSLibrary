package akka.actor;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00112q!\001\002\021\002G\005qA\001\013BEN$(/Y2u\003\016$xN]\"p]R,\007\020\036\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\001\001\024\007\001Aa\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021A\"Q2u_J\034uN\034;fqRDQa\005\001\007\002Q\t1bZ3u\007\"LG\016\032:f]R\tQ\003E\002\0277ui\021a\006\006\0031e\tA\001\\1oO*\t!$\001\003kCZ\f\027B\001\017\030\005!IE/\032:bE2,\007CA\b\037\023\ty\"A\001\005BGR|'OU3g\021\025\t\003A\"\001#\003!9W\r^\"iS2$GCA\017$\021\025!\003\0051\001&\003\021q\027-\\3\021\005\031JcBA\005(\023\tA#\"\001\004Qe\026$WMZ\005\003U-\022aa\025;sS:<'B\001\025\013\001")
public interface AbstractActorContext extends ActorContext {
  Iterable<ActorRef> getChildren();
  
  ActorRef getChild(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractActorContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */