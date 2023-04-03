package akka.dispatch;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0312q!\001\002\021\002G\005qAA\006NC&d'm\034=UsB,'BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\005\006\037\0011\t\001E\001\007GJ,\027\r^3\025\007E)\002\005\005\002\023'5\t!!\003\002\025\005\taQ*Z:tC\036,\027+^3vK\")aC\004a\001/\005)qn\0368feB\031\021\002\007\016\n\005eQ!AB(qi&|g\016\005\002\034=5\tAD\003\002\036\t\005)\021m\031;pe&\021q\004\b\002\t\003\016$xN\035*fM\")\021E\004a\001E\00511/_:uK6\0042!\003\r$!\tYB%\003\002&9\tY\021i\031;peNK8\017^3n\001")
public interface MailboxType {
  MessageQueue create(Option<ActorRef> paramOption, Option<ActorSystem> paramOption1);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\MailboxType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */