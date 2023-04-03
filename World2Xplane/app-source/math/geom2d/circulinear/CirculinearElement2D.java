package math.geom2d.circulinear;

import math.geom2d.Box2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.domain.SmoothOrientedCurve2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearElement2D extends CirculinearContinuousCurve2D, SmoothOrientedCurve2D {
  CirculinearElement2D parallel(double paramDouble);
  
  CirculinearElement2D transform(CircleInversion2D paramCircleInversion2D);
  
  CurveSet2D<? extends CirculinearElement2D> clip(Box2D paramBox2D);
  
  CirculinearElement2D subCurve(double paramDouble1, double paramDouble2);
  
  CirculinearElement2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearElement2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */