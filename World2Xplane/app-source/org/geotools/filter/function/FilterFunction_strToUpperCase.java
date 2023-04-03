/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_strToUpperCase extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("strToUpperCase", FunctionNameImpl.parameter("uppercase", String.class), new Parameter[] { FunctionNameImpl.parameter("string", String.class) });
/*    */   
/*    */   public FilterFunction_strToUpperCase() {
/* 39 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String arg0;
/*    */     try {
/* 46 */       arg0 = (String)getExpression(0).evaluate(feature, String.class);
/* 50 */     } catch (Exception e) {
/* 52 */       throw new IllegalArgumentException("Filter Function problem for function strLength argument #0 - expected type String");
/*    */     } 
/* 56 */     return StaticGeometry.strToUpperCase(arg0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_strToUpperCase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */