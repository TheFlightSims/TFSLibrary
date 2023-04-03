package math.geom2d.circulinear;

import math.geom2d.domain.Contour2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearContour2D extends Contour2D, CirculinearContinuousCurve2D, CirculinearBoundary2D {
  CirculinearContour2D parallel(double paramDouble);
  
  CirculinearContour2D transform(CircleInversion2D paramCircleInversion2D);
  
  CirculinearContour2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearContour2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */