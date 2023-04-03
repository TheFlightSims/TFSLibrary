/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.SortedSet;
/*    */ import scala.collection.generic.ImmutableSortedSetFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.SetBuilder;
/*    */ import scala.math.Ordering;
/*    */ 
/*    */ public final class TreeSet$ extends ImmutableSortedSetFactory<TreeSet> implements Serializable {
/*    */   public static final TreeSet$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 22 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private TreeSet$() {
/* 22 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> Builder<A, TreeSet<A>> implicitBuilder(Ordering<A> ordering) {
/* 23 */     return newBuilder(ordering);
/*    */   }
/*    */   
/*    */   public <A> Builder<A, TreeSet<A>> newBuilder(Ordering<?> ordering) {
/* 25 */     return (Builder<A, TreeSet<A>>)new SetBuilder(empty(ordering));
/*    */   }
/*    */   
/*    */   public <A> TreeSet<A> empty(Ordering<A> ordering) {
/* 29 */     return new TreeSet<A>(ordering);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\TreeSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */