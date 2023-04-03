/*    */ package org.geotools.util;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.UnsupportedCharsetException;
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class CharsetConverterFactory implements ConverterFactory {
/*    */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 39 */     if (CharSequence.class.isAssignableFrom(source) && Charset.class.isAssignableFrom(target))
/* 41 */       return new Converter() {
/*    */           public <T> T convert(Object source, Class<T> target) throws Exception {
/*    */             try {
/* 44 */               return (T)Charset.forName((String)source);
/* 46 */             } catch (UnsupportedCharsetException e) {
/* 48 */               return null;
/*    */             } 
/*    */           }
/*    */         }; 
/* 53 */     if (Charset.class.isAssignableFrom(source) && CharSequence.class.isAssignableFrom(target))
/* 55 */       return new Converter() {
/*    */           public <T> T convert(Object source, Class<T> target) throws Exception {
/* 57 */             return (T)((Charset)source).toString();
/*    */           }
/*    */         }; 
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CharsetConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */