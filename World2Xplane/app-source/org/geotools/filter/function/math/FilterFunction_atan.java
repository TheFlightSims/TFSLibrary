/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_atan extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("atan", FunctionNameImpl.parameter("arc tan", Double.class), new Parameter[] { FunctionNameImpl.parameter("value", Double.class) });
/*    */   
/*    */   public FilterFunction_atan() {
/* 42 */     super("atan");
/* 43 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 47 */     return 1;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     try {
/* 54 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 55 */     } catch (Exception e) {
/* 57 */       throw new IllegalArgumentException("Filter Function problem for function atan argument #0 - expected type double");
/*    */     } 
/* 61 */     return new Double(Math.atan(arg0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_atan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */