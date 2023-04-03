package math.geom2d.circulinear;

import java.util.Collection;
import math.geom2d.Box2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.domain.ContinuousOrientedCurve2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearContinuousCurve2D extends CirculinearCurve2D, ContinuousOrientedCurve2D {
  CirculinearContinuousCurve2D parallel(double paramDouble);
  
  CirculinearContinuousCurve2D transform(CircleInversion2D paramCircleInversion2D);
  
  Collection<? extends CirculinearElement2D> smoothPieces();
  
  CurveSet2D<? extends CirculinearContinuousCurve2D> clip(Box2D paramBox2D);
  
  CirculinearContinuousCurve2D subCurve(double paramDouble1, double paramDouble2);
  
  CirculinearContinuousCurve2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearContinuousCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */