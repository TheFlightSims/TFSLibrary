package org.hsqldb.persist;

import java.util.Comparator;
import org.hsqldb.lib.ArraySort;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.ObjectComparator;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.map.BaseHashMap;

public class Cache extends BaseHashMap {
  final DataFileCache dataFileCache;
  
  private int capacity;
  
  private long bytesCapacity;
  
  private final CachedObjectComparator rowComparator;
  
  private final BaseHashMap.BaseHashIterator objectIterator;
  
  private boolean updateAccess;
  
  private long maxPositionOnCleanup;
  
  private CachedObject[] rowTable;
  
  long cacheBytesLength;
  
  StopWatch saveAllTimer = new StopWatch(false);
  
  StopWatch sortTimer = new StopWatch(false);
  
  int saveRowCount = 0;
  
  Cache(DataFileCache paramDataFileCache) {
    super(paramDataFileCache.capacity(), 3, 0, true);
    this.maxCapacity = paramDataFileCache.capacity();
    this.dataFileCache = paramDataFileCache;
    this.capacity = paramDataFileCache.capacity();
    this.bytesCapacity = paramDataFileCache.bytesCapacity();
    this.rowComparator = new CachedObjectComparator();
    this.rowTable = new CachedObject[this.capacity];
    this.cacheBytesLength = 0L;
    this.objectIterator = new BaseHashMap.BaseHashIterator(this, true);
    this.updateAccess = true;
    this.comparator = this.rowComparator;
  }
  
  void resize(int paramInt, long paramLong) {}
  
  long getTotalCachedBlockSize() {
    return this.cacheBytesLength;
  }
  
  public CachedObject get(long paramLong) {
    if (this.accessCount > 2146435071 && this.updateAccess) {
      updateAccessCounts();
      resetAccessCount();
      updateObjectAccessCounts();
    } 
    int i = getObjectLookup(paramLong);
    if (i == -1)
      return null; 
    this.accessTable[i] = ++this.accessCount;
    return (CachedObject)this.objectKeyTable[i];
  }
  
  void put(CachedObject paramCachedObject) {
    int i = paramCachedObject.getStorageSize();
    if (size() >= this.capacity || i + this.cacheBytesLength > this.bytesCapacity) {
      cleanUp(false);
      if (size() >= this.capacity)
        clearUnchanged(); 
      if (size() >= this.capacity)
        cleanUp(true); 
    } 
    if (this.accessCount > 2146435071 && this.updateAccess) {
      updateAccessCounts();
      resetAccessCount();
      updateObjectAccessCounts();
    } 
    addOrRemoveObject(paramCachedObject, paramCachedObject.getPos(), false);
    paramCachedObject.setInMemory(true);
    this.cacheBytesLength += i;
  }
  
  CachedObject release(long paramLong) {
    CachedObject cachedObject = (CachedObject)addOrRemoveObject(null, paramLong, true);
    if (cachedObject == null)
      return null; 
    this.cacheBytesLength -= cachedObject.getStorageSize();
    cachedObject.setInMemory(false);
    return cachedObject;
  }
  
  void replace(long paramLong, CachedObject paramCachedObject) {
    int i = getLookup(paramLong);
    this.objectKeyTable[i] = paramCachedObject;
  }
  
  private void updateAccessCounts() {
    for (byte b = 0; b < this.objectKeyTable.length; b++) {
      CachedObject cachedObject = (CachedObject)this.objectKeyTable[b];
      if (cachedObject != null) {
        int i = cachedObject.getAccessCount();
        if (i > this.accessTable[b])
          this.accessTable[b] = i; 
      } 
    } 
  }
  
  private void updateObjectAccessCounts() {
    for (byte b = 0; b < this.objectKeyTable.length; b++) {
      CachedObject cachedObject = (CachedObject)this.objectKeyTable[b];
      if (cachedObject != null) {
        int i = this.accessTable[b];
        cachedObject.updateAccessCount(i);
      } 
    } 
  }
  
  private void cleanUp(boolean paramBoolean) {
    if (this.updateAccess)
      updateAccessCounts(); 
    int i = size() / 2;
    int j = getAccessCountCeiling(i, i / 8);
    byte b = 0;
    if (paramBoolean) {
      i = size();
      j = this.accessCount + 1;
    } 
    this.objectIterator.reset();
    while (this.objectIterator.hasNext()) {
      CachedObject cachedObject = (CachedObject)this.objectIterator.next();
      int k = this.objectIterator.getAccessCount();
      boolean bool1 = (k < j) ? true : false;
      boolean bool2 = (cachedObject.isNew() && cachedObject.getStorageSize() >= 4096) ? true : false;
      if (!bool1 && !bool2)
        continue; 
      this.objectIterator.setAccessCount(j);
      synchronized (cachedObject) {
        if (cachedObject.isKeepInMemory())
          continue; 
        if (cachedObject.hasChanged())
          this.rowTable[b++] = cachedObject; 
        if (bool1) {
          cachedObject.setInMemory(false);
          this.objectIterator.remove();
          this.cacheBytesLength -= cachedObject.getStorageSize();
          i--;
        } 
      } 
      if (b == this.rowTable.length) {
        saveRows(b);
        b = 0;
      } 
    } 
    setAccessCountFloor(j);
    saveRows(b);
    this.maxPositionOnCleanup = this.dataFileCache.fileFreePosition / this.dataFileCache.dataFileScale;
  }
  
  void clearUnchanged() {
    this.objectIterator.reset();
    while (this.objectIterator.hasNext()) {
      CachedObject cachedObject = (CachedObject)this.objectIterator.next();
      synchronized (cachedObject) {
        if (!cachedObject.isKeepInMemory() && !cachedObject.hasChanged()) {
          cachedObject.setInMemory(false);
          this.objectIterator.remove();
          this.cacheBytesLength -= cachedObject.getStorageSize();
        } 
      } 
    } 
  }
  
  private synchronized void saveRows(int paramInt) {
    if (paramInt == 0)
      return; 
    long l = this.saveAllTimer.elapsedTime();
    this.rowComparator.setType(1);
    this.sortTimer.zero();
    this.sortTimer.start();
    ArraySort.sort((Object[])this.rowTable, 0, paramInt, (Comparator)this.rowComparator);
    this.sortTimer.stop();
    this.saveAllTimer.start();
    this.dataFileCache.saveRows(this.rowTable, 0, paramInt);
    this.saveRowCount += paramInt;
    this.saveAllTimer.stop();
    logSaveRowsEvent(paramInt, l);
  }
  
  void saveAll() {
    byte b = 0;
    this.objectIterator.reset();
    while (this.objectIterator.hasNext()) {
      if (b == this.rowTable.length) {
        saveRows(b);
        b = 0;
      } 
      CachedObject cachedObject = (CachedObject)this.objectIterator.next();
      if (cachedObject.hasChanged()) {
        this.rowTable[b] = cachedObject;
        b++;
      } 
    } 
    saveRows(b);
  }
  
  void logSaveRowsEvent(int paramInt, long paramLong) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("cache save rows [count,time] totals ");
    stringBuffer.append(this.saveRowCount);
    stringBuffer.append(',').append(this.saveAllTimer.elapsedTime()).append(' ');
    stringBuffer.append("operation ").append(paramInt).append(',');
    stringBuffer.append(this.saveAllTimer.elapsedTime() - paramLong).append(' ');
    stringBuffer.append("txts ");
    stringBuffer.append(this.dataFileCache.database.txManager.getGlobalChangeTimestamp());
    this.dataFileCache.logDetailEvent(stringBuffer.toString());
  }
  
  public void clear() {
    super.clear();
    this.cacheBytesLength = 0L;
  }
  
  public Iterator getIterator() {
    this.objectIterator.reset();
    return (Iterator)this.objectIterator;
  }
  
  static final class CachedObjectComparator implements ObjectComparator {
    static final int COMPARE_LAST_ACCESS = 0;
    
    static final int COMPARE_POSITION = 1;
    
    static final int COMPARE_SIZE = 2;
    
    private int compareType = 1;
    
    void setType(int param1Int) {
      this.compareType = param1Int;
    }
    
    public int compare(Object param1Object1, Object param1Object2) {
      long l;
      switch (this.compareType) {
        case 1:
          l = ((CachedObject)param1Object1).getPos() - ((CachedObject)param1Object2).getPos();
          break;
        case 2:
          l = (((CachedObject)param1Object1).getStorageSize() - ((CachedObject)param1Object2).getStorageSize());
          break;
        default:
          return 0;
      } 
      return (l == 0L) ? 0 : ((l > 0L) ? 1 : -1);
    }
    
    public int hashCode(Object param1Object) {
      return param1Object.hashCode();
    }
    
    public long longKey(Object param1Object) {
      return ((CachedObject)param1Object).getPos();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\Cache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */