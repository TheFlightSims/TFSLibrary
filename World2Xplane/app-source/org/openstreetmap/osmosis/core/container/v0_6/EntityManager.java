package org.openstreetmap.osmosis.core.container.v0_6;

import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;

public interface EntityManager<T extends org.openstreetmap.osmosis.core.domain.v0_6.Entity> {
  T getEntity(long paramLong);
  
  ReleasableIterator<T> iterate();
  
  boolean exists(long paramLong);
  
  void addEntity(T paramT);
  
  void modifyEntity(T paramT);
  
  void removeEntity(long paramLong);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\EntityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */