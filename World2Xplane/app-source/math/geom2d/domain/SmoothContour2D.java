package math.geom2d.domain;

import math.geom2d.AffineTransform2D;

public interface SmoothContour2D extends SmoothOrientedCurve2D, Contour2D {
  SmoothContour2D transform(AffineTransform2D paramAffineTransform2D);
  
  SmoothContour2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\SmoothContour2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */