package org.opengis.metadata.extent;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "EX_SpatialTemporalExtent", specification = Specification.ISO_19115)
public interface SpatialTemporalExtent extends TemporalExtent {
  @UML(identifier = "spatialExtent", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends GeographicExtent> getSpatialExtent();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\extent\SpatialTemporalExtent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */