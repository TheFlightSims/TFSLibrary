package org.tukaani.xz.common;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.XZ;
import org.tukaani.xz.XZFormatException;

public class DecoderUtil extends Util {
  public static boolean isCRC32Valid(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    CRC32 cRC32 = new CRC32();
    cRC32.update(paramArrayOfbyte, paramInt1, paramInt2);
    long l = cRC32.getValue();
    for (byte b = 0; b < 4; b++) {
      if ((byte)(int)(l >>> b * 8) != paramArrayOfbyte[paramInt3 + b])
        return false; 
    } 
    return true;
  }
  
  public static StreamFlags decodeStreamHeader(byte[] paramArrayOfbyte) throws IOException {
    for (byte b = 0; b < XZ.HEADER_MAGIC.length; b++) {
      if (paramArrayOfbyte[b] != XZ.HEADER_MAGIC[b])
        throw new XZFormatException(); 
    } 
    if (!isCRC32Valid(paramArrayOfbyte, XZ.HEADER_MAGIC.length, 2, XZ.HEADER_MAGIC.length + 2))
      throw new CorruptedInputException("XZ Stream Header is corrupt"); 
    try {
      return decodeStreamFlags(paramArrayOfbyte, XZ.HEADER_MAGIC.length);
    } catch (UnsupportedOptionsException unsupportedOptionsException) {
      throw new UnsupportedOptionsException("Unsupported options in XZ Stream Header");
    } 
  }
  
  public static StreamFlags decodeStreamFooter(byte[] paramArrayOfbyte) throws IOException {
    StreamFlags streamFlags;
    if (paramArrayOfbyte[10] != XZ.FOOTER_MAGIC[0] || paramArrayOfbyte[11] != XZ.FOOTER_MAGIC[1])
      throw new CorruptedInputException("XZ Stream Footer is corrupt"); 
    if (!isCRC32Valid(paramArrayOfbyte, 4, 6, 0))
      throw new CorruptedInputException("XZ Stream Footer is corrupt"); 
    try {
      streamFlags = decodeStreamFlags(paramArrayOfbyte, 8);
    } catch (UnsupportedOptionsException unsupportedOptionsException) {
      throw new UnsupportedOptionsException("Unsupported options in XZ Stream Footer");
    } 
    streamFlags.backwardSize = 0L;
    for (byte b = 0; b < 4; b++)
      streamFlags.backwardSize |= ((paramArrayOfbyte[b + 4] & 0xFF) << b * 8); 
    streamFlags.backwardSize = (streamFlags.backwardSize + 1L) * 4L;
    return streamFlags;
  }
  
  private static StreamFlags decodeStreamFlags(byte[] paramArrayOfbyte, int paramInt) throws UnsupportedOptionsException {
    if (paramArrayOfbyte[paramInt] != 0 || (paramArrayOfbyte[paramInt + 1] & 0xFF) >= 16)
      throw new UnsupportedOptionsException(); 
    StreamFlags streamFlags = new StreamFlags();
    streamFlags.checkType = paramArrayOfbyte[paramInt + 1];
    return streamFlags;
  }
  
  public static boolean areStreamFlagsEqual(StreamFlags paramStreamFlags1, StreamFlags paramStreamFlags2) {
    return (paramStreamFlags1.checkType == paramStreamFlags2.checkType);
  }
  
  public static long decodeVLI(InputStream paramInputStream) throws IOException {
    int i = paramInputStream.read();
    if (i == -1)
      throw new EOFException(); 
    long l = (i & 0x7F);
    byte b = 0;
    while ((i & 0x80) != 0) {
      if (++b >= 9)
        throw new CorruptedInputException(); 
      i = paramInputStream.read();
      if (i == -1)
        throw new EOFException(); 
      if (i == 0)
        throw new CorruptedInputException(); 
      l |= (i & 0x7F) << b * 7;
    } 
    return l;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\common\DecoderUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */