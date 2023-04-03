package akka.actor;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0254Q!\001\002\002\002\035\021AaS5mY*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\031B\001\001\005\017%A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003'\005+Ho\034*fG\026Lg/\0323NKN\034\030mZ3\021\005=\031\022B\001\013\003\005=\001vn]:jE2L\b*\031:nMVd\007\"\002\f\001\t\0039\022A\002\037j]&$h\bF\001\031!\ty\001aB\003\033\005!\0055$\001\003LS2d\007CA\b\035\r\025\t!\001#!\036'\021a\002DH\021\021\005%y\022B\001\021\013\005\035\001&o\0343vGR\004\"!\003\022\n\005\rR!\001D*fe&\fG.\033>bE2,\007\"\002\f\035\t\003)C#A\016\t\013\035bB\021\001\025\002\027\035,G/\0238ti\006t7-Z\013\002S9\021q\"\007\005\bWq\t\t\021\"\021-\0035\001(o\0343vGR\004&/\0324jqV\tQ\006\005\002/g5\tqF\003\0021c\005!A.\0318h\025\005\021\024\001\0026bm\006L!\001N\030\003\rM#(/\0338h\021\0351D$!A\005\002]\nA\002\035:pIV\034G/\021:jif,\022\001\017\t\003\023eJ!A\017\006\003\007%sG\017C\004=9\005\005I\021A\037\002\035A\024x\016Z;di\026cW-\\3oiR\021a(\021\t\003\023}J!\001\021\006\003\007\005s\027\020C\004Cw\005\005\t\031\001\035\002\007a$\023\007C\004E9\005\005I\021I#\002\037A\024x\016Z;di&#XM]1u_J,\022A\022\t\004\017*sT\"\001%\013\005%S\021AC2pY2,7\r^5p]&\0211\n\023\002\t\023R,'/\031;pe\"9Q\nHA\001\n\003q\025\001C2b]\026\013X/\0317\025\005=\023\006CA\005Q\023\t\t&BA\004C_>dW-\0318\t\017\tc\025\021!a\001}!9A\013HA\001\n\003*\026\001\0035bg\"\034u\016Z3\025\003aBqa\026\017\002\002\023\005\003,\001\005u_N#(/\0338h)\005i\003b\002.\035\003\003%IaW\001\fe\026\fGMU3t_24X\rF\001]!\tqS,\003\002__\t1qJ\0316fGRD3\001\b1d!\tI\021-\003\002c\025\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003!\032\021\004Y2")
public abstract class Kill implements AutoReceivedMessage, PossiblyHarmful {
  public static boolean canEqual(Object paramObject) {
    return Kill$.MODULE$.canEqual(paramObject);
  }
  
  public static Iterator<Object> productIterator() {
    return Kill$.MODULE$.productIterator();
  }
  
  public static Object productElement(int paramInt) {
    return Kill$.MODULE$.productElement(paramInt);
  }
  
  public static int productArity() {
    return Kill$.MODULE$.productArity();
  }
  
  public static String productPrefix() {
    return Kill$.MODULE$.productPrefix();
  }
  
  public static Kill$ getInstance() {
    return Kill$.MODULE$.getInstance();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Kill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */