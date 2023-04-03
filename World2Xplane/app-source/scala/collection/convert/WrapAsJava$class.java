/*     */ package scala.collection.convert;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.concurrent.Map;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Seq;
/*     */ import scala.collection.mutable.Set;
/*     */ 
/*     */ public abstract class WrapAsJava$class {
/*     */   public static void $init$(WrapAsJava $this) {}
/*     */   
/*     */   public static Iterator asJavaIterator(WrapAsJava $this, Iterator<?> it) {
/*     */     Iterator iterator;
/*  31 */     if (it instanceof Wrappers.JIteratorWrapper) {
/*  31 */       Wrappers.JIteratorWrapper jIteratorWrapper = (Wrappers.JIteratorWrapper)it;
/*  32 */       iterator = jIteratorWrapper.underlying();
/*     */     } else {
/*  33 */       iterator = new Wrappers.IteratorWrapper(Wrappers$.MODULE$, it);
/*     */     } 
/*     */     return iterator;
/*     */   }
/*     */   
/*     */   public static Enumeration asJavaEnumeration(WrapAsJava $this, Iterator<?> it) {
/*     */     Enumeration enumeration;
/*  49 */     if (it instanceof Wrappers.JEnumerationWrapper) {
/*  49 */       Wrappers.JEnumerationWrapper jEnumerationWrapper = (Wrappers.JEnumerationWrapper)it;
/*  50 */       enumeration = jEnumerationWrapper.underlying();
/*     */     } else {
/*  51 */       enumeration = new Wrappers.IteratorWrapper(Wrappers$.MODULE$, it);
/*     */     } 
/*     */     return enumeration;
/*     */   }
/*     */   
/*     */   public static Iterable asJavaIterable(WrapAsJava $this, Iterable<?> i) {
/*     */     Iterable iterable;
/*  67 */     if (i instanceof Wrappers.JIterableWrapper) {
/*  67 */       Wrappers.JIterableWrapper jIterableWrapper = (Wrappers.JIterableWrapper)i;
/*  68 */       iterable = jIterableWrapper.underlying();
/*     */     } else {
/*  69 */       iterable = new Wrappers.IterableWrapper(Wrappers$.MODULE$, i);
/*     */     } 
/*     */     return iterable;
/*     */   }
/*     */   
/*     */   public static Collection asJavaCollection(WrapAsJava $this, Iterable<?> it) {
/*     */     Collection collection;
/*  83 */     if (it instanceof Wrappers.JCollectionWrapper) {
/*  83 */       Wrappers.JCollectionWrapper jCollectionWrapper = (Wrappers.JCollectionWrapper)it;
/*  84 */       collection = jCollectionWrapper.underlying();
/*     */     } else {
/*  85 */       collection = new Wrappers.IterableWrapper(Wrappers$.MODULE$, it);
/*     */     } 
/*     */     return collection;
/*     */   }
/*     */   
/*     */   public static List bufferAsJavaList(WrapAsJava $this, Buffer<?> b) {
/*     */     List list;
/* 101 */     if (b instanceof Wrappers.JListWrapper) {
/* 101 */       Wrappers.JListWrapper jListWrapper = (Wrappers.JListWrapper)b;
/* 102 */       list = jListWrapper.underlying();
/*     */     } else {
/* 103 */       list = new Wrappers.MutableBufferWrapper(Wrappers$.MODULE$, b);
/*     */     } 
/*     */     return list;
/*     */   }
/*     */   
/*     */   public static List mutableSeqAsJavaList(WrapAsJava $this, Seq<?> seq) {
/*     */     List list;
/* 119 */     if (seq instanceof Wrappers.JListWrapper) {
/* 119 */       Wrappers.JListWrapper jListWrapper = (Wrappers.JListWrapper)seq;
/* 120 */       list = jListWrapper.underlying();
/*     */     } else {
/* 121 */       list = new Wrappers.MutableSeqWrapper(Wrappers$.MODULE$, seq);
/*     */     } 
/*     */     return list;
/*     */   }
/*     */   
/*     */   public static List seqAsJavaList(WrapAsJava $this, Seq<?> seq) {
/*     */     List list;
/* 137 */     if (seq instanceof Wrappers.JListWrapper) {
/* 137 */       Wrappers.JListWrapper jListWrapper = (Wrappers.JListWrapper)seq;
/* 138 */       list = jListWrapper.underlying();
/*     */     } else {
/* 139 */       list = new Wrappers.SeqWrapper(Wrappers$.MODULE$, seq);
/*     */     } 
/*     */     return list;
/*     */   }
/*     */   
/*     */   public static Set mutableSetAsJavaSet(WrapAsJava $this, Set<?> s) {
/*     */     Set set;
/* 155 */     if (s instanceof Wrappers.JSetWrapper) {
/* 155 */       Wrappers.JSetWrapper jSetWrapper = (Wrappers.JSetWrapper)s;
/* 156 */       set = jSetWrapper.underlying();
/*     */     } else {
/* 157 */       set = new Wrappers.MutableSetWrapper(Wrappers$.MODULE$, s);
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   public static Set setAsJavaSet(WrapAsJava $this, Set<?> s) {
/*     */     Set set;
/* 173 */     if (s instanceof Wrappers.JSetWrapper) {
/* 173 */       Wrappers.JSetWrapper jSetWrapper = (Wrappers.JSetWrapper)s;
/* 174 */       set = jSetWrapper.underlying();
/*     */     } else {
/* 175 */       set = new Wrappers.SetWrapper(Wrappers$.MODULE$, s);
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   public static Map mutableMapAsJavaMap(WrapAsJava $this, Map<?, ?> m) {
/*     */     Map map;
/* 191 */     if (m instanceof Wrappers.JMapWrapper) {
/* 191 */       Wrappers.JMapWrapper jMapWrapper = (Wrappers.JMapWrapper)m;
/* 193 */       map = jMapWrapper.underlying();
/*     */     } else {
/* 194 */       map = new Wrappers.MutableMapWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return map;
/*     */   }
/*     */   
/*     */   public static Dictionary asJavaDictionary(WrapAsJava $this, Map<?, ?> m) {
/*     */     Dictionary dictionary;
/* 211 */     if (m instanceof Wrappers.JDictionaryWrapper) {
/* 211 */       Wrappers.JDictionaryWrapper jDictionaryWrapper = (Wrappers.JDictionaryWrapper)m;
/* 213 */       dictionary = jDictionaryWrapper.underlying();
/*     */     } else {
/* 214 */       dictionary = new Wrappers.DictionaryWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return dictionary;
/*     */   }
/*     */   
/*     */   public static Map mapAsJavaMap(WrapAsJava $this, Map<?, ?> m) {
/*     */     Map map;
/* 231 */     if (m instanceof Wrappers.JMapWrapper) {
/* 231 */       Wrappers.JMapWrapper jMapWrapper = (Wrappers.JMapWrapper)m;
/* 233 */       map = jMapWrapper.underlying();
/*     */     } else {
/* 234 */       map = new Wrappers.MapWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return map;
/*     */   }
/*     */   
/*     */   public static ConcurrentMap asJavaConcurrentMap(WrapAsJava $this, ConcurrentMap<?, ?> m) {
/*     */     ConcurrentMap concurrentMap;
/* 253 */     if (m instanceof Wrappers.JConcurrentMapDeprecatedWrapper) {
/* 253 */       Wrappers.JConcurrentMapDeprecatedWrapper jConcurrentMapDeprecatedWrapper = (Wrappers.JConcurrentMapDeprecatedWrapper)m;
/* 254 */       concurrentMap = jConcurrentMapDeprecatedWrapper.underlying();
/*     */     } else {
/* 255 */       concurrentMap = new Wrappers.ConcurrentMapDeprecatedWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return concurrentMap;
/*     */   }
/*     */   
/*     */   public static ConcurrentMap mapAsJavaConcurrentMap(WrapAsJava $this, Map<?, ?> m) {
/*     */     ConcurrentMap concurrentMap;
/* 273 */     if (m instanceof Wrappers.JConcurrentMapWrapper) {
/* 273 */       Wrappers.JConcurrentMapWrapper jConcurrentMapWrapper = (Wrappers.JConcurrentMapWrapper)m;
/* 274 */       concurrentMap = jConcurrentMapWrapper.underlying();
/*     */     } else {
/* 275 */       concurrentMap = new Wrappers.ConcurrentMapWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return concurrentMap;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\WrapAsJava$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */