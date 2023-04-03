package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_OffsetCurve", specification = Specification.ISO_19107)
public interface OffsetCurve extends CurveSegment {
  @UML(identifier = "baseCurve", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<CurveSegment> getBaseCurves();
  
  @UML(identifier = "distance", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getDistance();
  
  @UML(identifier = "refDirection", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  double[] getReferenceDirection();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\OffsetCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */