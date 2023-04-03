package akka.japi;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001i2q!\001\002\021\002G\005qAA\005Qe>\034W\rZ;sK*\0211\001B\001\005U\006\004\030NC\001\006\003\021\t7n[1\004\001U\021\001\"G\n\003\001%\001\"AC\007\016\003-Q\021\001D\001\006g\016\fG.Y\005\003\035-\021a!\0218z%\0264\007\"\002\t\001\r\003\t\022!B1qa2LHC\001\n\026!\tQ1#\003\002\025\027\t!QK\\5u\021\0251r\0021\001\030\003\025\001\030M]1n!\tA\022\004\004\001\005\013i\001!\031A\016\003\003Q\013\"\001H\020\021\005)i\022B\001\020\f\005\035qu\016\0365j]\036\004\"A\003\021\n\005\005Z!aA!os\"\032qb\t\031\021\007)!c%\003\002&\027\t1A\017\033:poN\004\"\001G\024\005\013i\001!\031\001\025\022\005qI\003C\001\026.\035\tQ1&\003\002-\027\0059\001/Y2lC\036,\027B\001\0300\005%!\006N]8xC\ndWM\003\002-\027\r\n\021\007\005\0023q9\0211g\013\b\003i]j\021!\016\006\003m\031\ta\001\020:p_Rt\024\"\001\007\n\005ez#!C#yG\026\004H/[8o\001")
public interface Procedure<T> {
  void apply(T paramT) throws Exception;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Procedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */