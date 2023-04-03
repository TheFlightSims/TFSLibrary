/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class IterableView$ {
/*    */   public static final IterableView$ MODULE$;
/*    */   
/*    */   private IterableView$() {
/* 24 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, IterableView<A, Iterable<?>>> canBuildFrom() {
/* 27 */     return new IterableView$$anon$1();
/*    */   }
/*    */   
/*    */   public static class IterableView$$anon$1 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, IterableView<A, Iterable<?>>> {
/*    */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 28 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */     
/*    */     public TraversableView.NoBuilder<A> apply() {
/* 29 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableView$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */