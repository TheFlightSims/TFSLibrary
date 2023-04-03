package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;

@XmlElement("Overlaps")
public interface Overlaps extends BinarySpatialOperator, BoundedSpatialOperator {
  public static final String NAME = "Overlaps";
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\Overlaps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */