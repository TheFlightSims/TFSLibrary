package org.opengis.metadata.spatial;

import java.util.Collection;
import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Point;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Georectified", specification = Specification.ISO_19115)
public interface Georectified extends GridSpatialRepresentation {
  @UML(identifier = "checkPointAvailability", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  boolean isCheckPointAvailable();
  
  @UML(identifier = "checkPointDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getCheckPointDescription();
  
  @UML(identifier = "cornerPoints", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  List<? extends Point> getCornerPoints();
  
  @UML(identifier = "centerPoint", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Point getCenterPoint();
  
  @UML(identifier = "pointInPixel", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  PixelOrientation getPointInPixel();
  
  @UML(identifier = "transformationDimensionDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getTransformationDimensionDescription();
  
  @UML(identifier = "transformationDimensionMapping", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getTransformationDimensionMapping();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\Georectified.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */