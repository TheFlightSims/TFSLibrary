/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DoubleRef implements Serializable {
/*    */   private static final long serialVersionUID = 8304402127373655534L;
/*    */   
/*    */   public double elem;
/*    */   
/*    */   public DoubleRef(double elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Double.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\DoubleRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */