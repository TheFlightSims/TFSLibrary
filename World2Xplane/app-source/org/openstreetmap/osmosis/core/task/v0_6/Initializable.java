package org.openstreetmap.osmosis.core.task.v0_6;

import java.util.Map;
import org.openstreetmap.osmosis.core.lifecycle.Completable;

public interface Initializable extends Completable {
  void initialize(Map<String, Object> paramMap);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\task\v0_6\Initializable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */