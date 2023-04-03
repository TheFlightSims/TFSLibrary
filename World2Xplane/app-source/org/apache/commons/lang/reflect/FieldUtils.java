/*     */ package org.apache.commons.lang.reflect;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ 
/*     */ public class FieldUtils {
/*     */   public static Field getField(Class cls, String fieldName) {
/*  60 */     Field field = getField(cls, fieldName, false);
/*  61 */     MemberUtils.setAccessibleWorkaround(field);
/*  62 */     return field;
/*     */   }
/*     */   
/*     */   public static Field getField(Class cls, String fieldName, boolean forceAccess) {
/*  78 */     if (cls == null)
/*  79 */       throw new IllegalArgumentException("The class must not be null"); 
/*  81 */     if (fieldName == null)
/*  82 */       throw new IllegalArgumentException("The field name must not be null"); 
/*  98 */     for (Class acls = cls; acls != null; acls = acls.getSuperclass()) {
/*     */       try {
/* 100 */         Field field = acls.getDeclaredField(fieldName);
/* 103 */         if (!Modifier.isPublic(field.getModifiers())) {
/* 104 */           if (forceAccess) {
/* 105 */             field.setAccessible(true);
/* 110 */             return field;
/*     */           } 
/*     */         } else {
/* 110 */           return field;
/*     */         } 
/* 111 */       } catch (NoSuchFieldException ex) {}
/*     */     } 
/* 118 */     Field match = null;
/* 119 */     Iterator intf = ClassUtils.getAllInterfaces(cls).iterator();
/* 120 */     while (intf.hasNext()) {
/*     */       try {
/* 122 */         Field test = ((Class)intf.next()).getField(fieldName);
/* 123 */         if (match != null)
/* 124 */           throw new IllegalArgumentException("Reference to field " + fieldName + " is ambiguous relative to " + cls + "; a matching field exists on two or more implemented interfaces."); 
/* 131 */         match = test;
/* 132 */       } catch (NoSuchFieldException ex) {}
/*     */     } 
/* 136 */     return match;
/*     */   }
/*     */   
/*     */   public static Field getDeclaredField(Class cls, String fieldName) {
/* 149 */     return getDeclaredField(cls, fieldName, false);
/*     */   }
/*     */   
/*     */   public static Field getDeclaredField(Class cls, String fieldName, boolean forceAccess) {
/* 164 */     if (cls == null)
/* 165 */       throw new IllegalArgumentException("The class must not be null"); 
/* 167 */     if (fieldName == null)
/* 168 */       throw new IllegalArgumentException("The field name must not be null"); 
/*     */     try {
/* 172 */       Field field = cls.getDeclaredField(fieldName);
/* 173 */       if (!MemberUtils.isAccessible(field))
/* 174 */         if (forceAccess) {
/* 175 */           field.setAccessible(true);
/*     */         } else {
/* 177 */           return null;
/*     */         }  
/* 180 */       return field;
/* 181 */     } catch (NoSuchFieldException e) {
/* 183 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object readStaticField(Field field) throws IllegalAccessException {
/* 194 */     return readStaticField(field, false);
/*     */   }
/*     */   
/*     */   public static Object readStaticField(Field field, boolean forceAccess) throws IllegalAccessException {
/* 207 */     if (field == null)
/* 208 */       throw new IllegalArgumentException("The field must not be null"); 
/* 210 */     if (!Modifier.isStatic(field.getModifiers()))
/* 211 */       throw new IllegalArgumentException("The field '" + field.getName() + "' is not static"); 
/* 213 */     return readField(field, (Object)null, forceAccess);
/*     */   }
/*     */   
/*     */   public static Object readStaticField(Class cls, String fieldName) throws IllegalAccessException {
/* 225 */     return readStaticField(cls, fieldName, false);
/*     */   }
/*     */   
/*     */   public static Object readStaticField(Class cls, String fieldName, boolean forceAccess) throws IllegalAccessException {
/* 240 */     Field field = getField(cls, fieldName, forceAccess);
/* 241 */     if (field == null)
/* 242 */       throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls); 
/* 245 */     return readStaticField(field, false);
/*     */   }
/*     */   
/*     */   public static Object readDeclaredStaticField(Class cls, String fieldName) throws IllegalAccessException {
/* 259 */     return readDeclaredStaticField(cls, fieldName, false);
/*     */   }
/*     */   
/*     */   public static Object readDeclaredStaticField(Class cls, String fieldName, boolean forceAccess) throws IllegalAccessException {
/* 277 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 278 */     if (field == null)
/* 279 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName); 
/* 282 */     return readStaticField(field, false);
/*     */   }
/*     */   
/*     */   public static Object readField(Field field, Object target) throws IllegalAccessException {
/* 294 */     return readField(field, target, false);
/*     */   }
/*     */   
/*     */   public static Object readField(Field field, Object target, boolean forceAccess) throws IllegalAccessException {
/* 308 */     if (field == null)
/* 309 */       throw new IllegalArgumentException("The field must not be null"); 
/* 311 */     if (forceAccess && !field.isAccessible()) {
/* 312 */       field.setAccessible(true);
/*     */     } else {
/* 314 */       MemberUtils.setAccessibleWorkaround(field);
/*     */     } 
/* 316 */     return field.get(target);
/*     */   }
/*     */   
/*     */   public static Object readField(Object target, String fieldName) throws IllegalAccessException {
/* 328 */     return readField(target, fieldName, false);
/*     */   }
/*     */   
/*     */   public static Object readField(Object target, String fieldName, boolean forceAccess) throws IllegalAccessException {
/* 343 */     if (target == null)
/* 344 */       throw new IllegalArgumentException("target object must not be null"); 
/* 346 */     Class cls = target.getClass();
/* 347 */     Field field = getField(cls, fieldName, forceAccess);
/* 348 */     if (field == null)
/* 349 */       throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls); 
/* 352 */     return readField(field, target);
/*     */   }
/*     */   
/*     */   public static Object readDeclaredField(Object target, String fieldName) throws IllegalAccessException {
/* 364 */     return readDeclaredField(target, fieldName, false);
/*     */   }
/*     */   
/*     */   public static Object readDeclaredField(Object target, String fieldName, boolean forceAccess) throws IllegalAccessException {
/* 381 */     if (target == null)
/* 382 */       throw new IllegalArgumentException("target object must not be null"); 
/* 384 */     Class cls = target.getClass();
/* 385 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 386 */     if (field == null)
/* 387 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName); 
/* 390 */     return readField(field, target);
/*     */   }
/*     */   
/*     */   public static void writeStaticField(Field field, Object value) throws IllegalAccessException {
/* 401 */     writeStaticField(field, value, false);
/*     */   }
/*     */   
/*     */   public static void writeStaticField(Field field, Object value, boolean forceAccess) throws IllegalAccessException {
/* 415 */     if (field == null)
/* 416 */       throw new IllegalArgumentException("The field must not be null"); 
/* 418 */     if (!Modifier.isStatic(field.getModifiers()))
/* 419 */       throw new IllegalArgumentException("The field '" + field.getName() + "' is not static"); 
/* 421 */     writeField(field, (Object)null, value, forceAccess);
/*     */   }
/*     */   
/*     */   public static void writeStaticField(Class cls, String fieldName, Object value) throws IllegalAccessException {
/* 433 */     writeStaticField(cls, fieldName, value, false);
/*     */   }
/*     */   
/*     */   public static void writeStaticField(Class cls, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
/* 449 */     Field field = getField(cls, fieldName, forceAccess);
/* 450 */     if (field == null)
/* 451 */       throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls); 
/* 454 */     writeStaticField(field, value);
/*     */   }
/*     */   
/*     */   public static void writeDeclaredStaticField(Class cls, String fieldName, Object value) throws IllegalAccessException {
/* 467 */     writeDeclaredStaticField(cls, fieldName, value, false);
/*     */   }
/*     */   
/*     */   public static void writeDeclaredStaticField(Class cls, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
/* 483 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 484 */     if (field == null)
/* 485 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName); 
/* 488 */     writeField(field, (Object)null, value);
/*     */   }
/*     */   
/*     */   public static void writeField(Field field, Object target, Object value) throws IllegalAccessException {
/* 500 */     writeField(field, target, value, false);
/*     */   }
/*     */   
/*     */   public static void writeField(Field field, Object target, Object value, boolean forceAccess) throws IllegalAccessException {
/* 515 */     if (field == null)
/* 516 */       throw new IllegalArgumentException("The field must not be null"); 
/* 518 */     if (forceAccess && !field.isAccessible()) {
/* 519 */       field.setAccessible(true);
/*     */     } else {
/* 521 */       MemberUtils.setAccessibleWorkaround(field);
/*     */     } 
/* 523 */     field.set(target, value);
/*     */   }
/*     */   
/*     */   public static void writeField(Object target, String fieldName, Object value) throws IllegalAccessException {
/* 535 */     writeField(target, fieldName, value, false);
/*     */   }
/*     */   
/*     */   public static void writeField(Object target, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
/* 551 */     if (target == null)
/* 552 */       throw new IllegalArgumentException("target object must not be null"); 
/* 554 */     Class cls = target.getClass();
/* 555 */     Field field = getField(cls, fieldName, forceAccess);
/* 556 */     if (field == null)
/* 557 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName); 
/* 560 */     writeField(field, target, value);
/*     */   }
/*     */   
/*     */   public static void writeDeclaredField(Object target, String fieldName, Object value) throws IllegalAccessException {
/* 572 */     writeDeclaredField(target, fieldName, value, false);
/*     */   }
/*     */   
/*     */   public static void writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
/* 588 */     if (target == null)
/* 589 */       throw new IllegalArgumentException("target object must not be null"); 
/* 591 */     Class cls = target.getClass();
/* 592 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 593 */     if (field == null)
/* 594 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName); 
/* 597 */     writeField(field, target, value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\reflect\FieldUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */