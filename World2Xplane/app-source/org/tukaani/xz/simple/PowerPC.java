package org.tukaani.xz.simple;

public final class PowerPC implements SimpleFilter {
  private final boolean isEncoder;
  
  private int pos;
  
  public PowerPC(boolean paramBoolean, int paramInt) {
    this.isEncoder = paramBoolean;
    this.pos = paramInt;
  }
  
  public int code(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2 - 4;
    int j;
    for (j = paramInt1; j <= i; j += 4) {
      if ((paramArrayOfbyte[j] & 0xFC) == 72 && (paramArrayOfbyte[j + 3] & 0x3) == 1) {
        int m;
        int k = (paramArrayOfbyte[j] & 0x3) << 24 | (paramArrayOfbyte[j + 1] & 0xFF) << 16 | (paramArrayOfbyte[j + 2] & 0xFF) << 8 | paramArrayOfbyte[j + 3] & 0xFC;
        if (this.isEncoder) {
          m = k + this.pos + j - paramInt1;
        } else {
          m = k - this.pos + j - paramInt1;
        } 
        paramArrayOfbyte[j] = (byte)(0x48 | m >>> 24 & 0x3);
        paramArrayOfbyte[j + 1] = (byte)(m >>> 16);
        paramArrayOfbyte[j + 2] = (byte)(m >>> 8);
        paramArrayOfbyte[j + 3] = (byte)(paramArrayOfbyte[j + 3] & 0x3 | m);
      } 
    } 
    j -= paramInt1;
    this.pos += j;
    return j;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\simple\PowerPC.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */