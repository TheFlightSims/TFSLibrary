package scala;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r]u!B\001\003\021\003)\021aB\"p]N|G.\032\006\002\007\005)1oY1mC\016\001\001C\001\004\b\033\005\021a!\002\005\003\021\003I!aB\"p]N|G.Z\n\003\017)\001\"AB\006\n\0051\021!AB!osJ+g\rC\003\017\017\021\005q\"\001\004=S:LGO\020\013\002\013!9\021c\002b\001\n\013\021\022!\002\"M\003\016[U#A\n\020\003Q\t\023!F\001\0067m\033\004'\034\005\007/\035\001\013QB\n\002\r\tc\025iQ&!\021\035IrA1A\005\006i\t1AU#E+\005Yr\"\001\017\"\003u\tQaG.4c5DaaH\004!\002\033Y\022\001\002*F\t\002Bq!I\004C\002\023\025!%A\003H%\026+e*F\001$\037\005!\023%A\023\002\013mY6GM7\t\r\035:\001\025!\004$\003\0319%+R#OA!9\021f\002b\001\n\013Q\023AB-F\0312{u+F\001,\037\005a\023%A\027\002\013mY6gM7\t\r=:\001\025!\004,\003\035IV\t\024'P/\002Bq!M\004C\002\023\025!'\001\003C\031V+U#A\032\020\003Q\n\023!N\001\0067m\033D'\034\005\007o\035\001\013QB\032\002\013\tcU+\022\021\t\017e:!\031!C\003u\0059Q*Q$F\035R\013U#A\036\020\003q\n\023!P\001\0067m\033T'\034\005\007\035\001\013QB\036\002\0215\013u)\022(U\003\002Bq!Q\004C\002\023\025!)\001\003D3\006sU#A\"\020\003\021\013\023!R\001\0067m\033d'\034\005\007\017\036\001\013QB\"\002\013\rK\026I\024\021\t\017%;!\031!C\003\025\006)q\013S%U\013V\t1jD\001MC\005i\025!B\016\\g]j\007BB(\bA\00351*\001\004X\021&#V\t\t\005\b#\036\021\r\021\"\002S\003\035\021E*Q\"L?\n+\022aU\b\002)\006\nQ+A\003\0347R\002T\016\003\004X\017\001\006iaU\001\t\0052\0135iS0CA!9\021l\002b\001\n\013Q\026!\002*F\t~\023U#A.\020\003q\013\023!X\001\0067m#\024'\034\005\007?\036\001\013QB.\002\rI+Ei\030\"!\021\035\twA1A\005\006\t\fqa\022*F\013:{&)F\001d\037\005!\027%A3\002\013mYFGM7\t\r\035<\001\025!\004d\003!9%+R#O?\n\003\003bB5\b\005\004%)A[\001\t3\026cEjT,`\005V\t1nD\001mC\005i\027!B\016\\iMj\007BB8\bA\00351.A\005Z\0132cujV0CA!9\021o\002b\001\n\013\021\030A\002\"M+\026{&)F\001t\037\005!\030%A;\002\013mYF\007N7\t\r]<\001\025!\004t\003\035\021E*V#`\005\002Bq!_\004C\002\023\025!0A\005N\003\036+e\nV!`\005V\t1pD\001}C\005i\030!B\016\\iUj\007BB@\bA\003510\001\006N\003\036+e\nV!`\005\002B\021\"a\001\b\005\004%)!!\002\002\r\rK\026IT0C+\t\t9a\004\002\002\n\005\022\0211B\001\0067m#d'\034\005\t\003\0379\001\025!\004\002\b\00591)W!O?\n\003\003\"CA\n\017\t\007IQAA\013\003\0359\006*\023+F?\n+\"!a\006\020\005\005e\021EAA\016\003\025Y2\fN\034n\021!\tyb\002Q\001\016\005]\021\001C,I\023R+uL\021\021\t\023\005\rrA1A\005\006\005\025\022!\002*F'\026#VCAA\024\037\t\tI#\t\002\002,\005!1d\027\031n\021!\tyc\002Q\001\016\005\035\022A\002*F'\026#\006\005C\005\0024\035\021\r\021\"\002\0026\005!!i\024'E+\t\t9d\004\002\002:\005\022\0211H\001\0057m\013T\016\003\005\002@\035\001\013QBA\034\003\025\021u\n\024#!\021%\t\031e\002b\001\n\013\t)%\001\006V\035\022+%\013T%O\013\022+\"!a\022\020\005\005%\023EAA&\003\021Y2\fN7\t\021\005=s\001)A\007\003\017\n1\"\026(E\013Jc\025JT#EA!I\0211K\004C\002\023\025\021QK\001\006\0052KejS\013\003\003/z!!!\027\"\005\005m\023\001B\016\\k5D\001\"a\030\bA\0035\021qK\001\007\0052Kej\023\021\t\023\005\rtA1A\005\006\005\025\024\001\003*F-\026\0236+\022#\026\005\005\035tBAA5C\t\tY'\001\003\0347^j\007\002CA8\017\001\006i!a\032\002\023I+e+\022*T\013\022\003\003\"CA:\017\t\007IQAA;\003%IeJV%T\023\ncU)\006\002\002x=\021\021\021P\021\003\003w\nAaG.9[\"A\021qP\004!\002\033\t9(\001\006J\035ZK5+\023\"M\013\002B\021\"a!\b\005\004%I!!\"\002\r=,HOV1s+\t\t9\t\005\004\002\n\006=\0251S\007\003\003\027S1!!$\003\003\021)H/\0337\n\t\005E\0251\022\002\020\tft\027-\\5d-\006\024\030.\0312mKB!\021QSAP\033\t\t9J\003\003\002\032\006m\025AA5p\025\t\ti*\001\003kCZ\f\027\002BAQ\003/\0231\002\025:j]R\034FO]3b[\"A\021QU\004!\002\023\t9)A\004pkR4\026M\035\021\t\023\005%vA1A\005\n\005\025\025AB3seZ\013'\017\003\005\002.\036\001\013\021BAD\003\035)'O\035,be\002B\021\"!-\b\005\004%I!a-\002\013%tg+\031:\026\005\005U\006CBAE\003\037\0139\f\005\003\002\026\006e\026\002BA^\003/\023aBQ;gM\026\024X\r\032*fC\022,'\017\003\005\002@\036\001\013\021BA[\003\031IgNV1sA!9\0211Y\004\005\002\005\025\027aA8viV\021\0211\023\005\b\003\023<A\021AAc\003\r)'O\035\005\b\003\033<A\021AAh\003\tIg.\006\002\0028\"9\0211[\004\005\002\005U\027AB:fi>+H\017\006\003\002X\006u\007c\001\004\002Z&\031\0211\034\002\003\tUs\027\016\036\005\t\003\007\f\t\0161\001\002\024\"9\021\021]\004\005\002\005\r\030aB<ji\"|U\017^\013\005\003K\fi\017\006\003\002h\n%A\003BAu\003\004B!a;\002n2\001A\001CAx\003?\024\r!!=\003\003Q\013B!a=\002zB\031a!!>\n\007\005](AA\004O_RD\027N\\4\021\007\031\tY0C\002\002~\n\0211!\0218z\021%\021\t!a8\005\002\004\021\031!A\003uQVt7\016E\003\007\005\013\tI/C\002\003\b\t\021\001\002\0202z]\006lWM\020\005\t\003\007\fy\0161\001\002\024\"9\0211[\004\005\002\t5A\003BAl\005\037A\001\"a1\003\f\001\007!\021\003\t\005\003+\023\031\"\003\003\003\026\005]%\001D(viB,Ho\025;sK\006l\007bBAq\017\021\005!\021D\013\005\0057\021\t\003\006\003\003\036\t\035B\003\002B\020\005G\001B!a;\003\"\021A\021q\036B\f\005\004\t\t\020C\005\003\002\t]A\0211\001\003&A)aA!\002\003 !A\0211\031B\f\001\004\021\t\002C\004\003,\035!\tA!\f\002\rM,G/\022:s)\021\t9Na\f\t\021\005%'\021\006a\001\003'CqAa\r\b\t\003\021)$A\004xSRDWI\035:\026\t\t]\"Q\b\013\005\005s\021\031\005\006\003\003<\t}\002\003BAv\005{!\001\"a<\0032\t\007\021\021\037\005\n\005\003\021\t\004\"a\001\005\003\002RA\002B\003\005wA\001\"!3\0032\001\007\0211\023\005\b\005W9A\021\001B$)\021\t9N!\023\t\021\005%'Q\ta\001\005#AqAa\r\b\t\003\021i%\006\003\003P\tUC\003\002B)\0057\"BAa\025\003XA!\0211\036B+\t!\tyOa\023C\002\005E\b\"\003B\001\005\027\"\t\031\001B-!\0251!Q\001B*\021!\tIMa\023A\002\tE\001b\002B0\017\021\005!\021M\001\006g\026$\030J\034\013\005\003/\024\031\007\003\005\003f\tu\003\031\001B4\003\031\021X-\0313feB!\021Q\023B5\023\021\021Y'a&\003\rI+\027\rZ3s\021\035\021yg\002C\001\005c\naa^5uQ&sW\003\002B:\005s\"BA!\036\003\000Q!!q\017B>!\021\tYO!\037\005\021\005=(Q\016b\001\003cD\021B!\001\003n\021\005\rA! \021\013\031\021)Aa\036\t\021\t\025$Q\016a\001\005OBqAa\030\b\t\003\021\031\t\006\003\002X\n\025\005\002CAg\005\003\003\rAa\"\021\t\005U%\021R\005\005\005\027\0139JA\006J]B,Ho\025;sK\006l\007b\002B8\017\021\005!qR\013\005\005#\0239\n\006\003\003\024\nuE\003\002BK\0053\003B!a;\003\030\022A\021q\036BG\005\004\t\t\020C\005\003\002\t5E\0211\001\003\034B)aA!\002\003\026\"A\021Q\032BG\001\004\0219\tC\004\003\"\036!\tAa)\002\013A\024\030N\034;\025\t\005]'Q\025\005\t\005O\023y\n1\001\002z\006\031qN\0316\t\017\t-v\001\"\001\003.\006)a\r\\;tQR\021\021q\033\005\b\005c;A\021\001BW\003\035\001(/\0338uY:DqA!-\b\t\003\021)\f\006\003\002X\n]\006\002\003B]\005g\003\r!!?\002\003aDqA!0\b\t\003\021y,\001\004qe&tGO\032\013\007\003/\024\tMa5\t\021\t\r'1\030a\001\005\013\fA\001^3yiB!!q\031Bg\035\r1!\021Z\005\004\005\027\024\021A\002)sK\022,g-\003\003\003P\nE'AB*ue&twMC\002\003L\nA\001B!6\003<\002\007!q[\001\005CJ<7\017E\003\007\0053\fI0C\002\003\\\n\021!\002\020:fa\026\fG/\0323?\021\035\021yn\002C\001\005C\f\001B]3bI2Kg.\032\013\003\005\013DqAa8\b\t\003\021)\017\006\004\003F\n\035(\021\036\005\t\005\007\024\031\0171\001\003F\"A!Q\033Br\001\004\0219\016C\004\003n\036!\tAa<\002\027I,\027\r\032\"p_2,\027M\034\013\003\005c\0042A\002Bz\023\r\021)P\001\002\b\005>|G.Z1o\021\035\021Ip\002C\001\005w\f\001B]3bI\nKH/\032\013\003\005{\0042A\002B\000\023\r\031\tA\001\002\005\005f$X\rC\004\004\006\035!\taa\002\002\023I,\027\rZ*i_J$HCAB\005!\r111B\005\004\007\033\021!!B*i_J$\bbBB\t\017\021\00511C\001\te\026\fGm\0215beR\0211Q\003\t\004\r\r]\021bAB\r\005\t!1\t[1s\021\035\031ib\002C\001\007?\tqA]3bI&sG\017\006\002\004\"A\031aaa\t\n\007\r\025\"AA\002J]RDqa!\013\b\t\003\031Y#\001\005sK\006$Gj\0348h)\t\031i\003E\002\007\007_I1a!\r\003\005\021auN\\4\t\017\rUr\001\"\001\0048\005I!/Z1e\r2|\027\r\036\013\003\007s\0012ABB\036\023\r\031iD\001\002\006\r2|\027\r\036\005\b\007\003:A\021AB\"\003)\021X-\0313E_V\024G.\032\013\003\007\013\0022ABB$\023\r\031IE\001\002\007\t>,(\r\\3\t\017\r5s\001\"\001\004P\005)!/Z1eMR!1\021KB0!\031\031\031f!\027\002z:\031aa!\026\n\007\r]#!A\004qC\016\\\027mZ3\n\t\rm3Q\f\002\005\031&\034HOC\002\004X\tA\001b!\031\004L\001\007!QY\001\007M>\024X.\031;\t\017\r\025t\001\"\001\004h\0051!/Z1eMF\"B!!?\004j!A1\021MB2\001\004\021)\rC\004\004n\035!\taa\034\002\rI,\027\r\03243)\021\031\tha\036\021\017\031\031\031(!?\002z&\0311Q\017\002\003\rQ+\b\017\\33\021!\031\tga\033A\002\t\025\007bBB>\017\021\0051QP\001\007e\026\fGMZ\032\025\t\r}4Q\021\t\n\r\r\005\025\021`A}\003sL1aa!\003\005\031!V\017\0357fg!A1\021MB=\001\004\021)\rC\004\004\n\036!Iaa#\002\035Q,\007\020^\"p[B|g.\0328ugR!1\021KBG\021!\031yia\"A\002\rE\025!A1\021\t\031\031\031JC\005\004\007+\023!!B!se\006L\b")
public final class Console {
  public static Tuple3<Object, Object, Object> readf3(String paramString) {
    return Console$.MODULE$.readf3(paramString);
  }
  
  public static Tuple2<Object, Object> readf2(String paramString) {
    return Console$.MODULE$.readf2(paramString);
  }
  
  public static Object readf1(String paramString) {
    return Console$.MODULE$.readf1(paramString);
  }
  
  public static List<Object> readf(String paramString) {
    return Console$.MODULE$.readf(paramString);
  }
  
  public static double readDouble() {
    return Console$.MODULE$.readDouble();
  }
  
  public static float readFloat() {
    return Console$.MODULE$.readFloat();
  }
  
  public static long readLong() {
    return Console$.MODULE$.readLong();
  }
  
  public static int readInt() {
    return Console$.MODULE$.readInt();
  }
  
  public static char readChar() {
    return Console$.MODULE$.readChar();
  }
  
  public static short readShort() {
    return Console$.MODULE$.readShort();
  }
  
  public static byte readByte() {
    return Console$.MODULE$.readByte();
  }
  
  public static boolean readBoolean() {
    return Console$.MODULE$.readBoolean();
  }
  
  public static String readLine(String paramString, Seq<Object> paramSeq) {
    return Console$.MODULE$.readLine(paramString, paramSeq);
  }
  
  public static String readLine() {
    return Console$.MODULE$.readLine();
  }
  
  public static void printf(String paramString, Seq<Object> paramSeq) {
    Console$.MODULE$.printf(paramString, paramSeq);
  }
  
  public static void println(Object paramObject) {
    Console$.MODULE$.println(paramObject);
  }
  
  public static void println() {
    Console$.MODULE$.println();
  }
  
  public static void flush() {
    Console$.MODULE$.flush();
  }
  
  public static void print(Object paramObject) {
    Console$.MODULE$.print(paramObject);
  }
  
  public static <T> T withIn(InputStream paramInputStream, Function0<T> paramFunction0) {
    return Console$.MODULE$.withIn(paramInputStream, paramFunction0);
  }
  
  public static void setIn(InputStream paramInputStream) {
    Console$.MODULE$.setIn(paramInputStream);
  }
  
  public static <T> T withIn(Reader paramReader, Function0<T> paramFunction0) {
    return Console$.MODULE$.withIn(paramReader, paramFunction0);
  }
  
  public static void setIn(Reader paramReader) {
    Console$.MODULE$.setIn(paramReader);
  }
  
  public static <T> T withErr(OutputStream paramOutputStream, Function0<T> paramFunction0) {
    return Console$.MODULE$.withErr(paramOutputStream, paramFunction0);
  }
  
  public static void setErr(OutputStream paramOutputStream) {
    Console$.MODULE$.setErr(paramOutputStream);
  }
  
  public static <T> T withErr(PrintStream paramPrintStream, Function0<T> paramFunction0) {
    return Console$.MODULE$.withErr(paramPrintStream, paramFunction0);
  }
  
  public static void setErr(PrintStream paramPrintStream) {
    Console$.MODULE$.setErr(paramPrintStream);
  }
  
  public static <T> T withOut(OutputStream paramOutputStream, Function0<T> paramFunction0) {
    return Console$.MODULE$.withOut(paramOutputStream, paramFunction0);
  }
  
  public static void setOut(OutputStream paramOutputStream) {
    Console$.MODULE$.setOut(paramOutputStream);
  }
  
  public static <T> T withOut(PrintStream paramPrintStream, Function0<T> paramFunction0) {
    return Console$.MODULE$.withOut(paramPrintStream, paramFunction0);
  }
  
  public static void setOut(PrintStream paramPrintStream) {
    Console$.MODULE$.setOut(paramPrintStream);
  }
  
  public static BufferedReader in() {
    return Console$.MODULE$.in();
  }
  
  public static PrintStream err() {
    return Console$.MODULE$.err();
  }
  
  public static PrintStream out() {
    return Console$.MODULE$.out();
  }
  
  public static String INVISIBLE() {
    return Console$.MODULE$.INVISIBLE();
  }
  
  public static String REVERSED() {
    return Console$.MODULE$.REVERSED();
  }
  
  public static String BLINK() {
    return Console$.MODULE$.BLINK();
  }
  
  public static String UNDERLINED() {
    return Console$.MODULE$.UNDERLINED();
  }
  
  public static String BOLD() {
    return Console$.MODULE$.BOLD();
  }
  
  public static String RESET() {
    return Console$.MODULE$.RESET();
  }
  
  public static String WHITE_B() {
    return Console$.MODULE$.WHITE_B();
  }
  
  public static String CYAN_B() {
    return Console$.MODULE$.CYAN_B();
  }
  
  public static String MAGENTA_B() {
    return Console$.MODULE$.MAGENTA_B();
  }
  
  public static String BLUE_B() {
    return Console$.MODULE$.BLUE_B();
  }
  
  public static String YELLOW_B() {
    return Console$.MODULE$.YELLOW_B();
  }
  
  public static String GREEN_B() {
    return Console$.MODULE$.GREEN_B();
  }
  
  public static String RED_B() {
    return Console$.MODULE$.RED_B();
  }
  
  public static String BLACK_B() {
    return Console$.MODULE$.BLACK_B();
  }
  
  public static String WHITE() {
    return Console$.MODULE$.WHITE();
  }
  
  public static String CYAN() {
    return Console$.MODULE$.CYAN();
  }
  
  public static String MAGENTA() {
    return Console$.MODULE$.MAGENTA();
  }
  
  public static String BLUE() {
    return Console$.MODULE$.BLUE();
  }
  
  public static String YELLOW() {
    return Console$.MODULE$.YELLOW();
  }
  
  public static String GREEN() {
    return Console$.MODULE$.GREEN();
  }
  
  public static String RED() {
    return Console$.MODULE$.RED();
  }
  
  public static String BLACK() {
    return Console$.MODULE$.BLACK();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Console.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */