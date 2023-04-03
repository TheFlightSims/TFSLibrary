package org.opengis.metadata.spatial;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_GridSpatialRepresentation", specification = Specification.ISO_19115)
public interface GridSpatialRepresentation extends SpatialRepresentation {
  @UML(identifier = "numberOfDimensions", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Integer getNumberOfDimensions();
  
  @UML(identifier = "axisDimensionsProperties", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  List<? extends Dimension> getAxisDimensionsProperties();
  
  @UML(identifier = "cellGeometry", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  CellGeometry getCellGeometry();
  
  @UML(identifier = "transformationParameterAvailability", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  boolean isTransformationParameterAvailable();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\GridSpatialRepresentation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */