package org.hsqldb.lib;

import java.util.Comparator;

public class ArraySort {
  public static int searchFirst(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, Comparator<Object> paramComparator) {
    int i = paramInt1;
    int j = paramInt2;
    int k = paramInt1;
    int m = 0;
    int n;
    for (n = paramInt2; i < j; n = k) {
      k = i + j >>> 1;
      m = paramComparator.compare(paramObject, paramArrayOfObject[k]);
      if (m < 0) {
        j = k;
        continue;
      } 
      if (m > 0) {
        i = k + 1;
        continue;
      } 
      j = k;
    } 
    return (n == paramInt2) ? (-i - 1) : n;
  }
  
  public static int deDuplicate(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Comparator<Object> paramComparator) {
    int i = paramInt1;
    int j = paramInt1 + 1;
    if (paramArrayOfObject.length == 0)
      return 0; 
    while (j < paramInt2) {
      int k = paramComparator.compare(paramArrayOfObject[i], paramArrayOfObject[j]);
      if (k != 0)
        paramArrayOfObject[++i] = paramArrayOfObject[j]; 
      j++;
    } 
    return i + 1;
  }
  
  public static void sort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Comparator paramComparator) {
    if (paramInt1 + 1 >= paramInt2)
      return; 
    quickSort(paramArrayOfObject, paramComparator, paramInt1, paramInt2 - 1);
    insertionSort(paramArrayOfObject, paramComparator, paramInt1, paramInt2 - 1);
  }
  
  static void quickSort(Object[] paramArrayOfObject, Comparator<Object> paramComparator, int paramInt1, int paramInt2) {
    byte b = 16;
    if (paramInt2 - paramInt1 > b) {
      int i = paramInt2 + paramInt1 >>> 1;
      if (paramComparator.compare(paramArrayOfObject[i], paramArrayOfObject[paramInt1]) < 0)
        swap(paramArrayOfObject, paramInt1, i); 
      if (paramComparator.compare(paramArrayOfObject[paramInt2], paramArrayOfObject[paramInt1]) < 0)
        swap(paramArrayOfObject, paramInt1, paramInt2); 
      if (paramComparator.compare(paramArrayOfObject[paramInt2], paramArrayOfObject[i]) < 0)
        swap(paramArrayOfObject, i, paramInt2); 
      int j = paramInt2 - 1;
      swap(paramArrayOfObject, i, j);
      i = paramInt1;
      int k = j;
      while (true) {
        if (paramComparator.compare(paramArrayOfObject[++i], paramArrayOfObject[k]) < 0)
          continue; 
        while (paramComparator.compare(paramArrayOfObject[k], paramArrayOfObject[--j]) < 0);
        if (j < i) {
          swap(paramArrayOfObject, i, paramInt2 - 1);
          quickSort(paramArrayOfObject, paramComparator, paramInt1, j);
          quickSort(paramArrayOfObject, paramComparator, i + 1, paramInt2);
          break;
        } 
        swap(paramArrayOfObject, i, j);
      } 
    } 
  }
  
  public static void insertionSort(Object[] paramArrayOfObject, Comparator<Object> paramComparator, int paramInt1, int paramInt2) {
    for (int i = paramInt1 + 1; i <= paramInt2; i++) {
      int j;
      for (j = i; j > paramInt1 && paramComparator.compare(paramArrayOfObject[i], paramArrayOfObject[j - 1]) < 0; j--);
      if (i != j)
        moveAndInsertRow(paramArrayOfObject, i, j); 
    } 
  }
  
  private static void swap(Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
    Object object = paramArrayOfObject[paramInt1];
    paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
    paramArrayOfObject[paramInt2] = object;
  }
  
  private static void moveAndInsertRow(Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
    Object object = paramArrayOfObject[paramInt1];
    moveRows(paramArrayOfObject, paramInt2, paramInt2 + 1, paramInt1 - paramInt2);
    paramArrayOfObject[paramInt2] = object;
  }
  
  private static void moveRows(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3) {
    System.arraycopy(paramArrayOfObject, paramInt1, paramArrayOfObject, paramInt2, paramInt3);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ArraySort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */