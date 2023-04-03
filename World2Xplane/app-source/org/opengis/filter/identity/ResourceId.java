package org.opengis.filter.identity;

import java.util.Date;
import org.opengis.annotation.XmlElement;

@XmlElement("ResourceId")
public interface ResourceId extends FeatureId {
  @XmlElement("version")
  Version getVersion();
  
  @XmlElement("startTime")
  Date getStartTime();
  
  @XmlElement("endTime")
  Date getEndTime();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\identity\ResourceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */