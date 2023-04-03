/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileShortRef implements Serializable {
/*    */   private static final long serialVersionUID = 4218441291229072313L;
/*    */   
/*    */   public volatile short elem;
/*    */   
/*    */   public VolatileShortRef(short elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Short.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileShortRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */