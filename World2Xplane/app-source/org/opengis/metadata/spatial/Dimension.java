package org.opengis.metadata.spatial;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_Dimension", specification = Specification.ISO_19115)
public interface Dimension {
  @UML(identifier = "dimensionName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  DimensionNameType getDimensionName();
  
  @UML(identifier = "dimensionSize", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Integer getDimensionSize();
  
  @UML(identifier = "resolution", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getResolution();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\Dimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */