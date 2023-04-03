/*    */ package org.apache.commons.beanutils;
/*    */ 
/*    */ public class ConvertUtilsBean2 extends ConvertUtilsBean {
/*    */   public String convert(Object value) {
/* 44 */     return (String)convert(value, String.class);
/*    */   }
/*    */   
/*    */   public Object convert(String value, Class clazz) {
/* 58 */     return convert(value, clazz);
/*    */   }
/*    */   
/*    */   public Object convert(String[] value, Class clazz) {
/* 72 */     return convert(value, clazz);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ConvertUtilsBean2.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */