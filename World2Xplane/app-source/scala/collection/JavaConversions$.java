/*     */ package scala.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import scala.collection.concurrent.Map;
/*     */ import scala.collection.convert.LowPriorityWrapAsScala;
/*     */ import scala.collection.convert.WrapAsJava;
/*     */ import scala.collection.convert.WrapAsScala;
/*     */ import scala.collection.convert.Wrappers$;
/*     */ import scala.collection.convert.Wrappers$DictionaryWrapper$;
/*     */ import scala.collection.convert.Wrappers$IterableWrapper$;
/*     */ import scala.collection.convert.Wrappers$IteratorWrapper$;
/*     */ import scala.collection.convert.Wrappers$JCollectionWrapper$;
/*     */ import scala.collection.convert.Wrappers$JConcurrentMapWrapper$;
/*     */ import scala.collection.convert.Wrappers$JDictionaryWrapper$;
/*     */ import scala.collection.convert.Wrappers$JEnumerationWrapper$;
/*     */ import scala.collection.convert.Wrappers$JIterableWrapper$;
/*     */ import scala.collection.convert.Wrappers$JIteratorWrapper$;
/*     */ import scala.collection.convert.Wrappers$JListWrapper$;
/*     */ import scala.collection.convert.Wrappers$JMapWrapper$;
/*     */ import scala.collection.convert.Wrappers$JPropertiesWrapper$;
/*     */ import scala.collection.convert.Wrappers$JSetWrapper$;
/*     */ import scala.collection.convert.Wrappers$MutableBufferWrapper$;
/*     */ import scala.collection.convert.Wrappers$MutableMapWrapper$;
/*     */ import scala.collection.convert.Wrappers$MutableSeqWrapper$;
/*     */ import scala.collection.convert.Wrappers$MutableSetWrapper$;
/*     */ import scala.collection.convert.Wrappers$SeqWrapper$;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Seq;
/*     */ import scala.collection.mutable.Set;
/*     */ 
/*     */ public final class JavaConversions$ implements WrapAsScala, WrapAsJava {
/*     */   public static final JavaConversions$ MODULE$;
/*     */   
/*     */   private final Wrappers$DictionaryWrapper$ DictionaryWrapper;
/*     */   
/*     */   private final Wrappers$IterableWrapper$ IterableWrapper;
/*     */   
/*     */   private final Wrappers$IteratorWrapper$ IteratorWrapper;
/*     */   
/*     */   private final Wrappers$JCollectionWrapper$ JCollectionWrapper;
/*     */   
/*     */   private final Wrappers$JConcurrentMapWrapper$ JConcurrentMapWrapper;
/*     */   
/*     */   private final Wrappers$JDictionaryWrapper$ JDictionaryWrapper;
/*     */   
/*     */   private final Wrappers$JEnumerationWrapper$ JEnumerationWrapper;
/*     */   
/*     */   private final Wrappers$JIterableWrapper$ JIterableWrapper;
/*     */   
/*     */   private final Wrappers$JIteratorWrapper$ JIteratorWrapper;
/*     */   
/*     */   private final Wrappers$JListWrapper$ JListWrapper;
/*     */   
/*     */   private final Wrappers$JMapWrapper$ JMapWrapper;
/*     */   
/*     */   private final Wrappers$JPropertiesWrapper$ JPropertiesWrapper;
/*     */   
/*     */   private final Wrappers$JSetWrapper$ JSetWrapper;
/*     */   
/*     */   private final Wrappers$MutableBufferWrapper$ MutableBufferWrapper;
/*     */   
/*     */   private final Wrappers$MutableMapWrapper$ MutableMapWrapper;
/*     */   
/*     */   private final Wrappers$MutableSeqWrapper$ MutableSeqWrapper;
/*     */   
/*     */   private final Wrappers$MutableSetWrapper$ MutableSetWrapper;
/*     */   
/*     */   private final Wrappers$SeqWrapper$ SeqWrapper;
/*     */   
/*     */   public <A> Iterator<A> asJavaIterator(Iterator it) {
/*  53 */     return WrapAsJava.class.asJavaIterator(this, it);
/*     */   }
/*     */   
/*     */   public <A> Enumeration<A> asJavaEnumeration(Iterator it) {
/*  53 */     return WrapAsJava.class.asJavaEnumeration(this, it);
/*     */   }
/*     */   
/*     */   public <A> java.lang.Iterable<A> asJavaIterable(Iterable i) {
/*  53 */     return WrapAsJava.class.asJavaIterable(this, i);
/*     */   }
/*     */   
/*     */   public <A> Collection<A> asJavaCollection(Iterable it) {
/*  53 */     return WrapAsJava.class.asJavaCollection(this, it);
/*     */   }
/*     */   
/*     */   public <A> List<A> bufferAsJavaList(Buffer b) {
/*  53 */     return WrapAsJava.class.bufferAsJavaList(this, b);
/*     */   }
/*     */   
/*     */   public <A> List<A> mutableSeqAsJavaList(Seq seq) {
/*  53 */     return WrapAsJava.class.mutableSeqAsJavaList(this, seq);
/*     */   }
/*     */   
/*     */   public <A> List<A> seqAsJavaList(Seq seq) {
/*  53 */     return WrapAsJava.class.seqAsJavaList(this, seq);
/*     */   }
/*     */   
/*     */   public <A> Set<A> mutableSetAsJavaSet(Set s) {
/*  53 */     return WrapAsJava.class.mutableSetAsJavaSet(this, s);
/*     */   }
/*     */   
/*     */   public <A> Set<A> setAsJavaSet(Set s) {
/*  53 */     return WrapAsJava.class.setAsJavaSet(this, s);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mutableMapAsJavaMap(Map m) {
/*  53 */     return WrapAsJava.class.mutableMapAsJavaMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Dictionary<A, B> asJavaDictionary(Map m) {
/*  53 */     return WrapAsJava.class.asJavaDictionary(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mapAsJavaMap(Map m) {
/*  53 */     return WrapAsJava.class.mapAsJavaMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap m) {
/*  53 */     return WrapAsJava.class.asJavaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map m) {
/*  53 */     return WrapAsJava.class.mapAsJavaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A> Iterator<A> asScalaIterator(Iterator it) {
/*  53 */     return WrapAsScala.class.asScalaIterator(this, it);
/*     */   }
/*     */   
/*     */   public <A> Iterator<A> enumerationAsScalaIterator(Enumeration i) {
/*  53 */     return WrapAsScala.class.enumerationAsScalaIterator(this, i);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> iterableAsScalaIterable(java.lang.Iterable i) {
/*  53 */     return WrapAsScala.class.iterableAsScalaIterable(this, i);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> collectionAsScalaIterable(Collection i) {
/*  53 */     return WrapAsScala.class.collectionAsScalaIterable(this, i);
/*     */   }
/*     */   
/*     */   public <A> Buffer<A> asScalaBuffer(List l) {
/*  53 */     return WrapAsScala.class.asScalaBuffer(this, l);
/*     */   }
/*     */   
/*     */   public <A> Set<A> asScalaSet(Set s) {
/*  53 */     return WrapAsScala.class.asScalaSet(this, s);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mapAsScalaMap(Map m) {
/*  53 */     return WrapAsScala.class.mapAsScalaMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap m) {
/*  53 */     return WrapAsScala.class.asScalaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap m) {
/*  53 */     return WrapAsScala.class.mapAsScalaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary p) {
/*  53 */     return WrapAsScala.class.dictionaryAsScalaMap(this, p);
/*     */   }
/*     */   
/*     */   public Map<String, String> propertiesAsScalaMap(Properties p) {
/*  53 */     return WrapAsScala.class.propertiesAsScalaMap(this, p);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap m) {
/*  53 */     return LowPriorityWrapAsScala.class.mapAsScalaDeprecatedConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   private JavaConversions$() {
/*  53 */     MODULE$ = this;
/*  53 */     LowPriorityWrapAsScala.class.$init$(this);
/*  53 */     WrapAsScala.class.$init$(this);
/*  53 */     WrapAsJava.class.$init$(this);
/*  76 */     this.DictionaryWrapper = Wrappers$.MODULE$.DictionaryWrapper();
/*  77 */     this.IterableWrapper = Wrappers$.MODULE$.IterableWrapper();
/*  78 */     this.IteratorWrapper = Wrappers$.MODULE$.IteratorWrapper();
/*  79 */     this.JCollectionWrapper = Wrappers$.MODULE$.JCollectionWrapper();
/*  80 */     this.JConcurrentMapWrapper = Wrappers$.MODULE$.JConcurrentMapWrapper();
/*  81 */     this.JDictionaryWrapper = Wrappers$.MODULE$.JDictionaryWrapper();
/*  82 */     this.JEnumerationWrapper = Wrappers$.MODULE$.JEnumerationWrapper();
/*  83 */     this.JIterableWrapper = Wrappers$.MODULE$.JIterableWrapper();
/*  84 */     this.JIteratorWrapper = Wrappers$.MODULE$.JIteratorWrapper();
/*  85 */     this.JListWrapper = Wrappers$.MODULE$.JListWrapper();
/*  86 */     this.JMapWrapper = Wrappers$.MODULE$.JMapWrapper();
/*  87 */     this.JPropertiesWrapper = Wrappers$.MODULE$.JPropertiesWrapper();
/*  88 */     this.JSetWrapper = Wrappers$.MODULE$.JSetWrapper();
/*  89 */     this.MutableBufferWrapper = Wrappers$.MODULE$.MutableBufferWrapper();
/*  90 */     this.MutableMapWrapper = Wrappers$.MODULE$.MutableMapWrapper();
/*  91 */     this.MutableSeqWrapper = Wrappers$.MODULE$.MutableSeqWrapper();
/*  92 */     this.MutableSetWrapper = Wrappers$.MODULE$.MutableSetWrapper();
/*  93 */     this.SeqWrapper = Wrappers$.MODULE$.SeqWrapper();
/*     */   }
/*     */   
/*     */   public Wrappers$DictionaryWrapper$ DictionaryWrapper() {
/*     */     return this.DictionaryWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$IterableWrapper$ IterableWrapper() {
/*     */     return this.IterableWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$IteratorWrapper$ IteratorWrapper() {
/*     */     return this.IteratorWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JCollectionWrapper$ JCollectionWrapper() {
/*     */     return this.JCollectionWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JConcurrentMapWrapper$ JConcurrentMapWrapper() {
/*     */     return this.JConcurrentMapWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JDictionaryWrapper$ JDictionaryWrapper() {
/*     */     return this.JDictionaryWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JEnumerationWrapper$ JEnumerationWrapper() {
/*     */     return this.JEnumerationWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JIterableWrapper$ JIterableWrapper() {
/*     */     return this.JIterableWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JIteratorWrapper$ JIteratorWrapper() {
/*     */     return this.JIteratorWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JListWrapper$ JListWrapper() {
/*     */     return this.JListWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JMapWrapper$ JMapWrapper() {
/*     */     return this.JMapWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JPropertiesWrapper$ JPropertiesWrapper() {
/*     */     return this.JPropertiesWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$JSetWrapper$ JSetWrapper() {
/*     */     return this.JSetWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$MutableBufferWrapper$ MutableBufferWrapper() {
/*     */     return this.MutableBufferWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$MutableMapWrapper$ MutableMapWrapper() {
/*     */     return this.MutableMapWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$MutableSeqWrapper$ MutableSeqWrapper() {
/*     */     return this.MutableSeqWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$MutableSetWrapper$ MutableSetWrapper() {
/*     */     return this.MutableSetWrapper;
/*     */   }
/*     */   
/*     */   public Wrappers$SeqWrapper$ SeqWrapper() {
/*  93 */     return this.SeqWrapper;
/*     */   }
/*     */   
/*     */   public <A> List<A> asJavaList(Buffer<A> b) {
/*  99 */     return bufferAsJavaList(b);
/*     */   }
/*     */   
/*     */   public <A> List<A> asJavaList(Seq<A> b) {
/* 102 */     return mutableSeqAsJavaList(b);
/*     */   }
/*     */   
/*     */   public <A> List<A> asJavaList(Seq<A> b) {
/* 105 */     return seqAsJavaList(b);
/*     */   }
/*     */   
/*     */   public <A> Set<A> asJavaSet(Set<A> s) {
/* 108 */     return mutableSetAsJavaSet(s);
/*     */   }
/*     */   
/*     */   public <A> Set<A> asJavaSet(Set<A> s) {
/* 111 */     return setAsJavaSet(s);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> asJavaMap(Map<A, B> m) {
/* 114 */     return mutableMapAsJavaMap(m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> asJavaMap(Map<A, B> m) {
/* 117 */     return mapAsJavaMap(m);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> asScalaIterable(java.lang.Iterable<A> i) {
/* 120 */     return iterableAsScalaIterable(i);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> asScalaIterable(Collection<A> i) {
/* 123 */     return collectionAsScalaIterable(i);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> asScalaMap(Map<A, B> m) {
/* 126 */     return mapAsScalaMap(m);
/*     */   }
/*     */   
/*     */   public Map<String, String> asScalaMap(Properties p) {
/* 129 */     return propertiesAsScalaMap(p);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\JavaConversions$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */