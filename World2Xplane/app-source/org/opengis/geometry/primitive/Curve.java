package org.opengis.geometry.primitive;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.coordinate.GenericCurve;

@UML(identifier = "GM_Curve", specification = Specification.ISO_19107)
public interface Curve extends OrientableCurve, GenericCurve {
  @UML(identifier = "segment", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<? extends CurveSegment> getSegments();
  
  @UML(identifier = "proxy", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  OrientableCurve[] getProxy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Curve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */