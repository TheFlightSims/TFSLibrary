package org.tukaani.xz.delta;

public class DeltaEncoder extends DeltaCoder {
  public DeltaEncoder(int paramInt) {
    super(paramInt);
  }
  
  public void encode(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) {
    for (byte b = 0; b < paramInt2; b++) {
      byte b1 = this.history[this.distance + this.pos & 0xFF];
      this.history[this.pos-- & 0xFF] = paramArrayOfbyte1[paramInt1 + b];
      paramArrayOfbyte2[b] = (byte)(paramArrayOfbyte1[paramInt1 + b] - b1);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\delta\DeltaEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */