/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SortedSetFactory;
/*    */ import scala.collection.immutable.SortedSet;
/*    */ import scala.collection.immutable.SortedSet$;
/*    */ import scala.math.Ordering;
/*    */ 
/*    */ public final class SortedSet$ extends SortedSetFactory<SortedSet> {
/*    */   public static final SortedSet$ MODULE$;
/*    */   
/*    */   private SortedSet$() {
/* 28 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> SortedSet<A> empty(Ordering ord) {
/* 29 */     return SortedSet$.MODULE$.empty(ord);
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<SortedSet<?>, A, SortedSet<A>> canBuildFrom(Ordering<A> ord) {
/* 30 */     return newCanBuildFrom(ord);
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<SortedSet<?>, A, SortedSet<A>> newCanBuildFrom(Ordering ord) {
/* 32 */     return super.newCanBuildFrom(ord);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */