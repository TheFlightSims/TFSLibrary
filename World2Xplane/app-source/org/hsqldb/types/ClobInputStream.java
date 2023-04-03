package org.hsqldb.types;

import java.io.IOException;
import java.io.Reader;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.java.JavaSystem;

public final class ClobInputStream extends Reader {
  final ClobData clob;
  
  final long availableLength;
  
  long bufferOffset;
  
  long currentPosition;
  
  char[] buffer;
  
  boolean isClosed;
  
  int streamBlockSize;
  
  public final SessionInterface session;
  
  public ClobInputStream(SessionInterface paramSessionInterface, ClobData paramClobData, long paramLong1, long paramLong2) {
    long l = paramClobData.length(paramSessionInterface);
    this.session = paramSessionInterface;
    this.clob = paramClobData;
    this.availableLength = paramLong1 + Math.min(paramLong2, l - paramLong1);
    this.currentPosition = paramLong1;
    this.streamBlockSize = paramSessionInterface.getStreamBlockSize();
  }
  
  public int read() throws IOException {
    checkClosed();
    if (this.currentPosition >= this.availableLength)
      return -1; 
    if (this.buffer == null || this.currentPosition >= this.bufferOffset + this.buffer.length)
      try {
        checkClosed();
        readIntoBuffer();
      } catch (Exception exception) {
        throw JavaSystem.toIOException(exception);
      }  
    char c = this.buffer[(int)(this.currentPosition - this.bufferOffset)];
    this.currentPosition++;
    return c;
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    checkClosed();
    if (this.currentPosition == this.availableLength)
      return -1; 
    if (this.currentPosition + paramInt2 > this.availableLength)
      paramInt2 = (int)(this.availableLength - this.currentPosition); 
    for (int i = paramInt1; i < paramInt2; i++)
      paramArrayOfchar[i] = (char)read(); 
    return paramInt2;
  }
  
  public long skip(long paramLong) throws IOException {
    checkClosed();
    if (paramLong <= 0L)
      return 0L; 
    if (this.currentPosition + paramLong > this.availableLength)
      paramLong = this.availableLength - this.currentPosition; 
    this.currentPosition += paramLong;
    return paramLong;
  }
  
  public int available() {
    long l = this.availableLength - this.currentPosition;
    return (l > 2147483647L) ? Integer.MAX_VALUE : (int)l;
  }
  
  public void close() throws IOException {
    this.isClosed = true;
  }
  
  private void checkClosed() throws IOException {
    if (this.isClosed)
      throw new IOException(Error.getMessage(3475)); 
  }
  
  private void readIntoBuffer() {
    // Byte code:
    //   0: aload_0
    //   1: getfield availableLength : J
    //   4: aload_0
    //   5: getfield currentPosition : J
    //   8: lsub
    //   9: lstore_1
    //   10: lload_1
    //   11: lconst_0
    //   12: lcmp
    //   13: ifgt -> 16
    //   16: lload_1
    //   17: aload_0
    //   18: getfield streamBlockSize : I
    //   21: i2l
    //   22: lcmp
    //   23: ifle -> 32
    //   26: aload_0
    //   27: getfield streamBlockSize : I
    //   30: i2l
    //   31: lstore_1
    //   32: aload_0
    //   33: aload_0
    //   34: getfield clob : Lorg/hsqldb/types/ClobData;
    //   37: aload_0
    //   38: getfield session : Lorg/hsqldb/SessionInterface;
    //   41: aload_0
    //   42: getfield currentPosition : J
    //   45: lload_1
    //   46: l2i
    //   47: invokeinterface getChars : (Lorg/hsqldb/SessionInterface;JI)[C
    //   52: putfield buffer : [C
    //   55: aload_0
    //   56: aload_0
    //   57: getfield currentPosition : J
    //   60: putfield bufferOffset : J
    //   63: return
  }
  
  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3) {
    return (paramLong2 >= 0L && paramLong3 >= 0L && paramLong2 + paramLong3 <= paramLong1);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\ClobInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */