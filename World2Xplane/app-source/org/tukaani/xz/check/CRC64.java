package org.tukaani.xz.check;

public class CRC64 extends Check {
  private static final long poly = -3932672073523589310L;
  
  private static final long[] crcTable = new long[256];
  
  private long crc = -1L;
  
  public void update(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2;
    while (paramInt1 < i)
      this.crc = crcTable[(paramArrayOfbyte[paramInt1++] ^ (int)this.crc) & 0xFF] ^ this.crc >>> 8L; 
  }
  
  public byte[] finish() {
    long l = this.crc ^ 0xFFFFFFFFFFFFFFFFL;
    this.crc = -1L;
    byte[] arrayOfByte = new byte[8];
    for (byte b = 0; b < arrayOfByte.length; b++)
      arrayOfByte[b] = (byte)(int)(l >> b * 8); 
    return arrayOfByte;
  }
  
  static {
    for (byte b = 0; b < crcTable.length; b++) {
      long l = b;
      for (byte b1 = 0; b1 < 8; b1++) {
        if ((l & 0x1L) == 1L) {
          l = l >>> 1L ^ 0xC96C5795D7870F42L;
        } else {
          l >>>= 1L;
        } 
      } 
      crcTable[b] = l;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\check\CRC64.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */