/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.SliceInterval;
/*    */ import scala.collection.generic.SliceInterval$;
/*    */ import scala.runtime.RichInt$;
/*    */ 
/*    */ public abstract class IndexedSeqView$class {
/*    */   public static void $init$(IndexedSeqView $this) {}
/*    */   
/*    */   public static IndexedSeqView.Transformed newFiltered(IndexedSeqView $this, Function1 p) {
/* 79 */     return new IndexedSeqView$$anon$1($this, (IndexedSeqView<A, Coll>)p);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView.Transformed newSliced(IndexedSeqView $this, SliceInterval _endpoints) {
/* 80 */     return new IndexedSeqView$$anon$2($this, (IndexedSeqView<A, Coll>)_endpoints);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView.Transformed newDroppedWhile(IndexedSeqView $this, Function1 p) {
/* 81 */     return new IndexedSeqView$$anon$3($this, (IndexedSeqView<A, Coll>)p);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView.Transformed newTakenWhile(IndexedSeqView $this, Function1 p) {
/* 82 */     return new IndexedSeqView$$anon$4($this, (IndexedSeqView<A, Coll>)p);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView.Transformed newReversed(IndexedSeqView<A, Coll> $this) {
/* 83 */     return new IndexedSeqView$$anon$5($this);
/*    */   }
/*    */   
/*    */   private static IndexedSeqView asThis(IndexedSeqView $this, IndexedSeqView.Transformed xs) {
/* 85 */     return xs;
/*    */   }
/*    */   
/*    */   public static IndexedSeqView filter(IndexedSeqView $this, Function1 p) {
/* 87 */     return $this.newFiltered(p);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView init(IndexedSeqView $this) {
/* 88 */     return $this.newSliced(SliceInterval$.MODULE$.apply(0, $this.length() - 1));
/*    */   }
/*    */   
/*    */   public static IndexedSeqView drop(IndexedSeqView $this, int n) {
/* 89 */     return $this.newSliced(SliceInterval$.MODULE$.apply(n, $this.length()));
/*    */   }
/*    */   
/*    */   public static IndexedSeqView take(IndexedSeqView $this, int n) {
/* 90 */     Predef$ predef$ = Predef$.MODULE$;
/* 90 */     return $this.newSliced(SliceInterval$.MODULE$.apply(0, RichInt$.MODULE$.min$extension(n, $this.length())));
/*    */   }
/*    */   
/*    */   public static IndexedSeqView slice(IndexedSeqView $this, int from, int until) {
/* 91 */     Predef$ predef$ = Predef$.MODULE$;
/* 91 */     return $this.newSliced(SliceInterval$.MODULE$.apply(from, RichInt$.MODULE$.min$extension(until, $this.length())));
/*    */   }
/*    */   
/*    */   public static IndexedSeqView dropWhile(IndexedSeqView $this, Function1 p) {
/* 92 */     return $this.newDroppedWhile(p);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView takeWhile(IndexedSeqView $this, Function1 p) {
/* 93 */     return $this.newTakenWhile(p);
/*    */   }
/*    */   
/*    */   public static Tuple2 span(IndexedSeqView $this, Function1 p) {
/* 94 */     return new Tuple2($this.newTakenWhile(p), $this.newDroppedWhile(p));
/*    */   }
/*    */   
/*    */   public static Tuple2 splitAt(IndexedSeqView $this, int n) {
/* 95 */     return new Tuple2($this.take(n), $this.drop(n));
/*    */   }
/*    */   
/*    */   public static IndexedSeqView reverse(IndexedSeqView $this) {
/* 96 */     return $this.newReversed();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeqView$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */