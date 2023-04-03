package javax.media.jai.iterator;

public interface RandomIter {
  int getSample(int paramInt1, int paramInt2, int paramInt3);
  
  float getSampleFloat(int paramInt1, int paramInt2, int paramInt3);
  
  double getSampleDouble(int paramInt1, int paramInt2, int paramInt3);
  
  int[] getPixel(int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  float[] getPixel(int paramInt1, int paramInt2, float[] paramArrayOffloat);
  
  double[] getPixel(int paramInt1, int paramInt2, double[] paramArrayOfdouble);
  
  void done();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\RandomIter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */