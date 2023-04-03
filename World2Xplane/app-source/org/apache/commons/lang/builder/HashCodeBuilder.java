/*      */ package org.apache.commons.lang.builder;
/*      */ 
/*      */ import java.lang.reflect.AccessibleObject;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang.ArrayUtils;
/*      */ 
/*      */ public class HashCodeBuilder {
/*  111 */   private static final ThreadLocal REGISTRY = new ThreadLocal();
/*      */   
/*      */   private final int iConstant;
/*      */   
/*      */   static Set getRegistry() {
/*  139 */     return REGISTRY.get();
/*      */   }
/*      */   
/*      */   static boolean isRegistered(Object value) {
/*  154 */     Set registry = getRegistry();
/*  155 */     return (registry != null && registry.contains(new IDKey(value)));
/*      */   }
/*      */   
/*      */   private static void reflectionAppend(Object object, Class clazz, HashCodeBuilder builder, boolean useTransients, String[] excludeFields) {
/*  176 */     if (isRegistered(object))
/*      */       return; 
/*      */     try {
/*  180 */       register(object);
/*  181 */       Field[] fields = clazz.getDeclaredFields();
/*  182 */       AccessibleObject.setAccessible((AccessibleObject[])fields, true);
/*  183 */       for (int i = 0; i < fields.length; i++) {
/*  184 */         Field field = fields[i];
/*  185 */         if (!ArrayUtils.contains((Object[])excludeFields, field.getName()) && field.getName().indexOf('$') == -1 && (useTransients || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))
/*      */           try {
/*  190 */             Object fieldValue = field.get(object);
/*  191 */             builder.append(fieldValue);
/*  192 */           } catch (IllegalAccessException e) {
/*  195 */             throw new InternalError("Unexpected IllegalAccessException");
/*      */           }  
/*      */       } 
/*      */     } finally {
/*  200 */       unregister(object);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object) {
/*  242 */     return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, false, null, null);
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object, boolean testTransients) {
/*  286 */     return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, testTransients, null, null);
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object, boolean testTransients, Class reflectUpToClass) {
/*  308 */     return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, testTransients, reflectUpToClass, null);
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object, boolean testTransients, Class reflectUpToClass, String[] excludeFields) {
/*  360 */     if (object == null)
/*  361 */       throw new IllegalArgumentException("The object to build a hash code for must not be null"); 
/*  363 */     HashCodeBuilder builder = new HashCodeBuilder(initialNonZeroOddNumber, multiplierNonZeroOddNumber);
/*  364 */     Class clazz = object.getClass();
/*  365 */     reflectionAppend(object, clazz, builder, testTransients, excludeFields);
/*  366 */     while (clazz.getSuperclass() != null && clazz != reflectUpToClass) {
/*  367 */       clazz = clazz.getSuperclass();
/*  368 */       reflectionAppend(object, clazz, builder, testTransients, excludeFields);
/*      */     } 
/*  370 */     return builder.toHashCode();
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(Object object) {
/*  404 */     return reflectionHashCode(17, 37, object, false, null, null);
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(Object object, boolean testTransients) {
/*  440 */     return reflectionHashCode(17, 37, object, testTransients, null, null);
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(Object object, Collection excludeFields) {
/*  476 */     return reflectionHashCode(object, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*      */   }
/*      */   
/*      */   public static int reflectionHashCode(Object object, String[] excludeFields) {
/*  514 */     return reflectionHashCode(17, 37, object, false, null, excludeFields);
/*      */   }
/*      */   
/*      */   static void register(Object value) {
/*  526 */     synchronized (HashCodeBuilder.class) {
/*  527 */       if (getRegistry() == null)
/*  528 */         REGISTRY.set(new HashSet()); 
/*      */     } 
/*  531 */     getRegistry().add(new IDKey(value));
/*      */   }
/*      */   
/*      */   static void unregister(Object value) {
/*  547 */     Set registry = getRegistry();
/*  548 */     if (registry != null) {
/*  549 */       registry.remove(new IDKey(value));
/*  550 */       synchronized (HashCodeBuilder.class) {
/*  552 */         registry = getRegistry();
/*  553 */         if (registry != null && registry.isEmpty())
/*  554 */           REGISTRY.set(null); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*  568 */   private int iTotal = 0;
/*      */   
/*      */   public HashCodeBuilder() {
/*  576 */     this.iConstant = 37;
/*  577 */     this.iTotal = 17;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {
/*  598 */     if (initialNonZeroOddNumber == 0)
/*  599 */       throw new IllegalArgumentException("HashCodeBuilder requires a non zero initial value"); 
/*  601 */     if (initialNonZeroOddNumber % 2 == 0)
/*  602 */       throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value"); 
/*  604 */     if (multiplierNonZeroOddNumber == 0)
/*  605 */       throw new IllegalArgumentException("HashCodeBuilder requires a non zero multiplier"); 
/*  607 */     if (multiplierNonZeroOddNumber % 2 == 0)
/*  608 */       throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier"); 
/*  610 */     this.iConstant = multiplierNonZeroOddNumber;
/*  611 */     this.iTotal = initialNonZeroOddNumber;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(boolean value) {
/*  636 */     this.iTotal = this.iTotal * this.iConstant + (value ? 0 : 1);
/*  637 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(boolean[] array) {
/*  650 */     if (array == null) {
/*  651 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  653 */       for (int i = 0; i < array.length; i++)
/*  654 */         append(array[i]); 
/*      */     } 
/*  657 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(byte value) {
/*  672 */     this.iTotal = this.iTotal * this.iConstant + value;
/*  673 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(byte[] array) {
/*  688 */     if (array == null) {
/*  689 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  691 */       for (int i = 0; i < array.length; i++)
/*  692 */         append(array[i]); 
/*      */     } 
/*  695 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(char value) {
/*  708 */     this.iTotal = this.iTotal * this.iConstant + value;
/*  709 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(char[] array) {
/*  722 */     if (array == null) {
/*  723 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  725 */       for (int i = 0; i < array.length; i++)
/*  726 */         append(array[i]); 
/*      */     } 
/*  729 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(double value) {
/*  742 */     return append(Double.doubleToLongBits(value));
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(double[] array) {
/*  755 */     if (array == null) {
/*  756 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  758 */       for (int i = 0; i < array.length; i++)
/*  759 */         append(array[i]); 
/*      */     } 
/*  762 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(float value) {
/*  775 */     this.iTotal = this.iTotal * this.iConstant + Float.floatToIntBits(value);
/*  776 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(float[] array) {
/*  789 */     if (array == null) {
/*  790 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  792 */       for (int i = 0; i < array.length; i++)
/*  793 */         append(array[i]); 
/*      */     } 
/*  796 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(int value) {
/*  809 */     this.iTotal = this.iTotal * this.iConstant + value;
/*  810 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(int[] array) {
/*  823 */     if (array == null) {
/*  824 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  826 */       for (int i = 0; i < array.length; i++)
/*  827 */         append(array[i]); 
/*      */     } 
/*  830 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(long value) {
/*  847 */     this.iTotal = this.iTotal * this.iConstant + (int)(value ^ value >> 32L);
/*  848 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(long[] array) {
/*  861 */     if (array == null) {
/*  862 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  864 */       for (int i = 0; i < array.length; i++)
/*  865 */         append(array[i]); 
/*      */     } 
/*  868 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(Object object) {
/*  881 */     if (object == null) {
/*  882 */       this.iTotal *= this.iConstant;
/*  885 */     } else if (object.getClass().isArray()) {
/*  888 */       if (object instanceof long[]) {
/*  889 */         append((long[])object);
/*  890 */       } else if (object instanceof int[]) {
/*  891 */         append((int[])object);
/*  892 */       } else if (object instanceof short[]) {
/*  893 */         append((short[])object);
/*  894 */       } else if (object instanceof char[]) {
/*  895 */         append((char[])object);
/*  896 */       } else if (object instanceof byte[]) {
/*  897 */         append((byte[])object);
/*  898 */       } else if (object instanceof double[]) {
/*  899 */         append((double[])object);
/*  900 */       } else if (object instanceof float[]) {
/*  901 */         append((float[])object);
/*  902 */       } else if (object instanceof boolean[]) {
/*  903 */         append((boolean[])object);
/*      */       } else {
/*  906 */         append((Object[])object);
/*      */       } 
/*      */     } else {
/*  909 */       this.iTotal = this.iTotal * this.iConstant + object.hashCode();
/*      */     } 
/*  912 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(Object[] array) {
/*  925 */     if (array == null) {
/*  926 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  928 */       for (int i = 0; i < array.length; i++)
/*  929 */         append(array[i]); 
/*      */     } 
/*  932 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(short value) {
/*  945 */     this.iTotal = this.iTotal * this.iConstant + value;
/*  946 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder append(short[] array) {
/*  959 */     if (array == null) {
/*  960 */       this.iTotal *= this.iConstant;
/*      */     } else {
/*  962 */       for (int i = 0; i < array.length; i++)
/*  963 */         append(array[i]); 
/*      */     } 
/*  966 */     return this;
/*      */   }
/*      */   
/*      */   public HashCodeBuilder appendSuper(int superHashCode) {
/*  980 */     this.iTotal = this.iTotal * this.iConstant + superHashCode;
/*  981 */     return this;
/*      */   }
/*      */   
/*      */   public int toHashCode() {
/*  992 */     return this.iTotal;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1005 */     return toHashCode();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\HashCodeBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */