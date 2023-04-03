package org.hsqldb.persist;

public interface PersistentStoreCollection {
  PersistentStore getStore(Object paramObject);
  
  void setStore(Object paramObject, PersistentStore paramPersistentStore);
  
  void release();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\PersistentStoreCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */