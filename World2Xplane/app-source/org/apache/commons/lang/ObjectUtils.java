/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.lang.exception.CloneFailedException;
/*     */ import org.apache.commons.lang.reflect.MethodUtils;
/*     */ 
/*     */ public class ObjectUtils {
/*  63 */   public static final Null NULL = new Null();
/*     */   
/*     */   public static Object defaultIfNull(Object object, Object defaultValue) {
/*  96 */     return (object != null) ? object : defaultValue;
/*     */   }
/*     */   
/*     */   public static boolean equals(Object object1, Object object2) {
/* 119 */     if (object1 == object2)
/* 120 */       return true; 
/* 122 */     if (object1 == null || object2 == null)
/* 123 */       return false; 
/* 125 */     return object1.equals(object2);
/*     */   }
/*     */   
/*     */   public static boolean notEqual(Object object1, Object object2) {
/* 149 */     return !equals(object1, object2);
/*     */   }
/*     */   
/*     */   public static int hashCode(Object obj) {
/* 166 */     return (obj == null) ? 0 : obj.hashCode();
/*     */   }
/*     */   
/*     */   public static String identityToString(Object object) {
/* 188 */     if (object == null)
/* 189 */       return null; 
/* 191 */     StringBuffer buffer = new StringBuffer();
/* 192 */     identityToString(buffer, object);
/* 193 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static void identityToString(StringBuffer buffer, Object object) {
/* 212 */     if (object == null)
/* 213 */       throw new NullPointerException("Cannot get the toString of a null identity"); 
/* 215 */     buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
/*     */   }
/*     */   
/*     */   public static StringBuffer appendIdentityToString(StringBuffer buffer, Object object) {
/* 240 */     if (object == null)
/* 241 */       return null; 
/* 243 */     if (buffer == null)
/* 244 */       buffer = new StringBuffer(); 
/* 246 */     return buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
/*     */   }
/*     */   
/*     */   public static String toString(Object obj) {
/* 272 */     return (obj == null) ? "" : obj.toString();
/*     */   }
/*     */   
/*     */   public static String toString(Object obj, String nullStr) {
/* 295 */     return (obj == null) ? nullStr : obj.toString();
/*     */   }
/*     */   
/*     */   public static Object min(Comparable c1, Comparable c2) {
/* 314 */     return (compare(c1, c2, true) <= 0) ? c1 : c2;
/*     */   }
/*     */   
/*     */   public static Object max(Comparable c1, Comparable c2) {
/* 331 */     return (compare(c1, c2, false) >= 0) ? c1 : c2;
/*     */   }
/*     */   
/*     */   public static int compare(Comparable c1, Comparable c2) {
/* 345 */     return compare(c1, c2, false);
/*     */   }
/*     */   
/*     */   public static int compare(Comparable c1, Comparable c2, boolean nullGreater) {
/* 362 */     if (c1 == c2)
/* 363 */       return 0; 
/* 364 */     if (c1 == null)
/* 365 */       return nullGreater ? 1 : -1; 
/* 366 */     if (c2 == null)
/* 367 */       return nullGreater ? -1 : 1; 
/* 369 */     return c1.compareTo(c2);
/*     */   }
/*     */   
/*     */   public static Object clone(Object o) {
/* 381 */     if (o instanceof Cloneable) {
/*     */       Object result;
/* 383 */       if (o.getClass().isArray()) {
/* 384 */         Class componentType = o.getClass().getComponentType();
/* 385 */         if (!componentType.isPrimitive()) {
/* 386 */           result = ((Object[])o).clone();
/*     */         } else {
/* 388 */           int length = Array.getLength(o);
/* 389 */           result = Array.newInstance(componentType, length);
/* 390 */           while (length-- > 0)
/* 391 */             Array.set(result, length, Array.get(o, length)); 
/*     */         } 
/*     */       } else {
/*     */         try {
/* 396 */           result = MethodUtils.invokeMethod(o, "clone", null);
/* 397 */         } catch (NoSuchMethodException e) {
/* 398 */           throw new CloneFailedException("Cloneable type " + o.getClass().getName() + " has no clone method", e);
/* 401 */         } catch (IllegalAccessException e) {
/* 402 */           throw new CloneFailedException("Cannot clone Cloneable type " + o.getClass().getName(), e);
/* 404 */         } catch (InvocationTargetException e) {
/* 405 */           throw new CloneFailedException("Exception cloning Cloneable type " + o.getClass().getName(), e.getTargetException());
/*     */         } 
/*     */       } 
/* 409 */       return result;
/*     */     } 
/* 412 */     return null;
/*     */   }
/*     */   
/*     */   public static Object cloneIfPossible(Object o) {
/* 429 */     Object clone = clone(o);
/* 430 */     return (clone == null) ? o : clone;
/*     */   }
/*     */   
/*     */   public static class Null implements Serializable {
/*     */     private static final long serialVersionUID = 7092611880189329093L;
/*     */     
/*     */     private Object readResolve() {
/* 470 */       return ObjectUtils.NULL;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\ObjectUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */