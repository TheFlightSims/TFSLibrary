package org.openstreetmap.osmosis.core.misc.v0_6;

import java.util.Map;
import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;

public class NullWriter implements Sink {
  public void initialize(Map<String, Object> metaTags) {}
  
  public void process(EntityContainer entityContainer) {}
  
  public void complete() {}
  
  public void release() {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\misc\v0_6\NullWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */