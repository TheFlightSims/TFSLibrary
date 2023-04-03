package akka.io;

import java.nio.ByteBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0052q!\001\002\021\002G\005qA\001\006Ck\0324WM\035)p_2T!a\001\003\002\005%|'\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\005\006\037\0011\t\001E\001\bC\016\fX/\033:f)\005\t\002C\001\n\030\033\005\031\"B\001\013\026\003\rq\027n\034\006\002-\005!!.\031<b\023\tA2C\001\006CsR,')\0364gKJDQA\007\001\007\002m\tqA]3mK\006\034X\r\006\002\035?A\021\021\"H\005\003=)\021A!\0268ji\")\001%\007a\001#\005\031!-\0364")
public interface BufferPool {
  ByteBuffer acquire();
  
  void release(ByteBuffer paramByteBuffer);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\BufferPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */