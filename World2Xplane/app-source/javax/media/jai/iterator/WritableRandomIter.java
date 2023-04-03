package javax.media.jai.iterator;

public interface WritableRandomIter extends RandomIter {
  void setSample(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  void setSample(int paramInt1, int paramInt2, int paramInt3, float paramFloat);
  
  void setSample(int paramInt1, int paramInt2, int paramInt3, double paramDouble);
  
  void setPixel(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  void setPixel(int paramInt1, int paramInt2, float[] paramArrayOffloat);
  
  void setPixel(int paramInt1, int paramInt2, double[] paramArrayOfdouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\WritableRandomIter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */