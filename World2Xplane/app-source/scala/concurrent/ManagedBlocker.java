package scala.concurrent;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001u1q!\001\002\021\002G\005qA\001\bNC:\fw-\0323CY>\0347.\032:\013\005\r!\021AC2p]\016,(O]3oi*\tQ!A\003tG\006d\027m\001\001\024\005\001A\001CA\005\013\033\005!\021BA\006\005\005\031\te.\037*fM\")Q\002\001D\001\035\005)!\r\\8dWR\tq\002\005\002\n!%\021\021\003\002\002\b\005>|G.Z1o\021\025\031\002A\"\001\025\0031I7OU3mK\006\034\030M\0317f+\005y\001\006\002\001\0273m\001\"!C\f\n\005a!!A\0033faJ,7-\031;fI\006\n!$A\fVg\026\004\003M\0317pG.Lgn\0321!S:\034H/Z1e]\005\nA$\001\0043]E\002d\006\r")
public interface ManagedBlocker {
  boolean block();
  
  boolean isReleasable();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ManagedBlocker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */