/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.sql.Time;
/*    */ import java.text.DateFormat;
/*    */ import java.util.Locale;
/*    */ import java.util.TimeZone;
/*    */ 
/*    */ public final class SqlTimeConverter extends DateTimeConverter {
/*    */   public SqlTimeConverter() {}
/*    */   
/*    */   public SqlTimeConverter(Object defaultValue) {
/* 59 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 69 */     return Time.class;
/*    */   }
/*    */   
/*    */   protected DateFormat getFormat(Locale locale, TimeZone timeZone) {
/* 81 */     DateFormat format = null;
/* 82 */     if (locale == null) {
/* 83 */       format = DateFormat.getTimeInstance(3);
/*    */     } else {
/* 85 */       format = DateFormat.getTimeInstance(3, locale);
/*    */     } 
/* 87 */     if (timeZone != null)
/* 88 */       format.setTimeZone(timeZone); 
/* 90 */     return format;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\SqlTimeConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */