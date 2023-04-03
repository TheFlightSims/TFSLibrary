/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strSubstring extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("strSubstring", FunctionNameImpl.parameter("substring", String.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class), FunctionNameImpl.parameter("beginIndex", Integer.class), FunctionNameImpl.parameter("endIndex", Integer.class) });
/*    */   
/*    */   public FilterFunction_strSubstring() {
/* 41 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     int arg1;
/*    */     int arg2;
/*    */     try {
/* 50 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 54 */     } catch (Exception e) {
/* 56 */       throw new IllegalArgumentException("Filter Function problem for function strSubstring argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 61 */       arg1 = ((Integer)getExpression(1).evaluate(feature, Integer.class)).intValue();
/* 62 */     } catch (Exception e) {
/* 64 */       throw new IllegalArgumentException("Filter Function problem for function strSubstring argument #1 - expected type int");
/*    */     } 
/*    */     try {
/* 69 */       arg2 = ((Integer)getExpression(2).evaluate(feature, Integer.class)).intValue();
/* 70 */     } catch (Exception e) {
/* 72 */       throw new IllegalArgumentException("Filter Function problem for function strSubstring argument #2 - expected type int");
/*    */     } 
/* 76 */     return StaticGeometry.strSubstring(arg0, Integer.valueOf(arg1), Integer.valueOf(arg2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strSubstring.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */