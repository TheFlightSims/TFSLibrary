package math.geom2d.circulinear;

import java.util.Collection;
import math.geom2d.domain.Domain2D;
import math.geom2d.transform.CircleInversion2D;

public interface CirculinearDomain2D extends CirculinearShape2D, Domain2D {
  CirculinearBoundary2D boundary();
  
  Collection<? extends CirculinearContour2D> contours();
  
  CirculinearDomain2D complement();
  
  CirculinearDomain2D transform(CircleInversion2D paramCircleInversion2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearDomain2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */