package org.openstreetmap.osmosis.core.task.v0_6;

import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
import org.openstreetmap.osmosis.core.task.common.Task;

public interface ChangeSink extends Task, Initializable {
  void process(ChangeContainer paramChangeContainer);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\task\v0_6\ChangeSink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */