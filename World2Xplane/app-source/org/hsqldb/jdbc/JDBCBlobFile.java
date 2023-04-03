package org.hsqldb.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hsqldb.lib.CountdownInputStream;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.InOutUtil;
import org.hsqldb.lib.KMPSearchAlgorithm;

public class JDBCBlobFile implements Blob {
  public static final String TEMP_FILE_PREFIX = "hsql_jdbc_blob_file_";
  
  public static final String TEMP_FILE_SUFFIX = ".tmp";
  
  private final File m_file;
  
  private boolean m_closed;
  
  private boolean m_deleteOnFree;
  
  private List m_streams = new ArrayList();
  
  public long length() throws SQLException {
    checkClosed();
    try {
      return this.m_file.length();
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
  }
  
  public byte[] getBytes(long paramLong, int paramInt) throws SQLException {
    InputStream inputStream = null;
    ByteArrayOutputStream byteArrayOutputStream = null;
    int i = Math.min(8192, paramInt);
    try {
      inputStream = getBinaryStream(paramLong, paramInt);
      byteArrayOutputStream = new ByteArrayOutputStream(i);
      InOutUtil.copy(inputStream, byteArrayOutputStream, paramInt);
    } catch (SQLException sQLException) {
      throw sQLException;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (Exception exception) {} 
    } 
    return byteArrayOutputStream.toByteArray();
  }
  
  public InputStream getBinaryStream() throws SQLException {
    return getBinaryStream(1L, Long.MAX_VALUE);
  }
  
  public long position(byte[] paramArrayOfbyte, long paramLong) throws SQLException {
    if (paramLong < 1L)
      throw JDBCUtil.outOfRangeArgument("start: " + paramLong); 
    if (paramArrayOfbyte == null || paramArrayOfbyte.length == 0 || paramLong > length())
      return -1L; 
    InputStream inputStream = null;
    try {
      inputStream = getBinaryStream(paramLong, Long.MAX_VALUE);
      long l = KMPSearchAlgorithm.search(inputStream, paramArrayOfbyte, KMPSearchAlgorithm.computeTable(paramArrayOfbyte));
      return (l == -1L) ? -1L : (paramLong + l);
    } catch (SQLException sQLException) {
      throw sQLException;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (Exception exception) {} 
    } 
  }
  
  public long position(Blob paramBlob, long paramLong) throws SQLException {
    byte[] arrayOfByte;
    if (paramLong < 1L)
      throw JDBCUtil.outOfRangeArgument("start: " + paramLong); 
    long l;
    if ((l = (paramBlob == null) ? 0L : paramBlob.length()) == 0L || paramLong > length())
      return -1L; 
    if (l > 2147483647L)
      throw JDBCUtil.outOfRangeArgument("pattern.length(): " + l); 
    if (paramBlob instanceof JDBCBlob) {
      arrayOfByte = ((JDBCBlob)paramBlob).data();
    } else {
      arrayOfByte = paramBlob.getBytes(1L, (int)l);
    } 
    return position(arrayOfByte, paramLong);
  }
  
  public int setBytes(long paramLong, byte[] paramArrayOfbyte) throws SQLException {
    return setBytes(paramLong, paramArrayOfbyte, 0, (paramArrayOfbyte == null) ? 0 : paramArrayOfbyte.length);
  }
  
  public int setBytes(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws SQLException {
    if (paramArrayOfbyte == null)
      throw JDBCUtil.nullArgument("bytes"); 
    OutputStream outputStream = setBinaryStream(paramLong);
    try {
      outputStream.write(paramArrayOfbyte, paramInt1, paramInt2);
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      try {
        outputStream.close();
      } catch (Exception exception) {}
    } 
    return paramInt2;
  }
  
  public OutputStream setBinaryStream(long paramLong) throws SQLException {
    OutputStreamAdapter outputStreamAdapter;
    if (paramLong < 1L)
      throw JDBCUtil.invalidArgument("pos: " + paramLong); 
    checkClosed();
    createFile();
    try {
      outputStreamAdapter = new OutputStreamAdapter(this.m_file, paramLong - 1L) {
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              JDBCBlobFile.this.m_streams.remove(this);
            } 
          }
        };
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    this.m_streams.add(outputStreamAdapter);
    return new BufferedOutputStream(outputStreamAdapter);
  }
  
  public void truncate(long paramLong) throws SQLException {
    if (paramLong < 0L)
      throw JDBCUtil.invalidArgument("len: " + paramLong); 
    checkClosed();
    RandomAccessFile randomAccessFile = null;
    try {
      randomAccessFile = new RandomAccessFile(this.m_file, "rw");
      randomAccessFile.setLength(paramLong);
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (randomAccessFile != null)
        try {
          randomAccessFile.close();
        } catch (Exception exception) {} 
    } 
  }
  
  public synchronized void free() throws SQLException {
    if (this.m_closed)
      return; 
    this.m_closed = true;
    ArrayList arrayList = new ArrayList();
    arrayList.addAll(this.m_streams);
    this.m_streams = null;
    for (InputStream inputStream : arrayList) {
      if (inputStream instanceof InputStream) {
        try {
          ((InputStream)inputStream).close();
        } catch (Exception exception) {}
        continue;
      } 
      if (inputStream instanceof OutputStream)
        try {
          ((OutputStream)inputStream).close();
        } catch (Exception exception) {} 
    } 
    if (this.m_deleteOnFree)
      try {
        this.m_file.delete();
      } catch (Exception exception) {} 
  }
  
  public InputStream getBinaryStream(long paramLong1, long paramLong2) throws SQLException {
    InputStreamAdapter inputStreamAdapter;
    if (paramLong1 < 1L)
      throw JDBCUtil.outOfRangeArgument("pos: " + paramLong1); 
    checkClosed();
    try {
      inputStreamAdapter = new InputStreamAdapter(this.m_file, paramLong1 - 1L, paramLong2) {
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              JDBCBlobFile.this.m_streams.remove(this);
            } 
          }
        };
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    this.m_streams.add(inputStreamAdapter);
    return inputStreamAdapter;
  }
  
  public File getFile() {
    return this.m_file;
  }
  
  public boolean isDeleteOnFree() {
    return this.m_deleteOnFree;
  }
  
  public void setDeleteOnFree(boolean paramBoolean) {
    this.m_deleteOnFree = paramBoolean;
  }
  
  protected void finalize() throws Throwable {
    try {
      super.finalize();
    } finally {
      free();
    } 
  }
  
  public JDBCBlobFile() throws SQLException {
    this(true);
  }
  
  public JDBCBlobFile(boolean paramBoolean) throws SQLException {
    this.m_deleteOnFree = paramBoolean;
    try {
      this.m_file = File.createTempFile("hsql_jdbc_blob_file_", ".tmp").getCanonicalFile();
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
  }
  
  public JDBCBlobFile(File paramFile) throws SQLException {
    this(paramFile, false);
  }
  
  public JDBCBlobFile(File paramFile, boolean paramBoolean) throws SQLException {
    this.m_deleteOnFree = paramBoolean;
    try {
      this.m_file = paramFile.getCanonicalFile();
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    checkIsFile(false);
  }
  
  protected final void checkIsFile(boolean paramBoolean) throws SQLException {
    boolean bool1 = false;
    boolean bool2 = false;
    try {
      bool1 = this.m_file.exists();
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    if (bool1)
      try {
        bool2 = this.m_file.isFile();
      } catch (Exception exception) {
        throw JDBCUtil.sqlException(exception);
      }  
    if (bool1) {
      if (!bool2)
        throw JDBCUtil.invalidArgument("Is not a file: " + this.m_file); 
    } else if (paramBoolean) {
      throw JDBCUtil.invalidArgument("Does not exist: " + this.m_file);
    } 
  }
  
  private void checkClosed() throws SQLException {
    if (this.m_closed)
      throw JDBCUtil.sqlException(1251); 
  }
  
  private void createFile() throws SQLException {
    try {
      if (!this.m_file.exists()) {
        FileUtil.getFileUtil().makeParentDirectories(this.m_file);
        this.m_file.createNewFile();
      } 
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    checkIsFile(true);
  }
  
  static class InputStreamAdapter extends InputStream {
    private final CountdownInputStream m_countdownInputStream;
    
    InputStreamAdapter(File param1File, long param1Long1, long param1Long2) throws FileNotFoundException, IOException {
      if (param1File == null)
        throw new NullPointerException("file"); 
      if (param1Long1 < 0L)
        throw new IllegalArgumentException("pos: " + param1Long1); 
      if (param1Long2 < 0L)
        throw new IllegalArgumentException("length: " + param1Long2); 
      FileInputStream fileInputStream = new FileInputStream(param1File);
      if (param1Long1 > 0L)
        long l = fileInputStream.skip(param1Long1); 
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      CountdownInputStream countdownInputStream = new CountdownInputStream(bufferedInputStream);
      countdownInputStream.setCount(param1Long2);
      this.m_countdownInputStream = countdownInputStream;
    }
    
    public int available() throws IOException {
      return this.m_countdownInputStream.available();
    }
    
    public int read() throws IOException {
      return this.m_countdownInputStream.read();
    }
    
    public int read(byte[] param1ArrayOfbyte) throws IOException {
      return this.m_countdownInputStream.read(param1ArrayOfbyte);
    }
    
    public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      return this.m_countdownInputStream.read(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public long skip(long param1Long) throws IOException {
      return this.m_countdownInputStream.skip(param1Long);
    }
    
    public void close() throws IOException {
      this.m_countdownInputStream.close();
    }
  }
  
  protected static class OutputStreamAdapter extends OutputStream {
    private final RandomAccessFile m_randomAccessFile;
    
    public OutputStreamAdapter(File param1File, long param1Long) throws FileNotFoundException, IOException {
      if (param1Long < 0L)
        throw new IllegalArgumentException("pos: " + param1Long); 
      this.m_randomAccessFile = new RandomAccessFile(param1File, "rw");
      this.m_randomAccessFile.seek(param1Long);
    }
    
    public void write(int param1Int) throws IOException {
      this.m_randomAccessFile.write(param1Int);
    }
    
    public void write(byte[] param1ArrayOfbyte) throws IOException {
      this.m_randomAccessFile.write(param1ArrayOfbyte);
    }
    
    public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      this.m_randomAccessFile.write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void flush() throws IOException {
      this.m_randomAccessFile.getFD().sync();
    }
    
    public void close() throws IOException {
      this.m_randomAccessFile.close();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCBlobFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */