package math.geom2d.circulinear;

import math.geom2d.domain.SmoothContour2D;
import math.geom2d.transform.CircleInversion2D;

public interface CircleLine2D extends CirculinearContour2D, CirculinearElement2D, SmoothContour2D {
  CircleLine2D parallel(double paramDouble);
  
  CircleLine2D transform(CircleInversion2D paramCircleInversion2D);
  
  CircleLine2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CircleLine2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */