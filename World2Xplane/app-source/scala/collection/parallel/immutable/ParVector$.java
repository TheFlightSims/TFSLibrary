/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParFactory;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ 
/*     */ public final class ParVector$ extends ParFactory<ParVector> implements Serializable {
/*     */   public static final ParVector$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  94 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParVector$() {
/*  94 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> CanCombineFrom<ParVector<?>, T, ParVector<T>> canBuildFrom() {
/*  96 */     return (CanCombineFrom<ParVector<?>, T, ParVector<T>>)new ParFactory.GenericCanCombineFrom(this);
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParVector<T>> newBuilder() {
/*  98 */     return newCombiner();
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParVector<T>> newCombiner() {
/* 100 */     return new LazyParVectorCombiner<T>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParVector$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */