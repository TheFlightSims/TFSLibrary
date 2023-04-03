package org.hsqldb.lib;

import java.lang.reflect.Array;

public class StringUtil {
  public static String toZeroPaddedString(long paramLong, int paramInt1, int paramInt2) {
    StringBuffer stringBuffer = new StringBuffer();
    if (paramLong < 0L)
      paramLong = -paramLong; 
    String str = Long.toString(paramLong);
    if (str.length() > paramInt1)
      str = str.substring(paramInt1); 
    for (int i = str.length(); i < paramInt1; i++)
      stringBuffer.append('0'); 
    stringBuffer.append(str);
    if (paramInt2 < paramInt1)
      stringBuffer.setLength(paramInt2); 
    return stringBuffer.toString();
  }
  
  public static String toPaddedString(String paramString, int paramInt, char paramChar, boolean paramBoolean) {
    int i = paramString.length();
    if (i >= paramInt)
      return paramString; 
    StringBuffer stringBuffer = new StringBuffer(paramInt);
    if (paramBoolean)
      stringBuffer.append(paramString); 
    for (int j = i; j < paramInt; j++)
      stringBuffer.append(paramChar); 
    if (!paramBoolean)
      stringBuffer.append(paramString); 
    return stringBuffer.toString();
  }
  
  public static String toPaddedString(String paramString1, int paramInt, String paramString2, boolean paramBoolean) {
    int i = paramString1.length();
    if (i == paramInt)
      return paramString1; 
    if (i > paramInt)
      return paramBoolean ? paramString1.substring(0, paramInt) : paramString1.substring(i - paramInt, i); 
    StringBuffer stringBuffer = new StringBuffer(paramInt);
    int j = paramString1.length();
    int k = (paramInt - j) % paramString2.length();
    if (paramBoolean) {
      stringBuffer.append(paramString1);
      stringBuffer.append(paramString2.substring(paramString2.length() - k, paramString2.length()));
    } 
    while (j + paramString2.length() <= paramInt) {
      stringBuffer.append(paramString2);
      j += paramString2.length();
    } 
    if (!paramBoolean) {
      stringBuffer.append(paramString2.substring(0, k));
      stringBuffer.append(paramString1);
    } 
    return stringBuffer.toString();
  }
  
  public static String toLowerSubset(String paramString, char paramChar) {
    int i = paramString.length();
    StringBuffer stringBuffer = new StringBuffer(i);
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (!Character.isLetterOrDigit(c)) {
        stringBuffer.append(paramChar);
      } else if (b == 0 && Character.isDigit(c)) {
        stringBuffer.append(paramChar);
      } else {
        stringBuffer.append(Character.toLowerCase(c));
      } 
    } 
    return stringBuffer.toString();
  }
  
  public static String arrayToString(Object paramObject) {
    int i = Array.getLength(paramObject);
    int j = i - 1;
    StringBuffer stringBuffer = new StringBuffer(2 * (i + 1));
    stringBuffer.append('{');
    for (byte b = 0; b < i; b++) {
      stringBuffer.append(Array.get(paramObject, b));
      if (b != j)
        stringBuffer.append(','); 
    } 
    stringBuffer.append('}');
    return stringBuffer.toString();
  }
  
  public static String getList(String[] paramArrayOfString, String paramString1, String paramString2) {
    int i = paramArrayOfString.length;
    StringBuffer stringBuffer = new StringBuffer(i * 16);
    for (byte b = 0; b < i; b++) {
      stringBuffer.append(paramString2);
      stringBuffer.append(paramArrayOfString[b]);
      stringBuffer.append(paramString2);
      if (b + 1 < i)
        stringBuffer.append(paramString1); 
    } 
    return stringBuffer.toString();
  }
  
  public static String getList(int[] paramArrayOfint, String paramString1, String paramString2) {
    int i = paramArrayOfint.length;
    StringBuffer stringBuffer = new StringBuffer(i * 8);
    for (byte b = 0; b < i; b++) {
      stringBuffer.append(paramString2);
      stringBuffer.append(paramArrayOfint[b]);
      stringBuffer.append(paramString2);
      if (b + 1 < i)
        stringBuffer.append(paramString1); 
    } 
    return stringBuffer.toString();
  }
  
  public static String getList(long[] paramArrayOflong, String paramString1, String paramString2) {
    int i = paramArrayOflong.length;
    StringBuffer stringBuffer = new StringBuffer(i * 8);
    for (byte b = 0; b < i; b++) {
      stringBuffer.append(paramString2);
      stringBuffer.append(paramArrayOflong[b]);
      stringBuffer.append(paramString2);
      if (b + 1 < i)
        stringBuffer.append(paramString1); 
    } 
    return stringBuffer.toString();
  }
  
  public static String getList(String[][] paramArrayOfString, String paramString1, String paramString2) {
    int i = paramArrayOfString.length;
    StringBuffer stringBuffer = new StringBuffer(i * 16);
    for (byte b = 0; b < i; b++) {
      stringBuffer.append(paramString2);
      stringBuffer.append(paramArrayOfString[b][0]);
      stringBuffer.append(paramString2);
      if (b + 1 < i)
        stringBuffer.append(paramString1); 
    } 
    return stringBuffer.toString();
  }
  
  public static boolean isEmpty(String paramString) {
    byte b = (paramString == null) ? 0 : paramString.length();
    while (b) {
      if (paramString.charAt(--b) > ' ')
        return false; 
    } 
    return true;
  }
  
  public static int rightTrimSize(String paramString) {
    int i = paramString.length();
    while (i > 0) {
      if (paramString.charAt(--i) != ' ')
        return i + 1; 
    } 
    return 0;
  }
  
  public static int skipSpaces(String paramString, int paramInt) {
    int i = paramString.length();
    int j;
    for (j = paramInt; j < i && paramString.charAt(j) == ' '; j++);
    return j;
  }
  
  public static String[] split(String paramString1, String paramString2) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    int i = 0;
    boolean bool = true;
    while (bool) {
      int j = paramString1.indexOf(paramString2, i);
      if (j == -1) {
        j = paramString1.length();
        bool = false;
      } 
      hsqlArrayList.add(paramString1.substring(i, j));
      i = j + paramString2.length();
    } 
    return (String[])hsqlArrayList.toArray(new String[hsqlArrayList.size()]);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\StringUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */