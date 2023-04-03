/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class IntRef implements Serializable {
/*    */   private static final long serialVersionUID = 1488197132022872888L;
/*    */   
/*    */   public int elem;
/*    */   
/*    */   public IntRef(int elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Integer.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\IntRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */