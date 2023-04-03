/*    */ package scala.math;
/*    */ 
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class Integral$ implements Serializable {
/*    */   public static final Integral$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 30 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Integral$() {
/* 30 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Integral$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */