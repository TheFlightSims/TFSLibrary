/*      */ package org.apache.commons.beanutils;
/*      */ 
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Map;
/*      */ import java.util.WeakHashMap;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public class MethodUtils {
/*      */   private static boolean loggedAccessibleWarning = false;
/*      */   
/*      */   private static boolean CACHE_METHODS = true;
/*      */   
/*   86 */   private static final Class[] EMPTY_CLASS_PARAMETERS = new Class[0];
/*      */   
/*   88 */   private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*      */   
/*  110 */   private static final Map cache = Collections.synchronizedMap(new WeakHashMap());
/*      */   
/*      */   public static synchronized void setCacheMethods(boolean cacheMethods) {
/*  123 */     CACHE_METHODS = cacheMethods;
/*  124 */     if (!CACHE_METHODS)
/*  125 */       clearCache(); 
/*      */   }
/*      */   
/*      */   public static synchronized int clearCache() {
/*  135 */     int size = cache.size();
/*  136 */     cache.clear();
/*  137 */     return size;
/*      */   }
/*      */   
/*      */   public static Object invokeMethod(Object object, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  176 */     Object[] args = { arg };
/*  177 */     return invokeMethod(object, methodName, args);
/*      */   }
/*      */   
/*      */   public static Object invokeMethod(Object object, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  218 */     if (args == null)
/*  219 */       args = EMPTY_OBJECT_ARRAY; 
/*  221 */     int arguments = args.length;
/*  222 */     Class[] parameterTypes = new Class[arguments];
/*  223 */     for (int i = 0; i < arguments; i++)
/*  224 */       parameterTypes[i] = args[i].getClass(); 
/*  226 */     return invokeMethod(object, methodName, args, parameterTypes);
/*      */   }
/*      */   
/*      */   public static Object invokeMethod(Object object, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  267 */     if (parameterTypes == null)
/*  268 */       parameterTypes = EMPTY_CLASS_PARAMETERS; 
/*  270 */     if (args == null)
/*  271 */       args = EMPTY_OBJECT_ARRAY; 
/*  274 */     Method method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
/*  278 */     if (method == null)
/*  279 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName()); 
/*  282 */     return method.invoke(object, args);
/*      */   }
/*      */   
/*      */   public static Object invokeExactMethod(Object object, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  314 */     Object[] args = { arg };
/*  315 */     return invokeExactMethod(object, methodName, args);
/*      */   }
/*      */   
/*      */   public static Object invokeExactMethod(Object object, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  346 */     if (args == null)
/*  347 */       args = EMPTY_OBJECT_ARRAY; 
/*  349 */     int arguments = args.length;
/*  350 */     Class[] parameterTypes = new Class[arguments];
/*  351 */     for (int i = 0; i < arguments; i++)
/*  352 */       parameterTypes[i] = args[i].getClass(); 
/*  354 */     return invokeExactMethod(object, methodName, args, parameterTypes);
/*      */   }
/*      */   
/*      */   public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  388 */     if (args == null)
/*  389 */       args = EMPTY_OBJECT_ARRAY; 
/*  392 */     if (parameterTypes == null)
/*  393 */       parameterTypes = EMPTY_CLASS_PARAMETERS; 
/*  396 */     Method method = getAccessibleMethod(object.getClass(), methodName, parameterTypes);
/*  400 */     if (method == null)
/*  401 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName()); 
/*  404 */     return method.invoke(object, args);
/*      */   }
/*      */   
/*      */   public static Object invokeExactStaticMethod(Class objectClass, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  438 */     if (args == null)
/*  439 */       args = EMPTY_OBJECT_ARRAY; 
/*  442 */     if (parameterTypes == null)
/*  443 */       parameterTypes = EMPTY_CLASS_PARAMETERS; 
/*  446 */     Method method = getAccessibleMethod(objectClass, methodName, parameterTypes);
/*  450 */     if (method == null)
/*  451 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + objectClass.getName()); 
/*  454 */     return method.invoke((Object)null, args);
/*      */   }
/*      */   
/*      */   public static Object invokeStaticMethod(Class objectClass, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  495 */     Object[] args = { arg };
/*  496 */     return invokeStaticMethod(objectClass, methodName, args);
/*      */   }
/*      */   
/*      */   public static Object invokeStaticMethod(Class objectClass, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  538 */     if (args == null)
/*  539 */       args = EMPTY_OBJECT_ARRAY; 
/*  541 */     int arguments = args.length;
/*  542 */     Class[] parameterTypes = new Class[arguments];
/*  543 */     for (int i = 0; i < arguments; i++)
/*  544 */       parameterTypes[i] = args[i].getClass(); 
/*  546 */     return invokeStaticMethod(objectClass, methodName, args, parameterTypes);
/*      */   }
/*      */   
/*      */   public static Object invokeStaticMethod(Class objectClass, String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  588 */     if (parameterTypes == null)
/*  589 */       parameterTypes = EMPTY_CLASS_PARAMETERS; 
/*  591 */     if (args == null)
/*  592 */       args = EMPTY_OBJECT_ARRAY; 
/*  595 */     Method method = getMatchingAccessibleMethod(objectClass, methodName, parameterTypes);
/*  599 */     if (method == null)
/*  600 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + objectClass.getName()); 
/*  603 */     return method.invoke((Object)null, args);
/*      */   }
/*      */   
/*      */   public static Object invokeExactStaticMethod(Class objectClass, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  636 */     Object[] args = { arg };
/*  637 */     return invokeExactStaticMethod(objectClass, methodName, args);
/*      */   }
/*      */   
/*      */   public static Object invokeExactStaticMethod(Class objectClass, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  669 */     if (args == null)
/*  670 */       args = EMPTY_OBJECT_ARRAY; 
/*  672 */     int arguments = args.length;
/*  673 */     Class[] parameterTypes = new Class[arguments];
/*  674 */     for (int i = 0; i < arguments; i++)
/*  675 */       parameterTypes[i] = args[i].getClass(); 
/*  677 */     return invokeExactStaticMethod(objectClass, methodName, args, parameterTypes);
/*      */   }
/*      */   
/*      */   public static Method getAccessibleMethod(Class clazz, String methodName, Class parameterType) {
/*  699 */     Class[] parameterTypes = { parameterType };
/*  700 */     return getAccessibleMethod(clazz, methodName, parameterTypes);
/*      */   }
/*      */   
/*      */   public static Method getAccessibleMethod(Class clazz, String methodName, Class[] parameterTypes) {
/*      */     try {
/*  723 */       MethodDescriptor md = new MethodDescriptor(clazz, methodName, parameterTypes, true);
/*  725 */       Method method = getCachedMethod(md);
/*  726 */       if (method != null)
/*  727 */         return method; 
/*  730 */       method = getAccessibleMethod(clazz, clazz.getMethod(methodName, parameterTypes));
/*  732 */       cacheMethod(md, method);
/*  733 */       return method;
/*  734 */     } catch (NoSuchMethodException e) {
/*  735 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Method getAccessibleMethod(Method method) {
/*  752 */     if (method == null)
/*  753 */       return null; 
/*  756 */     return getAccessibleMethod(method.getDeclaringClass(), method);
/*      */   }
/*      */   
/*      */   public static Method getAccessibleMethod(Class clazz, Method method) {
/*  775 */     if (method == null)
/*  776 */       return null; 
/*  780 */     if (!Modifier.isPublic(method.getModifiers()))
/*  781 */       return null; 
/*  784 */     boolean sameClass = true;
/*  785 */     if (clazz == null) {
/*  786 */       clazz = method.getDeclaringClass();
/*      */     } else {
/*  788 */       sameClass = clazz.equals(method.getDeclaringClass());
/*  789 */       if (!method.getDeclaringClass().isAssignableFrom(clazz))
/*  790 */         throw new IllegalArgumentException(clazz.getName() + " is not assignable from " + method.getDeclaringClass().getName()); 
/*      */     } 
/*  796 */     if (Modifier.isPublic(clazz.getModifiers())) {
/*  797 */       if (!sameClass && !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
/*  798 */         setMethodAccessible(method); 
/*  800 */       return method;
/*      */     } 
/*  803 */     String methodName = method.getName();
/*  804 */     Class[] parameterTypes = method.getParameterTypes();
/*  807 */     method = getAccessibleMethodFromInterfaceNest(clazz, methodName, parameterTypes);
/*  813 */     if (method == null)
/*  814 */       method = getAccessibleMethodFromSuperclass(clazz, methodName, parameterTypes); 
/*  819 */     return method;
/*      */   }
/*      */   
/*      */   private static Method getAccessibleMethodFromSuperclass(Class clazz, String methodName, Class[] parameterTypes) {
/*  838 */     Class parentClazz = clazz.getSuperclass();
/*  839 */     while (parentClazz != null) {
/*  840 */       if (Modifier.isPublic(parentClazz.getModifiers()))
/*      */         try {
/*  842 */           return parentClazz.getMethod(methodName, parameterTypes);
/*  843 */         } catch (NoSuchMethodException e) {
/*  844 */           return null;
/*      */         }  
/*  847 */       parentClazz = parentClazz.getSuperclass();
/*      */     } 
/*  849 */     return null;
/*      */   }
/*      */   
/*      */   private static Method getAccessibleMethodFromInterfaceNest(Class clazz, String methodName, Class[] parameterTypes) {
/*  869 */     Method method = null;
/*  872 */     for (; clazz != null; clazz = clazz.getSuperclass()) {
/*  875 */       Class[] interfaces = clazz.getInterfaces();
/*  876 */       for (int i = 0; i < interfaces.length; i++) {
/*  879 */         if (Modifier.isPublic(interfaces[i].getModifiers())) {
/*      */           try {
/*  885 */             method = interfaces[i].getDeclaredMethod(methodName, parameterTypes);
/*  887 */           } catch (NoSuchMethodException e) {}
/*  892 */           if (method != null)
/*  893 */             return method; 
/*  897 */           method = getAccessibleMethodFromInterfaceNest(interfaces[i], methodName, parameterTypes);
/*  901 */           if (method != null)
/*  902 */             return method; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  910 */     return null;
/*      */   }
/*      */   
/*      */   public static Method getMatchingAccessibleMethod(Class clazz, String methodName, Class[] parameterTypes) {
/*  942 */     Log log = LogFactory.getLog(MethodUtils.class);
/*  943 */     if (log.isTraceEnabled())
/*  944 */       log.trace("Matching name=" + methodName + " on " + clazz); 
/*  946 */     MethodDescriptor md = new MethodDescriptor(clazz, methodName, parameterTypes, false);
/*      */     try {
/*  952 */       Method method = getCachedMethod(md);
/*  953 */       if (method != null)
/*  954 */         return method; 
/*  957 */       method = clazz.getMethod(methodName, parameterTypes);
/*  958 */       if (log.isTraceEnabled()) {
/*  959 */         log.trace("Found straight match: " + method);
/*  960 */         log.trace("isPublic:" + Modifier.isPublic(method.getModifiers()));
/*      */       } 
/*  963 */       setMethodAccessible(method);
/*  965 */       cacheMethod(md, method);
/*  966 */       return method;
/*  968 */     } catch (NoSuchMethodException e) {
/*  971 */       int paramSize = parameterTypes.length;
/*  972 */       Method bestMatch = null;
/*  973 */       Method[] methods = clazz.getMethods();
/*  974 */       float bestMatchCost = Float.MAX_VALUE;
/*  975 */       float myCost = Float.MAX_VALUE;
/*  976 */       for (int i = 0, size = methods.length; i < size; i++) {
/*  977 */         if (methods[i].getName().equals(methodName)) {
/*  979 */           if (log.isTraceEnabled()) {
/*  980 */             log.trace("Found matching name:");
/*  981 */             log.trace(methods[i]);
/*      */           } 
/*  985 */           Class[] methodsParams = methods[i].getParameterTypes();
/*  986 */           int methodParamSize = methodsParams.length;
/*  987 */           if (methodParamSize == paramSize) {
/*  988 */             boolean match = true;
/*  989 */             for (int n = 0; n < methodParamSize; n++) {
/*  990 */               if (log.isTraceEnabled()) {
/*  991 */                 log.trace("Param=" + parameterTypes[n].getName());
/*  992 */                 log.trace("Method=" + methodsParams[n].getName());
/*      */               } 
/*  994 */               if (!isAssignmentCompatible(methodsParams[n], parameterTypes[n])) {
/*  995 */                 if (log.isTraceEnabled())
/*  996 */                   log.trace(methodsParams[n] + " is not assignable from " + parameterTypes[n]); 
/*  999 */                 match = false;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1004 */             if (match) {
/* 1006 */               Method method = getAccessibleMethod(clazz, methods[i]);
/* 1007 */               if (method != null) {
/* 1008 */                 if (log.isTraceEnabled())
/* 1009 */                   log.trace(method + " accessible version of " + methods[i]); 
/* 1012 */                 setMethodAccessible(method);
/* 1013 */                 myCost = getTotalTransformationCost(parameterTypes, method.getParameterTypes());
/* 1014 */                 if (myCost < bestMatchCost) {
/* 1015 */                   bestMatch = method;
/* 1016 */                   bestMatchCost = myCost;
/*      */                 } 
/*      */               } 
/* 1020 */               log.trace("Couldn't find accessible method.");
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1025 */       if (bestMatch != null) {
/* 1026 */         cacheMethod(md, bestMatch);
/*      */       } else {
/* 1029 */         log.trace("No match found.");
/*      */       } 
/* 1032 */       return bestMatch;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void setMethodAccessible(Method method) {
/*      */     try {
/* 1057 */       if (!method.isAccessible())
/* 1058 */         method.setAccessible(true); 
/* 1061 */     } catch (SecurityException se) {
/* 1063 */       Log log = LogFactory.getLog(MethodUtils.class);
/* 1064 */       if (!loggedAccessibleWarning) {
/* 1065 */         boolean vulnerableJVM = false;
/*      */         try {
/* 1067 */           String specVersion = System.getProperty("java.specification.version");
/* 1068 */           if (specVersion.charAt(0) == '1' && (specVersion.charAt(2) == '0' || specVersion.charAt(2) == '1' || specVersion.charAt(2) == '2' || specVersion.charAt(2) == '3'))
/* 1074 */             vulnerableJVM = true; 
/* 1076 */         } catch (SecurityException e) {
/* 1078 */           vulnerableJVM = true;
/*      */         } 
/* 1080 */         if (vulnerableJVM)
/* 1081 */           log.warn("Current Security Manager restricts use of workarounds for reflection bugs  in pre-1.4 JVMs."); 
/* 1085 */         loggedAccessibleWarning = true;
/*      */       } 
/* 1087 */       log.debug("Cannot setAccessible on method. Therefore cannot use jvm access bug workaround.", se);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static float getTotalTransformationCost(Class[] srcArgs, Class[] destArgs) {
/* 1100 */     float totalCost = 0.0F;
/* 1101 */     for (int i = 0; i < srcArgs.length; i++) {
/* 1103 */       Class srcClass = srcArgs[i];
/* 1104 */       Class destClass = destArgs[i];
/* 1105 */       totalCost += getObjectTransformationCost(srcClass, destClass);
/*      */     } 
/* 1108 */     return totalCost;
/*      */   }
/*      */   
/*      */   private static float getObjectTransformationCost(Class srcClass, Class destClass) {
/* 1120 */     float cost = 0.0F;
/* 1121 */     while (destClass != null && !destClass.equals(srcClass)) {
/* 1122 */       if (destClass.isInterface() && isAssignmentCompatible(destClass, srcClass)) {
/* 1127 */         cost += 0.25F;
/*      */         break;
/*      */       } 
/* 1130 */       cost++;
/* 1131 */       destClass = destClass.getSuperclass();
/*      */     } 
/* 1138 */     if (destClass == null)
/* 1139 */       cost += 1.5F; 
/* 1142 */     return cost;
/*      */   }
/*      */   
/*      */   public static final boolean isAssignmentCompatible(Class parameterType, Class parameterization) {
/* 1165 */     if (parameterType.isAssignableFrom(parameterization))
/* 1166 */       return true; 
/* 1169 */     if (parameterType.isPrimitive()) {
/* 1172 */       Class parameterWrapperClazz = getPrimitiveWrapper(parameterType);
/* 1173 */       if (parameterWrapperClazz != null)
/* 1174 */         return parameterWrapperClazz.equals(parameterization); 
/*      */     } 
/* 1178 */     return false;
/*      */   }
/*      */   
/*      */   public static Class getPrimitiveWrapper(Class primitiveType) {
/* 1190 */     if (boolean.class.equals(primitiveType))
/* 1191 */       return Boolean.class; 
/* 1192 */     if (float.class.equals(primitiveType))
/* 1193 */       return Float.class; 
/* 1194 */     if (long.class.equals(primitiveType))
/* 1195 */       return Long.class; 
/* 1196 */     if (int.class.equals(primitiveType))
/* 1197 */       return Integer.class; 
/* 1198 */     if (short.class.equals(primitiveType))
/* 1199 */       return Short.class; 
/* 1200 */     if (byte.class.equals(primitiveType))
/* 1201 */       return Byte.class; 
/* 1202 */     if (double.class.equals(primitiveType))
/* 1203 */       return Double.class; 
/* 1204 */     if (char.class.equals(primitiveType))
/* 1205 */       return Character.class; 
/* 1208 */     return null;
/*      */   }
/*      */   
/*      */   public static Class getPrimitiveType(Class wrapperType) {
/* 1221 */     if (Boolean.class.equals(wrapperType))
/* 1222 */       return boolean.class; 
/* 1223 */     if (Float.class.equals(wrapperType))
/* 1224 */       return float.class; 
/* 1225 */     if (Long.class.equals(wrapperType))
/* 1226 */       return long.class; 
/* 1227 */     if (Integer.class.equals(wrapperType))
/* 1228 */       return int.class; 
/* 1229 */     if (Short.class.equals(wrapperType))
/* 1230 */       return short.class; 
/* 1231 */     if (Byte.class.equals(wrapperType))
/* 1232 */       return byte.class; 
/* 1233 */     if (Double.class.equals(wrapperType))
/* 1234 */       return double.class; 
/* 1235 */     if (Character.class.equals(wrapperType))
/* 1236 */       return char.class; 
/* 1238 */     Log log = LogFactory.getLog(MethodUtils.class);
/* 1239 */     if (log.isDebugEnabled())
/* 1240 */       log.debug("Not a known primitive wrapper class: " + wrapperType); 
/* 1242 */     return null;
/*      */   }
/*      */   
/*      */   public static Class toNonPrimitiveClass(Class clazz) {
/* 1253 */     if (clazz.isPrimitive()) {
/* 1254 */       Class primitiveClazz = getPrimitiveWrapper(clazz);
/* 1256 */       if (primitiveClazz != null)
/* 1257 */         return primitiveClazz; 
/* 1259 */       return clazz;
/*      */     } 
/* 1262 */     return clazz;
/*      */   }
/*      */   
/*      */   private static Method getCachedMethod(MethodDescriptor md) {
/* 1274 */     if (CACHE_METHODS) {
/* 1275 */       Reference methodRef = (Reference)cache.get(md);
/* 1276 */       if (methodRef != null)
/* 1277 */         return methodRef.get(); 
/*      */     } 
/* 1280 */     return null;
/*      */   }
/*      */   
/*      */   private static void cacheMethod(MethodDescriptor md, Method method) {
/* 1290 */     if (CACHE_METHODS && 
/* 1291 */       method != null)
/* 1292 */       cache.put(md, new WeakReference(method)); 
/*      */   }
/*      */   
/*      */   private static class MethodDescriptor {
/*      */     private Class cls;
/*      */     
/*      */     private String methodName;
/*      */     
/*      */     private Class[] paramTypes;
/*      */     
/*      */     private boolean exact;
/*      */     
/*      */     private int hashCode;
/*      */     
/*      */     public MethodDescriptor(Class cls, String methodName, Class[] paramTypes, boolean exact) {
/* 1316 */       if (cls == null)
/* 1317 */         throw new IllegalArgumentException("Class cannot be null"); 
/* 1319 */       if (methodName == null)
/* 1320 */         throw new IllegalArgumentException("Method Name cannot be null"); 
/* 1322 */       if (paramTypes == null)
/* 1323 */         paramTypes = MethodUtils.EMPTY_CLASS_PARAMETERS; 
/* 1326 */       this.cls = cls;
/* 1327 */       this.methodName = methodName;
/* 1328 */       this.paramTypes = paramTypes;
/* 1329 */       this.exact = exact;
/* 1331 */       this.hashCode = methodName.length();
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1339 */       if (!(obj instanceof MethodDescriptor))
/* 1340 */         return false; 
/* 1342 */       MethodDescriptor md = (MethodDescriptor)obj;
/* 1344 */       return (this.exact == md.exact && this.methodName.equals(md.methodName) && this.cls.equals(md.cls) && Arrays.equals((Object[])this.paramTypes, (Object[])md.paramTypes));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1359 */       return this.hashCode;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\MethodUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */