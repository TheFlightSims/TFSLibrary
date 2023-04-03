package org.openstreetmap.osmosis.core.domain.v0_6;

import java.util.Collection;
import java.util.Map;
import org.openstreetmap.osmosis.core.store.Storeable;

public interface TagCollection extends Collection<Tag>, Storeable {
  Map<String, String> buildMap();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\TagCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */