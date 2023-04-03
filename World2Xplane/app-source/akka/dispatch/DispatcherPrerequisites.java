package akka.dispatch;

import akka.actor.ActorSystem;
import akka.actor.DynamicAccess;
import akka.actor.Scheduler;
import akka.event.EventStream;
import java.util.concurrent.ThreadFactory;
import scala.Option;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001=3q!\001\002\021\002G\005qAA\fESN\004\030\r^2iKJ\004&/\032:fcVL7/\033;fg*\0211\001B\001\tI&\034\b/\031;dQ*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\r\003\001\022!\004;ie\026\fGMR1di>\024\0300F\001\022!\t\021\022$D\001\024\025\t!R#\001\006d_:\034WO\035:f]RT!AF\f\002\tU$\030\016\034\006\0021\005!!.\031<b\023\tQ2CA\007UQJ,\027\r\032$bGR|'/\037\005\0069\0011\t!H\001\fKZ,g\016^*ue\026\fW.F\001\037!\ty\"%D\001!\025\t\tC!A\003fm\026tG/\003\002$A\tYQI^3oiN#(/Z1n\021\025)\003A\"\001'\003%\0318\r[3ek2,'/F\001(!\tA3&D\001*\025\tQC!A\003bGR|'/\003\002-S\tI1k\0315fIVdWM\035\005\006]\0011\taL\001\016Ift\027-\\5d\003\016\034Wm]:\026\003A\002\"\001K\031\n\005IJ#!\004#z]\006l\027nY!dG\026\0348\017C\0035\001\031\005Q'\001\005tKR$\030N\\4t+\0051\004CA\034;\035\tA\003(\003\002:S\005Y\021i\031;peNK8\017^3n\023\tYDH\001\005TKR$\030N\\4t\025\tI\024\006C\003?\001\031\005q(A\005nC&d'm\034=fgV\t\001\t\005\002B\0056\t!!\003\002D\005\tIQ*Y5mE>DXm\035\005\006\013\0021\tAR\001\030I\0264\027-\0367u\013b,7-\036;j_:\034uN\034;fqR,\022a\022\t\004\023!S\025BA%\013\005\031y\005\017^5p]B\0211*T\007\002\031*\021ACC\005\003\0352\023\001#\022=fGV$\030n\0348D_:$X\r\037;")
public interface DispatcherPrerequisites {
  ThreadFactory threadFactory();
  
  EventStream eventStream();
  
  Scheduler scheduler();
  
  DynamicAccess dynamicAccess();
  
  ActorSystem.Settings settings();
  
  Mailboxes mailboxes();
  
  Option<ExecutionContext> defaultExecutionContext();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DispatcherPrerequisites.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */