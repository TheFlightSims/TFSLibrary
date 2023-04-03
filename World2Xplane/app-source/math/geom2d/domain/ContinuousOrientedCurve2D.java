package math.geom2d.domain;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.curve.ContinuousCurve2D;
import math.geom2d.curve.CurveSet2D;

public interface ContinuousOrientedCurve2D extends ContinuousCurve2D, OrientedCurve2D {
  ContinuousOrientedCurve2D reverse();
  
  ContinuousOrientedCurve2D subCurve(double paramDouble1, double paramDouble2);
  
  ContinuousOrientedCurve2D transform(AffineTransform2D paramAffineTransform2D);
  
  CurveSet2D<? extends ContinuousOrientedCurve2D> clip(Box2D paramBox2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\ContinuousOrientedCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */