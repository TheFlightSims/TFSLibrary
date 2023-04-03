package org.opengis.geometry.complex;

import java.util.List;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Point;

public interface ComplexFactory {
  CompositePoint createCompositePoint(Point paramPoint);
  
  CompositeCurve createCompositeCurve(List<OrientableCurve> paramList);
  
  CompositeSurface createCompositeSurface(List<OrientableSurface> paramList);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\ComplexFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */