/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.math.Ordering;
/*    */ 
/*    */ public abstract class GenericOrderedTraversableTemplate$class {
/*    */   public static void $init$(GenericOrderedTraversableTemplate $this) {}
/*    */   
/*    */   public static Builder genericOrderedBuilder(GenericOrderedTraversableTemplate $this, Ordering ord) {
/* 26 */     return $this.orderedCompanion().newBuilder(ord);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericOrderedTraversableTemplate$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */