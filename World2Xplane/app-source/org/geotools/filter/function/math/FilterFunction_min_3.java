/*    */ package org.geotools.filter.function.math;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_min_3 extends FunctionExpressionImpl {
/* 37 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("min_3", FunctionNameImpl.parameter("minium", Float.class), new Parameter[] { FunctionNameImpl.parameter("float", Number.class), FunctionNameImpl.parameter("float", Number.class) });
/*    */   
/*    */   public FilterFunction_min_3() {
/* 43 */     super("min_3");
/* 44 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 48 */     return 2;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     float arg0;
/*    */     float arg1;
/*    */     try {
/* 56 */       arg0 = ((Float)getExpression(0).evaluate(feature, Float.class)).floatValue();
/* 57 */     } catch (Exception e) {
/* 59 */       throw new IllegalArgumentException("Filter Function problem for function min argument #0 - expected type float");
/*    */     } 
/*    */     try {
/* 64 */       arg1 = ((Float)getExpression(1).evaluate(feature, Float.class)).floatValue();
/* 65 */     } catch (Exception e) {
/* 67 */       throw new IllegalArgumentException("Filter Function problem for function min argument #1 - expected type float");
/*    */     } 
/* 71 */     return new Float(Math.min(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\FilterFunction_min_3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */