package org.openstreetmap.osmosis.core.container.v0_6;

import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.store.Storeable;

public abstract class EntityContainer implements Storeable {
  public abstract void process(EntityProcessor paramEntityProcessor);
  
  public abstract Entity getEntity();
  
  public abstract EntityContainer getWriteableInstance();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\EntityContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */