package math.geom2d.circulinear.buffer;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.circulinear.CirculinearContinuousCurve2D;

public interface CapFactory {
  CirculinearContinuousCurve2D createCap(Point2D paramPoint2D, Vector2D paramVector2D, double paramDouble);
  
  CirculinearContinuousCurve2D createCap(Point2D paramPoint2D1, Point2D paramPoint2D2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\CapFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */