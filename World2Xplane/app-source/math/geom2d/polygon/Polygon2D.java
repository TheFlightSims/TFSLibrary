package math.geom2d.polygon;

import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.circulinear.CirculinearContourArray2D;
import math.geom2d.circulinear.CirculinearDomain2D;
import math.geom2d.line.LineSegment2D;

public interface Polygon2D extends CirculinearDomain2D {
  Collection<Point2D> vertices();
  
  Point2D vertex(int paramInt);
  
  void setVertex(int paramInt, Point2D paramPoint2D);
  
  void addVertex(Point2D paramPoint2D);
  
  void insertVertex(int paramInt, Point2D paramPoint2D);
  
  void removeVertex(int paramInt);
  
  int vertexNumber();
  
  int closestVertexIndex(Point2D paramPoint2D);
  
  Collection<? extends LineSegment2D> edges();
  
  int edgeNumber();
  
  Point2D centroid();
  
  double area();
  
  CirculinearContourArray2D<? extends LinearRing2D> boundary();
  
  Collection<? extends LinearRing2D> contours();
  
  Polygon2D complement();
  
  Polygon2D transform(AffineTransform2D paramAffineTransform2D);
  
  Polygon2D clip(Box2D paramBox2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\Polygon2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */