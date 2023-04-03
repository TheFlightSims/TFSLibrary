package org.openstreetmap.osmosis.core.task.v0_6;

import org.openstreetmap.osmosis.core.container.v0_6.Dataset;
import org.openstreetmap.osmosis.core.lifecycle.Releasable;
import org.openstreetmap.osmosis.core.task.common.Task;

public interface DatasetSink extends Task, Releasable {
  void process(Dataset paramDataset);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\task\v0_6\DatasetSink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */