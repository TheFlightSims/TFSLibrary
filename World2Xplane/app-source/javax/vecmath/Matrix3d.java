package javax.vecmath;

import java.io.Serializable;

public class Matrix3d implements Serializable, Cloneable {
  static final long serialVersionUID = 6837536777072402710L;
  
  public double m00;
  
  public double m01;
  
  public double m02;
  
  public double m10;
  
  public double m11;
  
  public double m12;
  
  public double m20;
  
  public double m21;
  
  public double m22;
  
  private static final double EPS = 1.110223024E-16D;
  
  private static final double ERR_EPS = 1.0E-8D;
  
  private static double xin;
  
  private static double yin;
  
  private static double zin;
  
  private static double xout;
  
  private static double yout;
  
  private static double zout;
  
  public Matrix3d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9) {
    this.m00 = paramDouble1;
    this.m01 = paramDouble2;
    this.m02 = paramDouble3;
    this.m10 = paramDouble4;
    this.m11 = paramDouble5;
    this.m12 = paramDouble6;
    this.m20 = paramDouble7;
    this.m21 = paramDouble8;
    this.m22 = paramDouble9;
  }
  
  public Matrix3d(double[] paramArrayOfdouble) {
    this.m00 = paramArrayOfdouble[0];
    this.m01 = paramArrayOfdouble[1];
    this.m02 = paramArrayOfdouble[2];
    this.m10 = paramArrayOfdouble[3];
    this.m11 = paramArrayOfdouble[4];
    this.m12 = paramArrayOfdouble[5];
    this.m20 = paramArrayOfdouble[6];
    this.m21 = paramArrayOfdouble[7];
    this.m22 = paramArrayOfdouble[8];
  }
  
  public Matrix3d(Matrix3d paramMatrix3d) {
    this.m00 = paramMatrix3d.m00;
    this.m01 = paramMatrix3d.m01;
    this.m02 = paramMatrix3d.m02;
    this.m10 = paramMatrix3d.m10;
    this.m11 = paramMatrix3d.m11;
    this.m12 = paramMatrix3d.m12;
    this.m20 = paramMatrix3d.m20;
    this.m21 = paramMatrix3d.m21;
    this.m22 = paramMatrix3d.m22;
  }
  
  public Matrix3d(Matrix3f paramMatrix3f) {
    this.m00 = paramMatrix3f.m00;
    this.m01 = paramMatrix3f.m01;
    this.m02 = paramMatrix3f.m02;
    this.m10 = paramMatrix3f.m10;
    this.m11 = paramMatrix3f.m11;
    this.m12 = paramMatrix3f.m12;
    this.m20 = paramMatrix3f.m20;
    this.m21 = paramMatrix3f.m21;
    this.m22 = paramMatrix3f.m22;
  }
  
  public Matrix3d() {
    this.m00 = 0.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 0.0D;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 0.0D;
  }
  
  public String toString() {
    return this.m00 + ", " + this.m01 + ", " + this.m02 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + "\n";
  }
  
  public final void setIdentity() {
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
  }
  
  public final void setScale(double paramDouble) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = arrayOfDouble1[0] * paramDouble;
    this.m01 = arrayOfDouble1[1] * paramDouble;
    this.m02 = arrayOfDouble1[2] * paramDouble;
    this.m10 = arrayOfDouble1[3] * paramDouble;
    this.m11 = arrayOfDouble1[4] * paramDouble;
    this.m12 = arrayOfDouble1[5] * paramDouble;
    this.m20 = arrayOfDouble1[6] * paramDouble;
    this.m21 = arrayOfDouble1[7] * paramDouble;
    this.m22 = arrayOfDouble1[8] * paramDouble;
  }
  
  public final void setElement(int paramInt1, int paramInt2, double paramDouble) {
    switch (paramInt1) {
      case 0:
        switch (paramInt2) {
          case 0:
            this.m00 = paramDouble;
            return;
          case 1:
            this.m01 = paramDouble;
            return;
          case 2:
            this.m02 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
      case 1:
        switch (paramInt2) {
          case 0:
            this.m10 = paramDouble;
            return;
          case 1:
            this.m11 = paramDouble;
            return;
          case 2:
            this.m12 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
      case 2:
        switch (paramInt2) {
          case 0:
            this.m20 = paramDouble;
            return;
          case 1:
            this.m21 = paramDouble;
            return;
          case 2:
            this.m22 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
  }
  
  public final double getElement(int paramInt1, int paramInt2) {
    switch (paramInt1) {
      case 0:
        switch (paramInt2) {
          case 0:
            return this.m00;
          case 1:
            return this.m01;
          case 2:
            return this.m02;
        } 
        break;
      case 1:
        switch (paramInt2) {
          case 0:
            return this.m10;
          case 1:
            return this.m11;
          case 2:
            return this.m12;
        } 
        break;
      case 2:
        switch (paramInt2) {
          case 0:
            return this.m20;
          case 1:
            return this.m21;
          case 2:
            return this.m22;
        } 
        break;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d1"));
  }
  
  public final void getRow(int paramInt, Vector3d paramVector3d) {
    if (paramInt == 0) {
      paramVector3d.x = this.m00;
      paramVector3d.y = this.m01;
      paramVector3d.z = this.m02;
    } else if (paramInt == 1) {
      paramVector3d.x = this.m10;
      paramVector3d.y = this.m11;
      paramVector3d.z = this.m12;
    } else if (paramInt == 2) {
      paramVector3d.x = this.m20;
      paramVector3d.y = this.m21;
      paramVector3d.z = this.m22;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
    } 
  }
  
  public final void getRow(int paramInt, double[] paramArrayOfdouble) {
    if (paramInt == 0) {
      paramArrayOfdouble[0] = this.m00;
      paramArrayOfdouble[1] = this.m01;
      paramArrayOfdouble[2] = this.m02;
    } else if (paramInt == 1) {
      paramArrayOfdouble[0] = this.m10;
      paramArrayOfdouble[1] = this.m11;
      paramArrayOfdouble[2] = this.m12;
    } else if (paramInt == 2) {
      paramArrayOfdouble[0] = this.m20;
      paramArrayOfdouble[1] = this.m21;
      paramArrayOfdouble[2] = this.m22;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
    } 
  }
  
  public final void getColumn(int paramInt, Vector3d paramVector3d) {
    if (paramInt == 0) {
      paramVector3d.x = this.m00;
      paramVector3d.y = this.m10;
      paramVector3d.z = this.m20;
    } else if (paramInt == 1) {
      paramVector3d.x = this.m01;
      paramVector3d.y = this.m11;
      paramVector3d.z = this.m21;
    } else if (paramInt == 2) {
      paramVector3d.x = this.m02;
      paramVector3d.y = this.m12;
      paramVector3d.z = this.m22;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
    } 
  }
  
  public final void getColumn(int paramInt, double[] paramArrayOfdouble) {
    if (paramInt == 0) {
      paramArrayOfdouble[0] = this.m00;
      paramArrayOfdouble[1] = this.m10;
      paramArrayOfdouble[2] = this.m20;
    } else if (paramInt == 1) {
      paramArrayOfdouble[0] = this.m01;
      paramArrayOfdouble[1] = this.m11;
      paramArrayOfdouble[2] = this.m21;
    } else if (paramInt == 2) {
      paramArrayOfdouble[0] = this.m02;
      paramArrayOfdouble[1] = this.m12;
      paramArrayOfdouble[2] = this.m22;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
    } 
  }
  
  public final void setRow(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
    switch (paramInt) {
      case 0:
        this.m00 = paramDouble1;
        this.m01 = paramDouble2;
        this.m02 = paramDouble3;
        return;
      case 1:
        this.m10 = paramDouble1;
        this.m11 = paramDouble2;
        this.m12 = paramDouble3;
        return;
      case 2:
        this.m20 = paramDouble1;
        this.m21 = paramDouble2;
        this.m22 = paramDouble3;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
  }
  
  public final void setRow(int paramInt, Vector3d paramVector3d) {
    switch (paramInt) {
      case 0:
        this.m00 = paramVector3d.x;
        this.m01 = paramVector3d.y;
        this.m02 = paramVector3d.z;
        return;
      case 1:
        this.m10 = paramVector3d.x;
        this.m11 = paramVector3d.y;
        this.m12 = paramVector3d.z;
        return;
      case 2:
        this.m20 = paramVector3d.x;
        this.m21 = paramVector3d.y;
        this.m22 = paramVector3d.z;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
  }
  
  public final void setRow(int paramInt, double[] paramArrayOfdouble) {
    switch (paramInt) {
      case 0:
        this.m00 = paramArrayOfdouble[0];
        this.m01 = paramArrayOfdouble[1];
        this.m02 = paramArrayOfdouble[2];
        return;
      case 1:
        this.m10 = paramArrayOfdouble[0];
        this.m11 = paramArrayOfdouble[1];
        this.m12 = paramArrayOfdouble[2];
        return;
      case 2:
        this.m20 = paramArrayOfdouble[0];
        this.m21 = paramArrayOfdouble[1];
        this.m22 = paramArrayOfdouble[2];
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
  }
  
  public final void setColumn(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
    switch (paramInt) {
      case 0:
        this.m00 = paramDouble1;
        this.m10 = paramDouble2;
        this.m20 = paramDouble3;
        return;
      case 1:
        this.m01 = paramDouble1;
        this.m11 = paramDouble2;
        this.m21 = paramDouble3;
        return;
      case 2:
        this.m02 = paramDouble1;
        this.m12 = paramDouble2;
        this.m22 = paramDouble3;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
  }
  
  public final void setColumn(int paramInt, Vector3d paramVector3d) {
    switch (paramInt) {
      case 0:
        this.m00 = paramVector3d.x;
        this.m10 = paramVector3d.y;
        this.m20 = paramVector3d.z;
        return;
      case 1:
        this.m01 = paramVector3d.x;
        this.m11 = paramVector3d.y;
        this.m21 = paramVector3d.z;
        return;
      case 2:
        this.m02 = paramVector3d.x;
        this.m12 = paramVector3d.y;
        this.m22 = paramVector3d.z;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
  }
  
  public final void setColumn(int paramInt, double[] paramArrayOfdouble) {
    switch (paramInt) {
      case 0:
        this.m00 = paramArrayOfdouble[0];
        this.m10 = paramArrayOfdouble[1];
        this.m20 = paramArrayOfdouble[2];
        return;
      case 1:
        this.m01 = paramArrayOfdouble[0];
        this.m11 = paramArrayOfdouble[1];
        this.m21 = paramArrayOfdouble[2];
        return;
      case 2:
        this.m02 = paramArrayOfdouble[0];
        this.m12 = paramArrayOfdouble[1];
        this.m22 = paramArrayOfdouble[2];
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
  }
  
  public final double getScale() {
    double[] arrayOfDouble1 = new double[3];
    double[] arrayOfDouble2 = new double[9];
    getScaleRotate(arrayOfDouble1, arrayOfDouble2);
    return max3(arrayOfDouble1);
  }
  
  public final void add(double paramDouble) {
    this.m00 += paramDouble;
    this.m01 += paramDouble;
    this.m02 += paramDouble;
    this.m10 += paramDouble;
    this.m11 += paramDouble;
    this.m12 += paramDouble;
    this.m20 += paramDouble;
    this.m21 += paramDouble;
    this.m22 += paramDouble;
  }
  
  public final void add(double paramDouble, Matrix3d paramMatrix3d) {
    paramMatrix3d.m00 += paramDouble;
    paramMatrix3d.m01 += paramDouble;
    paramMatrix3d.m02 += paramDouble;
    paramMatrix3d.m10 += paramDouble;
    paramMatrix3d.m11 += paramDouble;
    paramMatrix3d.m12 += paramDouble;
    paramMatrix3d.m20 += paramDouble;
    paramMatrix3d.m21 += paramDouble;
    paramMatrix3d.m22 += paramDouble;
  }
  
  public final void add(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    paramMatrix3d1.m00 += paramMatrix3d2.m00;
    paramMatrix3d1.m01 += paramMatrix3d2.m01;
    paramMatrix3d1.m02 += paramMatrix3d2.m02;
    paramMatrix3d1.m10 += paramMatrix3d2.m10;
    paramMatrix3d1.m11 += paramMatrix3d2.m11;
    paramMatrix3d1.m12 += paramMatrix3d2.m12;
    paramMatrix3d1.m20 += paramMatrix3d2.m20;
    paramMatrix3d1.m21 += paramMatrix3d2.m21;
    paramMatrix3d1.m22 += paramMatrix3d2.m22;
  }
  
  public final void add(Matrix3d paramMatrix3d) {
    this.m00 += paramMatrix3d.m00;
    this.m01 += paramMatrix3d.m01;
    this.m02 += paramMatrix3d.m02;
    this.m10 += paramMatrix3d.m10;
    this.m11 += paramMatrix3d.m11;
    this.m12 += paramMatrix3d.m12;
    this.m20 += paramMatrix3d.m20;
    this.m21 += paramMatrix3d.m21;
    this.m22 += paramMatrix3d.m22;
  }
  
  public final void sub(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    paramMatrix3d1.m00 -= paramMatrix3d2.m00;
    paramMatrix3d1.m01 -= paramMatrix3d2.m01;
    paramMatrix3d1.m02 -= paramMatrix3d2.m02;
    paramMatrix3d1.m10 -= paramMatrix3d2.m10;
    paramMatrix3d1.m11 -= paramMatrix3d2.m11;
    paramMatrix3d1.m12 -= paramMatrix3d2.m12;
    paramMatrix3d1.m20 -= paramMatrix3d2.m20;
    paramMatrix3d1.m21 -= paramMatrix3d2.m21;
    paramMatrix3d1.m22 -= paramMatrix3d2.m22;
  }
  
  public final void sub(Matrix3d paramMatrix3d) {
    this.m00 -= paramMatrix3d.m00;
    this.m01 -= paramMatrix3d.m01;
    this.m02 -= paramMatrix3d.m02;
    this.m10 -= paramMatrix3d.m10;
    this.m11 -= paramMatrix3d.m11;
    this.m12 -= paramMatrix3d.m12;
    this.m20 -= paramMatrix3d.m20;
    this.m21 -= paramMatrix3d.m21;
    this.m22 -= paramMatrix3d.m22;
  }
  
  public final void transpose() {
    double d = this.m10;
    this.m10 = this.m01;
    this.m01 = d;
    d = this.m20;
    this.m20 = this.m02;
    this.m02 = d;
    d = this.m21;
    this.m21 = this.m12;
    this.m12 = d;
  }
  
  public final void transpose(Matrix3d paramMatrix3d) {
    if (this != paramMatrix3d) {
      this.m00 = paramMatrix3d.m00;
      this.m01 = paramMatrix3d.m10;
      this.m02 = paramMatrix3d.m20;
      this.m10 = paramMatrix3d.m01;
      this.m11 = paramMatrix3d.m11;
      this.m12 = paramMatrix3d.m21;
      this.m20 = paramMatrix3d.m02;
      this.m21 = paramMatrix3d.m12;
      this.m22 = paramMatrix3d.m22;
    } else {
      transpose();
    } 
  }
  
  public final void set(Quat4d paramQuat4d) {
    this.m00 = 1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z;
    this.m10 = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
    this.m20 = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
    this.m01 = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
    this.m11 = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z;
    this.m21 = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
    this.m02 = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
    this.m12 = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
    this.m22 = 1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y;
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d) {
    double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
    if (d < 1.110223024E-16D) {
      this.m00 = 1.0D;
      this.m01 = 0.0D;
      this.m02 = 0.0D;
      this.m10 = 0.0D;
      this.m11 = 1.0D;
      this.m12 = 0.0D;
      this.m20 = 0.0D;
      this.m21 = 0.0D;
      this.m22 = 1.0D;
    } else {
      d = 1.0D / d;
      double d1 = paramAxisAngle4d.x * d;
      double d2 = paramAxisAngle4d.y * d;
      double d3 = paramAxisAngle4d.z * d;
      double d4 = Math.sin(paramAxisAngle4d.angle);
      double d5 = Math.cos(paramAxisAngle4d.angle);
      double d6 = 1.0D - d5;
      double d7 = paramAxisAngle4d.x * paramAxisAngle4d.z;
      double d8 = paramAxisAngle4d.x * paramAxisAngle4d.y;
      double d9 = paramAxisAngle4d.y * paramAxisAngle4d.z;
      this.m00 = d6 * d1 * d1 + d5;
      this.m01 = d6 * d8 - d4 * d3;
      this.m02 = d6 * d7 + d4 * d2;
      this.m10 = d6 * d8 + d4 * d3;
      this.m11 = d6 * d2 * d2 + d5;
      this.m12 = d6 * d9 - d4 * d1;
      this.m20 = d6 * d7 - d4 * d2;
      this.m21 = d6 * d9 + d4 * d1;
      this.m22 = d6 * d3 * d3 + d5;
    } 
  }
  
  public final void set(Quat4f paramQuat4f) {
    this.m00 = 1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z;
    this.m10 = 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
    this.m20 = 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
    this.m01 = 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
    this.m11 = 1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z;
    this.m21 = 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
    this.m02 = 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
    this.m12 = 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
    this.m22 = 1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f) {
    double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
    if (d < 1.110223024E-16D) {
      this.m00 = 1.0D;
      this.m01 = 0.0D;
      this.m02 = 0.0D;
      this.m10 = 0.0D;
      this.m11 = 1.0D;
      this.m12 = 0.0D;
      this.m20 = 0.0D;
      this.m21 = 0.0D;
      this.m22 = 1.0D;
    } else {
      d = 1.0D / d;
      double d1 = paramAxisAngle4f.x * d;
      double d2 = paramAxisAngle4f.y * d;
      double d3 = paramAxisAngle4f.z * d;
      double d4 = Math.sin(paramAxisAngle4f.angle);
      double d5 = Math.cos(paramAxisAngle4f.angle);
      double d6 = 1.0D - d5;
      double d7 = d1 * d3;
      double d8 = d1 * d2;
      double d9 = d2 * d3;
      this.m00 = d6 * d1 * d1 + d5;
      this.m01 = d6 * d8 - d4 * d3;
      this.m02 = d6 * d7 + d4 * d2;
      this.m10 = d6 * d8 + d4 * d3;
      this.m11 = d6 * d2 * d2 + d5;
      this.m12 = d6 * d9 - d4 * d1;
      this.m20 = d6 * d7 - d4 * d2;
      this.m21 = d6 * d9 + d4 * d1;
      this.m22 = d6 * d3 * d3 + d5;
    } 
  }
  
  public final void set(Matrix3f paramMatrix3f) {
    this.m00 = paramMatrix3f.m00;
    this.m01 = paramMatrix3f.m01;
    this.m02 = paramMatrix3f.m02;
    this.m10 = paramMatrix3f.m10;
    this.m11 = paramMatrix3f.m11;
    this.m12 = paramMatrix3f.m12;
    this.m20 = paramMatrix3f.m20;
    this.m21 = paramMatrix3f.m21;
    this.m22 = paramMatrix3f.m22;
  }
  
  public final void set(Matrix3d paramMatrix3d) {
    this.m00 = paramMatrix3d.m00;
    this.m01 = paramMatrix3d.m01;
    this.m02 = paramMatrix3d.m02;
    this.m10 = paramMatrix3d.m10;
    this.m11 = paramMatrix3d.m11;
    this.m12 = paramMatrix3d.m12;
    this.m20 = paramMatrix3d.m20;
    this.m21 = paramMatrix3d.m21;
    this.m22 = paramMatrix3d.m22;
  }
  
  public final void set(double[] paramArrayOfdouble) {
    this.m00 = paramArrayOfdouble[0];
    this.m01 = paramArrayOfdouble[1];
    this.m02 = paramArrayOfdouble[2];
    this.m10 = paramArrayOfdouble[3];
    this.m11 = paramArrayOfdouble[4];
    this.m12 = paramArrayOfdouble[5];
    this.m20 = paramArrayOfdouble[6];
    this.m21 = paramArrayOfdouble[7];
    this.m22 = paramArrayOfdouble[8];
  }
  
  public final void invert(Matrix3d paramMatrix3d) {
    invertGeneral(paramMatrix3d);
  }
  
  public final void invert() {
    invertGeneral(this);
  }
  
  private final void invertGeneral(Matrix3d paramMatrix3d) {
    double[] arrayOfDouble1 = new double[9];
    int[] arrayOfInt = new int[3];
    double[] arrayOfDouble2 = new double[9];
    arrayOfDouble2[0] = paramMatrix3d.m00;
    arrayOfDouble2[1] = paramMatrix3d.m01;
    arrayOfDouble2[2] = paramMatrix3d.m02;
    arrayOfDouble2[3] = paramMatrix3d.m10;
    arrayOfDouble2[4] = paramMatrix3d.m11;
    arrayOfDouble2[5] = paramMatrix3d.m12;
    arrayOfDouble2[6] = paramMatrix3d.m20;
    arrayOfDouble2[7] = paramMatrix3d.m21;
    arrayOfDouble2[8] = paramMatrix3d.m22;
    if (!luDecomposition(arrayOfDouble2, arrayOfInt))
      throw new SingularMatrixException(VecMathI18N.getString("Matrix3d12")); 
    for (byte b = 0; b < 9; b++)
      arrayOfDouble1[b] = 0.0D; 
    arrayOfDouble1[0] = 1.0D;
    arrayOfDouble1[4] = 1.0D;
    arrayOfDouble1[8] = 1.0D;
    luBacksubstitution(arrayOfDouble2, arrayOfInt, arrayOfDouble1);
    this.m00 = arrayOfDouble1[0];
    this.m01 = arrayOfDouble1[1];
    this.m02 = arrayOfDouble1[2];
    this.m10 = arrayOfDouble1[3];
    this.m11 = arrayOfDouble1[4];
    this.m12 = arrayOfDouble1[5];
    this.m20 = arrayOfDouble1[6];
    this.m21 = arrayOfDouble1[7];
    this.m22 = arrayOfDouble1[8];
  }
  
  static boolean luDecomposition(double[] paramArrayOfdouble, int[] paramArrayOfint) {
    double[] arrayOfDouble = new double[3];
    int j = 0;
    int k = 0;
    int i = 3;
    while (i-- != 0) {
      double d = 0.0D;
      int m = 3;
      while (m-- != 0) {
        double d1 = paramArrayOfdouble[j++];
        d1 = Math.abs(d1);
        if (d1 > d)
          d = d1; 
      } 
      if (d == 0.0D)
        return false; 
      arrayOfDouble[k++] = 1.0D / d;
    } 
    byte b = 0;
    for (i = 0; i < 3; i++) {
      for (j = 0; j < i; j++) {
        int n = b + 3 * j + i;
        double d1 = paramArrayOfdouble[n];
        int m = j;
        int i1 = b + 3 * j;
        for (int i2 = b + i; m-- != 0; i2 += 3) {
          d1 -= paramArrayOfdouble[i1] * paramArrayOfdouble[i2];
          i1++;
        } 
        paramArrayOfdouble[n] = d1;
      } 
      double d = 0.0D;
      k = -1;
      for (j = i; j < 3; j++) {
        int n = b + 3 * j + i;
        double d1 = paramArrayOfdouble[n];
        int m = i;
        int i1 = b + 3 * j;
        for (int i2 = b + i; m-- != 0; i2 += 3) {
          d1 -= paramArrayOfdouble[i1] * paramArrayOfdouble[i2];
          i1++;
        } 
        paramArrayOfdouble[n] = d1;
        double d2;
        if ((d2 = arrayOfDouble[j] * Math.abs(d1)) >= d) {
          d = d2;
          k = j;
        } 
      } 
      if (k < 0)
        throw new RuntimeException(VecMathI18N.getString("Matrix3d13")); 
      if (i != k) {
        int m = 3;
        int n = b + 3 * k;
        int i1 = b + 3 * i;
        while (m-- != 0) {
          double d1 = paramArrayOfdouble[n];
          paramArrayOfdouble[n++] = paramArrayOfdouble[i1];
          paramArrayOfdouble[i1++] = d1;
        } 
        arrayOfDouble[k] = arrayOfDouble[i];
      } 
      paramArrayOfint[i] = k;
      if (paramArrayOfdouble[b + 3 * i + i] == 0.0D)
        return false; 
      if (i != 2) {
        double d1 = 1.0D / paramArrayOfdouble[b + 3 * i + i];
        int m = b + 3 * (i + 1) + i;
        j = 2 - i;
        while (j-- != 0) {
          paramArrayOfdouble[m] = paramArrayOfdouble[m] * d1;
          m += 3;
        } 
      } 
    } 
    return true;
  }
  
  static void luBacksubstitution(double[] paramArrayOfdouble1, int[] paramArrayOfint, double[] paramArrayOfdouble2) {
    byte b2 = 0;
    for (byte b1 = 0; b1 < 3; b1++) {
      byte b4 = b1;
      byte b = -1;
      for (byte b3 = 0; b3 < 3; b3++) {
        int i = paramArrayOfint[b2 + b3];
        double d = paramArrayOfdouble2[b4 + 3 * i];
        paramArrayOfdouble2[b4 + 3 * i] = paramArrayOfdouble2[b4 + 3 * b3];
        if (b >= 0) {
          int j = b3 * 3;
          for (byte b6 = b; b6 <= b3 - 1; b6++)
            d -= paramArrayOfdouble1[j + b6] * paramArrayOfdouble2[b4 + 3 * b6]; 
        } else if (d != 0.0D) {
          b = b3;
        } 
        paramArrayOfdouble2[b4 + 3 * b3] = d;
      } 
      byte b5 = 6;
      paramArrayOfdouble2[b4 + 6] = paramArrayOfdouble2[b4 + 6] / paramArrayOfdouble1[b5 + 2];
      b5 -= 3;
      paramArrayOfdouble2[b4 + 3] = (paramArrayOfdouble2[b4 + 3] - paramArrayOfdouble1[b5 + 2] * paramArrayOfdouble2[b4 + 6]) / paramArrayOfdouble1[b5 + 1];
      b5 -= 3;
      paramArrayOfdouble2[b4 + 0] = (paramArrayOfdouble2[b4 + 0] - paramArrayOfdouble1[b5 + 1] * paramArrayOfdouble2[b4 + 3] - paramArrayOfdouble1[b5 + 2] * paramArrayOfdouble2[b4 + 6]) / paramArrayOfdouble1[b5 + 0];
    } 
  }
  
  public final double determinant() {
    return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
  }
  
  public final void set(double paramDouble) {
    this.m00 = paramDouble;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = paramDouble;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = paramDouble;
  }
  
  public final void rotX(double paramDouble) {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = d2;
    this.m12 = -d1;
    this.m20 = 0.0D;
    this.m21 = d1;
    this.m22 = d2;
  }
  
  public final void rotY(double paramDouble) {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = d2;
    this.m01 = 0.0D;
    this.m02 = d1;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m20 = -d1;
    this.m21 = 0.0D;
    this.m22 = d2;
  }
  
  public final void rotZ(double paramDouble) {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = d2;
    this.m01 = -d1;
    this.m02 = 0.0D;
    this.m10 = d1;
    this.m11 = d2;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
  }
  
  public final void mul(double paramDouble) {
    this.m00 *= paramDouble;
    this.m01 *= paramDouble;
    this.m02 *= paramDouble;
    this.m10 *= paramDouble;
    this.m11 *= paramDouble;
    this.m12 *= paramDouble;
    this.m20 *= paramDouble;
    this.m21 *= paramDouble;
    this.m22 *= paramDouble;
  }
  
  public final void mul(double paramDouble, Matrix3d paramMatrix3d) {
    this.m00 = paramDouble * paramMatrix3d.m00;
    this.m01 = paramDouble * paramMatrix3d.m01;
    this.m02 = paramDouble * paramMatrix3d.m02;
    this.m10 = paramDouble * paramMatrix3d.m10;
    this.m11 = paramDouble * paramMatrix3d.m11;
    this.m12 = paramDouble * paramMatrix3d.m12;
    this.m20 = paramDouble * paramMatrix3d.m20;
    this.m21 = paramDouble * paramMatrix3d.m21;
    this.m22 = paramDouble * paramMatrix3d.m22;
  }
  
  public final void mul(Matrix3d paramMatrix3d) {
    double d1 = this.m00 * paramMatrix3d.m00 + this.m01 * paramMatrix3d.m10 + this.m02 * paramMatrix3d.m20;
    double d2 = this.m00 * paramMatrix3d.m01 + this.m01 * paramMatrix3d.m11 + this.m02 * paramMatrix3d.m21;
    double d3 = this.m00 * paramMatrix3d.m02 + this.m01 * paramMatrix3d.m12 + this.m02 * paramMatrix3d.m22;
    double d4 = this.m10 * paramMatrix3d.m00 + this.m11 * paramMatrix3d.m10 + this.m12 * paramMatrix3d.m20;
    double d5 = this.m10 * paramMatrix3d.m01 + this.m11 * paramMatrix3d.m11 + this.m12 * paramMatrix3d.m21;
    double d6 = this.m10 * paramMatrix3d.m02 + this.m11 * paramMatrix3d.m12 + this.m12 * paramMatrix3d.m22;
    double d7 = this.m20 * paramMatrix3d.m00 + this.m21 * paramMatrix3d.m10 + this.m22 * paramMatrix3d.m20;
    double d8 = this.m20 * paramMatrix3d.m01 + this.m21 * paramMatrix3d.m11 + this.m22 * paramMatrix3d.m21;
    double d9 = this.m20 * paramMatrix3d.m02 + this.m21 * paramMatrix3d.m12 + this.m22 * paramMatrix3d.m22;
    this.m00 = d1;
    this.m01 = d2;
    this.m02 = d3;
    this.m10 = d4;
    this.m11 = d5;
    this.m12 = d6;
    this.m20 = d7;
    this.m21 = d8;
    this.m22 = d9;
  }
  
  public final void mul(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    if (this != paramMatrix3d1 && this != paramMatrix3d2) {
      this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
      this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
      this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
      this.m10 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
      this.m11 = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
      this.m12 = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
      this.m20 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
      this.m21 = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
      this.m22 = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
    } else {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
      double d5 = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
      double d6 = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
      double d8 = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
      double d9 = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    } 
  }
  
  public final void mulNormalize(Matrix3d paramMatrix3d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = this.m00 * paramMatrix3d.m00 + this.m01 * paramMatrix3d.m10 + this.m02 * paramMatrix3d.m20;
    arrayOfDouble1[1] = this.m00 * paramMatrix3d.m01 + this.m01 * paramMatrix3d.m11 + this.m02 * paramMatrix3d.m21;
    arrayOfDouble1[2] = this.m00 * paramMatrix3d.m02 + this.m01 * paramMatrix3d.m12 + this.m02 * paramMatrix3d.m22;
    arrayOfDouble1[3] = this.m10 * paramMatrix3d.m00 + this.m11 * paramMatrix3d.m10 + this.m12 * paramMatrix3d.m20;
    arrayOfDouble1[4] = this.m10 * paramMatrix3d.m01 + this.m11 * paramMatrix3d.m11 + this.m12 * paramMatrix3d.m21;
    arrayOfDouble1[5] = this.m10 * paramMatrix3d.m02 + this.m11 * paramMatrix3d.m12 + this.m12 * paramMatrix3d.m22;
    arrayOfDouble1[6] = this.m20 * paramMatrix3d.m00 + this.m21 * paramMatrix3d.m10 + this.m22 * paramMatrix3d.m20;
    arrayOfDouble1[7] = this.m20 * paramMatrix3d.m01 + this.m21 * paramMatrix3d.m11 + this.m22 * paramMatrix3d.m21;
    arrayOfDouble1[8] = this.m20 * paramMatrix3d.m02 + this.m21 * paramMatrix3d.m12 + this.m22 * paramMatrix3d.m22;
    compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = arrayOfDouble2[0];
    this.m01 = arrayOfDouble2[1];
    this.m02 = arrayOfDouble2[2];
    this.m10 = arrayOfDouble2[3];
    this.m11 = arrayOfDouble2[4];
    this.m12 = arrayOfDouble2[5];
    this.m20 = arrayOfDouble2[6];
    this.m21 = arrayOfDouble2[7];
    this.m22 = arrayOfDouble2[8];
  }
  
  public final void mulNormalize(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
    arrayOfDouble1[1] = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
    arrayOfDouble1[2] = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
    arrayOfDouble1[3] = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
    arrayOfDouble1[4] = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
    arrayOfDouble1[5] = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
    arrayOfDouble1[6] = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
    arrayOfDouble1[7] = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
    arrayOfDouble1[8] = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
    compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = arrayOfDouble2[0];
    this.m01 = arrayOfDouble2[1];
    this.m02 = arrayOfDouble2[2];
    this.m10 = arrayOfDouble2[3];
    this.m11 = arrayOfDouble2[4];
    this.m12 = arrayOfDouble2[5];
    this.m20 = arrayOfDouble2[6];
    this.m21 = arrayOfDouble2[7];
    this.m22 = arrayOfDouble2[8];
  }
  
  public final void mulTransposeBoth(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    if (this != paramMatrix3d1 && this != paramMatrix3d2) {
      this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m20 * paramMatrix3d2.m02;
      this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m12;
      this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m10 * paramMatrix3d2.m21 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
      this.m10 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m02;
      this.m11 = paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m12;
      this.m12 = paramMatrix3d1.m01 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
      this.m20 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
      this.m21 = paramMatrix3d1.m02 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
      this.m22 = paramMatrix3d1.m02 * paramMatrix3d2.m20 + paramMatrix3d1.m12 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
    } else {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m20 * paramMatrix3d2.m02;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m12;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m10 * paramMatrix3d2.m21 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m02;
      double d5 = paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m12;
      double d6 = paramMatrix3d1.m01 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
      double d8 = paramMatrix3d1.m02 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
      double d9 = paramMatrix3d1.m02 * paramMatrix3d2.m20 + paramMatrix3d1.m12 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    } 
  }
  
  public final void mulTransposeRight(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    if (this != paramMatrix3d1 && this != paramMatrix3d2) {
      this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m02 * paramMatrix3d2.m02;
      this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m12;
      this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m01 * paramMatrix3d2.m21 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
      this.m10 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m02;
      this.m11 = paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m12;
      this.m12 = paramMatrix3d1.m10 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
      this.m20 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
      this.m21 = paramMatrix3d1.m20 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
      this.m22 = paramMatrix3d1.m20 * paramMatrix3d2.m20 + paramMatrix3d1.m21 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
    } else {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m02 * paramMatrix3d2.m02;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m12;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m01 * paramMatrix3d2.m21 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m02;
      double d5 = paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m12;
      double d6 = paramMatrix3d1.m10 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
      double d8 = paramMatrix3d1.m20 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
      double d9 = paramMatrix3d1.m20 * paramMatrix3d2.m20 + paramMatrix3d1.m21 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    } 
  }
  
  public final void mulTransposeLeft(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2) {
    if (this != paramMatrix3d1 && this != paramMatrix3d2) {
      this.m00 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m20 * paramMatrix3d2.m20;
      this.m01 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m21;
      this.m02 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m10 * paramMatrix3d2.m12 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
      this.m10 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m20;
      this.m11 = paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m21;
      this.m12 = paramMatrix3d1.m01 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
      this.m20 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
      this.m21 = paramMatrix3d1.m02 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
      this.m22 = paramMatrix3d1.m02 * paramMatrix3d2.m02 + paramMatrix3d1.m12 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
    } else {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m20 * paramMatrix3d2.m20;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m21;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m10 * paramMatrix3d2.m12 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m20;
      double d5 = paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m21;
      double d6 = paramMatrix3d1.m01 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
      double d8 = paramMatrix3d1.m02 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
      double d9 = paramMatrix3d1.m02 * paramMatrix3d2.m02 + paramMatrix3d1.m12 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    } 
  }
  
  public final void normalize() {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = arrayOfDouble1[0];
    this.m01 = arrayOfDouble1[1];
    this.m02 = arrayOfDouble1[2];
    this.m10 = arrayOfDouble1[3];
    this.m11 = arrayOfDouble1[4];
    this.m12 = arrayOfDouble1[5];
    this.m20 = arrayOfDouble1[6];
    this.m21 = arrayOfDouble1[7];
    this.m22 = arrayOfDouble1[8];
  }
  
  public final void normalize(Matrix3d paramMatrix3d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = paramMatrix3d.m00;
    arrayOfDouble1[1] = paramMatrix3d.m01;
    arrayOfDouble1[2] = paramMatrix3d.m02;
    arrayOfDouble1[3] = paramMatrix3d.m10;
    arrayOfDouble1[4] = paramMatrix3d.m11;
    arrayOfDouble1[5] = paramMatrix3d.m12;
    arrayOfDouble1[6] = paramMatrix3d.m20;
    arrayOfDouble1[7] = paramMatrix3d.m21;
    arrayOfDouble1[8] = paramMatrix3d.m22;
    compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = arrayOfDouble2[0];
    this.m01 = arrayOfDouble2[1];
    this.m02 = arrayOfDouble2[2];
    this.m10 = arrayOfDouble2[3];
    this.m11 = arrayOfDouble2[4];
    this.m12 = arrayOfDouble2[5];
    this.m20 = arrayOfDouble2[6];
    this.m21 = arrayOfDouble2[7];
    this.m22 = arrayOfDouble2[8];
  }
  
  public final void normalizeCP() {
    double d = 1.0D / Math.sqrt(this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20);
    this.m00 *= d;
    this.m10 *= d;
    this.m20 *= d;
    d = 1.0D / Math.sqrt(this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21);
    this.m01 *= d;
    this.m11 *= d;
    this.m21 *= d;
    this.m02 = this.m10 * this.m21 - this.m11 * this.m20;
    this.m12 = this.m01 * this.m20 - this.m00 * this.m21;
    this.m22 = this.m00 * this.m11 - this.m01 * this.m10;
  }
  
  public final void normalizeCP(Matrix3d paramMatrix3d) {
    double d = 1.0D / Math.sqrt(paramMatrix3d.m00 * paramMatrix3d.m00 + paramMatrix3d.m10 * paramMatrix3d.m10 + paramMatrix3d.m20 * paramMatrix3d.m20);
    paramMatrix3d.m00 *= d;
    paramMatrix3d.m10 *= d;
    paramMatrix3d.m20 *= d;
    d = 1.0D / Math.sqrt(paramMatrix3d.m01 * paramMatrix3d.m01 + paramMatrix3d.m11 * paramMatrix3d.m11 + paramMatrix3d.m21 * paramMatrix3d.m21);
    paramMatrix3d.m01 *= d;
    paramMatrix3d.m11 *= d;
    paramMatrix3d.m21 *= d;
    this.m02 = this.m10 * this.m21 - this.m11 * this.m20;
    this.m12 = this.m01 * this.m20 - this.m00 * this.m21;
    this.m22 = this.m00 * this.m11 - this.m01 * this.m10;
  }
  
  public boolean equals(Matrix3d paramMatrix3d) {
    try {
      return (this.m00 == paramMatrix3d.m00 && this.m01 == paramMatrix3d.m01 && this.m02 == paramMatrix3d.m02 && this.m10 == paramMatrix3d.m10 && this.m11 == paramMatrix3d.m11 && this.m12 == paramMatrix3d.m12 && this.m20 == paramMatrix3d.m20 && this.m21 == paramMatrix3d.m21 && this.m22 == paramMatrix3d.m22);
    } catch (NullPointerException nullPointerException) {
      return false;
    } 
  }
  
  public boolean equals(Object paramObject) {
    try {
      Matrix3d matrix3d = (Matrix3d)paramObject;
      return (this.m00 == matrix3d.m00 && this.m01 == matrix3d.m01 && this.m02 == matrix3d.m02 && this.m10 == matrix3d.m10 && this.m11 == matrix3d.m11 && this.m12 == matrix3d.m12 && this.m20 == matrix3d.m20 && this.m21 == matrix3d.m21 && this.m22 == matrix3d.m22);
    } catch (ClassCastException classCastException) {
      return false;
    } catch (NullPointerException nullPointerException) {
      return false;
    } 
  }
  
  public boolean epsilonEquals(Matrix3d paramMatrix3d, double paramDouble) {
    double d = this.m00 - paramMatrix3d.m00;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m01 - paramMatrix3d.m01;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m02 - paramMatrix3d.m02;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m10 - paramMatrix3d.m10;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m11 - paramMatrix3d.m11;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m12 - paramMatrix3d.m12;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m20 - paramMatrix3d.m20;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m21 - paramMatrix3d.m21;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m22 - paramMatrix3d.m22;
    return !(((d < 0.0D) ? -d : d) > paramDouble);
  }
  
  public int hashCode() {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m00);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m01);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m02);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m10);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m11);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m12);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m20);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m21);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m22);
    return (int)(l ^ l >> 32L);
  }
  
  public final void setZero() {
    this.m00 = 0.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 0.0D;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 0.0D;
  }
  
  public final void negate() {
    this.m00 = -this.m00;
    this.m01 = -this.m01;
    this.m02 = -this.m02;
    this.m10 = -this.m10;
    this.m11 = -this.m11;
    this.m12 = -this.m12;
    this.m20 = -this.m20;
    this.m21 = -this.m21;
    this.m22 = -this.m22;
  }
  
  public final void negate(Matrix3d paramMatrix3d) {
    this.m00 = -paramMatrix3d.m00;
    this.m01 = -paramMatrix3d.m01;
    this.m02 = -paramMatrix3d.m02;
    this.m10 = -paramMatrix3d.m10;
    this.m11 = -paramMatrix3d.m11;
    this.m12 = -paramMatrix3d.m12;
    this.m20 = -paramMatrix3d.m20;
    this.m21 = -paramMatrix3d.m21;
    this.m22 = -paramMatrix3d.m22;
  }
  
  public final void transform(Tuple3d paramTuple3d) {
    double d1 = this.m00 * paramTuple3d.x + this.m01 * paramTuple3d.y + this.m02 * paramTuple3d.z;
    double d2 = this.m10 * paramTuple3d.x + this.m11 * paramTuple3d.y + this.m12 * paramTuple3d.z;
    double d3 = this.m20 * paramTuple3d.x + this.m21 * paramTuple3d.y + this.m22 * paramTuple3d.z;
    paramTuple3d.set(d1, d2, d3);
  }
  
  public final void transform(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2) {
    double d1 = this.m00 * paramTuple3d1.x + this.m01 * paramTuple3d1.y + this.m02 * paramTuple3d1.z;
    double d2 = this.m10 * paramTuple3d1.x + this.m11 * paramTuple3d1.y + this.m12 * paramTuple3d1.z;
    paramTuple3d2.z = this.m20 * paramTuple3d1.x + this.m21 * paramTuple3d1.y + this.m22 * paramTuple3d1.z;
    paramTuple3d2.x = d1;
    paramTuple3d2.y = d2;
  }
  
  final void getScaleRotate(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2) {
    double[] arrayOfDouble = new double[9];
    arrayOfDouble[0] = this.m00;
    arrayOfDouble[1] = this.m01;
    arrayOfDouble[2] = this.m02;
    arrayOfDouble[3] = this.m10;
    arrayOfDouble[4] = this.m11;
    arrayOfDouble[5] = this.m12;
    arrayOfDouble[6] = this.m20;
    arrayOfDouble[7] = this.m21;
    arrayOfDouble[8] = this.m22;
    compute_svd(arrayOfDouble, paramArrayOfdouble1, paramArrayOfdouble2);
  }
  
  static void compute_svd(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[9];
    double[] arrayOfDouble4 = new double[9];
    double[] arrayOfDouble5 = arrayOfDouble3;
    double[] arrayOfDouble6 = arrayOfDouble4;
    double[] arrayOfDouble7 = new double[9];
    double[] arrayOfDouble8 = new double[3];
    double[] arrayOfDouble9 = new double[3];
    byte b2 = 0;
    byte b1;
    for (b1 = 0; b1 < 9; b1++)
      arrayOfDouble7[b1] = paramArrayOfdouble1[b1]; 
    if (paramArrayOfdouble1[3] * paramArrayOfdouble1[3] < 1.110223024E-16D) {
      arrayOfDouble1[0] = 1.0D;
      arrayOfDouble1[1] = 0.0D;
      arrayOfDouble1[2] = 0.0D;
      arrayOfDouble1[3] = 0.0D;
      arrayOfDouble1[4] = 1.0D;
      arrayOfDouble1[5] = 0.0D;
      arrayOfDouble1[6] = 0.0D;
      arrayOfDouble1[7] = 0.0D;
      arrayOfDouble1[8] = 1.0D;
    } else if (paramArrayOfdouble1[0] * paramArrayOfdouble1[0] < 1.110223024E-16D) {
      arrayOfDouble5[0] = paramArrayOfdouble1[0];
      arrayOfDouble5[1] = paramArrayOfdouble1[1];
      arrayOfDouble5[2] = paramArrayOfdouble1[2];
      paramArrayOfdouble1[0] = paramArrayOfdouble1[3];
      paramArrayOfdouble1[1] = paramArrayOfdouble1[4];
      paramArrayOfdouble1[2] = paramArrayOfdouble1[5];
      paramArrayOfdouble1[3] = -arrayOfDouble5[0];
      paramArrayOfdouble1[4] = -arrayOfDouble5[1];
      paramArrayOfdouble1[5] = -arrayOfDouble5[2];
      arrayOfDouble1[0] = 0.0D;
      arrayOfDouble1[1] = 1.0D;
      arrayOfDouble1[2] = 0.0D;
      arrayOfDouble1[3] = -1.0D;
      arrayOfDouble1[4] = 0.0D;
      arrayOfDouble1[5] = 0.0D;
      arrayOfDouble1[6] = 0.0D;
      arrayOfDouble1[7] = 0.0D;
      arrayOfDouble1[8] = 1.0D;
    } else {
      double d1 = 1.0D / Math.sqrt(paramArrayOfdouble1[0] * paramArrayOfdouble1[0] + paramArrayOfdouble1[3] * paramArrayOfdouble1[3]);
      double d2 = paramArrayOfdouble1[0] * d1;
      double d3 = paramArrayOfdouble1[3] * d1;
      arrayOfDouble5[0] = d2 * paramArrayOfdouble1[0] + d3 * paramArrayOfdouble1[3];
      arrayOfDouble5[1] = d2 * paramArrayOfdouble1[1] + d3 * paramArrayOfdouble1[4];
      arrayOfDouble5[2] = d2 * paramArrayOfdouble1[2] + d3 * paramArrayOfdouble1[5];
      paramArrayOfdouble1[3] = -d3 * paramArrayOfdouble1[0] + d2 * paramArrayOfdouble1[3];
      paramArrayOfdouble1[4] = -d3 * paramArrayOfdouble1[1] + d2 * paramArrayOfdouble1[4];
      paramArrayOfdouble1[5] = -d3 * paramArrayOfdouble1[2] + d2 * paramArrayOfdouble1[5];
      paramArrayOfdouble1[0] = arrayOfDouble5[0];
      paramArrayOfdouble1[1] = arrayOfDouble5[1];
      paramArrayOfdouble1[2] = arrayOfDouble5[2];
      arrayOfDouble1[0] = d2;
      arrayOfDouble1[1] = d3;
      arrayOfDouble1[2] = 0.0D;
      arrayOfDouble1[3] = -d3;
      arrayOfDouble1[4] = d2;
      arrayOfDouble1[5] = 0.0D;
      arrayOfDouble1[6] = 0.0D;
      arrayOfDouble1[7] = 0.0D;
      arrayOfDouble1[8] = 1.0D;
    } 
    if (paramArrayOfdouble1[6] * paramArrayOfdouble1[6] >= 1.110223024E-16D)
      if (paramArrayOfdouble1[0] * paramArrayOfdouble1[0] < 1.110223024E-16D) {
        arrayOfDouble5[0] = paramArrayOfdouble1[0];
        arrayOfDouble5[1] = paramArrayOfdouble1[1];
        arrayOfDouble5[2] = paramArrayOfdouble1[2];
        paramArrayOfdouble1[0] = paramArrayOfdouble1[6];
        paramArrayOfdouble1[1] = paramArrayOfdouble1[7];
        paramArrayOfdouble1[2] = paramArrayOfdouble1[8];
        paramArrayOfdouble1[6] = -arrayOfDouble5[0];
        paramArrayOfdouble1[7] = -arrayOfDouble5[1];
        paramArrayOfdouble1[8] = -arrayOfDouble5[2];
        arrayOfDouble5[0] = arrayOfDouble1[0];
        arrayOfDouble5[1] = arrayOfDouble1[1];
        arrayOfDouble5[2] = arrayOfDouble1[2];
        arrayOfDouble1[0] = arrayOfDouble1[6];
        arrayOfDouble1[1] = arrayOfDouble1[7];
        arrayOfDouble1[2] = arrayOfDouble1[8];
        arrayOfDouble1[6] = -arrayOfDouble5[0];
        arrayOfDouble1[7] = -arrayOfDouble5[1];
        arrayOfDouble1[8] = -arrayOfDouble5[2];
      } else {
        double d1 = 1.0D / Math.sqrt(paramArrayOfdouble1[0] * paramArrayOfdouble1[0] + paramArrayOfdouble1[6] * paramArrayOfdouble1[6]);
        double d2 = paramArrayOfdouble1[0] * d1;
        double d3 = paramArrayOfdouble1[6] * d1;
        arrayOfDouble5[0] = d2 * paramArrayOfdouble1[0] + d3 * paramArrayOfdouble1[6];
        arrayOfDouble5[1] = d2 * paramArrayOfdouble1[1] + d3 * paramArrayOfdouble1[7];
        arrayOfDouble5[2] = d2 * paramArrayOfdouble1[2] + d3 * paramArrayOfdouble1[8];
        paramArrayOfdouble1[6] = -d3 * paramArrayOfdouble1[0] + d2 * paramArrayOfdouble1[6];
        paramArrayOfdouble1[7] = -d3 * paramArrayOfdouble1[1] + d2 * paramArrayOfdouble1[7];
        paramArrayOfdouble1[8] = -d3 * paramArrayOfdouble1[2] + d2 * paramArrayOfdouble1[8];
        paramArrayOfdouble1[0] = arrayOfDouble5[0];
        paramArrayOfdouble1[1] = arrayOfDouble5[1];
        paramArrayOfdouble1[2] = arrayOfDouble5[2];
        arrayOfDouble5[0] = d2 * arrayOfDouble1[0];
        arrayOfDouble5[1] = d2 * arrayOfDouble1[1];
        arrayOfDouble1[2] = d3;
        arrayOfDouble5[6] = -arrayOfDouble1[0] * d3;
        arrayOfDouble5[7] = -arrayOfDouble1[1] * d3;
        arrayOfDouble1[8] = d2;
        arrayOfDouble1[0] = arrayOfDouble5[0];
        arrayOfDouble1[1] = arrayOfDouble5[1];
        arrayOfDouble1[6] = arrayOfDouble5[6];
        arrayOfDouble1[7] = arrayOfDouble5[7];
      }  
    if (paramArrayOfdouble1[2] * paramArrayOfdouble1[2] < 1.110223024E-16D) {
      arrayOfDouble2[0] = 1.0D;
      arrayOfDouble2[1] = 0.0D;
      arrayOfDouble2[2] = 0.0D;
      arrayOfDouble2[3] = 0.0D;
      arrayOfDouble2[4] = 1.0D;
      arrayOfDouble2[5] = 0.0D;
      arrayOfDouble2[6] = 0.0D;
      arrayOfDouble2[7] = 0.0D;
      arrayOfDouble2[8] = 1.0D;
    } else if (paramArrayOfdouble1[1] * paramArrayOfdouble1[1] < 1.110223024E-16D) {
      arrayOfDouble5[2] = paramArrayOfdouble1[2];
      arrayOfDouble5[5] = paramArrayOfdouble1[5];
      arrayOfDouble5[8] = paramArrayOfdouble1[8];
      paramArrayOfdouble1[2] = -paramArrayOfdouble1[1];
      paramArrayOfdouble1[5] = -paramArrayOfdouble1[4];
      paramArrayOfdouble1[8] = -paramArrayOfdouble1[7];
      paramArrayOfdouble1[1] = arrayOfDouble5[2];
      paramArrayOfdouble1[4] = arrayOfDouble5[5];
      paramArrayOfdouble1[7] = arrayOfDouble5[8];
      arrayOfDouble2[0] = 1.0D;
      arrayOfDouble2[1] = 0.0D;
      arrayOfDouble2[2] = 0.0D;
      arrayOfDouble2[3] = 0.0D;
      arrayOfDouble2[4] = 0.0D;
      arrayOfDouble2[5] = -1.0D;
      arrayOfDouble2[6] = 0.0D;
      arrayOfDouble2[7] = 1.0D;
      arrayOfDouble2[8] = 0.0D;
    } else {
      double d1 = 1.0D / Math.sqrt(paramArrayOfdouble1[1] * paramArrayOfdouble1[1] + paramArrayOfdouble1[2] * paramArrayOfdouble1[2]);
      double d2 = paramArrayOfdouble1[1] * d1;
      double d3 = paramArrayOfdouble1[2] * d1;
      arrayOfDouble5[1] = d2 * paramArrayOfdouble1[1] + d3 * paramArrayOfdouble1[2];
      paramArrayOfdouble1[2] = -d3 * paramArrayOfdouble1[1] + d2 * paramArrayOfdouble1[2];
      paramArrayOfdouble1[1] = arrayOfDouble5[1];
      arrayOfDouble5[4] = d2 * paramArrayOfdouble1[4] + d3 * paramArrayOfdouble1[5];
      paramArrayOfdouble1[5] = -d3 * paramArrayOfdouble1[4] + d2 * paramArrayOfdouble1[5];
      paramArrayOfdouble1[4] = arrayOfDouble5[4];
      arrayOfDouble5[7] = d2 * paramArrayOfdouble1[7] + d3 * paramArrayOfdouble1[8];
      paramArrayOfdouble1[8] = -d3 * paramArrayOfdouble1[7] + d2 * paramArrayOfdouble1[8];
      paramArrayOfdouble1[7] = arrayOfDouble5[7];
      arrayOfDouble2[0] = 1.0D;
      arrayOfDouble2[1] = 0.0D;
      arrayOfDouble2[2] = 0.0D;
      arrayOfDouble2[3] = 0.0D;
      arrayOfDouble2[4] = d2;
      arrayOfDouble2[5] = -d3;
      arrayOfDouble2[6] = 0.0D;
      arrayOfDouble2[7] = d3;
      arrayOfDouble2[8] = d2;
    } 
    if (paramArrayOfdouble1[7] * paramArrayOfdouble1[7] >= 1.110223024E-16D)
      if (paramArrayOfdouble1[4] * paramArrayOfdouble1[4] < 1.110223024E-16D) {
        arrayOfDouble5[3] = paramArrayOfdouble1[3];
        arrayOfDouble5[4] = paramArrayOfdouble1[4];
        arrayOfDouble5[5] = paramArrayOfdouble1[5];
        paramArrayOfdouble1[3] = paramArrayOfdouble1[6];
        paramArrayOfdouble1[4] = paramArrayOfdouble1[7];
        paramArrayOfdouble1[5] = paramArrayOfdouble1[8];
        paramArrayOfdouble1[6] = -arrayOfDouble5[3];
        paramArrayOfdouble1[7] = -arrayOfDouble5[4];
        paramArrayOfdouble1[8] = -arrayOfDouble5[5];
        arrayOfDouble5[3] = arrayOfDouble1[3];
        arrayOfDouble5[4] = arrayOfDouble1[4];
        arrayOfDouble5[5] = arrayOfDouble1[5];
        arrayOfDouble1[3] = arrayOfDouble1[6];
        arrayOfDouble1[4] = arrayOfDouble1[7];
        arrayOfDouble1[5] = arrayOfDouble1[8];
        arrayOfDouble1[6] = -arrayOfDouble5[3];
        arrayOfDouble1[7] = -arrayOfDouble5[4];
        arrayOfDouble1[8] = -arrayOfDouble5[5];
      } else {
        double d1 = 1.0D / Math.sqrt(paramArrayOfdouble1[4] * paramArrayOfdouble1[4] + paramArrayOfdouble1[7] * paramArrayOfdouble1[7]);
        double d2 = paramArrayOfdouble1[4] * d1;
        double d3 = paramArrayOfdouble1[7] * d1;
        arrayOfDouble5[3] = d2 * paramArrayOfdouble1[3] + d3 * paramArrayOfdouble1[6];
        paramArrayOfdouble1[6] = -d3 * paramArrayOfdouble1[3] + d2 * paramArrayOfdouble1[6];
        paramArrayOfdouble1[3] = arrayOfDouble5[3];
        arrayOfDouble5[4] = d2 * paramArrayOfdouble1[4] + d3 * paramArrayOfdouble1[7];
        paramArrayOfdouble1[7] = -d3 * paramArrayOfdouble1[4] + d2 * paramArrayOfdouble1[7];
        paramArrayOfdouble1[4] = arrayOfDouble5[4];
        arrayOfDouble5[5] = d2 * paramArrayOfdouble1[5] + d3 * paramArrayOfdouble1[8];
        paramArrayOfdouble1[8] = -d3 * paramArrayOfdouble1[5] + d2 * paramArrayOfdouble1[8];
        paramArrayOfdouble1[5] = arrayOfDouble5[5];
        arrayOfDouble5[3] = d2 * arrayOfDouble1[3] + d3 * arrayOfDouble1[6];
        arrayOfDouble1[6] = -d3 * arrayOfDouble1[3] + d2 * arrayOfDouble1[6];
        arrayOfDouble1[3] = arrayOfDouble5[3];
        arrayOfDouble5[4] = d2 * arrayOfDouble1[4] + d3 * arrayOfDouble1[7];
        arrayOfDouble1[7] = -d3 * arrayOfDouble1[4] + d2 * arrayOfDouble1[7];
        arrayOfDouble1[4] = arrayOfDouble5[4];
        arrayOfDouble5[5] = d2 * arrayOfDouble1[5] + d3 * arrayOfDouble1[8];
        arrayOfDouble1[8] = -d3 * arrayOfDouble1[5] + d2 * arrayOfDouble1[8];
        arrayOfDouble1[5] = arrayOfDouble5[5];
      }  
    arrayOfDouble6[0] = paramArrayOfdouble1[0];
    arrayOfDouble6[1] = paramArrayOfdouble1[4];
    arrayOfDouble6[2] = paramArrayOfdouble1[8];
    arrayOfDouble8[0] = paramArrayOfdouble1[1];
    arrayOfDouble8[1] = paramArrayOfdouble1[5];
    if (arrayOfDouble8[0] * arrayOfDouble8[0] >= 1.110223024E-16D || arrayOfDouble8[1] * arrayOfDouble8[1] >= 1.110223024E-16D)
      compute_qr(arrayOfDouble6, arrayOfDouble8, arrayOfDouble1, arrayOfDouble2); 
    arrayOfDouble9[0] = arrayOfDouble6[0];
    arrayOfDouble9[1] = arrayOfDouble6[1];
    arrayOfDouble9[2] = arrayOfDouble6[2];
    if (almostEqual(Math.abs(arrayOfDouble9[0]), 1.0D) && almostEqual(Math.abs(arrayOfDouble9[1]), 1.0D) && almostEqual(Math.abs(arrayOfDouble9[2]), 1.0D)) {
      for (b1 = 0; b1 < 3; b1++) {
        if (arrayOfDouble9[b1] < 0.0D)
          b2++; 
      } 
      if (b2 == 0 || b2 == 2) {
        paramArrayOfdouble2[2] = 1.0D;
        paramArrayOfdouble2[1] = 1.0D;
        paramArrayOfdouble2[0] = 1.0D;
        for (b1 = 0; b1 < 9; b1++)
          paramArrayOfdouble3[b1] = arrayOfDouble7[b1]; 
        return;
      } 
    } 
    transpose_mat(arrayOfDouble1, arrayOfDouble3);
    transpose_mat(arrayOfDouble2, arrayOfDouble4);
    svdReorder(paramArrayOfdouble1, arrayOfDouble3, arrayOfDouble4, arrayOfDouble9, paramArrayOfdouble3, paramArrayOfdouble2);
  }
  
  static void svdReorder(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3, double[] paramArrayOfdouble4, double[] paramArrayOfdouble5, double[] paramArrayOfdouble6) {
    int[] arrayOfInt1 = new int[3];
    int[] arrayOfInt2 = new int[3];
    double[] arrayOfDouble1 = new double[3];
    double[] arrayOfDouble2 = new double[9];
    if (paramArrayOfdouble4[0] < 0.0D) {
      paramArrayOfdouble4[0] = -paramArrayOfdouble4[0];
      paramArrayOfdouble3[0] = -paramArrayOfdouble3[0];
      paramArrayOfdouble3[1] = -paramArrayOfdouble3[1];
      paramArrayOfdouble3[2] = -paramArrayOfdouble3[2];
    } 
    if (paramArrayOfdouble4[1] < 0.0D) {
      paramArrayOfdouble4[1] = -paramArrayOfdouble4[1];
      paramArrayOfdouble3[3] = -paramArrayOfdouble3[3];
      paramArrayOfdouble3[4] = -paramArrayOfdouble3[4];
      paramArrayOfdouble3[5] = -paramArrayOfdouble3[5];
    } 
    if (paramArrayOfdouble4[2] < 0.0D) {
      paramArrayOfdouble4[2] = -paramArrayOfdouble4[2];
      paramArrayOfdouble3[6] = -paramArrayOfdouble3[6];
      paramArrayOfdouble3[7] = -paramArrayOfdouble3[7];
      paramArrayOfdouble3[8] = -paramArrayOfdouble3[8];
    } 
    mat_mul(paramArrayOfdouble2, paramArrayOfdouble3, arrayOfDouble2);
    if (almostEqual(Math.abs(paramArrayOfdouble4[0]), Math.abs(paramArrayOfdouble4[1])) && almostEqual(Math.abs(paramArrayOfdouble4[1]), Math.abs(paramArrayOfdouble4[2]))) {
      byte b;
      for (b = 0; b < 9; b++)
        paramArrayOfdouble5[b] = arrayOfDouble2[b]; 
      for (b = 0; b < 3; b++)
        paramArrayOfdouble6[b] = paramArrayOfdouble4[b]; 
    } else {
      byte b;
      boolean bool1;
      boolean bool2;
      if (paramArrayOfdouble4[0] > paramArrayOfdouble4[1]) {
        if (paramArrayOfdouble4[0] > paramArrayOfdouble4[2]) {
          if (paramArrayOfdouble4[2] > paramArrayOfdouble4[1]) {
            arrayOfInt1[0] = 0;
            arrayOfInt1[1] = 2;
            arrayOfInt1[2] = 1;
          } else {
            arrayOfInt1[0] = 0;
            arrayOfInt1[1] = 1;
            arrayOfInt1[2] = 2;
          } 
        } else {
          arrayOfInt1[0] = 2;
          arrayOfInt1[1] = 0;
          arrayOfInt1[2] = 1;
        } 
      } else if (paramArrayOfdouble4[1] > paramArrayOfdouble4[2]) {
        if (paramArrayOfdouble4[2] > paramArrayOfdouble4[0]) {
          arrayOfInt1[0] = 1;
          arrayOfInt1[1] = 2;
          arrayOfInt1[2] = 0;
        } else {
          arrayOfInt1[0] = 1;
          arrayOfInt1[1] = 0;
          arrayOfInt1[2] = 2;
        } 
      } else {
        arrayOfInt1[0] = 2;
        arrayOfInt1[1] = 1;
        arrayOfInt1[2] = 0;
      } 
      arrayOfDouble1[0] = paramArrayOfdouble1[0] * paramArrayOfdouble1[0] + paramArrayOfdouble1[1] * paramArrayOfdouble1[1] + paramArrayOfdouble1[2] * paramArrayOfdouble1[2];
      arrayOfDouble1[1] = paramArrayOfdouble1[3] * paramArrayOfdouble1[3] + paramArrayOfdouble1[4] * paramArrayOfdouble1[4] + paramArrayOfdouble1[5] * paramArrayOfdouble1[5];
      arrayOfDouble1[2] = paramArrayOfdouble1[6] * paramArrayOfdouble1[6] + paramArrayOfdouble1[7] * paramArrayOfdouble1[7] + paramArrayOfdouble1[8] * paramArrayOfdouble1[8];
      if (arrayOfDouble1[0] > arrayOfDouble1[1]) {
        if (arrayOfDouble1[0] > arrayOfDouble1[2]) {
          if (arrayOfDouble1[2] > arrayOfDouble1[1]) {
            b = 0;
            bool2 = true;
            bool1 = true;
          } else {
            b = 0;
            bool1 = true;
            bool2 = true;
          } 
        } else {
          bool2 = false;
          b = 1;
          bool1 = true;
        } 
      } else if (arrayOfDouble1[1] > arrayOfDouble1[2]) {
        if (arrayOfDouble1[2] > arrayOfDouble1[0]) {
          bool1 = false;
          bool2 = true;
          b = 2;
        } else {
          bool1 = false;
          b = 1;
          bool2 = true;
        } 
      } else {
        bool2 = false;
        bool1 = true;
        b = 2;
      } 
      int i = arrayOfInt1[b];
      paramArrayOfdouble6[0] = paramArrayOfdouble4[i];
      i = arrayOfInt1[bool1];
      paramArrayOfdouble6[1] = paramArrayOfdouble4[i];
      i = arrayOfInt1[bool2];
      paramArrayOfdouble6[2] = paramArrayOfdouble4[i];
      i = arrayOfInt1[b];
      paramArrayOfdouble5[0] = arrayOfDouble2[i];
      i = arrayOfInt1[b] + 3;
      paramArrayOfdouble5[3] = arrayOfDouble2[i];
      i = arrayOfInt1[b] + 6;
      paramArrayOfdouble5[6] = arrayOfDouble2[i];
      i = arrayOfInt1[bool1];
      paramArrayOfdouble5[1] = arrayOfDouble2[i];
      i = arrayOfInt1[bool1] + 3;
      paramArrayOfdouble5[4] = arrayOfDouble2[i];
      i = arrayOfInt1[bool1] + 6;
      paramArrayOfdouble5[7] = arrayOfDouble2[i];
      i = arrayOfInt1[bool2];
      paramArrayOfdouble5[2] = arrayOfDouble2[i];
      i = arrayOfInt1[bool2] + 3;
      paramArrayOfdouble5[5] = arrayOfDouble2[i];
      i = arrayOfInt1[bool2] + 6;
      paramArrayOfdouble5[8] = arrayOfDouble2[i];
    } 
  }
  
  static int compute_qr(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3, double[] paramArrayOfdouble4) {
    double[] arrayOfDouble1 = new double[2];
    double[] arrayOfDouble2 = new double[2];
    double[] arrayOfDouble3 = new double[2];
    double[] arrayOfDouble4 = new double[2];
    double[] arrayOfDouble5 = new double[9];
    double d1 = 1.0D;
    double d2 = -1.0D;
    boolean bool1 = false;
    boolean bool2 = true;
    if (Math.abs(paramArrayOfdouble2[1]) < 4.89E-15D || Math.abs(paramArrayOfdouble2[0]) < 4.89E-15D)
      bool1 = true; 
    for (byte b = 0; b < 10 && !bool1; b++) {
      double d3 = compute_shift(paramArrayOfdouble1[1], paramArrayOfdouble2[1], paramArrayOfdouble1[2]);
      double d7 = (Math.abs(paramArrayOfdouble1[0]) - d3) * (d_sign(d1, paramArrayOfdouble1[0]) + d3 / paramArrayOfdouble1[0]);
      double d8 = paramArrayOfdouble2[0];
      double d4 = compute_rot(d7, d8, arrayOfDouble4, arrayOfDouble2, 0, bool2);
      d7 = arrayOfDouble2[0] * paramArrayOfdouble1[0] + arrayOfDouble4[0] * paramArrayOfdouble2[0];
      paramArrayOfdouble2[0] = arrayOfDouble2[0] * paramArrayOfdouble2[0] - arrayOfDouble4[0] * paramArrayOfdouble1[0];
      d8 = arrayOfDouble4[0] * paramArrayOfdouble1[1];
      paramArrayOfdouble1[1] = arrayOfDouble2[0] * paramArrayOfdouble1[1];
      d4 = compute_rot(d7, d8, arrayOfDouble3, arrayOfDouble1, 0, bool2);
      bool2 = false;
      paramArrayOfdouble1[0] = d4;
      d7 = arrayOfDouble1[0] * paramArrayOfdouble2[0] + arrayOfDouble3[0] * paramArrayOfdouble1[1];
      paramArrayOfdouble1[1] = arrayOfDouble1[0] * paramArrayOfdouble1[1] - arrayOfDouble3[0] * paramArrayOfdouble2[0];
      d8 = arrayOfDouble3[0] * paramArrayOfdouble2[1];
      paramArrayOfdouble2[1] = arrayOfDouble1[0] * paramArrayOfdouble2[1];
      d4 = compute_rot(d7, d8, arrayOfDouble4, arrayOfDouble2, 1, bool2);
      paramArrayOfdouble2[0] = d4;
      d7 = arrayOfDouble2[1] * paramArrayOfdouble1[1] + arrayOfDouble4[1] * paramArrayOfdouble2[1];
      paramArrayOfdouble2[1] = arrayOfDouble2[1] * paramArrayOfdouble2[1] - arrayOfDouble4[1] * paramArrayOfdouble1[1];
      d8 = arrayOfDouble4[1] * paramArrayOfdouble1[2];
      paramArrayOfdouble1[2] = arrayOfDouble2[1] * paramArrayOfdouble1[2];
      d4 = compute_rot(d7, d8, arrayOfDouble3, arrayOfDouble1, 1, bool2);
      paramArrayOfdouble1[1] = d4;
      d7 = arrayOfDouble1[1] * paramArrayOfdouble2[1] + arrayOfDouble3[1] * paramArrayOfdouble1[2];
      paramArrayOfdouble1[2] = arrayOfDouble1[1] * paramArrayOfdouble1[2] - arrayOfDouble3[1] * paramArrayOfdouble2[1];
      paramArrayOfdouble2[1] = d7;
      double d5 = paramArrayOfdouble3[0];
      paramArrayOfdouble3[0] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfdouble3[3];
      paramArrayOfdouble3[3] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfdouble3[3];
      d5 = paramArrayOfdouble3[1];
      paramArrayOfdouble3[1] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfdouble3[4];
      paramArrayOfdouble3[4] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfdouble3[4];
      d5 = paramArrayOfdouble3[2];
      paramArrayOfdouble3[2] = arrayOfDouble1[0] * d5 + arrayOfDouble3[0] * paramArrayOfdouble3[5];
      paramArrayOfdouble3[5] = -arrayOfDouble3[0] * d5 + arrayOfDouble1[0] * paramArrayOfdouble3[5];
      d5 = paramArrayOfdouble3[3];
      paramArrayOfdouble3[3] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfdouble3[6];
      paramArrayOfdouble3[6] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfdouble3[6];
      d5 = paramArrayOfdouble3[4];
      paramArrayOfdouble3[4] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfdouble3[7];
      paramArrayOfdouble3[7] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfdouble3[7];
      d5 = paramArrayOfdouble3[5];
      paramArrayOfdouble3[5] = arrayOfDouble1[1] * d5 + arrayOfDouble3[1] * paramArrayOfdouble3[8];
      paramArrayOfdouble3[8] = -arrayOfDouble3[1] * d5 + arrayOfDouble1[1] * paramArrayOfdouble3[8];
      double d6 = paramArrayOfdouble4[0];
      paramArrayOfdouble4[0] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfdouble4[1];
      paramArrayOfdouble4[1] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfdouble4[1];
      d6 = paramArrayOfdouble4[3];
      paramArrayOfdouble4[3] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfdouble4[4];
      paramArrayOfdouble4[4] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfdouble4[4];
      d6 = paramArrayOfdouble4[6];
      paramArrayOfdouble4[6] = arrayOfDouble2[0] * d6 + arrayOfDouble4[0] * paramArrayOfdouble4[7];
      paramArrayOfdouble4[7] = -arrayOfDouble4[0] * d6 + arrayOfDouble2[0] * paramArrayOfdouble4[7];
      d6 = paramArrayOfdouble4[1];
      paramArrayOfdouble4[1] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfdouble4[2];
      paramArrayOfdouble4[2] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfdouble4[2];
      d6 = paramArrayOfdouble4[4];
      paramArrayOfdouble4[4] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfdouble4[5];
      paramArrayOfdouble4[5] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfdouble4[5];
      d6 = paramArrayOfdouble4[7];
      paramArrayOfdouble4[7] = arrayOfDouble2[1] * d6 + arrayOfDouble4[1] * paramArrayOfdouble4[8];
      paramArrayOfdouble4[8] = -arrayOfDouble4[1] * d6 + arrayOfDouble2[1] * paramArrayOfdouble4[8];
      arrayOfDouble5[0] = paramArrayOfdouble1[0];
      arrayOfDouble5[1] = paramArrayOfdouble2[0];
      arrayOfDouble5[2] = 0.0D;
      arrayOfDouble5[3] = 0.0D;
      arrayOfDouble5[4] = paramArrayOfdouble1[1];
      arrayOfDouble5[5] = paramArrayOfdouble2[1];
      arrayOfDouble5[6] = 0.0D;
      arrayOfDouble5[7] = 0.0D;
      arrayOfDouble5[8] = paramArrayOfdouble1[2];
      if (Math.abs(paramArrayOfdouble2[1]) < 4.89E-15D || Math.abs(paramArrayOfdouble2[0]) < 4.89E-15D)
        bool1 = true; 
    } 
    if (Math.abs(paramArrayOfdouble2[1]) < 4.89E-15D) {
      compute_2X2(paramArrayOfdouble1[0], paramArrayOfdouble2[0], paramArrayOfdouble1[1], paramArrayOfdouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 0);
      double d3 = paramArrayOfdouble3[0];
      paramArrayOfdouble3[0] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfdouble3[3];
      paramArrayOfdouble3[3] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfdouble3[3];
      d3 = paramArrayOfdouble3[1];
      paramArrayOfdouble3[1] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfdouble3[4];
      paramArrayOfdouble3[4] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfdouble3[4];
      d3 = paramArrayOfdouble3[2];
      paramArrayOfdouble3[2] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfdouble3[5];
      paramArrayOfdouble3[5] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfdouble3[5];
      double d4 = paramArrayOfdouble4[0];
      paramArrayOfdouble4[0] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfdouble4[1];
      paramArrayOfdouble4[1] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfdouble4[1];
      d4 = paramArrayOfdouble4[3];
      paramArrayOfdouble4[3] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfdouble4[4];
      paramArrayOfdouble4[4] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfdouble4[4];
      d4 = paramArrayOfdouble4[6];
      paramArrayOfdouble4[6] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfdouble4[7];
      paramArrayOfdouble4[7] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfdouble4[7];
    } else {
      compute_2X2(paramArrayOfdouble1[1], paramArrayOfdouble2[1], paramArrayOfdouble1[2], paramArrayOfdouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 1);
      double d3 = paramArrayOfdouble3[3];
      paramArrayOfdouble3[3] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfdouble3[6];
      paramArrayOfdouble3[6] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfdouble3[6];
      d3 = paramArrayOfdouble3[4];
      paramArrayOfdouble3[4] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfdouble3[7];
      paramArrayOfdouble3[7] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfdouble3[7];
      d3 = paramArrayOfdouble3[5];
      paramArrayOfdouble3[5] = arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfdouble3[8];
      paramArrayOfdouble3[8] = -arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfdouble3[8];
      double d4 = paramArrayOfdouble4[1];
      paramArrayOfdouble4[1] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfdouble4[2];
      paramArrayOfdouble4[2] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfdouble4[2];
      d4 = paramArrayOfdouble4[4];
      paramArrayOfdouble4[4] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfdouble4[5];
      paramArrayOfdouble4[5] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfdouble4[5];
      d4 = paramArrayOfdouble4[7];
      paramArrayOfdouble4[7] = arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfdouble4[8];
      paramArrayOfdouble4[8] = -arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfdouble4[8];
    } 
    return 0;
  }
  
  static double max(double paramDouble1, double paramDouble2) {
    return (paramDouble1 > paramDouble2) ? paramDouble1 : paramDouble2;
  }
  
  static double min(double paramDouble1, double paramDouble2) {
    return (paramDouble1 < paramDouble2) ? paramDouble1 : paramDouble2;
  }
  
  static double d_sign(double paramDouble1, double paramDouble2) {
    double d = (paramDouble1 >= 0.0D) ? paramDouble1 : -paramDouble1;
    return (paramDouble2 >= 0.0D) ? d : -d;
  }
  
  static double compute_shift(double paramDouble1, double paramDouble2, double paramDouble3) {
    double d6;
    double d3 = Math.abs(paramDouble1);
    double d4 = Math.abs(paramDouble2);
    double d5 = Math.abs(paramDouble3);
    double d1 = min(d3, d5);
    double d2 = max(d3, d5);
    if (d1 == 0.0D) {
      d6 = 0.0D;
      if (d2 != 0.0D)
        double d = min(d2, d4) / max(d2, d4); 
    } else if (d4 < d2) {
      double d9 = d1 / d2 + 1.0D;
      double d10 = (d2 - d1) / d2;
      double d7 = d4 / d2;
      double d11 = d7 * d7;
      double d8 = 2.0D / (Math.sqrt(d9 * d9 + d11) + Math.sqrt(d10 * d10 + d11));
      d6 = d1 * d8;
    } else {
      double d = d2 / d4;
      if (d == 0.0D) {
        d6 = d1 * d2 / d4;
      } else {
        double d10 = d1 / d2 + 1.0D;
        double d11 = (d2 - d1) / d2;
        double d7 = d10 * d;
        double d8 = d11 * d;
        double d9 = 1.0D / (Math.sqrt(d7 * d7 + 1.0D) + Math.sqrt(d8 * d8 + 1.0D));
        d6 = d1 * d9 * d;
        d6 += d6;
      } 
    } 
    return d6;
  }
  
  static int compute_2X2(double paramDouble1, double paramDouble2, double paramDouble3, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3, double[] paramArrayOfdouble4, double[] paramArrayOfdouble5, int paramInt) {
    boolean bool;
    double d1 = 2.0D;
    double d2 = 1.0D;
    double d15 = paramArrayOfdouble1[0];
    double d14 = paramArrayOfdouble1[1];
    double d10 = 0.0D;
    double d11 = 0.0D;
    double d12 = 0.0D;
    double d13 = 0.0D;
    double d3 = 0.0D;
    double d7 = paramDouble1;
    double d4 = Math.abs(d7);
    double d9 = paramDouble3;
    double d6 = Math.abs(paramDouble3);
    byte b = 1;
    if (d6 > d4) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      b = 3;
      double d = d7;
      d7 = d9;
      d9 = d;
      d = d4;
      d4 = d6;
      d6 = d;
    } 
    double d8 = paramDouble2;
    double d5 = Math.abs(d8);
    if (d5 == 0.0D) {
      paramArrayOfdouble1[1] = d6;
      paramArrayOfdouble1[0] = d4;
      d10 = 1.0D;
      d11 = 1.0D;
      d12 = 0.0D;
      d13 = 0.0D;
    } else {
      boolean bool1 = true;
      if (d5 > d4) {
        b = 2;
        if (d4 / d5 < 1.110223024E-16D) {
          bool1 = false;
          d15 = d5;
          if (d6 > 1.0D) {
            d14 = d4 / d5 / d6;
          } else {
            d14 = d4 / d5 * d6;
          } 
          d10 = 1.0D;
          d12 = d9 / d8;
          d13 = 1.0D;
          d11 = d7 / d8;
        } 
      } 
      if (bool1) {
        double d18;
        double d20;
        double d17 = d4 - d6;
        if (d17 == d4) {
          d18 = 1.0D;
        } else {
          d18 = d17 / d4;
        } 
        double d19 = d8 / d7;
        double d22 = 2.0D - d18;
        double d23 = d19 * d19;
        double d24 = d22 * d22;
        double d21 = Math.sqrt(d24 + d23);
        if (d18 == 0.0D) {
          d20 = Math.abs(d19);
        } else {
          d20 = Math.sqrt(d18 * d18 + d23);
        } 
        double d16 = (d21 + d20) * 0.5D;
        if (d5 > d4) {
          b = 2;
          if (d4 / d5 < 1.110223024E-16D) {
            bool1 = false;
            d15 = d5;
            if (d6 > 1.0D) {
              d14 = d4 / d5 / d6;
            } else {
              d14 = d4 / d5 * d6;
            } 
            d10 = 1.0D;
            d12 = d9 / d8;
            d13 = 1.0D;
            d11 = d7 / d8;
          } 
        } 
        if (bool1) {
          d17 = d4 - d6;
          if (d17 == d4) {
            d18 = 1.0D;
          } else {
            d18 = d17 / d4;
          } 
          d19 = d8 / d7;
          d22 = 2.0D - d18;
          d23 = d19 * d19;
          d24 = d22 * d22;
          d21 = Math.sqrt(d24 + d23);
          if (d18 == 0.0D) {
            d20 = Math.abs(d19);
          } else {
            d20 = Math.sqrt(d18 * d18 + d23);
          } 
          d16 = (d21 + d20) * 0.5D;
          d14 = d6 / d16;
          d15 = d4 * d16;
          if (d23 == 0.0D) {
            if (d18 == 0.0D) {
              d22 = d_sign(d1, d7) * d_sign(d2, d8);
            } else {
              d22 = d8 / d_sign(d17, d7) + d19 / d22;
            } 
          } else {
            d22 = (d19 / (d21 + d22) + d19 / (d20 + d18)) * (d16 + 1.0D);
          } 
          d18 = Math.sqrt(d22 * d22 + 4.0D);
          d11 = 2.0D / d18;
          d13 = d22 / d18;
          d10 = (d11 + d13 * d19) / d16;
          d12 = d9 / d7 * d13 / d16;
        } 
      } 
      if (bool) {
        paramArrayOfdouble3[0] = d13;
        paramArrayOfdouble2[0] = d11;
        paramArrayOfdouble5[0] = d12;
        paramArrayOfdouble4[0] = d10;
      } else {
        paramArrayOfdouble3[0] = d10;
        paramArrayOfdouble2[0] = d12;
        paramArrayOfdouble5[0] = d11;
        paramArrayOfdouble4[0] = d13;
      } 
      if (b == 1)
        d3 = d_sign(d2, paramArrayOfdouble5[0]) * d_sign(d2, paramArrayOfdouble3[0]) * d_sign(d2, paramDouble1); 
      if (b == 2)
        d3 = d_sign(d2, paramArrayOfdouble4[0]) * d_sign(d2, paramArrayOfdouble3[0]) * d_sign(d2, paramDouble2); 
      if (b == 3)
        d3 = d_sign(d2, paramArrayOfdouble4[0]) * d_sign(d2, paramArrayOfdouble2[0]) * d_sign(d2, paramDouble3); 
      paramArrayOfdouble1[paramInt] = d_sign(d15, d3);
      double d = d3 * d_sign(d2, paramDouble1) * d_sign(d2, paramDouble3);
      paramArrayOfdouble1[paramInt + 1] = d_sign(d14, d);
    } 
    return 0;
  }
  
  static double compute_rot(double paramDouble1, double paramDouble2, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, int paramInt1, int paramInt2) {
    double d1;
    double d2;
    double d3;
    if (paramDouble2 == 0.0D) {
      d1 = 1.0D;
      d2 = 0.0D;
      d3 = paramDouble1;
    } else if (paramDouble1 == 0.0D) {
      d1 = 0.0D;
      d2 = 1.0D;
      d3 = paramDouble2;
    } else {
      double d5 = paramDouble1;
      double d6 = paramDouble2;
      double d4 = max(Math.abs(d5), Math.abs(d6));
      if (d4 >= 4.994797680505588E145D) {
        byte b3 = 0;
        while (d4 >= 4.994797680505588E145D) {
          b3++;
          d5 *= 2.002083095183101E-146D;
          d6 *= 2.002083095183101E-146D;
          d4 = max(Math.abs(d5), Math.abs(d6));
        } 
        d3 = Math.sqrt(d5 * d5 + d6 * d6);
        d1 = d5 / d3;
        d2 = d6 / d3;
        byte b1 = b3;
        for (byte b2 = 1; b2 <= b3; b2++)
          d3 *= 4.994797680505588E145D; 
      } else if (d4 <= 2.002083095183101E-146D) {
        byte b3 = 0;
        while (d4 <= 2.002083095183101E-146D) {
          b3++;
          d5 *= 4.994797680505588E145D;
          d6 *= 4.994797680505588E145D;
          d4 = max(Math.abs(d5), Math.abs(d6));
        } 
        d3 = Math.sqrt(d5 * d5 + d6 * d6);
        d1 = d5 / d3;
        d2 = d6 / d3;
        byte b1 = b3;
        for (byte b2 = 1; b2 <= b3; b2++)
          d3 *= 2.002083095183101E-146D; 
      } else {
        d3 = Math.sqrt(d5 * d5 + d6 * d6);
        d1 = d5 / d3;
        d2 = d6 / d3;
      } 
      if (Math.abs(paramDouble1) > Math.abs(paramDouble2) && d1 < 0.0D) {
        d1 = -d1;
        d2 = -d2;
        d3 = -d3;
      } 
    } 
    paramArrayOfdouble1[paramInt1] = d2;
    paramArrayOfdouble2[paramInt1] = d1;
    return d3;
  }
  
  static void print_mat(double[] paramArrayOfdouble) {
    for (byte b = 0; b < 3; b++)
      System.out.println(paramArrayOfdouble[b * 3 + 0] + " " + paramArrayOfdouble[b * 3 + 1] + " " + paramArrayOfdouble[b * 3 + 2] + "\n"); 
  }
  
  static void print_det(double[] paramArrayOfdouble) {
    double d = paramArrayOfdouble[0] * paramArrayOfdouble[4] * paramArrayOfdouble[8] + paramArrayOfdouble[1] * paramArrayOfdouble[5] * paramArrayOfdouble[6] + paramArrayOfdouble[2] * paramArrayOfdouble[3] * paramArrayOfdouble[7] - paramArrayOfdouble[2] * paramArrayOfdouble[4] * paramArrayOfdouble[6] - paramArrayOfdouble[0] * paramArrayOfdouble[5] * paramArrayOfdouble[7] - paramArrayOfdouble[1] * paramArrayOfdouble[3] * paramArrayOfdouble[8];
    System.out.println("det= " + d);
  }
  
  static void mat_mul(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3) {
    double[] arrayOfDouble = new double[9];
    arrayOfDouble[0] = paramArrayOfdouble1[0] * paramArrayOfdouble2[0] + paramArrayOfdouble1[1] * paramArrayOfdouble2[3] + paramArrayOfdouble1[2] * paramArrayOfdouble2[6];
    arrayOfDouble[1] = paramArrayOfdouble1[0] * paramArrayOfdouble2[1] + paramArrayOfdouble1[1] * paramArrayOfdouble2[4] + paramArrayOfdouble1[2] * paramArrayOfdouble2[7];
    arrayOfDouble[2] = paramArrayOfdouble1[0] * paramArrayOfdouble2[2] + paramArrayOfdouble1[1] * paramArrayOfdouble2[5] + paramArrayOfdouble1[2] * paramArrayOfdouble2[8];
    arrayOfDouble[3] = paramArrayOfdouble1[3] * paramArrayOfdouble2[0] + paramArrayOfdouble1[4] * paramArrayOfdouble2[3] + paramArrayOfdouble1[5] * paramArrayOfdouble2[6];
    arrayOfDouble[4] = paramArrayOfdouble1[3] * paramArrayOfdouble2[1] + paramArrayOfdouble1[4] * paramArrayOfdouble2[4] + paramArrayOfdouble1[5] * paramArrayOfdouble2[7];
    arrayOfDouble[5] = paramArrayOfdouble1[3] * paramArrayOfdouble2[2] + paramArrayOfdouble1[4] * paramArrayOfdouble2[5] + paramArrayOfdouble1[5] * paramArrayOfdouble2[8];
    arrayOfDouble[6] = paramArrayOfdouble1[6] * paramArrayOfdouble2[0] + paramArrayOfdouble1[7] * paramArrayOfdouble2[3] + paramArrayOfdouble1[8] * paramArrayOfdouble2[6];
    arrayOfDouble[7] = paramArrayOfdouble1[6] * paramArrayOfdouble2[1] + paramArrayOfdouble1[7] * paramArrayOfdouble2[4] + paramArrayOfdouble1[8] * paramArrayOfdouble2[7];
    arrayOfDouble[8] = paramArrayOfdouble1[6] * paramArrayOfdouble2[2] + paramArrayOfdouble1[7] * paramArrayOfdouble2[5] + paramArrayOfdouble1[8] * paramArrayOfdouble2[8];
    for (byte b = 0; b < 9; b++)
      paramArrayOfdouble3[b] = arrayOfDouble[b]; 
  }
  
  static void transpose_mat(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2) {
    paramArrayOfdouble2[0] = paramArrayOfdouble1[0];
    paramArrayOfdouble2[1] = paramArrayOfdouble1[3];
    paramArrayOfdouble2[2] = paramArrayOfdouble1[6];
    paramArrayOfdouble2[3] = paramArrayOfdouble1[1];
    paramArrayOfdouble2[4] = paramArrayOfdouble1[4];
    paramArrayOfdouble2[5] = paramArrayOfdouble1[7];
    paramArrayOfdouble2[6] = paramArrayOfdouble1[2];
    paramArrayOfdouble2[7] = paramArrayOfdouble1[5];
    paramArrayOfdouble2[8] = paramArrayOfdouble1[8];
  }
  
  static double max3(double[] paramArrayOfdouble) {
    return (paramArrayOfdouble[0] > paramArrayOfdouble[1]) ? ((paramArrayOfdouble[0] > paramArrayOfdouble[2]) ? paramArrayOfdouble[0] : paramArrayOfdouble[2]) : ((paramArrayOfdouble[1] > paramArrayOfdouble[2]) ? paramArrayOfdouble[1] : paramArrayOfdouble[2]);
  }
  
  private static final boolean almostEqual(double paramDouble1, double paramDouble2) {
    if (paramDouble1 == paramDouble2)
      return true; 
    double d1 = Math.abs(paramDouble1 - paramDouble2);
    double d2 = Math.abs(paramDouble1);
    double d3 = Math.abs(paramDouble2);
    double d4 = (d2 >= d3) ? d2 : d3;
    return (d1 < 1.0E-6D) ? true : ((d1 / d4 < 1.0E-4D));
  }
  
  public Object clone() {
    Matrix3d matrix3d = null;
    try {
      matrix3d = (Matrix3d)super.clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new InternalError();
    } 
    return matrix3d;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Matrix3d.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */