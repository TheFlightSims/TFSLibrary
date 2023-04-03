/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strReplace extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("strReplace", FunctionNameImpl.parameter("string", String.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class), FunctionNameImpl.parameter("search", String.class), FunctionNameImpl.parameter("replace", String.class), FunctionNameImpl.parameter("all", Boolean.class) });
/*    */   
/*    */   public FilterFunction_strReplace() {
/* 42 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0, arg1, arg2;
/* 49 */     Boolean arg3 = null;
/*    */     try {
/* 52 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 53 */     } catch (Exception e) {
/* 55 */       throw new IllegalArgumentException("Filter Function problem for function strMatches argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 60 */       arg1 = (String)getExpression(1).evaluate(feature, String.class);
/* 61 */     } catch (Exception e) {
/* 63 */       throw new IllegalArgumentException("Filter Function problem for function strMatches argument #1 - expected type String");
/*    */     } 
/*    */     try {
/* 68 */       arg2 = (String)getExpression(2).evaluate(feature, String.class);
/* 69 */     } catch (Exception e) {
/* 71 */       throw new IllegalArgumentException("Filter Function problem for function strMatches argument #1 - expected type String");
/*    */     } 
/*    */     try {
/* 76 */       arg3 = (Boolean)getExpression(3).evaluate(feature, Boolean.class);
/* 77 */     } catch (Exception e) {}
/* 81 */     if (arg3 == null)
/* 82 */       arg3 = Boolean.FALSE; 
/* 84 */     return StaticGeometry.strReplace(arg0, arg1, arg2, Boolean.valueOf(arg3.booleanValue()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strReplace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */