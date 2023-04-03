/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_abs_4 extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("abs_4", FunctionNameImpl.parameter("double", Double.class), new Parameter[] { FunctionNameImpl.parameter("number", Double.class) });
/*    */   
/*    */   public FilterFunction_abs_4() {
/* 41 */     super("abs_4");
/* 42 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 46 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 53 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 54 */     } catch (Exception e) {
/* 56 */       throw new IllegalArgumentException("Filter Function problem for function abs argument #0 - expected type double");
/*    */     } 
/* 60 */     return new Double(Math.abs(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_abs_4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */