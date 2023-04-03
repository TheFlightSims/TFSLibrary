package org.tukaani.xz.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;

public class EncoderUtil extends Util {
  public static void writeCRC32(OutputStream paramOutputStream, byte[] paramArrayOfbyte) throws IOException {
    CRC32 cRC32 = new CRC32();
    cRC32.update(paramArrayOfbyte);
    long l = cRC32.getValue();
    for (byte b = 0; b < 4; b++)
      paramOutputStream.write((byte)(int)(l >>> b * 8)); 
  }
  
  public static void encodeVLI(OutputStream paramOutputStream, long paramLong) throws IOException {
    while (paramLong >= 128L) {
      paramOutputStream.write((byte)(int)(paramLong | 0x80L));
      paramLong >>>= 7L;
    } 
    paramOutputStream.write((byte)(int)paramLong);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\common\EncoderUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */