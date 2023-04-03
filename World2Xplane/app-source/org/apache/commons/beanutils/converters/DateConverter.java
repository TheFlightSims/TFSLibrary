/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class DateConverter extends DateTimeConverter {
/*    */   public DateConverter() {}
/*    */   
/*    */   public DateConverter(Object defaultValue) {
/* 55 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Date.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\DateConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */