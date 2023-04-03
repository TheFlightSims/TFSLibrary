package org.geotools.filter.visitor;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;

@XmlElement("Recode")
public interface Recode extends Function {
  @XmlElement("LookupValue")
  Expression getLookupValue();
  
  List<MapItem> getMapItems();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\Recode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */