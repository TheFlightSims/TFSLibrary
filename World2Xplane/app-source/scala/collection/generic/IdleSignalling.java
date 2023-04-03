package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Q9Q!\001\002\t\002%\ta\"\0233mKNKwM\\1mY&twM\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001\001C\001\006\f\033\005\021a!\002\007\003\021\003i!AD%eY\026\034\026n\0328bY2LgnZ\n\003\0279\001\"AC\b\n\005A\021!!\005#fM\006,H\016^*jO:\fG\016\\5oO\")!c\003C\001'\0051A(\0338jiz\"\022!\003")
public final class IdleSignalling {
  public static void abort() {
    IdleSignalling$.MODULE$.abort();
  }
  
  public static boolean isAborted() {
    return IdleSignalling$.MODULE$.isAborted();
  }
  
  public static int tag() {
    return IdleSignalling$.MODULE$.tag();
  }
  
  public static void setIndexFlagIfLesser(int paramInt) {
    IdleSignalling$.MODULE$.setIndexFlagIfLesser(paramInt);
  }
  
  public static void setIndexFlagIfGreater(int paramInt) {
    IdleSignalling$.MODULE$.setIndexFlagIfGreater(paramInt);
  }
  
  public static void setIndexFlag(int paramInt) {
    IdleSignalling$.MODULE$.setIndexFlag(paramInt);
  }
  
  public static int indexFlag() {
    return IdleSignalling$.MODULE$.indexFlag();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IdleSignalling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */