package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;

@XmlElement("DistanceBufferType")
public interface DistanceBufferOperator extends BinarySpatialOperator {
  @XmlElement("Distance")
  double getDistance();
  
  String getDistanceUnits();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\DistanceBufferOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */