/*      */ package org.apache.commons.lang;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.lang.builder.EqualsBuilder;
/*      */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*      */ import org.apache.commons.lang.builder.ToStringBuilder;
/*      */ import org.apache.commons.lang.builder.ToStringStyle;
/*      */ 
/*      */ public class ArrayUtils {
/*   56 */   public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*      */   
/*   60 */   public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
/*      */   
/*   64 */   public static final String[] EMPTY_STRING_ARRAY = new String[0];
/*      */   
/*   68 */   public static final long[] EMPTY_LONG_ARRAY = new long[0];
/*      */   
/*   72 */   public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
/*      */   
/*   76 */   public static final int[] EMPTY_INT_ARRAY = new int[0];
/*      */   
/*   80 */   public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
/*      */   
/*   84 */   public static final short[] EMPTY_SHORT_ARRAY = new short[0];
/*      */   
/*   88 */   public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
/*      */   
/*   92 */   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*      */   
/*   96 */   public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
/*      */   
/*  100 */   public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
/*      */   
/*  104 */   public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
/*      */   
/*  108 */   public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
/*      */   
/*  112 */   public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
/*      */   
/*  116 */   public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
/*      */   
/*  120 */   public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
/*      */   
/*  124 */   public static final char[] EMPTY_CHAR_ARRAY = new char[0];
/*      */   
/*  128 */   public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
/*      */   
/*      */   public static final int INDEX_NOT_FOUND = -1;
/*      */   
/*      */   public static String toString(Object array) {
/*  162 */     return toString(array, "{}");
/*      */   }
/*      */   
/*      */   public static String toString(Object array, String stringIfNull) {
/*  178 */     if (array == null)
/*  179 */       return stringIfNull; 
/*  181 */     return (new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE)).append(array).toString();
/*      */   }
/*      */   
/*      */   public static int hashCode(Object array) {
/*  193 */     return (new HashCodeBuilder()).append(array).toHashCode();
/*      */   }
/*      */   
/*      */   public static boolean isEquals(Object array1, Object array2) {
/*  207 */     return (new EqualsBuilder()).append(array1, array2).isEquals();
/*      */   }
/*      */   
/*      */   public static Map toMap(Object[] array) {
/*  238 */     if (array == null)
/*  239 */       return null; 
/*  241 */     Map map = new HashMap((int)(array.length * 1.5D));
/*  242 */     for (int i = 0; i < array.length; i++) {
/*  243 */       Object object = array[i];
/*  244 */       if (object instanceof Map.Entry) {
/*  245 */         Map.Entry entry = (Map.Entry)object;
/*  246 */         map.put(entry.getKey(), entry.getValue());
/*  247 */       } else if (object instanceof Object[]) {
/*  248 */         Object[] entry = (Object[])object;
/*  249 */         if (entry.length < 2)
/*  250 */           throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2"); 
/*  254 */         map.put(entry[0], entry[1]);
/*      */       } else {
/*  256 */         throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
/*      */       } 
/*      */     } 
/*  261 */     return map;
/*      */   }
/*      */   
/*      */   public static Object[] clone(Object[] array) {
/*  279 */     if (array == null)
/*  280 */       return null; 
/*  282 */     return (Object[])array.clone();
/*      */   }
/*      */   
/*      */   public static long[] clone(long[] array) {
/*  295 */     if (array == null)
/*  296 */       return null; 
/*  298 */     return (long[])array.clone();
/*      */   }
/*      */   
/*      */   public static int[] clone(int[] array) {
/*  311 */     if (array == null)
/*  312 */       return null; 
/*  314 */     return (int[])array.clone();
/*      */   }
/*      */   
/*      */   public static short[] clone(short[] array) {
/*  327 */     if (array == null)
/*  328 */       return null; 
/*  330 */     return (short[])array.clone();
/*      */   }
/*      */   
/*      */   public static char[] clone(char[] array) {
/*  343 */     if (array == null)
/*  344 */       return null; 
/*  346 */     return (char[])array.clone();
/*      */   }
/*      */   
/*      */   public static byte[] clone(byte[] array) {
/*  359 */     if (array == null)
/*  360 */       return null; 
/*  362 */     return (byte[])array.clone();
/*      */   }
/*      */   
/*      */   public static double[] clone(double[] array) {
/*  375 */     if (array == null)
/*  376 */       return null; 
/*  378 */     return (double[])array.clone();
/*      */   }
/*      */   
/*      */   public static float[] clone(float[] array) {
/*  391 */     if (array == null)
/*  392 */       return null; 
/*  394 */     return (float[])array.clone();
/*      */   }
/*      */   
/*      */   public static boolean[] clone(boolean[] array) {
/*  407 */     if (array == null)
/*  408 */       return null; 
/*  410 */     return (boolean[])array.clone();
/*      */   }
/*      */   
/*      */   public static Object[] nullToEmpty(Object[] array) {
/*  429 */     if (array == null || array.length == 0)
/*  430 */       return EMPTY_OBJECT_ARRAY; 
/*  432 */     return array;
/*      */   }
/*      */   
/*      */   public static String[] nullToEmpty(String[] array) {
/*  449 */     if (array == null || array.length == 0)
/*  450 */       return EMPTY_STRING_ARRAY; 
/*  452 */     return array;
/*      */   }
/*      */   
/*      */   public static long[] nullToEmpty(long[] array) {
/*  469 */     if (array == null || array.length == 0)
/*  470 */       return EMPTY_LONG_ARRAY; 
/*  472 */     return array;
/*      */   }
/*      */   
/*      */   public static int[] nullToEmpty(int[] array) {
/*  489 */     if (array == null || array.length == 0)
/*  490 */       return EMPTY_INT_ARRAY; 
/*  492 */     return array;
/*      */   }
/*      */   
/*      */   public static short[] nullToEmpty(short[] array) {
/*  509 */     if (array == null || array.length == 0)
/*  510 */       return EMPTY_SHORT_ARRAY; 
/*  512 */     return array;
/*      */   }
/*      */   
/*      */   public static char[] nullToEmpty(char[] array) {
/*  529 */     if (array == null || array.length == 0)
/*  530 */       return EMPTY_CHAR_ARRAY; 
/*  532 */     return array;
/*      */   }
/*      */   
/*      */   public static byte[] nullToEmpty(byte[] array) {
/*  549 */     if (array == null || array.length == 0)
/*  550 */       return EMPTY_BYTE_ARRAY; 
/*  552 */     return array;
/*      */   }
/*      */   
/*      */   public static double[] nullToEmpty(double[] array) {
/*  569 */     if (array == null || array.length == 0)
/*  570 */       return EMPTY_DOUBLE_ARRAY; 
/*  572 */     return array;
/*      */   }
/*      */   
/*      */   public static float[] nullToEmpty(float[] array) {
/*  589 */     if (array == null || array.length == 0)
/*  590 */       return EMPTY_FLOAT_ARRAY; 
/*  592 */     return array;
/*      */   }
/*      */   
/*      */   public static boolean[] nullToEmpty(boolean[] array) {
/*  609 */     if (array == null || array.length == 0)
/*  610 */       return EMPTY_BOOLEAN_ARRAY; 
/*  612 */     return array;
/*      */   }
/*      */   
/*      */   public static Long[] nullToEmpty(Long[] array) {
/*  629 */     if (array == null || array.length == 0)
/*  630 */       return EMPTY_LONG_OBJECT_ARRAY; 
/*  632 */     return array;
/*      */   }
/*      */   
/*      */   public static Integer[] nullToEmpty(Integer[] array) {
/*  649 */     if (array == null || array.length == 0)
/*  650 */       return EMPTY_INTEGER_OBJECT_ARRAY; 
/*  652 */     return array;
/*      */   }
/*      */   
/*      */   public static Short[] nullToEmpty(Short[] array) {
/*  669 */     if (array == null || array.length == 0)
/*  670 */       return EMPTY_SHORT_OBJECT_ARRAY; 
/*  672 */     return array;
/*      */   }
/*      */   
/*      */   public static Character[] nullToEmpty(Character[] array) {
/*  689 */     if (array == null || array.length == 0)
/*  690 */       return EMPTY_CHARACTER_OBJECT_ARRAY; 
/*  692 */     return array;
/*      */   }
/*      */   
/*      */   public static Byte[] nullToEmpty(Byte[] array) {
/*  709 */     if (array == null || array.length == 0)
/*  710 */       return EMPTY_BYTE_OBJECT_ARRAY; 
/*  712 */     return array;
/*      */   }
/*      */   
/*      */   public static Double[] nullToEmpty(Double[] array) {
/*  729 */     if (array == null || array.length == 0)
/*  730 */       return EMPTY_DOUBLE_OBJECT_ARRAY; 
/*  732 */     return array;
/*      */   }
/*      */   
/*      */   public static Float[] nullToEmpty(Float[] array) {
/*  749 */     if (array == null || array.length == 0)
/*  750 */       return EMPTY_FLOAT_OBJECT_ARRAY; 
/*  752 */     return array;
/*      */   }
/*      */   
/*      */   public static Boolean[] nullToEmpty(Boolean[] array) {
/*  769 */     if (array == null || array.length == 0)
/*  770 */       return EMPTY_BOOLEAN_OBJECT_ARRAY; 
/*  772 */     return array;
/*      */   }
/*      */   
/*      */   public static Object[] subarray(Object[] array, int startIndexInclusive, int endIndexExclusive) {
/*  805 */     if (array == null)
/*  806 */       return null; 
/*  808 */     if (startIndexInclusive < 0)
/*  809 */       startIndexInclusive = 0; 
/*  811 */     if (endIndexExclusive > array.length)
/*  812 */       endIndexExclusive = array.length; 
/*  814 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  815 */     Class type = array.getClass().getComponentType();
/*  816 */     if (newSize <= 0)
/*  817 */       return (Object[])Array.newInstance(type, 0); 
/*  819 */     Object[] subarray = (Object[])Array.newInstance(type, newSize);
/*  820 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  821 */     return subarray;
/*      */   }
/*      */   
/*      */   public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive) {
/*  844 */     if (array == null)
/*  845 */       return null; 
/*  847 */     if (startIndexInclusive < 0)
/*  848 */       startIndexInclusive = 0; 
/*  850 */     if (endIndexExclusive > array.length)
/*  851 */       endIndexExclusive = array.length; 
/*  853 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  854 */     if (newSize <= 0)
/*  855 */       return EMPTY_LONG_ARRAY; 
/*  858 */     long[] subarray = new long[newSize];
/*  859 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  860 */     return subarray;
/*      */   }
/*      */   
/*      */   public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive) {
/*  883 */     if (array == null)
/*  884 */       return null; 
/*  886 */     if (startIndexInclusive < 0)
/*  887 */       startIndexInclusive = 0; 
/*  889 */     if (endIndexExclusive > array.length)
/*  890 */       endIndexExclusive = array.length; 
/*  892 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  893 */     if (newSize <= 0)
/*  894 */       return EMPTY_INT_ARRAY; 
/*  897 */     int[] subarray = new int[newSize];
/*  898 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  899 */     return subarray;
/*      */   }
/*      */   
/*      */   public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive) {
/*  922 */     if (array == null)
/*  923 */       return null; 
/*  925 */     if (startIndexInclusive < 0)
/*  926 */       startIndexInclusive = 0; 
/*  928 */     if (endIndexExclusive > array.length)
/*  929 */       endIndexExclusive = array.length; 
/*  931 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  932 */     if (newSize <= 0)
/*  933 */       return EMPTY_SHORT_ARRAY; 
/*  936 */     short[] subarray = new short[newSize];
/*  937 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  938 */     return subarray;
/*      */   }
/*      */   
/*      */   public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive) {
/*  961 */     if (array == null)
/*  962 */       return null; 
/*  964 */     if (startIndexInclusive < 0)
/*  965 */       startIndexInclusive = 0; 
/*  967 */     if (endIndexExclusive > array.length)
/*  968 */       endIndexExclusive = array.length; 
/*  970 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  971 */     if (newSize <= 0)
/*  972 */       return EMPTY_CHAR_ARRAY; 
/*  975 */     char[] subarray = new char[newSize];
/*  976 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  977 */     return subarray;
/*      */   }
/*      */   
/*      */   public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1000 */     if (array == null)
/* 1001 */       return null; 
/* 1003 */     if (startIndexInclusive < 0)
/* 1004 */       startIndexInclusive = 0; 
/* 1006 */     if (endIndexExclusive > array.length)
/* 1007 */       endIndexExclusive = array.length; 
/* 1009 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1010 */     if (newSize <= 0)
/* 1011 */       return EMPTY_BYTE_ARRAY; 
/* 1014 */     byte[] subarray = new byte[newSize];
/* 1015 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1016 */     return subarray;
/*      */   }
/*      */   
/*      */   public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1039 */     if (array == null)
/* 1040 */       return null; 
/* 1042 */     if (startIndexInclusive < 0)
/* 1043 */       startIndexInclusive = 0; 
/* 1045 */     if (endIndexExclusive > array.length)
/* 1046 */       endIndexExclusive = array.length; 
/* 1048 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1049 */     if (newSize <= 0)
/* 1050 */       return EMPTY_DOUBLE_ARRAY; 
/* 1053 */     double[] subarray = new double[newSize];
/* 1054 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1055 */     return subarray;
/*      */   }
/*      */   
/*      */   public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1078 */     if (array == null)
/* 1079 */       return null; 
/* 1081 */     if (startIndexInclusive < 0)
/* 1082 */       startIndexInclusive = 0; 
/* 1084 */     if (endIndexExclusive > array.length)
/* 1085 */       endIndexExclusive = array.length; 
/* 1087 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1088 */     if (newSize <= 0)
/* 1089 */       return EMPTY_FLOAT_ARRAY; 
/* 1092 */     float[] subarray = new float[newSize];
/* 1093 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1094 */     return subarray;
/*      */   }
/*      */   
/*      */   public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1117 */     if (array == null)
/* 1118 */       return null; 
/* 1120 */     if (startIndexInclusive < 0)
/* 1121 */       startIndexInclusive = 0; 
/* 1123 */     if (endIndexExclusive > array.length)
/* 1124 */       endIndexExclusive = array.length; 
/* 1126 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1127 */     if (newSize <= 0)
/* 1128 */       return EMPTY_BOOLEAN_ARRAY; 
/* 1131 */     boolean[] subarray = new boolean[newSize];
/* 1132 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1133 */     return subarray;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(Object[] array1, Object[] array2) {
/* 1150 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1153 */       return false; 
/* 1155 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(long[] array1, long[] array2) {
/* 1168 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1171 */       return false; 
/* 1173 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(int[] array1, int[] array2) {
/* 1186 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1189 */       return false; 
/* 1191 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(short[] array1, short[] array2) {
/* 1204 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1207 */       return false; 
/* 1209 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(char[] array1, char[] array2) {
/* 1222 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1225 */       return false; 
/* 1227 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(byte[] array1, byte[] array2) {
/* 1240 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1243 */       return false; 
/* 1245 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(double[] array1, double[] array2) {
/* 1258 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1261 */       return false; 
/* 1263 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(float[] array1, float[] array2) {
/* 1276 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1279 */       return false; 
/* 1281 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSameLength(boolean[] array1, boolean[] array2) {
/* 1294 */     if ((array1 == null && array2 != null && array2.length > 0) || (array2 == null && array1 != null && array1.length > 0) || (array1 != null && array2 != null && array1.length != array2.length))
/* 1297 */       return false; 
/* 1299 */     return true;
/*      */   }
/*      */   
/*      */   public static int getLength(Object array) {
/* 1324 */     if (array == null)
/* 1325 */       return 0; 
/* 1327 */     return Array.getLength(array);
/*      */   }
/*      */   
/*      */   public static boolean isSameType(Object array1, Object array2) {
/* 1340 */     if (array1 == null || array2 == null)
/* 1341 */       throw new IllegalArgumentException("The Array must not be null"); 
/* 1343 */     return array1.getClass().getName().equals(array2.getClass().getName());
/*      */   }
/*      */   
/*      */   public static void reverse(Object[] array) {
/* 1358 */     if (array == null)
/*      */       return; 
/* 1361 */     int i = 0;
/* 1362 */     int j = array.length - 1;
/* 1364 */     while (j > i) {
/* 1365 */       Object tmp = array[j];
/* 1366 */       array[j] = array[i];
/* 1367 */       array[i] = tmp;
/* 1368 */       j--;
/* 1369 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(long[] array) {
/* 1381 */     if (array == null)
/*      */       return; 
/* 1384 */     int i = 0;
/* 1385 */     int j = array.length - 1;
/* 1387 */     while (j > i) {
/* 1388 */       long tmp = array[j];
/* 1389 */       array[j] = array[i];
/* 1390 */       array[i] = tmp;
/* 1391 */       j--;
/* 1392 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(int[] array) {
/* 1404 */     if (array == null)
/*      */       return; 
/* 1407 */     int i = 0;
/* 1408 */     int j = array.length - 1;
/* 1410 */     while (j > i) {
/* 1411 */       int tmp = array[j];
/* 1412 */       array[j] = array[i];
/* 1413 */       array[i] = tmp;
/* 1414 */       j--;
/* 1415 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(short[] array) {
/* 1427 */     if (array == null)
/*      */       return; 
/* 1430 */     int i = 0;
/* 1431 */     int j = array.length - 1;
/* 1433 */     while (j > i) {
/* 1434 */       short tmp = array[j];
/* 1435 */       array[j] = array[i];
/* 1436 */       array[i] = tmp;
/* 1437 */       j--;
/* 1438 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(char[] array) {
/* 1450 */     if (array == null)
/*      */       return; 
/* 1453 */     int i = 0;
/* 1454 */     int j = array.length - 1;
/* 1456 */     while (j > i) {
/* 1457 */       char tmp = array[j];
/* 1458 */       array[j] = array[i];
/* 1459 */       array[i] = tmp;
/* 1460 */       j--;
/* 1461 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(byte[] array) {
/* 1473 */     if (array == null)
/*      */       return; 
/* 1476 */     int i = 0;
/* 1477 */     int j = array.length - 1;
/* 1479 */     while (j > i) {
/* 1480 */       byte tmp = array[j];
/* 1481 */       array[j] = array[i];
/* 1482 */       array[i] = tmp;
/* 1483 */       j--;
/* 1484 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(double[] array) {
/* 1496 */     if (array == null)
/*      */       return; 
/* 1499 */     int i = 0;
/* 1500 */     int j = array.length - 1;
/* 1502 */     while (j > i) {
/* 1503 */       double tmp = array[j];
/* 1504 */       array[j] = array[i];
/* 1505 */       array[i] = tmp;
/* 1506 */       j--;
/* 1507 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(float[] array) {
/* 1519 */     if (array == null)
/*      */       return; 
/* 1522 */     int i = 0;
/* 1523 */     int j = array.length - 1;
/* 1525 */     while (j > i) {
/* 1526 */       float tmp = array[j];
/* 1527 */       array[j] = array[i];
/* 1528 */       array[i] = tmp;
/* 1529 */       j--;
/* 1530 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void reverse(boolean[] array) {
/* 1542 */     if (array == null)
/*      */       return; 
/* 1545 */     int i = 0;
/* 1546 */     int j = array.length - 1;
/* 1548 */     while (j > i) {
/* 1549 */       boolean tmp = array[j];
/* 1550 */       array[j] = array[i];
/* 1551 */       array[i] = tmp;
/* 1552 */       j--;
/* 1553 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int indexOf(Object[] array, Object objectToFind) {
/* 1573 */     return indexOf(array, objectToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
/* 1591 */     if (array == null)
/* 1592 */       return -1; 
/* 1594 */     if (startIndex < 0)
/* 1595 */       startIndex = 0; 
/* 1597 */     if (objectToFind == null) {
/* 1598 */       for (int i = startIndex; i < array.length; i++) {
/* 1599 */         if (array[i] == null)
/* 1600 */           return i; 
/*      */       } 
/* 1603 */     } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 1604 */       for (int i = startIndex; i < array.length; i++) {
/* 1605 */         if (objectToFind.equals(array[i]))
/* 1606 */           return i; 
/*      */       } 
/*      */     } 
/* 1610 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(Object[] array, Object objectToFind) {
/* 1624 */     return lastIndexOf(array, objectToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
/* 1642 */     if (array == null)
/* 1643 */       return -1; 
/* 1645 */     if (startIndex < 0)
/* 1646 */       return -1; 
/* 1647 */     if (startIndex >= array.length)
/* 1648 */       startIndex = array.length - 1; 
/* 1650 */     if (objectToFind == null) {
/* 1651 */       for (int i = startIndex; i >= 0; i--) {
/* 1652 */         if (array[i] == null)
/* 1653 */           return i; 
/*      */       } 
/* 1656 */     } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 1657 */       for (int i = startIndex; i >= 0; i--) {
/* 1658 */         if (objectToFind.equals(array[i]))
/* 1659 */           return i; 
/*      */       } 
/*      */     } 
/* 1663 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(Object[] array, Object objectToFind) {
/* 1676 */     return (indexOf(array, objectToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(long[] array, long valueToFind) {
/* 1692 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(long[] array, long valueToFind, int startIndex) {
/* 1710 */     if (array == null)
/* 1711 */       return -1; 
/* 1713 */     if (startIndex < 0)
/* 1714 */       startIndex = 0; 
/* 1716 */     for (int i = startIndex; i < array.length; i++) {
/* 1717 */       if (valueToFind == array[i])
/* 1718 */         return i; 
/*      */     } 
/* 1721 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(long[] array, long valueToFind) {
/* 1735 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
/* 1753 */     if (array == null)
/* 1754 */       return -1; 
/* 1756 */     if (startIndex < 0)
/* 1757 */       return -1; 
/* 1758 */     if (startIndex >= array.length)
/* 1759 */       startIndex = array.length - 1; 
/* 1761 */     for (int i = startIndex; i >= 0; i--) {
/* 1762 */       if (valueToFind == array[i])
/* 1763 */         return i; 
/*      */     } 
/* 1766 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(long[] array, long valueToFind) {
/* 1779 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(int[] array, int valueToFind) {
/* 1795 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(int[] array, int valueToFind, int startIndex) {
/* 1813 */     if (array == null)
/* 1814 */       return -1; 
/* 1816 */     if (startIndex < 0)
/* 1817 */       startIndex = 0; 
/* 1819 */     for (int i = startIndex; i < array.length; i++) {
/* 1820 */       if (valueToFind == array[i])
/* 1821 */         return i; 
/*      */     } 
/* 1824 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(int[] array, int valueToFind) {
/* 1838 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
/* 1856 */     if (array == null)
/* 1857 */       return -1; 
/* 1859 */     if (startIndex < 0)
/* 1860 */       return -1; 
/* 1861 */     if (startIndex >= array.length)
/* 1862 */       startIndex = array.length - 1; 
/* 1864 */     for (int i = startIndex; i >= 0; i--) {
/* 1865 */       if (valueToFind == array[i])
/* 1866 */         return i; 
/*      */     } 
/* 1869 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(int[] array, int valueToFind) {
/* 1882 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(short[] array, short valueToFind) {
/* 1898 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(short[] array, short valueToFind, int startIndex) {
/* 1916 */     if (array == null)
/* 1917 */       return -1; 
/* 1919 */     if (startIndex < 0)
/* 1920 */       startIndex = 0; 
/* 1922 */     for (int i = startIndex; i < array.length; i++) {
/* 1923 */       if (valueToFind == array[i])
/* 1924 */         return i; 
/*      */     } 
/* 1927 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(short[] array, short valueToFind) {
/* 1941 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
/* 1959 */     if (array == null)
/* 1960 */       return -1; 
/* 1962 */     if (startIndex < 0)
/* 1963 */       return -1; 
/* 1964 */     if (startIndex >= array.length)
/* 1965 */       startIndex = array.length - 1; 
/* 1967 */     for (int i = startIndex; i >= 0; i--) {
/* 1968 */       if (valueToFind == array[i])
/* 1969 */         return i; 
/*      */     } 
/* 1972 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(short[] array, short valueToFind) {
/* 1985 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(char[] array, char valueToFind) {
/* 2002 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(char[] array, char valueToFind, int startIndex) {
/* 2021 */     if (array == null)
/* 2022 */       return -1; 
/* 2024 */     if (startIndex < 0)
/* 2025 */       startIndex = 0; 
/* 2027 */     for (int i = startIndex; i < array.length; i++) {
/* 2028 */       if (valueToFind == array[i])
/* 2029 */         return i; 
/*      */     } 
/* 2032 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(char[] array, char valueToFind) {
/* 2047 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
/* 2066 */     if (array == null)
/* 2067 */       return -1; 
/* 2069 */     if (startIndex < 0)
/* 2070 */       return -1; 
/* 2071 */     if (startIndex >= array.length)
/* 2072 */       startIndex = array.length - 1; 
/* 2074 */     for (int i = startIndex; i >= 0; i--) {
/* 2075 */       if (valueToFind == array[i])
/* 2076 */         return i; 
/*      */     } 
/* 2079 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(char[] array, char valueToFind) {
/* 2093 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(byte[] array, byte valueToFind) {
/* 2109 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
/* 2127 */     if (array == null)
/* 2128 */       return -1; 
/* 2130 */     if (startIndex < 0)
/* 2131 */       startIndex = 0; 
/* 2133 */     for (int i = startIndex; i < array.length; i++) {
/* 2134 */       if (valueToFind == array[i])
/* 2135 */         return i; 
/*      */     } 
/* 2138 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(byte[] array, byte valueToFind) {
/* 2152 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
/* 2170 */     if (array == null)
/* 2171 */       return -1; 
/* 2173 */     if (startIndex < 0)
/* 2174 */       return -1; 
/* 2175 */     if (startIndex >= array.length)
/* 2176 */       startIndex = array.length - 1; 
/* 2178 */     for (int i = startIndex; i >= 0; i--) {
/* 2179 */       if (valueToFind == array[i])
/* 2180 */         return i; 
/*      */     } 
/* 2183 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(byte[] array, byte valueToFind) {
/* 2196 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(double[] array, double valueToFind) {
/* 2212 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(double[] array, double valueToFind, double tolerance) {
/* 2229 */     return indexOf(array, valueToFind, 0, tolerance);
/*      */   }
/*      */   
/*      */   public static int indexOf(double[] array, double valueToFind, int startIndex) {
/* 2247 */     if (isEmpty(array))
/* 2248 */       return -1; 
/* 2250 */     if (startIndex < 0)
/* 2251 */       startIndex = 0; 
/* 2253 */     for (int i = startIndex; i < array.length; i++) {
/* 2254 */       if (valueToFind == array[i])
/* 2255 */         return i; 
/*      */     } 
/* 2258 */     return -1;
/*      */   }
/*      */   
/*      */   public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
/* 2279 */     if (isEmpty(array))
/* 2280 */       return -1; 
/* 2282 */     if (startIndex < 0)
/* 2283 */       startIndex = 0; 
/* 2285 */     double min = valueToFind - tolerance;
/* 2286 */     double max = valueToFind + tolerance;
/* 2287 */     for (int i = startIndex; i < array.length; i++) {
/* 2288 */       if (array[i] >= min && array[i] <= max)
/* 2289 */         return i; 
/*      */     } 
/* 2292 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(double[] array, double valueToFind) {
/* 2306 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
/* 2323 */     return lastIndexOf(array, valueToFind, 2147483647, tolerance);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
/* 2341 */     if (isEmpty(array))
/* 2342 */       return -1; 
/* 2344 */     if (startIndex < 0)
/* 2345 */       return -1; 
/* 2346 */     if (startIndex >= array.length)
/* 2347 */       startIndex = array.length - 1; 
/* 2349 */     for (int i = startIndex; i >= 0; i--) {
/* 2350 */       if (valueToFind == array[i])
/* 2351 */         return i; 
/*      */     } 
/* 2354 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
/* 2375 */     if (isEmpty(array))
/* 2376 */       return -1; 
/* 2378 */     if (startIndex < 0)
/* 2379 */       return -1; 
/* 2380 */     if (startIndex >= array.length)
/* 2381 */       startIndex = array.length - 1; 
/* 2383 */     double min = valueToFind - tolerance;
/* 2384 */     double max = valueToFind + tolerance;
/* 2385 */     for (int i = startIndex; i >= 0; i--) {
/* 2386 */       if (array[i] >= min && array[i] <= max)
/* 2387 */         return i; 
/*      */     } 
/* 2390 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(double[] array, double valueToFind) {
/* 2403 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static boolean contains(double[] array, double valueToFind, double tolerance) {
/* 2420 */     return (indexOf(array, valueToFind, 0, tolerance) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(float[] array, float valueToFind) {
/* 2436 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(float[] array, float valueToFind, int startIndex) {
/* 2454 */     if (isEmpty(array))
/* 2455 */       return -1; 
/* 2457 */     if (startIndex < 0)
/* 2458 */       startIndex = 0; 
/* 2460 */     for (int i = startIndex; i < array.length; i++) {
/* 2461 */       if (valueToFind == array[i])
/* 2462 */         return i; 
/*      */     } 
/* 2465 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(float[] array, float valueToFind) {
/* 2479 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
/* 2497 */     if (isEmpty(array))
/* 2498 */       return -1; 
/* 2500 */     if (startIndex < 0)
/* 2501 */       return -1; 
/* 2502 */     if (startIndex >= array.length)
/* 2503 */       startIndex = array.length - 1; 
/* 2505 */     for (int i = startIndex; i >= 0; i--) {
/* 2506 */       if (valueToFind == array[i])
/* 2507 */         return i; 
/*      */     } 
/* 2510 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(float[] array, float valueToFind) {
/* 2523 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static int indexOf(boolean[] array, boolean valueToFind) {
/* 2539 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */   
/*      */   public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
/* 2558 */     if (isEmpty(array))
/* 2559 */       return -1; 
/* 2561 */     if (startIndex < 0)
/* 2562 */       startIndex = 0; 
/* 2564 */     for (int i = startIndex; i < array.length; i++) {
/* 2565 */       if (valueToFind == array[i])
/* 2566 */         return i; 
/*      */     } 
/* 2569 */     return -1;
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(boolean[] array, boolean valueToFind) {
/* 2584 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
/* 2602 */     if (isEmpty(array))
/* 2603 */       return -1; 
/* 2605 */     if (startIndex < 0)
/* 2606 */       return -1; 
/* 2607 */     if (startIndex >= array.length)
/* 2608 */       startIndex = array.length - 1; 
/* 2610 */     for (int i = startIndex; i >= 0; i--) {
/* 2611 */       if (valueToFind == array[i])
/* 2612 */         return i; 
/*      */     } 
/* 2615 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean contains(boolean[] array, boolean valueToFind) {
/* 2628 */     return (indexOf(array, valueToFind) != -1);
/*      */   }
/*      */   
/*      */   public static char[] toPrimitive(Character[] array) {
/* 2646 */     if (array == null)
/* 2647 */       return null; 
/* 2648 */     if (array.length == 0)
/* 2649 */       return EMPTY_CHAR_ARRAY; 
/* 2651 */     char[] result = new char[array.length];
/* 2652 */     for (int i = 0; i < array.length; i++)
/* 2653 */       result[i] = array[i].charValue(); 
/* 2655 */     return result;
/*      */   }
/*      */   
/*      */   public static char[] toPrimitive(Character[] array, char valueForNull) {
/* 2668 */     if (array == null)
/* 2669 */       return null; 
/* 2670 */     if (array.length == 0)
/* 2671 */       return EMPTY_CHAR_ARRAY; 
/* 2673 */     char[] result = new char[array.length];
/* 2674 */     for (int i = 0; i < array.length; i++) {
/* 2675 */       Character b = array[i];
/* 2676 */       result[i] = (b == null) ? valueForNull : b.charValue();
/*      */     } 
/* 2678 */     return result;
/*      */   }
/*      */   
/*      */   public static Character[] toObject(char[] array) {
/* 2690 */     if (array == null)
/* 2691 */       return null; 
/* 2692 */     if (array.length == 0)
/* 2693 */       return EMPTY_CHARACTER_OBJECT_ARRAY; 
/* 2695 */     Character[] result = new Character[array.length];
/* 2696 */     for (int i = 0; i < array.length; i++)
/* 2697 */       result[i] = new Character(array[i]); 
/* 2699 */     return result;
/*      */   }
/*      */   
/*      */   public static long[] toPrimitive(Long[] array) {
/* 2714 */     if (array == null)
/* 2715 */       return null; 
/* 2716 */     if (array.length == 0)
/* 2717 */       return EMPTY_LONG_ARRAY; 
/* 2719 */     long[] result = new long[array.length];
/* 2720 */     for (int i = 0; i < array.length; i++)
/* 2721 */       result[i] = array[i].longValue(); 
/* 2723 */     return result;
/*      */   }
/*      */   
/*      */   public static long[] toPrimitive(Long[] array, long valueForNull) {
/* 2736 */     if (array == null)
/* 2737 */       return null; 
/* 2738 */     if (array.length == 0)
/* 2739 */       return EMPTY_LONG_ARRAY; 
/* 2741 */     long[] result = new long[array.length];
/* 2742 */     for (int i = 0; i < array.length; i++) {
/* 2743 */       Long b = array[i];
/* 2744 */       result[i] = (b == null) ? valueForNull : b.longValue();
/*      */     } 
/* 2746 */     return result;
/*      */   }
/*      */   
/*      */   public static Long[] toObject(long[] array) {
/* 2758 */     if (array == null)
/* 2759 */       return null; 
/* 2760 */     if (array.length == 0)
/* 2761 */       return EMPTY_LONG_OBJECT_ARRAY; 
/* 2763 */     Long[] result = new Long[array.length];
/* 2764 */     for (int i = 0; i < array.length; i++)
/* 2765 */       result[i] = new Long(array[i]); 
/* 2767 */     return result;
/*      */   }
/*      */   
/*      */   public static int[] toPrimitive(Integer[] array) {
/* 2782 */     if (array == null)
/* 2783 */       return null; 
/* 2784 */     if (array.length == 0)
/* 2785 */       return EMPTY_INT_ARRAY; 
/* 2787 */     int[] result = new int[array.length];
/* 2788 */     for (int i = 0; i < array.length; i++)
/* 2789 */       result[i] = array[i].intValue(); 
/* 2791 */     return result;
/*      */   }
/*      */   
/*      */   public static int[] toPrimitive(Integer[] array, int valueForNull) {
/* 2804 */     if (array == null)
/* 2805 */       return null; 
/* 2806 */     if (array.length == 0)
/* 2807 */       return EMPTY_INT_ARRAY; 
/* 2809 */     int[] result = new int[array.length];
/* 2810 */     for (int i = 0; i < array.length; i++) {
/* 2811 */       Integer b = array[i];
/* 2812 */       result[i] = (b == null) ? valueForNull : b.intValue();
/*      */     } 
/* 2814 */     return result;
/*      */   }
/*      */   
/*      */   public static Integer[] toObject(int[] array) {
/* 2826 */     if (array == null)
/* 2827 */       return null; 
/* 2828 */     if (array.length == 0)
/* 2829 */       return EMPTY_INTEGER_OBJECT_ARRAY; 
/* 2831 */     Integer[] result = new Integer[array.length];
/* 2832 */     for (int i = 0; i < array.length; i++)
/* 2833 */       result[i] = new Integer(array[i]); 
/* 2835 */     return result;
/*      */   }
/*      */   
/*      */   public static short[] toPrimitive(Short[] array) {
/* 2850 */     if (array == null)
/* 2851 */       return null; 
/* 2852 */     if (array.length == 0)
/* 2853 */       return EMPTY_SHORT_ARRAY; 
/* 2855 */     short[] result = new short[array.length];
/* 2856 */     for (int i = 0; i < array.length; i++)
/* 2857 */       result[i] = array[i].shortValue(); 
/* 2859 */     return result;
/*      */   }
/*      */   
/*      */   public static short[] toPrimitive(Short[] array, short valueForNull) {
/* 2872 */     if (array == null)
/* 2873 */       return null; 
/* 2874 */     if (array.length == 0)
/* 2875 */       return EMPTY_SHORT_ARRAY; 
/* 2877 */     short[] result = new short[array.length];
/* 2878 */     for (int i = 0; i < array.length; i++) {
/* 2879 */       Short b = array[i];
/* 2880 */       result[i] = (b == null) ? valueForNull : b.shortValue();
/*      */     } 
/* 2882 */     return result;
/*      */   }
/*      */   
/*      */   public static Short[] toObject(short[] array) {
/* 2894 */     if (array == null)
/* 2895 */       return null; 
/* 2896 */     if (array.length == 0)
/* 2897 */       return EMPTY_SHORT_OBJECT_ARRAY; 
/* 2899 */     Short[] result = new Short[array.length];
/* 2900 */     for (int i = 0; i < array.length; i++)
/* 2901 */       result[i] = new Short(array[i]); 
/* 2903 */     return result;
/*      */   }
/*      */   
/*      */   public static byte[] toPrimitive(Byte[] array) {
/* 2918 */     if (array == null)
/* 2919 */       return null; 
/* 2920 */     if (array.length == 0)
/* 2921 */       return EMPTY_BYTE_ARRAY; 
/* 2923 */     byte[] result = new byte[array.length];
/* 2924 */     for (int i = 0; i < array.length; i++)
/* 2925 */       result[i] = array[i].byteValue(); 
/* 2927 */     return result;
/*      */   }
/*      */   
/*      */   public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
/* 2940 */     if (array == null)
/* 2941 */       return null; 
/* 2942 */     if (array.length == 0)
/* 2943 */       return EMPTY_BYTE_ARRAY; 
/* 2945 */     byte[] result = new byte[array.length];
/* 2946 */     for (int i = 0; i < array.length; i++) {
/* 2947 */       Byte b = array[i];
/* 2948 */       result[i] = (b == null) ? valueForNull : b.byteValue();
/*      */     } 
/* 2950 */     return result;
/*      */   }
/*      */   
/*      */   public static Byte[] toObject(byte[] array) {
/* 2962 */     if (array == null)
/* 2963 */       return null; 
/* 2964 */     if (array.length == 0)
/* 2965 */       return EMPTY_BYTE_OBJECT_ARRAY; 
/* 2967 */     Byte[] result = new Byte[array.length];
/* 2968 */     for (int i = 0; i < array.length; i++)
/* 2969 */       result[i] = new Byte(array[i]); 
/* 2971 */     return result;
/*      */   }
/*      */   
/*      */   public static double[] toPrimitive(Double[] array) {
/* 2986 */     if (array == null)
/* 2987 */       return null; 
/* 2988 */     if (array.length == 0)
/* 2989 */       return EMPTY_DOUBLE_ARRAY; 
/* 2991 */     double[] result = new double[array.length];
/* 2992 */     for (int i = 0; i < array.length; i++)
/* 2993 */       result[i] = array[i].doubleValue(); 
/* 2995 */     return result;
/*      */   }
/*      */   
/*      */   public static double[] toPrimitive(Double[] array, double valueForNull) {
/* 3008 */     if (array == null)
/* 3009 */       return null; 
/* 3010 */     if (array.length == 0)
/* 3011 */       return EMPTY_DOUBLE_ARRAY; 
/* 3013 */     double[] result = new double[array.length];
/* 3014 */     for (int i = 0; i < array.length; i++) {
/* 3015 */       Double b = array[i];
/* 3016 */       result[i] = (b == null) ? valueForNull : b.doubleValue();
/*      */     } 
/* 3018 */     return result;
/*      */   }
/*      */   
/*      */   public static Double[] toObject(double[] array) {
/* 3030 */     if (array == null)
/* 3031 */       return null; 
/* 3032 */     if (array.length == 0)
/* 3033 */       return EMPTY_DOUBLE_OBJECT_ARRAY; 
/* 3035 */     Double[] result = new Double[array.length];
/* 3036 */     for (int i = 0; i < array.length; i++)
/* 3037 */       result[i] = new Double(array[i]); 
/* 3039 */     return result;
/*      */   }
/*      */   
/*      */   public static float[] toPrimitive(Float[] array) {
/* 3054 */     if (array == null)
/* 3055 */       return null; 
/* 3056 */     if (array.length == 0)
/* 3057 */       return EMPTY_FLOAT_ARRAY; 
/* 3059 */     float[] result = new float[array.length];
/* 3060 */     for (int i = 0; i < array.length; i++)
/* 3061 */       result[i] = array[i].floatValue(); 
/* 3063 */     return result;
/*      */   }
/*      */   
/*      */   public static float[] toPrimitive(Float[] array, float valueForNull) {
/* 3076 */     if (array == null)
/* 3077 */       return null; 
/* 3078 */     if (array.length == 0)
/* 3079 */       return EMPTY_FLOAT_ARRAY; 
/* 3081 */     float[] result = new float[array.length];
/* 3082 */     for (int i = 0; i < array.length; i++) {
/* 3083 */       Float b = array[i];
/* 3084 */       result[i] = (b == null) ? valueForNull : b.floatValue();
/*      */     } 
/* 3086 */     return result;
/*      */   }
/*      */   
/*      */   public static Float[] toObject(float[] array) {
/* 3098 */     if (array == null)
/* 3099 */       return null; 
/* 3100 */     if (array.length == 0)
/* 3101 */       return EMPTY_FLOAT_OBJECT_ARRAY; 
/* 3103 */     Float[] result = new Float[array.length];
/* 3104 */     for (int i = 0; i < array.length; i++)
/* 3105 */       result[i] = new Float(array[i]); 
/* 3107 */     return result;
/*      */   }
/*      */   
/*      */   public static boolean[] toPrimitive(Boolean[] array) {
/* 3122 */     if (array == null)
/* 3123 */       return null; 
/* 3124 */     if (array.length == 0)
/* 3125 */       return EMPTY_BOOLEAN_ARRAY; 
/* 3127 */     boolean[] result = new boolean[array.length];
/* 3128 */     for (int i = 0; i < array.length; i++)
/* 3129 */       result[i] = array[i].booleanValue(); 
/* 3131 */     return result;
/*      */   }
/*      */   
/*      */   public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
/* 3144 */     if (array == null)
/* 3145 */       return null; 
/* 3146 */     if (array.length == 0)
/* 3147 */       return EMPTY_BOOLEAN_ARRAY; 
/* 3149 */     boolean[] result = new boolean[array.length];
/* 3150 */     for (int i = 0; i < array.length; i++) {
/* 3151 */       Boolean b = array[i];
/* 3152 */       result[i] = (b == null) ? valueForNull : b.booleanValue();
/*      */     } 
/* 3154 */     return result;
/*      */   }
/*      */   
/*      */   public static Boolean[] toObject(boolean[] array) {
/* 3166 */     if (array == null)
/* 3167 */       return null; 
/* 3168 */     if (array.length == 0)
/* 3169 */       return EMPTY_BOOLEAN_OBJECT_ARRAY; 
/* 3171 */     Boolean[] result = new Boolean[array.length];
/* 3172 */     for (int i = 0; i < array.length; i++)
/* 3173 */       result[i] = array[i] ? Boolean.TRUE : Boolean.FALSE; 
/* 3175 */     return result;
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(Object[] array) {
/* 3187 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(long[] array) {
/* 3198 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(int[] array) {
/* 3209 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(short[] array) {
/* 3220 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(char[] array) {
/* 3231 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(byte[] array) {
/* 3242 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(double[] array) {
/* 3253 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(float[] array) {
/* 3264 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(boolean[] array) {
/* 3275 */     return (array == null || array.length == 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(Object[] array) {
/* 3287 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(long[] array) {
/* 3298 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(int[] array) {
/* 3309 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(short[] array) {
/* 3320 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(char[] array) {
/* 3331 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(byte[] array) {
/* 3342 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(double[] array) {
/* 3353 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(float[] array) {
/* 3364 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(boolean[] array) {
/* 3375 */     return (array != null && array.length != 0);
/*      */   }
/*      */   
/*      */   public static Object[] addAll(Object[] array1, Object[] array2) {
/* 3402 */     if (array1 == null)
/* 3403 */       return clone(array2); 
/* 3404 */     if (array2 == null)
/* 3405 */       return clone(array1); 
/* 3407 */     Object[] joinedArray = (Object[])Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
/* 3409 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/*      */     try {
/* 3411 */       System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3412 */     } catch (ArrayStoreException ase) {
/* 3419 */       Class type1 = array1.getClass().getComponentType();
/* 3420 */       Class type2 = array2.getClass().getComponentType();
/* 3421 */       if (!type1.isAssignableFrom(type2))
/* 3422 */         throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName()); 
/* 3424 */       throw ase;
/*      */     } 
/* 3426 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static boolean[] addAll(boolean[] array1, boolean[] array2) {
/* 3447 */     if (array1 == null)
/* 3448 */       return clone(array2); 
/* 3449 */     if (array2 == null)
/* 3450 */       return clone(array1); 
/* 3452 */     boolean[] joinedArray = new boolean[array1.length + array2.length];
/* 3453 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3454 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3455 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static char[] addAll(char[] array1, char[] array2) {
/* 3476 */     if (array1 == null)
/* 3477 */       return clone(array2); 
/* 3478 */     if (array2 == null)
/* 3479 */       return clone(array1); 
/* 3481 */     char[] joinedArray = new char[array1.length + array2.length];
/* 3482 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3483 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3484 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static byte[] addAll(byte[] array1, byte[] array2) {
/* 3505 */     if (array1 == null)
/* 3506 */       return clone(array2); 
/* 3507 */     if (array2 == null)
/* 3508 */       return clone(array1); 
/* 3510 */     byte[] joinedArray = new byte[array1.length + array2.length];
/* 3511 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3512 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3513 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static short[] addAll(short[] array1, short[] array2) {
/* 3534 */     if (array1 == null)
/* 3535 */       return clone(array2); 
/* 3536 */     if (array2 == null)
/* 3537 */       return clone(array1); 
/* 3539 */     short[] joinedArray = new short[array1.length + array2.length];
/* 3540 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3541 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3542 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static int[] addAll(int[] array1, int[] array2) {
/* 3563 */     if (array1 == null)
/* 3564 */       return clone(array2); 
/* 3565 */     if (array2 == null)
/* 3566 */       return clone(array1); 
/* 3568 */     int[] joinedArray = new int[array1.length + array2.length];
/* 3569 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3570 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3571 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static long[] addAll(long[] array1, long[] array2) {
/* 3592 */     if (array1 == null)
/* 3593 */       return clone(array2); 
/* 3594 */     if (array2 == null)
/* 3595 */       return clone(array1); 
/* 3597 */     long[] joinedArray = new long[array1.length + array2.length];
/* 3598 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3599 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3600 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static float[] addAll(float[] array1, float[] array2) {
/* 3621 */     if (array1 == null)
/* 3622 */       return clone(array2); 
/* 3623 */     if (array2 == null)
/* 3624 */       return clone(array1); 
/* 3626 */     float[] joinedArray = new float[array1.length + array2.length];
/* 3627 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3628 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3629 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static double[] addAll(double[] array1, double[] array2) {
/* 3650 */     if (array1 == null)
/* 3651 */       return clone(array2); 
/* 3652 */     if (array2 == null)
/* 3653 */       return clone(array1); 
/* 3655 */     double[] joinedArray = new double[array1.length + array2.length];
/* 3656 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3657 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3658 */     return joinedArray;
/*      */   }
/*      */   
/*      */   public static Object[] add(Object[] array, Object element) {
/*      */     Class type;
/* 3689 */     if (array != null) {
/* 3690 */       type = (Class)array.getClass();
/* 3691 */     } else if (element != null) {
/* 3692 */       type = (Class)element.getClass();
/*      */     } else {
/* 3694 */       type = Object.class;
/*      */     } 
/* 3696 */     Object[] newArray = (Object[])copyArrayGrow1(array, type);
/* 3697 */     newArray[newArray.length - 1] = element;
/* 3698 */     return newArray;
/*      */   }
/*      */   
/*      */   public static boolean[] add(boolean[] array, boolean element) {
/* 3723 */     boolean[] newArray = (boolean[])copyArrayGrow1(array, boolean.class);
/* 3724 */     newArray[newArray.length - 1] = element;
/* 3725 */     return newArray;
/*      */   }
/*      */   
/*      */   public static byte[] add(byte[] array, byte element) {
/* 3750 */     byte[] newArray = (byte[])copyArrayGrow1(array, byte.class);
/* 3751 */     newArray[newArray.length - 1] = element;
/* 3752 */     return newArray;
/*      */   }
/*      */   
/*      */   public static char[] add(char[] array, char element) {
/* 3777 */     char[] newArray = (char[])copyArrayGrow1(array, char.class);
/* 3778 */     newArray[newArray.length - 1] = element;
/* 3779 */     return newArray;
/*      */   }
/*      */   
/*      */   public static double[] add(double[] array, double element) {
/* 3804 */     double[] newArray = (double[])copyArrayGrow1(array, double.class);
/* 3805 */     newArray[newArray.length - 1] = element;
/* 3806 */     return newArray;
/*      */   }
/*      */   
/*      */   public static float[] add(float[] array, float element) {
/* 3831 */     float[] newArray = (float[])copyArrayGrow1(array, float.class);
/* 3832 */     newArray[newArray.length - 1] = element;
/* 3833 */     return newArray;
/*      */   }
/*      */   
/*      */   public static int[] add(int[] array, int element) {
/* 3858 */     int[] newArray = (int[])copyArrayGrow1(array, int.class);
/* 3859 */     newArray[newArray.length - 1] = element;
/* 3860 */     return newArray;
/*      */   }
/*      */   
/*      */   public static long[] add(long[] array, long element) {
/* 3885 */     long[] newArray = (long[])copyArrayGrow1(array, long.class);
/* 3886 */     newArray[newArray.length - 1] = element;
/* 3887 */     return newArray;
/*      */   }
/*      */   
/*      */   public static short[] add(short[] array, short element) {
/* 3912 */     short[] newArray = (short[])copyArrayGrow1(array, short.class);
/* 3913 */     newArray[newArray.length - 1] = element;
/* 3914 */     return newArray;
/*      */   }
/*      */   
/*      */   private static Object copyArrayGrow1(Object array, Class newArrayComponentType) {
/* 3927 */     if (array != null) {
/* 3928 */       int arrayLength = Array.getLength(array);
/* 3929 */       Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
/* 3930 */       System.arraycopy(array, 0, newArray, 0, arrayLength);
/* 3931 */       return newArray;
/*      */     } 
/* 3933 */     return Array.newInstance(newArrayComponentType, 1);
/*      */   }
/*      */   
/*      */   public static Object[] add(Object[] array, int index, Object element) {
/* 3965 */     Class clss = null;
/* 3966 */     if (array != null) {
/* 3967 */       clss = array.getClass().getComponentType();
/* 3968 */     } else if (element != null) {
/* 3969 */       clss = element.getClass();
/*      */     } else {
/* 3971 */       return new Object[] { null };
/*      */     } 
/* 3973 */     return (Object[])add(array, index, element, clss);
/*      */   }
/*      */   
/*      */   public static boolean[] add(boolean[] array, int index, boolean element) {
/* 4004 */     return (boolean[])add(array, index, BooleanUtils.toBooleanObject(element), boolean.class);
/*      */   }
/*      */   
/*      */   public static char[] add(char[] array, int index, char element) {
/* 4036 */     return (char[])add(array, index, new Character(element), char.class);
/*      */   }
/*      */   
/*      */   public static byte[] add(byte[] array, int index, byte element) {
/* 4067 */     return (byte[])add(array, index, new Byte(element), byte.class);
/*      */   }
/*      */   
/*      */   public static short[] add(short[] array, int index, short element) {
/* 4098 */     return (short[])add(array, index, new Short(element), short.class);
/*      */   }
/*      */   
/*      */   public static int[] add(int[] array, int index, int element) {
/* 4129 */     return (int[])add(array, index, new Integer(element), int.class);
/*      */   }
/*      */   
/*      */   public static long[] add(long[] array, int index, long element) {
/* 4160 */     return (long[])add(array, index, new Long(element), long.class);
/*      */   }
/*      */   
/*      */   public static float[] add(float[] array, int index, float element) {
/* 4191 */     return (float[])add(array, index, new Float(element), float.class);
/*      */   }
/*      */   
/*      */   public static double[] add(double[] array, int index, double element) {
/* 4222 */     return (double[])add(array, index, new Double(element), double.class);
/*      */   }
/*      */   
/*      */   private static Object add(Object array, int index, Object element, Class clss) {
/* 4237 */     if (array == null) {
/* 4238 */       if (index != 0)
/* 4239 */         throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0"); 
/* 4241 */       Object joinedArray = Array.newInstance(clss, 1);
/* 4242 */       Array.set(joinedArray, 0, element);
/* 4243 */       return joinedArray;
/*      */     } 
/* 4245 */     int length = Array.getLength(array);
/* 4246 */     if (index > length || index < 0)
/* 4247 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length); 
/* 4249 */     Object result = Array.newInstance(clss, length + 1);
/* 4250 */     System.arraycopy(array, 0, result, 0, index);
/* 4251 */     Array.set(result, index, element);
/* 4252 */     if (index < length)
/* 4253 */       System.arraycopy(array, index, result, index + 1, length - index); 
/* 4255 */     return result;
/*      */   }
/*      */   
/*      */   public static Object[] remove(Object[] array, int index) {
/* 4287 */     return (Object[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static Object[] removeElement(Object[] array, Object element) {
/* 4316 */     int index = indexOf(array, element);
/* 4317 */     if (index == -1)
/* 4318 */       return clone(array); 
/* 4320 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static boolean[] remove(boolean[] array, int index) {
/* 4352 */     return (boolean[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static boolean[] removeElement(boolean[] array, boolean element) {
/* 4381 */     int index = indexOf(array, element);
/* 4382 */     if (index == -1)
/* 4383 */       return clone(array); 
/* 4385 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static byte[] remove(byte[] array, int index) {
/* 4417 */     return (byte[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static byte[] removeElement(byte[] array, byte element) {
/* 4446 */     int index = indexOf(array, element);
/* 4447 */     if (index == -1)
/* 4448 */       return clone(array); 
/* 4450 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static char[] remove(char[] array, int index) {
/* 4482 */     return (char[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static char[] removeElement(char[] array, char element) {
/* 4511 */     int index = indexOf(array, element);
/* 4512 */     if (index == -1)
/* 4513 */       return clone(array); 
/* 4515 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static double[] remove(double[] array, int index) {
/* 4547 */     return (double[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static double[] removeElement(double[] array, double element) {
/* 4576 */     int index = indexOf(array, element);
/* 4577 */     if (index == -1)
/* 4578 */       return clone(array); 
/* 4580 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static float[] remove(float[] array, int index) {
/* 4612 */     return (float[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static float[] removeElement(float[] array, float element) {
/* 4641 */     int index = indexOf(array, element);
/* 4642 */     if (index == -1)
/* 4643 */       return clone(array); 
/* 4645 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static int[] remove(int[] array, int index) {
/* 4677 */     return (int[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static int[] removeElement(int[] array, int element) {
/* 4706 */     int index = indexOf(array, element);
/* 4707 */     if (index == -1)
/* 4708 */       return clone(array); 
/* 4710 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static long[] remove(long[] array, int index) {
/* 4742 */     return (long[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static long[] removeElement(long[] array, long element) {
/* 4771 */     int index = indexOf(array, element);
/* 4772 */     if (index == -1)
/* 4773 */       return clone(array); 
/* 4775 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   public static short[] remove(short[] array, int index) {
/* 4807 */     return (short[])remove(array, index);
/*      */   }
/*      */   
/*      */   public static short[] removeElement(short[] array, short element) {
/* 4836 */     int index = indexOf(array, element);
/* 4837 */     if (index == -1)
/* 4838 */       return clone(array); 
/* 4840 */     return remove(array, index);
/*      */   }
/*      */   
/*      */   private static Object remove(Object array, int index) {
/* 4865 */     int length = getLength(array);
/* 4866 */     if (index < 0 || index >= length)
/* 4867 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length); 
/* 4870 */     Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
/* 4871 */     System.arraycopy(array, 0, result, 0, index);
/* 4872 */     if (index < length - 1)
/* 4873 */       System.arraycopy(array, index + 1, result, index, length - index - 1); 
/* 4876 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\ArrayUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */