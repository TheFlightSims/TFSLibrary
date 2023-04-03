/*    */ package scala.runtime;
/*    */ 
/*    */ public abstract class OrderedProxy$class {
/*    */   public static void $init$(OrderedProxy $this) {}
/*    */   
/*    */   public static int compare(OrderedProxy<T> $this, Object y) {
/* 71 */     return $this.ord().compare($this.self(), y);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\OrderedProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */