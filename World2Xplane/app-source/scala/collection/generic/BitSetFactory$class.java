/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function2;
/*    */ import scala.collection.BitSet;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public abstract class BitSetFactory$class {
/*    */   public static void $init$(BitSetFactory $this) {}
/*    */   
/*    */   public static BitSet apply(BitSetFactory<Object> $this, Seq elems) {
/* 32 */     Object object = $this.empty();
/* 32 */     return (BitSet)elems.$div$colon(object, (Function2)new BitSetFactory$$anonfun$apply$1((BitSetFactory)$this));
/*    */   }
/*    */   
/*    */   public static CanBuildFrom bitsetCanBuildFrom(BitSetFactory<Coll> $this) {
/* 33 */     return new BitSetFactory$$anon$1($this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\BitSetFactory$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */