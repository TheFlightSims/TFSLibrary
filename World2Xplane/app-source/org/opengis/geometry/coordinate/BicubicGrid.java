package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_BicubicGrid", specification = Specification.ISO_19107)
public interface BicubicGrid extends GriddedSurface {
  @UML(identifier = "horiVectorAtStart", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<double[]> getHorizontalVectorAtStart();
  
  @UML(identifier = "horiVectorAtEnd", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<double[]> getHorizontalVectorAtEnd();
  
  @UML(identifier = "vertVectorAtStart", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<double[]> getVerticalVectorAtStart();
  
  @UML(identifier = "vertVectorAtEnd", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<double[]> getVerticalVectorAtEnd();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\BicubicGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */