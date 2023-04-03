/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_IEEEremainder extends FunctionExpressionImpl {
/* 35 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("IEEEremainder", FunctionNameImpl.parameter("remainder", Double.class), new Parameter[] { FunctionNameImpl.parameter("dividend", Number.class), FunctionNameImpl.parameter("divisor", Number.class) });
/*    */   
/*    */   public FilterFunction_IEEEremainder() {
/* 41 */     super("IEEEremainder");
/* 42 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     double arg0;
/*    */     double arg1;
/*    */     try {
/* 50 */       arg0 = ((Double)getExpression(0).evaluate(feature, Double.class)).doubleValue();
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function IEEEremainder argument #0 - expected type double");
/*    */     } 
/*    */     try {
/* 58 */       arg1 = ((Double)getExpression(1).evaluate(feature, Double.class)).doubleValue();
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function IEEEremainder argument #1 - expected type double");
/*    */     } 
/* 65 */     return new Double(Math.IEEEremainder(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_IEEEremainder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */