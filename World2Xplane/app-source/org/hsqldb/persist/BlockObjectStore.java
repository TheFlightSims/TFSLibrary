package org.hsqldb.persist;

import java.lang.reflect.Constructor;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.rowio.RowInputInterface;

public class BlockObjectStore extends SimpleStore {
  Class objectClass;
  
  Constructor constructor;
  
  int storageSize;
  
  int blockSize;
  
  public BlockObjectStore(DataFileCache paramDataFileCache, TableSpaceManager paramTableSpaceManager, Class paramClass, int paramInt1, int paramInt2) {
    this.cache = paramDataFileCache;
    this.spaceManager = paramTableSpaceManager;
    this.objectClass = paramClass;
    this.blockSize = paramInt2;
    this.storageSize = paramInt1;
    try {
      this.constructor = paramClass.getConstructor(new Class[] { int.class });
    } catch (Exception exception) {
      throw Error.runtimeError(201, "BlockObjectStore");
    } 
  }
  
  public CachedObject get(long paramLong) {
    try {
      return this.cache.get(paramLong, this.storageSize, this, false);
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public CachedObject get(CachedObject paramCachedObject, boolean paramBoolean) {
    try {
      return this.cache.get(paramCachedObject, this, paramBoolean);
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public CachedObject get(long paramLong, boolean paramBoolean) {
    try {
      return this.cache.get(paramLong, this.storageSize, this, paramBoolean);
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public void add(Session paramSession, CachedObject paramCachedObject, boolean paramBoolean) {
    int i = paramCachedObject.getRealSize(this.cache.rowOut);
    i = this.cache.rowOut.getStorageSize(i);
    paramCachedObject.setStorageSize(i);
    long l = this.spaceManager.getFilePosition(i, true);
    paramCachedObject.setPos(l);
    this.cache.add(paramCachedObject);
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface) {
    CachedObject cachedObject = getNewInstance(this.blockSize);
    cachedObject.read(paramRowInputInterface);
    int i = cachedObject.getRealSize(this.cache.rowOut);
    i = this.cache.rowOut.getStorageSize(i);
    cachedObject.setStorageSize(i);
    return cachedObject;
  }
  
  public CachedObject getNewInstance(int paramInt) {
    try {
      return this.constructor.newInstance(new Object[] { Integer.valueOf(paramInt) });
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\BlockObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */