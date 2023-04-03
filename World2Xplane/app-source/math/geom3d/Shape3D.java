package math.geom3d;

import math.geom3d.transform.AffineTransform3D;

public interface Shape3D {
  public static final double ACCURACY = 1.0E-12D;
  
  boolean isEmpty();
  
  boolean isBounded();
  
  Box3D boundingBox();
  
  Shape3D clip(Box3D paramBox3D);
  
  Shape3D transform(AffineTransform3D paramAffineTransform3D);
  
  double distance(Point3D paramPoint3D);
  
  boolean contains(Point3D paramPoint3D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\Shape3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */