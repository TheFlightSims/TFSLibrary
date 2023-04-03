/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParMapFactory;
/*     */ import scala.collection.immutable.HashMap;
/*     */ import scala.collection.parallel.Combiner;
/*     */ 
/*     */ public final class ParHashMap$ extends ParMapFactory<ParHashMap> implements Serializable {
/*     */   public static final ParHashMap$ MODULE$;
/*     */   
/*     */   private AtomicInteger totalcombines;
/*     */   
/*     */   private Object readResolve() {
/* 146 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParHashMap$() {
/* 146 */     MODULE$ = this;
/* 157 */     this.totalcombines = new AtomicInteger(0);
/*     */   }
/*     */   
/*     */   public <K, V> ParHashMap<K, V> empty() {
/*     */     return new ParHashMap<K, V>();
/*     */   }
/*     */   
/*     */   public <K, V> Combiner<Tuple2<K, V>, ParHashMap<K, V>> newCombiner() {
/*     */     return (Combiner)HashMapCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
/*     */     return (CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>>)new ParMapFactory.CanCombineFromMap(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParHashMap<K, V> fromTrie(HashMap<K, V> t) {
/*     */     return new ParHashMap<K, V>(t);
/*     */   }
/*     */   
/*     */   public AtomicInteger totalcombines() {
/* 157 */     return this.totalcombines;
/*     */   }
/*     */   
/*     */   public void totalcombines_$eq(AtomicInteger x$1) {
/* 157 */     this.totalcombines = x$1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParHashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */