/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.HashMap;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class NumericConverterFactory implements ConverterFactory {
/*     */   public Converter createConverter(Class<?> source, Class target, Hints hints) {
/*  53 */     source = primitiveToWrapperClass(source);
/*  54 */     target = primitiveToWrapperClass(target);
/*  58 */     if (!Number.class.isAssignableFrom(source) && !String.class.isAssignableFrom(source))
/*  59 */       return null; 
/*  62 */     if (Long.class.equals(target) || Integer.class.equals(target) || Short.class.equals(target) || Byte.class.equals(target) || BigInteger.class.equals(target) || BigDecimal.class.equals(target) || Double.class.equals(target) || Float.class.equals(target) || Number.class.equals(target)) {
/*  68 */       if (hints != null) {
/*  69 */         Object safeConversion = hints.get(ConverterFactory.SAFE_CONVERSION);
/*  70 */         if (safeConversion instanceof Boolean && ((Boolean)safeConversion).booleanValue())
/*  71 */           return new SafeNumericConverter(); 
/*     */       } 
/*  74 */       return new NumericConverter();
/*     */     } 
/*  77 */     return null;
/*     */   }
/*     */   
/*     */   class SafeNumericConverter implements Converter {
/*     */     public <T> T convert(Object source, Class<T> target) throws Exception {
/*  82 */       target = NumericConverterFactory.primitiveToWrapperClass(target);
/*  83 */       if (source instanceof Number) {
/*  84 */         Number number = (Number)source;
/*  85 */         Class<?> c = number.getClass();
/*  87 */         if (BigDecimal.class.equals(target))
/*  88 */           return (T)new BigDecimal(source.toString()); 
/*  90 */         if (Double.class.equals(target)) {
/*  91 */           if (c != BigDecimal.class && c != BigInteger.class) {
/*  92 */             if (c == Float.class)
/*  94 */               return (T)new Double(number.toString()); 
/*  97 */             return (T)Double.valueOf(number.doubleValue());
/*     */           } 
/* 101 */         } else if (Float.class.equals(target)) {
/* 102 */           if (c == Float.class || c == Integer.class || c == Short.class || c == Byte.class)
/* 103 */             return (T)Float.valueOf(number.floatValue()); 
/* 107 */         } else if (BigInteger.class.equals(target)) {
/* 108 */           if (BigInteger.class.isAssignableFrom(c) || c == Long.class || c == Integer.class || c == Short.class || c == Byte.class)
/* 110 */             return (T)new BigInteger(number.toString()); 
/* 114 */         } else if (Long.class.equals(target)) {
/* 115 */           if (c == Long.class || c == Integer.class || c == Short.class || c == Byte.class)
/* 116 */             return (T)Long.valueOf(number.longValue()); 
/* 120 */         } else if (Integer.class.equals(target)) {
/* 121 */           if (c == Integer.class || c == Short.class || c == Byte.class)
/* 122 */             return (T)Integer.valueOf(number.intValue()); 
/* 126 */         } else if (Short.class.equals(target)) {
/* 127 */           if (c == Short.class || c == Byte.class)
/* 128 */             return (T)Short.valueOf(number.shortValue()); 
/* 132 */         } else if (Byte.class.equals(target) && 
/* 133 */           c == Byte.class) {
/* 134 */           return (T)Byte.valueOf(number.byteValue());
/*     */         } 
/* 139 */       } else if (source instanceof String) {
/* 140 */         String src = (String)source;
/*     */         try {
/* 142 */           if (BigDecimal.class.isAssignableFrom(target))
/* 143 */             return (T)new BigDecimal(src); 
/* 147 */           if (target == Double.class) {
/* 148 */             Double x = new Double(src);
/* 149 */             if (x.toString().equals(src))
/* 150 */               return (T)x; 
/* 152 */           } else if (target == Float.class) {
/* 153 */             Float x = new Float(src);
/* 154 */             if (x.toString().equals(src))
/* 155 */               return (T)x; 
/* 157 */           } else if (BigInteger.class.isAssignableFrom(target)) {
/* 158 */             BigInteger x = new BigInteger(src);
/* 159 */             if (x.toString().equals(src))
/* 160 */               return (T)x; 
/* 162 */           } else if (target == Long.class) {
/* 163 */             Long x = new Long(src);
/* 164 */             if (x.toString().equals(src))
/* 165 */               return (T)x; 
/* 167 */           } else if (target == Integer.class) {
/* 168 */             Integer x = new Integer(src);
/* 169 */             if (x.toString().equals(src))
/* 170 */               return (T)x; 
/* 172 */           } else if (target == Short.class) {
/* 173 */             Short x = new Short(src);
/* 174 */             if (x.toString().equals(src))
/* 175 */               return (T)x; 
/* 177 */           } else if (target == Byte.class) {
/* 178 */             Byte x = new Byte(src);
/* 179 */             if (x.toString().equals(src))
/* 180 */               return (T)x; 
/*     */           } 
/* 183 */         } catch (Exception ex) {
/* 184 */           return null;
/*     */         } 
/*     */       } 
/* 188 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   class NumericConverter implements Converter {
/*     */     public Object convert(Object source, Class target) throws Exception {
/* 195 */       target = NumericConverterFactory.primitiveToWrapperClass(target);
/* 196 */       if (source instanceof Number) {
/* 197 */         Number s = (Number)source;
/* 200 */         if (Long.class.equals(target))
/* 201 */           return new Long(s.longValue()); 
/* 203 */         if (Integer.class.equals(target))
/* 204 */           return new Integer(s.intValue()); 
/* 206 */         if (Short.class.equals(target))
/* 207 */           return new Short(s.shortValue()); 
/* 209 */         if (Byte.class.equals(target))
/* 210 */           return new Byte(s.byteValue()); 
/* 212 */         if (BigInteger.class.equals(target))
/* 213 */           return BigInteger.valueOf(s.longValue()); 
/* 220 */         if (Double.class.equals(target))
/* 221 */           return new Double(s.toString()); 
/* 223 */         if (Float.class.equals(target))
/* 224 */           return new Float(s.toString()); 
/* 226 */         if (BigDecimal.class.equals(target))
/* 227 */           return new BigDecimal(s.toString()); 
/* 230 */         if (Number.class.equals(target))
/*     */           try {
/* 232 */             return new Integer(s.toString());
/* 233 */           } catch (Exception e) {
/*     */             try {
/* 237 */               return new BigInteger(s.toString());
/* 238 */             } catch (Exception exception) {
/*     */               try {
/* 242 */                 return new Double(s.toString());
/* 243 */               } catch (Exception exception1) {
/*     */                 try {
/* 247 */                   return new BigDecimal(s.toString());
/* 248 */                 } catch (Exception exception2) {}
/*     */               } 
/*     */             } 
/*     */           }  
/* 251 */       } else if (source instanceof String) {
/* 252 */         String s = (String)source;
/* 254 */         s = s.trim();
/* 256 */         String integral = NumericConverterFactory.toIntegral(s);
/* 259 */         if (Double.class.equals(target))
/* 260 */           return new Double(s); 
/* 262 */         if (Float.class.equals(target))
/* 263 */           return new Float(s); 
/* 265 */         if (BigDecimal.class.equals(target))
/* 266 */           return new BigDecimal(s); 
/* 270 */         if (Long.class.equals(target))
/* 271 */           return new Long(integral); 
/* 273 */         if (Integer.class.equals(target))
/* 274 */           return new Integer(integral); 
/* 276 */         if (Short.class.equals(target))
/* 277 */           return new Short(integral); 
/* 279 */         if (Byte.class.equals(target))
/* 280 */           return new Byte(integral); 
/* 282 */         if (BigInteger.class.equals(target))
/* 283 */           return new BigInteger(integral); 
/* 287 */         if (Number.class.equals(target)) {
/* 288 */           if (integral.equals(s))
/*     */             try {
/* 291 */               return new Integer(integral);
/* 292 */             } catch (Exception e) {
/*     */               try {
/* 296 */                 return new BigInteger(integral);
/* 297 */               } catch (Exception exception) {}
/*     */             }  
/*     */           try {
/* 301 */             return new Double(s);
/* 302 */           } catch (Exception e) {
/*     */             try {
/* 306 */               return new BigDecimal(s);
/* 307 */             } catch (Exception exception) {}
/*     */           } 
/*     */         } 
/*     */       } 
/* 312 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static String toIntegral(String s) {
/* 326 */     int radex = -1;
/* 327 */     for (int i = s.length() - 1; i > 0; i--) {
/* 328 */       char ch = s.charAt(i);
/* 329 */       if (!Character.isDigit(ch) && '-' != ch) {
/* 330 */         radex = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 334 */     if (radex != -1)
/* 336 */       return s.substring(0, radex); 
/* 338 */     return s;
/*     */   }
/*     */   
/* 342 */   static HashMap<Class, Class> primitiveToWrapper = new HashMap<Class<?>, Class<?>>();
/*     */   
/*     */   static {
/* 344 */     primitiveToWrapper.put(byte.class, Byte.class);
/* 345 */     primitiveToWrapper.put(short.class, Short.class);
/* 346 */     primitiveToWrapper.put(int.class, Integer.class);
/* 347 */     primitiveToWrapper.put(long.class, Long.class);
/* 348 */     primitiveToWrapper.put(float.class, Float.class);
/* 349 */     primitiveToWrapper.put(double.class, Double.class);
/* 350 */     primitiveToWrapper.put(boolean.class, Boolean.class);
/*     */   }
/*     */   
/*     */   static Class primitiveToWrapperClass(Class primitive) {
/* 353 */     if (primitive.isPrimitive()) {
/* 354 */       Class wrapper = primitiveToWrapper.get(primitive);
/* 355 */       primitive = (wrapper != null) ? wrapper : primitive;
/*     */     } 
/* 357 */     return primitive;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\NumericConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */