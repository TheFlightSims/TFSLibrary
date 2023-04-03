/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ 
/*    */ public abstract class ParSeq$class {
/*    */   public static void $init$(ParSeq $this) {}
/*    */   
/*    */   public static GenericCompanion companion(ParSeq $this) {
/* 40 */     return (GenericCompanion)ParSeq$.MODULE$;
/*    */   }
/*    */   
/*    */   public static String toString(ParSeq $this) {
/* 45 */     return ParIterableLike$class.toString($this);
/*    */   }
/*    */   
/*    */   public static String stringPrefix(ParSeq $this) {
/* 47 */     return $this.getClass().getSimpleName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeq$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */