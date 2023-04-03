/*    */ package scala.collection.convert;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Dictionary;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentMap;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.concurrent.Map;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.ConcurrentMap;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.collection.mutable.Seq;
/*    */ import scala.collection.mutable.Set;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\0011;Q!\001\002\t\002%\tq\001]1dW\006<WM\003\002\004\t\00591m\0348wKJ$(BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001\001C\001\006\f\033\005\021a!\002\007\003\021\003i!a\0029bG.\fw-Z\n\003\0279\001\"a\004\t\016\003\031I!!\005\004\003\r\005s\027PU3g\021\025\0312\002\"\001\025\003\031a\024N\\5u}Q\t\021\002C\004\027\027\t\007I\021A\f\002\035\021,7m\034:bi\026\f5OS1wCV\t\001DE\002\032\035u1AAG\016\0011\taAH]3gS:,W.\0328u}!1Ad\003Q\001\na\tq\002Z3d_J\fG/Z!t\025\0064\030\r\t\t\003\025yI!a\b\002\003\035\021+7m\034:bi\026\f5OS1wC\"9\021e\003b\001\n\003\021\023a\0043fG>\024\030\r^3BgN\033\027\r\\1\026\003\r\0222\001\n\b(\r\021QR\005A\022\t\r\031Z\001\025!\003$\003A!WmY8sCR,\027i]*dC2\f\007\005\005\002\013Q%\021\021F\001\002\020\t\026\034wN]1uK\006\0338kY1mC\"91f\003b\001\n\003a\023a\0033fG>\024\030\r^3BY2,\022!\f\n\005]9irE\002\003\033_\001i\003B\002\031\fA\003%Q&\001\007eK\016|'/\031;f\0032d\007\005C\0043\027\t\007I\021A\032\002\025]\024\030\r]!t\025\0064\030-F\0015%\r)d\002\017\004\0055Y\002A\007\003\0048\027\001\006I\001N\001\foJ\f\007/Q:KCZ\f\007\005\005\002\013s%\021!H\001\002\013/J\f\007/Q:KCZ\f\007b\002\037\f\005\004%\t!P\001\foJ\f\007/Q:TG\006d\027-F\001?%\rydB\021\004\0055\001\003a\b\003\004B\027\001\006IAP\001\roJ\f\007/Q:TG\006d\027\r\t\t\003\025\rK!\001\022\002\003\027]\023\030\r]!t'\016\fG.\031\005\b\r.\021\r\021\"\001H\003\0359(/\0319BY2,\022\001\023\n\005\023:A$I\002\003\033\025\002A\005BB&\fA\003%\001*\001\005xe\006\004\030\t\0347!\001")
/*    */ public final class package {
/*    */   public static WrapAsJava wrapAll() {
/*    */     return package$.MODULE$.wrapAll();
/*    */   }
/*    */   
/*    */   public static WrapAsScala wrapAsScala() {
/*    */     return package$.MODULE$.wrapAsScala();
/*    */   }
/*    */   
/*    */   public static WrapAsJava wrapAsJava() {
/*    */     return package$.MODULE$.wrapAsJava();
/*    */   }
/*    */   
/*    */   public static DecorateAsJava decorateAll() {
/*    */     return package$.MODULE$.decorateAll();
/*    */   }
/*    */   
/*    */   public static DecorateAsScala decorateAsScala() {
/*    */     return package$.MODULE$.decorateAsScala();
/*    */   }
/*    */   
/*    */   public static DecorateAsJava decorateAsJava() {
/*    */     return package$.MODULE$.decorateAsJava();
/*    */   }
/*    */   
/*    */   public static class $anon$4 implements DecorateAsJava {
/*    */     public <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator i) {
/* 12 */       return DecorateAsJava$class.asJavaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator i) {
/* 12 */       return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Iterable<A>> asJavaIterableConverter(Iterable i) {
/* 12 */       return DecorateAsJava$class.asJavaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable i) {
/* 12 */       return DecorateAsJava$class.asJavaCollectionConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer b) {
/* 12 */       return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq b) {
/* 12 */       return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq b) {
/* 12 */       return DecorateAsJava$class.seqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set s) {
/* 12 */       return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set s) {
/* 12 */       return DecorateAsJava$class.setAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map m) {
/* 12 */       return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map m) {
/* 12 */       return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map m) {
/* 12 */       return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap m) {
/* 12 */       return DecorateAsJava$class.asJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map m) {
/* 12 */       return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public $anon$4() {
/* 12 */       DecorateAsJava$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anon$5 implements DecorateAsScala {
/*    */     public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator i) {
/* 13 */       return DecorateAsScala$class.asScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration i) {
/* 13 */       return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(Iterable i) {
/* 13 */       return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection i) {
/* 13 */       return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List l) {
/* 13 */       return DecorateAsScala$class.asScalaBufferConverter(this, l);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set s) {
/* 13 */       return DecorateAsScala$class.asScalaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map m) {
/* 13 */       return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap m) {
/* 13 */       return DecorateAsScala$class.asScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap m) {
/* 13 */       return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary p) {
/* 13 */       return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
/* 13 */       return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public $anon$5() {
/* 13 */       DecorateAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anon$2 implements DecorateAsJava, DecorateAsScala {
/*    */     public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator i) {
/* 14 */       return DecorateAsScala$class.asScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration i) {
/* 14 */       return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(Iterable i) {
/* 14 */       return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection i) {
/* 14 */       return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List l) {
/* 14 */       return DecorateAsScala$class.asScalaBufferConverter(this, l);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set s) {
/* 14 */       return DecorateAsScala$class.asScalaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map m) {
/* 14 */       return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap m) {
/* 14 */       return DecorateAsScala$class.asScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap m) {
/* 14 */       return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary p) {
/* 14 */       return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
/* 14 */       return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator i) {
/* 14 */       return DecorateAsJava$class.asJavaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator i) {
/* 14 */       return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Iterable<A>> asJavaIterableConverter(Iterable i) {
/* 14 */       return DecorateAsJava$class.asJavaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable i) {
/* 14 */       return DecorateAsJava$class.asJavaCollectionConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer b) {
/* 14 */       return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq b) {
/* 14 */       return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq b) {
/* 14 */       return DecorateAsJava$class.seqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set s) {
/* 14 */       return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set s) {
/* 14 */       return DecorateAsJava$class.setAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map m) {
/* 14 */       return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map m) {
/* 14 */       return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map m) {
/* 14 */       return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap m) {
/* 14 */       return DecorateAsJava$class.asJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map m) {
/* 14 */       return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public $anon$2() {
/* 14 */       DecorateAsJava$class.$init$(this);
/* 14 */       DecorateAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anon$6 implements WrapAsJava {
/*    */     public <A> Iterator<A> asJavaIterator(Iterator it) {
/* 15 */       return WrapAsJava$class.asJavaIterator(this, it);
/*    */     }
/*    */     
/*    */     public <A> Enumeration<A> asJavaEnumeration(Iterator it) {
/* 15 */       return WrapAsJava$class.asJavaEnumeration(this, it);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> asJavaIterable(Iterable i) {
/* 15 */       return WrapAsJava$class.asJavaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Collection<A> asJavaCollection(Iterable it) {
/* 15 */       return WrapAsJava$class.asJavaCollection(this, it);
/*    */     }
/*    */     
/*    */     public <A> List<A> bufferAsJavaList(Buffer b) {
/* 15 */       return WrapAsJava$class.bufferAsJavaList(this, b);
/*    */     }
/*    */     
/*    */     public <A> List<A> mutableSeqAsJavaList(Seq seq) {
/* 15 */       return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
/*    */     }
/*    */     
/*    */     public <A> List<A> seqAsJavaList(Seq seq) {
/* 15 */       return WrapAsJava$class.seqAsJavaList(this, seq);
/*    */     }
/*    */     
/*    */     public <A> Set<A> mutableSetAsJavaSet(Set s) {
/* 15 */       return WrapAsJava$class.mutableSetAsJavaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A> Set<A> setAsJavaSet(Set s) {
/* 15 */       return WrapAsJava$class.setAsJavaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mutableMapAsJavaMap(Map m) {
/* 15 */       return WrapAsJava$class.mutableMapAsJavaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Dictionary<A, B> asJavaDictionary(Map m) {
/* 15 */       return WrapAsJava$class.asJavaDictionary(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsJavaMap(Map m) {
/* 15 */       return WrapAsJava$class.mapAsJavaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap m) {
/* 15 */       return WrapAsJava$class.asJavaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map m) {
/* 15 */       return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public $anon$6() {
/* 15 */       WrapAsJava$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anon$3 implements WrapAsScala {
/*    */     public <A> Iterator<A> asScalaIterator(Iterator it) {
/* 16 */       return WrapAsScala$class.asScalaIterator(this, it);
/*    */     }
/*    */     
/*    */     public <A> Iterator<A> enumerationAsScalaIterator(Enumeration i) {
/* 16 */       return WrapAsScala$class.enumerationAsScalaIterator(this, i);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> iterableAsScalaIterable(Iterable i) {
/* 16 */       return WrapAsScala$class.iterableAsScalaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> collectionAsScalaIterable(Collection i) {
/* 16 */       return WrapAsScala$class.collectionAsScalaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Buffer<A> asScalaBuffer(List l) {
/* 16 */       return WrapAsScala$class.asScalaBuffer(this, l);
/*    */     }
/*    */     
/*    */     public <A> Set<A> asScalaSet(Set s) {
/* 16 */       return WrapAsScala$class.asScalaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsScalaMap(Map m) {
/* 16 */       return WrapAsScala$class.mapAsScalaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap m) {
/* 16 */       return WrapAsScala$class.asScalaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap m) {
/* 16 */       return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary p) {
/* 16 */       return WrapAsScala$class.dictionaryAsScalaMap(this, p);
/*    */     }
/*    */     
/*    */     public Map<String, String> propertiesAsScalaMap(Properties p) {
/* 16 */       return WrapAsScala$class.propertiesAsScalaMap(this, p);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap m) {
/* 16 */       return LowPriorityWrapAsScala$class.mapAsScalaDeprecatedConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public $anon$3() {
/* 16 */       LowPriorityWrapAsScala$class.$init$(this);
/* 16 */       WrapAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anon$1 implements WrapAsJava, WrapAsScala {
/*    */     public <A> Iterator<A> asScalaIterator(Iterator it) {
/* 17 */       return WrapAsScala$class.asScalaIterator(this, it);
/*    */     }
/*    */     
/*    */     public <A> Iterator<A> enumerationAsScalaIterator(Enumeration i) {
/* 17 */       return WrapAsScala$class.enumerationAsScalaIterator(this, i);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> iterableAsScalaIterable(Iterable i) {
/* 17 */       return WrapAsScala$class.iterableAsScalaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> collectionAsScalaIterable(Collection i) {
/* 17 */       return WrapAsScala$class.collectionAsScalaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Buffer<A> asScalaBuffer(List l) {
/* 17 */       return WrapAsScala$class.asScalaBuffer(this, l);
/*    */     }
/*    */     
/*    */     public <A> Set<A> asScalaSet(Set s) {
/* 17 */       return WrapAsScala$class.asScalaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsScalaMap(Map m) {
/* 17 */       return WrapAsScala$class.mapAsScalaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap m) {
/* 17 */       return WrapAsScala$class.asScalaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap m) {
/* 17 */       return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary p) {
/* 17 */       return WrapAsScala$class.dictionaryAsScalaMap(this, p);
/*    */     }
/*    */     
/*    */     public Map<String, String> propertiesAsScalaMap(Properties p) {
/* 17 */       return WrapAsScala$class.propertiesAsScalaMap(this, p);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap m) {
/* 17 */       return LowPriorityWrapAsScala$class.mapAsScalaDeprecatedConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A> Iterator<A> asJavaIterator(Iterator it) {
/* 17 */       return WrapAsJava$class.asJavaIterator(this, it);
/*    */     }
/*    */     
/*    */     public <A> Enumeration<A> asJavaEnumeration(Iterator it) {
/* 17 */       return WrapAsJava$class.asJavaEnumeration(this, it);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> asJavaIterable(Iterable i) {
/* 17 */       return WrapAsJava$class.asJavaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Collection<A> asJavaCollection(Iterable it) {
/* 17 */       return WrapAsJava$class.asJavaCollection(this, it);
/*    */     }
/*    */     
/*    */     public <A> List<A> bufferAsJavaList(Buffer b) {
/* 17 */       return WrapAsJava$class.bufferAsJavaList(this, b);
/*    */     }
/*    */     
/*    */     public <A> List<A> mutableSeqAsJavaList(Seq seq) {
/* 17 */       return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
/*    */     }
/*    */     
/*    */     public <A> List<A> seqAsJavaList(Seq seq) {
/* 17 */       return WrapAsJava$class.seqAsJavaList(this, seq);
/*    */     }
/*    */     
/*    */     public <A> Set<A> mutableSetAsJavaSet(Set s) {
/* 17 */       return WrapAsJava$class.mutableSetAsJavaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A> Set<A> setAsJavaSet(Set s) {
/* 17 */       return WrapAsJava$class.setAsJavaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mutableMapAsJavaMap(Map m) {
/* 17 */       return WrapAsJava$class.mutableMapAsJavaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Dictionary<A, B> asJavaDictionary(Map m) {
/* 17 */       return WrapAsJava$class.asJavaDictionary(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsJavaMap(Map m) {
/* 17 */       return WrapAsJava$class.mapAsJavaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap m) {
/* 17 */       return WrapAsJava$class.asJavaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map m) {
/* 17 */       return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public $anon$1() {
/* 17 */       WrapAsJava$class.$init$(this);
/* 17 */       LowPriorityWrapAsScala$class.$init$(this);
/* 17 */       WrapAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */