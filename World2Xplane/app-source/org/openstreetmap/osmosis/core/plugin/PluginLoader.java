package org.openstreetmap.osmosis.core.plugin;

import java.util.Map;
import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;

public interface PluginLoader {
  Map<String, TaskManagerFactory> loadTaskFactories();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\plugin\PluginLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */