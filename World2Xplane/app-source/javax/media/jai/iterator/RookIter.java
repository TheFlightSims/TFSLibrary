package javax.media.jai.iterator;

public interface RookIter extends RectIter {
  void prevLine();
  
  boolean prevLineDone();
  
  void endLines();
  
  void prevPixel();
  
  boolean prevPixelDone();
  
  void endPixels();
  
  void prevBand();
  
  boolean prevBandDone();
  
  void endBands();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\RookIter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */