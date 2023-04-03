package org.hsqldb.map;

import java.math.BigDecimal;

public class ValuePool {
  static ValuePoolHashMap intPool;
  
  static ValuePoolHashMap longPool;
  
  static ValuePoolHashMap doublePool;
  
  static ValuePoolHashMap bigdecimalPool;
  
  static ValuePoolHashMap stringPool;
  
  static final int SPACE_STRING_SIZE = 64;
  
  static final int DEFAULT_VALUE_POOL_SIZE = 4096;
  
  static final int[] defaultPoolLookupSize = new int[] { 4096, 4096, 4096, 4096, 4096 };
  
  static final int POOLS_COUNT = defaultPoolLookupSize.length;
  
  static final int defaultSizeFactor = 2;
  
  static final int defaultMaxStringLength = 16;
  
  static ValuePoolHashMap[] poolList;
  
  static int maxStringLength;
  
  public static final Integer INTEGER_0 = getInt(0);
  
  public static final Integer INTEGER_1 = getInt(1);
  
  public static final Integer INTEGER_2 = getInt(2);
  
  public static final Integer INTEGER_MAX = getInt(2147483647);
  
  public static final BigDecimal BIG_DECIMAL_0 = getBigDecimal(new BigDecimal(0.0D));
  
  public static final BigDecimal BIG_DECIMAL_1 = getBigDecimal(new BigDecimal(1.0D));
  
  public static final String[] emptyStringArray = new String[0];
  
  public static final Object[] emptyObjectArray = new Object[0];
  
  public static final int[] emptyIntArray = new int[0];
  
  public static String spaceString;
  
  private static void initPool() {
    int[] arrayOfInt = defaultPoolLookupSize;
    byte b = 2;
    synchronized (ValuePool.class) {
      maxStringLength = 16;
      poolList = new ValuePoolHashMap[POOLS_COUNT];
      for (byte b1 = 0; b1 < POOLS_COUNT; b1++) {
        int i = arrayOfInt[b1];
        poolList[b1] = new ValuePoolHashMap(i, i * b, 2);
      } 
      intPool = poolList[0];
      longPool = poolList[1];
      doublePool = poolList[2];
      bigdecimalPool = poolList[3];
      stringPool = poolList[4];
      char[] arrayOfChar = new char[64];
      for (byte b2 = 0; b2 < 64; b2++)
        arrayOfChar[b2] = ' '; 
      spaceString = new String(arrayOfChar);
    } 
  }
  
  public static int getMaxStringLength() {
    return maxStringLength;
  }
  
  public static void resetPool(int[] paramArrayOfint, int paramInt) {
    synchronized (ValuePool.class) {
      for (byte b = 0; b < POOLS_COUNT; b++) {
        poolList[b].clear();
        poolList[b].resetCapacity(paramArrayOfint[b] * paramInt, 2);
      } 
    } 
  }
  
  public static void resetPool() {
    synchronized (ValuePool.class) {
      resetPool(defaultPoolLookupSize, 2);
    } 
  }
  
  public static void clearPool() {
    synchronized (ValuePool.class) {
      for (byte b = 0; b < POOLS_COUNT; b++)
        poolList[b].clear(); 
    } 
  }
  
  public static Integer getInt(int paramInt) {
    synchronized (intPool) {
      return intPool.getOrAddInteger(paramInt);
    } 
  }
  
  public static Long getLong(long paramLong) {
    synchronized (longPool) {
      return longPool.getOrAddLong(paramLong);
    } 
  }
  
  public static Double getDouble(long paramLong) {
    synchronized (doublePool) {
      return doublePool.getOrAddDouble(paramLong);
    } 
  }
  
  public static String getString(String paramString) {
    if (paramString == null || paramString.length() > maxStringLength)
      return paramString; 
    synchronized (stringPool) {
      return stringPool.getOrAddString(paramString);
    } 
  }
  
  public static String getSubString(String paramString, int paramInt1, int paramInt2) {
    synchronized (stringPool) {
      return stringPool.getOrAddString(paramString.substring(paramInt1, paramInt2));
    } 
  }
  
  public static BigDecimal getBigDecimal(BigDecimal paramBigDecimal) {
    if (paramBigDecimal == null)
      return paramBigDecimal; 
    synchronized (bigdecimalPool) {
      return (BigDecimal)bigdecimalPool.getOrAddObject(paramBigDecimal);
    } 
  }
  
  public static Boolean getBoolean(boolean paramBoolean) {
    return paramBoolean ? Boolean.TRUE : Boolean.FALSE;
  }
  
  static {
    initPool();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\map\ValuePool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */