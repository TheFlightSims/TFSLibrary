/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_lessEqualThan extends FunctionExpressionImpl {
/* 36 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("lessEqualThan", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("a", Number.class), FunctionNameImpl.parameter("b", Number.class) });
/*    */   
/*    */   public FilterFunction_lessEqualThan() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     Object arg0;
/*    */     Object arg1;
/*    */     try {
/* 49 */       arg0 = getExpression(0).evaluate(feature);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function lessEqualThan argument #0 - expected type Object");
/*    */     } 
/*    */     try {
/* 57 */       arg1 = getExpression(1).evaluate(feature);
/* 58 */     } catch (Exception e) {
/* 60 */       throw new IllegalArgumentException("Filter Function problem for function lessEqualThan argument #1 - expected type Object");
/*    */     } 
/* 64 */     return new Boolean(StaticGeometry.lessEqualThan(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_lessEqualThan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */