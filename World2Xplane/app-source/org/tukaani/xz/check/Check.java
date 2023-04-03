package org.tukaani.xz.check;

import java.security.NoSuchAlgorithmException;
import org.tukaani.xz.UnsupportedOptionsException;

public abstract class Check {
  int size;
  
  String name;
  
  public abstract void update(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public abstract byte[] finish();
  
  public void update(byte[] paramArrayOfbyte) {
    update(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int getSize() {
    return this.size;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static Check getInstance(int paramInt) throws UnsupportedOptionsException {
    switch (paramInt) {
      case 0:
        return new None();
      case 1:
        return new CRC32();
      case 4:
        return new CRC64();
      case 10:
        try {
          return new SHA256();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
          break;
        } 
    } 
    throw new UnsupportedOptionsException("Unsupported Check ID " + paramInt);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\check\Check.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */