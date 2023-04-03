/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public abstract class GenericParMapTemplate$class {
/*    */   public static void $init$(GenericParMapTemplate $this) {}
/*    */   
/*    */   public static Combiner newCombiner(GenericParMapTemplate $this) {
/* 52 */     Combiner cb = $this.mapCompanion().newCombiner();
/* 53 */     return cb;
/*    */   }
/*    */   
/*    */   public static Combiner genericMapCombiner(GenericParMapTemplate $this) {
/* 59 */     Combiner cb = $this.mapCompanion().newCombiner();
/* 60 */     return cb;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericParMapTemplate$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */