/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_in4 extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in4", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class), FunctionNameImpl.parameter("in4", Object.class) });
/*    */   
/*    */   public FilterFunction_in4() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object arg0;
/*    */     Object arg1;
/*    */     Object arg2;
/*    */     Object arg3;
/*    */     Object arg4;
/*    */     try {
/* 53 */       arg0 = getExpression(0).evaluate(feature);
/* 54 */     } catch (Exception e) {
/* 56 */       throw new IllegalArgumentException("Filter Function problem for function in4 argument #0 - expected type Object");
/*    */     } 
/*    */     try {
/* 61 */       arg1 = getExpression(1).evaluate(feature);
/* 62 */     } catch (Exception e) {
/* 64 */       throw new IllegalArgumentException("Filter Function problem for function in4 argument #1 - expected type Object");
/*    */     } 
/*    */     try {
/* 69 */       arg2 = getExpression(2).evaluate(feature);
/* 70 */     } catch (Exception e) {
/* 72 */       throw new IllegalArgumentException("Filter Function problem for function in4 argument #2 - expected type Object");
/*    */     } 
/*    */     try {
/* 77 */       arg3 = getExpression(3).evaluate(feature);
/* 78 */     } catch (Exception e) {
/* 80 */       throw new IllegalArgumentException("Filter Function problem for function in4 argument #3 - expected type Object");
/*    */     } 
/*    */     try {
/* 85 */       arg4 = getExpression(4).evaluate(feature);
/* 86 */     } catch (Exception e) {
/* 88 */       throw new IllegalArgumentException("Filter Function problem for function in4 argument #4 - expected type Object");
/*    */     } 
/* 92 */     return new Boolean(StaticGeometry.in4(arg0, arg1, arg2, arg3, arg4));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */