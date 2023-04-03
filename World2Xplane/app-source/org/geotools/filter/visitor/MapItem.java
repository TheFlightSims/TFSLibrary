package org.geotools.filter.visitor;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("MapItem")
public interface MapItem extends Expression {
  @XmlElement("Value")
  Expression getValue();
  
  @XmlElement("Data")
  double getData();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\MapItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */