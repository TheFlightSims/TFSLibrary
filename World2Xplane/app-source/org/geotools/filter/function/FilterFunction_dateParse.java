/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_dateParse extends FunctionExpressionImpl {
/* 43 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("dateParse", FunctionNameImpl.parameter("date", Date.class), new Parameter[] { FunctionNameImpl.parameter("format", String.class), FunctionNameImpl.parameter("dateString", String.class) });
/*    */   
/*    */   public FilterFunction_dateParse() {
/* 49 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/*    */     String format, date;
/*    */     try {
/* 58 */       format = (String)getExpression(0).evaluate(feature, String.class);
/* 59 */     } catch (Exception e) {
/* 61 */       throw new IllegalArgumentException("Filter Function problem for function dateParse argument #0 - expected type String");
/*    */     } 
/*    */     try {
/* 66 */       date = (String)getExpression(1).evaluate(feature, String.class);
/* 67 */     } catch (Exception e) {
/* 69 */       throw new IllegalArgumentException("Filter Function problem for function dateParse argument #1 - expected type String");
/*    */     } 
/* 73 */     DateFormat dateFormat = new SimpleDateFormat(format);
/*    */     try {
/* 75 */       return dateFormat.parse(date);
/* 76 */     } catch (ParseException e) {
/* 77 */       throw new IllegalArgumentException("Invalid date, could not parse", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_dateParse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */