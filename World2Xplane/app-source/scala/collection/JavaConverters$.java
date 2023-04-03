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
/*     */ import scala.collection.convert.DecorateAsJava;
/*     */ import scala.collection.convert.DecorateAsScala;
/*     */ import scala.collection.convert.Decorators;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Seq;
/*     */ import scala.collection.mutable.Set;
/*     */ 
/*     */ public final class JavaConverters$ implements DecorateAsJava, DecorateAsScala {
/*     */   public static final JavaConverters$ MODULE$;
/*     */   
/*     */   public <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator i) {
/*  59 */     return DecorateAsScala.class.asScalaIteratorConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration i) {
/*  59 */     return DecorateAsScala.class.enumerationAsScalaIteratorConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(java.lang.Iterable i) {
/*  59 */     return DecorateAsScala.class.iterableAsScalaIterableConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection i) {
/*  59 */     return DecorateAsScala.class.collectionAsScalaIterableConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List l) {
/*  59 */     return DecorateAsScala.class.asScalaBufferConverter(this, l);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set s) {
/*  59 */     return DecorateAsScala.class.asScalaSetConverter(this, s);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map m) {
/*  59 */     return DecorateAsScala.class.mapAsScalaMapConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap m) {
/*  59 */     return DecorateAsScala.class.asScalaConcurrentMapConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap m) {
/*  59 */     return DecorateAsScala.class.mapAsScalaConcurrentMapConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary p) {
/*  59 */     return DecorateAsScala.class.dictionaryAsScalaMapConverter(this, p);
/*     */   }
/*     */   
/*     */   public Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties p) {
/*  59 */     return DecorateAsScala.class.propertiesAsScalaMapConverter(this, p);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator i) {
/*  59 */     return DecorateAsJava.class.asJavaIteratorConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator i) {
/*  59 */     return DecorateAsJava.class.asJavaEnumerationConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<java.lang.Iterable<A>> asJavaIterableConverter(Iterable i) {
/*  59 */     return DecorateAsJava.class.asJavaIterableConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable i) {
/*  59 */     return DecorateAsJava.class.asJavaCollectionConverter(this, i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer b) {
/*  59 */     return DecorateAsJava.class.bufferAsJavaListConverter(this, b);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq b) {
/*  59 */     return DecorateAsJava.class.mutableSeqAsJavaListConverter(this, b);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq b) {
/*  59 */     return DecorateAsJava.class.seqAsJavaListConverter(this, b);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set s) {
/*  59 */     return DecorateAsJava.class.mutableSetAsJavaSetConverter(this, s);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set s) {
/*  59 */     return DecorateAsJava.class.setAsJavaSetConverter(this, s);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map m) {
/*  59 */     return DecorateAsJava.class.mutableMapAsJavaMapConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map m) {
/*  59 */     return DecorateAsJava.class.asJavaDictionaryConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map m) {
/*  59 */     return DecorateAsJava.class.mapAsJavaMapConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap m) {
/*  59 */     return DecorateAsJava.class.asJavaConcurrentMapConverter(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map m) {
/*  59 */     return DecorateAsJava.class.mapAsJavaConcurrentMapConverter(this, m);
/*     */   }
/*     */   
/*     */   private JavaConverters$() {
/*  59 */     MODULE$ = this;
/*  59 */     DecorateAsJava.class.$init$(this);
/*  59 */     DecorateAsScala.class.$init$(this);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<List<A>> asJavaListConverter(Buffer<A> b) {
/*  72 */     return bufferAsJavaListConverter(b);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<List<A>> asJavaListConverter(Seq<A> b) {
/*  75 */     return mutableSeqAsJavaListConverter(b);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<List<A>> asJavaListConverter(Seq<A> b) {
/*  78 */     return seqAsJavaListConverter(b);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<Set<A>> asJavaSetConverter(Set<A> s) {
/*  81 */     return mutableSetAsJavaSetConverter(s);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsJava<Set<A>> asJavaSetConverter(Set<A> s) {
/*  84 */     return setAsJavaSetConverter(s);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJava<Map<A, B>> asJavaMapConverter(Map<A, B> m) {
/*  87 */     return mutableMapAsJavaMapConverter(m);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsJava<Map<A, B>> asJavaMapConverter(Map<A, B> m) {
/*  90 */     return mapAsJavaMapConverter(m);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Iterable<A>> asScalaIterableConverter(java.lang.Iterable<A> i) {
/*  93 */     return iterableAsScalaIterableConverter(i);
/*     */   }
/*     */   
/*     */   public <A> Decorators.AsScala<Iterable<A>> asScalaIterableConverter(Collection<A> i) {
/*  96 */     return collectionAsScalaIterableConverter(i);
/*     */   }
/*     */   
/*     */   public <A, B> Decorators.AsScala<Map<A, B>> asScalaMapConverter(Map<A, B> m) {
/*  99 */     return mapAsScalaMapConverter(m);
/*     */   }
/*     */   
/*     */   public Decorators.AsScala<Map<String, String>> asScalaMapConverter(Properties p) {
/* 102 */     return propertiesAsScalaMapConverter(p);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\JavaConverters$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */