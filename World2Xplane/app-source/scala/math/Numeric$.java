/*    */ package scala.math;
/*    */ 
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class Numeric$ implements Serializable {
/*    */   public static final Numeric$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Numeric$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Numeric$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */