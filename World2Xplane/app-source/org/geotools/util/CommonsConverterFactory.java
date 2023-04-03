/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.TimeZone;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class CommonsConverterFactory implements ConverterFactory {
/*     */   static class URIConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/*  54 */       if (source == null)
/*  54 */         return null; 
/*  55 */       String string = (String)source;
/*     */       try {
/*  57 */         URI uri = new URI(string);
/*  58 */         return target.cast(uri);
/*  60 */       } catch (URISyntaxException e) {
/*  61 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class NumberConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/*  66 */       if (source == null)
/*  66 */         return null; 
/*  67 */       String string = (String)source;
/*  68 */       Number parsed = null;
/*     */       try {
/*  70 */         parsed = (new CommonsConverterFactory.IntegerConverter()).<Number>convert(string, (Class)Integer.class);
/*  72 */       } catch (Exception e) {}
/*  73 */       if (parsed == null)
/*  74 */         parsed = (new CommonsConverterFactory.DoubleConverter()).<Number>convert(string, (Class)Double.class); 
/*  76 */       return target.cast(parsed);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ByteConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/*  81 */       if (source == null)
/*  81 */         return null; 
/*  82 */       String string = (String)source;
/*     */       try {
/*  84 */         Byte parsed = new Byte(string);
/*  85 */         return target.cast(parsed);
/*  86 */       } catch (Exception e) {
/*  87 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class ShortConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/*  93 */       if (source == null)
/*  93 */         return null; 
/*  94 */       String string = (String)source;
/*     */       try {
/*  96 */         Short parsed = new Short(string);
/*  97 */         return target.cast(parsed);
/*  98 */       } catch (Exception e) {
/*  99 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class IntegerConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 105 */       if (source == null)
/* 105 */         return null; 
/* 106 */       String string = (String)source;
/*     */       try {
/* 108 */         Integer parsed = new Integer(string);
/* 109 */         return target.cast(parsed);
/* 110 */       } catch (Exception e) {
/* 111 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class LongConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 117 */       if (source == null)
/* 117 */         return null; 
/* 118 */       String string = (String)source;
/*     */       try {
/* 120 */         Long parsed = new Long(string);
/* 121 */         return target.cast(parsed);
/* 122 */       } catch (Exception e) {
/* 123 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class BigIntegerConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 129 */       if (source == null)
/* 129 */         return null; 
/* 130 */       String string = (String)source;
/*     */       try {
/* 132 */         BigInteger parsed = new BigInteger(string);
/* 133 */         return target.cast(parsed);
/* 134 */       } catch (Exception e) {
/* 135 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class FloatConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 141 */       if (source == null)
/* 141 */         return null; 
/* 142 */       String string = (String)source;
/*     */       try {
/* 144 */         Float parsed = new Float(string);
/* 145 */         return target.cast(parsed);
/* 146 */       } catch (Exception e) {
/* 147 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class DoubleConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 153 */       if (source == null)
/* 153 */         return null; 
/* 154 */       String string = (String)source;
/*     */       try {
/* 156 */         Double parsed = new Double(string);
/* 157 */         return target.cast(parsed);
/* 158 */       } catch (Exception e) {
/* 159 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class BigDecimalConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 165 */       if (source == null)
/* 165 */         return null; 
/* 166 */       String string = (String)source;
/*     */       try {
/* 168 */         BigDecimal parsed = new BigDecimal(string);
/* 169 */         return target.cast(parsed);
/* 170 */       } catch (Exception e) {
/* 171 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class BooleanConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 182 */       if (source == null)
/* 182 */         return null; 
/* 183 */       String string = (String)source;
/* 194 */       if (string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("y") || string.equalsIgnoreCase("true") || string.equalsIgnoreCase("on") || string.equalsIgnoreCase("1"))
/* 199 */         return target.cast(Boolean.TRUE); 
/* 200 */       if (string.equalsIgnoreCase("no") || string.equalsIgnoreCase("n") || string.equalsIgnoreCase("false") || string.equalsIgnoreCase("off") || string.equalsIgnoreCase("0"))
/* 205 */         return target.cast(Boolean.FALSE); 
/* 207 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class CharacterConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 213 */       if (source == null)
/* 213 */         return null; 
/* 214 */       String string = (String)source;
/* 215 */       if (string.length() > 0)
/* 216 */         return target.cast(Character.valueOf(string.charAt(0))); 
/* 218 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class DateConverter implements Converter {
/* 223 */     private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S a");
/*     */     
/* 224 */     private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssa");
/*     */     
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 227 */       if (source == null)
/* 227 */         return null; 
/* 228 */       String string = (String)source;
/*     */       try {
/* 231 */         Date parsed = format1.parse(string);
/* 232 */         return target.cast(parsed);
/* 234 */       } catch (Exception ignore) {
/*     */         try {
/* 237 */           Date parsed = format2.parse(string);
/* 238 */           return target.cast(parsed);
/* 240 */         } catch (Exception exception) {
/* 242 */           return null;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class URLConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 248 */       if (source == null)
/* 248 */         return null; 
/* 249 */       String string = (String)source;
/*     */       try {
/* 251 */         URL parsed = new URL(string);
/* 252 */         return target.cast(parsed);
/* 253 */       } catch (Exception e) {
/* 254 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class SQLDateConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 261 */       if (source == null)
/* 261 */         return null; 
/* 262 */       String string = (String)source;
/*     */       try {
/* 264 */         Date parsed = Date.valueOf(string);
/* 265 */         return target.cast(parsed);
/* 266 */       } catch (Exception e) {
/* 267 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class SQLTimeConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 274 */       if (source == null)
/* 274 */         return null; 
/* 275 */       String string = (String)source;
/*     */       try {
/* 277 */         Time parsed = Time.valueOf(string);
/* 278 */         return target.cast(parsed);
/* 279 */       } catch (Exception e) {
/* 280 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class SQLTimestampConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 286 */       if (source == null)
/* 286 */         return null; 
/* 287 */       String string = (String)source;
/*     */       try {
/* 289 */         Timestamp parsed = Timestamp.valueOf(string);
/* 290 */         return target.cast(parsed);
/* 291 */       } catch (Exception e) {
/* 292 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class FileConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 298 */       if (source == null)
/* 298 */         return null; 
/* 299 */       String string = (String)source;
/*     */       try {
/* 301 */         File parsed = new File(string);
/* 302 */         return target.cast(parsed);
/* 303 */       } catch (Exception e) {
/* 304 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class TimeZoneConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/* 310 */       if (source == null)
/* 310 */         return null; 
/* 311 */       String string = (String)source;
/* 312 */       TimeZone timezone = TimeZone.getTimeZone(string);
/* 313 */       if (!string.equals(timezone.getID()))
/* 316 */         return null; 
/* 318 */       return target.cast(timezone);
/*     */     }
/*     */   }
/*     */   
/* 324 */   private static HashMap<Class<?>, Converter> register = new HashMap<Class<?>, Converter>();
/*     */   
/*     */   static {
/* 326 */     register.put(URI.class, new URIConverter());
/* 327 */     register.put(Number.class, new NumberConverter());
/* 330 */     register.put(Byte.class, new ByteConverter());
/* 331 */     register.put(byte.class, new ByteConverter());
/* 332 */     register.put(Short.class, new ShortConverter());
/* 333 */     register.put(short.class, new ShortConverter());
/* 334 */     register.put(Integer.class, new IntegerConverter());
/* 335 */     register.put(int.class, new IntegerConverter());
/* 336 */     register.put(Long.class, new LongConverter());
/* 337 */     register.put(long.class, new LongConverter());
/* 338 */     register.put(BigInteger.class, new BigIntegerConverter());
/* 339 */     register.put(Float.class, new FloatConverter());
/* 340 */     register.put(float.class, new FloatConverter());
/* 341 */     register.put(Double.class, new DoubleConverter());
/* 342 */     register.put(double.class, new DoubleConverter());
/* 343 */     register.put(BigDecimal.class, new BigDecimalConverter());
/* 344 */     register.put(Boolean.class, new BooleanConverter());
/* 345 */     register.put(boolean.class, new BooleanConverter());
/* 346 */     register.put(Character.class, new CharacterConverter());
/* 347 */     register.put(char.class, new CharacterConverter());
/* 352 */     register.put(File.class, new FileConverter());
/* 353 */     register.put(URL.class, new URLConverter());
/* 354 */     register.put(Date.class, new SQLDateConverter());
/* 355 */     register.put(Time.class, new SQLTimeConverter());
/* 356 */     register.put(Timestamp.class, new SQLTimestampConverter());
/* 357 */     register.put(TimeZone.class, new TimeZoneConverter());
/* 359 */     register.put(Date.class, new DateConverter());
/*     */   }
/*     */   
/*     */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 371 */     if (source == null || !source.equals(String.class))
/* 372 */       return null; 
/* 374 */     Converter converter = register.get(target);
/* 375 */     if (converter != null)
/* 376 */       return converter; 
/* 378 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\CommonsConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */