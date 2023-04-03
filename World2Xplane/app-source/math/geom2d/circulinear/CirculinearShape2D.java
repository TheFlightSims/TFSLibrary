package math.geom2d.circulinear;

import math.geom2d.Shape2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearShape2D extends Shape2D {
  CirculinearDomain2D buffer(double paramDouble);
  
  CirculinearShape2D transform(CircleInversion2D paramCircleInversion2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearShape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */