package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_GriddedSurface", specification = Specification.ISO_19107)
public interface GriddedSurface extends ParametricCurveSurface {
  @UML(identifier = "controlPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PointGrid getControlPoints();
  
  @UML(identifier = "rows", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getRows();
  
  @UML(identifier = "columns", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getColumns();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\GriddedSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */