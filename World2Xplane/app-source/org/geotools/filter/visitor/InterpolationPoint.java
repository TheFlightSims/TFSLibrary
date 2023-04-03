package org.geotools.filter.visitor;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("InterpolationPoint")
public interface InterpolationPoint {
  @XmlElement("Value")
  Expression getValue();
  
  @XmlElement("Data")
  double getData();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\InterpolationPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */