/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.util.hashing.MurmurHash3$;
/*    */ 
/*    */ public abstract class IndexedSeqLike$class {
/*    */   public static void $init$(IndexedSeqLike $this) {}
/*    */   
/*    */   public static int hashCode(IndexedSeqLike $this) {
/* 44 */     return MurmurHash3$.MODULE$.seqHash($this.seq());
/*    */   }
/*    */   
/*    */   public static IndexedSeq thisCollection(IndexedSeqLike $this) {
/* 46 */     return (IndexedSeq)$this;
/*    */   }
/*    */   
/*    */   public static IndexedSeq toCollection(IndexedSeqLike $this, Object repr) {
/* 47 */     return (IndexedSeq)repr;
/*    */   }
/*    */   
/*    */   public static Iterator iterator(IndexedSeqLike<A, Repr> $this) {
/* 91 */     return new IndexedSeqLike.Elements($this, 0, $this.length());
/*    */   }
/*    */   
/*    */   public static Buffer toBuffer(IndexedSeqLike $this) {
/* 95 */     ArrayBuffer result = new ArrayBuffer($this.size());
/* 96 */     $this.copyToBuffer((Buffer)result);
/* 97 */     return (Buffer)result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IndexedSeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */