package math.geom3d.curve;

import java.util.Collection;
import math.geom3d.Point3D;
import math.geom3d.Shape3D;
import math.geom3d.transform.AffineTransform3D;

public interface Curve3D extends Shape3D {
  double getT0();
  
  double getT1();
  
  Point3D point(double paramDouble);
  
  Point3D firstPoint();
  
  Point3D lastPoint();
  
  Collection<Point3D> singularPoints();
  
  double position(Point3D paramPoint3D);
  
  double project(Point3D paramPoint3D);
  
  Curve3D reverseCurve();
  
  Collection<? extends ContinuousCurve3D> continuousCurves();
  
  Curve3D subCurve(double paramDouble1, double paramDouble2);
  
  Curve3D transform(AffineTransform3D paramAffineTransform3D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\curve\Curve3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */