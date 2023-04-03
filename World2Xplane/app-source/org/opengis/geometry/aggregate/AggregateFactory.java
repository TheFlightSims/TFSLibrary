package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Primitive;

public interface AggregateFactory {
  MultiPrimitive createMultiPrimitive(Set<Primitive> paramSet);
  
  MultiPoint createMultiPoint(Set<Point> paramSet);
  
  MultiCurve createMultiCurve(Set<OrientableCurve> paramSet);
  
  MultiSurface createMultiSurface(Set<OrientableSurface> paramSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\aggregate\AggregateFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */