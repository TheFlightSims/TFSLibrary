/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class ColorConverterFactory implements ConverterFactory {
/*     */   public Converter createConverter(Class<?> source, Class target, Hints hints) {
/*  47 */     if (target.equals(Color.class)) {
/*  49 */       if (source.equals(String.class))
/*  50 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  52 */               String rgba = (String)source;
/*     */               try {
/*  54 */                 return Color.decode(rgba);
/*  55 */               } catch (NumberFormatException badRGB) {
/*  57 */                 return null;
/*     */               } 
/*     */             }
/*     */           }; 
/*  64 */       if (Number.class.isAssignableFrom(source))
/*  65 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  67 */               Number number = (Number)source;
/*  69 */               if ((int)number.doubleValue() == number.doubleValue() && number.doubleValue() < 2.147483647E9D) {
/*  70 */                 int rgba = number.intValue();
/*  71 */                 int alpha = 0xFF000000 & rgba;
/*  72 */                 return new Color(rgba, (alpha != 0));
/*     */               } 
/*  74 */               return null;
/*     */             }
/*     */           }; 
/*  79 */     } else if (target.equals(String.class) && source.equals(Color.class)) {
/*  80 */       return new Converter() {
/*     */           public <T> T convert(Object source, Class<T> target) throws Exception {
/*  83 */             Color color = (Color)source;
/*  85 */             String redCode = Integer.toHexString(color.getRed());
/*  86 */             String greenCode = Integer.toHexString(color.getGreen());
/*  87 */             String blueCode = Integer.toHexString(color.getBlue());
/*  89 */             if (redCode.length() == 1)
/*  90 */               redCode = "0" + redCode; 
/*  93 */             if (greenCode.length() == 1)
/*  94 */               greenCode = "0" + greenCode; 
/*  97 */             if (blueCode.length() == 1)
/*  98 */               blueCode = "0" + blueCode; 
/* 101 */             return (T)("#" + redCode + greenCode + blueCode).toUpperCase();
/*     */           }
/*     */         };
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ColorConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */