package akka.dispatch;

import java.util.concurrent.ExecutorService;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001m1q!\001\002\021\002G\005qA\001\fFq\026\034W\017^8s'\026\024h/[2f\r\006\034Go\034:z\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001a\005\002\001\021A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032DQa\004\001\007\002A\tQc\031:fCR,W\t_3dkR|'oU3sm&\034W-F\001\022!\t\021\022$D\001\024\025\t!R#\001\006d_:\034WO\035:f]RT!AF\f\002\tU$\030\016\034\006\0021\005!!.\031<b\023\tQ2CA\bFq\026\034W\017^8s'\026\024h/[2f\001")
public interface ExecutorServiceFactory {
  ExecutorService createExecutorService();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ExecutorServiceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */