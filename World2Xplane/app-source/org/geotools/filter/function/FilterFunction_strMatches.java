/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strMatches extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("strMatches", FunctionNameImpl.parameter("match", Boolean.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class), FunctionNameImpl.parameter("regex", String.class) });
/*    */   
/*    */   public FilterFunction_strMatches() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     String arg1;
/*    */     try {
/* 48 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 52 */     } catch (Exception e) {
/* 54 */       throw new IllegalArgumentException("Filter Function problem for function strMatches argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 59 */       arg1 = (String)getExpression(1).evaluate(feature, String.class);
/* 63 */     } catch (Exception e) {
/* 65 */       throw new IllegalArgumentException("Filter Function problem for function strMatches argument #1 - expected type String");
/*    */     } 
/* 69 */     return new Boolean(StaticGeometry.strMatches(arg0, arg1));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strMatches.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */