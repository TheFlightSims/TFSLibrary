/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileIntRef implements Serializable {
/*    */   private static final long serialVersionUID = 1488197132022872888L;
/*    */   
/*    */   public volatile int elem;
/*    */   
/*    */   public VolatileIntRef(int elem) {
/* 17 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return Integer.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileIntRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */