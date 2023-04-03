package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.error.Error;

public class LobStoreRAFile implements LobStore {
  final int lobBlockSize;
  
  RandomAccessInterface file;
  
  Database database;
  
  public LobStoreRAFile(Database paramDatabase, int paramInt) {
    this.lobBlockSize = paramInt;
    this.database = paramDatabase;
    try {
      String str = paramDatabase.getPath() + ".lobs";
      boolean bool = paramDatabase.logger.getFileAccess().isStreamElement(str);
      if (bool)
        openFile(); 
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  private void openFile() {
    try {
      String str = this.database.getPath() + ".lobs";
      boolean bool = this.database.isFilesReadOnly();
      if (this.database.logger.isStoredFileAccess()) {
        this.file = RAFile.newScaledRAFile(this.database, str, bool, 3);
      } else {
        this.file = new RAFileSimple(this.database, str, bool ? "r" : "rws");
      } 
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public byte[] getBlockBytes(int paramInt1, int paramInt2) {
    if (this.file == null)
      throw Error.error(452); 
    try {
      long l = paramInt1 * this.lobBlockSize;
      int i = paramInt2 * this.lobBlockSize;
      byte[] arrayOfByte = new byte[i];
      this.file.seek(l);
      this.file.read(arrayOfByte, 0, i);
      return arrayOfByte;
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void setBlockBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this.file == null)
      openFile(); 
    try {
      long l = paramInt1 * this.lobBlockSize;
      int i = paramInt2 * this.lobBlockSize;
      this.file.seek(l);
      this.file.write(paramArrayOfbyte, 0, i);
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void setBlockBytes(byte[] paramArrayOfbyte, long paramLong, int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return; 
    if (this.file == null)
      openFile(); 
    try {
      this.file.seek(paramLong);
      this.file.write(paramArrayOfbyte, paramInt1, paramInt2);
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public int getBlockSize() {
    return this.lobBlockSize;
  }
  
  public long getLength() {
    if (this.file == null)
      openFile(); 
    try {
      return this.file.length();
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void setLength(long paramLong) {
    try {
      if (this.file != null) {
        this.file.setLength(paramLong);
        this.file.synch();
      } 
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void close() {
    try {
      if (this.file != null) {
        this.file.synch();
        this.file.close();
      } 
    } catch (Throwable throwable) {
      throw Error.error(466, throwable);
    } 
  }
  
  public void synch() {
    if (this.file != null)
      this.file.synch(); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\LobStoreRAFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */