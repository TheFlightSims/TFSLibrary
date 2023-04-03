package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2d implements Serializable, Cloneable {
  static final long serialVersionUID = 6205762482756093838L;
  
  public double x;
  
  public double y;
  
  public Tuple2d(double paramDouble1, double paramDouble2) {
    this.x = paramDouble1;
    this.y = paramDouble2;
  }
  
  public Tuple2d(double[] paramArrayOfdouble) {
    this.x = paramArrayOfdouble[0];
    this.y = paramArrayOfdouble[1];
  }
  
  public Tuple2d(Tuple2d paramTuple2d) {
    this.x = paramTuple2d.x;
    this.y = paramTuple2d.y;
  }
  
  public Tuple2d(Tuple2f paramTuple2f) {
    this.x = paramTuple2f.x;
    this.y = paramTuple2f.y;
  }
  
  public Tuple2d() {
    this.x = 0.0D;
    this.y = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2) {
    this.x = paramDouble1;
    this.y = paramDouble2;
  }
  
  public final void set(double[] paramArrayOfdouble) {
    this.x = paramArrayOfdouble[0];
    this.y = paramArrayOfdouble[1];
  }
  
  public final void set(Tuple2d paramTuple2d) {
    this.x = paramTuple2d.x;
    this.y = paramTuple2d.y;
  }
  
  public final void set(Tuple2f paramTuple2f) {
    this.x = paramTuple2f.x;
    this.y = paramTuple2f.y;
  }
  
  public final void get(double[] paramArrayOfdouble) {
    paramArrayOfdouble[0] = this.x;
    paramArrayOfdouble[1] = this.y;
  }
  
  public final void add(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2) {
    paramTuple2d1.x += paramTuple2d2.x;
    paramTuple2d1.y += paramTuple2d2.y;
  }
  
  public final void add(Tuple2d paramTuple2d) {
    this.x += paramTuple2d.x;
    this.y += paramTuple2d.y;
  }
  
  public final void sub(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2) {
    paramTuple2d1.x -= paramTuple2d2.x;
    paramTuple2d1.y -= paramTuple2d2.y;
  }
  
  public final void sub(Tuple2d paramTuple2d) {
    this.x -= paramTuple2d.x;
    this.y -= paramTuple2d.y;
  }
  
  public final void negate(Tuple2d paramTuple2d) {
    this.x = -paramTuple2d.x;
    this.y = -paramTuple2d.y;
  }
  
  public final void negate() {
    this.x = -this.x;
    this.y = -this.y;
  }
  
  public final void scale(double paramDouble, Tuple2d paramTuple2d) {
    this.x = paramDouble * paramTuple2d.x;
    this.y = paramDouble * paramTuple2d.y;
  }
  
  public final void scale(double paramDouble) {
    this.x *= paramDouble;
    this.y *= paramDouble;
  }
  
  public final void scaleAdd(double paramDouble, Tuple2d paramTuple2d1, Tuple2d paramTuple2d2) {
    this.x = paramDouble * paramTuple2d1.x + paramTuple2d2.x;
    this.y = paramDouble * paramTuple2d1.y + paramTuple2d2.y;
  }
  
  public final void scaleAdd(double paramDouble, Tuple2d paramTuple2d) {
    this.x = paramDouble * this.x + paramTuple2d.x;
    this.y = paramDouble * this.y + paramTuple2d.y;
  }
  
  public int hashCode() {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
    return (int)(l ^ l >> 32L);
  }
  
  public boolean equals(Tuple2d paramTuple2d) {
    try {
      return (this.x == paramTuple2d.x && this.y == paramTuple2d.y);
    } catch (NullPointerException nullPointerException) {
      return false;
    } 
  }
  
  public boolean equals(Object paramObject) {
    try {
      Tuple2d tuple2d = (Tuple2d)paramObject;
      return (this.x == tuple2d.x && this.y == tuple2d.y);
    } catch (NullPointerException nullPointerException) {
      return false;
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public boolean epsilonEquals(Tuple2d paramTuple2d, double paramDouble) {
    double d = this.x - paramTuple2d.x;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.y - paramTuple2d.y;
    return !(((d < 0.0D) ? -d : d) > paramDouble);
  }
  
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
  
  public final void clamp(double paramDouble1, double paramDouble2, Tuple2d paramTuple2d) {
    if (paramTuple2d.x > paramDouble2) {
      this.x = paramDouble2;
    } else if (paramTuple2d.x < paramDouble1) {
      this.x = paramDouble1;
    } else {
      this.x = paramTuple2d.x;
    } 
    if (paramTuple2d.y > paramDouble2) {
      this.y = paramDouble2;
    } else if (paramTuple2d.y < paramDouble1) {
      this.y = paramDouble1;
    } else {
      this.y = paramTuple2d.y;
    } 
  }
  
  public final void clampMin(double paramDouble, Tuple2d paramTuple2d) {
    if (paramTuple2d.x < paramDouble) {
      this.x = paramDouble;
    } else {
      this.x = paramTuple2d.x;
    } 
    if (paramTuple2d.y < paramDouble) {
      this.y = paramDouble;
    } else {
      this.y = paramTuple2d.y;
    } 
  }
  
  public final void clampMax(double paramDouble, Tuple2d paramTuple2d) {
    if (paramTuple2d.x > paramDouble) {
      this.x = paramDouble;
    } else {
      this.x = paramTuple2d.x;
    } 
    if (paramTuple2d.y > paramDouble) {
      this.y = paramDouble;
    } else {
      this.y = paramTuple2d.y;
    } 
  }
  
  public final void absolute(Tuple2d paramTuple2d) {
    this.x = Math.abs(paramTuple2d.x);
    this.y = Math.abs(paramTuple2d.y);
  }
  
  public final void clamp(double paramDouble1, double paramDouble2) {
    if (this.x > paramDouble2) {
      this.x = paramDouble2;
    } else if (this.x < paramDouble1) {
      this.x = paramDouble1;
    } 
    if (this.y > paramDouble2) {
      this.y = paramDouble2;
    } else if (this.y < paramDouble1) {
      this.y = paramDouble1;
    } 
  }
  
  public final void clampMin(double paramDouble) {
    if (this.x < paramDouble)
      this.x = paramDouble; 
    if (this.y < paramDouble)
      this.y = paramDouble; 
  }
  
  public final void clampMax(double paramDouble) {
    if (this.x > paramDouble)
      this.x = paramDouble; 
    if (this.y > paramDouble)
      this.y = paramDouble; 
  }
  
  public final void absolute() {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
  }
  
  public final void interpolate(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2, double paramDouble) {
    this.x = (1.0D - paramDouble) * paramTuple2d1.x + paramDouble * paramTuple2d2.x;
    this.y = (1.0D - paramDouble) * paramTuple2d1.y + paramDouble * paramTuple2d2.y;
  }
  
  public final void interpolate(Tuple2d paramTuple2d, double paramDouble) {
    this.x = (1.0D - paramDouble) * this.x + paramDouble * paramTuple2d.x;
    this.y = (1.0D - paramDouble) * this.y + paramDouble * paramTuple2d.y;
  }
  
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new InternalError();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Tuple2d.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */