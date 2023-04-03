package math.geom2d.line;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.circulinear.CirculinearElement2D;
import math.geom2d.curve.CurveSet2D;

public interface LinearElement2D extends CirculinearElement2D, LinearShape2D {
  LinearElement2D transform(AffineTransform2D paramAffineTransform2D);
  
  LinearElement2D subCurve(double paramDouble1, double paramDouble2);
  
  CurveSet2D<? extends LinearElement2D> clip(Box2D paramBox2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\LinearElement2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */