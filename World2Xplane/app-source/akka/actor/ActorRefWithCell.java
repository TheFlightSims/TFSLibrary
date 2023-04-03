package akka.actor;

import scala.collection.immutable.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001e2q!\001\002\002\002\02111G\001\tBGR|'OU3g/&$\bnQ3mY*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b'\t\001q\001\005\002\t\0235\t!!\003\002\013\005\t\001\022J\034;fe:\fG.Q2u_J\024VM\032\005\006\031\001!\tAD\001\007y%t\027\016\036 \004\001Q\tq\002\005\002\t\001!)\021\003\001D\001%\005QQO\0343fe2L\030N\\4\026\003M\001\"\001\003\013\n\005U\021!\001B\"fY2DQa\006\001\007\002a\t\001b\0315jY\022\024XM\\\013\0023A\031!$I\022\016\003mQ!\001H\017\002\023%lW.\036;bE2,'B\001\020 \003)\031w\016\0347fGRLwN\034\006\002A\005)1oY1mC&\021!e\007\002\t\023R,'/\0312mKB\021\001\002J\005\003K\t\021\001\"Q2u_J\024VM\032\005\006O\0011\t\001K\001\017O\026$8+\0338hY\026\034\005.\0337e)\t9\021\006C\003+M\001\0071&\001\003oC6,\007C\001\0271\035\tic&D\001 \023\tys$\001\004Qe\026$WMZ\005\003cI\022aa\025;sS:<'BA\030 %\r!tB\016\004\005k\001\0011G\001\007=e\0264\027N\\3nK:$h\b\005\002\to%\021\001H\001\002\016\003\016$xN\035*fMN\033w\016]3")
public abstract class ActorRefWithCell extends InternalActorRef {
  public abstract Cell underlying();
  
  public abstract Iterable<ActorRef> children();
  
  public abstract InternalActorRef getSingleChild(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorRefWithCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */