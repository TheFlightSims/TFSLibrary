package org.tukaani.xz.check;

public class CRC32 extends Check {
  private java.util.zip.CRC32 state = new java.util.zip.CRC32();
  
  public void update(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.state.update(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public byte[] finish() {
    long l = this.state.getValue();
    byte[] arrayOfByte = { (byte)(int)l, (byte)(int)(l >>> 8L), (byte)(int)(l >>> 16L), (byte)(int)(l >>> 24L) };
    this.state.reset();
    return arrayOfByte;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\check\CRC32.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */