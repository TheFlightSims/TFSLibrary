/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_numberFormat2 extends FunctionExpressionImpl {
/* 44 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("numberFormat2", String.class, new Parameter[] { FunctionNameImpl.parameter("format", String.class), FunctionNameImpl.parameter("number", Number.class), FunctionNameImpl.parameter("minus", String.class), FunctionNameImpl.parameter("decimal", String.class), FunctionNameImpl.parameter("separator", String.class) });
/*    */   
/*    */   public FilterFunction_numberFormat2() {
/* 52 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String format;
/*    */     Double number;
/*    */     try {
/* 61 */       format = (String)getExpression(0).evaluate(feature, String.class);
/* 62 */     } catch (Exception e) {
/* 64 */       throw new IllegalArgumentException("Filter Function problem for function dateFormat argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 69 */       number = (Double)getExpression(1).evaluate(feature, Double.class);
/* 70 */     } catch (Exception e) {
/* 72 */       throw new IllegalArgumentException("Filter Function problem for function dateFormat argument #1 - expected type java.util.Date");
/*    */     } 
/* 76 */     if (format == null || number == null)
/* 77 */       return null; 
/* 80 */     DecimalFormatSymbols symbols = new DecimalFormatSymbols();
/* 82 */     if (this.params.size() > 2) {
/* 83 */       Character neg = (Character)getExpression(2).evaluate(feature, Character.class);
/* 84 */       symbols.setMinusSign(neg.charValue());
/*    */     } 
/* 87 */     if (this.params.size() > 3) {
/* 88 */       Character dec = (Character)getExpression(3).evaluate(feature, Character.class);
/* 89 */       symbols.setDecimalSeparator(dec.charValue());
/*    */     } 
/* 92 */     if (this.params.size() > 4) {
/* 93 */       Character grp = (Character)getExpression(4).evaluate(feature, Character.class);
/* 94 */       symbols.setGroupingSeparator(grp.charValue());
/*    */     } 
/* 97 */     DecimalFormat numberFormat = new DecimalFormat(format, symbols);
/* 98 */     return numberFormat.format(number);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_numberFormat2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */