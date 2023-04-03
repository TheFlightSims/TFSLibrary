package scala.sys.process;

import java.io.File;
import java.net.URL;
import scala.Function1;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.Elem;

@ScalaSignature(bytes = "\006\001\005\005aaB\001\003!\003\r\t!\003\002\021!J|7-Z:t\0236\004H.[2jiNT!a\001\003\002\017A\024xnY3tg*\021QAB\001\004gf\034(\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\023j]&$H\005F\001\022!\tY!#\003\002\024\r\t!QK\\5u\021\025)\002\001b\001\027\003E\021W/\0337eKJ\034Hk\034)s_\016,7o]\013\003/Q\"\"\001G\037\025\005ei\003c\001\016#K9\0211\004\t\b\0039}i\021!\b\006\003=!\ta\001\020:p_Rt\024\"A\004\n\005\0052\021a\0029bG.\fw-Z\005\003G\021\0221aU3r\025\t\tc\001\005\002'U9\021q\005K\007\002\005%\021\021FA\001\017!J|7-Z:t\005VLG\016Z3s\023\tYCF\001\004T_V\0248-\032\006\003S\tAQA\f\013A\004=\nqaY8om\026\024H\017\005\003\faI*\023BA\031\007\005%1UO\\2uS>t\027\007\005\0024i1\001A!B\033\025\005\0041$!\001+\022\005]R\004CA\0069\023\tIdAA\004O_RD\027N\\4\021\005-Y\024B\001\037\007\005\r\te.\037\005\006}Q\001\raP\001\tEVLG\016Z3sgB\031!D\t\032\t\013\005\003A1\001\"\002!\t,\030\016\0343feR{\007K]8dKN\034HCA\"G!\t9C)\003\002F\005\tq\001K]8dKN\034()^5mI\026\024\b\"B$A\001\004A\025a\0022vS2$WM\035\t\003\0232s!a\n&\n\005-\023\021a\0049s_\016,7o]%oi\026\024h.\0317\n\0055s%a\004&Qe>\034Wm]:Ck&dG-\032:\013\005-\023\001\"\002)\001\t\007\t\026!\0044jY\026$v\016\025:pG\026\0348\017\006\002S+B\021aeU\005\003)2\0221BR5mK\n+\030\016\0343fe\")ak\024a\001/\006!a-\0337f!\tI\005,\003\002Z\035\n!a)\0337f\021\025Y\006\001b\001]\0031)(\017\034+p!J|7-Z:t)\ti\006\r\005\002'=&\021q\f\f\002\013+Jc%)^5mI\026\024\b\"B1[\001\004\021\027aA;sYB\021\021jY\005\003I:\0231!\026*M\021\0251\007\001b\001h\0031AX\016\034+p!J|7-Z:t)\t\031\005\016C\003jK\002\007!.A\004d_6l\027M\0343\021\005-tW\"\0017\013\00554\021a\001=nY&\021q\016\034\002\005\0132,W\016C\003r\001\021\r!/A\btiJLgn\032+p!J|7-Z:t)\t\0315\017C\003ja\002\007A\017\005\002vq:\0211B^\005\003o\032\ta\001\025:fI\0264\027BA={\005\031\031FO]5oO*\021qO\002\005\006y\002!\031!`\001\023gR\024\030N\\4TKF$v\016\025:pG\026\0348\017\006\002D}\")\021n\037a\001B\031!D\t;")
public interface ProcessImplicits {
  <T> Seq<ProcessBuilder.Source> buildersToProcess(Seq<T> paramSeq, Function1<T, ProcessBuilder.Source> paramFunction1);
  
  ProcessBuilder builderToProcess(java.lang.ProcessBuilder paramProcessBuilder);
  
  ProcessBuilder.FileBuilder fileToProcess(File paramFile);
  
  ProcessBuilder.URLBuilder urlToProcess(URL paramURL);
  
  ProcessBuilder xmlToProcess(Elem paramElem);
  
  ProcessBuilder stringToProcess(String paramString);
  
  ProcessBuilder stringSeqToProcess(Seq<String> paramSeq);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessImplicits.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */