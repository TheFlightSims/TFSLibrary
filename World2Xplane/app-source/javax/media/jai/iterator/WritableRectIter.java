package javax.media.jai.iterator;

public interface WritableRectIter extends RectIter {
  void setSample(int paramInt);
  
  void setSample(int paramInt1, int paramInt2);
  
  void setSample(float paramFloat);
  
  void setSample(int paramInt, float paramFloat);
  
  void setSample(double paramDouble);
  
  void setSample(int paramInt, double paramDouble);
  
  void setPixel(int[] paramArrayOfint);
  
  void setPixel(float[] paramArrayOffloat);
  
  void setPixel(double[] paramArrayOfdouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\WritableRectIter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */