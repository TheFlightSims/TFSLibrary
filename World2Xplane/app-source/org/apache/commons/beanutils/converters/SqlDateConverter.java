/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.sql.Date;
/*    */ 
/*    */ public final class SqlDateConverter extends DateTimeConverter {
/*    */   public SqlDateConverter() {}
/*    */   
/*    */   public SqlDateConverter(Object defaultValue) {
/* 56 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 66 */     return Date.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\SqlDateConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */