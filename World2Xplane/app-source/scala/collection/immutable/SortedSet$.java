/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.SortedSet;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.ImmutableSortedSetFactory;
/*    */ import scala.math.Ordering;
/*    */ 
/*    */ public final class SortedSet$ extends ImmutableSortedSetFactory<SortedSet> {
/*    */   public static final SortedSet$ MODULE$;
/*    */   
/*    */   private SortedSet$() {
/* 36 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<SortedSet<?>, A, SortedSet<A>> canBuildFrom(Ordering<A> ord) {
/* 38 */     return newCanBuildFrom(ord);
/*    */   }
/*    */   
/*    */   public <A> SortedSet<A> empty(Ordering<A> ord) {
/* 39 */     return TreeSet$.MODULE$.empty(ord);
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<SortedSet<?>, A, SortedSet<A>> newCanBuildFrom(Ordering ord) {
/* 41 */     return super.newCanBuildFrom(ord);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\SortedSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */