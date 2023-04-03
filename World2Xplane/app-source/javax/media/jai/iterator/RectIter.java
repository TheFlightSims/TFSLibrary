package javax.media.jai.iterator;

public interface RectIter {
  void startLines();
  
  void nextLine();
  
  boolean nextLineDone();
  
  void jumpLines(int paramInt);
  
  boolean finishedLines();
  
  void startPixels();
  
  void nextPixel();
  
  boolean nextPixelDone();
  
  void jumpPixels(int paramInt);
  
  boolean finishedPixels();
  
  void startBands();
  
  void nextBand();
  
  boolean nextBandDone();
  
  boolean finishedBands();
  
  int getSample();
  
  int getSample(int paramInt);
  
  float getSampleFloat();
  
  float getSampleFloat(int paramInt);
  
  double getSampleDouble();
  
  double getSampleDouble(int paramInt);
  
  int[] getPixel(int[] paramArrayOfint);
  
  float[] getPixel(float[] paramArrayOffloat);
  
  double[] getPixel(double[] paramArrayOfdouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\RectIter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */