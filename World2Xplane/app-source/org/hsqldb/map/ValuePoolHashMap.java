package org.hsqldb.map;

import org.hsqldb.types.TimestampData;

public class ValuePoolHashMap extends BaseHashMap {
  public ValuePoolHashMap(int paramInt1, int paramInt2, int paramInt3) throws IllegalArgumentException {
    super(paramInt1, 3, 0, true);
    this.maxCapacity = paramInt2;
    this.purgePolicy = paramInt3;
  }
  
  public void resetCapacity(int paramInt1, int paramInt2) throws IllegalArgumentException {
    if (paramInt1 != 0 && this.hashIndex.elementCount > paramInt1) {
      int i = this.hashIndex.elementCount - paramInt1;
      i += i >> 5;
      if (i > this.hashIndex.elementCount)
        i = this.hashIndex.elementCount; 
      clear(i, i >> 6);
    } 
    if (paramInt1 != 0 && paramInt1 < this.threshold) {
      rehash(paramInt1);
      if (paramInt1 < this.hashIndex.elementCount)
        paramInt1 = this.maxCapacity; 
    } 
    this.maxCapacity = paramInt1;
    this.purgePolicy = paramInt2;
  }
  
  protected Integer getOrAddInteger(int paramInt) {
    int i = this.hashIndex.getHashIndex(paramInt);
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0) {
      Integer integer1 = (Integer)this.objectKeyTable[j];
      if (integer1.intValue() == paramInt) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[j] = this.accessCount++;
        return integer1;
      } 
      k = j;
      j = this.hashIndex.getNextLookup(j);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddInteger(paramInt);
    } 
    j = this.hashIndex.linkNode(i, k);
    Integer integer = Integer.valueOf(paramInt);
    this.objectKeyTable[j] = integer;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[j] = this.accessCount++;
    return integer;
  }
  
  protected Long getOrAddLong(long paramLong) {
    int i = this.hashIndex.getHashIndex((int)(paramLong ^ paramLong >>> 32L));
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0) {
      Long long_1 = (Long)this.objectKeyTable[j];
      if (long_1.longValue() == paramLong) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[j] = this.accessCount++;
        return long_1;
      } 
      k = j;
      j = this.hashIndex.getNextLookup(j);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddLong(paramLong);
    } 
    j = this.hashIndex.linkNode(i, k);
    Long long_ = Long.valueOf(paramLong);
    this.objectKeyTable[j] = long_;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[j] = this.accessCount++;
    return long_;
  }
  
  protected String getOrAddString(Object paramObject) {
    int i = this.hashIndex.getHashIndex(paramObject.hashCode());
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0) {
      String str1 = (String)this.objectKeyTable[j];
      if (paramObject.equals(str1)) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[j] = this.accessCount++;
        return str1;
      } 
      k = j;
      j = this.hashIndex.getNextLookup(j);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddString(paramObject);
    } 
    String str = paramObject.toString();
    j = this.hashIndex.linkNode(i, k);
    this.objectKeyTable[j] = str;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[j] = this.accessCount++;
    return str;
  }
  
  protected String getOrAddSubString(String paramString, int paramInt1, int paramInt2) {
    paramString = paramString.substring(paramInt1, paramInt2);
    int i = this.hashIndex.getHashIndex(paramString.hashCode());
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0) {
      String str1 = (String)this.objectKeyTable[j];
      if (paramString.equals(str1)) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[j] = this.accessCount++;
        return str1;
      } 
      k = j;
      j = this.hashIndex.getNextLookup(j);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddString(paramString);
    } 
    String str = new String(paramString.toCharArray());
    j = this.hashIndex.linkNode(i, k);
    this.objectKeyTable[j] = str;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[j] = this.accessCount++;
    return str;
  }
  
  protected TimestampData getOrAddDate(long paramLong) {
    int i = (int)paramLong ^ (int)(paramLong >>> 32L);
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.hashTable[j];
    int m = -1;
    while (k >= 0) {
      TimestampData timestampData1 = (TimestampData)this.objectKeyTable[k];
      if (timestampData1.getSeconds() == paramLong) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[k] = this.accessCount++;
        return timestampData1;
      } 
      m = k;
      k = this.hashIndex.getNextLookup(k);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddDate(paramLong);
    } 
    k = this.hashIndex.linkNode(j, m);
    TimestampData timestampData = new TimestampData(paramLong);
    this.objectKeyTable[k] = timestampData;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[k] = this.accessCount++;
    return timestampData;
  }
  
  protected Double getOrAddDouble(long paramLong) {
    int i = this.hashIndex.getHashIndex((int)(paramLong ^ paramLong >>> 32L));
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0) {
      Double double_1 = (Double)this.objectKeyTable[j];
      if (Double.doubleToLongBits(double_1.doubleValue()) == paramLong) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[j] = this.accessCount++;
        return double_1;
      } 
      k = j;
      j = this.hashIndex.getNextLookup(j);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddDouble(paramLong);
    } 
    j = this.hashIndex.linkNode(i, k);
    Double double_ = new Double(Double.longBitsToDouble(paramLong));
    this.objectKeyTable[j] = double_;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[j] = this.accessCount++;
    return double_;
  }
  
  protected Object getOrAddObject(Object paramObject) {
    int i = this.hashIndex.getHashIndex(paramObject.hashCode());
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0) {
      Object object = this.objectKeyTable[j];
      if (object.equals(paramObject)) {
        if (this.accessCount > 2146435071)
          resetAccessCount(); 
        this.accessTable[j] = this.accessCount++;
        return object;
      } 
      k = j;
      j = this.hashIndex.getNextLookup(j);
    } 
    if (this.hashIndex.elementCount >= this.threshold) {
      reset();
      return getOrAddObject(paramObject);
    } 
    j = this.hashIndex.linkNode(i, k);
    this.objectKeyTable[j] = paramObject;
    if (this.accessCount > 2146435071)
      resetAccessCount(); 
    this.accessTable[j] = this.accessCount++;
    return paramObject;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\map\ValuePoolHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */