package math.geom2d.grid;

import java.util.Collection;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.line.LineSegment2D;
import math.geom2d.point.PointSet2D;

public interface Grid2D {
  Point2D getOrigin();
  
  PointSet2D getVertices(Box2D paramBox2D);
  
  Collection<LineSegment2D> getEdges(Box2D paramBox2D);
  
  Point2D getClosestVertex(Point2D paramPoint2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\grid\Grid2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */