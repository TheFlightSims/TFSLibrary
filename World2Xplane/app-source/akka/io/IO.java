package akka.io;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.ExtensionId;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q:Q!\001\002\t\002\035\t!!S(\013\005\r!\021AA5p\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\002J\037N\021\021\002\004\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\t\013MIA\021\001\013\002\rqJg.\033;?)\0059aa\002\f\n!\003\r\na\006\002\n\013b$XM\\:j_:\0342!\006\007\031!\tIB$D\001\033\025\tYB!A\003bGR|'/\003\002\0275!)a$\006D\001?\0059Q.\0318bO\026\024X#\001\021\021\005e\t\023B\001\022\033\005!\t5\r^8s%\0264\007\"\002\023\n\t\003)\023!B1qa2LXC\001\0245)\t9S\006\006\002!Q!)\021f\ta\002U\00511/_:uK6\004\"!G\026\n\0051R\"aC!di>\0248+_:uK6DQAL\022A\002=\n1a[3z!\rI\002GM\005\003ci\0211\"\022=uK:\034\030n\0348JIB\0211\007\016\007\001\t\025)4E1\0017\005\005!\026CA\034;!\ti\001(\003\002:\035\t9aj\034;iS:<\007CA\036\026\033\005I\001")
public final class IO {
  public static <T extends Extension> ActorRef apply(ExtensionId<T> paramExtensionId, ActorSystem paramActorSystem) {
    return IO$.MODULE$.apply(paramExtensionId, paramActorSystem);
  }
  
  public static interface Extension extends akka.actor.Extension {
    ActorRef manager();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\IO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */