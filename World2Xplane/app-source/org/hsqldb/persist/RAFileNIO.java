package org.hsqldb.persist;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.hsqldb.Database;
import org.hsqldb.error.Error;
import org.hsqldb.lib.java.JavaSystem;

final class RAFileNIO implements RandomAccessInterface {
  private final Database database;
  
  private final boolean readOnly;
  
  private final long maxLength;
  
  private long fileLength;
  
  private RandomAccessFile file;
  
  private FileDescriptor fileDescriptor;
  
  private MappedByteBuffer buffer;
  
  private long bufferPosition;
  
  private int bufferLength;
  
  private long currentPosition;
  
  private FileChannel channel;
  
  private boolean buffersModified;
  
  private MappedByteBuffer[] buffers = new MappedByteBuffer[0];
  
  private static final String JVM_ERROR = "NIO access failed";
  
  static final int largeBufferScale = 24;
  
  static final int largeBufferSize = 16777216;
  
  static final long largeBufferMask = -16777216L;
  
  RAFileNIO(Database paramDatabase, String paramString, boolean paramBoolean, long paramLong1, long paramLong2) throws IOException {
    this.database = paramDatabase;
    this.maxLength = paramLong2;
    File file = new File(paramString);
    if (paramBoolean) {
      paramLong1 = file.length();
    } else {
      if (file.length() > paramLong1)
        paramLong1 = file.length(); 
      paramLong1 = RAFile.getBinaryNormalisedCeiling(paramLong1, 24);
    } 
    this.file = new RandomAccessFile(paramString, paramBoolean ? "r" : "rw");
    this.readOnly = paramBoolean;
    this.channel = this.file.getChannel();
    this.fileDescriptor = this.file.getFD();
    if (ensureLength(paramLong1)) {
      this.buffer = this.buffers[0];
      this.bufferLength = this.buffer.limit();
      this.bufferPosition = 0L;
      this.currentPosition = 0L;
    } else {
      close();
      IOException iOException = new IOException("NIO buffer allocation failed");
      throw iOException;
    } 
  }
  
  public long length() throws IOException {
    try {
      return this.file.length();
    } catch (IOException iOException) {
      this.database.logger.logWarningEvent("NIO access failed", iOException);
      throw iOException;
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = new IOException(throwable.toString());
      try {
        iOException.initCause(throwable);
      } catch (Throwable throwable1) {}
      throw iOException;
    } 
  }
  
  public void seek(long paramLong) throws IOException {
    try {
      positionBufferSeek(paramLong);
      this.buffer.position((int)(paramLong - this.bufferPosition));
    } catch (IllegalArgumentException illegalArgumentException) {
      this.database.logger.logWarningEvent("NIO access failed", illegalArgumentException);
      IOException iOException = JavaSystem.toIOException(illegalArgumentException);
      throw iOException;
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public long getFilePointer() throws IOException {
    try {
      return this.currentPosition;
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = new IOException(throwable.toString());
      try {
        iOException.initCause(throwable);
      } catch (Throwable throwable1) {}
      throw iOException;
    } 
  }
  
  public int read() throws IOException {
    try {
      byte b = this.buffer.get();
      positionBufferMove(1);
      return b;
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = new IOException(throwable.toString());
      try {
        iOException.initCause(throwable);
      } catch (Throwable throwable1) {}
      throw iOException;
    } 
  }
  
  public void read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    try {
      do {
        long l = this.bufferPosition + this.bufferLength - this.currentPosition;
        if (l > paramInt2)
          l = paramInt2; 
        this.buffer.get(paramArrayOfbyte, paramInt1, (int)l);
        positionBufferMove((int)l);
        paramInt2 = (int)(paramInt2 - l);
        paramInt1 = (int)(paramInt1 + l);
      } while (paramInt2 != 0);
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public int readInt() throws IOException {
    try {
      int i = this.buffer.getInt();
      positionBufferMove(4);
      return i;
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public long readLong() throws IOException {
    try {
      long l = this.buffer.getLong();
      positionBufferMove(8);
      return l;
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    try {
      this.buffersModified = true;
      do {
        long l = this.bufferPosition + this.bufferLength - this.currentPosition;
        if (l > paramInt2)
          l = paramInt2; 
        this.buffer.put(paramArrayOfbyte, paramInt1, (int)l);
        positionBufferMove((int)l);
        paramInt2 = (int)(paramInt2 - l);
        paramInt1 = (int)(paramInt1 + l);
      } while (paramInt2 != 0);
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public void writeInt(int paramInt) throws IOException {
    try {
      this.buffersModified = true;
      this.buffer.putInt(paramInt);
      positionBufferMove(4);
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public void writeLong(long paramLong) throws IOException {
    try {
      this.buffersModified = true;
      this.buffer.putLong(paramLong);
      positionBufferMove(8);
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO access failed", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public void close() throws IOException {
    try {
      this.database.logger.logDetailEvent("NIO file close, size: " + this.fileLength);
      this.buffer = null;
      this.channel = null;
      for (byte b = 0; b < this.buffers.length; b++) {
        unmap(this.buffers[b]);
        this.buffers[b] = null;
      } 
      this.file.close();
    } catch (Throwable throwable) {
      this.database.logger.logWarningEvent("NIO buffer close error", throwable);
      IOException iOException = JavaSystem.toIOException(throwable);
      throw iOException;
    } 
  }
  
  public boolean isReadOnly() {
    return this.readOnly;
  }
  
  public boolean ensureLength(long paramLong) {
    if (paramLong > this.maxLength)
      return false; 
    while (paramLong > this.fileLength) {
      if (!enlargeFile(paramLong))
        return false; 
    } 
    return true;
  }
  
  private boolean enlargeFile(long paramLong) {
    try {
      long l = paramLong;
      if (!this.readOnly)
        l = 16777216L; 
      FileChannel.MapMode mapMode = this.readOnly ? FileChannel.MapMode.READ_ONLY : FileChannel.MapMode.READ_WRITE;
      if (!this.readOnly && this.file.length() < this.fileLength + l) {
        this.file.seek(this.fileLength + l - 1L);
        this.file.writeByte(0);
      } 
      MappedByteBuffer[] arrayOfMappedByteBuffer = new MappedByteBuffer[this.buffers.length + 1];
      MappedByteBuffer mappedByteBuffer = this.channel.map(mapMode, this.fileLength, l);
      System.arraycopy(this.buffers, 0, arrayOfMappedByteBuffer, 0, this.buffers.length);
      arrayOfMappedByteBuffer[this.buffers.length] = mappedByteBuffer;
      this.buffers = arrayOfMappedByteBuffer;
      this.fileLength += l;
      this.database.logger.logDetailEvent("NIO buffer instance, file size " + this.fileLength);
    } catch (Throwable throwable) {
      this.database.logger.logDetailEvent("NOI buffer allocate failed, file size " + paramLong);
      return false;
    } 
    return true;
  }
  
  public boolean setLength(long paramLong) {
    if (paramLong > this.fileLength)
      return enlargeFile(paramLong); 
    try {
      seek(0L);
    } catch (Throwable throwable) {}
    return true;
  }
  
  public Database getDatabase() {
    return null;
  }
  
  public void synch() {
    boolean bool = false;
    byte b1 = 0;
    byte b2;
    for (b2 = 0; b2 < this.buffers.length; b2++) {
      try {
        this.buffers[b2].force();
      } catch (Throwable throwable) {
        this.database.logger.logWarningEvent("NIO buffer force error: pos " + (b2 * 16777216) + " ", throwable);
        if (!bool)
          b1 = b2; 
        bool = true;
      } 
    } 
    if (bool)
      for (b2 = b1; b2 < this.buffers.length; b2++) {
        try {
          this.buffers[b2].force();
        } catch (Throwable throwable) {
          this.database.logger.logWarningEvent("NIO buffer force error " + (b2 * 16777216) + " ", throwable);
        } 
      }  
    try {
      this.fileDescriptor.sync();
      this.buffersModified = false;
    } catch (Throwable throwable) {
      this.database.logger.logSevereEvent("NIO RA file sync error ", throwable);
      throw Error.error(throwable, 452, null);
    } 
  }
  
  private void positionBufferSeek(long paramLong) {
    if (paramLong < this.bufferPosition || paramLong >= this.bufferPosition + this.bufferLength)
      setCurrentBuffer(paramLong); 
    this.buffer.position((int)(paramLong - this.bufferPosition));
    this.currentPosition = paramLong;
  }
  
  private void positionBufferMove(int paramInt) {
    long l = this.currentPosition + paramInt;
    if (l >= this.bufferPosition + this.bufferLength)
      setCurrentBuffer(l); 
    this.buffer.position((int)(l - this.bufferPosition));
    this.currentPosition = l;
  }
  
  private void setCurrentBuffer(long paramLong) {
    int i = (int)(paramLong >> 24L);
    if (i == this.buffers.length) {
      i = this.buffers.length - 1;
      this.bufferPosition = 16777216L;
      this.buffer = this.buffers[i];
      return;
    } 
    this.buffer = this.buffers[i];
    this.bufferPosition = paramLong &= 0xFFFFFFFFFF000000L;
  }
  
  private void unmap(MappedByteBuffer paramMappedByteBuffer) throws IOException {
    if (paramMappedByteBuffer == null)
      return; 
    try {
      Method method1 = paramMappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
      method1.setAccessible(true);
      Object object = method1.invoke(paramMappedByteBuffer, new Object[0]);
      Method method2 = object.getClass().getMethod("clean", new Class[0]);
      method2.invoke(object, new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {
    
    } catch (Throwable throwable) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\RAFileNIO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */