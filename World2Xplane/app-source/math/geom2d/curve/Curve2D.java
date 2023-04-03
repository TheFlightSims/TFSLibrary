package math.geom2d.curve;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Shape2D;
import math.geom2d.line.LinearShape2D;

public interface Curve2D extends Shape2D, Cloneable {
  double t0();
  
  @Deprecated
  double getT0();
  
  double t1();
  
  @Deprecated
  double getT1();
  
  Point2D point(double paramDouble);
  
  Point2D firstPoint();
  
  Point2D lastPoint();
  
  Collection<Point2D> singularPoints();
  
  Collection<Point2D> vertices();
  
  boolean isSingular(double paramDouble);
  
  double position(Point2D paramPoint2D);
  
  double project(Point2D paramPoint2D);
  
  Collection<Point2D> intersections(LinearShape2D paramLinearShape2D);
  
  Curve2D reverse();
  
  Collection<? extends ContinuousCurve2D> continuousCurves();
  
  Curve2D subCurve(double paramDouble1, double paramDouble2);
  
  Curve2D transform(AffineTransform2D paramAffineTransform2D);
  
  CurveSet2D<? extends Curve2D> clip(Box2D paramBox2D);
  
  Shape asAwtShape();
  
  void draw(Graphics2D paramGraphics2D);
  
  Curve2D clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\Curve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */