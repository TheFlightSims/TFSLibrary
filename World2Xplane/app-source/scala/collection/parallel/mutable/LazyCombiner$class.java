/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Function2;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class LazyCombiner$class {
/*    */   public static void $init$(LazyCombiner $this) {
/* 27 */     $this.scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq((Growable)$this.chain().last());
/*    */   }
/*    */   
/*    */   public static LazyCombiner $plus$eq(LazyCombiner $this, Object elem) {
/* 28 */     $this.lastbuff().$plus$eq(elem);
/* 28 */     return $this;
/*    */   }
/*    */   
/*    */   public static Object result(LazyCombiner $this) {
/* 29 */     return $this.allocateAndCopy();
/*    */   }
/*    */   
/*    */   public static void clear(LazyCombiner $this) {
/* 30 */     $this.chain().clear();
/*    */   }
/*    */   
/*    */   public static Combiner combine(LazyCombiner $this, Combiner other) {
/* 31 */     if ($this != other) {
/* 32 */       if (other instanceof LazyCombiner) {
/* 33 */         LazyCombiner that = (LazyCombiner)other;
/*    */       } else {
/* 35 */         throw new UnsupportedOperationException("Cannot combine with combiner of different type.");
/*    */       } 
/*    */     } else {
/*    */     
/*    */     } 
/* 36 */     return $this;
/*    */   }
/*    */   
/*    */   public static int size(LazyCombiner<Elem, To, Buff> $this) {
/* 37 */     return BoxesRunTime.unboxToInt($this.chain().foldLeft(BoxesRunTime.boxToInteger(0), (Function2)new LazyCombiner$$anonfun$size$1($this)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\LazyCombiner$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */