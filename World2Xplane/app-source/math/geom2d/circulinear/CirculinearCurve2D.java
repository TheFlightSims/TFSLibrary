package math.geom2d.circulinear;

import java.util.Collection;
import math.geom2d.Box2D;
import math.geom2d.curve.Curve2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearCurve2D extends CirculinearShape2D, Curve2D {
  double length();
  
  double length(double paramDouble);
  
  double position(double paramDouble);
  
  CirculinearCurve2D parallel(double paramDouble);
  
  CirculinearCurve2D transform(CircleInversion2D paramCircleInversion2D);
  
  Collection<? extends CirculinearContinuousCurve2D> continuousCurves();
  
  CurveSet2D<? extends CirculinearCurve2D> clip(Box2D paramBox2D);
  
  CirculinearCurve2D subCurve(double paramDouble1, double paramDouble2);
  
  CirculinearCurve2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */