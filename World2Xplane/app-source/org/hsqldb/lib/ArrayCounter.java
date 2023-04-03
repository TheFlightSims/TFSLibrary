package org.hsqldb.lib;

public class ArrayCounter {
  public static int[] countSegments(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int[] arrayOfInt = new int[paramInt2];
    long l = calcInterval(paramInt2, paramInt3, paramInt4);
    int i = 0;
    int j = 0;
    if (l <= 0L)
      return arrayOfInt; 
    for (byte b = 0; b < paramInt1; b++) {
      j = paramArrayOfint[b];
      if (j >= paramInt3 && j < paramInt4) {
        i = (int)((j - paramInt3) / l);
        arrayOfInt[i] = arrayOfInt[i] + 1;
      } 
    } 
    return arrayOfInt;
  }
  
  public static int rank(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int i = 0;
    int j;
    for (j = paramInt4;; j = (paramInt3 + l < paramInt4) ? (int)(paramInt3 + l) : paramInt4) {
      long l = calcInterval(256, paramInt3, j);
      int[] arrayOfInt = countSegments(paramArrayOfint, paramInt1, 256, paramInt3, j);
      for (byte b = 0; b < arrayOfInt.length && i + arrayOfInt[b] < paramInt2; b++) {
        i += arrayOfInt[b];
        paramInt3 = (int)(paramInt3 + l);
      } 
      if (i + paramInt5 >= paramInt2)
        return paramInt3; 
      if (l <= 1L)
        return paramInt3; 
    } 
  }
  
  static long calcInterval(int paramInt1, int paramInt2, int paramInt3) {
    long l = (paramInt3 - paramInt2);
    if (l < 0L)
      return 0L; 
    boolean bool = (l % paramInt1 == 0L) ? false : true;
    return l / paramInt1 + bool;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ArrayCounter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */