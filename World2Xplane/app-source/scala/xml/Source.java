package scala.xml;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Reader;
import org.xml.sax.InputSource;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001U;Q!\001\002\t\002\035\taaU8ve\016,'BA\002\005\003\rAX\016\034\006\002\013\005)1oY1mC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!AB*pkJ\034Wm\005\002\n\031A\021QBD\007\002\t%\021q\002\002\002\007\003:L(+\0324\t\013EIA\021\001\n\002\rqJg.\033;?)\0059\001\"\002\013\n\t\003)\022\001\0034s_64\025\016\\3\025\005Yy\002CA\f\036\033\005A\"BA\r\033\003\r\031\030\r\037\006\003\007mQ\021\001H\001\004_J<\027B\001\020\031\005-Ie\016];u'>,(oY3\t\013\001\032\002\031A\021\002\t\031LG.\032\t\003E\035j\021a\t\006\003I\025\n!![8\013\003\031\nAA[1wC&\021\001f\t\002\005\r&dW\rC\003\025\023\021\005!\006\006\002\027W!)A&\013a\001[\005\021a\r\032\t\003E9J!aL\022\003\035\031KG.\032#fg\016\024\030\016\035;pe\")A#\003C\001cQ\021aC\r\005\006gA\002\r\001N\001\005]\006lW\r\005\0026q9\021QBN\005\003o\021\ta\001\025:fI\0264\027BA\035;\005\031\031FO]5oO*\021q\007\002\005\006y%!\t!P\001\020MJ|W.\0238qkR\034FO]3b[R\021aC\020\005\006m\002\r\001Q\001\003SN\004\"AI!\n\005\t\033#aC%oaV$8\013\036:fC6DQ\001R\005\005\002\025\013!B\032:p[J+\027\rZ3s)\t1b\tC\003H\007\002\007\001*\001\004sK\006$WM\035\t\003E%K!AS\022\003\rI+\027\rZ3s\021\025a\025\002\"\001N\003%1'o\\7TsNLE\r\006\002\027\035\")qj\023a\001i\005)1/_:J\t\")\021+\003C\001%\006QaM]8n'R\024\030N\\4\025\005Y\031\006\"\002+Q\001\004!\024AB:ue&tw\r")
public final class Source {
  public static InputSource fromString(String paramString) {
    return Source$.MODULE$.fromString(paramString);
  }
  
  public static InputSource fromSysId(String paramString) {
    return Source$.MODULE$.fromSysId(paramString);
  }
  
  public static InputSource fromReader(Reader paramReader) {
    return Source$.MODULE$.fromReader(paramReader);
  }
  
  public static InputSource fromInputStream(InputStream paramInputStream) {
    return Source$.MODULE$.fromInputStream(paramInputStream);
  }
  
  public static InputSource fromFile(String paramString) {
    return Source$.MODULE$.fromFile(paramString);
  }
  
  public static InputSource fromFile(FileDescriptor paramFileDescriptor) {
    return Source$.MODULE$.fromFile(paramFileDescriptor);
  }
  
  public static InputSource fromFile(File paramFile) {
    return Source$.MODULE$.fromFile(paramFile);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Source.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */