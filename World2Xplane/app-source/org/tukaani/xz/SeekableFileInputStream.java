package org.tukaani.xz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SeekableFileInputStream extends SeekableInputStream {
  protected RandomAccessFile randomAccessFile;
  
  public SeekableFileInputStream(File paramFile) throws FileNotFoundException {
    this.randomAccessFile = new RandomAccessFile(paramFile, "r");
  }
  
  public SeekableFileInputStream(String paramString) throws FileNotFoundException {
    this.randomAccessFile = new RandomAccessFile(paramString, "r");
  }
  
  public SeekableFileInputStream(RandomAccessFile paramRandomAccessFile) {
    this.randomAccessFile = paramRandomAccessFile;
  }
  
  public int read() throws IOException {
    return this.randomAccessFile.read();
  }
  
  public int read(byte[] paramArrayOfbyte) throws IOException {
    return this.randomAccessFile.read(paramArrayOfbyte);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    return this.randomAccessFile.read(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void close() throws IOException {
    this.randomAccessFile.close();
  }
  
  public long length() throws IOException {
    return this.randomAccessFile.length();
  }
  
  public long position() throws IOException {
    return this.randomAccessFile.getFilePointer();
  }
  
  public void seek(long paramLong) throws IOException {
    this.randomAccessFile.seek(paramLong);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\SeekableFileInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */