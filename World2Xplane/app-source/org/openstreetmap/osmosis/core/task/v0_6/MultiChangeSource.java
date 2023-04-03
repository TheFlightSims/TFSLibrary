package org.openstreetmap.osmosis.core.task.v0_6;

import org.openstreetmap.osmosis.core.task.common.Task;

public interface MultiChangeSource extends Task {
  ChangeSource getChangeSource(int paramInt);
  
  int getChangeSourceCount();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\task\v0_6\MultiChangeSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */