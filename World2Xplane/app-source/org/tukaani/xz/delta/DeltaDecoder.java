package org.tukaani.xz.delta;

public class DeltaDecoder extends DeltaCoder {
  public DeltaDecoder(int paramInt) {
    super(paramInt);
  }
  
  public void decode(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2;
    for (int j = paramInt1; j < i; j++) {
      paramArrayOfbyte[j] = (byte)(paramArrayOfbyte[j] + this.history[this.distance + this.pos & 0xFF]);
      this.history[this.pos-- & 0xFF] = paramArrayOfbyte[j];
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\delta\DeltaDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */