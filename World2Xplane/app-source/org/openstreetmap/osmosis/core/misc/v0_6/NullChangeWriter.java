package org.openstreetmap.osmosis.core.misc.v0_6;

import java.util.Map;
import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;

public class NullChangeWriter implements ChangeSink {
  public void initialize(Map<String, Object> metaTags) {}
  
  public void process(ChangeContainer change) {}
  
  public void complete() {}
  
  public void release() {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\misc\v0_6\NullChangeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */