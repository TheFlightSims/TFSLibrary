/*    */ package scala;
/*    */ 
/*    */ public abstract class Proxy$class {
/*    */   public static void $init$(Proxy $this) {}
/*    */   
/*    */   public static int hashCode(Proxy $this) {
/* 28 */     return $this.self().hashCode();
/*    */   }
/*    */   
/*    */   public static boolean equals(Proxy $this, Object that) {
/*    */     boolean bool;
/* 29 */     if (that == null) {
/* 29 */       bool = false;
/*    */     } else {
/* 31 */       bool = 
/* 32 */         (that == $this || that == $this.self() || that.equals($this.self())) ? true : false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public static String toString(Proxy $this) {
/* 35 */     return String.valueOf($this.self());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Proxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */