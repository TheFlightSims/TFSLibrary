package scala.sys;

import scala.Function0;
import scala.collection.IndexedSeq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

@ScalaSignature(bytes = "\006\001\t<Q!\001\002\t\002\035\tq\001]1dW\006<WM\003\002\004\t\005\0311/_:\013\003\025\tQa]2bY\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\004qC\016\\\027mZ3\024\005%a\001CA\007\017\033\005!\021BA\b\005\005\031\te.\037*fM\")\021#\003C\001%\0051A(\0338jiz\"\022a\002\005\006)%!\t!F\001\006KJ\024xN\035\013\003-e\001\"!D\f\n\005a!!a\002(pi\"Lgn\032\005\0065M\001\raG\001\b[\026\0348/Y4f!\tarD\004\002\016;%\021a\004B\001\007!J,G-\0324\n\005\001\n#AB*ue&twM\003\002\037\t!)1%\003C\001I\005!Q\r_5u)\0051\002\"B\022\n\t\0031CC\001\f(\021\025AS\0051\001*\003\031\031H/\031;vgB\021QBK\005\003W\021\0211!\0238u\021\025i\023\002\"\001/\003\035\021XO\034;j[\026,\022a\f\t\003aUj\021!\r\006\003eM\nA\001\\1oO*\tA'\001\003kCZ\f\027B\001\0342\005\035\021VO\034;j[\026DQ\001O\005\005\002e\nQ\001\035:paN,\022A\017\t\003\021mJ!\001\020\002\003!MK8\017^3n!J|\007/\032:uS\026\034\b\"\002 \n\t\003y\024aA3omV\t\001\t\005\003B\rnYR\"\001\"\013\005\r#\025!C5n[V$\030M\0317f\025\t)E!\001\006d_2dWm\031;j_:L!a\022\"\003\0075\013\007\017C\003J\023\021\005!*A\bbI\022\034\006.\036;e_^t\007j\\8l)\tYe\n\005\002\t\031&\021QJ\001\002\023'\",H\017Z8x]\"{wn\033+ie\026\fG\r\003\004P\021\022\005\r\001U\001\005E>$\027\020E\002\016#NK!A\025\003\003\021q\022\027P\\1nKz\002\"!\004+\n\005U#!\001B+oSRDQaV\005\005\002a\013!\"\0317m)\"\024X-\0313t)\005I\006c\001.]?:\021QbW\005\003\003\021I!!\0300\003\025%sG-\032=fIN+\027O\003\002\002\tA\021\001\007Y\005\003CF\022a\001\0265sK\006$\007")
public final class package {
  public static IndexedSeq<Thread> allThreads() {
    return package$.MODULE$.allThreads();
  }
  
  public static ShutdownHookThread addShutdownHook(Function0<BoxedUnit> paramFunction0) {
    return package$.MODULE$.addShutdownHook(paramFunction0);
  }
  
  public static Map<String, String> env() {
    return package$.MODULE$.env();
  }
  
  public static SystemProperties props() {
    return package$.MODULE$.props();
  }
  
  public static Runtime runtime() {
    return package$.MODULE$.runtime();
  }
  
  public static Nothing$ exit(int paramInt) {
    return package$.MODULE$.exit(paramInt);
  }
  
  public static Nothing$ exit() {
    return package$.MODULE$.exit();
  }
  
  public static Nothing$ error(String paramString) {
    return package$.MODULE$.error(paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */