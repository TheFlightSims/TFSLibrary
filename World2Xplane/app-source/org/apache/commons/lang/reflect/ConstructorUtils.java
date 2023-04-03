/*     */ package org.apache.commons.lang.reflect;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ 
/*     */ public class ConstructorUtils {
/*     */   public static Object invokeConstructor(Class cls, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/*  92 */     return invokeConstructor(cls, new Object[] { arg });
/*     */   }
/*     */   
/*     */   public static Object invokeConstructor(Class cls, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 120 */     if (null == args)
/* 121 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 123 */     Class[] parameterTypes = new Class[args.length];
/* 124 */     for (int i = 0; i < args.length; i++)
/* 125 */       parameterTypes[i] = args[i].getClass(); 
/* 127 */     return invokeConstructor(cls, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeConstructor(Class cls, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 151 */     if (parameterTypes == null)
/* 152 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY; 
/* 154 */     if (args == null)
/* 155 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 157 */     Constructor ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
/* 158 */     if (null == ctor)
/* 159 */       throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName()); 
/* 162 */     return ctor.newInstance(args);
/*     */   }
/*     */   
/*     */   public static Object invokeExactConstructor(Class cls, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 188 */     return invokeExactConstructor(cls, new Object[] { arg });
/*     */   }
/*     */   
/*     */   public static Object invokeExactConstructor(Class cls, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 217 */     if (null == args)
/* 218 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 220 */     int arguments = args.length;
/* 221 */     Class[] parameterTypes = new Class[arguments];
/* 222 */     for (int i = 0; i < arguments; i++)
/* 223 */       parameterTypes[i] = args[i].getClass(); 
/* 225 */     return invokeExactConstructor(cls, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeExactConstructor(Class cls, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 249 */     if (args == null)
/* 250 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 252 */     if (parameterTypes == null)
/* 253 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY; 
/* 255 */     Constructor ctor = getAccessibleConstructor(cls, parameterTypes);
/* 256 */     if (null == ctor)
/* 257 */       throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName()); 
/* 260 */     return ctor.newInstance(args);
/*     */   }
/*     */   
/*     */   public static Constructor getAccessibleConstructor(Class cls, Class parameterType) {
/* 273 */     return getAccessibleConstructor(cls, new Class[] { parameterType });
/*     */   }
/*     */   
/*     */   public static Constructor getAccessibleConstructor(Class cls, Class[] parameterTypes) {
/*     */     try {
/* 287 */       return getAccessibleConstructor(cls.getConstructor(parameterTypes));
/* 288 */     } catch (NoSuchMethodException e) {
/* 289 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Constructor getAccessibleConstructor(Constructor ctor) {
/* 300 */     return (MemberUtils.isAccessible(ctor) && Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) ? ctor : null;
/*     */   }
/*     */   
/*     */   public static Constructor getMatchingAccessibleConstructor(Class cls, Class[] parameterTypes) {
/*     */     try {
/* 325 */       Constructor ctor = cls.getConstructor(parameterTypes);
/* 326 */       MemberUtils.setAccessibleWorkaround(ctor);
/* 327 */       return ctor;
/* 328 */     } catch (NoSuchMethodException e) {
/* 330 */       Constructor result = null;
/* 332 */       Constructor[] ctors = (Constructor[])cls.getConstructors();
/* 334 */       for (int i = 0; i < ctors.length; i++) {
/* 337 */         if (ClassUtils.isAssignable(parameterTypes, ctors[i].getParameterTypes(), true)) {
/* 339 */           Constructor ctor = getAccessibleConstructor(ctors[i]);
/* 340 */           if (ctor != null) {
/* 341 */             MemberUtils.setAccessibleWorkaround(ctor);
/* 342 */             if (result == null || MemberUtils.compareParameterTypes(ctor.getParameterTypes(), result.getParameterTypes(), parameterTypes) < 0)
/* 345 */               result = ctor; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 350 */       return result;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\reflect\ConstructorUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */