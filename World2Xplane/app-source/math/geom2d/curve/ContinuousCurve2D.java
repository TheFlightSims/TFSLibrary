package math.geom2d.curve;

import java.awt.geom.GeneralPath;
import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Vector2D;
import math.geom2d.polygon.LinearCurve2D;

public interface ContinuousCurve2D extends Curve2D {
  boolean isClosed();
  
  Vector2D leftTangent(double paramDouble);
  
  Vector2D rightTangent(double paramDouble);
  
  double curvature(double paramDouble);
  
  Collection<? extends SmoothCurve2D> smoothPieces();
  
  LinearCurve2D asPolyline(int paramInt);
  
  GeneralPath appendPath(GeneralPath paramGeneralPath);
  
  ContinuousCurve2D reverse();
  
  ContinuousCurve2D subCurve(double paramDouble1, double paramDouble2);
  
  CurveSet2D<? extends ContinuousCurve2D> clip(Box2D paramBox2D);
  
  ContinuousCurve2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\ContinuousCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */