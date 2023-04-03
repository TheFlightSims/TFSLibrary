package javax.media.jai;

public interface ImageFunction {
  boolean isComplex();
  
  int getNumElements();
  
  void getElements(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOffloat1, float[] paramArrayOffloat2);
  
  void getElements(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt1, int paramInt2, int paramInt3, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ImageFunction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */