package math.geom2d.domain;

import java.awt.Graphics2D;
import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Shape2D;
import math.geom2d.polygon.Polygon2D;

public interface Domain2D extends Shape2D {
  Boundary2D boundary();
  
  Collection<? extends Contour2D> contours();
  
  Domain2D complement();
  
  Polygon2D asPolygon(int paramInt);
  
  Domain2D transform(AffineTransform2D paramAffineTransform2D);
  
  Domain2D clip(Box2D paramBox2D);
  
  void draw(Graphics2D paramGraphics2D);
  
  void fill(Graphics2D paramGraphics2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\Domain2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */