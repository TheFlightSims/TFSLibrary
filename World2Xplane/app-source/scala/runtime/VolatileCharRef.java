/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileCharRef implements Serializable {
/*    */   private static final long serialVersionUID = 6537214938268005702L;
/*    */   
/*    */   public volatile char elem;
/*    */   
/*    */   public VolatileCharRef(char elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Character.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileCharRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */