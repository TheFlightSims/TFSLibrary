/*     */ package scala.collection.convert;
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
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.concurrent.Map;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Set;
/*     */ 
/*     */ public abstract class WrapAsScala$class {
/*     */   public static void $init$(WrapAsScala $this) {}
/*     */   
/*     */   public static Iterator asScalaIterator(WrapAsScala $this, Iterator<?> it) {
/*     */     Iterator iterator;
/*  54 */     if (it instanceof Wrappers.IteratorWrapper) {
/*  54 */       Wrappers.IteratorWrapper iteratorWrapper = (Wrappers.IteratorWrapper)it;
/*  55 */       iterator = iteratorWrapper.underlying();
/*     */     } else {
/*  56 */       iterator = new Wrappers.JIteratorWrapper(Wrappers$.MODULE$, it);
/*     */     } 
/*     */     return iterator;
/*     */   }
/*     */   
/*     */   public static Iterator enumerationAsScalaIterator(WrapAsScala $this, Enumeration<?> i) {
/*     */     Iterator iterator;
/*  72 */     if (i instanceof Wrappers.IteratorWrapper) {
/*  72 */       Wrappers.IteratorWrapper iteratorWrapper = (Wrappers.IteratorWrapper)i;
/*  73 */       iterator = iteratorWrapper.underlying();
/*     */     } else {
/*  74 */       iterator = new Wrappers.JEnumerationWrapper(Wrappers$.MODULE$, i);
/*     */     } 
/*     */     return iterator;
/*     */   }
/*     */   
/*     */   public static Iterable iterableAsScalaIterable(WrapAsScala $this, Iterable<?> i) {
/*     */     Iterable iterable;
/*  91 */     if (i instanceof Wrappers.IterableWrapper) {
/*  91 */       Wrappers.IterableWrapper iterableWrapper = (Wrappers.IterableWrapper)i;
/*  92 */       iterable = iterableWrapper.underlying();
/*     */     } else {
/*  93 */       iterable = new Wrappers.JIterableWrapper(Wrappers$.MODULE$, i);
/*     */     } 
/*     */     return iterable;
/*     */   }
/*     */   
/*     */   public static Iterable collectionAsScalaIterable(WrapAsScala $this, Collection<?> i) {
/*     */     Iterable iterable;
/* 106 */     if (i instanceof Wrappers.IterableWrapper) {
/* 106 */       Wrappers.IterableWrapper iterableWrapper = (Wrappers.IterableWrapper)i;
/* 107 */       iterable = iterableWrapper.underlying();
/*     */     } else {
/* 108 */       iterable = new Wrappers.JCollectionWrapper(Wrappers$.MODULE$, i);
/*     */     } 
/*     */     return iterable;
/*     */   }
/*     */   
/*     */   public static Buffer asScalaBuffer(WrapAsScala $this, List<?> l) {
/*     */     Buffer buffer;
/* 125 */     if (l instanceof Wrappers.MutableBufferWrapper) {
/* 125 */       Wrappers.MutableBufferWrapper mutableBufferWrapper = (Wrappers.MutableBufferWrapper)l;
/* 126 */       buffer = mutableBufferWrapper.underlying();
/*     */     } else {
/* 127 */       buffer = new Wrappers.JListWrapper(Wrappers$.MODULE$, l);
/*     */     } 
/*     */     return buffer;
/*     */   }
/*     */   
/*     */   public static Set asScalaSet(WrapAsScala $this, Set<?> s) {
/*     */     Set set;
/* 143 */     if (s instanceof Wrappers.MutableSetWrapper) {
/* 143 */       Wrappers.MutableSetWrapper mutableSetWrapper = (Wrappers.MutableSetWrapper)s;
/* 144 */       set = mutableSetWrapper.underlying();
/*     */     } else {
/* 145 */       set = new Wrappers.JSetWrapper(Wrappers$.MODULE$, s);
/*     */     } 
/*     */     return set;
/*     */   }
/*     */   
/*     */   public static Map mapAsScalaMap(WrapAsScala $this, Map<?, ?> m) {
/*     */     Map map;
/* 162 */     if (m instanceof Wrappers.MutableMapWrapper) {
/* 162 */       Wrappers.MutableMapWrapper mutableMapWrapper = (Wrappers.MutableMapWrapper)m;
/* 164 */       map = mutableMapWrapper.underlying();
/*     */     } else {
/* 165 */       map = new Wrappers.JMapWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return map;
/*     */   }
/*     */   
/*     */   public static ConcurrentMap asScalaConcurrentMap(WrapAsScala $this, ConcurrentMap<?, ?> m) {
/*     */     ConcurrentMap concurrentMap;
/* 182 */     if (m instanceof Wrappers.ConcurrentMapDeprecatedWrapper) {
/* 182 */       Wrappers.ConcurrentMapDeprecatedWrapper concurrentMapDeprecatedWrapper = (Wrappers.ConcurrentMapDeprecatedWrapper)m;
/* 182 */       concurrentMap = concurrentMapDeprecatedWrapper.underlying();
/*     */     } else {
/* 184 */       concurrentMap = new Wrappers.JConcurrentMapDeprecatedWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return concurrentMap;
/*     */   }
/*     */   
/*     */   public static Map mapAsScalaConcurrentMap(WrapAsScala $this, ConcurrentMap<?, ?> m) {
/*     */     Map map;
/* 200 */     if (m instanceof Wrappers.ConcurrentMapWrapper) {
/* 200 */       Wrappers.ConcurrentMapWrapper concurrentMapWrapper = (Wrappers.ConcurrentMapWrapper)m;
/* 200 */       map = concurrentMapWrapper.underlying();
/*     */     } else {
/* 202 */       map = new Wrappers.JConcurrentMapWrapper<Object, Object>(Wrappers$.MODULE$, m);
/*     */     } 
/*     */     return map;
/*     */   }
/*     */   
/*     */   public static Map dictionaryAsScalaMap(WrapAsScala $this, Dictionary<?, ?> p) {
/*     */     Map map;
/* 216 */     if (p instanceof Wrappers.DictionaryWrapper) {
/* 216 */       Wrappers.DictionaryWrapper dictionaryWrapper = (Wrappers.DictionaryWrapper)p;
/* 217 */       map = dictionaryWrapper.underlying();
/*     */     } else {
/* 218 */       map = new Wrappers.JDictionaryWrapper<Object, Object>(Wrappers$.MODULE$, p);
/*     */     } 
/*     */     return map;
/*     */   }
/*     */   
/*     */   public static Map propertiesAsScalaMap(WrapAsScala $this, Properties p) {
/* 231 */     return new Wrappers.JPropertiesWrapper(Wrappers$.MODULE$, p);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\WrapAsScala$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */