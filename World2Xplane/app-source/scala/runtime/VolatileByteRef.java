/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileByteRef implements Serializable {
/*    */   private static final long serialVersionUID = -100666928446877072L;
/*    */   
/*    */   public volatile byte elem;
/*    */   
/*    */   public VolatileByteRef(byte elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Byte.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileByteRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */