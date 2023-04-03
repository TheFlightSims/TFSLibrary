package org.apache.commons.pool;

public interface PoolableObjectFactory {
  Object makeObject() throws Exception;
  
  void destroyObject(Object paramObject) throws Exception;
  
  boolean validateObject(Object paramObject);
  
  void activateObject(Object paramObject) throws Exception;
  
  void passivateObject(Object paramObject) throws Exception;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\PoolableObjectFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */