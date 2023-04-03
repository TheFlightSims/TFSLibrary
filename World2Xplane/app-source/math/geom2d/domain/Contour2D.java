package math.geom2d.domain;

import math.geom2d.AffineTransform2D;

public interface Contour2D extends Boundary2D, ContinuousOrientedCurve2D {
  Contour2D reverse();
  
  Contour2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\Contour2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */