/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.sql.Timestamp;
/*    */ import java.text.DateFormat;
/*    */ import java.util.Locale;
/*    */ import java.util.TimeZone;
/*    */ 
/*    */ public final class SqlTimestampConverter extends DateTimeConverter {
/*    */   public SqlTimestampConverter() {}
/*    */   
/*    */   public SqlTimestampConverter(Object defaultValue) {
/* 59 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 69 */     return Timestamp.class;
/*    */   }
/*    */   
/*    */   protected DateFormat getFormat(Locale locale, TimeZone timeZone) {
/* 81 */     DateFormat format = null;
/* 82 */     if (locale == null) {
/* 83 */       format = DateFormat.getDateTimeInstance(3, 3);
/*    */     } else {
/* 85 */       format = DateFormat.getDateTimeInstance(3, 3, locale);
/*    */     } 
/* 87 */     if (timeZone != null)
/* 88 */       format.setTimeZone(timeZone); 
/* 90 */     return format;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\SqlTimestampConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */