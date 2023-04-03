package org.opengis.metadata.spatial;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_VectorSpatialRepresentation", specification = Specification.ISO_19115)
public interface VectorSpatialRepresentation extends SpatialRepresentation {
  @UML(identifier = "topologyLevel", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  TopologyLevel getTopologyLevel();
  
  @UML(identifier = "geometricObjects", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends GeometricObjects> getGeometricObjects();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\VectorSpatialRepresentation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */