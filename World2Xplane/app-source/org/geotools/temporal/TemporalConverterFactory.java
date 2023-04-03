/*    */ package org.geotools.temporal;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.geotools.factory.Hints;
/*    */ import org.geotools.temporal.object.DefaultInstant;
/*    */ import org.geotools.temporal.object.DefaultPosition;
/*    */ import org.geotools.util.Converter;
/*    */ import org.geotools.util.ConverterFactory;
/*    */ import org.geotools.util.Converters;
/*    */ import org.opengis.temporal.Instant;
/*    */ import org.opengis.temporal.Position;
/*    */ 
/*    */ public class TemporalConverterFactory implements ConverterFactory {
/* 41 */   static Converter dateToInstant = new Converter() {
/*    */       public <T> T convert(Object source, Class<T> target) throws Exception {
/* 43 */         return (T)new DefaultInstant((Position)new DefaultPosition((Date)source));
/*    */       }
/*    */     };
/*    */   
/* 47 */   static Converter stringToInstant = new Converter() {
/*    */       public <T> T convert(Object source, Class<T> target) throws Exception {
/* 51 */         Date d = (Date)Converters.convert(source, Date.class);
/* 54 */         return (d != null) ? (T)TemporalConverterFactory.dateToInstant.convert(d, target) : null;
/*    */       }
/*    */     };
/*    */   
/*    */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 60 */     if (Instant.class.isAssignableFrom(target)) {
/* 61 */       if (Date.class.isAssignableFrom(source))
/* 62 */         return dateToInstant; 
/* 65 */       if (String.class.equals(source))
/* 66 */         return stringToInstant; 
/*    */     } 
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\TemporalConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */