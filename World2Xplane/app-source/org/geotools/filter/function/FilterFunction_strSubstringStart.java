/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strSubstringStart extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("strSubstringStart", FunctionNameImpl.parameter("substring", String.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class), FunctionNameImpl.parameter("beginIndex", Integer.class) });
/*    */   
/*    */   public FilterFunction_strSubstringStart() {
/* 40 */     super("strSubstringStart");
/* 41 */     this.functionName = NAME;
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     int arg1;
/*    */     try {
/* 49 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function strSubstringStart argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 57 */       arg1 = ((Integer)getExpression(1).evaluate(feature, Integer.class)).intValue();
/* 58 */     } catch (Exception e) {
/* 60 */       throw new IllegalArgumentException("Filter Function problem for function strSubstringStart argument #1 - expected type int");
/*    */     } 
/* 64 */     return StaticGeometry.strSubstringStart(arg0, Integer.valueOf(arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strSubstringStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */