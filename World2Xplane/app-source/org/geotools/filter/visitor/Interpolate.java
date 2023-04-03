package org.geotools.filter.visitor;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;

@XmlElement("Interpolate")
public interface Interpolate extends Function {
  @XmlElement("LookupValue")
  Expression getLookupValue();
  
  List<InterpolationPoint> getInterpolationPoints();
  
  @XmlElement("Mode")
  Mode getMode();
  
  @XmlElement("Method")
  Method getMethod();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\Interpolate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */