/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ 
/*     */ public class ConstructorUtils {
/*  53 */   private static final Class[] EMPTY_CLASS_PARAMETERS = new Class[0];
/*     */   
/*  55 */   private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*     */   
/*     */   public static Object invokeConstructor(Class klass, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/*  84 */     Object[] args = { arg };
/*  85 */     return invokeConstructor(klass, args);
/*     */   }
/*     */   
/*     */   public static Object invokeConstructor(Class klass, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 114 */     if (null == args)
/* 115 */       args = EMPTY_OBJECT_ARRAY; 
/* 117 */     int arguments = args.length;
/* 118 */     Class[] parameterTypes = new Class[arguments];
/* 119 */     for (int i = 0; i < arguments; i++)
/* 120 */       parameterTypes[i] = args[i].getClass(); 
/* 122 */     return invokeConstructor(klass, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeConstructor(Class klass, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 153 */     if (parameterTypes == null)
/* 154 */       parameterTypes = EMPTY_CLASS_PARAMETERS; 
/* 156 */     if (args == null)
/* 157 */       args = EMPTY_OBJECT_ARRAY; 
/* 160 */     Constructor ctor = getMatchingAccessibleConstructor(klass, parameterTypes);
/* 162 */     if (null == ctor)
/* 163 */       throw new NoSuchMethodException("No such accessible constructor on object: " + klass.getName()); 
/* 166 */     return ctor.newInstance(args);
/*     */   }
/*     */   
/*     */   public static Object invokeExactConstructor(Class klass, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 195 */     Object[] args = { arg };
/* 196 */     return invokeExactConstructor(klass, args);
/*     */   }
/*     */   
/*     */   public static Object invokeExactConstructor(Class klass, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 224 */     if (null == args)
/* 225 */       args = EMPTY_OBJECT_ARRAY; 
/* 227 */     int arguments = args.length;
/* 228 */     Class[] parameterTypes = new Class[arguments];
/* 229 */     for (int i = 0; i < arguments; i++)
/* 230 */       parameterTypes[i] = args[i].getClass(); 
/* 232 */     return invokeExactConstructor(klass, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeExactConstructor(Class klass, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 264 */     if (args == null)
/* 265 */       args = EMPTY_OBJECT_ARRAY; 
/* 268 */     if (parameterTypes == null)
/* 269 */       parameterTypes = EMPTY_CLASS_PARAMETERS; 
/* 272 */     Constructor ctor = getAccessibleConstructor(klass, parameterTypes);
/* 273 */     if (null == ctor)
/* 274 */       throw new NoSuchMethodException("No such accessible constructor on object: " + klass.getName()); 
/* 277 */     return ctor.newInstance(args);
/*     */   }
/*     */   
/*     */   public static Constructor getAccessibleConstructor(Class klass, Class parameterType) {
/* 293 */     Class[] parameterTypes = { parameterType };
/* 294 */     return getAccessibleConstructor(klass, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Constructor getAccessibleConstructor(Class klass, Class[] parameterTypes) {
/*     */     try {
/* 311 */       return getAccessibleConstructor(klass.getConstructor(parameterTypes));
/* 313 */     } catch (NoSuchMethodException e) {
/* 314 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Constructor getAccessibleConstructor(Constructor ctor) {
/* 328 */     if (ctor == null)
/* 329 */       return null; 
/* 333 */     if (!Modifier.isPublic(ctor.getModifiers()))
/* 334 */       return null; 
/* 338 */     Class clazz = ctor.getDeclaringClass();
/* 339 */     if (Modifier.isPublic(clazz.getModifiers()))
/* 340 */       return ctor; 
/* 344 */     return null;
/*     */   }
/*     */   
/*     */   private static Constructor getMatchingAccessibleConstructor(Class clazz, Class[] parameterTypes) {
/*     */     try {
/* 370 */       Constructor ctor = clazz.getConstructor(parameterTypes);
/*     */       try {
/* 388 */         ctor.setAccessible(true);
/* 389 */       } catch (SecurityException se) {}
/* 392 */       return ctor;
/* 394 */     } catch (NoSuchMethodException e) {
/* 398 */       int paramSize = parameterTypes.length;
/* 399 */       Constructor[] ctors = (Constructor[])clazz.getConstructors();
/* 400 */       for (int i = 0, size = ctors.length; i < size; i++) {
/* 402 */         Class[] ctorParams = ctors[i].getParameterTypes();
/* 403 */         int ctorParamSize = ctorParams.length;
/* 404 */         if (ctorParamSize == paramSize) {
/* 405 */           boolean match = true;
/* 406 */           for (int n = 0; n < ctorParamSize; n++) {
/* 407 */             if (!MethodUtils.isAssignmentCompatible(ctorParams[n], parameterTypes[n])) {
/* 411 */               match = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 416 */           if (match) {
/* 418 */             Constructor ctor = getAccessibleConstructor(ctors[i]);
/* 419 */             if (ctor != null) {
/*     */               try {
/* 421 */                 ctor.setAccessible(true);
/* 422 */               } catch (SecurityException se) {}
/* 427 */               return ctor;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 433 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ConstructorUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */