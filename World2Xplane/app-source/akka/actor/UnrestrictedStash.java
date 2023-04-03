package akka.actor;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001}2q!\001\002\021\002\007\005qAA\tV]J,7\017\036:jGR,Gm\025;bg\"T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011\003\002\001\t\035I\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005\025\t5\r^8s!\ty1#\003\002\025\005\ta1\013^1tQN+\b\017]8si\")a\003\001C\001/\0051A%\0338ji\022\"\022\001\007\t\003\023eI!A\007\006\003\tUs\027\016\036\005\0069\001!\t%H\001\013aJ,'+Z:uCJ$Hc\001\r\037Y!)qd\007a\001A\0051!/Z1t_:\004\"!I\025\017\005\t:cBA\022'\033\005!#BA\023\007\003\031a$o\\8u}%\t1\"\003\002)\025\0059\001/Y2lC\036,\027B\001\026,\005%!\006N]8xC\ndWM\003\002)\025!)Qf\007a\001]\0059Q.Z:tC\036,\007cA\0050c%\021\001G\003\002\007\037B$\030n\0348\021\005%\021\024BA\032\013\005\r\te.\037\005\006k\001!\teF\001\ta>\034Ho\025;pa\"Iq\007AA\001\002\023%\001hO\001\021gV\004XM\035\023qe\026\024Vm\035;beR$2\001G\035;\021\025yb\0071\001!\021\025ic\0071\001/\023\ta\002\003C\005>\001\005\005\t\021\"\003\030}\005q1/\0369fe\022\002xn\035;Ti>\004\030BA\033\021\001")
public interface UnrestrictedStash extends Actor, StashSupport {
  void akka$actor$UnrestrictedStash$$super$preRestart(Throwable paramThrowable, Option<Object> paramOption);
  
  void akka$actor$UnrestrictedStash$$super$postStop();
  
  void preRestart(Throwable paramThrowable, Option<Object> paramOption);
  
  void postStop();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UnrestrictedStash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */