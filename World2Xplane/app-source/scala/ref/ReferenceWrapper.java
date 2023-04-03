package scala.ref;

import java.lang.ref.Reference;
import scala.Option;
import scala.Proxy;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00113q!\001\002\021\002\007\005qA\001\tSK\032,'/\0328dK^\023\030\r\0359fe*\0211\001B\001\004e\0264'\"A\003\002\013M\034\027\r\\1\004\001U\021\001bE\n\005\001%i\021\004\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\0042AD\b\022\033\005\021\021B\001\t\003\005%\021VMZ3sK:\034W\r\005\002\023'1\001AA\002\013\001\t\013\007QCA\001U#\t1\022\002\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h!\tQ!$\003\002\034\t\t)\001K]8ys\")Q\004\001C\001=\0051A%\0338ji\022\"\022a\b\t\003\025\001J!!\t\003\003\tUs\027\016\036\005\bG\001\021\rQ\"\001%\003))h\016Z3sYfLgnZ\013\002KA\022ae\f\t\004O5rS\"\001\025\013\005\rI#B\001\026,\003\021a\027M\\4\013\0031\nAA[1wC&\021\001\003\013\t\003%=\"\021\002\r\022\002\002\003\005)\021A\031\003\007}#\023'\005\002\027#!)1\007\001C!i\005\031q-\032;\026\003U\0022A\003\034\022\023\t9DA\001\004PaRLwN\034\005\006s\001!\tAO\001\006CB\004H.\037\013\002#!)A\b\001C\001=\005)1\r\\3be\")a\b\001C\001\0059QM\\9vKV,G#\001!\021\005)\t\025B\001\"\005\005\035\021un\0347fC:DQ\001\022\001\005\002}\n!\"[:F]F,X-^3e\021\0251\005\001\"\001H\003\021\031X\r\0344\026\003!\003$!S&\021\007\035j#\n\005\002\023\027\022I\001'RA\001\002\003\025\t!\r")
public interface ReferenceWrapper<T> extends Reference<T>, Proxy {
  Reference<? extends T> underlying();
  
  Option<T> get();
  
  T apply();
  
  void clear();
  
  boolean enqueue();
  
  boolean isEnqueued();
  
  Reference<? extends T> self();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\ReferenceWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */