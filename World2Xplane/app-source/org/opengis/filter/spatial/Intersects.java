package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;

@XmlElement("Intersects")
public interface Intersects extends BinarySpatialOperator, BoundedSpatialOperator {
  public static final String NAME = "Intersects";
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\Intersects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */