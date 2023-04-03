/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_in2 extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("in2", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("value", Boolean.class), FunctionNameImpl.parameter("in1", Object.class), FunctionNameImpl.parameter("in2", Object.class) });
/*    */   
/*    */   public FilterFunction_in2() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object arg0;
/*    */     Object arg1;
/*    */     Object arg2;
/*    */     try {
/* 49 */       arg0 = getExpression(0).evaluate(feature);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function in2 argument #0 - expected type Object");
/*    */     } 
/*    */     try {
/* 57 */       arg1 = getExpression(1).evaluate(feature);
/* 58 */     } catch (Exception e) {
/* 60 */       throw new IllegalArgumentException("Filter Function problem for function in2 argument #1 - expected type Object");
/*    */     } 
/*    */     try {
/* 65 */       arg2 = getExpression(2).evaluate(feature);
/* 66 */     } catch (Exception e) {
/* 68 */       throw new IllegalArgumentException("Filter Function problem for function in2 argument #2 - expected type Object");
/*    */     } 
/* 72 */     return new Boolean(StaticGeometry.in2(arg0, arg1, arg2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_in2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */