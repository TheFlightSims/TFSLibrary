package akka.io;

import akka.actor.ActorRef;
import akka.util.ByteString;
import java.net.InetSocketAddress;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]<Q!\001\002\t\002\035\t!\"\0263q\033\026\0348/Y4f\025\t\031A!\001\002j_*\tQ!\001\003bW.\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\013+\022\004X*Z:tC\036,7CA\005\r!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fM\")1#\003C\001)\0051A(\0338jiz\"\022a\002\005\006-%!\taF\001\006]>\f5m\033\013\0031}\001\"!\007\017\017\005!Q\022BA\016\003\003\r)F\r]\005\003;y\021QAT8BG.T!a\007\002\t\013\001*\002\031\001\007\002\013Q|7.\0328\t\013YIA\021\001\022\026\003aAQ\001J\005\005\002\025\nAa]3oIR!a%K\031<!\tIr%\003\002)=\t91i\\7nC:$\007\"\002\026$\001\004Y\023a\0029bs2|\027\r\032\t\003Y=j\021!\f\006\003]\021\tA!\036;jY&\021\001'\f\002\013\005f$Xm\025;sS:<\007\"\002\032$\001\004\031\024A\002;be\036,G\017\005\0025s5\tQG\003\0027o\005\031a.\032;\013\003a\nAA[1wC&\021!(\016\002\022\023:,GoU8dW\026$\030\t\0323sKN\034\b\"\002\037$\001\004i\024aA1dWB\021\021DP\005\003y\021Q!\022<f]RDQ\001J\005\005\002\005#2A\n\"D\021\025Q\003\t1\001,\021\025\021\004\t1\0014\021\025)\025\002\"\001G\003\021\021\027N\0343\025\t\031:u*\025\005\006\021\022\003\r!S\001\bQ\006tG\r\\3s!\tQU*D\001L\025\taE!A\003bGR|'/\003\002O\027\nA\021i\031;peJ+g\rC\003Q\t\002\0071'\001\005f]\022\004x.\0338u\021\025\021F\t1\001T\003\035y\007\017^5p]N\0042\001V,Z\033\005)&B\001,8\003\021a\027M\\4\n\005a+&\001C%uKJ\f'\r\\3\021\005i#gBA.c\035\ta\026M\004\002^A6\taL\003\002`\r\0051AH]8pizJ\021!B\005\003\007\021I!a\031\002\002\t%sW\r^\005\003K\032\024AbU8dW\026$x\n\035;j_:T!a\031\002\t\013\025KA\021\0015\025\007\031J'\016C\003IO\002\007\021\nC\003QO\002\0071\007C\003m\023\021\005Q.\001\004v]\nLg\016Z\013\002M!)q.\003C\001a\006a1/[7qY\026\034VM\0343feR\021a%\035\005\006%:\004\ra\025\005\006_&!\t!\034\005\006i&!\t!\\\001\017gV\034\b/\0328e%\026\fG-\0338h\021\0251\030\002\"\001n\0035\021Xm];nKJ+\027\rZ5oO\002")
public final class UdpMessage {
  public static Udp.Command resumeReading() {
    return UdpMessage$.MODULE$.resumeReading();
  }
  
  public static Udp.Command suspendReading() {
    return UdpMessage$.MODULE$.suspendReading();
  }
  
  public static Udp.Command simpleSender() {
    return UdpMessage$.MODULE$.simpleSender();
  }
  
  public static Udp.Command simpleSender(Iterable<Inet.SocketOption> paramIterable) {
    return UdpMessage$.MODULE$.simpleSender(paramIterable);
  }
  
  public static Udp.Command unbind() {
    return UdpMessage$.MODULE$.unbind();
  }
  
  public static Udp.Command bind(ActorRef paramActorRef, InetSocketAddress paramInetSocketAddress) {
    return UdpMessage$.MODULE$.bind(paramActorRef, paramInetSocketAddress);
  }
  
  public static Udp.Command bind(ActorRef paramActorRef, InetSocketAddress paramInetSocketAddress, Iterable<Inet.SocketOption> paramIterable) {
    return UdpMessage$.MODULE$.bind(paramActorRef, paramInetSocketAddress, paramIterable);
  }
  
  public static Udp.Command send(ByteString paramByteString, InetSocketAddress paramInetSocketAddress) {
    return UdpMessage$.MODULE$.send(paramByteString, paramInetSocketAddress);
  }
  
  public static Udp.Command send(ByteString paramByteString, InetSocketAddress paramInetSocketAddress, Udp.Event paramEvent) {
    return UdpMessage$.MODULE$.send(paramByteString, paramInetSocketAddress, paramEvent);
  }
  
  public static Udp.NoAck noAck() {
    return UdpMessage$.MODULE$.noAck();
  }
  
  public static Udp.NoAck noAck(Object paramObject) {
    return UdpMessage$.MODULE$.noAck(paramObject);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */