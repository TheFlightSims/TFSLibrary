/*    */ package org.geotools.util;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public interface ConverterFactory {
/* 41 */   public static final Hints.Key SAFE_CONVERSION = new Hints.Key(Boolean.class);
/*    */   
/*    */   Converter createConverter(Class<?> paramClass1, Class<?> paramClass2, Hints paramHints);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */