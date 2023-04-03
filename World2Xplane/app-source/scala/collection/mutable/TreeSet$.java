/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.generic.MutableSortedSetFactory;
/*     */ import scala.math.Ordering;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class TreeSet$ extends MutableSortedSetFactory<TreeSet> implements Serializable {
/*     */   public static final TreeSet$ MODULE$;
/*     */   
/*     */   private TreeSet$() {
/*  23 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  23 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public <A> TreeSet<A> empty(Ordering<A> ordering) {
/*  27 */     return new TreeSet<A>(ordering);
/*     */   }
/*     */   
/*     */   public class TreeSet$$anonfun$iterator$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Object e) {
/* 120 */       return !this.$outer.scala$collection$mutable$TreeSet$$isLeftAcceptable(this.$outer.scala$collection$mutable$TreeSet$$from(), this.$outer.ordering(), e);
/*     */     }
/*     */     
/*     */     public TreeSet$$anonfun$iterator$1(TreeSet $outer) {}
/*     */   }
/*     */   
/*     */   public class TreeSet$$anonfun$iterator$2 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Object e) {
/* 121 */       return this.$outer.scala$collection$mutable$TreeSet$$isRightAcceptable(this.$outer.scala$collection$mutable$TreeSet$$until(), this.$outer.ordering(), e);
/*     */     }
/*     */     
/*     */     public TreeSet$$anonfun$iterator$2(TreeSet $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\TreeSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */