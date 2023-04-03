package org.geotools.data;

import java.io.IOException;
import org.geotools.factory.Hints;
import org.opengis.filter.identity.GmlObjectId;

public interface GmlObjectStore {
  Object getGmlObject(GmlObjectId paramGmlObjectId, Hints paramHints) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\GmlObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */