package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.OrientableCurve;

@UML(identifier = "GM_MultiCurve", specification = Specification.ISO_19107)
public interface MultiCurve extends MultiPrimitive {
  @UML(identifier = "element", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<OrientableCurve> getElements();
  
  @UML(identifier = "length", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double length();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\aggregate\MultiCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */