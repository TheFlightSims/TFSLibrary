package math.geom2d.domain;

import java.awt.Graphics2D;
import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;

public interface Boundary2D extends OrientedCurve2D {
  boolean isInside(Point2D paramPoint2D);
  
  Collection<? extends Contour2D> continuousCurves();
  
  Domain2D domain();
  
  Boundary2D reverse();
  
  Boundary2D transform(AffineTransform2D paramAffineTransform2D);
  
  void fill(Graphics2D paramGraphics2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\Boundary2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */