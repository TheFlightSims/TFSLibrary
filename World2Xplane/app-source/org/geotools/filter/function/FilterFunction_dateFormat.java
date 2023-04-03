/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_dateFormat extends FunctionExpressionImpl {
/* 41 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("dateFormat", FunctionNameImpl.parameter("formatted", String.class), new Parameter[] { FunctionNameImpl.parameter("format", String.class), FunctionNameImpl.parameter("date", Date.class) });
/*    */   
/*    */   public FilterFunction_dateFormat() {
/* 47 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String format;
/*    */     Date date;
/*    */     try {
/* 56 */       format = (String)getExpression(0).evaluate(feature, String.class);
/* 57 */     } catch (Exception e) {
/* 59 */       throw new IllegalArgumentException("Filter Function problem for function dateFormat argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 64 */       date = (Date)getExpression(1).evaluate(feature, Date.class);
/* 65 */     } catch (Exception e) {
/* 67 */       throw new IllegalArgumentException("Filter Function problem for function dateFormat argument #1 - expected type java.util.Date");
/*    */     } 
/* 71 */     DateFormat dateFormat = new SimpleDateFormat(format);
/* 72 */     return dateFormat.format(date);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_dateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */