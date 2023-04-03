package math.geom2d.conic;

import math.geom2d.AffineTransform2D;
import math.geom2d.domain.SmoothOrientedCurve2D;

public interface EllipseArcShape2D extends SmoothOrientedCurve2D {
  EllipseArcShape2D reverse();
  
  EllipseArcShape2D subCurve(double paramDouble1, double paramDouble2);
  
  EllipseArcShape2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\EllipseArcShape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */