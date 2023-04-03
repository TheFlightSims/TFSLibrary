package org.hsqldb.types;

import java.io.IOException;
import java.io.InputStream;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.java.JavaSystem;

public class BlobInputStream extends InputStream {
  final BlobData blob;
  
  final long availableLength;
  
  long bufferOffset;
  
  long currentPosition;
  
  byte[] buffer;
  
  boolean isClosed;
  
  int streamBlockSize;
  
  public final SessionInterface session;
  
  public BlobInputStream(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong1, long paramLong2) {
    long l = paramBlobData.length(paramSessionInterface);
    this.session = paramSessionInterface;
    this.blob = paramBlobData;
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
    int i = this.buffer[(int)(this.currentPosition - this.bufferOffset)] & 0xFF;
    this.currentPosition++;
    return i;
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
    if (this.isClosed || this.blob.isClosed())
      throw new IOException(Error.getMessage(3475)); 
  }
  
  private void readIntoBuffer() {
    long l = this.availableLength - this.currentPosition;
    if (l <= 0L)
      return; 
    if (l > this.streamBlockSize)
      l = this.streamBlockSize; 
    this.buffer = this.blob.getBytes(this.session, this.currentPosition, (int)l);
    this.bufferOffset = this.currentPosition;
  }
  
  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3) {
    return (paramLong2 >= 0L && paramLong3 >= 0L && paramLong2 + paramLong3 <= paramLong1);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\BlobInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */