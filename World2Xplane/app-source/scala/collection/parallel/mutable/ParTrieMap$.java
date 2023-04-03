/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParMapFactory;
/*     */ import scala.collection.parallel.Combiner;
/*     */ 
/*     */ public final class ParTrieMap$ extends ParMapFactory<ParTrieMap> implements Serializable {
/*     */   public static final ParTrieMap$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 179 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParTrieMap$() {
/* 179 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <K, V> ParTrieMap<K, V> empty() {
/* 181 */     return new ParTrieMap<K, V>();
/*     */   }
/*     */   
/*     */   public <K, V> Combiner<Tuple2<K, V>, ParTrieMap<K, V>> newCombiner() {
/* 183 */     return new ParTrieMap<K, V>();
/*     */   }
/*     */   
/*     */   public <K, V> CanCombineFrom<ParTrieMap<?, ?>, Tuple2<K, V>, ParTrieMap<K, V>> canBuildFrom() {
/* 185 */     return (CanCombineFrom<ParTrieMap<?, ?>, Tuple2<K, V>, ParTrieMap<K, V>>)new ParMapFactory.CanCombineFromMap(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParTrieMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */