package scala.sys.process;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import scala.Function1;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.xml.Elem;

@ScalaSignature(bytes = "\006\001\001;Q!\001\002\t\002%\tq\001]1dW\006<WM\003\002\004\t\0059\001O]8dKN\034(BA\003\007\003\r\031\030p\035\006\002\017\005)1oY1mC\016\001\001C\001\006\f\033\005\021a!\002\007\003\021\003i!a\0029bG.\fw-Z\n\004\0279\021\002CA\b\021\033\0051\021BA\t\007\005\031\te.\037*fMB\021!bE\005\003)\t\021\001\003\025:pG\026\0348/S7qY&\034\027\016^:\t\013YYA\021A\f\002\rqJg.\033;?)\005I\001\"B\r\f\t\003Q\022a\0046bm\0064V.\021:hk6,g\016^:\026\003m\0012\001H\022'\035\ti\"E\004\002\037C5\tqD\003\002!\021\0051AH]8pizJ\021aB\005\003\003\031I!\001J\023\003\t1K7\017\036\006\003\003\031\001\"a\n\026\017\005=A\023BA\025\007\003\031\001&/\0323fM&\0211\006\f\002\007'R\024\030N\\4\013\005%2\001\"\002\030\f\t\003y\023!B:uI&tW#\001\031\021\005E2T\"\001\032\013\005M\"\024AA5p\025\005)\024\001\0026bm\006L!a\016\032\003\027%s\007/\036;TiJ,\027-\034\005\006s-!\tAO\001\007gR$w.\036;\026\003m\002\"!\r\037\n\005u\022$a\003)sS:$8\013\036:fC6DQaP\006\005\002i\naa\035;eKJ\024\b")
public final class package {
  public static ProcessBuilder stringSeqToProcess(Seq<String> paramSeq) {
    return package$.MODULE$.stringSeqToProcess(paramSeq);
  }
  
  public static ProcessBuilder stringToProcess(String paramString) {
    return package$.MODULE$.stringToProcess(paramString);
  }
  
  public static ProcessBuilder xmlToProcess(Elem paramElem) {
    return package$.MODULE$.xmlToProcess(paramElem);
  }
  
  public static ProcessBuilder.URLBuilder urlToProcess(URL paramURL) {
    return package$.MODULE$.urlToProcess(paramURL);
  }
  
  public static ProcessBuilder.FileBuilder fileToProcess(File paramFile) {
    return package$.MODULE$.fileToProcess(paramFile);
  }
  
  public static ProcessBuilder builderToProcess(java.lang.ProcessBuilder paramProcessBuilder) {
    return package$.MODULE$.builderToProcess(paramProcessBuilder);
  }
  
  public static <T> Seq<ProcessBuilder.Source> buildersToProcess(Seq<T> paramSeq, Function1<T, ProcessBuilder.Source> paramFunction1) {
    return package$.MODULE$.buildersToProcess(paramSeq, paramFunction1);
  }
  
  public static PrintStream stderr() {
    return package$.MODULE$.stderr();
  }
  
  public static PrintStream stdout() {
    return package$.MODULE$.stdout();
  }
  
  public static InputStream stdin() {
    return package$.MODULE$.stdin();
  }
  
  public static List<String> javaVmArguments() {
    return package$.MODULE$.javaVmArguments();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */