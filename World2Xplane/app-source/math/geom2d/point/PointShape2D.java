package math.geom2d.point;

import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.circulinear.CirculinearShape2D;

public interface PointShape2D extends CirculinearShape2D, Iterable<Point2D> {
  Collection<Point2D> points();
  
  int size();
  
  PointShape2D transform(AffineTransform2D paramAffineTransform2D);
  
  PointShape2D clip(Box2D paramBox2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\point\PointShape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */