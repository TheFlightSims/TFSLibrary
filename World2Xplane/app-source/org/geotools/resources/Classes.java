/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.lang.reflect.WildcardType;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public final class Classes {
/*     */   public static final byte DOUBLE = 8;
/*     */   
/*     */   public static final byte FLOAT = 7;
/*     */   
/*     */   public static final byte LONG = 6;
/*     */   
/*     */   public static final byte INTEGER = 5;
/*     */   
/*     */   public static final byte SHORT = 4;
/*     */   
/*     */   public static final byte BYTE = 3;
/*     */   
/*     */   public static final byte CHARACTER = 2;
/*     */   
/*     */   public static final byte BOOLEAN = 1;
/*     */   
/*     */   public static final byte OTHER = 0;
/*     */   
/*  54 */   private static final Map<Class<?>, Classes> MAPPING = new HashMap<Class<?>, Classes>(16);
/*     */   
/*     */   private final Class<?> primitive;
/*     */   
/*     */   private final Class<?> wrapper;
/*     */   
/*     */   private final boolean isFloat;
/*     */   
/*     */   private final boolean isInteger;
/*     */   
/*     */   private final byte size;
/*     */   
/*     */   private final byte ordinal;
/*     */   
/*     */   static {
/*  56 */     new Classes(double.class, Double.class, true, false, (byte)64, (byte)8);
/*  57 */     new Classes(float.class, Float.class, true, false, (byte)32, (byte)7);
/*  58 */     new Classes(long.class, Long.class, false, true, (byte)64, (byte)6);
/*  59 */     new Classes(int.class, Integer.class, false, true, (byte)32, (byte)5);
/*  60 */     new Classes(short.class, Short.class, false, true, (byte)16, (byte)4);
/*  61 */     new Classes(byte.class, Byte.class, false, true, (byte)8, (byte)3);
/*  62 */     new Classes(char.class, Character.class, false, false, (byte)16, (byte)2);
/*  63 */     new Classes(boolean.class, Boolean.class, false, false, (byte)1, (byte)1);
/*  64 */     new Classes(void.class, Void.class, false, false, (byte)0, (byte)0);
/*     */   }
/*     */   
/*     */   private Classes(Class<?> primitive, Class<?> wrapper, boolean isFloat, boolean isInteger, byte size, byte ordinal) {
/*  81 */     this.primitive = primitive;
/*  82 */     this.wrapper = wrapper;
/*  83 */     this.isFloat = isFloat;
/*  84 */     this.isInteger = isInteger;
/*  85 */     this.size = size;
/*  86 */     this.ordinal = ordinal;
/*  87 */     if (MAPPING.put(primitive, this) != null || MAPPING.put(wrapper, this) != null)
/*  88 */       throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   public static Class<?> boundOfParameterizedAttribute(Field field) {
/* 119 */     return getActualTypeArgument(field.getGenericType());
/*     */   }
/*     */   
/*     */   public static Class<?> boundOfParameterizedAttribute(Method method) {
/* 138 */     Class<?> c = getActualTypeArgument(method.getGenericReturnType());
/* 139 */     if (c == null) {
/* 140 */       Type[] parameters = method.getGenericParameterTypes();
/* 141 */       if (parameters != null && parameters.length == 1)
/* 142 */         c = getActualTypeArgument(parameters[0]); 
/*     */     } 
/* 145 */     return c;
/*     */   }
/*     */   
/*     */   private static Class<?> getActualTypeArgument(Type type) {
/* 154 */     if (type instanceof ParameterizedType) {
/* 155 */       Type[] p = ((ParameterizedType)type).getActualTypeArguments();
/* 156 */       while (p != null && p.length == 1) {
/* 157 */         type = p[0];
/* 158 */         if (type instanceof Class)
/* 159 */           return (Class)type; 
/* 160 */         if (type instanceof WildcardType)
/* 161 */           p = ((WildcardType)type).getUpperBounds(); 
/*     */       } 
/*     */     } 
/* 167 */     return null;
/*     */   }
/*     */   
/*     */   public static <T> Class<? extends T> getClass(T object) {
/* 188 */     return (object != null) ? (Class)object.getClass() : null;
/*     */   }
/*     */   
/*     */   private static Set<Class<?>> getClasses(Collection<?> objects) {
/* 195 */     Set<Class<?>> types = new LinkedHashSet<Class<?>>();
/* 196 */     for (Object object : objects) {
/* 197 */       if (object != null)
/* 198 */         types.add(object.getClass()); 
/*     */     } 
/* 201 */     return types;
/*     */   }
/*     */   
/*     */   public static Class<?> specializedClass(Collection<?> objects) {
/* 213 */     Set<Class<?>> types = getClasses(objects);
/* 214 */     Class<?> type = removeAssignables(types);
/* 215 */     return (type != null) ? type : commonSuperClass(types);
/*     */   }
/*     */   
/*     */   public static Class<?> commonClass(Collection<?> objects) {
/* 225 */     Set<Class<?>> types = getClasses(objects);
/* 231 */     label13: for (Class<?> candidate : types) {
/* 232 */       for (Class<?> type : types) {
/* 233 */         if (!candidate.isAssignableFrom(type))
/*     */           continue label13; 
/*     */       } 
/* 237 */       return candidate;
/*     */     } 
/* 239 */     return commonSuperClass(types);
/*     */   }
/*     */   
/*     */   private static Class<?> commonSuperClass(Collection<Class<?>> types) {
/* 248 */     Set<Class<?>> superTypes = new LinkedHashSet<Class<?>>();
/* 249 */     for (Class<?> type : types) {
/*     */       do {
/*     */       
/* 250 */       } while ((type = type.getSuperclass()) != null && 
/* 251 */         superTypes.add(type));
/*     */     } 
/* 258 */     for (Iterator<Class<?>> it = superTypes.iterator(); it.hasNext(); ) {
/* 259 */       Class<?> candidate = it.next();
/* 260 */       for (Class<?> type : types) {
/* 261 */         if (!candidate.isAssignableFrom(type))
/* 262 */           it.remove(); 
/*     */       } 
/*     */     } 
/* 269 */     return removeAssignables(superTypes);
/*     */   }
/*     */   
/*     */   private static Class<?> removeAssignables(Collection<Class<?>> types) {
/* 281 */     for (Iterator<Class<?>> it = types.iterator(); it.hasNext(); ) {
/* 282 */       Class<?> candidate = it.next();
/* 283 */       for (Class<?> type : types) {
/* 284 */         if (candidate != type && candidate.isAssignableFrom(type))
/* 285 */           it.remove(); 
/*     */       } 
/*     */     } 
/* 290 */     return (types.size() == 1) ? types.iterator().next() : null;
/*     */   }
/*     */   
/*     */   public static <T> boolean sameInterfaces(Class<? extends T> object1, Class<? extends T> object2, Class<T> base) {
/* 315 */     if (object1 == object2)
/* 316 */       return true; 
/* 318 */     if (object1 == null || object2 == null)
/* 319 */       return false; 
/* 321 */     Class<?>[] c1 = object1.getInterfaces();
/* 322 */     Class<?>[] c2 = object2.getInterfaces();
/* 328 */     int n = 0;
/*     */     int i;
/* 329 */     for (i = 0; i < c2.length; i++) {
/* 330 */       Class<?> c = c2[i];
/* 331 */       if (base.isAssignableFrom(c))
/* 332 */         c2[n++] = c; 
/*     */     } 
/* 339 */     for (i = 0; i < c1.length; i++) {
/* 340 */       Class<?> c = c1[i];
/* 341 */       if (base.isAssignableFrom(c)) {
/* 342 */         int j = 0;
/*     */         while (true) {
/* 342 */           if (j < n) {
/* 343 */             if (c.equals(c2[j])) {
/* 344 */               System.arraycopy(c2, j + 1, c2, j, --n - j);
/*     */               break;
/*     */             } 
/*     */             j++;
/*     */             continue;
/*     */           } 
/* 348 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 351 */     return (n == 0);
/*     */   }
/*     */   
/*     */   public static boolean isFloat(Class<?> type) {
/* 362 */     Classes mapping = MAPPING.get(type);
/* 363 */     return (mapping != null && mapping.isFloat);
/*     */   }
/*     */   
/*     */   public static boolean isInteger(Class<?> type) {
/* 374 */     Classes mapping = MAPPING.get(type);
/* 375 */     return (mapping != null && mapping.isInteger);
/*     */   }
/*     */   
/*     */   public static int getBitCount(Class<?> type) {
/* 385 */     Classes mapping = MAPPING.get(type);
/* 386 */     return (mapping != null) ? mapping.size : 0;
/*     */   }
/*     */   
/*     */   public static Class<?> primitiveToWrapper(Class<?> type) {
/* 397 */     Classes mapping = MAPPING.get(type);
/* 398 */     return (mapping != null) ? mapping.wrapper : type;
/*     */   }
/*     */   
/*     */   public static Class<?> wrapperToPrimitive(Class<?> type) {
/* 409 */     Classes mapping = MAPPING.get(type);
/* 410 */     return (mapping != null) ? mapping.primitive : type;
/*     */   }
/*     */   
/*     */   public static byte getEnumConstant(Class<?> type) {
/* 422 */     Classes mapping = MAPPING.get(type);
/* 423 */     return (mapping != null) ? mapping.ordinal : 0;
/*     */   }
/*     */   
/*     */   public static <T> T valueOf(Class<T> type, String value) throws IllegalArgumentException, NumberFormatException {
/* 446 */     if (value == null)
/* 447 */       return null; 
/* 449 */     if (Double.class.equals(type))
/* 449 */       return (T)Double.valueOf(value); 
/* 450 */     if (Float.class.equals(type))
/* 450 */       return (T)Float.valueOf(value); 
/* 451 */     if (Long.class.equals(type))
/* 451 */       return (T)Long.valueOf(value); 
/* 452 */     if (Integer.class.equals(type))
/* 452 */       return (T)Integer.valueOf(value); 
/* 453 */     if (Short.class.equals(type))
/* 453 */       return (T)Short.valueOf(value); 
/* 454 */     if (Byte.class.equals(type))
/* 454 */       return (T)Byte.valueOf(value); 
/* 455 */     if (Boolean.class.equals(type))
/* 455 */       return (T)Boolean.valueOf(value); 
/* 456 */     if (Character.class.equals(type))
/* 464 */       return (T)Character.valueOf((value.length() != 0) ? value.charAt(0) : Character.MIN_VALUE); 
/* 466 */     if (String.class.equals(type))
/* 467 */       return (T)value; 
/* 469 */     throw new IllegalArgumentException(Errors.format(187, type));
/*     */   }
/*     */   
/*     */   public static String getShortName(Class<?> classe) {
/* 483 */     if (classe == null)
/* 484 */       return "<*>"; 
/* 486 */     String name = classe.getSimpleName();
/* 487 */     Class<?> enclosing = classe.getEnclosingClass();
/* 488 */     if (enclosing != null) {
/* 489 */       StringBuilder buffer = new StringBuilder();
/*     */       do {
/* 491 */         buffer.insert(0, '.').insert(0, enclosing.getSimpleName());
/* 492 */       } while ((enclosing = enclosing.getEnclosingClass()) != null);
/* 493 */       name = buffer.append(name).toString();
/*     */     } 
/* 495 */     return name;
/*     */   }
/*     */   
/*     */   public static String getShortClassName(Object object) {
/* 507 */     return getShortName(getClass(object));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\Classes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */