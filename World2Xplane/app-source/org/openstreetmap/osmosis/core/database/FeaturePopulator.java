package org.openstreetmap.osmosis.core.database;

import org.openstreetmap.osmosis.core.lifecycle.Releasable;

public interface FeaturePopulator<T> extends Releasable {
  void populateFeatures(T paramT);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\FeaturePopulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */