package javax.vecmath;

import java.io.Serializable;

public class Vector2d extends Tuple2d implements Serializable {
  static final long serialVersionUID = 8572646365302599857L;
  
  public Vector2d(double paramDouble1, double paramDouble2) {
    super(paramDouble1, paramDouble2);
  }
  
  public Vector2d(double[] paramArrayOfdouble) {
    super(paramArrayOfdouble);
  }
  
  public Vector2d(Vector2d paramVector2d) {
    super(paramVector2d);
  }
  
  public Vector2d(Vector2f paramVector2f) {
    super(paramVector2f);
  }
  
  public Vector2d(Tuple2d paramTuple2d) {
    super(paramTuple2d);
  }
  
  public Vector2d(Tuple2f paramTuple2f) {
    super(paramTuple2f);
  }
  
  public Vector2d() {}
  
  public final double dot(Vector2d paramVector2d) {
    return this.x * paramVector2d.x + this.y * paramVector2d.y;
  }
  
  public final double length() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
  
  public final double lengthSquared() {
    return this.x * this.x + this.y * this.y;
  }
  
  public final void normalize(Vector2d paramVector2d) {
    double d = 1.0D / Math.sqrt(paramVector2d.x * paramVector2d.x + paramVector2d.y * paramVector2d.y);
    paramVector2d.x *= d;
    paramVector2d.y *= d;
  }
  
  public final void normalize() {
    double d = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y);
    this.x *= d;
    this.y *= d;
  }
  
  public final double angle(Vector2d paramVector2d) {
    double d = dot(paramVector2d) / length() * paramVector2d.length();
    if (d < -1.0D)
      d = -1.0D; 
    if (d > 1.0D)
      d = 1.0D; 
    return Math.acos(d);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Vector2d.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */