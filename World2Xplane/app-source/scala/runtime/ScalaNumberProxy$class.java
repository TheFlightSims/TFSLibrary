/*    */ package scala.runtime;
/*    */ 
/*    */ public abstract class ScalaNumberProxy$class {
/*    */   public static void $init$(ScalaNumberProxy $this) {}
/*    */   
/*    */   public static Object underlying(ScalaNumberProxy $this) {
/* 26 */     return $this.self();
/*    */   }
/*    */   
/*    */   public static double doubleValue(ScalaNumberProxy<T> $this) {
/* 27 */     return $this.num().toDouble($this.self());
/*    */   }
/*    */   
/*    */   public static float floatValue(ScalaNumberProxy<T> $this) {
/* 28 */     return $this.num().toFloat($this.self());
/*    */   }
/*    */   
/*    */   public static long longValue(ScalaNumberProxy<T> $this) {
/* 29 */     return $this.num().toLong($this.self());
/*    */   }
/*    */   
/*    */   public static int intValue(ScalaNumberProxy<T> $this) {
/* 30 */     return $this.num().toInt($this.self());
/*    */   }
/*    */   
/*    */   public static byte byteValue(ScalaNumberProxy $this) {
/* 31 */     return (byte)$this.intValue();
/*    */   }
/*    */   
/*    */   public static short shortValue(ScalaNumberProxy $this) {
/* 32 */     return (short)$this.intValue();
/*    */   }
/*    */   
/*    */   public static Object min(ScalaNumberProxy<T> $this, Object that) {
/* 34 */     return $this.num().min($this.self(), that);
/*    */   }
/*    */   
/*    */   public static Object max(ScalaNumberProxy<T> $this, Object that) {
/* 35 */     return $this.num().max($this.self(), that);
/*    */   }
/*    */   
/*    */   public static Object abs(ScalaNumberProxy<T> $this) {
/* 36 */     return $this.num().abs($this.self());
/*    */   }
/*    */   
/*    */   public static int signum(ScalaNumberProxy<T> $this) {
/* 37 */     return $this.num().signum($this.self());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ScalaNumberProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */