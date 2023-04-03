/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_isLike extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("isLike", Boolean.class, new Parameter[] { FunctionNameImpl.parameter("string", String.class), FunctionNameImpl.parameter("regex", String.class) });
/*    */   
/*    */   public FilterFunction_isLike() {
/* 39 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     String arg1;
/*    */     try {
/* 47 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 51 */     } catch (Exception e) {
/* 53 */       throw new IllegalArgumentException("Filter Function problem for function isLike argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 58 */       arg1 = (String)getExpression(1).evaluate(feature, String.class);
/* 62 */     } catch (Exception e) {
/* 64 */       throw new IllegalArgumentException("Filter Function problem for function isLike argument #1 - expected type String");
/*    */     } 
/* 68 */     return new Boolean(StaticGeometry.isLike(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_isLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */