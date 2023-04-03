/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.mutable.ArraySeq;
/*    */ import scala.collection.mutable.UnrolledBuffer;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.package$;
/*    */ import scala.reflect.ClassTag$;
/*    */ 
/*    */ public abstract class UnrolledParArrayCombiner$class {
/*    */   public static void $init$(UnrolledParArrayCombiner $this) {
/* 33 */     $this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(new DoublingUnrolledBuffer(ClassTag$.MODULE$.Any()));
/*    */   }
/*    */   
/*    */   public static UnrolledParArrayCombiner $plus$eq(UnrolledParArrayCombiner $this, Object elem) {
/* 36 */     $this.buff().$plus$eq(elem);
/* 37 */     return $this;
/*    */   }
/*    */   
/*    */   public static ParArray result(UnrolledParArrayCombiner<T> $this) {
/* 41 */     ArraySeq<?> arrayseq = new ArraySeq($this.size());
/* 42 */     Object[] array = arrayseq.array();
/* 44 */     $this.combinerTaskSupport().executeAndWaitResult(new UnrolledParArrayCombiner.CopyUnrolledToArray($this, array, 0, $this.size()));
/* 46 */     return new ParArray(arrayseq);
/*    */   }
/*    */   
/*    */   public static void clear(UnrolledParArrayCombiner $this) {
/* 50 */     $this.buff().clear();
/*    */   }
/*    */   
/*    */   public static void sizeHint(UnrolledParArrayCombiner $this, int sz) {
/* 54 */     $this.buff().lastPtr().next_$eq(new UnrolledBuffer.Unrolled(0, new Object[sz], null, $this.buff(), ClassTag$.MODULE$.Any()));
/* 55 */     $this.buff().lastPtr_$eq($this.buff().lastPtr().next());
/*    */   }
/*    */   
/*    */   public static Combiner combine(UnrolledParArrayCombiner $this, Combiner other) {
/* 58 */     if (other != $this) {
/* 60 */       if (other instanceof UnrolledParArrayCombiner) {
/* 60 */         UnrolledParArrayCombiner unrolledParArrayCombiner = (UnrolledParArrayCombiner)other;
/* 61 */         $this.buff().concat(unrolledParArrayCombiner.buff());
/*    */         return $this;
/*    */       } 
/*    */     } else {
/*    */       return $this;
/*    */     } 
/* 63 */     throw package$.MODULE$.unsupportedop("Cannot combine with combiner of different type.");
/*    */   }
/*    */   
/*    */   public static int size(UnrolledParArrayCombiner $this) {
/* 66 */     return $this.buff().size();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\UnrolledParArrayCombiner$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */