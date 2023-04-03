/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public abstract class GenericParTemplate$class {
/*    */   public static void $init$(GenericParTemplate $this) {}
/*    */   
/*    */   public static Builder newBuilder(GenericParTemplate $this) {
/* 32 */     return (Builder)$this.newCombiner();
/*    */   }
/*    */   
/*    */   public static Combiner newCombiner(GenericParTemplate $this) {
/* 35 */     Combiner cb = ((GenericParCompanion)$this.companion()).newCombiner();
/* 36 */     return cb;
/*    */   }
/*    */   
/*    */   public static Combiner genericBuilder(GenericParTemplate $this) {
/* 39 */     return $this.genericCombiner();
/*    */   }
/*    */   
/*    */   public static Combiner genericCombiner(GenericParTemplate $this) {
/* 42 */     Combiner cb = ((GenericParCompanion)$this.companion()).newCombiner();
/* 43 */     return cb;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericParTemplate$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */