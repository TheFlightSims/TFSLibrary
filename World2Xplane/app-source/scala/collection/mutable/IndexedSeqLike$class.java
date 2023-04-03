/*    */ package scala.collection.mutable;
/*    */ 
/*    */ public abstract class IndexedSeqLike$class {
/*    */   public static void $init$(IndexedSeqLike $this) {}
/*    */   
/*    */   public static IndexedSeq thisCollection(IndexedSeqLike $this) {
/* 41 */     return (IndexedSeq)$this;
/*    */   }
/*    */   
/*    */   public static IndexedSeq toCollection(IndexedSeqLike $this, Object repr) {
/* 42 */     return (IndexedSeq)repr;
/*    */   }
/*    */   
/*    */   public static IndexedSeqView view(IndexedSeqLike<A, Repr> $this) {
/* 54 */     return new IndexedSeqLike$$anon$1($this);
/*    */   }
/*    */   
/*    */   public static IndexedSeqView view(IndexedSeqLike $this, int from, int until) {
/* 72 */     return $this.view().slice(from, until);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */