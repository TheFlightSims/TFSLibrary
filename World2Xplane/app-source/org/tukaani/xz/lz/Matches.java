package org.tukaani.xz.lz;

public final class Matches {
  public final int[] len;
  
  public final int[] dist;
  
  public int count = 0;
  
  Matches(int paramInt) {
    this.len = new int[paramInt];
    this.dist = new int[paramInt];
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\Matches.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */