package org.opengis.style;

import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;

@UML(identifier = "PF_PortrayalSpecification", specification = Specification.ISO_19117)
public interface Symbolizer {
  @XmlElement("uom")
  Unit<Length> getUnitOfMeasure();
  
  @XmlElement("Geometry")
  String getGeometryPropertyName();
  
  @XmlElement("Name")
  String getName();
  
  @XmlElement("Description")
  Description getDescription();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Symbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */