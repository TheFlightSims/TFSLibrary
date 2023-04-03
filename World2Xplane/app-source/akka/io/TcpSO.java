package akka.io;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\021;Q!\001\002\t\002\035\tQ\001V2q'>S!a\001\003\002\005%|'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005\025!6\r]*P'\rIAB\005\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\021\005MibB\001\013\034\035\t)\"D\004\002\02735\tqC\003\002\031\r\0051AH]8pizJ\021!B\005\003\007\021I!\001\b\002\002\t%sW\r^\005\003=}\021qbU8KCZ\fg)Y2u_JLWm\035\006\0039\tAQ!I\005\005\002\t\na\001P5oSRtD#A\004\t\013\021JA\021A\023\002\023-,W\r]!mSZ,GC\001\0242!\t9cF\004\002)W9\021\001\"K\005\003U\t\t1\001V2q\023\taS&\001\002T\037*\021!FA\005\003_A\022\021bS3fa\006c\027N^3\013\0051j\003\"\002\032$\001\004\031\024AA8o!\tiA'\003\0026\035\t9!i\\8mK\006t\007\"B\034\n\t\003A\024!C8pE&sG.\0338f)\tID\b\005\002(u%\0211\b\r\002\n\037>\023\025J\0347j]\026DQA\r\034A\002MBQAP\005\005\002}\n!\002^2q\035>$U\r\\1z)\t\0015\t\005\002(\003&\021!\t\r\002\013)\016\004hj\034#fY\006L\b\"\002\032>\001\004\031\004")
public final class TcpSO {
  public static Inet.SO$.rafficClass trafficClass(int paramInt) {
    return TcpSO$.MODULE$.trafficClass(paramInt);
  }
  
  public static Inet.SO$.endBufferSize sendBufferSize(int paramInt) {
    return TcpSO$.MODULE$.sendBufferSize(paramInt);
  }
  
  public static Inet.SO$.euseAddress reuseAddress(boolean paramBoolean) {
    return TcpSO$.MODULE$.reuseAddress(paramBoolean);
  }
  
  public static Inet.SO$.eceiveBufferSize receiveBufferSize(int paramInt) {
    return TcpSO$.MODULE$.receiveBufferSize(paramInt);
  }
  
  public static Tcp.SO$.cpNoDelay tcpNoDelay(boolean paramBoolean) {
    return TcpSO$.MODULE$.tcpNoDelay(paramBoolean);
  }
  
  public static Tcp.SO$.OBInline oobInline(boolean paramBoolean) {
    return TcpSO$.MODULE$.oobInline(paramBoolean);
  }
  
  public static Tcp.SO$.eepAlive keepAlive(boolean paramBoolean) {
    return TcpSO$.MODULE$.keepAlive(paramBoolean);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpSO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */