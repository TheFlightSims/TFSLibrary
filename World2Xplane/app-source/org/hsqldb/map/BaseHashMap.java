package org.hsqldb.map;

import java.util.NoSuchElementException;
import org.hsqldb.lib.ArrayCounter;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.ObjectComparator;

public class BaseHashMap {
  boolean isIntKey;
  
  boolean isLongKey;
  
  boolean isObjectKey;
  
  boolean isNoValue;
  
  boolean isIntValue;
  
  boolean isLongValue;
  
  boolean isObjectValue;
  
  protected boolean isTwoObjectValue;
  
  protected boolean isList;
  
  private ValuesIterator valuesIterator;
  
  protected HashIndex hashIndex;
  
  protected int[] intKeyTable;
  
  protected Object[] objectKeyTable;
  
  protected long[] longKeyTable;
  
  protected int[] intValueTable;
  
  protected Object[] objectValueTable;
  
  protected long[] longValueTable;
  
  protected int accessMin;
  
  protected int accessCount;
  
  protected int[] accessTable;
  
  protected boolean[] multiValueTable;
  
  protected Object[] objectValueTable2;
  
  final float loadFactor;
  
  final int initialCapacity;
  
  int threshold;
  
  protected int maxCapacity;
  
  protected int purgePolicy = 0;
  
  protected boolean minimizeOnEmpty;
  
  protected ObjectComparator comparator;
  
  boolean hasZeroKey;
  
  int zeroKeyIndex = -1;
  
  protected static final int noKeyOrValue = 0;
  
  protected static final int intKeyOrValue = 1;
  
  protected static final int longKeyOrValue = 2;
  
  protected static final int objectKeyOrValue = 3;
  
  protected static final int NO_PURGE = 0;
  
  protected static final int PURGE_ALL = 1;
  
  protected static final int PURGE_HALF = 2;
  
  protected static final int PURGE_QUARTER = 3;
  
  public static final int ACCESS_MAX = 2146435071;
  
  public static final Object[] emptyObjectArray = new Object[0];
  
  protected BaseHashMap(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws IllegalArgumentException {
    if (paramInt1 <= 0)
      throw new IllegalArgumentException(); 
    if (paramInt1 < 3)
      paramInt1 = 3; 
    this.loadFactor = 1.0F;
    this.initialCapacity = paramInt1;
    this.threshold = paramInt1;
    int i = (int)(paramInt1 * this.loadFactor);
    if (i < 3)
      i = 3; 
    this.hashIndex = new HashIndex(i, paramInt1, true);
    int j = this.threshold;
    if (paramInt2 == 1) {
      this.isIntKey = true;
      this.intKeyTable = new int[j];
    } else if (paramInt2 == 3) {
      this.isObjectKey = true;
      this.objectKeyTable = new Object[j];
    } else {
      this.isLongKey = true;
      this.longKeyTable = new long[j];
    } 
    if (paramInt3 == 1) {
      this.isIntValue = true;
      this.intValueTable = new int[j];
    } else if (paramInt3 == 3) {
      this.isObjectValue = true;
      this.objectValueTable = new Object[j];
    } else if (paramInt3 == 2) {
      this.isLongValue = true;
      this.longValueTable = new long[j];
    } else {
      this.isNoValue = true;
    } 
    if (paramBoolean)
      this.accessTable = new int[j]; 
  }
  
  protected int getLookup(Object paramObject, int paramInt) {
    int i;
    for (i = this.hashIndex.getLookup(paramInt); i >= 0; i = this.hashIndex.getNextLookup(i)) {
      Object object = this.objectKeyTable[i];
      if (paramObject.equals(object))
        break; 
    } 
    return i;
  }
  
  protected int getLookup(int paramInt) {
    int i;
    for (i = this.hashIndex.getLookup(paramInt); i >= 0; i = this.hashIndex.linkTable[i]) {
      int j = this.intKeyTable[i];
      if (paramInt == j)
        break; 
    } 
    return i;
  }
  
  protected int getLookup(long paramLong) {
    int i;
    for (i = this.hashIndex.getLookup((int)paramLong); i >= 0; i = this.hashIndex.getNextLookup(i)) {
      long l = this.longKeyTable[i];
      if (paramLong == l)
        break; 
    } 
    return i;
  }
  
  protected int getObjectLookup(long paramLong) {
    int i;
    for (i = this.hashIndex.getLookup((int)paramLong); i >= 0; i = this.hashIndex.getNextLookup(i)) {
      long l = this.comparator.longKey(this.objectKeyTable[i]);
      if (l == paramLong)
        break; 
    } 
    return i;
  }
  
  protected Iterator getValuesIterator(Object paramObject, int paramInt) {
    int i = getLookup(paramObject, paramInt);
    if (this.valuesIterator == null)
      this.valuesIterator = new ValuesIterator(); 
    this.valuesIterator.reset(paramObject, i);
    return this.valuesIterator;
  }
  
  protected int valueCount(Object paramObject, int paramInt) {
    int i = getLookup(paramObject, paramInt);
    if (i == -1)
      return 0; 
    byte b = 1;
    while (true) {
      i = this.hashIndex.getNextLookup(i);
      if (i == -1)
        return b; 
      if (this.objectKeyTable[i].equals(paramObject))
        b++; 
    } 
  }
  
  protected Object addOrRemove(long paramLong1, long paramLong2, Object paramObject1, Object paramObject2, boolean paramBoolean) {
    int i = (int)paramLong1;
    if (this.isObjectKey) {
      if (paramObject1 == null)
        return null; 
      if (this.comparator == null) {
        i = paramObject1.hashCode();
      } else {
        i = this.comparator.hashCode(paramObject1);
      } 
    } 
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.hashTable[j];
    int m = -1;
    Object object = null;
    while (k >= 0 && (this.isObjectKey ? ((this.comparator == null) ? this.objectKeyTable[k].equals(paramObject1) : (this.comparator.compare(this.objectKeyTable[k], paramObject1) == 0)) : (this.isIntKey ? (paramLong1 == this.intKeyTable[k]) : (this.isLongKey && paramLong1 == this.longKeyTable[k])))) {
      m = k;
      k = this.hashIndex.getNextLookup(k);
    } 
    if (k >= 0) {
      if (paramBoolean) {
        if (this.isObjectKey) {
          this.objectKeyTable[k] = null;
        } else {
          if (paramLong1 == 0L) {
            this.hasZeroKey = false;
            this.zeroKeyIndex = -1;
          } 
          if (this.isIntKey) {
            this.intKeyTable[k] = 0;
          } else {
            this.longKeyTable[k] = 0L;
          } 
        } 
        if (this.isObjectValue) {
          object = this.objectValueTable[k];
          this.objectValueTable[k] = null;
        } else if (this.isIntValue) {
          this.intValueTable[k] = 0;
        } else if (this.isLongValue) {
          this.longValueTable[k] = 0L;
        } 
        this.hashIndex.unlinkNode(j, m, k);
        if (this.accessTable != null)
          this.accessTable[k] = 0; 
        if (this.minimizeOnEmpty && this.hashIndex.elementCount == 0)
          rehash(this.initialCapacity); 
        return object;
      } 
      if (this.isObjectValue) {
        object = this.objectValueTable[k];
        this.objectValueTable[k] = paramObject2;
      } else if (this.isIntValue) {
        this.intValueTable[k] = (int)paramLong2;
      } else if (this.isLongValue) {
        this.longValueTable[k] = paramLong2;
      } 
      if (this.accessTable != null)
        this.accessTable[k] = ++this.accessCount; 
      return object;
    } 
    if (paramBoolean)
      return null; 
    if (this.hashIndex.elementCount >= this.threshold) {
      if (reset())
        return addOrRemove(paramLong1, paramLong2, paramObject1, paramObject2, paramBoolean); 
      throw new NoSuchElementException("BaseHashMap");
    } 
    k = this.hashIndex.linkNode(j, m);
    if (this.isObjectKey) {
      this.objectKeyTable[k] = paramObject1;
    } else if (this.isIntKey) {
      this.intKeyTable[k] = (int)paramLong1;
      if (paramLong1 == 0L) {
        this.hasZeroKey = true;
        this.zeroKeyIndex = k;
      } 
    } else if (this.isLongKey) {
      this.longKeyTable[k] = paramLong1;
      if (paramLong1 == 0L) {
        this.hasZeroKey = true;
        this.zeroKeyIndex = k;
      } 
    } 
    if (this.isObjectValue) {
      this.objectValueTable[k] = paramObject2;
    } else if (this.isIntValue) {
      this.intValueTable[k] = (int)paramLong2;
    } else if (this.isLongValue) {
      this.longValueTable[k] = paramLong2;
    } 
    if (this.accessTable != null)
      this.accessTable[k] = ++this.accessCount; 
    return object;
  }
  
  protected Object addOrRemoveMultiVal(long paramLong1, long paramLong2, Object paramObject1, Object paramObject2, boolean paramBoolean1, boolean paramBoolean2) {
    int i = (int)paramLong1;
    if (this.isObjectKey) {
      if (paramObject1 == null)
        return null; 
      if (this.comparator == null) {
        i = paramObject1.hashCode();
      } else {
        i = this.comparator.hashCode(paramObject1);
      } 
    } 
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.hashTable[j];
    int m = -1;
    Object object = null;
    boolean bool = false;
    while (k >= 0) {
      if (this.isObjectKey) {
        if ((this.comparator == null) ? this.objectKeyTable[k].equals(paramObject1) : (this.comparator.compare(this.objectKeyTable[k], paramObject1) == 0)) {
          if (paramBoolean1) {
            do {
              this.objectKeyTable[k] = null;
              object = this.objectValueTable[k];
              this.objectValueTable[k] = null;
              this.hashIndex.unlinkNode(j, m, k);
              this.multiValueTable[k] = false;
              k = this.hashIndex.hashTable[j];
            } while (k >= 0 && this.objectKeyTable[k].equals(paramObject1));
            return object;
          } 
          if (this.objectValueTable[k].equals(paramObject2)) {
            if (paramBoolean2) {
              this.objectKeyTable[k] = null;
              object = this.objectValueTable[k];
              this.objectValueTable[k] = null;
              this.hashIndex.unlinkNode(j, m, k);
              this.multiValueTable[k] = false;
              k = m;
              return object;
            } 
            return this.objectValueTable[k];
          } 
          bool = true;
        } 
      } else if (this.isIntKey) {
        if (paramLong1 == this.intKeyTable[k]) {
          if (paramBoolean1) {
            do {
              if (paramLong1 == 0L) {
                this.hasZeroKey = false;
                this.zeroKeyIndex = -1;
              } 
              this.intKeyTable[k] = 0;
              this.intValueTable[k] = 0;
              this.hashIndex.unlinkNode(j, m, k);
              this.multiValueTable[k] = false;
              k = this.hashIndex.hashTable[j];
            } while (k >= 0 && paramLong1 == this.intKeyTable[k]);
            return null;
          } 
          if (this.intValueTable[k] == paramLong2)
            return null; 
          bool = true;
        } 
      } else if (this.isLongKey && paramLong1 == this.longKeyTable[k]) {
        if (paramBoolean1) {
          do {
            if (paramLong1 == 0L) {
              this.hasZeroKey = false;
              this.zeroKeyIndex = -1;
            } 
            this.longKeyTable[k] = 0L;
            this.longValueTable[k] = 0L;
            this.hashIndex.unlinkNode(j, m, k);
            this.multiValueTable[k] = false;
            k = this.hashIndex.hashTable[j];
          } while (k >= 0 && paramLong1 == this.longKeyTable[k]);
          return null;
        } 
        if (this.intValueTable[k] == paramLong2)
          return null; 
        bool = true;
      } 
      m = k;
      k = this.hashIndex.getNextLookup(k);
    } 
    if (paramBoolean1 || paramBoolean2)
      return object; 
    if (this.hashIndex.elementCount >= this.threshold) {
      if (reset())
        return addOrRemoveMultiVal(paramLong1, paramLong2, paramObject1, paramObject2, paramBoolean1, paramBoolean2); 
      throw new NoSuchElementException("BaseHashMap");
    } 
    k = this.hashIndex.linkNode(j, m);
    if (this.isObjectKey) {
      this.objectKeyTable[k] = paramObject1;
    } else if (this.isIntKey) {
      this.intKeyTable[k] = (int)paramLong1;
      if (paramLong1 == 0L) {
        this.hasZeroKey = true;
        this.zeroKeyIndex = k;
      } 
    } else if (this.isLongKey) {
      this.longKeyTable[k] = paramLong1;
      if (paramLong1 == 0L) {
        this.hasZeroKey = true;
        this.zeroKeyIndex = k;
      } 
    } 
    if (this.isObjectValue) {
      this.objectValueTable[k] = paramObject2;
    } else if (this.isIntValue) {
      this.intValueTable[k] = (int)paramLong2;
    } else if (this.isLongValue) {
      this.longValueTable[k] = paramLong2;
    } 
    if (bool)
      this.multiValueTable[k] = true; 
    if (this.accessTable != null)
      this.accessTable[k] = ++this.accessCount; 
    return object;
  }
  
  protected Object addOrRemove(long paramLong, Object paramObject1, Object paramObject2, boolean paramBoolean) {
    int i = (int)paramLong;
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.hashTable[j];
    int m = -1;
    Object object = null;
    while (k >= 0 && (this.isIntKey ? (paramLong == this.intKeyTable[k]) : (paramLong == this.longKeyTable[k]))) {
      m = k;
      k = this.hashIndex.getNextLookup(k);
    } 
    if (k >= 0) {
      if (paramBoolean) {
        if (paramLong == 0L) {
          this.hasZeroKey = false;
          this.zeroKeyIndex = -1;
        } 
        if (this.isIntKey) {
          this.intKeyTable[k] = 0;
        } else {
          this.longKeyTable[k] = 0L;
        } 
        object = this.objectValueTable[k];
        this.objectValueTable[k] = null;
        this.hashIndex.unlinkNode(j, m, k);
        if (this.isTwoObjectValue)
          this.objectKeyTable[k] = null; 
        if (this.accessTable != null)
          this.accessTable[k] = 0; 
        return object;
      } 
      if (this.isObjectValue) {
        object = this.objectValueTable[k];
        this.objectValueTable[k] = paramObject1;
      } 
      if (this.isTwoObjectValue)
        this.objectKeyTable[k] = paramObject2; 
      if (this.accessTable != null)
        this.accessTable[k] = ++this.accessCount; 
      return object;
    } 
    if (paramBoolean)
      return object; 
    if (this.hashIndex.elementCount >= this.threshold)
      return reset() ? addOrRemove(paramLong, paramObject1, paramObject2, paramBoolean) : null; 
    k = this.hashIndex.linkNode(j, m);
    if (this.isIntKey) {
      this.intKeyTable[k] = (int)paramLong;
    } else {
      this.longKeyTable[k] = paramLong;
    } 
    if (paramLong == 0L) {
      this.hasZeroKey = true;
      this.zeroKeyIndex = k;
    } 
    this.objectValueTable[k] = paramObject1;
    if (this.isTwoObjectValue)
      this.objectKeyTable[k] = paramObject2; 
    if (this.accessTable != null)
      this.accessTable[k] = ++this.accessCount; 
    return object;
  }
  
  protected Object removeObject(Object paramObject, boolean paramBoolean) {
    if (paramObject == null)
      return null; 
    int i = paramObject.hashCode();
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.hashTable[j];
    int m = -1;
    Object object = null;
    while (k >= 0) {
      if (this.objectKeyTable[k].equals(paramObject)) {
        object = this.objectKeyTable[k];
        this.objectKeyTable[k] = null;
        this.hashIndex.unlinkNode(j, m, k);
        if (this.isObjectValue) {
          object = this.objectValueTable[k];
          this.objectValueTable[k] = null;
        } 
        if (paramBoolean)
          removeRow(k); 
        return object;
      } 
      m = k;
      k = this.hashIndex.getNextLookup(k);
    } 
    return object;
  }
  
  protected Object addOrRemoveObject(Object paramObject, long paramLong, boolean paramBoolean) {
    int i = (int)paramLong;
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.getLookup(i);
    int m = -1;
    Object object = null;
    while (k >= 0) {
      object = this.objectKeyTable[k];
      if (this.comparator.longKey(object) == paramLong)
        break; 
      m = k;
      k = this.hashIndex.getNextLookup(k);
    } 
    if (k >= 0) {
      if (paramBoolean) {
        this.objectKeyTable[k] = null;
        this.hashIndex.unlinkNode(j, m, k);
        if (this.accessTable != null)
          this.accessTable[k] = 0; 
        if (this.minimizeOnEmpty && this.hashIndex.elementCount == 0)
          rehash(this.initialCapacity); 
      } else {
        this.objectKeyTable[k] = paramObject;
        if (this.accessTable != null)
          this.accessTable[k] = ++this.accessCount; 
      } 
      return object;
    } 
    if (paramBoolean)
      return null; 
    if (this.hashIndex.elementCount >= this.threshold) {
      if (reset())
        return addOrRemoveObject(paramObject, paramLong, paramBoolean); 
      throw new NoSuchElementException("BaseHashMap");
    } 
    k = this.hashIndex.linkNode(j, m);
    this.objectKeyTable[k] = paramObject;
    if (this.accessTable != null)
      this.accessTable[k] = ++this.accessCount; 
    return object;
  }
  
  protected boolean reset() {
    if (this.maxCapacity == 0 || this.maxCapacity > this.threshold) {
      rehash(this.hashIndex.linkTable.length * 2);
      return true;
    } 
    if (this.purgePolicy == 1) {
      clear();
      return true;
    } 
    if (this.purgePolicy == 3) {
      clear(this.threshold / 4, this.threshold >> 8);
      return true;
    } 
    if (this.purgePolicy == 2) {
      clear(this.threshold / 2, this.threshold >> 8);
      return true;
    } 
    return (this.purgePolicy == 0) ? false : false;
  }
  
  protected void rehash(int paramInt) {
    int i = this.hashIndex.newNodePointer;
    boolean bool = this.hasZeroKey;
    int j = this.zeroKeyIndex;
    if (paramInt < this.hashIndex.elementCount)
      return; 
    this.hashIndex.reset((int)(paramInt * this.loadFactor), paramInt);
    if (this.multiValueTable != null) {
      int m = this.multiValueTable.length;
      while (--m >= 0)
        this.multiValueTable[m] = false; 
    } 
    this.hasZeroKey = false;
    this.zeroKeyIndex = -1;
    this.threshold = paramInt;
    int k = -1;
    while ((k = nextLookup(k, i, bool, j)) < i) {
      long l1 = 0L;
      long l2 = 0L;
      Object object1 = null;
      Object object2 = null;
      if (this.isObjectKey) {
        object1 = this.objectKeyTable[k];
      } else if (this.isIntKey) {
        l1 = this.intKeyTable[k];
      } else {
        l1 = this.longKeyTable[k];
      } 
      if (this.isObjectValue) {
        object2 = this.objectValueTable[k];
      } else if (this.isIntValue) {
        l2 = this.intValueTable[k];
      } else if (this.isLongValue) {
        l2 = this.longValueTable[k];
      } 
      if (this.multiValueTable == null) {
        addOrRemove(l1, l2, object1, object2, false);
      } else {
        addOrRemoveMultiVal(l1, l2, object1, object2, false, false);
      } 
      if (this.accessTable != null)
        this.accessTable[this.hashIndex.elementCount - 1] = this.accessTable[k]; 
    } 
    resizeElementArrays(this.hashIndex.newNodePointer, paramInt);
  }
  
  private void resizeElementArrays(int paramInt1, int paramInt2) {
    int i = (paramInt2 > paramInt1) ? paramInt1 : paramInt2;
    if (this.isIntKey) {
      int[] arrayOfInt = this.intKeyTable;
      this.intKeyTable = new int[paramInt2];
      System.arraycopy(arrayOfInt, 0, this.intKeyTable, 0, i);
    } 
    if (this.isIntValue) {
      int[] arrayOfInt = this.intValueTable;
      this.intValueTable = new int[paramInt2];
      System.arraycopy(arrayOfInt, 0, this.intValueTable, 0, i);
    } 
    if (this.isLongKey) {
      long[] arrayOfLong = this.longKeyTable;
      this.longKeyTable = new long[paramInt2];
      System.arraycopy(arrayOfLong, 0, this.longKeyTable, 0, i);
    } 
    if (this.isLongValue) {
      long[] arrayOfLong = this.longValueTable;
      this.longValueTable = new long[paramInt2];
      System.arraycopy(arrayOfLong, 0, this.longValueTable, 0, i);
    } 
    if (this.objectKeyTable != null) {
      Object[] arrayOfObject = this.objectKeyTable;
      this.objectKeyTable = new Object[paramInt2];
      System.arraycopy(arrayOfObject, 0, this.objectKeyTable, 0, i);
    } 
    if (this.isObjectValue) {
      Object[] arrayOfObject = this.objectValueTable;
      this.objectValueTable = new Object[paramInt2];
      System.arraycopy(arrayOfObject, 0, this.objectValueTable, 0, i);
    } 
    if (this.objectValueTable2 != null) {
      Object[] arrayOfObject = this.objectValueTable2;
      this.objectValueTable2 = new Object[paramInt2];
      System.arraycopy(arrayOfObject, 0, this.objectValueTable2, 0, i);
    } 
    if (this.accessTable != null) {
      int[] arrayOfInt = this.accessTable;
      this.accessTable = new int[paramInt2];
      System.arraycopy(arrayOfInt, 0, this.accessTable, 0, i);
    } 
    if (this.multiValueTable != null) {
      boolean[] arrayOfBoolean = this.multiValueTable;
      this.multiValueTable = new boolean[paramInt2];
      System.arraycopy(arrayOfBoolean, 0, this.multiValueTable, 0, i);
    } 
  }
  
  private void clearElementArrays(int paramInt1, int paramInt2) {
    if (this.isIntKey) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.intKeyTable[i] = 0; 
    } else if (this.isLongKey) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.longKeyTable[i] = 0L; 
    } else if (this.isObjectKey || this.objectKeyTable != null) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.objectKeyTable[i] = null; 
    } 
    if (this.isIntValue) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.intValueTable[i] = 0; 
    } else if (this.isLongValue) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.longValueTable[i] = 0L; 
    } else if (this.isObjectValue) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.objectValueTable[i] = null; 
    } 
    if (this.accessTable != null) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.accessTable[i] = 0; 
    } 
    if (this.multiValueTable != null) {
      int i = paramInt2;
      while (--i >= paramInt1)
        this.multiValueTable[i] = false; 
    } 
  }
  
  void removeFromElementArrays(int paramInt) {
    int i = this.hashIndex.newNodePointer;
    if (this.isIntKey) {
      int[] arrayOfInt = this.intKeyTable;
      System.arraycopy(arrayOfInt, paramInt + 1, arrayOfInt, paramInt, i - paramInt);
      this.intKeyTable[i] = 0;
    } 
    if (this.isLongKey) {
      long[] arrayOfLong = this.longKeyTable;
      System.arraycopy(arrayOfLong, paramInt + 1, arrayOfLong, paramInt, i - paramInt);
      this.longKeyTable[i] = 0L;
    } 
    if (this.isObjectKey || this.objectKeyTable != null) {
      Object[] arrayOfObject = this.objectKeyTable;
      System.arraycopy(arrayOfObject, paramInt + 1, arrayOfObject, paramInt, i - paramInt);
      this.objectKeyTable[i] = null;
    } 
    if (this.isIntValue) {
      int[] arrayOfInt = this.intValueTable;
      System.arraycopy(arrayOfInt, paramInt + 1, arrayOfInt, paramInt, i - paramInt);
      this.intValueTable[i] = 0;
    } 
    if (this.isLongValue) {
      long[] arrayOfLong = this.longValueTable;
      System.arraycopy(arrayOfLong, paramInt + 1, arrayOfLong, paramInt, i - paramInt);
      this.longValueTable[i] = 0L;
    } 
    if (this.isObjectValue) {
      Object[] arrayOfObject = this.objectValueTable;
      System.arraycopy(arrayOfObject, paramInt + 1, arrayOfObject, paramInt, i - paramInt);
      this.objectValueTable[i] = null;
    } 
  }
  
  int nextLookup(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) {
    while (++paramInt1 < paramInt2) {
      if (this.isObjectKey) {
        if (this.objectKeyTable[paramInt1] != null)
          return paramInt1; 
      } else if (this.isIntKey) {
        if (this.intKeyTable[paramInt1] != 0)
          return paramInt1; 
        if (paramBoolean && paramInt1 == paramInt3)
          return paramInt1; 
      } else {
        if (this.longKeyTable[paramInt1] != 0L)
          return paramInt1; 
        if (paramBoolean && paramInt1 == paramInt3)
          return paramInt1; 
      } 
      paramInt1++;
    } 
    return paramInt1;
  }
  
  protected int nextLookup(int paramInt) {
    while (++paramInt < this.hashIndex.newNodePointer) {
      if (this.isObjectKey) {
        if (this.objectKeyTable[paramInt] != null)
          return paramInt; 
      } else if (this.isIntKey) {
        if (this.intKeyTable[paramInt] != 0)
          return paramInt; 
        if (this.hasZeroKey && paramInt == this.zeroKeyIndex)
          return paramInt; 
      } else {
        if (this.longKeyTable[paramInt] != 0L)
          return paramInt; 
        if (this.hasZeroKey && paramInt == this.zeroKeyIndex)
          return paramInt; 
      } 
      paramInt++;
    } 
    return -1;
  }
  
  protected void removeRow(int paramInt) {
    this.hashIndex.removeEmptyNode(paramInt);
    removeFromElementArrays(paramInt);
  }
  
  public void clear() {
    if (this.hashIndex.modified) {
      this.accessCount = 0;
      this.accessMin = this.accessCount;
      this.hasZeroKey = false;
      this.zeroKeyIndex = -1;
      clearElementArrays(0, this.hashIndex.linkTable.length);
      this.hashIndex.clear();
      if (this.minimizeOnEmpty)
        rehash(this.initialCapacity); 
    } 
  }
  
  public int getAccessCountCeiling(int paramInt1, int paramInt2) {
    return ArrayCounter.rank(this.accessTable, this.hashIndex.newNodePointer, paramInt1, this.accessMin + 1, this.accessCount, paramInt2);
  }
  
  public void setAccessCountFloor(int paramInt) {
    this.accessMin = paramInt;
  }
  
  public int incrementAccessCount() {
    return ++this.accessCount;
  }
  
  protected void clear(int paramInt1, int paramInt2) {
    if (paramInt2 < 64)
      paramInt2 = 64; 
    int i = this.hashIndex.newNodePointer;
    int j = getAccessCountCeiling(paramInt1, paramInt2);
    for (byte b = 0; b < i; b++) {
      Object object = this.objectKeyTable[b];
      if (object != null && this.accessTable[b] < j)
        removeObject(object, false); 
    } 
    this.accessMin = j;
  }
  
  protected void resetAccessCount() {
    if (this.accessCount < 2146435071)
      return; 
    if (this.accessMin < 2130706431)
      this.accessMin = 2130706431; 
    int i = this.accessTable.length;
    while (--i >= 0) {
      if (this.accessTable[i] <= this.accessMin) {
        this.accessTable[i] = 0;
        continue;
      } 
      this.accessTable[i] = this.accessTable[i] - this.accessMin;
    } 
    this.accessCount -= this.accessMin;
    this.accessMin = 0;
  }
  
  public int capacity() {
    return this.hashIndex.linkTable.length;
  }
  
  public int size() {
    return this.hashIndex.elementCount;
  }
  
  public boolean isEmpty() {
    return (this.hashIndex.elementCount == 0);
  }
  
  protected void setComparator(ObjectComparator paramObjectComparator) {
    this.comparator = paramObjectComparator;
  }
  
  protected boolean containsKey(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this.hashIndex.elementCount == 0)
      return false; 
    int i = getLookup(paramObject, paramObject.hashCode());
    return !(i == -1);
  }
  
  protected boolean containsKey(int paramInt) {
    if (this.hashIndex.elementCount == 0)
      return false; 
    int i = getLookup(paramInt);
    return !(i == -1);
  }
  
  protected boolean containsKey(long paramLong) {
    if (this.hashIndex.elementCount == 0)
      return false; 
    int i = getLookup(paramLong);
    return !(i == -1);
  }
  
  protected boolean containsValue(Object paramObject) {
    byte b = 0;
    if (this.hashIndex.elementCount == 0)
      return false; 
    if (paramObject == null) {
      while (b < this.hashIndex.newNodePointer) {
        if (this.objectValueTable[b] == null)
          if (this.isObjectKey) {
            if (this.objectKeyTable[b] != null)
              return true; 
          } else if (this.isIntKey) {
            if (this.intKeyTable[b] != 0)
              return true; 
            if (this.hasZeroKey && b == this.zeroKeyIndex)
              return true; 
          } else {
            if (this.longKeyTable[b] != 0L)
              return true; 
            if (this.hasZeroKey && b == this.zeroKeyIndex)
              return true; 
          }  
        b++;
      } 
    } else {
      while (b < this.hashIndex.newNodePointer) {
        if (paramObject.equals(this.objectValueTable[b]))
          return true; 
        b++;
      } 
    } 
    return false;
  }
  
  protected class BaseHashIterator implements Iterator {
    boolean keys;
    
    int lookup = -1;
    
    int counter;
    
    boolean removed;
    
    public BaseHashIterator() {}
    
    public BaseHashIterator(boolean param1Boolean) {
      this.keys = param1Boolean;
    }
    
    public void reset() {
      this.lookup = -1;
      this.counter = 0;
      this.removed = false;
    }
    
    public boolean hasNext() {
      return (this.counter < BaseHashMap.this.hashIndex.elementCount);
    }
    
    public Object next() throws NoSuchElementException {
      if ((this.keys && !BaseHashMap.this.isObjectKey) || (!this.keys && !BaseHashMap.this.isObjectValue))
        throw new NoSuchElementException("Hash Iterator"); 
      this.removed = false;
      if (hasNext()) {
        this.counter++;
        this.lookup = BaseHashMap.this.nextLookup(this.lookup);
        return this.keys ? BaseHashMap.this.objectKeyTable[this.lookup] : BaseHashMap.this.objectValueTable[this.lookup];
      } 
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public int nextInt() throws NoSuchElementException {
      if ((this.keys && !BaseHashMap.this.isIntKey) || (!this.keys && !BaseHashMap.this.isIntValue))
        throw new NoSuchElementException("Hash Iterator"); 
      this.removed = false;
      if (hasNext()) {
        this.counter++;
        this.lookup = BaseHashMap.this.nextLookup(this.lookup);
        return this.keys ? BaseHashMap.this.intKeyTable[this.lookup] : BaseHashMap.this.intValueTable[this.lookup];
      } 
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public long nextLong() throws NoSuchElementException {
      if (!BaseHashMap.this.isLongKey || !this.keys)
        throw new NoSuchElementException("Hash Iterator"); 
      this.removed = false;
      if (hasNext()) {
        this.counter++;
        this.lookup = BaseHashMap.this.nextLookup(this.lookup);
        return this.keys ? BaseHashMap.this.longKeyTable[this.lookup] : BaseHashMap.this.longValueTable[this.lookup];
      } 
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public void remove() throws NoSuchElementException {
      if (this.removed)
        throw new NoSuchElementException("Hash Iterator"); 
      this.counter--;
      this.removed = true;
      if (BaseHashMap.this.isObjectKey) {
        if (BaseHashMap.this.multiValueTable == null) {
          BaseHashMap.this.addOrRemove(0L, 0L, BaseHashMap.this.objectKeyTable[this.lookup], null, true);
        } else if (this.keys) {
          BaseHashMap.this.addOrRemoveMultiVal(0L, 0L, BaseHashMap.this.objectKeyTable[this.lookup], null, true, false);
        } else {
          BaseHashMap.this.addOrRemoveMultiVal(0L, 0L, BaseHashMap.this.objectKeyTable[this.lookup], BaseHashMap.this.objectValueTable[this.lookup], false, true);
        } 
      } else if (BaseHashMap.this.isIntKey) {
        BaseHashMap.this.addOrRemove(BaseHashMap.this.intKeyTable[this.lookup], 0L, null, null, true);
      } else {
        BaseHashMap.this.addOrRemove(BaseHashMap.this.longKeyTable[this.lookup], 0L, null, null, true);
      } 
      if (BaseHashMap.this.isList) {
        BaseHashMap.this.removeRow(this.lookup);
        this.lookup--;
      } 
    }
    
    public void setValue(Object param1Object) {
      if (this.keys)
        throw new NoSuchElementException(); 
      BaseHashMap.this.objectValueTable[this.lookup] = param1Object;
    }
    
    public int getAccessCount() {
      if (this.removed || BaseHashMap.this.accessTable == null)
        throw new NoSuchElementException(); 
      return BaseHashMap.this.accessTable[this.lookup];
    }
    
    public void setAccessCount(int param1Int) {
      if (this.removed || BaseHashMap.this.accessTable == null)
        throw new NoSuchElementException(); 
      BaseHashMap.this.accessTable[this.lookup] = param1Int;
    }
    
    public int getLookup() {
      return this.lookup;
    }
  }
  
  protected class MultiValueKeyIterator implements Iterator {
    boolean keys;
    
    int lookup = -1;
    
    int counter;
    
    boolean removed;
    
    public MultiValueKeyIterator() {
      toNextLookup();
    }
    
    private void toNextLookup() {
      do {
        this.lookup = BaseHashMap.this.nextLookup(this.lookup);
      } while (this.lookup != -1 && BaseHashMap.this.multiValueTable[this.lookup]);
    }
    
    public boolean hasNext() {
      return (this.lookup != -1);
    }
    
    public Object next() throws NoSuchElementException {
      Object object = BaseHashMap.this.objectKeyTable[this.lookup];
      toNextLookup();
      return object;
    }
    
    public int nextInt() throws NoSuchElementException {
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public long nextLong() throws NoSuchElementException {
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public void remove() throws NoSuchElementException {
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public void setValue(Object param1Object) {
      throw new NoSuchElementException("Hash Iterator");
    }
  }
  
  protected class ValuesIterator implements Iterator {
    int lookup = -1;
    
    Object key;
    
    private void reset(Object param1Object, int param1Int) {
      this.key = param1Object;
      this.lookup = param1Int;
    }
    
    public boolean hasNext() {
      return (this.lookup != -1);
    }
    
    public Object next() throws NoSuchElementException {
      if (this.lookup == -1)
        return null; 
      Object object = BaseHashMap.this.objectValueTable[this.lookup];
      do {
        this.lookup = BaseHashMap.this.hashIndex.getNextLookup(this.lookup);
      } while (this.lookup != -1 && !BaseHashMap.this.objectKeyTable[this.lookup].equals(this.key));
      return object;
    }
    
    public int nextInt() throws NoSuchElementException {
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public long nextLong() throws NoSuchElementException {
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public void remove() throws NoSuchElementException {
      throw new NoSuchElementException("Hash Iterator");
    }
    
    public void setValue(Object param1Object) {
      throw new NoSuchElementException("Hash Iterator");
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\map\BaseHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */