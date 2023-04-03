/*     */ package org.apache.commons.lang.reflect;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ 
/*     */ public class MethodUtils {
/*     */   public static Object invokeMethod(Object object, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  95 */     return invokeMethod(object, methodName, new Object[] { arg });
/*     */   }
/*     */   
/*     */   public static Object invokeMethod(Object object, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 123 */     if (args == null)
/* 124 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 126 */     int arguments = args.length;
/* 127 */     Class[] parameterTypes = new Class[arguments];
/* 128 */     for (int i = 0; i < arguments; i++)
/* 129 */       parameterTypes[i] = args[i].getClass(); 
/* 131 */     return invokeMethod(object, methodName, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeMethod(Object object, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 157 */     if (parameterTypes == null)
/* 158 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY; 
/* 160 */     if (args == null)
/* 161 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 163 */     Method method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
/* 165 */     if (method == null)
/* 166 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName()); 
/* 170 */     return method.invoke(object, args);
/*     */   }
/*     */   
/*     */   public static Object invokeExactMethod(Object object, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 195 */     return invokeExactMethod(object, methodName, new Object[] { arg });
/*     */   }
/*     */   
/*     */   public static Object invokeExactMethod(Object object, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 219 */     if (args == null)
/* 220 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 222 */     int arguments = args.length;
/* 223 */     Class[] parameterTypes = new Class[arguments];
/* 224 */     for (int i = 0; i < arguments; i++)
/* 225 */       parameterTypes[i] = args[i].getClass(); 
/* 227 */     return invokeExactMethod(object, methodName, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 253 */     if (args == null)
/* 254 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 256 */     if (parameterTypes == null)
/* 257 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY; 
/* 259 */     Method method = getAccessibleMethod(object.getClass(), methodName, parameterTypes);
/* 261 */     if (method == null)
/* 262 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName()); 
/* 266 */     return method.invoke(object, args);
/*     */   }
/*     */   
/*     */   public static Object invokeExactStaticMethod(Class cls, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 292 */     if (args == null)
/* 293 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 295 */     if (parameterTypes == null)
/* 296 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY; 
/* 298 */     Method method = getAccessibleMethod(cls, methodName, parameterTypes);
/* 299 */     if (method == null)
/* 300 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName()); 
/* 303 */     return method.invoke(null, args);
/*     */   }
/*     */   
/*     */   public static Object invokeStaticMethod(Class cls, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 333 */     return invokeStaticMethod(cls, methodName, new Object[] { arg });
/*     */   }
/*     */   
/*     */   public static Object invokeStaticMethod(Class cls, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 363 */     if (args == null)
/* 364 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 366 */     int arguments = args.length;
/* 367 */     Class[] parameterTypes = new Class[arguments];
/* 368 */     for (int i = 0; i < arguments; i++)
/* 369 */       parameterTypes[i] = args[i].getClass(); 
/* 371 */     return invokeStaticMethod(cls, methodName, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Object invokeStaticMethod(Class cls, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 400 */     if (parameterTypes == null)
/* 401 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY; 
/* 403 */     if (args == null)
/* 404 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 406 */     Method method = getMatchingAccessibleMethod(cls, methodName, parameterTypes);
/* 408 */     if (method == null)
/* 409 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName()); 
/* 412 */     return method.invoke(null, args);
/*     */   }
/*     */   
/*     */   public static Object invokeExactStaticMethod(Class cls, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 437 */     return invokeExactStaticMethod(cls, methodName, new Object[] { arg });
/*     */   }
/*     */   
/*     */   public static Object invokeExactStaticMethod(Class cls, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/* 461 */     if (args == null)
/* 462 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY; 
/* 464 */     int arguments = args.length;
/* 465 */     Class[] parameterTypes = new Class[arguments];
/* 466 */     for (int i = 0; i < arguments; i++)
/* 467 */       parameterTypes[i] = args[i].getClass(); 
/* 469 */     return invokeExactStaticMethod(cls, methodName, args, parameterTypes);
/*     */   }
/*     */   
/*     */   public static Method getAccessibleMethod(Class cls, String methodName, Class parameterType) {
/* 486 */     return getAccessibleMethod(cls, methodName, new Class[] { parameterType });
/*     */   }
/*     */   
/*     */   public static Method getAccessibleMethod(Class cls, String methodName, Class[] parameterTypes) {
/*     */     try {
/* 505 */       return getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
/* 507 */     } catch (NoSuchMethodException e) {
/* 508 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Method getAccessibleMethod(Method method) {
/* 521 */     if (!MemberUtils.isAccessible(method))
/* 522 */       return null; 
/* 525 */     Class cls = method.getDeclaringClass();
/* 526 */     if (Modifier.isPublic(cls.getModifiers()))
/* 527 */       return method; 
/* 529 */     String methodName = method.getName();
/* 530 */     Class[] parameterTypes = method.getParameterTypes();
/* 533 */     method = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
/* 537 */     if (method == null)
/* 538 */       method = getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes); 
/* 541 */     return method;
/*     */   }
/*     */   
/*     */   private static Method getAccessibleMethodFromSuperclass(Class cls, String methodName, Class[] parameterTypes) {
/* 556 */     Class parentClass = cls.getSuperclass();
/* 557 */     while (parentClass != null) {
/* 558 */       if (Modifier.isPublic(parentClass.getModifiers()))
/*     */         try {
/* 560 */           return parentClass.getMethod(methodName, parameterTypes);
/* 561 */         } catch (NoSuchMethodException e) {
/* 562 */           return null;
/*     */         }  
/* 565 */       parentClass = parentClass.getSuperclass();
/*     */     } 
/* 567 */     return null;
/*     */   }
/*     */   
/*     */   private static Method getAccessibleMethodFromInterfaceNest(Class cls, String methodName, Class[] parameterTypes) {
/* 587 */     Method method = null;
/* 590 */     for (; cls != null; cls = cls.getSuperclass()) {
/* 593 */       Class[] interfaces = cls.getInterfaces();
/* 594 */       for (int i = 0; i < interfaces.length; i++) {
/* 596 */         if (Modifier.isPublic(interfaces[i].getModifiers())) {
/*     */           try {
/* 601 */             method = interfaces[i].getDeclaredMethod(methodName, parameterTypes);
/* 603 */           } catch (NoSuchMethodException e) {}
/* 609 */           if (method != null)
/*     */             break; 
/* 613 */           method = getAccessibleMethodFromInterfaceNest(interfaces[i], methodName, parameterTypes);
/* 615 */           if (method != null)
/*     */             break; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 620 */     return method;
/*     */   }
/*     */   
/*     */   public static Method getMatchingAccessibleMethod(Class cls, String methodName, Class[] parameterTypes) {
/*     */     try {
/* 646 */       Method method = cls.getMethod(methodName, parameterTypes);
/* 647 */       MemberUtils.setAccessibleWorkaround(method);
/* 648 */       return method;
/* 649 */     } catch (NoSuchMethodException e) {
/* 652 */       Method bestMatch = null;
/* 653 */       Method[] methods = cls.getMethods();
/* 654 */       for (int i = 0, size = methods.length; i < size; i++) {
/* 655 */         if (methods[i].getName().equals(methodName))
/* 657 */           if (ClassUtils.isAssignable(parameterTypes, methods[i].getParameterTypes(), true)) {
/* 660 */             Method accessibleMethod = getAccessibleMethod(methods[i]);
/* 661 */             if (accessibleMethod != null && (
/* 662 */               bestMatch == null || MemberUtils.compareParameterTypes(accessibleMethod.getParameterTypes(), bestMatch.getParameterTypes(), parameterTypes) < 0))
/* 667 */               bestMatch = accessibleMethod; 
/*     */           }  
/*     */       } 
/* 673 */       if (bestMatch != null)
/* 674 */         MemberUtils.setAccessibleWorkaround(bestMatch); 
/* 676 */       return bestMatch;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\reflect\MethodUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */