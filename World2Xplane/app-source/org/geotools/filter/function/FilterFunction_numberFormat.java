/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_numberFormat extends FunctionExpressionImpl {
/* 40 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("numberFormat", String.class, new Parameter[] { FunctionNameImpl.parameter("format", String.class), FunctionNameImpl.parameter("number", Number.class) });
/*    */   
/*    */   public FilterFunction_numberFormat() {
/* 45 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String format;
/*    */     Double number;
/*    */     try {
/* 54 */       format = (String)getExpression(0).evaluate(feature, String.class);
/* 55 */     } catch (Exception e) {
/* 57 */       throw new IllegalArgumentException("Filter Function problem for function dateFormat argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 62 */       number = (Double)getExpression(1).evaluate(feature, Double.class);
/* 63 */     } catch (Exception e) {
/* 65 */       throw new IllegalArgumentException("Filter Function problem for function dateFormat argument #1 - expected type java.util.Date");
/*    */     } 
/* 69 */     if (format == null || number == null)
/* 70 */       return null; 
/* 73 */     DecimalFormat numberFormat = new DecimalFormat(format);
/* 74 */     return numberFormat.format(number);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_numberFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */