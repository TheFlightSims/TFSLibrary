package scala.concurrent;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y2q!\001\002\021\002G\005qA\001\006UCN\\'+\0368oKJT!a\001\003\002\025\r|gnY;se\026tGOC\001\006\003\025\0318-\0317b\007\001\031\"\001\001\005\021\005%QQ\"\001\003\n\005-!!AB!osJ+g\rB\003\016\001\t\005aB\001\003UCN\\WCA\b\027#\t\0012\003\005\002\n#%\021!\003\002\002\b\035>$\b.\0338h!\tIA#\003\002\026\t\t\031\021I\\=\005\013]a!\031A\b\003\003QCQ!\007\001\007\004i\taBZ;oGRLwN\\!t)\006\0348.\006\002\034AQ\021AD\t\t\004;1qR\"\001\001\021\005}\001C\002\001\003\006Ca\021\ra\004\002\002'\")1\005\007a\001I\005\031a-\0368\021\007%)c$\003\002'\t\tIa)\0368di&|g\016\r\005\006Q\0011\t!K\001\bKb,7-\036;f+\tQ#\007\006\002,]A\021\021\002L\005\003[\021\021A!\0268ji\")qf\na\001a\005!A/Y:l!\riB\"\r\t\003?I\"Q!I\024C\002=AQ\001\016\001\007\002U\n\001b\0355vi\022|wO\034\013\002W!\"\001a\016\036=!\tI\001(\003\002:\t\tQA-\0329sK\016\fG/\0323\"\003m\nq$V:fA\001,\0050Z2vi&|gnQ8oi\026DH\017\031\021j]N$X-\0313/C\005i\024A\002\032/cAr\003\007")
public interface TaskRunner {
  <S> Object functionAsTask(Function0<S> paramFunction0);
  
  <S> void execute(Object paramObject);
  
  void shutdown();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\TaskRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */