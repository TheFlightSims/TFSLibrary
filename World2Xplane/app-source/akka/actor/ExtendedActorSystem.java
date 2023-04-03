package akka.actor;

import java.util.concurrent.ThreadFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001-3Q!\001\002\002\002\035\0211#\022=uK:$W\rZ!di>\0248+_:uK6T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tI!\"D\001\003\023\tY!AA\006BGR|'oU=ti\026l\007\"B\007\001\t\003q\021A\002\037j]&$h\bF\001\020!\tI\001\001C\003\022\001\031\005!#\001\005qe>4\030\016Z3s+\005\031\002CA\005\025\023\t)\"A\001\tBGR|'OU3g!J|g/\0333fe\")q\003\001D\0011\005Aq-^1sI&\fg.F\001\032!\tI!$\003\002\034\005\t\001\022J\034;fe:\fG.Q2u_J\024VM\032\005\006;\0011\t\001G\001\017gf\034H/Z7Hk\006\024H-[1o\021\025y\002A\"\001!\0035\031\030p\035;f[\006\033Go\034:PMR\031\021\005J\025\021\005%\021\023BA\022\003\005!\t5\r^8s%\0264\007\"B\023\037\001\0041\023!\0029s_B\034\bCA\005(\023\tA#AA\003Qe>\0048\017C\003+=\001\0071&\001\003oC6,\007C\001\0273\035\ti\003'D\001/\025\005y\023!B:dC2\f\027BA\031/\003\031\001&/\0323fM&\0211\007\016\002\007'R\024\030N\\4\013\005Er\003\"\002\034\001\r\0039\024!\004;ie\026\fGMR1di>\024\0300F\0019!\tI\004)D\001;\025\tYD(\001\006d_:\034WO\035:f]RT!!\020 \002\tU$\030\016\034\006\002\005!!.\031<b\023\t\t%HA\007UQJ,\027\r\032$bGR|'/\037\005\006\007\0021\t\001R\001\016Ift\027-\\5d\003\016\034Wm]:\026\003\025\003\"!\003$\n\005\035\023!!\004#z]\006l\027nY!dG\026\0348\017\003\004J\001\031\005AAS\001\naJLg\016\036+sK\026,\022a\013")
public abstract class ExtendedActorSystem extends ActorSystem {
  public abstract ActorRefProvider provider();
  
  public abstract InternalActorRef guardian();
  
  public abstract InternalActorRef systemGuardian();
  
  public abstract ActorRef systemActorOf(Props paramProps, String paramString);
  
  public abstract ThreadFactory threadFactory();
  
  public abstract DynamicAccess dynamicAccess();
  
  public abstract String printTree();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ExtendedActorSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */