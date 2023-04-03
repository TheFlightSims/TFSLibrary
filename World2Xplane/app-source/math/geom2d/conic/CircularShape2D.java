package math.geom2d.conic;

import math.geom2d.Box2D;
import math.geom2d.circulinear.CirculinearElement2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.domain.SmoothOrientedCurve2D;

public interface CircularShape2D extends CirculinearElement2D, SmoothOrientedCurve2D {
  Circle2D supportingCircle();
  
  CurveSet2D<? extends CircularShape2D> clip(Box2D paramBox2D);
  
  CircularShape2D subCurve(double paramDouble1, double paramDouble2);
  
  CircularShape2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\CircularShape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */