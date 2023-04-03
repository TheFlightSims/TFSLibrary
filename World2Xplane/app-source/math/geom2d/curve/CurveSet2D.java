package math.geom2d.curve;

import java.util.Collection;
import math.geom2d.AffineTransform2D;
import math.geom2d.ShapeSet2D;

public interface CurveSet2D<T extends Curve2D> extends Curve2D, ShapeSet2D<T> {
  boolean contains(T paramT);
  
  Collection<T> curves();
  
  T get(int paramInt);
  
  T childCurve(double paramDouble);
  
  T firstCurve();
  
  T lastCurve();
  
  double localPosition(double paramDouble);
  
  double globalPosition(int paramInt, double paramDouble);
  
  int curveIndex(double paramDouble);
  
  CurveSet2D<? extends Curve2D> transform(AffineTransform2D paramAffineTransform2D);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\CurveSet2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */