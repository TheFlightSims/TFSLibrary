package scala.collection.parallel;

import scala.Function0;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001I3q!\001\002\021\002\007\005\021BA\013Fq\026\034W\017^5p]\016{g\016^3yiR\0137o[:\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\024\007\001Qa\002\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\013Q\0137o[:\t\013M\001A\021\001\013\002\r\021Jg.\033;%)\005)\002CA\006\027\023\t9bA\001\003V]&$\b\"B\r\001\t\003Q\022\001E3yK\016,H/[8o\007>tG/\032=u+\005Y\002C\001\017 \033\005i\"B\001\020\007\003)\031wN\\2veJ,g\016^\005\003Au\021\001#\022=fGV$\030n\0348D_:$X\r\037;\t\017\t\002!\031!D\0015\005YQM\034<je>tW.\0328u\021\035!\003A1A\005\002\025\na\001\032:jm\026\024X#\001\b\t\r\035\002\001\025!\003\017\003\035!'/\033<fe\002BQ!\013\001\005\002)\nq!\032=fGV$X-F\002,c\001#\"\001\f\036\021\007-is&\003\002/\r\tIa)\0368di&|g\016\r\t\003aEb\001\001B\0033Q\t\0071GA\001S#\t!t\007\005\002\fk%\021aG\002\002\b\035>$\b.\0338h!\tY\001(\003\002:\r\t\031\021I\\=\t\013mB\003\031\001\037\002\tQ\f7o\033\t\005\037uzs(\003\002?\005\t!A+Y:l!\t\001\004\tB\003BQ\t\0071G\001\002Ua\")1\t\001C\001\t\006!R\r_3dkR,\027I\0343XC&$(+Z:vYR,2!R$L)\t1\005\n\005\0021\017\022)!G\021b\001g!)1H\021a\001\023B!q\"\020$K!\t\0014\nB\003B\005\n\0071\007C\003N\001\021\005a*\001\tqCJ\fG\016\\3mSNlG*\032<fYV\tq\n\005\002\f!&\021\021K\002\002\004\023:$\b")
public interface ExecutionContextTasks extends Tasks {
  void scala$collection$parallel$ExecutionContextTasks$_setter_$driver_$eq(Tasks paramTasks);
  
  ExecutionContext executionContext();
  
  ExecutionContext environment();
  
  Tasks driver();
  
  <R, Tp> Function0<R> execute(Task<R, Tp> paramTask);
  
  <R, Tp> R executeAndWaitResult(Task<R, Tp> paramTask);
  
  int parallelismLevel();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ExecutionContextTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */