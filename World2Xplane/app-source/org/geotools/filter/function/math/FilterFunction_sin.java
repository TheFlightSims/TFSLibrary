/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_sin extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("sin", FunctionNameImpl.parameter("sine", Double.class), new Parameter[] { FunctionNameImpl.parameter("radians", Number.class) });
/*    */   
/*    */   public FilterFunction_sin() {
/* 39 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 46 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 47 */     } catch (Exception e) {
/* 49 */       throw new IllegalArgumentException("Filter Function problem for function sin argument #0 - expected type double");
/*    */     } 
/* 53 */     return new Double(Math.sin(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_sin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */