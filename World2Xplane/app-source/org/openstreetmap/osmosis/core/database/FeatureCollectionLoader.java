package org.openstreetmap.osmosis.core.database;

import java.util.Collection;

public interface FeatureCollectionLoader<Te, Tf> {
  Collection<Tf> getFeatureCollection(Te paramTe);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\FeatureCollectionLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */