/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class StringConverter extends AbstractConverter {
/*    */   public StringConverter() {}
/*    */   
/*    */   public StringConverter(Object defaultValue) {
/* 65 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 75 */     return String.class;
/*    */   }
/*    */   
/*    */   protected Object convertToType(Class type, Object value) throws Throwable {
/* 89 */     return value.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\StringConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */