package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.OrientableSurface;

@UML(identifier = "GM_MultiSurface", specification = Specification.ISO_19107)
public interface MultiSurface extends MultiPrimitive {
  @UML(identifier = "element", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<OrientableSurface> getElements();
  
  @UML(identifier = "area", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getArea();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\aggregate\MultiSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */