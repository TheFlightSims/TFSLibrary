package math.geom2d.domain;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.curve.SmoothCurve2D;

public interface SmoothOrientedCurve2D extends SmoothCurve2D, ContinuousOrientedCurve2D {
  SmoothOrientedCurve2D reverse();
  
  SmoothOrientedCurve2D subCurve(double paramDouble1, double paramDouble2);
  
  CurveSet2D<? extends SmoothOrientedCurve2D> clip(Box2D paramBox2D);
  
  SmoothOrientedCurve2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\SmoothOrientedCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */