package math.geom2d.circulinear;

import math.geom2d.Box2D;
import math.geom2d.curve.CurveSet2D;

public interface CirculinearCurveSet2D<T extends CirculinearCurve2D> extends CurveSet2D<T>, CirculinearCurve2D {
  CirculinearCurveSet2D<? extends CirculinearCurve2D> clip(Box2D paramBox2D);
  
  CirculinearCurveSet2D<? extends CirculinearCurve2D> subCurve(double paramDouble1, double paramDouble2);
  
  CirculinearCurveSet2D<? extends CirculinearCurve2D> reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearCurveSet2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */