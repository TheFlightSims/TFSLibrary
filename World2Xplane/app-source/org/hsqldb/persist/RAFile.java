package org.hsqldb.persist;

import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.hsqldb.Database;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlByteArrayInputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.Storage;

final class RAFile implements RandomAccessInterface {
  static final int DATA_FILE_RAF = 0;
  
  static final int DATA_FILE_NIO = 1;
  
  static final int DATA_FILE_JAR = 2;
  
  static final int DATA_FILE_STORED = 3;
  
  static final int DATA_FILE_SINGLE = 4;
  
  static final int DATA_FILE_TEXT = 5;
  
  static final int bufferScale = 12;
  
  static final int bufferSize = 4096;
  
  static final long bufferMask = -4096L;
  
  final Database database;
  
  final RandomAccessFile file;
  
  final FileDescriptor fileDescriptor;
  
  private final boolean readOnly;
  
  final String fileName;
  
  final byte[] buffer;
  
  final HsqlByteArrayInputStream ba;
  
  final byte[] valueBuffer;
  
  final HsqlByteArrayOutputStream vbao;
  
  final HsqlByteArrayInputStream vbai;
  
  long bufferOffset;
  
  long fileLength;
  
  final boolean extendLength;
  
  long seekPosition;
  
  int cacheHit;
  
  static RandomAccessInterface newScaledRAFile(Database paramDatabase, String paramString, boolean paramBoolean, int paramInt) throws FileNotFoundException, IOException {
    if (paramInt == 3)
      try {
        Class<?> clazz;
        String str1 = paramDatabase.getURLProperties().getProperty("storage_class_name");
        String str2 = paramDatabase.getURLProperties().getProperty("storage_key");
        try {
          ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          clazz = classLoader.loadClass(str1);
        } catch (ClassNotFoundException classNotFoundException) {
          clazz = Class.forName(str1);
        } 
        Constructor<?> constructor = clazz.getConstructor(new Class[] { String.class, Boolean.class, Object.class });
        Object object = constructor.newInstance(new Object[] { paramString, Boolean.valueOf(paramBoolean), str2 });
        if (object instanceof RandomAccessInterface)
          return (RandomAccessInterface)object; 
        if (object instanceof Storage)
          return new RAStorageWrapper((Storage)object); 
        throw new IOException();
      } catch (ClassNotFoundException classNotFoundException) {
        throw new IOException();
      } catch (NoSuchMethodException noSuchMethodException) {
        throw new IOException();
      } catch (InstantiationException instantiationException) {
        throw new IOException();
      } catch (IllegalAccessException illegalAccessException) {
        throw new IOException();
      } catch (InvocationTargetException invocationTargetException) {
        throw new IOException();
      }  
    if (paramInt == 2)
      return new RAFileInJar(paramString); 
    if (paramInt == 5)
      return new RAFile(paramDatabase, paramString, paramBoolean, false, true); 
    if (paramInt == 0)
      return new RAFile(paramDatabase, paramString, paramBoolean, true, false); 
    File file = new File(paramString);
    long l = file.length();
    if (l > paramDatabase.logger.propNioMaxSize)
      return new RAFile(paramDatabase, paramString, paramBoolean, true, false); 
    try {
      Class.forName("java.nio.MappedByteBuffer");
      return new RAFileHybrid(paramDatabase, paramString, paramBoolean);
    } catch (Exception exception) {
      return new RAFile(paramDatabase, paramString, paramBoolean, true, false);
    } 
  }
  
  RAFile(Database paramDatabase, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws FileNotFoundException, IOException {
    this.database = paramDatabase;
    this.fileName = paramString;
    this.readOnly = paramBoolean1;
    this.extendLength = paramBoolean2;
    String str = paramBoolean1 ? "r" : (paramBoolean3 ? "rws" : "rw");
    this.file = new RandomAccessFile(paramString, str);
    this.buffer = new byte[4096];
    this.ba = new HsqlByteArrayInputStream(this.buffer);
    this.valueBuffer = new byte[8];
    this.vbao = new HsqlByteArrayOutputStream(this.valueBuffer);
    this.vbai = new HsqlByteArrayInputStream(this.valueBuffer);
    this.fileDescriptor = this.file.getFD();
    this.fileLength = length();
    readIntoBuffer();
  }
  
  public long length() throws IOException {
    return this.file.length();
  }
  
  public void seek(long paramLong) throws IOException {
    if (this.readOnly && this.fileLength < paramLong)
      throw new IOException("read beyond end of file"); 
    this.seekPosition = paramLong;
  }
  
  public long getFilePointer() throws IOException {
    return this.seekPosition;
  }
  
  private void readIntoBuffer() throws IOException {
    long l1 = this.seekPosition & 0xFFFFFFFFFFFFF000L;
    long l2 = this.fileLength - l1;
    if (l2 > this.buffer.length)
      l2 = this.buffer.length; 
    if (l2 < 0L)
      throw new IOException("read beyond end of file"); 
    try {
      this.file.seek(l1);
      this.file.readFully(this.buffer, 0, (int)l2);
      this.bufferOffset = l1;
    } catch (IOException iOException) {
      resetPointer();
      this.database.logger.logWarningEvent(" " + l1 + " " + l2, iOException);
      throw iOException;
    } 
  }
  
  public int read() throws IOException {
    try {
      if (this.seekPosition >= this.fileLength)
        return -1; 
      if (this.seekPosition < this.bufferOffset || this.seekPosition >= this.bufferOffset + this.buffer.length) {
        readIntoBuffer();
      } else {
        this.cacheHit++;
      } 
      int i = this.buffer[(int)(this.seekPosition - this.bufferOffset)] & 0xFF;
      this.seekPosition++;
      return i;
    } catch (IOException iOException) {
      resetPointer();
      this.database.logger.logWarningEvent("read failed", iOException);
      throw iOException;
    } 
  }
  
  public long readLong() throws IOException {
    this.vbai.reset();
    read(this.valueBuffer, 0, 8);
    return this.vbai.readLong();
  }
  
  public int readInt() throws IOException {
    this.vbai.reset();
    read(this.valueBuffer, 0, 4);
    return this.vbai.readInt();
  }
  
  public void read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    try {
      if (this.seekPosition + paramInt2 > this.fileLength)
        throw new EOFException(); 
      if (paramInt2 > this.buffer.length && (this.seekPosition < this.bufferOffset || this.seekPosition >= this.bufferOffset + this.buffer.length)) {
        this.file.seek(this.seekPosition);
        this.file.readFully(paramArrayOfbyte, paramInt1, paramInt2);
        this.seekPosition += paramInt2;
        return;
      } 
      if (this.seekPosition < this.bufferOffset || this.seekPosition >= this.bufferOffset + this.buffer.length) {
        readIntoBuffer();
      } else {
        this.cacheHit++;
      } 
      this.ba.reset();
      if (this.seekPosition - this.bufferOffset != this.ba.skip(this.seekPosition - this.bufferOffset))
        throw new EOFException(); 
      int i = this.ba.read(paramArrayOfbyte, paramInt1, paramInt2);
      this.seekPosition += i;
      if (i < paramInt2) {
        this.file.seek(this.seekPosition);
        this.file.readFully(paramArrayOfbyte, paramInt1 + i, paramInt2 - i);
        this.seekPosition += (paramInt2 - i);
      } 
    } catch (IOException iOException) {
      resetPointer();
      this.database.logger.logWarningEvent("failed to read a byte array", iOException);
      throw iOException;
    } 
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    try {
      this.file.seek(this.seekPosition);
      if (this.seekPosition < this.bufferOffset + this.buffer.length && this.seekPosition + paramInt2 > this.bufferOffset)
        writeToBuffer(paramArrayOfbyte, paramInt1, paramInt2); 
      this.file.write(paramArrayOfbyte, paramInt1, paramInt2);
      this.seekPosition += paramInt2;
      if (!this.extendLength && this.fileLength < this.seekPosition)
        this.fileLength = this.seekPosition; 
    } catch (IOException iOException) {
      resetPointer();
      this.database.logger.logWarningEvent("failed to write a byte array", iOException);
      throw iOException;
    } 
  }
  
  public void writeInt(int paramInt) throws IOException {
    this.vbao.reset();
    this.vbao.writeInt(paramInt);
    write(this.valueBuffer, 0, 4);
  }
  
  public void writeLong(long paramLong) throws IOException {
    this.vbao.reset();
    this.vbao.writeLong(paramLong);
    write(this.valueBuffer, 0, 8);
  }
  
  public void close() throws IOException {
    this.file.close();
  }
  
  public boolean isReadOnly() {
    return this.readOnly;
  }
  
  public boolean ensureLength(long paramLong) {
    if (paramLong <= this.fileLength)
      return true; 
    try {
      extendLength(paramLong);
    } catch (IOException iOException) {
      return false;
    } 
    return true;
  }
  
  public boolean setLength(long paramLong) {
    try {
      this.file.setLength(paramLong);
      this.file.seek(0L);
      this.fileLength = this.file.length();
      this.seekPosition = 0L;
      readIntoBuffer();
      return true;
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public void synch() {
    try {
      this.fileDescriptor.sync();
    } catch (Throwable throwable) {
      try {
        this.fileDescriptor.sync();
      } catch (Throwable throwable1) {
        this.database.logger.logSevereEvent("RA file sync error ", throwable1);
        throw Error.error(throwable, 452, null);
      } 
    } 
  }
  
  private int writeToBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    return ArrayUtil.copyBytes(this.seekPosition - paramInt1, paramArrayOfbyte, paramInt1, paramInt2, this.bufferOffset, this.buffer, this.buffer.length);
  }
  
  private long getExtendLength(long paramLong) {
    byte b;
    if (!this.extendLength)
      return paramLong; 
    if (paramLong < 262144L) {
      b = 2;
    } else if (paramLong < 1048576L) {
      b = 6;
    } else if (paramLong < 33554432L) {
      b = 8;
    } else {
      b = 12;
    } 
    return getBinaryNormalisedCeiling(paramLong, 12 + b);
  }
  
  private void extendLength(long paramLong) throws IOException {
    long l = getExtendLength(paramLong);
    if (l > this.fileLength)
      try {
        this.file.seek(l - 1L);
        this.file.write(0);
        this.fileLength = l;
      } catch (IOException iOException) {
        this.database.logger.logWarningEvent("data file enlarge failed ", iOException);
        throw iOException;
      }  
  }
  
  private void resetPointer() {
    try {
      this.seekPosition = 0L;
      this.fileLength = length();
      readIntoBuffer();
    } catch (Throwable throwable) {}
  }
  
  static long getBinaryNormalisedCeiling(long paramLong, int paramInt) {
    long l1 = -1L << paramInt;
    long l2 = paramLong & l1;
    if (l2 != paramLong)
      l2 += (1 << paramInt); 
    return l2;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RAFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */