/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public final class FileConverter extends AbstractConverter {
/*    */   public FileConverter() {}
/*    */   
/*    */   public FileConverter(Object defaultValue) {
/* 51 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 61 */     return File.class;
/*    */   }
/*    */   
/*    */   protected Object convertToType(Class type, Object value) throws Throwable {
/* 74 */     return new File(value.toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\FileConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */