package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;

@XmlElement("Contains")
public interface Contains extends BinarySpatialOperator, BoundedSpatialOperator {
  public static final String NAME = "Contains";
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\Contains.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */