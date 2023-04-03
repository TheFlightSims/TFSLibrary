package scala.collection.convert;

import java.util.concurrent.ConcurrentMap;
import scala.collection.mutable.ConcurrentMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r3\001\"\001\002\021\002\007\005\021b\020\002\027\031><\bK]5pe&$\030p\026:ba\006\0338kY1mC*\0211\001B\001\bG>tg/\032:u\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001'\t\001!\002\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032DQa\004\001\005\002A\ta\001J5oSR$C#A\t\021\005-\021\022BA\n\007\005\021)f.\033;\t\013U\001A1\001\f\002C5\f\007/Q:TG\006d\027\rR3qe\026\034\027\r^3e\007>t7-\036:sK:$X*\0319\026\007]\001#\006\006\002\031YA!\021\004\b\020*\033\005Q\"BA\016\005\003\035iW\017^1cY\026L!!\b\016\003\033\r{gnY;se\026tG/T1q!\ty\002\005\004\001\005\013\005\"\"\031\001\022\003\003\005\013\"a\t\024\021\005-!\023BA\023\007\005\035qu\016\0365j]\036\004\"aC\024\n\005!2!aA!osB\021qD\013\003\006WQ\021\rA\t\002\002\005\")Q\006\006a\001]\005\tQ\016\005\0030myIS\"\001\031\013\005E\022\024AC2p]\016,(O]3oi*\0211\007N\001\005kRLGNC\0016\003\021Q\027M^1\n\005u\001\004\006\002\0139wu\002\"aC\035\n\005i2!A\0033faJ,7-\031;fI\006\nA(A.Vg\026\004\003-\\1q\003N\0346-\0317b\007>t7-\036:sK:$X*\0319aA%t7\017^3bI2\002\023M\0343!kN,\007\005Y2p]\016,(O]3oi:j\025\r\0351!S:\034H/Z1eA=4\007\005Y\"p]\016,(O]3oi6\013\007\017\031\030\"\003y\naA\r\0302a9\002\004C\001!B\033\005\021\021B\001\"\003\005-9&/\0319BgN\033\027\r\\1")
public interface LowPriorityWrapAsScala {
  <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\LowPriorityWrapAsScala.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */