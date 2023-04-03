package javax.vecmath;

import java.io.Serializable;

public class Point2f extends Tuple2f implements Serializable {
  static final long serialVersionUID = -4801347926528714435L;
  
  public Point2f(float paramFloat1, float paramFloat2) {
    super(paramFloat1, paramFloat2);
  }
  
  public Point2f(float[] paramArrayOffloat) {
    super(paramArrayOffloat);
  }
  
  public Point2f(Point2f paramPoint2f) {
    super(paramPoint2f);
  }
  
  public Point2f(Point2d paramPoint2d) {
    super(paramPoint2d);
  }
  
  public Point2f(Tuple2d paramTuple2d) {
    super(paramTuple2d);
  }
  
  public Point2f(Tuple2f paramTuple2f) {
    super(paramTuple2f);
  }
  
  public Point2f() {}
  
  public final float distanceSquared(Point2f paramPoint2f) {
    float f1 = this.x - paramPoint2f.x;
    float f2 = this.y - paramPoint2f.y;
    return f1 * f1 + f2 * f2;
  }
  
  public final float distance(Point2f paramPoint2f) {
    float f1 = this.x - paramPoint2f.x;
    float f2 = this.y - paramPoint2f.y;
    return (float)Math.sqrt((f1 * f1 + f2 * f2));
  }
  
  public final float distanceL1(Point2f paramPoint2f) {
    return Math.abs(this.x - paramPoint2f.x) + Math.abs(this.y - paramPoint2f.y);
  }
  
  public final float distanceLinf(Point2f paramPoint2f) {
    return Math.max(Math.abs(this.x - paramPoint2f.x), Math.abs(this.y - paramPoint2f.y));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Point2f.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */