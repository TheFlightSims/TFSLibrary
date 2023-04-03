/*    */ package scala.math;
/*    */ 
/*    */ public abstract class Ordered$class {
/*    */   public static void $init$(Ordered $this) {}
/*    */   
/*    */   public static boolean $less(Ordered $this, Object that) {
/* 75 */     return ($this.compare((A)that) < 0);
/*    */   }
/*    */   
/*    */   public static boolean $greater(Ordered $this, Object that) {
/* 79 */     return ($this.compare((A)that) > 0);
/*    */   }
/*    */   
/*    */   public static boolean $less$eq(Ordered $this, Object that) {
/* 83 */     return ($this.compare((A)that) <= 0);
/*    */   }
/*    */   
/*    */   public static boolean $greater$eq(Ordered $this, Object that) {
/* 87 */     return ($this.compare((A)that) >= 0);
/*    */   }
/*    */   
/*    */   public static int compareTo(Ordered<Object> $this, Object that) {
/* 91 */     return $this.compare(that);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Ordered$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */