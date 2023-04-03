/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileFloatRef implements Serializable {
/*    */   private static final long serialVersionUID = -5793980990371366933L;
/*    */   
/*    */   public volatile float elem;
/*    */   
/*    */   public VolatileFloatRef(float elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Float.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileFloatRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */