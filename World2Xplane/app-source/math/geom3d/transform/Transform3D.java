package math.geom3d.transform;

import math.geom3d.Point3D;

public interface Transform3D {
  Point3D[] transformPoints(Point3D[] paramArrayOfPoint3D1, Point3D[] paramArrayOfPoint3D2);
  
  Point3D transformPoint(Point3D paramPoint3D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\transform\Transform3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */