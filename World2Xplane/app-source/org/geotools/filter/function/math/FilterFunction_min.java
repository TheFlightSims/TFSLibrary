/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_min extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("min", FunctionNameImpl.parameter("minium", Double.class), new Parameter[] { FunctionNameImpl.parameter("double", Double.class), FunctionNameImpl.parameter("double", Double.class) });
/*    */   
/*    */   public FilterFunction_min() {
/* 43 */     super("min");
/* 44 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 48 */     return 2;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     double arg1;
/*    */     try {
/* 56 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 57 */     } catch (Exception e) {
/* 59 */       throw new IllegalArgumentException("Filter Function problem for function min argument #0 - expected type int");
/*    */     } 
/*    */     try {
/* 64 */       arg1 = ((Double)getExpression(1).evaluate(feature, Double.class)).doubleValue();
/* 65 */     } catch (Exception e) {
/* 67 */       throw new IllegalArgumentException("Filter Function problem for function min argument #1 - expected type int");
/*    */     } 
/* 71 */     return new Double(Math.min(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_min.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */