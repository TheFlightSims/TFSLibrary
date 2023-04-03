/*      */ package org.geotools.resources;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.text.FieldPosition;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Comparator;
/*      */ import java.util.Locale;
/*      */ import org.geotools.util.Utilities;
/*      */ 
/*      */ public final class XArray {
/*      */   private static <T> T doResize(T array, int length) {
/*   76 */     int current = (array == null) ? 0 : Array.getLength(array);
/*   77 */     if (current != length) {
/*   79 */       T newArray = (T)Array.newInstance(array.getClass().getComponentType(), length);
/*   80 */       System.arraycopy(array, 0, newArray, 0, Math.min(current, length));
/*   81 */       return newArray;
/*      */     } 
/*   83 */     return array;
/*      */   }
/*      */   
/*      */   public static <E> E[] resize(E[] array, int length) {
/*  113 */     return (E[])doResize((Object[])array, length);
/*      */   }
/*      */   
/*      */   public static double[] resize(double[] array, int length) {
/*  126 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static float[] resize(float[] array, int length) {
/*  139 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static long[] resize(long[] array, int length) {
/*  152 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static int[] resize(int[] array, int length) {
/*  165 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static short[] resize(short[] array, int length) {
/*  178 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static byte[] resize(byte[] array, int length) {
/*  191 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static char[] resize(char[] array, int length) {
/*  204 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   public static boolean[] resize(boolean[] array, int length) {
/*  217 */     return doResize(array, length);
/*      */   }
/*      */   
/*      */   private static <T> T doRemove(T array, int index, int length) {
/*  231 */     if (length == 0)
/*  232 */       return array; 
/*  234 */     int arrayLength = Array.getLength(array);
/*  236 */     T newArray = (T)Array.newInstance(array.getClass().getComponentType(), arrayLength -= length);
/*  237 */     System.arraycopy(array, 0, newArray, 0, index);
/*  238 */     System.arraycopy(array, index + length, newArray, index, arrayLength - index);
/*  239 */     return newArray;
/*      */   }
/*      */   
/*      */   public static <E> E[] remove(E[] array, int index, int length) {
/*  253 */     return (E[])doRemove((Object[])array, index, length);
/*      */   }
/*      */   
/*      */   public static double[] remove(double[] array, int index, int length) {
/*  266 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static float[] remove(float[] array, int index, int length) {
/*  279 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static long[] remove(long[] array, int index, int length) {
/*  292 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static int[] remove(int[] array, int index, int length) {
/*  305 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static short[] remove(short[] array, int index, int length) {
/*  318 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static byte[] remove(byte[] array, int index, int length) {
/*  331 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static char[] remove(char[] array, int index, int length) {
/*  344 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   public static boolean[] remove(boolean[] array, int index, int length) {
/*  357 */     return doRemove(array, index, length);
/*      */   }
/*      */   
/*      */   private static <T> T doInsert(T array, int index, int length) {
/*  372 */     if (length == 0)
/*  373 */       return array; 
/*  375 */     int arrayLength = Array.getLength(array);
/*  377 */     T newArray = (T)Array.newInstance(array.getClass().getComponentType(), arrayLength + length);
/*  378 */     System.arraycopy(array, 0, newArray, 0, index);
/*  379 */     System.arraycopy(array, index, newArray, index + length, arrayLength - index);
/*  380 */     return newArray;
/*      */   }
/*      */   
/*      */   public static <E> E[] insert(E[] array, int index, int length) {
/*  395 */     return (E[])doInsert((Object[])array, index, length);
/*      */   }
/*      */   
/*      */   public static double[] insert(double[] array, int index, int length) {
/*  410 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static float[] insert(float[] array, int index, int length) {
/*  425 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static long[] insert(long[] array, int index, int length) {
/*  440 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static int[] insert(int[] array, int index, int length) {
/*  455 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static short[] insert(short[] array, int index, int length) {
/*  470 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static byte[] insert(byte[] array, int index, int length) {
/*  485 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static char[] insert(char[] array, int index, int length) {
/*  500 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   public static boolean[] insert(boolean[] array, int index, int length) {
/*  515 */     return doInsert(array, index, length);
/*      */   }
/*      */   
/*      */   private static <T> Object doInsert(T src, int src_pos, T dst, int dst_pos, int length) {
/*  538 */     if (length == 0)
/*  539 */       return dst; 
/*  541 */     int dstLength = Array.getLength(dst);
/*  543 */     T newArray = (T)Array.newInstance(dst.getClass().getComponentType(), dstLength + length);
/*  544 */     System.arraycopy(dst, 0, newArray, 0, dst_pos);
/*  545 */     System.arraycopy(src, src_pos, newArray, dst_pos, length);
/*  546 */     System.arraycopy(dst, dst_pos, newArray, dst_pos + length, dstLength - dst_pos);
/*  547 */     return newArray;
/*      */   }
/*      */   
/*      */   public static <E> E[] insert(E[] src, int src_pos, E[] dst, int dst_pos, int length) {
/*  570 */     return (E[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static double[] insert(double[] src, int src_pos, double[] dst, int dst_pos, int length) {
/*  592 */     return (double[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static float[] insert(float[] src, int src_pos, float[] dst, int dst_pos, int length) {
/*  614 */     return (float[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static long[] insert(long[] src, int src_pos, long[] dst, int dst_pos, int length) {
/*  636 */     return (long[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static int[] insert(int[] src, int src_pos, int[] dst, int dst_pos, int length) {
/*  658 */     return (int[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static short[] insert(short[] src, int src_pos, short[] dst, int dst_pos, int length) {
/*  680 */     return (short[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static byte[] insert(byte[] src, int src_pos, byte[] dst, int dst_pos, int length) {
/*  703 */     return (byte[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static char[] insert(char[] src, int src_pos, char[] dst, int dst_pos, int length) {
/*  725 */     return (char[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static boolean[] insert(boolean[] src, int src_pos, boolean[] dst, int dst_pos, int length) {
/*  747 */     return (boolean[])doInsert(src, src_pos, dst, dst_pos, length);
/*      */   }
/*      */   
/*      */   public static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
/*  755 */     for (int i = 1; i < array.length; i++) {
/*  756 */       if (comparator.compare(array[i], array[i - 1]) < 0)
/*  757 */         return false; 
/*      */     } 
/*  760 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(char[] array) {
/*  768 */     for (int i = 1; i < array.length; i++) {
/*  769 */       if (array[i] < array[i - 1])
/*  770 */         return false; 
/*      */     } 
/*  773 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(byte[] array) {
/*  781 */     for (int i = 1; i < array.length; i++) {
/*  782 */       if (array[i] < array[i - 1])
/*  783 */         return false; 
/*      */     } 
/*  786 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(short[] array) {
/*  794 */     for (int i = 1; i < array.length; i++) {
/*  795 */       if (array[i] < array[i - 1])
/*  796 */         return false; 
/*      */     } 
/*  799 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(int[] array) {
/*  807 */     for (int i = 1; i < array.length; i++) {
/*  808 */       if (array[i] < array[i - 1])
/*  809 */         return false; 
/*      */     } 
/*  812 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(long[] array) {
/*  820 */     for (int i = 1; i < array.length; i++) {
/*  821 */       if (array[i] < array[i - 1])
/*  822 */         return false; 
/*      */     } 
/*  825 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(float[] array) {
/*  834 */     int previous = 0;
/*  835 */     for (int i = 1; i < array.length; i++) {
/*  836 */       float value = array[i];
/*  837 */       if (value < array[previous])
/*  838 */         return false; 
/*  840 */       if (!Float.isNaN(value))
/*  841 */         previous = i; 
/*      */     } 
/*  844 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isSorted(double[] array) {
/*  853 */     int previous = 0;
/*  854 */     for (int i = 1; i < array.length; i++) {
/*  855 */       double value = array[i];
/*  856 */       if (value < array[previous])
/*  857 */         return false; 
/*  859 */       if (!Double.isNaN(value))
/*  860 */         previous = i; 
/*      */     } 
/*  863 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isStrictlySorted(int[] array) {
/*  871 */     for (int i = 1; i < array.length; i++) {
/*  872 */       if (array[i] <= array[i - 1])
/*  873 */         return false; 
/*      */     } 
/*  876 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean allEquals(float[] array, float value) {
/*  884 */     if (Float.isNaN(value)) {
/*  885 */       for (int i = 0; i < array.length; i++) {
/*  886 */         if (!Float.isNaN(array[i]))
/*  887 */           return false; 
/*      */       } 
/*      */     } else {
/*  891 */       for (int i = 0; i < array.length; i++) {
/*  892 */         if (array[i] != value)
/*  893 */           return false; 
/*      */       } 
/*      */     } 
/*  897 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean allEquals(double[] array, double value) {
/*  905 */     if (Double.isNaN(value)) {
/*  906 */       for (int i = 0; i < array.length; i++) {
/*  907 */         if (!Double.isNaN(array[i]))
/*  908 */           return false; 
/*      */       } 
/*      */     } else {
/*  912 */       for (int i = 0; i < array.length; i++) {
/*  913 */         if (array[i] != value)
/*  914 */           return false; 
/*      */       } 
/*      */     } 
/*  918 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean hasNaN(float[] array) {
/*  926 */     for (int i = 0; i < array.length; i++) {
/*  927 */       if (Float.isNaN(array[i]))
/*  928 */         return true; 
/*      */     } 
/*  931 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean hasNaN(double[] array) {
/*  939 */     for (int i = 0; i < array.length; i++) {
/*  940 */       if (Double.isNaN(array[i]))
/*  941 */         return true; 
/*      */     } 
/*  944 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean containsIgnoreCase(String[] array, String value) {
/*  956 */     if (array != null)
/*  957 */       for (String element : array) {
/*  958 */         if (value.equalsIgnoreCase(element))
/*  959 */           return true; 
/*      */       }  
/*  963 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean contains(Object[] array, Object value) {
/*  977 */     if (array != null)
/*  978 */       for (Object element : array) {
/*  979 */         if (Utilities.equals(element, value))
/*  980 */           return true; 
/*      */       }  
/*  984 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean intersects(Object[] array1, Object[] array2) {
/*  997 */     for (Object element : array1) {
/*  998 */       if (contains(array2, element))
/*  999 */         return true; 
/*      */     } 
/* 1002 */     return false;
/*      */   }
/*      */   
/*      */   public static String toString(Object array, Locale locale) {
/* 1017 */     StringBuffer buffer = new StringBuffer();
/* 1018 */     NumberFormat format = NumberFormat.getNumberInstance(locale);
/* 1019 */     FieldPosition dummy = new FieldPosition(0);
/* 1020 */     int length = Array.getLength(array);
/* 1021 */     for (int i = 0; i < length; i++) {
/* 1022 */       if (i != 0)
/* 1023 */         buffer.append(", "); 
/* 1025 */       format.format(Array.get(array, i), buffer, dummy);
/*      */     } 
/* 1027 */     return buffer.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\XArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */