package org.hsqldb.persist;

import java.io.File;
import java.io.IOException;
import org.hsqldb.Database;

public final class RAFileHybrid implements RandomAccessInterface {
  final Database database;
  
  final String fileName;
  
  final boolean isReadOnly;
  
  boolean preNio;
  
  boolean isNio;
  
  long initialMaxLength = 8388608L;
  
  RandomAccessInterface store;
  
  public RAFileHybrid(Database paramDatabase, String paramString, boolean paramBoolean) throws IOException {
    this.database = paramDatabase;
    this.fileName = paramString;
    this.isReadOnly = paramBoolean;
    File file = new File(paramString);
    long l = file.length();
    newStore(l);
  }
  
  public long length() throws IOException {
    return this.store.length();
  }
  
  public void seek(long paramLong) throws IOException {
    this.store.seek(paramLong);
  }
  
  public long getFilePointer() throws IOException {
    return this.store.getFilePointer();
  }
  
  public int read() throws IOException {
    return this.store.read();
  }
  
  public void read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.store.read(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.store.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public int readInt() throws IOException {
    return this.store.readInt();
  }
  
  public void writeInt(int paramInt) throws IOException {
    this.store.writeInt(paramInt);
  }
  
  public long readLong() throws IOException {
    return this.store.readLong();
  }
  
  public void writeLong(long paramLong) throws IOException {
    this.store.writeLong(paramLong);
  }
  
  public void close() throws IOException {
    this.store.close();
  }
  
  public boolean isReadOnly() {
    return this.store.isReadOnly();
  }
  
  public boolean ensureLength(long paramLong) {
    if (paramLong <= this.initialMaxLength)
      return this.store.ensureLength(paramLong); 
    if (this.preNio)
      try {
        newStore(paramLong);
      } catch (IOException iOException) {} 
    if (this.store.ensureLength(paramLong))
      return true; 
    if (this.isNio)
      try {
        newStore(paramLong);
      } catch (IOException iOException) {} 
    return this.store.ensureLength(paramLong);
  }
  
  public boolean setLength(long paramLong) {
    return this.store.setLength(paramLong);
  }
  
  public Database getDatabase() {
    return null;
  }
  
  public void synch() {
    this.store.synch();
  }
  
  void newStore(long paramLong) throws IOException {
    long l = 0L;
    if (this.store == null) {
      this.preNio = (paramLong <= this.database.logger.propNioMaxSize);
    } else {
      l = this.store.getFilePointer();
      this.store.synch();
      this.store.close();
    } 
    if (this.preNio && this.initialMaxLength <= paramLong)
      try {
        this.store = new RAFileNIO(this.database, this.fileName, this.isReadOnly, paramLong, this.database.logger.propNioMaxSize);
        this.store.seek(l);
        this.preNio = false;
        this.isNio = true;
        return;
      } catch (Throwable throwable) {
        this.preNio = false;
      }  
    this.isNio = false;
    this.store = new RAFile(this.database, this.fileName, this.isReadOnly, true, false);
    this.store.seek(l);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RAFileHybrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */