package akka.dispatch.sysmsg;

import akka.actor.PossiblyHarmful;
import scala.Serializable;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\006\00193\001\"\001\002\021\002\007\005b\001\003\002\016'f\034H/Z7NKN\034\030mZ3\013\005\r!\021AB:zg6\034xM\003\002\006\r\005AA-[:qCR\034\007NC\001\b\003\021\t7n[1\024\t\001Iq\"\006\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\021\005A\031R\"A\t\013\005I1\021!B1di>\024\030B\001\013\022\005=\001vn]:jE2L\b*\031:nMVd\007C\001\006\027\023\t92B\001\007TKJL\027\r\\5{C\ndW\rC\003\032\001\021\0051$\001\004%S:LG\017J\002\001)\005a\002C\001\006\036\023\tq2B\001\003V]&$\bB\003\021\001\001\004\005\r\021\"\001\003C\005!a.\032=u+\005\021\003CA\022\001\033\005\021\001BC\023\001\001\004\005\r\021\"\001\003M\005Aa.\032=u?\022*\027\017\006\002\035O!9\001\006JA\001\002\004\021\023a\001=%c!1!\006\001Q!\n\t\nQA\\3yi\002B#!\013\027\021\005)i\023B\001\030\f\005%!(/\0318tS\026tG\017C\0031\001\021\0051$\001\004v]2Lgn\033\005\006e\001!\taM\001\tk:d\027N\\6fIV\tA\007\005\002\013k%\021ag\003\002\b\005>|G.Z1oS1\001\001H\017\037?\001\n#e\t\023&M\023\tI$A\001\004De\026\fG/Z\005\003w\t\021a\003R3bi\"<\026\r^2i\035>$\030NZ5dCRLwN\\\005\003{\t\021aAR1jY\026$'BA \003\003%qu.T3tg\006<W-\003\002B\005\tA!+Z2sK\006$X-\003\002D\005\t1!+Z:v[\026L!!\022\002\003\023M+\b/\032:wSN,\027BA$\003\005\035\031Vo\0359f]\022L!!\023\002\003\023Q+'/\\5oCR,\027BA&\003\005\035)fn^1uG\"L!!\024\002\003\013]\013Go\0315")
public interface SystemMessage extends PossiblyHarmful, Serializable {
  SystemMessage next();
  
  @TraitSetter
  void next_$eq(SystemMessage paramSystemMessage);
  
  void unlink();
  
  boolean unlinked();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\SystemMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */