/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.net.URL;
/*    */ 
/*    */ public final class URLConverter extends AbstractConverter {
/*    */   public URLConverter() {}
/*    */   
/*    */   public URLConverter(Object defaultValue) {
/* 50 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 60 */     return URL.class;
/*    */   }
/*    */   
/*    */   protected Object convertToType(Class type, Object value) throws Throwable {
/* 73 */     return new URL(value.toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\URLConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */