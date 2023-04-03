package org.hsqldb.persist;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.hsqldb.Database;
import org.hsqldb.error.Error;

public class LobStoreInJar implements LobStore {
  final int lobBlockSize;
  
  Database database;
  
  DataInputStream dataInput;
  
  final String fileName;
  
  long realPosition;
  
  public LobStoreInJar(Database paramDatabase, int paramInt) {
    this.lobBlockSize = paramInt;
    this.database = paramDatabase;
    try {
      this.fileName = paramDatabase.getPath() + ".lobs";
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public byte[] getBlockBytes(int paramInt1, int paramInt2) {
    try {
      long l = paramInt1 * this.lobBlockSize;
      int i = paramInt2 * this.lobBlockSize;
      byte[] arrayOfByte = new byte[i];
      fileSeek(l);
      this.dataInput.readFully(arrayOfByte, 0, i);
      this.realPosition = l + i;
      return arrayOfByte;
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void setBlockBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {}
  
  public void setBlockBytes(byte[] paramArrayOfbyte, long paramLong, int paramInt1, int paramInt2) {}
  
  public int getBlockSize() {
    return this.lobBlockSize;
  }
  
  public long getLength() {
    return 0L;
  }
  
  public void setLength(long paramLong) {}
  
  public void close() {
    try {
      if (this.dataInput != null)
        this.dataInput.close(); 
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void synch() {}
  
  private void resetStream() throws IOException {
    if (this.dataInput != null)
      this.dataInput.close(); 
    InputStream inputStream = null;
    try {
      inputStream = getClass().getResourceAsStream(this.fileName);
      if (inputStream == null) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null)
          inputStream = classLoader.getResourceAsStream(this.fileName); 
      } 
    } catch (Throwable throwable) {
    
    } finally {
      if (inputStream == null)
        throw new FileNotFoundException(this.fileName); 
    } 
    this.dataInput = new DataInputStream(inputStream);
    this.realPosition = 0L;
  }
  
  private void fileSeek(long paramLong) throws IOException {
    if (this.dataInput == null)
      resetStream(); 
    long l = this.realPosition;
    if (paramLong < l) {
      resetStream();
      l = 0L;
    } 
    while (paramLong > l)
      l += this.dataInput.skip(paramLong - l); 
    this.realPosition = paramLong;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\LobStoreInJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */