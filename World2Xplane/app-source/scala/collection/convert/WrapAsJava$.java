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
/*     */ public final class WrapAsJava$ implements WrapAsJava {
/*     */   public static final WrapAsJava$ MODULE$;
/*     */   
/*     */   public <A> Iterator<A> asJavaIterator(Iterator it) {
/* 279 */     return WrapAsJava$class.asJavaIterator(this, it);
/*     */   }
/*     */   
/*     */   public <A> Enumeration<A> asJavaEnumeration(Iterator it) {
/* 279 */     return WrapAsJava$class.asJavaEnumeration(this, it);
/*     */   }
/*     */   
/*     */   public <A> Iterable<A> asJavaIterable(Iterable i) {
/* 279 */     return WrapAsJava$class.asJavaIterable(this, i);
/*     */   }
/*     */   
/*     */   public <A> Collection<A> asJavaCollection(Iterable it) {
/* 279 */     return WrapAsJava$class.asJavaCollection(this, it);
/*     */   }
/*     */   
/*     */   public <A> List<A> bufferAsJavaList(Buffer b) {
/* 279 */     return WrapAsJava$class.bufferAsJavaList(this, b);
/*     */   }
/*     */   
/*     */   public <A> List<A> mutableSeqAsJavaList(Seq seq) {
/* 279 */     return WrapAsJava$class.mutableSeqAsJavaList(this, seq);
/*     */   }
/*     */   
/*     */   public <A> List<A> seqAsJavaList(Seq seq) {
/* 279 */     return WrapAsJava$class.seqAsJavaList(this, seq);
/*     */   }
/*     */   
/*     */   public <A> Set<A> mutableSetAsJavaSet(Set s) {
/* 279 */     return WrapAsJava$class.mutableSetAsJavaSet(this, s);
/*     */   }
/*     */   
/*     */   public <A> Set<A> setAsJavaSet(Set s) {
/* 279 */     return WrapAsJava$class.setAsJavaSet(this, s);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mutableMapAsJavaMap(Map m) {
/* 279 */     return WrapAsJava$class.mutableMapAsJavaMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Dictionary<A, B> asJavaDictionary(Map m) {
/* 279 */     return WrapAsJava$class.asJavaDictionary(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> Map<A, B> mapAsJavaMap(Map m) {
/* 279 */     return WrapAsJava$class.mapAsJavaMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap m) {
/* 279 */     return WrapAsJava$class.asJavaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   public <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map m) {
/* 279 */     return WrapAsJava$class.mapAsJavaConcurrentMap(this, m);
/*     */   }
/*     */   
/*     */   private WrapAsJava$() {
/* 279 */     MODULE$ = this;
/* 279 */     WrapAsJava$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\WrapAsJava$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */