package org.opengis.coverage.grid;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.operation.MathTransform;

@UML(identifier = "CV_GridGeometry", specification = Specification.OGC_01004)
public interface GridGeometry {
  @UML(identifier = "gridRange", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  GridEnvelope getGridRange();
  
  @UML(identifier = "gridToCoordinateSystem", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  MathTransform getGridToCRS();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridGeometry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */