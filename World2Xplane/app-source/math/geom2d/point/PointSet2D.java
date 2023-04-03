package math.geom2d.point;

import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.ShapeSet2D;

public interface PointSet2D extends PointShape2D, ShapeSet2D<Point2D> {
  boolean add(Point2D paramPoint2D);
  
  void addAll(Collection<? extends Point2D> paramCollection);
  
  Collection<Point2D> points();
  
  int size();
  
  PointSet2D transform(AffineTransform2D paramAffineTransform2D);
  
  PointSet2D clip(Box2D paramBox2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\point\PointSet2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */