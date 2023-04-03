package math.geom2d.line;

import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.circulinear.CirculinearCurve2D;

public interface LinearShape2D extends CirculinearCurve2D {
  StraightLine2D supportingLine();
  
  double horizontalAngle();
  
  Point2D origin();
  
  Vector2D direction();
  
  Point2D intersection(LinearShape2D paramLinearShape2D);
  
  LinearShape2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\LinearShape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */