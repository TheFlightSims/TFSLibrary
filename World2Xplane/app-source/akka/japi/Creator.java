package akka.japi;

import scala.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001u2q!\001\002\021\002G\005qAA\004De\026\fGo\034:\013\005\r!\021\001\0026ba&T\021!B\001\005C.\\\027m\001\001\026\005!92c\001\001\n\037A\021!\"D\007\002\027)\tA\"A\003tG\006d\027-\003\002\017\027\t1\021I\\=SK\032\004\"A\003\t\n\005EY!\001D*fe&\fG.\033>bE2,\007\"B\n\001\r\003!\022AB2sK\006$X\rF\001\026!\t1r\003\004\001\005\013a\001!\031A\r\003\003Q\013\"AG\017\021\005)Y\022B\001\017\f\005\035qu\016\0365j]\036\004\"A\003\020\n\005}Y!aA!os\"\032!#\t\030\021\007)\021C%\003\002$\027\t1A\017\033:poN\004\"AF\023\005\013a\001!\031\001\024\022\005i9\003C\001\025,\035\tQ\021&\003\002+\027\0059\001/Y2lC\036,\027B\001\027.\005%!\006N]8xC\ndWM\003\002+\027\r\nq\006\005\0021m9\021\021'\013\b\003eUj\021a\r\006\003i\031\ta\001\020:p_Rt\024\"\001\007\n\005]j#!C#yG\026\004H/[8oQ\r\001\021\b\020\t\003\025iJ!aO\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001")
public interface Creator<T> extends Serializable {
  public static final long serialVersionUID = 1L;
  
  T create() throws Exception;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Creator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */