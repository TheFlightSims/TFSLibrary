package math.geom2d.circulinear;

public interface CirculinearRing2D extends CirculinearContour2D {
  CirculinearDomain2D domain();
  
  CirculinearRing2D parallel(double paramDouble);
  
  CirculinearRing2D reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearRing2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */