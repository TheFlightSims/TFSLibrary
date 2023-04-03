/*      */ package org.apache.commons.lang;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.lang.text.StrBuilder;
/*      */ 
/*      */ public class ClassUtils {
/*      */   public static final char PACKAGE_SEPARATOR_CHAR = '.';
/*      */   
/*   58 */   public static final String PACKAGE_SEPARATOR = String.valueOf('.');
/*      */   
/*      */   public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
/*      */   
/*   68 */   public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
/*      */   
/*   73 */   private static final Map primitiveWrapperMap = new HashMap();
/*      */   
/*      */   static {
/*   75 */     primitiveWrapperMap.put(boolean.class, Boolean.class);
/*   76 */     primitiveWrapperMap.put(byte.class, Byte.class);
/*   77 */     primitiveWrapperMap.put(char.class, Character.class);
/*   78 */     primitiveWrapperMap.put(short.class, Short.class);
/*   79 */     primitiveWrapperMap.put(int.class, Integer.class);
/*   80 */     primitiveWrapperMap.put(long.class, Long.class);
/*   81 */     primitiveWrapperMap.put(double.class, Double.class);
/*   82 */     primitiveWrapperMap.put(float.class, Float.class);
/*   83 */     primitiveWrapperMap.put(void.class, void.class);
/*      */   }
/*      */   
/*   89 */   private static final Map wrapperPrimitiveMap = new HashMap();
/*      */   
/*      */   static {
/*   91 */     for (Iterator it = primitiveWrapperMap.keySet().iterator(); it.hasNext(); ) {
/*   92 */       Class primitiveClass = it.next();
/*   93 */       Class wrapperClass = (Class)primitiveWrapperMap.get(primitiveClass);
/*   94 */       if (!primitiveClass.equals(wrapperClass))
/*   95 */         wrapperPrimitiveMap.put(wrapperClass, primitiveClass); 
/*      */     } 
/*      */   }
/*      */   
/*  103 */   private static final Map abbreviationMap = new HashMap();
/*      */   
/*  108 */   private static final Map reverseAbbreviationMap = new HashMap();
/*      */   
/*      */   private static void addAbbreviation(String primitive, String abbreviation) {
/*  117 */     abbreviationMap.put(primitive, abbreviation);
/*  118 */     reverseAbbreviationMap.put(abbreviation, primitive);
/*      */   }
/*      */   
/*      */   static {
/*  125 */     addAbbreviation("int", "I");
/*  126 */     addAbbreviation("boolean", "Z");
/*  127 */     addAbbreviation("float", "F");
/*  128 */     addAbbreviation("long", "J");
/*  129 */     addAbbreviation("short", "S");
/*  130 */     addAbbreviation("byte", "B");
/*  131 */     addAbbreviation("double", "D");
/*  132 */     addAbbreviation("char", "C");
/*      */   }
/*      */   
/*      */   public static String getShortClassName(Object object, String valueIfNull) {
/*  157 */     if (object == null)
/*  158 */       return valueIfNull; 
/*  160 */     return getShortClassName(object.getClass());
/*      */   }
/*      */   
/*      */   public static String getShortClassName(Class cls) {
/*  170 */     if (cls == null)
/*  171 */       return ""; 
/*  173 */     return getShortClassName(cls.getName());
/*      */   }
/*      */   
/*      */   public static String getShortClassName(String className) {
/*  185 */     if (className == null)
/*  186 */       return ""; 
/*  188 */     if (className.length() == 0)
/*  189 */       return ""; 
/*  192 */     StrBuilder arrayPrefix = new StrBuilder();
/*  195 */     if (className.startsWith("[")) {
/*  196 */       while (className.charAt(0) == '[') {
/*  197 */         className = className.substring(1);
/*  198 */         arrayPrefix.append("[]");
/*      */       } 
/*  201 */       if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';')
/*  202 */         className = className.substring(1, className.length() - 1); 
/*      */     } 
/*  206 */     if (reverseAbbreviationMap.containsKey(className))
/*  207 */       className = (String)reverseAbbreviationMap.get(className); 
/*  210 */     int lastDotIdx = className.lastIndexOf('.');
/*  211 */     int innerIdx = className.indexOf('$', (lastDotIdx == -1) ? 0 : (lastDotIdx + 1));
/*  213 */     String out = className.substring(lastDotIdx + 1);
/*  214 */     if (innerIdx != -1)
/*  215 */       out = out.replace('$', '.'); 
/*  217 */     return out + arrayPrefix;
/*      */   }
/*      */   
/*      */   public static String getPackageName(Object object, String valueIfNull) {
/*  230 */     if (object == null)
/*  231 */       return valueIfNull; 
/*  233 */     return getPackageName(object.getClass());
/*      */   }
/*      */   
/*      */   public static String getPackageName(Class cls) {
/*  243 */     if (cls == null)
/*  244 */       return ""; 
/*  246 */     return getPackageName(cls.getName());
/*      */   }
/*      */   
/*      */   public static String getPackageName(String className) {
/*  259 */     if (className == null || className.length() == 0)
/*  260 */       return ""; 
/*  264 */     while (className.charAt(0) == '[')
/*  265 */       className = className.substring(1); 
/*  268 */     if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';')
/*  269 */       className = className.substring(1); 
/*  272 */     int i = className.lastIndexOf('.');
/*  273 */     if (i == -1)
/*  274 */       return ""; 
/*  276 */     return className.substring(0, i);
/*      */   }
/*      */   
/*      */   public static List getAllSuperclasses(Class cls) {
/*  289 */     if (cls == null)
/*  290 */       return null; 
/*  292 */     List classes = new ArrayList();
/*  293 */     Class superclass = cls.getSuperclass();
/*  294 */     while (superclass != null) {
/*  295 */       classes.add(superclass);
/*  296 */       superclass = superclass.getSuperclass();
/*      */     } 
/*  298 */     return classes;
/*      */   }
/*      */   
/*      */   public static List getAllInterfaces(Class cls) {
/*  315 */     if (cls == null)
/*  316 */       return null; 
/*  319 */     List interfacesFound = new ArrayList();
/*  320 */     getAllInterfaces(cls, interfacesFound);
/*  322 */     return interfacesFound;
/*      */   }
/*      */   
/*      */   private static void getAllInterfaces(Class cls, List interfacesFound) {
/*  332 */     while (cls != null) {
/*  333 */       Class[] interfaces = cls.getInterfaces();
/*  335 */       for (int i = 0; i < interfaces.length; i++) {
/*  336 */         if (!interfacesFound.contains(interfaces[i])) {
/*  337 */           interfacesFound.add(interfaces[i]);
/*  338 */           getAllInterfaces(interfaces[i], interfacesFound);
/*      */         } 
/*      */       } 
/*  342 */       cls = cls.getSuperclass();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static List convertClassNamesToClasses(List classNames) {
/*  361 */     if (classNames == null)
/*  362 */       return null; 
/*  364 */     List classes = new ArrayList(classNames.size());
/*  365 */     for (Iterator it = classNames.iterator(); it.hasNext(); ) {
/*  366 */       String className = it.next();
/*      */       try {
/*  368 */         classes.add(Class.forName(className));
/*  369 */       } catch (Exception ex) {
/*  370 */         classes.add(null);
/*      */       } 
/*      */     } 
/*  373 */     return classes;
/*      */   }
/*      */   
/*      */   public static List convertClassesToClassNames(List classes) {
/*  389 */     if (classes == null)
/*  390 */       return null; 
/*  392 */     List classNames = new ArrayList(classes.size());
/*  393 */     for (Iterator it = classes.iterator(); it.hasNext(); ) {
/*  394 */       Class cls = it.next();
/*  395 */       if (cls == null) {
/*  396 */         classNames.add(null);
/*      */         continue;
/*      */       } 
/*  398 */       classNames.add(cls.getName());
/*      */     } 
/*  401 */     return classNames;
/*      */   }
/*      */   
/*      */   public static boolean isAssignable(Class[] classArray, Class[] toClassArray) {
/*  438 */     return isAssignable(classArray, toClassArray, false);
/*      */   }
/*      */   
/*      */   public static boolean isAssignable(Class[] classArray, Class[] toClassArray, boolean autoboxing) {
/*  475 */     if (!ArrayUtils.isSameLength((Object[])classArray, (Object[])toClassArray))
/*  476 */       return false; 
/*  478 */     if (classArray == null)
/*  479 */       classArray = ArrayUtils.EMPTY_CLASS_ARRAY; 
/*  481 */     if (toClassArray == null)
/*  482 */       toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY; 
/*  484 */     for (int i = 0; i < classArray.length; i++) {
/*  485 */       if (!isAssignable(classArray[i], toClassArray[i], autoboxing))
/*  486 */         return false; 
/*      */     } 
/*  489 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isAssignable(Class cls, Class toClass) {
/*  519 */     return isAssignable(cls, toClass, false);
/*      */   }
/*      */   
/*      */   public static boolean isAssignable(Class cls, Class toClass, boolean autoboxing) {
/*  551 */     if (toClass == null)
/*  552 */       return false; 
/*  555 */     if (cls == null)
/*  556 */       return !toClass.isPrimitive(); 
/*  559 */     if (autoboxing) {
/*  560 */       if (cls.isPrimitive() && !toClass.isPrimitive()) {
/*  561 */         cls = primitiveToWrapper(cls);
/*  562 */         if (cls == null)
/*  563 */           return false; 
/*      */       } 
/*  566 */       if (toClass.isPrimitive() && !cls.isPrimitive()) {
/*  567 */         cls = wrapperToPrimitive(cls);
/*  568 */         if (cls == null)
/*  569 */           return false; 
/*      */       } 
/*      */     } 
/*  573 */     if (cls.equals(toClass))
/*  574 */       return true; 
/*  576 */     if (cls.isPrimitive()) {
/*  577 */       if (!toClass.isPrimitive())
/*  578 */         return false; 
/*  580 */       if (int.class.equals(cls))
/*  581 */         return (long.class.equals(toClass) || float.class.equals(toClass) || double.class.equals(toClass)); 
/*  585 */       if (long.class.equals(cls))
/*  586 */         return (float.class.equals(toClass) || double.class.equals(toClass)); 
/*  589 */       if (boolean.class.equals(cls))
/*  590 */         return false; 
/*  592 */       if (double.class.equals(cls))
/*  593 */         return false; 
/*  595 */       if (float.class.equals(cls))
/*  596 */         return double.class.equals(toClass); 
/*  598 */       if (char.class.equals(cls))
/*  599 */         return (int.class.equals(toClass) || long.class.equals(toClass) || float.class.equals(toClass) || double.class.equals(toClass)); 
/*  604 */       if (short.class.equals(cls))
/*  605 */         return (int.class.equals(toClass) || long.class.equals(toClass) || float.class.equals(toClass) || double.class.equals(toClass)); 
/*  610 */       if (byte.class.equals(cls))
/*  611 */         return (short.class.equals(toClass) || int.class.equals(toClass) || long.class.equals(toClass) || float.class.equals(toClass) || double.class.equals(toClass)); 
/*  618 */       return false;
/*      */     } 
/*  620 */     return toClass.isAssignableFrom(cls);
/*      */   }
/*      */   
/*      */   public static Class primitiveToWrapper(Class cls) {
/*  636 */     Class convertedClass = cls;
/*  637 */     if (cls != null && cls.isPrimitive())
/*  638 */       convertedClass = (Class)primitiveWrapperMap.get(cls); 
/*  640 */     return convertedClass;
/*      */   }
/*      */   
/*      */   public static Class[] primitivesToWrappers(Class[] classes) {
/*  654 */     if (classes == null)
/*  655 */       return null; 
/*  658 */     if (classes.length == 0)
/*  659 */       return classes; 
/*  662 */     Class[] convertedClasses = new Class[classes.length];
/*  663 */     for (int i = 0; i < classes.length; i++)
/*  664 */       convertedClasses[i] = primitiveToWrapper(classes[i]); 
/*  666 */     return convertedClasses;
/*      */   }
/*      */   
/*      */   public static Class wrapperToPrimitive(Class cls) {
/*  686 */     return (Class)wrapperPrimitiveMap.get(cls);
/*      */   }
/*      */   
/*      */   public static Class[] wrappersToPrimitives(Class[] classes) {
/*  704 */     if (classes == null)
/*  705 */       return null; 
/*  708 */     if (classes.length == 0)
/*  709 */       return classes; 
/*  712 */     Class[] convertedClasses = new Class[classes.length];
/*  713 */     for (int i = 0; i < classes.length; i++)
/*  714 */       convertedClasses[i] = wrapperToPrimitive(classes[i]); 
/*  716 */     return convertedClasses;
/*      */   }
/*      */   
/*      */   public static boolean isInnerClass(Class cls) {
/*  729 */     if (cls == null)
/*  730 */       return false; 
/*  732 */     return (cls.getName().indexOf('$') >= 0);
/*      */   }
/*      */   
/*      */   public static Class getClass(ClassLoader classLoader, String className, boolean initialize) throws ClassNotFoundException {
/*      */     try {
/*      */       Class clazz;
/*  753 */       if (abbreviationMap.containsKey(className)) {
/*  754 */         String clsName = "[" + abbreviationMap.get(className);
/*  755 */         clazz = Class.forName(clsName, initialize, classLoader).getComponentType();
/*      */       } else {
/*  757 */         clazz = Class.forName(toCanonicalName(className), initialize, classLoader);
/*      */       } 
/*  759 */       return clazz;
/*  760 */     } catch (ClassNotFoundException ex) {
/*  762 */       int lastDotIndex = className.lastIndexOf('.');
/*  764 */       if (lastDotIndex != -1)
/*      */         try {
/*  766 */           return getClass(classLoader, className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1), initialize);
/*  769 */         } catch (ClassNotFoundException ex2) {} 
/*  773 */       throw ex;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Class getClass(ClassLoader classLoader, String className) throws ClassNotFoundException {
/*  790 */     return getClass(classLoader, className, true);
/*      */   }
/*      */   
/*      */   public static Class getClass(String className) throws ClassNotFoundException {
/*  805 */     return getClass(className, true);
/*      */   }
/*      */   
/*      */   public static Class getClass(String className, boolean initialize) throws ClassNotFoundException {
/*  820 */     ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
/*  821 */     ClassLoader loader = (contextCL == null) ? ClassUtils.class.getClassLoader() : contextCL;
/*  822 */     return getClass(loader, className, initialize);
/*      */   }
/*      */   
/*      */   public static Method getPublicMethod(Class cls, String methodName, Class[] parameterTypes) throws SecurityException, NoSuchMethodException {
/*  851 */     Method declaredMethod = cls.getMethod(methodName, parameterTypes);
/*  852 */     if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers()))
/*  853 */       return declaredMethod; 
/*  856 */     List candidateClasses = new ArrayList();
/*  857 */     candidateClasses.addAll(getAllInterfaces(cls));
/*  858 */     candidateClasses.addAll(getAllSuperclasses(cls));
/*  860 */     for (Iterator it = candidateClasses.iterator(); it.hasNext(); ) {
/*      */       Method candidateMethod;
/*  861 */       Class candidateClass = it.next();
/*  862 */       if (!Modifier.isPublic(candidateClass.getModifiers()))
/*      */         continue; 
/*      */       try {
/*  867 */         candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
/*  868 */       } catch (NoSuchMethodException ex) {
/*      */         continue;
/*      */       } 
/*  871 */       if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers()))
/*  872 */         return candidateMethod; 
/*      */     } 
/*  876 */     throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + ArrayUtils.toString(parameterTypes));
/*      */   }
/*      */   
/*      */   private static String toCanonicalName(String className) {
/*  888 */     className = StringUtils.deleteWhitespace(className);
/*  889 */     if (className == null)
/*  890 */       throw new NullArgumentException("className"); 
/*  891 */     if (className.endsWith("[]")) {
/*  892 */       StrBuilder classNameBuffer = new StrBuilder();
/*  893 */       while (className.endsWith("[]")) {
/*  894 */         className = className.substring(0, className.length() - 2);
/*  895 */         classNameBuffer.append("[");
/*      */       } 
/*  897 */       String abbreviation = (String)abbreviationMap.get(className);
/*  898 */       if (abbreviation != null) {
/*  899 */         classNameBuffer.append(abbreviation);
/*      */       } else {
/*  901 */         classNameBuffer.append("L").append(className).append(";");
/*      */       } 
/*  903 */       className = classNameBuffer.toString();
/*      */     } 
/*  905 */     return className;
/*      */   }
/*      */   
/*      */   public static Class[] toClass(Object[] array) {
/*  919 */     if (array == null)
/*  920 */       return null; 
/*  921 */     if (array.length == 0)
/*  922 */       return ArrayUtils.EMPTY_CLASS_ARRAY; 
/*  924 */     Class[] classes = new Class[array.length];
/*  925 */     for (int i = 0; i < array.length; i++)
/*  926 */       classes[i] = (array[i] == null) ? null : array[i].getClass(); 
/*  928 */     return classes;
/*      */   }
/*      */   
/*      */   public static String getShortCanonicalName(Object object, String valueIfNull) {
/*  942 */     if (object == null)
/*  943 */       return valueIfNull; 
/*  945 */     return getShortCanonicalName(object.getClass().getName());
/*      */   }
/*      */   
/*      */   public static String getShortCanonicalName(Class cls) {
/*  956 */     if (cls == null)
/*  957 */       return ""; 
/*  959 */     return getShortCanonicalName(cls.getName());
/*      */   }
/*      */   
/*      */   public static String getShortCanonicalName(String canonicalName) {
/*  972 */     return getShortClassName(getCanonicalName(canonicalName));
/*      */   }
/*      */   
/*      */   public static String getPackageCanonicalName(Object object, String valueIfNull) {
/*  986 */     if (object == null)
/*  987 */       return valueIfNull; 
/*  989 */     return getPackageCanonicalName(object.getClass().getName());
/*      */   }
/*      */   
/*      */   public static String getPackageCanonicalName(Class cls) {
/* 1000 */     if (cls == null)
/* 1001 */       return ""; 
/* 1003 */     return getPackageCanonicalName(cls.getName());
/*      */   }
/*      */   
/*      */   public static String getPackageCanonicalName(String canonicalName) {
/* 1017 */     return getPackageName(getCanonicalName(canonicalName));
/*      */   }
/*      */   
/*      */   private static String getCanonicalName(String className) {
/* 1037 */     className = StringUtils.deleteWhitespace(className);
/* 1038 */     if (className == null)
/* 1039 */       return null; 
/* 1041 */     int dim = 0;
/* 1042 */     while (className.startsWith("[")) {
/* 1043 */       dim++;
/* 1044 */       className = className.substring(1);
/*      */     } 
/* 1046 */     if (dim < 1)
/* 1047 */       return className; 
/* 1049 */     if (className.startsWith("L")) {
/* 1050 */       className = className.substring(1, className.endsWith(";") ? (className.length() - 1) : className.length());
/* 1056 */     } else if (className.length() > 0) {
/* 1057 */       className = (String)reverseAbbreviationMap.get(className.substring(0, 1));
/*      */     } 
/* 1061 */     StrBuilder canonicalClassNameBuffer = new StrBuilder(className);
/* 1062 */     for (int i = 0; i < dim; i++)
/* 1063 */       canonicalClassNameBuffer.append("[]"); 
/* 1065 */     return canonicalClassNameBuffer.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\ClassUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */