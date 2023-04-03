/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strConcat extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("strConcat", FunctionNameImpl.parameter("strConcat", String.class), new Parameter[] { FunctionNameImpl.parameter("string1", String.class), FunctionNameImpl.parameter("string2", String.class) });
/*    */   
/*    */   public FilterFunction_strConcat() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     String arg1;
/*    */     try {
/* 49 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function strConcat argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 57 */       arg1 = (String)getExpression(1).evaluate(feature, String.class);
/* 58 */     } catch (Exception e) {
/* 60 */       throw new IllegalArgumentException("Filter Function problem for function strConcat argument #1 - expected type String");
/*    */     } 
/* 64 */     return StaticGeometry.strConcat(arg0, arg1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strConcat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */