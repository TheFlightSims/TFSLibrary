/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class TraversableView$ {
/*    */   public static final TraversableView$ MODULE$;
/*    */   
/*    */   private TraversableView$() {
/* 23 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, TraversableView<A, Traversable<?>>> canBuildFrom() {
/* 32 */     return new TraversableView$$anon$1();
/*    */   }
/*    */   
/*    */   public static class TraversableView$$anon$1 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, TraversableView<A, Traversable<?>>> {
/*    */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 33 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */     
/*    */     public TraversableView.NoBuilder<A> apply() {
/* 34 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableView$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */