package scala.concurrent;

import java.util.concurrent.TimeoutException;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q3q!\001\002\021\002G\005qAA\005Bo\006LG/\0312mK*\0211\001B\001\013G>t7-\036:sK:$(\"A\003\002\013M\034\027\r\\1\004\001U\021\001\"T\n\003\001%\001\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g\021\025q\001A\"\001\020\003\025\021X-\0313z)\t\001\002\004\006\002\022%5\t\001\001C\003\024\033\001\017A#\001\004qKJl\027\016\036\t\003+Yi\021AA\005\003/\t\021\001bQ1o\003^\f\027\016\036\005\00635\001\rAG\001\007CRlun\035;\021\005mqR\"\001\017\013\005u\021\021\001\0033ve\006$\030n\0348\n\005}a\"\001\003#ve\006$\030n\0348)\0075\t3\007E\002\013E\021J!a\t\003\003\rQD'o\\<t!\t)c\005\004\001\005\013\035\002!\031\001\025\003\003Q\013\"!\013\027\021\005)Q\023BA\026\005\005\035qu\016\0365j]\036\004\"!\f\031\017\005)q\023BA\030\005\003\035\001\030mY6bO\026L!!\r\032\003\023QC'o\\<bE2,'BA\030\005G\005!\004CA\033<\035\t1dF\004\0028u5\t\001H\003\002:\r\0051AH]8pizJ\021!B\005\003yI\022A#\0238uKJ\024X\017\035;fI\026C8-\0329uS>t\007fA\007?\003B\031!BI \021\005\025\002E!B\024\001\005\004A3%\001\"\021\005\r+eBA\013E\023\ty#!\003\002G\017\n\001B+[7f_V$X\t_2faRLwN\034\006\003_\tAQ!\023\001\007\002)\013aA]3tk2$HCA&T)\ta%\013\005\002&\033\0221q\005\001CC\0029\013\"!K(\021\005)\001\026BA)\005\005\r\te.\037\005\006'!\003\035\001\006\005\0063!\003\rA\007\025\004\021VC\006c\001\006#-B\021Qe\026\003\006O\001\021\r\001K\022\0023B\021QGW\005\0037J\022\021\"\022=dKB$\030n\0348")
public interface Awaitable<T> {
  Awaitable<T> ready(Duration paramDuration, CanAwait paramCanAwait) throws TimeoutException, InterruptedException;
  
  T result(Duration paramDuration, CanAwait paramCanAwait) throws Exception;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Awaitable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */