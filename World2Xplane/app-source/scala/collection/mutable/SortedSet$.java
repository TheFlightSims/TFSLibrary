/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.SortedSet;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.MutableSortedSetFactory;
/*    */ import scala.collection.generic.SortedSetFactory;
/*    */ import scala.math.Ordering;
/*    */ 
/*    */ public final class SortedSet$ extends MutableSortedSetFactory<SortedSet> {
/*    */   public static final SortedSet$ MODULE$;
/*    */   
/*    */   private SortedSet$() {
/* 44 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<SortedSet<?>, A, SortedSet<A>> canBuildFrom(Ordering ord) {
/* 45 */     return (CanBuildFrom<SortedSet<?>, A, SortedSet<A>>)new SortedSetFactory.SortedSetCanBuildFrom((SortedSetFactory)this, ord);
/*    */   }
/*    */   
/*    */   public <A> SortedSet<A> empty(Ordering<A> ord) {
/* 47 */     return TreeSet$.MODULE$.empty(ord);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SortedSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */