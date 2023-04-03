/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class CharacterConverter extends AbstractConverter {
/*    */   public CharacterConverter() {}
/*    */   
/*    */   public CharacterConverter(Object defaultValue) {
/* 49 */     super(defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 59 */     return Character.class;
/*    */   }
/*    */   
/*    */   protected String convertToString(Object value) {
/* 70 */     String strValue = value.toString();
/* 71 */     return (strValue.length() == 0) ? "" : strValue.substring(0, 1);
/*    */   }
/*    */   
/*    */   protected Object convertToType(Class type, Object value) throws Exception {
/* 84 */     return new Character(value.toString().charAt(0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\CharacterConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */