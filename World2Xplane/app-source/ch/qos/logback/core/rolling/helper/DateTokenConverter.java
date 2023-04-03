/*    */ package ch.qos.logback.core.rolling.helper;
/*    */ 
/*    */ import ch.qos.logback.core.pattern.DynamicConverter;
/*    */ import ch.qos.logback.core.util.CachingDateFormatter;
/*    */ import ch.qos.logback.core.util.DatePatternToRegexUtil;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DateTokenConverter<E> extends DynamicConverter<E> implements MonoTypedConverter {
/*    */   public static final String CONVERTER_KEY = "d";
/*    */   
/*    */   public static final String AUXILIARY_TOKEN = "AUX";
/*    */   
/*    */   public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
/*    */   
/*    */   private String datePattern;
/*    */   
/*    */   private CachingDateFormatter cdf;
/*    */   
/*    */   private boolean primary = true;
/*    */   
/*    */   public void start() {
/* 44 */     this.datePattern = getFirstOption();
/* 45 */     if (this.datePattern == null)
/* 46 */       this.datePattern = "yyyy-MM-dd"; 
/* 49 */     List<String> optionList = getOptionList();
/* 50 */     if (optionList != null && optionList.size() > 1) {
/* 51 */       String secondOption = optionList.get(1);
/* 52 */       if ("AUX".equalsIgnoreCase(secondOption))
/* 53 */         this.primary = false; 
/*    */     } 
/* 56 */     this.cdf = new CachingDateFormatter(this.datePattern);
/*    */   }
/*    */   
/*    */   public String convert(Date date) {
/* 60 */     return this.cdf.format(date.getTime());
/*    */   }
/*    */   
/*    */   public String convert(Object o) {
/* 64 */     if (o == null)
/* 65 */       throw new IllegalArgumentException("Null argument forbidden"); 
/* 67 */     if (o instanceof Date)
/* 68 */       return convert((Date)o); 
/* 70 */     throw new IllegalArgumentException("Cannot convert " + o + " of type" + o.getClass().getName());
/*    */   }
/*    */   
/*    */   public String getDatePattern() {
/* 77 */     return this.datePattern;
/*    */   }
/*    */   
/*    */   public boolean isApplicable(Object o) {
/* 81 */     return o instanceof Date;
/*    */   }
/*    */   
/*    */   public String toRegex() {
/* 85 */     DatePatternToRegexUtil datePatternToRegexUtil = new DatePatternToRegexUtil(this.datePattern);
/* 86 */     return datePatternToRegexUtil.toRegex();
/*    */   }
/*    */   
/*    */   public boolean isPrimary() {
/* 90 */     return this.primary;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\helper\DateTokenConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */