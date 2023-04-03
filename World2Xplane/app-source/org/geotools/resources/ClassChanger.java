/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public abstract class ClassChanger<S extends Comparable<S>, T extends Number> {
/*  48 */   private static final Class<? extends Number>[] TYPES_BY_SIZE = new Class[] { Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class };
/*     */   
/*  63 */   private static ClassChanger<?, ?>[] changers = new ClassChanger[] { new ClassChanger<Date, Long>(Date.class, Long.class) {
/*     */         protected Long convert(Date object) {
/*  66 */           return Long.valueOf(object.getTime());
/*     */         }
/*     */         
/*     */         protected Date inverseConvert(Long value) {
/*  70 */           return new Date(value.longValue());
/*     */         }
/*     */       } };
/*     */   
/*     */   private final Class<S> source;
/*     */   
/*     */   private final Class<T> target;
/*     */   
/*     */   protected ClassChanger(Class<S> source, Class<T> target) {
/*  92 */     this.source = source;
/*  93 */     this.target = target;
/*  94 */     if (!Comparable.class.isAssignableFrom(source))
/*  95 */       throw new IllegalArgumentException(String.valueOf(source)); 
/*  97 */     if (!Number.class.isAssignableFrom(target))
/*  98 */       throw new IllegalArgumentException(String.valueOf(target)); 
/*     */   }
/*     */   
/*     */   protected abstract T convert(S paramS) throws ClassCastException;
/*     */   
/*     */   protected abstract S inverseConvert(T paramT);
/*     */   
/*     */   public String toString() {
/* 124 */     return "ClassChanger[" + this.source.getName() + " ⇨ " + this.target.getName() + ']';
/*     */   }
/*     */   
/*     */   public static synchronized void register(ClassChanger<?, ?> converter) throws IllegalStateException {
/*     */     int i;
/* 154 */     for (i = 0; i < changers.length; i++) {
/* 155 */       if ((changers[i]).source.isAssignableFrom(converter.source)) {
/* 161 */         for (int j = i; j < changers.length; j++) {
/* 162 */           if ((changers[j]).source.equals(converter.source))
/* 163 */             throw new IllegalStateException(changers[j].toString()); 
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/* 169 */     changers = XArray.<ClassChanger<?, ?>>insert(changers, i, 1);
/* 170 */     changers[i] = converter;
/*     */   }
/*     */   
/*     */   private static synchronized <S extends Comparable<S>> ClassChanger<S, ?> getClassChanger(Class<S> source) throws ClassNotFoundException {
/* 183 */     for (int i = 0; i < changers.length; i++) {
/* 184 */       ClassChanger<?, ?> candidate = changers[i];
/* 185 */       if (candidate.source.isAssignableFrom(source))
/* 187 */         return (ClassChanger)candidate; 
/*     */     } 
/* 191 */     throw new ClassNotFoundException(source.getName());
/*     */   }
/*     */   
/*     */   public static synchronized Class<?> getTransformedClass(Class<?> source) {
/* 202 */     if (source != null)
/* 203 */       for (int i = 0; i < changers.length; i++) {
/* 204 */         if ((changers[i]).source.isAssignableFrom(source))
/* 205 */           return (changers[i]).target; 
/*     */       }  
/* 209 */     return source;
/*     */   }
/*     */   
/*     */   public static Number toNumber(Comparable<?> object) throws ClassNotFoundException {
/* 225 */     if (object != null) {
/* 226 */       if (object instanceof Number)
/* 227 */         return (Number)object; 
/* 229 */       ClassChanger<?, ?> changer = getClassChanger(object.getClass());
/* 230 */       return (Number)changer.convert(object);
/*     */     } 
/* 232 */     return null;
/*     */   }
/*     */   
/*     */   public static <C extends Comparable> C toComparable(Number value, Class<C> classe) throws ClassNotFoundException {
/* 251 */     if (value != null) {
/* 252 */       if (Number.class.isAssignableFrom(classe))
/* 253 */         return classe.cast(value); 
/* 255 */       ClassChanger<C, ?> changer = getClassChanger(classe);
/* 256 */       Comparable<?> c = (Comparable<?>)changer.inverseConvert(value);
/* 257 */       return classe.cast(c);
/*     */     } 
/* 259 */     return null;
/*     */   }
/*     */   
/*     */   public static Class<?> toPrimitive(Class<?> c) throws IllegalArgumentException {
/* 271 */     if (Double.class.equals(c))
/* 271 */       return double.class; 
/* 272 */     if (Float.class.equals(c))
/* 272 */       return float.class; 
/* 273 */     if (Long.class.equals(c))
/* 273 */       return long.class; 
/* 274 */     if (Integer.class.equals(c))
/* 274 */       return int.class; 
/* 275 */     if (Short.class.equals(c))
/* 275 */       return short.class; 
/* 276 */     if (Byte.class.equals(c))
/* 276 */       return byte.class; 
/* 277 */     if (Boolean.class.equals(c))
/* 277 */       return boolean.class; 
/* 278 */     if (Character.class.equals(c))
/* 278 */       return char.class; 
/* 279 */     throw unknownType(c);
/*     */   }
/*     */   
/*     */   public static Class<?> toWrapper(Class<?> c) throws IllegalArgumentException {
/* 291 */     if (double.class.equals(c))
/* 291 */       return Double.class; 
/* 292 */     if (float.class.equals(c))
/* 292 */       return Float.class; 
/* 293 */     if (long.class.equals(c))
/* 293 */       return Long.class; 
/* 294 */     if (int.class.equals(c))
/* 294 */       return Integer.class; 
/* 295 */     if (short.class.equals(c))
/* 295 */       return Short.class; 
/* 296 */     if (byte.class.equals(c))
/* 296 */       return Byte.class; 
/* 297 */     if (boolean.class.equals(c))
/* 297 */       return Boolean.class; 
/* 298 */     if (char.class.equals(c))
/* 298 */       return Character.class; 
/* 299 */     throw unknownType(c);
/*     */   }
/*     */   
/*     */   public static <N extends Number> N cast(Number n, Class<N> c) throws IllegalArgumentException {
/* 310 */     if (n == null || n.getClass().equals(c))
/* 311 */       return (N)n; 
/* 313 */     if (Byte.class.equals(c))
/* 313 */       return (N)Byte.valueOf(n.byteValue()); 
/* 314 */     if (Short.class.equals(c))
/* 314 */       return (N)Short.valueOf(n.shortValue()); 
/* 315 */     if (Integer.class.equals(c))
/* 315 */       return (N)Integer.valueOf(n.intValue()); 
/* 316 */     if (Long.class.equals(c))
/* 316 */       return (N)Long.valueOf(n.longValue()); 
/* 317 */     if (Float.class.equals(c))
/* 317 */       return (N)Float.valueOf(n.floatValue()); 
/* 318 */     if (Double.class.equals(c))
/* 318 */       return (N)Double.valueOf(n.doubleValue()); 
/* 319 */     throw unknownType(c);
/*     */   }
/*     */   
/*     */   public static Class<? extends Number> getWidestClass(Number n1, Number n2) throws IllegalArgumentException {
/* 330 */     return getWidestClass((n1 != null) ? (Class)n1.getClass() : null, (n2 != null) ? (Class)n2.getClass() : null);
/*     */   }
/*     */   
/*     */   public static Class<? extends Number> getWidestClass(Class<? extends Number> c1, Class<? extends Number> c2) throws IllegalArgumentException {
/* 343 */     if (c1 == null)
/* 343 */       return c2; 
/* 344 */     if (c2 == null)
/* 344 */       return c1; 
/* 345 */     return TYPES_BY_SIZE[Math.max(getRank(c1), getRank(c2))];
/*     */   }
/*     */   
/*     */   public static Class<? extends Number> getFinestClass(Class<? extends Number> c1, Class<? extends Number> c2) throws IllegalArgumentException {
/* 357 */     if (c1 == null)
/* 357 */       return c2; 
/* 358 */     if (c2 == null)
/* 358 */       return c1; 
/* 359 */     return TYPES_BY_SIZE[Math.min(getRank(c1), getRank(c2))];
/*     */   }
/*     */   
/*     */   public static Class<? extends Number> getFinestClass(double value) {
/* 366 */     long lg = (long)value;
/* 367 */     if (value == lg) {
/* 368 */       if (lg >= -128L && lg <= 127L)
/* 368 */         return (Class)Byte.class; 
/* 369 */       if (lg >= -32768L && lg <= 32767L)
/* 369 */         return (Class)Short.class; 
/* 370 */       if (lg >= -2147483648L && lg <= 2147483647L)
/* 370 */         return (Class)Integer.class; 
/* 371 */       if (lg >= Long.MIN_VALUE && lg <= Long.MAX_VALUE)
/* 371 */         return (Class)Long.class; 
/*     */     } 
/* 373 */     float fv = (float)value;
/* 374 */     if (value == fv)
/* 375 */       return (Class)Float.class; 
/* 377 */     return (Class)Double.class;
/*     */   }
/*     */   
/*     */   private static int getRank(Class<? extends Number> c) throws IllegalArgumentException {
/* 384 */     for (int i = 0; i < TYPES_BY_SIZE.length; i++) {
/* 385 */       if (TYPES_BY_SIZE[i].isAssignableFrom(c))
/* 386 */         return i; 
/*     */     } 
/* 389 */     throw unknownType(c);
/*     */   }
/*     */   
/*     */   private static IllegalArgumentException unknownType(Class<?> type) {
/* 396 */     return new IllegalArgumentException(Errors.format(187, type));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\ClassChanger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */