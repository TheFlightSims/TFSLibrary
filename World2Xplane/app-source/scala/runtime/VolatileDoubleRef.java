/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileDoubleRef implements Serializable {
/*    */   private static final long serialVersionUID = 8304402127373655534L;
/*    */   
/*    */   public volatile double elem;
/*    */   
/*    */   public VolatileDoubleRef(double elem) {
/* 17 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return Double.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileDoubleRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */