/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.collection.immutable.NumericRange$;
/*    */ 
/*    */ public abstract class IntegralProxy$class {
/*    */   public static void $init$(IntegralProxy $this) {}
/*    */   
/*    */   public static NumericRange.Exclusive until(IntegralProxy<T> $this, Object end) {
/* 46 */     return NumericRange$.MODULE$.apply($this.self(), end, $this.num().one(), $this.num());
/*    */   }
/*    */   
/*    */   public static NumericRange.Exclusive until(IntegralProxy $this, Object end, Object step) {
/* 47 */     return NumericRange$.MODULE$.apply($this.self(), end, step, $this.num());
/*    */   }
/*    */   
/*    */   public static NumericRange.Inclusive to(IntegralProxy<T> $this, Object end) {
/* 48 */     return NumericRange$.MODULE$.inclusive($this.self(), end, $this.num().one(), $this.num());
/*    */   }
/*    */   
/*    */   public static NumericRange.Inclusive to(IntegralProxy $this, Object end, Object step) {
/* 49 */     return NumericRange$.MODULE$.inclusive($this.self(), end, step, $this.num());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\IntegralProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */