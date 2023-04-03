/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class LessThanFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   private Pattern pattern;
/*    */   
/*    */   public LessThanFilter(String name) {
/* 39 */     super(name);
/* 40 */     this.pattern = Pattern.compile("([0-9]*\\.[0-9]+|[0-9]+)");
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 45 */     value = value.replaceAll(" ", "");
/* 46 */     value = value.replaceAll("[A-Za-z]", "");
/* 47 */     if (!this.pattern.matcher(value).matches())
/* 48 */       return false; 
/* 52 */     value = value.replaceAll(" ", "");
/* 53 */     value = value.replaceAll("[A-Za-z]", "");
/*    */     try {
/*    */       Number numberValue;
/* 56 */       DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 58 */       NumberFormat numberFormat = new DecimalFormat("#.###########", formatSymbols);
/* 62 */       value = value.replaceAll("[A-Za-z]", "");
/* 63 */       value = value.replaceAll("[A-Za-z]", "");
/* 64 */       if (!this.pattern.matcher(value).matches())
/* 65 */         return false; 
/* 67 */       if (this.filter.indexOf(".") > 0) {
/* 68 */         if (!value.contains("."))
/* 69 */           value = value + ".0"; 
/* 71 */         numberValue = Double.valueOf(numberFormat.parse(value).doubleValue());
/* 72 */         Double double_ = Double.valueOf(numberFormat.parse(this.filter).doubleValue());
/* 73 */         return (numberValue.doubleValue() <= double_.doubleValue());
/*    */       } 
/* 76 */       if (value.contains(".")) {
/* 77 */         numberValue = Double.valueOf(numberFormat.parse(value).doubleValue());
/*    */       } else {
/* 79 */         numberValue = new Integer(value);
/*    */       } 
/* 81 */       Integer filterValue = new Integer(this.filter);
/* 82 */       return (numberValue.intValue() <= filterValue.intValue());
/* 84 */     } catch (Exception e) {
/* 85 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 92 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 96 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\LessThanFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */