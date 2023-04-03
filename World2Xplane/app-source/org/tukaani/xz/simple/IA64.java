package org.tukaani.xz.simple;

public final class IA64 implements SimpleFilter {
  private static final int[] BRANCH_TABLE = new int[] { 
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
      0, 0, 0, 0, 0, 0, 4, 4, 6, 6, 
      0, 0, 7, 7, 4, 4, 0, 0, 4, 4, 
      0, 0 };
  
  private final boolean isEncoder;
  
  private int pos;
  
  public IA64(boolean paramBoolean, int paramInt) {
    this.isEncoder = paramBoolean;
    this.pos = paramInt;
  }
  
  public int code(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2 - 16;
    int j;
    for (j = paramInt1; j <= i; j += 16) {
      int k = paramArrayOfbyte[j] & 0x1F;
      int m = BRANCH_TABLE[k];
      byte b1 = 0;
      for (byte b2 = 5; b1 < 3; b2 += 41) {
        if ((m >>> b1 & 0x1) != 0) {
          int n = b2 >>> 3;
          int i1 = b2 & 0x7;
          long l1 = 0L;
          for (byte b = 0; b < 6; b++)
            l1 |= (paramArrayOfbyte[j + n + b] & 0xFFL) << 8 * b; 
          long l2 = l1 >>> i1;
          if ((l2 >>> 37L & 0xFL) == 5L && (l2 >>> 9L & 0x7L) == 0L) {
            int i3;
            int i2 = (int)(l2 >>> 13L & 0xFFFFFL);
            i2 |= ((int)(l2 >>> 36L) & 0x1) << 20;
            i2 <<= 4;
            if (this.isEncoder) {
              i3 = i2 + this.pos + j - paramInt1;
            } else {
              i3 = i2 - this.pos + j - paramInt1;
            } 
            i3 >>>= 4;
            l2 &= 0xFFFFFFEE00001FFFL;
            l2 |= (i3 & 0xFFFFFL) << 13L;
            l2 |= (i3 & 0x100000L) << 16L;
            l1 &= ((1 << i1) - 1);
            l1 |= l2 << i1;
            for (byte b3 = 0; b3 < 6; b3++)
              paramArrayOfbyte[j + n + b3] = (byte)(int)(l1 >>> 8 * b3); 
          } 
        } 
        b1++;
      } 
    } 
    j -= paramInt1;
    this.pos += j;
    return j;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\simple\IA64.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */