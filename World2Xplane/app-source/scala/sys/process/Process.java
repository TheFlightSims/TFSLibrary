package scala.sys.process;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001%2q!\001\002\021\002G\005\021BA\004Qe>\034Wm]:\013\005\r!\021a\0029s_\016,7o\035\006\003\013\031\t1a]=t\025\0059\021!B:dC2\f7\001A\n\003\001)\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g\021\025y\001A\"\001\021\003%)\0070\033;WC2,X\rF\001\022!\tY!#\003\002\024\r\t\031\021J\034;\t\013U\001a\021\001\f\002\017\021,7\017\036:psR\tq\003\005\002\f1%\021\021D\002\002\005+:LGoB\003\034\005!\005A$A\004Qe>\034Wm]:\021\005uqR\"\001\002\007\013\005\021\001\022A\020\024\tyQ\001e\t\t\003;\005J!A\t\002\003\027A\023xnY3tg&k\007\017\034\t\003;\021J!!\n\002\003\037A\023xnY3tg\016\023X-\031;j_:DQa\n\020\005\002!\na\001P5oSRtD#\001\017")
public interface Process {
  int exitValue();
  
  void destroy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\Process.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */