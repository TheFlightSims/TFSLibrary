/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class SeqView$ {
/*    */   public static final SeqView$ MODULE$;
/*    */   
/*    */   private SeqView$() {
/* 24 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> canBuildFrom() {
/* 27 */     return new SeqView$$anon$1();
/*    */   }
/*    */   
/*    */   public static class SeqView$$anon$1 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> {
/*    */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 28 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */     
/*    */     public TraversableView.NoBuilder<A> apply() {
/* 29 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqView$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */