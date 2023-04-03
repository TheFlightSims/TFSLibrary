package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;
import org.opengis.geometry.BoundingBox;

@XmlElement("BBOX")
public interface BBOX extends BinarySpatialOperator {
  public static final String NAME = "BBOX";
  
  @XmlElement("PropertyName")
  String getPropertyName();
  
  String getSRS();
  
  double getMinX();
  
  double getMinY();
  
  double getMaxX();
  
  double getMaxY();
  
  BoundingBox getBounds();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\BBOX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */