package org.tukaani.xz;

import java.io.InputStream;

public class DeltaOptions extends FilterOptions {
  public static final int DISTANCE_MIN = 1;
  
  public static final int DISTANCE_MAX = 256;
  
  private int distance = 1;
  
  static final boolean $assertionsDisabled;
  
  public DeltaOptions() {}
  
  public DeltaOptions(int paramInt) throws UnsupportedOptionsException {
    setDistance(paramInt);
  }
  
  public void setDistance(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 1 || paramInt > 256)
      throw new UnsupportedOptionsException("Delta distance must be in the range [1, 256]: " + paramInt); 
    this.distance = paramInt;
  }
  
  public int getDistance() {
    return this.distance;
  }
  
  public int getEncoderMemoryUsage() {
    return DeltaOutputStream.getMemoryUsage();
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return new DeltaOutputStream(paramFinishableOutputStream, this);
  }
  
  public int getDecoderMemoryUsage() {
    return 1;
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new DeltaInputStream(paramInputStream, this.distance);
  }
  
  FilterEncoder getFilterEncoder() {
    return new DeltaEncoder(this);
  }
  
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      assert false;
      throw new RuntimeException();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\DeltaOptions.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */