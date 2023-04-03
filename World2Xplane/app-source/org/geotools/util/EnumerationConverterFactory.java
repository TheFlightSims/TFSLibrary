/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class EnumerationConverterFactory implements ConverterFactory {
/*    */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 32 */     if ((String.class.equals(source) && target.isEnum()) || (source.isEnum() && String.class.equals(source)))
/* 34 */       return new EnumConverter(); 
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   private static class EnumConverter implements Converter {
/*    */     private EnumConverter() {}
/*    */     
/*    */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 43 */       if (source instanceof String && target.isEnum())
/* 44 */         return (T)Enum.valueOf((Class)target, (String)source); 
/* 45 */       if (source.getClass().isEnum() && String.class.equals(target))
/* 46 */         return (T)((Enum)source).name(); 
/* 48 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\EnumerationConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */