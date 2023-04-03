/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_toDegrees extends FunctionExpressionImpl {
/* 35 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("toDegrees", FunctionNameImpl.parameter("degrees", Double.class), new Parameter[] { FunctionNameImpl.parameter("radians", Number.class) });
/*    */   
/*    */   public FilterFunction_toDegrees() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 47 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 48 */     } catch (Exception e) {
/* 50 */       throw new IllegalArgumentException("Filter Function problem for function toDegrees argument #0 - expected type double");
/*    */     } 
/* 54 */     return new Double(Math.toDegrees(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_toDegrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */