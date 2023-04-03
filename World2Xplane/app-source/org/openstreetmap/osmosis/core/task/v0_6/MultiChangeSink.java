package org.openstreetmap.osmosis.core.task.v0_6;

public interface MultiChangeSink {
  ChangeSink getChangeSink(int paramInt);
  
  int getChangeSinkCount();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\task\v0_6\MultiChangeSink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */