package math.geom2d.domain;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.curve.Curve2D;
import math.geom2d.curve.CurveSet2D;

public interface OrientedCurve2D extends Curve2D {
  double windingAngle(Point2D paramPoint2D);
  
  double signedDistance(Point2D paramPoint2D);
  
  double signedDistance(double paramDouble1, double paramDouble2);
  
  boolean isInside(Point2D paramPoint2D);
  
  OrientedCurve2D reverse();
  
  CurveSet2D<? extends OrientedCurve2D> clip(Box2D paramBox2D);
  
  OrientedCurve2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\OrientedCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */