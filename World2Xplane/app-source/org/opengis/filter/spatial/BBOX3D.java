package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;
import org.opengis.geometry.BoundingBox3D;

@XmlElement("BBOX3D")
public interface BBOX3D extends BBOX {
  public static final String NAME = "BBOX3D";
  
  BoundingBox3D getBounds();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\BBOX3D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */