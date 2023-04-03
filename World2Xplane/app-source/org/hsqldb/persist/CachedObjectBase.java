package org.hsqldb.persist;

import org.hsqldb.error.Error;

public abstract class CachedObjectBase implements CachedObject {
  boolean isMemory;
  
  long position;
  
  int storageSize;
  
  boolean isInMemory;
  
  boolean hasChanged;
  
  int keepCount;
  
  int accessCount;
  
  public boolean isMemory() {
    return this.isMemory;
  }
  
  public void updateAccessCount(int paramInt) {
    this.accessCount = paramInt;
  }
  
  public int getAccessCount() {
    return this.accessCount;
  }
  
  public void setStorageSize(int paramInt) {
    this.storageSize = paramInt;
  }
  
  public int getStorageSize() {
    return this.storageSize;
  }
  
  public final boolean isBlock() {
    return true;
  }
  
  public long getPos() {
    return this.position;
  }
  
  public void setPos(long paramLong) {
    this.position = paramLong;
  }
  
  public boolean isNew() {
    return false;
  }
  
  public boolean hasChanged() {
    return this.hasChanged;
  }
  
  public boolean isKeepInMemory() {
    return (this.keepCount > 0);
  }
  
  public boolean keepInMemory(boolean paramBoolean) {
    if (!this.isInMemory)
      return false; 
    if (paramBoolean) {
      this.keepCount++;
    } else {
      this.keepCount--;
      if (this.keepCount < 0)
        throw Error.runtimeError(201, "CachedObjectBase - keep count"); 
    } 
    return true;
  }
  
  public boolean isInMemory() {
    return this.isInMemory;
  }
  
  public void setInMemory(boolean paramBoolean) {
    this.isInMemory = paramBoolean;
  }
  
  public int hashCode() {
    return (int)this.position;
  }
  
  public void restore() {}
  
  public void destroy() {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\CachedObjectBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */