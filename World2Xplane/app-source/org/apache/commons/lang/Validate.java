/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class Validate {
/*     */   public static void isTrue(boolean expression, String message, Object value) {
/*  70 */     if (!expression)
/*  71 */       throw new IllegalArgumentException(message + value); 
/*     */   }
/*     */   
/*     */   public static void isTrue(boolean expression, String message, long value) {
/*  92 */     if (!expression)
/*  93 */       throw new IllegalArgumentException(message + value); 
/*     */   }
/*     */   
/*     */   public static void isTrue(boolean expression, String message, double value) {
/* 114 */     if (!expression)
/* 115 */       throw new IllegalArgumentException(message + value); 
/*     */   }
/*     */   
/*     */   public static void isTrue(boolean expression, String message) {
/* 135 */     if (!expression)
/* 136 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */   
/*     */   public static void isTrue(boolean expression) {
/* 157 */     if (!expression)
/* 158 */       throw new IllegalArgumentException("The validated expression is false"); 
/*     */   }
/*     */   
/*     */   public static void notNull(Object object) {
/* 178 */     notNull(object, "The validated object is null");
/*     */   }
/*     */   
/*     */   public static void notNull(Object object, String message) {
/* 191 */     if (object == null)
/* 192 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */   
/*     */   public static void notEmpty(Object[] array, String message) {
/* 211 */     if (array == null || array.length == 0)
/* 212 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */   
/*     */   public static void notEmpty(Object[] array) {
/* 229 */     notEmpty(array, "The validated array is empty");
/*     */   }
/*     */   
/*     */   public static void notEmpty(Collection collection, String message) {
/* 247 */     if (collection == null || collection.size() == 0)
/* 248 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */   
/*     */   public static void notEmpty(Collection collection) {
/* 265 */     notEmpty(collection, "The validated collection is empty");
/*     */   }
/*     */   
/*     */   public static void notEmpty(Map map, String message) {
/* 283 */     if (map == null || map.size() == 0)
/* 284 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */   
/*     */   public static void notEmpty(Map map) {
/* 302 */     notEmpty(map, "The validated map is empty");
/*     */   }
/*     */   
/*     */   public static void notEmpty(String string, String message) {
/* 320 */     if (string == null || string.length() == 0)
/* 321 */       throw new IllegalArgumentException(message); 
/*     */   }
/*     */   
/*     */   public static void notEmpty(String string) {
/* 339 */     notEmpty(string, "The validated string is empty");
/*     */   }
/*     */   
/*     */   public static void noNullElements(Object[] array, String message) {
/* 361 */     notNull(array);
/* 362 */     for (int i = 0; i < array.length; i++) {
/* 363 */       if (array[i] == null)
/* 364 */         throw new IllegalArgumentException(message); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void noNullElements(Object[] array) {
/* 388 */     notNull(array);
/* 389 */     for (int i = 0; i < array.length; i++) {
/* 390 */       if (array[i] == null)
/* 391 */         throw new IllegalArgumentException("The validated array contains null element at index: " + i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void noNullElements(Collection collection, String message) {
/* 416 */     notNull(collection);
/* 417 */     for (Iterator it = collection.iterator(); it.hasNext();) {
/* 418 */       if (it.next() == null)
/* 419 */         throw new IllegalArgumentException(message); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void noNullElements(Collection collection) {
/* 443 */     notNull(collection);
/* 444 */     int i = 0;
/* 445 */     for (Iterator it = collection.iterator(); it.hasNext(); i++) {
/* 446 */       if (it.next() == null)
/* 447 */         throw new IllegalArgumentException("The validated collection contains null element at index: " + i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void allElementsOfType(Collection collection, Class clazz, String message) {
/* 467 */     notNull(collection);
/* 468 */     notNull(clazz);
/* 469 */     for (Iterator it = collection.iterator(); it.hasNext();) {
/* 470 */       if (!clazz.isInstance(it.next()))
/* 471 */         throw new IllegalArgumentException(message); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void allElementsOfType(Collection collection, Class clazz) {
/* 495 */     notNull(collection);
/* 496 */     notNull(clazz);
/* 497 */     int i = 0;
/* 498 */     for (Iterator it = collection.iterator(); it.hasNext(); i++) {
/* 499 */       if (!clazz.isInstance(it.next()))
/* 500 */         throw new IllegalArgumentException("The validated collection contains an element not of type " + clazz.getName() + " at index: " + i); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\Validate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */