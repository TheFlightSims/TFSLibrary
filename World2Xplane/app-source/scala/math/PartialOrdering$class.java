/*    */ package scala.math;
/*    */ 
/*    */ public abstract class PartialOrdering$class {
/*    */   public static void $init$(PartialOrdering $this) {}
/*    */   
/*    */   public static boolean gteq(PartialOrdering<Object> $this, Object x, Object y) {
/* 51 */     return $this.lteq(y, x);
/*    */   }
/*    */   
/*    */   public static boolean lt(PartialOrdering<Object> $this, Object x, Object y) {
/* 56 */     return ($this.lteq(x, y) && !$this.equiv(x, y));
/*    */   }
/*    */   
/*    */   public static boolean gt(PartialOrdering<Object> $this, Object x, Object y) {
/* 61 */     return ($this.gteq(x, y) && !$this.equiv(x, y));
/*    */   }
/*    */   
/*    */   public static boolean equiv(PartialOrdering<Object> $this, Object x, Object y) {
/* 65 */     return ($this.lteq(x, y) && $this.lteq(y, x));
/*    */   }
/*    */   
/*    */   public static PartialOrdering reverse(PartialOrdering<T> $this) {
/* 67 */     return new PartialOrdering$$anon$1($this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\PartialOrdering$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */