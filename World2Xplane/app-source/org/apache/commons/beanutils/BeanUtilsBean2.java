/*    */ package org.apache.commons.beanutils;
/*    */ 
/*    */ public class BeanUtilsBean2 extends BeanUtilsBean {
/*    */   public BeanUtilsBean2() {
/* 61 */     super(new ConvertUtilsBean2());
/*    */   }
/*    */   
/*    */   protected Object convert(Object value, Class type) {
/* 73 */     return getConvertUtils().convert(value, type);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanUtilsBean2.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */