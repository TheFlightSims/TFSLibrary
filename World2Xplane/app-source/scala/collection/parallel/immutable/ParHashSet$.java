/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParSetFactory;
/*     */ import scala.collection.immutable.HashSet;
/*     */ import scala.collection.parallel.Combiner;
/*     */ 
/*     */ public final class ParHashSet$ extends ParSetFactory<ParHashSet> implements Serializable {
/*     */   public static final ParHashSet$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 124 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParHashSet$() {
/* 124 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParHashSet<T>> newCombiner() {
/* 125 */     return (Combiner)HashSetCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
/* 128 */     return (CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>>)new ParSetFactory.GenericCanCombineFrom(this);
/*     */   }
/*     */   
/*     */   public <T> ParHashSet<T> fromTrie(HashSet<T> t) {
/* 130 */     return new ParHashSet<T>(t);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParHashSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */