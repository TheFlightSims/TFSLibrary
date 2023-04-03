package math.geom2d.curve;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Vector2D;

public interface SmoothCurve2D extends ContinuousCurve2D {
  Vector2D tangent(double paramDouble);
  
  Vector2D normal(double paramDouble);
  
  SmoothCurve2D reverse();
  
  SmoothCurve2D subCurve(double paramDouble1, double paramDouble2);
  
  CurveSet2D<? extends SmoothCurve2D> clip(Box2D paramBox2D);
  
  SmoothCurve2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\SmoothCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */