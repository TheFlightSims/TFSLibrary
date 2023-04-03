package scala.runtime;

import scala.Proxy;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001i2q!\001\002\021\002G\005qAA\006SC:<W\r\032)s_bL(BA\002\005\003\035\021XO\034;j[\026T\021!B\001\006g\016\fG.Y\002\001+\tAacE\002\001\0235\001\"AC\006\016\003\021I!\001\004\003\003\007\005s\027\020E\002\017#Qq!AC\b\n\005A!\021!\002)s_bL\030B\001\n\024\005\025!\026\020]3e\025\t\001B\001\005\002\026-1\001A!B\f\001\005\004A\"!\001+\022\005eI\001C\001\006\033\023\tYBAA\004O_RD\027N\\4\005\013u\001!\021\001\r\003#I+7/\0367u/&$\bn\\;u'R,\007\017C\003 \001\031\005\001%A\003v]RLG\016\006\002\"GA\021!\005H\007\002\001!)AE\ba\001)\005\031QM\0343\t\013}\001a\021\001\024\025\007\035z\003\007E\002)[Qi\021!\013\006\003U-\n\021\"[7nkR\f'\r\\3\013\0051\"\021AC2pY2,7\r^5p]&\021a&\013\002\013\023:$W\r_3e'\026\f\b\"\002\023&\001\004!\002\"B\031&\001\004!\022\001B:uKBDQa\r\001\007\002Q\n!\001^8\025\005\005*\004\"\002\0233\001\004!\002\"B\032\001\r\0039DcA\0249s!)AE\016a\001)!)\021G\016a\001)\001")
public interface RangedProxy<T> extends Proxy.Typed<T> {
  Object until(T paramT);
  
  IndexedSeq<T> until(T paramT1, T paramT2);
  
  Object to(T paramT);
  
  IndexedSeq<T> to(T paramT1, T paramT2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RangedProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */