package math.geom2d.polygon.convhull;

import java.util.Collection;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;

public interface ConvexHull2D {
  Polygon2D convexHull(Collection<? extends Point2D> paramCollection);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\convhull\ConvexHull2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */