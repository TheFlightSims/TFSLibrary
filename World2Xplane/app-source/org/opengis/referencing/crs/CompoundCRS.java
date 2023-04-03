package org.opengis.referencing.crs;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "SC_CompoundCRS", specification = Specification.ISO_19111)
public interface CompoundCRS extends CoordinateReferenceSystem {
  @UML(identifier = "includesCRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19111)
  List<CoordinateReferenceSystem> getCoordinateReferenceSystems();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\crs\CompoundCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */