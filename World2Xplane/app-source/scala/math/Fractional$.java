/*    */ package scala.math;
/*    */ 
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class Fractional$ implements Serializable {
/*    */   public static final Fractional$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Fractional$() {
/* 26 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Fractional$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */