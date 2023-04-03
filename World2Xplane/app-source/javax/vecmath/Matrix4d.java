package javax.vecmath;

import java.io.Serializable;

public class Matrix4d implements Serializable, Cloneable {
  static final long serialVersionUID = 8223903484171633710L;
  
  public double m00;
  
  public double m01;
  
  public double m02;
  
  public double m03;
  
  public double m10;
  
  public double m11;
  
  public double m12;
  
  public double m13;
  
  public double m20;
  
  public double m21;
  
  public double m22;
  
  public double m23;
  
  public double m30;
  
  public double m31;
  
  public double m32;
  
  public double m33;
  
  private static final double EPS = 1.0E-10D;
  
  public Matrix4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9, double paramDouble10, double paramDouble11, double paramDouble12, double paramDouble13, double paramDouble14, double paramDouble15, double paramDouble16) {
    this.m00 = paramDouble1;
    this.m01 = paramDouble2;
    this.m02 = paramDouble3;
    this.m03 = paramDouble4;
    this.m10 = paramDouble5;
    this.m11 = paramDouble6;
    this.m12 = paramDouble7;
    this.m13 = paramDouble8;
    this.m20 = paramDouble9;
    this.m21 = paramDouble10;
    this.m22 = paramDouble11;
    this.m23 = paramDouble12;
    this.m30 = paramDouble13;
    this.m31 = paramDouble14;
    this.m32 = paramDouble15;
    this.m33 = paramDouble16;
  }
  
  public Matrix4d(double[] paramArrayOfdouble) {
    this.m00 = paramArrayOfdouble[0];
    this.m01 = paramArrayOfdouble[1];
    this.m02 = paramArrayOfdouble[2];
    this.m03 = paramArrayOfdouble[3];
    this.m10 = paramArrayOfdouble[4];
    this.m11 = paramArrayOfdouble[5];
    this.m12 = paramArrayOfdouble[6];
    this.m13 = paramArrayOfdouble[7];
    this.m20 = paramArrayOfdouble[8];
    this.m21 = paramArrayOfdouble[9];
    this.m22 = paramArrayOfdouble[10];
    this.m23 = paramArrayOfdouble[11];
    this.m30 = paramArrayOfdouble[12];
    this.m31 = paramArrayOfdouble[13];
    this.m32 = paramArrayOfdouble[14];
    this.m33 = paramArrayOfdouble[15];
  }
  
  public Matrix4d(Quat4d paramQuat4d, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
    this.m10 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
    this.m20 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
    this.m01 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
    this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
    this.m21 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
    this.m02 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
    this.m12 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
    this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
    this.m03 = paramVector3d.x;
    this.m13 = paramVector3d.y;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public Matrix4d(Quat4f paramQuat4f, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m10 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
    this.m20 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
    this.m01 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
    this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m21 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
    this.m02 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
    this.m12 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
    this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
    this.m03 = paramVector3d.x;
    this.m13 = paramVector3d.y;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public Matrix4d(Matrix4d paramMatrix4d) {
    this.m00 = paramMatrix4d.m00;
    this.m01 = paramMatrix4d.m01;
    this.m02 = paramMatrix4d.m02;
    this.m03 = paramMatrix4d.m03;
    this.m10 = paramMatrix4d.m10;
    this.m11 = paramMatrix4d.m11;
    this.m12 = paramMatrix4d.m12;
    this.m13 = paramMatrix4d.m13;
    this.m20 = paramMatrix4d.m20;
    this.m21 = paramMatrix4d.m21;
    this.m22 = paramMatrix4d.m22;
    this.m23 = paramMatrix4d.m23;
    this.m30 = paramMatrix4d.m30;
    this.m31 = paramMatrix4d.m31;
    this.m32 = paramMatrix4d.m32;
    this.m33 = paramMatrix4d.m33;
  }
  
  public Matrix4d(Matrix4f paramMatrix4f) {
    this.m00 = paramMatrix4f.m00;
    this.m01 = paramMatrix4f.m01;
    this.m02 = paramMatrix4f.m02;
    this.m03 = paramMatrix4f.m03;
    this.m10 = paramMatrix4f.m10;
    this.m11 = paramMatrix4f.m11;
    this.m12 = paramMatrix4f.m12;
    this.m13 = paramMatrix4f.m13;
    this.m20 = paramMatrix4f.m20;
    this.m21 = paramMatrix4f.m21;
    this.m22 = paramMatrix4f.m22;
    this.m23 = paramMatrix4f.m23;
    this.m30 = paramMatrix4f.m30;
    this.m31 = paramMatrix4f.m31;
    this.m32 = paramMatrix4f.m32;
    this.m33 = paramMatrix4f.m33;
  }
  
  public Matrix4d(Matrix3f paramMatrix3f, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramMatrix3f.m00 * paramDouble;
    this.m01 = paramMatrix3f.m01 * paramDouble;
    this.m02 = paramMatrix3f.m02 * paramDouble;
    this.m03 = paramVector3d.x;
    this.m10 = paramMatrix3f.m10 * paramDouble;
    this.m11 = paramMatrix3f.m11 * paramDouble;
    this.m12 = paramMatrix3f.m12 * paramDouble;
    this.m13 = paramVector3d.y;
    this.m20 = paramMatrix3f.m20 * paramDouble;
    this.m21 = paramMatrix3f.m21 * paramDouble;
    this.m22 = paramMatrix3f.m22 * paramDouble;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public Matrix4d(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramMatrix3d.m00 * paramDouble;
    this.m01 = paramMatrix3d.m01 * paramDouble;
    this.m02 = paramMatrix3d.m02 * paramDouble;
    this.m03 = paramVector3d.x;
    this.m10 = paramMatrix3d.m10 * paramDouble;
    this.m11 = paramMatrix3d.m11 * paramDouble;
    this.m12 = paramMatrix3d.m12 * paramDouble;
    this.m13 = paramVector3d.y;
    this.m20 = paramMatrix3d.m20 * paramDouble;
    this.m21 = paramMatrix3d.m21 * paramDouble;
    this.m22 = paramMatrix3d.m22 * paramDouble;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public Matrix4d() {
    this.m00 = 0.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 0.0D;
    this.m12 = 0.0D;
    this.m13 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 0.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 0.0D;
  }
  
  public String toString() {
    return this.m00 + ", " + this.m01 + ", " + this.m02 + ", " + this.m03 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + ", " + this.m13 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + ", " + this.m23 + "\n" + this.m30 + ", " + this.m31 + ", " + this.m32 + ", " + this.m33 + "\n";
  }
  
  public final void setIdentity() {
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m13 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
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
          case 3:
            this.m03 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
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
          case 3:
            this.m13 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
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
          case 3:
            this.m23 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
      case 3:
        switch (paramInt2) {
          case 0:
            this.m30 = paramDouble;
            return;
          case 1:
            this.m31 = paramDouble;
            return;
          case 2:
            this.m32 = paramDouble;
            return;
          case 3:
            this.m33 = paramDouble;
            return;
        } 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d0"));
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
          case 3:
            return this.m03;
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
          case 3:
            return this.m13;
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
          case 3:
            return this.m23;
        } 
        break;
      case 3:
        switch (paramInt2) {
          case 0:
            return this.m30;
          case 1:
            return this.m31;
          case 2:
            return this.m32;
          case 3:
            return this.m33;
        } 
        break;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d1"));
  }
  
  public final void getRow(int paramInt, Vector4d paramVector4d) {
    if (paramInt == 0) {
      paramVector4d.x = this.m00;
      paramVector4d.y = this.m01;
      paramVector4d.z = this.m02;
      paramVector4d.w = this.m03;
    } else if (paramInt == 1) {
      paramVector4d.x = this.m10;
      paramVector4d.y = this.m11;
      paramVector4d.z = this.m12;
      paramVector4d.w = this.m13;
    } else if (paramInt == 2) {
      paramVector4d.x = this.m20;
      paramVector4d.y = this.m21;
      paramVector4d.z = this.m22;
      paramVector4d.w = this.m23;
    } else if (paramInt == 3) {
      paramVector4d.x = this.m30;
      paramVector4d.y = this.m31;
      paramVector4d.z = this.m32;
      paramVector4d.w = this.m33;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d2"));
    } 
  }
  
  public final void getRow(int paramInt, double[] paramArrayOfdouble) {
    if (paramInt == 0) {
      paramArrayOfdouble[0] = this.m00;
      paramArrayOfdouble[1] = this.m01;
      paramArrayOfdouble[2] = this.m02;
      paramArrayOfdouble[3] = this.m03;
    } else if (paramInt == 1) {
      paramArrayOfdouble[0] = this.m10;
      paramArrayOfdouble[1] = this.m11;
      paramArrayOfdouble[2] = this.m12;
      paramArrayOfdouble[3] = this.m13;
    } else if (paramInt == 2) {
      paramArrayOfdouble[0] = this.m20;
      paramArrayOfdouble[1] = this.m21;
      paramArrayOfdouble[2] = this.m22;
      paramArrayOfdouble[3] = this.m23;
    } else if (paramInt == 3) {
      paramArrayOfdouble[0] = this.m30;
      paramArrayOfdouble[1] = this.m31;
      paramArrayOfdouble[2] = this.m32;
      paramArrayOfdouble[3] = this.m33;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d2"));
    } 
  }
  
  public final void getColumn(int paramInt, Vector4d paramVector4d) {
    if (paramInt == 0) {
      paramVector4d.x = this.m00;
      paramVector4d.y = this.m10;
      paramVector4d.z = this.m20;
      paramVector4d.w = this.m30;
    } else if (paramInt == 1) {
      paramVector4d.x = this.m01;
      paramVector4d.y = this.m11;
      paramVector4d.z = this.m21;
      paramVector4d.w = this.m31;
    } else if (paramInt == 2) {
      paramVector4d.x = this.m02;
      paramVector4d.y = this.m12;
      paramVector4d.z = this.m22;
      paramVector4d.w = this.m32;
    } else if (paramInt == 3) {
      paramVector4d.x = this.m03;
      paramVector4d.y = this.m13;
      paramVector4d.z = this.m23;
      paramVector4d.w = this.m33;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d3"));
    } 
  }
  
  public final void getColumn(int paramInt, double[] paramArrayOfdouble) {
    if (paramInt == 0) {
      paramArrayOfdouble[0] = this.m00;
      paramArrayOfdouble[1] = this.m10;
      paramArrayOfdouble[2] = this.m20;
      paramArrayOfdouble[3] = this.m30;
    } else if (paramInt == 1) {
      paramArrayOfdouble[0] = this.m01;
      paramArrayOfdouble[1] = this.m11;
      paramArrayOfdouble[2] = this.m21;
      paramArrayOfdouble[3] = this.m31;
    } else if (paramInt == 2) {
      paramArrayOfdouble[0] = this.m02;
      paramArrayOfdouble[1] = this.m12;
      paramArrayOfdouble[2] = this.m22;
      paramArrayOfdouble[3] = this.m32;
    } else if (paramInt == 3) {
      paramArrayOfdouble[0] = this.m03;
      paramArrayOfdouble[1] = this.m13;
      paramArrayOfdouble[2] = this.m23;
      paramArrayOfdouble[3] = this.m33;
    } else {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d3"));
    } 
  }
  
  public final void get(Matrix3d paramMatrix3d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    paramMatrix3d.m00 = arrayOfDouble1[0];
    paramMatrix3d.m01 = arrayOfDouble1[1];
    paramMatrix3d.m02 = arrayOfDouble1[2];
    paramMatrix3d.m10 = arrayOfDouble1[3];
    paramMatrix3d.m11 = arrayOfDouble1[4];
    paramMatrix3d.m12 = arrayOfDouble1[5];
    paramMatrix3d.m20 = arrayOfDouble1[6];
    paramMatrix3d.m21 = arrayOfDouble1[7];
    paramMatrix3d.m22 = arrayOfDouble1[8];
  }
  
  public final void get(Matrix3f paramMatrix3f) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    paramMatrix3f.m00 = (float)arrayOfDouble1[0];
    paramMatrix3f.m01 = (float)arrayOfDouble1[1];
    paramMatrix3f.m02 = (float)arrayOfDouble1[2];
    paramMatrix3f.m10 = (float)arrayOfDouble1[3];
    paramMatrix3f.m11 = (float)arrayOfDouble1[4];
    paramMatrix3f.m12 = (float)arrayOfDouble1[5];
    paramMatrix3f.m20 = (float)arrayOfDouble1[6];
    paramMatrix3f.m21 = (float)arrayOfDouble1[7];
    paramMatrix3f.m22 = (float)arrayOfDouble1[8];
  }
  
  public final double get(Matrix3d paramMatrix3d, Vector3d paramVector3d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    paramMatrix3d.m00 = arrayOfDouble1[0];
    paramMatrix3d.m01 = arrayOfDouble1[1];
    paramMatrix3d.m02 = arrayOfDouble1[2];
    paramMatrix3d.m10 = arrayOfDouble1[3];
    paramMatrix3d.m11 = arrayOfDouble1[4];
    paramMatrix3d.m12 = arrayOfDouble1[5];
    paramMatrix3d.m20 = arrayOfDouble1[6];
    paramMatrix3d.m21 = arrayOfDouble1[7];
    paramMatrix3d.m22 = arrayOfDouble1[8];
    paramVector3d.x = this.m03;
    paramVector3d.y = this.m13;
    paramVector3d.z = this.m23;
    return Matrix3d.max3(arrayOfDouble2);
  }
  
  public final double get(Matrix3f paramMatrix3f, Vector3d paramVector3d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    paramMatrix3f.m00 = (float)arrayOfDouble1[0];
    paramMatrix3f.m01 = (float)arrayOfDouble1[1];
    paramMatrix3f.m02 = (float)arrayOfDouble1[2];
    paramMatrix3f.m10 = (float)arrayOfDouble1[3];
    paramMatrix3f.m11 = (float)arrayOfDouble1[4];
    paramMatrix3f.m12 = (float)arrayOfDouble1[5];
    paramMatrix3f.m20 = (float)arrayOfDouble1[6];
    paramMatrix3f.m21 = (float)arrayOfDouble1[7];
    paramMatrix3f.m22 = (float)arrayOfDouble1[8];
    paramVector3d.x = this.m03;
    paramVector3d.y = this.m13;
    paramVector3d.z = this.m23;
    return Matrix3d.max3(arrayOfDouble2);
  }
  
  public final void get(Quat4f paramQuat4f) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    double d = 0.25D * (1.0D + arrayOfDouble1[0] + arrayOfDouble1[4] + arrayOfDouble1[8]);
    if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
      paramQuat4f.w = (float)Math.sqrt(d);
      d = 0.25D / paramQuat4f.w;
      paramQuat4f.x = (float)((arrayOfDouble1[7] - arrayOfDouble1[5]) * d);
      paramQuat4f.y = (float)((arrayOfDouble1[2] - arrayOfDouble1[6]) * d);
      paramQuat4f.z = (float)((arrayOfDouble1[3] - arrayOfDouble1[1]) * d);
      return;
    } 
    paramQuat4f.w = 0.0F;
    d = -0.5D * (arrayOfDouble1[4] + arrayOfDouble1[8]);
    if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
      paramQuat4f.x = (float)Math.sqrt(d);
      d = 0.5D / paramQuat4f.x;
      paramQuat4f.y = (float)(arrayOfDouble1[3] * d);
      paramQuat4f.z = (float)(arrayOfDouble1[6] * d);
      return;
    } 
    paramQuat4f.x = 0.0F;
    d = 0.5D * (1.0D - arrayOfDouble1[8]);
    if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
      paramQuat4f.y = (float)Math.sqrt(d);
      paramQuat4f.z = (float)(arrayOfDouble1[7] / 2.0D * paramQuat4f.y);
      return;
    } 
    paramQuat4f.y = 0.0F;
    paramQuat4f.z = 1.0F;
  }
  
  public final void get(Quat4d paramQuat4d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    double d = 0.25D * (1.0D + arrayOfDouble1[0] + arrayOfDouble1[4] + arrayOfDouble1[8]);
    if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
      paramQuat4d.w = Math.sqrt(d);
      d = 0.25D / paramQuat4d.w;
      paramQuat4d.x = (arrayOfDouble1[7] - arrayOfDouble1[5]) * d;
      paramQuat4d.y = (arrayOfDouble1[2] - arrayOfDouble1[6]) * d;
      paramQuat4d.z = (arrayOfDouble1[3] - arrayOfDouble1[1]) * d;
      return;
    } 
    paramQuat4d.w = 0.0D;
    d = -0.5D * (arrayOfDouble1[4] + arrayOfDouble1[8]);
    if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
      paramQuat4d.x = Math.sqrt(d);
      d = 0.5D / paramQuat4d.x;
      paramQuat4d.y = arrayOfDouble1[3] * d;
      paramQuat4d.z = arrayOfDouble1[6] * d;
      return;
    } 
    paramQuat4d.x = 0.0D;
    d = 0.5D * (1.0D - arrayOfDouble1[8]);
    if (((d < 0.0D) ? -d : d) >= 1.0E-30D) {
      paramQuat4d.y = Math.sqrt(d);
      paramQuat4d.z = arrayOfDouble1[7] / 2.0D * paramQuat4d.y;
      return;
    } 
    paramQuat4d.y = 0.0D;
    paramQuat4d.z = 1.0D;
  }
  
  public final void get(Vector3d paramVector3d) {
    paramVector3d.x = this.m03;
    paramVector3d.y = this.m13;
    paramVector3d.z = this.m23;
  }
  
  public final void getRotationScale(Matrix3f paramMatrix3f) {
    paramMatrix3f.m00 = (float)this.m00;
    paramMatrix3f.m01 = (float)this.m01;
    paramMatrix3f.m02 = (float)this.m02;
    paramMatrix3f.m10 = (float)this.m10;
    paramMatrix3f.m11 = (float)this.m11;
    paramMatrix3f.m12 = (float)this.m12;
    paramMatrix3f.m20 = (float)this.m20;
    paramMatrix3f.m21 = (float)this.m21;
    paramMatrix3f.m22 = (float)this.m22;
  }
  
  public final void getRotationScale(Matrix3d paramMatrix3d) {
    paramMatrix3d.m00 = this.m00;
    paramMatrix3d.m01 = this.m01;
    paramMatrix3d.m02 = this.m02;
    paramMatrix3d.m10 = this.m10;
    paramMatrix3d.m11 = this.m11;
    paramMatrix3d.m12 = this.m12;
    paramMatrix3d.m20 = this.m20;
    paramMatrix3d.m21 = this.m21;
    paramMatrix3d.m22 = this.m22;
  }
  
  public final double getScale() {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    return Matrix3d.max3(arrayOfDouble2);
  }
  
  public final void setRotationScale(Matrix3d paramMatrix3d) {
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
  
  public final void setRotationScale(Matrix3f paramMatrix3f) {
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
  
  public final void setRow(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    switch (paramInt) {
      case 0:
        this.m00 = paramDouble1;
        this.m01 = paramDouble2;
        this.m02 = paramDouble3;
        this.m03 = paramDouble4;
        return;
      case 1:
        this.m10 = paramDouble1;
        this.m11 = paramDouble2;
        this.m12 = paramDouble3;
        this.m13 = paramDouble4;
        return;
      case 2:
        this.m20 = paramDouble1;
        this.m21 = paramDouble2;
        this.m22 = paramDouble3;
        this.m23 = paramDouble4;
        return;
      case 3:
        this.m30 = paramDouble1;
        this.m31 = paramDouble2;
        this.m32 = paramDouble3;
        this.m33 = paramDouble4;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d4"));
  }
  
  public final void setRow(int paramInt, Vector4d paramVector4d) {
    switch (paramInt) {
      case 0:
        this.m00 = paramVector4d.x;
        this.m01 = paramVector4d.y;
        this.m02 = paramVector4d.z;
        this.m03 = paramVector4d.w;
        return;
      case 1:
        this.m10 = paramVector4d.x;
        this.m11 = paramVector4d.y;
        this.m12 = paramVector4d.z;
        this.m13 = paramVector4d.w;
        return;
      case 2:
        this.m20 = paramVector4d.x;
        this.m21 = paramVector4d.y;
        this.m22 = paramVector4d.z;
        this.m23 = paramVector4d.w;
        return;
      case 3:
        this.m30 = paramVector4d.x;
        this.m31 = paramVector4d.y;
        this.m32 = paramVector4d.z;
        this.m33 = paramVector4d.w;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d4"));
  }
  
  public final void setRow(int paramInt, double[] paramArrayOfdouble) {
    switch (paramInt) {
      case 0:
        this.m00 = paramArrayOfdouble[0];
        this.m01 = paramArrayOfdouble[1];
        this.m02 = paramArrayOfdouble[2];
        this.m03 = paramArrayOfdouble[3];
        return;
      case 1:
        this.m10 = paramArrayOfdouble[0];
        this.m11 = paramArrayOfdouble[1];
        this.m12 = paramArrayOfdouble[2];
        this.m13 = paramArrayOfdouble[3];
        return;
      case 2:
        this.m20 = paramArrayOfdouble[0];
        this.m21 = paramArrayOfdouble[1];
        this.m22 = paramArrayOfdouble[2];
        this.m23 = paramArrayOfdouble[3];
        return;
      case 3:
        this.m30 = paramArrayOfdouble[0];
        this.m31 = paramArrayOfdouble[1];
        this.m32 = paramArrayOfdouble[2];
        this.m33 = paramArrayOfdouble[3];
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d4"));
  }
  
  public final void setColumn(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    switch (paramInt) {
      case 0:
        this.m00 = paramDouble1;
        this.m10 = paramDouble2;
        this.m20 = paramDouble3;
        this.m30 = paramDouble4;
        return;
      case 1:
        this.m01 = paramDouble1;
        this.m11 = paramDouble2;
        this.m21 = paramDouble3;
        this.m31 = paramDouble4;
        return;
      case 2:
        this.m02 = paramDouble1;
        this.m12 = paramDouble2;
        this.m22 = paramDouble3;
        this.m32 = paramDouble4;
        return;
      case 3:
        this.m03 = paramDouble1;
        this.m13 = paramDouble2;
        this.m23 = paramDouble3;
        this.m33 = paramDouble4;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d7"));
  }
  
  public final void setColumn(int paramInt, Vector4d paramVector4d) {
    switch (paramInt) {
      case 0:
        this.m00 = paramVector4d.x;
        this.m10 = paramVector4d.y;
        this.m20 = paramVector4d.z;
        this.m30 = paramVector4d.w;
        return;
      case 1:
        this.m01 = paramVector4d.x;
        this.m11 = paramVector4d.y;
        this.m21 = paramVector4d.z;
        this.m31 = paramVector4d.w;
        return;
      case 2:
        this.m02 = paramVector4d.x;
        this.m12 = paramVector4d.y;
        this.m22 = paramVector4d.z;
        this.m32 = paramVector4d.w;
        return;
      case 3:
        this.m03 = paramVector4d.x;
        this.m13 = paramVector4d.y;
        this.m23 = paramVector4d.z;
        this.m33 = paramVector4d.w;
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d7"));
  }
  
  public final void setColumn(int paramInt, double[] paramArrayOfdouble) {
    switch (paramInt) {
      case 0:
        this.m00 = paramArrayOfdouble[0];
        this.m10 = paramArrayOfdouble[1];
        this.m20 = paramArrayOfdouble[2];
        this.m30 = paramArrayOfdouble[3];
        return;
      case 1:
        this.m01 = paramArrayOfdouble[0];
        this.m11 = paramArrayOfdouble[1];
        this.m21 = paramArrayOfdouble[2];
        this.m31 = paramArrayOfdouble[3];
        return;
      case 2:
        this.m02 = paramArrayOfdouble[0];
        this.m12 = paramArrayOfdouble[1];
        this.m22 = paramArrayOfdouble[2];
        this.m32 = paramArrayOfdouble[3];
        return;
      case 3:
        this.m03 = paramArrayOfdouble[0];
        this.m13 = paramArrayOfdouble[1];
        this.m23 = paramArrayOfdouble[2];
        this.m33 = paramArrayOfdouble[3];
        return;
    } 
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4d7"));
  }
  
  public final void add(double paramDouble) {
    this.m00 += paramDouble;
    this.m01 += paramDouble;
    this.m02 += paramDouble;
    this.m03 += paramDouble;
    this.m10 += paramDouble;
    this.m11 += paramDouble;
    this.m12 += paramDouble;
    this.m13 += paramDouble;
    this.m20 += paramDouble;
    this.m21 += paramDouble;
    this.m22 += paramDouble;
    this.m23 += paramDouble;
    this.m30 += paramDouble;
    this.m31 += paramDouble;
    this.m32 += paramDouble;
    this.m33 += paramDouble;
  }
  
  public final void add(double paramDouble, Matrix4d paramMatrix4d) {
    paramMatrix4d.m00 += paramDouble;
    paramMatrix4d.m01 += paramDouble;
    paramMatrix4d.m02 += paramDouble;
    paramMatrix4d.m03 += paramDouble;
    paramMatrix4d.m10 += paramDouble;
    paramMatrix4d.m11 += paramDouble;
    paramMatrix4d.m12 += paramDouble;
    paramMatrix4d.m13 += paramDouble;
    paramMatrix4d.m20 += paramDouble;
    paramMatrix4d.m21 += paramDouble;
    paramMatrix4d.m22 += paramDouble;
    paramMatrix4d.m23 += paramDouble;
    paramMatrix4d.m30 += paramDouble;
    paramMatrix4d.m31 += paramDouble;
    paramMatrix4d.m32 += paramDouble;
    paramMatrix4d.m33 += paramDouble;
  }
  
  public final void add(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
    paramMatrix4d1.m00 += paramMatrix4d2.m00;
    paramMatrix4d1.m01 += paramMatrix4d2.m01;
    paramMatrix4d1.m02 += paramMatrix4d2.m02;
    paramMatrix4d1.m03 += paramMatrix4d2.m03;
    paramMatrix4d1.m10 += paramMatrix4d2.m10;
    paramMatrix4d1.m11 += paramMatrix4d2.m11;
    paramMatrix4d1.m12 += paramMatrix4d2.m12;
    paramMatrix4d1.m13 += paramMatrix4d2.m13;
    paramMatrix4d1.m20 += paramMatrix4d2.m20;
    paramMatrix4d1.m21 += paramMatrix4d2.m21;
    paramMatrix4d1.m22 += paramMatrix4d2.m22;
    paramMatrix4d1.m23 += paramMatrix4d2.m23;
    paramMatrix4d1.m30 += paramMatrix4d2.m30;
    paramMatrix4d1.m31 += paramMatrix4d2.m31;
    paramMatrix4d1.m32 += paramMatrix4d2.m32;
    paramMatrix4d1.m33 += paramMatrix4d2.m33;
  }
  
  public final void add(Matrix4d paramMatrix4d) {
    this.m00 += paramMatrix4d.m00;
    this.m01 += paramMatrix4d.m01;
    this.m02 += paramMatrix4d.m02;
    this.m03 += paramMatrix4d.m03;
    this.m10 += paramMatrix4d.m10;
    this.m11 += paramMatrix4d.m11;
    this.m12 += paramMatrix4d.m12;
    this.m13 += paramMatrix4d.m13;
    this.m20 += paramMatrix4d.m20;
    this.m21 += paramMatrix4d.m21;
    this.m22 += paramMatrix4d.m22;
    this.m23 += paramMatrix4d.m23;
    this.m30 += paramMatrix4d.m30;
    this.m31 += paramMatrix4d.m31;
    this.m32 += paramMatrix4d.m32;
    this.m33 += paramMatrix4d.m33;
  }
  
  public final void sub(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
    paramMatrix4d1.m00 -= paramMatrix4d2.m00;
    paramMatrix4d1.m01 -= paramMatrix4d2.m01;
    paramMatrix4d1.m02 -= paramMatrix4d2.m02;
    paramMatrix4d1.m03 -= paramMatrix4d2.m03;
    paramMatrix4d1.m10 -= paramMatrix4d2.m10;
    paramMatrix4d1.m11 -= paramMatrix4d2.m11;
    paramMatrix4d1.m12 -= paramMatrix4d2.m12;
    paramMatrix4d1.m13 -= paramMatrix4d2.m13;
    paramMatrix4d1.m20 -= paramMatrix4d2.m20;
    paramMatrix4d1.m21 -= paramMatrix4d2.m21;
    paramMatrix4d1.m22 -= paramMatrix4d2.m22;
    paramMatrix4d1.m23 -= paramMatrix4d2.m23;
    paramMatrix4d1.m30 -= paramMatrix4d2.m30;
    paramMatrix4d1.m31 -= paramMatrix4d2.m31;
    paramMatrix4d1.m32 -= paramMatrix4d2.m32;
    paramMatrix4d1.m33 -= paramMatrix4d2.m33;
  }
  
  public final void sub(Matrix4d paramMatrix4d) {
    this.m00 -= paramMatrix4d.m00;
    this.m01 -= paramMatrix4d.m01;
    this.m02 -= paramMatrix4d.m02;
    this.m03 -= paramMatrix4d.m03;
    this.m10 -= paramMatrix4d.m10;
    this.m11 -= paramMatrix4d.m11;
    this.m12 -= paramMatrix4d.m12;
    this.m13 -= paramMatrix4d.m13;
    this.m20 -= paramMatrix4d.m20;
    this.m21 -= paramMatrix4d.m21;
    this.m22 -= paramMatrix4d.m22;
    this.m23 -= paramMatrix4d.m23;
    this.m30 -= paramMatrix4d.m30;
    this.m31 -= paramMatrix4d.m31;
    this.m32 -= paramMatrix4d.m32;
    this.m33 -= paramMatrix4d.m33;
  }
  
  public final void transpose() {
    double d = this.m10;
    this.m10 = this.m01;
    this.m01 = d;
    d = this.m20;
    this.m20 = this.m02;
    this.m02 = d;
    d = this.m30;
    this.m30 = this.m03;
    this.m03 = d;
    d = this.m21;
    this.m21 = this.m12;
    this.m12 = d;
    d = this.m31;
    this.m31 = this.m13;
    this.m13 = d;
    d = this.m32;
    this.m32 = this.m23;
    this.m23 = d;
  }
  
  public final void transpose(Matrix4d paramMatrix4d) {
    if (this != paramMatrix4d) {
      this.m00 = paramMatrix4d.m00;
      this.m01 = paramMatrix4d.m10;
      this.m02 = paramMatrix4d.m20;
      this.m03 = paramMatrix4d.m30;
      this.m10 = paramMatrix4d.m01;
      this.m11 = paramMatrix4d.m11;
      this.m12 = paramMatrix4d.m21;
      this.m13 = paramMatrix4d.m31;
      this.m20 = paramMatrix4d.m02;
      this.m21 = paramMatrix4d.m12;
      this.m22 = paramMatrix4d.m22;
      this.m23 = paramMatrix4d.m32;
      this.m30 = paramMatrix4d.m03;
      this.m31 = paramMatrix4d.m13;
      this.m32 = paramMatrix4d.m23;
      this.m33 = paramMatrix4d.m33;
    } else {
      transpose();
    } 
  }
  
  public final void set(double[] paramArrayOfdouble) {
    this.m00 = paramArrayOfdouble[0];
    this.m01 = paramArrayOfdouble[1];
    this.m02 = paramArrayOfdouble[2];
    this.m03 = paramArrayOfdouble[3];
    this.m10 = paramArrayOfdouble[4];
    this.m11 = paramArrayOfdouble[5];
    this.m12 = paramArrayOfdouble[6];
    this.m13 = paramArrayOfdouble[7];
    this.m20 = paramArrayOfdouble[8];
    this.m21 = paramArrayOfdouble[9];
    this.m22 = paramArrayOfdouble[10];
    this.m23 = paramArrayOfdouble[11];
    this.m30 = paramArrayOfdouble[12];
    this.m31 = paramArrayOfdouble[13];
    this.m32 = paramArrayOfdouble[14];
    this.m33 = paramArrayOfdouble[15];
  }
  
  public final void set(Matrix3f paramMatrix3f) {
    this.m00 = paramMatrix3f.m00;
    this.m01 = paramMatrix3f.m01;
    this.m02 = paramMatrix3f.m02;
    this.m03 = 0.0D;
    this.m10 = paramMatrix3f.m10;
    this.m11 = paramMatrix3f.m11;
    this.m12 = paramMatrix3f.m12;
    this.m13 = 0.0D;
    this.m20 = paramMatrix3f.m20;
    this.m21 = paramMatrix3f.m21;
    this.m22 = paramMatrix3f.m22;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Matrix3d paramMatrix3d) {
    this.m00 = paramMatrix3d.m00;
    this.m01 = paramMatrix3d.m01;
    this.m02 = paramMatrix3d.m02;
    this.m03 = 0.0D;
    this.m10 = paramMatrix3d.m10;
    this.m11 = paramMatrix3d.m11;
    this.m12 = paramMatrix3d.m12;
    this.m13 = 0.0D;
    this.m20 = paramMatrix3d.m20;
    this.m21 = paramMatrix3d.m21;
    this.m22 = paramMatrix3d.m22;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
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
    this.m03 = 0.0D;
    this.m13 = 0.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d) {
    double d = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
    if (d < 1.0E-10D) {
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
    this.m03 = 0.0D;
    this.m13 = 0.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
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
    this.m03 = 0.0D;
    this.m13 = 0.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f) {
    double d = Math.sqrt((paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z));
    if (d < 1.0E-10D) {
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
    this.m03 = 0.0D;
    this.m13 = 0.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Quat4d paramQuat4d, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
    this.m10 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z);
    this.m20 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y);
    this.m01 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z);
    this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
    this.m21 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x);
    this.m02 = paramDouble * 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y);
    this.m12 = paramDouble * 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x);
    this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
    this.m03 = paramVector3d.x;
    this.m13 = paramVector3d.y;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Quat4f paramQuat4f, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramDouble * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m10 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
    this.m20 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
    this.m01 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
    this.m11 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m21 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
    this.m02 = paramDouble * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
    this.m12 = paramDouble * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
    this.m22 = paramDouble * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
    this.m03 = paramVector3d.x;
    this.m13 = paramVector3d.y;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Quat4f paramQuat4f, Vector3f paramVector3f, float paramFloat) {
    this.m00 = paramFloat * (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m10 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z);
    this.m20 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y);
    this.m01 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z);
    this.m11 = paramFloat * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m21 = paramFloat * 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x);
    this.m02 = paramFloat * 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y);
    this.m12 = paramFloat * 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x);
    this.m22 = paramFloat * (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
    this.m03 = paramVector3f.x;
    this.m13 = paramVector3f.y;
    this.m23 = paramVector3f.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Matrix4f paramMatrix4f) {
    this.m00 = paramMatrix4f.m00;
    this.m01 = paramMatrix4f.m01;
    this.m02 = paramMatrix4f.m02;
    this.m03 = paramMatrix4f.m03;
    this.m10 = paramMatrix4f.m10;
    this.m11 = paramMatrix4f.m11;
    this.m12 = paramMatrix4f.m12;
    this.m13 = paramMatrix4f.m13;
    this.m20 = paramMatrix4f.m20;
    this.m21 = paramMatrix4f.m21;
    this.m22 = paramMatrix4f.m22;
    this.m23 = paramMatrix4f.m23;
    this.m30 = paramMatrix4f.m30;
    this.m31 = paramMatrix4f.m31;
    this.m32 = paramMatrix4f.m32;
    this.m33 = paramMatrix4f.m33;
  }
  
  public final void set(Matrix4d paramMatrix4d) {
    this.m00 = paramMatrix4d.m00;
    this.m01 = paramMatrix4d.m01;
    this.m02 = paramMatrix4d.m02;
    this.m03 = paramMatrix4d.m03;
    this.m10 = paramMatrix4d.m10;
    this.m11 = paramMatrix4d.m11;
    this.m12 = paramMatrix4d.m12;
    this.m13 = paramMatrix4d.m13;
    this.m20 = paramMatrix4d.m20;
    this.m21 = paramMatrix4d.m21;
    this.m22 = paramMatrix4d.m22;
    this.m23 = paramMatrix4d.m23;
    this.m30 = paramMatrix4d.m30;
    this.m31 = paramMatrix4d.m31;
    this.m32 = paramMatrix4d.m32;
    this.m33 = paramMatrix4d.m33;
  }
  
  public final void invert(Matrix4d paramMatrix4d) {
    invertGeneral(paramMatrix4d);
  }
  
  public final void invert() {
    invertGeneral(this);
  }
  
  final void invertGeneral(Matrix4d paramMatrix4d) {
    double[] arrayOfDouble1 = new double[16];
    int[] arrayOfInt = new int[4];
    double[] arrayOfDouble2 = new double[16];
    arrayOfDouble2[0] = paramMatrix4d.m00;
    arrayOfDouble2[1] = paramMatrix4d.m01;
    arrayOfDouble2[2] = paramMatrix4d.m02;
    arrayOfDouble2[3] = paramMatrix4d.m03;
    arrayOfDouble2[4] = paramMatrix4d.m10;
    arrayOfDouble2[5] = paramMatrix4d.m11;
    arrayOfDouble2[6] = paramMatrix4d.m12;
    arrayOfDouble2[7] = paramMatrix4d.m13;
    arrayOfDouble2[8] = paramMatrix4d.m20;
    arrayOfDouble2[9] = paramMatrix4d.m21;
    arrayOfDouble2[10] = paramMatrix4d.m22;
    arrayOfDouble2[11] = paramMatrix4d.m23;
    arrayOfDouble2[12] = paramMatrix4d.m30;
    arrayOfDouble2[13] = paramMatrix4d.m31;
    arrayOfDouble2[14] = paramMatrix4d.m32;
    arrayOfDouble2[15] = paramMatrix4d.m33;
    if (!luDecomposition(arrayOfDouble2, arrayOfInt))
      throw new SingularMatrixException(VecMathI18N.getString("Matrix4d10")); 
    for (byte b = 0; b < 16; b++)
      arrayOfDouble1[b] = 0.0D; 
    arrayOfDouble1[0] = 1.0D;
    arrayOfDouble1[5] = 1.0D;
    arrayOfDouble1[10] = 1.0D;
    arrayOfDouble1[15] = 1.0D;
    luBacksubstitution(arrayOfDouble2, arrayOfInt, arrayOfDouble1);
    this.m00 = arrayOfDouble1[0];
    this.m01 = arrayOfDouble1[1];
    this.m02 = arrayOfDouble1[2];
    this.m03 = arrayOfDouble1[3];
    this.m10 = arrayOfDouble1[4];
    this.m11 = arrayOfDouble1[5];
    this.m12 = arrayOfDouble1[6];
    this.m13 = arrayOfDouble1[7];
    this.m20 = arrayOfDouble1[8];
    this.m21 = arrayOfDouble1[9];
    this.m22 = arrayOfDouble1[10];
    this.m23 = arrayOfDouble1[11];
    this.m30 = arrayOfDouble1[12];
    this.m31 = arrayOfDouble1[13];
    this.m32 = arrayOfDouble1[14];
    this.m33 = arrayOfDouble1[15];
  }
  
  static boolean luDecomposition(double[] paramArrayOfdouble, int[] paramArrayOfint) {
    double[] arrayOfDouble = new double[4];
    int j = 0;
    int k = 0;
    int i = 4;
    while (i-- != 0) {
      double d = 0.0D;
      int m = 4;
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
    for (i = 0; i < 4; i++) {
      for (j = 0; j < i; j++) {
        int n = b + 4 * j + i;
        double d1 = paramArrayOfdouble[n];
        int m = j;
        int i1 = b + 4 * j;
        for (int i2 = b + i; m-- != 0; i2 += 4) {
          d1 -= paramArrayOfdouble[i1] * paramArrayOfdouble[i2];
          i1++;
        } 
        paramArrayOfdouble[n] = d1;
      } 
      double d = 0.0D;
      k = -1;
      for (j = i; j < 4; j++) {
        int n = b + 4 * j + i;
        double d1 = paramArrayOfdouble[n];
        int m = i;
        int i1 = b + 4 * j;
        for (int i2 = b + i; m-- != 0; i2 += 4) {
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
        throw new RuntimeException(VecMathI18N.getString("Matrix4d11")); 
      if (i != k) {
        int m = 4;
        int n = b + 4 * k;
        int i1 = b + 4 * i;
        while (m-- != 0) {
          double d1 = paramArrayOfdouble[n];
          paramArrayOfdouble[n++] = paramArrayOfdouble[i1];
          paramArrayOfdouble[i1++] = d1;
        } 
        arrayOfDouble[k] = arrayOfDouble[i];
      } 
      paramArrayOfint[i] = k;
      if (paramArrayOfdouble[b + 4 * i + i] == 0.0D)
        return false; 
      if (i != 3) {
        double d1 = 1.0D / paramArrayOfdouble[b + 4 * i + i];
        int m = b + 4 * (i + 1) + i;
        j = 3 - i;
        while (j-- != 0) {
          paramArrayOfdouble[m] = paramArrayOfdouble[m] * d1;
          m += 4;
        } 
      } 
    } 
    return true;
  }
  
  static void luBacksubstitution(double[] paramArrayOfdouble1, int[] paramArrayOfint, double[] paramArrayOfdouble2) {
    byte b2 = 0;
    for (byte b1 = 0; b1 < 4; b1++) {
      byte b4 = b1;
      byte b = -1;
      for (byte b3 = 0; b3 < 4; b3++) {
        int i = paramArrayOfint[b2 + b3];
        double d = paramArrayOfdouble2[b4 + 4 * i];
        paramArrayOfdouble2[b4 + 4 * i] = paramArrayOfdouble2[b4 + 4 * b3];
        if (b >= 0) {
          int j = b3 * 4;
          for (byte b6 = b; b6 <= b3 - 1; b6++)
            d -= paramArrayOfdouble1[j + b6] * paramArrayOfdouble2[b4 + 4 * b6]; 
        } else if (d != 0.0D) {
          b = b3;
        } 
        paramArrayOfdouble2[b4 + 4 * b3] = d;
      } 
      byte b5 = 12;
      paramArrayOfdouble2[b4 + 12] = paramArrayOfdouble2[b4 + 12] / paramArrayOfdouble1[b5 + 3];
      b5 -= 4;
      paramArrayOfdouble2[b4 + 8] = (paramArrayOfdouble2[b4 + 8] - paramArrayOfdouble1[b5 + 3] * paramArrayOfdouble2[b4 + 12]) / paramArrayOfdouble1[b5 + 2];
      b5 -= 4;
      paramArrayOfdouble2[b4 + 4] = (paramArrayOfdouble2[b4 + 4] - paramArrayOfdouble1[b5 + 2] * paramArrayOfdouble2[b4 + 8] - paramArrayOfdouble1[b5 + 3] * paramArrayOfdouble2[b4 + 12]) / paramArrayOfdouble1[b5 + 1];
      b5 -= 4;
      paramArrayOfdouble2[b4 + 0] = (paramArrayOfdouble2[b4 + 0] - paramArrayOfdouble1[b5 + 1] * paramArrayOfdouble2[b4 + 4] - paramArrayOfdouble1[b5 + 2] * paramArrayOfdouble2[b4 + 8] - paramArrayOfdouble1[b5 + 3] * paramArrayOfdouble2[b4 + 12]) / paramArrayOfdouble1[b5 + 0];
    } 
  }
  
  public final double determinant() {
    double d = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
    d -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
    d += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
    d -= this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
    return d;
  }
  
  public final void set(double paramDouble) {
    this.m00 = paramDouble;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = paramDouble;
    this.m12 = 0.0D;
    this.m13 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = paramDouble;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Vector3d paramVector3d) {
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = paramVector3d.x;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m13 = paramVector3d.y;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(double paramDouble, Vector3d paramVector3d) {
    this.m00 = paramDouble;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = paramVector3d.x;
    this.m10 = 0.0D;
    this.m11 = paramDouble;
    this.m12 = 0.0D;
    this.m13 = paramVector3d.y;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = paramDouble;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramDouble;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = paramDouble * paramVector3d.x;
    this.m10 = 0.0D;
    this.m11 = paramDouble;
    this.m12 = 0.0D;
    this.m13 = paramDouble * paramVector3d.y;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = paramDouble;
    this.m23 = paramDouble * paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Matrix3f paramMatrix3f, Vector3f paramVector3f, float paramFloat) {
    this.m00 = (paramMatrix3f.m00 * paramFloat);
    this.m01 = (paramMatrix3f.m01 * paramFloat);
    this.m02 = (paramMatrix3f.m02 * paramFloat);
    this.m03 = paramVector3f.x;
    this.m10 = (paramMatrix3f.m10 * paramFloat);
    this.m11 = (paramMatrix3f.m11 * paramFloat);
    this.m12 = (paramMatrix3f.m12 * paramFloat);
    this.m13 = paramVector3f.y;
    this.m20 = (paramMatrix3f.m20 * paramFloat);
    this.m21 = (paramMatrix3f.m21 * paramFloat);
    this.m22 = (paramMatrix3f.m22 * paramFloat);
    this.m23 = paramVector3f.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void set(Matrix3d paramMatrix3d, Vector3d paramVector3d, double paramDouble) {
    this.m00 = paramMatrix3d.m00 * paramDouble;
    this.m01 = paramMatrix3d.m01 * paramDouble;
    this.m02 = paramMatrix3d.m02 * paramDouble;
    this.m03 = paramVector3d.x;
    this.m10 = paramMatrix3d.m10 * paramDouble;
    this.m11 = paramMatrix3d.m11 * paramDouble;
    this.m12 = paramMatrix3d.m12 * paramDouble;
    this.m13 = paramVector3d.y;
    this.m20 = paramMatrix3d.m20 * paramDouble;
    this.m21 = paramMatrix3d.m21 * paramDouble;
    this.m22 = paramMatrix3d.m22 * paramDouble;
    this.m23 = paramVector3d.z;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void setTranslation(Vector3d paramVector3d) {
    this.m03 = paramVector3d.x;
    this.m13 = paramVector3d.y;
    this.m23 = paramVector3d.z;
  }
  
  public final void rotX(double paramDouble) {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = d2;
    this.m12 = -d1;
    this.m13 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = d1;
    this.m22 = d2;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void rotY(double paramDouble) {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = d2;
    this.m01 = 0.0D;
    this.m02 = d1;
    this.m03 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m13 = 0.0D;
    this.m20 = -d1;
    this.m21 = 0.0D;
    this.m22 = d2;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void rotZ(double paramDouble) {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = d2;
    this.m01 = -d1;
    this.m02 = 0.0D;
    this.m03 = 0.0D;
    this.m10 = d1;
    this.m11 = d2;
    this.m12 = 0.0D;
    this.m13 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 1.0D;
  }
  
  public final void mul(double paramDouble) {
    this.m00 *= paramDouble;
    this.m01 *= paramDouble;
    this.m02 *= paramDouble;
    this.m03 *= paramDouble;
    this.m10 *= paramDouble;
    this.m11 *= paramDouble;
    this.m12 *= paramDouble;
    this.m13 *= paramDouble;
    this.m20 *= paramDouble;
    this.m21 *= paramDouble;
    this.m22 *= paramDouble;
    this.m23 *= paramDouble;
    this.m30 *= paramDouble;
    this.m31 *= paramDouble;
    this.m32 *= paramDouble;
    this.m33 *= paramDouble;
  }
  
  public final void mul(double paramDouble, Matrix4d paramMatrix4d) {
    paramMatrix4d.m00 *= paramDouble;
    paramMatrix4d.m01 *= paramDouble;
    paramMatrix4d.m02 *= paramDouble;
    paramMatrix4d.m03 *= paramDouble;
    paramMatrix4d.m10 *= paramDouble;
    paramMatrix4d.m11 *= paramDouble;
    paramMatrix4d.m12 *= paramDouble;
    paramMatrix4d.m13 *= paramDouble;
    paramMatrix4d.m20 *= paramDouble;
    paramMatrix4d.m21 *= paramDouble;
    paramMatrix4d.m22 *= paramDouble;
    paramMatrix4d.m23 *= paramDouble;
    paramMatrix4d.m30 *= paramDouble;
    paramMatrix4d.m31 *= paramDouble;
    paramMatrix4d.m32 *= paramDouble;
    paramMatrix4d.m33 *= paramDouble;
  }
  
  public final void mul(Matrix4d paramMatrix4d) {
    double d1 = this.m00 * paramMatrix4d.m00 + this.m01 * paramMatrix4d.m10 + this.m02 * paramMatrix4d.m20 + this.m03 * paramMatrix4d.m30;
    double d2 = this.m00 * paramMatrix4d.m01 + this.m01 * paramMatrix4d.m11 + this.m02 * paramMatrix4d.m21 + this.m03 * paramMatrix4d.m31;
    double d3 = this.m00 * paramMatrix4d.m02 + this.m01 * paramMatrix4d.m12 + this.m02 * paramMatrix4d.m22 + this.m03 * paramMatrix4d.m32;
    double d4 = this.m00 * paramMatrix4d.m03 + this.m01 * paramMatrix4d.m13 + this.m02 * paramMatrix4d.m23 + this.m03 * paramMatrix4d.m33;
    double d5 = this.m10 * paramMatrix4d.m00 + this.m11 * paramMatrix4d.m10 + this.m12 * paramMatrix4d.m20 + this.m13 * paramMatrix4d.m30;
    double d6 = this.m10 * paramMatrix4d.m01 + this.m11 * paramMatrix4d.m11 + this.m12 * paramMatrix4d.m21 + this.m13 * paramMatrix4d.m31;
    double d7 = this.m10 * paramMatrix4d.m02 + this.m11 * paramMatrix4d.m12 + this.m12 * paramMatrix4d.m22 + this.m13 * paramMatrix4d.m32;
    double d8 = this.m10 * paramMatrix4d.m03 + this.m11 * paramMatrix4d.m13 + this.m12 * paramMatrix4d.m23 + this.m13 * paramMatrix4d.m33;
    double d9 = this.m20 * paramMatrix4d.m00 + this.m21 * paramMatrix4d.m10 + this.m22 * paramMatrix4d.m20 + this.m23 * paramMatrix4d.m30;
    double d10 = this.m20 * paramMatrix4d.m01 + this.m21 * paramMatrix4d.m11 + this.m22 * paramMatrix4d.m21 + this.m23 * paramMatrix4d.m31;
    double d11 = this.m20 * paramMatrix4d.m02 + this.m21 * paramMatrix4d.m12 + this.m22 * paramMatrix4d.m22 + this.m23 * paramMatrix4d.m32;
    double d12 = this.m20 * paramMatrix4d.m03 + this.m21 * paramMatrix4d.m13 + this.m22 * paramMatrix4d.m23 + this.m23 * paramMatrix4d.m33;
    double d13 = this.m30 * paramMatrix4d.m00 + this.m31 * paramMatrix4d.m10 + this.m32 * paramMatrix4d.m20 + this.m33 * paramMatrix4d.m30;
    double d14 = this.m30 * paramMatrix4d.m01 + this.m31 * paramMatrix4d.m11 + this.m32 * paramMatrix4d.m21 + this.m33 * paramMatrix4d.m31;
    double d15 = this.m30 * paramMatrix4d.m02 + this.m31 * paramMatrix4d.m12 + this.m32 * paramMatrix4d.m22 + this.m33 * paramMatrix4d.m32;
    double d16 = this.m30 * paramMatrix4d.m03 + this.m31 * paramMatrix4d.m13 + this.m32 * paramMatrix4d.m23 + this.m33 * paramMatrix4d.m33;
    this.m00 = d1;
    this.m01 = d2;
    this.m02 = d3;
    this.m03 = d4;
    this.m10 = d5;
    this.m11 = d6;
    this.m12 = d7;
    this.m13 = d8;
    this.m20 = d9;
    this.m21 = d10;
    this.m22 = d11;
    this.m23 = d12;
    this.m30 = d13;
    this.m31 = d14;
    this.m32 = d15;
    this.m33 = d16;
  }
  
  public final void mul(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
    if (this != paramMatrix4d1 && this != paramMatrix4d2) {
      this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m03 * paramMatrix4d2.m30;
      this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m21 + paramMatrix4d1.m03 * paramMatrix4d2.m31;
      this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m01 * paramMatrix4d2.m12 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m32;
      this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m01 * paramMatrix4d2.m13 + paramMatrix4d1.m02 * paramMatrix4d2.m23 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
      this.m10 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m30;
      this.m11 = paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m13 * paramMatrix4d2.m31;
      this.m12 = paramMatrix4d1.m10 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m32;
      this.m13 = paramMatrix4d1.m10 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m12 * paramMatrix4d2.m23 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
      this.m20 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m23 * paramMatrix4d2.m30;
      this.m21 = paramMatrix4d1.m20 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m31;
      this.m22 = paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m32;
      this.m23 = paramMatrix4d1.m20 * paramMatrix4d2.m03 + paramMatrix4d1.m21 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
      this.m30 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m10 + paramMatrix4d1.m32 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
      this.m31 = paramMatrix4d1.m30 * paramMatrix4d2.m01 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
      this.m32 = paramMatrix4d1.m30 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
      this.m33 = paramMatrix4d1.m30 * paramMatrix4d2.m03 + paramMatrix4d1.m31 * paramMatrix4d2.m13 + paramMatrix4d1.m32 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
    } else {
      double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m03 * paramMatrix4d2.m30;
      double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m21 + paramMatrix4d1.m03 * paramMatrix4d2.m31;
      double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m01 * paramMatrix4d2.m12 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m32;
      double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m01 * paramMatrix4d2.m13 + paramMatrix4d1.m02 * paramMatrix4d2.m23 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
      double d5 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m30;
      double d6 = paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m13 * paramMatrix4d2.m31;
      double d7 = paramMatrix4d1.m10 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m32;
      double d8 = paramMatrix4d1.m10 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m12 * paramMatrix4d2.m23 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
      double d9 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m23 * paramMatrix4d2.m30;
      double d10 = paramMatrix4d1.m20 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m31;
      double d11 = paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m32;
      double d12 = paramMatrix4d1.m20 * paramMatrix4d2.m03 + paramMatrix4d1.m21 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
      double d13 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m10 + paramMatrix4d1.m32 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
      double d14 = paramMatrix4d1.m30 * paramMatrix4d2.m01 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
      double d15 = paramMatrix4d1.m30 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
      double d16 = paramMatrix4d1.m30 * paramMatrix4d2.m03 + paramMatrix4d1.m31 * paramMatrix4d2.m13 + paramMatrix4d1.m32 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m03 = d4;
      this.m10 = d5;
      this.m11 = d6;
      this.m12 = d7;
      this.m13 = d8;
      this.m20 = d9;
      this.m21 = d10;
      this.m22 = d11;
      this.m23 = d12;
      this.m30 = d13;
      this.m31 = d14;
      this.m32 = d15;
      this.m33 = d16;
    } 
  }
  
  public final void mulTransposeBoth(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
    if (this != paramMatrix4d1 && this != paramMatrix4d2) {
      this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m30 * paramMatrix4d2.m03;
      this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m12 + paramMatrix4d1.m30 * paramMatrix4d2.m13;
      this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m10 * paramMatrix4d2.m21 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m23;
      this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m10 * paramMatrix4d2.m31 + paramMatrix4d1.m20 * paramMatrix4d2.m32 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
      this.m10 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m03;
      this.m11 = paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m31 * paramMatrix4d2.m13;
      this.m12 = paramMatrix4d1.m01 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m23;
      this.m13 = paramMatrix4d1.m01 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m21 * paramMatrix4d2.m32 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
      this.m20 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m32 * paramMatrix4d2.m03;
      this.m21 = paramMatrix4d1.m02 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m13;
      this.m22 = paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m23;
      this.m23 = paramMatrix4d1.m02 * paramMatrix4d2.m30 + paramMatrix4d1.m12 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
      this.m30 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m01 + paramMatrix4d1.m23 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
      this.m31 = paramMatrix4d1.m03 * paramMatrix4d2.m10 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
      this.m32 = paramMatrix4d1.m03 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
      this.m33 = paramMatrix4d1.m03 * paramMatrix4d2.m30 + paramMatrix4d1.m13 * paramMatrix4d2.m31 + paramMatrix4d1.m23 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
    } else {
      double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m01 + paramMatrix4d1.m20 * paramMatrix4d2.m02 + paramMatrix4d1.m30 * paramMatrix4d2.m03;
      double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m12 + paramMatrix4d1.m30 * paramMatrix4d2.m13;
      double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m10 * paramMatrix4d2.m21 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m23;
      double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m10 * paramMatrix4d2.m31 + paramMatrix4d1.m20 * paramMatrix4d2.m32 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
      double d5 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m21 * paramMatrix4d2.m02 + paramMatrix4d1.m31 * paramMatrix4d2.m03;
      double d6 = paramMatrix4d1.m01 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m12 + paramMatrix4d1.m31 * paramMatrix4d2.m13;
      double d7 = paramMatrix4d1.m01 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m23;
      double d8 = paramMatrix4d1.m01 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m21 * paramMatrix4d2.m32 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
      double d9 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m32 * paramMatrix4d2.m03;
      double d10 = paramMatrix4d1.m02 * paramMatrix4d2.m10 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m32 * paramMatrix4d2.m13;
      double d11 = paramMatrix4d1.m02 * paramMatrix4d2.m20 + paramMatrix4d1.m12 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m23;
      double d12 = paramMatrix4d1.m02 * paramMatrix4d2.m30 + paramMatrix4d1.m12 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
      double d13 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m01 + paramMatrix4d1.m23 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
      double d14 = paramMatrix4d1.m03 * paramMatrix4d2.m10 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
      double d15 = paramMatrix4d1.m03 * paramMatrix4d2.m20 + paramMatrix4d1.m13 * paramMatrix4d2.m21 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
      double d16 = paramMatrix4d1.m03 * paramMatrix4d2.m30 + paramMatrix4d1.m13 * paramMatrix4d2.m31 + paramMatrix4d1.m23 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m03 = d4;
      this.m10 = d5;
      this.m11 = d6;
      this.m12 = d7;
      this.m13 = d8;
      this.m20 = d9;
      this.m21 = d10;
      this.m22 = d11;
      this.m23 = d12;
      this.m30 = d13;
      this.m31 = d14;
      this.m32 = d15;
      this.m33 = d16;
    } 
  }
  
  public final void mulTransposeRight(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
    if (this != paramMatrix4d1 && this != paramMatrix4d2) {
      this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m03 * paramMatrix4d2.m03;
      this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m12 + paramMatrix4d1.m03 * paramMatrix4d2.m13;
      this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m01 * paramMatrix4d2.m21 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m23;
      this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m01 * paramMatrix4d2.m31 + paramMatrix4d1.m02 * paramMatrix4d2.m32 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
      this.m10 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m03;
      this.m11 = paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m13 * paramMatrix4d2.m13;
      this.m12 = paramMatrix4d1.m10 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m23;
      this.m13 = paramMatrix4d1.m10 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m12 * paramMatrix4d2.m32 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
      this.m20 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m23 * paramMatrix4d2.m03;
      this.m21 = paramMatrix4d1.m20 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m13;
      this.m22 = paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m23;
      this.m23 = paramMatrix4d1.m20 * paramMatrix4d2.m30 + paramMatrix4d1.m21 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
      this.m30 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m01 + paramMatrix4d1.m32 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
      this.m31 = paramMatrix4d1.m30 * paramMatrix4d2.m10 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
      this.m32 = paramMatrix4d1.m30 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
      this.m33 = paramMatrix4d1.m30 * paramMatrix4d2.m30 + paramMatrix4d1.m31 * paramMatrix4d2.m31 + paramMatrix4d1.m32 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
    } else {
      double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m03 * paramMatrix4d2.m03;
      double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m10 + paramMatrix4d1.m01 * paramMatrix4d2.m11 + paramMatrix4d1.m02 * paramMatrix4d2.m12 + paramMatrix4d1.m03 * paramMatrix4d2.m13;
      double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m20 + paramMatrix4d1.m01 * paramMatrix4d2.m21 + paramMatrix4d1.m02 * paramMatrix4d2.m22 + paramMatrix4d1.m03 * paramMatrix4d2.m23;
      double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m30 + paramMatrix4d1.m01 * paramMatrix4d2.m31 + paramMatrix4d1.m02 * paramMatrix4d2.m32 + paramMatrix4d1.m03 * paramMatrix4d2.m33;
      double d5 = paramMatrix4d1.m10 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m03;
      double d6 = paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m13 * paramMatrix4d2.m13;
      double d7 = paramMatrix4d1.m10 * paramMatrix4d2.m20 + paramMatrix4d1.m11 * paramMatrix4d2.m21 + paramMatrix4d1.m12 * paramMatrix4d2.m22 + paramMatrix4d1.m13 * paramMatrix4d2.m23;
      double d8 = paramMatrix4d1.m10 * paramMatrix4d2.m30 + paramMatrix4d1.m11 * paramMatrix4d2.m31 + paramMatrix4d1.m12 * paramMatrix4d2.m32 + paramMatrix4d1.m13 * paramMatrix4d2.m33;
      double d9 = paramMatrix4d1.m20 * paramMatrix4d2.m00 + paramMatrix4d1.m21 * paramMatrix4d2.m01 + paramMatrix4d1.m22 * paramMatrix4d2.m02 + paramMatrix4d1.m23 * paramMatrix4d2.m03;
      double d10 = paramMatrix4d1.m20 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m13;
      double d11 = paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m23 * paramMatrix4d2.m23;
      double d12 = paramMatrix4d1.m20 * paramMatrix4d2.m30 + paramMatrix4d1.m21 * paramMatrix4d2.m31 + paramMatrix4d1.m22 * paramMatrix4d2.m32 + paramMatrix4d1.m23 * paramMatrix4d2.m33;
      double d13 = paramMatrix4d1.m30 * paramMatrix4d2.m00 + paramMatrix4d1.m31 * paramMatrix4d2.m01 + paramMatrix4d1.m32 * paramMatrix4d2.m02 + paramMatrix4d1.m33 * paramMatrix4d2.m03;
      double d14 = paramMatrix4d1.m30 * paramMatrix4d2.m10 + paramMatrix4d1.m31 * paramMatrix4d2.m11 + paramMatrix4d1.m32 * paramMatrix4d2.m12 + paramMatrix4d1.m33 * paramMatrix4d2.m13;
      double d15 = paramMatrix4d1.m30 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m23;
      double d16 = paramMatrix4d1.m30 * paramMatrix4d2.m30 + paramMatrix4d1.m31 * paramMatrix4d2.m31 + paramMatrix4d1.m32 * paramMatrix4d2.m32 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m03 = d4;
      this.m10 = d5;
      this.m11 = d6;
      this.m12 = d7;
      this.m13 = d8;
      this.m20 = d9;
      this.m21 = d10;
      this.m22 = d11;
      this.m23 = d12;
      this.m30 = d13;
      this.m31 = d14;
      this.m32 = d15;
      this.m33 = d16;
    } 
  }
  
  public final void mulTransposeLeft(Matrix4d paramMatrix4d1, Matrix4d paramMatrix4d2) {
    if (this != paramMatrix4d1 && this != paramMatrix4d2) {
      this.m00 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m30 * paramMatrix4d2.m30;
      this.m01 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m21 + paramMatrix4d1.m30 * paramMatrix4d2.m31;
      this.m02 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m10 * paramMatrix4d2.m12 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m32;
      this.m03 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m10 * paramMatrix4d2.m13 + paramMatrix4d1.m20 * paramMatrix4d2.m23 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
      this.m10 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m30;
      this.m11 = paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m31 * paramMatrix4d2.m31;
      this.m12 = paramMatrix4d1.m01 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m32;
      this.m13 = paramMatrix4d1.m01 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m21 * paramMatrix4d2.m23 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
      this.m20 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m32 * paramMatrix4d2.m30;
      this.m21 = paramMatrix4d1.m02 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m31;
      this.m22 = paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m32;
      this.m23 = paramMatrix4d1.m02 * paramMatrix4d2.m03 + paramMatrix4d1.m12 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
      this.m30 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m10 + paramMatrix4d1.m23 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
      this.m31 = paramMatrix4d1.m03 * paramMatrix4d2.m01 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
      this.m32 = paramMatrix4d1.m03 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
      this.m33 = paramMatrix4d1.m03 * paramMatrix4d2.m03 + paramMatrix4d1.m13 * paramMatrix4d2.m13 + paramMatrix4d1.m23 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
    } else {
      double d1 = paramMatrix4d1.m00 * paramMatrix4d2.m00 + paramMatrix4d1.m10 * paramMatrix4d2.m10 + paramMatrix4d1.m20 * paramMatrix4d2.m20 + paramMatrix4d1.m30 * paramMatrix4d2.m30;
      double d2 = paramMatrix4d1.m00 * paramMatrix4d2.m01 + paramMatrix4d1.m10 * paramMatrix4d2.m11 + paramMatrix4d1.m20 * paramMatrix4d2.m21 + paramMatrix4d1.m30 * paramMatrix4d2.m31;
      double d3 = paramMatrix4d1.m00 * paramMatrix4d2.m02 + paramMatrix4d1.m10 * paramMatrix4d2.m12 + paramMatrix4d1.m20 * paramMatrix4d2.m22 + paramMatrix4d1.m30 * paramMatrix4d2.m32;
      double d4 = paramMatrix4d1.m00 * paramMatrix4d2.m03 + paramMatrix4d1.m10 * paramMatrix4d2.m13 + paramMatrix4d1.m20 * paramMatrix4d2.m23 + paramMatrix4d1.m30 * paramMatrix4d2.m33;
      double d5 = paramMatrix4d1.m01 * paramMatrix4d2.m00 + paramMatrix4d1.m11 * paramMatrix4d2.m10 + paramMatrix4d1.m21 * paramMatrix4d2.m20 + paramMatrix4d1.m31 * paramMatrix4d2.m30;
      double d6 = paramMatrix4d1.m01 * paramMatrix4d2.m01 + paramMatrix4d1.m11 * paramMatrix4d2.m11 + paramMatrix4d1.m21 * paramMatrix4d2.m21 + paramMatrix4d1.m31 * paramMatrix4d2.m31;
      double d7 = paramMatrix4d1.m01 * paramMatrix4d2.m02 + paramMatrix4d1.m11 * paramMatrix4d2.m12 + paramMatrix4d1.m21 * paramMatrix4d2.m22 + paramMatrix4d1.m31 * paramMatrix4d2.m32;
      double d8 = paramMatrix4d1.m01 * paramMatrix4d2.m03 + paramMatrix4d1.m11 * paramMatrix4d2.m13 + paramMatrix4d1.m21 * paramMatrix4d2.m23 + paramMatrix4d1.m31 * paramMatrix4d2.m33;
      double d9 = paramMatrix4d1.m02 * paramMatrix4d2.m00 + paramMatrix4d1.m12 * paramMatrix4d2.m10 + paramMatrix4d1.m22 * paramMatrix4d2.m20 + paramMatrix4d1.m32 * paramMatrix4d2.m30;
      double d10 = paramMatrix4d1.m02 * paramMatrix4d2.m01 + paramMatrix4d1.m12 * paramMatrix4d2.m11 + paramMatrix4d1.m22 * paramMatrix4d2.m21 + paramMatrix4d1.m32 * paramMatrix4d2.m31;
      double d11 = paramMatrix4d1.m02 * paramMatrix4d2.m02 + paramMatrix4d1.m12 * paramMatrix4d2.m12 + paramMatrix4d1.m22 * paramMatrix4d2.m22 + paramMatrix4d1.m32 * paramMatrix4d2.m32;
      double d12 = paramMatrix4d1.m02 * paramMatrix4d2.m03 + paramMatrix4d1.m12 * paramMatrix4d2.m13 + paramMatrix4d1.m22 * paramMatrix4d2.m23 + paramMatrix4d1.m32 * paramMatrix4d2.m33;
      double d13 = paramMatrix4d1.m03 * paramMatrix4d2.m00 + paramMatrix4d1.m13 * paramMatrix4d2.m10 + paramMatrix4d1.m23 * paramMatrix4d2.m20 + paramMatrix4d1.m33 * paramMatrix4d2.m30;
      double d14 = paramMatrix4d1.m03 * paramMatrix4d2.m01 + paramMatrix4d1.m13 * paramMatrix4d2.m11 + paramMatrix4d1.m23 * paramMatrix4d2.m21 + paramMatrix4d1.m33 * paramMatrix4d2.m31;
      double d15 = paramMatrix4d1.m03 * paramMatrix4d2.m02 + paramMatrix4d1.m13 * paramMatrix4d2.m12 + paramMatrix4d1.m23 * paramMatrix4d2.m22 + paramMatrix4d1.m33 * paramMatrix4d2.m32;
      double d16 = paramMatrix4d1.m03 * paramMatrix4d2.m03 + paramMatrix4d1.m13 * paramMatrix4d2.m13 + paramMatrix4d1.m23 * paramMatrix4d2.m23 + paramMatrix4d1.m33 * paramMatrix4d2.m33;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m03 = d4;
      this.m10 = d5;
      this.m11 = d6;
      this.m12 = d7;
      this.m13 = d8;
      this.m20 = d9;
      this.m21 = d10;
      this.m22 = d11;
      this.m23 = d12;
      this.m30 = d13;
      this.m31 = d14;
      this.m32 = d15;
      this.m33 = d16;
    } 
  }
  
  public boolean equals(Matrix4d paramMatrix4d) {
    try {
      return (this.m00 == paramMatrix4d.m00 && this.m01 == paramMatrix4d.m01 && this.m02 == paramMatrix4d.m02 && this.m03 == paramMatrix4d.m03 && this.m10 == paramMatrix4d.m10 && this.m11 == paramMatrix4d.m11 && this.m12 == paramMatrix4d.m12 && this.m13 == paramMatrix4d.m13 && this.m20 == paramMatrix4d.m20 && this.m21 == paramMatrix4d.m21 && this.m22 == paramMatrix4d.m22 && this.m23 == paramMatrix4d.m23 && this.m30 == paramMatrix4d.m30 && this.m31 == paramMatrix4d.m31 && this.m32 == paramMatrix4d.m32 && this.m33 == paramMatrix4d.m33);
    } catch (NullPointerException nullPointerException) {
      return false;
    } 
  }
  
  public boolean equals(Object paramObject) {
    try {
      Matrix4d matrix4d = (Matrix4d)paramObject;
      return (this.m00 == matrix4d.m00 && this.m01 == matrix4d.m01 && this.m02 == matrix4d.m02 && this.m03 == matrix4d.m03 && this.m10 == matrix4d.m10 && this.m11 == matrix4d.m11 && this.m12 == matrix4d.m12 && this.m13 == matrix4d.m13 && this.m20 == matrix4d.m20 && this.m21 == matrix4d.m21 && this.m22 == matrix4d.m22 && this.m23 == matrix4d.m23 && this.m30 == matrix4d.m30 && this.m31 == matrix4d.m31 && this.m32 == matrix4d.m32 && this.m33 == matrix4d.m33);
    } catch (ClassCastException classCastException) {
      return false;
    } catch (NullPointerException nullPointerException) {
      return false;
    } 
  }
  
  public boolean epsilonEquals(Matrix4d paramMatrix4d, float paramFloat) {
    return epsilonEquals(paramMatrix4d, paramFloat);
  }
  
  public boolean epsilonEquals(Matrix4d paramMatrix4d, double paramDouble) {
    double d = this.m00 - paramMatrix4d.m00;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m01 - paramMatrix4d.m01;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m02 - paramMatrix4d.m02;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m03 - paramMatrix4d.m03;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m10 - paramMatrix4d.m10;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m11 - paramMatrix4d.m11;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m12 - paramMatrix4d.m12;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m13 - paramMatrix4d.m13;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m20 - paramMatrix4d.m20;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m21 - paramMatrix4d.m21;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m22 - paramMatrix4d.m22;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m23 - paramMatrix4d.m23;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m30 - paramMatrix4d.m30;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m31 - paramMatrix4d.m31;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m32 - paramMatrix4d.m32;
    if (((d < 0.0D) ? -d : d) > paramDouble)
      return false; 
    d = this.m33 - paramMatrix4d.m33;
    return !(((d < 0.0D) ? -d : d) > paramDouble);
  }
  
  public int hashCode() {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m00);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m01);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m02);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m03);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m10);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m11);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m12);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m13);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m20);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m21);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m22);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m23);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m30);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m31);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m32);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m33);
    return (int)(l ^ l >> 32L);
  }
  
  public final void transform(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2) {
    double d1 = this.m00 * paramTuple4d1.x + this.m01 * paramTuple4d1.y + this.m02 * paramTuple4d1.z + this.m03 * paramTuple4d1.w;
    double d2 = this.m10 * paramTuple4d1.x + this.m11 * paramTuple4d1.y + this.m12 * paramTuple4d1.z + this.m13 * paramTuple4d1.w;
    double d3 = this.m20 * paramTuple4d1.x + this.m21 * paramTuple4d1.y + this.m22 * paramTuple4d1.z + this.m23 * paramTuple4d1.w;
    paramTuple4d2.w = this.m30 * paramTuple4d1.x + this.m31 * paramTuple4d1.y + this.m32 * paramTuple4d1.z + this.m33 * paramTuple4d1.w;
    paramTuple4d2.x = d1;
    paramTuple4d2.y = d2;
    paramTuple4d2.z = d3;
  }
  
  public final void transform(Tuple4d paramTuple4d) {
    double d1 = this.m00 * paramTuple4d.x + this.m01 * paramTuple4d.y + this.m02 * paramTuple4d.z + this.m03 * paramTuple4d.w;
    double d2 = this.m10 * paramTuple4d.x + this.m11 * paramTuple4d.y + this.m12 * paramTuple4d.z + this.m13 * paramTuple4d.w;
    double d3 = this.m20 * paramTuple4d.x + this.m21 * paramTuple4d.y + this.m22 * paramTuple4d.z + this.m23 * paramTuple4d.w;
    paramTuple4d.w = this.m30 * paramTuple4d.x + this.m31 * paramTuple4d.y + this.m32 * paramTuple4d.z + this.m33 * paramTuple4d.w;
    paramTuple4d.x = d1;
    paramTuple4d.y = d2;
    paramTuple4d.z = d3;
  }
  
  public final void transform(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2) {
    float f1 = (float)(this.m00 * paramTuple4f1.x + this.m01 * paramTuple4f1.y + this.m02 * paramTuple4f1.z + this.m03 * paramTuple4f1.w);
    float f2 = (float)(this.m10 * paramTuple4f1.x + this.m11 * paramTuple4f1.y + this.m12 * paramTuple4f1.z + this.m13 * paramTuple4f1.w);
    float f3 = (float)(this.m20 * paramTuple4f1.x + this.m21 * paramTuple4f1.y + this.m22 * paramTuple4f1.z + this.m23 * paramTuple4f1.w);
    paramTuple4f2.w = (float)(this.m30 * paramTuple4f1.x + this.m31 * paramTuple4f1.y + this.m32 * paramTuple4f1.z + this.m33 * paramTuple4f1.w);
    paramTuple4f2.x = f1;
    paramTuple4f2.y = f2;
    paramTuple4f2.z = f3;
  }
  
  public final void transform(Tuple4f paramTuple4f) {
    float f1 = (float)(this.m00 * paramTuple4f.x + this.m01 * paramTuple4f.y + this.m02 * paramTuple4f.z + this.m03 * paramTuple4f.w);
    float f2 = (float)(this.m10 * paramTuple4f.x + this.m11 * paramTuple4f.y + this.m12 * paramTuple4f.z + this.m13 * paramTuple4f.w);
    float f3 = (float)(this.m20 * paramTuple4f.x + this.m21 * paramTuple4f.y + this.m22 * paramTuple4f.z + this.m23 * paramTuple4f.w);
    paramTuple4f.w = (float)(this.m30 * paramTuple4f.x + this.m31 * paramTuple4f.y + this.m32 * paramTuple4f.z + this.m33 * paramTuple4f.w);
    paramTuple4f.x = f1;
    paramTuple4f.y = f2;
    paramTuple4f.z = f3;
  }
  
  public final void transform(Point3d paramPoint3d1, Point3d paramPoint3d2) {
    double d1 = this.m00 * paramPoint3d1.x + this.m01 * paramPoint3d1.y + this.m02 * paramPoint3d1.z + this.m03;
    double d2 = this.m10 * paramPoint3d1.x + this.m11 * paramPoint3d1.y + this.m12 * paramPoint3d1.z + this.m13;
    paramPoint3d2.z = this.m20 * paramPoint3d1.x + this.m21 * paramPoint3d1.y + this.m22 * paramPoint3d1.z + this.m23;
    paramPoint3d2.x = d1;
    paramPoint3d2.y = d2;
  }
  
  public final void transform(Point3d paramPoint3d) {
    double d1 = this.m00 * paramPoint3d.x + this.m01 * paramPoint3d.y + this.m02 * paramPoint3d.z + this.m03;
    double d2 = this.m10 * paramPoint3d.x + this.m11 * paramPoint3d.y + this.m12 * paramPoint3d.z + this.m13;
    paramPoint3d.z = this.m20 * paramPoint3d.x + this.m21 * paramPoint3d.y + this.m22 * paramPoint3d.z + this.m23;
    paramPoint3d.x = d1;
    paramPoint3d.y = d2;
  }
  
  public final void transform(Point3f paramPoint3f1, Point3f paramPoint3f2) {
    float f1 = (float)(this.m00 * paramPoint3f1.x + this.m01 * paramPoint3f1.y + this.m02 * paramPoint3f1.z + this.m03);
    float f2 = (float)(this.m10 * paramPoint3f1.x + this.m11 * paramPoint3f1.y + this.m12 * paramPoint3f1.z + this.m13);
    paramPoint3f2.z = (float)(this.m20 * paramPoint3f1.x + this.m21 * paramPoint3f1.y + this.m22 * paramPoint3f1.z + this.m23);
    paramPoint3f2.x = f1;
    paramPoint3f2.y = f2;
  }
  
  public final void transform(Point3f paramPoint3f) {
    float f1 = (float)(this.m00 * paramPoint3f.x + this.m01 * paramPoint3f.y + this.m02 * paramPoint3f.z + this.m03);
    float f2 = (float)(this.m10 * paramPoint3f.x + this.m11 * paramPoint3f.y + this.m12 * paramPoint3f.z + this.m13);
    paramPoint3f.z = (float)(this.m20 * paramPoint3f.x + this.m21 * paramPoint3f.y + this.m22 * paramPoint3f.z + this.m23);
    paramPoint3f.x = f1;
    paramPoint3f.y = f2;
  }
  
  public final void transform(Vector3d paramVector3d1, Vector3d paramVector3d2) {
    double d1 = this.m00 * paramVector3d1.x + this.m01 * paramVector3d1.y + this.m02 * paramVector3d1.z;
    double d2 = this.m10 * paramVector3d1.x + this.m11 * paramVector3d1.y + this.m12 * paramVector3d1.z;
    paramVector3d2.z = this.m20 * paramVector3d1.x + this.m21 * paramVector3d1.y + this.m22 * paramVector3d1.z;
    paramVector3d2.x = d1;
    paramVector3d2.y = d2;
  }
  
  public final void transform(Vector3d paramVector3d) {
    double d1 = this.m00 * paramVector3d.x + this.m01 * paramVector3d.y + this.m02 * paramVector3d.z;
    double d2 = this.m10 * paramVector3d.x + this.m11 * paramVector3d.y + this.m12 * paramVector3d.z;
    paramVector3d.z = this.m20 * paramVector3d.x + this.m21 * paramVector3d.y + this.m22 * paramVector3d.z;
    paramVector3d.x = d1;
    paramVector3d.y = d2;
  }
  
  public final void transform(Vector3f paramVector3f1, Vector3f paramVector3f2) {
    float f1 = (float)(this.m00 * paramVector3f1.x + this.m01 * paramVector3f1.y + this.m02 * paramVector3f1.z);
    float f2 = (float)(this.m10 * paramVector3f1.x + this.m11 * paramVector3f1.y + this.m12 * paramVector3f1.z);
    paramVector3f2.z = (float)(this.m20 * paramVector3f1.x + this.m21 * paramVector3f1.y + this.m22 * paramVector3f1.z);
    paramVector3f2.x = f1;
    paramVector3f2.y = f2;
  }
  
  public final void transform(Vector3f paramVector3f) {
    float f1 = (float)(this.m00 * paramVector3f.x + this.m01 * paramVector3f.y + this.m02 * paramVector3f.z);
    float f2 = (float)(this.m10 * paramVector3f.x + this.m11 * paramVector3f.y + this.m12 * paramVector3f.z);
    paramVector3f.z = (float)(this.m20 * paramVector3f.x + this.m21 * paramVector3f.y + this.m22 * paramVector3f.z);
    paramVector3f.x = f1;
    paramVector3f.y = f2;
  }
  
  public final void setRotation(Matrix3d paramMatrix3d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = paramMatrix3d.m00 * arrayOfDouble2[0];
    this.m01 = paramMatrix3d.m01 * arrayOfDouble2[1];
    this.m02 = paramMatrix3d.m02 * arrayOfDouble2[2];
    this.m10 = paramMatrix3d.m10 * arrayOfDouble2[0];
    this.m11 = paramMatrix3d.m11 * arrayOfDouble2[1];
    this.m12 = paramMatrix3d.m12 * arrayOfDouble2[2];
    this.m20 = paramMatrix3d.m20 * arrayOfDouble2[0];
    this.m21 = paramMatrix3d.m21 * arrayOfDouble2[1];
    this.m22 = paramMatrix3d.m22 * arrayOfDouble2[2];
  }
  
  public final void setRotation(Matrix3f paramMatrix3f) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = paramMatrix3f.m00 * arrayOfDouble2[0];
    this.m01 = paramMatrix3f.m01 * arrayOfDouble2[1];
    this.m02 = paramMatrix3f.m02 * arrayOfDouble2[2];
    this.m10 = paramMatrix3f.m10 * arrayOfDouble2[0];
    this.m11 = paramMatrix3f.m11 * arrayOfDouble2[1];
    this.m12 = paramMatrix3f.m12 * arrayOfDouble2[2];
    this.m20 = paramMatrix3f.m20 * arrayOfDouble2[0];
    this.m21 = paramMatrix3f.m21 * arrayOfDouble2[1];
    this.m22 = paramMatrix3f.m22 * arrayOfDouble2[2];
  }
  
  public final void setRotation(Quat4f paramQuat4f) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = (1.0D - (2.0F * paramQuat4f.y * paramQuat4f.y) - (2.0F * paramQuat4f.z * paramQuat4f.z)) * arrayOfDouble2[0];
    this.m10 = 2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z) * arrayOfDouble2[0];
    this.m20 = 2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y) * arrayOfDouble2[0];
    this.m01 = 2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z) * arrayOfDouble2[1];
    this.m11 = (1.0D - (2.0F * paramQuat4f.x * paramQuat4f.x) - (2.0F * paramQuat4f.z * paramQuat4f.z)) * arrayOfDouble2[1];
    this.m21 = 2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x) * arrayOfDouble2[1];
    this.m02 = 2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y) * arrayOfDouble2[2];
    this.m12 = 2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x) * arrayOfDouble2[2];
    this.m22 = (1.0D - (2.0F * paramQuat4f.x * paramQuat4f.x) - (2.0F * paramQuat4f.y * paramQuat4f.y)) * arrayOfDouble2[2];
  }
  
  public final void setRotation(Quat4d paramQuat4d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z) * arrayOfDouble2[0];
    this.m10 = 2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z) * arrayOfDouble2[0];
    this.m20 = 2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y) * arrayOfDouble2[0];
    this.m01 = 2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z) * arrayOfDouble2[1];
    this.m11 = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z) * arrayOfDouble2[1];
    this.m21 = 2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x) * arrayOfDouble2[1];
    this.m02 = 2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y) * arrayOfDouble2[2];
    this.m12 = 2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x) * arrayOfDouble2[2];
    this.m22 = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y) * arrayOfDouble2[2];
  }
  
  public final void setRotation(AxisAngle4d paramAxisAngle4d) {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    double d1 = 1.0D / Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
    double d2 = paramAxisAngle4d.x * d1;
    double d3 = paramAxisAngle4d.y * d1;
    double d4 = paramAxisAngle4d.z * d1;
    double d5 = Math.sin(paramAxisAngle4d.angle);
    double d6 = Math.cos(paramAxisAngle4d.angle);
    double d7 = 1.0D - d6;
    double d8 = paramAxisAngle4d.x * paramAxisAngle4d.z;
    double d9 = paramAxisAngle4d.x * paramAxisAngle4d.y;
    double d10 = paramAxisAngle4d.y * paramAxisAngle4d.z;
    this.m00 = (d7 * d2 * d2 + d6) * arrayOfDouble2[0];
    this.m01 = (d7 * d9 - d5 * d4) * arrayOfDouble2[1];
    this.m02 = (d7 * d8 + d5 * d3) * arrayOfDouble2[2];
    this.m10 = (d7 * d9 + d5 * d4) * arrayOfDouble2[0];
    this.m11 = (d7 * d3 * d3 + d6) * arrayOfDouble2[1];
    this.m12 = (d7 * d10 - d5 * d2) * arrayOfDouble2[2];
    this.m20 = (d7 * d8 - d5 * d3) * arrayOfDouble2[0];
    this.m21 = (d7 * d10 + d5 * d2) * arrayOfDouble2[1];
    this.m22 = (d7 * d4 * d4 + d6) * arrayOfDouble2[2];
  }
  
  public final void setZero() {
    this.m00 = 0.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m03 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 0.0D;
    this.m12 = 0.0D;
    this.m13 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 0.0D;
    this.m23 = 0.0D;
    this.m30 = 0.0D;
    this.m31 = 0.0D;
    this.m32 = 0.0D;
    this.m33 = 0.0D;
  }
  
  public final void negate() {
    this.m00 = -this.m00;
    this.m01 = -this.m01;
    this.m02 = -this.m02;
    this.m03 = -this.m03;
    this.m10 = -this.m10;
    this.m11 = -this.m11;
    this.m12 = -this.m12;
    this.m13 = -this.m13;
    this.m20 = -this.m20;
    this.m21 = -this.m21;
    this.m22 = -this.m22;
    this.m23 = -this.m23;
    this.m30 = -this.m30;
    this.m31 = -this.m31;
    this.m32 = -this.m32;
    this.m33 = -this.m33;
  }
  
  public final void negate(Matrix4d paramMatrix4d) {
    this.m00 = -paramMatrix4d.m00;
    this.m01 = -paramMatrix4d.m01;
    this.m02 = -paramMatrix4d.m02;
    this.m03 = -paramMatrix4d.m03;
    this.m10 = -paramMatrix4d.m10;
    this.m11 = -paramMatrix4d.m11;
    this.m12 = -paramMatrix4d.m12;
    this.m13 = -paramMatrix4d.m13;
    this.m20 = -paramMatrix4d.m20;
    this.m21 = -paramMatrix4d.m21;
    this.m22 = -paramMatrix4d.m22;
    this.m23 = -paramMatrix4d.m23;
    this.m30 = -paramMatrix4d.m30;
    this.m31 = -paramMatrix4d.m31;
    this.m32 = -paramMatrix4d.m32;
    this.m33 = -paramMatrix4d.m33;
  }
  
  private final void getScaleRotate(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2) {
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
    Matrix3d.compute_svd(arrayOfDouble, paramArrayOfdouble1, paramArrayOfdouble2);
  }
  
  public Object clone() {
    Matrix4d matrix4d = null;
    try {
      matrix4d = (Matrix4d)super.clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new InternalError();
    } 
    return matrix4d;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Matrix4d.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */