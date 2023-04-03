package akka.event;

import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Q;Q!\001\002\t\002\035\t\021BT8M_\036<\027N\\4\013\005\r!\021!B3wK:$(\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005%qu\016T8hO&twmE\002\n\031I\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007C\001\005\024\023\t!\"A\001\bM_\036<\027N\\4BI\006\004H/\032:\t\013YIA\021A\f\002\rqJg.\033;?)\0059\001\"B\r\n\t\003Q\022aC4fi&s7\017^1oG\026,\022a\007\b\003\021\001AQ!H\005\005Fy\ta\"[:FeJ|'/\0228bE2,G-F\001 !\ti\001%\003\002\"\035\t9!i\\8mK\006t\007\"B\022\n\t\013r\022\001E5t/\006\024h.\0338h\013:\f'\r\\3e\021\025)\023\002\"\022\037\0035I7/\0238g_\026s\027M\0317fI\")q%\003C#=\005q\021n\035#fEV<WI\\1cY\026$\007\"B\025\n\t+R\023a\0038pi&4\0270\022:s_J$\"a\013\030\021\0055a\023BA\027\017\005\021)f.\033;\t\013=B\003\031\001\031\002\0175,7o]1hKB\021\021\007\016\b\003\033IJ!a\r\b\002\rA\023X\rZ3g\023\t)dG\001\004TiJLgn\032\006\003g9AQ!K\005\005Va\"2aK\035H\021\025Qt\0071\001<\003\025\031\027-^:f!\taDI\004\002>\005:\021a(Q\007\002)\021\001IB\001\007yI|w\016\036 \n\003=I!a\021\b\002\017A\f7m[1hK&\021QI\022\002\n)\"\024xn^1cY\026T!a\021\b\t\013=:\004\031\001\031\t\013%KAQ\013&\002\0339|G/\0334z/\006\024h.\0338h)\tY3\nC\0030\021\002\007\001\007C\003N\023\021Uc*\001\006o_RLg-_%oM>$\"aK(\t\013=b\005\031\001\031\t\013EKAQ\013*\002\0279|G/\0334z\t\026\024Wo\032\013\003WMCQa\f)A\002A\002")
public final class NoLogging {
  public static String format(String paramString, Seq<Object> paramSeq) {
    return NoLogging$.MODULE$.format(paramString, paramSeq);
  }
  
  public static void notifyLog(int paramInt, String paramString) {
    NoLogging$.MODULE$.notifyLog(paramInt, paramString);
  }
  
  public static boolean isEnabled(int paramInt) {
    return NoLogging$.MODULE$.isEnabled(paramInt);
  }
  
  public static void log(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    NoLogging$.MODULE$.log(paramInt, paramString, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public static void log(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    NoLogging$.MODULE$.log(paramInt, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public static void log(int paramInt, String paramString, Object paramObject1, Object paramObject2) {
    NoLogging$.MODULE$.log(paramInt, paramString, paramObject1, paramObject2);
  }
  
  public static void log(int paramInt, String paramString, Object paramObject) {
    NoLogging$.MODULE$.log(paramInt, paramString, paramObject);
  }
  
  public static void log(int paramInt, String paramString) {
    NoLogging$.MODULE$.log(paramInt, paramString);
  }
  
  public static void debug(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    NoLogging$.MODULE$.debug(paramString, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public static void debug(String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    NoLogging$.MODULE$.debug(paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public static void debug(String paramString, Object paramObject1, Object paramObject2) {
    NoLogging$.MODULE$.debug(paramString, paramObject1, paramObject2);
  }
  
  public static void debug(String paramString, Object paramObject) {
    NoLogging$.MODULE$.debug(paramString, paramObject);
  }
  
  public static void debug(String paramString) {
    NoLogging$.MODULE$.debug(paramString);
  }
  
  public static void info(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    NoLogging$.MODULE$.info(paramString, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public static void info(String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    NoLogging$.MODULE$.info(paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public static void info(String paramString, Object paramObject1, Object paramObject2) {
    NoLogging$.MODULE$.info(paramString, paramObject1, paramObject2);
  }
  
  public static void info(String paramString, Object paramObject) {
    NoLogging$.MODULE$.info(paramString, paramObject);
  }
  
  public static void info(String paramString) {
    NoLogging$.MODULE$.info(paramString);
  }
  
  public static void warning(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    NoLogging$.MODULE$.warning(paramString, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public static void warning(String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    NoLogging$.MODULE$.warning(paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public static void warning(String paramString, Object paramObject1, Object paramObject2) {
    NoLogging$.MODULE$.warning(paramString, paramObject1, paramObject2);
  }
  
  public static void warning(String paramString, Object paramObject) {
    NoLogging$.MODULE$.warning(paramString, paramObject);
  }
  
  public static void warning(String paramString) {
    NoLogging$.MODULE$.warning(paramString);
  }
  
  public static void error(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    NoLogging$.MODULE$.error(paramString, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public static void error(String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    NoLogging$.MODULE$.error(paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public static void error(String paramString, Object paramObject1, Object paramObject2) {
    NoLogging$.MODULE$.error(paramString, paramObject1, paramObject2);
  }
  
  public static void error(String paramString, Object paramObject) {
    NoLogging$.MODULE$.error(paramString, paramObject);
  }
  
  public static void error(String paramString) {
    NoLogging$.MODULE$.error(paramString);
  }
  
  public static void error(Throwable paramThrowable, String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    NoLogging$.MODULE$.error(paramThrowable, paramString, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public static void error(Throwable paramThrowable, String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    NoLogging$.MODULE$.error(paramThrowable, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public static void error(Throwable paramThrowable, String paramString, Object paramObject1, Object paramObject2) {
    NoLogging$.MODULE$.error(paramThrowable, paramString, paramObject1, paramObject2);
  }
  
  public static void error(Throwable paramThrowable, String paramString, Object paramObject) {
    NoLogging$.MODULE$.error(paramThrowable, paramString, paramObject);
  }
  
  public static void error(Throwable paramThrowable, String paramString) {
    NoLogging$.MODULE$.error(paramThrowable, paramString);
  }
  
  public static Map<String, Object> mdc() {
    return NoLogging$.MODULE$.mdc();
  }
  
  public static boolean isDebugEnabled() {
    return NoLogging$.MODULE$.isDebugEnabled();
  }
  
  public static boolean isInfoEnabled() {
    return NoLogging$.MODULE$.isInfoEnabled();
  }
  
  public static boolean isWarningEnabled() {
    return NoLogging$.MODULE$.isWarningEnabled();
  }
  
  public static boolean isErrorEnabled() {
    return NoLogging$.MODULE$.isErrorEnabled();
  }
  
  public static NoLogging$ getInstance() {
    return NoLogging$.MODULE$.getInstance();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\NoLogging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */