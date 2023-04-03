package org.opengis.geometry.complex;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Point;

@UML(identifier = "GM_CompositePoint", specification = Specification.ISO_19107)
public interface CompositePoint extends Composite {
  @UML(identifier = "generator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Point> getGenerators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\CompositePoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */