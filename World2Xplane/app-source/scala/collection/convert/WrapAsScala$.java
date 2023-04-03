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
/*     */ public final class WrapAsScala$ implements WrapAsScala {
/*     */   public static final WrapAsScala$ MODULE$;
/*     */   
/*     */   public <A> Iterator<A> asScalaIterator(Iterator it) {
/* 236 */     return WrapAsScala$class.asScalaIterator(this, it);
/*     */   }
/*     */   
/*     */   public <A> Iterator<A> enumerationAsScalaIterator(Enumeration i) {
/* 236 */     return WrapAsScala$class.enumerationAsScalaIterator(this, i);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> iterableAsScalaIterable(Iterable i) {
/* 236 */     return WrapAsScala$class.iterableAsScalaIterable(this, i);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> collectionAsScalaIterable(Collection i) {
/* 236 */     return WrapAsScala$class.collectionAsScalaIterable(this, i);
/*     */   }
/*     */   
/*     */   public <A> Buffer<A> asScalaBuffer(List l) {
/* 236 */     return WrapAsScala$class.asScalaBuffer(this, l);
/*     */   }
/*     */   
/*     */   public <A> Set<A> asScalaSet(Set s) {
/* 236 */     return WrapAsScala$class.asScalaSet(this, s);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mapAsScalaMap(Map m) {
/* 236 */     return WrapAsScala$class.mapAsScalaMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap m) {
/* 236 */     return WrapAsScala$class.asScalaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap m) {
/* 236 */     return WrapAsScala$class.mapAsScalaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary p) {
/* 236 */     return WrapAsScala$class.dictionaryAsScalaMap(this, p);
/*     */   }
/*     */   
/*     */   public Map<String, String> propertiesAsScalaMap(Properties p) {
/* 236 */     return WrapAsScala$class.propertiesAsScalaMap(this, p);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap m) {
/* 236 */     return LowPriorityWrapAsScala$class.mapAsScalaDeprecatedConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   private WrapAsScala$() {
/* 236 */     MODULE$ = this;
/* 236 */     LowPriorityWrapAsScala$class.$init$(this);
/* 236 */     WrapAsScala$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\WrapAsScala$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */