package org.tukaani.xz.check;

import java.security.MessageDigest;

public class SHA256 extends Check {
  private MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
  
  public void update(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.sha256.update(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public byte[] finish() {
    byte[] arrayOfByte = this.sha256.digest();
    this.sha256.reset();
    return arrayOfByte;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\check\SHA256.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */