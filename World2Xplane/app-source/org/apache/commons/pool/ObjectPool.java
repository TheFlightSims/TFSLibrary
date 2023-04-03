package org.apache.commons.pool;

import java.util.NoSuchElementException;

public interface ObjectPool {
  Object borrowObject() throws Exception, NoSuchElementException, IllegalStateException;
  
  void returnObject(Object paramObject) throws Exception;
  
  void invalidateObject(Object paramObject) throws Exception;
  
  void addObject() throws Exception, IllegalStateException, UnsupportedOperationException;
  
  int getNumIdle() throws UnsupportedOperationException;
  
  int getNumActive() throws UnsupportedOperationException;
  
  void clear() throws Exception, UnsupportedOperationException;
  
  void close() throws Exception;
  
  void setFactory(PoolableObjectFactory paramPoolableObjectFactory) throws IllegalStateException, UnsupportedOperationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\ObjectPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */