package scala.xml.dtd;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.util.regexp.Base;
import scala.util.regexp.WordExp;

@ScalaSignature(bytes = "\006\001u;Q!\001\002\t\002%\t!cQ8oi\026tG/T8eK2\004\026M]:fe*\0211\001B\001\004IR$'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\001\001C\001\006\f\033\005\021a!\002\007\003\021\003i!AE\"p]R,g\016^'pI\026d\007+\031:tKJ\034\"a\003\b\021\005)y\021B\001\t\003\005\035\0316-\0318oKJDQAE\006\005\002M\ta\001P5oSRtD#A\005\t\013UYA\021\001\f\002\013A\f'o]3\025\005]Q\002C\001\006\031\023\tI\"A\001\007D_:$XM\034;N_\022,G\016C\003\034)\001\007A$A\001t!\ti\022E\004\002\037?5\ta!\003\002!\r\0051\001K]3eK\032L!AI\022\003\rM#(/\0338h\025\t\001c\001C\003&\027\021\005a%\001\004bG\016,\007\017\036\013\003O)\002\"A\b\025\n\005%2!\001B+oSRDQa\013\023A\0021\n1\001^8l!\tqR&\003\002/\r\t\031\021J\034;\t\013AZA\021A\031\002\0275\f\027PY3Tk\0324\027\016\037\013\003ey\002\"a\r\034\017\005)!\024BA\033\003\0031\031uN\034;f]Rlu\016Z3m\023\t9\004H\001\004SK\036,\005\020]\005\003si\022AAQ1tK*\0211\bP\001\007e\026<W\r\0379\013\005u2\021\001B;uS2DQaG\030A\002IBQ\001Q\006\005\002\005\0131bY8oi\026tGo\0359fGV\tq\003C\003D\027\021\005A)\001\003t\037B$H#A\024\t\013mZA\021\001$\026\003IBQ\001S\006\005\002%\013qa]3r%\026\034H\017\006\0023\025\")1j\022a\001e\005\t\001\017C\003N\027\021\005a*\001\006dQ>L7-\032*fgR$\"a\024*\021\005M\002\026BA)9\005\r\tE\016\036\005\006\0272\003\rA\r\005\006).!\tAR\001\ta\006\024H/[2mK\")ak\003C\001/\006!\021\r^8n+\005A\006CA\032Z\023\tQ6L\001\004MKR$XM]\005\0039j\022qaV8sI\026C\b\017")
public final class ContentModelParser {
  public static WordExp.Letter atom() {
    return ContentModelParser$.MODULE$.atom();
  }
  
  public static Base.RegExp particle() {
    return ContentModelParser$.MODULE$.particle();
  }
  
  public static Base.Alt choiceRest(Base.RegExp paramRegExp) {
    return ContentModelParser$.MODULE$.choiceRest(paramRegExp);
  }
  
  public static Base.RegExp seqRest(Base.RegExp paramRegExp) {
    return ContentModelParser$.MODULE$.seqRest(paramRegExp);
  }
  
  public static Base.RegExp regexp() {
    return ContentModelParser$.MODULE$.regexp();
  }
  
  public static void sOpt() {
    ContentModelParser$.MODULE$.sOpt();
  }
  
  public static ContentModel contentspec() {
    return ContentModelParser$.MODULE$.contentspec();
  }
  
  public static Base.RegExp maybeSuffix(Base.RegExp paramRegExp) {
    return ContentModelParser$.MODULE$.maybeSuffix(paramRegExp);
  }
  
  public static void accept(int paramInt) {
    ContentModelParser$.MODULE$.accept(paramInt);
  }
  
  public static ContentModel parse(String paramString) {
    return ContentModelParser$.MODULE$.parse(paramString);
  }
  
  public static boolean checkPubID(String paramString) {
    return ContentModelParser$.MODULE$.checkPubID(paramString);
  }
  
  public static boolean checkSysID(String paramString) {
    return ContentModelParser$.MODULE$.checkSysID(paramString);
  }
  
  public static boolean isValidIANAEncoding(Seq<Object> paramSeq) {
    return ContentModelParser$.MODULE$.isValidIANAEncoding(paramSeq);
  }
  
  public static boolean isPubIDChar(char paramChar) {
    return ContentModelParser$.MODULE$.isPubIDChar(paramChar);
  }
  
  public static boolean isName(String paramString) {
    return ContentModelParser$.MODULE$.isName(paramString);
  }
  
  public static boolean isNameStart(char paramChar) {
    return ContentModelParser$.MODULE$.isNameStart(paramChar);
  }
  
  public static boolean isNameChar(char paramChar) {
    return ContentModelParser$.MODULE$.isNameChar(paramChar);
  }
  
  public static boolean isAlphaDigit(char paramChar) {
    return ContentModelParser$.MODULE$.isAlphaDigit(paramChar);
  }
  
  public static boolean isAlpha(char paramChar) {
    return ContentModelParser$.MODULE$.isAlpha(paramChar);
  }
  
  public static boolean isSpace(Seq<Object> paramSeq) {
    return ContentModelParser$.MODULE$.isSpace(paramSeq);
  }
  
  public static boolean isSpace(char paramChar) {
    return ContentModelParser$.MODULE$.isSpace(paramChar);
  }
  
  public static int name() {
    return ContentModelParser$.MODULE$.name();
  }
  
  public static int readToken() {
    return ContentModelParser$.MODULE$.readToken();
  }
  
  public static void accS(Seq<Object> paramSeq) {
    ContentModelParser$.MODULE$.accS(paramSeq);
  }
  
  public static void acc(char paramChar) {
    ContentModelParser$.MODULE$.acc(paramChar);
  }
  
  public static void next() {
    ContentModelParser$.MODULE$.next();
  }
  
  public static boolean isIdentChar() {
    return ContentModelParser$.MODULE$.isIdentChar();
  }
  
  public static void nextToken() {
    ContentModelParser$.MODULE$.nextToken();
  }
  
  public static void initScanner(String paramString) {
    ContentModelParser$.MODULE$.initScanner(paramString);
  }
  
  public static void value_$eq(String paramString) {
    ContentModelParser$.MODULE$.value_$eq(paramString);
  }
  
  public static String value() {
    return ContentModelParser$.MODULE$.value();
  }
  
  public static void token_$eq(int paramInt) {
    ContentModelParser$.MODULE$.token_$eq(paramInt);
  }
  
  public static int token() {
    return ContentModelParser$.MODULE$.token();
  }
  
  public static char ENDCH() {
    return ContentModelParser$.MODULE$.ENDCH();
  }
  
  public static String token2string(int paramInt) {
    return ContentModelParser$.MODULE$.token2string(paramInt);
  }
  
  public static int S() {
    return ContentModelParser$.MODULE$.S();
  }
  
  public static int END() {
    return ContentModelParser$.MODULE$.END();
  }
  
  public static int CHOICE() {
    return ContentModelParser$.MODULE$.CHOICE();
  }
  
  public static int OPT() {
    return ContentModelParser$.MODULE$.OPT();
  }
  
  public static int PLUS() {
    return ContentModelParser$.MODULE$.PLUS();
  }
  
  public static int STAR() {
    return ContentModelParser$.MODULE$.STAR();
  }
  
  public static int COMMA() {
    return ContentModelParser$.MODULE$.COMMA();
  }
  
  public static int RPAREN() {
    return ContentModelParser$.MODULE$.RPAREN();
  }
  
  public static int LPAREN() {
    return ContentModelParser$.MODULE$.LPAREN();
  }
  
  public static int NAME() {
    return ContentModelParser$.MODULE$.NAME();
  }
  
  public static int TOKEN_PCDATA() {
    return ContentModelParser$.MODULE$.TOKEN_PCDATA();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ContentModelParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */