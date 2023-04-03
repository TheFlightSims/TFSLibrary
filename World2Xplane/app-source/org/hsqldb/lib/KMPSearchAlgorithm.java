package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class KMPSearchAlgorithm {
  public static long search(InputStream paramInputStream, byte[] paramArrayOfbyte, int[] paramArrayOfint) throws IOException {
    if (paramInputStream == null || paramArrayOfbyte == null || paramArrayOfbyte.length == 0)
      return -1L; 
    int i = paramArrayOfbyte.length;
    long l = -1L;
    if (i == 1) {
      byte b = paramArrayOfbyte[0];
      int m;
      while (-1 != (m = paramInputStream.read())) {
        l++;
        if (m == b)
          return l; 
      } 
      return -1L;
    } 
    int k = 0;
    if (paramArrayOfint == null)
      paramArrayOfint = computeTable(paramArrayOfbyte); 
    int j;
    while (-1 != (j = paramInputStream.read())) {
      l++;
      if (j == paramArrayOfbyte[k]) {
        k++;
      } else if (k > 0) {
        k = paramArrayOfint[k];
        k++;
      } 
      if (k == i)
        return l - (i - 1); 
    } 
    return -1L;
  }
  
  public static long search(Reader paramReader, char[] paramArrayOfchar, int[] paramArrayOfint) throws IOException {
    if (paramReader == null || paramArrayOfchar == null || paramArrayOfchar.length == 0)
      return -1L; 
    int i = paramArrayOfchar.length;
    long l = -1L;
    if (i == 1) {
      char c = paramArrayOfchar[0];
      int m;
      while (-1 != (m = paramReader.read())) {
        l++;
        if (m == c)
          return l; 
      } 
      return -1L;
    } 
    int k = 0;
    if (paramArrayOfint == null)
      paramArrayOfint = computeTable(paramArrayOfchar); 
    int j;
    while (-1 != (j = paramReader.read())) {
      l++;
      if (j == paramArrayOfchar[k]) {
        k++;
      } else if (k > 0) {
        k = paramArrayOfint[k];
        k++;
      } 
      if (k == i)
        return l - (i - 1); 
    } 
    return -1L;
  }
  
  public static int search(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfbyte1 == null || paramArrayOfbyte2 == null || paramArrayOfbyte2.length == 0)
      return -1; 
    int i = paramArrayOfbyte1.length;
    int j = paramArrayOfbyte2.length;
    int k = paramInt;
    if (j == 1) {
      byte b = paramArrayOfbyte2[0];
      while (k < i) {
        if (paramArrayOfbyte1[k] == b)
          return k; 
        k++;
      } 
      return -1;
    } 
    int m = paramInt;
    int n = 0;
    if (paramArrayOfint == null)
      paramArrayOfint = computeTable(paramArrayOfbyte2); 
    while (k < i && n < j) {
      if (paramArrayOfbyte1[k] == paramArrayOfbyte2[n]) {
        n++;
      } else {
        int i1 = paramArrayOfint[n];
        m += n - i1;
        if (n > 0)
          n = i1; 
        n++;
      } 
      k = m + n;
    } 
    return (n == j) ? m : -1;
  }
  
  public static int search(char[] paramArrayOfchar1, char[] paramArrayOfchar2, int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfchar1 == null || paramArrayOfchar2 == null || paramArrayOfchar2.length == 0)
      return -1; 
    int i = paramArrayOfchar1.length;
    int j = paramArrayOfchar2.length;
    int k = paramInt;
    if (j == 1) {
      char c = paramArrayOfchar2[0];
      while (k < i) {
        if (paramArrayOfchar1[k] == c)
          return k; 
        k++;
      } 
      return -1;
    } 
    int m = paramInt;
    int n = 0;
    if (paramArrayOfint == null)
      paramArrayOfint = computeTable(paramArrayOfchar2); 
    while (k < i && n < j) {
      if (paramArrayOfchar1[k] == paramArrayOfchar2[n]) {
        n++;
      } else {
        int i1 = paramArrayOfint[n];
        m += n - i1;
        if (n > 0)
          n = i1; 
        n++;
      } 
      k = m + n;
    } 
    return (n == j) ? m : -1;
  }
  
  public static int search(String paramString1, String paramString2, int[] paramArrayOfint, int paramInt) {
    if (paramString1 == null || paramString2 == null || paramString2.length() == 0)
      return -1; 
    int i = paramString2.length();
    if (i == 1)
      return paramString1.indexOf(paramString2, paramInt); 
    int j = paramString1.length();
    int k = paramInt;
    int m = paramInt;
    int n = 0;
    if (paramArrayOfint == null)
      paramArrayOfint = computeTable(paramString2); 
    while (m < j && n < i) {
      if (paramString1.charAt(m) == paramString2.charAt(n)) {
        n++;
      } else {
        int i1 = paramArrayOfint[n];
        k += n - i1;
        if (n > 0)
          n = i1; 
        n++;
      } 
      m = k + n;
    } 
    return (n == i) ? k : -1;
  }
  
  public static int[] computeTable(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      throw new IllegalArgumentException("Pattern must  not be null."); 
    if (paramArrayOfbyte.length < 2)
      throw new IllegalArgumentException("Pattern length must be > 1."); 
    int[] arrayOfInt = new int[paramArrayOfbyte.length];
    byte b = 2;
    int i = 0;
    arrayOfInt[0] = -1;
    arrayOfInt[1] = 0;
    while (b < paramArrayOfbyte.length) {
      if (paramArrayOfbyte[b - 1] == paramArrayOfbyte[i]) {
        arrayOfInt[b] = i + 1;
        i++;
        b++;
        continue;
      } 
      if (i > 0) {
        i = arrayOfInt[i];
        continue;
      } 
      arrayOfInt[b] = 0;
      b++;
      i = 0;
    } 
    return arrayOfInt;
  }
  
  public static int[] computeTable(char[] paramArrayOfchar) {
    if (paramArrayOfchar == null)
      throw new IllegalArgumentException("Pattern must  not be null."); 
    if (paramArrayOfchar.length < 2)
      throw new IllegalArgumentException("Pattern length must be > 1."); 
    int[] arrayOfInt = new int[paramArrayOfchar.length];
    byte b = 2;
    int i = 0;
    arrayOfInt[0] = -1;
    arrayOfInt[1] = 0;
    while (b < paramArrayOfchar.length) {
      if (paramArrayOfchar[b - 1] == paramArrayOfchar[i]) {
        arrayOfInt[b] = i + 1;
        i++;
        b++;
        continue;
      } 
      if (i > 0) {
        i = arrayOfInt[i];
        continue;
      } 
      arrayOfInt[b] = 0;
      b++;
      i = 0;
    } 
    return arrayOfInt;
  }
  
  public static int[] computeTable(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("Pattern must  not be null."); 
    if (paramString.length() < 2)
      throw new IllegalArgumentException("Pattern length must be > 1."); 
    int i = paramString.length();
    int[] arrayOfInt = new int[i];
    byte b = 2;
    int j = 0;
    arrayOfInt[0] = -1;
    arrayOfInt[1] = 0;
    while (b < i) {
      if (paramString.charAt(b - 1) == paramString.charAt(j)) {
        arrayOfInt[b] = j + 1;
        j++;
        b++;
        continue;
      } 
      if (j > 0) {
        j = arrayOfInt[j];
        continue;
      } 
      arrayOfInt[b] = 0;
      b++;
      j = 0;
    } 
    return arrayOfInt;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\KMPSearchAlgorithm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */