/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_between extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("between", FunctionNameImpl.parameter("between", Boolean.class), new Parameter[] { FunctionNameImpl.parameter("value", Object.class), FunctionNameImpl.parameter("low", Object.class), FunctionNameImpl.parameter("high", Object.class) });
/*    */   
/*    */   public FilterFunction_between() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object value;
/*    */     Object low;
/*    */     Object high;
/*    */     try {
/* 50 */       value = getExpression(0).evaluate(feature);
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function between argument #0 - expected type Object");
/*    */     } 
/*    */     try {
/* 58 */       low = getExpression(1).evaluate(feature);
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function between argument #1 - expected type Object");
/*    */     } 
/*    */     try {
/* 66 */       high = getExpression(2).evaluate(feature);
/* 67 */     } catch (Exception e) {
/* 69 */       throw new IllegalArgumentException("Filter Function problem for function between argument #2 - expected type Object");
/*    */     } 
/* 73 */     return new Boolean(StaticGeometry.between(value, low, high));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_between.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */