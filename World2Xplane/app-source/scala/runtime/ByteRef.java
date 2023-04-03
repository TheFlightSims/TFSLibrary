/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ByteRef implements Serializable {
/*    */   private static final long serialVersionUID = -100666928446877072L;
/*    */   
/*    */   public byte elem;
/*    */   
/*    */   public ByteRef(byte elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Byte.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ByteRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */