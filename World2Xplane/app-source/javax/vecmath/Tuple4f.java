package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4f implements Serializable, Cloneable {
  static final long serialVersionUID = 7068460319248845763L;
  
  public float x;
  
  public float y;
  
  public float z;
  
  public float w;
  
  public Tuple4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.z = paramFloat3;
    this.w = paramFloat4;
  }
  
  public Tuple4f(float[] paramArrayOffloat) {
    this.x = paramArrayOffloat[0];
    this.y = paramArrayOffloat[1];
    this.z = paramArrayOffloat[2];
    this.w = paramArrayOffloat[3];
  }
  
  public Tuple4f(Tuple4f paramTuple4f) {
    this.x = paramTuple4f.x;
    this.y = paramTuple4f.y;
    this.z = paramTuple4f.z;
    this.w = paramTuple4f.w;
  }
  
  public Tuple4f(Tuple4d paramTuple4d) {
    this.x = (float)paramTuple4d.x;
    this.y = (float)paramTuple4d.y;
    this.z = (float)paramTuple4d.z;
    this.w = (float)paramTuple4d.w;
  }
  
  public Tuple4f() {
    this.x = 0.0F;
    this.y = 0.0F;
    this.z = 0.0F;
    this.w = 0.0F;
  }
  
  public final void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.z = paramFloat3;
    this.w = paramFloat4;
  }
  
  public final void set(float[] paramArrayOffloat) {
    this.x = paramArrayOffloat[0];
    this.y = paramArrayOffloat[1];
    this.z = paramArrayOffloat[2];
    this.w = paramArrayOffloat[3];
  }
  
  public final void set(Tuple4f paramTuple4f) {
    this.x = paramTuple4f.x;
    this.y = paramTuple4f.y;
    this.z = paramTuple4f.z;
    this.w = paramTuple4f.w;
  }
  
  public final void set(Tuple4d paramTuple4d) {
    this.x = (float)paramTuple4d.x;
    this.y = (float)paramTuple4d.y;
    this.z = (float)paramTuple4d.z;
    this.w = (float)paramTuple4d.w;
  }
  
  public final void get(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = this.x;
    paramArrayOffloat[1] = this.y;
    paramArrayOffloat[2] = this.z;
    paramArrayOffloat[3] = this.w;
  }
  
  public final void get(Tuple4f paramTuple4f) {
    paramTuple4f.x = this.x;
    paramTuple4f.y = this.y;
    paramTuple4f.z = this.z;
    paramTuple4f.w = this.w;
  }
  
  public final void add(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
    paramTuple4f1.x += paramTuple4f2.x;
    paramTuple4f1.y += paramTuple4f2.y;
    paramTuple4f1.z += paramTuple4f2.z;
    paramTuple4f1.w += paramTuple4f2.w;
  }
  
  public final void add(Tuple4f paramTuple4f) {
    this.x += paramTuple4f.x;
    this.y += paramTuple4f.y;
    this.z += paramTuple4f.z;
    this.w += paramTuple4f.w;
  }
  
  public final void sub(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
    paramTuple4f1.x -= paramTuple4f2.x;
    paramTuple4f1.y -= paramTuple4f2.y;
    paramTuple4f1.z -= paramTuple4f2.z;
    paramTuple4f1.w -= paramTuple4f2.w;
  }
  
  public final void sub(Tuple4f paramTuple4f) {
    this.x -= paramTuple4f.x;
    this.y -= paramTuple4f.y;
    this.z -= paramTuple4f.z;
    this.w -= paramTuple4f.w;
  }
  
  public final void negate(Tuple4f paramTuple4f) {
    this.x = -paramTuple4f.x;
    this.y = -paramTuple4f.y;
    this.z = -paramTuple4f.z;
    this.w = -paramTuple4f.w;
  }
  
  public final void negate() {
    this.x = -this.x;
    this.y = -this.y;
    this.z = -this.z;
    this.w = -this.w;
  }
  
  public final void scale(float paramFloat, Tuple4f paramTuple4f) {
    this.x = paramFloat * paramTuple4f.x;
    this.y = paramFloat * paramTuple4f.y;
    this.z = paramFloat * paramTuple4f.z;
    this.w = paramFloat * paramTuple4f.w;
  }
  
  public final void scale(float paramFloat) {
    this.x *= paramFloat;
    this.y *= paramFloat;
    this.z *= paramFloat;
    this.w *= paramFloat;
  }
  
  public final void scaleAdd(float paramFloat, Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
    this.x = paramFloat * paramTuple4f1.x + paramTuple4f2.x;
    this.y = paramFloat * paramTuple4f1.y + paramTuple4f2.y;
    this.z = paramFloat * paramTuple4f1.z + paramTuple4f2.z;
    this.w = paramFloat * paramTuple4f1.w + paramTuple4f2.w;
  }
  
  public final void scaleAdd(float paramFloat, Tuple4f paramTuple4f) {
    this.x = paramFloat * this.x + paramTuple4f.x;
    this.y = paramFloat * this.y + paramTuple4f.y;
    this.z = paramFloat * this.z + paramTuple4f.z;
    this.w = paramFloat * this.w + paramTuple4f.w;
  }
  
  public String toString() {
    return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
  }
  
  public boolean equals(Tuple4f paramTuple4f) {
    try {
      return (this.x == paramTuple4f.x && this.y == paramTuple4f.y && this.z == paramTuple4f.z && this.w == paramTuple4f.w);
    } catch (NullPointerException nullPointerException) {
      return false;
    } 
  }
  
  public boolean equals(Object paramObject) {
    try {
      Tuple4f tuple4f = (Tuple4f)paramObject;
      return (this.x == tuple4f.x && this.y == tuple4f.y && this.z == tuple4f.z && this.w == tuple4f.w);
    } catch (NullPointerException nullPointerException) {
      return false;
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public boolean epsilonEquals(Tuple4f paramTuple4f, float paramFloat) {
    float f = this.x - paramTuple4f.x;
    if (((f < 0.0F) ? -f : f) > paramFloat)
      return false; 
    f = this.y - paramTuple4f.y;
    if (((f < 0.0F) ? -f : f) > paramFloat)
      return false; 
    f = this.z - paramTuple4f.z;
    if (((f < 0.0F) ? -f : f) > paramFloat)
      return false; 
    f = this.w - paramTuple4f.w;
    return !(((f < 0.0F) ? -f : f) > paramFloat);
  }
  
  public int hashCode() {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.x);
    l = 31L * l + VecMathUtil.floatToIntBits(this.y);
    l = 31L * l + VecMathUtil.floatToIntBits(this.z);
    l = 31L * l + VecMathUtil.floatToIntBits(this.w);
    return (int)(l ^ l >> 32L);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2, Tuple4f paramTuple4f) {
    if (paramTuple4f.x > paramFloat2) {
      this.x = paramFloat2;
    } else if (paramTuple4f.x < paramFloat1) {
      this.x = paramFloat1;
    } else {
      this.x = paramTuple4f.x;
    } 
    if (paramTuple4f.y > paramFloat2) {
      this.y = paramFloat2;
    } else if (paramTuple4f.y < paramFloat1) {
      this.y = paramFloat1;
    } else {
      this.y = paramTuple4f.y;
    } 
    if (paramTuple4f.z > paramFloat2) {
      this.z = paramFloat2;
    } else if (paramTuple4f.z < paramFloat1) {
      this.z = paramFloat1;
    } else {
      this.z = paramTuple4f.z;
    } 
    if (paramTuple4f.w > paramFloat2) {
      this.w = paramFloat2;
    } else if (paramTuple4f.w < paramFloat1) {
      this.w = paramFloat1;
    } else {
      this.w = paramTuple4f.w;
    } 
  }
  
  public final void clampMin(float paramFloat, Tuple4f paramTuple4f) {
    if (paramTuple4f.x < paramFloat) {
      this.x = paramFloat;
    } else {
      this.x = paramTuple4f.x;
    } 
    if (paramTuple4f.y < paramFloat) {
      this.y = paramFloat;
    } else {
      this.y = paramTuple4f.y;
    } 
    if (paramTuple4f.z < paramFloat) {
      this.z = paramFloat;
    } else {
      this.z = paramTuple4f.z;
    } 
    if (paramTuple4f.w < paramFloat) {
      this.w = paramFloat;
    } else {
      this.w = paramTuple4f.w;
    } 
  }
  
  public final void clampMax(float paramFloat, Tuple4f paramTuple4f) {
    if (paramTuple4f.x > paramFloat) {
      this.x = paramFloat;
    } else {
      this.x = paramTuple4f.x;
    } 
    if (paramTuple4f.y > paramFloat) {
      this.y = paramFloat;
    } else {
      this.y = paramTuple4f.y;
    } 
    if (paramTuple4f.z > paramFloat) {
      this.z = paramFloat;
    } else {
      this.z = paramTuple4f.z;
    } 
    if (paramTuple4f.w > paramFloat) {
      this.w = paramFloat;
    } else {
      this.w = paramTuple4f.z;
    } 
  }
  
  public final void absolute(Tuple4f paramTuple4f) {
    this.x = Math.abs(paramTuple4f.x);
    this.y = Math.abs(paramTuple4f.y);
    this.z = Math.abs(paramTuple4f.z);
    this.w = Math.abs(paramTuple4f.w);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2) {
    if (this.x > paramFloat2) {
      this.x = paramFloat2;
    } else if (this.x < paramFloat1) {
      this.x = paramFloat1;
    } 
    if (this.y > paramFloat2) {
      this.y = paramFloat2;
    } else if (this.y < paramFloat1) {
      this.y = paramFloat1;
    } 
    if (this.z > paramFloat2) {
      this.z = paramFloat2;
    } else if (this.z < paramFloat1) {
      this.z = paramFloat1;
    } 
    if (this.w > paramFloat2) {
      this.w = paramFloat2;
    } else if (this.w < paramFloat1) {
      this.w = paramFloat1;
    } 
  }
  
  public final void clampMin(float paramFloat) {
    if (this.x < paramFloat)
      this.x = paramFloat; 
    if (this.y < paramFloat)
      this.y = paramFloat; 
    if (this.z < paramFloat)
      this.z = paramFloat; 
    if (this.w < paramFloat)
      this.w = paramFloat; 
  }
  
  public final void clampMax(float paramFloat) {
    if (this.x > paramFloat)
      this.x = paramFloat; 
    if (this.y > paramFloat)
      this.y = paramFloat; 
    if (this.z > paramFloat)
      this.z = paramFloat; 
    if (this.w > paramFloat)
      this.w = paramFloat; 
  }
  
  public final void absolute() {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
    this.z = Math.abs(this.z);
    this.w = Math.abs(this.w);
  }
  
  public void interpolate(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2, float paramFloat) {
    this.x = (1.0F - paramFloat) * paramTuple4f1.x + paramFloat * paramTuple4f2.x;
    this.y = (1.0F - paramFloat) * paramTuple4f1.y + paramFloat * paramTuple4f2.y;
    this.z = (1.0F - paramFloat) * paramTuple4f1.z + paramFloat * paramTuple4f2.z;
    this.w = (1.0F - paramFloat) * paramTuple4f1.w + paramFloat * paramTuple4f2.w;
  }
  
  public void interpolate(Tuple4f paramTuple4f, float paramFloat) {
    this.x = (1.0F - paramFloat) * this.x + paramFloat * paramTuple4f.x;
    this.y = (1.0F - paramFloat) * this.y + paramFloat * paramTuple4f.y;
    this.z = (1.0F - paramFloat) * this.z + paramFloat * paramTuple4f.z;
    this.w = (1.0F - paramFloat) * this.w + paramFloat * paramTuple4f.w;
  }
  
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new InternalError();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Tuple4f.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */