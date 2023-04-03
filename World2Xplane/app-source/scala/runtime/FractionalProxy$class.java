/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.collection.immutable.NumericRange$;
/*    */ import scala.collection.immutable.Range;
/*    */ 
/*    */ public abstract class FractionalProxy$class {
/*    */   public static void $init$(FractionalProxy $this) {}
/*    */   
/*    */   public static boolean isWhole(FractionalProxy $this) {
/* 61 */     return false;
/*    */   }
/*    */   
/*    */   public static Range.Partial until(FractionalProxy $this, Object end) {
/* 62 */     return new Range.Partial(new FractionalProxy$$anonfun$until$1($this, (FractionalProxy<T>)end));
/*    */   }
/*    */   
/*    */   public static NumericRange.Exclusive until(FractionalProxy $this, Object end, Object step) {
/* 63 */     return NumericRange$.MODULE$.apply($this.self(), end, step, $this.integralNum());
/*    */   }
/*    */   
/*    */   public static Range.Partial to(FractionalProxy $this, Object end) {
/* 64 */     return new Range.Partial(new FractionalProxy$$anonfun$to$1($this, (FractionalProxy<T>)end));
/*    */   }
/*    */   
/*    */   public static NumericRange.Inclusive to(FractionalProxy $this, Object end, Object step) {
/* 65 */     return NumericRange$.MODULE$.inclusive($this.self(), end, step, $this.integralNum());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\FractionalProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */