/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileLongRef implements Serializable {
/*    */   private static final long serialVersionUID = -3567869820105829499L;
/*    */   
/*    */   public volatile long elem;
/*    */   
/*    */   public VolatileLongRef(long elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Long.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileLongRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */