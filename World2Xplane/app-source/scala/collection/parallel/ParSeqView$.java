/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class ParSeqView$ {
/*    */   public static final ParSeqView$ MODULE$;
/*    */   
/*    */   private ParSeqView$() {
/* 27 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSeqView<?, ? extends ParSeq<?>, ?>, T, ParSeqView<T, ParSeq<T>, Seq<T>>> canBuildFrom() {
/* 42 */     return new ParSeqView$$anon$3();
/*    */   }
/*    */   
/*    */   public static class ParSeqView$$anon$3 implements CanCombineFrom<ParSeqView<?, ? extends ParSeq<?>, ?>, T, ParSeqView<T, ParSeq<T>, Seq<T>>> {
/*    */     public ParSeqView.NoCombiner<T> apply(ParSeqView from) {
/* 43 */       return new ParSeqView$$anon$3$$anon$1(this);
/*    */     }
/*    */     
/*    */     public class ParSeqView$$anon$3$$anon$1 extends ParSeqView.NoCombiner<T> {
/*    */       public ParSeqView$$anon$3$$anon$1(ParSeqView$$anon$3 $outer) {}
/*    */     }
/*    */     
/*    */     public ParSeqView.NoCombiner<T> apply() {
/* 44 */       return new ParSeqView$$anon$3$$anon$2(this);
/*    */     }
/*    */     
/*    */     public class ParSeqView$$anon$3$$anon$2 extends ParSeqView.NoCombiner<T> {
/*    */       public ParSeqView$$anon$3$$anon$2(ParSeqView$$anon$3 $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeqView$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */