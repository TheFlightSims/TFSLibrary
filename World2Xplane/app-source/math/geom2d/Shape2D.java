package math.geom2d;

import java.awt.Graphics2D;

public interface Shape2D extends GeometricObject2D {
  public static final double ACCURACY = 1.0E-12D;
  
  boolean contains(double paramDouble1, double paramDouble2);
  
  boolean contains(Point2D paramPoint2D);
  
  double distance(Point2D paramPoint2D);
  
  double distance(double paramDouble1, double paramDouble2);
  
  boolean isBounded();
  
  boolean isEmpty();
  
  Box2D boundingBox();
  
  Shape2D clip(Box2D paramBox2D);
  
  Shape2D transform(AffineTransform2D paramAffineTransform2D);
  
  void draw(Graphics2D paramGraphics2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\Shape2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */