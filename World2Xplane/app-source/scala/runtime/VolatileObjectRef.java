/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VolatileObjectRef<T> implements Serializable {
/*    */   private static final long serialVersionUID = -9055728157600312291L;
/*    */   
/*    */   public volatile T elem;
/*    */   
/*    */   public VolatileObjectRef(T elem) {
/* 18 */     this.elem = elem;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return String.valueOf(this.elem);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\VolatileObjectRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */