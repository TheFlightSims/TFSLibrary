/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileBooleanRef implements Serializable {
/*    */   private static final long serialVersionUID = -5730524563015615974L;
/*    */   
/*    */   public volatile boolean elem;
/*    */   
/*    */   public VolatileBooleanRef(boolean elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return String.valueOf(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileBooleanRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */