/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class ParIterableView$ {
/*    */   public static final ParIterableView$ MODULE$;
/*    */   
/*    */   private ParIterableView$() {
/* 27 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParIterableView<?, ? extends ParIterable<?>, ?>, T, ParIterableView<T, ParIterable<T>, Iterable<T>>> canBuildFrom() {
/* 42 */     return new ParIterableView$$anon$3();
/*    */   }
/*    */   
/*    */   public static class ParIterableView$$anon$3 implements CanCombineFrom<ParIterableView<?, ? extends ParIterable<?>, ?>, T, ParIterableView<T, ParIterable<T>, Iterable<T>>> {
/*    */     public ParIterableView.NoCombiner<T> apply(ParIterableView from) {
/* 43 */       return new ParIterableView$$anon$3$$anon$1(this);
/*    */     }
/*    */     
/*    */     public class ParIterableView$$anon$3$$anon$1 extends ParIterableView.NoCombiner<T> {
/*    */       public ParIterableView$$anon$3$$anon$1(ParIterableView$$anon$3 $outer) {}
/*    */     }
/*    */     
/*    */     public ParIterableView.NoCombiner<T> apply() {
/* 44 */       return new ParIterableView$$anon$3$$anon$2(this);
/*    */     }
/*    */     
/*    */     public class ParIterableView$$anon$3$$anon$2 extends ParIterableView.NoCombiner<T> {
/*    */       public ParIterableView$$anon$3$$anon$2(ParIterableView$$anon$3 $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParIterableView$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */