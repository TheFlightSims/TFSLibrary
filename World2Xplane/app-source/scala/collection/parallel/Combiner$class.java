/*    */ package scala.collection.parallel;
/*    */ 
/*    */ public abstract class Combiner$class {
/*    */   public static void $init$(Combiner $this) {
/* 39 */     $this._combinerTaskSupport_$eq(package$.MODULE$.defaultTaskSupport());
/*    */   }
/*    */   
/*    */   public static TaskSupport combinerTaskSupport(Combiner $this) {
/* 42 */     TaskSupport cts = $this._combinerTaskSupport();
/* 44 */     $this._combinerTaskSupport_$eq(package$.MODULE$.defaultTaskSupport());
/* 45 */     return (cts == null) ? package$.MODULE$.defaultTaskSupport() : 
/* 46 */       cts;
/*    */   }
/*    */   
/*    */   public static void combinerTaskSupport_$eq(Combiner $this, TaskSupport cts) {
/* 49 */     $this._combinerTaskSupport_$eq(cts);
/*    */   }
/*    */   
/*    */   public static boolean canBeShared(Combiner $this) {
/* 83 */     return false;
/*    */   }
/*    */   
/*    */   public static Object resultWithTaskSupport(Combiner $this) {
/* 89 */     Object res = $this.result();
/* 90 */     return package$.MODULE$.setTaskSupport(res, $this.combinerTaskSupport());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Combiner$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */