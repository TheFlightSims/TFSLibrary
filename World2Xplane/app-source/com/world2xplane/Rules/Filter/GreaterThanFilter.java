/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class GreaterThanFilter extends Filter {
/*    */   private final Pattern pattern;
/*    */   
/*    */   private String filter;
/*    */   
/*    */   public GreaterThanFilter(String name) {
/* 39 */     super(name);
/* 40 */     this.pattern = Pattern.compile("([0-9]*\\.[0-9]+|[0-9]+)");
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/*    */     try {
/*    */       Number numberValue;
/* 48 */       DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 50 */       NumberFormat numberFormat = new DecimalFormat("#.###########", formatSymbols);
/* 54 */       value = value.replaceAll(" ", "");
/* 55 */       value = value.replaceAll("[A-Za-z]", "");
/* 56 */       value = value.replaceAll("[A-Za-z]", "");
/* 57 */       if (!this.pattern.matcher(value).matches())
/* 58 */         return false; 
/* 60 */       if (this.filter.indexOf(".") > 0) {
/* 61 */         if (!value.contains("."))
/* 62 */           value = value + ".0"; 
/* 64 */         numberValue = Double.valueOf(numberFormat.parse(value).doubleValue());
/* 65 */         Double double_ = Double.valueOf(numberFormat.parse(this.filter).doubleValue());
/* 66 */         return (numberValue.doubleValue() >= double_.doubleValue());
/*    */       } 
/* 69 */       if (value.contains(".")) {
/* 70 */         numberValue = Double.valueOf(numberFormat.parse(value).doubleValue());
/*    */       } else {
/* 72 */         numberValue = new Integer(value);
/*    */       } 
/* 74 */       Integer filterValue = new Integer(this.filter);
/* 75 */       return (numberValue.intValue() >= filterValue.intValue());
/* 78 */     } catch (Exception e) {
/* 79 */       e.printStackTrace();
/* 80 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 85 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 89 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\GreaterThanFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */