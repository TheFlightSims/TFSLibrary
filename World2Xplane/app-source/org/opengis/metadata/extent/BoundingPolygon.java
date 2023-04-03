package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.Geometry;

@UML(identifier = "EX_BoundingPolygon", specification = Specification.ISO_19115)
public interface BoundingPolygon extends GeographicExtent {
  @UML(identifier = "polygon", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends Geometry> getPolygons();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\BoundingPolygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */