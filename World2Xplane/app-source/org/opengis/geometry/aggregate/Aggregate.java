package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.Geometry;

@UML(identifier = "GM_Aggregate", specification = Specification.ISO_19107)
public interface Aggregate extends Geometry {
  @UML(identifier = "element", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<? extends Geometry> getElements();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\aggregate\Aggregate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */