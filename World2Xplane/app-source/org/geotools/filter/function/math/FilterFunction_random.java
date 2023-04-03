/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.opengis.filter.expression.VolatileFunction;
/*    */ 
/*    */ public class FilterFunction_random extends FunctionExpressionImpl implements VolatileFunction {
/*    */   public FilterFunction_random() {
/* 34 */     super("random");
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 38 */     return 0;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 43 */     return new Double(Math.random());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_random.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */