/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class BooleanConverterFactory implements ConverterFactory {
/*    */   public Converter createConverter(Class source, Class target, Hints hints) {
/* 44 */     if (target.equals(Boolean.class)) {
/* 47 */       if (source.equals(String.class))
/* 48 */         return new Converter() {
/*    */             public Object convert(Object source, Class target) throws Exception {
/* 51 */               if ("true".equals(source) || "1".equals(source))
/* 52 */                 return Boolean.TRUE; 
/* 54 */               if ("false".equals(source) || "0".equals(source))
/* 55 */                 return Boolean.FALSE; 
/* 58 */               return null;
/*    */             }
/*    */           }; 
/* 65 */       if (source.equals(Integer.class))
/* 66 */         return new Converter() {
/*    */             public Object convert(Object source, Class target) throws Exception {
/* 69 */               if ((new Integer(1)).equals(source))
/* 70 */                 return Boolean.TRUE; 
/* 72 */               if ((new Integer(0)).equals(source))
/* 73 */                 return Boolean.FALSE; 
/* 76 */               return null;
/*    */             }
/*    */           }; 
/*    */     } 
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\BooleanConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */