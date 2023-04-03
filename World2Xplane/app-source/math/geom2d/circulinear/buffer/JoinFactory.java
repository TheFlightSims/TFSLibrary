package math.geom2d.circulinear.buffer;

import math.geom2d.circulinear.CirculinearContinuousCurve2D;
import math.geom2d.circulinear.CirculinearElement2D;

public interface JoinFactory {
  CirculinearContinuousCurve2D createJoin(CirculinearElement2D paramCirculinearElement2D1, CirculinearElement2D paramCirculinearElement2D2, double paramDouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\buffer\JoinFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */