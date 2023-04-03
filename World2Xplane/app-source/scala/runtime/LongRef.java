/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LongRef implements Serializable {
/*    */   private static final long serialVersionUID = -3567869820105829499L;
/*    */   
/*    */   public long elem;
/*    */   
/*    */   public LongRef(long elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Long.toString(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\LongRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */