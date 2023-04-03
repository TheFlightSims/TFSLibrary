package akka.io;

import akka.actor.ActorRef;
import akka.util.ByteString;
import java.net.InetSocketAddress;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005mq!B\001\003\021\0039\021aE+ea\016{gN\\3di\026$W*Z:tC\036,'BA\002\005\003\tIwNC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t\031R\013\0329D_:tWm\031;fI6+7o]1hKN\021\021\002\004\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\t\013MIA\021\001\013\002\rqJg.\033;?)\0059\001\"\002\f\n\t\0039\022aB2p]:,7\r\036\013\0061}9\023g\r\t\0033qq!\001\003\016\n\005m\021\021\001D+ea\016{gN\\3di\026$\027BA\017\037\005\035\031u.\\7b]\022T!a\007\002\t\013\001*\002\031A\021\002\017!\fg\016\0327feB\021!%J\007\002G)\021A\005B\001\006C\016$xN]\005\003M\r\022\001\"Q2u_J\024VM\032\005\006QU\001\r!K\001\016e\026lw\016^3BI\022\024Xm]:\021\005)zS\"A\026\013\0051j\023a\0018fi*\ta&\001\003kCZ\f\027B\001\031,\005EIe.\032;T_\016\\W\r^!eIJ,7o\035\005\006eU\001\r!K\001\rY>\034\027\r\\!eIJ,7o\035\005\006iU\001\r!N\001\b_B$\030n\0348t!\r1\024hO\007\002o)\021\001(L\001\005Y\006tw-\003\002;o\tA\021\n^3sC\ndW\r\005\002=\r:\021Q\b\022\b\003}\rs!a\020\"\016\003\001S!!\021\004\002\rq\022xn\034;?\023\005)\021BA\002\005\023\t)%!\001\003J]\026$\030BA$I\0051\031vnY6fi>\003H/[8o\025\t)%\001C\003\027\023\021\005!\n\006\003\031\0272k\005\"\002\021J\001\004\t\003\"\002\025J\001\004I\003\"\002\033J\001\004)\004\"\002\f\n\t\003yEc\001\rQ#\")\001E\024a\001C!)\001F\024a\001S!)1+\003C\001)\006!1/\0328e)\rAR+\030\005\006-J\003\raV\001\005I\006$\030\r\005\002Y76\t\021L\003\002[\t\005!Q\017^5m\023\ta\026L\001\006CsR,7\013\036:j]\036DQA\030*A\0021\t1!Y2l\021\025\031\026\002\"\001a)\tA\022\rC\003W?\002\007q\013C\003d\023\021\005A-\001\006eSN\034wN\0348fGR,\022\001\007\005\006M&!\taZ\001\006]>\f5m\033\013\003Q.\004\"!G5\n\005)t\"!\002(p\003\016\\\007\"\0027f\001\004a\021!\002;pW\026t\007\"\0024\n\t\003qW#\0015\t\013ALA\021\0013\002\035M,8\017]3oIJ+\027\rZ5oO\")!/\003C\001I\006i!/Z:v[\026\024V-\0313j]\036DQ\001^\005\005\fU\f\001B\032:p[*\013g/Y\013\004m\006\rAcA<\002\026A\031\0010`@\016\003eT!A_>\002\023%lW.\036;bE2,'B\001?\017\003)\031w\016\0347fGRLwN\\\005\003}f\0241\002\026:bm\026\0248/\0312mKB!\021\021AA\002\031\001!q!!\002t\005\004\t9AA\001U#\021\tI!a\004\021\0075\tY!C\002\002\0169\021qAT8uQ&tw\rE\002\016\003#I1!a\005\017\005\r\te.\037\005\b\003/\031\b\031AA\r\003\021\031w\016\0347\021\007YJt\020")
public final class UdpConnectedMessage {
  public static UdpConnected.Command resumeReading() {
    return UdpConnectedMessage$.MODULE$.resumeReading();
  }
  
  public static UdpConnected.Command suspendReading() {
    return UdpConnectedMessage$.MODULE$.suspendReading();
  }
  
  public static UdpConnected.NoAck noAck() {
    return UdpConnectedMessage$.MODULE$.noAck();
  }
  
  public static UdpConnected.NoAck noAck(Object paramObject) {
    return UdpConnectedMessage$.MODULE$.noAck(paramObject);
  }
  
  public static UdpConnected.Command disconnect() {
    return UdpConnectedMessage$.MODULE$.disconnect();
  }
  
  public static UdpConnected.Command send(ByteString paramByteString) {
    return UdpConnectedMessage$.MODULE$.send(paramByteString);
  }
  
  public static UdpConnected.Command send(ByteString paramByteString, Object paramObject) {
    return UdpConnectedMessage$.MODULE$.send(paramByteString, paramObject);
  }
  
  public static UdpConnected.Command connect(ActorRef paramActorRef, InetSocketAddress paramInetSocketAddress) {
    return UdpConnectedMessage$.MODULE$.connect(paramActorRef, paramInetSocketAddress);
  }
  
  public static UdpConnected.Command connect(ActorRef paramActorRef, InetSocketAddress paramInetSocketAddress, Iterable<Inet.SocketOption> paramIterable) {
    return UdpConnectedMessage$.MODULE$.connect(paramActorRef, paramInetSocketAddress, paramIterable);
  }
  
  public static UdpConnected.Command connect(ActorRef paramActorRef, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, Iterable<Inet.SocketOption> paramIterable) {
    return UdpConnectedMessage$.MODULE$.connect(paramActorRef, paramInetSocketAddress1, paramInetSocketAddress2, paramIterable);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnectedMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */