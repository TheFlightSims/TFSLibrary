/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_pow extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("pow", FunctionNameImpl.parameter("power", Double.class), new Parameter[] { FunctionNameImpl.parameter("base", Number.class), FunctionNameImpl.parameter("exponent", Number.class) });
/*    */   
/*    */   public FilterFunction_pow() {
/* 42 */     super("pow");
/* 43 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 47 */     return 2;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     double arg1;
/*    */     try {
/* 55 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 56 */     } catch (Exception e) {
/* 58 */       throw new IllegalArgumentException("Filter Function problem for function pow argument #0 - expected type double");
/*    */     } 
/*    */     try {
/* 63 */       arg1 = ((Double)getExpression(1).evaluate(feature, Double.class)).doubleValue();
/* 64 */     } catch (Exception e) {
/* 66 */       throw new IllegalArgumentException("Filter Function problem for function pow argument #1 - expected type double");
/*    */     } 
/* 70 */     return new Double(Math.pow(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_pow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */