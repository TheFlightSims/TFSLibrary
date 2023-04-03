/*    */ package org.geotools.util;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class UuidConverterFactory implements ConverterFactory {
/*    */   public Converter createConverter(Class source, Class target, Hints hints) {
/* 38 */     if (target.equals(UUID.class)) {
/* 41 */       if (source.equals(String.class))
/* 42 */         return new Converter() {
/*    */             public Object convert(Object source, Class target) throws Exception {
/* 45 */               return UUID.fromString((String)source);
/*    */             }
/*    */           }; 
/* 51 */       if (source.equals(byte[].class))
/* 52 */         return new Converter() {
/*    */             public Object convert(Object source, Class target) throws Exception {
/* 55 */               return UUID.nameUUIDFromBytes((byte[])source);
/*    */             }
/*    */           }; 
/*    */     } 
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\UuidConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */