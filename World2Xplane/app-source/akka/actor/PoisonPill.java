package akka.actor;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0254Q!\001\002\002\002\035\021!\002U8jg>t\007+\0337m\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M!\001\001\003\b\023!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\024\003V$xNU3dK&4X\rZ'fgN\fw-\032\t\003\037MI!\001\006\002\003\037A{7o]5cYfD\025M]7gk2DQA\006\001\005\002]\ta\001P5oSRtD#\001\r\021\005=\001q!\002\016\003\021\003[\022A\003)pSN|g\016U5mYB\021q\002\b\004\006\003\tA\t)H\n\0059aq\022\005\005\002\n?%\021\001E\003\002\b!J|G-^2u!\tI!%\003\002$\025\ta1+\032:jC2L'0\0312mK\")a\003\bC\001KQ\t1\004C\003(9\021\005\001&A\006hKRLen\035;b]\016,W#A\025\017\005=I\002bB\026\035\003\003%\t\005L\001\016aJ|G-^2u!J,g-\033=\026\0035\002\"AL\032\016\003=R!\001M\031\002\t1\fgn\032\006\002e\005!!.\031<b\023\t!tF\001\004TiJLgn\032\005\bmq\t\t\021\"\0018\0031\001(o\0343vGR\f%/\033;z+\005A\004CA\005:\023\tQ$BA\002J]RDq\001\020\017\002\002\023\005Q(\001\bqe>$Wo\031;FY\026lWM\034;\025\005y\n\005CA\005@\023\t\001%BA\002B]fDqAQ\036\002\002\003\007\001(A\002yIEBq\001\022\017\002\002\023\005S)A\bqe>$Wo\031;Ji\026\024\030\r^8s+\0051\005cA$K}5\t\001J\003\002J\025\005Q1m\0347mK\016$\030n\0348\n\005-C%\001C%uKJ\fGo\034:\t\0175c\022\021!C\001\035\006A1-\0318FcV\fG\016\006\002P%B\021\021\002U\005\003#*\021qAQ8pY\026\fg\016C\004C\031\006\005\t\031\001 \t\017Qc\022\021!C!+\006A\001.Y:i\007>$W\rF\0019\021\0359F$!A\005Ba\013\001\002^8TiJLgn\032\013\002[!9!\fHA\001\n\023Y\026a\003:fC\022\024Vm]8mm\026$\022\001\030\t\003]uK!AX\030\003\r=\023'.Z2uQ\ra\002m\031\t\003\023\005L!A\031\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001)\007e\0017\r")
public abstract class PoisonPill implements AutoReceivedMessage, PossiblyHarmful {
  public static boolean canEqual(Object paramObject) {
    return PoisonPill$.MODULE$.canEqual(paramObject);
  }
  
  public static Iterator<Object> productIterator() {
    return PoisonPill$.MODULE$.productIterator();
  }
  
  public static Object productElement(int paramInt) {
    return PoisonPill$.MODULE$.productElement(paramInt);
  }
  
  public static int productArity() {
    return PoisonPill$.MODULE$.productArity();
  }
  
  public static String productPrefix() {
    return PoisonPill$.MODULE$.productPrefix();
  }
  
  public static PoisonPill$ getInstance() {
    return PoisonPill$.MODULE$.getInstance();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PoisonPill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */