package scala.runtime;

import scala.Proxy;
import scala.math.Ordered;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q2q!\001\002\021\002\007\005qA\001\007Pe\022,'/\0323Qe>D\030P\003\002\004\t\0059!/\0368uS6,'\"A\003\002\013M\034\027\r\\1\004\001U\021\001bG\n\005\001%i\021\005\005\002\013\0275\tA!\003\002\r\t\t\031\021I\\=\021\00791\022D\004\002\020)9\021\001cE\007\002#)\021!CB\001\007yI|w\016\036 \n\003\025I!!\006\003\002\017A\f7m[1hK&\021q\003\007\002\b\037J$WM]3e\025\t)B\001\005\002\03371\001A!\002\017\001\005\004i\"!\001+\022\005yI\001C\001\006 \023\t\001CAA\004O_RD\027N\\4\021\007\t*\023D\004\002\013G%\021A\005B\001\006!J|\0070_\005\003M\035\022Q\001V=qK\022T!\001\n\003\t\013%\002A\021\001\026\002\r\021Jg.\033;%)\005Y\003C\001\006-\023\tiCA\001\003V]&$\b\"B\030\001\r#\001\024aA8sIV\t\021\007E\002\017eeI!a\r\r\003\021=\023H-\032:j]\036DQ!\016\001\005\002Y\nqaY8na\006\024X\r\006\0028uA\021!\002O\005\003s\021\0211!\0238u\021\025YD\0071\001\032\003\005I\b")
public interface OrderedProxy<T> extends Ordered<T>, Proxy.Typed<T> {
  Ordering<T> ord();
  
  int compare(T paramT);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\OrderedProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */