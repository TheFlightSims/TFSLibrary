/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ 
/*    */ public final class CalendarConverter extends DateTimeConverter {
/*    */   public CalendarConverter() {}
/*    */   
/*    */   public CalendarConverter(Object defaultValue) {
/* 55 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Calendar.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\CalendarConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */