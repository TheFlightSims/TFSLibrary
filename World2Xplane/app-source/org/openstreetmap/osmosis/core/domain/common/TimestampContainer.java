package org.openstreetmap.osmosis.core.domain.common;

import java.util.Date;

public interface TimestampContainer {
  Date getTimestamp();
  
  String getFormattedTimestamp(TimestampFormat paramTimestampFormat);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\common\TimestampContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */