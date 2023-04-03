package akka.io;

import akka.actor.ActorRef;
import java.nio.channels.SelectableChannel;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00192\001\"\001\002\021\002G\005!A\002\002\020\007\"\fgN\\3m%\026<\027n\035;ss*\0211\001B\001\003S>T\021!B\001\005C.\\\027m\005\002\001\017A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032DQA\004\001\007\002A\t\001B]3hSN$XM]\002\001)\r\tR$\013\013\003%U\001\"\001C\n\n\005QI!\001B+oSRDQAF\007A\004]\tAb\0315b]:,G.Q2u_J\004\"\001G\016\016\003eQ!A\007\003\002\013\005\034Go\034:\n\005qI\"\001C!di>\024(+\0324\t\013yi\001\031A\020\002\017\rD\027M\0348fYB\021\001eJ\007\002C)\021!eI\001\tG\"\fgN\\3mg*\021A%J\001\004]&|'\"\001\024\002\t)\fg/Y\005\003Q\005\022\021cU3mK\016$\030M\0317f\007\"\fgN\\3m\021\025QS\0021\001,\003)Ig.\033;jC2|\005o\035\t\003\0211J!!L\005\003\007%sG\017")
public interface ChannelRegistry {
  void register(SelectableChannel paramSelectableChannel, int paramInt, ActorRef paramActorRef);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\ChannelRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */