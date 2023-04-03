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
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private final DecorateAsJava decorateAsJava;
/*    */   
/*    */   private final DecorateAsScala decorateAsScala;
/*    */   
/*    */   private final DecorateAsJava decorateAll;
/*    */   
/*    */   private final WrapAsJava wrapAsJava;
/*    */   
/*    */   private final WrapAsScala wrapAsScala;
/*    */   
/*    */   private final WrapAsJava wrapAll;
/*    */   
/*    */   private package$() {
/* 11 */     MODULE$ = this;
/* 12 */     this.decorateAsJava = new package.$anon$4();
/* 13 */     this.decorateAsScala = new package.$anon$5();
/* 14 */     this.decorateAll = new package.$anon$2();
/* 15 */     this.wrapAsJava = new package.$anon$6();
/* 16 */     this.wrapAsScala = new package.$anon$3();
/* 17 */     this.wrapAll = new package.$anon$1();
/*    */   }
/*    */   
/*    */   public DecorateAsJava decorateAsJava() {
/*    */     return this.decorateAsJava;
/*    */   }
/*    */   
/*    */   public static class $anon$4 implements DecorateAsJava {
/*    */     public <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator i) {
/*    */       return DecorateAsJava$class.asJavaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator i) {
/*    */       return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Iterable<A>> asJavaIterableConverter(Iterable i) {
/*    */       return DecorateAsJava$class.asJavaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable i) {
/*    */       return DecorateAsJava$class.asJavaCollectionConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer b) {
/*    */       return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq b) {
/*    */       return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq b) {
/*    */       return DecorateAsJava$class.seqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set s) {
/*    */       return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set s) {
/*    */       return DecorateAsJava$class.setAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map m) {
/*    */       return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map m) {
/*    */       return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map m) {
/*    */       return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap m) {
/*    */       return DecorateAsJava$class.asJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map m) {
/*    */       return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public $anon$4() {
/*    */       DecorateAsJava$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public DecorateAsScala decorateAsScala() {
/*    */     return this.decorateAsScala;
/*    */   }
/*    */   
/*    */   public static class $anon$5 implements DecorateAsScala {
/*    */     public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator i) {
/*    */       return DecorateAsScala$class.asScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration i) {
/*    */       return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(Iterable i) {
/*    */       return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection i) {
/*    */       return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List l) {
/*    */       return DecorateAsScala$class.asScalaBufferConverter(this, l);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set s) {
/*    */       return DecorateAsScala$class.asScalaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map m) {
/*    */       return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap m) {
/*    */       return DecorateAsScala$class.asScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap m) {
/*    */       return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary p) {
/*    */       return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
/*    */       return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public $anon$5() {
/*    */       DecorateAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public DecorateAsJava decorateAll() {
/*    */     return this.decorateAll;
/*    */   }
/*    */   
/*    */   public static class $anon$2 implements DecorateAsJava, DecorateAsScala {
/*    */     public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator i) {
/*    */       return DecorateAsScala$class.asScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration i) {
/*    */       return DecorateAsScala$class.enumerationAsScalaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(Iterable i) {
/*    */       return DecorateAsScala$class.iterableAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection i) {
/*    */       return DecorateAsScala$class.collectionAsScalaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List l) {
/*    */       return DecorateAsScala$class.asScalaBufferConverter(this, l);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set s) {
/*    */       return DecorateAsScala$class.asScalaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map m) {
/*    */       return DecorateAsScala$class.mapAsScalaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap m) {
/*    */       return DecorateAsScala$class.asScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap m) {
/*    */       return DecorateAsScala$class.mapAsScalaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary p) {
/*    */       return DecorateAsScala$class.dictionaryAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
/*    */       return DecorateAsScala$class.propertiesAsScalaMapConverter(this, p);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator i) {
/*    */       return DecorateAsJava$class.asJavaIteratorConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator i) {
/*    */       return DecorateAsJava$class.asJavaEnumerationConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Iterable<A>> asJavaIterableConverter(Iterable i) {
/*    */       return DecorateAsJava$class.asJavaIterableConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable i) {
/*    */       return DecorateAsJava$class.asJavaCollectionConverter(this, i);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer b) {
/*    */       return DecorateAsJava$class.bufferAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq b) {
/*    */       return DecorateAsJava$class.mutableSeqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq b) {
/*    */       return DecorateAsJava$class.seqAsJavaListConverter(this, b);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set s) {
/*    */       return DecorateAsJava$class.mutableSetAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set s) {
/*    */       return DecorateAsJava$class.setAsJavaSetConverter(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map m) {
/*    */       return DecorateAsJava$class.mutableMapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map m) {
/*    */       return DecorateAsJava$class.asJavaDictionaryConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map m) {
/*    */       return DecorateAsJava$class.mapAsJavaMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap m) {
/*    */       return DecorateAsJava$class.asJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map m) {
/*    */       return DecorateAsJava$class.mapAsJavaConcurrentMapConverter(this, m);
/*    */     }
/*    */     
/*    */     public $anon$2() {
/*    */       DecorateAsJava$class.$init$(this);
/*    */       DecorateAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public WrapAsJava wrapAsJava() {
/*    */     return this.wrapAsJava;
/*    */   }
/*    */   
/*    */   public static class $anon$6 implements WrapAsJava {
/*    */     public <A> Iterator<A> asJavaIterator(Iterator it) {
/*    */       return WrapAsJava$class.asJavaIterator(this, it);
/*    */     }
/*    */     
/*    */     public <A> Enumeration<A> asJavaEnumeration(Iterator it) {
/*    */       return WrapAsJava$class.asJavaEnumeration(this, it);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> asJavaIterable(Iterable i) {
/*    */       return WrapAsJava$class.asJavaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Collection<A> asJavaCollection(Iterable it) {
/*    */       return WrapAsJava$class.asJavaCollection(this, it);
/*    */     }
/*    */     
/*    */     public <A> List<A> bufferAsJavaList(Buffer b) {
/*    */       return WrapAsJava$class.bufferAsJavaList(this, b);
/*    */     }
/*    */     
/*    */     public <A> List<A> mutableSeqAsJavaList(Seq seq) {
/*    */       return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
/*    */     }
/*    */     
/*    */     public <A> List<A> seqAsJavaList(Seq seq) {
/*    */       return WrapAsJava$class.seqAsJavaList(this, seq);
/*    */     }
/*    */     
/*    */     public <A> Set<A> mutableSetAsJavaSet(Set s) {
/*    */       return WrapAsJava$class.mutableSetAsJavaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A> Set<A> setAsJavaSet(Set s) {
/*    */       return WrapAsJava$class.setAsJavaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mutableMapAsJavaMap(Map m) {
/*    */       return WrapAsJava$class.mutableMapAsJavaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Dictionary<A, B> asJavaDictionary(Map m) {
/*    */       return WrapAsJava$class.asJavaDictionary(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsJavaMap(Map m) {
/*    */       return WrapAsJava$class.mapAsJavaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap m) {
/*    */       return WrapAsJava$class.asJavaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map m) {
/*    */       return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public $anon$6() {
/*    */       WrapAsJava$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public WrapAsScala wrapAsScala() {
/*    */     return this.wrapAsScala;
/*    */   }
/*    */   
/*    */   public static class $anon$3 implements WrapAsScala {
/*    */     public <A> Iterator<A> asScalaIterator(Iterator it) {
/*    */       return WrapAsScala$class.asScalaIterator(this, it);
/*    */     }
/*    */     
/*    */     public <A> Iterator<A> enumerationAsScalaIterator(Enumeration i) {
/*    */       return WrapAsScala$class.enumerationAsScalaIterator(this, i);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> iterableAsScalaIterable(Iterable i) {
/*    */       return WrapAsScala$class.iterableAsScalaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Iterable<A> collectionAsScalaIterable(Collection i) {
/*    */       return WrapAsScala$class.collectionAsScalaIterable(this, i);
/*    */     }
/*    */     
/*    */     public <A> Buffer<A> asScalaBuffer(List l) {
/*    */       return WrapAsScala$class.asScalaBuffer(this, l);
/*    */     }
/*    */     
/*    */     public <A> Set<A> asScalaSet(Set s) {
/*    */       return WrapAsScala$class.asScalaSet(this, s);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsScalaMap(Map m) {
/*    */       return WrapAsScala$class.mapAsScalaMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap m) {
/*    */       return WrapAsScala$class.asScalaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap m) {
/*    */       return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary p) {
/*    */       return WrapAsScala$class.dictionaryAsScalaMap(this, p);
/*    */     }
/*    */     
/*    */     public Map<String, String> propertiesAsScalaMap(Properties p) {
/*    */       return WrapAsScala$class.propertiesAsScalaMap(this, p);
/*    */     }
/*    */     
/*    */     public <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap m) {
/*    */       return LowPriorityWrapAsScala$class.mapAsScalaDeprecatedConcurrentMap(this, m);
/*    */     }
/*    */     
/*    */     public $anon$3() {
/*    */       LowPriorityWrapAsScala$class.$init$(this);
/*    */       WrapAsScala$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public WrapAsJava wrapAll() {
/* 17 */     return this.wrapAll;
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */