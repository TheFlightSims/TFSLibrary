package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.concurrent.Map;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ConcurrentMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005ugaB\001\003!\003\r\t!\003\002\013/J\f\007/Q:KCZ\f'BA\002\005\003\035\031wN\034<feRT!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001\031\"\001\001\006\021\005-aQ\"\001\004\n\00551!AB!osJ+g\rC\003\020\001\021\005\001#\001\004%S:LG\017\n\013\002#A\0211BE\005\003'\031\021A!\0268ji\")Q\003\001C\002-\005q\021m\035&bm\006LE/\032:bi>\024XCA\f#)\tA2\006E\002\032=\001j\021A\007\006\0037q\tA!\036;jY*\tQ$\001\003kCZ\f\027BA\020\033\005!IE/\032:bi>\024\bCA\021#\031\001!Qa\t\013C\002\021\022\021!Q\t\003K!\002\"a\003\024\n\005\0352!a\002(pi\"Lgn\032\t\003\027%J!A\013\004\003\007\005s\027\020C\003-)\001\007Q&\001\002jiB\031af\f\021\016\003\021I!a\b\003\t\013E\002A1\001\032\002#\005\034(*\031<b\013:,X.\032:bi&|g.\006\0024qQ\021A'\017\t\0043U:\024B\001\034\033\005-)e.^7fe\006$\030n\0348\021\005\005BD!B\0221\005\004!\003\"\002\0271\001\004Q\004c\001\0300o!)A\b\001C\002{\005q\021m\035&bm\006LE/\032:bE2,WC\001 G)\tyt\tE\002A\007\026k\021!\021\006\003\005r\tA\001\\1oO&\021A)\021\002\t\023R,'/\0312mKB\021\021E\022\003\006Gm\022\r\001\n\005\006\021n\002\r!S\001\002SB\031aFS#\n\005\021#\001\"\002'\001\t\007i\025\001E1t\025\0064\030mQ8mY\026\034G/[8o+\tq5\013\006\002P)B\031\021\004\025*\n\005ES\"AC\"pY2,7\r^5p]B\021\021e\025\003\006G-\023\r\001\n\005\006Y-\003\r!\026\t\004])\023\006\"B,\001\t\007A\026\001\0052vM\032,'/Q:KCZ\fG*[:u+\tIf\f\006\002[?B\031\021dW/\n\005qS\"\001\002'jgR\004\"!\t0\005\013\r2&\031\001\023\t\013\0014\006\031A1\002\003\t\0042AY3^\033\005\031'B\0013\005\003\035iW\017^1cY\026L!AZ2\003\r\t+hMZ3s\021\025A\007\001b\001j\003QiW\017^1cY\026\034V-]!t\025\0064\030\rT5tiV\021!.\034\013\003W:\0042!G.m!\t\tS\016B\003$O\n\007A\005C\003pO\002\007\001/A\002tKF\0042AY9m\023\t\0218MA\002TKFDQ\001\036\001\005\004U\fQb]3r\003NT\025M^1MSN$XC\001<z)\t9(\020E\002\0327b\004\"!I=\005\013\r\032(\031\001\023\t\013=\034\b\031A>\021\0079b\b0\003\002s\t!)a\020\001C\002\006\031R.\036;bE2,7+\032;Bg*\013g/Y*fiV!\021\021AA\006)\021\t\031!!\004\021\013e\t)!!\003\n\007\005\035!DA\002TKR\0042!IA\006\t\025\031SP1\001%\021\035\ty! a\001\003#\t\021a\035\t\006E\006M\021\021B\005\004\003\017\031\007bBA\f\001\021\r\021\021D\001\rg\026$\030i\035&bm\006\034V\r^\013\005\0037\t\t\003\006\003\002\036\005\r\002#B\r\002\006\005}\001cA\021\002\"\02111%!\006C\002\021B\001\"a\004\002\026\001\007\021Q\005\t\006]\005\035\022qD\005\004\003\017!\001bBA\026\001\021\r\021QF\001\024[V$\030M\0317f\033\006\004\030i\035&bm\006l\025\r]\013\007\003_\tI$!\020\025\t\005E\022\021\t\t\b3\005M\022qGA\036\023\r\t)D\007\002\004\033\006\004\bcA\021\002:\02111%!\013C\002\021\0022!IA\037\t\035\ty$!\013C\002\021\022\021A\021\005\t\003\007\nI\0031\001\002F\005\tQ\016E\004c\003\017\n9$a\017\n\007\005U2\rC\004\002L\001!\031!!\024\002!\005\034(*\031<b\t&\034G/[8oCJLXCBA(\0033\ni\006\006\003\002R\005}\003cB\r\002T\005]\0231L\005\004\003+R\"A\003#jGRLwN\\1ssB\031\021%!\027\005\r\r\nIE1\001%!\r\t\023Q\f\003\b\003\tIE1\001%\021!\t\031%!\023A\002\005\005\004c\0022\002H\005]\0231\f\005\b\003K\002A1AA4\0031i\027\r]!t\025\0064\030-T1q+\031\tI'a\034\002tQ!\0211NA;!\035I\0221GA7\003c\0022!IA8\t\031\031\0231\rb\001IA\031\021%a\035\005\017\005}\0221\rb\001I!A\0211IA2\001\004\t9\bE\004/\003s\ni'!\035\n\007\005UB\001C\004\002~\001!\031!a \002'\005\034(*\031<b\007>t7-\036:sK:$X*\0319\026\r\005\005\025\021SAK)\021\t\031)a&\021\021\005\025\0251RAH\003'k!!a\"\013\007\005%%$\001\006d_:\034WO\035:f]RLA!!$\002\b\ni1i\0348dkJ\024XM\034;NCB\0042!IAI\t\031\031\0231\020b\001IA\031\021%!&\005\017\005}\0221\020b\001I!A\0211IA>\001\004\tI\nE\004c\0037\013y)a%\n\007\00555\r\013\005\002|\005}\025QUAU!\rY\021\021U\005\004\003G3!A\0033faJ,7-\031;fI\006\022\021qU\0011+N,\007\005Y2p]\016,(O]3oi:j\025\r\0351!S:\034H/Z1eA=4\007\005Y\"p]\016,(O]3oi6\013\007\017\031\030\"\005\005-\026A\002\032/cAr\003\007C\004\0020\002!\031!!-\002-5\f\007/Q:KCZ\f7i\0348dkJ\024XM\034;NCB,b!a-\002:\006uF\003BA[\003\003\002\"!\"\002\f\006]\0261\030\t\004C\005eFAB\022\002.\n\007A\005E\002\"\003{#q!a\020\002.\n\007A\005\003\005\002D\0055\006\031AAa!!\t\031-a2\0028\006mVBAAc\025\r\tI\tB\005\005\003k\t)mB\004\002L\nA\t!!4\002\025]\023\030\r]!t\025\0064\030\r\005\003\002P\006EW\"\001\002\007\r\005\021\001\022AAj'\025\t\tNCAk!\r\ty\r\001\005\t\0033\f\t\016\"\001\002\\\0061A(\0338jiz\"\"!!4")
public interface WrapAsJava {
  <A> Iterator<A> asJavaIterator(Iterator<A> paramIterator);
  
  <A> Enumeration<A> asJavaEnumeration(Iterator<A> paramIterator);
  
  <A> Iterable<A> asJavaIterable(Iterable<A> paramIterable);
  
  <A> Collection<A> asJavaCollection(Iterable<A> paramIterable);
  
  <A> List<A> bufferAsJavaList(Buffer<A> paramBuffer);
  
  <A> List<A> mutableSeqAsJavaList(Seq<A> paramSeq);
  
  <A> List<A> seqAsJavaList(Seq<A> paramSeq);
  
  <A> Set<A> mutableSetAsJavaSet(Set<A> paramSet);
  
  <A> Set<A> setAsJavaSet(Set<A> paramSet);
  
  <A, B> Map<A, B> mutableMapAsJavaMap(Map<A, B> paramMap);
  
  <A, B> Dictionary<A, B> asJavaDictionary(Map<A, B> paramMap);
  
  <A, B> Map<A, B> mapAsJavaMap(Map<A, B> paramMap);
  
  <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap);
  
  <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map<A, B> paramMap);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\WrapAsJava.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */