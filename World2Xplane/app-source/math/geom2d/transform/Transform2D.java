package math.geom2d.transform;

import math.geom2d.Point2D;

public interface Transform2D {
  Point2D transform(Point2D paramPoint2D);
  
  Point2D[] transform(Point2D[] paramArrayOfPoint2D1, Point2D[] paramArrayOfPoint2D2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\transform\Transform2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */