package math.geom2d.circulinear;

import java.util.Collection;
import math.geom2d.Box2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.domain.Boundary2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearBoundary2D extends CirculinearCurve2D, Boundary2D {
  CirculinearDomain2D domain();
  
  CirculinearBoundary2D parallel(double paramDouble);
  
  Collection<? extends CirculinearContour2D> continuousCurves();
  
  CurveSet2D<? extends CirculinearContinuousCurve2D> clip(Box2D paramBox2D);
  
  CirculinearBoundary2D transform(CircleInversion2D paramCircleInversion2D);
  
  CirculinearBoundary2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearBoundary2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */