package org.hsqldb.lib;

import java.lang.reflect.Array;

public class ArrayUtil {
  public static final int CLASS_CODE_BYTE = 66;
  
  public static final int CLASS_CODE_CHAR = 67;
  
  public static final int CLASS_CODE_DOUBLE = 68;
  
  public static final int CLASS_CODE_FLOAT = 70;
  
  public static final int CLASS_CODE_INT = 73;
  
  public static final int CLASS_CODE_LONG = 74;
  
  public static final int CLASS_CODE_OBJECT = 76;
  
  public static final int CLASS_CODE_SHORT = 83;
  
  public static final int CLASS_CODE_BOOLEAN = 90;
  
  private static IntValueHashMap classCodeMap = new IntValueHashMap();
  
  static int getClassCode(Class paramClass) {
    return !paramClass.isPrimitive() ? 76 : classCodeMap.get(paramClass, -1);
  }
  
  public static void clearArray(int paramInt1, Object paramObject, int paramInt2, int paramInt3) {
    byte[] arrayOfByte;
    short[] arrayOfShort;
    int[] arrayOfInt;
    long[] arrayOfLong;
    float[] arrayOfFloat;
    double[] arrayOfDouble;
    boolean[] arrayOfBoolean;
    switch (paramInt1) {
      case 66:
        arrayOfByte = (byte[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfByte[paramInt3] = 0; 
        return;
      case 67:
        arrayOfByte = (byte[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfByte[paramInt3] = 0; 
        return;
      case 83:
        arrayOfShort = (short[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfShort[paramInt3] = 0; 
        return;
      case 73:
        arrayOfInt = (int[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfInt[paramInt3] = 0; 
        return;
      case 74:
        arrayOfLong = (long[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfLong[paramInt3] = 0L; 
        return;
      case 70:
        arrayOfFloat = (float[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfFloat[paramInt3] = 0.0F; 
        return;
      case 68:
        arrayOfDouble = (double[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfDouble[paramInt3] = 0.0D; 
        return;
      case 90:
        arrayOfBoolean = (boolean[])paramObject;
        while (--paramInt3 >= paramInt2)
          arrayOfBoolean[paramInt3] = false; 
        return;
    } 
    Object[] arrayOfObject = (Object[])paramObject;
    while (--paramInt3 >= paramInt2)
      arrayOfObject[paramInt3] = null; 
  }
  
  public static void adjustArray(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4) {
    int j;
    int k;
    int m;
    if (paramInt3 >= paramInt2)
      return; 
    int i = paramInt2 + paramInt4;
    if (paramInt4 >= 0) {
      j = paramInt3;
      k = paramInt3 + paramInt4;
      m = paramInt2 - paramInt3;
    } else {
      j = paramInt3 - paramInt4;
      k = paramInt3;
      m = paramInt2 - paramInt3 + paramInt4;
    } 
    if (m > 0)
      System.arraycopy(paramObject, j, paramObject, k, m); 
    if (paramInt4 < 0)
      clearArray(paramInt1, paramObject, i, paramInt2); 
  }
  
  public static void sortArray(int[] paramArrayOfint) {
    boolean bool;
    do {
      bool = false;
      for (byte b = 0; b < paramArrayOfint.length - 1; b++) {
        if (paramArrayOfint[b] > paramArrayOfint[b + 1]) {
          int i = paramArrayOfint[b + 1];
          paramArrayOfint[b + 1] = paramArrayOfint[b];
          paramArrayOfint[b] = i;
          bool = true;
        } 
      } 
    } while (bool);
  }
  
  public static int find(Object[] paramArrayOfObject, Object paramObject) {
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      if (paramArrayOfObject[b] == paramObject)
        return b; 
      if (paramObject != null && paramObject.equals(paramArrayOfObject[b]))
        return b; 
    } 
    return -1;
  }
  
  public static int find(int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfint[b] == paramInt)
        return b; 
    } 
    return -1;
  }
  
  public static int find(short[] paramArrayOfshort, int paramInt) {
    for (byte b = 0; b < paramArrayOfshort.length; b++) {
      if (paramArrayOfshort[b] == paramInt)
        return b; 
    } 
    return -1;
  }
  
  public static int find(short[] paramArrayOfshort, int paramInt1, int paramInt2, int paramInt3) {
    for (int i = paramInt2; i < paramInt2 + paramInt3; i++) {
      if (paramArrayOfshort[i] == paramInt1)
        return i; 
    } 
    return -1;
  }
  
  public static int findNot(int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfint[b] != paramInt)
        return b; 
    } 
    return -1;
  }
  
  public static boolean areEqualSets(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    return (paramArrayOfint1.length == paramArrayOfint2.length && haveEqualSets(paramArrayOfint1, paramArrayOfint2, paramArrayOfint1.length));
  }
  
  public static boolean areEqual(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt, boolean paramBoolean) {
    return haveEqualArrays(paramArrayOfint1, paramArrayOfint2, paramInt) ? (paramBoolean ? ((paramArrayOfint1.length == paramArrayOfint2.length && paramInt == paramArrayOfint1.length)) : true) : false;
  }
  
  public static boolean haveEqualSets(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    if (haveEqualArrays(paramArrayOfint1, paramArrayOfint2, paramInt))
      return true; 
    if (paramInt > paramArrayOfint1.length || paramInt > paramArrayOfint2.length)
      return false; 
    if (paramInt == 1)
      return (paramArrayOfint1[0] == paramArrayOfint2[0]); 
    int[] arrayOfInt1 = (int[])resizeArray(paramArrayOfint1, paramInt);
    int[] arrayOfInt2 = (int[])resizeArray(paramArrayOfint2, paramInt);
    sortArray(arrayOfInt1);
    sortArray(arrayOfInt2);
    for (byte b = 0; b < paramInt; b++) {
      if (arrayOfInt1[b] != arrayOfInt2[b])
        return false; 
    } 
    return true;
  }
  
  public static boolean haveEqualArrays(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    if (paramInt > paramArrayOfint1.length || paramInt > paramArrayOfint2.length)
      return false; 
    for (byte b = 0; b < paramInt; b++) {
      if (paramArrayOfint1[b] != paramArrayOfint2[b])
        return false; 
    } 
    return true;
  }
  
  public static boolean haveEqualArrays(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt) {
    if (paramInt > paramArrayOfObject1.length || paramInt > paramArrayOfObject2.length)
      return false; 
    for (byte b = 0; b < paramInt; b++) {
      if (paramArrayOfObject1[b] != paramArrayOfObject2[b] && (paramArrayOfObject1[b] == null || !paramArrayOfObject1[b].equals(paramArrayOfObject2[b])))
        return false; 
    } 
    return true;
  }
  
  public static boolean haveCommonElement(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    if (paramArrayOfint1 == null || paramArrayOfint2 == null)
      return false; 
    for (byte b = 0; b < paramArrayOfint1.length; b++) {
      int i = paramArrayOfint1[b];
      for (byte b1 = 0; b1 < paramArrayOfint2.length; b1++) {
        if (i == paramArrayOfint2[b1])
          return true; 
      } 
    } 
    return false;
  }
  
  public static int[] commonElements(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    int[] arrayOfInt = null;
    int i = countCommonElements(paramArrayOfint1, paramArrayOfint2);
    if (i > 0) {
      arrayOfInt = new int[i];
      byte b1 = 0;
      for (byte b2 = 0; b2 < paramArrayOfint1.length; b2++) {
        for (byte b = 0; b < paramArrayOfint2.length; b++) {
          if (paramArrayOfint1[b2] == paramArrayOfint2[b])
            arrayOfInt[b1++] = paramArrayOfint1[b2]; 
        } 
      } 
    } 
    return arrayOfInt;
  }
  
  public static int countCommonElements(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfint1.length; b2++) {
      for (byte b = 0; b < paramArrayOfint2.length; b++) {
        if (paramArrayOfint1[b2] == paramArrayOfint2[b]) {
          b1++;
          break;
        } 
      } 
    } 
    return b1;
  }
  
  public static int countCommonElements(Object[] paramArrayOfObject1, int paramInt, Object[] paramArrayOfObject2) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt; b2++) {
      for (byte b = 0; b < paramArrayOfObject2.length; b++) {
        if (paramArrayOfObject1[b2] == paramArrayOfObject2[b]) {
          b1++;
          break;
        } 
      } 
    } 
    return b1;
  }
  
  public static int countSameElements(byte[] paramArrayOfbyte1, int paramInt, byte[] paramArrayOfbyte2) {
    byte b1 = 0;
    int i = paramArrayOfbyte1.length - paramInt;
    if (i > paramArrayOfbyte2.length)
      i = paramArrayOfbyte2.length; 
    for (byte b2 = 0; b2 < i && paramArrayOfbyte1[b2 + paramInt] == paramArrayOfbyte2[b2]; b2++)
      b1++; 
    return b1;
  }
  
  public static int countSameElements(char[] paramArrayOfchar1, int paramInt, char[] paramArrayOfchar2) {
    byte b1 = 0;
    int i = paramArrayOfchar1.length - paramInt;
    if (i > paramArrayOfchar2.length)
      i = paramArrayOfchar2.length; 
    for (byte b2 = 0; b2 < i && paramArrayOfchar1[b2 + paramInt] == paramArrayOfchar2[b2]; b2++)
      b1++; 
    return b1;
  }
  
  public static int[] union(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    int i = paramArrayOfint1.length + paramArrayOfint2.length - countCommonElements(paramArrayOfint1, paramArrayOfint2);
    if (i > paramArrayOfint1.length && i > paramArrayOfint2.length) {
      int[] arrayOfInt = (int[])resizeArray(paramArrayOfint2, i);
      int j = paramArrayOfint2.length;
      for (byte b = 0; b < paramArrayOfint1.length; b++) {
        byte b1 = 0;
        while (true) {
          if (b1 < paramArrayOfint2.length) {
            if (paramArrayOfint1[b] == paramArrayOfint2[b1])
              break; 
            b1++;
            continue;
          } 
          arrayOfInt[j++] = paramArrayOfint1[b];
          break;
        } 
      } 
      return arrayOfInt;
    } 
    return (paramArrayOfint1.length > paramArrayOfint2.length) ? paramArrayOfint1 : paramArrayOfint2;
  }
  
  public static int find(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) {
    int i = paramInt1;
    paramInt2 = paramInt2 - paramArrayOfbyte2.length + 1;
    byte b = paramArrayOfbyte2[0];
    while (i < paramInt2) {
      if (paramArrayOfbyte1[i] == b) {
        if (paramArrayOfbyte2.length == 1)
          return i; 
        if (containsAt(paramArrayOfbyte1, i, paramArrayOfbyte2))
          return i; 
      } 
      i++;
    } 
    return -1;
  }
  
  public static int findNotIn(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) {
    byte b = 0;
    if (b < paramInt2) {
      for (byte b1 = 0; b1 < paramArrayOfbyte2.length; b1++) {
        if (paramArrayOfbyte1[b] == paramArrayOfbyte2[b1]);
      } 
      return b;
    } 
    return -1;
  }
  
  public static int findIn(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) {
    for (byte b = 0; b < paramInt2; b++) {
      for (byte b1 = 0; b1 < paramArrayOfbyte2.length; b1++) {
        if (paramArrayOfbyte1[b] == paramArrayOfbyte2[b1])
          return b; 
      } 
    } 
    return -1;
  }
  
  public static int find(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    for (byte b = 0; b < paramInt2; b++) {
      if (paramArrayOfbyte[b] == paramInt3 || paramArrayOfbyte[b] == paramInt4)
        return b; 
    } 
    return -1;
  }
  
  public static int[] booleanArrayToIntIndexes(boolean[] paramArrayOfboolean) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfboolean.length; b2++) {
      if (paramArrayOfboolean[b2])
        b1++; 
    } 
    int[] arrayOfInt = new int[b1];
    b1 = 0;
    for (byte b3 = 0; b3 < paramArrayOfboolean.length; b3++) {
      if (paramArrayOfboolean[b3])
        arrayOfInt[b1++] = b3; 
    } 
    return arrayOfInt;
  }
  
  public static void intIndexesToBooleanArray(int[] paramArrayOfint, boolean[] paramArrayOfboolean) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfint[b] < paramArrayOfboolean.length)
        paramArrayOfboolean[paramArrayOfint[b]] = true; 
    } 
  }
  
  public static int countStartIntIndexesInBooleanArray(int[] paramArrayOfint, boolean[] paramArrayOfboolean) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfint.length && paramArrayOfboolean[paramArrayOfint[b2]]; b2++)
      b1++; 
    return b1;
  }
  
  public static void orBooleanArray(boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2) {
    for (byte b = 0; b < paramArrayOfboolean2.length; b++)
      paramArrayOfboolean2[b] = paramArrayOfboolean2[b] | paramArrayOfboolean1[b]; 
  }
  
  public static boolean areAllIntIndexesAsBooleanArray(int[] paramArrayOfint, boolean[] paramArrayOfboolean) {
    byte b = 0;
    while (b < paramArrayOfint.length) {
      if (paramArrayOfboolean[paramArrayOfint[b]]) {
        b++;
        continue;
      } 
      return false;
    } 
    return (paramArrayOfint.length == countTrueElements(paramArrayOfboolean));
  }
  
  public static boolean areAllIntIndexesInBooleanArray(int[] paramArrayOfint, boolean[] paramArrayOfboolean) {
    byte b = 0;
    while (b < paramArrayOfint.length) {
      if (paramArrayOfboolean[paramArrayOfint[b]]) {
        b++;
        continue;
      } 
      return false;
    } 
    return true;
  }
  
  public static boolean isAnyIntIndexInBooleanArray(int[] paramArrayOfint, boolean[] paramArrayOfboolean) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfboolean[paramArrayOfint[b]])
        return true; 
    } 
    return false;
  }
  
  public static boolean containsAllTrueElements(boolean[] paramArrayOfboolean1, boolean[] paramArrayOfboolean2) {
    for (byte b = 0; b < paramArrayOfboolean1.length; b++) {
      if (paramArrayOfboolean2[b] && !paramArrayOfboolean1[b])
        return false; 
    } 
    return true;
  }
  
  public static int countTrueElements(boolean[] paramArrayOfboolean) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfboolean.length; b2++) {
      if (paramArrayOfboolean[b2])
        b1++; 
    } 
    return b1;
  }
  
  public static boolean hasNull(Object[] paramArrayOfObject, int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfObject[paramArrayOfint[b]] == null)
        return true; 
    } 
    return false;
  }
  
  public static boolean hasAllNull(Object[] paramArrayOfObject, int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfObject[paramArrayOfint[b]] != null)
        return false; 
    } 
    return true;
  }
  
  public static boolean containsAt(byte[] paramArrayOfbyte1, int paramInt, byte[] paramArrayOfbyte2) {
    return (countSameElements(paramArrayOfbyte1, paramInt, paramArrayOfbyte2) == paramArrayOfbyte2.length);
  }
  
  public static int countStartElementsAt(byte[] paramArrayOfbyte1, int paramInt, byte[] paramArrayOfbyte2) {
    byte b = 0;
    int i = paramInt;
    while (i < paramArrayOfbyte1.length) {
      byte b1 = 0;
      while (b1 < paramArrayOfbyte2.length) {
        if (paramArrayOfbyte1[i] == paramArrayOfbyte2[b1]) {
          b++;
        } else {
          b1++;
          continue;
        } 
        i++;
      } 
    } 
    return b;
  }
  
  public static boolean containsAt(char[] paramArrayOfchar1, int paramInt, char[] paramArrayOfchar2) {
    return (countSameElements(paramArrayOfchar1, paramInt, paramArrayOfchar2) == paramArrayOfchar2.length);
  }
  
  public static int countNonStartElementsAt(byte[] paramArrayOfbyte1, int paramInt, byte[] paramArrayOfbyte2) {
    byte b = 0;
    int i;
    label14: for (i = paramInt; i < paramArrayOfbyte1.length; i++) {
      for (byte b1 = 0; b1 < paramArrayOfbyte2.length; b1++) {
        if (paramArrayOfbyte1[i] == paramArrayOfbyte2[b1])
          break label14; 
      } 
      b++;
    } 
    return b;
  }
  
  public static int copyBytes(long paramLong1, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, long paramLong2, byte[] paramArrayOfbyte2, int paramInt3) {
    if (paramInt1 >= paramArrayOfbyte1.length)
      return 0; 
    if (paramInt1 + paramInt2 > paramArrayOfbyte1.length)
      paramInt2 = paramArrayOfbyte1.length - paramInt1; 
    if (paramInt3 > paramArrayOfbyte2.length)
      paramInt3 = paramArrayOfbyte2.length; 
    if (paramLong1 + paramInt1 >= paramLong2 + paramInt3 || paramLong1 + paramInt1 + paramInt2 <= paramLong2)
      return 0; 
    long l1 = paramLong2 - paramLong1;
    long l2 = 0L;
    int i = paramInt1 + paramInt2;
    if (l1 >= 0L) {
      if (l1 < paramInt1)
        l1 = paramInt1; 
    } else {
      l2 = -l1 + paramInt1;
      l1 = paramInt1;
    } 
    paramInt2 = i - (int)l1;
    if (paramInt2 > paramInt3 - l2)
      paramInt2 = paramInt3 - (int)l2; 
    System.arraycopy(paramArrayOfbyte1, (int)l1, paramArrayOfbyte2, (int)l2, paramInt2);
    return paramInt2;
  }
  
  public static byte[] copyBytes(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt) {
    if (paramArrayOfbyte1.length + paramInt > paramArrayOfbyte2.length) {
      byte[] arrayOfByte = new byte[paramArrayOfbyte1.length + paramInt];
      System.arraycopy(paramArrayOfbyte2, 0, arrayOfByte, 0, paramArrayOfbyte2.length);
      paramArrayOfbyte2 = arrayOfByte;
    } 
    System.arraycopy(paramArrayOfbyte1, 0, paramArrayOfbyte2, paramInt, paramArrayOfbyte1.length);
    return paramArrayOfbyte2;
  }
  
  public static void copyArray(Object paramObject1, Object paramObject2, int paramInt) {
    System.arraycopy(paramObject1, 0, paramObject2, 0, paramInt);
  }
  
  public static void copyMoveSegment(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    boolean bool = (paramInt2 < paramInt4) ? true : false;
    int i = bool ? paramInt2 : paramInt4;
    System.arraycopy(paramObject1, 0, paramObject2, 0, i);
    i = bool ? (paramInt1 - paramInt4 - paramInt3) : (paramInt1 - paramInt2 - paramInt3);
    int j = bool ? (paramInt4 + paramInt3) : (paramInt2 + paramInt3);
    System.arraycopy(paramObject1, j, paramObject2, j, i);
    System.arraycopy(paramObject1, paramInt2, paramObject2, paramInt4, paramInt3);
    i = Math.abs(paramInt2 - paramInt4);
    j = bool ? (paramInt2 + paramInt3) : paramInt4;
    int k = bool ? paramInt2 : (paramInt4 + paramInt3);
    System.arraycopy(paramObject1, j, paramObject2, k, i);
  }
  
  public static int[] arraySlice(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    int[] arrayOfInt = new int[paramInt2];
    System.arraycopy(paramArrayOfint, paramInt1, arrayOfInt, 0, paramInt2);
    return arrayOfInt;
  }
  
  public static void fillArray(char[] paramArrayOfchar, int paramInt, char paramChar) {
    int i = paramArrayOfchar.length;
    while (--i >= paramInt)
      paramArrayOfchar[i] = paramChar; 
  }
  
  public static void fillArray(byte[] paramArrayOfbyte, int paramInt, byte paramByte) {
    int i = paramArrayOfbyte.length;
    while (--i >= paramInt)
      paramArrayOfbyte[i] = paramByte; 
  }
  
  public static void fillArray(Object[] paramArrayOfObject, Object paramObject) {
    int i = paramArrayOfObject.length;
    while (--i >= 0)
      paramArrayOfObject[i] = paramObject; 
  }
  
  public static void fillArray(int[] paramArrayOfint, int paramInt) {
    int i = paramArrayOfint.length;
    while (--i >= 0)
      paramArrayOfint[i] = paramInt; 
  }
  
  public static void fillArray(double[] paramArrayOfdouble, double paramDouble) {
    int i = paramArrayOfdouble.length;
    while (--i >= 0)
      paramArrayOfdouble[i] = paramDouble; 
  }
  
  public static void fillArray(boolean[] paramArrayOfboolean, boolean paramBoolean) {
    int i = paramArrayOfboolean.length;
    while (--i >= 0)
      paramArrayOfboolean[i] = paramBoolean; 
  }
  
  public static Object duplicateArray(Object paramObject) {
    int i = Array.getLength(paramObject);
    Object object = Array.newInstance(paramObject.getClass().getComponentType(), i);
    System.arraycopy(paramObject, 0, object, 0, i);
    return object;
  }
  
  public static Object resizeArrayIfDifferent(Object paramObject, int paramInt) {
    int i = Array.getLength(paramObject);
    if (i == paramInt)
      return paramObject; 
    Object object = Array.newInstance(paramObject.getClass().getComponentType(), paramInt);
    if (i < paramInt)
      paramInt = i; 
    System.arraycopy(paramObject, 0, object, 0, paramInt);
    return object;
  }
  
  public static Object resizeArray(Object paramObject, int paramInt) {
    Object object = Array.newInstance(paramObject.getClass().getComponentType(), paramInt);
    int i = Array.getLength(paramObject);
    if (i < paramInt)
      paramInt = i; 
    System.arraycopy(paramObject, 0, object, 0, paramInt);
    return object;
  }
  
  public static Object toAdjustedArray(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2) {
    int i = Array.getLength(paramObject1) + paramInt2;
    Object object = Array.newInstance(paramObject1.getClass().getComponentType(), i);
    copyAdjustArray(paramObject1, object, paramObject2, paramInt1, paramInt2);
    return object;
  }
  
  public static void copyAdjustArray(Object paramObject1, Object paramObject2, Object paramObject3, int paramInt1, int paramInt2) {
    int i = Array.getLength(paramObject1);
    if (paramInt1 < 0) {
      System.arraycopy(paramObject1, 0, paramObject2, 0, i);
      return;
    } 
    System.arraycopy(paramObject1, 0, paramObject2, 0, paramInt1);
    if (paramInt2 == 0) {
      int j = i - paramInt1 - 1;
      Array.set(paramObject2, paramInt1, paramObject3);
      if (j > 0)
        System.arraycopy(paramObject1, paramInt1 + 1, paramObject2, paramInt1 + 1, j); 
    } else if (paramInt2 < 0) {
      int j = i - paramInt1 - 1;
      if (j > 0)
        System.arraycopy(paramObject1, paramInt1 + 1, paramObject2, paramInt1, j); 
    } else {
      int j = i - paramInt1;
      Array.set(paramObject2, paramInt1, paramObject3);
      if (j > 0)
        System.arraycopy(paramObject1, paramInt1, paramObject2, paramInt1 + 1, j); 
    } 
  }
  
  public static int[] toAdjustedColumnArray(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    if (paramArrayOfint == null)
      return null; 
    if (paramInt1 < 0)
      return paramArrayOfint; 
    int[] arrayOfInt = new int[paramArrayOfint.length];
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfint.length; b2++) {
      if (paramArrayOfint[b2] > paramInt1) {
        arrayOfInt[b1] = paramArrayOfint[b2] + paramInt2;
        b1++;
      } else if (paramArrayOfint[b2] == paramInt1) {
        if (paramInt2 >= 0) {
          arrayOfInt[b1] = paramArrayOfint[b2] + paramInt2;
          b1++;
        } 
      } else {
        arrayOfInt[b1] = paramArrayOfint[b2];
        b1++;
      } 
    } 
    if (paramArrayOfint.length != b1) {
      int[] arrayOfInt1 = new int[b1];
      copyArray(arrayOfInt, arrayOfInt1, b1);
      return arrayOfInt1;
    } 
    return arrayOfInt;
  }
  
  public static void projectRow(Object[] paramArrayOfObject1, int[] paramArrayOfint, Object[] paramArrayOfObject2) {
    for (byte b = 0; b < paramArrayOfint.length; b++)
      paramArrayOfObject2[b] = paramArrayOfObject1[paramArrayOfint[b]]; 
  }
  
  public static void projectRow(int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) {
    for (byte b = 0; b < paramArrayOfint2.length; b++)
      paramArrayOfint3[b] = paramArrayOfint1[paramArrayOfint2[b]]; 
  }
  
  public static void projectRowReverse(Object[] paramArrayOfObject1, int[] paramArrayOfint, Object[] paramArrayOfObject2) {
    for (byte b = 0; b < paramArrayOfint.length; b++)
      paramArrayOfObject1[paramArrayOfint[b]] = paramArrayOfObject2[b]; 
  }
  
  public static void projectMap(int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) {
    for (byte b = 0; b < paramArrayOfint2.length; b++) {
      for (byte b1 = 0; b1 < paramArrayOfint1.length; b1++) {
        if (paramArrayOfint2[b] == paramArrayOfint1[b1]) {
          paramArrayOfint3[b] = b1;
          break;
        } 
      } 
    } 
  }
  
  public static void reorderMaps(int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) {
    for (byte b = 0; b < paramArrayOfint1.length; b++) {
      for (byte b1 = b; b1 < paramArrayOfint2.length; b1++) {
        if (paramArrayOfint1[b] == paramArrayOfint2[b1]) {
          int i = paramArrayOfint2[b];
          paramArrayOfint2[b] = paramArrayOfint2[b1];
          paramArrayOfint2[b1] = i;
          i = paramArrayOfint3[b];
          paramArrayOfint3[b] = paramArrayOfint3[b1];
          paramArrayOfint3[b1] = i;
          break;
        } 
      } 
    } 
  }
  
  public static void fillSequence(int[] paramArrayOfint) {
    for (byte b = 0; b < paramArrayOfint.length; b++)
      paramArrayOfint[b] = b; 
  }
  
  public static char[] byteArrayToChars(byte[] paramArrayOfbyte) {
    return byteArrayToChars(paramArrayOfbyte, paramArrayOfbyte.length);
  }
  
  public static char[] byteArrayToChars(byte[] paramArrayOfbyte, int paramInt) {
    char[] arrayOfChar = new char[paramInt / 2];
    byte b1 = 0;
    for (byte b2 = 0; b2 < arrayOfChar.length; b2++) {
      arrayOfChar[b2] = (char)((paramArrayOfbyte[b1] << 8) + (paramArrayOfbyte[b1 + 1] & 0xFF));
      b1 += 2;
    } 
    return arrayOfChar;
  }
  
  public static byte[] charArrayToBytes(char[] paramArrayOfchar) {
    return charArrayToBytes(paramArrayOfchar, paramArrayOfchar.length);
  }
  
  public static byte[] charArrayToBytes(char[] paramArrayOfchar, int paramInt) {
    byte[] arrayOfByte = new byte[paramInt * 2];
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt; b2++) {
      char c = paramArrayOfchar[b2];
      arrayOfByte[b1] = (byte)(c >> 8);
      arrayOfByte[b1 + 1] = (byte)c;
      b1 += 2;
    } 
    return arrayOfByte;
  }
  
  public static boolean isInSortedArray(char paramChar, char[] paramArrayOfchar) {
    if (paramArrayOfchar.length == 0 || paramChar < paramArrayOfchar[0] || paramChar > paramArrayOfchar[paramArrayOfchar.length - 1])
      return false; 
    int i = 0;
    int j = paramArrayOfchar.length;
    int k = 0;
    while (i < j) {
      k = i + j >>> 1;
      if (paramChar < paramArrayOfchar[k]) {
        j = k;
        continue;
      } 
      if (paramChar > paramArrayOfchar[k]) {
        i = k + 1;
        continue;
      } 
      return true;
    } 
    return false;
  }
  
  public static boolean containsAll(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    for (byte b = 0; b < paramArrayOfObject2.length; b++) {
      byte b1 = 0;
      while (true) {
        if (b1 < paramArrayOfObject1.length) {
          if (paramArrayOfObject2[b] == paramArrayOfObject1[b1] || paramArrayOfObject2[b].equals(paramArrayOfObject1[b1]))
            break; 
          b1++;
          continue;
        } 
        return false;
      } 
    } 
    return true;
  }
  
  public static boolean containsAny(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    for (byte b = 0; b < paramArrayOfObject2.length; b++) {
      for (byte b1 = 0; b1 < paramArrayOfObject1.length; b1++) {
        if (paramArrayOfObject2[b] == paramArrayOfObject1[b1] || paramArrayOfObject2[b].equals(paramArrayOfObject1[b1]))
          return true; 
      } 
    } 
    return false;
  }
  
  public static boolean containsAll(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    for (byte b = 0; b < paramArrayOfint2.length; b++) {
      byte b1 = 0;
      while (true) {
        if (b1 < paramArrayOfint1.length) {
          if (paramArrayOfint2[b] == paramArrayOfint1[b1])
            break; 
          b1++;
          continue;
        } 
        return false;
      } 
    } 
    return true;
  }
  
  public static boolean containsAllAtStart(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    if (paramArrayOfint2.length > paramArrayOfint1.length)
      return false; 
    for (byte b = 0; b < paramArrayOfint1.length; b++) {
      if (b == paramArrayOfint2.length)
        return true; 
      byte b1 = 0;
      while (true) {
        if (b1 < paramArrayOfint2.length) {
          if (paramArrayOfint1[b] == paramArrayOfint2[b1])
            break; 
          b1++;
          continue;
        } 
        return false;
      } 
    } 
    return true;
  }
  
  public static byte[] toByteArray(long paramLong1, long paramLong2) {
    byte[] arrayOfByte = new byte[16];
    byte b = 0;
    while (b < 16) {
      int i;
      if (b == 0) {
        i = (int)(paramLong1 >>> 32L);
      } else if (b == 4) {
        i = (int)paramLong1;
      } else if (b == 8) {
        i = (int)(paramLong2 >>> 32L);
      } else {
        i = (int)paramLong2;
      } 
      arrayOfByte[b++] = (byte)(i >>> 24);
      arrayOfByte[b++] = (byte)(i >>> 16);
      arrayOfByte[b++] = (byte)(i >>> 8);
      arrayOfByte[b++] = (byte)i;
    } 
    return arrayOfByte;
  }
  
  public static int compare(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    return compare(paramArrayOfbyte1, paramArrayOfbyte1.length, paramArrayOfbyte2, paramArrayOfbyte2.length);
  }
  
  public static int compare(byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, int paramInt2) {
    int i = paramInt1;
    if (i > paramInt2)
      i = paramInt2; 
    byte b = 0;
    while (b < i) {
      if (paramArrayOfbyte1[b] == paramArrayOfbyte2[b]) {
        b++;
        continue;
      } 
      return ((paramArrayOfbyte1[b] & 0xFF) > (paramArrayOfbyte2[b] & 0xFF)) ? 1 : -1;
    } 
    return (paramInt1 == paramInt2) ? 0 : ((paramInt1 < paramInt2) ? -1 : 1);
  }
  
  public static long getBinaryMultipleCeiling(long paramLong1, long paramLong2) {
    long l = paramLong1 & -paramLong2;
    if (l != paramLong1)
      l += paramLong2; 
    return l;
  }
  
  public static long getBinaryNormalisedCeiling(long paramLong, int paramInt) {
    long l1 = -1L << paramInt;
    long l2 = paramLong & l1;
    if (l2 != paramLong)
      l2 += (1 << paramInt); 
    return l2;
  }
  
  public static boolean isTwoPower(int paramInt1, int paramInt2) {
    for (byte b = 0; b <= paramInt2; b++) {
      if ((paramInt1 & 0x1) != 0)
        return (paramInt1 == 1); 
      paramInt1 >>= 1;
    } 
    return false;
  }
  
  public static int getTwoPowerFloor(int paramInt) {
    byte b1 = 0;
    if (paramInt == 0)
      return 0; 
    for (byte b2 = 0; b2 < 32; b2++) {
      if ((paramInt & 0x1) != 0)
        b1 = b2; 
      paramInt >>= 1;
    } 
    return 1 << b1;
  }
  
  static {
    classCodeMap.put(byte.class, 66);
    classCodeMap.put(char.class, 83);
    classCodeMap.put(short.class, 83);
    classCodeMap.put(int.class, 73);
    classCodeMap.put(long.class, 74);
    classCodeMap.put(float.class, 70);
    classCodeMap.put(double.class, 68);
    classCodeMap.put(boolean.class, 90);
    classCodeMap.put(Object.class, 76);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ArrayUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */