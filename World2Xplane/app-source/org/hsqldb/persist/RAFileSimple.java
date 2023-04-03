package org.hsqldb.persist;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.hsqldb.Database;
import org.hsqldb.error.Error;

final class RAFileSimple implements RandomAccessInterface {
  final RandomAccessFile file;
  
  final boolean readOnly;
  
  final Database database;
  
  RAFileSimple(Database paramDatabase, String paramString1, String paramString2) throws FileNotFoundException, IOException {
    this.file = new RandomAccessFile(paramString1, paramString2);
    this.database = paramDatabase;
    this.readOnly = paramString2.equals("r");
  }
  
  public long length() throws IOException {
    return this.file.length();
  }
  
  public void seek(long paramLong) throws IOException {
    this.file.seek(paramLong);
  }
  
  public long getFilePointer() throws IOException {
    return this.file.getFilePointer();
  }
  
  public int read() throws IOException {
    return this.file.read();
  }
  
  public long readLong() throws IOException {
    return this.file.readLong();
  }
  
  public int readInt() throws IOException {
    return this.file.readInt();
  }
  
  public void read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.file.readFully(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.file.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeInt(int paramInt) throws IOException {
    this.file.writeInt(paramInt);
  }
  
  public void writeLong(long paramLong) throws IOException {
    this.file.writeLong(paramLong);
  }
  
  public void close() throws IOException {
    this.file.close();
  }
  
  public boolean isReadOnly() {
    return this.readOnly;
  }
  
  public boolean ensureLength(long paramLong) {
    try {
      if (!this.readOnly && this.file.length() < paramLong) {
        this.file.seek(paramLong - 1L);
        this.file.writeByte(0);
      } 
    } catch (IOException iOException) {
      this.database.logger.logWarningEvent("data file enlarge failed ", iOException);
      return false;
    } 
    return true;
  }
  
  public boolean setLength(long paramLong) {
    try {
      this.file.setLength(paramLong);
      return true;
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public Database getDatabase() {
    return null;
  }
  
  public void synch() {
    try {
      this.file.getFD().sync();
    } catch (Throwable throwable) {
      try {
        this.file.getFD().sync();
      } catch (Throwable throwable1) {
        this.database.logger.logSevereEvent("RA file sync error ", throwable);
        throw Error.error(throwable, 452, null);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RAFileSimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */