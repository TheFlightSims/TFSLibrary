/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.SortedMap;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.ImmutableSortedMapFactory;
/*     */ import scala.collection.generic.SortedMapFactory;
/*     */ import scala.math.Ordering;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class TreeMap$ extends ImmutableSortedMapFactory<TreeMap> implements Serializable {
/*     */   public static final TreeMap$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  22 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private TreeMap$() {
/*  22 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> TreeMap<A, B> empty(Ordering<A> ord) {
/*  23 */     return new TreeMap<A, B>(ord);
/*     */   }
/*     */   
/*     */   public <A, B> CanBuildFrom<TreeMap<?, ?>, Tuple2<A, B>, TreeMap<A, B>> canBuildFrom(Ordering ord) {
/*  25 */     return (CanBuildFrom<TreeMap<?, ?>, Tuple2<A, B>, TreeMap<A, B>>)new SortedMapFactory.SortedMapCanBuildFrom((SortedMapFactory)this, ord);
/*     */   }
/*     */   
/*     */   public class TreeMap$$anonfun$$plus$plus$1 extends AbstractFunction2<TreeMap<A, B1>, Tuple2<A, B1>, TreeMap<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final TreeMap<A, B1> apply(TreeMap x$2, Tuple2 x$3) {
/* 162 */       return x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public TreeMap$$anonfun$$plus$plus$1(TreeMap $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\TreeMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */