/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import org.apache.commons.beanutils.Converter;
/*    */ 
/*    */ public final class ConverterFacade implements Converter {
/*    */   private final Converter converter;
/*    */   
/*    */   public ConverterFacade(Converter converter) {
/* 44 */     if (converter == null)
/* 45 */       throw new IllegalArgumentException("Converter is missing"); 
/* 47 */     this.converter = converter;
/*    */   }
/*    */   
/*    */   public Object convert(Class type, Object value) {
/* 60 */     return this.converter.convert(type, value);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 71 */     return "ConverterFacade[" + this.converter.toString() + "]";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\ConverterFacade.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */