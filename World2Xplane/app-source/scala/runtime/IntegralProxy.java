package scala.runtime;

import scala.collection.immutable.NumericRange;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]3q!\001\002\021\002\007\005qAA\007J]R,wM]1m!J|\0070\037\006\003\007\021\tqA];oi&lWMC\001\006\003\025\0318-\0317b\007\001)\"\001C\n\024\t\001IQ\"\007\t\003\025-i\021\001B\005\003\031\021\0211!\0218z!\rqq\"E\007\002\005%\021\001C\001\002\026'\016\fG.Y,i_2,g*^7cKJ\004&o\034=z!\t\0212\003\004\001\005\013Q\001!\031A\013\003\003Q\013\"AF\005\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\0042A\004\016\022\023\tY\"AA\006SC:<W\r\032)s_bL\b\"B\017\001\t\003q\022A\002\023j]&$H\005F\001 !\tQ\001%\003\002\"\t\t!QK\\5u\021\025\031\003Ab\005%\003\rqW/\\\013\002KA\031aEL\t\017\005\035bcB\001\025,\033\005I#B\001\026\007\003\031a$o\\8u}%\tQ!\003\002.\t\0059\001/Y2lC\036,\027BA\0301\005!Ie\016^3he\006d'BA\027\005\013\021\021\004\001A\032\003#I+7/\0367u/&$\bn\\;u'R,\007\017E\0025sEi\021!\016\006\003m]\n\021\"[7nkR\f'\r\\3\013\005a\"\021AC2pY2,7\r^5p]&\021!(\016\002\r\035VlWM]5d%\006tw-\032\005\006y\001!\t!P\001\006k:$\030\016\034\013\003}\025\0032a\020\"\022\035\t!\004)\003\002Bk\005aa*^7fe&\034'+\0318hK&\0211\t\022\002\n\013b\034G.^:jm\026T!!Q\033\t\013\031[\004\031A\t\002\007\025tG\rC\003=\001\021\005\001\nF\002?\023*CQAR$A\002EAQaS$A\002E\tAa\035;fa\")Q\n\001C\001\035\006\021Ao\034\013\003\037J\0032a\020)\022\023\t\tFIA\005J]\016dWo]5wK\")a\t\024a\001#!)Q\n\001C\001)R\031q*\026,\t\013\031\033\006\031A\t\t\013-\033\006\031A\t")
public interface IntegralProxy<T> extends ScalaWholeNumberProxy<T>, RangedProxy<T> {
  Integral<T> num();
  
  NumericRange.Exclusive<T> until(T paramT);
  
  NumericRange.Exclusive<T> until(T paramT1, T paramT2);
  
  NumericRange.Inclusive<T> to(T paramT);
  
  NumericRange.Inclusive<T> to(T paramT1, T paramT2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\IntegralProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */