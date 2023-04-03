/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.ArraySeq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class ResizableParArrayCombiner$class {
/*    */   public static void $init$(ResizableParArrayCombiner $this) {}
/*    */   
/*    */   public static void sizeHint(ResizableParArrayCombiner<T> $this, int sz) {
/* 26 */     if ($this.chain().length() == 1)
/* 26 */       ((ExposedArrayBuffer)$this.chain().apply(0)).sizeHint(sz); 
/*    */   }
/*    */   
/*    */   public static ResizableParArrayCombiner newLazyCombiner(ResizableParArrayCombiner $this, ArrayBuffer<ExposedArrayBuffer<?>> c) {
/* 29 */     return ResizableParArrayCombiner$.MODULE$.apply(c);
/*    */   }
/*    */   
/*    */   public static ParArray allocateAndCopy(ResizableParArrayCombiner<T> $this) {
/* 32 */     ArraySeq<?> arrayseq = new ArraySeq($this.size());
/* 33 */     Object[] array = arrayseq.array();
/* 35 */     $this.combinerTaskSupport().executeAndWaitResult(new ResizableParArrayCombiner.CopyChainToArray($this, array, 0, $this.size()));
/* 37 */     return ($this.chain().size() > 1) ? new ParArray(arrayseq) : 
/*    */       
/* 39 */       new ParArray(new ExposedArraySeq(((ExposedArrayBuffer)$this.chain().apply(0)).internalArray(), $this.size()));
/*    */   }
/*    */   
/*    */   public static String toString(ResizableParArrayCombiner $this) {
/* 42 */     return (new StringBuilder()).append("ResizableParArrayCombiner(").append(BoxesRunTime.boxToInteger($this.size())).append("): ").toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ResizableParArrayCombiner$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */