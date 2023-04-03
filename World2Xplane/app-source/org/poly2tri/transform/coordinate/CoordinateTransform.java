package org.poly2tri.transform.coordinate;

import java.util.List;
import org.poly2tri.geometry.primitives.Point;

public interface CoordinateTransform {
  void transform(Point paramPoint1, Point paramPoint2);
  
  void transform(Point paramPoint);
  
  void transform(List<? extends Point> paramList);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\transform\coordinate\CoordinateTransform.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */