package akka.dispatch;

import java.util.concurrent.ThreadFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)2q!\001\002\021\002G\005qA\001\020Fq\026\034W\017^8s'\026\024h/[2f\r\006\034Go\034:z!J|g/\0333fe*\0211\001B\001\tI&\034\b/\031;dQ*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\r\003\001\022\001H2sK\006$X-\022=fGV$xN]*feZL7-\032$bGR|'/\037\013\004#Uq\002C\001\n\024\033\005\021\021B\001\013\003\005Y)\0050Z2vi>\0248+\032:wS\016,g)Y2u_JL\b\"\002\f\017\001\0049\022AA5e!\tA2D\004\002\n3%\021!DC\001\007!J,G-\0324\n\005qi\"AB*ue&twM\003\002\033\025!)qD\004a\001A\005iA\017\033:fC\0224\025m\031;pef\004\"!\t\025\016\003\tR!a\t\023\002\025\r|gnY;se\026tGO\003\002&M\005!Q\017^5m\025\0059\023\001\0026bm\006L!!\013\022\003\033QC'/Z1e\r\006\034Go\034:z\001")
public interface ExecutorServiceFactoryProvider {
  ExecutorServiceFactory createExecutorServiceFactory(String paramString, ThreadFactory paramThreadFactory);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ExecutorServiceFactoryProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */