package scala.xml.factory;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import org.xml.sax.InputSource;
import scala.reflect.ScalaSignature;
import scala.xml.parsing.FactoryAdapter;

@ScalaSignature(bytes = "\006\001\005\raaB\001\003!\003\r\t!\003\002\n16cEj\\1eKJT!a\001\003\002\017\031\f7\r^8ss*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001U\021!BL\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\021\025\001\002\001\"\001\022\003\031!\023N\\5uIQ\t!\003\005\002\r'%\021AC\002\002\005+:LG\017C\003\027\001\021\005q#A\004bI\006\004H/\032:\026\003a\001\"!\007\017\016\003iQ!a\007\003\002\017A\f'o]5oO&\021QD\007\002\017\r\006\034Go\034:z\003\022\f\007\017^3s\021\025y\002\001\"\001!\003\031\001\030M]:feV\t\021\005\005\002#M9\0211\005J\007\002\t%\021Q\005B\001\ba\006\0347.Y4f\023\t9\003FA\005T\003b\003\026M]:fe*\021Q\005\002\005\006U\001!\taK\001\bY>\fG\rW'M)\ras\007\020\t\003[9b\001\001B\0030\001\t\007\001GA\001U#\t\tD\007\005\002\re%\0211G\002\002\b\035>$\b.\0338h!\t\031S'\003\0027\t\t!aj\0343f\021\025A\024\0061\001:\003\031\031x.\036:dKB\021!EO\005\003w!\0221\"\0238qkR\034v.\036:dK\")q$\013a\001C!)a\b\001C\001\005AAn\\1e\r&dW\r\006\002-\001\")\021)\020a\001\005\006!a-\0337f!\t\031\005*D\001E\025\t)e)\001\002j_*\tq)\001\003kCZ\f\027BA%E\005\0211\025\016\\3\t\013y\002A\021A&\025\0051b\005\"B'K\001\004q\025A\0014e!\t\031u*\003\002Q\t\nqa)\0337f\t\026\0348M]5qi>\024\b\"\002 \001\t\003\021FC\001\027T\021\025!\026\0131\001V\003\021q\027-\\3\021\005YKfB\001\007X\023\tAf!\001\004Qe\026$WMZ\005\0035n\023aa\025;sS:<'B\001-\007\021\025i\006\001\"\001_\003\021aw.\0313\025\0051z\006\"\0021]\001\004\t\027AA5t!\t\031%-\003\002d\t\nY\021J\0349viN#(/Z1n\021\025i\006\001\"\001f)\tac\rC\003hI\002\007\001.\001\004sK\006$WM\035\t\003\007&L!A\033#\003\rI+\027\rZ3s\021\025i\006\001\"\001m)\taS\016C\003oW\002\007Q+A\003tsNLE\tC\003^\001\021\005\001\017\006\002-c\")\001h\034a\001s!)Q\f\001C\001gR\021A\006\036\005\006kJ\004\rA^\001\004kJd\007CA<{\033\005A(BA=G\003\rqW\r^\005\003wb\0241!\026*M\021\025i\b\001\"\001\003)aw.\0313TiJLgn\032\013\003Y}Da!!\001}\001\004)\026AB:ue&tw\r")
public interface XMLLoader<T extends scala.xml.Node> {
  FactoryAdapter adapter();
  
  SAXParser parser();
  
  T loadXML(InputSource paramInputSource, SAXParser paramSAXParser);
  
  T loadFile(File paramFile);
  
  T loadFile(FileDescriptor paramFileDescriptor);
  
  T loadFile(String paramString);
  
  T load(InputStream paramInputStream);
  
  T load(Reader paramReader);
  
  T load(String paramString);
  
  T load(InputSource paramInputSource);
  
  T load(URL paramURL);
  
  T loadString(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\XMLLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */