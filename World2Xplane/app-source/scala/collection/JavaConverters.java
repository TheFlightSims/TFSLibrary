package scala.collection;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.concurrent.Map;
import scala.collection.convert.Decorators;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ConcurrentMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\tEr!B\001\003\021\0039\021A\004&bm\006\034uN\034<feR,'o\035\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\017\025\0064\030mQ8om\026\024H/\032:t'\021IA\002\005\f\021\0055qQ\"\001\003\n\005=!!AB!osJ+g\r\005\002\022)5\t!C\003\002\024\005\00591m\0348wKJ$\030BA\013\023\0059!UmY8sCR,\027i\035&bm\006\004\"!E\f\n\005a\021\"a\004#fG>\024\030\r^3BgN\033\027\r\\1\t\013iIA\021A\016\002\rqJg.\033;?)\0059Q\001B\017\n\001y\021a!Q:KCZ\fWCA\020)!\r\0013E\n\b\003#\005J!A\t\n\002\025\021+7m\034:bi>\0248/\003\002\036I%\021QE\005\002\013\t\026\034wN]1u_J\034\bCA\024)\031\001!Q!\013\017C\002)\022\021!Q\t\003W9\002\"!\004\027\n\0055\"!a\002(pi\"Lgn\032\t\003\033=J!\001\r\003\003\007\005s\027\020\013\003\035eU:\004CA\0074\023\t!DA\001\006eKB\024XmY1uK\022\f\023AN\001(\t>tw\005\036\021bG\016,7o\035\021uQ\026\034X\r\t3fG>\024\030\r^8sg\002\"\027N]3di2Lh&I\0019\003\031\021d&\r\031/a\025!!(\003\001<\005\035\t5oU2bY\006,\"\001P \021\007\001jd(\003\002;IA\021qe\020\003\006Se\022\rA\013\025\005sI*t'\002\003C\023\001\031%\001E!t\025\0064\030mQ8mY\026\034G/[8o+\t!u\tE\002!\013\032K!A\021\023\021\005\035:E!B\025B\005\004Q\003\006B!3k]*AAS\005\001\027\n\t\022i\035&bm\006,e.^7fe\006$\030n\0348\026\0051{\005c\001\021N\035&\021!\n\n\t\003O=#Q!K%C\002)BC!\023\0326o\025!!+\003\001T\005A\t5OS1wC\022K7\r^5p]\006\024\0300F\002U/f\003B\001I+W1&\021!\013\n\t\003O]#Q!K)C\002)\002\"aJ-\005\013i\013&\031\001\026\003\003\tCC!\025\0326o!)Q,\003C\001=\006\031\022m\035&bm\006d\025n\035;D_:4XM\035;feV\021ql\033\013\003A2\0042!\031\017c\033\005I\001cA2iU6\tAM\003\002fM\006!Q\017^5m\025\0059\027\001\0026bm\006L!!\0333\003\t1K7\017\036\t\003O-$Q!\013/C\002)BQ!\034/A\0029\f\021A\031\t\004_JTW\"\0019\013\005E\024\021aB7vi\006\024G.Z\005\003gB\024aAQ;gM\026\024\b\006\002/3k^\f\023A^\001&+N,\007EY;gM\026\024\030i\035&bm\006d\025n\035;D_:4XM\035;fe\002Jgn\035;fC\022\f\023\001_\001\006e9Jd\006\r\005\006;&!\tA_\013\003w~$2\001`A\001!\r\tG$ \t\004G\"t\bCA\024\000\t\025I\023P1\001+\021\031i\027\0201\001\002\004A!q.!\002\023\r\t9\001\035\002\004'\026\f\b&B=3\003\0279\030EAA\007\003%*6/\032\021nkR\f'\r\\3TKF\f5OS1wC2K7\017^\"p]Z,'\017^3sA%t7\017^3bI\"1Q,\003C\001\003#)B!a\005\002\034Q!\021QCA\017!\021\tG$a\006\021\t\rD\027\021\004\t\004O\005mAAB\025\002\020\t\007!\006C\004n\003\037\001\r!a\b\021\013!\t\t#!\007\n\007\005\035!\001\013\004\002\020I\n)c^\021\003\003O\t!%V:fAM,\027/Q:KCZ\fG*[:u\007>tg/\032:uKJ\004\023N\\:uK\006$\007bBA\026\023\021\005\021QF\001\023CNT\025M^1TKR\034uN\034<feR,'/\006\003\0020\005mB\003BA\031\003{\001B!\031\017\0024A)1-!\016\002:%\031\021q\0073\003\007M+G\017E\002(\003w!a!KA\025\005\004Q\003\002CA \003S\001\r!!\021\002\003M\004Ra\\A\"\003sI1!a\016qQ\031\tICMA$o\006\022\021\021J\001)+N,\007%\\;uC\ndWmU3u\003NT\025M^1TKR\034uN\034<feR,'\017I5ogR,\027\r\032\005\b\003WIA\021AA'+\021\ty%a\026\025\t\005E\023\021\f\t\005Cr\t\031\006E\003d\003k\t)\006E\002(\003/\"a!KA&\005\004Q\003\002CA \003\027\002\r!a\027\021\013!\ti&!\026\n\007\005]\"\001\013\004\002LI\n\tg^\021\003\003G\n\021%V:fAM,G/Q:KCZ\f7+\032;D_:4XM\035;fe\002Jgn\035;fC\022Dq!a\032\n\t\003\tI'\001\nbg*\013g/Y'ba\016{gN^3si\026\024XCBA6\003o\nY\b\006\003\002n\005u\004\003B1\035\003_\002raYA9\003k\nI(C\002\002t\021\0241!T1q!\r9\023q\017\003\007S\005\025$\031\001\026\021\007\035\nY\b\002\004[\003K\022\rA\013\005\t\003\n)\0071\001\002\002\006\tQ\016E\004p\003\007\013)(!\037\n\007\005M\004\017\013\004\002fI\n9i^\021\003\003\023\013\001&^:fA5,H/\0312mK6\013\007/Q:KCZ\fW*\0319D_:4XM\035;fe\002Jgn\035;fC\022Dq!a\032\n\t\003\ti)\006\004\002\020\006]\0251\024\013\005\003#\013i\n\005\003b9\005M\005cB2\002r\005U\025\021\024\t\004O\005]EAB\025\002\f\n\007!\006E\002(\0037#aAWAF\005\004Q\003\002CA@\003\027\003\r!a(\021\017!\t\t+!&\002\032&\031\0211\017\002)\r\005-%'!*xC\t\t9+A\021Vg\026\004S.\0319Bg*\013g/Y'ba\016{gN^3si\026\024\b%\0338ti\026\fG\rC\004\002,&!\t!!,\0021\005\0348kY1mC&#XM]1cY\026\034uN\034<feR,'/\006\003\0020\006mF\003BAY\003{\003B!Y\035\0024B)\001\"!.\002:&\031\021q\027\002\003\021%#XM]1cY\026\0042aJA^\t\031I\023\021\026b\001U!A\021qXAU\001\004\t\t-A\001j!\031\t\031-!3\002:6\021\021Q\031\006\004\003\0174\027\001\0027b]\036LA!a.\002F\"2\021\021\026\032\002N^\f#!a4\002YU\033X\rI5uKJ\f'\r\\3BgN\033\027\r\\1Ji\026\024\030M\0317f\007>tg/\032:uKJ\004\023N\\:uK\006$\007bBAV\023\021\005\0211[\013\005\003+\fi\016\006\003\002X\006}\007\003B1:\0033\004R\001CA[\0037\0042aJAo\t\031I\023\021\033b\001U!A\021qXAi\001\004\t\t\017E\003d\003G\fY.C\002\002f\022\024!bQ8mY\026\034G/[8oQ\031\t\tNMAuo\006\022\0211^\001/+N,\007eY8mY\026\034G/[8o\003N\0346-\0317b\023R,'/\0312mK\016{gN^3si\026\024\b%\0338ti\026\fG\rC\004\002p&!\t!!=\002'\005\0348kY1mC6\013\007oQ8om\026\024H/\032:\026\r\005M\0301`A\000)\021\t)P!\001\021\t\005L\024q\037\t\b_\006\r\025\021`A!\r9\0231 \003\007S\0055(\031\001\026\021\007\035\ny\020\002\004[\003[\024\rA\013\005\t\003\ni\0171\001\003\004A91-!\035\002z\006u\bFBAwe\t\035q/\t\002\003\n\005\021Sk]3![\006\004\030i]*dC2\fW*\0319D_:4XM\035;fe\002Jgn\035;fC\022Dq!a<\n\t\003\021i\001\006\003\003\020\t\005\002\003B1:\005#\001ra\\AB\005'\021\031\002\005\003\003\026\tmabA\007\003\030%\031!\021\004\003\002\rA\023X\rZ3g\023\021\021iBa\b\003\rM#(/\0338h\025\r\021I\002\002\005\t\005G\021Y\0011\001\003&\005\t\001\017E\002d\005OI1A!\013e\005)\001&o\0349feRLWm\035\025\007\005\027\021$QF<\"\005\t=\022!K+tK\002\002(o\0349feRLWm]!t'\016\fG.Y'ba\016{gN^3si\026\024\b%\0338ti\026\fG\r")
public final class JavaConverters {
  public static <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.mapAsJavaConcurrentMapConverter(paramMap);
  }
  
  public static <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConverters$.MODULE$.asJavaConcurrentMapConverter(paramConcurrentMap);
  }
  
  public static <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.mapAsJavaMapConverter(paramMap);
  }
  
  public static <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.asJavaDictionaryConverter(paramMap);
  }
  
  public static <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.mutableMapAsJavaMapConverter(paramMap);
  }
  
  public static <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set<A> paramSet) {
    return JavaConverters$.MODULE$.setAsJavaSetConverter(paramSet);
  }
  
  public static <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set<A> paramSet) {
    return JavaConverters$.MODULE$.mutableSetAsJavaSetConverter(paramSet);
  }
  
  public static <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq<A> paramSeq) {
    return JavaConverters$.MODULE$.seqAsJavaListConverter(paramSeq);
  }
  
  public static <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq<A> paramSeq) {
    return JavaConverters$.MODULE$.mutableSeqAsJavaListConverter(paramSeq);
  }
  
  public static <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer<A> paramBuffer) {
    return JavaConverters$.MODULE$.bufferAsJavaListConverter(paramBuffer);
  }
  
  public static <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable<A> paramIterable) {
    return JavaConverters$.MODULE$.asJavaCollectionConverter(paramIterable);
  }
  
  public static <A> Decorators.AsJava<java.lang.Iterable<A>> asJavaIterableConverter(Iterable<A> paramIterable) {
    return JavaConverters$.MODULE$.asJavaIterableConverter(paramIterable);
  }
  
  public static <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator<A> paramIterator) {
    return JavaConverters$.MODULE$.asJavaEnumerationConverter(paramIterator);
  }
  
  public static <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator<A> paramIterator) {
    return JavaConverters$.MODULE$.asJavaIteratorConverter(paramIterator);
  }
  
  public static Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties paramProperties) {
    return JavaConverters$.MODULE$.propertiesAsScalaMapConverter(paramProperties);
  }
  
  public static <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary<A, B> paramDictionary) {
    return JavaConverters$.MODULE$.dictionaryAsScalaMapConverter(paramDictionary);
  }
  
  public static <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConverters$.MODULE$.mapAsScalaConcurrentMapConverter(paramConcurrentMap);
  }
  
  public static <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConverters$.MODULE$.asScalaConcurrentMapConverter(paramConcurrentMap);
  }
  
  public static <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.mapAsScalaMapConverter(paramMap);
  }
  
  public static <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set<A> paramSet) {
    return JavaConverters$.MODULE$.asScalaSetConverter(paramSet);
  }
  
  public static <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List<A> paramList) {
    return JavaConverters$.MODULE$.asScalaBufferConverter(paramList);
  }
  
  public static <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection<A> paramCollection) {
    return JavaConverters$.MODULE$.collectionAsScalaIterableConverter(paramCollection);
  }
  
  public static <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(java.lang.Iterable<A> paramIterable) {
    return JavaConverters$.MODULE$.iterableAsScalaIterableConverter(paramIterable);
  }
  
  public static <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration<A> paramEnumeration) {
    return JavaConverters$.MODULE$.enumerationAsScalaIteratorConverter(paramEnumeration);
  }
  
  public static <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator<A> paramIterator) {
    return JavaConverters$.MODULE$.asScalaIteratorConverter(paramIterator);
  }
  
  public static Decorators.AsScala<Map<String, String>> asScalaMapConverter(Properties paramProperties) {
    return JavaConverters$.MODULE$.asScalaMapConverter(paramProperties);
  }
  
  public static <A, B> Decorators.AsScala<Map<A, B>> asScalaMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.asScalaMapConverter(paramMap);
  }
  
  public static <A> Decorators.AsScala<Iterable<A>> asScalaIterableConverter(Collection<A> paramCollection) {
    return JavaConverters$.MODULE$.asScalaIterableConverter(paramCollection);
  }
  
  public static <A> Decorators.AsScala<Iterable<A>> asScalaIterableConverter(java.lang.Iterable<A> paramIterable) {
    return JavaConverters$.MODULE$.asScalaIterableConverter(paramIterable);
  }
  
  public static <A, B> Decorators.AsJava<Map<A, B>> asJavaMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.asJavaMapConverter(paramMap);
  }
  
  public static <A, B> Decorators.AsJava<Map<A, B>> asJavaMapConverter(Map<A, B> paramMap) {
    return JavaConverters$.MODULE$.asJavaMapConverter(paramMap);
  }
  
  public static <A> Decorators.AsJava<Set<A>> asJavaSetConverter(Set<A> paramSet) {
    return JavaConverters$.MODULE$.asJavaSetConverter(paramSet);
  }
  
  public static <A> Decorators.AsJava<Set<A>> asJavaSetConverter(Set<A> paramSet) {
    return JavaConverters$.MODULE$.asJavaSetConverter(paramSet);
  }
  
  public static <A> Decorators.AsJava<List<A>> asJavaListConverter(Seq<A> paramSeq) {
    return JavaConverters$.MODULE$.asJavaListConverter(paramSeq);
  }
  
  public static <A> Decorators.AsJava<List<A>> asJavaListConverter(Seq<A> paramSeq) {
    return JavaConverters$.MODULE$.asJavaListConverter(paramSeq);
  }
  
  public static <A> Decorators.AsJava<List<A>> asJavaListConverter(Buffer<A> paramBuffer) {
    return JavaConverters$.MODULE$.asJavaListConverter(paramBuffer);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\JavaConverters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */