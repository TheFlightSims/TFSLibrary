package org.openstreetmap.osmosis.core.task.v0_6;

import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.task.common.Task;

public interface Sink extends Task, Initializable {
  void process(EntityContainer paramEntityContainer);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\task\v0_6\Sink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */