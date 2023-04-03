/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_toRadians extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("toRadians", FunctionNameImpl.parameter("radians", Double.class), new Parameter[] { FunctionNameImpl.parameter("degree", Number.class) });
/*    */   
/*    */   public FilterFunction_toRadians() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 49 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function toRadians argument #0 - expected type double");
/*    */     } 
/* 56 */     return new Double(Math.toRadians(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_toRadians.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */