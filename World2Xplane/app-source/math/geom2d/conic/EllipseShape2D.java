package math.geom2d.conic;

import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;
import math.geom2d.domain.SmoothContour2D;

public interface EllipseShape2D extends SmoothContour2D, Conic2D {
  Point2D center();
  
  boolean isCircle();
  
  boolean isDirect();
  
  EllipseShape2D reverse();
  
  Collection<? extends EllipseShape2D> continuousCurves();
  
  EllipseShape2D transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\EllipseShape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */