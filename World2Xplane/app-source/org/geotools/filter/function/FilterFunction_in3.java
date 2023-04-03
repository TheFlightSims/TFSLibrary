/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_in3 extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in3", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class), FunctionNameImpl.parameter("in3", Object.class) });
/*    */   
/*    */   public FilterFunction_in3() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object arg0;
/*    */     Object arg1;
/*    */     Object arg2;
/*    */     Object arg3;
/*    */     try {
/* 51 */       arg0 = getExpression(0).evaluate(feature);
/* 52 */     } catch (Exception e) {
/* 54 */       throw new IllegalArgumentException("Filter Function problem for function in3 argument #0 - expected type Object");
/*    */     } 
/*    */     try {
/* 59 */       arg1 = getExpression(1).evaluate(feature);
/* 60 */     } catch (Exception e) {
/* 62 */       throw new IllegalArgumentException("Filter Function problem for function in3 argument #1 - expected type Object");
/*    */     } 
/*    */     try {
/* 67 */       arg2 = getExpression(2).evaluate(feature);
/* 68 */     } catch (Exception e) {
/* 70 */       throw new IllegalArgumentException("Filter Function problem for function in3 argument #2 - expected type Object");
/*    */     } 
/*    */     try {
/* 75 */       arg3 = getExpression(3).evaluate(feature);
/* 76 */     } catch (Exception e) {
/* 78 */       throw new IllegalArgumentException("Filter Function problem for function in3 argument #3 - expected type Object");
/*    */     } 
/* 82 */     return new Boolean(StaticGeometry.in3(arg0, arg1, arg2, arg3));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */