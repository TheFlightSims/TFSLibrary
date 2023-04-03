package org.geotools.filter.visitor;

import java.util.SortedMap;
import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;
import org.opengis.filter.expression.Literal;

@XmlElement("Categorize")
public interface Categorize extends Function {
  @XmlElement("LookupValue")
  Expression getLookupValue();
  
  SortedMap<Literal, Literal> getThresholds();
  
  @XmlElement("ThreshholdsBelongTo")
  ThreshholdsBelongTo getBelongTo();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\Categorize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */