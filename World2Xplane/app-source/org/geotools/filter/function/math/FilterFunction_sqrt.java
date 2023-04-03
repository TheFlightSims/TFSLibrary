/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_sqrt extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("sqrt", FunctionNameImpl.parameter("root", Double.class), new Parameter[] { FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public FilterFunction_sqrt() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 49 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function sqrt argument #0 - expected type double");
/*    */     } 
/* 56 */     return new Double(Math.sqrt(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_sqrt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */