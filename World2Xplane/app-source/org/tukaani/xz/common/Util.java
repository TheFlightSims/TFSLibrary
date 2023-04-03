package org.tukaani.xz.common;

public class Util {
  public static final int STREAM_HEADER_SIZE = 12;
  
  public static final long BACKWARD_SIZE_MAX = 17179869184L;
  
  public static final int BLOCK_HEADER_SIZE_MAX = 1024;
  
  public static final long VLI_MAX = 9223372036854775807L;
  
  public static final int VLI_SIZE_MAX = 9;
  
  public static int getVLISize(long paramLong) {
    byte b = 0;
    while (true) {
      b++;
      paramLong >>= 7L;
      if (paramLong == 0L)
        return b; 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\common\Util.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */