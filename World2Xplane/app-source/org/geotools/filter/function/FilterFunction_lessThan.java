/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_lessThan extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("lessThan", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("a", Number.class), FunctionNameImpl.parameter("b", Number.class) });
/*    */   
/*    */   public FilterFunction_lessThan() {
/* 39 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object arg0;
/*    */     Object arg1;
/*    */     try {
/* 47 */       arg0 = getExpression(0).evaluate(feature);
/* 48 */     } catch (Exception e) {
/* 50 */       throw new IllegalArgumentException("Filter Function problem for function lessThan argument #0 - expected type Object");
/*    */     } 
/*    */     try {
/* 55 */       arg1 = getExpression(1).evaluate(feature);
/* 56 */     } catch (Exception e) {
/* 58 */       throw new IllegalArgumentException("Filter Function problem for function lessThan argument #1 - expected type Object");
/*    */     } 
/* 62 */     return new Boolean(StaticGeometry.lessThan(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_lessThan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */