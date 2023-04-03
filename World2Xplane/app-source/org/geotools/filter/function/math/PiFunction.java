/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ 
/*    */ public class PiFunction extends FunctionExpressionImpl {
/*    */   public PiFunction() {
/* 36 */     super("pi");
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 40 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     return "PI()";
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object) {
/* 49 */     return new Double(Math.PI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\PiFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */