package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

public abstract class SeekableInputStream extends InputStream {
  public long skip(long paramLong) throws IOException {
    if (paramLong <= 0L)
      return 0L; 
    long l1 = length();
    long l2 = position();
    if (l2 >= l1)
      return 0L; 
    if (l1 - l2 < paramLong)
      paramLong = l1 - l2; 
    seek(l2 + paramLong);
    return paramLong;
  }
  
  public abstract long length() throws IOException;
  
  public abstract long position() throws IOException;
  
  public abstract void seek(long paramLong) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\SeekableInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */